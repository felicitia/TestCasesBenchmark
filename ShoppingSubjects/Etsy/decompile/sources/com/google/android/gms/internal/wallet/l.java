package com.google.android.gms.internal.wallet;

import android.annotation.SuppressLint;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.b;

@SuppressLint({"MissingRemoteException"})
public final class l implements b {
    public final PendingResult<BooleanResult> a(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new b(this, googleApiClient));
    }

    public final void a(GoogleApiClient googleApiClient, FullWalletRequest fullWalletRequest, int i) {
        googleApiClient.enqueue(new m(this, googleApiClient, fullWalletRequest, i));
    }

    public final void a(GoogleApiClient googleApiClient, String str, String str2, int i) {
        a aVar = new a(this, googleApiClient, str, str2, i);
        googleApiClient.enqueue(aVar);
    }
}
