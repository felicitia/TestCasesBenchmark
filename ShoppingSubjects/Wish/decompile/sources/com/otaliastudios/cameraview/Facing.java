package com.otaliastudios.cameraview;

public enum Facing implements Control {
    BACK(0),
    FRONT(1);
    
    static final Facing DEFAULT = null;
    private int value;

    static {
        DEFAULT = BACK;
    }

    private Facing(int i) {
        this.value = i;
    }

    /* access modifiers changed from: 0000 */
    public int value() {
        return this.value;
    }

    static Facing fromValue(int i) {
        Facing[] values;
        for (Facing facing : values()) {
            if (facing.value() == i) {
                return facing;
            }
        }
        return null;
    }
}
