package com.etsy.android.ui.search.v2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request.a;
import com.etsy.android.lib.core.http.request.a.C0065a;
import com.etsy.android.lib.core.http.request.d;
import com.etsy.android.lib.core.http.request.d.b;
import com.etsy.android.lib.core.n;
import com.etsy.android.lib.models.apiv3.SearchCategoryRedirectPage;
import com.etsy.android.lib.requests.HttpUtil;
import com.etsy.android.uikit.BaseRecyclerViewListFragment;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCategoryPageRedirectFragment extends BaseRecyclerViewListFragment {
    @NonNull
    public String getApiUrl() {
        return "/etsyapps/v3/bespoke/member/category-landing-redirect/%s";
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        loadContent();
        return onCreateView;
    }

    /* access modifiers changed from: protected */
    public void onLoadContent() {
        final Bundle arguments = getArguments();
        if (!arguments.containsKey("SEARCH_CATEGORY_REDIRECT") || !arguments.containsKey("SEARCH_CATEGORY_REDIRECT_QUERY_PARAMS")) {
            showErrorView();
            return;
        }
        String string = arguments.getString("SEARCH_CATEGORY_REDIRECT");
        String format = String.format(getApiUrl(), new Object[]{string});
        HashMap hashMap = (HashMap) arguments.getSerializable("SEARCH_CATEGORY_REDIRECT_QUERY_PARAMS");
        if (hashMap != null) {
            HashMap hashMap2 = new HashMap();
            if (hashMap.containsKey(SearchCategoryRedirectPage.PARAM_QUERY) && !TextUtils.isEmpty((CharSequence) hashMap.get(SearchCategoryRedirectPage.PARAM_QUERY))) {
                hashMap2.put(SearchCategoryRedirectPage.PARAM_QUERY, hashMap.get(SearchCategoryRedirectPage.PARAM_QUERY));
            }
            if (!Collections.disjoint(n.a.a(), hashMap.keySet())) {
                hashMap2.putAll(n.a.a(hashMap));
            }
            if (hashMap2.size() > 0) {
                hashMap.keySet().removeAll(hashMap2.keySet());
                hashMap.putAll(HttpUtil.formatMapAsParams("query_parameters", hashMap2));
            }
        }
        a a = a.a(SearchCategoryRedirectPage.class, format);
        a.a((Map<String, String>) hashMap);
        d.a a2 = d.a.a((EtsyApiV3Request) a.d());
        a2.a((C0065a<ResultType>) new b<SearchCategoryRedirectPage>() {
            public void a(@NonNull List<SearchCategoryRedirectPage> list, int i, @NonNull com.etsy.android.lib.core.a.a<SearchCategoryRedirectPage> aVar) {
                if (!list.isEmpty()) {
                    SearchCategoryRedirectPage searchCategoryRedirectPage = (SearchCategoryRedirectPage) list.get(0);
                    if (searchCategoryRedirectPage.isCategoryLandingPage()) {
                        FragmentTransaction beginTransaction = SearchCategoryPageRedirectFragment.this.getFragmentManager().beginTransaction();
                        SearchTaxonomyCategoryPageFragment searchTaxonomyCategoryPageFragment = new SearchTaxonomyCategoryPageFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("SEARCH_CATEGORY_REDIRECT", org.parceler.d.a(searchCategoryRedirectPage));
                        searchTaxonomyCategoryPageFragment.setArguments(bundle);
                        beginTransaction.remove(SearchCategoryPageRedirectFragment.this);
                        beginTransaction.add(R.id.search_content_view, searchTaxonomyCategoryPageFragment, SearchTaxonomyCategoryPageFragment.TAG);
                        beginTransaction.commit();
                    } else {
                        FragmentTransaction beginTransaction2 = SearchCategoryPageRedirectFragment.this.getFragmentManager().beginTransaction();
                        SearchResultsListingsFragment searchResultsListingsFragment = new SearchResultsListingsFragment();
                        Bundle bundle2 = new Bundle();
                        bundle2.putParcelable("SEARCH_CATEGORY_REDIRECT", org.parceler.d.a(searchCategoryRedirectPage));
                        if (arguments.containsKey("SEARCH_OPTIONS")) {
                            bundle2.putParcelable("SEARCH_OPTIONS", arguments.getParcelable("SEARCH_OPTIONS"));
                        }
                        searchResultsListingsFragment.setArguments(bundle2);
                        beginTransaction2.remove(SearchCategoryPageRedirectFragment.this);
                        beginTransaction2.add(R.id.search_content_view, searchResultsListingsFragment, SearchResultsListingsFragment.TAG);
                        beginTransaction2.commit();
                    }
                }
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<SearchCategoryRedirectPage> aVar) {
                SearchCategoryPageRedirectFragment.this.showErrorView();
            }
        }, (Fragment) this);
        getRequestQueue().a((Object) this, a2.c());
    }
}
