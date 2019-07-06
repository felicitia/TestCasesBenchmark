package com.otaliastudios.cameraview;

public enum Flash implements Control {
    OFF(0),
    ON(1),
    AUTO(2),
    TORCH(3);
    
    static final Flash DEFAULT = null;
    private int value;

    static {
        DEFAULT = OFF;
    }

    private Flash(int i) {
        this.value = i;
    }

    /* access modifiers changed from: 0000 */
    public int value() {
        return this.value;
    }

    static Flash fromValue(int i) {
        Flash[] values;
        for (Flash flash : values()) {
            if (flash.value() == i) {
                return flash;
            }
        }
        return null;
    }
}
