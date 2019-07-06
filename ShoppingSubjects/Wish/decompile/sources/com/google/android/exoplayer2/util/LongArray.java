package com.google.android.exoplayer2.util;

import java.util.Arrays;

public final class LongArray {
    private int size;
    private long[] values;

    public LongArray() {
        this(32);
    }

    public LongArray(int i) {
        this.values = new long[i];
    }

    public void add(long j) {
        if (this.size == this.values.length) {
            this.values = Arrays.copyOf(this.values, this.size * 2);
        }
        long[] jArr = this.values;
        int i = this.size;
        this.size = i + 1;
        jArr[i] = j;
    }

    public long get(int i) {
        if (i >= 0 && i < this.size) {
            return this.values[i];
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid index ");
        sb.append(i);
        sb.append(", size is ");
        sb.append(this.size);
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public int size() {
        return this.size;
    }

    public long[] toArray() {
        return Arrays.copyOf(this.values, this.size);
    }
}
