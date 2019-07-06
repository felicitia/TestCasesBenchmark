package androidx.work.impl.background.systemjob;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.text.TextUtils;
import android.util.Log;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.WorkManagerImpl;
import java.util.HashMap;
import java.util.Map;

@TargetApi(23)
public class SystemJobService extends JobService implements ExecutionListener {
    private final Map<String, JobParameters> mJobParameters = new HashMap();
    private WorkManagerImpl mWorkManagerImpl;

    public void onCreate() {
        super.onCreate();
        this.mWorkManagerImpl = WorkManagerImpl.getInstance();
        this.mWorkManagerImpl.getProcessor().addExecutionListener(this);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mWorkManagerImpl.getProcessor().removeExecutionListener(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006d, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0072, code lost:
        if (android.os.Build.VERSION.SDK_INT < 24) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0078, code lost:
        if (r8.getTriggeredContentUris() != null) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007e, code lost:
        if (r8.getTriggeredContentAuthorities() == null) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0080, code lost:
        r0 = new androidx.work.impl.Extras.RuntimeExtras();
        r0.triggeredContentUris = r8.getTriggeredContentUris();
        r0.triggeredContentAuthorities = r8.getTriggeredContentAuthorities();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0091, code lost:
        r7.mWorkManagerImpl.startWork(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0096, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onStartJob(android.app.job.JobParameters r8) {
        /*
            r7 = this;
            android.os.PersistableBundle r0 = r8.getExtras()
            java.lang.String r1 = "EXTRA_WORK_SPEC_ID"
            java.lang.String r1 = r0.getString(r1)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            r3 = 0
            if (r2 == 0) goto L_0x0019
            java.lang.String r8 = "SystemJobService"
            java.lang.String r0 = "WorkSpec id not found!"
            android.util.Log.e(r8, r0)
            return r3
        L_0x0019:
            java.util.Map<java.lang.String, android.app.job.JobParameters> r2 = r7.mJobParameters
            monitor-enter(r2)
            java.util.Map<java.lang.String, android.app.job.JobParameters> r4 = r7.mJobParameters     // Catch:{ all -> 0x0097 }
            boolean r4 = r4.containsKey(r1)     // Catch:{ all -> 0x0097 }
            r5 = 1
            if (r4 == 0) goto L_0x0036
            java.lang.String r8 = "SystemJobService"
            java.lang.String r0 = "Job is already being executed by SystemJobService: %s"
            java.lang.Object[] r4 = new java.lang.Object[r5]     // Catch:{ all -> 0x0097 }
            r4[r3] = r1     // Catch:{ all -> 0x0097 }
            java.lang.String r0 = java.lang.String.format(r0, r4)     // Catch:{ all -> 0x0097 }
            android.util.Log.d(r8, r0)     // Catch:{ all -> 0x0097 }
            monitor-exit(r2)     // Catch:{ all -> 0x0097 }
            return r3
        L_0x0036:
            java.lang.String r4 = "EXTRA_IS_PERIODIC"
            boolean r0 = r0.getBoolean(r4, r3)     // Catch:{ all -> 0x0097 }
            if (r0 == 0) goto L_0x0058
            boolean r0 = r8.isOverrideDeadlineExpired()     // Catch:{ all -> 0x0097 }
            if (r0 == 0) goto L_0x0058
            java.lang.String r0 = "SystemJobService"
            java.lang.String r4 = "Override deadline expired for id %s. Retry requested"
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ all -> 0x0097 }
            r6[r3] = r1     // Catch:{ all -> 0x0097 }
            java.lang.String r1 = java.lang.String.format(r4, r6)     // Catch:{ all -> 0x0097 }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x0097 }
            r7.jobFinished(r8, r5)     // Catch:{ all -> 0x0097 }
            monitor-exit(r2)     // Catch:{ all -> 0x0097 }
            return r3
        L_0x0058:
            java.lang.String r0 = "SystemJobService"
            java.lang.String r4 = "onStartJob for %s"
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ all -> 0x0097 }
            r6[r3] = r1     // Catch:{ all -> 0x0097 }
            java.lang.String r3 = java.lang.String.format(r4, r6)     // Catch:{ all -> 0x0097 }
            android.util.Log.d(r0, r3)     // Catch:{ all -> 0x0097 }
            java.util.Map<java.lang.String, android.app.job.JobParameters> r0 = r7.mJobParameters     // Catch:{ all -> 0x0097 }
            r0.put(r1, r8)     // Catch:{ all -> 0x0097 }
            monitor-exit(r2)     // Catch:{ all -> 0x0097 }
            r0 = 0
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 24
            if (r2 < r3) goto L_0x0091
            android.net.Uri[] r2 = r8.getTriggeredContentUris()
            if (r2 != 0) goto L_0x0080
            java.lang.String[] r2 = r8.getTriggeredContentAuthorities()
            if (r2 == 0) goto L_0x0091
        L_0x0080:
            androidx.work.impl.Extras$RuntimeExtras r0 = new androidx.work.impl.Extras$RuntimeExtras
            r0.<init>()
            android.net.Uri[] r2 = r8.getTriggeredContentUris()
            r0.triggeredContentUris = r2
            java.lang.String[] r8 = r8.getTriggeredContentAuthorities()
            r0.triggeredContentAuthorities = r8
        L_0x0091:
            androidx.work.impl.WorkManagerImpl r8 = r7.mWorkManagerImpl
            r8.startWork(r1, r0)
            return r5
        L_0x0097:
            r8 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0097 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.background.systemjob.SystemJobService.onStartJob(android.app.job.JobParameters):boolean");
    }

    public boolean onStopJob(JobParameters jobParameters) {
        String string = jobParameters.getExtras().getString("EXTRA_WORK_SPEC_ID");
        if (TextUtils.isEmpty(string)) {
            Log.e("SystemJobService", "WorkSpec id not found!");
            return false;
        }
        Log.d("SystemJobService", String.format("onStopJob for %s", new Object[]{string}));
        synchronized (this.mJobParameters) {
            this.mJobParameters.remove(string);
        }
        this.mWorkManagerImpl.stopWork(string);
        return !this.mWorkManagerImpl.getProcessor().isCancelled(string);
    }

    public void onExecuted(String str, boolean z, boolean z2) {
        JobParameters jobParameters;
        Log.d("SystemJobService", String.format("%s executed on JobScheduler", new Object[]{str}));
        synchronized (this.mJobParameters) {
            jobParameters = (JobParameters) this.mJobParameters.get(str);
        }
        if (jobParameters != null) {
            jobFinished(jobParameters, z2);
        }
    }
}
