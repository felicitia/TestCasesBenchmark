package com.google.android.gms.internal.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.d.c;

final class m extends c {
    private final /* synthetic */ FullWalletRequest a;
    private final /* synthetic */ int b;

    m(l lVar, GoogleApiClient googleApiClient, FullWalletRequest fullWalletRequest, int i) {
        this.a = fullWalletRequest;
        this.b = i;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final void a(c cVar) {
        cVar.a(this.a, this.b);
        setResult(Status.RESULT_SUCCESS);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        doExecute((c) anyClient);
    }
}
