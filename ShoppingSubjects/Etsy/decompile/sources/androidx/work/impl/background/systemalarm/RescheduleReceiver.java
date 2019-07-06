package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import androidx.work.e;
import androidx.work.impl.f;

public class RescheduleReceiver extends BroadcastReceiver {
    private static final String TAG = "RescheduleReceiver";

    public void onReceive(Context context, Intent intent) {
        if (VERSION.SDK_INT >= 23) {
            f b = f.b();
            if (b == null) {
                e.e(TAG, "Cannot reschedule jobs. WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().", new Throwable[0]);
            } else {
                b.a(goAsync());
            }
        } else {
            context.startService(b.b(context));
        }
    }
}
