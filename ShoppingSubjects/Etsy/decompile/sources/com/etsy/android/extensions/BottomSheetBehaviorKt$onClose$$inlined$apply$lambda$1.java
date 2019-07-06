package com.etsy.android.extensions;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.view.View;
import kotlin.jvm.a.a;
import kotlin.jvm.internal.p;

/* compiled from: BottomSheetBehavior.kt */
public final class BottomSheetBehaviorKt$onClose$$inlined$apply$lambda$1 extends BottomSheetCallback {
    final /* synthetic */ a $listener$inlined;
    final /* synthetic */ BottomSheetBehavior receiver$0;

    public void onSlide(View view, float f) {
        p.b(view, "bottomSheet");
    }

    BottomSheetBehaviorKt$onClose$$inlined$apply$lambda$1(BottomSheetBehavior bottomSheetBehavior, a aVar) {
        this.receiver$0 = bottomSheetBehavior;
        this.$listener$inlined = aVar;
    }

    public void onStateChanged(View view, int i) {
        p.b(view, "bottomSheet");
        if (a.a(this.receiver$0)) {
            this.$listener$inlined.invoke();
        }
    }
}
