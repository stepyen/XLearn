package com.stepyen.chivox.record;

/**
 * date：2020-02-13
 * author：stepyen
 * description：
 */

import android.media.AudioRecord;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

public final class StreamAudioRecorder {
    private static final String TAG = "StreamAudioRecorder";
    private static final int DEFAULT_SAMPLE_RATE = 16000;
    private static final int DEFAULT_BUFFER_SIZE = 2048;
    public static final int FLAG_RECORDER_NULL = 99;
    public static final int FLAG_RECORDER_PLAYER = 100;
    public static final int FLAG_RECORDER_STOP = 101;
    public static final int FLAG_RECORDER_CANCEL = 102;
    public static final int FLAG_RECORDER_EXCEPTION = 103;
    public AtomicBoolean mIsRecording;
    private ExecutorService mExecutorService;
    private Boolean fileHeader;
    private int mCurrentRecoredrFlag;
    private AudioTypeEnum mAudioType;
    private AtomicBoolean mIsExceptionFlag;
    private StreamAudioRecorder.AudioRecordRunnable mRunnable;
    private static StreamAudioRecorder sStreamAudioRecorder;

    private StreamAudioRecorder() {
        this.fileHeader = true;
        this.mCurrentRecoredrFlag = 99;
        this.mAudioType = AudioTypeEnum.WAV;
        LogUtil.w("BaseSingEngine", "StreamAudioRecorder");
        this.mIsRecording = new AtomicBoolean(false);
        this.mIsExceptionFlag = new AtomicBoolean(false);
        this.mExecutorService = Executors.newSingleThreadExecutor();
    }

    public static StreamAudioRecorder getInstance() {
        return StreamAudioRecorder.StreamAudioRecorderHolder.INSTANCE;
    }

    public ExecutorService getmExecutorService() {
        return this.mExecutorService;
    }

    public int start(String path, @NonNull StreamAudioRecorder.AudioStartCompeletedCallback audioStartCompeletedCallback, @NonNull StreamAudioRecorder.AudioStopCompletedCallback stopCompletedCallback, @NonNull StreamAudioRecorder.AudioDataCallback audioDataCallback) {
        if (path != null && !TextUtils.isEmpty(path)) {
            if (this.mIsRecording.compareAndSet(false, true)) {
                this.mCurrentRecoredrFlag = 100;
                this.mRunnable = new StreamAudioRecorder.AudioRecordRunnable(2, audioStartCompeletedCallback, stopCompletedCallback, audioDataCallback, path);
                this.mExecutorService.execute(this.mRunnable);
                return 0;
            } else {
                return 2;
            }
        } else {
            LogUtil.w("StreamAudioRecorder", "can't set empty  record_path");
            return 1;
        }
    }

    public int stop() {
        this.mIsRecording.compareAndSet(true, false);
        this.mCurrentRecoredrFlag = 101;
        this.mIsExceptionFlag.compareAndSet(false, true);
        return 0;
    }

    public int clean() {
        this.mIsRecording.compareAndSet(true, false);
        this.mCurrentRecoredrFlag = 102;
        this.mIsExceptionFlag.compareAndSet(false, true);
        return 0;
    }

    public int getmCurrentRecoredrFlag() {
        return this.mCurrentRecoredrFlag;
    }

    private RandomAccessFile fopen(String path) throws IOException {
        int CHANNELS = 1;
        int BITS = 16;
        int FREQUENCY = 16000;
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        } else {
            File file = f.getParentFile();
            if (!file.exists()) {
                file.mkdirs();
            }
        }

        RandomAccessFile file1 = new RandomAccessFile(f, "rws");
        if (AudioTypeEnum.WAV == this.mAudioType && getInstance().fileHeader) {
            file1.writeBytes("RIFF");
            file1.writeInt(0);
            file1.writeBytes("WAVE");
            file1.writeBytes("fmt ");
            file1.writeInt(Integer.reverseBytes(16));
            file1.writeShort(Short.reverseBytes((short)1));
            file1.writeShort(Short.reverseBytes((short)CHANNELS));
            file1.writeInt(Integer.reverseBytes(FREQUENCY));
            file1.writeInt(Integer.reverseBytes(CHANNELS * FREQUENCY * BITS / 8));
            file1.writeShort(Short.reverseBytes((short)(CHANNELS * BITS / 8)));
            file1.writeShort(Short.reverseBytes((short)(CHANNELS * BITS)));
            file1.writeBytes("data");
            file1.writeInt(0);
            LogUtil.d("StreamAudioRecorder", "PCM to WAV");
        }

