package com.contextlogic.wish.activity.wishpartner.info;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.ScrollView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarItem;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.wishpartner.dashboard.WishPartnerDashboardActivity;
import com.contextlogic.wish.activity.wishpartner.learnmore.WishPartnerLearnMoreActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishPartnerInfoSpec;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.ClipboardUtil;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.IntentUtil;

public class WishPartnerInfoFragment extends UiFragment<WishPartnerInfoActivity> {
    private int mActionBarHeight;
    private ThemedTextView mApplyPromoExample;
    private View mContent;
    /* access modifiers changed from: private */
    public ThemedTextView mCouponCodeCopyButton;
    private View mCouponCodeShareButton;
    private ThemedTextView mCouponCodeText;
    private ThemedTextView mDashboardLink;
    private ThemedTextView mInstructionsHeader;
    private ThemedTextView mPriceTextView;
    /* access modifiers changed from: private */
    public WishImage mProductImage;
    private NetworkImageView mProductImageView;
    /* access modifiers changed from: private */
    public String mProductPrice;
    /* access modifiers changed from: private */
    public View mProgressView;
    /* access modifiers changed from: private */
    public ScrollView mScrollview;
    private ThemedTextView mStepOneText;
    private ThemedTextView mStepThreeText;
    private ThemedTextView mStepTwoText;
    private ThemedTextView mSubtitleText;
    private ThemedTextView mTitleText;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.wish_partner_info_fragment;
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        withActivity(new ActivityTask<WishPartnerInfoActivity>() {
            public void performTask(WishPartnerInfoActivity wishPartnerInfoActivity) {
                final String productId = wishPartnerInfoActivity.getProductId();
                WishPartnerInfoFragment.this.withServiceFragment(new ServiceTask<BaseActivity, WishPartnerInfoServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerInfoServiceFragment wishPartnerInfoServiceFragment) {
                        wishPartnerInfoServiceFragment.loadWishProductInfo(productId);
                    }
                });
                if (wishPartnerInfoActivity.getProductImage() != null) {
                    WishPartnerInfoFragment.this.mProductImage = new WishImage(wishPartnerInfoActivity.getProductImage());
                }
                WishPartnerInfoFragment.this.mProductPrice = wishPartnerInfoActivity.getProductPrice();
            }
        });
        this.mContent = findViewById(R.id.wish_partner_info_content);
        this.mProgressView = findViewById(R.id.wish_partner_info_progress);
        if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
            View findViewById = this.mProgressView.findViewById(R.id.wish_partner_info_primary_progress);
            View findViewById2 = this.mProgressView.findViewById(R.id.wish_partner_info_three_dot_progress);
            if (!(findViewById2 == null || findViewById == null)) {
                findViewById.setVisibility(8);
                findViewById2.setVisibility(0);
            }
        }
        this.mProductImageView = (NetworkImageView) findViewById(R.id.wish_partner_info_product_image);
        this.mPriceTextView = (ThemedTextView) findViewById(R.id.wish_partner_info_product_price);
        if (this.mProductImage != null) {
            this.mProductImageView.setImage(this.mProductImage);
        }
        this.mPriceTextView.setText(this.mProductPrice);
        this.mTitleText = (ThemedTextView) findViewById(R.id.wish_partner_info_title);
        this.mSubtitleText = (ThemedTextView) findViewById(R.id.wish_partner_info_body);
        this.mInstructionsHeader = (ThemedTextView) findViewById(R.id.wish_partner_instructions_header);
        this.mStepOneText = (ThemedTextView) findViewById(R.id.wish_partner_step_1);
        this.mStepTwoText = (ThemedTextView) findViewById(R.id.wish_partner_step_2);
        this.mStepThreeText = (ThemedTextView) findViewById(R.id.wish_partner_step_3);
        this.mCouponCodeText = (ThemedTextView) findViewById(R.id.wish_partner_coupon_code_text);
        this.mCouponCodeCopyButton = (ThemedTextView) findViewById(R.id.wish_partner_coupon_copy_text);
        this.mCouponCodeShareButton = findViewById(R.id.wish_partner_coupon_share_button);
        this.mApplyPromoExample = (ThemedTextView) findViewById(R.id.wish_partner_menu_apply_promo_button);
        this.mDashboardLink = (ThemedTextView) findViewById(R.id.wish_partner_link_to_dashboard);
        this.mScrollview = (ScrollView) findViewById(R.id.wish_partner_info_scrollview);
        this.mActionBarHeight = DisplayUtil.getActionBarHeight(getActivity());
        showProgressSpinner();
        withActivity(new ActivityTask<WishPartnerInfoActivity>() {
            public void performTask(WishPartnerInfoActivity wishPartnerInfoActivity) {
                ActionBarManager actionBarManager = wishPartnerInfoActivity.getActionBarManager();
                actionBarManager.addActionBarItem(ActionBarItem.createLeranMoreBarItem(actionBarManager));
            }
        });
        this.mScrollview.getViewTreeObserver().addOnScrollChangedListener(new OnScrollChangedListener() {
            public void onScrollChanged() {
                WishPartnerInfoFragment.this.onVerticalScrollPositionUpdated(WishPartnerInfoFragment.this.mScrollview.getScrollY());
            }
        });
    }

    public void releaseImages() {
        this.mProductImageView.releaseImages();
    }

    public void restoreImages() {
        this.mProductImageView.restoreImages();
    }

    public void showProgressSpinner() {
        this.mProgressView.setVisibility(0);
        this.mContent.setVisibility(8);
    }

    public void hideProgressSpinner() {
        this.mProgressView.setVisibility(8);
        this.mContent.setVisibility(0);
    }

    public void handleLoadingInfoSuccess(final WishPartnerInfoSpec wishPartnerInfoSpec) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_WISH_PARTNER_GENERATE_CODE_VIEW);
        this.mTitleText.setText(wishPartnerInfoSpec.getTitle());
        this.mSubtitleText.setText(wishPartnerInfoSpec.getSubtitle());
        this.mInstructionsHeader.setText(wishPartnerInfoSpec.getInstructionsHeader());
        this.mStepOneText.setText(wishPartnerInfoSpec.getStepOneText());
        this.mStepTwoText.setText(wishPartnerInfoSpec.getStepTwoText());
        this.mStepThreeText.setText(wishPartnerInfoSpec.getStepThreeText());
        this.mCouponCodeText.setText(wishPartnerInfoSpec.getCouponCode());
        this.mApplyPromoExample.setText(wishPartnerInfoSpec.getMenuItemLable());
        if (wishPartnerInfoSpec.getDashboardLinkText() != null) {
            this.mDashboardLink.setText(wishPartnerInfoSpec.getDashboardLinkText());
            this.mDashboardLink.setVisibility(0);
            this.mDashboardLink.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishPartnerInfoFragment.this.withActivity(new ActivityTask<WishPartnerInfoActivity>() {
                        public void performTask(WishPartnerInfoActivity wishPartnerInfoActivity) {
                            Intent intent = new Intent();
                            intent.setClass(wishPartnerInfoActivity, WishPartnerDashboardActivity.class);
                            intent.putExtra("ExtraShowBackButton", true);
                            wishPartnerInfoActivity.startActivity(intent);
                        }
                    });
                }
            });
        } else {
            this.mDashboardLink.setVisibility(8);
        }
        this.mCouponCodeCopyButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (ClipboardUtil.copyToClipboard(wishPartnerInfoSpec.getCouponCode())) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_INFO_COPY_CODE);
                    WishPartnerInfoFragment.this.mCouponCodeCopyButton.setText(R.string.copied_exclamation);
                }
            }
        });
        if (TextUtils.isEmpty(wishPartnerInfoSpec.getShareMessage())) {
            this.mCouponCodeShareButton.setVisibility(8);
        } else {
            this.mCouponCodeShareButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_INFO_SHARE_CODE);
                    Intent shareIntent = IntentUtil.getShareIntent(null, wishPartnerInfoSpec.getShareMessage());
                    if (shareIntent != null && WishPartnerInfoFragment.this.getContext() != null) {
                        WishPartnerInfoFragment.this.getContext().startActivity(shareIntent);
                    }
                }
            });
        }
        hideProgressSpinner();
    }

    public boolean handleActionBarItemSelected(int i) {
        if (i != 2002) {
            return super.handleActionBarItemSelected(i);
        }
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_INFO_FRAGMENT_LEARN_MORE);
        withActivity(new ActivityTask<WishPartnerInfoActivity>() {
            public void performTask(WishPartnerInfoActivity wishPartnerInfoActivity) {
                Intent intent = new Intent();
                intent.setClass(wishPartnerInfoActivity, WishPartnerLearnMoreActivity.class);
                wishPartnerInfoActivity.startActivity(intent);
            }
        });
        return true;
    }

    /* access modifiers changed from: protected */
    public void onVerticalScrollPositionUpdated(int i) {
        float f;
        ActionBarManager actionBarManager = getBaseActivity() == null ? null : ((WishPartnerInfoActivity) getBaseActivity()).getActionBarManager();
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

    public void handleLoadingInfoFailure() {
        getHandler().post(new Runnable() {
            public void run() {
                WishPartnerInfoFragment.this.mProgressView.setVisibility(8);
            }
        });
    }
}
