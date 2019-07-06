package com.contextlogic.wish.dialog.promotion;

import android.graphics.PorterDuff.Mode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishPromotionNoCouponSpec.SplashSpec;
import com.contextlogic.wish.api.model.WishTextViewSpec;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.ColorUtil;
import com.contextlogic.wish.util.DisplayUtil;

public class SplashPromotionNoCouponView extends RelativeLayout implements ImageRestorable {
    private Button mActionButton;
    private NetworkImageView mBackgroundImage;
    private AutoReleasableImageView mCancel;
    private View mDividerView;
    /* access modifiers changed from: private */
    public BaseDialogFragment mFragment;
    private RelativeLayout mMainContainer;
    private ThemedTextView mPromoHeader;
    private ThemedTextView mPromoSubtext;
    private ThemedTextView mPromoText;
    private ThemedTextView mSubtitle;
    private ThemedTextView mTitle;

    public SplashPromotionNoCouponView(BaseDialogFragment baseDialogFragment) {
        super(baseDialogFragment.getContext());
        init();
        this.mFragment = baseDialogFragment;
    }

    private void init() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        if (layoutInflater != null) {
            View inflate = layoutInflater.inflate(R.layout.promotion_dialog_no_coupon_splash_view, this);
            this.mBackgroundImage = (NetworkImageView) inflate.findViewById(R.id.promotion_dialog_no_coupon_fragment_background);
            this.mCancel = (AutoReleasableImageView) inflate.findViewById(R.id.promotion_dialog_no_coupon_fragment_x);
            this.mTitle = (ThemedTextView) inflate.findViewById(R.id.promotion_dialog_no_coupon_fragment_title);
            this.mSubtitle = (ThemedTextView) inflate.findViewById(R.id.promotion_dialog_no_coupon_fragment_subtitle);
            this.mPromoHeader = (ThemedTextView) inflate.findViewById(R.id.promotion_dialog_no_coupon_fragment_promo_header_text);
            this.mPromoText = (ThemedTextView) inflate.findViewById(R.id.promotion_dialog_no_coupon_fragment_promo_text);
            this.mPromoSubtext = (ThemedTextView) inflate.findViewById(R.id.promotion_dialog_no_coupon_fragment_promo_subtext);
            this.mDividerView = inflate.findViewById(R.id.promotion_dialog_no_coupon_fragment_divider);
            this.mActionButton = (Button) inflate.findViewById(R.id.promotion_dialog_no_coupon_fragment_main_button);
            this.mMainContainer = (RelativeLayout) inflate.findViewById(R.id.promotion_dialog_no_coupon_fragment_main_container);
        }
    }

    public void setup(final SplashSpec splashSpec) {
        if (splashSpec.getBackgroundColor() != null) {
            this.mMainContainer.setBackgroundColor(ColorUtil.safeParseColor(splashSpec.getBackgroundColor(), -16776961));
        }
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.mTitle.getLayoutParams();
        int dimensionPixelSize = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.eight_padding);
        int dimensionPixelSize2 = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
        marginLayoutParams.setMargins(dimensionPixelSize2, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.fourty_padding) + DisplayUtil.getStatusBarHeight(), dimensionPixelSize2, dimensionPixelSize);
        String backgroundImageUrl = splashSpec.getBackgroundImageUrl();
        if (backgroundImageUrl != null) {
            this.mBackgroundImage.setImage(new WishImage(backgroundImageUrl));
        }
        if (splashSpec.getTextColor() != null) {
            int safeParseColor = ColorUtil.safeParseColor(splashSpec.getTextColor(), -1);
            this.mTitle.setTextColor(safeParseColor);
            this.mSubtitle.setTextColor(safeParseColor);
            this.mPromoHeader.setTextColor(safeParseColor);
            this.mPromoText.setTextColor(safeParseColor);
            this.mPromoSubtext.setTextColor(safeParseColor);
            this.mCancel.setColorFilter(safeParseColor);
            this.mDividerView.setBackgroundColor(safeParseColor);
        }
        WishTextViewSpec.applyTextViewSpec(this.mTitle, splashSpec.getTitle());
        WishTextViewSpec.applyTextViewSpec(this.mSubtitle, splashSpec.getSubtitle());
        WishTextViewSpec.applyTextViewSpec(this.mPromoHeader, splashSpec.getPromoHeader());
        WishTextViewSpec.applyTextViewSpec(this.mPromoText, splashSpec.getPromoText());
        WishTextViewSpec.applyTextViewSpec(this.mPromoSubtext, splashSpec.getPromoSubtext());
        if (splashSpec.getPromoHeader() == null || !splashSpec.isDividerVisible()) {
            this.mDividerView.setVisibility(8);
        } else {
            this.mDividerView.setVisibility(0);
        }
        WishTextViewSpec.applyTextViewSpec(this.mActionButton, splashSpec.getMainButton());
        if (!(splashSpec.getButtonColor() == null || splashSpec.getButtonColor().isEmpty() || this.mActionButton.getBackground() == null)) {
            int safeParseColor2 = ColorUtil.safeParseColor(splashSpec.getButtonColor(), 0);
            if (safeParseColor2 != 0) {
                this.mActionButton.getBackground().setColorFilter(safeParseColor2, Mode.MULTIPLY);
            }
        }
        this.mCancel.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SplashPromotionNoCouponView.this.dismiss();
            }
        });
        this.mActionButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SplashPromotionNoCouponView.this.mainButtonOnClick(splashSpec);
            }
        });
    }

    /* access modifiers changed from: private */
    public void mainButtonOnClick(final SplashSpec splashSpec) {
        if (this.mFragment != null) {
            this.mFragment.withActivity(new ActivityTask<BaseActivity>() {
                public void performTask(BaseActivity baseActivity) {
                    String buttonDeeplink = splashSpec.getButtonDeeplink();
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PROMO_SPLASH_BUTTON);
                    if (buttonDeeplink == null || buttonDeeplink.trim().length() <= 0) {
                        SplashPromotionNoCouponView.this.mFragment.dismiss();
                    } else {
                        DeepLinkManager.processDeepLink(baseActivity, new DeepLink(buttonDeeplink));
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void dismiss() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PROMO_SPLASH_X);
        if (this.mFragment != null) {
            this.mFragment.dismiss();
        }
    }

    public void releaseImages() {
        if (this.mBackgroundImage != null) {
            this.mBackgroundImage.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mBackgroundImage != null) {
            this.mBackgroundImage.restoreImages();
        }
    }
}
