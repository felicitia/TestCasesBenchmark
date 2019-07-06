package com.google.android.gms.internal.ads;

final class mf implements Runnable {
    private final /* synthetic */ boolean a;
    private final /* synthetic */ zzapi b;

    mf(zzapi zzapi, boolean z) {
        this.b = zzapi;
        this.a = z;
    }

    public final void run() {
        this.b.zza("windowVisibilityChanged", "isVisible", String.valueOf(this.a));
    }
}
