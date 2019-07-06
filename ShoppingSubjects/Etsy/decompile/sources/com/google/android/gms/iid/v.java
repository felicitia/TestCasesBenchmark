package com.google.android.gms.iid;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayDeque;
import java.util.Queue;

final class v implements ServiceConnection {
    int a;
    final Messenger b;
    aa c;
    final Queue<ab<?>> d;
    final SparseArray<ab<?>> e;
    final /* synthetic */ t f;

    private v(t tVar) {
        this.f = tVar;
        this.a = 0;
        this.b = new Messenger(new Handler(Looper.getMainLooper(), new w(this)));
        this.d = new ArrayDeque();
        this.e = new SparseArray<>();
    }

    private final void c() {
        this.f.b.execute(new y(this));
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void a() {
        if (this.a == 2 && this.d.isEmpty() && this.e.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.a = 3;
            ConnectionTracker.getInstance().unbindService(this.f.a, this);
        }
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void a(int i) {
        ab abVar = (ab) this.e.get(i);
        if (abVar != null) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Timing out request: ");
            sb.append(i);
            Log.w("MessengerIpcClient", sb.toString());
            this.e.remove(i);
            abVar.a(new zzaa(3, "Timed out waiting for response"));
            a();
        }
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void a(int i, String str) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String str2 = "MessengerIpcClient";
            String str3 = "Disconnected: ";
            String valueOf = String.valueOf(str);
            Log.d(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        }
        switch (this.a) {
            case 0:
                throw new IllegalStateException();
            case 1:
            case 2:
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Unbinding service");
                }
                this.a = 4;
                ConnectionTracker.getInstance().unbindService(this.f.a, this);
                zzaa zzaa = new zzaa(i, str);
                for (ab a2 : this.d) {
                    a2.a(zzaa);
                }
                this.d.clear();
                for (int i2 = 0; i2 < this.e.size(); i2++) {
                    ((ab) this.e.valueAt(i2)).a(zzaa);
                }
                this.e.clear();
                return;
            case 3:
                this.a = 4;
                return;
            case 4:
                return;
            default:
                int i3 = this.a;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i3);
                throw new IllegalStateException(sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0052, code lost:
        r5 = r5.getData();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005d, code lost:
        if (r5.getBoolean("unsupported", false) == false) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x005f, code lost:
        r1.a(new com.google.android.gms.iid.zzaa(4, "Not supported by GmsCore"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x006a, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x006b, code lost:
        r1.a(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006e, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(android.os.Message r5) {
        /*
            r4 = this;
            int r0 = r5.arg1
            java.lang.String r1 = "MessengerIpcClient"
            r2 = 3
            boolean r1 = android.util.Log.isLoggable(r1, r2)
            if (r1 == 0) goto L_0x0023
            java.lang.String r1 = "MessengerIpcClient"
            r2 = 41
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Received response to request: "
            r3.append(r2)
            r3.append(r0)
            java.lang.String r2 = r3.toString()
            android.util.Log.d(r1, r2)
        L_0x0023:
            monitor-enter(r4)
            android.util.SparseArray<com.google.android.gms.iid.ab<?>> r1 = r4.e     // Catch:{ all -> 0x006f }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x006f }
            com.google.android.gms.iid.ab r1 = (com.google.android.gms.iid.ab) r1     // Catch:{ all -> 0x006f }
            r2 = 1
            if (r1 != 0) goto L_0x0049
            java.lang.String r5 = "MessengerIpcClient"
            r1 = 50
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x006f }
            r3.<init>(r1)     // Catch:{ all -> 0x006f }
            java.lang.String r1 = "Received response for unknown request: "
            r3.append(r1)     // Catch:{ all -> 0x006f }
            r3.append(r0)     // Catch:{ all -> 0x006f }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x006f }
            android.util.Log.w(r5, r0)     // Catch:{ all -> 0x006f }
            monitor-exit(r4)     // Catch:{ all -> 0x006f }
            return r2
        L_0x0049:
            android.util.SparseArray<com.google.android.gms.iid.ab<?>> r3 = r4.e     // Catch:{ all -> 0x006f }
            r3.remove(r0)     // Catch:{ all -> 0x006f }
            r4.a()     // Catch:{ all -> 0x006f }
            monitor-exit(r4)     // Catch:{ all -> 0x006f }
            android.os.Bundle r5 = r5.getData()
            java.lang.String r0 = "unsupported"
            r3 = 0
            boolean r0 = r5.getBoolean(r0, r3)
            if (r0 == 0) goto L_0x006b
            com.google.android.gms.iid.zzaa r5 = new com.google.android.gms.iid.zzaa
            r0 = 4
            java.lang.String r3 = "Not supported by GmsCore"
            r5.<init>(r0, r3)
            r1.a(r5)
            return r2
        L_0x006b:
            r1.a(r5)
            return r2
        L_0x006f:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x006f }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.iid.v.a(android.os.Message):boolean");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0074, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean a(com.google.android.gms.iid.ab r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            int r0 = r5.a     // Catch:{ all -> 0x008e }
            r1 = 0
            r2 = 1
            switch(r0) {
                case 0: goto L_0x001e;
                case 1: goto L_0x0017;
                case 2: goto L_0x000d;
                case 3: goto L_0x000b;
                case 4: goto L_0x000b;
                default: goto L_0x0008;
            }     // Catch:{ all -> 0x008e }
        L_0x0008:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch:{ all -> 0x008e }
            goto L_0x0075
        L_0x000b:
            monitor-exit(r5)
            return r1
        L_0x000d:
            java.util.Queue<com.google.android.gms.iid.ab<?>> r0 = r5.d     // Catch:{ all -> 0x008e }
            r0.add(r6)     // Catch:{ all -> 0x008e }
            r5.c()     // Catch:{ all -> 0x008e }
            monitor-exit(r5)
            return r2
        L_0x0017:
            java.util.Queue<com.google.android.gms.iid.ab<?>> r0 = r5.d     // Catch:{ all -> 0x008e }
            r0.add(r6)     // Catch:{ all -> 0x008e }
            monitor-exit(r5)
            return r2
        L_0x001e:
            java.util.Queue<com.google.android.gms.iid.ab<?>> r0 = r5.d     // Catch:{ all -> 0x008e }
            r0.add(r6)     // Catch:{ all -> 0x008e }
            int r6 = r5.a     // Catch:{ all -> 0x008e }
            if (r6 != 0) goto L_0x0029
            r6 = r2
            goto L_0x002a
        L_0x0029:
            r6 = r1
        L_0x002a:
            com.google.android.gms.common.internal.Preconditions.checkState(r6)     // Catch:{ all -> 0x008e }
            java.lang.String r6 = "MessengerIpcClient"
            r0 = 2
            boolean r6 = android.util.Log.isLoggable(r6, r0)     // Catch:{ all -> 0x008e }
            if (r6 == 0) goto L_0x003d
            java.lang.String r6 = "MessengerIpcClient"
            java.lang.String r0 = "Starting bind to GmsCore"
            android.util.Log.v(r6, r0)     // Catch:{ all -> 0x008e }
        L_0x003d:
            r5.a = r2     // Catch:{ all -> 0x008e }
            android.content.Intent r6 = new android.content.Intent     // Catch:{ all -> 0x008e }
            java.lang.String r0 = "com.google.android.c2dm.intent.REGISTER"
            r6.<init>(r0)     // Catch:{ all -> 0x008e }
            java.lang.String r0 = "com.google.android.gms"
            r6.setPackage(r0)     // Catch:{ all -> 0x008e }
            com.google.android.gms.common.stats.ConnectionTracker r0 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ all -> 0x008e }
            com.google.android.gms.iid.t r3 = r5.f     // Catch:{ all -> 0x008e }
            android.content.Context r3 = r3.a     // Catch:{ all -> 0x008e }
            boolean r6 = r0.bindService(r3, r6, r5, r2)     // Catch:{ all -> 0x008e }
            if (r6 != 0) goto L_0x0061
            java.lang.String r6 = "Unable to bind to service"
            r5.a(r1, r6)     // Catch:{ all -> 0x008e }
            goto L_0x0073
        L_0x0061:
            com.google.android.gms.iid.t r6 = r5.f     // Catch:{ all -> 0x008e }
            java.util.concurrent.ScheduledExecutorService r6 = r6.b     // Catch:{ all -> 0x008e }
            com.google.android.gms.iid.x r0 = new com.google.android.gms.iid.x     // Catch:{ all -> 0x008e }
            r0.<init>(r5)     // Catch:{ all -> 0x008e }
            r3 = 30
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ all -> 0x008e }
            r6.schedule(r0, r3, r1)     // Catch:{ all -> 0x008e }
        L_0x0073:
            monitor-exit(r5)
            return r2
        L_0x0075:
            int r0 = r5.a     // Catch:{ all -> 0x008e }
            r1 = 26
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x008e }
            r2.<init>(r1)     // Catch:{ all -> 0x008e }
            java.lang.String r1 = "Unknown state: "
            r2.append(r1)     // Catch:{ all -> 0x008e }
            r2.append(r0)     // Catch:{ all -> 0x008e }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x008e }
            r6.<init>(r0)     // Catch:{ all -> 0x008e }
            throw r6     // Catch:{ all -> 0x008e }
        L_0x008e:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.iid.v.a(com.google.android.gms.iid.ab):boolean");
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void b() {
        if (this.a == 1) {
            a(1, "Timed out while binding");
        }
    }

    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        if (iBinder == null) {
            a(0, "Null service connection");
            return;
        }
        try {
            this.c = new aa(iBinder);
            this.a = 2;
            c();
        } catch (RemoteException e2) {
            a(0, e2.getMessage());
        }
    }

    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        a(2, "Service disconnected");
    }
}
