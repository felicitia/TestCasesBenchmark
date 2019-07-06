package com.etsy.android.ui.core;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.f.a;
import com.etsy.android.lib.core.f.c;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.ListingsRequest;
import com.etsy.android.lib.util.m;
import com.etsy.android.ui.EtsyLoadingListFragment;
import com.etsy.android.ui.adapters.MultiColumnListingAdapter;
import com.etsy.android.ui.adapters.b;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.HashMap;
import java.util.List;

public class CollectionFragment extends EtsyLoadingListFragment {
    private static final String TAG = "com.etsy.android.ui.core.CollectionFragment";
    /* access modifiers changed from: private */
    public MultiColumnListingAdapter mAdapter;
    /* access modifiers changed from: private */
    public Collection mCollection;
    private View mEditButton;
    private View mHeader;
    private boolean mIsPrivate = false;
    /* access modifiers changed from: private */
    public boolean mIsRefresh = false;
    /* access modifiers changed from: private */
    public int mMaxItemCount;
    /* access modifiers changed from: private */
    public int mOffset = 0;
    private a mOnEmptyHandler = new a() {
        public void a(k kVar) {
            CollectionFragment.this.showEmptyView();
        }
    };
    private c<Listing> mOnSuccessHandler = new c<Listing>() {
        public void a(List<Listing> list, int i, k<Listing> kVar) {
            if (kVar == null || !kVar.a() || kVar.g() == null) {
                CollectionFragment.this.handleError(kVar);
                return;
            }
            CollectionFragment.this.trackAllListings(kVar, i);
            if (CollectionFragment.this.mIsRefresh) {
                CollectionFragment.this.mAdapter.clear();
            }
            for (Listing listing : kVar.g()) {
                if (listing.isVisible()) {
                    CollectionFragment.this.mAdapter.add(listing);
                }
            }
            CollectionFragment.this.mAdapter.notifyDataSetChanged();
            CollectionFragment.this.mMaxItemCount = i;
            CollectionFragment.this.mOffset = CollectionFragment.this.mOffset + 24;
            CollectionFragment.this.showViews();
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        setPullToRefreshEnabled(true);
        this.mCollection = (Collection) getArguments().getSerializable(Collection.TYPE_COLLECTION);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mHeader = layoutInflater.inflate(R.layout.collection_header, null);
        this.mEditButton = this.mHeader.findViewById(R.id.edit_button);
        if (this.mEditButton != null) {
            if (isYou()) {
                this.mEditButton.setVisibility(0);
                this.mEditButton.setOnClickListener(new TrackingOnClickListener(this.mCollection) {
                    public void onViewClick(View view) {
                        e.a((Activity) CollectionFragment.this.mActivity).a(600, (Fragment) CollectionFragment.this).b(CollectionFragment.this.mCollection);
                    }
                });
            } else {
                this.mEditButton.setVisibility(8);
            }
        }
        this.mListView.addHeaderView(this.mHeader);
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setEmptyText((int) R.string.empty_favorites);
        if (this.mAdapter == null) {
            this.mAdapter = createAdapter();
            showLoadingView();
            onLoadMoreItems();
        } else if (m.a(this.mAdapter) == 0) {
            showEmptyView();
        } else if (this.mMaxItemCount > m.a(this.mAdapter)) {
            startEndless();
            showListView();
        } else {
            showListView();
        }
        this.mListView.setDivider(null);
        this.mListView.setPadding(0, 0, getResources().getDimensionPixelOffset(R.dimen.listview_extra_padding), 0);
        this.mAdapter.notifyDataSetChanged();
        setListAdapter(this.mAdapter);
    }

    public void onResume() {
        super.onResume();
        this.mActivity.supportInvalidateOptionsMenu();
        this.mActivity.setTitle(R.string.collection);
    }

    public void onFragmentResume() {
        super.onFragmentResume();
        this.mActivity.supportInvalidateOptionsMenu();
        this.mActivity.setTitle(R.string.collection);
    }

    private MultiColumnListingAdapter createAdapter() {
        MultiColumnListingAdapter multiColumnListingAdapter = new MultiColumnListingAdapter(this.mActivity, getImageBatch(), R.layout.list_item_listing_shopname_small, R.integer.listing_favorite_card_item_columns);
        multiColumnListingAdapter.addExtraPaddingToTopRow(true);
        multiColumnListingAdapter.setOnListingClickListener(new b.a() {
            public void a(Listing listing) {
                e.a((FragmentActivity) CollectionFragment.this.mActivity).a().a(listing.getListingId());
            }
        });
        return multiColumnListingAdapter;
    }

    private void setResult(int i, Collection collection) {
        Intent intent = this.mActivity.getIntent();
        intent.putExtra(Collection.TYPE_COLLECTION, collection);
        this.mActivity.setResult(i, intent);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 600 && intent != null) {
            Collection collection = (Collection) intent.getSerializableExtra(Collection.TYPE_COLLECTION);
            switch (i2) {
                case 611:
                    if (this.mCollection.equals(collection)) {
                        setResult(611, collection);
                        this.mActivity.finish();
                        return;
                    }
                    return;
                case 612:
                    if (this.mCollection.equals(collection)) {
                        setResult(612, collection);
                        this.mCollection.update(collection);
                        this.mActivity.supportInvalidateOptionsMenu();
                        fillHeader(this.mCollection);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mAdapter.notifyDataSetChanged();
    }

    public void onLoadMoreItems() {
        loadFavorites(false);
    }

    /* access modifiers changed from: protected */
    public void onPullToRefresh() {
        stopEndless();
        this.mOffset = 0;
        loadFavorites(true);
    }

    /* access modifiers changed from: private */
    public void trackAllListings(k<Listing> kVar, final int i) {
        final int size = kVar.g().size();
        w analyticsContext = getAnalyticsContext();
        if (analyticsContext != null && size > 0) {
            final StringBuilder sb = new StringBuilder();
            for (ListingLike listingId : kVar.g()) {
                sb.append(listingId.getListingId().getId());
                sb.append(",");
            }
            analyticsContext.a("load_more_listings", new HashMap<AnalyticsLogAttribute, Object>() {
                {
                    put(AnalyticsLogAttribute.LISTING_IDS, sb.toString());
                    put(AnalyticsLogAttribute.TOTAL_RESULTS, Integer.valueOf(i));
                    put(AnalyticsLogAttribute.LISTINGS_COUNT, Integer.valueOf(size));
                }
            });
        }
    }

    private void loadFavorites(boolean z) {
        this.mIsRefresh = z;
        getRequestQueue().a((Object) this, (g<Result>) com.etsy.android.lib.core.e.a((EtsyRequest<Result>) ListingsRequest.findAllCollectionListings(this.mCollection.getKey())).b(24).a(this.mOffset).b(ListingsRequest.LISTING_CARD_INCLUDES).a(ListingsRequest.LISTING_CARD_FIELDS).a(this.mOnSuccessHandler).a(com.etsy.android.ui.util.g.a(this)).a(com.etsy.android.ui.util.g.b(this)).a());
    }

    /* access modifiers changed from: protected */
    public void onRetryClicked() {
        showLoadingView();
        onLoadMoreItems();
    }

    private boolean isYou() {
        if (this.mCollection == null) {
            return false;
        }
        User creator = this.mCollection.getCreator();
        if (creator == null || !creator.getUserId().hasId()) {
            return false;
        }
        return v.a().m().equals(creator.getUserId());
    }

    /* access modifiers changed from: private */
    public void showViews() {
        stopPullToRefresh();
        if (m.a(this.mAdapter) == 0) {
            showEmptyView();
        } else if (this.mOffset >= this.mMaxItemCount) {
            showListView();
            stopEndless();
        } else {
            showListView();
            startEndless();
        }
    }

    /* access modifiers changed from: private */
    public void handleError(k kVar) {
        f.c(TAG, "handleError");
        stopPullToRefresh();
        if (m.a(this.mAdapter) > 0) {
            showEndlessError();
        } else if (kVar == null || kVar.q() != 403) {
            showErrorView();
        } else {
            showPrivateView();
        }
    }

    public void showEmptyView() {
        if (isYou()) {
            showListView();
            return;
        }
        if (this.mIsPrivate) {
            setEmptyText((int) R.string.private_favorites);
        }
        super.showEmptyView();
    }

    public void showListView() {
        super.showListView();
        fillHeader(this.mCollection);
    }

    private void showPrivateView() {
        this.mIsPrivate = true;
        setEmptyText((int) R.string.private_favorites);
        showEmptyView();
    }

    private void fillHeader(Collection collection) {
        ((TextView) this.mHeader.findViewById(R.id.collection_title)).setText(collection.getName());
        this.mHeader.findViewById(R.id.collection_privacy_icon).setVisibility(collection.isPrivate() ? 0 : 8);
        int listingsCount = collection.getListingsCount();
        ((TextView) this.mHeader.findViewById(R.id.collection_count)).setText(getResources().getQuantityString(R.plurals.item_lowercase_quantity, listingsCount, new Object[]{Integer.valueOf(listingsCount)}));
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem findItem = menu.findItem(R.id.menu_share);
        if (findItem == null) {
            return;
        }
        if (this.mCollection == null || !this.mCollection.isPublic() || this.mCollection.getListingsCount() <= 0) {
            findItem.setVisible(false);
        } else {
            findItem.setVisible(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_share) {
            return super.onOptionsItemSelected(menuItem);
        }
        shareCollection();
        return true;
    }

    private boolean shareCollection() {
        String str = "";
        if (this.mCollection.getRepresentativeListings().size() > 0) {
            str = ((Listing) this.mCollection.getRepresentativeListings().get(0)).getImage().getImageUrlForPixelWidth(((Integer) BaseModelImage.IMG_SIZE_340.first).intValue());
        }
        com.etsy.android.lib.logger.legacy.b.a().a("share_collection", "share", "collection_key", (Object) this.mCollection.getKey());
        String url = this.mCollection.getUrl();
        com.etsy.android.ui.nav.b a = e.a(getActivity()).a();
        String string = getString(R.string.share_collection_subject);
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.share_collection_message));
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(url);
        a.a(string, sb.toString(), url, str);
        return true;
    }
}