        LogUtil.d("StreamAudioRecorder", "wav path: " + path);
        return file1;
    }

    private void fwrite(RandomAccessFile file, byte[] data, int offset, int size) throws IOException {
        file.write(data, offset, size);
    }

    private void fclose(RandomAccessFile file) throws IOException {
        try {
            if (AudioTypeEnum.WAV == this.mAudioType) {
                file.seek(4L);
                file.writeInt(Integer.reverseBytes((int)(file.length() - 8L)));
                file.seek(40L);
                file.writeInt(Integer.reverseBytes((int)(file.length() - 44L)));
                LogUtil.d("StreamAudioRecorder", "wav size: " + file.length());
            }
        } finally {
            file.getFD().sync();
            file.close();
        }

    }

    public void setAudioType(AudioTypeEnum audioType) {
        this.mAudioType = audioType;
    }

    private class AudioRecordRunnable implements Runnable {
        private final StreamAudioRecorder.AudioDataCallback mAudioDataCallback;
        private final StreamAudioRecorder.AudioStartCompeletedCallback mAudioStartCompeletedCallback;
        private final StreamAudioRecorder.AudioStopCompletedCallback mAudioStopCompletedCallback;
        private final byte[] mByteBuffer;
        private final short[] mShortBuffer;
        private final int mByteBufferSize;
        private final int mShortBufferSize;
        private final int mAudioFormat;
        private RandomAccessFile file = null;
        private int minBufferSize;
        private AudioRecord mAudioRecord;

        AudioRecordRunnable(int audioFormat, @NonNull StreamAudioRecorder.AudioStartCompeletedCallback audioStartCompeletedCallback, @NonNull StreamAudioRecorder.AudioStopCompletedCallback stopCompletedCallback, @NonNull StreamAudioRecorder.AudioDataCallback audioDataCallback, String path) {
            if (this.mAudioRecord != null) {
                this.mAudioRecord.release();
                this.mAudioRecord = null;
            }

            this.minBufferSize = AudioRecord.getMinBufferSize(16000, 16, 2);
            this.mAudioRecord = new AudioRecord(1, 16000, 16, 2, Math.max(this.minBufferSize, 2048));
            this.mAudioFormat = audioFormat;
            this.mByteBufferSize = Math.max(this.minBufferSize, 2048);
            this.mShortBufferSize = this.mByteBufferSize / 2;
            this.mByteBuffer = new byte[this.mByteBufferSize];
            this.mShortBuffer = new short[this.mShortBufferSize];
            this.mAudioDataCallback = audioDataCallback;
            this.mAudioStartCompeletedCallback = audioStartCompeletedCallback;
            this.mAudioStopCompletedCallback = stopCompletedCallback;

            try {
                this.file = StreamAudioRecorder.this.fopen(path);
            } catch (Exception var8) {
                var8.printStackTrace();
            }

        }

        public void run() {
            if (this.mAudioRecord.getState() == 1) {
                try {
                    this.mAudioRecord.startRecording();
                    LogUtil.w("StreamAudioRecorder", "startRecorded");
                    this.mAudioStartCompeletedCallback.onAudioStartCompeleted();
                } catch (Exception var6) {
                    LogUtil.w("StreamAudioRecorder", "startRecording fail: " + var6.getMessage());
                    this.mAudioDataCallback.onError(-3);
                }

                try {
                    int discardBytes = 3200;

                    int ret;
                    while(discardBytes > 0) {
                        ret = this.mByteBuffer.length < discardBytes ? this.mByteBuffer.length : discardBytes;
                        int readBytes = this.mAudioRecord.read(this.mByteBuffer, 0, ret);
                        if (readBytes <= 0) {
                            break;
                        }

                        discardBytes -= readBytes;
                        LogUtil.d("StreamAudioRecorder", "discard: " + readBytes);
                    }

                    while(StreamAudioRecorder.this.mIsRecording.get()) {
                        if (this.mAudioFormat == 2) {
                            ret = this.mAudioRecord.read(this.mByteBuffer, 0, this.mByteBufferSize);
                            StreamAudioRecorder.this.mIsExceptionFlag.compareAndSet(true, false);
                            if (ret <= 0) {
                                this.onError(ret);
                                LogUtil.w("StreamAudioRecorder", "Recording error");
                                break;
                            }

                            LogUtil.w("StreamAudioRecorder", "Recording .... " + StreamAudioRecorder.this.mCurrentRecoredrFlag);
                            this.mAudioDataCallback.onAudioData(this.mByteBuffer, ret, StreamAudioRecorder.this.mIsRecording, StreamAudioRecorder.this.mCurrentRecoredrFlag);
                            if (this.file != null) {
                                StreamAudioRecorder.this.fwrite(this.file, this.mByteBuffer, 0, ret);
                            }
                        }
                    }

                    if (StreamAudioRecorder.this.mIsExceptionFlag.get() && (StreamAudioRecorder.this.mCurrentRecoredrFlag == 101 || StreamAudioRecorder.this.mCurrentRecoredrFlag == 102)) {
                        StreamAudioRecorder.this.mCurrentRecoredrFlag = 103;
                        LogUtil.w("StreamAudioRecorder", "Recording .... " + StreamAudioRecorder.this.mCurrentRecoredrFlag);
                        this.mAudioDataCallback.onAudioData(this.mByteBuffer, 0, StreamAudioRecorder.this.mIsRecording, StreamAudioRecorder.this.mCurrentRecoredrFlag);
                    }
                } catch (Exception var7) {
                    StreamAudioRecorder.this.mCurrentRecoredrFlag = 103;
                    LogUtil.w("StreamAudioRecorder", "Recording[E] .... " + StreamAudioRecorder.this.mCurrentRecoredrFlag);
                    this.mAudioDataCallback.onAudioData(this.mByteBuffer, 0, StreamAudioRecorder.this.mIsRecording, StreamAudioRecorder.this.mCurrentRecoredrFlag);
                    var7.printStackTrace();
                }

                try {
                    this.mAudioRecord.stop();
                    this.mAudioRecord.release();
                    this.mAudioRecord = null;
                    LogUtil.w("StreamAudioRecorder", "release sucess");
                } catch (Exception var5) {
                    var5.printStackTrace();
                }

                try {
                    if (this.file != null) {
                        StreamAudioRecorder.this.fclose(this.file);
                        if (this.mAudioStopCompletedCallback != null) {
                            this.mAudioStopCompletedCallback.onAudioStopCompeleted();
                        }
                    }
                } catch (Exception var4) {
                    var4.printStackTrace();
                }
            }

        }

        private byte[] short2byte(short[] sData, int size, byte[] bData) {
            if (size > sData.length || size * 2 > bData.length) {
                LogUtil.w("StreamAudioRecorder", "short2byte: too long short data array");
            }

            for(int i = 0; i < size; ++i) {
                bData[i * 2] = (byte)(sData[i] & 255);
                bData[i * 2 + 1] = (byte)(sData[i] >> 8);
            }

            return bData;
        }

        private void onError(int errorCode) {
            if (errorCode == -3) {
                LogUtil.w("StreamAudioRecorder", "record fail: ERROR_INVALID_OPERATION");
                this.mAudioDataCallback.onError(errorCode);
            } else if (errorCode == -2) {
                LogUtil.w("StreamAudioRecorder", "record fail: ERROR_BAD_VALUE");
                this.mAudioDataCallback.onError(errorCode);
            } else {
                LogUtil.w("StreamAudioRecorder", "record fail: ERROR");
                this.mAudioDataCallback.onError(errorCode);
            }

        }
    }

    public interface AudioStopCompletedCallback {
        @WorkerThread
        void onAudioStopCompeleted();
    }

    public interface AudioStartCompeletedCallback {
        @WorkerThread
        void onAudioStartCompeleted();
    }

    public interface AudioDataCallback {
        @WorkerThread
        void onAudioData(byte[] var1, int var2, AtomicBoolean var3, int var4);

        void onError(int var1);
    }

    private static final class StreamAudioRecorderHolder {
        private static final StreamAudioRecorder INSTANCE = new StreamAudioRecorder();

        private StreamAudioRecorderHolder() {
        }
    }
}

