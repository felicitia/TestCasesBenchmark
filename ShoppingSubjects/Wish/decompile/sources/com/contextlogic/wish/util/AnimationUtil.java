package com.contextlogic.wish.util;

import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.LinearInterpolator;

public class AnimationUtil {
    public static void animateViewFadeInLinear(View view, AnimatorListenerAdapter animatorListenerAdapter) {
        animateViewFadeIn(view, 200, new LinearInterpolator(), animatorListenerAdapter);
    }

    private static void animateViewFadeIn(final View view, final int i, final TimeInterpolator timeInterpolator, final AnimatorListenerAdapter animatorListenerAdapter) {
        if (view == null) {
            triggerCallbackCancel(animatorListenerAdapter);
            return;
        }
        if (view.getHeight() == 0) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        if (view.getViewTreeObserver() != null) {
                            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                        AnimationUtil.animateView(view, animatorListenerAdapter, 0.0f, 0.0f, 0.0f, 1.0f, i, timeInterpolator);
                    }
                });
            } else {
                triggerCallbackCancel(animatorListenerAdapter);
            }
        } else {
            animateView(view, animatorListenerAdapter, 0.0f, 0.0f, 0.1f, 1.0f, i, timeInterpolator);
        }
    }

    private static void triggerCallbackCancel(AnimatorListenerAdapter animatorListenerAdapter) {
        if (animatorListenerAdapter != null) {
            animatorListenerAdapter.onAnimationCancel(null);
        }
    }

    /* access modifiers changed from: private */
    public static void animateView(View view, AnimatorListenerAdapter animatorListenerAdapter, float f, float f2, float f3, float f4, int i, TimeInterpolator timeInterpolator) {
        view.setTranslationY(f);
        view.setAlpha(f3);
        animateView(view, animatorListenerAdapter, f2, f4, i, timeInterpolator);
    }

    private static void animateView(View view, AnimatorListenerAdapter animatorListenerAdapter, float f, float f2, int i, TimeInterpolator timeInterpolator) {
        view.animate().translationY(f).alpha(f2).setDuration((long) i).setInterpolator(timeInterpolator).setListener(animatorListenerAdapter);
    }
}
