package com.stepyen.chivox.record;

/**
 * date：2020-02-14
 * author：stepyen
 * description：
 */

import android.media.AudioRecord;
import android.os.Process;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

public class XSAudioRecorder {
    public static final int AUDIO_TYPE_PCM = 0;
    public static final int AUDIO_TYPE_WAV = 1;
    public static final int AUDIO_TYPE_ELSE = -1;
    static final int DEFAULT_SAMPLE_RATE = 16000;
    static final int DEFAULT_BUFFER_SIZE = 2048;
    static final int FLAG_RECORDER_NORMAL = 0;
    static final int FLAG_RECORDER_NULL = 99;
    static final int FLAG_RECORDER_PLAYER = 100;
    static final int FLAG_RECORDER_STOP = 101;
    static final int FLAG_RECORDER_CANCEL = 102;
    static final int FLAG_RECORDER_CANCEL_QUIET = 104;
    static final int FLAG_RECORDER_EXCEPTION = 103;
    private static final String TAG = "XSAudioRecorder";
    private final AtomicBoolean mIsRecording;
    private volatile int mCurrentFlag;
    private ExecutorService mExecutorService;

    private XSAudioRecorder() {
        this.mCurrentFlag = 0;
        this.mIsRecording = new AtomicBoolean(false);
        this.mExecutorService = Executors.newSingleThreadExecutor();
    }

    public static XSAudioRecorder getInstance() {
        return XSAudioRecorder.Instance.INSTANCE;
    }

    public synchronized boolean start(String savePath, int audioSource, int audioType, @NonNull XSAudioRecorder.OnAudioDataCallback audioDataCallback) {
        return this.start(savePath, audioSource, audioType, 16000, 16, 2, 2048, audioDataCallback);
    }

    public synchronized boolean start(String savePath, int audioSource, int audioType, int sampleRate, int channelConfig, int audioFormat, int bufferSize, @NonNull XSAudioRecorder.OnAudioDataCallback audioDataCallback) {
        if (this.mIsRecording.get()) {
            this.stop();
        }

        if (this.mIsRecording.compareAndSet(false, true)) {
            this.mExecutorService.execute(new XSAudioRecorder.AudioRecordRunnable(savePath, audioSource, audioType, sampleRate, channelConfig, audioFormat, bufferSize, audioDataCallback));
            return true;
        } else {
            return false;
        }
    }

    public synchronized void stop() {
        this.mCurrentFlag = 101;
        this.mIsRecording.compareAndSet(true, false);
    }

    public boolean isRecording() {
        return this.mIsRecording.get();
    }

    public boolean cancel() {
        this.mCurrentFlag = 102;
        return this.mIsRecording.compareAndSet(true, false);
    }

    public boolean deleteSafe() {
        this.mCurrentFlag = 104;
        return this.mIsRecording.compareAndSet(true, false);
    }

    private class AudioRecordRunnable implements Runnable {
        private final XSAudioRecorder.OnAudioDataCallback mAudioDataCallback;
        private final byte[] mByteBuffer;
        private final short[] mShortBuffer;
        private final int mByteBufferSize;
        private final int mShortBufferSize;
        private final int mAudioFormat;
        private AudioRecord mAudioRecord;
        private FileOutputStream mOutputStream;
        private int mAudioType;
        private PcmToWavUtil mPcmToWavUtil;
        private String mPcmPath;
        private String mWavPath;
        private int minBufferSize;
        private int sampleRate;
        private int channelConfig;
        private int audioFormat;
        private int byteBufferSize;
        private int bufferSizeInBytes;

        /** @deprecated */
        @Deprecated
        AudioRecordRunnable(int sampleRate, int channelConfig, int audioFormat, int byteBufferSize, @NonNull XSAudioRecorder.OnAudioDataCallback audioDataCallback) {
            this.mAudioFormat = audioFormat;
            int minBufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, this.mAudioFormat);
            this.mByteBufferSize = byteBufferSize;
            this.mShortBufferSize = this.mByteBufferSize / 2;
            this.mByteBuffer = new byte[this.mByteBufferSize];
            this.mShortBuffer = new short[this.mShortBufferSize];
            this.mAudioRecord = new AudioRecord(1, sampleRate, channelConfig, audioFormat, Math.max(minBufferSize, byteBufferSize));
            this.mAudioDataCallback = audioDataCallback;
        }

