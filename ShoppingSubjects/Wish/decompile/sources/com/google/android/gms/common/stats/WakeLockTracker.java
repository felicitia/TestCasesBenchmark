package com.google.android.gms.common.stats;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.DeviceStateUtils;
import java.util.List;

public class WakeLockTracker {
    private static boolean zzyh = false;
    private static WakeLockTracker zzyz = new WakeLockTracker();
    private static Boolean zzza;

    public static WakeLockTracker getInstance() {
        return zzyz;
    }

    public void registerEvent(Context context, String str, int i, String str2, String str3, String str4, int i2, List<String> list) {
        registerEvent(context, str, i, str2, str3, str4, i2, list, 0);
    }

    public void registerEvent(Context context, String str, int i, String str2, String str3, String str4, int i2, List<String> list, long j) {
        int i3 = i;
        List<String> list2 = list;
        if (zzza == null) {
            zzza = Boolean.valueOf(false);
        }
        if (zzza.booleanValue()) {
            if (TextUtils.isEmpty(str)) {
                String str5 = "WakeLockTracker";
                String str6 = "missing wakeLock key. ";
                String valueOf = String.valueOf(str);
                Log.e(str5, valueOf.length() != 0 ? str6.concat(valueOf) : new String(str6));
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (7 == i3 || 8 == i3 || 10 == i3 || 11 == i3) {
                if (list2 != null && list.size() == 1 && "com.google.android.gms".equals(list2.get(0))) {
                    list2 = null;
                }
                List<String> list3 = list2;
                long elapsedRealtime = SystemClock.elapsedRealtime();
                int deviceState = DeviceStateUtils.getDeviceState(context);
                String packageName = context.getPackageName();
                WakeLockEvent wakeLockEvent = r1;
                WakeLockEvent wakeLockEvent2 = new WakeLockEvent(currentTimeMillis, i3, str2, i2, list3, str, elapsedRealtime, deviceState, str3, "com.google.android.gms".equals(packageName) ? null : packageName, DeviceStateUtils.getPowerPercentage(context), j, str4);
                try {
                    context.startService(new Intent().setComponent(LoggingConstants.STATS_SERVICE_COMPONENT_NAME).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", wakeLockEvent));
                } catch (Exception e) {
                    Log.wtf("WakeLockTracker", e);
                }
            }
        }
    }
}
