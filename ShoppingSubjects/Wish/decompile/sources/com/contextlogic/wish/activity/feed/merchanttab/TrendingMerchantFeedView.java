package com.contextlogic.wish.activity.feed.merchanttab;

import android.annotation.SuppressLint;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.activity.feed.recentlyviewed.BaseMerchantFeedView;
import com.contextlogic.wish.api.model.MerchantFeedItem;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.MerchantsFeedTabService.SuccessCallback;
import com.contextlogic.wish.ui.recyclerview.infinitescroll.InfiniteDataFetcher;
import com.contextlogic.wish.ui.recyclerview.infinitescroll.InfiniteDataFinisher;
import java.util.Collections;
import java.util.List;

@SuppressLint({"ViewConstructor"})
public class TrendingMerchantFeedView extends BaseMerchantFeedView {
    /* access modifiers changed from: protected */
    public int getEmptyText() {
        return R.string.merchant_feed_tab_no_trending_merchants;
    }

    public TrendingMerchantFeedView(BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
        super(baseProductFeedServiceFragment);
    }

    /* access modifiers changed from: protected */
    public InfiniteDataFetcher<MerchantFeedItem> createPagingFetcher(final BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
        return new InfiniteDataFetcher<MerchantFeedItem>() {
            public void fetch(int i, int i2, final InfiniteDataFinisher<MerchantFeedItem> infiniteDataFinisher) {
                baseProductFeedServiceFragment.fetchTrendingMerchants(i, i2, new SuccessCallback() {
                    public void onSuccess(boolean z, List<MerchantFeedItem> list) {
                        infiniteDataFinisher.finish(false, z, list);
                    }
                }, new DefaultFailureCallback() {
                    public void onFailure(String str) {
                        infiniteDataFinisher.finish(true, false, Collections.emptyList());
                    }
                });
            }
        };
    }
}
