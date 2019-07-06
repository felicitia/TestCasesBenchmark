package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobInfo.TriggerContentUri;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import android.util.Log;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.ContentUriTriggers.Trigger;
import androidx.work.NetworkType;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.IdGenerator;
import java.util.Iterator;

class SystemJobInfoConverter {
    private final IdGenerator mIdGenerator;
    private final ComponentName mWorkServiceComponent;

    SystemJobInfoConverter(Context context) {
        this(context, new IdGenerator(context));
    }

    SystemJobInfoConverter(Context context, IdGenerator idGenerator) {
        this.mWorkServiceComponent = new ComponentName(context.getApplicationContext(), SystemJobService.class);
        this.mIdGenerator = idGenerator;
    }

    /* access modifiers changed from: 0000 */
    public JobInfo convert(WorkSpec workSpec) {
        Constraints constraints = workSpec.constraints;
        int nextJobSchedulerId = this.mIdGenerator.nextJobSchedulerId();
        int convertNetworkType = convertNetworkType(constraints.getRequiredNetworkType());
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString("EXTRA_WORK_SPEC_ID", workSpec.id);
        persistableBundle.putBoolean("EXTRA_IS_PERIODIC", workSpec.isPeriodic());
        Builder extras = new Builder(nextJobSchedulerId, this.mWorkServiceComponent).setRequiredNetworkType(convertNetworkType).setRequiresCharging(constraints.requiresCharging()).setRequiresDeviceIdle(constraints.requiresDeviceIdle()).setExtras(persistableBundle);
        if (!constraints.requiresDeviceIdle()) {
            extras.setBackoffCriteria(workSpec.backoffDelayDuration, workSpec.backoffPolicy == BackoffPolicy.LINEAR ? 0 : 1);
        }
        if (!workSpec.isPeriodic()) {
            extras.setMinimumLatency(workSpec.initialDelay);
        } else if (VERSION.SDK_INT >= 24) {
            extras.setPeriodic(workSpec.intervalDuration, workSpec.flexDuration);
        } else {
            Log.d("SystemJobInfoConverter", "Flex duration is currently not supported before API 24. Ignoring.");
            extras.setPeriodic(workSpec.intervalDuration);
        }
        if (VERSION.SDK_INT < 24 || !constraints.hasContentUriTriggers()) {
            extras.setPersisted(true);
        } else {
            Iterator it = constraints.getContentUriTriggers().iterator();
            while (it.hasNext()) {
                extras.addTriggerContentUri(convertContentUriTrigger((Trigger) it.next()));
            }
        }
        if (VERSION.SDK_INT >= 26) {
            extras.setRequiresBatteryNotLow(constraints.requiresBatteryNotLow());
            extras.setRequiresStorageNotLow(constraints.requiresStorageNotLow());
        }
        return extras.build();
    }

    private static TriggerContentUri convertContentUriTrigger(Trigger trigger) {
        return new TriggerContentUri(trigger.getUri(), trigger.shouldTriggerForDescendants() ? 1 : 0);
    }

    static int convertNetworkType(NetworkType networkType) {
        switch (networkType) {
            case NOT_REQUIRED:
                return 0;
            case CONNECTED:
                return 1;
            case UNMETERED:
                return 2;
            case NOT_ROAMING:
                if (VERSION.SDK_INT >= 24) {
                    return 3;
                }
                break;
            case METERED:
                if (VERSION.SDK_INT >= 26) {
                    return 4;
                }
                break;
        }
        Log.d("SystemJobInfoConverter", String.format("API version too low. Cannot convert network type value %s", new Object[]{networkType}));
        return 1;
    }
}
