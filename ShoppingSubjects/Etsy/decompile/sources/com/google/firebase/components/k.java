package com.google.firebase.components;

import android.support.annotation.GuardedBy;
import com.google.firebase.a.a;
import com.google.firebase.a.b;
import com.google.firebase.a.c;
import com.google.firebase.a.d;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

class k implements c, d {
    @GuardedBy("this")
    private final Map<Class<?>, ConcurrentHashMap<b<Object>, Executor>> a = new HashMap();
    @GuardedBy("this")
    private Queue<a<?>> b = new ArrayDeque();
    private final Executor c;

    k(Executor executor) {
        this.c = executor;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r0.hasNext() == false) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        r1 = (java.util.Map.Entry) r0.next();
        ((java.util.concurrent.Executor) r1.getValue()).execute(new com.google.firebase.components.l(r1, r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0033, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        r0 = b(r5).iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.google.firebase.a.a<?> r5) {
        /*
            r4 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            monitor-enter(r4)
            java.util.Queue<com.google.firebase.a.a<?>> r0 = r4.b     // Catch:{ all -> 0x0034 }
            if (r0 == 0) goto L_0x000f
            java.util.Queue<com.google.firebase.a.a<?>> r0 = r4.b     // Catch:{ all -> 0x0034 }
            r0.add(r5)     // Catch:{ all -> 0x0034 }
            monitor-exit(r4)     // Catch:{ all -> 0x0034 }
            return
        L_0x000f:
            monitor-exit(r4)     // Catch:{ all -> 0x0034 }
            java.util.Set r0 = r4.b(r5)
            java.util.Iterator r0 = r0.iterator()
        L_0x0018:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0033
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getValue()
            java.util.concurrent.Executor r2 = (java.util.concurrent.Executor) r2
            com.google.firebase.components.l r3 = new com.google.firebase.components.l
            r3.<init>(r1, r5)
            r2.execute(r3)
            goto L_0x0018
        L_0x0033:
            return
        L_0x0034:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0034 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.components.k.a(com.google.firebase.a.a):void");
    }

    private synchronized Set<Entry<b<Object>, Executor>> b(a<?> aVar) {
        Map map = (Map) this.a.get(aVar.a());
        if (map == null) {
            return Collections.emptySet();
        }
        return map.entrySet();
    }

    /* access modifiers changed from: 0000 */
    public final void a() {
        Queue<a> queue;
        synchronized (this) {
            if (this.b != null) {
                queue = this.b;
                this.b = null;
            } else {
                queue = null;
            }
        }
        if (queue != null) {
            for (a a2 : queue) {
                a(a2);
            }
        }
    }
}
