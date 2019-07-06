package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.c;
import com.google.android.gms.tasks.f;
import com.google.android.gms.tasks.g;

final class zzac implements c<TResult> {
    private final /* synthetic */ zzaa zzgz;
    private final /* synthetic */ g zzha;

    zzac(zzaa zzaa, g gVar) {
        this.zzgz = zzaa;
        this.zzha = gVar;
    }

    public final void onComplete(@NonNull f<TResult> fVar) {
        this.zzgz.zzgx.remove(this.zzha);
    }
}
