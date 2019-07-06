package com.google.android.gms.internal.wallet;

import android.annotation.SuppressLint;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.Payments;

@SuppressLint({"MissingRemoteException"})
public final class zzw implements Payments {
    public final void changeMaskedWallet(GoogleApiClient googleApiClient, String str, String str2, int i) {
        zzaa zzaa = new zzaa(this, googleApiClient, str, str2, i);
        googleApiClient.enqueue(zzaa);
    }

    public final void loadFullWallet(GoogleApiClient googleApiClient, FullWalletRequest fullWalletRequest, int i) {
        googleApiClient.enqueue(new zzz(this, googleApiClient, fullWalletRequest, i));
    }

    public final void loadMaskedWallet(GoogleApiClient googleApiClient, MaskedWalletRequest maskedWalletRequest, int i) {
        googleApiClient.enqueue(new zzy(this, googleApiClient, maskedWalletRequest, i));
    }
}