        AudioRecordRunnable(String targetPath, int audioSource, int audioType, int sampleRate, int channelConfig, int audioFormat, int byteBufferSize, @NonNull XSAudioRecorder.OnAudioDataCallback audioDataCallback) {
            this.mAudioFormat = audioFormat;
            this.minBufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, this.mAudioFormat);
            this.sampleRate = sampleRate;
            this.channelConfig = channelConfig;
            this.audioFormat = audioFormat;
            this.byteBufferSize = byteBufferSize;
            this.mByteBufferSize = byteBufferSize;
            this.mShortBufferSize = this.mByteBufferSize / 2;
            this.mByteBuffer = new byte[this.mByteBufferSize];
            this.mShortBuffer = new short[this.mShortBufferSize];
            this.bufferSizeInBytes = Math.max(this.minBufferSize, byteBufferSize);
            this.mAudioRecord = new AudioRecord(audioSource, sampleRate, channelConfig, audioFormat, this.bufferSizeInBytes);
            this.mAudioDataCallback = audioDataCallback;

            try {
                String pcm = ".pcm";
                String wav = ".wav";
                String m = ".m";
                int end;
                if (targetPath.endsWith(pcm)) {
                    this.mPcmPath = targetPath;
                    end = targetPath.lastIndexOf(pcm);
                    targetPath = targetPath.substring(0, end);
                } else if (targetPath.endsWith(wav)) {
                    end = targetPath.lastIndexOf(wav);
                    this.mPcmPath = targetPath.substring(0, end) + pcm;
                } else if (targetPath.endsWith(m)) {
                    end = targetPath.lastIndexOf(m);
                    this.mPcmPath = targetPath.substring(0, end) + pcm;
                } else {
                    this.mPcmPath = targetPath + pcm;
                }

                File file = new File(this.mPcmPath);
                File parentFile;
                if (!file.exists()) {
                    parentFile = file.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                } else {
                    parentFile = new File(file.getAbsolutePath() + System.currentTimeMillis());
                    file.renameTo(parentFile);
                    parentFile.delete();
                }

                this.mOutputStream = new FileOutputStream(file);
                this.mAudioType = audioType;
                if (this.mAudioType == 1) {
                    this.mPcmToWavUtil = PcmToWavUtil.inStance(this.bufferSizeInBytes);
                    if (!targetPath.endsWith(wav) && !targetPath.endsWith(m)) {
                        this.mWavPath = targetPath + wav;
                    } else {
                        this.mWavPath = targetPath;
                    }
                }
            } catch (FileNotFoundException var15) {
                var15.printStackTrace();
            }

        }

        public void run() {
            Process.setThreadPriority(-19);
            LogUtil.e("XSAudioRecorder", "start:   start");
            if (this.mAudioRecord.getState() == 1) {
                label283: {
                    try {
                        this.mAudioRecord.startRecording();
                        this.mAudioDataCallback.onBeginRecorder();
                    } catch (IllegalStateException var22) {
                        LogUtil.w("XSAudioRecorder", "startRecording fail: " + var22.getMessage());
                        this.mAudioDataCallback.onError(-3, var22.getMessage());
                        return;
                    }

                    int discardBytes = 3200;

                    int ret;
                    while(discardBytes > 0) {
                        ret = this.mByteBuffer.length < discardBytes ? this.mByteBuffer.length : discardBytes;
                        int readBytes = this.mAudioRecord.read(this.mByteBuffer, 0, ret);
                        if (readBytes <= 0) {
                            break;
                        }

                        discardBytes -= readBytes;
                        LogUtil.w("XSAudioRecorder", "discard: " + readBytes);
                    }

                    while(true) {
                        boolean var16 = false;

                        try {
                            var16 = true;
                            if (XSAudioRecorder.this.mIsRecording.get()) {
                                if (this.mAudioFormat == 2) {
                                    ret = this.mAudioRecord.read(this.mShortBuffer, 0, this.mShortBufferSize);
                                    if (ret >= 0) {
                                        byte[] data = this.short2byte(this.mShortBuffer, ret, this.mByteBuffer);
                                        int size = ret * 2;
                                        this.mAudioDataCallback.onAudioData(data, size);

                                        try {
                                            this.mOutputStream.write(data, 0, size);
                                        } catch (IOException var21) {
                                            var21.printStackTrace();
                                        }

                                        LogUtil.w("XSAudioRecorder", "writing:   " + data + "  " + size + "  >=0 " + ret);
                                        continue;
                                    }

                                    LogUtil.w("XSAudioRecorder", "error:   ret :  " + ret);
                                    if (ret == -3 || ret == -2) {
                                        continue;
                                    }

                                    if (ret == -6) {
                                        this.mAudioRecord = new AudioRecord(1, this.sampleRate, this.channelConfig, this.audioFormat, this.bufferSizeInBytes);
                                    }

                                    this.onError(ret);
                                    var16 = false;
                                    break;
                                }

                                ret = this.mAudioRecord.read(this.mByteBuffer, 0, this.mByteBufferSize);
                                if (ret > 0) {
                                    this.mAudioDataCallback.onAudioData(this.mByteBuffer, ret);

                                    try {
                                        this.mOutputStream.write(this.mByteBuffer, 0, ret);
                                    } catch (IOException var20) {
                                        var20.printStackTrace();
                                    }
                                    continue;
                                }

                                this.onError(ret);
                                var16 = false;
                                break;
                            }

                            var16 = false;
                            break;
                        } catch (Exception var23) {
                            var23.printStackTrace();
                            var16 = false;
                        } finally {
                            if (var16) {
                                try {
                                    this.mOutputStream.close();
                                } catch (Exception var17) {
                                    var17.printStackTrace();
                                }

                                this.mAudioRecord.release();
                                this.mAudioRecord = null;
                                if (this.mPcmToWavUtil != null) {
                                    LogUtil.w("XSAudioRecorder", "convert:   " + this.mPcmPath + "  " + this.mWavPath);
                                    this.mPcmToWavUtil.pcmToWav(this.mPcmPath, this.mWavPath);
                                }

                                LogUtil.w("XSAudioRecorder", "pcmToWav:   stop");
                                switch(XSAudioRecorder.this.mCurrentFlag) {
                                    case 101:
                                        this.mAudioDataCallback.onRecordStop();
                                        break;
                                    case 102:
                                        this.mAudioDataCallback.onCancel();
                                    case 103:
                                    default:
                                        break;
                                    case 104:
                                        this.mAudioDataCallback.onCancelQuiet();
                                }

                                XSAudioRecorder.this.mCurrentFlag = 0;
                            }
                        }

                        try {
                            this.mOutputStream.close();
                        } catch (Exception var18) {
                            var18.printStackTrace();
                        }

                        this.mAudioRecord.release();
                        this.mAudioRecord = null;
                        if (this.mPcmToWavUtil != null) {
                            LogUtil.w("XSAudioRecorder", "convert:   " + this.mPcmPath + "  " + this.mWavPath);
                            this.mPcmToWavUtil.pcmToWav(this.mPcmPath, this.mWavPath);
                        }

                        LogUtil.w("XSAudioRecorder", "pcmToWav:   stop");
                        switch(XSAudioRecorder.this.mCurrentFlag) {
                            case 101:
                                this.mAudioDataCallback.onRecordStop();
                                break;
                            case 102:
                                this.mAudioDataCallback.onCancel();
                            case 103:
                            default:
                                break;
                            case 104:
                                this.mAudioDataCallback.onCancelQuiet();
                        }

                        XSAudioRecorder.this.mCurrentFlag = 0;
                        break label283;
                    }

                    try {
                        this.mOutputStream.close();
                    } catch (Exception var19) {
                        var19.printStackTrace();
                    }

                    this.mAudioRecord.release();
                    this.mAudioRecord = null;
                    if (this.mPcmToWavUtil != null) {
                        LogUtil.w("XSAudioRecorder", "convert:   " + this.mPcmPath + "  " + this.mWavPath);
                        this.mPcmToWavUtil.pcmToWav(this.mPcmPath, this.mWavPath);
                    }

                    LogUtil.w("XSAudioRecorder", "pcmToWav:   stop");
                    switch(XSAudioRecorder.this.mCurrentFlag) {
                        case 101:
                            this.mAudioDataCallback.onRecordStop();
                            break;
                        case 102:
                            this.mAudioDataCallback.onCancel();
                        case 103:
                        default:
                            break;
                        case 104:
                            this.mAudioDataCallback.onCancelQuiet();
                    }

                    XSAudioRecorder.this.mCurrentFlag = 0;
                }
            }

            LogUtil.e("XSAudioRecorder", "over stop :   start");
        }

        private byte[] short2byte(short[] sData, int size, byte[] bData) {
            if (size > sData.length || size * 2 > bData.length) {
                LogUtil.w("XSAudioRecorder", "short2byte: too long short data array");
            }

            for(int i = 0; i < size; ++i) {
                bData[i * 2] = (byte)(sData[i] & 255);
                bData[i * 2 + 1] = (byte)(sData[i] >> 8);
            }

            return bData;
        }

        private void onError(int errorCode) {
            LogUtil.w("XSAudioRecorder", "onError <0 : " + errorCode);
            XSAudioRecorder.this.stop();
            if (errorCode == -3) {
                LogUtil.w("XSAudioRecorder", "record fail: ERROR_INVALID_OPERATION");
                this.mAudioDataCallback.onError(errorCode, "表示不恰当的方法导致的失败");
            } else if (errorCode == -2) {
                LogUtil.w("XSAudioRecorder", "record fail: ERROR_BAD_VALUE");
                this.mAudioDataCallback.onError(errorCode, "表示不恰当的方法导致的失败");
            }

        }
    }

    private static final class Instance {
        private static final XSAudioRecorder INSTANCE = new XSAudioRecorder();

        private Instance() {
        }
    }

    public interface OnAudioDataCallback {
        void onBeginRecorder();

        void onRecordStop();

        void onCancel();

        void onCancelQuiet();

        @WorkerThread
        void onAudioData(byte[] var1, int var2);

        void onError(int var1, String var2);
    }
}
