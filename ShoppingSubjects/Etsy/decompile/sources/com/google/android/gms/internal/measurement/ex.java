package com.google.android.gms.internal.measurement;

abstract class ex extends ew {
    private boolean b;

    ex(ey eyVar) {
        super(eyVar);
        this.a.a(this);
    }

    /* access modifiers changed from: 0000 */
    public final boolean H() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public final void I() {
        if (!H()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void J() {
        if (this.b) {
            throw new IllegalStateException("Can't initialize twice");
        }
        e();
        this.a.l();
        this.b = true;
    }

    /* access modifiers changed from: protected */
    public abstract boolean e();
}
