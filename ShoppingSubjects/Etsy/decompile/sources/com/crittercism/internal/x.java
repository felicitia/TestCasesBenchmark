package com.crittercism.internal;

public final class x extends v {
    private int d;
    private int e = 0;

    public final boolean a(w wVar) {
        return true;
    }

    /* access modifiers changed from: protected */
    public final int d() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public final int e() {
        return 0;
    }

    public x(v vVar, int i) {
        super(vVar);
        this.d = i;
    }

    public final v b() {
        return aj.d;
    }

    public final v c() {
        return aj.d;
    }

    public final boolean a(int i) {
        if (i == -1) {
            this.a.a((v) aj.d);
            return true;
        }
        this.e++;
        this.c++;
        if (this.e != this.d) {
            return false;
        }
        this.a.b(a());
        this.a.a(this.a.b());
        return true;
    }

    public final int b(byte[] bArr, int i, int i2) {
        if (i2 == -1) {
            this.a.a((v) aj.d);
            return -1;
        } else if (this.e + i2 < this.d) {
            this.e += i2;
            this.c += i2;
            return i2;
        } else {
            int i3 = this.d - this.e;
            this.c += i3;
            this.a.b(a());
            this.a.a(this.a.b());
            return i3;
        }
    }

    public final void f() {
        this.a.b(a());
        this.a.a((v) aj.d);
    }
}
