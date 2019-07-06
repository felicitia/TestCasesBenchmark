package com.google.android.gms.wallet;

import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

@Deprecated
public interface b {
    @Deprecated
    PendingResult<BooleanResult> a(GoogleApiClient googleApiClient);

    void a(GoogleApiClient googleApiClient, FullWalletRequest fullWalletRequest, int i);

    void a(GoogleApiClient googleApiClient, String str, String str2, int i);
}
