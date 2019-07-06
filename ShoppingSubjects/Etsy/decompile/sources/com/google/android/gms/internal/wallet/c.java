package com.google.android.gms.internal.wallet;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.internal.AccountType;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.MaskedWallet;

public final class c extends GmsClient<zzq> {
    private final Context a;
    private final int b;
    private final String c;
    private final int d;
    private final boolean e;

    public c(Context context, Looper looper, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, int i, int i2, boolean z) {
        super(context, looper, 4, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.a = context;
        this.b = i;
        this.c = clientSettings.getAccountName();
        this.d = i2;
        this.e = z;
    }

    private final Bundle a() {
        int i = this.b;
        String packageName = this.a.getPackageName();
        String str = this.c;
        int i2 = this.d;
        boolean z = this.e;
        Bundle bundle = new Bundle();
        bundle.putInt("com.google.android.gms.wallet.EXTRA_ENVIRONMENT", i);
        bundle.putBoolean("com.google.android.gms.wallet.EXTRA_USING_ANDROID_PAY_BRAND", z);
        bundle.putString("androidPackageName", packageName);
        if (!TextUtils.isEmpty(str)) {
            bundle.putParcelable("com.google.android.gms.wallet.EXTRA_BUYER_ACCOUNT", new Account(str, AccountType.GOOGLE));
        }
        bundle.putInt("com.google.android.gms.wallet.EXTRA_THEME", i2);
        return bundle;
    }

    public final void a(FullWalletRequest fullWalletRequest, int i) {
        d dVar = new d((Activity) this.a, i);
        try {
            ((zzq) getService()).zza(fullWalletRequest, a(), (zzu) dVar);
        } catch (RemoteException e2) {
            Log.e("WalletClientImpl", "RemoteException getting full wallet", e2);
            dVar.zza(8, (FullWallet) null, Bundle.EMPTY);
        }
    }

    public final void a(IsReadyToPayRequest isReadyToPayRequest, ResultHolder<BooleanResult> resultHolder) {
        e eVar = new e(resultHolder);
        try {
            ((zzq) getService()).zza(isReadyToPayRequest, a(), (zzu) eVar);
        } catch (RemoteException e2) {
            Log.e("WalletClientImpl", "RemoteException during isReadyToPay", e2);
            eVar.zza(Status.RESULT_INTERNAL_ERROR, false, Bundle.EMPTY);
        }
    }

    public final void a(String str, String str2, int i) {
        Activity activity = (Activity) this.a;
        Bundle a2 = a();
        d dVar = new d(activity, i);
        try {
            ((zzq) getService()).zza(str, str2, a2, dVar);
        } catch (RemoteException e2) {
            Log.e("WalletClientImpl", "RemoteException changing masked wallet", e2);
            dVar.zza(8, (MaskedWallet) null, Bundle.EMPTY);
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.wallet.internal.IOwService");
        return queryLocalInterface instanceof zzq ? (zzq) queryLocalInterface : new zzr(iBinder);
    }

    public final int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    /* access modifiers changed from: protected */
    public final String getServiceDescriptor() {
        return "com.google.android.gms.wallet.internal.IOwService";
    }

    /* access modifiers changed from: protected */
    public final String getStartServiceAction() {
        return "com.google.android.gms.wallet.service.BIND";
    }

    public final boolean requiresAccount() {
        return true;
    }
}
