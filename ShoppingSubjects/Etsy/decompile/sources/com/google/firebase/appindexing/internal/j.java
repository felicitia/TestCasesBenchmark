package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback.Stub;
import com.google.android.gms.tasks.g;

final class j extends Stub {
    private final /* synthetic */ g a;
    private final /* synthetic */ i b;

    j(i iVar, g gVar) {
        this.b = iVar;
        this.a = gVar;
    }

    public final void onResult(Status status) throws RemoteException {
        if (this.a.b(null)) {
            if (status.isSuccess()) {
                this.b.a.c.a(null);
                return;
            }
            this.b.a.c.a((Exception) a.a(status, "Indexing error, please try again."));
        }
    }
}
