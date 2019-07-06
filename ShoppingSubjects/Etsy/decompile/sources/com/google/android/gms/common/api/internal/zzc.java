package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager.zza;
import com.google.android.gms.tasks.g;

abstract class zzc<T> extends zzb {
    protected final g<T> zzdu;

    public zzc(int i, g<T> gVar) {
        super(i);
        this.zzdu = gVar;
    }

    public void zza(@NonNull Status status) {
        this.zzdu.b((Exception) new ApiException(status));
    }

    public final void zza(zza<?> zza) throws DeadObjectException {
        try {
            zzb(zza);
        } catch (DeadObjectException e) {
            zza(zzb.zza((RemoteException) e));
            throw e;
        } catch (RemoteException e2) {
            zza(zzb.zza(e2));
        } catch (RuntimeException e3) {
            zza(e3);
        }
    }

    public void zza(@NonNull zzaa zzaa, boolean z) {
    }

    public void zza(@NonNull RuntimeException runtimeException) {
        this.zzdu.b((Exception) runtimeException);
    }

    /* access modifiers changed from: protected */
    public abstract void zzb(zza<?> zza) throws RemoteException;
}
