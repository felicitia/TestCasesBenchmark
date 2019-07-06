package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Preconditions;

public final class apx extends lk<aou> {
    private final Object a = new Object();
    /* access modifiers changed from: private */
    public ii<aou> b;
    private boolean c;
    private int d;

    public apx(ii<aou> iiVar) {
        this.b = iiVar;
        this.c = false;
        this.d = 0;
    }

    private final void f() {
        synchronized (this.a) {
            Preconditions.checkState(this.d >= 0);
            if (!this.c || this.d != 0) {
                gv.a("There are still references to the engine. Not destroying.");
            } else {
                gv.a("No reference is left (including root). Cleaning up engine.");
                a(new aqa(this), new li());
            }
        }
    }

    public final apt c() {
        apt apt = new apt(this);
        synchronized (this.a) {
            a(new apy(this, apt), new apz(this, apt));
            Preconditions.checkState(this.d >= 0);
            this.d++;
        }
        return apt;
    }

    /* access modifiers changed from: protected */
    public final void d() {
        synchronized (this.a) {
            Preconditions.checkState(this.d > 0);
            gv.a("Releasing 1 reference for JS Engine");
            this.d--;
            f();
        }
    }

    public final void e() {
        synchronized (this.a) {
            Preconditions.checkState(this.d >= 0);
            gv.a("Releasing root reference. JS Engine will be destroyed once other references are released.");
            this.c = true;
            f();
        }
    }
}
