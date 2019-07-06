package androidx.work.impl.background.systemalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.AlarmInfo;
import androidx.work.impl.model.AlarmInfoDao;
import androidx.work.impl.utils.IdGenerator;

class Alarms {
    public static void setAlarm(Context context, WorkManagerImpl workManagerImpl, String str, long j) {
        AlarmInfoDao alarmInfoDao = workManagerImpl.getWorkDatabase().alarmInfoDao();
        AlarmInfo alarmInfo = alarmInfoDao.getAlarmInfo(str);
        if (alarmInfo != null) {
            cancelExactAlarm(context, str, alarmInfo.alarmId);
            setExactAlarm(context, str, alarmInfo.alarmId, j);
            return;
        }
        int nextAlarmManagerId = new IdGenerator(context).nextAlarmManagerId();
        alarmInfoDao.insertAlarmInfo(new AlarmInfo(str, nextAlarmManagerId));
        setExactAlarm(context, str, nextAlarmManagerId, j);
    }

    public static void cancelAlarm(Context context, WorkManagerImpl workManagerImpl, String str) {
        AlarmInfoDao alarmInfoDao = workManagerImpl.getWorkDatabase().alarmInfoDao();
        AlarmInfo alarmInfo = alarmInfoDao.getAlarmInfo(str);
        if (alarmInfo != null) {
            cancelExactAlarm(context, str, alarmInfo.alarmId);
            Log.d("Alarms", String.format("Removing AlarmInfo for workSpecId (%s)", new Object[]{str}));
            alarmInfoDao.removeAlarmInfo(str);
        }
    }

    private static void cancelExactAlarm(Context context, String str, int i) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent service = PendingIntent.getService(context, i, CommandHandler.createDelayMetIntent(context, str), 536870912);
        if (service != null && alarmManager != null) {
            Log.d("Alarms", String.format("Cancelling existing alarm with (workSpecId, alarmId) (%s, %s)", new Object[]{str, Integer.valueOf(i)}));
            alarmManager.cancel(service);
        }
    }

    private static void setExactAlarm(Context context, String str, int i, long j) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent service = PendingIntent.getService(context, i, CommandHandler.createDelayMetIntent(context, str), 1073741824);
        if (alarmManager == null) {
            return;
        }
        if (VERSION.SDK_INT >= 19) {
            alarmManager.setExact(0, j, service);
        } else {
            alarmManager.set(0, j, service);
        }
    }
}
