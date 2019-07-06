package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class s extends BroadcastReceiver {
    private r a;

    public s(r rVar) {
        this.a = rVar;
    }

    public final void a() {
        if (FirebaseInstanceId.f()) {
            Log.d("FirebaseInstanceId", "Connectivity change received registered");
        }
        this.a.a().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.a != null && this.a.b()) {
            if (FirebaseInstanceId.f()) {
                Log.d("FirebaseInstanceId", "Connectivity changed. Starting background sync.");
            }
            FirebaseInstanceId.a((Runnable) this.a, 0);
            this.a.a().unregisterReceiver(this);
            this.a = null;
        }
    }
}
