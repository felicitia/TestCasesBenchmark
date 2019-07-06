package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback.Stub;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzn extends Stub {
    private final /* synthetic */ TaskCompletionSource zzcx;
    private final /* synthetic */ zzm zzcy;

    zzn(zzm zzm, TaskCompletionSource taskCompletionSource) {
        this.zzcy = zzm;
        this.zzcx = taskCompletionSource;
    }

    public final void onResult(Status status) throws RemoteException {
        if (this.zzcx.trySetResult(null)) {
            if (status.isSuccess()) {
                this.zzcy.zzcw.zzct.setResult(null);
                return;
            }
            this.zzcy.zzcw.zzct.setException(zzab.zza(status, "Indexing error, please try again."));
        }
    }
}
