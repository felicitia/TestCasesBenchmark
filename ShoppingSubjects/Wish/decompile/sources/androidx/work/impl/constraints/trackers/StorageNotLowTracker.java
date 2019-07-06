package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class StorageNotLowTracker extends BroadcastReceiverConstraintTracker<Boolean> {
    public StorageNotLowTracker(Context context) {
        super(context);
    }

    public Boolean getInitialState() {
        Intent registerReceiver = this.mAppContext.registerReceiver(null, getIntentFilter());
        if (registerReceiver == null || registerReceiver.getAction() == null) {
            return Boolean.valueOf(true);
        }
        String action = registerReceiver.getAction();
        char c = 65535;
        int hashCode = action.hashCode();
        if (hashCode != -1181163412) {
            if (hashCode == -730838620 && action.equals("android.intent.action.DEVICE_STORAGE_OK")) {
                c = 0;
            }
        } else if (action.equals("android.intent.action.DEVICE_STORAGE_LOW")) {
            c = 1;
        }
        switch (c) {
            case 0:
                return Boolean.valueOf(true);
            case 1:
                return Boolean.valueOf(false);
            default:
                return null;
        }
    }

    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.DEVICE_STORAGE_OK");
        intentFilter.addAction("android.intent.action.DEVICE_STORAGE_LOW");
        return intentFilter;
    }

    public void onBroadcastReceive(Context context, Intent intent) {
        if (intent.getAction() != null) {
            Log.d("StorageNotLowTracker", String.format("Received %s", new Object[]{intent.getAction()}));
            String action = intent.getAction();
            char c = 65535;
            int hashCode = action.hashCode();
            if (hashCode != -1181163412) {
                if (hashCode == -730838620 && action.equals("android.intent.action.DEVICE_STORAGE_OK")) {
                    c = 0;
                }
            } else if (action.equals("android.intent.action.DEVICE_STORAGE_LOW")) {
                c = 1;
            }
            switch (c) {
                case 0:
                    setState(Boolean.valueOf(true));
                    break;
                case 1:
                    setState(Boolean.valueOf(false));
                    break;
            }
        }
    }
}
