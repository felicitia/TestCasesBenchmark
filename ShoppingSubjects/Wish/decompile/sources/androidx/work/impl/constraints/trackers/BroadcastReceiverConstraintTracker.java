package androidx.work.impl.constraints.trackers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public abstract class BroadcastReceiverConstraintTracker<T> extends ConstraintTracker<T> {
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                BroadcastReceiverConstraintTracker.this.onBroadcastReceive(context, intent);
            }
        }
    };

    public abstract IntentFilter getIntentFilter();

    public abstract void onBroadcastReceive(Context context, Intent intent);

    public BroadcastReceiverConstraintTracker(Context context) {
        super(context);
    }

    public void startTracking() {
        Log.d("BrdcstRcvrCnstrntTrckr", String.format("%s: registering receiver", new Object[]{getClass().getSimpleName()}));
        this.mAppContext.registerReceiver(this.mBroadcastReceiver, getIntentFilter());
    }

    public void stopTracking() {
        Log.d("BrdcstRcvrCnstrntTrckr", String.format("%s: unregistering receiver", new Object[]{getClass().getSimpleName()}));
        this.mAppContext.unregisterReceiver(this.mBroadcastReceiver);
    }
}
