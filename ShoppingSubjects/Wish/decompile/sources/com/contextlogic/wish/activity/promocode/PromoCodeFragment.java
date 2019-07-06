package com.contextlogic.wish.activity.promocode;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.ScrollView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;
import com.contextlogic.wish.ui.text.ThemedEditText;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.DisplayUtil;

public class PromoCodeFragment extends UiFragment<PromoCodeActivity> {
    private int mActionBarHeight;
    private ThemedTextView mApplyCouponButton;
    /* access modifiers changed from: private */
    public ThemedEditText mCouponCodeEditText;
    private ThemedTextView mMessage;
    /* access modifiers changed from: private */
    public ScrollView mScrollView;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.promo_code_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mCouponCodeEditText = (ThemedEditText) findViewById(R.id.promo_code_input);
        this.mMessage = (ThemedTextView) findViewById(R.id.promo_code_message);
        this.mScrollView = (ScrollView) findViewById(R.id.scrollview);
        this.mApplyCouponButton = (ThemedTextView) findViewById(R.id.promo_code_apply_button);
        this.mActionBarHeight = DisplayUtil.getActionBarHeight(getActivity());
        setupApplyCouponCodeButton();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_APPLY_PROMO_PAGE);
        this.mScrollView.getViewTreeObserver().addOnScrollChangedListener(new OnScrollChangedListener() {
            public void onScrollChanged() {
                PromoCodeFragment.this.onVerticalScrollPositionUpdated(PromoCodeFragment.this.mScrollView.getScrollY());
            }
        });
    }

    private void setupApplyCouponCodeButton() {
        this.mApplyCouponButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PromoCodeFragment.this.mCouponCodeEditText.getText() != null && PromoCodeFragment.this.mCouponCodeEditText.getText().length() > 0) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_APPLY_COUPON_ON_COUPON_PAGE);
                    PromoCodeFragment.this.withServiceFragment(new ServiceTask<PromoCodeActivity, PromoCodeServiceFragment>() {
                        public void performTask(PromoCodeActivity promoCodeActivity, PromoCodeServiceFragment promoCodeServiceFragment) {
                            promoCodeServiceFragment.applyPromoCodeService(PromoCodeFragment.this.mCouponCodeEditText.getText().toString());
                        }
                    });
                }
            }
        });
    }

    public void handleApplyPromoCodeFailure(String str) {
        if (str == null) {
            str = getString(R.string.promo_code_failed_to_apply);
        }
        updateMessage(str, true);
    }

    public void handleApplyPromoCodeSuccess(WishCart wishCart) {
        final String promoCodeDeeplink = wishCart.getPromoCodeDeeplink();
        if (promoCodeDeeplink == null || promoCodeDeeplink.trim().length() <= 0) {
            updateMessage(wishCart.getPromoCodeMessage(), false);
        } else {
            withActivity(new ActivityTask<PromoCodeActivity>() {
                public void performTask(PromoCodeActivity promoCodeActivity) {
                    DeepLinkManager.processDeepLink(promoCodeActivity, new DeepLink(promoCodeDeeplink));
                }
            });
        }
    }

    private void updateMessage(String str, boolean z) {
        if (this.mMessage != null) {
            if (z) {
                this.mMessage.setTextColor(getResources().getColor(R.color.red));
            } else {
                this.mMessage.setTextColor(getResources().getColor(R.color.green));
            }
            this.mMessage.setText(str);
            this.mMessage.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onVerticalScrollPositionUpdated(int i) {
        float f;
        ActionBarManager actionBarManager = getBaseActivity() == null ? null : ((PromoCodeActivity) getBaseActivity()).getActionBarManager();
        if (actionBarManager != null && actionBarManager.hasTransparentActionBar()) {
            int max = Math.max(0, i);
            if (max >= this.mActionBarHeight) {
                f = 1.0f;
            } else {
                f = ((float) max) / ((float) this.mActionBarHeight);
            }
            actionBarManager.interpolateBackground(f);
        }
    }
}
