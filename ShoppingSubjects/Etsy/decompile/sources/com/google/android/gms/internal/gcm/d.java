package com.google.android.gms.internal.gcm;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

final class d {
    private final ConcurrentHashMap<e, List<Throwable>> a = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> b = new ReferenceQueue<>();

    d() {
    }

    public final List<Throwable> a(Throwable th, boolean z) {
        while (true) {
            Reference poll = this.b.poll();
            if (poll == null) {
                break;
            }
            this.a.remove(poll);
        }
        List<Throwable> list = (List) this.a.get(new e(th, null));
        if (list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List<Throwable> list2 = (List) this.a.putIfAbsent(new e(th, this.b), vector);
        return list2 == null ? vector : list2;
    }
}
