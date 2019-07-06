package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.Objects;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public final class ArrayUtils {
    public static <T> T[] concat(T[]... tArr) {
        if (tArr.length == 0) {
            return (Object[]) Array.newInstance(tArr.getClass(), 0);
        }
        int i = 0;
        for (T[] length : tArr) {
            i += length.length;
        }
        T[] copyOf = Arrays.copyOf(tArr[0], i);
        int length2 = tArr[0].length;
        for (int i2 = 1; i2 < tArr.length; i2++) {
            T[] tArr2 = tArr[i2];
            System.arraycopy(tArr2, 0, copyOf, length2, tArr2.length);
            length2 += tArr2.length;
        }
        return copyOf;
    }

    public static <T> boolean contains(T[] tArr, T t) {
        return indexOf(tArr, t) >= 0;
    }

    public static <T> int indexOf(T[] tArr, T t) {
        int length = tArr != null ? tArr.length : 0;
        for (int i = 0; i < length; i++) {
            if (Objects.equal(tArr[i], t)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> T[] removeAll(T[] tArr, T... tArr2) {
        int i;
        if (tArr == null) {
            return null;
        }
        if (tArr2 == null || tArr2.length == 0) {
            return Arrays.copyOf(tArr, tArr.length);
        }
        Object[] objArr = (Object[]) Array.newInstance(tArr2.getClass().getComponentType(), tArr.length);
        if (tArr2.length == 1) {
            i = 0;
            for (T t : tArr) {
                if (!Objects.equal(tArr2[0], t)) {
                    int i2 = i + 1;
                    objArr[i] = t;
                    i = i2;
                }
            }
        } else {
            i = 0;
            for (T t2 : tArr) {
                if (!contains(tArr2, t2)) {
                    int i3 = i + 1;
                    objArr[i] = t2;
                    i = i3;
                }
            }
        }
        return resize(objArr, i);
    }

    public static <T> T[] resize(T[] tArr, int i) {
        if (tArr == null) {
            return null;
        }
        if (i != tArr.length) {
            tArr = Arrays.copyOf(tArr, i);
        }
        return tArr;
    }
}
