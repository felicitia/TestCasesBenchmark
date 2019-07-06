package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;

public final class zziy implements ServiceConnection, BaseConnectionCallbacks, BaseOnConnectionFailedListener {
    final /* synthetic */ zzik zzaqv;
    /* access modifiers changed from: private */
    public volatile boolean zzarb;
    private volatile zzfh zzarc;

    protected zziy(zzik zzik) {
        this.zzaqv = zzik;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0022 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onConnected(android.os.Bundle r4) {
        /*
            r3 = this;
            java.lang.String r4 = "MeasurementServiceConnection.onConnected"
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r4)
            monitor-enter(r3)
            r4 = 0
            com.google.android.gms.internal.measurement.zzfh r0 = r3.zzarc     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            android.os.IInterface r0 = r0.getService()     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.measurement.zzfa r0 = (com.google.android.gms.internal.measurement.zzfa) r0     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            r3.zzarc = r4     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.measurement.zzik r1 = r3.zzaqv     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.measurement.zzgi r1 = r1.zzgh()     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.measurement.zzjb r2 = new com.google.android.gms.internal.measurement.zzjb     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            r2.<init>(r3, r0)     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            r1.zzc(r2)     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
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

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionFailed");
        zzfi zzjy = this.zzaqv.zzacv.zzjy();
        if (zzjy != null) {
            zzjy.zziy().zzg("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zzarb = false;
            this.zzarc = null;
        }
        this.zzaqv.zzgh().zzc((Runnable) new zzjd(this));
    }

    public final void onConnectionSuspended(int i) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionSuspended");
        this.zzaqv.zzgi().zzjb().log("Service connection suspended");
        this.zzaqv.zzgh().zzc((Runnable) new zzjc(this));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:22|23) */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r3.zzaqv.zzgi().zziv().log("Service connect failed to get IMeasurementService");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0063 */
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
            com.google.android.gms.internal.measurement.zzik r4 = r3.zzaqv     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzgi()     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zziv()     // Catch:{ all -> 0x001c }
            java.lang.String r5 = "Service connected with null binder"
            r4.log(r5)     // Catch:{ all -> 0x001c }
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
            com.google.android.gms.internal.measurement.zzik r5 = r3.zzaqv     // Catch:{ RemoteException -> 0x0063 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzgi()     // Catch:{ RemoteException -> 0x0063 }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zzjc()     // Catch:{ RemoteException -> 0x0063 }
            java.lang.String r1 = "Bound to IMeasurementService interface"
            r5.log(r1)     // Catch:{ RemoteException -> 0x0063 }
            goto L_0x0072
        L_0x0053:
            com.google.android.gms.internal.measurement.zzik r5 = r3.zzaqv     // Catch:{ RemoteException -> 0x0063 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzgi()     // Catch:{ RemoteException -> 0x0063 }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziv()     // Catch:{ RemoteException -> 0x0063 }
            java.lang.String r2 = "Got binder with a wrong descriptor"
            r5.zzg(r2, r1)     // Catch:{ RemoteException -> 0x0063 }
            goto L_0x0072
        L_0x0063:
            com.google.android.gms.internal.measurement.zzik r5 = r3.zzaqv     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzgi()     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziv()     // Catch:{ all -> 0x001c }
            java.lang.String r1 = "Service connect failed to get IMeasurementService"
            r5.log(r1)     // Catch:{ all -> 0x001c }
        L_0x0072:
            if (r0 != 0) goto L_0x008a
            r3.zzarb = r4     // Catch:{ all -> 0x001c }
            com.google.android.gms.common.stats.ConnectionTracker r4 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ IllegalArgumentException -> 0x0098 }
            com.google.android.gms.internal.measurement.zzik r5 = r3.zzaqv     // Catch:{ IllegalArgumentException -> 0x0098 }
            android.content.Context r5 = r5.getContext()     // Catch:{ IllegalArgumentException -> 0x0098 }
            com.google.android.gms.internal.measurement.zzik r0 = r3.zzaqv     // Catch:{ IllegalArgumentException -> 0x0098 }
            com.google.android.gms.internal.measurement.zziy r0 = r0.zzaqo     // Catch:{ IllegalArgumentException -> 0x0098 }
            r4.unbindService(r5, r0)     // Catch:{ IllegalArgumentException -> 0x0098 }
            goto L_0x0098
        L_0x008a:
            com.google.android.gms.internal.measurement.zzik r4 = r3.zzaqv     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.measurement.zzgi r4 = r4.zzgh()     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.measurement.zziz r5 = new com.google.android.gms.internal.measurement.zziz     // Catch:{ all -> 0x001c }
            r5.<init>(r3, r0)     // Catch:{ all -> 0x001c }
            r4.zzc(r5)     // Catch:{ all -> 0x001c }
        L_0x0098:
            monitor-exit(r3)     // Catch:{ all -> 0x001c }
            return
        L_0x009a:
            monitor-exit(r3)     // Catch:{ all -> 0x001c }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zziy.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceDisconnected");
        this.zzaqv.zzgi().zzjb().log("Service disconnected");
        this.zzaqv.zzgh().zzc((Runnable) new zzja(this, componentName));
    }

    public final void zzc(Intent intent) {
        this.zzaqv.zzab();
        Context context = this.zzaqv.getContext();
        ConnectionTracker instance = ConnectionTracker.getInstance();
        synchronized (this) {
            if (this.zzarb) {
                this.zzaqv.zzgi().zzjc().log("Connection attempt already in progress");
                return;
            }
            this.zzaqv.zzgi().zzjc().log("Using local app measurement service");
            this.zzarb = true;
            instance.bindService(context, intent, this.zzaqv.zzaqo, 129);
        }
    }

    public final void zzkt() {
        this.zzaqv.zzab();
        Context context = this.zzaqv.getContext();
        synchronized (this) {
            if (this.zzarb) {
                this.zzaqv.zzgi().zzjc().log("Connection attempt already in progress");
            } else if (this.zzarc != null) {
                this.zzaqv.zzgi().zzjc().log("Already awaiting connection attempt");
            } else {
                this.zzarc = new zzfh(context, Looper.getMainLooper(), this, this);
                this.zzaqv.zzgi().zzjc().log("Connecting to remote service");
                this.zzarb = true;
                this.zzarc.checkAvailabilityAndConnect();
            }
        }
    }
}
