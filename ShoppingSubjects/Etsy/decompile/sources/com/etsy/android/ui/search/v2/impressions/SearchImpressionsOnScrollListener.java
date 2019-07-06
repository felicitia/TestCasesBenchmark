package com.etsy.android.ui.search.v2.impressions;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter;
import java.util.HashSet;
import kotlin.TypeCastException;
import kotlin.jvm.internal.p;

/* compiled from: SearchImpressionsOnScrollListener.kt */
public final class SearchImpressionsOnScrollListener extends OnScrollListener {
    private HashSet<String> displayedListings = new HashSet<>();
    private boolean isEnabled;
    private a onListingCardShownListener;

    /* compiled from: SearchImpressionsOnScrollListener.kt */
    public interface a {
        void onListingCardShown(ListingCard listingCard);
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
    }

    public final boolean isEnabled() {
        return this.isEnabled;
    }

    public final void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    public final HashSet<String> getDisplayedListings() {
        return this.displayedListings;
    }

    public final void setDisplayedListings(HashSet<String> hashSet) {
        p.b(hashSet, "<set-?>");
        this.displayedListings = hashSet;
    }

    public final a getOnListingCardShownListener() {
        return this.onListingCardShownListener;
    }

    public final void setOnListingCardShownListener(a aVar) {
        this.onListingCardShownListener = aVar;
    }

    public final void reset() {
        this.displayedListings.clear();
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        if (this.isEnabled) {
            if (recyclerView == null) {
                p.a();
            }
            LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.support.v7.widget.GridLayoutManager");
            }
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int findFirstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
            int findLastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
            Adapter adapter = recyclerView.getAdapter();
            if (adapter == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter");
            }
            BaseViewHolderFactoryRecyclerViewAdapter baseViewHolderFactoryRecyclerViewAdapter = (BaseViewHolderFactoryRecyclerViewAdapter) adapter;
            if (findFirstVisibleItemPosition != -1 && findLastVisibleItemPosition != -1 && findFirstVisibleItemPosition <= findLastVisibleItemPosition) {
                while (true) {
                    if (baseViewHolderFactoryRecyclerViewAdapter.getItemViewType(findFirstVisibleItemPosition) == R.id.view_type_listing_card) {
                        Object item = baseViewHolderFactoryRecyclerViewAdapter.getItem(findFirstVisibleItemPosition);
                        if (item == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.etsy.android.lib.models.apiv3.ListingCard");
                        }
                        ListingCard listingCard = (ListingCard) item;
                        EtsyId listingId = listingCard.getListingId();
                        p.a((Object) listingId, "listingCard.listingId");
                        String id = listingId.getId();
                        if (!this.displayedListings.contains(id)) {
                            this.displayedListings.add(id);
                            a aVar = this.onListingCardShownListener;
                            if (aVar != null) {
                                aVar.onListingCardShown(listingCard);
                            }
                        }
                    }
                    if (findFirstVisibleItemPosition == findLastVisibleItemPosition) {
                        break;
                    }
                    findFirstVisibleItemPosition++;
                }
            }
        }
    }
}
