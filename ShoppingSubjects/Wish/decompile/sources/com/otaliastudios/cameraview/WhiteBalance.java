package com.otaliastudios.cameraview;

public enum WhiteBalance implements Control {
    AUTO(0),
    INCANDESCENT(1),
    FLUORESCENT(2),
    DAYLIGHT(3),
    CLOUDY(4);
    
    static final WhiteBalance DEFAULT = null;
    private int value;

    static {
        DEFAULT = AUTO;
    }

    private WhiteBalance(int i) {
        this.value = i;
    }

    /* access modifiers changed from: 0000 */
    public int value() {
        return this.value;
    }

    static WhiteBalance fromValue(int i) {
        WhiteBalance[] values;
        for (WhiteBalance whiteBalance : values()) {
            if (whiteBalance.value() == i) {
                return whiteBalance;
            }
        }
        return null;
    }
}
