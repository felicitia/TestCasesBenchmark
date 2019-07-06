package com.etsy.android.ui.shophome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.a.C0065a;
import com.etsy.android.lib.core.http.request.d.b;
import com.etsy.android.lib.core.http.url.a.b.e;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShopSection;
import com.etsy.android.lib.models.apiv3.FAQ;
import com.etsy.android.lib.models.apiv3.FAQs;
import com.etsy.android.lib.models.apiv3.ShopHomePage;
import com.etsy.android.lib.models.apiv3.ShopListingsSearchResult;
import com.etsy.android.lib.models.apiv3.ShopV3;
import com.etsy.android.lib.models.apiv3.TranslatedPrivacyPolicy;
import com.etsy.android.lib.models.apiv3.TranslatedReview;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.ShopHomeSortOption;
import com.etsy.android.lib.shophome.ShopHomeAdapter;
import com.etsy.android.lib.shophome.ShopHomeInitialLoadConfiguration;
import com.etsy.android.lib.shophome.ShopHomeSpacingUtil.ItemDecoration;
import com.etsy.android.lib.shophome.ShopHomeStateManager;
import com.etsy.android.lib.shophome.ShopHomeStateManager.a;
import com.etsy.android.lib.shophome.model.ShopHomeReviewViewModel;
import com.etsy.android.lib.shophome.model.section.ShopHomeStructuredPoliciesSectionViewModel;
import com.etsy.android.lib.shophome.viewholder.ShopHomeItemDividerDecoration;
import com.etsy.android.lib.util.t;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.uikit.util.MachineTranslationViewState;
import com.etsy.android.vespa.VespaBaseFragment;
import java.util.Iterator;
import java.util.List;
import org.parceler.d;

public class SectionedShopHomeFragment extends VespaBaseFragment implements a {
    public static final String ARG_SECTION_ID = "section_id";
    private static final int DEFAULT_LISTINGS_LIMIT = 24;
    public static final String SEARCH_PARAM_SEARCH_QUERY = "search_query";
    public static final String SEARCH_PARAM_SECTION_ID = "section_id";
    public static final String SEARCH_PARAM_SORT_ORDER = "sort_order";
    private static final String SHOP_DATA = "shop_data";
    private static final String SHOP_STATE = "shop_state";
    com.etsy.android.lib.util.b.a fileSupport;
    @Nullable
    protected ShopHomeInitialLoadConfiguration mInitialLoadConfig;
    @Nullable
    protected EtsyId mShopId;
    protected String mShopName = "";

    public void didSelectSection(@NonNull ShopSection shopSection) {
    }

    public void didSelectSortOption(@NonNull ShopHomeSortOption shopHomeSortOption) {
    }

    public void didSubmitSearchQuery(@NonNull String str) {
    }

    /* access modifiers changed from: protected */
    public int getOffset() {
        return 0;
    }

    public void performShopFavorite(boolean z) {
    }

    public void performVacationNotificationSubscription(boolean z) {
    }

