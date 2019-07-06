package com.otaliastudios.cameraview;

import java.util.HashMap;

public class AspectRatio implements Comparable<AspectRatio> {
    static final HashMap<String, AspectRatio> sCache = new HashMap<>(16);
    private final int mX;
    private final int mY;

    public static AspectRatio of(int i, int i2) {
        int gcd = gcd(i, i2);
        int i3 = i / gcd;
        int i4 = i2 / gcd;
        StringBuilder sb = new StringBuilder();
        sb.append(i3);
        sb.append(":");
        sb.append(i4);
        String sb2 = sb.toString();
        AspectRatio aspectRatio = (AspectRatio) sCache.get(sb2);
        if (aspectRatio != null) {
            return aspectRatio;
        }
        AspectRatio aspectRatio2 = new AspectRatio(i3, i4);
        sCache.put(sb2, aspectRatio2);
        return aspectRatio2;
    }

    public static AspectRatio parse(String str) {
        String[] split = str.split(":");
        if (split.length == 2) {
            return of(Integer.valueOf(split[0]).intValue(), Integer.valueOf(split[1]).intValue());
        }
        throw new NumberFormatException("Illegal AspectRatio string. Must be x:y");
    }

    private AspectRatio(int i, int i2) {
        this.mX = i;
        this.mY = i2;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AspectRatio)) {
            return false;
        }
        AspectRatio aspectRatio = (AspectRatio) obj;
        if (this.mX == aspectRatio.mX && this.mY == aspectRatio.mY) {
            z = true;
        }
        return z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mX);
        sb.append(":");
        sb.append(this.mY);
        return sb.toString();
    }

    public float toFloat() {
        return ((float) this.mX) / ((float) this.mY);
    }

    public int hashCode() {
        return this.mY ^ ((this.mX << 16) | (this.mX >>> 16));
    }

    public int compareTo(AspectRatio aspectRatio) {
        if (equals(aspectRatio)) {
            return 0;
        }
        return toFloat() - aspectRatio.toFloat() > 0.0f ? 1 : -1;
    }

    public AspectRatio inverse() {
        return of(this.mY, this.mX);
    }

    private static int gcd(int i, int i2) {
        while (true) {
            int i3 = i2;
            int i4 = i;
            i = i3;
            if (i == 0) {
                return i4;
            }
            i2 = i4 % i;
        }
    }
}
