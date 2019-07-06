package androidx.work.impl.constraints.trackers;

import android.content.Context;

public class Trackers {
    private static Trackers sInstance;
    private BatteryChargingTracker mBatteryChargingTracker;
    private BatteryNotLowTracker mBatteryNotLowTracker;
    private NetworkStateTracker mNetworkStateTracker;
    private StorageNotLowTracker mStorageNotLowTracker;

    public static synchronized Trackers getInstance(Context context) {
        Trackers trackers;
        synchronized (Trackers.class) {
            if (sInstance == null) {
                sInstance = new Trackers(context);
            }
            trackers = sInstance;
        }
        return trackers;
    }

    private Trackers(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mBatteryChargingTracker = new BatteryChargingTracker(applicationContext);
        this.mBatteryNotLowTracker = new BatteryNotLowTracker(applicationContext);
        this.mNetworkStateTracker = new NetworkStateTracker(applicationContext);
        this.mStorageNotLowTracker = new StorageNotLowTracker(applicationContext);
    }

    public BatteryChargingTracker getBatteryChargingTracker() {
        return this.mBatteryChargingTracker;
    }

    public BatteryNotLowTracker getBatteryNotLowTracker() {
        return this.mBatteryNotLowTracker;
    }

    public NetworkStateTracker getNetworkStateTracker() {
        return this.mNetworkStateTracker;
    }

    public StorageNotLowTracker getStorageNotLowTracker() {
        return this.mStorageNotLowTracker;
    }
}
