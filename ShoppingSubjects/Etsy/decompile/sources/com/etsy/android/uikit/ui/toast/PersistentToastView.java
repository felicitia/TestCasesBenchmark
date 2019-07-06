package com.etsy.android.uikit.ui.toast;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import com.etsy.android.lib.a.i;

public class PersistentToastView extends RelativeLayout {
    /* access modifiers changed from: private */
    public boolean mAllowDismiss;
    /* access modifiers changed from: private */
    public boolean mAnimating = false;
    private boolean mIsFirstMeasure = true;
    private int mPopupHeight;
    protected a mToast;
    protected View mToastPopupContainer;

    /* access modifiers changed from: protected */
    public void setActionClickListener(OnClickListener onClickListener) {
    }

    /* access modifiers changed from: protected */
    public void setDismissClickListener(OnClickListener onClickListener) {
    }

    public PersistentToastView(Context context) {
        super(context);
    }

    public PersistentToastView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PersistentToastView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mToastPopupContainer = findViewById(i.popup_container);
    }

    /* access modifiers changed from: 0000 */
    public void setPersistentToastPopup(a aVar) {
        this.mToast = aVar;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mAllowDismiss) {
            this.mToast.d();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mPopupHeight = findViewById(i.popup_container).getMeasuredHeight();
        if (this.mIsFirstMeasure) {
            hide();
            this.mIsFirstMeasure = false;
        }
    }

    public void show() {
        setAlpha(1.0f);
        setTranslationY(0.0f);
        onViewShown();
    }

    public void hide() {
        setAlpha(0.0f);
        setTranslationY((float) this.mPopupHeight);
        this.mAllowDismiss = false;
    }

    public void animateIn(final long j) {
        if (!this.mAnimating) {
            this.mAnimating = true;
            post(new Runnable() {
                public void run() {
                    PersistentToastView.this.animate().cancel();
                    PersistentToastView.this.animate().translationY(0.0f).alpha(1.0f).setDuration(j).setListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            PersistentToastView.this.mAnimating = false;
                            PersistentToastView.this.setVisibility(0);
                        }
                    });
                }
            });
            onViewShown();
        }
    }

    public void animateOut(long j) {
        if (!this.mAnimating) {
            this.mAnimating = true;
            animate().cancel();
            animate().translationY((float) this.mPopupHeight).alpha(0.0f).setDuration(j).setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    PersistentToastView.this.mAnimating = false;
                    PersistentToastView.this.setVisibility(8);
                }
            });
            this.mAllowDismiss = false;
        }
    }

    private void onViewShown() {
        postDelayed(new Runnable() {
            public void run() {
                PersistentToastView.this.mAllowDismiss = true;
            }
        }, 2000);
    }
}
