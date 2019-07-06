package androidx.work.impl.a.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.net.ConnectivityManagerCompat;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: NetworkStateTracker */
public class e extends d<androidx.work.impl.a.b> {
    private final ConnectivityManager b = ((ConnectivityManager) this.a.getSystemService("connectivity"));
    @RequiresApi(24)
    private b c;
    private a d;

    /* compiled from: NetworkStateTracker */
    private class a extends BroadcastReceiver {
        a() {
        }

        public void onReceive(Context context, Intent intent) {
            if (!(intent == null || intent.getAction() == null || !intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE"))) {
                androidx.work.e.b("NetworkStateTracker", "Network broadcast received", new Throwable[0]);
                e.this.a(e.this.b());
            }
        }
    }

    @RequiresApi(24)
    /* compiled from: NetworkStateTracker */
    private class b extends NetworkCallback {
        b() {
        }

        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            androidx.work.e.b("NetworkStateTracker", String.format("Network capabilities changed: %s", new Object[]{networkCapabilities}), new Throwable[0]);
            e.this.a(e.this.b());
        }

        public void onLost(Network network) {
            androidx.work.e.b("NetworkStateTracker", "Network connection lost", new Throwable[0]);
            e.this.a(e.this.b());
        }
    }

    public e(Context context) {
        super(context);
        if (f()) {
            this.c = new b();
        } else {
            this.d = new a();
        }
    }

    /* renamed from: a */
    public androidx.work.impl.a.b c() {
        return b();
    }

    public void d() {
        if (f()) {
            androidx.work.e.b("NetworkStateTracker", "Registering network callback", new Throwable[0]);
            this.b.registerDefaultNetworkCallback(this.c);
            return;
        }
        androidx.work.e.b("NetworkStateTracker", "Registering broadcast receiver", new Throwable[0]);
        this.a.registerReceiver(this.d, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public void e() {
        if (f()) {
            androidx.work.e.b("NetworkStateTracker", "Unregistering network callback", new Throwable[0]);
            this.b.unregisterNetworkCallback(this.c);
            return;
        }
        androidx.work.e.b("NetworkStateTracker", "Unregistering broadcast receiver", new Throwable[0]);
        this.a.unregisterReceiver(this.d);
    }

    private static boolean f() {
        return VERSION.SDK_INT >= 24;
    }

    /* access modifiers changed from: 0000 */
    public androidx.work.impl.a.b b() {
        NetworkInfo activeNetworkInfo = this.b.getActiveNetworkInfo();
        boolean z = false;
        boolean z2 = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        boolean g = g();
        boolean isActiveNetworkMetered = ConnectivityManagerCompat.isActiveNetworkMetered(this.b);
        if (activeNetworkInfo != null && !activeNetworkInfo.isRoaming()) {
            z = true;
        }
        return new androidx.work.impl.a.b(z2, g, isActiveNetworkMetered, z);
    }

    private boolean g() {
        boolean z = false;
        if (VERSION.SDK_INT < 23) {
            return false;
        }
        NetworkCapabilities networkCapabilities = this.b.getNetworkCapabilities(this.b.getActiveNetwork());
        if (networkCapabilities != null && networkCapabilities.hasCapability(16)) {
            z = true;
        }
        return z;
    }
}
