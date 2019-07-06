package com.google.firebase.iid;

final /* synthetic */ class b implements Runnable {
    private final al a;

    b(al alVar) {
        this.a = alVar;
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
        r3 = r0.f.b;
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
        r0.c.a(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00a5, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a6, code lost:
        r0.a(2, r1.getMessage());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r8 = this;
            com.google.firebase.iid.al r0 = r8.a
        L_0x0002:
            monitor-enter(r0)
            int r1 = r0.a     // Catch:{ all -> 0x00af }
            r2 = 2
            if (r1 == r2) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x00af }
            return
        L_0x000a:
            java.util.Queue<com.google.firebase.iid.e<?>> r1 = r0.d     // Catch:{ all -> 0x00af }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x00af }
            if (r1 == 0) goto L_0x0017
            r0.a()     // Catch:{ all -> 0x00af }
            monitor-exit(r0)     // Catch:{ all -> 0x00af }
            return
        L_0x0017:
            java.util.Queue<com.google.firebase.iid.e<?>> r1 = r0.d     // Catch:{ all -> 0x00af }
            java.lang.Object r1 = r1.poll()     // Catch:{ all -> 0x00af }
            com.google.firebase.iid.e r1 = (com.google.firebase.iid.e) r1     // Catch:{ all -> 0x00af }
            android.util.SparseArray<com.google.firebase.iid.e<?>> r3 = r0.e     // Catch:{ all -> 0x00af }
            int r4 = r1.a     // Catch:{ all -> 0x00af }
            r3.put(r4, r1)     // Catch:{ all -> 0x00af }
            com.google.firebase.iid.aj r3 = r0.f     // Catch:{ all -> 0x00af }
            java.util.concurrent.ScheduledExecutorService r3 = r3.c     // Catch:{ all -> 0x00af }
            com.google.firebase.iid.c r4 = new com.google.firebase.iid.c     // Catch:{ all -> 0x00af }
            r4.<init>(r0, r1)     // Catch:{ all -> 0x00af }
            r5 = 30
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ all -> 0x00af }
            r3.schedule(r4, r5, r7)     // Catch:{ all -> 0x00af }
            monitor-exit(r0)     // Catch:{ all -> 0x00af }
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
            com.google.firebase.iid.aj r3 = r0.f
            android.content.Context r3 = r3.b
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
            com.google.firebase.iid.d r1 = r0.c     // Catch:{ RemoteException -> 0x00a5 }
            r1.a(r5)     // Catch:{ RemoteException -> 0x00a5 }
            goto L_0x0002
        L_0x00a5:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            r0.a(r2, r1)
            goto L_0x0002
        L_0x00af:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00af }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.b.run():void");
    }
}
