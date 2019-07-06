package com.salesforce.marketingcloud;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.app.job.JobWorkItem;
import android.content.Context;
import android.os.AsyncTask;

@TargetApi(26)
public class MCJobService extends JobService {
    /* access modifiers changed from: private */
    public static final String b = j.a(MCJobService.class);
    a a;

    static class a extends AsyncTask<Void, Void, Void> {
        private final Context a;
        private final JobParameters b;

        a(Context context, JobParameters jobParameters) {
            this.a = context;
            this.b = jobParameters;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public final Void doInBackground(Void... voidArr) {
            while (!isCancelled()) {
                try {
                    JobWorkItem dequeueWork = this.b.dequeueWork();
                    if (dequeueWork == null) {
                        break;
                    }
                    k.a(this.a.getApplicationContext(), dequeueWork.getIntent());
                    this.b.completeWork(dequeueWork);
                } catch (Exception e) {
                    j.c(MCJobService.b, e, "doInBackground threw exception", new Object[0]);
                }
            }
            return null;
        }
    }

    public boolean onStartJob(JobParameters jobParameters) {
        j.b(b, "onStartJob %d", Integer.valueOf(jobParameters.getJobId()));
        this.a = new a(this, jobParameters);
        this.a.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        return true;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        j.b(b, "onStopJob %d", Integer.valueOf(jobParameters.getJobId()));
        if (this.a != null) {
            this.a.cancel(true);
            this.a = null;
        }
        return false;
    }
}
