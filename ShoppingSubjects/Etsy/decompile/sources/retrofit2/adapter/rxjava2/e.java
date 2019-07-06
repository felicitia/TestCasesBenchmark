package retrofit2.adapter.rxjava2;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.q;
import retrofit2.l;

/* compiled from: ResultObservable */
final class e<T> extends q<d<T>> {
    private final q<l<T>> a;

    /* compiled from: ResultObservable */
    private static class a<R> implements Observer<l<R>> {
        private final Observer<? super d<R>> a;

        a(Observer<? super d<R>> observer) {
            this.a = observer;
        }

        public void onSubscribe(Disposable disposable) {
            this.a.onSubscribe(disposable);
        }

        /* renamed from: a */
        public void onNext(l<R> lVar) {
            this.a.onNext(d.a(lVar));
        }

        public void onError(Throwable th) {
            try {
                this.a.onNext(d.a(th));
                this.a.onComplete();
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                io.reactivex.d.a.a((Throwable) new CompositeException(th, th2));
            }
        }

        public void onComplete() {
            this.a.onComplete();
        }
    }

    e(q<l<T>> qVar) {
        this.a = qVar;
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super d<T>> observer) {
        this.a.subscribe(new a(observer));
    }
}
