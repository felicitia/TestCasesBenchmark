package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.logging.Logger;

public final class zzi {
    private static Logger zzer = new Logger("GoogleSignInCommon", new String[0]);

    public static GoogleSignInResult getSignInResultFromIntent(Intent intent) {
        if (intent == null || (!intent.hasExtra("googleSignInStatus") && !intent.hasExtra("googleSignInAccount"))) {
            return null;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) intent.getParcelableExtra("googleSignInAccount");
        Status status = (Status) intent.getParcelableExtra("googleSignInStatus");
        if (googleSignInAccount != null) {
            status = Status.RESULT_SUCCESS;
        }
        return new GoogleSignInResult(googleSignInAccount, status);
    }

    public static Intent zzd(Context context, GoogleSignInOptions googleSignInOptions) {
        zzer.d("getSignInIntent()", new Object[0]);
        SignInConfiguration signInConfiguration = new SignInConfiguration(context.getPackageName(), googleSignInOptions);
        Intent intent = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
        intent.setPackage(context.getPackageName());
        intent.setClass(context, SignInHubActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("config", signInConfiguration);
        intent.putExtra("config", bundle);
        return intent;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0098  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.common.api.OptionalPendingResult<com.google.android.gms.auth.api.signin.GoogleSignInResult> zzd(com.google.android.gms.common.api.GoogleApiClient r5, android.content.Context r6, com.google.android.gms.auth.api.signin.GoogleSignInOptions r7, boolean r8) {
        /*
            com.google.android.gms.common.logging.Logger r0 = zzer
            java.lang.String r1 = "silentSignIn()"
            r2 = 0
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r0.d(r1, r3)
            com.google.android.gms.common.logging.Logger r0 = zzer
            java.lang.String r1 = "getEligibleSavedSignInResult()"
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r0.d(r1, r3)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r7)
            com.google.android.gms.auth.api.signin.internal.zzq r0 = com.google.android.gms.auth.api.signin.internal.zzq.zze(r6)
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r0 = r0.zzp()
            r1 = 0
            if (r0 == 0) goto L_0x0087
            android.accounts.Account r3 = r0.getAccount()
            android.accounts.Account r4 = r7.getAccount()
            if (r3 != 0) goto L_0x0031
            if (r4 != 0) goto L_0x002f
            r3 = 1
            goto L_0x0035
        L_0x002f:
            r3 = 0
            goto L_0x0035
        L_0x0031:
            boolean r3 = r3.equals(r4)
        L_0x0035:
            if (r3 == 0) goto L_0x0087
            boolean r3 = r7.isServerAuthCodeRequested()
            if (r3 != 0) goto L_0x0087
            boolean r3 = r7.isIdTokenRequested()
            if (r3 == 0) goto L_0x0057
            boolean r3 = r0.isIdTokenRequested()
            if (r3 == 0) goto L_0x0087
            java.lang.String r3 = r7.getServerClientId()
            java.lang.String r4 = r0.getServerClientId()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0087
        L_0x0057:
            java.util.HashSet r3 = new java.util.HashSet
            java.util.ArrayList r0 = r0.getScopes()
            r3.<init>(r0)
            java.util.HashSet r0 = new java.util.HashSet
            java.util.ArrayList r4 = r7.getScopes()
            r0.<init>(r4)
            boolean r0 = r3.containsAll(r0)
            if (r0 == 0) goto L_0x0087
            com.google.android.gms.auth.api.signin.internal.zzq r0 = com.google.android.gms.auth.api.signin.internal.zzq.zze(r6)
            com.google.android.gms.auth.api.signin.GoogleSignInAccount r0 = r0.zzo()
            if (r0 == 0) goto L_0x0087
            boolean r3 = r0.isExpired()
            if (r3 != 0) goto L_0x0087
            com.google.android.gms.auth.api.signin.GoogleSignInResult r3 = new com.google.android.gms.auth.api.signin.GoogleSignInResult
            com.google.android.gms.common.api.Status r4 = com.google.android.gms.common.api.Status.RESULT_SUCCESS
            r3.<init>(r0, r4)
            goto L_0x0088
        L_0x0087:
            r3 = r1
        L_0x0088:
            if (r3 == 0) goto L_0x0098
            com.google.android.gms.common.logging.Logger r6 = zzer
            java.lang.String r7 = "Eligible saved sign in result found"
            java.lang.Object[] r8 = new java.lang.Object[r2]
            r6.d(r7, r8)
            com.google.android.gms.common.api.OptionalPendingResult r5 = com.google.android.gms.common.api.PendingResults.immediatePendingResult(r3, r5)
            return r5
        L_0x0098:
            if (r8 == 0) goto L_0x00aa
            com.google.android.gms.auth.api.signin.GoogleSignInResult r6 = new com.google.android.gms.auth.api.signin.GoogleSignInResult
            com.google.android.gms.common.api.Status r7 = new com.google.android.gms.common.api.Status
            r8 = 4
            r7.<init>(r8)
            r6.<init>(r1, r7)
            com.google.android.gms.common.api.OptionalPendingResult r5 = com.google.android.gms.common.api.PendingResults.immediatePendingResult(r6, r5)
            return r5
        L_0x00aa:
            com.google.android.gms.common.logging.Logger r8 = zzer
            java.lang.String r0 = "trySilentSignIn()"
            java.lang.Object[] r1 = new java.lang.Object[r2]
            r8.d(r0, r1)
            com.google.android.gms.auth.api.signin.internal.zzj r8 = new com.google.android.gms.auth.api.signin.internal.zzj
            r8.<init>(r5, r6, r7)
            com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl r5 = r5.enqueue(r8)
            com.google.android.gms.common.api.internal.OptionalPendingResultImpl r6 = new com.google.android.gms.common.api.internal.OptionalPendingResultImpl
            r6.<init>(r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.internal.zzi.zzd(com.google.android.gms.common.api.GoogleApiClient, android.content.Context, com.google.android.gms.auth.api.signin.GoogleSignInOptions, boolean):com.google.android.gms.common.api.OptionalPendingResult");
    }

    public static PendingResult<Status> zzd(GoogleApiClient googleApiClient, Context context, boolean z) {
        zzer.d("Signing out", new Object[0]);
        zzd(context);
        return z ? PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, googleApiClient) : googleApiClient.execute(new zzl(googleApiClient));
    }

    private static void zzd(Context context) {
        zzq.zze(context).clear();
        for (GoogleApiClient maybeSignOut : GoogleApiClient.getAllClients()) {
            maybeSignOut.maybeSignOut();
        }
        GoogleApiManager.reportSignOut();
    }

    public static PendingResult<Status> zze(GoogleApiClient googleApiClient, Context context, boolean z) {
        zzer.d("Revoking access", new Object[0]);
        String savedRefreshToken = Storage.getInstance(context).getSavedRefreshToken();
        zzd(context);
        return z ? zze.zzg(savedRefreshToken) : googleApiClient.execute(new zzn(googleApiClient));
    }
}
