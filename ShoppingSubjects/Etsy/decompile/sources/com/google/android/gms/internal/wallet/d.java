package com.google.android.gms.internal.wallet;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;
import java.lang.ref.WeakReference;

final class d extends zzaf {
    private final WeakReference<Activity> a;
    private final int b;

    public d(Activity activity, int i) {
        this.a = new WeakReference<>(activity);
        this.b = i;
    }

    public final void zza(int i, Bundle bundle) {
        Preconditions.checkNotNull(bundle, "Bundle should not be null");
        Activity activity = (Activity) this.a.get();
        if (activity == null) {
            Log.d("WalletClientImpl", "Ignoring onWalletObjectsCreated, Activity has gone");
            return;
        }
        ConnectionResult connectionResult = new ConnectionResult(i, (PendingIntent) bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT"));
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(activity, this.b);
            } catch (SendIntentException e) {
                Log.w("WalletClientImpl", "Exception starting pending intent", e);
            }
        } else {
            String valueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(75 + String.valueOf(valueOf).length());
            sb.append("Create Wallet Objects confirmation UI will not be shown connection result: ");
            sb.append(valueOf);
            Log.e("WalletClientImpl", sb.toString());
            Intent intent = new Intent();
            intent.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", 413);
            PendingIntent createPendingResult = activity.createPendingResult(this.b, intent, ErrorDialogData.SUPPRESSED);
            if (createPendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onWalletObjectsCreated");
                return;
            }
            try {
                createPendingResult.send(1);
            } catch (CanceledException e2) {
                Log.w("WalletClientImpl", "Exception setting pending result", e2);
            }
        }
    }

    public final void zza(int i, FullWallet fullWallet, Bundle bundle) {
        int i2;
        Activity activity = (Activity) this.a.get();
        if (activity == null) {
            Log.d("WalletClientImpl", "Ignoring onFullWalletLoaded, Activity has gone");
            return;
        }
        PendingIntent pendingIntent = null;
        if (bundle != null) {
            pendingIntent = (PendingIntent) bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
        }
        ConnectionResult connectionResult = new ConnectionResult(i, pendingIntent);
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(activity, this.b);
            } catch (SendIntentException e) {
                Log.w("WalletClientImpl", "Exception starting pending intent", e);
            }
        } else {
            Intent intent = new Intent();
            if (connectionResult.isSuccess()) {
                i2 = -1;
                intent.putExtra("com.google.android.gms.wallet.EXTRA_FULL_WALLET", fullWallet);
            } else {
                int i3 = i == 408 ? 0 : 1;
                intent.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", i);
                i2 = i3;
            }
            PendingIntent createPendingResult = activity.createPendingResult(this.b, intent, ErrorDialogData.SUPPRESSED);
            if (createPendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onFullWalletLoaded");
                return;
            }
            try {
                createPendingResult.send(i2);
            } catch (CanceledException e2) {
                Log.w("WalletClientImpl", "Exception setting pending result", e2);
            }
        }
    }

    public final void zza(int i, MaskedWallet maskedWallet, Bundle bundle) {
        int i2;
        Activity activity = (Activity) this.a.get();
        if (activity == null) {
            Log.d("WalletClientImpl", "Ignoring onMaskedWalletLoaded, Activity has gone");
            return;
        }
        PendingIntent pendingIntent = null;
        if (bundle != null) {
            pendingIntent = (PendingIntent) bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
        }
        ConnectionResult connectionResult = new ConnectionResult(i, pendingIntent);
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(activity, this.b);
            } catch (SendIntentException e) {
                Log.w("WalletClientImpl", "Exception starting pending intent", e);
            }
        } else {
            Intent intent = new Intent();
            if (connectionResult.isSuccess()) {
                i2 = -1;
                intent.putExtra("com.google.android.gms.wallet.EXTRA_MASKED_WALLET", maskedWallet);
            } else {
                int i3 = i == 408 ? 0 : 1;
                intent.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", i);
                i2 = i3;
            }
            PendingIntent createPendingResult = activity.createPendingResult(this.b, intent, ErrorDialogData.SUPPRESSED);
            if (createPendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onMaskedWalletLoaded");
                return;
            }
            try {
                createPendingResult.send(i2);
            } catch (CanceledException e2) {
                Log.w("WalletClientImpl", "Exception setting pending result", e2);
            }
        }
    }

    public final void zza(int i, boolean z, Bundle bundle) {
        Activity activity = (Activity) this.a.get();
        if (activity == null) {
            Log.d("WalletClientImpl", "Ignoring onPreAuthorizationDetermined, Activity has gone");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("com.google.android.gm.wallet.EXTRA_IS_USER_PREAUTHORIZED", z);
        PendingIntent createPendingResult = activity.createPendingResult(this.b, intent, ErrorDialogData.SUPPRESSED);
        if (createPendingResult == null) {
            Log.w("WalletClientImpl", "Null pending result returned for onPreAuthorizationDetermined");
            return;
        }
        try {
            createPendingResult.send(-1);
        } catch (CanceledException e) {
            Log.w("WalletClientImpl", "Exception setting pending result", e);
        }
    }
}
