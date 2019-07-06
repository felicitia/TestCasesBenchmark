package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobInfo.TriggerContentUri;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import androidx.work.BackoffPolicy;
import androidx.work.NetworkType;
import androidx.work.b;
import androidx.work.e;
import androidx.work.impl.b.j;
import java.util.Iterator;

@RequiresApi(api = 23)
@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: SystemJobInfoConverter */
class a {
    private final ComponentName a;

    @VisibleForTesting(otherwise = 3)
    a(@NonNull Context context) {
        this.a = new ComponentName(context.getApplicationContext(), SystemJobService.class);
    }

    /* access modifiers changed from: 0000 */
    public JobInfo a(j jVar, int i) {
        b bVar = jVar.j;
        int a2 = a(bVar.a());
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString("EXTRA_WORK_SPEC_ID", jVar.a);
        persistableBundle.putBoolean("EXTRA_IS_PERIODIC", jVar.a());
        Builder extras = new Builder(i, this.a).setRequiredNetworkType(a2).setRequiresCharging(bVar.b()).setRequiresDeviceIdle(bVar.c()).setExtras(persistableBundle);
        if (!bVar.c()) {
            extras.setBackoffCriteria(jVar.m, jVar.l == BackoffPolicy.LINEAR ? 0 : 1);
        }
        if (!jVar.a()) {
            extras.setMinimumLatency(jVar.g);
        } else if (VERSION.SDK_INT >= 24) {
            extras.setPeriodic(jVar.h, jVar.i);
        } else {
            e.b("SystemJobInfoConverter", "Flex duration is currently not supported before API 24. Ignoring.", new Throwable[0]);
            extras.setPeriodic(jVar.h);
        }
        if (VERSION.SDK_INT >= 24 && bVar.g()) {
            Iterator it = bVar.f().iterator();
            while (it.hasNext()) {
                extras.addTriggerContentUri(a((androidx.work.c.a) it.next()));
            }
        }
        extras.setPersisted(false);
        if (VERSION.SDK_INT >= 26) {
            extras.setRequiresBatteryNotLow(bVar.d());
            extras.setRequiresStorageNotLow(bVar.e());
        }
        return extras.build();
    }

    @RequiresApi(24)
    private static TriggerContentUri a(androidx.work.c.a aVar) {
        return new TriggerContentUri(aVar.a(), aVar.b() ? 1 : 0);
    }

    static int a(NetworkType networkType) {
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
        e.b("SystemJobInfoConverter", String.format("API version too low. Cannot convert network type value %s", new Object[]{networkType}), new Throwable[0]);
        return 1;
    }
}
