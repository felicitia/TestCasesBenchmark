package io.reactivex.subscribers;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.a;
import io.reactivex.j;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

/* compiled from: SerializedSubscriber */
public final class b<T> implements j<T>, Subscription {
    final c<? super T> a;
    final boolean b;
    Subscription c;
    boolean d;
    a<Object> e;
    volatile boolean f;

    public b(c<? super T> cVar) {
        this(cVar, false);
    }

    public b(c<? super T> cVar, boolean z) {
        this.a = cVar;
        this.b = z;
    }

    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.c, subscription)) {
            this.c = subscription;
            this.a.onSubscribe(this);
        }
    }

    public void onNext(T t) {
        if (!this.f) {
            if (t == null) {
                this.c.cancel();
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            synchronized (this) {
                if (!this.f) {
                    if (this.d) {
                        a<Object> aVar = this.e;
                        if (aVar == null) {
                            aVar = new a<>(4);
                            this.e = aVar;
                        }
                        aVar.a(NotificationLite.next(t));
                        return;
                    }
                    this.d = true;
                    this.a.onNext(t);
                    a();
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0031, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0038, code lost:
        if (r1 == false) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003a, code lost:
        io.reactivex.d.a.a(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003e, code lost:
        r2.a.onError(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0043, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onError(java.lang.Throwable r3) {
        /*
            r2 = this;
            boolean r0 = r2.f
            if (r0 == 0) goto L_0x0008
            io.reactivex.d.a.a(r3)
            return
        L_0x0008:
            monitor-enter(r2)
            boolean r0 = r2.f     // Catch:{ all -> 0x0044 }
            r1 = 1
            if (r0 == 0) goto L_0x000f
            goto L_0x0037
        L_0x000f:
            boolean r0 = r2.d     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0032
            r2.f = r1     // Catch:{ all -> 0x0044 }
            io.reactivex.internal.util.a<java.lang.Object> r0 = r2.e     // Catch:{ all -> 0x0044 }
            if (r0 != 0) goto L_0x0021
            io.reactivex.internal.util.a r0 = new io.reactivex.internal.util.a     // Catch:{ all -> 0x0044 }
            r1 = 4
            r0.<init>(r1)     // Catch:{ all -> 0x0044 }
            r2.e = r0     // Catch:{ all -> 0x0044 }
        L_0x0021:
            java.lang.Object r3 = io.reactivex.internal.util.NotificationLite.error(r3)     // Catch:{ all -> 0x0044 }
            boolean r1 = r2.b     // Catch:{ all -> 0x0044 }
            if (r1 == 0) goto L_0x002d
            r0.a(r3)     // Catch:{ all -> 0x0044 }
            goto L_0x0030
        L_0x002d:
            r0.b(r3)     // Catch:{ all -> 0x0044 }
        L_0x0030:
            monitor-exit(r2)     // Catch:{ all -> 0x0044 }
            return
        L_0x0032:
            r2.f = r1     // Catch:{ all -> 0x0044 }
            r2.d = r1     // Catch:{ all -> 0x0044 }
            r1 = 0
        L_0x0037:
            monitor-exit(r2)     // Catch:{ all -> 0x0044 }
            if (r1 == 0) goto L_0x003e
            io.reactivex.d.a.a(r3)
            return
        L_0x003e:
            org.reactivestreams.c<? super T> r0 = r2.a
            r0.onError(r3)
            return
        L_0x0044:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0044 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subscribers.b.onError(java.lang.Throwable):void");
    }

    public void onComplete() {
        if (!this.f) {
            synchronized (this) {
                if (!this.f) {
                    if (this.d) {
                        a<Object> aVar = this.e;
                        if (aVar == null) {
                            aVar = new a<>(4);
                            this.e = aVar;
                        }
                        aVar.a(NotificationLite.complete());
                        return;
                    }
                    this.f = true;
                    this.d = true;
                    this.a.onComplete();
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        a<Object> aVar;
        do {
            synchronized (this) {
                aVar = this.e;
                if (aVar == null) {
                    this.d = false;
                    return;
                }
                this.e = null;
            }
        } while (!aVar.a(this.a));
    }

    public void request(long j) {
        this.c.request(j);
    }

    public void cancel() {
        this.c.cancel();
    }
}
