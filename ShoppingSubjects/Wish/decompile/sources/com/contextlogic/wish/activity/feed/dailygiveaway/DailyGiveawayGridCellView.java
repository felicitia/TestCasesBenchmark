package com.contextlogic.wish.activity.feed.dailygiveaway;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.feed.BaseProductFeedFragment;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayGridAdapter.WishProductPair;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.view.Recyclable;
import com.contextlogic.wish.util.DisplayUtil;
import java.util.HashMap;

public class DailyGiveawayGridCellView extends LinearLayout implements ImageRestorable, Recyclable {
    /* access modifiers changed from: private */
    public BaseProductFeedFragment mFragment;
    /* access modifiers changed from: private */
    public WishProduct mLeftProduct;
    private NetworkImageView mLeftProductImage;
    /* access modifiers changed from: private */
    public WishProduct mRightProduct;
    private NetworkImageView mRightProductImage;
    private FrameLayout mRightProductOutline;

    public DailyGiveawayGridCellView(Context context, BaseProductFeedFragment baseProductFeedFragment) {
        super(context);
        this.mFragment = baseProductFeedFragment;
        init(context);
    }

    public void init(Context context) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.daily_giveaway_grid_cell_view, this);
        int displayWidth = DisplayUtil.getDisplayWidth();
        inflate.setLayoutParams(new LayoutParams(displayWidth, (displayWidth - (WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.sixteen_padding) * 3)) / 2));
        this.mLeftProductImage = (NetworkImageView) inflate.findViewById(R.id.daily_giveaway_tile_product_image_left);
        this.mRightProductImage = (NetworkImageView) inflate.findViewById(R.id.daily_giveaway_tile_product_image_right);
        this.mRightProductOutline = (FrameLayout) inflate.findViewById(R.id.daily_giveaway_tile_product_image_right_outline);
        if (ExperimentDataCenter.getInstance().shouldShowDailyRaffle()) {
            inflate.setBackgroundResource(R.color.gray6);
            this.mLeftProductImage.setBackgroundResource(R.color.gray6);
            this.mRightProductImage.setBackgroundResource(R.color.gray6);
        }
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mLeftProductImage.setImagePrefetcher(imageHttpPrefetcher);
        this.mRightProductImage.setImagePrefetcher(imageHttpPrefetcher);
    }

    public void setProducts(WishProductPair wishProductPair) {
        this.mLeftProduct = wishProductPair.leftProduct;
        this.mRightProduct = wishProductPair.rightProduct;
        this.mLeftProductImage.setImage(this.mLeftProduct.getImage());
        this.mLeftProductImage.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_UPCOMING_PRODUCT_DETAILS, DailyGiveawayGridCellView.getGiveawayImpressionInfo());
                Intent intent = new Intent();
                intent.setClass(DailyGiveawayGridCellView.this.mFragment.getBaseActivity(), ProductDetailsActivity.class);
                ProductDetailsActivity.addInitialProduct(intent, DailyGiveawayGridCellView.this.mLeftProduct);
                DailyGiveawayGridCellView.this.mFragment.getBaseActivity().startActivity(intent);
            }
        });
        if (this.mRightProduct != null) {
            this.mRightProductImage.setVisibility(0);
            this.mRightProductOutline.setBackgroundResource(R.color.cool_gray5);
            this.mRightProductImage.setImage(this.mRightProduct.getImage());
            this.mRightProductImage.setClickable(true);
            this.mRightProductImage.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_UPCOMING_PRODUCT_DETAILS, DailyGiveawayGridCellView.getGiveawayImpressionInfo());
                    Intent intent = new Intent();
                    intent.setClass(DailyGiveawayGridCellView.this.mFragment.getBaseActivity(), ProductDetailsActivity.class);
                    ProductDetailsActivity.addInitialProduct(intent, DailyGiveawayGridCellView.this.mRightProduct);
                    DailyGiveawayGridCellView.this.mFragment.getBaseActivity().startActivity(intent);
                }
            });
            return;
        }
        this.mRightProductImage.setVisibility(4);
        this.mRightProductOutline.setBackgroundResource(R.color.transparent);
        this.mRightProductImage.setClickable(false);
    }

    /* access modifiers changed from: private */
    public static HashMap<String, String> getGiveawayImpressionInfo() {
        HashMap<String, String> hashMap = new HashMap<>();
        if (ExperimentDataCenter.getInstance().shouldShowDailyRaffle()) {
            hashMap.put("GiveawayType", "DailyRaffle");
        } else {
            hashMap.put("GiveawayType", "DailyGiveaway");
        }
        return hashMap;
    }

    public void releaseImages() {
        this.mLeftProductImage.releaseImages();
        this.mRightProductImage.releaseImages();
    }

    public void restoreImages() {
        this.mLeftProductImage.restoreImages();
        this.mRightProductImage.restoreImages();
    }

    public void recycle() {
        this.mLeftProductImage.recycle();
        this.mRightProductImage.recycle();
    }
}
