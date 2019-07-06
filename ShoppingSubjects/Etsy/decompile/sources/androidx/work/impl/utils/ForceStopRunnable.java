package androidx.work.impl.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.NotificationCompat;
import androidx.work.e;
import androidx.work.impl.f;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.concurrent.TimeUnit;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ForceStopRunnable implements Runnable {
    private static final long a = TimeUnit.DAYS.toMillis(3650);
    private final Context b;
    private final f c;

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static class BroadcastReceiver extends android.content.BroadcastReceiver {
        private static final String TAG = "ForceStopRunnable$Rcvr";

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if ("ACTION_FORCE_STOP_RESCHEDULE".equals(intent.getAction())) {
                    e.a(TAG, "Rescheduling alarm that keeps track of force-stops.", new Throwable[0]);
                    ForceStopRunnable.b(context);
                }
            }
        }
    }

    public ForceStopRunnable(@NonNull Context context, @NonNull f fVar) {
        this.b = context.getApplicationContext();
        this.c = fVar;
    }

    public void run() {
        if (b()) {
            e.b("ForceStopRunnable", "Rescheduling Workers.", new Throwable[0]);
            this.c.j();
            this.c.i().a(false);
        } else if (a()) {
            e.b("ForceStopRunnable", "Application was force-stopped, rescheduling.", new Throwable[0]);
            this.c.j();
        }
        this.c.k();
    }

    @VisibleForTesting
    public boolean a() {
        if (a(this.b, ErrorDialogData.DYNAMITE_CRASH) != null) {
            return false;
        }
        b(this.b);
        return true;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public boolean b() {
        return this.c.i().a();
    }

    private static PendingIntent a(Context context, int i) {
        return PendingIntent.getBroadcast(context, -1, a(context), i);
    }

    @VisibleForTesting
    static Intent a(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, BroadcastReceiver.class));
        intent.setAction("ACTION_FORCE_STOP_RESCHEDULE");
        return intent;
    }

    static void b(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent a2 = a(context, 134217728);
        long currentTimeMillis = System.currentTimeMillis() + a;
        if (alarmManager == null) {
            return;
        }
        if (VERSION.SDK_INT >= 19) {
            alarmManager.setExact(0, currentTimeMillis, a2);
        } else {
            alarmManager.set(0, currentTimeMillis, a2);
        }
    }
}
