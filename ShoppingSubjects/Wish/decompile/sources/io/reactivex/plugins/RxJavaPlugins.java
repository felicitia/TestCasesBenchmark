package io.reactivex.plugins;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.concurrent.Callable;

public final class RxJavaPlugins {
    static volatile Consumer<Throwable> errorHandler;
    static volatile Function<Completable, Completable> onCompletableAssembly;
    static volatile Function<Flowable, Flowable> onFlowableAssembly;
    static volatile Function<Callable<Scheduler>, Scheduler> onInitComputationHandler;
    static volatile Function<Callable<Scheduler>, Scheduler> onInitIoHandler;
    static volatile Function<Callable<Scheduler>, Scheduler> onInitNewThreadHandler;
    static volatile Function<Callable<Scheduler>, Scheduler> onInitSingleHandler;
    static volatile Function<Scheduler, Scheduler> onIoHandler;
    static volatile Function<Maybe, Maybe> onMaybeAssembly;
    static volatile Function<Observable, Observable> onObservableAssembly;
    static volatile BiFunction<Observable, Observer, Observer> onObservableSubscribe;
    static volatile Function<Runnable, Runnable> onScheduleHandler;
    static volatile Function<Single, Single> onSingleAssembly;

    public static Scheduler initComputationScheduler(Callable<Scheduler> callable) {
        ObjectHelper.requireNonNull(callable, "Scheduler Callable can't be null");
        Function<Callable<Scheduler>, Scheduler> function = onInitComputationHandler;
        if (function == null) {
            return callRequireNonNull(callable);
        }
        return applyRequireNonNull(function, callable);
    }

    public static Scheduler initIoScheduler(Callable<Scheduler> callable) {
        ObjectHelper.requireNonNull(callable, "Scheduler Callable can't be null");
        Function<Callable<Scheduler>, Scheduler> function = onInitIoHandler;
        if (function == null) {
            return callRequireNonNull(callable);
        }
        return applyRequireNonNull(function, callable);
    }

    public static Scheduler initNewThreadScheduler(Callable<Scheduler> callable) {
        ObjectHelper.requireNonNull(callable, "Scheduler Callable can't be null");
        Function<Callable<Scheduler>, Scheduler> function = onInitNewThreadHandler;
        if (function == null) {
            return callRequireNonNull(callable);
        }
        return applyRequireNonNull(function, callable);
    }

    public static Scheduler initSingleScheduler(Callable<Scheduler> callable) {
        ObjectHelper.requireNonNull(callable, "Scheduler Callable can't be null");
        Function<Callable<Scheduler>, Scheduler> function = onInitSingleHandler;
        if (function == null) {
            return callRequireNonNull(callable);
        }
        return applyRequireNonNull(function, callable);
    }

    public static void onError(Throwable th) {
        Consumer<Throwable> consumer = errorHandler;
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        if (consumer != null) {
            try {
                consumer.accept(th);
                return;
            } catch (Throwable th2) {
                th2.printStackTrace();
                uncaught(th2);
            }
        }
        th.printStackTrace();
        uncaught(th);
    }

    static void uncaught(Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    public static Scheduler onIoScheduler(Scheduler scheduler) {
        Function<Scheduler, Scheduler> function = onIoHandler;
        if (function == null) {
            return scheduler;
        }
        return (Scheduler) apply(function, scheduler);
    }

    public static Runnable onSchedule(Runnable runnable) {
        Function<Runnable, Runnable> function = onScheduleHandler;
        if (function == null) {
            return runnable;
        }
        return (Runnable) apply(function, runnable);
    }

    public static <T> Observer<? super T> onSubscribe(Observable<T> observable, Observer<? super T> observer) {
        BiFunction<Observable, Observer, Observer> biFunction = onObservableSubscribe;
        return biFunction != null ? (Observer) apply(biFunction, observable, observer) : observer;
    }

    public static <T> Maybe<T> onAssembly(Maybe<T> maybe) {
        Function<Maybe, Maybe> function = onMaybeAssembly;
        return function != null ? (Maybe) apply(function, maybe) : maybe;
    }

    public static <T> Flowable<T> onAssembly(Flowable<T> flowable) {
        Function<Flowable, Flowable> function = onFlowableAssembly;
        return function != null ? (Flowable) apply(function, flowable) : flowable;
    }

    public static <T> Observable<T> onAssembly(Observable<T> observable) {
        Function<Observable, Observable> function = onObservableAssembly;
        return function != null ? (Observable) apply(function, observable) : observable;
    }

    public static <T> Single<T> onAssembly(Single<T> single) {
        Function<Single, Single> function = onSingleAssembly;
        return function != null ? (Single) apply(function, single) : single;
    }

    public static Completable onAssembly(Completable completable) {
        Function<Completable, Completable> function = onCompletableAssembly;
        return function != null ? (Completable) apply(function, completable) : completable;
    }

    static <T, R> R apply(Function<T, R> function, T t) {
        try {
            return function.apply(t);
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    static <T, U, R> R apply(BiFunction<T, U, R> biFunction, T t, U u) {
        try {
            return biFunction.apply(t, u);
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    static Scheduler callRequireNonNull(Callable<Scheduler> callable) {
        try {
            return (Scheduler) ObjectHelper.requireNonNull(callable.call(), "Scheduler Callable result can't be null");
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    static Scheduler applyRequireNonNull(Function<Callable<Scheduler>, Scheduler> function, Callable<Scheduler> callable) {
        return (Scheduler) ObjectHelper.requireNonNull(apply(function, callable), "Scheduler Callable result can't be null");
    }
}
