package com.etsy.android.uikit.behavior;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.design.widget.Snackbar.SnackbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Interpolator;
import java.util.HashSet;
import java.util.Iterator;

public final class BottomNavigationBehavior<V extends View> extends VerticalScrollingBehavior<V> {
    private static final Interpolator INTERPOLATOR = new LinearOutSlowInInterpolator();
    private boolean hidden;
    private ViewPropertyAnimatorCompat mOffsetValueAnimator;
    /* access modifiers changed from: private */
    public int mSnackbarHeight;
    /* access modifiers changed from: private */
    public HashSet<View> mSnackbarLayouts;
    private final a mWithSnackBarImpl;
    private boolean scrollingEnabled;

    private interface a {
        void a(CoordinatorLayout coordinatorLayout, View view, View view2);
    }

    private class b implements a {
        private b() {
        }

        public void a(CoordinatorLayout coordinatorLayout, View view, View view2) {
            if (view instanceof SnackbarLayout) {
                BottomNavigationBehavior.this.mSnackbarLayouts.add(view);
                if (BottomNavigationBehavior.this.mSnackbarHeight == -1) {
                    BottomNavigationBehavior.this.mSnackbarHeight = view.getHeight();
                }
                view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), (BottomNavigationBehavior.this.mSnackbarHeight + view2.getMeasuredHeight()) - ((int) view2.getTranslationY()));
            }
        }
    }

    private class c implements a {
        private c() {
        }

        public void a(CoordinatorLayout coordinatorLayout, View view, View view2) {
            if (view instanceof SnackbarLayout) {
                if (BottomNavigationBehavior.this.mSnackbarHeight == -1) {
                    BottomNavigationBehavior.this.mSnackbarHeight = view.getHeight();
                }
                ((MarginLayoutParams) view.getLayoutParams()).bottomMargin = (view2.getMeasuredHeight() - ((int) view2.getTranslationY())) - ((int) ViewCompat.getElevation(view2));
                view2.bringToFront();
                if (VERSION.SDK_INT < 19) {
                    view2.getParent().requestLayout();
                    ((View) view2.getParent()).invalidate();
                }
            }
        }
    }

    public void onNestedVerticalOverScroll(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3) {
    }

    public BottomNavigationBehavior() {
        this.mWithSnackBarImpl = VERSION.SDK_INT >= 21 ? new b() : new c();
        this.hidden = false;
        this.mSnackbarHeight = -1;
        this.scrollingEnabled = true;
        this.mSnackbarLayouts = new HashSet<>();
    }

    public BottomNavigationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mWithSnackBarImpl = VERSION.SDK_INT >= 21 ? new b() : new c();
        this.hidden = false;
        this.mSnackbarHeight = -1;
        this.scrollingEnabled = true;
        this.mSnackbarLayouts = new HashSet<>();
    }

    public static <V extends View> BottomNavigationBehavior<V> from(@NonNull V v) {
        LayoutParams layoutParams = v.getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
        if (behavior instanceof BottomNavigationBehavior) {
            return (BottomNavigationBehavior) behavior;
        }
        throw new IllegalArgumentException("The view is not associated with BottomNavigationBehavior");
    }

    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, V v, View view) {
        this.mWithSnackBarImpl.a(coordinatorLayout, view, v);
        return view instanceof SnackbarLayout;
    }

    public void onDependentViewRemoved(CoordinatorLayout coordinatorLayout, V v, View view) {
        this.mSnackbarLayouts.remove(view);
        super.onDependentViewRemoved(coordinatorLayout, v, view);
    }

    public void onDirectionNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3) {
        handleDirection(v, i3);
    }

    private void handleDirection(V v, int i) {
        if (this.scrollingEnabled) {
            if (i == -1 && this.hidden) {
                this.hidden = false;
                animateOffset(v, 0);
            } else if (i == 1 && !this.hidden) {
                this.hidden = true;
                animateOffset(v, v.getHeight());
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean onNestedDirectionFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2, int i) {
        handleDirection(v, i);
        return true;
    }

    private void animateOffset(V v, int i) {
        ensureOrCancelAnimator(v);
        this.mOffsetValueAnimator.translationY((float) i).start();
    }

    private void ensureOrCancelAnimator(V v) {
        if (this.mOffsetValueAnimator == null) {
            this.mOffsetValueAnimator = ViewCompat.animate(v);
            this.mOffsetValueAnimator.setDuration(100);
            this.mOffsetValueAnimator.setInterpolator(INTERPOLATOR);
            if (VERSION.SDK_INT >= 21) {
                this.mOffsetValueAnimator.setUpdateListener(new ViewPropertyAnimatorUpdateListener() {
                    public void onAnimationUpdate(View view) {
                        Iterator it = BottomNavigationBehavior.this.mSnackbarLayouts.iterator();
                        while (it.hasNext()) {
                            ((View) it.next()).requestLayout();
                        }
                    }
                });
                return;
            }
            return;
        }
        this.mOffsetValueAnimator.cancel();
    }

    public boolean isScrollingEnabled() {
        return this.scrollingEnabled;
    }

    public void setScrollingEnabled(boolean z) {
        this.scrollingEnabled = z;
    }

    public void setHidden(V v, boolean z) {
        if (!z && this.hidden) {
            animateOffset(v, 0);
        } else if (z && !this.hidden) {
            animateOffset(v, -v.getHeight());
        }
        this.hidden = z;
    }
}
