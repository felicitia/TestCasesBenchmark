package com.onfido.android.sdk.capture.ui.camera;

public final class RectData {
    private final int a;
    private final int b;
    private final int c;
    private final int d;

    public RectData(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RectData) {
            RectData rectData = (RectData) obj;
            if (this.a == rectData.a) {
                if (this.b == rectData.b) {
                    if (this.c == rectData.c) {
                        if (this.d == rectData.d) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int getHeight() {
        return this.b;
    }

    public final int getLeft() {
        return this.c;
    }

    public final int getTop() {
        return this.d;
    }

    public final int getWidth() {
        return this.a;
    }

    public int hashCode() {
        return (((((this.a * 31) + this.b) * 31) + this.c) * 31) + this.d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RectData(width=");
        sb.append(this.a);
        sb.append(", height=");
        sb.append(this.b);
        sb.append(", left=");
        sb.append(this.c);
        sb.append(", top=");
        sb.append(this.d);
        sb.append(")");
        return sb.toString();
    }
}
