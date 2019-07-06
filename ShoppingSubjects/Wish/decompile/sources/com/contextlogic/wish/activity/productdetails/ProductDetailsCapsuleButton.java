package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;

public class ProductDetailsCapsuleButton extends LinearLayout {
    private TextView mFirstText;
    /* access modifiers changed from: private */
    public ProductDetailsFragment mFragment;
    private AutoReleasableImageView mPhotoIconImage;
    private TextView mSecondText;
    private AutoReleasableImageView mVideoIconImage;

    public enum ButtonType {
        PlayVideo,
        ImageIndex,
        PhotoVideoCount
    }

    public ProductDetailsCapsuleButton(Context context) {
        this(context, null);
    }

    public ProductDetailsCapsuleButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.product_details_fragment_capsule_button, this);
        setOrientation(1);
        setLayoutParams(new LayoutParams(-2, -2));
        this.mVideoIconImage = (AutoReleasableImageView) inflate.findViewById(R.id.fragment_product_details_capsule_image_video);
        this.mPhotoIconImage = (AutoReleasableImageView) inflate.findViewById(R.id.fragment_product_details_capsule_image_photo_count);
        this.mFirstText = (TextView) inflate.findViewById(R.id.fragment_product_details_capsule_button_first_text);
        this.mSecondText = (TextView) inflate.findViewById(R.id.fragment_product_details_capsule_button_second_text);
    }

    public void setFragment(ProductDetailsFragment productDetailsFragment) {
        this.mFragment = productDetailsFragment;
    }

    public void setProduct(WishProduct wishProduct, ButtonType buttonType) {
        setProduct(wishProduct, buttonType, 0, 0);
    }

    public void setProduct(WishProduct wishProduct, final ButtonType buttonType, int i, int i2) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mVideoIconImage.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.mPhotoIconImage.getLayoutParams();
        final int i3 = 0;
        if (buttonType == ButtonType.PlayVideo) {
            this.mVideoIconImage.setVisibility(0);
            this.mVideoIconImage.setImageResource(R.drawable.video_capsule_play);
            layoutParams.width = getResources().getDimensionPixelSize(R.dimen.video_play_capsule_icon_size);
            layoutParams.height = getResources().getDimensionPixelSize(R.dimen.video_play_capsule_icon_size);
            this.mVideoIconImage.setLayoutParams(layoutParams);
            this.mSecondText.setText(WishApplication.getInstance().getString(R.string.play_video));
            i3 = 1;
        } else if (buttonType == ButtonType.PhotoVideoCount) {
            if (i > 0) {
                this.mPhotoIconImage.setVisibility(0);
                this.mPhotoIconImage.setImageResource(R.drawable.image_capsule_icon);
                layoutParams.width = getResources().getDimensionPixelSize(R.dimen.product_details_fragment_extra_images_icon_width);
                layoutParams.height = getResources().getDimensionPixelSize(R.dimen.product_details_fragment_extra_images_icon_height);
                this.mPhotoIconImage.setLayoutParams(layoutParams2);
                setFirstText(Integer.toString(i));
            }
            if (i2 > 0) {
                this.mVideoIconImage.setVisibility(0);
                this.mVideoIconImage.setImageResource(R.drawable.video_capsule_play);
                layoutParams.width = getResources().getDimensionPixelSize(R.dimen.product_details_fragment_extra_images_icon_width);
                layoutParams.height = getResources().getDimensionPixelSize(R.dimen.product_details_fragment_extra_images_icon_height);
                layoutParams.leftMargin = getResources().getDimensionPixelSize(R.dimen.eight_padding);
                this.mVideoIconImage.setLayoutParams(layoutParams);
                setSecondText(Integer.toString(i2));
            }
        }
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (buttonType == ButtonType.PlayVideo) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRODUCT_DETAILS_PLAY_VIDEO);
                }
                if (buttonType == ButtonType.PhotoVideoCount) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PHOTO_VIDEO_CAPSULE_CLICK, ProductDetailsCapsuleButton.this.mFragment.getProductId());
                    ProductDetailsCapsuleButton.this.mFragment.showProductExtraPhotos(0);
                    return;
                }
                ProductDetailsCapsuleButton.this.mFragment.showProductExtraPhotosImageViewer(i3);
            }
        });
    }

    public void setFirstText(String str) {
        this.mFirstText.setVisibility(0);
        this.mFirstText.setText(str);
    }

    public void setSecondText(String str) {
        this.mSecondText.setVisibility(0);
        this.mSecondText.setText(str);
    }
}
