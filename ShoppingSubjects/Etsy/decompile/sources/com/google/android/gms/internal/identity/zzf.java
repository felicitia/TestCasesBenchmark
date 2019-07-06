package com.google.android.gms.internal.identity;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public final class zzf extends zzh {
    private Activity mActivity;
    private final int zzj;

    public zzf(int i, Activity activity) {
        this.zzj = i;
        this.mActivity = activity;
    }

    /* access modifiers changed from: private */
    public final void setActivity(Activity activity) {
        this.mActivity = null;
    }

    public final void zza(int i, Bundle bundle) {
        String str;
        String str2;
        if (i == 1) {
            Intent intent = new Intent();
            intent.putExtras(bundle);
            PendingIntent createPendingResult = this.mActivity.createPendingResult(this.zzj, intent, ErrorDialogData.SUPPRESSED);
            if (createPendingResult != null) {
                try {
                    createPendingResult.send(1);
                } catch (CanceledException e) {
                    e = e;
                    str = "AddressClientImpl";
                    str2 = "Exception settng pending result";
                    Log.w(str, str2, e);
                }
            }
        } else {
            PendingIntent pendingIntent = null;
            if (bundle != null) {
                pendingIntent = (PendingIntent) bundle.getParcelable("com.google.android.gms.identity.intents.EXTRA_PENDING_INTENT");
            }
            ConnectionResult connectionResult = new ConnectionResult(i, pendingIntent);
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this.mActivity, this.zzj);
                } catch (SendIntentException e2) {
                    e = e2;
                    str = "AddressClientImpl";
                    str2 = "Exception starting pending intent";
                    Log.w(str, str2, e);
                }
            } else {
                try {
                    PendingIntent createPendingResult2 = this.mActivity.createPendingResult(this.zzj, new Intent(), ErrorDialogData.SUPPRESSED);
                    if (createPendingResult2 != null) {
                        createPendingResult2.send(1);
                    }
                } catch (CanceledException e3) {
                    e = e3;
                    str = "AddressClientImpl";
                    str2 = "Exception setting pending result";
                    Log.w(str, str2, e);
                }
            }
        }
    }
}
