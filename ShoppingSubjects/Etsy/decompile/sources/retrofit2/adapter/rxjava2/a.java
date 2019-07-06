package retrofit2.adapter.rxjava2;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.q;
import retrofit2.l;

/* compiled from: BodyObservable */
final class a<T> extends q<T> {
    private final q<l<T>> a;

    /* renamed from: retrofit2.adapter.rxjava2.a$a reason: collision with other inner class name */
    /* compiled from: BodyObservable */
    private static class C0199a<R> implements Observer<l<R>> {
        private final Observer<? super R> a;
        private boolean b;

        C0199a(Observer<? super R> observer) {
            this.a = observer;
        }

        public void onSubscribe(Disposable disposable) {
            this.a.onSubscribe(disposable);
        }

        /* renamed from: a */
        public void onNext(l<R> lVar) {
            if (lVar.d()) {
                this.a.onNext(lVar.e());
                return;
            }
            this.b = true;
            HttpException httpException = new HttpException(lVar);
            try {
                this.a.onError(httpException);
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                io.reactivex.d.a.a((Throwable) new CompositeException(httpException, th));
            }
        }

        public void onComplete() {
            if (!this.b) {
                this.a.onComplete();
            }
        }

        public void onError(Throwable th) {
            if (!this.b) {
                this.a.onError(th);
                return;
            }
            AssertionError assertionError = new AssertionError("This should never happen! Report as a bug with the full stacktrace.");
            assertionError.initCause(th);
            io.reactivex.d.a.a((Throwable) assertionError);
        }
    }

    a(q<l<T>> qVar) {
        this.a = qVar;
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super T> observer) {
        this.a.subscribe(new C0199a(observer));
    }
}
