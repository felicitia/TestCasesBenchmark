package com.etsy.android.extensions;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.etsy.android.lib.models.ResponseConstants;
import kotlin.jvm.internal.p;

/* compiled from: ViewExtensions.kt */
public final class ViewExtensionsKt$fadeToGone$1$2 implements AnimationListener {
    final /* synthetic */ View receiver$0;

    public void onAnimationRepeat(Animation animation) {
        p.b(animation, ResponseConstants.ANIMATION);
    }

    public void onAnimationStart(Animation animation) {
        p.b(animation, ResponseConstants.ANIMATION);
    }

    ViewExtensionsKt$fadeToGone$1$2(View view) {
        this.receiver$0 = view;
    }

    public void onAnimationEnd(Animation animation) {
        p.b(animation, ResponseConstants.ANIMATION);
        this.receiver$0.setVisibility(8);
    }
}
