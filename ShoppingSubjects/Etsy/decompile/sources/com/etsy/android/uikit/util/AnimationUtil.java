package com.etsy.android.uikit.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import com.etsy.android.lib.util.k;
import com.etsy.android.lib.util.l;
import java.util.Random;

public class AnimationUtil {

    public static class ViewHeightEvaluator extends IntEvaluator {
        private final LayoutParams mParams;
        private final View mView;

        public ViewHeightEvaluator(View view) {
            this.mView = view;
            this.mParams = view.getLayoutParams();
        }

        public Integer evaluate(float f, Integer num, Integer num2) {
            int intValue = super.evaluate(f, num, num2).intValue();
            this.mParams.height = intValue;
            this.mView.setLayoutParams(this.mParams);
            return Integer.valueOf(intValue);
        }
    }

    public static void a(View... viewArr) {
        if (viewArr != null) {
            for (View view : viewArr) {
                if (view != null) {
                    view.animate().setListener(null);
                    if (k.b()) {
                        view.animate().setUpdateListener(null);
                    }
                    view.animate().cancel();
                }
            }
        }
    }

    public static void a(View view, int i) {
        a(view, i, (AnimatorListenerAdapter) null);
    }

    public static void a(View view, int i, AnimatorListenerAdapter animatorListenerAdapter) {
        view.animate().cancel();
        view.setAlpha(0.0f);
        view.animate().setDuration((long) i).alpha(1.0f).setListener(animatorListenerAdapter).start();
        view.setVisibility(0);
    }

    public static void b(View view, int i, AnimatorListenerAdapter animatorListenerAdapter) {
        view.animate().cancel();
        view.setAlpha(1.0f);
        view.animate().setDuration((long) i).alpha(0.0f).setListener(animatorListenerAdapter).start();
    }

    public static void b(View view, int i) {
        view.animate().cancel();
        int height = view.getHeight();
        view.setY((float) i);
        view.animate().setDuration(200).yBy((float) (-height)).setListener(null).start();
    }

    public static void c(View view, int i) {
        view.animate().cancel();
        int height = view.getHeight();
        view.setY((float) (i - height));
        view.animate().setDuration(200).yBy((float) height).setListener(null).start();
    }

    public static void a(ImageView imageView, int i) {
        b(imageView, i);
    }

    public static void b(final ImageView imageView, final int i) {
        imageView.animate().cancel();
        AnonymousClass1 r0 = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                imageView.setImageResource(i);
            }
        };
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(imageView, "scaleX", new float[]{1.0f, 1.25f}), ObjectAnimator.ofFloat(imageView, "scaleY", new float[]{1.0f, 1.25f})});
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(100);
        animatorSet.addListener(r0);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(new Animator[]{ObjectAnimator.ofFloat(imageView, "scaleX", new float[]{1.25f, 1.0f}), ObjectAnimator.ofFloat(imageView, "scaleY", new float[]{1.25f, 1.0f})});
        animatorSet2.setInterpolator(new DecelerateInterpolator());
        animatorSet2.setDuration(100);
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playSequentially(new Animator[]{animatorSet, animatorSet2});
        animatorSet3.start();
    }

    public static Animator a(final View view, AnimatorListenerAdapter animatorListenerAdapter) {
        if (view == null || view.getLayoutParams() == null) {
            return null;
        }
        int measuredHeight = view.getMeasuredHeight();
        ValueAnimator ofObject = ValueAnimator.ofObject(new ViewHeightEvaluator(view), new Object[]{Integer.valueOf(measuredHeight), Integer.valueOf(0)});
        ofObject.setDuration(300);
        ofObject.setInterpolator(new DecelerateInterpolator());
        ofObject.addListener(new HardwareAnimatorListener(view) {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                view.setVisibility(8);
            }

            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                view.setVisibility(8);
            }
        });
        if (animatorListenerAdapter != null) {
            ofObject.addListener(animatorListenerAdapter);
        }
        ofObject.start();
        return ofObject;
    }

    public static void c(View view, int i, AnimatorListenerAdapter animatorListenerAdapter) {
        if (view != null && view.getLayoutParams() != null) {
            ValueAnimator ofObject = ValueAnimator.ofObject(new ViewHeightEvaluator(view), new Object[]{Integer.valueOf(0), Integer.valueOf(i)});
            ofObject.setDuration(300);
            ofObject.setInterpolator(new DecelerateInterpolator());
            if (animatorListenerAdapter != null) {
                ofObject.addListener(animatorListenerAdapter);
            }
            ofObject.start();
        }
    }

    public static AnimatorSet d(View view, int i) {
        view.animate().cancel();
        Random random = new Random();
        float f = 1.5f;
        float f2 = random.nextBoolean() ? -1.5f : 1.5f;
        if (random.nextBoolean()) {
            f = -1.5f;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "rotation", new float[]{f, -f});
        ofFloat.setRepeatMode(2);
        ofFloat.setRepeatCount(-1);
        ofFloat.setDuration(200);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "translationY", new float[]{f2, -f2});
        ofFloat2.setRepeatMode(2);
        ofFloat2.setRepeatCount(-1);
        ofFloat2.setDuration(100);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, "rotation", new float[]{0.0f, f});
        ofFloat3.setDuration(100);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, "translationY", new float[]{0.0f, f2});
        ofFloat4.setDuration(100);
        animatorSet.play(ofFloat3).with(ofFloat4);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.play(ofFloat2).with(ofFloat).after(animatorSet);
        animatorSet2.setStartDelay((long) i);
        animatorSet2.start();
        return animatorSet2;
    }

    public static AnimatorSet a(View view, int i, HardwareAnimatorListener hardwareAnimatorListener) {
        float f = -15.0f;
        if (view.getRotation() > 0.0f || (view.getRotation() >= 0.0f && !new Random().nextBoolean())) {
            f = 15.0f;
        }
        l lVar = new l(view.getContext());
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "rotation", new float[]{view.getRotation(), f});
        ofFloat.setDuration(200);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "translationY", new float[]{(float) lVar.e()});
        ofFloat2.setDuration(200);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f});
        ofFloat3.setDuration(200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat2).with(ofFloat3).after(ofFloat);
        animatorSet.setStartDelay((long) i);
        if (hardwareAnimatorListener != null) {
            animatorSet.addListener(hardwareAnimatorListener);
        }
        animatorSet.start();
        return animatorSet;
    }
}
