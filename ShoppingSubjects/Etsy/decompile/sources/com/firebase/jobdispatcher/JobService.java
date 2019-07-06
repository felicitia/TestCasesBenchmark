package com.firebase.jobdispatcher;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import com.firebase.jobdispatcher.IRemoteJobService.Stub;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Locale;

public abstract class JobService extends Service {
    @VisibleForTesting
    static final String ACTION_EXECUTE = "com.firebase.jobdispatcher.ACTION_EXECUTE";
    public static final int RESULT_FAIL_NORETRY = 2;
    public static final int RESULT_FAIL_RETRY = 1;
    public static final int RESULT_SUCCESS = 0;
    static final String TAG = "FJD.JobService";
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Stub binder = new Stub() {
        public void start(Bundle bundle, IJobCallback iJobCallback) {
            a b = GooglePlayReceiver.getJobCoder().b(bundle);
            if (b == null) {
                Log.wtf(JobService.TAG, "start: unknown invocation provided");
            } else {
                JobService.this.start(b.a(), iJobCallback);
            }
        }

        public void stop(Bundle bundle, boolean z) {
            a b = GooglePlayReceiver.getJobCoder().b(bundle);
            if (b == null) {
                Log.wtf(JobService.TAG, "stop: unknown invocation provided");
            } else {
                JobService.this.stop(b.a(), z);
            }
        }
    };
    /* access modifiers changed from: private */
    public final SimpleArrayMap<String, a> runningJobs = new SimpleArrayMap<>(1);

    private static final class a {
        final o a;
        final IJobCallback b;

        private a(o oVar, IJobCallback iJobCallback) {
            this.a = oVar;
            this.b = iJobCallback;
        }

        /* access modifiers changed from: 0000 */
        public void a(int i) {
            try {
                this.b.jobFinished(GooglePlayReceiver.getJobCoder().a(this.a, new Bundle()), i);
            } catch (RemoteException e) {
                Log.e(JobService.TAG, "Failed to send result to driver", e);
            }
        }
    }

    public final void onStart(Intent intent, int i) {
    }

    @MainThread
    public abstract boolean onStartJob(o oVar);

    @MainThread
    public abstract boolean onStopJob(o oVar);

    /* access modifiers changed from: 0000 */
    public void start(final o oVar, IJobCallback iJobCallback) {
        synchronized (this.runningJobs) {
            if (this.runningJobs.containsKey(oVar.e())) {
                Log.w(TAG, String.format(Locale.US, "Job with tag = %s was already running.", new Object[]{oVar.e()}));
                return;
            }
            this.runningJobs.put(oVar.e(), new a(oVar, iJobCallback));
            mainHandler.post(new Runnable() {
                public void run() {
                    synchronized (JobService.this.runningJobs) {
                        if (!JobService.this.onStartJob(oVar)) {
                            a aVar = (a) JobService.this.runningJobs.remove(oVar.e());
                            if (aVar != null) {
                                aVar.a(0);
                            }
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void stop(final com.firebase.jobdispatcher.o r5, final boolean r6) {
        /*
            r4 = this;
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.JobService$a> r0 = r4.runningJobs
            monitor-enter(r0)
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.JobService$a> r1 = r4.runningJobs     // Catch:{ all -> 0x002f }
            java.lang.String r2 = r5.e()     // Catch:{ all -> 0x002f }
            java.lang.Object r1 = r1.remove(r2)     // Catch:{ all -> 0x002f }
            com.firebase.jobdispatcher.JobService$a r1 = (com.firebase.jobdispatcher.JobService.a) r1     // Catch:{ all -> 0x002f }
            if (r1 != 0) goto L_0x0023
            java.lang.String r5 = "FJD.JobService"
            r6 = 3
            boolean r5 = android.util.Log.isLoggable(r5, r6)     // Catch:{ all -> 0x002f }
            if (r5 == 0) goto L_0x0021
            java.lang.String r5 = "FJD.JobService"
            java.lang.String r6 = "Provided job has already been executed."
            android.util.Log.d(r5, r6)     // Catch:{ all -> 0x002f }
        L_0x0021:
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return
        L_0x0023:
            android.os.Handler r2 = mainHandler     // Catch:{ all -> 0x002f }
            com.firebase.jobdispatcher.JobService$3 r3 = new com.firebase.jobdispatcher.JobService$3     // Catch:{ all -> 0x002f }
            r3.<init>(r5, r6, r1)     // Catch:{ all -> 0x002f }
            r2.post(r3)     // Catch:{ all -> 0x002f }
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return
        L_0x002f:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.JobService.stop(com.firebase.jobdispatcher.o, boolean):void");
    }

    public final void jobFinished(@NonNull o oVar, boolean z) {
        if (oVar == null) {
            Log.e(TAG, "jobFinished called with a null JobParameters");
            return;
        }
        synchronized (this.runningJobs) {
            a aVar = (a) this.runningJobs.remove(oVar.e());
            if (aVar != null) {
                aVar.a(z ? 1 : 0);
            }
        }
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        stopSelf(i2);
        return 2;
    }

    @Nullable
    public final IBinder onBind(Intent intent) {
        return this.binder;
    }

    @MainThread
    public final boolean onUnbind(Intent intent) {
        synchronized (this.runningJobs) {
            for (int size = this.runningJobs.size() - 1; size >= 0; size--) {
                a aVar = (a) this.runningJobs.remove(this.runningJobs.keyAt(size));
                if (aVar != null) {
                    aVar.a(onStopJob(aVar.a) ? 1 : 2);
                }
            }
        }
        return super.onUnbind(intent);
    }

    public final void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    /* access modifiers changed from: protected */
    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(fileDescriptor, printWriter, strArr);
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public final void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
    }
}
