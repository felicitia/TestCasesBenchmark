package com.contextlogic.wish.dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;

public class WishTooltip extends DialogFragment {
    /* access modifiers changed from: private */
    public WishTooltipListener mCallback;
    private Animator mForceHideAnim;
    /* access modifiers changed from: private */
    public Animator mHideAnim;
    private Animator mShowAnim;
    /* access modifiers changed from: private */
    public View mTargetView;
    /* access modifiers changed from: private */
    public Drawable mTargetViewOverlay;
    private TextView mTitleTextView;
    /* access modifiers changed from: private */
    public View mTooltip;
    /* access modifiers changed from: private */
    public View mViewPlaceholder;

    public interface WishTooltipListener {
        void clickedOutsideTooltip();

        void clickedTooltip();
    }

    public static WishTooltip make(String str, int i) {
        WishTooltip wishTooltip = new WishTooltip();
        Bundle bundle = new Bundle();
        bundle.putString("ArgumentTitle", str);
        bundle.putInt("ArgumentDuration", i);
        wishTooltip.setArguments(bundle);
        wishTooltip.setStyle(2, 2131820888);
        return wishTooltip;
    }

    public WishTooltip setTargetViewOverlay(Drawable drawable) {
        this.mTargetViewOverlay = drawable;
        return this;
    }

    public WishTooltip setCallback(WishTooltipListener wishTooltipListener) {
        this.mCallback = wishTooltipListener;
        return this;
    }

    public void showWhenReady(final BaseActivity baseActivity, final View view) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (view.getHeight() != 0) {
                    WishTooltip.this.mTargetView = view;
                    baseActivity.showTooltip(WishTooltip.this);
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.wish_tooltip, viewGroup);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mTooltip = view.findViewById(R.id.wish_tooltip_container);
        this.mViewPlaceholder = view.findViewById(R.id.wish_tooltip_view_placeholder);
        this.mTitleTextView = (TextView) view.findViewById(R.id.wish_tooltip_text);
        this.mTitleTextView.setText(getText());
        AnonymousClass2 r2 = new OnClickListener() {
            public void onClick(View view) {
                WishTooltip.this.animateForceHideTooltip();
                if (WishTooltip.this.mCallback != null) {
                    WishTooltip.this.mCallback.clickedTooltip();
                }
            }
        };
        AnonymousClass3 r3 = new OnClickListener() {
            public void onClick(View view) {
                WishTooltip.this.animateForceHideTooltip();
                if (WishTooltip.this.mCallback != null) {
                    WishTooltip.this.mCallback.clickedOutsideTooltip();
                }
            }
        };
        this.mViewPlaceholder.setOnClickListener(r2);
        this.mTitleTextView.setOnClickListener(r2);
        this.mTooltip.setOnClickListener(r3);
        this.mTooltip.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (!(WishTooltip.this.getDialog() == null || WishTooltip.this.getDialog().getWindow() == null || WishTooltip.this.mTargetView == null)) {
                    WishTooltip.this.setPosition(WishTooltip.this.getDialog().getWindow(), WishTooltip.this.mTargetView);
                    if (WishTooltip.this.mTargetViewOverlay != null) {
                        WishTooltip.this.mViewPlaceholder.setBackground(WishTooltip.this.mTargetViewOverlay);
                    }
                    WishTooltip.this.animateShowTooltip();
                }
                WishTooltip.this.mTooltip.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private String getText() {
        if (getArguments() != null) {
            return getArguments().getString("ArgumentTitle");
        }
        return null;
    }

    private int getDuration() {
        if (getArguments() != null) {
            return getArguments().getInt("ArgumentDuration");
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public void setPosition(Window window, View view) {
        int i;
        Rect rect = new Rect();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        Rect rect2 = new Rect();
        view.getGlobalVisibleRect(rect2);
        int i2 = rect.top;
        rect2.top -= i2;
        rect2.bottom -= i2;
        LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        window.setAttributes(attributes);
        boolean z = rect2.centerX() < rect.centerX();
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.mViewPlaceholder.getLayoutParams();
        layoutParams.width = rect2.width();
        layoutParams.height = rect2.height();
        if (z) {
            layoutParams.startToStart = 0;
        } else {
            layoutParams.endToEnd = 0;
        }
        this.mViewPlaceholder.setLayoutParams(layoutParams);
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.mTitleTextView.getLayoutParams();
        if (rect2.width() >= this.mTitleTextView.getWidth()) {
            layoutParams2.startToStart = R.id.wish_tooltip_view_placeholder;
            layoutParams2.endToEnd = R.id.wish_tooltip_view_placeholder;
        } else if (z) {
            layoutParams2.startToStart = R.id.wish_tooltip_tip;
        } else {
            layoutParams2.endToEnd = R.id.wish_tooltip_tip;
        }
        this.mTitleTextView.setLayoutParams(layoutParams2);
        View view2 = this.mTooltip;
        if (z) {
            i = rect2.left;
        } else {
            i = this.mTooltip.getPaddingLeft();
        }
        view2.setPadding(i, rect2.top, z ? this.mTooltip.getPaddingRight() : rect.right - rect2.right, this.mTooltip.getPaddingBottom());
    }

    private void initAnims(int i) {
        this.mShowAnim = ObjectAnimator.ofPropertyValuesHolder(this.mTooltip, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("alpha", new float[]{0.5f, 1.0f})});
        this.mHideAnim = ObjectAnimator.ofPropertyValuesHolder(this.mTooltip, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f, 0.0f})});
        this.mForceHideAnim = ObjectAnimator.ofPropertyValuesHolder(this.mTooltip, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f, 0.0f})});
        if (i != 2) {
            this.mShowAnim.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    WishTooltip.this.mHideAnim.start();
                }
            });
            this.mHideAnim.setStartDelay(i == 0 ? 500 : 1500);
        }
        this.mHideAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                WishTooltip.this.dismissAllowingStateLoss();
            }
        });
        this.mForceHideAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                WishTooltip.this.dismissAllowingStateLoss();
            }
        });
    }

    /* access modifiers changed from: private */
    public void animateShowTooltip() {
        if (this.mShowAnim == null) {
            initAnims(getDuration());
        }
        this.mShowAnim.start();
    }

    /* access modifiers changed from: private */
    public void animateForceHideTooltip() {
        if (this.mForceHideAnim == null || this.mHideAnim == null) {
            initAnims(getDuration());
        }
        if (!this.mHideAnim.isRunning() && !this.mForceHideAnim.isStarted()) {
            this.mHideAnim.removeAllListeners();
            this.mHideAnim.cancel();
            this.mForceHideAnim.start();
        }
    }

    public void onDetach() {
        super.onDetach();
        if (this.mShowAnim != null) {
            this.mShowAnim.removeAllListeners();
            this.mShowAnim.cancel();
        }
        if (this.mHideAnim != null) {
            this.mHideAnim.removeAllListeners();
            this.mHideAnim.cancel();
        }
        if (this.mForceHideAnim != null) {
            this.mForceHideAnim.removeAllListeners();
            this.mForceHideAnim.cancel();
        }
    }

    public static Drawable createSimpleCircleOverlay(Context context) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(0);
        gradientDrawable.setStroke(context.getResources().getDimensionPixelSize(R.dimen.two_padding), ContextCompat.getColor(context, R.color.main_primary));
        return gradientDrawable;
    }
}
