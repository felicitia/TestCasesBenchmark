package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager.zza;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.tasks.g;

public final class zzg extends zzc<Boolean> {
    private final ListenerKey<?> zzea;

    public zzg(ListenerKey<?> listenerKey, g<Boolean> gVar) {
        super(4, gVar);
        this.zzea = listenerKey;
    }

    public final /* bridge */ /* synthetic */ void zza(@NonNull Status status) {
        super.zza(status);
    }

    public final /* bridge */ /* synthetic */ void zza(@NonNull zzaa zzaa, boolean z) {
    }

    public final /* bridge */ /* synthetic */ void zza(@NonNull RuntimeException runtimeException) {
        super.zza(runtimeException);
    }

    public final void zzb(zza<?> zza) throws RemoteException {
        zzbv zzbv = (zzbv) zza.zzbn().remove(this.zzea);
        if (zzbv != null) {
            zzbv.zzlu.unregisterListener(zza.zzae(), this.zzdu);
            zzbv.zzlt.clearListener();
            return;
        }
        this.zzdu.b(Boolean.valueOf(false));
    }
}
