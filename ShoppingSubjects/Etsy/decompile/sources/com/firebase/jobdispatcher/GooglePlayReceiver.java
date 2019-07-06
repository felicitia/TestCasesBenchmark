package com.firebase.jobdispatcher;

import android.app.Service;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.util.Pair;
import com.firebase.jobdispatcher.k.a;

public class GooglePlayReceiver extends Service implements a {
    @VisibleForTesting
    static final String ACTION_EXECUTE = "com.google.android.gms.gcm.ACTION_TASK_READY";
    @VisibleForTesting
    static final String ACTION_INITIALIZE = "com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE";
    private static final String ERROR_NO_DATA = "No data provided, terminating";
    private static final String ERROR_NULL_INTENT = "Null Intent passed, terminating";
    private static final String ERROR_UNKNOWN_ACTION = "Unknown action received, terminating";
    static final String TAG = "FJD.GooglePlayReceiver";
    private static final SimpleArrayMap<String, SimpleArrayMap<String, l>> callbacks = new SimpleArrayMap<>(1);
    private static final m prefixedCoder = new m("com.firebase.jobdispatcher.");
    private final e callbackExtractor = new e();
    @VisibleForTesting
    c driver;
    private d executionDelegator;
    private int latestStartId;
    @VisibleForTesting
    Messenger serviceMessenger;
    @VisibleForTesting
    ValidationEnforcer validationEnforcer;

    @VisibleForTesting
    static void clearCallbacks() {
        synchronized (callbacks) {
            callbacks.clear();
        }
    }

    private static void sendResultSafely(l lVar, int i) {
        try {
            lVar.a(i);
        } catch (Throwable th) {
            Log.e(TAG, "Encountered error running callback", th.getCause());
        }
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        try {
            super.onStartCommand(intent, i, i2);
            if (intent == null) {
                Log.w(TAG, ERROR_NULL_INTENT);
                synchronized (callbacks) {
                    this.latestStartId = i2;
                    if (callbacks.isEmpty()) {
                        stopSelf(this.latestStartId);
                    }
                }
                return 2;
            }
            String action = intent.getAction();
            if ("com.google.android.gms.gcm.ACTION_TASK_READY".equals(action)) {
                getExecutionDelegator().a(prepareJob(intent));
                synchronized (callbacks) {
                    this.latestStartId = i2;
                    if (callbacks.isEmpty()) {
                        stopSelf(this.latestStartId);
                    }
                }
                return 2;
            } else if ("com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE".equals(action)) {
                synchronized (callbacks) {
                    this.latestStartId = i2;
                    if (callbacks.isEmpty()) {
                        stopSelf(this.latestStartId);
                    }
                }
                return 2;
            } else {
                Log.e(TAG, ERROR_UNKNOWN_ACTION);
                synchronized (callbacks) {
                    this.latestStartId = i2;
                    if (callbacks.isEmpty()) {
                        stopSelf(this.latestStartId);
                    }
                }
                return 2;
            }
        } catch (Throwable th) {
            synchronized (callbacks) {
                this.latestStartId = i2;
                if (callbacks.isEmpty()) {
                    stopSelf(this.latestStartId);
                }
                throw th;
            }
        }
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        if (intent == null || VERSION.SDK_INT < 21 || !"com.google.android.gms.gcm.ACTION_TASK_READY".equals(intent.getAction())) {
            return null;
        }
        return getServiceMessenger().getBinder();
    }

    private synchronized Messenger getServiceMessenger() {
        if (this.serviceMessenger == null) {
            this.serviceMessenger = new Messenger(new i(Looper.getMainLooper(), this));
        }
        return this.serviceMessenger;
    }

    /* access modifiers changed from: 0000 */
    public synchronized d getExecutionDelegator() {
        if (this.executionDelegator == null) {
            this.executionDelegator = new d(this, this);
        }
        return this.executionDelegator;
    }

