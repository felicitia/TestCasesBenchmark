package com.google.android.gms.iid;

final /* synthetic */ class y implements Runnable {
    private final v a;

    y(v vVar) {
        this.a = vVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0040, code lost:
        if (android.util.Log.isLoggable("MessengerIpcClient", 3) == false) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        r4 = java.lang.String.valueOf(r1);
        r6 = new java.lang.StringBuilder(8 + java.lang.String.valueOf(r4).length());
        r6.append("Sending ");
        r6.append(r4);
        android.util.Log.d("MessengerIpcClient", r6.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0067, code lost:
        r3 = r0.f.a;
        r4 = r0.b;
        r5 = android.os.Message.obtain();
        r5.what = r1.c;
        r5.arg1 = r1.a;
        r5.replyTo = r4;
        r4 = new android.os.Bundle();
        r4.putBoolean("oneWay", r1.a());
        r4.putString("pkg", r3.getPackageName());
        r4.putBundle("data", r1.d);
        r5.setData(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r1 = r0.c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00a2, code lost:
        if (r1.a == null) goto L_0x00ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a4, code lost:
        r1.a.send(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00ad, code lost:
        if (r1.b == null) goto L_0x00b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00af, code lost:
        r1.b.send(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00bd, code lost:
        throw new java.lang.IllegalStateException("Both messengers are null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00be, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00bf, code lost:
        r0.a(2, r1.getMessage());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r8 = this;
            com.google.android.gms.iid.v r0 = r8.a
        L_0x0002:
            monitor-enter(r0)
            int r1 = r0.a     // Catch:{ all -> 0x00c8 }
            r2 = 2
            if (r1 == r2) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x00c8 }
            return
        L_0x000a:
            java.util.Queue<com.google.android.gms.iid.ab<?>> r1 = r0.d     // Catch:{ all -> 0x00c8 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x00c8 }
            if (r1 == 0) goto L_0x0017
            r0.a()     // Catch:{ all -> 0x00c8 }
            monitor-exit(r0)     // Catch:{ all -> 0x00c8 }
            return
        L_0x0017:
            java.util.Queue<com.google.android.gms.iid.ab<?>> r1 = r0.d     // Catch:{ all -> 0x00c8 }
            java.lang.Object r1 = r1.poll()     // Catch:{ all -> 0x00c8 }
            com.google.android.gms.iid.ab r1 = (com.google.android.gms.iid.ab) r1     // Catch:{ all -> 0x00c8 }
            android.util.SparseArray<com.google.android.gms.iid.ab<?>> r3 = r0.e     // Catch:{ all -> 0x00c8 }
            int r4 = r1.a     // Catch:{ all -> 0x00c8 }
            r3.put(r4, r1)     // Catch:{ all -> 0x00c8 }
            com.google.android.gms.iid.t r3 = r0.f     // Catch:{ all -> 0x00c8 }
            java.util.concurrent.ScheduledExecutorService r3 = r3.b     // Catch:{ all -> 0x00c8 }
            com.google.android.gms.iid.z r4 = new com.google.android.gms.iid.z     // Catch:{ all -> 0x00c8 }
            r4.<init>(r0, r1)     // Catch:{ all -> 0x00c8 }
            r5 = 30
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ all -> 0x00c8 }
            r3.schedule(r4, r5, r7)     // Catch:{ all -> 0x00c8 }
            monitor-exit(r0)     // Catch:{ all -> 0x00c8 }
            java.lang.String r3 = "MessengerIpcClient"
            r4 = 3
            boolean r3 = android.util.Log.isLoggable(r3, r4)
            if (r3 == 0) goto L_0x0067
            java.lang.String r3 = "MessengerIpcClient"
            java.lang.String r4 = java.lang.String.valueOf(r1)
            r5 = 8
            java.lang.String r6 = java.lang.String.valueOf(r4)
            int r6 = r6.length()
            int r5 = r5 + r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r5)
            java.lang.String r5 = "Sending "
            r6.append(r5)
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            android.util.Log.d(r3, r4)
        L_0x0067:
            com.google.android.gms.iid.t r3 = r0.f
            android.content.Context r3 = r3.a
            android.os.Messenger r4 = r0.b
            android.os.Message r5 = android.os.Message.obtain()
            int r6 = r1.c
            r5.what = r6
            int r6 = r1.a
            r5.arg1 = r6
            r5.replyTo = r4
            android.os.Bundle r4 = new android.os.Bundle
            r4.<init>()
            java.lang.String r6 = "oneWay"
            boolean r7 = r1.a()
            r4.putBoolean(r6, r7)
            java.lang.String r6 = "pkg"
            java.lang.String r3 = r3.getPackageName()
            r4.putString(r6, r3)
            java.lang.String r3 = "data"
            android.os.Bundle r1 = r1.d
            r4.putBundle(r3, r1)
            r5.setData(r4)
            com.google.android.gms.iid.aa r1 = r0.c     // Catch:{ RemoteException -> 0x00be }
            android.os.Messenger r3 = r1.a     // Catch:{ RemoteException -> 0x00be }
            if (r3 == 0) goto L_0x00ab
            android.os.Messenger r1 = r1.a     // Catch:{ RemoteException -> 0x00be }
            r1.send(r5)     // Catch:{ RemoteException -> 0x00be }
            goto L_0x0002
        L_0x00ab:
            com.google.android.gms.iid.MessengerCompat r3 = r1.b     // Catch:{ RemoteException -> 0x00be }
            if (r3 == 0) goto L_0x00b6
            com.google.android.gms.iid.MessengerCompat r1 = r1.b     // Catch:{ RemoteException -> 0x00be }
            r1.send(r5)     // Catch:{ RemoteException -> 0x00be }
            goto L_0x0002
        L_0x00b6:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ RemoteException -> 0x00be }
            java.lang.String r3 = "Both messengers are null"
            r1.<init>(r3)     // Catch:{ RemoteException -> 0x00be }
            throw r1     // Catch:{ RemoteException -> 0x00be }
        L_0x00be:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            r0.a(r2, r1)
            goto L_0x0002
        L_0x00c8:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00c8 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.iid.y.run():void");
    }
}
