package com.google.firebase.appindexing.internal;

import com.google.android.gms.tasks.OnFailureListener;

final /* synthetic */ class zzl implements OnFailureListener {
    private final zzk zzcv;

    zzl(zzk zzk) {
        this.zzcv = zzk;
    }

    public final void onFailure(Exception exc) {
        this.zzcv.zza(exc);
    }
}
