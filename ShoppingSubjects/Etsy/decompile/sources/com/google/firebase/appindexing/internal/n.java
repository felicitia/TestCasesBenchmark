package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.icing.b;
import com.google.android.gms.internal.icing.zzab;
import com.google.android.gms.tasks.g;

abstract class n extends TaskApiCall<b, Void> implements ResultHolder<Status> {
    private g<Void> a;

    private n() {
    }

    /* synthetic */ n(l lVar) {
        this();
    }

    /* access modifiers changed from: protected */
    public abstract void a(zzab zzab) throws RemoteException;

    /* access modifiers changed from: protected */
    public /* synthetic */ void doExecute(AnyClient anyClient, g gVar) throws RemoteException {
        b bVar = (b) anyClient;
        this.a = gVar;
        a((zzab) bVar.getService());
    }

    public void setFailedResult(Status status) {
        Preconditions.checkArgument(!status.isSuccess(), "Failed result must not be success.");
        this.a.a((Exception) a.a(status, status.getStatusMessage()));
    }

    public /* synthetic */ void setResult(Object obj) {
        Status status = (Status) obj;
        if (status.isSuccess()) {
            this.a.a(null);
        } else {
            this.a.a((Exception) a.a(status, "User Action indexing error, please try again."));
        }
    }
}
