package retrofit2.adapter.rxjava2;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.q;
import retrofit2.b;
import retrofit2.l;

/* compiled from: CallExecuteObservable */
final class c<T> extends q<l<T>> {
    private final b<T> a;

    /* compiled from: CallExecuteObservable */
    private static final class a implements Disposable {
        private final b<?> a;
        private volatile boolean b;

        a(b<?> bVar) {
            this.a = bVar;
        }

        public void dispose() {
            this.b = true;
            this.a.b();
        }

        public boolean isDisposed() {
            return this.b;
        }
    }

    c(b<T> bVar) {
        this.a = bVar;
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super l<T>> observer) {
        boolean z;
        b d = this.a.d();
        a aVar = new a(d);
        observer.onSubscribe(aVar);
        try {
            l a2 = d.a();
            if (!aVar.isDisposed()) {
                observer.onNext(a2);
            }
            if (!aVar.isDisposed()) {
                try {
                    observer.onComplete();
                } catch (Throwable th) {
                    th = th;
                    z = true;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            z = false;
            io.reactivex.exceptions.a.b(th);
            if (z) {
                io.reactivex.d.a.a(th);
            } else if (!aVar.isDisposed()) {
                try {
                    observer.onError(th);
                } catch (Throwable th3) {
                    io.reactivex.exceptions.a.b(th3);
                    io.reactivex.d.a.a((Throwable) new CompositeException(th, th3));
                }
            }
        }
    }
}
