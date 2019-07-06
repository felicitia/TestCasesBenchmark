package com.google.android.gms.internal.ads;

import android.os.Bundle;

@bu
public final class aob {
    private static aob a = new aob();
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;

    public static aob a() {
        return a;
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i) {
        this.b += i;
    }

    /* access modifiers changed from: 0000 */
    public final void b() {
        this.c++;
    }

    /* access modifiers changed from: 0000 */
    public final void c() {
        this.d++;
    }

    /* access modifiers changed from: 0000 */
    public final void d() {
        this.e++;
    }

    /* access modifiers changed from: 0000 */
    public final void e() {
        this.f++;
    }

    public final int f() {
        return this.c;
    }

    public final int g() {
        return this.d;
    }

    public final int h() {
        return this.e;
    }

    public final int i() {
        return this.f;
    }

    public final Bundle j() {
        Bundle bundle = new Bundle();
        bundle.putInt("ipl", this.b);
        bundle.putInt("ipds", this.c);
        bundle.putInt("ipde", this.d);
        bundle.putInt("iph", this.e);
        bundle.putInt("ipm", this.f);
        return bundle;
    }
}
