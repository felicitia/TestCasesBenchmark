package com.google.firebase.iid;

import android.support.annotation.GuardedBy;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.tasks.g;
import java.util.Map;

final class t {
    @GuardedBy("this")
    private int a = 0;
    @GuardedBy("this")
    private final Map<Integer, g<Void>> b = new ArrayMap();
    @GuardedBy("itself")
    private final p c;

    t(p pVar) {
        this.c = pVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0042, code lost:
        android.util.Log.d(r6, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0045, code lost:
        return true;
     */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(com.google.firebase.iid.FirebaseInstanceId r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "!"
            java.lang.String[] r7 = r7.split(r0)
            r0 = 1
            r1 = 2
            int r2 = r7.length
            if (r2 != r1) goto L_0x0075
            r1 = 0
            r2 = r7[r1]
            r7 = r7[r0]
            r3 = -1
            int r4 = r2.hashCode()     // Catch:{ IOException -> 0x0054 }
            r5 = 83
            if (r4 == r5) goto L_0x0028
            r5 = 85
            if (r4 == r5) goto L_0x001e
            goto L_0x0031
        L_0x001e:
            java.lang.String r4 = "U"
            boolean r2 = r2.equals(r4)     // Catch:{ IOException -> 0x0054 }
            if (r2 == 0) goto L_0x0031
            r3 = r0
            goto L_0x0031
        L_0x0028:
            java.lang.String r4 = "S"
            boolean r2 = r2.equals(r4)     // Catch:{ IOException -> 0x0054 }
            if (r2 == 0) goto L_0x0031
            r3 = r1
        L_0x0031:
            switch(r3) {
                case 0: goto L_0x0046;
                case 1: goto L_0x0035;
                default: goto L_0x0034;
            }     // Catch:{ IOException -> 0x0054 }
        L_0x0034:
            return r0
        L_0x0035:
            r6.b(r7)     // Catch:{ IOException -> 0x0054 }
            boolean r6 = com.google.firebase.iid.FirebaseInstanceId.f()     // Catch:{ IOException -> 0x0054 }
            if (r6 == 0) goto L_0x0075
            java.lang.String r6 = "FirebaseInstanceId"
            java.lang.String r7 = "unsubscribe operation succeeded"
        L_0x0042:
            android.util.Log.d(r6, r7)     // Catch:{ IOException -> 0x0054 }
            return r0
        L_0x0046:
            r6.a(r7)     // Catch:{ IOException -> 0x0054 }
            boolean r6 = com.google.firebase.iid.FirebaseInstanceId.f()     // Catch:{ IOException -> 0x0054 }
            if (r6 == 0) goto L_0x0075
            java.lang.String r6 = "FirebaseInstanceId"
            java.lang.String r7 = "subscribe operation succeeded"
            goto L_0x0042
        L_0x0054:
            r6 = move-exception
            java.lang.String r7 = "FirebaseInstanceId"
            java.lang.String r0 = "Topic sync failed: "
            java.lang.String r6 = r6.getMessage()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            int r2 = r6.length()
            if (r2 == 0) goto L_0x006c
            java.lang.String r6 = r0.concat(r6)
            goto L_0x0071
        L_0x006c:
            java.lang.String r6 = new java.lang.String
            r6.<init>(r0)
        L_0x0071:
            android.util.Log.e(r7, r6)
            return r1
        L_0x0075:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.t.a(com.google.firebase.iid.FirebaseInstanceId, java.lang.String):boolean");
    }

    private final synchronized boolean a(String str) {
        synchronized (this.c) {
            String a2 = this.c.a();
            String valueOf = String.valueOf(",");
            String valueOf2 = String.valueOf(str);
            if (!a2.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
                return false;
            }
            String valueOf3 = String.valueOf(",");
            String valueOf4 = String.valueOf(str);
            this.c.a(a2.substring((valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3)).length()));
            return true;
        }
    }

    @Nullable
    @GuardedBy("this")
    private final String b() {
        String a2;
        synchronized (this.c) {
            a2 = this.c.a();
        }
        if (!TextUtils.isEmpty(a2)) {
            String[] split = a2.split(",");
            if (split.length > 1 && !TextUtils.isEmpty(split[1])) {
                return split[1];
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public final synchronized boolean a() {
        return b() != null;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r2 = (com.google.android.gms.tasks.g) r4.b.remove(java.lang.Integer.valueOf(r4.a));
        a(r0);
        r4.a++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0031, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0032, code lost:
        if (r2 == null) goto L_0x0000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0034, code lost:
        r2.a(null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        if (a(r5, r0) != false) goto L_0x001a;
     */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(com.google.firebase.iid.FirebaseInstanceId r5) {
        /*
            r4 = this;
        L_0x0000:
            monitor-enter(r4)
            java.lang.String r0 = r4.b()     // Catch:{ all -> 0x003c }
            r1 = 1
            if (r0 != 0) goto L_0x0011
            java.lang.String r5 = "FirebaseInstanceId"
            java.lang.String r0 = "topic sync succeeded"
            android.util.Log.d(r5, r0)     // Catch:{ all -> 0x003c }
            monitor-exit(r4)     // Catch:{ all -> 0x003c }
            return r1
        L_0x0011:
            monitor-exit(r4)     // Catch:{ all -> 0x003c }
            boolean r2 = a(r5, r0)
            if (r2 != 0) goto L_0x001a
            r5 = 0
            return r5
        L_0x001a:
            monitor-enter(r4)
            java.util.Map<java.lang.Integer, com.google.android.gms.tasks.g<java.lang.Void>> r2 = r4.b     // Catch:{ all -> 0x0039 }
            int r3 = r4.a     // Catch:{ all -> 0x0039 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0039 }
            java.lang.Object r2 = r2.remove(r3)     // Catch:{ all -> 0x0039 }
            com.google.android.gms.tasks.g r2 = (com.google.android.gms.tasks.g) r2     // Catch:{ all -> 0x0039 }
            r4.a(r0)     // Catch:{ all -> 0x0039 }
            int r0 = r4.a     // Catch:{ all -> 0x0039 }
            int r0 = r0 + r1
            r4.a = r0     // Catch:{ all -> 0x0039 }
            monitor-exit(r4)     // Catch:{ all -> 0x0039 }
            if (r2 == 0) goto L_0x0000
            r0 = 0
            r2.a(r0)
            goto L_0x0000
        L_0x0039:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0039 }
            throw r5
        L_0x003c:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x003c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.t.a(com.google.firebase.iid.FirebaseInstanceId):boolean");
    }
}
