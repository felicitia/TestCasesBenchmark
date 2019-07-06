package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.model.WorkSpec;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler implements ExecutionListener {
    private final Context mContext;
    private final Object mLock = new Object();
    private final Map<String, ExecutionListener> mPendingDelayMet = new HashMap();

    static Intent createScheduleWorkIntent(Context context, String str) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_SCHEDULE_WORK");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    static Intent createDelayMetIntent(Context context, String str) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_DELAY_MET");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    static Intent createStopWorkIntent(Context context, String str) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_STOP_WORK");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    static Intent createConstraintsChangedIntent(Context context) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_CONSTRAINTS_CHANGED");
        return intent;
    }

    static Intent createRescheduleIntent(Context context) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_RESCHEDULE");
        return intent;
    }

    static Intent createExecutionCompletedIntent(Context context, String str, boolean z, boolean z2) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_EXECUTION_COMPLETED");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        intent.putExtra("KEY_IS_SUCCESSFUL", z);
        intent.putExtra("KEY_NEEDS_RESCHEDULE", z2);
        return intent;
    }

    CommandHandler(Context context) {
        this.mContext = context;
    }

    public void onExecuted(String str, boolean z, boolean z2) {
        synchronized (this.mLock) {
            ExecutionListener executionListener = (ExecutionListener) this.mPendingDelayMet.remove(str);
            if (executionListener != null) {
                executionListener.onExecuted(str, z, z2);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean hasPendingCommands() {
        boolean z;
        synchronized (this.mLock) {
            z = !this.mPendingDelayMet.isEmpty();
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public void onHandleIntent(Intent intent, int i, SystemAlarmDispatcher systemAlarmDispatcher) {
        String action = intent.getAction();
        if ("ACTION_CONSTRAINTS_CHANGED".equals(action)) {
            handleConstraintsChanged(intent, i, systemAlarmDispatcher);
        } else if ("ACTION_RESCHEDULE".equals(action)) {
            handleReschedule(intent, i, systemAlarmDispatcher);
        } else {
            if (!hasKeys(intent.getExtras(), "KEY_WORKSPEC_ID")) {
                Log.e("CommandHandler", String.format("Invalid request for %s, requires %s.", new Object[]{action, "KEY_WORKSPEC_ID"}));
            } else if ("ACTION_SCHEDULE_WORK".equals(action)) {
                handleScheduleWorkIntent(intent, i, systemAlarmDispatcher);
            } else if ("ACTION_DELAY_MET".equals(action)) {
                handleDelayMet(intent, i, systemAlarmDispatcher);
            } else if ("ACTION_STOP_WORK".equals(action)) {
                handleStopWork(intent, i, systemAlarmDispatcher);
            } else if ("ACTION_EXECUTION_COMPLETED".equals(action)) {
                handleExecutionCompleted(intent, i, systemAlarmDispatcher);
            } else {
                Log.w("CommandHandler", String.format("Ignoring intent %s", new Object[]{intent}));
            }
        }
    }

    private void handleScheduleWorkIntent(Intent intent, int i, SystemAlarmDispatcher systemAlarmDispatcher) {
        String string = intent.getExtras().getString("KEY_WORKSPEC_ID");
        Log.d("CommandHandler", String.format("Handling schedule work for %s", new Object[]{string}));
        WorkSpec workSpec = systemAlarmDispatcher.getWorkManager().getWorkDatabase().workSpecDao().getWorkSpec(string);
        long calculateNextRunTime = workSpec.calculateNextRunTime();
        if (!workSpec.hasConstraints()) {
            Log.d("CommandHandler", String.format("Setting up Alarms for %s", new Object[]{string}));
            Alarms.setAlarm(this.mContext, systemAlarmDispatcher.getWorkManager(), string, calculateNextRunTime);
            return;
        }
        Log.d("CommandHandler", String.format("Opportunistically setting an alarm for %s", new Object[]{string}));
        Alarms.setAlarm(this.mContext, systemAlarmDispatcher.getWorkManager(), string, calculateNextRunTime);
        systemAlarmDispatcher.postOnMainThread(new AddRunnable(systemAlarmDispatcher, createConstraintsChangedIntent(this.mContext), i));
    }

    private void handleDelayMet(Intent intent, int i, SystemAlarmDispatcher systemAlarmDispatcher) {
        Bundle extras = intent.getExtras();
        synchronized (this.mLock) {
            String string = extras.getString("KEY_WORKSPEC_ID");
            Log.d("CommandHandler", String.format("Handing delay met for %s", new Object[]{string}));
            DelayMetCommandHandler delayMetCommandHandler = new DelayMetCommandHandler(this.mContext, i, string, systemAlarmDispatcher);
            this.mPendingDelayMet.put(string, delayMetCommandHandler);
            delayMetCommandHandler.handleProcessWork();
        }
    }

    private void handleStopWork(Intent intent, int i, SystemAlarmDispatcher systemAlarmDispatcher) {
        String string = intent.getExtras().getString("KEY_WORKSPEC_ID");
        Log.d("CommandHandler", String.format("Handing stopWork work for %s", new Object[]{string}));
        systemAlarmDispatcher.getWorkManager().stopWork(string);
        Alarms.cancelAlarm(this.mContext, systemAlarmDispatcher.getWorkManager(), string);
        systemAlarmDispatcher.onExecuted(string, false, false);
    }

    private void handleConstraintsChanged(Intent intent, int i, SystemAlarmDispatcher systemAlarmDispatcher) {
        Log.d("CommandHandler", String.format("Handling constraints changed %s", new Object[]{intent}));
        new ConstraintsCommandHandler(this.mContext, i, systemAlarmDispatcher).handleConstraintsChanged();
    }

    private void handleReschedule(Intent intent, int i, SystemAlarmDispatcher systemAlarmDispatcher) {
        Log.d("CommandHandler", String.format("Handling reschedule %s, %s", new Object[]{intent, Integer.valueOf(i)}));
        systemAlarmDispatcher.getWorkManager().rescheduleEligibleWork();
    }

    private void handleExecutionCompleted(Intent intent, int i, SystemAlarmDispatcher systemAlarmDispatcher) {
        Bundle extras = intent.getExtras();
        String string = extras.getString("KEY_WORKSPEC_ID");
        boolean z = extras.getBoolean("KEY_IS_SUCCESSFUL");
        boolean z2 = extras.getBoolean("KEY_NEEDS_RESCHEDULE");
        Log.d("CommandHandler", String.format("Handling onExecutionCompleted %s, %s", new Object[]{intent, Integer.valueOf(i)}));
        onExecuted(string, z, z2);
        systemAlarmDispatcher.postOnMainThread(new CheckForCompletionRunnable(systemAlarmDispatcher));
    }

    private static boolean hasKeys(Bundle bundle, String... strArr) {
        if (bundle == null || bundle.isEmpty()) {
            return false;
        }
        for (String str : strArr) {
            if (!bundle.containsKey(str) || bundle.get(str) == null) {
                return false;
            }
        }
        return true;
    }
}
