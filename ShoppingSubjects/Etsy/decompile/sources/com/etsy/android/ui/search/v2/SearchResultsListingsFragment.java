package com.etsy.android.ui.search.v2;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.work.j;
import com.etsy.android.R;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.i;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.models.SearchImpressionMetadata;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.apiv3.FacetCount;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.apiv3.SearchCategoryRedirectPage;
import com.etsy.android.lib.models.apiv3.SearchWithAds;
import com.etsy.android.lib.models.cardviewelement.ListSection;
import com.etsy.android.lib.models.cardviewelement.Page;
import com.etsy.android.lib.models.cardviewelement.SearchFilterHeader;
import com.etsy.android.stylekit.CompoundVectorTextView;
import com.etsy.android.ui.cardview.viewholders.SearchFilterHeaderViewHolder;
import com.etsy.android.ui.nav.e;
import com.etsy.android.ui.search.v2.impressions.SearchAdsImpressionsLogger;
import com.etsy.android.ui.search.v2.impressions.SearchImpressionsOnScrollListener;
import com.etsy.android.ui.search.v2.impressions.SearchImpressionsUploadWorker;
import com.etsy.android.ui.search.v2.impressions.d;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.vespa.VespaBaseFragment;
import com.etsy.android.vespa.c;
import com.etsy.android.vespa.k;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import io.reactivex.functions.Consumer;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SearchResultsListingsFragment extends VespaBaseFragment implements com.etsy.android.lib.core.b.a, a, com.etsy.android.ui.search.v2.impressions.SearchImpressionsOnScrollListener.a {
    private static final int ITEMS_PER_PAGE = 30;
    private static final String SAVE_CATEGORY_FACETS = "category_facets";
    private static final String SAVE_FILTER_SHEET_IS_SHOWING = "filter_sheet_is_showing";
    private static final String SAVE_RESULT_COUNT = "result_count";
    private static final String SAVE_SEARCH_IMPRESSION_DISPLAYED_LISTINGS = "displayed_listings";
    private static final String SAVE_SEARCH_IMPRESSION_ON_SCROLL_LISTENER_ENABLED = "enabled";
    private static final String SAVE_SEARCH_OPTIONS = "search_options";
    public static final String TAG = f.a(SearchResultsListingsFragment.class);
    protected SearchAdsImpressionsLogger adsImpressionsLogger = new SearchAdsImpressionsLogger();
    @Nullable
    private String anchorListingId = null;
    List<FacetCount> categoryFacets;
    private io.reactivex.disposables.a disposables = new io.reactivex.disposables.a();
    SearchFilterHeader filterHeader;
    SearchFiltersSheet filtersSheet;
    protected SearchWithAds forwardedSearchWithAds;
    final a listeners = new a();
    l logCat;
    /* access modifiers changed from: private */
    @NonNull
    public com.etsy.android.vespa.b.b pagination = new com.etsy.android.vespa.b.b();
    private String query;
    @Nullable
    private Bundle requestParams = null;
    private int resultCount;
    com.etsy.android.lib.f.a schedulers;
    d searchImpressionRepository;
    private SearchImpressionsOnScrollListener searchImpressionsOnScrollListener = new SearchImpressionsOnScrollListener();
    protected SearchOptions searchOptions;
    private TaxonomyNode taxonomyNode;
    j workManager;

    class a implements com.etsy.android.ui.search.v2.SearchFiltersSheet.b {
        a() {
        }

        public void a(SearchOptions searchOptions) {
            if (SearchResultsListingsFragment.this.filtersSheet.d() || SearchResultsListingsFragment.this.mEmptyView.getVisibility() == 0) {
                SearchResultsListingsFragment.this.resetAndLoadContent();
            }
        }
    }

    class b extends c<FragmentActivity> {
        public b(FragmentActivity fragmentActivity, com.etsy.android.lib.logger.b bVar) {
            super(fragmentActivity, bVar);
        }

        /* access modifiers changed from: protected */
        public void a() {
            a((int) R.id.view_type_search_filter_header, (com.etsy.android.vespa.b) new com.etsy.android.vespa.b<k>(SearchResultsListingsFragment.this.getActivity(), SearchResultsListingsFragment.this.getAnalyticsContext()) {
                public void a(k kVar) {
                    SearchResultsListingsFragment.this.filtersSheet.a(SearchResultsListingsFragment.this.categoryFacets);
                }
            });
        }

        public BaseViewHolder a(ViewGroup viewGroup, int i) {
            if (i != R.id.view_type_search_filter_header) {
                return null;
            }
            return new SearchFilterHeaderViewHolder(viewGroup, b(i));
        }

        public int a(int i, int i2) {
            if (i2 >= SearchResultsListingsFragment.this.getAdapter().getItemCount()) {
                return 1;
            }
            return c();
        }
    }

    static final /* synthetic */ void lambda$onListingCardShown$1$SearchResultsListingsFragment(Long l) throws Exception {
    }

    @NonNull
    public String getApiUrl() {
        return null;
    }

    @LayoutRes
    public int getLayoutId() {
        return R.layout.fragment_new_search_results;
    }

    public int getLoadTriggerPosition() {
        return 16;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            this.query = "";
        } else if (bundle != null || !arguments.containsKey("SEARCH_CATEGORY_REDIRECT")) {
            this.query = arguments.getString("SEARCH_QUERY", "");
            this.anchorListingId = arguments.getString("ANCHOR_LISTING_ID", null);
            this.requestParams = arguments.getBundle("SEARCH_REQUEST_PARAMS");
            if (bundle == null) {
                this.taxonomyNode = (TaxonomyNode) org.parceler.d.a(arguments.getParcelable("SEARCH_TAXONOMY_NODE"));
                this.searchOptions = (SearchOptions) arguments.getParcelable("SEARCH_OPTIONS");
            } else {
                this.taxonomyNode = null;
            }
        } else {
            SearchCategoryRedirectPage searchCategoryRedirectPage = (SearchCategoryRedirectPage) org.parceler.d.a(arguments.getParcelable("SEARCH_CATEGORY_REDIRECT"));
            this.forwardedSearchWithAds = searchCategoryRedirectPage.getSearchResults();
            this.taxonomyNode = searchCategoryRedirectPage.getTaxonomyNode();
            arguments.remove("SEARCH_CATEGORY_REDIRECT");
            this.query = searchCategoryRedirectPage.getFilterParam(SearchCategoryRedirectPage.PARAM_QUERY);
            if (arguments.containsKey("SEARCH_OPTIONS")) {
                this.searchOptions = (SearchOptions) arguments.getParcelable("SEARCH_OPTIONS");
                arguments.remove("SEARCH_OPTIONS");
            }
            SearchV2Activity.getSearchViewHelper(getActivity()).b(this.query);
            return;
        }
        this.searchImpressionsOnScrollListener.setOnListingCardShownListener(this);
        this.searchImpressionsOnScrollListener.setEnabled(!TextUtils.isEmpty(this.query));
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.filtersSheet != null) {
            bundle.putBoolean(SAVE_FILTER_SHEET_IS_SHOWING, this.filtersSheet.d());
        }
        bundle.putInt(SAVE_RESULT_COUNT, this.resultCount);
        this.adsImpressionsLogger.onSaveInstanceState(bundle);
        if (this.categoryFacets != null) {
            bundle.putParcelable(SAVE_CATEGORY_FACETS, org.parceler.d.a(this.categoryFacets));
        }
        if (this.searchOptions != null) {
            bundle.putParcelable(SAVE_SEARCH_OPTIONS, this.searchOptions);
        }
        bundle.putSerializable(SAVE_SEARCH_IMPRESSION_DISPLAYED_LISTINGS, this.searchImpressionsOnScrollListener.getDisplayedListings());
        bundle.putBoolean("enabled", this.searchImpressionsOnScrollListener.isEnabled());
        super.onSaveInstanceState(bundle);
    }

    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        this.adsImpressionsLogger.onRestoreInstanceState(bundle);
        if (bundle != null) {
            if (bundle.containsKey(SAVE_SEARCH_IMPRESSION_DISPLAYED_LISTINGS)) {
                HashSet hashSet = (HashSet) bundle.getSerializable(SAVE_SEARCH_IMPRESSION_DISPLAYED_LISTINGS);
                if (hashSet != null) {
                    this.searchImpressionsOnScrollListener.setDisplayedListings(hashSet);
                }
            }
            if (bundle.containsKey("enabled")) {
                this.searchImpressionsOnScrollListener.setEnabled(bundle.getBoolean("enabled"));
            }
        }
    }

    private void init(Bundle bundle) {
        if (bundle != null) {
            this.categoryFacets = (List) org.parceler.d.a(bundle.getParcelable(SAVE_CATEGORY_FACETS));
            this.searchOptions = (SearchOptions) bundle.getParcelable(SAVE_SEARCH_OPTIONS);
            this.resultCount = bundle.getInt(SAVE_RESULT_COUNT);
            getAdapter().onRestoreInstanceState(bundle);
        }
        if (this.searchOptions == null) {
            this.searchOptions = new SearchOptions();
        }
    }

    @NonNull
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        onCreateView.setBackground(getResources().getDrawable(R.color.sk_bg_white));
        this.mRecyclerView.addOnScrollListener(this.adsImpressionsLogger);
        this.mRecyclerView.addOnScrollListener(this.searchImpressionsOnScrollListener);
        addDelegateViewHolderFactory(new b(getActivity(), getAnalyticsContext()));
        addDelegateViewHolderFactory(new com.etsy.android.ui.cardview.a(getActivity(), getAdapter(), getAnalyticsContext()));
        init(bundle);
        this.filtersSheet = new SearchFiltersSheet(onCreateView, this.searchOptions, this.listeners, getAnalyticsContext().c());
        SearchV2Activity.getSearchViewHelper(getActivity()).c();
        if (this.forwardedSearchWithAds != null) {
            initTopLevelFacet();
            setSearchWithAdsResults(this.forwardedSearchWithAds, true);
            this.forwardedSearchWithAds = null;
        } else if (isEmpty()) {
            loadContent();
        }
        if (bundle != null && bundle.getBoolean(SAVE_FILTER_SHEET_IS_SHOWING, false)) {
            this.filtersSheet.a(this.categoryFacets);
        }
        this.mEmptyView.setVisibility(8);
        return onCreateView;
    }

    @NonNull
    public com.etsy.android.vespa.b.a getPagination() {
        return this.pagination;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.setVisibility(0);
        com.etsy.android.uikit.util.j.a(this.mRecyclerView.getViewTreeObserver(), (OnGlobalLayoutListener) new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (SearchResultsListingsFragment.this.mRecyclerView != null && SearchResultsListingsFragment.this.mRecyclerView.getChildCount() > 0) {
                    com.etsy.android.uikit.util.j.b(SearchResultsListingsFragment.this.mRecyclerView.getViewTreeObserver(), (OnGlobalLayoutListener) this);
                    FragmentActivity activity = SearchResultsListingsFragment.this.getActivity();
                    if (activity instanceof TrackingBaseActivity) {
                        ((TrackingBaseActivity) activity).getGraphiteTimerManager().b("search_results.time_to_results_displayed");
                    }
                }
            }
        });
    }

    public void onPause() {
        super.onPause();
        androidx.work.f fVar = (androidx.work.f) new androidx.work.f.a(SearchImpressionsUploadWorker.class).e();
        this.workManager.a(fVar);
    }

    public void onStop() {
        super.onStop();
        this.disposables.dispose();
    }

    public void onDestroyView() {
        this.filtersSheet.f();
        this.filtersSheet = null;
        getRequestQueue().a((Object) this);
        super.onDestroyView();
    }

    private void loadSearchResultsWithTaxonomyFilter() {
        initTopLevelFacet();
        loadSearchResults();
    }

    private void initTopLevelFacet() {
        if (this.taxonomyNode != null) {
            this.filtersSheet.a(new FacetCount(this.taxonomyNode.getTaxonomyNodeId().getId(), this.taxonomyNode.getName(), 0));
            this.taxonomyNode = null;
        }
    }

    private void loadSearchResults() {
        final boolean isEmpty = TextUtils.isEmpty(this.pagination.getApiNextLink());
        if (isEmpty) {
            this.adsImpressionsLogger.reset();
            this.searchImpressionsOnScrollListener.reset();
        }
        i a2 = e.a(this.query, this.anchorListingId, this.filtersSheet.e(), e.a, new e.c() {
            public void a(SearchWithAds searchWithAds, String str) {
                SearchResultsListingsFragment.this.pagination.a(str);
                SearchResultsListingsFragment.this.setSearchWithAdsResults(searchWithAds, isEmpty);
            }

            public void a() {
                SearchResultsListingsFragment.this.onLoadFailure();
            }
        }, this.requestParams, this.pagination.getApiNextLink());
        com.etsy.android.lib.core.j requestQueue = getRequestQueue();
        requestQueue.a((Object) this);
        requestQueue.a((Object) this, (g<Result>) a2);
    }

    /* access modifiers changed from: 0000 */
    public void setSearchWithAdsResults(SearchWithAds searchWithAds, boolean z) {
        List<FacetCount> categoryFacetCounts = searchWithAds.getFacetCountListMap().getCategoryFacetCounts();
        if (categoryFacetCounts != null && categoryFacetCounts.size() > 0) {
            this.categoryFacets = categoryFacetCounts;
            FacetCount selectedCategoryFacet = this.filtersSheet.e().getSelectedCategoryFacet();
            if (selectedCategoryFacet != null) {
                this.filtersSheet.a(categoryFacetCounts, selectedCategoryFacet.getId());
            }
        }
        if (z) {
            this.mAdapter.clear();
            this.resultCount = searchWithAds.getCount();
        }
        int size = searchWithAds.getListingCardList().size();
        for (int i = 0; i < size; i++) {
            boolean isAd = ((ListingCard) searchWithAds.getListingCardList().get(i)).isAd();
        }
        onLoadComplete(formatResultsAsPage(searchWithAds, z));
    }

    private Page formatResultsAsPage(SearchWithAds searchWithAds, boolean z) {
        Page page = new Page();
        if (searchWithAds.getCount() > 0) {
            ListingCard anchorListing = searchWithAds.getAnchorListing();
            if (anchorListing != null) {
                ListSection listSection = new ListSection();
                ArrayList arrayList = new ArrayList();
                anchorListing.setViewType(R.id.view_type_anchor_listing_card);
                arrayList.add(anchorListing);
                listSection.setItems(arrayList);
                page.addListSection(listSection);
            }
            ListSection listSection2 = new ListSection();
            if (z) {
                this.filterHeader = new SearchFilterHeader(getString(R.string.search_filter));
                listSection2.setHeader(this.filterHeader);
            }
            listSection2.setItems(searchWithAds.getListingCardList());
            page.addListSection(listSection2);
            if (this.filterHeader != null && z) {
                String str = "";
                if (searchWithAds.getBackoffQuery() != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(String.format(getString(R.string.search_backoff_query), new Object[]{searchWithAds.getBackoffQuery(), this.query}));
                    sb.append("\n");
                    str = sb.toString();
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(getResources().getQuantityString(R.plurals.item_lowercase_quantity, this.resultCount, new Object[]{NumberFormat.getInstance().format((long) this.resultCount)}));
                this.filterHeader.setTitle(sb2.toString());
                this.filterHeader.setSubtitle(buildActiveOptionsString(this.filtersSheet.h()));
            }
        }
        return page;
    }

    /* access modifiers changed from: protected */
    public String buildActiveOptionsString(List<com.etsy.android.ui.search.v2.SearchFiltersSheet.a> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(((com.etsy.android.ui.search.v2.SearchFiltersSheet.a) list.get(i)).a());
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void onLoadContent() {
        loadResults();
    }

    /* access modifiers changed from: protected */
    public void loadResults() {
        if (this.taxonomyNode != null) {
            loadSearchResultsWithTaxonomyFilter();
        } else {
            loadSearchResults();
        }
    }

    public boolean handleBackPressed() {
        if (this.filtersSheet == null || !this.filtersSheet.d()) {
            return super.handleBackPressed();
        }
        return this.filtersSheet.c();
    }

    static View createSimplifiedQueryView(final Activity activity, final String str) {
        Resources resources = activity.getResources();
        TextView textView = new TextView(activity);
        textView.setLayoutParams(new LayoutParams(-2, -2));
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.fixed_medium);
        textView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        textView.setTextAppearance(activity, R.style.TextBlue);
        textView.setText(str);
        textView.setOnClickListener(new TrackingOnClickListener(AnalyticsLogAttribute.QUERY, str) {
            public void onViewClick(View view) {
                e.a(activity).a(str, (SearchOptions) null, (TaxonomyNode) null, (String) null, (Bundle) null);
            }
        });
        return textView;
    }

    private static View createFilterRemovalView(Activity activity, com.etsy.android.ui.search.v2.SearchFiltersSheet.a aVar) {
        Resources resources = activity.getResources();
        CompoundVectorTextView compoundVectorTextView = new CompoundVectorTextView(activity);
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.fixed_medium);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        compoundVectorTextView.setLayoutParams(layoutParams);
        compoundVectorTextView.setBackgroundResource(R.drawable.btn_white_selector_v2);
        compoundVectorTextView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        compoundVectorTextView.setCompoundDrawablePadding(dimensionPixelSize);
        compoundVectorTextView.setTextAppearance(activity, R.style.TextMidGrey);
        compoundVectorTextView.setText(aVar.a());
        compoundVectorTextView.setGravity(16);
        compoundVectorTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, com.etsy.android.uikit.c.a(compoundVectorTextView.getContext(), R.drawable.sk_ic_close, R.color.sk_gray_50), null);
        compoundVectorTextView.setOnClickListener(new h(aVar));
        return compoundVectorTextView;
    }

    /* access modifiers changed from: protected */
    public void initEmptyStateViews(View view) {
        this.mEmptyView = view.findViewById(R.id.empty_view);
        this.mEmptyText = (TextView) view.findViewById(R.id.empty_view_text);
        this.mEmptySubtext = (TextView) view.findViewById(R.id.empty_view_subtext);
        this.mEmptyButton = (Button) view.findViewById(R.id.empty_button);
        this.mEmptyImage = (ImageView) view.findViewById(R.id.empty_image);
        this.mErrorView = view.findViewById(R.id.no_internet);
        this.mLoadingView = view.findViewById(R.id.loading_view);
        View findViewById = this.mErrorView.findViewById(R.id.btn_retry_internet);
        if (findViewById != null) {
            findViewById.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    SearchResultsListingsFragment.this.onRetry();
                }
            });
        }
    }

    public void showErrorView() {
        if (this.mSwipeRefreshLayout != null) {
            this.mSwipeRefreshLayout.setRefreshing(false);
        }
        if (this.mEmptyView != null) {
            this.mEmptyView.setVisibility(8);
        }
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(0);
        }
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(8);
        }
        if (this.mRecyclerView != null) {
            this.mRecyclerView.setVisibility(8);
        }
    }

    public void showListView() {
        super.showListView();
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(8);
        }
    }

    public void showEmptyView() {
        Resources resources = this.mEmptyView.getResources();
        TextView textView = (TextView) this.mEmptyView.findViewById(R.id.new_search_empty_title);
        int i = 0;
        if (TextUtils.isEmpty(this.query)) {
            textView.setText(R.string.new_search_empty_view_title_for_no_query);
        } else {
            textView.setText(resources.getString(R.string.new_search_empty_view_title, new Object[]{this.query}));
        }
        LinearLayout linearLayout = (LinearLayout) this.mEmptyView.findViewById(R.id.new_search_empty_filter_list);
        linearLayout.removeAllViews();
        List g = this.filtersSheet.g();
        int size = g.size();
        for (int i2 = 0; i2 < size; i2++) {
            linearLayout.addView(createFilterRemovalView(getActivity(), (com.etsy.android.ui.search.v2.SearchFiltersSheet.a) g.get(i2)));
        }
        View findViewById = this.mEmptyView.findViewById(R.id.new_search_empty_filter_title);
        if (g.isEmpty()) {
            i = 8;
        }
        findViewById.setVisibility(i);
        final View findViewById2 = this.mEmptyView.findViewById(R.id.new_search_empty_query_title);
        findViewById2.setVisibility(8);
        final LinearLayout linearLayout2 = (LinearLayout) this.mEmptyView.findViewById(R.id.new_search_empty_query_list);
        linearLayout2.removeAllViews();
        getRequestQueue().a((g<Result>) e.a(this.query, new com.etsy.android.ui.search.v2.e.b() {
            public void a() {
            }

            public void a(List<String> list) {
                if (SearchResultsListingsFragment.this.getActivity() != null) {
                    FragmentActivity activity = SearchResultsListingsFragment.this.getActivity();
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        linearLayout2.addView(SearchResultsListingsFragment.createSimplifiedQueryView(activity, (String) list.get(i)));
                    }
                    if (!list.isEmpty()) {
                        findViewById2.setVisibility(0);
                    }
                }
            }
        }));
        super.showEmptyView();
    }

    public void onListingCardShown(ListingCard listingCard) {
        SearchImpressionMetadata searchImpressionMetadata = listingCard.getSearchImpressionMetadata();
        if (searchImpressionMetadata != null && searchImpressionMetadata.getDisplayLocation() != null && searchImpressionMetadata.getLoggingKey() != null && searchImpressionMetadata.getData() != null) {
            this.disposables.a(this.searchImpressionRepository.a(new com.etsy.android.ui.search.v2.impressions.c(searchImpressionMetadata.getDisplayLocation(), searchImpressionMetadata.getLoggingKey(), searchImpressionMetadata.getData())).b(this.schedulers.a()).a(i.a, (Consumer<? super Throwable>) new j<Object>(this)));
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$onListingCardShown$2$SearchResultsListingsFragment(Throwable th) throws Exception {
        this.logCat.b("Error inserting search impression", th);
    }

    public String getQuery() {
        return this.query;
    }
}
