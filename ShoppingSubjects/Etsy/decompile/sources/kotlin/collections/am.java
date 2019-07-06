package kotlin.collections;

import java.util.Collections;
import java.util.Set;
import kotlin.jvm.internal.p;

/* compiled from: SetsJVM.kt */
class am {
    public static final <T> Set<T> a(T t) {
        Set<T> singleton = Collections.singleton(t);
        p.a((Object) singleton, "java.util.Collections.singleton(element)");
        return singleton;
    }
}
