package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.c.b;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.t;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableGroupBy<T, K, V> extends a<T, b<K, V>> {
    final g<? super T, ? extends K> b;
    final g<? super T, ? extends V> c;
    final int d;
    final boolean e;

    public static final class GroupByObserver<T, K, V> extends AtomicInteger implements Observer<T>, Disposable {
        static final Object NULL_KEY = new Object();
        private static final long serialVersionUID = -3688291656102519502L;
        final Observer<? super b<K, V>> actual;
        final int bufferSize;
        final AtomicBoolean cancelled = new AtomicBoolean();
        final boolean delayError;
        final Map<Object, a<K, V>> groups;
        final g<? super T, ? extends K> keySelector;
        Disposable s;
        final g<? super T, ? extends V> valueSelector;

        public GroupByObserver(Observer<? super b<K, V>> observer, g<? super T, ? extends K> gVar, g<? super T, ? extends V> gVar2, int i, boolean z) {
            this.actual = observer;
            this.keySelector = gVar;
            this.valueSelector = gVar2;
            this.bufferSize = i;
            this.delayError = z;
            this.groups = new ConcurrentHashMap();
            lazySet(1);
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            Object obj;
            try {
                Object apply = this.keySelector.apply(t);
                if (apply != null) {
                    obj = apply;
                } else {
                    obj = NULL_KEY;
                }
                a aVar = (a) this.groups.get(obj);
                if (aVar == null) {
                    if (!this.cancelled.get()) {
                        aVar = a.a(apply, this.bufferSize, this, this.delayError);
                        this.groups.put(obj, aVar);
                        getAndIncrement();
                        this.actual.onNext(aVar);
                    } else {
                        return;
                    }
                }
                try {
                    aVar.a(io.reactivex.internal.functions.a.a(this.valueSelector.apply(t), "The value supplied is null"));
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.s.dispose();
                    onError(th);
                }
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                this.s.dispose();
                onError(th2);
            }
        }

        public void onError(Throwable th) {
            ArrayList<a> arrayList = new ArrayList<>(this.groups.values());
            this.groups.clear();
            for (a b : arrayList) {
                b.b(th);
            }
            this.actual.onError(th);
        }

        public void onComplete() {
            ArrayList<a> arrayList = new ArrayList<>(this.groups.values());
            this.groups.clear();
            for (a a : arrayList) {
                a.a();
            }
            this.actual.onComplete();
        }

        public void dispose() {
            if (this.cancelled.compareAndSet(false, true) && decrementAndGet() == 0) {
                this.s.dispose();
            }
        }

        public boolean isDisposed() {
            return this.cancelled.get();
        }

        public void cancel(K k) {
            if (k == null) {
                k = NULL_KEY;
            }
            this.groups.remove(k);
            if (decrementAndGet() == 0) {
                this.s.dispose();
            }
        }
    }

    static final class State<T, K> extends AtomicInteger implements Disposable, t<T> {
        private static final long serialVersionUID = -3852313036005250360L;
        final AtomicReference<Observer<? super T>> actual = new AtomicReference<>();
        final AtomicBoolean cancelled = new AtomicBoolean();
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final K key;
        final AtomicBoolean once = new AtomicBoolean();
        final GroupByObserver<?, K, T> parent;
        final io.reactivex.internal.queue.a<T> queue;

        State(int i, GroupByObserver<?, K, T> groupByObserver, K k, boolean z) {
            this.queue = new io.reactivex.internal.queue.a<>(i);
            this.parent = groupByObserver;
            this.key = k;
            this.delayError = z;
        }

        public void dispose() {
            if (this.cancelled.compareAndSet(false, true) && getAndIncrement() == 0) {
                this.actual.lazySet(null);
                this.parent.cancel(this.key);
            }
        }

        public boolean isDisposed() {
            return this.cancelled.get();
        }

        public void subscribe(Observer<? super T> observer) {
            if (this.once.compareAndSet(false, true)) {
                observer.onSubscribe(this);
                this.actual.lazySet(observer);
                if (this.cancelled.get()) {
                    this.actual.lazySet(null);
                } else {
                    drain();
                }
            } else {
                EmptyDisposable.error((Throwable) new IllegalStateException("Only one Observer allowed!"), observer);
            }
        }

        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                io.reactivex.internal.queue.a<T> aVar = this.queue;
                boolean z = this.delayError;
                Observer observer = (Observer) this.actual.get();
                int i = 1;
                while (true) {
                    if (observer != null) {
                        while (true) {
                            boolean z2 = this.done;
                            Object poll = aVar.poll();
                            boolean z3 = poll == null;
                            if (!checkTerminated(z2, z3, observer, z)) {
                                if (z3) {
                                    break;
                                }
                                observer.onNext(poll);
                            } else {
                                return;
                            }
                        }
                    }
                    i = addAndGet(-i);
                    if (i != 0) {
                        if (observer == null) {
                            observer = (Observer) this.actual.get();
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean checkTerminated(boolean z, boolean z2, Observer<? super T> observer, boolean z3) {
            if (this.cancelled.get()) {
                this.queue.clear();
                this.parent.cancel(this.key);
                this.actual.lazySet(null);
                return true;
            }
            if (z) {
                if (!z3) {
                    Throwable th = this.error;
                    if (th != null) {
                        this.queue.clear();
                        this.actual.lazySet(null);
                        observer.onError(th);
                        return true;
                    } else if (z2) {
                        this.actual.lazySet(null);
                        observer.onComplete();
                        return true;
                    }
                } else if (z2) {
                    Throwable th2 = this.error;
                    this.actual.lazySet(null);
                    if (th2 != null) {
                        observer.onError(th2);
                    } else {
                        observer.onComplete();
                    }
                    return true;
                }
            }
            return false;
        }
    }

    static final class a<K, T> extends b<K, T> {
        final State<T, K> a;

        public static <T, K> a<K, T> a(K k, int i, GroupByObserver<?, K, T> groupByObserver, boolean z) {
            return new a<>(k, new State(i, groupByObserver, k, z));
        }

        protected a(K k, State<T, K> state) {
            super(k);
            this.a = state;
        }

        /* access modifiers changed from: protected */
        public void a(Observer<? super T> observer) {
            this.a.subscribe(observer);
        }

        public void a(T t) {
            this.a.onNext(t);
        }

        public void b(Throwable th) {
            this.a.onError(th);
        }

        public void a() {
            this.a.onComplete();
        }
    }

    public void a(Observer<? super b<K, V>> observer) {
        t tVar = this.a;
        GroupByObserver groupByObserver = new GroupByObserver(observer, this.b, this.c, this.d, this.e);
        tVar.subscribe(groupByObserver);
    }
}
