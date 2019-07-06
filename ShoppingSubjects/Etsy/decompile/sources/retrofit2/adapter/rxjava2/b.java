package retrofit2.adapter.rxjava2;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.q;
import retrofit2.d;
import retrofit2.l;

/* compiled from: CallEnqueueObservable */
final class b<T> extends q<l<T>> {
    private final retrofit2.b<T> a;

    /* compiled from: CallEnqueueObservable */
    private static final class a<T> implements Disposable, d<T> {
        boolean a = false;
        private final retrofit2.b<?> b;
        private final Observer<? super l<T>> c;
        private volatile boolean d;

        a(retrofit2.b<?> bVar, Observer<? super l<T>> observer) {
            this.b = bVar;
            this.c = observer;
        }

        public void a(retrofit2.b<T> bVar, l<T> lVar) {
            if (!this.d) {
                try {
                    this.c.onNext(lVar);
                    if (!this.d) {
                        this.a = true;
                        this.c.onComplete();
                    }
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    io.reactivex.d.a.a((Throwable) new CompositeException(th, th));
                }
            }
        }

        public void a(retrofit2.b<T> bVar, Throwable th) {
            if (!bVar.c()) {
                try {
                    this.c.onError(th);
                } catch (Throwable th2) {
                    io.reactivex.exceptions.a.b(th2);
                    io.reactivex.d.a.a((Throwable) new CompositeException(th, th2));
                }
            }
        }

        public void dispose() {
            this.d = true;
            this.b.b();
        }

        public boolean isDisposed() {
            return this.d;
        }
    }

    b(retrofit2.b<T> bVar) {
        this.a = bVar;
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super l<T>> observer) {
        retrofit2.b d = this.a.d();
        a aVar = new a(d, observer);
        observer.onSubscribe(aVar);
        d.a(aVar);
    }
}
