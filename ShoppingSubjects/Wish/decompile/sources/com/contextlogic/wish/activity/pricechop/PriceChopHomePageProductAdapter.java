package com.contextlogic.wish.activity.pricechop;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishHomePageInfo.HomePagePriceChopProduct;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.ui.timer.TimerTextView.TimerWatcherAdapter;
import com.contextlogic.wish.util.DrawableUtil;
import com.contextlogic.wish.util.JellyBeanMR1Util;
import java.util.ArrayList;
import java.util.List;

public class PriceChopHomePageProductAdapter extends Adapter<PriceChopHomePageProductViewHolder> {
    /* access modifiers changed from: private */
    public PriceChopHomePageProductAdapterListener mListener;
    /* access modifiers changed from: private */
    public List<HomePagePriceChopProduct> mProducts = new ArrayList();

    public interface PriceChopHomePageProductAdapterListener {
        void onTimeUp(HomePagePriceChopProduct homePagePriceChopProduct);
    }

    public void setup(List<HomePagePriceChopProduct> list) {
        this.mProducts = list;
        notifyDataSetChanged();
    }

    public void setListener(PriceChopHomePageProductAdapterListener priceChopHomePageProductAdapterListener) {
        this.mListener = priceChopHomePageProductAdapterListener;
    }

    public PriceChopHomePageProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PriceChopHomePageProductViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.price_chop_home_page_product_cell, viewGroup, false));
    }

    public void onBindViewHolder(PriceChopHomePageProductViewHolder priceChopHomePageProductViewHolder, int i) {
        Context context = priceChopHomePageProductViewHolder.itemView.getContext();
        final HomePagePriceChopProduct homePagePriceChopProduct = (HomePagePriceChopProduct) this.mProducts.get(i);
        priceChopHomePageProductViewHolder.mImageView.setImageUrl(homePagePriceChopProduct.getProductImageUrl());
        priceChopHomePageProductViewHolder.mPriceText.setText(homePagePriceChopProduct.getPrice().toFormattedString());
        JellyBeanMR1Util.setCompoundDrawablesRelativeWithIntrinsicBounds(priceChopHomePageProductViewHolder.mPriceText, DrawableUtil.tintDrawable(ContextCompat.getDrawable(context, R.drawable.axe), ContextCompat.getColor(context, R.color.price_chop_yellow)), null, null, null);
        priceChopHomePageProductViewHolder.mPriceText.setCompoundDrawablePadding(context.getResources().getDimensionPixelOffset(R.dimen.four_padding));
        priceChopHomePageProductViewHolder.mDiscountText.setText(homePagePriceChopProduct.getOriginalPrice().toFormattedString());
        priceChopHomePageProductViewHolder.mCountDownTimer.setup(homePagePriceChopProduct.getExpireDate(), new TimerWatcherAdapter() {
            public void onCountdownComplete() {
                if (PriceChopHomePageProductAdapter.this.mListener != null) {
                    PriceChopHomePageProductAdapter.this.mProducts.remove(homePagePriceChopProduct);
                    PriceChopHomePageProductAdapter.this.notifyDataSetChanged();
                    PriceChopHomePageProductAdapter.this.mListener.onTimeUp(homePagePriceChopProduct);
                }
            }
        });
        priceChopHomePageProductViewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PriceChopHomePageProductAdapter.this.handleProductClicked(view.getContext(), homePagePriceChopProduct.getProductId());
            }
        });
    }

    public int getItemCount() {
        return this.mProducts.size();
    }

    /* access modifiers changed from: private */
    public void handleProductClicked(Context context, String str) {
        Intent intent = new Intent();
        intent.setClass(context, ProductDetailsActivity.class);
        ProductDetailsActivity.addInitialProduct(intent, new WishProduct(str));
        intent.putExtra("ArgExtraScrollToPriceChop", true);
        context.startActivity(intent);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_PRICE_CHOP_FEED_PRODUCT);
    }
}
