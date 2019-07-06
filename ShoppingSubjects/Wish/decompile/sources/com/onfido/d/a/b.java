package com.onfido.d.a;

import java.util.Arrays;

public final class b implements Cloneable {
    private final int a;
    private final int b;
    private final int c;
    private final int[] d;

    public b(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.a = i;
        this.b = i2;
        this.c = (i + 31) / 32;
        this.d = new int[(this.c * i2)];
    }

    private b(int i, int i2, int i3, int[] iArr) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = iArr;
    }

    private String a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(this.b * (this.a + 1));
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.a; i2++) {
                sb.append(a(i2, i) ? str : str2);
            }
            sb.append(str3);
        }
        return sb.toString();
    }

    public a a(int i, a aVar) {
        if (aVar == null || aVar.a() < this.a) {
            aVar = new a(this.a);
        } else {
            aVar.b();
        }
        int i2 = i * this.c;
        for (int i3 = 0; i3 < this.c; i3++) {
            aVar.a(i3 << 5, this.d[i2 + i3]);
        }
        return aVar;
    }

    public String a(String str, String str2) {
        return a(str, str2, "\n");
    }

    public void a() {
        int b2 = b();
        int c2 = c();
        a aVar = new a(b2);
        a aVar2 = new a(b2);
        for (int i = 0; i < (c2 + 1) / 2; i++) {
            aVar = a(i, aVar);
            int i2 = (c2 - 1) - i;
            aVar2 = a(i2, aVar2);
            aVar.d();
            aVar2.d();
            b(i, aVar2);
            b(i2, aVar);
        }
    }

    public boolean a(int i, int i2) {
        return ((this.d[(i2 * this.c) + (i / 32)] >>> (i & 31)) & 1) != 0;
    }

    public int b() {
        return this.a;
    }

    public void b(int i, int i2) {
        int i3 = (i2 * this.c) + (i / 32);
        int[] iArr = this.d;
        iArr[i3] = (1 << (i & 31)) | iArr[i3];
    }

    public void b(int i, a aVar) {
        System.arraycopy(aVar.c(), 0, this.d, i * this.c, this.c);
    }

    public int c() {
        return this.b;
    }

    /* renamed from: d */
    public b clone() {
        return new b(this.a, this.b, this.c, (int[]) this.d.clone());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.a == bVar.a && this.b == bVar.b && this.c == bVar.c && Arrays.equals(this.d, bVar.d);
    }

    public int hashCode() {
        return (((((((this.a * 31) + this.a) * 31) + this.b) * 31) + this.c) * 31) + Arrays.hashCode(this.d);
    }

    public String toString() {
        return a("X ", "  ");
    }
}
