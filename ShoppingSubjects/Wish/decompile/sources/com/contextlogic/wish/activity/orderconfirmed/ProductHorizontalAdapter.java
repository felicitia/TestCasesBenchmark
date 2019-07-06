package com.contextlogic.wish.activity.orderconfirmed;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.feed.ProductFeedTileView;
import com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedView.DataMode;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.analytics.FeedTileLogger;
import com.contextlogic.wish.analytics.FeedTileLogger.Action;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.listview.HorizontalListView.Adapter;
import com.contextlogic.wish.ui.listview.HorizontalListView.OnItemClickListener;
import java.util.ArrayList;

public class ProductHorizontalAdapter extends Adapter {
    /* access modifiers changed from: private */
    public BaseActivity mBaseActivity;
    /* access modifiers changed from: private */
    public DataMode mDataMode;
    private ImageHttpPrefetcher mImagePrefetcher;
    protected int mProductHeight;
    /* access modifiers changed from: private */
    public String mProductId;
    protected int mProductWidth;
    private ArrayList<WishProduct> mProducts;

    /* renamed from: com.contextlogic.wish.activity.orderconfirmed.ProductHorizontalAdapter$2 reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$contextlogic$wish$activity$orderconfirmed$OrderConfirmedView$DataMode = new int[DataMode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedView$DataMode[] r0 = com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedView.DataMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$contextlogic$wish$activity$orderconfirmed$OrderConfirmedView$DataMode = r0
                int[] r0 = $SwitchMap$com$contextlogic$wish$activity$orderconfirmed$OrderConfirmedView$DataMode     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedView$DataMode r1 = com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedView.DataMode.ALSO_BOUGHT     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$contextlogic$wish$activity$orderconfirmed$OrderConfirmedView$DataMode     // Catch:{ NoSuchFieldError -> 0x001f }
                com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedView$DataMode r1 = com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedView.DataMode.WISHLIST     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.orderconfirmed.ProductHorizontalAdapter.AnonymousClass2.<clinit>():void");
        }
    }

    public boolean includeLeadingMargin() {
        return false;
    }

    public boolean includeTrailingMargin() {
        return false;
    }

    public ProductHorizontalAdapter(BaseActivity baseActivity, ArrayList<WishProduct> arrayList, DataMode dataMode) {
        this(baseActivity, arrayList, dataMode, null);
    }

    public ProductHorizontalAdapter(BaseActivity baseActivity, ArrayList<WishProduct> arrayList, DataMode dataMode, String str) {
        this.mBaseActivity = baseActivity;
        this.mProducts = arrayList;
        this.mDataMode = dataMode;
        this.mProductId = str;
        this.mProductWidth = this.mBaseActivity.getResources().getDimensionPixelOffset(R.dimen.order_confirmed_view_product_width);
        this.mProductHeight = this.mBaseActivity.getResources().getDimensionPixelOffset(R.dimen.order_confirmed_view_product_height);
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    public int getItemWidth(int i) {
        return this.mProductWidth;
    }

    public int getItemHeight(int i) {
        return this.mProductHeight;
    }

    public int getCount() {
        return this.mProducts.size();
    }

    public WishProduct getItem(int i) {
        return (WishProduct) this.mProducts.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ProductFeedTileView productFeedTileView;
        if (view != null) {
            productFeedTileView = (ProductFeedTileView) view;
        } else {
            productFeedTileView = new ProductFeedTileView(this.mBaseActivity);
            productFeedTileView.setImagePrefetcher(this.mImagePrefetcher);
        }
        productFeedTileView.setProduct(getItem(i));
        productFeedTileView.setPosition(i);
        return productFeedTileView;
    }

    public void attachItemClickListener(HorizontalListView horizontalListView) {
        horizontalListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(int i, View view) {
                WishProduct item = ProductHorizontalAdapter.this.getItem(i);
                switch (AnonymousClass2.$SwitchMap$com$contextlogic$wish$activity$orderconfirmed$OrderConfirmedView$DataMode[ProductHorizontalAdapter.this.mDataMode.ordinal()]) {
                    case 1:
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ORDER_CONFIRMED_VIEW_ALSO_BOUGHT_PRODUCT, ProductHorizontalAdapter.this.mProductId);
                        break;
                    case 2:
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ORDER_CONFIRMED_VIEW_WISHLIST_PRODUCT);
                        break;
                }
                if (item != null) {
                    FeedTileLogger.getInstance().addToQueue(item.getLoggingFields(), Action.CLICKED, i);
                    Intent intent = new Intent();
                    intent.setClass(ProductHorizontalAdapter.this.mBaseActivity, ProductDetailsActivity.class);
                    ProductDetailsActivity.addInitialProduct(intent, item);
                    ProductHorizontalAdapter.this.mBaseActivity.startActivity(intent);
                }
            }
        });
    }
}
