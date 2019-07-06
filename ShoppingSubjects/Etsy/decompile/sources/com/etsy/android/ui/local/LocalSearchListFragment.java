package com.etsy.android.ui.local;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseBaseHeaderViewHolder;
import com.etsy.android.uikit.adapter.AbstractContextRecyclerViewAdapter;
import java.util.List;

public class LocalSearchListFragment extends LocalBrowseBaseListFragment<LocalMarketCard> {
    private List<LocalMarketCard> mResultsCards;

    private class a extends LocalBrowseAdapter {
        public a(FragmentActivity fragmentActivity, c cVar, com.etsy.android.ui.cardview.viewholders.LocalBrowseMarketViewHolder.a aVar, @NonNull com.etsy.android.ui.cardview.viewholders.LocalBrowseSectionFooterViewHolder.a aVar2) {
            super(fragmentActivity, cVar, aVar, aVar2);
        }

        public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
            return new LocalBrowseBaseHeaderViewHolder(viewGroup, LocalSearchListFragment.this.mHeaderListener);
        }

        public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
            ((LocalBrowseBaseHeaderViewHolder) viewHolder).bind(false);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a aVar = new a(getActivity(), getImageBatch(), this.mMarketListener, this.mSectionListener);
        this.mAdapter = aVar;
    }

    /* access modifiers changed from: protected */
    public void getOrFetchResults() {
        if (this.mLocalBrowseManager == null) {
            showErrorView();
        } else if (this.mLocalBrowseManager.isRequestPending()) {
            showLoadingView();
        } else if (this.mLocalBrowseManager.getCurrentSearchResponse() != null) {
            this.mResultsCards = this.mLocalBrowseManager.getCurrentSearchResponse();
            if (!this.mResultsCards.isEmpty()) {
                fillAdapter(this.mResultsCards);
            } else {
                showEmptyView();
            }
        } else {
            this.mLocalBrowseManager.refreshLastRequest();
        }
    }

    public void onSearchResultsSuccess(List<LocalMarketCard> list) {
        this.mResultsCards = null;
        if (list != null && !list.isEmpty()) {
            this.mResultsCards = list;
            fillAdapter(this.mResultsCards);
        }
    }

    private void fillAdapter(@NonNull List<LocalMarketCard> list) {
        this.mAdapter.clear();
        this.mAdapter.addHeader(AbstractContextRecyclerViewAdapter.VIEW_TYPE_HEADER);
        onLoadSuccess(list, 0);
    }
}
