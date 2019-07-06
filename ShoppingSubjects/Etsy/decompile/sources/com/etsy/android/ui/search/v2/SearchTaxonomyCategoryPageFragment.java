package com.etsy.android.ui.search.v2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.apiv3.SearchCategoryRedirectPage;
import com.etsy.android.lib.models.apiv3.TaxonomyCategory;
import com.etsy.android.lib.models.cardviewelement.Page;
import com.etsy.android.lib.models.cardviewelement.SearchPageLink;
import com.etsy.android.ui.homescreen.CardRecyclerViewBaseFragment;
import com.etsy.android.ui.search.v2.impressions.SearchAdsImpressionsLogger;
import com.etsy.android.vespa.b;
import com.etsy.android.vespa.b.a;
import com.etsy.android.vespa.b.d;
import com.etsy.android.vespa.k;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SearchTaxonomyCategoryPageFragment extends CardRecyclerViewBaseFragment {
    public static final String TAG = f.a(SearchTaxonomyCategoryPageFragment.class);
    private SearchAdsImpressionsLogger mAdsImpressionsLogger = new SearchAdsImpressionsLogger();
    private String mAnchorListingId;
    private Page mCategoryRedirectPage;
    protected d mPaginationNone = new d();
    /* access modifiers changed from: private */
    public TaxonomyNode mTaxonomyNode;

    @NonNull
    public String getApiUrl() {
        return "/etsyapps/v3/bespoke/member/category-taxonomy-page";
    }

    @NonNull
    public String getTrackingName() {
        return "native_category_page";
    }

    public a getPagination() {
        return this.mPaginationNone;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments.containsKey("SEARCH_TAXONOMY_NODE")) {
            this.mTaxonomyNode = (TaxonomyNode) org.parceler.d.a(arguments.getParcelable("SEARCH_TAXONOMY_NODE"));
            if (this.mTaxonomyNode != null) {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Taxonomy Node is ");
                sb.append(this.mTaxonomyNode.toString());
                f.c(str, sb.toString());
            }
        } else if (bundle == null && arguments.containsKey("SEARCH_CATEGORY_REDIRECT")) {
            SearchCategoryRedirectPage searchCategoryRedirectPage = (SearchCategoryRedirectPage) org.parceler.d.a(arguments.getParcelable("SEARCH_CATEGORY_REDIRECT"));
            this.mCategoryRedirectPage = searchCategoryRedirectPage.getCategoryLandingPage();
            this.mTaxonomyNode = searchCategoryRedirectPage.getTaxonomyNode();
            arguments.remove("SEARCH_CATEGORY_REDIRECT");
            if (this.mTaxonomyNode != null) {
                arguments.putParcelable("SEARCH_TAXONOMY_NODE", org.parceler.d.a(this.mTaxonomyNode));
            }
        }
        if (arguments.containsKey("ANCHOR_LISTING_ID")) {
            this.mAnchorListingId = arguments.getString("ANCHOR_LISTING_ID");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        onCreateView.setBackgroundColor(getResources().getColor(R.color.sk_bg_white));
        this.mRecyclerView.addOnScrollListener(this.mAdsImpressionsLogger);
        AnonymousClass1 r4 = new b<k>(getActivity(), getAnalyticsContext()) {
            public void a(k kVar) {
                if (kVar instanceof SearchPageLink) {
                    SearchPageLink searchPageLink = (SearchPageLink) kVar;
                    TaxonomyNode taxonomyNode = new TaxonomyNode(searchPageLink.getTaxonomyId().getId(), searchPageLink.getPageTitle());
                    SearchTaxonomyCategoryPageFragment.this.logTouchEvent("view_more_results_click", taxonomyNode.getTaxonomyNodeId().toString());
                    if (SearchTaxonomyCategoryPageFragment.this.getActivity() instanceof SearchV2Activity) {
                        SearchV2Activity.addSearchResultsFragment(d(), "", taxonomyNode);
                    }
                } else if (kVar instanceof TaxonomyCategory) {
                    TaxonomyCategory taxonomyCategory = (TaxonomyCategory) kVar;
                    TaxonomyNode buildTaxonomyNode = taxonomyCategory.buildTaxonomyNode();
                    if (!(SearchTaxonomyCategoryPageFragment.this.mTaxonomyNode == null || buildTaxonomyNode == null)) {
                        SearchTaxonomyCategoryPageFragment.this.logTouchEvent("category_click", buildTaxonomyNode.getTaxonomyNodeId().toString());
                    }
                    FragmentActivity d = d();
                    if (taxonomyCategory.getPageLink().isCategoryPage()) {
                        SearchV2Activity.addCategoryPageFragment(d, buildTaxonomyNode, null);
                    } else {
                        SearchV2Activity.addSearchResultsFragment(d, "", buildTaxonomyNode);
                    }
                }
            }
        };
        registerCardViewFactoryClickHandler(R.id.view_type_section_link_footer, r4);
        registerCardViewFactoryClickHandler(R.id.view_type_category_card, r4);
        registerCardViewFactoryClickHandler(R.id.view_type_taxonomy_category, r4);
        if (bundle == null) {
            if (this.mCategoryRedirectPage != null) {
                onLoadComplete(this.mCategoryRedirectPage);
                this.mCategoryRedirectPage = null;
            } else {
                loadContent();
            }
        }
        return onCreateView;
    }

    public void onResume() {
        super.onResume();
        HashMap hashMap = new HashMap();
        if (this.mTaxonomyNode != null) {
            hashMap.put(ResponseConstants.TAXONOMY_ID, this.mTaxonomyNode.getTaxonomyNodeId().toString());
        }
        com.etsy.android.lib.logger.legacy.b.a().a("native_category_page", hashMap);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getAdapter().onSaveInstanceState(bundle);
        this.mAdsImpressionsLogger.onSaveInstanceState(bundle);
    }

    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        this.mAdsImpressionsLogger.onRestoreInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getRequestParams() {
        HashMap hashMap = new HashMap();
        if (this.mTaxonomyNode != null) {
            hashMap.put(ResponseConstants.TAXONOMY_ID, this.mTaxonomyNode.getTaxonomyNodeId().toString());
        }
        if (this.mAnchorListingId != null) {
            hashMap.put(ResponseConstants.ANCHOR_LISTING_ID, this.mAnchorListingId);
        }
        Locale locale = Locale.getDefault();
        if (locale != null) {
            String country = locale.getCountry();
            if (!TextUtils.isEmpty(country)) {
                hashMap.put("ship_to", country);
            }
        }
        return hashMap;
    }

    public void onRefresh() {
        super.onRefresh();
        this.mAdsImpressionsLogger.reset();
    }

    /* access modifiers changed from: private */
    public void logTouchEvent(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put(AnalyticsLogAttribute.TAXONOMY_ID, str2);
        getAnalyticsContext().a(str, hashMap);
    }
}
