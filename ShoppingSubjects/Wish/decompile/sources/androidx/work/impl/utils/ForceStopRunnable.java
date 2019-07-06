package androidx.work.impl.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.util.Log;
import androidx.work.impl.WorkManagerImpl;
import java.util.concurrent.TimeUnit;

public class ForceStopRunnable implements Runnable {
    private static final long TEN_YEARS = TimeUnit.DAYS.toMillis(3650);
    private final Context mContext;
    private final WorkManagerImpl mWorkManager;

    public static class BroadcastReceiver extends android.content.BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if ("ACTION_FORCE_STOP_RESCHEDULE".equals(intent.getAction())) {
                    Log.v("ForceStopRunnable$Rcvr", "Rescheduling alarm that keeps track of force-stops.");
                    new ForceStopRunnable(context, WorkManagerImpl.getInstance()).setAlarm(-1);
                }
            }
        }
    }

    public ForceStopRunnable(Context context, WorkManagerImpl workManagerImpl) {
        this.mContext = context.getApplicationContext();
        this.mWorkManager = workManagerImpl;
    }

    public void run() {
        if (isForceStopped()) {
            Log.d("ForceStopRunnable", "Application was force-stopped, rescheduling.");
            this.mWorkManager.rescheduleEligibleWork();
        }
    }

    public boolean isForceStopped() {
        if (getPendingIntent(-1, 536870912) != null) {
            return false;
        }
        setAlarm(-1);
        return true;
    }

    public PendingIntent getPendingIntent(int i, int i2) {
        return PendingIntent.getBroadcast(this.mContext, i, getIntent(), i2);
    }

    public Intent getIntent() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this.mContext, BroadcastReceiver.class));
        intent.setAction("ACTION_FORCE_STOP_RESCHEDULE");
        return intent;
    }

    /* access modifiers changed from: private */
    public void setAlarm(int i) {
        AlarmManager alarmManager = (AlarmManager) this.mContext.getSystemService("alarm");
        PendingIntent pendingIntent = getPendingIntent(i, 134217728);
        long currentTimeMillis = System.currentTimeMillis() + TEN_YEARS;
        if (alarmManager == null) {
            return;
        }
        if (VERSION.SDK_INT >= 19) {
            alarmManager.setExact(0, currentTimeMillis, pendingIntent);
        } else {
            alarmManager.set(0, currentTimeMillis, pendingIntent);
        }
    }
}
