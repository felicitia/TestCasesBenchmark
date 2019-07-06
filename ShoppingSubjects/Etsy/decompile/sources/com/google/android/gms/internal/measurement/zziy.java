package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zziy implements ServiceConnection, BaseConnectionCallbacks, BaseOnConnectionFailedListener {
    final /* synthetic */ dq zzaqv;
    /* access modifiers changed from: private */
    public volatile boolean zzarb;
    private volatile ap zzarc;

    protected zziy(dq dqVar) {
        this.zzaqv = dqVar;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0022 */
    @android.support.annotation.MainThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onConnected(@android.support.annotation.Nullable android.os.Bundle r4) {
        /*
            r3 = this;
            java.lang.String r4 = "MeasurementServiceConnection.onConnected"
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r4)
            monitor-enter(r3)
            r4 = 0
            com.google.android.gms.internal.measurement.ap r0 = r3.zzarc     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            android.os.IInterface r0 = r0.getService()     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.measurement.zzfa r0 = (com.google.android.gms.internal.measurement.zzfa) r0     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            r3.zzarc = r4     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.measurement.dq r1 = r3.zzaqv     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.measurement.bq r1 = r1.q()     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.measurement.eg r2 = new com.google.android.gms.internal.measurement.eg     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            r2.<init>(r3, r0)     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            r1.a(r2)     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            goto L_0x0027
        L_0x0020:
            r4 = move-exception
            goto L_0x0029
        L_0x0022:
            r3.zzarc = r4     // Catch:{ all -> 0x0020 }
            r4 = 0
            r3.zzarb = r4     // Catch:{ all -> 0x0020 }
        L_0x0027:
            monitor-exit(r3)     // Catch:{ all -> 0x0020 }
            return
        L_0x0029:
            monitor-exit(r3)     // Catch:{ all -> 0x0020 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zziy.onConnected(android.os.Bundle):void");
    }

    @MainThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionFailed");
        aq d = this.zzaqv.q.d();
        if (d != null) {
            d.i().a("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zzarb = false;
            this.zzarc = null;
        }
        this.zzaqv.q().a((Runnable) new ei(this));
    }

    @MainThread
    public final void onConnectionSuspended(int i) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionSuspended");
        this.zzaqv.r().v().a("Service connection suspended");
        this.zzaqv.q().a((Runnable) new eh(this));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:22|23) */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r3.zzaqv.r().h_().a("Service connect failed to get IMeasurementService");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0063 */
    @android.support.annotation.MainThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected(android.content.ComponentName r4, android.os.IBinder r5) {
        /*
            r3 = this;
            java.lang.String r4 = "MeasurementServiceConnection.onServiceConnected"
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r4)
            monitor-enter(r3)
            r4 = 0
            if (r5 != 0) goto L_0x001f
            r3.zzarb = r4     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.measurement.dq r4 = r3.zzaqv     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.measurement.aq r4 = r4.r()     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.measurement.as r4 = r4.h_()     // Catch:{ all -> 0x001c }
            java.lang.String r5 = "Service connected with null binder"
            r4.a(r5)     // Catch:{ all -> 0x001c }
            monitor-exit(r3)     // Catch:{ all -> 0x001c }
            return
        L_0x001c:
            r4 = move-exception
            goto L_0x009a
        L_0x001f:
            r0 = 0
            java.lang.String r1 = r5.getInterfaceDescriptor()     // Catch:{ RemoteException -> 0x0063 }
            java.lang.String r2 = "com.google.android.gms.measurement.internal.IMeasurementService"
            boolean r2 = r2.equals(r1)     // Catch:{ RemoteException -> 0x0063 }
            if (r2 == 0) goto L_0x0053
            if (r5 != 0) goto L_0x002f
            goto L_0x0043
        L_0x002f:
            java.lang.String r1 = "com.google.android.gms.measurement.internal.IMeasurementService"
            android.os.IInterface r1 = r5.queryLocalInterface(r1)     // Catch:{ RemoteException -> 0x0063 }
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzfa     // Catch:{ RemoteException -> 0x0063 }
            if (r2 == 0) goto L_0x003d
            com.google.android.gms.internal.measurement.zzfa r1 = (com.google.android.gms.internal.measurement.zzfa) r1     // Catch:{ RemoteException -> 0x0063 }
        L_0x003b:
            r0 = r1
            goto L_0x0043
        L_0x003d:
            com.google.android.gms.internal.measurement.zzfc r1 = new com.google.android.gms.internal.measurement.zzfc     // Catch:{ RemoteException -> 0x0063 }
            r1.<init>(r5)     // Catch:{ RemoteException -> 0x0063 }
            goto L_0x003b
        L_0x0043:
            com.google.android.gms.internal.measurement.dq r5 = r3.zzaqv     // Catch:{ RemoteException -> 0x0063 }
            com.google.android.gms.internal.measurement.aq r5 = r5.r()     // Catch:{ RemoteException -> 0x0063 }
            com.google.android.gms.internal.measurement.as r5 = r5.w()     // Catch:{ RemoteException -> 0x0063 }
            java.lang.String r1 = "Bound to IMeasurementService interface"
            r5.a(r1)     // Catch:{ RemoteException -> 0x0063 }
            goto L_0x0072
        L_0x0053:
            com.google.android.gms.internal.measurement.dq r5 = r3.zzaqv     // Catch:{ RemoteException -> 0x0063 }
            com.google.android.gms.internal.measurement.aq r5 = r5.r()     // Catch:{ RemoteException -> 0x0063 }
            com.google.android.gms.internal.measurement.as r5 = r5.h_()     // Catch:{ RemoteException -> 0x0063 }
            java.lang.String r2 = "Got binder with a wrong descriptor"
            r5.a(r2, r1)     // Catch:{ RemoteException -> 0x0063 }
            goto L_0x0072
        L_0x0063:
            com.google.android.gms.internal.measurement.dq r5 = r3.zzaqv     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.measurement.aq r5 = r5.r()     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.measurement.as r5 = r5.h_()     // Catch:{ all -> 0x001c }
            java.lang.String r1 = "Service connect failed to get IMeasurementService"
            r5.a(r1)     // Catch:{ all -> 0x001c }
        L_0x0072:
            if (r0 != 0) goto L_0x008a
            r3.zzarb = r4     // Catch:{ all -> 0x001c }
            com.google.android.gms.common.stats.ConnectionTracker r4 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ IllegalArgumentException -> 0x0098 }
            com.google.android.gms.internal.measurement.dq r5 = r3.zzaqv     // Catch:{ IllegalArgumentException -> 0x0098 }
            android.content.Context r5 = r5.n()     // Catch:{ IllegalArgumentException -> 0x0098 }
            com.google.android.gms.internal.measurement.dq r0 = r3.zzaqv     // Catch:{ IllegalArgumentException -> 0x0098 }
            com.google.android.gms.internal.measurement.zziy r0 = r0.a     // Catch:{ IllegalArgumentException -> 0x0098 }
            r4.unbindService(r5, r0)     // Catch:{ IllegalArgumentException -> 0x0098 }
            goto L_0x0098
        L_0x008a:
            com.google.android.gms.internal.measurement.dq r4 = r3.zzaqv     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.measurement.bq r4 = r4.q()     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.measurement.ee r5 = new com.google.android.gms.internal.measurement.ee     // Catch:{ all -> 0x001c }
            r5.<init>(r3, r0)     // Catch:{ all -> 0x001c }
            r4.a(r5)     // Catch:{ all -> 0x001c }
        L_0x0098:
            monitor-exit(r3)     // Catch:{ all -> 0x001c }
            return
        L_0x009a:
            monitor-exit(r3)     // Catch:{ all -> 0x001c }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zziy.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceDisconnected");
        this.zzaqv.r().v().a("Service disconnected");
        this.zzaqv.q().a((Runnable) new ef(this, componentName));
    }

    @WorkerThread
    public final void zzc(Intent intent) {
        this.zzaqv.d();
        Context n = this.zzaqv.n();
        ConnectionTracker instance = ConnectionTracker.getInstance();
        synchronized (this) {
            if (this.zzarb) {
                this.zzaqv.r().w().a("Connection attempt already in progress");
                return;
            }
            this.zzaqv.r().w().a("Using local app measurement service");
            this.zzarb = true;
            instance.bindService(n, intent, this.zzaqv.a, GmsClientSupervisor.DEFAULT_BIND_FLAGS);
        }
    }

    @WorkerThread
    public final void zzkt() {
        this.zzaqv.d();
        Context n = this.zzaqv.n();
        synchronized (this) {
            if (this.zzarb) {
                this.zzaqv.r().w().a("Connection attempt already in progress");
            } else if (this.zzarc != null) {
                this.zzaqv.r().w().a("Already awaiting connection attempt");
            } else {
                this.zzarc = new ap(n, Looper.getMainLooper(), this, this);
                this.zzaqv.r().w().a("Connecting to remote service");
                this.zzarb = true;
                this.zzarc.checkAvailabilityAndConnect();
            }
        }
    }
}
