package com.google.android.gms.internal.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.d.c;

final class a extends c {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ int c;

    a(l lVar, GoogleApiClient googleApiClient, String str, String str2, int i) {
        this.a = str;
        this.b = str2;
        this.c = i;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final void a(c cVar) {
        cVar.a(this.a, this.b, this.c);
        setResult(Status.RESULT_SUCCESS);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        doExecute((c) anyClient);
    }
}
