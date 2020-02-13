package com.stepyen.chivox.record;

/**
 * date：2020-02-13
 * author：stepyen
 * description：
 */
public enum AudioTypeEnum {
    PCM("pcm"),
    WAV("wav");

    private String value;

    private AudioTypeEnum(String value) {
        this.setValue(value);
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
