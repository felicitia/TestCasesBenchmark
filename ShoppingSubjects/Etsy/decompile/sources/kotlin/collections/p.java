package kotlin.collections;

import java.util.Collections;
import java.util.List;

/* compiled from: CollectionsJVM.kt */
class p {
    public static final <T> List<T> a(T t) {
        List<T> singletonList = Collections.singletonList(t);
        kotlin.jvm.internal.p.a((Object) singletonList, "java.util.Collections.singletonList(element)");
        return singletonList;
    }
}
