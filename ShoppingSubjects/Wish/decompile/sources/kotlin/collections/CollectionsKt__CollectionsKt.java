package kotlin.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Collections.kt */
class CollectionsKt__CollectionsKt {
    public static final <T> Collection<T> asCollection(T[] tArr) {
        Intrinsics.checkParameterIsNotNull(tArr, "$receiver");
        return new ArrayAsCollection<>(tArr, false);
    }

    public static final <T> List<T> emptyList() {
        return EmptyList.INSTANCE;
    }

    public static final <T> List<T> listOf(T... tArr) {
        Intrinsics.checkParameterIsNotNull(tArr, "elements");
        return tArr.length > 0 ? ArraysKt.asList(tArr) : CollectionsKt.emptyList();
    }

    public static final <T> List<T> listOf(T t) {
        List<T> singletonList = Collections.singletonList(t);
        Intrinsics.checkExpressionValueIsNotNull(singletonList, "java.util.Collections.singletonList(element)");
        return singletonList;
    }

    public static final <T> int getLastIndex(List<? extends T> list) {
        Intrinsics.checkParameterIsNotNull(list, "$receiver");
        return list.size() - 1;
    }

    public static final <T> List<T> optimizeReadOnlyList(List<? extends T> list) {
        Intrinsics.checkParameterIsNotNull(list, "$receiver");
        switch (list.size()) {
            case 0:
                return CollectionsKt.emptyList();
            case 1:
                return CollectionsKt.listOf((T) list.get(0));
            default:
                return list;
        }
    }

    /* access modifiers changed from: private */
    public static final <T> Object[] copyToArrayOfAny$CollectionsKt__CollectionsKt(T[] tArr, boolean z) {
        if (!z || !Intrinsics.areEqual(tArr.getClass(), Object[].class)) {
            Object[] copyOf = Arrays.copyOf(tArr, tArr.length, Object[].class);
            Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(… Array<Any?>::class.java)");
            return copyOf;
        } else if (tArr != null) {
            return tArr;
        } else {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        }
    }
}
