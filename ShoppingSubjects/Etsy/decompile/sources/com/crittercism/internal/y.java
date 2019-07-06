package com.crittercism.internal;

public final class y extends v {
    private z d;
    private int e;
    private int f = 0;

    public final boolean a(w wVar) {
        return true;
    }

    public final v c() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final int d() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public final int e() {
        return 0;
    }

    public y(z zVar, int i) {
        super((v) zVar);
        this.d = zVar;
        this.e = i;
    }

    public final boolean a(int i) {
        if (this.f >= this.e + 2) {
            return false;
        }
        if (i == -1) {
            this.a.b(a());
            this.a.a((v) aj.d);
            return true;
        }
        this.c++;
        char c = (char) i;
        this.f++;
        if (this.f > this.e) {
            if (c == 10) {
                this.d.b(a());
                this.a.a((v) this.d);
                return true;
            } else if (this.f == this.e + 2 && c != 10) {
                this.a.a((v) aj.d);
                return true;
            }
        }
        return false;
    }

    public final v b() {
        return this.d;
    }

    public final void f() {
        this.a.b(a());
        this.a.a((v) aj.d);
    }
}