    @NonNull
    private synchronized c getGooglePlayDriver() {
        if (this.driver == null) {
            this.driver = new f(getApplicationContext());
        }
        return this.driver;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public synchronized void setGooglePlayDriver(c cVar) {
        this.driver = cVar;
    }

    @NonNull
    private synchronized ValidationEnforcer getValidationEnforcer() {
        if (this.validationEnforcer == null) {
            this.validationEnforcer = new ValidationEnforcer(getGooglePlayDriver().a());
        }
        return this.validationEnforcer;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public synchronized void setValidationEnforcer(ValidationEnforcer validationEnforcer2) {
        this.validationEnforcer = validationEnforcer2;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    @VisibleForTesting
    public n prepareJob(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            Log.e(TAG, ERROR_NO_DATA);
            return null;
        }
        Pair a = this.callbackExtractor.a(extras);
        if (a != null) {
            return prepareJob((l) a.first, (Bundle) a.second);
        }
        Log.i(TAG, "no callback found");
        return null;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public n prepareJob(l lVar, Bundle bundle) {
        n a = prefixedCoder.a(bundle);
        if (a == null) {
            Log.e(TAG, "unable to decode job");
            sendResultSafely(lVar, 2);
            return null;
        }
        synchronized (callbacks) {
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) callbacks.get(a.i());
            if (simpleArrayMap == null) {
                simpleArrayMap = new SimpleArrayMap(1);
                callbacks.put(a.i(), simpleArrayMap);
            }
            simpleArrayMap.put(a.e(), lVar);
        }
        return a;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003a, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0090, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onJobFinished(@android.support.annotation.NonNull com.firebase.jobdispatcher.n r6, int r7) {
        /*
            r5 = this;
            android.support.v4.util.SimpleArrayMap<java.lang.String, android.support.v4.util.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.l>> r0 = callbacks
            monitor-enter(r0)
            android.support.v4.util.SimpleArrayMap<java.lang.String, android.support.v4.util.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.l>> r1 = callbacks     // Catch:{ all -> 0x0091 }
            java.lang.String r2 = r6.i()     // Catch:{ all -> 0x0091 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0091 }
            android.support.v4.util.SimpleArrayMap r1 = (android.support.v4.util.SimpleArrayMap) r1     // Catch:{ all -> 0x0091 }
            if (r1 != 0) goto L_0x0020
            android.support.v4.util.SimpleArrayMap<java.lang.String, android.support.v4.util.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.l>> r6 = callbacks     // Catch:{ all -> 0x00a0 }
            boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x00a0 }
            if (r6 == 0) goto L_0x001e
            int r6 = r5.latestStartId     // Catch:{ all -> 0x00a0 }
            r5.stopSelf(r6)     // Catch:{ all -> 0x00a0 }
        L_0x001e:
            monitor-exit(r0)     // Catch:{ all -> 0x00a0 }
            return
        L_0x0020:
            java.lang.String r2 = r6.e()     // Catch:{ all -> 0x0091 }
            java.lang.Object r2 = r1.remove(r2)     // Catch:{ all -> 0x0091 }
            com.firebase.jobdispatcher.l r2 = (com.firebase.jobdispatcher.l) r2     // Catch:{ all -> 0x0091 }
            if (r2 != 0) goto L_0x003b
            android.support.v4.util.SimpleArrayMap<java.lang.String, android.support.v4.util.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.l>> r6 = callbacks     // Catch:{ all -> 0x00a0 }
            boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x00a0 }
            if (r6 == 0) goto L_0x0039
            int r6 = r5.latestStartId     // Catch:{ all -> 0x00a0 }
            r5.stopSelf(r6)     // Catch:{ all -> 0x00a0 }
        L_0x0039:
            monitor-exit(r0)     // Catch:{ all -> 0x00a0 }
            return
        L_0x003b:
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0091 }
            if (r1 == 0) goto L_0x004a
            android.support.v4.util.SimpleArrayMap<java.lang.String, android.support.v4.util.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.l>> r1 = callbacks     // Catch:{ all -> 0x0091 }
            java.lang.String r3 = r6.i()     // Catch:{ all -> 0x0091 }
            r1.remove(r3)     // Catch:{ all -> 0x0091 }
        L_0x004a:
            boolean r1 = needsToBeRescheduled(r6, r7)     // Catch:{ all -> 0x0091 }
            if (r1 == 0) goto L_0x0054
            r5.reschedule(r6)     // Catch:{ all -> 0x0091 }
            goto L_0x0082
        L_0x0054:
            java.lang.String r1 = "FJD.GooglePlayReceiver"
            r3 = 2
            boolean r1 = android.util.Log.isLoggable(r1, r3)     // Catch:{ all -> 0x0091 }
            if (r1 == 0) goto L_0x007f
            java.lang.String r1 = "FJD.GooglePlayReceiver"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0091 }
            r3.<init>()     // Catch:{ all -> 0x0091 }
            java.lang.String r4 = "sending jobFinished for "
            r3.append(r4)     // Catch:{ all -> 0x0091 }
            java.lang.String r6 = r6.e()     // Catch:{ all -> 0x0091 }
            r3.append(r6)     // Catch:{ all -> 0x0091 }
            java.lang.String r6 = " = "
            r3.append(r6)     // Catch:{ all -> 0x0091 }
            r3.append(r7)     // Catch:{ all -> 0x0091 }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x0091 }
            android.util.Log.v(r1, r6)     // Catch:{ all -> 0x0091 }
        L_0x007f:
            sendResultSafely(r2, r7)     // Catch:{ all -> 0x0091 }
        L_0x0082:
            android.support.v4.util.SimpleArrayMap<java.lang.String, android.support.v4.util.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.l>> r6 = callbacks     // Catch:{ all -> 0x00a0 }
            boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x00a0 }
            if (r6 == 0) goto L_0x008f
            int r6 = r5.latestStartId     // Catch:{ all -> 0x00a0 }
            r5.stopSelf(r6)     // Catch:{ all -> 0x00a0 }
        L_0x008f:
            monitor-exit(r0)     // Catch:{ all -> 0x00a0 }
            return
        L_0x0091:
            r6 = move-exception
            android.support.v4.util.SimpleArrayMap<java.lang.String, android.support.v4.util.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.l>> r7 = callbacks     // Catch:{ all -> 0x00a0 }
            boolean r7 = r7.isEmpty()     // Catch:{ all -> 0x00a0 }
            if (r7 == 0) goto L_0x009f
            int r7 = r5.latestStartId     // Catch:{ all -> 0x00a0 }
            r5.stopSelf(r7)     // Catch:{ all -> 0x00a0 }
        L_0x009f:
            throw r6     // Catch:{ all -> 0x00a0 }
        L_0x00a0:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00a0 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.GooglePlayReceiver.onJobFinished(com.firebase.jobdispatcher.n, int):void");
    }

    private void reschedule(n nVar) {
        getGooglePlayDriver().a(new a(getValidationEnforcer(), nVar).a(true).j());
    }

    private static boolean needsToBeRescheduled(o oVar, int i) {
        if (!oVar.h() || !(oVar.f() instanceof q.a) || i == 1) {
            return false;
        }
        return true;
    }

    static m getJobCoder() {
        return prefixedCoder;
    }

    static void onSchedule(k kVar) {
        synchronized (callbacks) {
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) callbacks.get(kVar.i());
            if (simpleArrayMap != null) {
                if (((l) simpleArrayMap.get(kVar.e())) != null) {
                    d.a(new a().a(kVar.e()).b(kVar.i()).a(kVar.f()).a(), false);
                }
            }
        }
    }
}
