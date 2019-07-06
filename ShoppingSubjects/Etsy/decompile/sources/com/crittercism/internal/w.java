package com.crittercism.internal;

public final class w {
    char[] a;
    int b;

    private static boolean a(char c) {
        return c == ' ' || c == 9 || c == 13 || c == 10;
    }

    public w(int i) {
        this.a = new char[i];
    }

    public final String a(int i) {
        if (i > this.b) {
            StringBuilder sb = new StringBuilder("endIndex: ");
            sb.append(i);
            sb.append(" > length: ");
            sb.append(this.b);
            throw new IndexOutOfBoundsException(sb.toString());
        } else if (i < 0) {
            StringBuilder sb2 = new StringBuilder("beginIndex: 0 > endIndex: ");
            sb2.append(i);
            throw new IndexOutOfBoundsException(sb2.toString());
        } else {
            int i2 = 0;
            while (i2 < i && a(this.a[i2])) {
                i2++;
            }
            while (i > i2 && a(this.a[i - 1])) {
                i--;
            }
            return new String(this.a, i2, i - i2);
        }
    }

    public final String toString() {
        return new String(this.a, 0, this.b);
    }
}
