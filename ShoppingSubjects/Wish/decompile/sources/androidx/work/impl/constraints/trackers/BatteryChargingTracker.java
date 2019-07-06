package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.util.Log;

public class BatteryChargingTracker extends BroadcastReceiverConstraintTracker<Boolean> {
    public BatteryChargingTracker(Context context) {
        super(context);
    }

    public Boolean getInitialState() {
        Intent registerReceiver = this.mAppContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver != null) {
            return Boolean.valueOf(isBatteryChangedIntentCharging(registerReceiver));
        }
        Log.e("BatteryChrgTracker", "getInitialState - null intent received");
        return null;
    }

    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        if (VERSION.SDK_INT >= 23) {
            intentFilter.addAction("android.os.action.CHARGING");
            intentFilter.addAction("android.os.action.DISCHARGING");
        } else {
            intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
            intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        }
        return intentFilter;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0077  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBroadcastReceive(android.content.Context r5, android.content.Intent r6) {
        /*
            r4 = this;
            java.lang.String r5 = r6.getAction()
            if (r5 != 0) goto L_0x0007
            return
        L_0x0007:
            java.lang.String r6 = "BatteryChrgTracker"
            java.lang.String r0 = "Received %s"
            r1 = 1
            java.lang.Object[] r2 = new java.lang.Object[r1]
            r3 = 0
            r2[r3] = r5
            java.lang.String r0 = java.lang.String.format(r0, r2)
            android.util.Log.d(r6, r0)
            r6 = -1
            int r0 = r5.hashCode()
            r2 = -1886648615(0xffffffff8f8c06d9, float:-1.3807703E-29)
            if (r0 == r2) goto L_0x0050
            r2 = -54942926(0xfffffffffcb9a332, float:-7.711079E36)
            if (r0 == r2) goto L_0x0046
            r2 = 948344062(0x388694fe, float:6.41737E-5)
            if (r0 == r2) goto L_0x003c
            r2 = 1019184907(0x3cbf870b, float:0.023379823)
            if (r0 == r2) goto L_0x0032
            goto L_0x005a
        L_0x0032:
            java.lang.String r0 = "android.intent.action.ACTION_POWER_CONNECTED"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x005a
            r5 = 2
            goto L_0x005b
        L_0x003c:
            java.lang.String r0 = "android.os.action.CHARGING"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x005a
            r5 = 0
            goto L_0x005b
        L_0x0046:
            java.lang.String r0 = "android.os.action.DISCHARGING"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x005a
            r5 = 1
            goto L_0x005b
        L_0x0050:
            java.lang.String r0 = "android.intent.action.ACTION_POWER_DISCONNECTED"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x005a
            r5 = 3
            goto L_0x005b
        L_0x005a:
            r5 = -1
        L_0x005b:
            switch(r5) {
                case 0: goto L_0x0077;
                case 1: goto L_0x006f;
                case 2: goto L_0x0067;
                case 3: goto L_0x005f;
                default: goto L_0x005e;
            }
        L_0x005e:
            goto L_0x007e
        L_0x005f:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r3)
            r4.setState(r5)
            goto L_0x007e
        L_0x0067:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)
            r4.setState(r5)
            goto L_0x007e
        L_0x006f:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r3)
            r4.setState(r5)
            goto L_0x007e
        L_0x0077:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)
            r4.setState(r5)
        L_0x007e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.constraints.trackers.BatteryChargingTracker.onBroadcastReceive(android.content.Context, android.content.Intent):void");
    }

    private boolean isBatteryChangedIntentCharging(Intent intent) {
        if (VERSION.SDK_INT >= 23) {
            int intExtra = intent.getIntExtra("status", -1);
            if (intExtra == 2 || intExtra == 5) {
                return true;
            }
        } else if (intent.getIntExtra("plugged", 0) != 0) {
            return true;
        }
        return false;
    }
}
