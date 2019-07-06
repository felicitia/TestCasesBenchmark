package com.contextlogic.wish.activity.feed.recentlyviewed;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints.LayoutParams;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.merchantprofile.MerchantProfileActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.MerchantFeedItem;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.recyclerview.itemdecoration.SpacingItemDecoration;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class MerchantCellView extends ConstraintLayout {
    private MerchantCellProductsAdapter mAdapter;
    private MerchantFeedItem mMerchant;
    private NetworkImageView mMerchantImageView;
    private ThemedTextView mMerchantTitleText;
    private RecyclerView mProductsRecyclerView;
    private ThemedTextView mViewAllText;

    public MerchantCellView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.merchant_cell, this);
        setLayoutParams(new LayoutParams(-1, -2));
        this.mMerchantImageView = (NetworkImageView) findViewById(R.id.merchant_image_view);
        this.mMerchantTitleText = (ThemedTextView) findViewById(R.id.merchant_title_text_view);
        this.mViewAllText = (ThemedTextView) findViewById(R.id.view_store_text);
        this.mProductsRecyclerView = (RecyclerView) findViewById(R.id.product_recycler_view);
        this.mAdapter = new MerchantCellProductsAdapter();
        this.mProductsRecyclerView.setAdapter(this.mAdapter);
        this.mProductsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.mProductsRecyclerView.addItemDecoration(new SpacingItemDecoration(getContext().getResources().getDimensionPixelSize(R.dimen.four_padding)));
        this.mProductsRecyclerView.setNestedScrollingEnabled(false);
        this.mMerchantImageView.setOnClickListener(createMerchantClickListener());
        this.mMerchantTitleText.setOnClickListener(createMerchantClickListener());
        this.mViewAllText.setOnClickListener(createMerchantClickListener());
    }

    public void setRecentlyViewedMerchant(MerchantFeedItem merchantFeedItem, int i) {
        this.mMerchant = merchantFeedItem;
        this.mMerchantImageView.setImage(merchantFeedItem.getDisplayPic());
        this.mMerchantTitleText.setText(merchantFeedItem.getDisplayName());
        this.mAdapter.setProducts(merchantFeedItem.getProducts());
        ((LinearLayoutManager) this.mProductsRecyclerView.getLayoutManager()).scrollToPositionWithOffset(0, -i);
    }

    public int getRecyclerViewScrollX() {
        return this.mProductsRecyclerView.computeHorizontalScrollOffset();
    }

    private OnClickListener createMerchantClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                MerchantCellView.this.onMerchantClicked();
            }
        };
    }

    /* access modifiers changed from: private */
    public void onMerchantClicked() {
        if (this.mMerchant != null) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RECENTLY_VIEWED_STORES_VIEW_STORE);
            Intent createIntent = MerchantProfileActivity.createIntent(this.mMerchant.getMerchantId(), this.mMerchant.getMerchantName());
            Context context = getContext();
            if (context != null) {
                context.startActivity(createIntent);
            }
        }
    }
}
