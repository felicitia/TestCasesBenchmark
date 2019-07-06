package com.etsy.android.ui.local;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.apiv3.LocalBrowseModule;
import com.etsy.android.lib.models.apiv3.LocalBrowseResponse;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseBaseHeaderViewHolder;
import com.etsy.android.uikit.adapter.AbstractContextRecyclerViewAdapter;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.adapter.SectionedRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

public class LocalBrowseListFragment extends LocalBrowseBaseListFragment<BaseRecyclerViewAdapter> {
    private List<LocalBrowseModule> mResultsSections;
    private com.etsy.android.ui.cardview.viewholders.LocalBrowseSectionFooterViewHolder.a mSectionListener = new com.etsy.android.ui.cardview.viewholders.LocalBrowseSectionFooterViewHolder.a() {
        public void a(LocalBrowseModule localBrowseModule) {
            if (LocalBrowseListFragment.this.mLocalBrowseManager != null) {
                LocalBrowseListFragment.this.mLocalBrowseManager.onLoadBrowseSection(localBrowseModule);
            }
        }
    };

    private class a extends LocalBrowseBaseHeaderViewHolder {
        public a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(viewGroup, LocalBrowseListFragment.this.mHeaderListener);
        }
    }

    private class b extends SectionedRecyclerViewAdapter {
        public b(FragmentActivity fragmentActivity, c cVar) {
            super(fragmentActivity, cVar);
        }

        public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
            return new a(this.mInflater, viewGroup);
        }

        public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
            ((LocalBrowseBaseHeaderViewHolder) viewHolder).bind(true);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAdapter = new b(getActivity(), getImageBatch());
    }

    /* access modifiers changed from: protected */
    public void getOrFetchResults() {
        if (this.mLocalBrowseManager == null) {
            showErrorView();
        } else if (this.mLocalBrowseManager.isRequestPending()) {
            showLoadingView();
        } else if (this.mLocalBrowseManager.getCurrentBrowseResponse() != null) {
            this.mResultsSections = this.mLocalBrowseManager.getCurrentBrowseResponse().getSections();
            if (this.mResultsSections == null || this.mResultsSections.isEmpty()) {
                showEmptyView();
            } else {
                fillAdapter(this.mResultsSections);
            }
        } else {
            this.mLocalBrowseManager.refreshLastRequest();
        }
    }

    public void onBrowseResultsSuccess(LocalBrowseResponse localBrowseResponse) {
        this.mResultsSections = null;
        if (localBrowseResponse != null && localBrowseResponse.getSections() != null && !localBrowseResponse.getSections().isEmpty()) {
            this.mResultsSections = localBrowseResponse.getSections();
            fillAdapter(this.mResultsSections);
        }
    }

    private void fillAdapter(@NonNull List<LocalBrowseModule> list) {
        this.mAdapter.clear();
        this.mAdapter.addHeader(AbstractContextRecyclerViewAdapter.VIEW_TYPE_HEADER);
        ArrayList arrayList = new ArrayList();
        for (LocalBrowseModule localBrowseModule : list) {
            if (localBrowseModule.getLocalMarkets() != null && !localBrowseModule.getLocalMarkets().isEmpty()) {
                LocalBrowseAdapter localBrowseAdapter = new LocalBrowseAdapter(getActivity(), getImageBatch(), this.mMarketListener, this.mSectionListener);
                localBrowseAdapter.addSectionData(localBrowseModule);
                localBrowseAdapter.addItems(localBrowseModule.getLocalMarkets());
                arrayList.add(localBrowseAdapter);
            }
        }
        onLoadSuccess(arrayList, 0);
    }
}
