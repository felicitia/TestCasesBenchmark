package com.firebase.jobdispatcher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import com.firebase.jobdispatcher.IJobCallback.Stub;

/* compiled from: ExecutionDelegator */
class d {
    private static final SimpleArrayMap<String, p> a = new SimpleArrayMap<>();
    private final IJobCallback b = new Stub() {
        public void jobFinished(Bundle bundle, int i) {
            a b = GooglePlayReceiver.getJobCoder().b(bundle);
            if (b == null) {
                Log.wtf("FJD.ExternalReceiver", "jobFinished: unknown invocation provided");
            } else {
                d.this.a(b.a(), i);
            }
        }
    };
    private final Context c;
    private final a d;

    /* compiled from: ExecutionDelegator */
    interface a {
        void onJobFinished(@NonNull n nVar, int i);
    }

    d(Context context, a aVar) {
        this.c = context;
        this.d = aVar;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.firebase.jobdispatcher.n r6) {
        /*
            r5 = this;
            if (r6 != 0) goto L_0x0003
            return
        L_0x0003:
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.p> r0 = a
            monitor-enter(r0)
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.p> r1 = a     // Catch:{ all -> 0x006c }
            java.lang.String r2 = r6.i()     // Catch:{ all -> 0x006c }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x006c }
            com.firebase.jobdispatcher.p r1 = (com.firebase.jobdispatcher.p) r1     // Catch:{ all -> 0x006c }
            if (r1 == 0) goto L_0x0028
            boolean r2 = r1.a()     // Catch:{ all -> 0x006c }
            if (r2 != 0) goto L_0x0028
            boolean r2 = r1.c(r6)     // Catch:{ all -> 0x006c }
            if (r2 == 0) goto L_0x003a
            boolean r2 = r1.b()     // Catch:{ all -> 0x006c }
            if (r2 != 0) goto L_0x003a
            monitor-exit(r0)     // Catch:{ all -> 0x006c }
            return
        L_0x0028:
            com.firebase.jobdispatcher.p r1 = new com.firebase.jobdispatcher.p     // Catch:{ all -> 0x006c }
            com.firebase.jobdispatcher.IJobCallback r2 = r5.b     // Catch:{ all -> 0x006c }
            android.content.Context r3 = r5.c     // Catch:{ all -> 0x006c }
            r1.<init>(r2, r3)     // Catch:{ all -> 0x006c }
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.p> r2 = a     // Catch:{ all -> 0x006c }
            java.lang.String r3 = r6.i()     // Catch:{ all -> 0x006c }
            r2.put(r3, r1)     // Catch:{ all -> 0x006c }
        L_0x003a:
            boolean r2 = r1.b(r6)     // Catch:{ all -> 0x006c }
            if (r2 != 0) goto L_0x006a
            android.content.Context r2 = r5.c     // Catch:{ all -> 0x006c }
            android.content.Intent r3 = r5.a(r6)     // Catch:{ all -> 0x006c }
            r4 = 1
            boolean r2 = r2.bindService(r3, r1, r4)     // Catch:{ all -> 0x006c }
            if (r2 != 0) goto L_0x006a
            java.lang.String r2 = "FJD.ExternalReceiver"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x006c }
            r3.<init>()     // Catch:{ all -> 0x006c }
            java.lang.String r4 = "Unable to bind to "
            r3.append(r4)     // Catch:{ all -> 0x006c }
            java.lang.String r6 = r6.i()     // Catch:{ all -> 0x006c }
            r3.append(r6)     // Catch:{ all -> 0x006c }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x006c }
            android.util.Log.e(r2, r6)     // Catch:{ all -> 0x006c }
            r1.c()     // Catch:{ all -> 0x006c }
        L_0x006a:
            monitor-exit(r0)     // Catch:{ all -> 0x006c }
            return
        L_0x006c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x006c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.d.a(com.firebase.jobdispatcher.n):void");
    }

    @NonNull
    private Intent a(o oVar) {
        Intent intent = new Intent("com.firebase.jobdispatcher.ACTION_EXECUTE");
        intent.setClassName(this.c, oVar.i());
        return intent;
    }

    static void a(n nVar, boolean z) {
        synchronized (a) {
            p pVar = (p) a.get(nVar.i());
            if (pVar != null) {
                pVar.a(nVar, z);
                if (pVar.a()) {
                    a.remove(nVar.i());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(n nVar, int i) {
        synchronized (a) {
            p pVar = (p) a.get(nVar.i());
            if (pVar != null) {
                pVar.a(nVar);
                if (pVar.a()) {
                    a.remove(nVar.i());
                }
            }
        }
        this.d.onJobFinished(nVar, i);
    }
}
