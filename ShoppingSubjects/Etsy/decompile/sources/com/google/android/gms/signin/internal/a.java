package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.signin.b;
import com.google.android.gms.signin.c;
import com.google.android.gms.signin.internal.ISignInService.Stub;

public class a extends GmsClient<ISignInService> implements b {
    private final boolean a;
    private final ClientSettings b;
    private final Bundle c;
    private Integer d;

    public a(Context context, Looper looper, boolean z, ClientSettings clientSettings, Bundle bundle, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.a = z;
        this.b = clientSettings;
        this.c = bundle;
        this.d = clientSettings.getClientSessionId();
    }

    public a(Context context, Looper looper, boolean z, ClientSettings clientSettings, c cVar, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, z, clientSettings, a(clientSettings), connectionCallbacks, onConnectionFailedListener);
    }

    public static Bundle a(ClientSettings clientSettings) {
        c signInOptions = clientSettings.getSignInOptions();
        Integer clientSessionId = clientSettings.getClientSessionId();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", clientSettings.getAccount());
        if (clientSessionId != null) {
            bundle.putInt(ClientSettings.KEY_CLIENT_SESSION_ID, clientSessionId.intValue());
        }
        if (signInOptions != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", signInOptions.a());
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", signInOptions.b());
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", signInOptions.c());
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", signInOptions.d());
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", signInOptions.e());
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", signInOptions.f());
            if (signInOptions.g() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", signInOptions.g().longValue());
            }
            if (signInOptions.h() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", signInOptions.h().longValue());
            }
        }
        return bundle;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public ISignInService createServiceInterface(IBinder iBinder) {
        return Stub.asInterface(iBinder);
    }

    public void a() {
        try {
            ((ISignInService) getService()).clearAccountFromSessionStore(this.d.intValue());
        } catch (RemoteException unused) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    public void a(IAccountAccessor iAccountAccessor, boolean z) {
        try {
            ((ISignInService) getService()).saveDefaultAccountToSharedPref(iAccountAccessor, this.d.intValue(), z);
        } catch (RemoteException unused) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    public void a(ISignInCallbacks iSignInCallbacks) {
        Preconditions.checkNotNull(iSignInCallbacks, "Expecting a valid ISignInCallbacks");
        try {
            Account accountOrDefault = this.b.getAccountOrDefault();
            GoogleSignInAccount googleSignInAccount = null;
            if ("<<default account>>".equals(accountOrDefault.name)) {
                googleSignInAccount = com.google.android.gms.auth.api.signin.internal.b.a(getContext()).a();
            }
            ((ISignInService) getService()).signIn(new SignInRequest(new ResolveAccountRequest(accountOrDefault, this.d.intValue(), googleSignInAccount)), iSignInCallbacks);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                iSignInCallbacks.onSignInComplete(new SignInResponse(8));
            } catch (RemoteException unused) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    public void b() {
        connect(new LegacyClientCallbackAdapter());
    }

    /* access modifiers changed from: protected */
    public Bundle getGetServiceRequestExtraArgs() {
        if (!getContext().getPackageName().equals(this.b.getRealClientPackageName())) {
            this.c.putString("com.google.android.gms.signin.internal.realClientPackageName", this.b.getRealClientPackageName());
        }
        return this.c;
    }

    public int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    /* access modifiers changed from: protected */
    public String getServiceDescriptor() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    /* access modifiers changed from: protected */
    public String getStartServiceAction() {
        return "com.google.android.gms.signin.service.START";
    }

    public boolean requiresSignIn() {
        return this.a;
    }
}
