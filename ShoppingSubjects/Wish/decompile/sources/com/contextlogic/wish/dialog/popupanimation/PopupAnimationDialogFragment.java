package com.contextlogic.wish.dialog.popupanimation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.util.DisplayUtil;

public abstract class PopupAnimationDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    /* access modifiers changed from: private */
    public Runnable mAnimateDownwards = new Runnable() {
        public void run() {
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) PopupAnimationDialogFragment.this.mPopupHeight);
            translateAnimation.setDuration((long) PopupAnimationDialogFragment.this.getPopupTranslateLength());
            translateAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    if (PopupAnimationDialogFragment.this.mPopupHolder != null) {
                        PopupAnimationDialogFragment.this.mPopupHolder.setVisibility(8);
                    }
                    PopupAnimationDialogFragment.this.cancel();
                    PopupAnimationDialogFragment.this.onPopOutEnded();
                }
            });
            PopupAnimationDialogFragment.this.mPopupHolder.startAnimation(translateAnimation);
        }
    };
    /* access modifiers changed from: private */
    public int mPopupHeight;
    protected ViewGroup mPopupHolder;

    public int getGravity() {
        return 81;
    }

    /* access modifiers changed from: protected */
    public abstract int getLayout();

    /* access modifiers changed from: protected */
    public OnClickListener getPopupClickListener() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract int getPopupHeight();

    /* access modifiers changed from: protected */
    public abstract ViewGroup getPopupHolder(View view);

    /* access modifiers changed from: protected */
    public int getPopupShowLength() {
        return 2000;
    }

    /* access modifiers changed from: protected */
    public int getPopupTranslateLength() {
        return 300;
    }

    /* access modifiers changed from: protected */
    public abstract View getPopupView();

    /* access modifiers changed from: protected */
    public abstract void handleArguments(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onPopInEnded() {
    }

    /* access modifiers changed from: protected */
    public void onPopOutEnded() {
    }

    /* access modifiers changed from: protected */
    public boolean shouldAnimateDown() {
        return true;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        handleArguments(getArguments());
        View inflate = layoutInflater.inflate(getLayout(), viewGroup, false);
        this.mPopupHolder = getPopupHolder(inflate);
        View popupView = getPopupView();
        popupView.setOnClickListener(getPopupClickListener());
        this.mPopupHolder.addView(popupView);
        performPopupAnimation();
        return inflate;
    }

    public int getDialogWidth() {
        int displayWidth = DisplayUtil.getDisplayWidth();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.bottom_dialog_fragment_max_width);
        return displayWidth > dimensionPixelSize ? dimensionPixelSize : displayWidth;
    }

    /* access modifiers changed from: protected */
    public void performPopupAnimation() {
        this.mPopupHolder.setVisibility(8);
        this.mPopupHeight = getPopupHeight();
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) this.mPopupHeight, 0.0f);
        translateAnimation.setDuration((long) getPopupTranslateLength());
        translateAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                PopupAnimationDialogFragment.this.mPopupHolder.setVisibility(0);
            }

            public void onAnimationEnd(Animation animation) {
                if (PopupAnimationDialogFragment.this.shouldAnimateDown()) {
                    PopupAnimationDialogFragment.this.getHandler().postDelayed(PopupAnimationDialogFragment.this.mAnimateDownwards, (long) PopupAnimationDialogFragment.this.getPopupShowLength());
                }
                PopupAnimationDialogFragment.this.onPopInEnded();
            }
        });
        this.mPopupHolder.startAnimation(translateAnimation);
    }
}
