package com.contextlogic.wish.activity.pricewatch;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.contextlogic.wish.activity.pricewatch.PriceWatchItemView.PriceWatchItemClickListener;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishPriceWatchSpec.PriceWatchItem;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PriceWatchItemAdapter extends BaseAdapter implements PriceWatchItemClickListener {
    private PriceWatchFragment mFragment;
    private ImageHttpPrefetcher mImagePrefetcher;
    private List<PriceWatchItem> mPriceWatchItems = new ArrayList();

    public long getItemId(int i) {
        return (long) i;
    }

    public PriceWatchItemAdapter(PriceWatchFragment priceWatchFragment, ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
        this.mFragment = priceWatchFragment;
    }

    public int getCount() {
        if (this.mPriceWatchItems != null) {
            return this.mPriceWatchItems.size();
        }
        return 0;
    }

    public PriceWatchItem getItem(int i) {
        if (i < this.mPriceWatchItems.size()) {
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
        priceWatchItemView.setup((PriceWatchItem) this.mPriceWatchItems.get(i), false);
        return priceWatchItemView;
    }

    public void setPriceWatchItems(List<PriceWatchItem> list) {
        this.mPriceWatchItems = list;
    }

    public void onRemoveClick(String str) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRICE_WATCH_REMOVE_BUTTON);
        this.mFragment.handleItemRemove(str);
    }

    public void onBuyClick(String str, double d, double d2) {
        HashMap hashMap = new HashMap();
        hashMap.put("price_difference", String.valueOf(d));
        hashMap.put("original_price", String.valueOf(d2));
        hashMap.put("product_id", str);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRICE_WATCH_BUY_BUTTON, hashMap);
        this.mFragment.handleBuyClick(str);
    }

    public void onOpenClick(String str) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRICE_WATCH_ITEM);
        WishProduct wishProduct = new WishProduct(str);
        Intent intent = new Intent();
        intent.setClass(this.mFragment.getContext(), ProductDetailsActivity.class);
        ProductDetailsActivity.addInitialProduct(intent, wishProduct);
        this.mFragment.getContext().startActivity(intent);
    }
}
