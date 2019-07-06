package androidx.work.impl.background.systemalarm;

import android.arch.lifecycle.LifecycleService;
import android.content.Intent;
import android.util.Log;

public class SystemAlarmService extends LifecycleService implements CommandsCompletedListener {
    private SystemAlarmDispatcher mDispatcher;

    public void onCreate() {
        super.onCreate();
        this.mDispatcher = new SystemAlarmDispatcher(this);
        this.mDispatcher.setCompletedListener(this);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mDispatcher.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (intent != null) {
            this.mDispatcher.add(intent, i2);
        }
        return 1;
    }

    public void onAllCommandsCompleted() {
        Log.d("SystemAlarmService", "All commands completed in dispatcher");
        stopSelf();
    }
}
