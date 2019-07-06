package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import androidx.work.e;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.b.d;
import androidx.work.impl.b.j;
import androidx.work.impl.c;
import androidx.work.impl.f;
import java.util.List;

@RequiresApi(23)
@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: SystemJobScheduler */
public class b implements c {
    private final JobScheduler a;
    private final f b;
    private final androidx.work.impl.utils.c c;
    private final a d;

    public b(@NonNull Context context, @NonNull f fVar) {
        this(context, fVar, (JobScheduler) context.getSystemService("jobscheduler"), new a(context));
    }

    @VisibleForTesting
    public b(Context context, f fVar, JobScheduler jobScheduler, a aVar) {
        this.b = fVar;
        this.a = jobScheduler;
        this.c = new androidx.work.impl.utils.c(context);
        this.d = aVar;
    }

    /* JADX INFO: finally extract failed */
    public void a(j... jVarArr) {
        WorkDatabase d2 = this.b.d();
        int i = 0;
        int length = jVarArr.length;
        while (i < length) {
            j jVar = jVarArr[i];
            try {
                d2.beginTransaction();
                d a2 = d2.systemIdInfoDao().a(jVar.a);
                int a3 = a2 != null ? a2.b : this.c.a(this.b.e().c(), this.b.e().d());
                if (a2 == null) {
                    this.b.d().systemIdInfoDao().a(new d(jVar.a, a3));
                }
                a(jVar, a3);
                if (VERSION.SDK_INT == 23) {
                    a(jVar, this.c.a(this.b.e().c(), this.b.e().d()));
                }
                d2.setTransactionSuccessful();
                d2.endTransaction();
                i++;
            } catch (Throwable th) {
                d2.endTransaction();
                throw th;
            }
        }
    }

    @VisibleForTesting
    public void a(j jVar, int i) {
        JobInfo a2 = this.d.a(jVar, i);
        e.b("SystemJobScheduler", String.format("Scheduling work ID %s Job ID %s", new Object[]{jVar.a, Integer.valueOf(i)}), new Throwable[0]);
        this.a.schedule(a2);
    }

    public void a(@NonNull String str) {
        List<JobInfo> allPendingJobs = this.a.getAllPendingJobs();
        if (allPendingJobs != null) {
            for (JobInfo jobInfo : allPendingJobs) {
                if (str.equals(jobInfo.getExtras().getString("EXTRA_WORK_SPEC_ID"))) {
                    this.b.d().systemIdInfoDao().b(str);
                    this.a.cancel(jobInfo.getId());
                    if (VERSION.SDK_INT != 23) {
                        return;
                    }
                }
            }
        }
    }

    public static void a(@NonNull Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler != null) {
            List<JobInfo> allPendingJobs = jobScheduler.getAllPendingJobs();
            if (allPendingJobs != null) {
                for (JobInfo jobInfo : allPendingJobs) {
                    if (jobInfo.getExtras().containsKey("EXTRA_WORK_SPEC_ID")) {
                        jobScheduler.cancel(jobInfo.getId());
                    }
                }
            }
        }
    }
}
