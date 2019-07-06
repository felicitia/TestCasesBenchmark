package com.contextlogic.wish.activity.feed.promotion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.feed.BaseFeedHeaderView;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishPromotionNoCouponSpec.BannerSmallSpec;
import com.contextlogic.wish.api.model.WishTextViewSpec;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.ColorUtil;

public class PromotionBannerSmallNoCouponHeaderView extends BaseFeedHeaderView {
    private NetworkImageView mBackgroundImage;
    private ThemedTextView mTitle;

    public PromotionBannerSmallNoCouponHeaderView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.promotion_banner_small_no_coupon_view, this);
        this.mBackgroundImage = (NetworkImageView) inflate.findViewById(R.id.promotion_banner_small_view_background);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.promotion_banner_small_view_text);
    }

    public void setup(BannerSmallSpec bannerSmallSpec) {
        if (bannerSmallSpec.getBackgroundImageUrl() != null) {
            this.mBackgroundImage.setImage(new WishImage(bannerSmallSpec.getBackgroundImageUrl()));
        }
        if (bannerSmallSpec.getBackgroundColor() != null) {
            this.mBackgroundImage.setBackgroundColor(ColorUtil.safeParseColor(bannerSmallSpec.getBackgroundColor(), -16776961));
        }
        if (bannerSmallSpec.getTextColor() != null) {
            this.mTitle.setTextColor(ColorUtil.safeParseColor(bannerSmallSpec.getTextColor(), -1));
        }
        WishTextViewSpec.applyTextViewSpec(this.mTitle, bannerSmallSpec.getTitle());
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
