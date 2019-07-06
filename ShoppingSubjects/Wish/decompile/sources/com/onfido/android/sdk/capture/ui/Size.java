package com.onfido.android.sdk.capture.ui;

public final class Size {
    private final int a;
    private final int b;

    public Size(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            if (this.a == size.a) {
                if (this.b == size.b) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int getHeight() {
        return this.b;
    }

    public final int getWidth() {
        return this.a;
    }

    public int hashCode() {
        return (this.a * 31) + this.b;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Size(width=");
        sb.append(this.a);
        sb.append(", height=");
        sb.append(this.b);
        sb.append(")");
        return sb.toString();
    }
}
