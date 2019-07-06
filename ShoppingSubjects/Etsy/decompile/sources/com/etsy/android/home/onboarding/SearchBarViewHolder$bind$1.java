package com.etsy.android.home.onboarding;

import android.support.v7.widget.SearchView.OnQueryTextListener;
import com.etsy.android.lib.models.apiv3.vespa.SearchBar;
import com.etsy.android.vespa.m;
import kotlin.collections.af;

/* compiled from: SearchBarViewHolder.kt */
public final class SearchBarViewHolder$bind$1 implements OnQueryTextListener {
    final /* synthetic */ SearchBar $data;
    final /* synthetic */ SearchBarViewHolder this$0;

    public boolean onQueryTextChange(String str) {
        return false;
    }

    SearchBarViewHolder$bind$1(SearchBarViewHolder searchBarViewHolder, SearchBar searchBar) {
        this.this$0 = searchBarViewHolder;
        this.$data = searchBar;
    }

    public boolean onQueryTextSubmit(String str) {
        m metadataProvider = this.$data.getMetadataProvider();
        if (metadataProvider != null) {
            String listSectionAnalyticsName = metadataProvider.getListSectionAnalyticsName();
            if (listSectionAnalyticsName != null) {
                this.this$0.mViewTracker.a(listSectionAnalyticsName, af.a());
            }
        }
        return false;
    }
}
