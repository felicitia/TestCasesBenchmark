package com.firebase.jobdispatcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.firebase.jobdispatcher.IRemoteJobService.Stub;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

@VisibleForTesting
/* compiled from: JobServiceConnection */
class p implements ServiceConnection {
    private final Map<n, Boolean> a = new HashMap();
    private final IJobCallback b;
    private final Context c;
    private boolean d = false;
    private IRemoteJobService e;

    p(IJobCallback iJobCallback, Context context) {
        this.b = iJobCallback;
        this.c = context;
    }

    public synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (a()) {
            Log.w("FJD.ExternalReceiver", "Connection have been used already.");
            return;
        }
        this.e = Stub.asInterface(iBinder);
        HashSet<n> hashSet = new HashSet<>();
        for (Entry entry : this.a.entrySet()) {
            if (Boolean.FALSE.equals(entry.getValue())) {
                try {
                    this.e.start(a((o) entry.getKey()), this.b);
                    hashSet.add(entry.getKey());
                } catch (RemoteException e2) {
                    String str = "FJD.ExternalReceiver";
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to start job ");
                    sb.append(entry.getKey());
                    Log.e(str, sb.toString(), e2);
                    c();
                    return;
                }
            }
        }
        for (n put : hashSet) {
            this.a.put(put, Boolean.valueOf(true));
        }
    }

    public synchronized void onServiceDisconnected(ComponentName componentName) {
        c();
    }

    /* access modifiers changed from: 0000 */
    public synchronized boolean a() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public synchronized boolean b() {
        return this.e != null;
    }

    /* access modifiers changed from: 0000 */
    public synchronized void a(n nVar, boolean z) {
        if (!a()) {
            if (Boolean.TRUE.equals(this.a.remove(nVar)) && b()) {
                a(z, nVar);
            }
            if (!z && this.a.isEmpty()) {
                c();
            }
        } else {
            Log.w("FJD.ExternalReceiver", "Can't send stop request because service was unbound.");
        }
    }

    private synchronized void a(boolean z, n nVar) {
        try {
            this.e.stop(a((o) nVar), z);
        } catch (RemoteException e2) {
            Log.e("FJD.ExternalReceiver", "Failed to stop a job", e2);
            c();
        }
        return;
    }

    /* access modifiers changed from: 0000 */
    public synchronized void c() {
        if (!a()) {
            this.e = null;
            this.d = true;
            try {
                this.c.unbindService(this);
            } catch (IllegalArgumentException e2) {
                String str = "FJD.ExternalReceiver";
                StringBuilder sb = new StringBuilder();
                sb.append("Error unbinding service: ");
                sb.append(e2.getMessage());
                Log.w(str, sb.toString());
            }
        }
        return;
    }

    /* access modifiers changed from: 0000 */
    public synchronized void a(n nVar) {
        this.a.remove(nVar);
        if (this.a.isEmpty()) {
            c();
        }
    }

    /* access modifiers changed from: 0000 */
    public synchronized boolean b(n nVar) {
        boolean b2;
        b2 = b();
        if (b2) {
            if (Boolean.TRUE.equals((Boolean) this.a.get(nVar))) {
                StringBuilder sb = new StringBuilder();
                sb.append("Received an execution request for already running job ");
                sb.append(nVar);
                Log.w("FJD.ExternalReceiver", sb.toString());
                a(false, nVar);
            }
            try {
                this.e.start(a((o) nVar), this.b);
            } catch (RemoteException e2) {
                String str = "FJD.ExternalReceiver";
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Failed to start the job ");
                sb2.append(nVar);
                Log.e(str, sb2.toString(), e2);
                c();
                return false;
            }
        }
        this.a.put(nVar, Boolean.valueOf(b2));
        return b2;
    }

    private static Bundle a(o oVar) {
        return GooglePlayReceiver.getJobCoder().a(oVar, new Bundle());
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public synchronized boolean c(n nVar) {
        return this.a.containsKey(nVar);
    }
}
