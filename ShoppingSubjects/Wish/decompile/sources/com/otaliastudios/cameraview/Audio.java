package com.otaliastudios.cameraview;

public enum Audio implements Control {
    OFF(0),
    ON(1);
    
    static final Audio DEFAULT = null;
    private int value;

    static {
        DEFAULT = ON;
    }

    private Audio(int i) {
        this.value = i;
    }

    /* access modifiers changed from: 0000 */
    public int value() {
        return this.value;
    }

    static Audio fromValue(int i) {
        Audio[] values;
        for (Audio audio : values()) {
            if (audio.value() == i) {
                return audio;
            }
        }
        return null;
    }
}
