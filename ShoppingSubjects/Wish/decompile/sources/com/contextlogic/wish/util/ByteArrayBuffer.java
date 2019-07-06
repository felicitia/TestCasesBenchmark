package com.contextlogic.wish.util;

import java.io.Serializable;

public final class ByteArrayBuffer implements Serializable {
    private byte[] buffer;
    private int len;

    public ByteArrayBuffer(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Buffer capacity may not be negative");
        }
        this.buffer = new byte[i];
    }

    private void expand(int i) {
        byte[] bArr = new byte[Math.max(this.buffer.length << 1, i)];
        System.arraycopy(this.buffer, 0, bArr, 0, this.len);
        this.buffer = bArr;
    }

    public void append(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            if (i >= 0 && i <= bArr.length && i2 >= 0) {
                int i3 = i + i2;
                if (i3 >= 0 && i3 <= bArr.length) {
                    if (i2 != 0) {
                        int i4 = this.len + i2;
                        if (i4 > this.buffer.length) {
                            expand(i4);
                        }
                        System.arraycopy(bArr, i, this.buffer, this.len, i2);
                        this.len = i4;
                        return;
                    }
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("off: ");
            sb.append(i);
            sb.append(" len: ");
            sb.append(i2);
            sb.append(" b.length: ");
            sb.append(bArr.length);
            throw new IndexOutOfBoundsException(sb.toString());
        }
    }

    public byte[] toByteArray() {
        byte[] bArr = new byte[this.len];
        if (this.len > 0) {
            System.arraycopy(this.buffer, 0, bArr, 0, this.len);
        }
        return bArr;
    }

    public int capacity() {
        return this.buffer.length;
    }

    public int length() {
        return this.len;
    }

    public byte[] buffer() {
        return this.buffer;
    }
}
