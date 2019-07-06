package com.contextlogic.wish.activity.feed.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishImage.ImageSize;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.Recyclable;

public class HomePageDetailedProductCellView extends LinearLayout implements ImageRestorable, Recyclable {
    private ThemedTextView mDiscountBannerText;
    private NetworkImageView mImageView;
    private ThemedTextView mPriceSubText;
    private ThemedTextView mPriceSubTextSwapped;
    private ThemedTextView mPriceText;
    private ThemedTextView mPriceTextSwapped;
    private WishProduct mProduct;
    private ThemedTextView mUrgencyBannerText;

    public HomePageDetailedProductCellView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setOrientation(1);
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.home_page_detailed_product_view, this);
        this.mUrgencyBannerText = (ThemedTextView) inflate.findViewById(R.id.product_urgency_banner_text);
        this.mDiscountBannerText = (ThemedTextView) inflate.findViewById(R.id.product_discount_banner_text);
        this.mPriceText = (ThemedTextView) inflate.findViewById(R.id.product_price_main_text);
        this.mPriceSubText = (ThemedTextView) inflate.findViewById(R.id.product_price_sub_text);
        this.mPriceTextSwapped = (ThemedTextView) inflate.findViewById(R.id.product_price_main_text_swapped);
        this.mPriceSubTextSwapped = (ThemedTextView) inflate.findViewById(R.id.product_price_sub_text_swapped);
        this.mPriceSubText.setPaintFlags(this.mPriceSubText.getPaintFlags() | 16);
        this.mPriceSubTextSwapped.setPaintFlags(this.mPriceSubTextSwapped.getPaintFlags() | 16);
        this.mPriceTextSwapped.setVisibility(8);
        this.mPriceSubTextSwapped.setVisibility(8);
        this.mImageView = (NetworkImageView) inflate.findViewById(R.id.product_image);
    }

    public void setProduct(WishProduct wishProduct) {
        this.mProduct = wishProduct;
        this.mImageView.setImage(new WishImage(this.mProduct.getImage().getUrlString(ImageSize.MEDIUM)));
        WishLocalizedCurrencyValue commerceValue = this.mProduct.getCommerceValue();
        String urgencyText = this.mProduct.getUrgencyText() != null ? this.mProduct.getUrgencyText() : null;
        if (urgencyText != null) {
            this.mUrgencyBannerText.setVisibility(0);
            this.mUrgencyBannerText.setText(urgencyText);
        } else {
            this.mUrgencyBannerText.setVisibility(8);
        }
        WishLocalizedCurrencyValue value = this.mProduct.getValue();
        if (value.getValue() <= commerceValue.getValue() || commerceValue.getValue() <= 0.0d) {
            this.mDiscountBannerText.setVisibility(8);
        } else {
            double divide = value.subtract(commerceValue).divide(value) * 100.0d;
            this.mDiscountBannerText.setVisibility(0);
            this.mDiscountBannerText.setText(String.format("-%1$.0f%%", new Object[]{Double.valueOf(Math.floor(divide))}));
        }
        if (commerceValue.getValue() > 0.0d) {
            String formattedString = commerceValue.toFormattedString();
            this.mPriceText.setText(formattedString);
            this.mPriceTextSwapped.setText(formattedString);
        } else {
            this.mPriceText.setText(R.string.free);
            this.mPriceTextSwapped.setText(R.string.free);
        }
        if (value.getValue() > commerceValue.getValue()) {
            this.mPriceSubText.setVisibility(0);
            if (value.getValue() > 0.0d) {
                String formattedString2 = value.toFormattedString();
                this.mPriceSubText.setText(formattedString2);
                this.mPriceSubTextSwapped.setText(formattedString2);
            } else {
                this.mPriceSubText.setText(R.string.free);
                this.mPriceSubTextSwapped.setText(R.string.free);
            }
        } else {
            this.mPriceSubText.setVisibility(8);
        }
        if (this.mPriceSubText.getVisibility() != 0 || !ExperimentDataCenter.getInstance().shouldShowCrossedOutPriceToLeftInFeed()) {
            this.mPriceText.setVisibility(0);
            this.mPriceSubText.setVisibility(0);
            this.mPriceTextSwapped.setVisibility(8);
            this.mPriceSubTextSwapped.setVisibility(8);
            return;
        }
        this.mPriceTextSwapped.setVisibility(0);
        this.mPriceSubTextSwapped.setVisibility(0);
        this.mPriceText.setVisibility(8);
        this.mPriceSubText.setVisibility(8);
    }

    public void releaseImages() {
        this.mImageView.releaseImages();
    }

    public void restoreImages() {
        this.mImageView.restoreImages();
    }

    public void recycle() {
        this.mImageView.releaseImages();
        this.mImageView.setImage(null);
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImageView.setImagePrefetcher(imageHttpPrefetcher);
    }
}
