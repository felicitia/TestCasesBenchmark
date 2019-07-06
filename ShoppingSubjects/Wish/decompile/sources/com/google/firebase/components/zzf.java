package com.google.firebase.components;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-common@@16.0.1 */
class zzf implements Publisher, Subscriber {
    private final Map<Class<?>, ConcurrentHashMap<EventHandler<Object>, Executor>> zza = new HashMap();
    private Queue<Event<?>> zzb = new ArrayDeque();
    private final Executor zzc;

    zzf(Executor executor) {
        this.zzc = executor;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r0.hasNext() == false) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        r1 = (java.util.Map.Entry) r0.next();
        ((java.util.concurrent.Executor) r1.getValue()).execute(new com.google.firebase.components.zzg(r1, r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0033, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        r0 = zza(r5).iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void publish(com.google.firebase.events.Event<?> r5) {
        /*
            r4 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            monitor-enter(r4)
            java.util.Queue<com.google.firebase.events.Event<?>> r0 = r4.zzb     // Catch:{ all -> 0x0034 }
            if (r0 == 0) goto L_0x000f
            java.util.Queue<com.google.firebase.events.Event<?>> r0 = r4.zzb     // Catch:{ all -> 0x0034 }
            r0.add(r5)     // Catch:{ all -> 0x0034 }
            monitor-exit(r4)     // Catch:{ all -> 0x0034 }
            return
        L_0x000f:
            monitor-exit(r4)     // Catch:{ all -> 0x0034 }
            java.util.Set r0 = r4.zza(r5)
            java.util.Iterator r0 = r0.iterator()
        L_0x0018:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0033
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getValue()
            java.util.concurrent.Executor r2 = (java.util.concurrent.Executor) r2
            com.google.firebase.components.zzg r3 = new com.google.firebase.components.zzg
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.components.zzf.publish(com.google.firebase.events.Event):void");
    }

    private synchronized Set<Entry<EventHandler<Object>, Executor>> zza(Event<?> event) {
        Map map = (Map) this.zza.get(event.getType());
        if (map == null) {
            return Collections.emptySet();
        }
        return map.entrySet();
    }

    /* access modifiers changed from: 0000 */
    public final void zza() {
        Queue<Event> queue;
        synchronized (this) {
            if (this.zzb != null) {
                queue = this.zzb;
                this.zzb = null;
            } else {
                queue = null;
            }
        }
        if (queue != null) {
            for (Event publish : queue) {
                publish(publish);
            }
        }
    }
}
