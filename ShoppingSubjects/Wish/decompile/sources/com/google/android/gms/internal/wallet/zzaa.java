package com.google.android.gms.internal.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.Wallet.zzb;

final class zzaa extends zzb {
    private final /* synthetic */ int val$requestCode;
    private final /* synthetic */ String zzgf;
    private final /* synthetic */ String zzgg;

    zzaa(zzw zzw, GoogleApiClient googleApiClient, String str, String str2, int i) {
        this.zzgf = str;
        this.zzgg = str2;
        this.val$requestCode = i;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        doExecute((zzad) anyClient);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzad zzad) {
        zzad.zza(this.zzgf, this.zzgg, this.val$requestCode);
        setResult(Status.RESULT_SUCCESS);
    }
}
