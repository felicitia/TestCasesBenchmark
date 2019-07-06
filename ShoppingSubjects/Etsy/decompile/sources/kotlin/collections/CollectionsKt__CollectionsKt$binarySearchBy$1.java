package kotlin.collections;

import kotlin.a.a;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: Collections.kt */
public final class CollectionsKt__CollectionsKt$binarySearchBy$1 extends Lambda implements b<T, Integer> {
    final /* synthetic */ Comparable $key;
    final /* synthetic */ b $selector;

    public CollectionsKt__CollectionsKt$binarySearchBy$1(b bVar, Comparable comparable) {
        this.$selector = bVar;
        this.$key = comparable;
        super(1);
    }

    public final int invoke(T t) {
        return a.a((Comparable) this.$selector.invoke(t), this.$key);
    }
}
