package com.bumptech.glide.load.engine.a;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: DiskCacheWriteLocker */
final class c {
    private final Map<com.bumptech.glide.load.b, a> a = new HashMap();
    private final b b = new b();

    /* compiled from: DiskCacheWriteLocker */
    private static class a {
        final Lock a;
        int b;

        private a() {
            this.a = new ReentrantLock();
        }
    }

    /* compiled from: DiskCacheWriteLocker */
    private static class b {
        private final Queue<a> a;

        private b() {
            this.a = new ArrayDeque();
        }

        /* access modifiers changed from: 0000 */
        public a a() {
            a aVar;
            synchronized (this.a) {
                aVar = (a) this.a.poll();
            }
            return aVar == null ? new a() : aVar;
        }

        /* access modifiers changed from: 0000 */
        public void a(a aVar) {
            synchronized (this.a) {
                if (this.a.size() < 10) {
                    this.a.offer(aVar);
                }
            }
        }
    }

    c() {
    }

    /* access modifiers changed from: 0000 */
    public void a(com.bumptech.glide.load.b bVar) {
        a aVar;
        synchronized (this) {
            aVar = (a) this.a.get(bVar);
            if (aVar == null) {
                aVar = this.b.a();
                this.a.put(bVar, aVar);
            }
            aVar.b++;
        }
        aVar.a.lock();
    }

    /* access modifiers changed from: 0000 */
    public void b(com.bumptech.glide.load.b bVar) {
        a aVar;
        synchronized (this) {
            aVar = (a) this.a.get(bVar);
            if (aVar != null) {
                if (aVar.b > 0) {
                    int i = aVar.b - 1;
                    aVar.b = i;
                    if (i == 0) {
                        a aVar2 = (a) this.a.remove(bVar);
                        if (!aVar2.equals(aVar)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Removed the wrong lock, expected to remove: ");
                            sb.append(aVar);
                            sb.append(", but actually removed: ");
                            sb.append(aVar2);
                            sb.append(", key: ");
                            sb.append(bVar);
                            throw new IllegalStateException(sb.toString());
                        }
                        this.b.a(aVar2);
                    }
                }
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Cannot release a lock that is not held, key: ");
            sb2.append(bVar);
            sb2.append(", interestedThreads: ");
            sb2.append(aVar == null ? 0 : aVar.b);
            throw new IllegalArgumentException(sb2.toString());
        }
        aVar.a.unlock();
    }
}
