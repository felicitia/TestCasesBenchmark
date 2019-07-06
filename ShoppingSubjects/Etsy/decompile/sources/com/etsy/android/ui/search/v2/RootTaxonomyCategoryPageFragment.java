package com.etsy.android.ui.search.v2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.ui.search.v2.SearchTaxonomyListLayout.a;
import com.etsy.android.uikit.ui.core.TrackingBaseFragment;

public class RootTaxonomyCategoryPageFragment extends TrackingBaseFragment implements a {
    private static final String KEY_CONFIG_SHOW_TRENDING_SEARCHES = "key_config_show_trending_searches";
    private boolean mConfigShowTrendingSearches = false;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.mConfigShowTrendingSearches = getAnalyticsContext().c().c(b.bG);
        } else {
            this.mConfigShowTrendingSearches = bundle.getBoolean(KEY_CONFIG_SHOW_TRENDING_SEARCHES);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        SearchTaxonomyListLayout searchTaxonomyListLayout = new SearchTaxonomyListLayout((Context) getActivity(), this.mConfigShowTrendingSearches);
        searchTaxonomyListLayout.setListener(this);
        return searchTaxonomyListLayout;
    }

    public void onTaxonomySelected(TaxonomyNode taxonomyNode) {
        SearchV2Activity.addCategoryPageFragment(getActivity(), taxonomyNode, null);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean(KEY_CONFIG_SHOW_TRENDING_SEARCHES, this.mConfigShowTrendingSearches);
        super.onSaveInstanceState(bundle);
    }
}
