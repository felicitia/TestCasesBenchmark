package com.google.android.gms.internal.measurement;

abstract class s extends r {
    private boolean a;

    s(bu buVar) {
        super(buVar);
        this.q.a(this);
    }

    /* access modifiers changed from: protected */
    public void A() {
    }

    /* access modifiers changed from: 0000 */
    public final boolean v() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public final void w() {
        if (!v()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void x() {
        if (this.a) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!z()) {
            this.q.C();
            this.a = true;
        }
    }

    public final void y() {
        if (this.a) {
            throw new IllegalStateException("Can't initialize twice");
        }
        A();
        this.q.C();
        this.a = true;
    }

    /* access modifiers changed from: protected */
    public abstract boolean z();
}
