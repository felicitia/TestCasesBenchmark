package com.google.zxing;

/* compiled from: ResultPoint */
public class d {
    private final float a;
    private final float b;

    public final boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        if (this.a == dVar.a && this.b == dVar.b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (31 * Float.floatToIntBits(this.a)) + Float.floatToIntBits(this.b);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(this.a);
        sb.append(',');
        sb.append(this.b);
        sb.append(')');
        return sb.toString();
    }
}
