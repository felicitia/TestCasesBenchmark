package com.crittercism.internal;

public final class ai extends v {
    private v d;

    public final boolean a(w wVar) {
        return true;
    }

    public final v b() {
        return this;
    }

    public final v c() {
        return this;
    }

    /* access modifiers changed from: protected */
    public final int d() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public final int e() {
        return 0;
    }

    public ai(v vVar) {
        super(vVar);
        this.d = vVar;
    }

    public final boolean a(int i) {
        if (i == -1) {
            this.a.a((v) aj.d);
            return true;
        }
        this.c++;
        if (((char) i) != 10) {
            return false;
        }
        this.d.b(a());
        this.a.a(this.d);
        return true;
    }
}
