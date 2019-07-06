package io.reactivex.internal.queue;

import io.reactivex.internal.a.f;
import io.reactivex.internal.util.h;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* compiled from: SpscLinkedArrayQueue */
public final class a<T> implements f<T> {
    static final int a = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    private static final Object j = new Object();
    final AtomicLong b = new AtomicLong();
    int c;
    long d;
    final int e;
    AtomicReferenceArray<Object> f;
    final int g;
    AtomicReferenceArray<Object> h;
    final AtomicLong i = new AtomicLong();

    private static int b(int i2) {
        return i2;
    }

    public a(int i2) {
        int a2 = h.a(Math.max(8, i2));
        int i3 = a2 - 1;
        AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<>(a2 + 1);
        this.f = atomicReferenceArray;
        this.e = i3;
        a(a2);
        this.h = atomicReferenceArray;
        this.g = i3;
        this.d = (long) (i3 - 1);
        a(0);
    }

    public boolean offer(T t) {
        if (t == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        AtomicReferenceArray<Object> atomicReferenceArray = this.f;
        long e2 = e();
        int i2 = this.e;
        int a2 = a(e2, i2);
        if (e2 < this.d) {
            return a(atomicReferenceArray, t, e2, a2);
        }
        long j2 = e2 + ((long) this.c);
        if (b(atomicReferenceArray, a(j2, i2)) == null) {
            this.d = j2 - 1;
            return a(atomicReferenceArray, t, e2, a2);
        } else if (b(atomicReferenceArray, a(e2 + 1, i2)) == null) {
            return a(atomicReferenceArray, t, e2, a2);
        } else {
            a(atomicReferenceArray, e2, a2, t, (long) i2);
            return true;
        }
    }

    private boolean a(AtomicReferenceArray<Object> atomicReferenceArray, T t, long j2, int i2) {
        a(atomicReferenceArray, i2, (Object) t);
        a(j2 + 1);
        return true;
    }

    private void a(AtomicReferenceArray<Object> atomicReferenceArray, long j2, int i2, T t, long j3) {
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.f = atomicReferenceArray2;
        this.d = (j2 + j3) - 1;
        a(atomicReferenceArray2, i2, (Object) t);
        a(atomicReferenceArray, atomicReferenceArray2);
        a(atomicReferenceArray, i2, j);
        a(j2 + 1);
    }

    private void a(AtomicReferenceArray<Object> atomicReferenceArray, AtomicReferenceArray<Object> atomicReferenceArray2) {
        a(atomicReferenceArray, b(atomicReferenceArray.length() - 1), (Object) atomicReferenceArray2);
    }

    private AtomicReferenceArray<Object> a(AtomicReferenceArray<Object> atomicReferenceArray, int i2) {
        int b2 = b(i2);
        AtomicReferenceArray<Object> atomicReferenceArray2 = (AtomicReferenceArray) b(atomicReferenceArray, b2);
        a(atomicReferenceArray, b2, (Object) null);
        return atomicReferenceArray2;
    }

    public T poll() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.h;
        long f2 = f();
        int i2 = this.g;
        int a2 = a(f2, i2);
        T b2 = b(atomicReferenceArray, a2);
        boolean z = b2 == j;
        if (b2 != null && !z) {
            a(atomicReferenceArray, a2, (Object) null);
            b(f2 + 1);
            return b2;
        } else if (z) {
            return a(a(atomicReferenceArray, i2 + 1), f2, i2);
        } else {
            return null;
        }
    }

    private T a(AtomicReferenceArray<Object> atomicReferenceArray, long j2, int i2) {
        this.h = atomicReferenceArray;
        int a2 = a(j2, i2);
        T b2 = b(atomicReferenceArray, a2);
        if (b2 != null) {
            a(atomicReferenceArray, a2, (Object) null);
            b(j2 + 1);
        }
        return b2;
    }

    public T a() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.h;
        long f2 = f();
        int i2 = this.g;
        T b2 = b(atomicReferenceArray, a(f2, i2));
        return b2 == j ? b(a(atomicReferenceArray, i2 + 1), f2, i2) : b2;
    }

    private T b(AtomicReferenceArray<Object> atomicReferenceArray, long j2, int i2) {
        this.h = atomicReferenceArray;
        return b(atomicReferenceArray, a(j2, i2));
    }

    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    public int b() {
        long d2 = d();
        while (true) {
            long c2 = c();
            long d3 = d();
            if (d2 == d3) {
                return (int) (c2 - d3);
            }
            d2 = d3;
        }
    }

    public boolean isEmpty() {
        return c() == d();
    }

    private void a(int i2) {
        this.c = Math.min(i2 / 4, a);
    }

    private long c() {
        return this.b.get();
    }

    private long d() {
        return this.i.get();
    }

    private long e() {
        return this.b.get();
    }

    private long f() {
        return this.i.get();
    }

    private void a(long j2) {
        this.b.lazySet(j2);
    }

    private void b(long j2) {
        this.i.lazySet(j2);
    }

    private static int a(long j2, int i2) {
        return b(((int) j2) & i2);
    }

    private static void a(AtomicReferenceArray<Object> atomicReferenceArray, int i2, Object obj) {
        atomicReferenceArray.lazySet(i2, obj);
    }

    private static <E> Object b(AtomicReferenceArray<Object> atomicReferenceArray, int i2) {
        return atomicReferenceArray.get(i2);
    }

    public boolean a(T t, T t2) {
        AtomicReferenceArray<Object> atomicReferenceArray = this.f;
        long c2 = c();
        int i2 = this.e;
        long j2 = c2 + 2;
        if (b(atomicReferenceArray, a(j2, i2)) == null) {
            int a2 = a(c2, i2);
            a(atomicReferenceArray, a2 + 1, (Object) t2);
            a(atomicReferenceArray, a2, (Object) t);
            a(j2);
        } else {
            AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
            this.f = atomicReferenceArray2;
            int a3 = a(c2, i2);
            a(atomicReferenceArray2, a3 + 1, (Object) t2);
            a(atomicReferenceArray2, a3, (Object) t);
            a(atomicReferenceArray, atomicReferenceArray2);
            a(atomicReferenceArray, a3, j);
            a(j2);
        }
        return true;
    }
}
