package io.reactivex;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.observers.CallbackCompletableObserver;
import io.reactivex.internal.operators.completable.CompletableObserveOn;
import io.reactivex.internal.operators.completable.CompletableSubscribeOn;

/* compiled from: Completable */
public abstract class a implements e {
    /* access modifiers changed from: protected */
    public abstract void b(c cVar);

    private static NullPointerException a(Throwable th) {
        NullPointerException nullPointerException = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        nullPointerException.initCause(th);
        return nullPointerException;
    }

    public final a a(io.reactivex.functions.a aVar) {
        return a(Functions.b(), Functions.b(), aVar, Functions.c, Functions.c, Functions.c);
    }

    private a a(Consumer<? super Disposable> consumer, Consumer<? super Throwable> consumer2, io.reactivex.functions.a aVar, io.reactivex.functions.a aVar2, io.reactivex.functions.a aVar3, io.reactivex.functions.a aVar4) {
        io.reactivex.internal.functions.a.a(consumer, "onSubscribe is null");
        io.reactivex.internal.functions.a.a(consumer2, "onError is null");
        io.reactivex.internal.functions.a.a(aVar, "onComplete is null");
        io.reactivex.internal.functions.a.a(aVar2, "onTerminate is null");
        io.reactivex.internal.functions.a.a(aVar3, "onAfterTerminate is null");
        io.reactivex.internal.functions.a.a(aVar4, "onDispose is null");
        io.reactivex.internal.operators.completable.a aVar5 = new io.reactivex.internal.operators.completable.a(this, consumer, consumer2, aVar, aVar2, aVar3, aVar4);
        return io.reactivex.d.a.a((a) aVar5);
    }

    public final a a(u uVar) {
        io.reactivex.internal.functions.a.a(uVar, "scheduler is null");
        return io.reactivex.d.a.a((a) new CompletableObserveOn(this, uVar));
    }

    public final void a(c cVar) {
        io.reactivex.internal.functions.a.a(cVar, "s is null");
        try {
            b(io.reactivex.d.a.a(this, cVar));
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            io.reactivex.d.a.a(th);
            throw a(th);
        }
    }

    public final Disposable a(io.reactivex.functions.a aVar, Consumer<? super Throwable> consumer) {
        io.reactivex.internal.functions.a.a(consumer, "onError is null");
        io.reactivex.internal.functions.a.a(aVar, "onComplete is null");
        CallbackCompletableObserver callbackCompletableObserver = new CallbackCompletableObserver(consumer, aVar);
        a((c) callbackCompletableObserver);
        return callbackCompletableObserver;
    }

    public final a b(u uVar) {
        io.reactivex.internal.functions.a.a(uVar, "scheduler is null");
        return io.reactivex.d.a.a((a) new CompletableSubscribeOn(this, uVar));
    }
}
