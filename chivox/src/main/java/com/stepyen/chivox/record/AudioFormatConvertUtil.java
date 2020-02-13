package com.stepyen.chivox.record;

/**
 * date：2020-02-13
 * author：stepyen
 * description：
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class AudioFormatConvertUtil {
    private static final Logger logger = Logger.getLogger(AudioFormatConvertUtil.class.getName());
    private static final String TAG = "AudioFormatConvertUtil";
    private static String PCM = ".pcm";
    private static String WAV = ".wav";
    private static String MP3 = ".mp3";
    private static String AMR = ".amr";

    public AudioFormatConvertUtil() {
    }

    public static void pcmToWav(File source, File target, boolean isDeleteSource) throws IOException {
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(target);
        byte[] buf = new byte[4194304];
        int size = fis.read(buf);

        int PCMSize;
        for(PCMSize = 0; size != -1; size = fis.read(buf)) {
            LogUtil.e("pcmToWav", "pcmToWav: " + size);
            PCMSize += size;
        }

        fis.close();
        WaveHeader header = new WaveHeader();
        header.fileLength = PCMSize + 36;
        header.FmtHdrLeth = 16;
        header.BitsPerSample = 16;
        header.Channels = 1;
        header.FormatTag = 1;
        header.SamplesPerSec = 16000;
        header.BlockAlign = (short)(header.Channels * header.BitsPerSample / 8);
        header.AvgBytesPerSec = header.BlockAlign * header.SamplesPerSec;
        header.DataHdrLeth = PCMSize;
        byte[] head = header.getHeader();
        fos.write(head, 0, head.length);
        fis = new FileInputStream(source);

        for(size = fis.read(buf); size != -1; size = fis.read(buf)) {
            fos.write(buf, 0, size);
        }

        fis.close();
        fos.close();
        LogUtil.w("AudioFormatConvertUtil", "pcmToWav: ****pcm 转 wav格式转换成功****");
        if (isDeleteSource) {
            source.delete();
            LogUtil.w("AudioFormatConvertUtil", "******删除源成功******");
        }

    }
}
