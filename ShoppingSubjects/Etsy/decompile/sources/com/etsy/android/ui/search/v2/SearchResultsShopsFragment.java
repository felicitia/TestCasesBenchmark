package com.etsy.android.ui.search.v2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.ui.adapters.CardListAdapter;
import com.etsy.android.ui.search.BaseSearchResultFragment;
import java.util.Collection;
import java.util.List;

public class SearchResultsShopsFragment extends BaseSearchResultFragment implements a {
    private static final int BATCH_SIZE = 30;
    /* access modifiers changed from: private */
    public View mEmptySuggestions;
    /* access modifiers changed from: private */
    public int mOffset = 0;
    /* access modifiers changed from: private */
    public CardListAdapter mResultsAdapter;
    private View mShopHeader;
    /* access modifiers changed from: private */
    public TextView mShopsSectionTitle;

    private class a extends com.etsy.android.ui.search.a {
        public a(String str, int i, int i2) {
            super(str, i, i2, false);
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k<Shop> kVar) {
            if (kVar.a() && kVar.g() != null) {
                List g = kVar.g();
                int f = kVar.f();
                if (SearchResultsShopsFragment.this.getActivity() == null || f <= 0) {
                    if (SearchResultsShopsFragment.this.mListView.getVisibility() != 0) {
                        SearchResultsShopsFragment.this.showListView();
                    }
                    SearchResultsShopsFragment.this.mEmptySuggestions.setVisibility(0);
                    return;
                }
                SearchResultsShopsFragment.this.mEmptySuggestions.setVisibility(8);
                SearchResultsShopsFragment.this.mShopsSectionTitle.setText(com.etsy.android.ui.util.a.a(SearchResultsShopsFragment.this.mActivity, f));
                SearchResultsShopsFragment.this.mResultsAdapter.addAll((Collection<? extends T>) g);
                SearchResultsShopsFragment.this.mResultsAdapter.notifyDataSetChanged();
                if (SearchResultsShopsFragment.this.mListView.getVisibility() != 0) {
                    SearchResultsShopsFragment.this.showListView();
                }
                if (SearchResultsShopsFragment.this.mResultsAdapter.getCount() >= f) {
                    SearchResultsShopsFragment.this.stopEndless();
                } else {
                    SearchResultsShopsFragment.this.startEndless();
                }
                SearchResultsShopsFragment.this.mOffset = SearchResultsShopsFragment.this.mOffset + 30;
            } else if (kVar.k()) {
                SearchResultsShopsFragment.this.showErrorView();
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.mShopHeader == null) {
            this.mShopHeader = layoutInflater.inflate(R.layout.list_section_header_full, null);
            this.mShopsSectionTitle = (TextView) this.mShopHeader.findViewById(R.id.txt_header);
            this.mEmptySuggestions = this.mShopHeader.findViewById(R.id.empty_suggestion_panel);
        }
        onCreateView.setBackgroundColor(getResources().getColor(R.color.sk_bg_white));
        SearchV2Activity.getSearchViewHelper(getActivity()).c();
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mListView.setDivider(null);
        setEmptyText((int) R.string.empty_shop_search);
        if (this.mResultsAdapter == null) {
            this.mResultsAdapter = new CardListAdapter(this.mActivity, R.layout.list_item_card_standard_full, getImageBatch(), getAnalyticsContext());
            this.mShopsSectionTitle.setText(com.etsy.android.ui.util.a.a(this.mActivity, 0));
            this.mResultsAdapter.setHeaderView(this.mShopHeader);
            this.mResultsAdapter.setItemCountIntResource(R.integer.search_card_item_list_count);
            setListAdapter(this.mResultsAdapter);
            showLoadingView();
            doSearch();
            return;
        }
        setListAdapter(this.mResultsAdapter);
        this.mResultsAdapter.notifyDataSetChanged();
        showListView();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        getImageBatch().a();
        updateHeaderPadding();
        if (this.mResultsAdapter != null) {
            this.mResultsAdapter.notifyDataSetChanged();
        }
    }

    private void updateHeaderPadding() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.list_horizontal_card_padding);
        this.mShopHeader.setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
    }

    public void onLoadMoreItems() {
        doSearch();
    }

    /* access modifiers changed from: protected */
    public void onRetryClicked() {
        doSearch();
    }

    private void doSearch() {
        if (NetworkUtils.a().b()) {
            getRequestQueue().a((Object) this, (g<Result>) new a<Result>(this.mQuery, 30, this.mOffset));
        } else {
            showErrorView();
        }
    }

    public void showErrorView() {
        if (this.mOffset == 0) {
            super.showErrorView();
        } else {
            showEndlessError();
        }
    }
}
