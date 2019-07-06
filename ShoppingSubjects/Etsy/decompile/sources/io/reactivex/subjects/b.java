package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.a;
import io.reactivex.internal.util.a.C0189a;

/* compiled from: SerializedSubject */
final class b<T> extends c<T> implements C0189a<Object> {
    final c<T> a;
    boolean b;
    a<Object> c;
    volatile boolean d;

    b(c<T> cVar) {
        this.a = cVar;
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super T> observer) {
        this.a.subscribe(observer);
    }

    public void onSubscribe(Disposable disposable) {
        boolean z = true;
        if (!this.d) {
            synchronized (this) {
                if (!this.d) {
                    if (this.b) {
                        a<Object> aVar = this.c;
                        if (aVar == null) {
                            aVar = new a<>(4);
                            this.c = aVar;
                        }
                        aVar.a(NotificationLite.disposable(disposable));
                        return;
                    }
                    this.b = true;
                    z = false;
                }
            }
        }
        if (z) {
            disposable.dispose();
        } else {
            this.a.onSubscribe(disposable);
            a();
        }
    }

    public void onNext(T t) {
        if (!this.d) {
            synchronized (this) {
                if (!this.d) {
                    if (this.b) {
                        a<Object> aVar = this.c;
                        if (aVar == null) {
                            aVar = new a<>(4);
                            this.c = aVar;
                        }
                        aVar.a(NotificationLite.next(t));
                        return;
                    }
                    this.b = true;
                    this.a.onNext(t);
                    a();
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
        r2.a.onError(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onError(java.lang.Throwable r3) {
        /*
            r2 = this;
            boolean r0 = r2.d
            if (r0 == 0) goto L_0x0008
            io.reactivex.d.a.a(r3)
            return
        L_0x0008:
            monitor-enter(r2)
            boolean r0 = r2.d     // Catch:{ all -> 0x003b }
            r1 = 1
            if (r0 == 0) goto L_0x0010
            r0 = r1
            goto L_0x002e
        L_0x0010:
            r2.d = r1     // Catch:{ all -> 0x003b }
            boolean r0 = r2.b     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x002b
            io.reactivex.internal.util.a<java.lang.Object> r0 = r2.c     // Catch:{ all -> 0x003b }
            if (r0 != 0) goto L_0x0022
            io.reactivex.internal.util.a r0 = new io.reactivex.internal.util.a     // Catch:{ all -> 0x003b }
            r1 = 4
            r0.<init>(r1)     // Catch:{ all -> 0x003b }
            r2.c = r0     // Catch:{ all -> 0x003b }
        L_0x0022:
            java.lang.Object r3 = io.reactivex.internal.util.NotificationLite.error(r3)     // Catch:{ all -> 0x003b }
            r0.b(r3)     // Catch:{ all -> 0x003b }
            monitor-exit(r2)     // Catch:{ all -> 0x003b }
            return
        L_0x002b:
            r0 = 0
            r2.b = r1     // Catch:{ all -> 0x003b }
        L_0x002e:
            monitor-exit(r2)     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x0035
            io.reactivex.d.a.a(r3)
            return
        L_0x0035:
            io.reactivex.subjects.c<T> r0 = r2.a
            r0.onError(r3)
            return
        L_0x003b:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x003b }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subjects.b.onError(java.lang.Throwable):void");
    }

    public void onComplete() {
        if (!this.d) {
            synchronized (this) {
                if (!this.d) {
                    this.d = true;
                    if (this.b) {
                        a<Object> aVar = this.c;
                        if (aVar == null) {
                            aVar = new a<>(4);
                            this.c = aVar;
                        }
                        aVar.a(NotificationLite.complete());
                        return;
                    }
                    this.b = true;
                    this.a.onComplete();
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        a<Object> aVar;
        while (true) {
            synchronized (this) {
                aVar = this.c;
                if (aVar == null) {
                    this.b = false;
                    return;
                }
                this.c = null;
            }
            aVar.a((C0189a<? super T>) this);
        }
        while (true) {
        }
    }

    public boolean test(Object obj) {
        return NotificationLite.acceptFull(obj, (Observer<? super T>) this.a);
    }
}
