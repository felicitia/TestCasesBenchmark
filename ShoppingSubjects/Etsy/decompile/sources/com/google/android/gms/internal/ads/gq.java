package com.google.android.gms.internal.ads;

@bu
public abstract class gq implements hw<kt> {
    private final Runnable a = new gr(this);
    /* access modifiers changed from: private */
    public volatile Thread b;
    private boolean c = false;

    public gq() {
    }

    public gq(boolean z) {
    }

    public abstract void a();

    public final void b() {
        c_();
        if (this.b != null) {
            this.b.interrupt();
        }
    }

    public final /* synthetic */ Object c() {
        return this.c ? hb.b(this.a) : hb.a(this.a);
    }

    public abstract void c_();

    public final kt h() {
        return this.c ? hb.b(this.a) : hb.a(this.a);
    }
}
