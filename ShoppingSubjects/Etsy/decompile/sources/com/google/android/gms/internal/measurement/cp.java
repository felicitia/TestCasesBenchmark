package com.google.android.gms.internal.measurement;

abstract class cp extends co {
    private boolean a;

    cp(bu buVar) {
        super(buVar);
        this.q.a(this);
    }

    public final void A() {
        if (this.a) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!e()) {
            this.q.C();
            this.a = true;
        }
    }

    public final void B() {
        if (this.a) {
            throw new IllegalStateException("Can't initialize twice");
        }
        f();
        this.q.C();
        this.a = true;
    }

    /* access modifiers changed from: protected */
    public abstract boolean e();

    /* access modifiers changed from: protected */
    public void f() {
    }

    /* access modifiers changed from: 0000 */
    public final boolean y() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public final void z() {
        if (!y()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}
