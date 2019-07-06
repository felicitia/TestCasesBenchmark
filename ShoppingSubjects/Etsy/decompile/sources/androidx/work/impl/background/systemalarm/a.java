package androidx.work.impl.background.systemalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.NotificationCompat;
import androidx.work.impl.b.d;
import androidx.work.impl.b.e;
import androidx.work.impl.f;
import androidx.work.impl.utils.c;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: Alarms */
class a {
    public static void a(@NonNull Context context, @NonNull f fVar, @NonNull String str, long j) {
        e systemIdInfoDao = fVar.d().systemIdInfoDao();
        d a = systemIdInfoDao.a(str);
        if (a != null) {
            a(context, str, a.b);
            a(context, str, a.b, j);
            return;
        }
        int a2 = new c(context).a();
        systemIdInfoDao.a(new d(str, a2));
        a(context, str, a2, j);
    }

    public static void a(@NonNull Context context, @NonNull f fVar, @NonNull String str) {
        e systemIdInfoDao = fVar.d().systemIdInfoDao();
        d a = systemIdInfoDao.a(str);
        if (a != null) {
            a(context, str, a.b);
            androidx.work.e.b("Alarms", String.format("Removing SystemIdInfo for workSpecId (%s)", new Object[]{str}), new Throwable[0]);
            systemIdInfoDao.b(str);
        }
    }

    private static void a(@NonNull Context context, @NonNull String str, int i) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent service = PendingIntent.getService(context, i, b.b(context, str), ErrorDialogData.DYNAMITE_CRASH);
        if (service != null && alarmManager != null) {
            androidx.work.e.b("Alarms", String.format("Cancelling existing alarm with (workSpecId, systemId) (%s, %s)", new Object[]{str, Integer.valueOf(i)}), new Throwable[0]);
            alarmManager.cancel(service);
        }
    }

    private static void a(@NonNull Context context, @NonNull String str, int i, long j) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent service = PendingIntent.getService(context, i, b.b(context, str), ErrorDialogData.SUPPRESSED);
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
