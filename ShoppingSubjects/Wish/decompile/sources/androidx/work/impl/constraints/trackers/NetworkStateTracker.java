package androidx.work.impl.constraints.trackers;

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
import android.support.v4.net.ConnectivityManagerCompat;
import android.util.Log;
import androidx.work.impl.constraints.NetworkState;

public class NetworkStateTracker extends ConstraintTracker<NetworkState> {
    private NetworkStateBroadcastReceiver mBroadcastReceiver;
    private final ConnectivityManager mConnectivityManager = ((ConnectivityManager) this.mAppContext.getSystemService("connectivity"));
    private NetworkStateCallback mNetworkCallback;

    private class NetworkStateBroadcastReceiver extends BroadcastReceiver {
        private NetworkStateBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (!(intent == null || intent.getAction() == null || !intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE"))) {
                Log.d("NetworkStateTracker", "Network broadcast received");
                NetworkStateTracker.this.setState(NetworkStateTracker.this.getActiveNetworkState());
            }
        }
    }

    private class NetworkStateCallback extends NetworkCallback {
        private NetworkStateCallback() {
        }

        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            Log.d("NetworkStateTracker", String.format("Network capabilities changed: %s", new Object[]{networkCapabilities}));
            NetworkStateTracker.this.setState(NetworkStateTracker.this.getActiveNetworkState());
        }

        public void onLost(Network network) {
            Log.d("NetworkStateTracker", "Network connection lost");
            NetworkStateTracker.this.setState(NetworkStateTracker.this.getActiveNetworkState());
        }
    }

    public NetworkStateTracker(Context context) {
        super(context);
        if (isNetworkCallbackSupported()) {
            this.mNetworkCallback = new NetworkStateCallback();
        } else {
            this.mBroadcastReceiver = new NetworkStateBroadcastReceiver();
        }
    }

    public NetworkState getInitialState() {
        return getActiveNetworkState();
    }

    public void startTracking() {
        if (isNetworkCallbackSupported()) {
            Log.d("NetworkStateTracker", "Registering network callback");
            this.mConnectivityManager.registerDefaultNetworkCallback(this.mNetworkCallback);
            return;
        }
        Log.d("NetworkStateTracker", "Registering broadcast receiver");
        this.mAppContext.registerReceiver(this.mBroadcastReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public void stopTracking() {
        if (isNetworkCallbackSupported()) {
            Log.d("NetworkStateTracker", "Unregistering network callback");
            this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
            return;
        }
        Log.d("NetworkStateTracker", "Unregistering broadcast receiver");
        this.mAppContext.unregisterReceiver(this.mBroadcastReceiver);
    }

    private static boolean isNetworkCallbackSupported() {
        return VERSION.SDK_INT >= 24;
    }

    /* access modifiers changed from: private */
    public NetworkState getActiveNetworkState() {
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        boolean z = false;
        boolean z2 = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        boolean isActiveNetworkValidated = isActiveNetworkValidated();
        boolean isActiveNetworkMetered = ConnectivityManagerCompat.isActiveNetworkMetered(this.mConnectivityManager);
        if (activeNetworkInfo != null && !activeNetworkInfo.isRoaming()) {
            z = true;
        }
        return new NetworkState(z2, isActiveNetworkValidated, isActiveNetworkMetered, z);
    }

    private boolean isActiveNetworkValidated() {
        boolean z = false;
        if (VERSION.SDK_INT < 23) {
            return false;
        }
        NetworkCapabilities networkCapabilities = this.mConnectivityManager.getNetworkCapabilities(this.mConnectivityManager.getActiveNetwork());
        if (networkCapabilities != null && networkCapabilities.hasCapability(16)) {
            z = true;
        }
        return z;
    }
}
