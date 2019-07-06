package com.contextlogic.wish.activity.feed.recentlyviewed;

import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.MerchantFeedItem;
import com.contextlogic.wish.ui.recyclerview.adapter.LoadingFooterAdapter;
import java.util.ArrayList;
import java.util.List;

public class MerchantCellAdapter extends LoadingFooterAdapter<MerchantFeedItemModel, MerchantCellViewHolder> {
    public int getMyItemViewType(int i) {
        return R.layout.merchant_cell;
    }

    public MerchantCellViewHolder onCreateMyViewHolder(ViewGroup viewGroup, int i) {
        return new MerchantCellViewHolder(new MerchantCellView(viewGroup.getContext()));
    }

    public void onBindMyViewHolder(MerchantCellViewHolder merchantCellViewHolder, int i) {
        MerchantFeedItemModel merchantFeedItemModel = (MerchantFeedItemModel) getValueAt(i);
        merchantCellViewHolder.itemView.setRecentlyViewedMerchant(merchantFeedItemModel.getMerchantFeedItem(), merchantFeedItemModel.getScrollX());
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MERCHANT_FEED_ITEM_CELL);
    }

    public void onMyViewRecycled(MerchantCellViewHolder merchantCellViewHolder, int i) {
        ((MerchantFeedItemModel) getValueAt(i)).setScrollX(merchantCellViewHolder.itemView.getRecyclerViewScrollX());
    }

    public static List<MerchantFeedItemModel> transformToItemModels(List<MerchantFeedItem> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (MerchantFeedItem merchantFeedItemModel : list) {
            arrayList.add(new MerchantFeedItemModel(merchantFeedItemModel));
        }
        return arrayList;
    }
}
