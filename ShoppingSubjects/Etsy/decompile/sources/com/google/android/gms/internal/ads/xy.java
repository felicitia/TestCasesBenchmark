package com.google.android.gms.internal.ads;

import java.util.List;

final class xy extends xv {
    private xy() {
        super();
    }

    private static <E> xm<E> c(Object obj, long j) {
        return (xm) aab.f(obj, j);
    }

    /* access modifiers changed from: 0000 */
    public final <L> List<L> a(Object obj, long j) {
        xm c = c(obj, j);
        if (c.a()) {
            return c;
        }
        int size = c.size();
        xm a = c.a(size == 0 ? 10 : size << 1);
        aab.a(obj, j, (Object) a);
        return a;
    }

    /* access modifiers changed from: 0000 */
    public final <E> void a(Object obj, Object obj2, long j) {
        xm c = c(obj, j);
        xm c2 = c(obj2, j);
        int size = c.size();
        int size2 = c2.size();
        if (size > 0 && size2 > 0) {
            if (!c.a()) {
                c = c.a(size2 + size);
            }
            c.addAll(c2);
        }
        if (size > 0) {
            c2 = c;
        }
        aab.a(obj, j, (Object) c2);
    }

    /* access modifiers changed from: 0000 */
    public final void b(Object obj, long j) {
        c(obj, j).b();
    }
}
