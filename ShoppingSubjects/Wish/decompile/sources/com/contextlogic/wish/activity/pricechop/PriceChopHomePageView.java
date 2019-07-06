package com.contextlogic.wish.activity.pricechop;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.pricechop.PriceChopHomePageProductAdapter.PriceChopHomePageProductAdapterListener;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishHomePageInfo.HomePagePriceChopItemHolder;
import com.contextlogic.wish.api.model.WishHomePageInfo.HomePagePriceChopProduct;
import com.contextlogic.wish.ui.recyclerview.itemdecoration.SpacingItemDecoration;

public class PriceChopHomePageView extends LinearLayout {
    /* access modifiers changed from: private */
    public PriceChopHomePageProductAdapter mAdapter;
    private TextView mHomePageTitle;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;

    public PriceChopHomePageView(Context context) {
        super(context);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.price_chop_home_page_view, this, true);
        this.mHomePageTitle = (TextView) findViewById(R.id.price_chop_home_title);
        this.mProgressBar = (ProgressBar) findViewById(R.id.price_chop_home_progress);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.price_chop_home_recycler_view);
        this.mAdapter = new PriceChopHomePageProductAdapter();
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.addItemDecoration(new SpacingItemDecoration(getContext(), R.dimen.eight_padding));
        this.mAdapter.setListener(new PriceChopHomePageProductAdapterListener() {
            public void onTimeUp(HomePagePriceChopProduct homePagePriceChopProduct) {
                PriceChopHomePageView.this.setVisibility(PriceChopHomePageView.this.mAdapter.getItemCount() > 0 ? 0 : 8);
            }
        });
    }

    public void setup(HomePagePriceChopItemHolder homePagePriceChopItemHolder, boolean z) {
        setVisibility(0);
        this.mHomePageTitle.setText(homePagePriceChopItemHolder.getTitle());
        if (z) {
            this.mProgressBar.setVisibility(0);
            this.mRecyclerView.setVisibility(8);
            return;
        }
        this.mProgressBar.setVisibility(8);
        this.mRecyclerView.setVisibility(0);
        this.mAdapter.setup(homePagePriceChopItemHolder.getProducts());
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_PRICE_CHOP_FEED);
    }
}
