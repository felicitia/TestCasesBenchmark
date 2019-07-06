package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;

final class r implements Runnable {
    private final long a;
    private final WakeLock b = ((PowerManager) a().getSystemService("power")).newWakeLock(1, "fiid-sync");
    private final FirebaseInstanceId c;
    private final g d;
    private final t e;

    @VisibleForTesting
    r(FirebaseInstanceId firebaseInstanceId, g gVar, t tVar, long j) {
        this.c = firebaseInstanceId;
        this.d = gVar;
        this.e = tVar;
        this.a = j;
        this.b.setReferenceCounted(false);
    }

    @VisibleForTesting
    private final boolean c() {
        q d2 = this.c.d();
        if (d2 != null && !d2.b(this.d.b())) {
            return true;
        }
        try {
            String e2 = this.c.e();
            if (e2 == null) {
                Log.e("FirebaseInstanceId", "Token retrieval failed: null");
                return false;
            }
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Token successfully retrieved");
            }
            if (d2 == null || (d2 != null && !e2.equals(d2.a))) {
                Context a2 = a();
                Intent intent = new Intent("com.google.firebase.iid.TOKEN_REFRESH");
                Intent intent2 = new Intent("com.google.firebase.INSTANCE_ID_EVENT");
                intent2.setClass(a2, FirebaseInstanceIdReceiver.class);
                intent2.putExtra("wrapped_intent", intent);
                a2.sendBroadcast(intent2);
            }
            return true;
        } catch (IOException | SecurityException e3) {
            String str = "FirebaseInstanceId";
            String str2 = "Token retrieval failed: ";
            String valueOf = String.valueOf(e3.getMessage());
            Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public final Context a() {
        return this.c.b().a();
    }

    /* access modifiers changed from: 0000 */
    public final boolean b() {
        ConnectivityManager connectivityManager = (ConnectivityManager) a().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public final void run() {
        FirebaseInstanceId firebaseInstanceId;
        this.b.acquire();
        try {
            boolean z = true;
            this.c.a(true);
            if (this.d.a() == 0) {
                z = false;
            }
            if (!z) {
                firebaseInstanceId = this.c;
            } else {
                if (!b()) {
                    new s(this).a();
                } else if (!c() || !this.e.a(this.c)) {
                    this.c.a(this.a);
                } else {
                    firebaseInstanceId = this.c;
                }
            }
            firebaseInstanceId.a(false);
        } finally {
            this.b.release();
        }
    }
}
