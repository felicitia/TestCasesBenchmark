package com.etsy.android.ui.search.v2.impressions;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import com.etsy.android.R;
import com.etsy.android.lib.core.posts.PersistentRequest;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.requests.SearchAdsLogRequest;
import com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter;
import java.util.HashSet;

public class SearchAdsImpressionsLogger extends OnScrollListener {
    private static final String SAVE_DISPLAYED_ADS = "displayed_ads";
    private final String TAG = f.a(SearchAdsImpressionsLogger.class);
    private HashSet<String> mDisplayedAds = new HashSet<>();

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.mDisplayedAds != null) {
            bundle.putSerializable(SAVE_DISPLAYED_ADS, this.mDisplayedAds);
        }
    }

    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null && bundle.containsKey(SAVE_DISPLAYED_ADS)) {
            this.mDisplayedAds = (HashSet) bundle.getSerializable(SAVE_DISPLAYED_ADS);
        }
    }

    public void reset() {
        this.mDisplayedAds.clear();
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        int findFirstCompletelyVisibleItemPosition = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
        int findLastCompletelyVisibleItemPosition = gridLayoutManager.findLastCompletelyVisibleItemPosition();
        BaseViewHolderFactoryRecyclerViewAdapter baseViewHolderFactoryRecyclerViewAdapter = (BaseViewHolderFactoryRecyclerViewAdapter) recyclerView.getAdapter();
        if (baseViewHolderFactoryRecyclerViewAdapter == null) {
            f.e(this.TAG, "******************************************************************");
            f.e(this.TAG, "WARNING: No Impressions being logged, SearchAdsImpressionLogger only works with CardViewFactoryRecyclerViewAdapter at the moment");
            f.e(this.TAG, "******************************************************************");
        } else if (findFirstCompletelyVisibleItemPosition != -1 && findLastCompletelyVisibleItemPosition != -1) {
            while (findFirstCompletelyVisibleItemPosition <= findLastCompletelyVisibleItemPosition) {
                if (baseViewHolderFactoryRecyclerViewAdapter.getItemViewType(findFirstCompletelyVisibleItemPosition) == R.id.view_type_listing_card) {
                    ListingCard listingCard = (ListingCard) baseViewHolderFactoryRecyclerViewAdapter.getItem(findFirstCompletelyVisibleItemPosition);
                    String etsyId = listingCard.getListingId().toString();
                    if (listingCard.isAd() && !this.mDisplayedAds.contains(etsyId)) {
                        this.mDisplayedAds.add(etsyId);
                        v.a().k().a((PersistentRequest<Request, Result>) SearchAdsLogRequest.logSearchAdsImpression(listingCard));
                    }
                }
                findFirstCompletelyVisibleItemPosition++;
            }
        }
    }
}
