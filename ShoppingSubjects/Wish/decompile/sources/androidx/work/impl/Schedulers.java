package androidx.work.impl;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import androidx.work.impl.background.systemalarm.SystemAlarmScheduler;
import androidx.work.impl.background.systemalarm.SystemAlarmService;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.PackageManagerHelper;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Schedulers {
    public static void schedule(WorkDatabase workDatabase, List<Scheduler> list) {
        scheduleInternal(workDatabase, list, workDatabase.workSpecDao().getEligibleWorkForScheduling());
    }

    /* JADX INFO: finally extract failed */
    private static void scheduleInternal(WorkDatabase workDatabase, List<Scheduler> list, List<WorkSpec> list2) {
        if (list2 != null && list != null) {
            long currentTimeMillis = System.currentTimeMillis();
            WorkSpecDao workSpecDao = workDatabase.workSpecDao();
            workDatabase.beginTransaction();
            try {
                for (WorkSpec workSpec : list2) {
                    workSpecDao.markWorkSpecScheduled(workSpec.id, currentTimeMillis);
                }
                workDatabase.setTransactionSuccessful();
                workDatabase.endTransaction();
                WorkSpec[] workSpecArr = (WorkSpec[]) list2.toArray(new WorkSpec[0]);
                for (Scheduler schedule : list) {
                    schedule.schedule(workSpecArr);
                }
            } catch (Throwable th) {
                workDatabase.endTransaction();
                throw th;
            }
        }
    }

    static Scheduler createBestAvailableBackgroundScheduler(Context context) {
        Scheduler scheduler;
        boolean z = false;
        boolean z2 = true;
        if (VERSION.SDK_INT >= 23) {
            scheduler = new SystemJobScheduler(context);
            PackageManagerHelper.setComponentEnabled(context, SystemJobService.class, true);
            Log.d("Schedulers", "Created SystemJobScheduler and enabled SystemJobService");
        } else {
            try {
                scheduler = tryCreateFirebaseJobScheduler(context);
                try {
                    Log.d("Schedulers", "Created FirebaseJobScheduler");
                    z = true;
                } catch (Exception unused) {
                    z = true;
                    scheduler = new SystemAlarmScheduler(context);
                    Log.d("Schedulers", "Created SystemAlarmScheduler");
                    PackageManagerHelper.setComponentEnabled(context, Class.forName("androidx.work.impl.background.firebase.FirebaseJobService"), z);
                    PackageManagerHelper.setComponentEnabled(context, SystemAlarmService.class, z2);
                    return scheduler;
                }
            } catch (Exception unused2) {
                scheduler = new SystemAlarmScheduler(context);
                Log.d("Schedulers", "Created SystemAlarmScheduler");
                PackageManagerHelper.setComponentEnabled(context, Class.forName("androidx.work.impl.background.firebase.FirebaseJobService"), z);
                PackageManagerHelper.setComponentEnabled(context, SystemAlarmService.class, z2);
                return scheduler;
            }
        }
        z2 = false;
        try {
            PackageManagerHelper.setComponentEnabled(context, Class.forName("androidx.work.impl.background.firebase.FirebaseJobService"), z);
        } catch (ClassNotFoundException unused3) {
        }
        PackageManagerHelper.setComponentEnabled(context, SystemAlarmService.class, z2);
        return scheduler;
    }

    private static Scheduler tryCreateFirebaseJobScheduler(Context context) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        return (Scheduler) Class.forName("androidx.work.impl.background.firebase.FirebaseJobScheduler").getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
    }
}
