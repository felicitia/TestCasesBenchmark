package com.contextlogic.wish.activity.feed.recentlyviewed;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints.LayoutParams;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class MerchantCellProductView extends ConstraintLayout {
    private ThemedTextView mBoughtCountText;
    private ThemedTextView mDiscountedPriceText;
    private NetworkImageView mNetworkImageView;
    private ThemedTextView mNewText;
    private ThemedTextView mPriceText;
    private WishProduct mWishProduct;

    public MerchantCellProductView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.merchant_cell_product_view, this);
        setLayoutParams(new LayoutParams(getResources().getDimensionPixelSize(R.dimen.recently_viewed_merchants_product_cell_width), getResources().getDimensionPixelSize(R.dimen.recently_viewed_merchants_product_cell_height)));
        this.mNetworkImageView = (NetworkImageView) findViewById(R.id.product_image_view);
        this.mPriceText = (ThemedTextView) findViewById(R.id.price_text);
        this.mDiscountedPriceText = (ThemedTextView) findViewById(R.id.discounted_price_text);
        this.mBoughtCountText = (ThemedTextView) findViewById(R.id.bought_count_text);
        this.mNewText = (ThemedTextView) findViewById(R.id.new_text);
        this.mDiscountedPriceText.setPaintFlags(this.mDiscountedPriceText.getPaintFlags() | 16);
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MerchantCellProductView.this.onProductClicked();
            }
        });
    }

    public void setProduct(WishProduct wishProduct) {
        this.mWishProduct = wishProduct;
        this.mNetworkImageView.setImage(wishProduct.getImage());
        WishLocalizedCurrencyValue commerceValue = wishProduct.getCommerceValue();
        if (commerceValue.getValue() > 0.0d) {
            this.mPriceText.setText(commerceValue.toFormattedString());
        } else {
            this.mPriceText.setText(getResources().getString(R.string.free));
        }
        WishLocalizedCurrencyValue value = wishProduct.getValue();
        int i = 8;
        if (value.getValue() > commerceValue.getValue()) {
            this.mDiscountedPriceText.setText(value.toFormattedString());
            this.mDiscountedPriceText.setVisibility(0);
        } else {
            this.mDiscountedPriceText.setVisibility(8);
        }
        String numPurchasedText = wishProduct.getNumPurchasedText();
        this.mBoughtCountText.setVisibility(!TextUtils.isEmpty(numPurchasedText) ? 0 : 8);
        this.mBoughtCountText.setText(numPurchasedText);
        ThemedTextView themedTextView = this.mNewText;
        if (wishProduct.isNew()) {
            i = 0;
        }
        themedTextView.setVisibility(i);
    }

    /* access modifiers changed from: private */
    public void onProductClicked() {
        if (this.mWishProduct != null) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RECENTLY_VIEWED_STORES_VIEW_PRODUCT);
            Intent intent = new Intent();
            intent.setClass(getContext(), ProductDetailsActivity.class);
            ProductDetailsActivity.addInitialProduct(intent, this.mWishProduct);
            Context context = getContext();
            if (context != null) {
                context.startActivity(intent);
            }
        }
    }
}
