package com.facebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.internal.aa;
import com.facebook.internal.z;

/* compiled from: AccessTokenTracker */
public abstract class c {
    /* access modifiers changed from: private */
    public static final String a = "c";
    private final BroadcastReceiver b;
    private final LocalBroadcastManager c;
    private boolean d = false;

    /* compiled from: AccessTokenTracker */
    private class a extends BroadcastReceiver {
        private a() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED".equals(intent.getAction())) {
                z.b(c.a, "AccessTokenChanged");
                c.this.a((AccessToken) intent.getParcelableExtra("com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN"), (AccessToken) intent.getParcelableExtra("com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN"));
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a(AccessToken accessToken, AccessToken accessToken2);

    public c() {
        aa.a();
        this.b = new a();
        this.c = LocalBroadcastManager.getInstance(f.f());
        a();
    }

    public void a() {
        if (!this.d) {
            e();
            this.d = true;
        }
    }

    public void b() {
        if (this.d) {
            this.c.unregisterReceiver(this.b);
            this.d = false;
        }
    }

    public boolean c() {
        return this.d;
    }

    private void e() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
        this.c.registerReceiver(this.b, intentFilter);
    }
}
