package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.b;
import io.reactivex.t;
import io.reactivex.u;
import io.reactivex.u.c;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableDebounceTimed<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final u d;

    static final class DebounceEmitter<T> extends AtomicReference<Disposable> implements Disposable, Runnable {
        private static final long serialVersionUID = 6812032969491025141L;
        final long idx;
        final AtomicBoolean once = new AtomicBoolean();
        final a<T> parent;
        final T value;

        DebounceEmitter(T t, long j, a<T> aVar) {
            this.value = t;
            this.idx = j;
            this.parent = aVar;
        }

        public void run() {
            if (this.once.compareAndSet(false, true)) {
                this.parent.a(this.idx, this.value, this);
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        public void setResource(Disposable disposable) {
            DisposableHelper.replace(this, disposable);
        }
    }

    static final class a<T> implements Observer<T>, Disposable {
        final Observer<? super T> a;
        final long b;
        final TimeUnit c;
        final c d;
        Disposable e;
        final AtomicReference<Disposable> f = new AtomicReference<>();
        volatile long g;
        boolean h;

        a(Observer<? super T> observer, long j, TimeUnit timeUnit, c cVar) {
            this.a = observer;
            this.b = j;
            this.c = timeUnit;
            this.d = cVar;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.e, disposable)) {
                this.e = disposable;
                this.a.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.h) {
                long j = this.g + 1;
                this.g = j;
                Disposable disposable = (Disposable) this.f.get();
                if (disposable != null) {
                    disposable.dispose();
                }
                DebounceEmitter debounceEmitter = new DebounceEmitter(t, j, this);
                if (this.f.compareAndSet(disposable, debounceEmitter)) {
                    debounceEmitter.setResource(this.d.a(debounceEmitter, this.b, this.c));
                }
            }
        }

        public void onError(Throwable th) {
            if (this.h) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.h = true;
            this.a.onError(th);
            this.d.dispose();
        }

        public void onComplete() {
            if (!this.h) {
                this.h = true;
                Disposable disposable = (Disposable) this.f.get();
                if (disposable != DisposableHelper.DISPOSED) {
                    DebounceEmitter debounceEmitter = (DebounceEmitter) disposable;
                    if (debounceEmitter != null) {
                        debounceEmitter.run();
                    }
                    this.a.onComplete();
                    this.d.dispose();
                }
            }
        }

        public void dispose() {
            this.e.dispose();
            this.d.dispose();
        }

        public boolean isDisposed() {
            return this.d.isDisposed();
        }

        /* access modifiers changed from: 0000 */
        public void a(long j, T t, DebounceEmitter<T> debounceEmitter) {
            if (j == this.g) {
                this.a.onNext(t);
                debounceEmitter.dispose();
            }
        }
    }

    public ObservableDebounceTimed(t<T> tVar, long j, TimeUnit timeUnit, u uVar) {
        super(tVar);
        this.b = j;
        this.c = timeUnit;
        this.d = uVar;
    }

    public void a(Observer<? super T> observer) {
        t tVar = this.a;
        a aVar = new a(new b(observer), this.b, this.c, this.d.a());
        tVar.subscribe(aVar);
    }
}
