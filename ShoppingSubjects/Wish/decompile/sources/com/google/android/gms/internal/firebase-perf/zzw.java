package com.google.android.gms.internal.firebase-perf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.google.firebase.FirebaseApp;

public final class zzw extends zzf {
    @SuppressLint({"StaticFieldLeak"})
    private static zzw zzcz = new zzw();
    private Context zzcy = FirebaseApp.getInstance().getApplicationContext();
    private zzr zzda = zzr.zzr();

    public static zzw zzae() {
        return zzcz;
    }

    public final zzr zzaf() {
        return this.zzda;
    }

    public static Context zzag() {
        return FirebaseApp.getInstance().getApplicationContext();
    }

    public final void zzd(int i) {
        super.zzd(i);
        if (i == 1) {
            this.zzda = zzr.zzr();
            LocalBroadcastManager.getInstance(this.zzcy).sendBroadcast(new Intent("SessionIdUpdate"));
        }
    }

    private zzw() {
        zzl();
    }
}
