package com.stepyen.xlearn.fragment.basics.lyric;

/**
 * date：2020-01-02
 * author：stepyen
 * description：
 */
public class LyricItem {

    public static final int STATUS_ALREADY_READ = 1;
    public static final int STATUS_CURRENT_READ = 2;
    public static final int STATUS_WAIT_READ = 3;

    public int status;
    public String text;

}
