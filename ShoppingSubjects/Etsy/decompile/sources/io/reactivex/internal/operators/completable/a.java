package io.reactivex.internal.operators.completable;

import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.e;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;

/* compiled from: CompletablePeek */
public final class a extends io.reactivex.a {
    final e a;
    final Consumer<? super Disposable> b;
    final Consumer<? super Throwable> c;
    final io.reactivex.functions.a d;
    final io.reactivex.functions.a e;
    final io.reactivex.functions.a f;
    final io.reactivex.functions.a g;

    /* renamed from: io.reactivex.internal.operators.completable.a$a reason: collision with other inner class name */
    /* compiled from: CompletablePeek */
    final class C0185a implements c, Disposable {
        final c a;
        Disposable b;

        C0185a(c cVar) {
            this.a = cVar;
        }

        public void onSubscribe(Disposable disposable) {
            try {
                a.this.b.accept(disposable);
                if (DisposableHelper.validate(this.b, disposable)) {
                    this.b = disposable;
                    this.a.onSubscribe(this);
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                disposable.dispose();
                this.b = DisposableHelper.DISPOSED;
                EmptyDisposable.error(th, this.a);
            }
        }

        public void onError(Throwable th) {
            if (this.b == DisposableHelper.DISPOSED) {
                io.reactivex.d.a.a(th);
                return;
            }
            try {
                a.this.c.accept(th);
                a.this.e.a();
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                th = new CompositeException(th, th2);
            }
            this.a.onError(th);
            a();
        }

        public void onComplete() {
            if (this.b != DisposableHelper.DISPOSED) {
                try {
                    a.this.d.a();
                    a.this.e.a();
                    this.a.onComplete();
                    a();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.a.onError(th);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            try {
                a.this.f.a();
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                io.reactivex.d.a.a(th);
            }
        }

        public void dispose() {
            try {
                a.this.g.a();
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                io.reactivex.d.a.a(th);
            }
            this.b.dispose();
        }

        public boolean isDisposed() {
            return this.b.isDisposed();
        }
    }

    public a(e eVar, Consumer<? super Disposable> consumer, Consumer<? super Throwable> consumer2, io.reactivex.functions.a aVar, io.reactivex.functions.a aVar2, io.reactivex.functions.a aVar3, io.reactivex.functions.a aVar4) {
        this.a = eVar;
        this.b = consumer;
        this.c = consumer2;
        this.d = aVar;
        this.e = aVar2;
        this.f = aVar3;
        this.g = aVar4;
    }

    /* access modifiers changed from: protected */
    public void b(c cVar) {
        this.a.a(new C0185a(cVar));
    }
}
