package io.reactivex.internal.functions;

import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.LongConsumer;
import io.reactivex.functions.Predicate;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Comparator;
import java.util.concurrent.Callable;
import org.reactivestreams.Subscription;

public final class Functions {
    static final Predicate<Object> ALWAYS_FALSE = new Predicate<Object>() {
        public boolean test(Object obj) {
            return false;
        }
    };
    static final Predicate<Object> ALWAYS_TRUE = new Predicate<Object>() {
        public boolean test(Object obj) {
            return true;
        }
    };
    public static final Action EMPTY_ACTION = new Action() {
        public void run() {
        }

        public String toString() {
            return "EmptyAction";
        }
    };
    static final Consumer<Object> EMPTY_CONSUMER = new Consumer<Object>() {
        public void accept(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }
    };
    public static final LongConsumer EMPTY_LONG_CONSUMER = new LongConsumer() {
    };
    public static final Runnable EMPTY_RUNNABLE = new Runnable() {
        public void run() {
        }

        public String toString() {
            return "EmptyRunnable";
        }
    };
    public static final Consumer<Throwable> ERROR_CONSUMER = new Consumer<Throwable>() {
        public void accept(Throwable th) {
            RxJavaPlugins.onError(th);
        }
    };
    static final Function<Object, Object> IDENTITY = new Function<Object, Object>() {
        public Object apply(Object obj) {
            return obj;
        }

        public String toString() {
            return "IdentityFunction";
        }
    };
    static final Comparator<Object> NATURAL_COMPARATOR = new Comparator<Object>() {
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    };
    static final Callable<Object> NULL_SUPPLIER = new Callable<Object>() {
        public Object call() {
            return null;
        }
    };
    public static final Consumer<Subscription> REQUEST_MAX = new Consumer<Subscription>() {
        public void accept(Subscription subscription) throws Exception {
            subscription.request(Long.MAX_VALUE);
        }
    };

    public static <T1, T2, R> Function<Object[], R> toFunction(final BiFunction<? super T1, ? super T2, ? extends R> biFunction) {
        ObjectHelper.requireNonNull(biFunction, "f is null");
        return new Function<Object[], R>() {
            public R apply(Object[] objArr) throws Exception {
                if (objArr.length == 2) {
                    return biFunction.apply(objArr[0], objArr[1]);
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Array of size 2 expected but got ");
                sb.append(objArr.length);
                throw new IllegalArgumentException(sb.toString());
            }
        };
    }

    public static <T> Consumer<T> emptyConsumer() {
        return EMPTY_CONSUMER;
    }
}
