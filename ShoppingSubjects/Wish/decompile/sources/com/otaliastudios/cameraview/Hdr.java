package com.otaliastudios.cameraview;

public enum Hdr implements Control {
    OFF(0),
    ON(1);
    
    static final Hdr DEFAULT = null;
    private int value;

    static {
        DEFAULT = OFF;
    }

    private Hdr(int i) {
        this.value = i;
    }

    /* access modifiers changed from: 0000 */
    public int value() {
        return this.value;
    }

    static Hdr fromValue(int i) {
        Hdr[] values;
        for (Hdr hdr : values()) {
            if (hdr.value() == i) {
                return hdr;
            }
        }
        return null;
    }
}
