package com.otaliastudios.cameraview;

public enum GestureAction {
    NONE(0),
    FOCUS(1),
    FOCUS_WITH_MARKER(2),
    CAPTURE(3),
    ZOOM(4),
    EXPOSURE_CORRECTION(5);
    
    static final GestureAction DEFAULT_LONG_TAP = null;
    static final GestureAction DEFAULT_PINCH = null;
    static final GestureAction DEFAULT_SCROLL_HORIZONTAL = null;
    static final GestureAction DEFAULT_SCROLL_VERTICAL = null;
    static final GestureAction DEFAULT_TAP = null;
    private int value;

    static {
        DEFAULT_PINCH = NONE;
        DEFAULT_TAP = NONE;
        DEFAULT_LONG_TAP = NONE;
        DEFAULT_SCROLL_HORIZONTAL = NONE;
        DEFAULT_SCROLL_VERTICAL = NONE;
    }

    private GestureAction(int i) {
        this.value = i;
    }

    /* access modifiers changed from: 0000 */
    public int value() {
        return this.value;
    }

    static GestureAction fromValue(int i) {
        GestureAction[] values;
        for (GestureAction gestureAction : values()) {
            if (gestureAction.value() == i) {
                return gestureAction;
            }
        }
        return null;
    }
}
