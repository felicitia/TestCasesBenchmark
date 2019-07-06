package com.contextlogic.wish.activity.feed.freegifts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.feed.BaseProductFeedFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishFreeGiftTabInfo;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.view.Recyclable;

public class FreeGiftsTabCellView extends LinearLayout implements ImageRestorable, Recyclable {
    /* access modifiers changed from: private */
    public BaseProductFeedFragment mFragment;
    private NetworkImageView mImageView;
    /* access modifiers changed from: private */
    public WishProduct mItem;
    private TextView mPriceLayoutSubText;

    public FreeGiftsTabCellView(Context context, BaseProductFeedFragment baseProductFeedFragment) {
        super(context);
        this.mFragment = baseProductFeedFragment;
        init();
    }

    public void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.free_gifts_tab_cell_view, this);
        this.mImageView = (NetworkImageView) inflate.findViewById(R.id.free_gifts_tab_grid_cell_view_image);
        this.mPriceLayoutSubText = (TextView) inflate.findViewById(R.id.free_gifts_tab_grid_cell_view_price_sub_text);
        this.mPriceLayoutSubText.setPaintFlags(this.mPriceLayoutSubText.getPaintFlags() | 16);
        ((TextView) inflate.findViewById(R.id.free_gifts_tab_grid_cell_view_buy_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (FreeGiftsTabCellView.this.mFragment != null && FreeGiftsTabCellView.this.mItem != null) {
                    WishFreeGiftTabInfo.logFreeGiftTabEvent(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_TAB_FEED_CHECKOUT);
                    FreeGiftsTabCellView.this.mFragment.addProductToCart(FreeGiftsTabCellView.this.mItem, Source.FREE_GIFT);
                }
            }
        });
    }

    public NetworkImageView getImageView() {
        return this.mImageView;
    }

    public void setPrices(WishProduct wishProduct) {
        this.mItem = wishProduct;
        WishLocalizedCurrencyValue signupGiftPrice = wishProduct.getSignupGiftPrice();
        WishLocalizedCurrencyValue variationRetailPrice = wishProduct.getVariationRetailPrice(wishProduct.getCommerceDefaultVariationId());
        if (variationRetailPrice.getValue() > signupGiftPrice.getValue()) {
            this.mPriceLayoutSubText.setVisibility(0);
            if (variationRetailPrice.getValue() > 0.0d) {
                this.mPriceLayoutSubText.setText(variationRetailPrice.toFormattedString());
            } else {
                this.mPriceLayoutSubText.setText(WishApplication.getInstance().getResources().getString(R.string.free));
            }
        } else {
            this.mPriceLayoutSubText.setVisibility(8);
        }
    }

    public void setProduct(WishProduct wishProduct) {
        setPrices(wishProduct);
        this.mImageView.setImage(wishProduct.getImage());
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

    public static int getExpectedHeight(WishProduct wishProduct, int i) {
        return (int) (((float) ((int) (((float) ((int) (((float) i) + WishApplication.getInstance().getResources().getDimension(R.dimen.filter_feed_price_layout_height)))) + WishApplication.getInstance().getResources().getDimension(R.dimen.filter_feed_original_price_layout_height_small)))) + WishApplication.getInstance().getResources().getDimension(R.dimen.eight_padding));
    }
}
