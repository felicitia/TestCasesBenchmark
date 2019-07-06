package kotlin;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Lazy.kt */
public final class LazyKt {
    public static final <T> Lazy<T> lazy(Function0<? extends T> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "initializer");
        return new SynchronizedLazyImpl<>(function0, null, 2, null);
    }
}
