package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.icing.zzab;
import com.google.android.gms.internal.icing.zzag;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzr extends TaskApiCall<zzag, Void> implements ResultHolder<Status> {
    private TaskCompletionSource<Void> zzdb;

    private zzr() {
    }

    /* synthetic */ zzr(zzp zzp) {
        this();
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzag zzag = (zzag) anyClient;
        this.zzdb = taskCompletionSource;
        zza((zzab) zzag.getService());
    }

    public /* synthetic */ void setResult(Object obj) {
        Status status = (Status) obj;
        if (status.isSuccess()) {
            this.zzdb.setResult(null);
        } else {
            this.zzdb.setException(zzab.zza(status, "User Action indexing error, please try again."));
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzab zzab) throws RemoteException;
}
