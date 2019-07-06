package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.q;
import io.reactivex.subjects.UnicastSubject;
import io.reactivex.t;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableWindow<T> extends a<T, q<T>> {
    final long b;
    final long c;
    final int d;

    static final class WindowExactObserver<T> extends AtomicInteger implements Observer<T>, Disposable, Runnable {
        private static final long serialVersionUID = -7481782523886138128L;
        final Observer<? super q<T>> actual;
        volatile boolean cancelled;
        final int capacityHint;
        final long count;
        Disposable s;
        long size;
        UnicastSubject<T> window;

        WindowExactObserver(Observer<? super q<T>> observer, long j, int i) {
            this.actual = observer;
            this.count = j;
            this.capacityHint = i;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            UnicastSubject<T> unicastSubject = this.window;
            if (unicastSubject == null && !this.cancelled) {
                unicastSubject = UnicastSubject.a(this.capacityHint, (Runnable) this);
                this.window = unicastSubject;
                this.actual.onNext(unicastSubject);
            }
            if (unicastSubject != null) {
                unicastSubject.onNext(t);
                long j = this.size + 1;
                this.size = j;
                if (j >= this.count) {
                    this.size = 0;
                    this.window = null;
                    unicastSubject.onComplete();
                    if (this.cancelled) {
                        this.s.dispose();
                    }
                }
            }
        }

        public void onError(Throwable th) {
            UnicastSubject<T> unicastSubject = this.window;
            if (unicastSubject != null) {
                this.window = null;
                unicastSubject.onError(th);
            }
            this.actual.onError(th);
        }

        public void onComplete() {
            UnicastSubject<T> unicastSubject = this.window;
            if (unicastSubject != null) {
                this.window = null;
                unicastSubject.onComplete();
            }
            this.actual.onComplete();
        }

        public void dispose() {
            this.cancelled = true;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void run() {
            if (this.cancelled) {
                this.s.dispose();
            }
        }
    }

    static final class WindowSkipObserver<T> extends AtomicBoolean implements Observer<T>, Disposable, Runnable {
        private static final long serialVersionUID = 3366976432059579510L;
        final Observer<? super q<T>> actual;
        volatile boolean cancelled;
        final int capacityHint;
        final long count;
        long firstEmission;
        long index;
        Disposable s;
        final long skip;
        final ArrayDeque<UnicastSubject<T>> windows;
        final AtomicInteger wip = new AtomicInteger();

        WindowSkipObserver(Observer<? super q<T>> observer, long j, long j2, int i) {
            this.actual = observer;
            this.count = j;
            this.skip = j2;
            this.capacityHint = i;
            this.windows = new ArrayDeque<>();
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            ArrayDeque<UnicastSubject<T>> arrayDeque = this.windows;
            long j = this.index;
            long j2 = this.skip;
            if (j % j2 == 0 && !this.cancelled) {
                this.wip.getAndIncrement();
                UnicastSubject a = UnicastSubject.a(this.capacityHint, (Runnable) this);
                arrayDeque.offer(a);
                this.actual.onNext(a);
            }
            long j3 = this.firstEmission + 1;
            Iterator it = arrayDeque.iterator();
            while (it.hasNext()) {
                ((UnicastSubject) it.next()).onNext(t);
            }
            if (j3 >= this.count) {
                ((UnicastSubject) arrayDeque.poll()).onComplete();
                if (!arrayDeque.isEmpty() || !this.cancelled) {
                    this.firstEmission = j3 - j2;
                } else {
                    this.s.dispose();
                    return;
                }
            } else {
                this.firstEmission = j3;
            }
            this.index = j + 1;
        }

        public void onError(Throwable th) {
            ArrayDeque<UnicastSubject<T>> arrayDeque = this.windows;
            while (!arrayDeque.isEmpty()) {
                ((UnicastSubject) arrayDeque.poll()).onError(th);
            }
            this.actual.onError(th);
        }

        public void onComplete() {
            ArrayDeque<UnicastSubject<T>> arrayDeque = this.windows;
            while (!arrayDeque.isEmpty()) {
                ((UnicastSubject) arrayDeque.poll()).onComplete();
            }
            this.actual.onComplete();
        }

        public void dispose() {
            this.cancelled = true;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void run() {
            if (this.wip.decrementAndGet() == 0 && this.cancelled) {
                this.s.dispose();
            }
        }
    }

    public void a(Observer<? super q<T>> observer) {
        if (this.b == this.c) {
            this.a.subscribe(new WindowExactObserver(observer, this.b, this.d));
            return;
        }
        t tVar = this.a;
        WindowSkipObserver windowSkipObserver = new WindowSkipObserver(observer, this.b, this.c, this.d);
        tVar.subscribe(windowSkipObserver);
    }
}
