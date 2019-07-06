package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.a.C0189a;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: BehaviorSubject */
public final class a<T> extends c<T> {
    static final C0190a[] c = new C0190a[0];
    static final C0190a[] d = new C0190a[0];
    private static final Object[] j = new Object[0];
    final AtomicReference<Object> a = new AtomicReference<>();
    final AtomicReference<C0190a<T>[]> b = new AtomicReference<>(c);
    final ReadWriteLock e = new ReentrantReadWriteLock();
    final Lock f = this.e.readLock();
    final Lock g = this.e.writeLock();
    final AtomicReference<Throwable> h = new AtomicReference<>();
    long i;

    /* renamed from: io.reactivex.subjects.a$a reason: collision with other inner class name */
    /* compiled from: BehaviorSubject */
    static final class C0190a<T> implements Disposable, C0189a<Object> {
        final Observer<? super T> a;
        final a<T> b;
        boolean c;
        boolean d;
        io.reactivex.internal.util.a<Object> e;
        boolean f;
        volatile boolean g;
        long h;

        C0190a(Observer<? super T> observer, a<T> aVar) {
            this.a = observer;
            this.b = aVar;
        }

        public void dispose() {
            if (!this.g) {
                this.g = true;
                this.b.b(this);
            }
        }

        public boolean isDisposed() {
            return this.g;
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0031, code lost:
            if (r0 == null) goto L_0x003d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0037, code lost:
            if (test(r0) == false) goto L_0x003a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0039, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x003a, code lost:
            b();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x003d, code lost:
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
                boolean r0 = r4.g     // Catch:{ all -> 0x003e }
                if (r0 == 0) goto L_0x000c
                monitor-exit(r4)     // Catch:{ all -> 0x003e }
                return
            L_0x000c:
                boolean r0 = r4.c     // Catch:{ all -> 0x003e }
                if (r0 == 0) goto L_0x0012
                monitor-exit(r4)     // Catch:{ all -> 0x003e }
                return
            L_0x0012:
                io.reactivex.subjects.a<T> r0 = r4.b     // Catch:{ all -> 0x003e }
                java.util.concurrent.locks.Lock r1 = r0.f     // Catch:{ all -> 0x003e }
                r1.lock()     // Catch:{ all -> 0x003e }
                long r2 = r0.i     // Catch:{ all -> 0x003e }
                r4.h = r2     // Catch:{ all -> 0x003e }
                java.util.concurrent.atomic.AtomicReference<java.lang.Object> r0 = r0.a     // Catch:{ all -> 0x003e }
                java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x003e }
                r1.unlock()     // Catch:{ all -> 0x003e }
                r1 = 1
                if (r0 == 0) goto L_0x002b
                r2 = r1
                goto L_0x002c
            L_0x002b:
                r2 = 0
            L_0x002c:
                r4.d = r2     // Catch:{ all -> 0x003e }
                r4.c = r1     // Catch:{ all -> 0x003e }
                monitor-exit(r4)     // Catch:{ all -> 0x003e }
                if (r0 == 0) goto L_0x003d
                boolean r0 = r4.test(r0)
                if (r0 == 0) goto L_0x003a
                return
            L_0x003a:
                r4.b()
            L_0x003d:
                return
            L_0x003e:
                r0 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x003e }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subjects.a.C0190a.a():void");
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0031, code lost:
            r3.f = true;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(java.lang.Object r4, long r5) {
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
                io.reactivex.internal.util.a<java.lang.Object> r5 = r3.e     // Catch:{ all -> 0x0034 }
                if (r5 != 0) goto L_0x0028
                io.reactivex.internal.util.a r5 = new io.reactivex.internal.util.a     // Catch:{ all -> 0x0034 }
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
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subjects.a.C0190a.a(java.lang.Object, long):void");
        }

        public boolean test(Object obj) {
            return this.g || NotificationLite.accept(obj, this.a);
        }

        /* JADX INFO: used method not loaded: io.reactivex.internal.util.a.a(io.reactivex.internal.util.a$a):null, types can be incorrect */
        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0013, code lost:
            r0.a((io.reactivex.internal.util.a.C0189a) r2);
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
                io.reactivex.internal.util.a<java.lang.Object> r0 = r2.e     // Catch:{ all -> 0x0017 }
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
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subjects.a.C0190a.b():void");
        }
    }

    public static <T> a<T> a() {
        return new a<>();
    }

    a() {
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super T> observer) {
        C0190a aVar = new C0190a(observer, this);
        observer.onSubscribe(aVar);
        if (!a(aVar)) {
            Throwable th = (Throwable) this.h.get();
            if (th == ExceptionHelper.a) {
                observer.onComplete();
            } else {
                observer.onError(th);
            }
        } else if (aVar.g) {
            b(aVar);
        } else {
            aVar.a();
        }
    }

    public void onSubscribe(Disposable disposable) {
        if (this.h.get() != null) {
            disposable.dispose();
        }
    }

    public void onNext(T t) {
        if (t == null) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
        } else if (this.h.get() == null) {
            Object next = NotificationLite.next(t);
            b(next);
            for (C0190a a2 : (C0190a[]) this.b.get()) {
                a2.a(next, this.i);
            }
        }
    }

    public void onError(Throwable th) {
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        if (!this.h.compareAndSet(null, th)) {
            io.reactivex.d.a.a(th);
            return;
        }
        Object error = NotificationLite.error(th);
        for (C0190a a2 : a(error)) {
            a2.a(error, this.i);
        }
    }

    public void onComplete() {
        if (this.h.compareAndSet(null, ExceptionHelper.a)) {
            Object complete = NotificationLite.complete();
            for (C0190a a2 : a(complete)) {
                a2.a(complete, this.i);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(C0190a<T> aVar) {
        C0190a[] aVarArr;
        C0190a[] aVarArr2;
        do {
            aVarArr = (C0190a[]) this.b.get();
            if (aVarArr == d) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new C0190a[(length + 1)];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.b.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void b(C0190a<T> aVar) {
        C0190a<T>[] aVarArr;
        C0190a[] aVarArr2;
        do {
            aVarArr = (C0190a[]) this.b.get();
            if (aVarArr != d && aVarArr != c) {
                int length = aVarArr.length;
                int i2 = -1;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    } else if (aVarArr[i3] == aVar) {
                        i2 = i3;
                        break;
                    } else {
                        i3++;
                    }
                }
                if (i2 >= 0) {
                    if (length == 1) {
                        aVarArr2 = c;
                    } else {
                        C0190a[] aVarArr3 = new C0190a[(length - 1)];
                        System.arraycopy(aVarArr, 0, aVarArr3, 0, i2);
                        System.arraycopy(aVarArr, i2 + 1, aVarArr3, i2, (length - i2) - 1);
                        aVarArr2 = aVarArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.b.compareAndSet(aVarArr, aVarArr2));
    }

    /* access modifiers changed from: 0000 */
    public C0190a<T>[] a(Object obj) {
        C0190a<T>[] aVarArr = (C0190a[]) this.b.get();
        if (aVarArr != d) {
            aVarArr = (C0190a[]) this.b.getAndSet(d);
            if (aVarArr != d) {
                b(obj);
            }
        }
        return aVarArr;
    }

    /* access modifiers changed from: 0000 */
    public void b(Object obj) {
        this.g.lock();
        try {
            this.i++;
            this.a.lazySet(obj);
        } finally {
            this.g.unlock();
        }
    }
}
