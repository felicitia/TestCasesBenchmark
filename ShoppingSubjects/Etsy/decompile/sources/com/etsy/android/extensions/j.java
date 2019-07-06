package com.etsy.android.extensions;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import com.etsy.android.lib.util.s;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.p;

/* compiled from: ViewExtensions.kt */
public final class j {
    public static final void a(View view, b<? super View, h> bVar) {
        p.b(view, "$receiver");
        p.b(bVar, CastExtraArgs.LISTENER);
        view.setOnClickListener(new ViewExtensionsKt$trackingClick$1(bVar));
    }

    public static final void a(View view) {
        if (view != null && view.getVisibility() != 0) {
            view.setVisibility(0);
        }
    }

    public static final void b(View view) {
        if (view != null && view.getVisibility() != 8) {
            view.setVisibility(8);
        }
    }

    public static final void a(View view, int i, Interpolator interpolator) {
        p.b(interpolator, "interpolator");
        if (!(view == null || view.getVisibility() == 8)) {
            Animation animation = view.getAnimation();
            if (animation == null || animation.hasEnded()) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setInterpolator(interpolator);
                alphaAnimation.setDuration((long) i);
                alphaAnimation.setAnimationListener(new ViewExtensionsKt$fadeToGone$1$2(view));
                view.startAnimation(alphaAnimation);
            } else {
                view.getAnimation().setAnimationListener(new ViewExtensionsKt$fadeToGone$$inlined$apply$lambda$1(view, i, interpolator));
            }
        }
    }

    public static final void c(View view) {
        if (view != null && view.getVisibility() != 4) {
            view.setVisibility(4);
        }
    }

    public static final void d(View view) {
        if (view != null) {
            s.c(view);
        }
    }
}
