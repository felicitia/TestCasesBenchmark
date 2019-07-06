package com.google.firebase.iid;

import android.support.v4.util.ArrayMap;
import android.util.Pair;
import com.google.android.gms.tasks.f;
import java.util.Map;

final class j {
    private final Map<Pair<String, String>, f<String>> a = new ArrayMap();

    j() {
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ f a(Pair pair, f fVar) throws Exception {
        synchronized (this) {
            this.a.remove(pair);
        }
        return fVar;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x003f, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized com.google.android.gms.tasks.f<java.lang.String> a(java.lang.String r4, java.lang.String r5, com.google.firebase.iid.l r6) {
        /*
            r3 = this;
            monitor-enter(r3)
            android.util.Pair r0 = new android.util.Pair     // Catch:{ all -> 0x0083 }
            r0.<init>(r4, r5)     // Catch:{ all -> 0x0083 }
            java.util.Map<android.util.Pair<java.lang.String, java.lang.String>, com.google.android.gms.tasks.f<java.lang.String>> r4 = r3.a     // Catch:{ all -> 0x0083 }
            java.lang.Object r4 = r4.get(r0)     // Catch:{ all -> 0x0083 }
            com.google.android.gms.tasks.f r4 = (com.google.android.gms.tasks.f) r4     // Catch:{ all -> 0x0083 }
            r5 = 3
            if (r4 == 0) goto L_0x0040
            java.lang.String r6 = "FirebaseInstanceId"
            boolean r5 = android.util.Log.isLoggable(r6, r5)     // Catch:{ all -> 0x0083 }
            if (r5 == 0) goto L_0x003e
            java.lang.String r5 = "FirebaseInstanceId"
            java.lang.String r6 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0083 }
            r0 = 29
            java.lang.String r1 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x0083 }
            int r1 = r1.length()     // Catch:{ all -> 0x0083 }
            int r0 = r0 + r1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0083 }
            r1.<init>(r0)     // Catch:{ all -> 0x0083 }
            java.lang.String r0 = "Joining ongoing request for: "
            r1.append(r0)     // Catch:{ all -> 0x0083 }
            r1.append(r6)     // Catch:{ all -> 0x0083 }
            java.lang.String r6 = r1.toString()     // Catch:{ all -> 0x0083 }
            android.util.Log.d(r5, r6)     // Catch:{ all -> 0x0083 }
        L_0x003e:
            monitor-exit(r3)
            return r4
        L_0x0040:
            java.lang.String r4 = "FirebaseInstanceId"
            boolean r4 = android.util.Log.isLoggable(r4, r5)     // Catch:{ all -> 0x0083 }
            if (r4 == 0) goto L_0x006d
            java.lang.String r4 = "FirebaseInstanceId"
            java.lang.String r5 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0083 }
            r1 = 24
            java.lang.String r2 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0083 }
            int r2 = r2.length()     // Catch:{ all -> 0x0083 }
            int r1 = r1 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0083 }
            r2.<init>(r1)     // Catch:{ all -> 0x0083 }
            java.lang.String r1 = "Making new request for: "
            r2.append(r1)     // Catch:{ all -> 0x0083 }
            r2.append(r5)     // Catch:{ all -> 0x0083 }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x0083 }
            android.util.Log.d(r4, r5)     // Catch:{ all -> 0x0083 }
        L_0x006d:
            com.google.android.gms.tasks.f r4 = r6.a()     // Catch:{ all -> 0x0083 }
            java.util.concurrent.Executor r5 = com.google.firebase.iid.FirebaseInstanceId.a     // Catch:{ all -> 0x0083 }
            com.google.firebase.iid.k r6 = new com.google.firebase.iid.k     // Catch:{ all -> 0x0083 }
            r6.<init>(r3, r0)     // Catch:{ all -> 0x0083 }
            com.google.android.gms.tasks.f r4 = r4.b(r5, r6)     // Catch:{ all -> 0x0083 }
            java.util.Map<android.util.Pair<java.lang.String, java.lang.String>, com.google.android.gms.tasks.f<java.lang.String>> r5 = r3.a     // Catch:{ all -> 0x0083 }
            r5.put(r0, r4)     // Catch:{ all -> 0x0083 }
            monitor-exit(r3)
            return r4
        L_0x0083:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.j.a(java.lang.String, java.lang.String, com.google.firebase.iid.l):com.google.android.gms.tasks.f");
    }
}
