package com.contextlogic.wish.activity.productdetails.relateditemsrow;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishProductBadge;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.Recyclable;
import java.util.Iterator;

public class FasterShippingRowItemView extends LinearLayout implements ImageRestorable, Recyclable {
    private AutoReleasableImageView mBadge;
    private ThemedTextView mMainPrice;
    /* access modifiers changed from: private */
    public ThemedTextView mMainPriceSwapped;
    private NetworkImageView mMainTile;
    private ThemedTextView mSubPrice;
    /* access modifiers changed from: private */
    public ThemedTextView mSubPriceSwapped;

    public FasterShippingRowItemView(Context context) {
        super(context);
        init();
    }

    public void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.product_details_express_shipping_tile, this);
        this.mMainPrice = (ThemedTextView) inflate.findViewById(R.id.product_details_express_shipping_tile_price_main_text);
        this.mSubPrice = (ThemedTextView) inflate.findViewById(R.id.product_details_express_shipping_tile_price_sub_text);
        this.mMainPriceSwapped = (ThemedTextView) inflate.findViewById(R.id.product_details_express_shipping_tile_price_main_text_swapped);
        this.mSubPriceSwapped = (ThemedTextView) inflate.findViewById(R.id.product_details_express_shipping_tile_price_sub_text_swapped);
        this.mSubPrice.setPaintFlags(this.mSubPrice.getPaintFlags() | 16);
        this.mSubPriceSwapped.setPaintFlags(this.mSubPriceSwapped.getPaintFlags() | 16);
        this.mMainPriceSwapped.setVisibility(8);
        this.mSubPriceSwapped.setVisibility(8);
        this.mMainTile = (NetworkImageView) inflate.findViewById(R.id.product_details_express_shipping_tile_image);
        this.mBadge = (AutoReleasableImageView) inflate.findViewById(R.id.product_details_express_shipping_tile_badge);
        LayoutParams layoutParams = (LayoutParams) this.mMainTile.getLayoutParams();
        int dimensionPixelSize = getResources().getDimensionPixelSize(ExperimentDataCenter.getInstance().shouldSeeLargerExpressShippingTiles() ? R.dimen.product_details_express_shipping_tile_height_larger : R.dimen.product_details_express_shipping_tile_height);
        layoutParams.width = dimensionPixelSize;
        layoutParams.height = dimensionPixelSize;
        this.mMainTile.setLayoutParams(layoutParams);
    }

    public void releaseImages() {
        this.mMainTile.releaseImages();
    }

    public void restoreImages() {
        this.mMainTile.restoreImages();
    }

    public void recycle() {
        this.mMainTile.releaseImages();
        this.mMainTile.setImage(null);
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mMainTile.setImagePrefetcher(imageHttpPrefetcher);
    }

    public void setProduct(WishProduct wishProduct) {
        if (wishProduct != null) {
            this.mMainTile.setPlaceholderColor(ContextCompat.getColor(getContext(), R.color.image_placeholder_light_background));
            this.mMainTile.setImage(wishProduct.getImage());
            String formattedString = wishProduct.getValue().toFormattedString();
            this.mSubPrice.setText(formattedString);
            this.mSubPriceSwapped.setText(formattedString);
            this.mMainPrice.setVisibility(0);
            this.mSubPrice.setVisibility(0);
            this.mMainPriceSwapped.setVisibility(8);
            this.mSubPriceSwapped.setVisibility(8);
            if (wishProduct.getCommerceValue().getValue() > 0.0d) {
                String formattedString2 = wishProduct.getCommerceValue().toFormattedString();
                this.mMainPrice.setText(formattedString2);
                this.mMainPriceSwapped.setText(formattedString2);
                if (wishProduct.getValue().getValue() <= wishProduct.getCommerceValue().getValue() || wishProduct.getValue().getValue() <= 0.0d) {
                    this.mSubPrice.setVisibility(8);
                } else {
                    String formattedString3 = wishProduct.getValue().toFormattedString();
                    this.mSubPrice.setVisibility(0);
                    this.mSubPrice.setText(formattedString3);
                    this.mSubPriceSwapped.setText(formattedString3);
                }
            } else {
                this.mMainPrice.setText(R.string.free);
                this.mMainPriceSwapped.setText(R.string.free);
                this.mSubPrice.setVisibility(8);
            }
            final boolean shouldShowCrossedOutPriceToLeftInFeed = ExperimentDataCenter.getInstance().shouldShowCrossedOutPriceToLeftInFeed();
            if (shouldShowCrossedOutPriceToLeftInFeed && this.mMainPrice.getVisibility() == 0 && this.mSubPrice.getVisibility() == 0) {
                this.mMainPriceSwapped.setVisibility(0);
                this.mSubPriceSwapped.setVisibility(0);
                this.mMainPrice.setVisibility(8);
                this.mSubPrice.setVisibility(8);
            }
            this.mMainPriceSwapped.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    LayoutParams layoutParams = (LayoutParams) FasterShippingRowItemView.this.mSubPriceSwapped.getLayoutParams();
                    layoutParams.weight = (!shouldShowCrossedOutPriceToLeftInFeed || FasterShippingRowItemView.this.mMainPriceSwapped.getLineCount() <= 1) ? 0.0f : 1.0f;
                    FasterShippingRowItemView.this.mSubPriceSwapped.setLayoutParams(layoutParams);
                    FasterShippingRowItemView.this.mMainPriceSwapped.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
            Iterator it = wishProduct.getProductBadges().iterator();
            while (it.hasNext()) {
                WishProductBadge wishProductBadge = (WishProductBadge) it.next();
                if (wishProductBadge.getType() == 2) {
                    this.mBadge.setImageResource(wishProductBadge.getBadgeIcon());
                }
            }
        }
    }
}
