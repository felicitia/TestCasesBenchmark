package com.etsy.android.extensions;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import com.etsy.android.lib.models.ResponseConstants;
import kotlin.jvm.internal.p;

/* compiled from: SizeExtensions.kt */
public final class g {
    public static final int a(Number number, Context context) {
        p.b(number, "$receiver");
        p.b(context, ResponseConstants.CONTEXT);
        float floatValue = number.floatValue();
        Resources resources = context.getResources();
        p.a((Object) resources, "context.resources");
        return (int) TypedValue.applyDimension(1, floatValue, resources.getDisplayMetrics());
    }
}
