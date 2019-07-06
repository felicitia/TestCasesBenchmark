package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.concurrent.Executor;

public abstract class f<TResult> {
    @NonNull
    public <TContinuationResult> f<TContinuationResult> a(@NonNull a<TResult, TContinuationResult> aVar) {
        throw new UnsupportedOperationException("continueWith is not implemented");
    }

    @NonNull
    public f<TResult> a(@NonNull c<TResult> cVar) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    @NonNull
    public <TContinuationResult> f<TContinuationResult> a(@NonNull Executor executor, @NonNull a<TResult, TContinuationResult> aVar) {
        throw new UnsupportedOperationException("continueWith is not implemented");
    }

    @NonNull
    public f<TResult> a(@NonNull Executor executor, @NonNull b bVar) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented");
    }

    @NonNull
    public f<TResult> a(@NonNull Executor executor, @NonNull c<TResult> cVar) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    @NonNull
    public abstract f<TResult> a(@NonNull Executor executor, @NonNull d dVar);

    @NonNull
    public abstract f<TResult> a(@NonNull Executor executor, @NonNull e<? super TResult> eVar);

    public abstract <X extends Throwable> TResult a(@NonNull Class<X> cls) throws Throwable;

    public abstract boolean a();

    @NonNull
    public <TContinuationResult> f<TContinuationResult> b(@NonNull Executor executor, @NonNull a<TResult, f<TContinuationResult>> aVar) {
        throw new UnsupportedOperationException("continueWithTask is not implemented");
    }

    public abstract boolean b();

    public abstract boolean c();

    public abstract TResult d();

    @Nullable
    public abstract Exception e();
}
