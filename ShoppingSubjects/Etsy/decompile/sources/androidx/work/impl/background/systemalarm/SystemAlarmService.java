package androidx.work.impl.background.systemalarm;

import android.arch.lifecycle.LifecycleService;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.e;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SystemAlarmService extends LifecycleService implements c {
    private static final String TAG = "SystemAlarmService";
    private e mDispatcher;

    public void onCreate() {
        super.onCreate();
        this.mDispatcher = new e(this);
        this.mDispatcher.a((c) this);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mDispatcher.a();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (intent != null) {
            this.mDispatcher.a(intent, i2);
        }
        return 1;
    }

    @MainThread
    public void onAllCommandsCompleted() {
        e.b(TAG, "All commands completed in dispatcher", new Throwable[0]);
        stopSelf();
    }
}
