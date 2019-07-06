package com.google.android.gms.internal.measurement;

abstract class zzjs extends zzjr {
    private boolean zzvn;

    zzjs(zzjt zzjt) {
        super(zzjt);
        this.zzalo.zzb(this);
    }

    /* access modifiers changed from: 0000 */
    public final boolean isInitialized() {
        return this.zzvn;
    }

    /* access modifiers changed from: protected */
    public final void zzch() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzgn();

    public final void zzm() {
        if (this.zzvn) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzgn();
        this.zzalo.zzll();
        this.zzvn = true;
    }
}
