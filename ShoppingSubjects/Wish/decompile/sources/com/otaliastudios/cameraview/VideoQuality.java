package com.otaliastudios.cameraview;

public enum VideoQuality implements Control {
    LOWEST(0),
    HIGHEST(1),
    MAX_QVGA(2),
    MAX_480P(3),
    MAX_720P(4),
    MAX_1080P(5),
    MAX_2160P(6);
    
    static final VideoQuality DEFAULT = null;
    private int value;

    static {
        DEFAULT = MAX_480P;
    }

    private VideoQuality(int i) {
        this.value = i;
    }

    /* access modifiers changed from: 0000 */
    public int value() {
        return this.value;
    }

    static VideoQuality fromValue(int i) {
        VideoQuality[] values;
        for (VideoQuality videoQuality : values()) {
            if (videoQuality.value() == i) {
                return videoQuality;
            }
        }
        return null;
    }
}
