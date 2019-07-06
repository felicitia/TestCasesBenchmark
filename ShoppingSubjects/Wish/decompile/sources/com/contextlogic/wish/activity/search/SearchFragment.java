package com.contextlogic.wish.activity.search;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.LoadingUiFragment;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.activity.feed.search.SearchFeedActivity;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.activity.recentwishlistproducts.RecentWishlistProductsActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.listview.HorizontalListView.OnItemClickListener;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.ValueUtil;
import com.crashlytics.android.Crashlytics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SearchFragment extends LoadingUiFragment<SearchActivity> implements SearchBarCallback {
    /* access modifiers changed from: private */
    public SearchAutocompleteAdapter mAutocompleteAdapter;
    /* access modifiers changed from: private */
    public ImageHttpPrefetcher mImagePrefetcher;
    /* access modifiers changed from: private */
    public SearchRecentlyViewedAdapter mRecentlyViewedAdapter;
    /* access modifiers changed from: private */
    public HorizontalListView mRecentlyViewedList;
    /* access modifiers changed from: private */
    public ArrayList<WishProduct> mRecentlyViewedProducts;
    private View mRecentlyViewedSection;
    private View mRecentlyViewedViewAll;
    private ArrayList<String> mSearchHistoryItems;
    private LinearLayout mSearchHistoryMainContainer;
    /* access modifiers changed from: private */
    public LinearLayout mSearchHistorySecondaryContainer;
    private View mSearchHistorySection;
    /* access modifiers changed from: private */
    public View mSearchHistoryViewAll;
    /* access modifiers changed from: private */
    public ArrayList<WishProduct> mWishlistProducts;
    /* access modifiers changed from: private */
    public SearchRecentlyViewedAdapter mWishlistProductsAdapter;
    /* access modifiers changed from: private */
    public HorizontalListView mWishlistProductsList;
    private View mWishlistProductsSection;
    private View mWishlistProductsViewAll;

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.search_fragment;
    }

    public void onQueryChanged(String str) {
    }

    public void restoreImages() {
        if (this.mRecentlyViewedList != null) {
            this.mRecentlyViewedList.restoreImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.resumePrefetching();
        }
        if (this.mWishlistProductsList != null) {
            this.mWishlistProductsList.restoreImages();
        }
    }

    public void releaseImages() {
        if (this.mRecentlyViewedList != null) {
            this.mRecentlyViewedList.releaseImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.pausePrefetching();
        }
        if (this.mWishlistProductsList != null) {
            this.mWishlistProductsList.releaseImages();
        }
    }

    public void initializeLoadingContentView(View view) {
        this.mSearchHistoryItems = new ArrayList<>();
        this.mRecentlyViewedProducts = new ArrayList<>();
        this.mWishlistProducts = new ArrayList<>();
        getLoadingPageView().setHideEmptyState(true);
        getLoadingPageView().setHideErrors(true);
        withActivity(new ActivityTask<SearchActivity>() {
            public void performTask(SearchActivity searchActivity) {
                SearchFragment.this.mAutocompleteAdapter = new SearchAutocompleteAdapter(searchActivity, SearchFragment.this);
                searchActivity.getActionBarManager().setSearchBarCallback(SearchFragment.this);
                searchActivity.getActionBarManager().startSearch(false, searchActivity.getQuery());
            }
        });
        this.mSearchHistorySection = view.findViewById(R.id.fragment_search_history_section);
        this.mSearchHistoryViewAll = view.findViewById(R.id.fragment_search_history_view_all);
        this.mSearchHistoryViewAll.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SearchFragment.this.mSearchHistorySecondaryContainer.setVisibility(0);
                SearchFragment.this.mSearchHistoryViewAll.setVisibility(8);
            }
        });
        this.mSearchHistoryMainContainer = (LinearLayout) view.findViewById(R.id.fragment_search_history_main_container);
        this.mSearchHistorySecondaryContainer = (LinearLayout) view.findViewById(R.id.fragment_search_history_secondary_container);
        this.mRecentlyViewedSection = view.findViewById(R.id.fragment_search_recently_viewed_section);
        this.mRecentlyViewedViewAll = view.findViewById(R.id.fragment_search_recently_viewed_view_all);
        this.mRecentlyViewedViewAll.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SearchFragment.this.showAllRecentlyViewed();
            }
        });
        this.mRecentlyViewedList = (HorizontalListView) view.findViewById(R.id.fragment_search_recently_viewed_list);
        this.mRecentlyViewedList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(int i, View view) {
                if (i >= 0 && i < SearchFragment.this.mRecentlyViewedProducts.size()) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_STANDALONE_SEARCH_RECENTLY_VIEWED);
                    SearchFragment.this.showProduct((WishProduct) SearchFragment.this.mRecentlyViewedProducts.get(i));
                }
            }
        });
        this.mWishlistProductsSection = view.findViewById(R.id.fragment_search_wishlist_products_section);
        this.mWishlistProductsViewAll = view.findViewById(R.id.fragment_search_wishlist_products_view_all);
        this.mWishlistProductsViewAll.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SEARCH_RECENT_WISHLIST_ITEMS_VIEW_ALL);
                SearchFragment.this.showAllRecentWishlistItems();
            }
        });
        this.mWishlistProductsList = (HorizontalListView) view.findViewById(R.id.fragment_search_wishlist_products_list);
        this.mWishlistProductsList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(int i, View view) {
                if (i >= 0 && i < SearchFragment.this.mWishlistProducts.size()) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SEARCH_RECENT_WISHLIST_ITEMS_ITEM);
                    SearchFragment.this.showProduct((WishProduct) SearchFragment.this.mWishlistProducts.get(i));
                }
            }
        });
        this.mWishlistProductsSection.setVisibility(ExperimentDataCenter.getInstance().shouldSeeRecentWishlist() ? 0 : 8);
        withActivity(new ActivityTask<SearchActivity>() {
            public void performTask(SearchActivity searchActivity) {
                SearchFragment.this.mImagePrefetcher = new ImageHttpPrefetcher();
                SearchFragment.this.mRecentlyViewedAdapter = new SearchRecentlyViewedAdapter(searchActivity, SearchFragment.this, SearchFragment.this.mRecentlyViewedProducts);
                SearchFragment.this.mRecentlyViewedAdapter.setImagePrefetcher(SearchFragment.this.mImagePrefetcher);
                SearchFragment.this.mRecentlyViewedList.setAdapter(SearchFragment.this.mRecentlyViewedAdapter);
                if (ExperimentDataCenter.getInstance().shouldSeeRecentWishlist()) {
                    SearchFragment.this.mWishlistProductsAdapter = new SearchRecentlyViewedAdapter(searchActivity, SearchFragment.this, SearchFragment.this.mWishlistProducts);
                    SearchFragment.this.mWishlistProductsAdapter.setImagePrefetcher(SearchFragment.this.mImagePrefetcher);
                    SearchFragment.this.mWishlistProductsList.setAdapter(SearchFragment.this.mWishlistProductsAdapter);
                }
            }
        });
        initializeValues();
    }

    private void initializeValues() {
        if (getSavedInstanceState() != null) {
            updateContent(getSavedInstanceState().getStringArrayList("SavedStateSearchHistory"), StateStoreCache.getInstance().getParcelableList(getSavedInstanceState(), "SavedStateRecentlyViewedProducts", WishProduct.class), StateStoreCache.getInstance().getParcelableList(getSavedInstanceState(), "SavedStateWishlistProducts", WishProduct.class));
        }
    }

    public void handleResume() {
        super.handleResume();
        if (!getLoadingPageView().isLoadingComplete()) {
            handleReload();
        }
        getHandler().post(new Runnable() {
            public void run() {
                SearchFragment.this.withActivity(new ActivityTask<SearchActivity>() {
                    public void performTask(SearchActivity searchActivity) {
                        searchActivity.getActionBarManager().clearSearchFocus();
                    }
                });
            }
        });
    }

    public void handleSaveInstanceState(Bundle bundle) {
        if (getLoadingPageView() != null && getLoadingPageView().isLoadingComplete()) {
            bundle.putStringArrayList("SavedStateSearchHistory", this.mSearchHistoryItems);
            bundle.putString("SavedStateRecentlyViewedProducts", StateStoreCache.getInstance().storeParcelableList(this.mRecentlyViewedProducts));
            if (ExperimentDataCenter.getInstance().shouldSeeRecentWishlist()) {
                bundle.putString("SavedStateWishlistProducts", StateStoreCache.getInstance().storeParcelableList(this.mWishlistProducts));
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.cancelPrefetching();
        }
    }

    public void handleReload() {
        updateContent(null, null, null);
        if (this.mRecentlyViewedList != null) {
            this.mRecentlyViewedList.notifyDataSetChanged(true);
            this.mRecentlyViewedList.scrollTo(0, 0);
        }
        if (this.mWishlistProductsList != null && ExperimentDataCenter.getInstance().shouldSeeRecentWishlist()) {
            this.mWishlistProductsList.notifyDataSetChanged(true);
            this.mWishlistProductsList.scrollTo(0, 0);
        }
        withServiceFragment(new ServiceTask<BaseActivity, SearchServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SearchServiceFragment searchServiceFragment) {
                searchServiceFragment.loadSearchContent();
            }
        });
    }

    public boolean hasItems() {
        return this.mSearchHistoryItems.size() > 0 || this.mRecentlyViewedProducts.size() > 0 || this.mWishlistProducts.size() > 0;
    }

    public void fetchAutocompleteResults(final String str) {
        withServiceFragment(new ServiceTask<BaseActivity, SearchServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SearchServiceFragment searchServiceFragment) {
                searchServiceFragment.fetchAutocompleteResults(str);
            }
        });
    }

    public void updateAutocompleteResults(ArrayList<String> arrayList) {
        if (this.mAutocompleteAdapter != null) {
            this.mAutocompleteAdapter.updateAutocompleteResults(arrayList);
        }
    }

    public void onSearchSubmit(String str) {
        if (str != null && !str.trim().equals("")) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SEARCH);
            showSearchResults(str);
        }
    }

    public CursorAdapter getSearchTypeaheadAdapter() {
        return this.mAutocompleteAdapter;
    }

    public void handleSearchTypeaheadClick(int i) {
        Cursor cursor = this.mAutocompleteAdapter.getCursor();
        if (cursor != null && cursor.moveToPosition(i)) {
            String string = cursor.getString(cursor.getColumnIndex("suggest_text_1"));
            ArrayList pendingQueryResults = this.mAutocompleteAdapter.getPendingQueryResults();
            HashMap hashMap = new HashMap();
            hashMap.put("query", this.mAutocompleteAdapter.getCurrentQuery());
            hashMap.put("suggestions", pendingQueryResults != null ? pendingQueryResults.toString() : null);
            hashMap.put("selected_suggestion", string);
            hashMap.put("suggestion_index", Integer.toString(i));
            if (this.mSearchHistoryItems.contains(string)) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_STANDALONE_SEARCH_HISTORY_ITEM);
            } else {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SEARCH_TEXT_AUTOCOMPLETE, hashMap);
            }
            showSearchResults(string);
        }
    }

    /* access modifiers changed from: private */
    public void showSearchResults(final String str) {
        withActivity(new ActivityTask<SearchActivity>() {
            public void performTask(SearchActivity searchActivity) {
                Intent intent = new Intent();
                intent.setClass(searchActivity, SearchFeedActivity.class);
                intent.putExtra(SearchFeedActivity.EXTRA_QUERY, str);
                searchActivity.startActivity(intent);
            }
        });
    }

    /* access modifiers changed from: private */
    public void showProduct(final WishProduct wishProduct) {
        withActivity(new ActivityTask<SearchActivity>() {
            public void performTask(SearchActivity searchActivity) {
                Intent intent = new Intent();
                intent.setClass(searchActivity, ProductDetailsActivity.class);
                ProductDetailsActivity.addInitialProduct(intent, wishProduct);
                searchActivity.startActivity(intent);
            }
        });
    }

    /* access modifiers changed from: private */
    public void showAllRecentlyViewed() {
        withActivity(new ActivityTask<SearchActivity>() {
            public void performTask(SearchActivity searchActivity) {
                Intent intent = new Intent();
                intent.setClass(searchActivity, BrowseActivity.class);
                intent.putExtra("ExtraCategoryId", "recently_viewed__tab");
                searchActivity.startActivity(intent);
            }
        });
    }

    /* access modifiers changed from: private */
    public void showAllRecentWishlistItems() {
        withActivity(new ActivityTask<SearchActivity>() {
            public void performTask(SearchActivity searchActivity) {
                Intent intent = new Intent();
                if (ExperimentDataCenter.getInstance().shouldSeeRecentWishlistAsTab()) {
                    intent.setClass(searchActivity, BrowseActivity.class);
                    intent.putExtra("ExtraCategoryId", "recent_wishlist__tab");
                } else if (ExperimentDataCenter.getInstance().shouldSeeRecentWishlistAsModal()) {
                    intent.setClass(searchActivity, RecentWishlistProductsActivity.class);
                    intent.putExtra("ExtraCategoryId", "recent_wishlist__tab");
                    intent.putExtra("ExtraWishlistItems", SearchFragment.this.mWishlistProducts);
                }
                searchActivity.startActivity(intent);
            }
        });
    }

    public void handleContentLoadingErrored() {
        getLoadingPageView().markLoadingErrored();
    }

    public void handleContentLoadingSuccess(ArrayList<String> arrayList, ArrayList<WishProduct> arrayList2, ArrayList<WishProduct> arrayList3) {
        updateContent(arrayList, arrayList2, arrayList3);
    }

    public void updateContent(ArrayList<String> arrayList, ArrayList<WishProduct> arrayList2, ArrayList<WishProduct> arrayList3) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        this.mSearchHistoryItems.clear();
        this.mRecentlyViewedProducts.clear();
        this.mSearchHistoryMainContainer.removeAllViews();
        this.mSearchHistorySecondaryContainer.removeAllViews();
        int i = 8;
        if (arrayList != null) {
            if (arrayList.size() > 0) {
                this.mSearchHistoryItems.addAll(arrayList);
                this.mSearchHistoryViewAll.setVisibility(8);
                this.mSearchHistorySecondaryContainer.setVisibility(8);
                if (ExperimentDataCenter.getInstance().shouldSeeSearchHistoryInAutocomplete()) {
                    this.mAutocompleteAdapter.setSearchHistory(this.mSearchHistoryItems);
                }
                for (int i2 = 0; i2 < this.mSearchHistoryItems.size(); i2++) {
                    if (i2 < 4) {
                        addSearchHistoryRow((String) this.mSearchHistoryItems.get(i2), this.mSearchHistoryMainContainer);
                    } else {
                        this.mSearchHistoryViewAll.setVisibility(0);
                        addSearchHistoryRow((String) this.mSearchHistoryItems.get(i2), this.mSearchHistorySecondaryContainer);
                    }
                }
                z = true;
            } else {
                z = false;
            }
            z2 = true;
        } else {
            z = false;
            z2 = false;
        }
        if (!z || ExperimentDataCenter.getInstance().shouldSeeSearchHistoryInAutocomplete()) {
            this.mSearchHistorySection.setVisibility(8);
        } else {
            this.mSearchHistorySection.setVisibility(0);
        }
        if (arrayList2 != null) {
            if (arrayList2.size() > 0) {
                this.mRecentlyViewedProducts.addAll(arrayList2);
                for (int i3 = 0; i3 < this.mRecentlyViewedProducts.size(); i3++) {
                    try {
                        this.mImagePrefetcher.queueImage(((WishProduct) this.mRecentlyViewedProducts.get(i3)).getImage());
                    } catch (NullPointerException unused) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("SearchFragment#updateContent(): mRecentlyViewProducts of size");
                        sb.append(this.mRecentlyViewedProducts.size());
                        sb.append(" contains null at index: ");
                        sb.append(i3);
                        Crashlytics.logException(new Throwable(sb.toString()));
                    }
                }
                z3 = true;
            } else {
                z3 = false;
            }
            z2 = true;
        } else {
            z3 = false;
        }
        this.mRecentlyViewedList.notifyDataSetChanged();
        this.mRecentlyViewedSection.setVisibility(z3 ? 0 : 8);
        if (ExperimentDataCenter.getInstance().shouldSeeRecentWishlist()) {
            if (arrayList3 != null) {
                if (arrayList3.size() > 0) {
                    this.mWishlistProducts.addAll(arrayList3);
                    Iterator it = this.mWishlistProducts.iterator();
                    while (it.hasNext()) {
                        this.mImagePrefetcher.queueImage(((WishProduct) it.next()).getImage());
                    }
                    z4 = true;
                } else {
                    z4 = false;
                }
                z2 = true;
            } else {
                z4 = false;
            }
            this.mWishlistProductsList.notifyDataSetChanged();
            View view = this.mWishlistProductsSection;
            if (z4) {
                i = 0;
            }
            view.setVisibility(i);
        }
        if (z2) {
            getLoadingPageView().markLoadingComplete();
        }
    }

    private void addSearchHistoryRow(final String str, final LinearLayout linearLayout) {
        withActivity(new ActivityTask<SearchActivity>() {
            public void performTask(SearchActivity searchActivity) {
                ThemedTextView themedTextView = new ThemedTextView(searchActivity);
                themedTextView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.text_primary));
                themedTextView.setText(str);
                themedTextView.setBackgroundResource(R.drawable.row_selector_default);
                themedTextView.setTextSize(0, (float) SearchFragment.this.getResources().getDimensionPixelSize(R.dimen.text_size_body));
                LayoutParams layoutParams = new LayoutParams(-1, (int) ValueUtil.convertDpToPx(40.0f));
                themedTextView.setGravity(19);
                themedTextView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_STANDALONE_SEARCH_HISTORY_ITEM);
                        SearchFragment.this.showSearchResults(str);
                    }
                });
                linearLayout.addView(themedTextView, layoutParams);
            }
        });
    }
}
