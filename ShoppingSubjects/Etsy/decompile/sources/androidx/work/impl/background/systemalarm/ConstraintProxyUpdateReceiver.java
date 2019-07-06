package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.e;
import androidx.work.impl.background.systemalarm.ConstraintProxy.BatteryChargingProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxy.BatteryNotLowProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxy.NetworkStateProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxy.StorageNotLowProxy;
import androidx.work.impl.utils.d;

public class ConstraintProxyUpdateReceiver extends BroadcastReceiver {
    static final String ACTION = "androidx.work.impl.background.systemalarm.UpdateProxies";
    static final String KEY_BATTERY_CHARGING_PROXY_ENABLED = "KEY_BATTERY_CHARGING_PROXY_ENABLED";
    static final String KEY_BATTERY_NOT_LOW_PROXY_ENABLED = "KEY_BATTERY_NOT_LOW_PROXY_ENABLED";
    static final String KEY_NETWORK_STATE_PROXY_ENABLED = "KEY_NETWORK_STATE_PROXY_ENABLED";
    static final String KEY_STORAGE_NOT_LOW_PROXY_ENABLED = "KEY_STORAGE_NOT_LOW_PROXY_ENABLED";
    private static final String TAG = "ConstrntProxyUpdtRecvr";

    public static Intent newConstraintProxyUpdateIntent(boolean z, boolean z2, boolean z3, boolean z4) {
        Intent intent = new Intent(ACTION);
        intent.putExtra(KEY_BATTERY_NOT_LOW_PROXY_ENABLED, z).putExtra(KEY_BATTERY_CHARGING_PROXY_ENABLED, z2).putExtra(KEY_STORAGE_NOT_LOW_PROXY_ENABLED, z3).putExtra(KEY_NETWORK_STATE_PROXY_ENABLED, z4);
        return intent;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent != null ? intent.getAction() : null;
        if (!ACTION.equals(action)) {
            e.b(TAG, String.format("Ignoring unknown action %s", new Object[]{action}), new Throwable[0]);
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra(KEY_BATTERY_NOT_LOW_PROXY_ENABLED, false);
        boolean booleanExtra2 = intent.getBooleanExtra(KEY_BATTERY_CHARGING_PROXY_ENABLED, false);
        boolean booleanExtra3 = intent.getBooleanExtra(KEY_STORAGE_NOT_LOW_PROXY_ENABLED, false);
        boolean booleanExtra4 = intent.getBooleanExtra(KEY_NETWORK_STATE_PROXY_ENABLED, false);
        e.b(TAG, String.format("Updating proxies: BatteryNotLowProxy enabled (%s), BatteryChargingProxy enabled (%s), StorageNotLowProxy (%s), NetworkStateProxy enabled (%s)", new Object[]{Boolean.valueOf(booleanExtra), Boolean.valueOf(booleanExtra2), Boolean.valueOf(booleanExtra3), Boolean.valueOf(booleanExtra4)}), new Throwable[0]);
        d.a(context, BatteryNotLowProxy.class, booleanExtra);
        d.a(context, BatteryChargingProxy.class, booleanExtra2);
        d.a(context, StorageNotLowProxy.class, booleanExtra3);
        d.a(context, NetworkStateProxy.class, booleanExtra4);
    }
}
