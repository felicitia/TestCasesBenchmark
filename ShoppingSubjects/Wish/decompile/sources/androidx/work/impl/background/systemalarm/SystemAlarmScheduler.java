package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.util.Log;
import androidx.work.impl.Scheduler;
import androidx.work.impl.model.WorkSpec;

public class SystemAlarmScheduler implements Scheduler {
    private final Context mContext;

    public SystemAlarmScheduler(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void schedule(WorkSpec... workSpecArr) {
        for (WorkSpec scheduleWorkSpec : workSpecArr) {
            scheduleWorkSpec(scheduleWorkSpec);
        }
    }

    public void cancel(String str) {
        this.mContext.startService(CommandHandler.createStopWorkIntent(this.mContext, str));
    }

    private void scheduleWorkSpec(WorkSpec workSpec) {
        Log.d("SystemAlarmScheduler", String.format("Scheduling work with workSpecId %s", new Object[]{workSpec.id}));
        this.mContext.startService(CommandHandler.createScheduleWorkIntent(this.mContext, workSpec.id));
    }
}
