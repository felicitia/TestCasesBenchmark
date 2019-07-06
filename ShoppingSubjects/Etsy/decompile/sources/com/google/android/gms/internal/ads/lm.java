package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;

@bu
public final class lm {
    public static void a(View view, OnGlobalLayoutListener onGlobalLayoutListener) {
        new ln(view, onGlobalLayoutListener).a();
    }

    public static void a(View view, OnScrollChangedListener onScrollChangedListener) {
        new lo(view, onScrollChangedListener).a();
    }
}
