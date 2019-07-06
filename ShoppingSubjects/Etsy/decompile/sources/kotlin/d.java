package kotlin;

import kotlin.jvm.a.a;
import kotlin.jvm.internal.p;

/* compiled from: LazyJVM.kt */
class d {
    public static final <T> b<T> a(a<? extends T> aVar) {
        p.b(aVar, "initializer");
        return new SynchronizedLazyImpl<>(aVar, null, 2, null);
    }
}
