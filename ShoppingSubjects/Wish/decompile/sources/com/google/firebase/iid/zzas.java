package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import java.io.IOException;

final class zzas implements Runnable {
    private final zzah zzao;
    private final zzau zzar;
    private final long zzdb;
    private final WakeLock zzdc = ((PowerManager) getContext().getSystemService("power")).newWakeLock(1, "fiid-sync");
    private final FirebaseInstanceId zzdd;

    zzas(FirebaseInstanceId firebaseInstanceId, zzah zzah, zzau zzau, long j) {
        this.zzdd = firebaseInstanceId;
        this.zzao = zzah;
        this.zzar = zzau;
        this.zzdb = j;
        this.zzdc.setReferenceCounted(false);
    }

    private final boolean zzag() {
        zzar zzg = this.zzdd.zzg();
        if (zzg != null && !zzg.zzj(this.zzao.zzx())) {
            return true;
        }
        try {
            String zzh = this.zzdd.zzh();
            if (zzh == null) {
                Log.e("FirebaseInstanceId", "Token retrieval failed: null");
                return false;
            }
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Token successfully retrieved");
            }
            if (zzg == null || (zzg != null && !zzh.equals(zzg.zzcz))) {
                Context context = getContext();
                Intent intent = new Intent("com.google.firebase.iid.TOKEN_REFRESH");
                Intent intent2 = new Intent("com.google.firebase.INSTANCE_ID_EVENT");
                intent2.setClass(context, FirebaseInstanceIdReceiver.class);
                intent2.putExtra("wrapped_intent", intent);
                context.sendBroadcast(intent2);
            }
            return true;
        } catch (IOException | SecurityException e) {
            String str = "FirebaseInstanceId";
            String str2 = "Token retrieval failed: ";
            String valueOf = String.valueOf(e.getMessage());
            Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public final Context getContext() {
        return this.zzdd.zze().getApplicationContext();
    }

    public final void run() {
        FirebaseInstanceId firebaseInstanceId;
        this.zzdc.acquire();
        try {
            boolean z = true;
            this.zzdd.zza(true);
            if (this.zzao.zzw() == 0) {
                z = false;
            }
            if (!z) {
                firebaseInstanceId = this.zzdd;
            } else {
                if (!zzah()) {
                    new zzat(this).zzai();
                } else if (!zzag() || !this.zzar.zza(this.zzdd)) {
                    this.zzdd.zza(this.zzdb);
                } else {
                    firebaseInstanceId = this.zzdd;
                }
            }
            firebaseInstanceId.zza(false);
        } finally {
            this.zzdc.release();
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzah() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
