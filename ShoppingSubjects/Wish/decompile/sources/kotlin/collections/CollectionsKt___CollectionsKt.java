package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: _Collections.kt */
class CollectionsKt___CollectionsKt extends CollectionsKt__ReversedViewsKt {
    public static final <T> List<T> sortedWith(Iterable<? extends T> iterable, Comparator<? super T> comparator) {
        Intrinsics.checkParameterIsNotNull(iterable, "$receiver");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            if (collection.size() <= 1) {
                return CollectionsKt.toList(iterable);
            }
            Object[] array = collection.toArray(new Object[0]);
            if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            } else if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            } else {
                ArraysKt.sortWith(array, comparator);
                return ArraysKt.asList(array);
            }
        } else {
            List<T> mutableList = CollectionsKt.toMutableList(iterable);
            CollectionsKt.sortWith(mutableList, comparator);
            return mutableList;
        }
    }

    public static final int[] toIntArray(Collection<Integer> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "$receiver");
        int[] iArr = new int[collection.size()];
        int i = 0;
        for (Number intValue : collection) {
            int i2 = i + 1;
            iArr[i] = intValue.intValue();
            i = i2;
        }
        return iArr;
    }

    public static final <T, C extends Collection<? super T>> C toCollection(Iterable<? extends T> iterable, C c) {
        Intrinsics.checkParameterIsNotNull(iterable, "$receiver");
        Intrinsics.checkParameterIsNotNull(c, "destination");
        for (Object add : iterable) {
            c.add(add);
        }
        return c;
    }

    public static final <T> List<T> toList(Iterable<? extends T> iterable) {
        List<T> list;
        Intrinsics.checkParameterIsNotNull(iterable, "$receiver");
        if (!(iterable instanceof Collection)) {
            return CollectionsKt.optimizeReadOnlyList(CollectionsKt.toMutableList(iterable));
        }
        Collection collection = (Collection) iterable;
        switch (collection.size()) {
            case 0:
                list = CollectionsKt.emptyList();
                break;
            case 1:
                list = CollectionsKt.listOf(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
                break;
            default:
                list = CollectionsKt.toMutableList(collection);
                break;
        }
        return list;
    }

    public static final <T> List<T> toMutableList(Iterable<? extends T> iterable) {
        Intrinsics.checkParameterIsNotNull(iterable, "$receiver");
        if (iterable instanceof Collection) {
            return CollectionsKt.toMutableList((Collection) iterable);
        }
        return (List) CollectionsKt.toCollection(iterable, new ArrayList());
    }

    public static final <T> List<T> toMutableList(Collection<? extends T> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "$receiver");
        return new ArrayList<>(collection);
    }
}
