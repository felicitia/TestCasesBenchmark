package com.contextlogic.wish.activity.feed.recentlyviewed;

import com.contextlogic.wish.api.model.MerchantFeedItem;

public class MerchantFeedItemModel {
    private MerchantFeedItem merchantFeedItem;
    private int scrollX;

    public MerchantFeedItemModel(MerchantFeedItem merchantFeedItem2) {
        this(merchantFeedItem2, 0);
    }

    public MerchantFeedItemModel(MerchantFeedItem merchantFeedItem2, int i) {
        this.merchantFeedItem = merchantFeedItem2;
        this.scrollX = i;
    }

    public MerchantFeedItem getMerchantFeedItem() {
        return this.merchantFeedItem;
    }

    public int getScrollX() {
        return this.scrollX;
    }

    public void setScrollX(int i) {
        this.scrollX = i;
    }
}
