package com.contextlogic.wish.activity.ugcfeedback;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.ugcfeedback.UgcFeedbackHeaderView.HeaderTheme;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishLoginActionUgcFeedback;
import com.contextlogic.wish.api.model.WishUgcRatableProduct;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;
import java.util.Iterator;

public class UgcFeedbackDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    private final int ANIMATION_START_UP_DELAY = 300;
    private final int COUNTER_ADDITIONAL_DURATION = 50;
    private final int COUNTER_INITIAL_DURATION = 300;
    private final float INTERPOLATION_FACTOR = 2.0f;
    private final int MID_ANIMATION_DURATION = 1000;
    private final int SHORT_ANIMATION_DURATION = 750;
    private ThemedButton mActionButton;
    /* access modifiers changed from: private */
    public UgcFeedbackHeaderView mAnimatedHeader;
    private ArrayList<Animator> mAnims;
    private LinearLayout mContentContainer;
    private ThemedTextView mDismissText;
    /* access modifiers changed from: private */
    public UgcFeedbackHeaderView mHeaderView;
    private ThemedTextView mProductDescription;
    private NetworkImageView mProductImage;
    /* access modifiers changed from: private */
    public WishUgcRatableProduct mRateableProduct;
    private WishLoginActionUgcFeedback mUgcFeedback;

    public static UgcFeedbackDialogFragment<BaseActivity> createUgcDialogFragment(WishLoginActionUgcFeedback wishLoginActionUgcFeedback) {
        Bundle bundle = new Bundle();
        if (wishLoginActionUgcFeedback == null) {
            return null;
        }
        bundle.putParcelable("ArgumentUgc", wishLoginActionUgcFeedback);
        UgcFeedbackDialogFragment<BaseActivity> ugcFeedbackDialogFragment = new UgcFeedbackDialogFragment<>();
        ugcFeedbackDialogFragment.setArguments(bundle);
        return ugcFeedbackDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mUgcFeedback = (WishLoginActionUgcFeedback) getArguments().getParcelable("ArgumentUgc");
        if (this.mUgcFeedback == null) {
            return null;
        }
        this.mRateableProduct = this.mUgcFeedback.getRatableProduct();
        if (this.mRateableProduct == null) {
            return null;
        }
        View inflate = layoutInflater.inflate(R.layout.ugc_feedback_dialog_fragment, viewGroup, false);
        this.mAnimatedHeader = (UgcFeedbackHeaderView) inflate.findViewById(R.id.ugc_feedback_header_animated);
        this.mHeaderView = (UgcFeedbackHeaderView) inflate.findViewById(R.id.ugc_feedback_header);
        this.mContentContainer = (LinearLayout) inflate.findViewById(R.id.ugc_feedback_content_container);
        this.mProductImage = (NetworkImageView) inflate.findViewById(R.id.ugc_feedback_rate_product_image);
        this.mProductDescription = (ThemedTextView) inflate.findViewById(R.id.ugc_feedback_rate_product_description);
        this.mActionButton = (ThemedButton) inflate.findViewById(R.id.ugc_feedback_rate_product_button);
        this.mDismissText = (ThemedTextView) inflate.findViewById(R.id.ugc_feedback_dismiss_text);
        this.mAnims = new ArrayList<>();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_UGC_POPUP_SHOWN);
        setUpHeaders();
        animateHeader();
        return inflate;
    }

    private void setUpHeaders() {
        this.mHeaderView.setTheme(HeaderTheme.YELLOW);
        this.mAnimatedHeader.setTheme(HeaderTheme.YELLOW);
        this.mHeaderView.setup(this.mUgcFeedback.getTotalViews(), this.mUgcFeedback.getIntervalText(), this.mUgcFeedback.getDescriptionString(), false);
        this.mAnimatedHeader.setup(this.mUgcFeedback.getTotalViews(), this.mUgcFeedback.getIntervalText(), this.mUgcFeedback.getDescriptionString(), true);
        this.mHeaderView.setContentVisibility(4);
    }

    private void animateHeader() {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.mAnimatedHeader, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scaleX", new float[]{0.2f, 1.25f}), PropertyValuesHolder.ofFloat("scaleY", new float[]{0.2f, 1.25f}), PropertyValuesHolder.ofFloat("alpha", new float[]{0.0f, 1.0f})});
        ofPropertyValuesHolder.setInterpolator(new AccelerateInterpolator(2.0f));
        ofPropertyValuesHolder.setDuration(750);
        ofPropertyValuesHolder.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                UgcFeedbackDialogFragment.this.animateCounter();
            }
        });
        ofPropertyValuesHolder.start();
        this.mAnims.add(ofPropertyValuesHolder);
    }

    /* access modifiers changed from: private */
    public void setUpFinalDialog() {
        this.mHeaderView.reset();
        this.mContentContainer.setBackgroundResource(R.color.white);
        setUpRatableProduct();
        animateContainerFadeIn();
        animateCounterFloatUp();
    }

    private void animateCounterFloatUp() {
        this.mContentContainer.setVisibility(0);
        int height = this.mContentContainer.getHeight();
        if (getResources() != null) {
            int i = getResources().getDisplayMetrics().heightPixels;
            int i2 = ((-(i - this.mAnimatedHeader.getHeight())) / 2) + ((i - height) / 2);
            ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.mAnimatedHeader, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scaleX", new float[]{1.25f, 1.0f}), PropertyValuesHolder.ofFloat("scaleY", new float[]{1.25f, 1.0f}), PropertyValuesHolder.ofFloat("translationY", new float[]{0.0f, (float) i2})});
            ofPropertyValuesHolder.setStartDelay(300);
            ofPropertyValuesHolder.setInterpolator(new AccelerateInterpolator(2.0f));
            ofPropertyValuesHolder.setDuration(750);
            ofPropertyValuesHolder.start();
            this.mAnims.add(ofPropertyValuesHolder);
            return;
        }
        cancel();
    }

    private void animateContainerFadeIn() {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.mContentContainer, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("alpha", new float[]{0.0f, 1.0f})});
        ofPropertyValuesHolder.setStartDelay(300);
        ofPropertyValuesHolder.setDuration(1000);
        ofPropertyValuesHolder.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                UgcFeedbackDialogFragment.this.mHeaderView.setContentVisibility(0);
                UgcFeedbackDialogFragment.this.mHeaderView.animateSparkles();
                UgcFeedbackDialogFragment.this.mAnimatedHeader.setContentVisibility(4);
            }
        });
        ofPropertyValuesHolder.start();
        this.mAnims.add(ofPropertyValuesHolder);
    }

    private void setUpRatableProduct() {
        if (this.mRateableProduct.getProductImageUrl() != null && !this.mRateableProduct.getProductImageUrl().isEmpty()) {
            this.mProductImage.setImageUrl(this.mRateableProduct.getProductImageUrl());
        }
        this.mProductDescription.setText(this.mRateableProduct.getProductDescription());
        this.mActionButton.setText(this.mRateableProduct.getActionString());
        this.mActionButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_UGC_WRITE_REVIEW, UgcFeedbackDialogFragment.this.mRateableProduct.getProductId());
                UgcFeedbackDialogFragment.this.withActivity(new ActivityTask<A>() {
                    public void performTask(BaseActivity baseActivity) {
                        if (UgcFeedbackDialogFragment.this.mRateableProduct.getActionDeepLink() != null) {
                            DeepLinkManager.processDeepLink(baseActivity, new DeepLink(UgcFeedbackDialogFragment.this.mRateableProduct.getActionDeepLink()));
                            UgcFeedbackDialogFragment.this.cancel();
                        }
                    }
                });
            }
        });
        this.mDismissText.setText(this.mRateableProduct.getDismissString());
        this.mDismissText.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_UGC_DISMISS);
                UgcFeedbackDialogFragment.this.cancel();
            }
        });
    }

    public void animateCounter() {
        int numElements = this.mAnimatedHeader.getNumElements();
        int[] iArr = new int[numElements];
        int totalCount = this.mAnimatedHeader.getTotalCount();
        for (int i = numElements - 1; i >= 0; i--) {
            iArr[i] = totalCount % 10;
            totalCount /= 10;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        final int i2 = 0;
        while (i2 < numElements) {
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, iArr[i2] + 10});
            ofInt.setDuration((long) ((i2 * 50) + 300));
            int i3 = i2 + 1;
            ofInt.setRepeatCount(i3);
            ofInt.setInterpolator(null);
            ofInt.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    UgcFeedbackDialogFragment.this.mAnimatedHeader.updateCounterElementTextAtIndex(i2, Integer.toString(((Integer) valueAnimator.getAnimatedValue()).intValue() % 10));
                }
            });
            arrayList.add(ofInt);
            i2 = i3;
        }
        animatorSet.playTogether(arrayList);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                UgcFeedbackDialogFragment.this.setUpFinalDialog();
            }
        });
        this.mAnims.add(animatorSet);
        animatorSet.start();
    }

    public void onStart() {
        super.onStart();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(getDialog().getWindow(), "dimAmount", new float[]{0.0f, 1.0f});
        ofFloat.setDuration(750);
        ofFloat.start();
        if (this.mAnims != null) {
            this.mAnims.add(ofFloat);
        }
    }

    public void onDetach() {
        super.onDetach();
        if (this.mAnims != null && !this.mAnims.isEmpty()) {
            Iterator it = this.mAnims.iterator();
            while (it.hasNext()) {
                Animator animator = (Animator) it.next();
                animator.removeAllListeners();
                animator.cancel();
            }
            this.mAnims.clear();
        }
        this.mHeaderView.clearAnimations();
    }
}
