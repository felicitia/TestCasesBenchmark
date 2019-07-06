package com.contextlogic.wish.activity.feed.promotion;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.feed.BaseFeedHeaderView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishPromotionBaseSpec.PromoActionType;
import com.contextlogic.wish.api.model.WishPromotionCouponSpec.BannerSpec;
import com.contextlogic.wish.api.model.WishTextViewSpec;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.ColorUtil;

public class PromotionBannerCouponHeaderView extends BaseFeedHeaderView implements ImageRestorable {
    private LinearLayout mActionContainer;
    private ThemedTextView mActionTextView;
    private NetworkImageView mBackgroundImage;
    private AutoReleasableImageView mChevron;
    private ThemedTextView mExpiryText;
    private ThemedTextView mPromoCode;
    private ThemedTextView mSubtitle;
    private ThemedTextView mTitle;

    public PromotionBannerCouponHeaderView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.promotion_banner_coupon_view, this);
        this.mBackgroundImage = (NetworkImageView) inflate.findViewById(R.id.promotion_banner_view_background);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.promotion_banner_view_title);
        this.mSubtitle = (ThemedTextView) inflate.findViewById(R.id.promotion_banner_view_subtitle);
        this.mPromoCode = (ThemedTextView) inflate.findViewById(R.id.promotion_banner_view_coupon_code);
        this.mExpiryText = (ThemedTextView) inflate.findViewById(R.id.promotion_banner_view_expiry_text);
        this.mActionContainer = (LinearLayout) inflate.findViewById(R.id.promotion_banner_view_action_container);
        this.mActionTextView = (ThemedTextView) inflate.findViewById(R.id.promotion_banner_view_action_text);
        this.mChevron = (AutoReleasableImageView) inflate.findViewById(R.id.promotion_banner_view_sale_chevron);
    }

    public void setup(BannerSpec bannerSpec, WishAnalyticsEvent wishAnalyticsEvent) {
        this.mSubtitle.setEllipsize(TruncateAt.END);
        this.mSubtitle.setFontResizable(true);
        if (bannerSpec.getBackgroundImageUrl() != null) {
            this.mBackgroundImage.setImage(new WishImage(bannerSpec.getBackgroundImageUrl()));
        }
        if (wishAnalyticsEvent != null) {
            WishAnalyticsLogger.trackEvent(wishAnalyticsEvent);
        }
        if (bannerSpec.getBackgroundColor() != null) {
            setBackgroundColor(ColorUtil.safeParseColor(bannerSpec.getBackgroundColor(), -16776961));
        }
        if (bannerSpec.getTextColor() != null) {
            int safeParseColor = ColorUtil.safeParseColor(bannerSpec.getTextColor(), -1);
            this.mPromoCode.setTextColor(safeParseColor);
            if (this.mPromoCode.getBackground() != null) {
                this.mPromoCode.getBackground().setColorFilter(safeParseColor, Mode.MULTIPLY);
            }
            this.mExpiryText.setTextColor(safeParseColor);
            this.mSubtitle.setTextColor(safeParseColor);
            this.mTitle.setTextColor(safeParseColor);
            this.mActionTextView.setTextColor(safeParseColor);
            this.mChevron.setColorFilter(safeParseColor);
        }
        WishTextViewSpec.applyTextViewSpec(this.mTitle, bannerSpec.getTitle());
        WishTextViewSpec.applyTextViewSpec(this.mSubtitle, bannerSpec.getSubtitle());
        WishTextViewSpec.applyTextViewSpec(this.mPromoCode, bannerSpec.getPromoCode());
        WishTextViewSpec.applyTextViewSpec(this.mExpiryText, bannerSpec.getExpiryText());
        if (bannerSpec.getActionType() != PromoActionType.UNKNOWN) {
            this.mActionContainer.setVisibility(0);
            WishTextViewSpec.applyTextViewSpec(this.mActionTextView, bannerSpec.getActionText());
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
