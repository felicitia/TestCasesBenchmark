package com.contextlogic.wish.activity.productdetails.bundles;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.analytics.FeedTileLogger;
import com.contextlogic.wish.analytics.FeedTileLogger.Action;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.util.DisplayUtil;
import java.util.ArrayList;
import java.util.Iterator;

public class BundlesProductImageView extends LinearLayout implements BundlesViewConnector {
    private final Context mContext;
    private ArrayList<NetworkImageView> mProductImages;

    public BundlesProductImageView(Context context) {
        this(context, null);
    }

    public BundlesProductImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public void setup(final ArrayList<WishProduct> arrayList) {
        this.mProductImages = new ArrayList<>();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.double_screen_padding);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.screen_padding);
        setLayoutParams(new LayoutParams(-1, -2));
        int i = dimensionPixelSize2 * 2;
        int displayWidth = ((int) (((((float) DisplayUtil.getDisplayWidth()) - ((float) getResources().getDimensionPixelSize(R.dimen.bundles_content_padding))) - ((float) ((arrayList.size() - 1) * (getResources().getDimensionPixelSize(R.dimen.bundles_product_image_separator) + i)))) - ((float) i))) / 3;
        LayoutParams layoutParams = new LayoutParams(displayWidth, displayWidth);
        setGravity(17);
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen.screen_padding);
        layoutParams.leftMargin = dimensionPixelSize3;
        layoutParams.rightMargin = dimensionPixelSize3;
        layoutParams.topMargin = dimensionPixelSize;
        layoutParams.bottomMargin = dimensionPixelSize;
        int dimensionPixelSize4 = getResources().getDimensionPixelSize(R.dimen.bundles_product_image_separator);
        LayoutParams layoutParams2 = new LayoutParams(dimensionPixelSize4, dimensionPixelSize4);
        for (final int i2 = 0; i2 < arrayList.size(); i2++) {
            final WishProduct wishProduct = (WishProduct) arrayList.get(i2);
            NetworkImageView networkImageView = new NetworkImageView(this.mContext);
            networkImageView.setLayoutParams(layoutParams);
            networkImageView.setImage(wishProduct.getImage());
            networkImageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BUNDLES_PHOTO, wishProduct.getProductId());
                    Intent intent = new Intent();
                    intent.setClass(BundlesProductImageView.this.getContext(), ProductDetailsActivity.class);
                    WishProduct wishProduct = new WishProduct(((WishProduct) arrayList.get(i2)).getProductId());
                    ProductDetailsActivity.addInitialProduct(intent, wishProduct);
                    FeedTileLogger.getInstance().addToQueue(wishProduct.getLoggingFields(), Action.CLICKED, i2);
                    BundlesProductImageView.this.getContext().startActivity(intent);
                }
            });
            addView(networkImageView);
            this.mProductImages.add(networkImageView);
            if (i2 < arrayList.size() - 1) {
                AutoReleasableImageView autoReleasableImageView = new AutoReleasableImageView(this.mContext);
                autoReleasableImageView.setImageResource(R.drawable.dark_plus);
                autoReleasableImageView.setLayoutParams(layoutParams2);
                addView(autoReleasableImageView);
            }
        }
        setBackgroundResource(R.drawable.bundles_top_border);
    }

    public void handleCheckboxTapped(int i, boolean z) {
        if (z) {
            ((NetworkImageView) this.mProductImages.get(i)).setAlpha(255);
        } else {
            ((NetworkImageView) this.mProductImages.get(i)).setAlpha(100);
        }
    }

    public void releaseImages() {
        if (this.mProductImages != null) {
            Iterator it = this.mProductImages.iterator();
            while (it.hasNext()) {
                ((NetworkImageView) it.next()).releaseImages();
            }
        }
    }

    public void restoreImages() {
        if (this.mProductImages != null) {
            Iterator it = this.mProductImages.iterator();
            while (it.hasNext()) {
                ((NetworkImageView) it.next()).restoreImages();
            }
        }
    }
}
