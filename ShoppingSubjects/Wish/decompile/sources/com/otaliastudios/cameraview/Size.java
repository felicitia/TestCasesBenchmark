package com.otaliastudios.cameraview;

public class Size implements Comparable<Size> {
    private final int mHeight;
    private final int mWidth;

    Size(int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    /* access modifiers changed from: 0000 */
    public Size flip() {
        return new Size(this.mHeight, this.mWidth);
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Size)) {
            return false;
        }
        Size size = (Size) obj;
        if (this.mWidth == size.mWidth && this.mHeight == size.mHeight) {
            z = true;
        }
        return z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mWidth);
        sb.append("x");
        sb.append(this.mHeight);
        return sb.toString();
    }

    public int hashCode() {
        return this.mHeight ^ ((this.mWidth << 16) | (this.mWidth >>> 16));
    }

    public int compareTo(Size size) {
        return (this.mWidth * this.mHeight) - (size.mWidth * size.mHeight);
    }
}
