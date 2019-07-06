package com.onfido.android.sdk.capture.utils;

import android.content.Context;
import android.os.Build.VERSION;
import com.onfido.android.sdk.capture.ui.options.Orientation;
import kotlin.jvm.internal.Intrinsics;

public final class ContextUtilsKt {
    public static final boolean apilevelAtLeast(int i) {
        return VERSION.SDK_INT >= i;
    }

    public static final int dimen(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "$receiver");
        return context.getResources().getDimensionPixelSize(i);
    }

    public static final Orientation getScreenOrientation(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "$receiver");
        return context.getResources().getConfiguration().orientation == 2 ? Orientation.LANDSCAPE : Orientation.PORTRAIT;
    }
}
