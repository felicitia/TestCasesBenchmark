package io.reactivex.processors;

import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.a;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

/* compiled from: SerializedProcessor */
final class b<T> extends a<T> {
    final a<T> b;
    boolean c;
    a<Object> d;
    volatile boolean e;

    b(a<T> aVar) {
        this.b = aVar;
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        this.b.subscribe(cVar);
    }

    public void onSubscribe(Subscription subscription) {
        boolean z = true;
        if (!this.e) {
            synchronized (this) {
                if (!this.e) {
                    if (this.c) {
                        a<Object> aVar = this.d;
                        if (aVar == null) {
                            aVar = new a<>(4);
                            this.d = aVar;
                        }
                        aVar.a(NotificationLite.subscription(subscription));
                        return;
                    }
                    this.c = true;
                    z = false;
                }
            }
        }
        if (z) {
            subscription.cancel();
        } else {
            this.b.onSubscribe(subscription);
            f();
        }
    }

    public void onNext(T t) {
        if (!this.e) {
            synchronized (this) {
                if (!this.e) {
                    if (this.c) {
                        a<Object> aVar = this.d;
                        if (aVar == null) {
                            aVar = new a<>(4);
                            this.d = aVar;
                        }
                        aVar.a(NotificationLite.next(t));
                        return;
                    }
                    this.c = true;
                    this.b.onNext(t);
                    f();
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002f, code lost:
        if (r0 == false) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0031, code lost:
        io.reactivex.d.a.a(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0034, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0035, code lost:
        r2.b.onError(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onError(java.lang.Throwable r3) {
        /*
            r2 = this;
            boolean r0 = r2.e
            if (r0 == 0) goto L_0x0008
            io.reactivex.d.a.a(r3)
            return
        L_0x0008:
            monitor-enter(r2)
            boolean r0 = r2.e     // Catch:{ all -> 0x003b }
            r1 = 1
            if (r0 == 0) goto L_0x0010
            r0 = r1
            goto L_0x002e
        L_0x0010:
            r2.e = r1     // Catch:{ all -> 0x003b }
            boolean r0 = r2.c     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x002b
            io.reactivex.internal.util.a<java.lang.Object> r0 = r2.d     // Catch:{ all -> 0x003b }
            if (r0 != 0) goto L_0x0022
            io.reactivex.internal.util.a r0 = new io.reactivex.internal.util.a     // Catch:{ all -> 0x003b }
            r1 = 4
            r0.<init>(r1)     // Catch:{ all -> 0x003b }
            r2.d = r0     // Catch:{ all -> 0x003b }
        L_0x0022:
            java.lang.Object r3 = io.reactivex.internal.util.NotificationLite.error(r3)     // Catch:{ all -> 0x003b }
            r0.b(r3)     // Catch:{ all -> 0x003b }
            monitor-exit(r2)     // Catch:{ all -> 0x003b }
            return
        L_0x002b:
            r0 = 0
            r2.c = r1     // Catch:{ all -> 0x003b }
        L_0x002e:
            monitor-exit(r2)     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x0035
            io.reactivex.d.a.a(r3)
            return
        L_0x0035:
            io.reactivex.processors.a<T> r0 = r2.b
            r0.onError(r3)
            return
        L_0x003b:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x003b }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.processors.b.onError(java.lang.Throwable):void");
    }

    public void onComplete() {
        if (!this.e) {
            synchronized (this) {
                if (!this.e) {
                    this.e = true;
                    if (this.c) {
                        a<Object> aVar = this.d;
                        if (aVar == null) {
                            aVar = new a<>(4);
                            this.d = aVar;
                        }
                        aVar.a(NotificationLite.complete());
                        return;
                    }
                    this.c = true;
                    this.b.onComplete();
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void f() {
        a<Object> aVar;
        while (true) {
            synchronized (this) {
                aVar = this.d;
                if (aVar == null) {
                    this.c = false;
                    return;
                }
                this.d = null;
            }
            aVar.a((c<? super U>) this.b);
        }
        while (true) {
        }
    }
}
