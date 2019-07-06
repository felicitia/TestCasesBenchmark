package com.google.android.gms.internal.ads;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

final class vq {
    private final ConcurrentHashMap<vr, List<Throwable>> a = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> b = new ReferenceQueue<>();

    vq() {
    }

    public final List<Throwable> a(Throwable th, boolean z) {
        while (true) {
            Reference poll = this.b.poll();
            if (poll != null) {
                this.a.remove(poll);
            } else {
                return (List) this.a.get(new vr(th, null));
            }
        }
    }
}
