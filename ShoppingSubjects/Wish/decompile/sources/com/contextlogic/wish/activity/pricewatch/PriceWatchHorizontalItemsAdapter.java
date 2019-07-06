package com.contextlogic.wish.activity.pricewatch;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedFragment;
import com.contextlogic.wish.activity.pricewatch.PriceWatchItemView.PriceWatchItemClickListener;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishPriceWatchSpec.PriceWatchItem;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.listview.HorizontalListView.Adapter;
import java.util.HashMap;
import java.util.List;

public class PriceWatchHorizontalItemsAdapter extends Adapter implements PriceWatchItemClickListener {
    private OrderConfirmedFragment mFragment;
    private ImageHttpPrefetcher mImagePrefetcher;
    private List<PriceWatchItem> mPriceWatchItems;
    private int mProductHeight = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.price_watch_order_confirmed_view_product_height);
    private int mProductWidth = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.price_watch_order_confirmed_view_product_width);

    public boolean includeLeadingMargin() {
        return false;
    }

    public boolean includeTrailingMargin() {
        return false;
    }

    public void onRemoveClick(String str) {
    }

    public PriceWatchHorizontalItemsAdapter(List<PriceWatchItem> list, OrderConfirmedFragment orderConfirmedFragment) {
        this.mPriceWatchItems = list;
        this.mFragment = orderConfirmedFragment;
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
        if (this.mPriceWatchItems != null) {
            return this.mPriceWatchItems.size();
        }
        return 0;
    }

    public PriceWatchItem getItem(int i) {
        if (i < getCount()) {
            return (PriceWatchItem) this.mPriceWatchItems.get(i);
        }
        return null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        PriceWatchItemView priceWatchItemView;
        if (view == null) {
            priceWatchItemView = new PriceWatchItemView(this.mFragment.getContext());
            priceWatchItemView.setListener(this);
            if (this.mImagePrefetcher != null) {
                priceWatchItemView.setImagePrefetcher(this.mImagePrefetcher);
            }
        } else {
            priceWatchItemView = (PriceWatchItemView) view;
        }
        priceWatchItemView.setup((PriceWatchItem) this.mPriceWatchItems.get(i), true);
        priceWatchItemView.setBackgroundResource(R.drawable.raffle_grey_five_border_rounded);
        return priceWatchItemView;
    }

    public void onBuyClick(String str, double d, double d2) {
        HashMap hashMap = new HashMap();
        hashMap.put("price_difference", String.valueOf(d));
        hashMap.put("original_price", String.valueOf(d2));
        hashMap.put("product_id", str);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRICE_WATCH_ORDER_CONFIRMED_BUY_BUTTON, hashMap);
        this.mFragment.handleBuyClick(str);
    }

    public void onOpenClick(String str) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRICE_WATCH_ORDER_CONFIRMED_ITEM);
        WishProduct wishProduct = new WishProduct(str);
        Intent intent = new Intent();
        intent.setClass(this.mFragment.getContext(), ProductDetailsActivity.class);
        ProductDetailsActivity.addInitialProduct(intent, wishProduct);
        this.mFragment.getContext().startActivity(intent);
    }
}
