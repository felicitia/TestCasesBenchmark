package com.jakewharton.rxrelay2;

import com.jakewharton.rxrelay2.a.C0156a;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: BehaviorRelay */
public final class b<T> extends c<T> {
    private static final Object[] d = new Object[0];
    private static final a[] f = new a[0];
    final AtomicReference<T> a;
    final Lock b;
    long c;
    private final AtomicReference<a<T>[]> e;
    private final Lock g;

    /* compiled from: BehaviorRelay */
    static final class a<T> implements C0156a<T>, Disposable {
        final Observer<? super T> a;
        final b<T> b;
        boolean c;
        boolean d;
        a<T> e;
        boolean f;
        volatile boolean g;
        long h;

        a(Observer<? super T> observer, b<T> bVar) {
            this.a = observer;
            this.b = bVar;
        }

        public void dispose() {
            if (!this.g) {
                this.g = true;
                this.b.a(this);
            }
        }

        public boolean isDisposed() {
            return this.g;
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0031, code lost:
            if (r0 == null) goto L_0x0039;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0033, code lost:
            test(r0);
            b();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a() {
            /*
                r4 = this;
                boolean r0 = r4.g
                if (r0 == 0) goto L_0x0005
                return
            L_0x0005:
                monitor-enter(r4)
                boolean r0 = r4.g     // Catch:{ all -> 0x003a }
                if (r0 == 0) goto L_0x000c
                monitor-exit(r4)     // Catch:{ all -> 0x003a }
                return
            L_0x000c:
                boolean r0 = r4.c     // Catch:{ all -> 0x003a }
                if (r0 == 0) goto L_0x0012
                monitor-exit(r4)     // Catch:{ all -> 0x003a }
                return
            L_0x0012:
                com.jakewharton.rxrelay2.b<T> r0 = r4.b     // Catch:{ all -> 0x003a }
                java.util.concurrent.locks.Lock r1 = r0.b     // Catch:{ all -> 0x003a }
                r1.lock()     // Catch:{ all -> 0x003a }
                long r2 = r0.c     // Catch:{ all -> 0x003a }
                r4.h = r2     // Catch:{ all -> 0x003a }
                java.util.concurrent.atomic.AtomicReference<T> r0 = r0.a     // Catch:{ all -> 0x003a }
                java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x003a }
                r1.unlock()     // Catch:{ all -> 0x003a }
                r1 = 1
                if (r0 == 0) goto L_0x002b
                r2 = r1
                goto L_0x002c
            L_0x002b:
                r2 = 0
            L_0x002c:
                r4.d = r2     // Catch:{ all -> 0x003a }
                r4.c = r1     // Catch:{ all -> 0x003a }
                monitor-exit(r4)     // Catch:{ all -> 0x003a }
                if (r0 == 0) goto L_0x0039
                r4.test(r0)
                r4.b()
            L_0x0039:
                return
            L_0x003a:
                r0 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x003a }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jakewharton.rxrelay2.b.a.a():void");
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0031, code lost:
            r3.f = true;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(T r4, long r5) {
            /*
                r3 = this;
                boolean r0 = r3.g
                if (r0 == 0) goto L_0x0005
                return
            L_0x0005:
                boolean r0 = r3.f
                if (r0 != 0) goto L_0x0037
                monitor-enter(r3)
                boolean r0 = r3.g     // Catch:{ all -> 0x0034 }
                if (r0 == 0) goto L_0x0010
                monitor-exit(r3)     // Catch:{ all -> 0x0034 }
                return
            L_0x0010:
                long r0 = r3.h     // Catch:{ all -> 0x0034 }
                int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
                if (r2 != 0) goto L_0x0018
                monitor-exit(r3)     // Catch:{ all -> 0x0034 }
                return
            L_0x0018:
                boolean r5 = r3.d     // Catch:{ all -> 0x0034 }
                if (r5 == 0) goto L_0x002d
                com.jakewharton.rxrelay2.a<T> r5 = r3.e     // Catch:{ all -> 0x0034 }
                if (r5 != 0) goto L_0x0028
                com.jakewharton.rxrelay2.a r5 = new com.jakewharton.rxrelay2.a     // Catch:{ all -> 0x0034 }
                r6 = 4
                r5.<init>(r6)     // Catch:{ all -> 0x0034 }
                r3.e = r5     // Catch:{ all -> 0x0034 }
            L_0x0028:
                r5.a(r4)     // Catch:{ all -> 0x0034 }
                monitor-exit(r3)     // Catch:{ all -> 0x0034 }
                return
            L_0x002d:
                r5 = 1
                r3.c = r5     // Catch:{ all -> 0x0034 }
                monitor-exit(r3)     // Catch:{ all -> 0x0034 }
                r3.f = r5
                goto L_0x0037
            L_0x0034:
                r4 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x0034 }
                throw r4
            L_0x0037:
                r3.test(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jakewharton.rxrelay2.b.a.a(java.lang.Object, long):void");
        }

        public boolean test(T t) {
            if (!this.g) {
                this.a.onNext(t);
            }
            return false;
        }

        /* JADX INFO: used method not loaded: com.jakewharton.rxrelay2.a.a(com.jakewharton.rxrelay2.a$a):null, types can be incorrect */
        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0013, code lost:
            r0.a((com.jakewharton.rxrelay2.a.C0156a) r2);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void b() {
            /*
                r2 = this;
            L_0x0000:
                boolean r0 = r2.g
                if (r0 == 0) goto L_0x0005
                return
            L_0x0005:
                monitor-enter(r2)
                com.jakewharton.rxrelay2.a<T> r0 = r2.e     // Catch:{ all -> 0x0017 }
                if (r0 != 0) goto L_0x000f
                r0 = 0
                r2.d = r0     // Catch:{ all -> 0x0017 }
                monitor-exit(r2)     // Catch:{ all -> 0x0017 }
                return
            L_0x000f:
                r1 = 0
                r2.e = r1     // Catch:{ all -> 0x0017 }
                monitor-exit(r2)     // Catch:{ all -> 0x0017 }
                r0.a(r2)
                goto L_0x0000
            L_0x0017:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0017 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jakewharton.rxrelay2.b.a.b():void");
        }
    }

    public static <T> b<T> a(T t) {
        return new b<>(t);
    }

    private b() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.b = reentrantReadWriteLock.readLock();
        this.g = reentrantReadWriteLock.writeLock();
        this.e = new AtomicReference<>(f);
        this.a = new AtomicReference<>();
    }

    private b(T t) {
        this();
        if (t == null) {
            throw new NullPointerException("defaultValue == null");
        }
        this.a.lazySet(t);
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super T> observer) {
        a aVar = new a(observer, this);
        observer.onSubscribe(aVar);
        b(aVar);
        if (aVar.g) {
            a(aVar);
        } else {
            aVar.a();
        }
    }

    public void accept(T t) {
        if (t == null) {
            throw new NullPointerException("value == null");
        }
        b(t);
        for (a a2 : (a[]) this.e.get()) {
            a2.a(t, this.c);
        }
    }

    private void b(a<T> aVar) {
        a[] aVarArr;
        a[] aVarArr2;
        do {
            aVarArr = (a[]) this.e.get();
            int length = aVarArr.length;
            aVarArr2 = new a[(length + 1)];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.e.compareAndSet(aVarArr, aVarArr2));
    }

    /* access modifiers changed from: 0000 */
    public void a(a<T> aVar) {
        a<T>[] aVarArr;
        a[] aVarArr2;
        do {
            aVarArr = (a[]) this.e.get();
            if (aVarArr != f) {
                int length = aVarArr.length;
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (aVarArr[i2] == aVar) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        aVarArr2 = f;
                    } else {
                        a[] aVarArr3 = new a[(length - 1)];
                        System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                        System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                        aVarArr2 = aVarArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.e.compareAndSet(aVarArr, aVarArr2));
    }

    private void b(T t) {
        this.g.lock();
        try {
            this.c++;
            this.a.lazySet(t);
        } finally {
            this.g.unlock();
        }
    }
}
