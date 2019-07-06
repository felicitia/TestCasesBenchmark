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
import com.contextlogic.wish.api.model.WishPromotionCouponSpec.SplashSpec;
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

public class SplashPromotionCouponView extends RelativeLayout implements ImageRestorable {
    private Button mActionButton;
    private NetworkImageView mBackgroundImage;
    private ThemedTextView mBody;
    private AutoReleasableImageView mCancel;
    private ThemedTextView mCouponCode;
    private ThemedTextView mExpiryDate;
    /* access modifiers changed from: private */
    public BaseDialogFragment mFragment;
    private RelativeLayout mMainContainer;
    private ThemedTextView mSubtitle;
    private ThemedTextView mTitle;

    public SplashPromotionCouponView(BaseDialogFragment baseDialogFragment) {
        super(baseDialogFragment.getContext());
        this.mFragment = baseDialogFragment;
        init();
    }

    private void init() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        if (layoutInflater != null) {
            View inflate = layoutInflater.inflate(R.layout.promotion_dialog_coupon_splash_view, this);
            this.mMainContainer = (RelativeLayout) inflate.findViewById(R.id.promotion_dialog_coupon_fragment_main_container);
            this.mBackgroundImage = (NetworkImageView) inflate.findViewById(R.id.promotion_dialog_coupon_fragment_background);
            this.mCancel = (AutoReleasableImageView) inflate.findViewById(R.id.promotion_dialog_coupon_fragment_x);
            this.mTitle = (ThemedTextView) inflate.findViewById(R.id.promotion_dialog_coupon_fragment_title);
            this.mSubtitle = (ThemedTextView) inflate.findViewById(R.id.promotion_dialog_coupon_fragment_subtitle);
            this.mBody = (ThemedTextView) inflate.findViewById(R.id.promotion_dialog_coupon_fragment_body);
            this.mCouponCode = (ThemedTextView) inflate.findViewById(R.id.promotion_dialog_coupon_fragment_code);
            this.mExpiryDate = (ThemedTextView) inflate.findViewById(R.id.promotion_dialog_coupon_fragment_expiry_date);
            this.mActionButton = (Button) inflate.findViewById(R.id.promotion_dialog_coupon_fragment_main_button);
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
        if (splashSpec.getBackgroundImageUrl() != null) {
            this.mBackgroundImage.setImage(new WishImage(splashSpec.getBackgroundImageUrl()));
        }
        if (splashSpec.getTextColor() != null) {
            int safeParseColor = ColorUtil.safeParseColor(splashSpec.getTextColor(), -1);
            this.mTitle.setTextColor(safeParseColor);
            this.mSubtitle.setTextColor(safeParseColor);
            this.mBody.setTextColor(safeParseColor);
            this.mCouponCode.setTextColor(safeParseColor);
            if (this.mCouponCode.getBackground() != null) {
                this.mCouponCode.getBackground().setColorFilter(safeParseColor, Mode.MULTIPLY);
            }
            this.mExpiryDate.setTextColor(safeParseColor);
            this.mCancel.setColorFilter(safeParseColor);
        }
        WishTextViewSpec.applyTextViewSpec(this.mTitle, splashSpec.getTitle());
        WishTextViewSpec.applyTextViewSpec(this.mSubtitle, splashSpec.getSubtitle());
        WishTextViewSpec.applyTextViewSpec(this.mBody, splashSpec.getBody());
        WishTextViewSpec.applyTextViewSpec(this.mCouponCode, splashSpec.getPromoCode());
        WishTextViewSpec.applyTextViewSpec(this.mExpiryDate, splashSpec.getExpiryText());
        WishTextViewSpec.applyTextViewSpec(this.mActionButton, splashSpec.getMainButton());
        if (!(splashSpec.getButtonColor() == null || splashSpec.getButtonColor().isEmpty() || this.mActionButton.getBackground() == null)) {
            int safeParseColor2 = ColorUtil.safeParseColor(splashSpec.getButtonColor(), 0);
            if (safeParseColor2 != 0) {
                this.mActionButton.getBackground().setColorFilter(safeParseColor2, Mode.MULTIPLY);
            }
        }
        this.mCancel.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SplashPromotionCouponView.this.dismiss();
            }
        });
        this.mActionButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SplashPromotionCouponView.this.mainButtonOnClick(splashSpec);
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
                        SplashPromotionCouponView.this.mFragment.dismiss();
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
