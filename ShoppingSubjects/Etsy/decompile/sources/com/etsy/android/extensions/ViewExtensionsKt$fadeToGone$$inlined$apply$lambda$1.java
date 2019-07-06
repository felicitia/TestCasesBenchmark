package com.etsy.android.extensions;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Interpolator;
import com.etsy.android.lib.models.ResponseConstants;
import kotlin.jvm.internal.p;

/* compiled from: ViewExtensions.kt */
public final class ViewExtensionsKt$fadeToGone$$inlined$apply$lambda$1 implements AnimationListener {
    final /* synthetic */ int $duration$inlined;
    final /* synthetic */ Interpolator $interpolator$inlined;
    final /* synthetic */ View receiver$0;

    public void onAnimationRepeat(Animation animation) {
        p.b(animation, ResponseConstants.ANIMATION);
    }

    public void onAnimationStart(Animation animation) {
        p.b(animation, ResponseConstants.ANIMATION);
    }

    ViewExtensionsKt$fadeToGone$$inlined$apply$lambda$1(View view, int i, Interpolator interpolator) {
        this.receiver$0 = view;
        this.$duration$inlined = i;
        this.$interpolator$inlined = interpolator;
    }

    public void onAnimationEnd(Animation animation) {
        p.b(animation, ResponseConstants.ANIMATION);
        j.a(this.receiver$0, this.$duration$inlined, this.$interpolator$inlined);
    }
}
