package com.otaliastudios.cameraview;

public enum Grid implements Control {
    OFF(0),
    DRAW_3X3(1),
    DRAW_4X4(2),
    DRAW_PHI(3);
    
    static final Grid DEFAULT = null;
    private int value;

    static {
        DEFAULT = OFF;
    }

    private Grid(int i) {
        this.value = i;
    }

    /* access modifiers changed from: 0000 */
    public int value() {
        return this.value;
    }

    static Grid fromValue(int i) {
        Grid[] values;
        for (Grid grid : values()) {
            if (grid.value() == i) {
                return grid;
            }
        }
        return null;
    }
}
