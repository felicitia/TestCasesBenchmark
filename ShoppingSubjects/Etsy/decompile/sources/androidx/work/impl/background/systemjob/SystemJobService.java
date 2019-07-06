package androidx.work.impl.background.systemjob;

import android.app.Application;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import androidx.work.e;
import androidx.work.impl.a;
import androidx.work.impl.f;
import java.util.HashMap;
import java.util.Map;

@RequiresApi(23)
@RestrictTo({Scope.LIBRARY_GROUP})
public class SystemJobService extends JobService implements a {
    private static final String TAG = "SystemJobService";
    private final Map<String, JobParameters> mJobParameters = new HashMap();
    private f mWorkManagerImpl;

    public void onCreate() {
        super.onCreate();
        this.mWorkManagerImpl = f.b();
        if (this.mWorkManagerImpl != null) {
            this.mWorkManagerImpl.g().a((a) this);
        } else if (!Application.class.equals(getApplication().getClass())) {
            throw new IllegalStateException("WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().");
        } else {
            e.d(TAG, "Could not find WorkManager instance; this may be because an auto-backup is in progress. Ignoring JobScheduler commands for now. Please make sure that you are initializing WorkManager if you have manually disabled WorkManagerInitializer.", new Throwable[0]);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mWorkManagerImpl != null) {
            this.mWorkManagerImpl.g().b((a) this);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0062, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0067, code lost:
        if (android.os.Build.VERSION.SDK_INT < 24) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0069, code lost:
        r2 = new androidx.work.impl.Extras.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0072, code lost:
        if (r8.getTriggeredContentUris() != null) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0078, code lost:
        if (r8.getTriggeredContentAuthorities() == null) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007a, code lost:
        r2.b = r8.getTriggeredContentUris();
        r2.a = r8.getTriggeredContentAuthorities();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008a, code lost:
        if (android.os.Build.VERSION.SDK_INT < 28) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008c, code lost:
        r2.c = r8.getNetwork();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0092, code lost:
        r7.mWorkManagerImpl.a(r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0097, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onStartJob(android.app.job.JobParameters r8) {
        /*
            r7 = this;
            androidx.work.impl.f r0 = r7.mWorkManagerImpl
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0013
            java.lang.String r0 = "SystemJobService"
            java.lang.String r3 = "WorkManager is not initialized; requesting retry."
            java.lang.Throwable[] r4 = new java.lang.Throwable[r2]
            androidx.work.e.b(r0, r3, r4)
            r7.jobFinished(r8, r1)
            return r2
        L_0x0013:
            android.os.PersistableBundle r0 = r8.getExtras()
            java.lang.String r3 = "EXTRA_WORK_SPEC_ID"
            java.lang.String r0 = r0.getString(r3)
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 == 0) goto L_0x002d
            java.lang.String r8 = "SystemJobService"
            java.lang.String r0 = "WorkSpec id not found!"
            java.lang.Throwable[] r1 = new java.lang.Throwable[r2]
            androidx.work.e.e(r8, r0, r1)
            return r2
        L_0x002d:
            java.util.Map<java.lang.String, android.app.job.JobParameters> r3 = r7.mJobParameters
            monitor-enter(r3)
            java.util.Map<java.lang.String, android.app.job.JobParameters> r4 = r7.mJobParameters     // Catch:{ all -> 0x0098 }
            boolean r4 = r4.containsKey(r0)     // Catch:{ all -> 0x0098 }
            if (r4 == 0) goto L_0x004b
            java.lang.String r8 = "SystemJobService"
            java.lang.String r4 = "Job is already being executed by SystemJobService: %s"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0098 }
            r1[r2] = r0     // Catch:{ all -> 0x0098 }
            java.lang.String r0 = java.lang.String.format(r4, r1)     // Catch:{ all -> 0x0098 }
            java.lang.Throwable[] r1 = new java.lang.Throwable[r2]     // Catch:{ all -> 0x0098 }
            androidx.work.e.b(r8, r0, r1)     // Catch:{ all -> 0x0098 }
            monitor-exit(r3)     // Catch:{ all -> 0x0098 }
            return r2
        L_0x004b:
            java.lang.String r4 = "SystemJobService"
            java.lang.String r5 = "onStartJob for %s"
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ all -> 0x0098 }
            r6[r2] = r0     // Catch:{ all -> 0x0098 }
            java.lang.String r5 = java.lang.String.format(r5, r6)     // Catch:{ all -> 0x0098 }
            java.lang.Throwable[] r2 = new java.lang.Throwable[r2]     // Catch:{ all -> 0x0098 }
            androidx.work.e.b(r4, r5, r2)     // Catch:{ all -> 0x0098 }
            java.util.Map<java.lang.String, android.app.job.JobParameters> r2 = r7.mJobParameters     // Catch:{ all -> 0x0098 }
            r2.put(r0, r8)     // Catch:{ all -> 0x0098 }
            monitor-exit(r3)     // Catch:{ all -> 0x0098 }
            r2 = 0
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 24
            if (r3 < r4) goto L_0x0092
            androidx.work.impl.Extras$a r2 = new androidx.work.impl.Extras$a
            r2.<init>()
            android.net.Uri[] r3 = r8.getTriggeredContentUris()
            if (r3 != 0) goto L_0x007a
            java.lang.String[] r3 = r8.getTriggeredContentAuthorities()
            if (r3 == 0) goto L_0x0086
        L_0x007a:
            android.net.Uri[] r3 = r8.getTriggeredContentUris()
            r2.b = r3
            java.lang.String[] r3 = r8.getTriggeredContentAuthorities()
            r2.a = r3
        L_0x0086:
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 28
            if (r3 < r4) goto L_0x0092
            android.net.Network r8 = r8.getNetwork()
            r2.c = r8
        L_0x0092:
            androidx.work.impl.f r8 = r7.mWorkManagerImpl
            r8.a(r0, r2)
            return r1
        L_0x0098:
            r8 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0098 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.background.systemjob.SystemJobService.onStartJob(android.app.job.JobParameters):boolean");
    }

    public boolean onStopJob(JobParameters jobParameters) {
        if (this.mWorkManagerImpl == null) {
            e.b(TAG, "WorkManager is not initialized; requesting retry.", new Throwable[0]);
            return true;
        }
        String string = jobParameters.getExtras().getString("EXTRA_WORK_SPEC_ID");
        if (TextUtils.isEmpty(string)) {
            e.e(TAG, "WorkSpec id not found!", new Throwable[0]);
            return false;
        }
        e.b(TAG, String.format("onStopJob for %s", new Object[]{string}), new Throwable[0]);
        synchronized (this.mJobParameters) {
            this.mJobParameters.remove(string);
        }
        this.mWorkManagerImpl.c(string);
        return !this.mWorkManagerImpl.g().d(string);
    }

    public void onExecuted(@NonNull String str, boolean z, boolean z2) {
        JobParameters jobParameters;
        e.b(TAG, String.format("%s executed on JobScheduler", new Object[]{str}), new Throwable[0]);
        synchronized (this.mJobParameters) {
            jobParameters = (JobParameters) this.mJobParameters.get(str);
        }
        if (jobParameters != null) {
            jobFinished(jobParameters, z2);
        }
    }
}
