package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class i {

    private static final class a implements b {
        private final CountDownLatch a;

        private a() {
            this.a = new CountDownLatch(1);
        }

        /* synthetic */ a(z zVar) {
            this();
        }

        public final void a() {
            this.a.countDown();
        }

        public final void a(@NonNull Exception exc) {
            this.a.countDown();
        }

        public final void a(Object obj) {
            this.a.countDown();
        }

        public final boolean a(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.a.await(j, timeUnit);
        }

        public final void b() throws InterruptedException {
            this.a.await();
        }
    }

    interface b extends b, d, e<Object> {
    }

    public static <TResult> f<TResult> a(@NonNull Exception exc) {
        y yVar = new y();
        yVar.a(exc);
        return yVar;
    }

    public static <TResult> f<TResult> a(TResult tresult) {
        y yVar = new y();
        yVar.a(tresult);
        return yVar;
    }

    public static <TResult> f<TResult> a(@NonNull Executor executor, @NonNull Callable<TResult> callable) {
        Preconditions.checkNotNull(executor, "Executor must not be null");
        Preconditions.checkNotNull(callable, "Callback must not be null");
        y yVar = new y();
        executor.execute(new z(yVar, callable));
        return yVar;
    }

    public static <TResult> TResult a(@NonNull f<TResult> fVar) throws ExecutionException, InterruptedException {
        Preconditions.checkNotMainThread();
        Preconditions.checkNotNull(fVar, "Task must not be null");
        if (fVar.a()) {
            return b(fVar);
        }
        a aVar = new a(null);
        a(fVar, (b) aVar);
        aVar.b();
        return b(fVar);
    }

    public static <TResult> TResult a(@NonNull f<TResult> fVar, long j, @NonNull TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        Preconditions.checkNotMainThread();
        Preconditions.checkNotNull(fVar, "Task must not be null");
        Preconditions.checkNotNull(timeUnit, "TimeUnit must not be null");
        if (fVar.a()) {
            return b(fVar);
        }
        a aVar = new a(null);
        a(fVar, (b) aVar);
        if (aVar.a(j, timeUnit)) {
            return b(fVar);
        }
        throw new TimeoutException("Timed out waiting for Task");
    }

    private static void a(f<?> fVar, b bVar) {
        fVar.a(h.b, (e<? super TResult>) bVar);
        fVar.a(h.b, (d) bVar);
        fVar.a(h.b, (b) bVar);
    }

    private static <TResult> TResult b(f<TResult> fVar) throws ExecutionException {
        if (fVar.b()) {
            return fVar.d();
        }
        if (fVar.c()) {
            throw new CancellationException("Task is already canceled");
        }
        throw new ExecutionException(fVar.e());
    }
}
