package com.google.android.gms.internal.ads;

final class me implements Runnable {
    private final /* synthetic */ zzapi a;

    me(zzapi zzapi) {
        this.a = zzapi;
    }

    public final void run() {
        this.a.zza("surfaceDestroyed", new String[0]);
    }
}
