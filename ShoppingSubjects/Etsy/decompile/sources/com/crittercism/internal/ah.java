package com.crittercism.internal;

public final class ah extends v {
    private boolean d = false;

    /* access modifiers changed from: protected */
    public final int d() {
        return 8;
    }

    /* access modifiers changed from: protected */
    public final int e() {
        return 128;
    }

    public ah(v vVar) {
        super(vVar);
    }

    public final v b() {
        if (this.d) {
            this.a.b(a());
            return this.a.b();
        }
        this.b.b = 0;
        return this;
    }

    public final v c() {
        this.b.b = 0;
        return new ai(this);
    }

    public final boolean a(w wVar) {
        this.d = wVar.a(wVar.b).length() == 0;
        return true;
    }
}
