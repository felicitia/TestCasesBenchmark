package androidx.work.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.a;
import androidx.work.e;
import androidx.work.impl.b.j;
import androidx.work.impl.b.k;
import androidx.work.impl.background.systemalarm.SystemAlarmService;
import androidx.work.impl.background.systemalarm.f;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.background.systemjob.b;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: Schedulers */
public class d {
    public static void a(@NonNull a aVar, @NonNull WorkDatabase workDatabase, List<c> list) {
        if (list != null && list.size() != 0) {
            k workSpecDao = workDatabase.workSpecDao();
            workDatabase.beginTransaction();
            try {
                List<j> a = workSpecDao.a(aVar.e());
                if (a != null && a.size() > 0) {
                    long currentTimeMillis = System.currentTimeMillis();
                    for (j jVar : a) {
                        workSpecDao.b(jVar.a, currentTimeMillis);
                    }
                }
                workDatabase.setTransactionSuccessful();
                if (a != null && a.size() > 0) {
                    j[] jVarArr = (j[]) a.toArray(new j[0]);
                    for (c a2 : list) {
                        a2.a(jVarArr);
                    }
                }
            } finally {
                workDatabase.endTransaction();
            }
        }
    }

    @SuppressLint({"NewApi"})
    @NonNull
    static c a(@NonNull Context context, @NonNull f fVar) {
        boolean z;
        c cVar;
        boolean z2 = false;
        if (VERSION.SDK_INT >= 23) {
            cVar = new b(context, fVar);
            androidx.work.impl.utils.d.a(context, SystemJobService.class, true);
            e.b("Schedulers", "Created SystemJobScheduler and enabled SystemJobService", new Throwable[0]);
            z = false;
        } else {
            try {
                cVar = a(context);
                try {
                    e.b("Schedulers", "Created FirebaseJobScheduler", new Throwable[0]);
                    z = true;
                } catch (Exception unused) {
                    z = true;
                    cVar = new f(context);
                    e.b("Schedulers", "Created SystemAlarmScheduler", new Throwable[0]);
                    z2 = true;
                    androidx.work.impl.utils.d.a(context, Class.forName("androidx.work.impl.background.firebase.FirebaseJobService"), z);
                    androidx.work.impl.utils.d.a(context, SystemAlarmService.class, z2);
                    return cVar;
                }
            } catch (Exception unused2) {
                z = false;
                cVar = new f(context);
                e.b("Schedulers", "Created SystemAlarmScheduler", new Throwable[0]);
                z2 = true;
                androidx.work.impl.utils.d.a(context, Class.forName("androidx.work.impl.background.firebase.FirebaseJobService"), z);
                androidx.work.impl.utils.d.a(context, SystemAlarmService.class, z2);
                return cVar;
            }
        }
        try {
            androidx.work.impl.utils.d.a(context, Class.forName("androidx.work.impl.background.firebase.FirebaseJobService"), z);
        } catch (ClassNotFoundException unused3) {
        }
        androidx.work.impl.utils.d.a(context, SystemAlarmService.class, z2);
        return cVar;
    }

    @NonNull
    private static c a(@NonNull Context context) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        return (c) Class.forName("androidx.work.impl.background.firebase.FirebaseJobScheduler").getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
    }
}
