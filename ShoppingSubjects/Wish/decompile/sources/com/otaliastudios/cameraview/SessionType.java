package com.otaliastudios.cameraview;

public enum SessionType implements Control {
    PICTURE(0),
    VIDEO(1);
    
    static final SessionType DEFAULT = null;
    private int value;

    static {
        DEFAULT = PICTURE;
    }

    private SessionType(int i) {
        this.value = i;
    }

    /* access modifiers changed from: 0000 */
    public int value() {
        return this.value;
    }

    static SessionType fromValue(int i) {
        SessionType[] values;
        for (SessionType sessionType : values()) {
            if (sessionType.value() == i) {
                return sessionType;
            }
        }
        return null;
    }
}
