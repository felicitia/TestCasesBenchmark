package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: _Arrays.kt */
class ArraysKt___ArraysKt extends ArraysKt__ArraysKt {
    public static final <T> boolean contains(T[] tArr, T t) {
        Intrinsics.checkParameterIsNotNull(tArr, "$receiver");
        return ArraysKt.indexOf(tArr, t) >= 0;
    }

    public static final <T> T getOrNull(T[] tArr, int i) {
        Intrinsics.checkParameterIsNotNull(tArr, "$receiver");
        if (i < 0 || i > ArraysKt.getLastIndex(tArr)) {
            return null;
        }
        return tArr[i];
    }

    public static final <T> int indexOf(T[] tArr, T t) {
        Intrinsics.checkParameterIsNotNull(tArr, "$receiver");
        int i = 0;
        if (t == null) {
            int length = tArr.length;
            while (i < length) {
                if (tArr[i] == null) {
                    return i;
                }
                i++;
            }
        } else {
            int length2 = tArr.length;
            while (i < length2) {
                if (Intrinsics.areEqual(t, tArr[i])) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    public static final char single(char[] cArr) {
        Intrinsics.checkParameterIsNotNull(cArr, "$receiver");
        switch (cArr.length) {
            case 0:
                throw new NoSuchElementException("Array is empty.");
            case 1:
                return cArr[0];
            default:
                throw new IllegalArgumentException("Array has more than one element.");
        }
    }

    public static final <T> List<T> asList(T[] tArr) {
        Intrinsics.checkParameterIsNotNull(tArr, "$receiver");
        List<T> asList = ArraysUtilJVM.asList(tArr);
        Intrinsics.checkExpressionValueIsNotNull(asList, "ArraysUtilJVM.asList(this)");
        return asList;
    }

    public static final <T> int getLastIndex(T[] tArr) {
        Intrinsics.checkParameterIsNotNull(tArr, "$receiver");
        return tArr.length - 1;
    }

    public static final <T> void sortWith(T[] tArr, Comparator<? super T> comparator) {
        Intrinsics.checkParameterIsNotNull(tArr, "$receiver");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        if (tArr.length > 1) {
            Arrays.sort(tArr, comparator);
        }
    }

    public static final <T> List<T> toList(T[] tArr) {
        Intrinsics.checkParameterIsNotNull(tArr, "$receiver");
        switch (tArr.length) {
            case 0:
                return CollectionsKt.emptyList();
            case 1:
                return CollectionsKt.listOf(tArr[0]);
            default:
                return ArraysKt.toMutableList(tArr);
        }
    }

    public static final <T> List<T> toMutableList(T[] tArr) {
        Intrinsics.checkParameterIsNotNull(tArr, "$receiver");
        return new ArrayList<>(CollectionsKt.asCollection(tArr));
    }
}
