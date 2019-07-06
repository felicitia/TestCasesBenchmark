package com.firebase.jobdispatcher;

import android.os.AsyncTask;
import android.support.annotation.CallSuper;
import android.support.v4.util.SimpleArrayMap;

public abstract class SimpleJobService extends JobService {
    private final SimpleArrayMap<o, a> runningJobs = new SimpleArrayMap<>();

    private static class a extends AsyncTask<Void, Void, Integer> {
        private final SimpleJobService a;
        private final o b;

        private a(SimpleJobService simpleJobService, o oVar) {
            this.a = simpleJobService;
            this.b = oVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Integer doInBackground(Void... voidArr) {
            return Integer.valueOf(this.a.onRunJob(this.b));
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Integer num) {
            SimpleJobService simpleJobService = this.a;
            o oVar = this.b;
            boolean z = true;
            if (num.intValue() != 1) {
                z = false;
            }
            simpleJobService.onJobFinished(oVar, z);
        }
    }

    public abstract int onRunJob(o oVar);

    @CallSuper
    public boolean onStartJob(o oVar) {
        a aVar = new a(oVar);
        synchronized (this.runningJobs) {
            this.runningJobs.put(oVar, aVar);
        }
        aVar.execute(new Void[0]);
        return true;
    }

    @CallSuper
    public boolean onStopJob(o oVar) {
        synchronized (this.runningJobs) {
            a aVar = (a) this.runningJobs.remove(oVar);
            if (aVar == null) {
                return false;
            }
            aVar.cancel(true);
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void onJobFinished(o oVar, boolean z) {
        synchronized (this.runningJobs) {
            this.runningJobs.remove(oVar);
        }
        jobFinished(oVar, z);
    }
}