    /* access modifiers changed from: protected */
    public void searchListings(@NonNull ShopHomeStateManager shopHomeStateManager, boolean z) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (bundle == null) {
            bundle = arguments;
        }
        this.mShopId = (EtsyId) d.a(bundle.getParcelable("shop_id"));
        this.mShopName = bundle.getString(ResponseConstants.SHOP_NAME, "");
        ShopHomeAdapter shopHomeAdapter = new ShopHomeAdapter(getActivity(), getAnalyticsContext(), this.fileSupport);
        shopHomeAdapter.setScrollLoadTriggerListener(this);
        this.mAdapter = shopHomeAdapter;
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mSwipeRefreshLayout.setColorSchemeResources(R.color.sk_orange_30);
        this.mSwipeRefreshLayout.setEnabled(false);
        RecyclerView recyclerView = this.mRecyclerView;
        recyclerView.setHasFixedSize(true);
        int dimensionPixelOffset = onCreateView.getResources().getDimensionPixelOffset(R.dimen.shop2_home_side_margin);
        recyclerView.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        recyclerView.setItemAnimator(new DefaultItemAnimator() {
            public boolean canReuseUpdatedViewHolder(@NonNull ViewHolder viewHolder) {
                return viewHolder.getItemViewType() != R.id.view_type_shop_home_announcement_content;
            }
        });
        recyclerView.addItemDecoration(new ShopHomeItemDividerDecoration(onCreateView.getContext()));
        recyclerView.addItemDecoration(new ItemDecoration(onCreateView.getResources()));
        recyclerView.setScrollBarStyle(33554432);
        getShopHomeFactoryAdapter().setEndlessScrolling(true);
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        ShopHomeStateManager shopHomeStateManager;
        super.onActivityCreated(bundle);
        if (bundle == null || !bundle.containsKey(SHOP_DATA)) {
            loadContent();
            return;
        }
        ShopHomePage shopHomePage = (ShopHomePage) d.a(bundle.getParcelable(SHOP_DATA));
        if (bundle.containsKey(SHOP_STATE)) {
            shopHomeStateManager = (ShopHomeStateManager) d.a(bundle.getParcelable(SHOP_STATE));
            shopHomeStateManager.setDelegate(this);
        } else {
            shopHomeStateManager = ShopHomeStateManager.newInstance(shopHomePage.getShopSections(), shopHomePage.getShop(), this, getResources());
        }
        handlePageData(shopHomePage, shopHomeStateManager);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.mAdapter;
        ShopHomePage pageData = shopHomeAdapter.getPageData();
        if (pageData != null) {
            bundle.putParcelable(SHOP_DATA, d.a(pageData));
        }
        ShopHomeStateManager stateManager = shopHomeAdapter.getStateManager();
        if (stateManager != null) {
            bundle.putParcelable(SHOP_STATE, d.a(stateManager));
        }
        bundle.putParcelable("shop_id", d.a(this.mShopId));
        bundle.putString(ResponseConstants.SHOP_NAME, this.mShopName);
    }

    public void onDestroy() {
        ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) getAdapter();
        if (shopHomeAdapter != null) {
            ShopHomeStateManager stateManager = shopHomeAdapter.getStateManager();
            if (stateManager != null) {
                stateManager.setDelegate(null);
            }
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void handlePageData(@NonNull ShopHomePage shopHomePage, @NonNull ShopHomeStateManager shopHomeStateManager) {
        populateAdapter(shopHomePage, shopHomeStateManager);
    }

    public void performSearch(@NonNull ShopHomeStateManager shopHomeStateManager) {
        searchListings(shopHomeStateManager, false);
    }

    public void loadMoreListings(@NonNull ShopHomeStateManager shopHomeStateManager) {
        ((ShopHomeAdapter) this.mAdapter).startedLoadingMoreListings();
        searchListings(shopHomeStateManager, true);
    }

    /* access modifiers changed from: protected */
    public void stopLoad() {
        this.mSwipeRefreshLayout.setRefreshing(false);
        setLoading(false);
        setRefreshing(false);
    }

    /* access modifiers changed from: protected */
    public EtsyApiV3Request<ShopListingsSearchResult> freshSearchRequest(@NonNull ShopHomeStateManager shopHomeStateManager, @NonNull EtsyId etsyId, boolean z) {
        int i = 0;
        EtsyApiV3Request.a a = EtsyApiV3Request.a.a(ShopListingsSearchResult.class, String.format("/etsyapps/v3/bespoke/member/shops/%s/listings-view", new Object[]{etsyId.getId()}));
        a.c(24);
        if (z) {
            i = getOffset();
        }
        a.b(i);
        String trim = shopHomeStateManager.getSearchedQuery().trim();
        ShopSection currentSection = shopHomeStateManager.getCurrentSection();
        ShopHomeSortOption currentSortOption = shopHomeStateManager.getCurrentSortOption();
        if (trim.length() > 0) {
            a.a("search_query", trim);
        } else if (!currentSection.isAllItemsSection()) {
            a.a("section_id", currentSection.getShopSectionId().toString());
        }
        a.a("sort_order", currentSortOption.getOptionId());
        return (EtsyApiV3Request) a.d();
    }

    public void translateReviewMessage(@NonNull final ShopHomeReviewViewModel shopHomeReviewViewModel) {
        String d = t.d();
        EtsyApiV3Request.a a = EtsyApiV3Request.a.a(TranslatedReview.class, e.a(shopHomeReviewViewModel.getReview().getTransactionId(), this.mShopId));
        a.a("language", d);
        getRequestQueue().a((Object) this, ((com.etsy.android.lib.core.http.request.d.a) com.etsy.android.lib.core.http.request.d.a.a((EtsyApiV3Request) a.d()).a((C0065a<ResultType>) new b<TranslatedReview>() {
            public void a(@NonNull List<TranslatedReview> list, int i, @NonNull com.etsy.android.lib.core.a.a<TranslatedReview> aVar) {
                if (list.size() > 0 && SectionedShopHomeFragment.this.getActivity() != null && SectionedShopHomeFragment.this.mAdapter != null) {
                    shopHomeReviewViewModel.setTranslatedReviewMessage(((TranslatedReview) list.get(0)).getTranslatedReview());
                    ((ShopHomeAdapter) SectionedShopHomeFragment.this.mAdapter).notifyShopHomeItemChanged(R.id.view_type_shop_home_review_message, shopHomeReviewViewModel);
                }
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<TranslatedReview> aVar) {
                shopHomeReviewViewModel.getTranslationState().setErrorLoadingTranslation();
                ((ShopHomeAdapter) SectionedShopHomeFragment.this.mAdapter).notifyShopHomeItemChanged(R.id.view_type_shop_home_review_message, shopHomeReviewViewModel);
            }
        }, (Fragment) this)).c());
    }

    public void translatePrivacyOther(@NonNull final ShopHomeStructuredPoliciesSectionViewModel shopHomeStructuredPoliciesSectionViewModel) {
        String d = t.d();
        EtsyApiV3Request.a a = EtsyApiV3Request.a.a(TranslatedPrivacyPolicy.class, e.c(this.mShopId));
        a.a("language", d);
        getRequestQueue().a((Object) this, ((com.etsy.android.lib.core.http.request.d.a) com.etsy.android.lib.core.http.request.d.a.a((EtsyApiV3Request) a.d()).a((C0065a<ResultType>) new b<TranslatedPrivacyPolicy>() {
            public void a(@NonNull List<TranslatedPrivacyPolicy> list, int i, @NonNull com.etsy.android.lib.core.a.a<TranslatedPrivacyPolicy> aVar) {
                if (list.size() > 0 && SectionedShopHomeFragment.this.getActivity() != null && SectionedShopHomeFragment.this.mAdapter != null) {
                    shopHomeStructuredPoliciesSectionViewModel.setTranslatedOtherPolicyText(((TranslatedPrivacyPolicy) list.get(0)).getTranslatedOtherPolicy());
                    ((ShopHomeAdapter) SectionedShopHomeFragment.this.mAdapter).notifyShopHomeItemChanged(R.id.view_type_shop_home_structured_policies_privacy, shopHomeStructuredPoliciesSectionViewModel);
                }
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<TranslatedPrivacyPolicy> aVar) {
                shopHomeStructuredPoliciesSectionViewModel.getOtherTranslationState().setErrorLoadingTranslation();
                ((ShopHomeAdapter) SectionedShopHomeFragment.this.mAdapter).notifyShopHomeItemChanged(R.id.view_type_shop_home_structured_policies_privacy, shopHomeStructuredPoliciesSectionViewModel);
            }
        }, (Fragment) this)).c());
    }

    public void translateFAQs(@NonNull final FAQs fAQs, final com.etsy.android.lib.shophome.model.a aVar) {
        final MachineTranslationViewState d = aVar.d();
        if (d.hasLoadedTranslation()) {
            d.toggleShowingOriginal();
            Iterator it = fAQs.iterator();
            while (it.hasNext()) {
                FAQ faq = (FAQ) it.next();
                faq.setShowTranslatedFAQ(!d.isShowingOriginal());
                ((ShopHomeAdapter) this.mAdapter).notifyShopHomeItemChanged(R.id.view_type_shop_home_faq_content, faq);
            }
            ((ShopHomeAdapter) this.mAdapter).notifyShopHomeItemChanged(R.id.view_type_shop_home_faq_subsection_heading, aVar);
            return;
        }
        String d2 = t.d();
        EtsyApiV3Request.a a = EtsyApiV3Request.a.a(FAQ.class, e.d(this.mShopId));
        a.a("language", d2);
        getRequestQueue().a((Object) this, ((com.etsy.android.lib.core.http.request.d.a) com.etsy.android.lib.core.http.request.d.a.a((EtsyApiV3Request) a.d()).a((C0065a<ResultType>) new b<FAQ>() {
            public void a(@NonNull List<FAQ> list, int i, @NonNull com.etsy.android.lib.core.a.a<FAQ> aVar) {
                if (list.size() > 0 && SectionedShopHomeFragment.this.getActivity() != null && SectionedShopHomeFragment.this.mAdapter != null) {
                    d.setSuccessLoadingTranslation();
                    ((ShopHomeAdapter) SectionedShopHomeFragment.this.mAdapter).notifyShopHomeItemChanged(R.id.view_type_shop_home_faq_subsection_heading, aVar);
                    fAQs.updateTranslatedFAQs(list);
                    Iterator it = fAQs.iterator();
                    while (it.hasNext()) {
                        ((ShopHomeAdapter) SectionedShopHomeFragment.this.mAdapter).notifyShopHomeItemChanged(R.id.view_type_shop_home_faq_content, (FAQ) it.next());
                    }
                }
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<FAQ> aVar) {
                aVar.d().setErrorLoadingTranslation();
                ((ShopHomeAdapter) SectionedShopHomeFragment.this.mAdapter).notifyShopHomeItemChanged(R.id.view_type_shop_home_structured_policies_privacy, aVar);
            }
        }, (Fragment) this)).c());
    }

    public void refreshFilterSpinners() {
        ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.mAdapter;
        if (shopHomeAdapter != null) {
            shopHomeAdapter.refreshFilterSpinners();
        }
    }

    public void refreshSearchBox() {
        ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.mAdapter;
        if (shopHomeAdapter != null) {
            shopHomeAdapter.refreshSearchBox();
        }
    }

    public void didClearSearch() {
        ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.mAdapter;
        if (shopHomeAdapter != null) {
            shopHomeAdapter.refreshSearchBox();
            shopHomeAdapter.hideSearchDescription();
        }
    }

    /* access modifiers changed from: protected */
    public ShopHomeAdapter getShopHomeFactoryAdapter() {
        return (ShopHomeAdapter) this.mAdapter;
    }

    public String getApiUrl() {
        String str;
        Object[] objArr;
        if (this.mShopId != null) {
            str = "/etsyapps/v3/bespoke/member/shops/%s/home";
            objArr = new Object[]{this.mShopId.toString()};
        } else {
            str = "/etsyapps/v3/bespoke/member/shops/%s/home-by-name";
            objArr = new Object[]{this.mShopName};
        }
        return String.format(str, objArr);
    }

    public void onLoadContent() {
        if (getActivity() instanceof ShopHomeActivity) {
            ShopHomePage shopHomePage = ((ShopHomeActivity) getActivity()).getShopHomePage();
            if (shopHomePage != null) {
                onPageLoaded(shopHomePage);
                return;
            }
        }
        showErrorView();
    }

    /* access modifiers changed from: protected */
    public void onPageLoaded(@NonNull ShopHomePage shopHomePage) {
        getAnalyticsContext().a("shop_home_complementary", shopHomePage.getTrackingParameters());
        ShopV3 shop = shopHomePage.getShop();
        this.mShopId = shop.getShopId();
        this.mShopName = shop.getShopName();
        handlePageData(shopHomePage, ShopHomeStateManager.newInstance(shopHomePage.getShopSections(), shop, this, getResources()));
        getActivity().invalidateOptionsMenu();
        showListView();
        stopLoad();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        MenuItem findItem = menu.findItem(R.id.menu_share);
        if (findItem != null) {
            ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.mAdapter;
            if (shopHomeAdapter == null || shopHomeAdapter.getPageData() == null) {
                findItem.setVisible(false);
                return;
            }
            findItem.setVisible(true);
        }
    }

    /* access modifiers changed from: protected */
    public void populateAdapter(@NonNull ShopHomePage shopHomePage, @NonNull ShopHomeStateManager shopHomeStateManager) {
        ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.mAdapter;
        if (shopHomeAdapter.getItemCount() == 0) {
            shopHomeAdapter.configureDataForSection(getSectionId(), shopHomePage, shopHomeStateManager, createShopHomeRouter(shopHomePage));
        }
    }

    /* access modifiers changed from: protected */
    public com.etsy.android.lib.shophome.b createShopHomeRouter(ShopHomePage shopHomePage) {
        return new com.etsy.android.ui.shop.b((TrackingBaseActivity) getActivity(), (com.etsy.android.vespa.d) this.mAdapter, shopHomePage.getShop().getUserId()) {
            public void a(@NonNull Tab tab, boolean z) {
            }

            public void a(String str, boolean z) {
            }

            public void d() {
            }
        };
    }

    public int getSectionId() {
        return getArguments().getInt("section_id");
    }

    @Nullable
    public EtsyId getShopId() {
        return this.mShopId;
    }

    @NonNull
    public com.etsy.android.vespa.b.a getPagination() {
        return new com.etsy.android.vespa.b.d();
    }
}
