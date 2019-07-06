package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.t;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSwitchMap<T, R> extends a<T, R> {
    final g<? super T, ? extends t<? extends R>> b;
    final int c;
    final boolean d;

    static final class SwitchMapInnerObserver<T, R> extends AtomicReference<Disposable> implements Observer<R> {
        private static final long serialVersionUID = 3837284832786408377L;
        volatile boolean done;
        final long index;
        final SwitchMapObserver<T, R> parent;
        final a<R> queue;

        SwitchMapInnerObserver(SwitchMapObserver<T, R> switchMapObserver, long j, int i) {
            this.parent = switchMapObserver;
            this.index = j;
            this.queue = new a<>(i);
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        public void onNext(R r) {
            if (this.index == this.parent.unique) {
                this.queue.offer(r);
                this.parent.drain();
            }
        }

        public void onError(Throwable th) {
            this.parent.innerError(this, th);
        }

        public void onComplete() {
            if (this.index == this.parent.unique) {
                this.done = true;
                this.parent.drain();
            }
        }

        public void cancel() {
            DisposableHelper.dispose(this);
        }
    }

    static final class SwitchMapObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        static final SwitchMapInnerObserver<Object, Object> CANCELLED = new SwitchMapInnerObserver<>(null, -1, 1);
        private static final long serialVersionUID = -3491074160481096299L;
        final AtomicReference<SwitchMapInnerObserver<T, R>> active = new AtomicReference<>();
        final Observer<? super R> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final AtomicThrowable errors;
        final g<? super T, ? extends t<? extends R>> mapper;
        Disposable s;
        volatile long unique;

        static {
            CANCELLED.cancel();
        }

        SwitchMapObserver(Observer<? super R> observer, g<? super T, ? extends t<? extends R>> gVar, int i, boolean z) {
            this.actual = observer;
            this.mapper = gVar;
            this.bufferSize = i;
            this.delayErrors = z;
            this.errors = new AtomicThrowable();
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            long j = this.unique + 1;
            this.unique = j;
            SwitchMapInnerObserver switchMapInnerObserver = (SwitchMapInnerObserver) this.active.get();
            if (switchMapInnerObserver != null) {
                switchMapInnerObserver.cancel();
            }
            try {
                t tVar = (t) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The ObservableSource returned is null");
                SwitchMapInnerObserver switchMapInnerObserver2 = new SwitchMapInnerObserver(this, j, this.bufferSize);
                while (true) {
                    SwitchMapInnerObserver<Object, Object> switchMapInnerObserver3 = (SwitchMapInnerObserver) this.active.get();
                    if (switchMapInnerObserver3 != CANCELLED) {
                        if (this.active.compareAndSet(switchMapInnerObserver3, switchMapInnerObserver2)) {
                            tVar.subscribe(switchMapInnerObserver2);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.s.dispose();
                onError(th);
            }
        }

        public void onError(Throwable th) {
            if (this.done || !this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    disposeInner();
                }
                io.reactivex.d.a.a(th);
                return;
            }
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.s.dispose();
                disposeInner();
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: 0000 */
        public void disposeInner() {
            if (((SwitchMapInnerObserver) this.active.get()) != CANCELLED) {
                SwitchMapInnerObserver<Object, Object> switchMapInnerObserver = (SwitchMapInnerObserver) this.active.getAndSet(CANCELLED);
                if (switchMapInnerObserver != CANCELLED && switchMapInnerObserver != null) {
                    switchMapInnerObserver.cancel();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Removed duplicated region for block: B:78:0x000b A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drain() {
            /*
                r10 = this;
                int r0 = r10.getAndIncrement()
                if (r0 == 0) goto L_0x0007
                return
            L_0x0007:
                io.reactivex.Observer<? super R> r0 = r10.actual
                r1 = 1
                r2 = r1
            L_0x000b:
                boolean r3 = r10.cancelled
                if (r3 == 0) goto L_0x0010
                return
            L_0x0010:
                boolean r3 = r10.done
                r4 = 0
                if (r3 == 0) goto L_0x0052
                java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservableSwitchMap$SwitchMapInnerObserver<T, R>> r3 = r10.active
                java.lang.Object r3 = r3.get()
                if (r3 != 0) goto L_0x001f
                r3 = r1
                goto L_0x0020
            L_0x001f:
                r3 = r4
            L_0x0020:
                boolean r5 = r10.delayErrors
                if (r5 == 0) goto L_0x0038
                if (r3 == 0) goto L_0x0052
                io.reactivex.internal.util.AtomicThrowable r1 = r10.errors
                java.lang.Object r1 = r1.get()
                java.lang.Throwable r1 = (java.lang.Throwable) r1
                if (r1 == 0) goto L_0x0034
                r0.onError(r1)
                goto L_0x0037
            L_0x0034:
                r0.onComplete()
            L_0x0037:
                return
            L_0x0038:
                io.reactivex.internal.util.AtomicThrowable r5 = r10.errors
                java.lang.Object r5 = r5.get()
                java.lang.Throwable r5 = (java.lang.Throwable) r5
                if (r5 == 0) goto L_0x004c
                io.reactivex.internal.util.AtomicThrowable r1 = r10.errors
                java.lang.Throwable r1 = r1.terminate()
                r0.onError(r1)
                return
            L_0x004c:
                if (r3 == 0) goto L_0x0052
                r0.onComplete()
                return
            L_0x0052:
                java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservableSwitchMap$SwitchMapInnerObserver<T, R>> r3 = r10.active
                java.lang.Object r3 = r3.get()
                io.reactivex.internal.operators.observable.ObservableSwitchMap$SwitchMapInnerObserver r3 = (io.reactivex.internal.operators.observable.ObservableSwitchMap.SwitchMapInnerObserver) r3
                if (r3 == 0) goto L_0x00d6
                io.reactivex.internal.queue.a<R> r5 = r3.queue
                boolean r6 = r3.done
                r7 = 0
                if (r6 == 0) goto L_0x0090
                boolean r6 = r5.isEmpty()
                boolean r8 = r10.delayErrors
                if (r8 == 0) goto L_0x0073
                if (r6 == 0) goto L_0x0090
                java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservableSwitchMap$SwitchMapInnerObserver<T, R>> r4 = r10.active
                r4.compareAndSet(r3, r7)
                goto L_0x000b
            L_0x0073:
                io.reactivex.internal.util.AtomicThrowable r8 = r10.errors
                java.lang.Object r8 = r8.get()
                java.lang.Throwable r8 = (java.lang.Throwable) r8
                if (r8 == 0) goto L_0x0087
                io.reactivex.internal.util.AtomicThrowable r1 = r10.errors
                java.lang.Throwable r1 = r1.terminate()
                r0.onError(r1)
                return
            L_0x0087:
                if (r6 == 0) goto L_0x0090
                java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservableSwitchMap$SwitchMapInnerObserver<T, R>> r4 = r10.active
                r4.compareAndSet(r3, r7)
                goto L_0x000b
            L_0x0090:
                boolean r6 = r10.cancelled
                if (r6 == 0) goto L_0x0095
                return
            L_0x0095:
                java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservableSwitchMap$SwitchMapInnerObserver<T, R>> r6 = r10.active
                java.lang.Object r6 = r6.get()
                if (r3 == r6) goto L_0x009f
            L_0x009d:
                r4 = r1
                goto L_0x00ce
            L_0x009f:
                boolean r6 = r10.delayErrors
                if (r6 != 0) goto L_0x00b7
                io.reactivex.internal.util.AtomicThrowable r6 = r10.errors
                java.lang.Object r6 = r6.get()
                java.lang.Throwable r6 = (java.lang.Throwable) r6
                if (r6 == 0) goto L_0x00b7
                io.reactivex.internal.util.AtomicThrowable r1 = r10.errors
                java.lang.Throwable r1 = r1.terminate()
                r0.onError(r1)
                return
            L_0x00b7:
                boolean r6 = r3.done
                java.lang.Object r8 = r5.poll()
                if (r8 != 0) goto L_0x00c1
                r9 = r1
                goto L_0x00c2
            L_0x00c1:
                r9 = r4
            L_0x00c2:
                if (r6 == 0) goto L_0x00cc
                if (r9 == 0) goto L_0x00cc
                java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservableSwitchMap$SwitchMapInnerObserver<T, R>> r4 = r10.active
                r4.compareAndSet(r3, r7)
                goto L_0x009d
            L_0x00cc:
                if (r9 == 0) goto L_0x00d2
            L_0x00ce:
                if (r4 == 0) goto L_0x00d6
                goto L_0x000b
            L_0x00d2:
                r0.onNext(r8)
                goto L_0x0090
            L_0x00d6:
                int r2 = -r2
                int r2 = r10.addAndGet(r2)
                if (r2 != 0) goto L_0x000b
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableSwitchMap.SwitchMapObserver.drain():void");
        }

        /* access modifiers changed from: 0000 */
        public void innerError(SwitchMapInnerObserver<T, R> switchMapInnerObserver, Throwable th) {
            if (switchMapInnerObserver.index != this.unique || !this.errors.addThrowable(th)) {
                io.reactivex.d.a.a(th);
                return;
            }
            if (!this.delayErrors) {
                this.s.dispose();
            }
            switchMapInnerObserver.done = true;
            drain();
        }
    }

    public void a(Observer<? super R> observer) {
        if (!ObservableScalarXMap.a(this.a, observer, this.b)) {
            this.a.subscribe(new SwitchMapObserver(observer, this.b, this.c, this.d));
        }
    }
}
