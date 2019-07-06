package com.onfido.android.sdk.capture.utils;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class ListExtensionsKt {
    public static final <T> boolean hasDuplicate(List<? extends T> list, T t) {
        Intrinsics.checkParameterIsNotNull(list, "$receiver");
        int indexOf = list.indexOf(t);
        boolean z = false;
        if (indexOf == -1) {
            return false;
        }
        if (indexOf != list.lastIndexOf(t)) {
            z = true;
        }
        return z;
    }
}
