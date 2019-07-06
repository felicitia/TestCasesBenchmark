package com.etsy.android.ui.finds;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.core.f.b;
import com.etsy.android.lib.core.f.c;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.m;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsPage;
import com.etsy.android.ui.nav.e;
import com.etsy.android.vespa.VespaBaseFragment;
import com.etsy.android.vespa.b.a;
import com.etsy.android.vespa.b.d;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindsFragment extends VespaBaseFragment {
    /* access modifiers changed from: private */
    public static final String TAG = f.a(FindsFragment.class);
    private final String KEY_PAGE_ID = "page_id";
    private final String KEY_PUBLISHED_PAGE_ID = "published_page_id";
    private final String KEY_SHARE_IMAGE_URL = "share_image_url";
    private final String KEY_SHARE_TITLE = "share_title";
    private final String KEY_SHARE_URL = "share_url";
    private String mAnchorListing;
    private Map<String, Boolean> mEventsTracked = new HashMap();
    /* access modifiers changed from: private */
    public EtsyId mFindsPageId = new EtsyId();
    /* access modifiers changed from: private */
    public EtsyId mFindsPagePublishedId = new EtsyId();
    private boolean mIsDraft = false;
    a mPagination = new d();
    /* access modifiers changed from: private */
    public String mShareImageUrl = "";
    /* access modifiers changed from: private */
    public String mShareTitle = "";
    /* access modifiers changed from: private */
    public String mShareUrl = "";
    /* access modifiers changed from: private */
    public String mSlug = "";

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSlug = getArguments().getString("finds_slug");
        this.mAnchorListing = getArguments().getString("ANCHOR_LISTING_ID");
        this.mIsDraft = getArguments().getBoolean("finds_is_draft");
        setHasOptionsMenu(true);
    }

    /* access modifiers changed from: protected */
    public void initAdapter() {
        if (this.mAdapter == null) {
            this.mAdapter = new FindsRecyclerViewAdapter(getActivity(), getTrackingName(), getAnalyticsContext());
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("published_page_id", this.mFindsPagePublishedId.getId());
        bundle.putString("page_id", this.mFindsPageId.getId());
        bundle.putString("share_url", this.mShareUrl);
        bundle.putString("share_title", this.mShareTitle);
        bundle.putString("share_image_url", this.mShareImageUrl);
    }

    public void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null) {
            this.mFindsPagePublishedId.setId(bundle.getString("published_page_id", this.mFindsPagePublishedId.getId()));
            this.mFindsPageId.setId(bundle.getString("page_id", this.mFindsPageId.getId()));
            this.mShareUrl = bundle.getString("share_url");
            this.mShareTitle = bundle.getString("share_title");
            this.mShareImageUrl = bundle.getString("share_image_url");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        getActivity().setTitle(getResources().getString(R.string.editors_picks));
        int applyDimension = (int) TypedValue.applyDimension(1, layoutInflater.getContext().getResources().getDimension(R.dimen.finds_margin), layoutInflater.getContext().getResources().getDisplayMetrics());
        int i = applyDimension / 2;
        this.mRecyclerView.setPadding(i, 0, i, applyDimension);
        this.mRecyclerView.addOnScrollListener(new OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                FindsFragment.this.trackScrollToBottom();
            }
        });
        if (((FindsRecyclerViewAdapter) this.mAdapter).getItemCount() == 0) {
            resetAndLoadContent();
        }
        return onCreateView;
    }

    public a getPagination() {
        return this.mPagination;
    }

    public void onRefresh() {
        this.mEventsTracked.remove("finds_page");
        super.onRefresh();
    }

    @NonNull
    public String getApiUrl() {
        try {
            return "/etsyapps/v3/bespoke/member/finds-page/${slug}$/modules".replace("${slug}$", URLEncoder.encode(this.mSlug, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("UnsupportedEncodingException encoding string \"");
            sb.append(this.mSlug);
            sb.append("\"");
            f.e(str, sb.toString(), e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onLoadContent() {
        getActivity().supportInvalidateOptionsMenu();
        if (this.mSlug.isEmpty()) {
            f.c(TAG, "No slug has been set for this editors picks page.");
            return;
        }
        m a = m.a(FindsPage.class, getContentUrl()).a((c<Result>) new c<FindsPage>() {
            public void a(List<FindsPage> list, int i, k<FindsPage> kVar) {
                FindsFragment.this.showListView();
                FindsRecyclerViewAdapter findsRecyclerViewAdapter = (FindsRecyclerViewAdapter) FindsFragment.this.mAdapter;
                if (FindsFragment.this.mSwipeRefreshLayout.isRefreshing()) {
                    findsRecyclerViewAdapter.clear();
                }
                FindsPage findsPage = (FindsPage) kVar.g().get(0);
                FindsFragment.this.mFindsPageId = findsPage.getFindsPageId();
                FindsFragment.this.mFindsPagePublishedId = findsPage.getFindsPagePublishedId();
                FindsFragment.this.mShareUrl = findsPage.getUrl();
                FindsFragment.this.mShareTitle = findsPage.getTitle();
                if (findsPage.getHeroListings().size() > 0) {
                    FindsFragment.this.mShareImageUrl = ((ListingCard) findsPage.getHeroListings().get(0)).getListingImage().getImageUrl();
                }
                FindsFragment.this.trackPageLoad();
                findsRecyclerViewAdapter.setFindsPage(findsPage);
                FindsFragment.this.onLoadComplete(null);
                FindsFragment.this.getPagination().onSuccess(kVar, FindsFragment.this.getAdapter().getItemCount());
                FindsFragment.this.getActivity().supportInvalidateOptionsMenu();
            }
        }).a((b) new b() {
            public void a(int i, String str, k kVar) {
                FindsFragment.this.onLoadFailure();
                f.c(FindsFragment.TAG, str);
            }
        }).a((com.etsy.android.lib.core.f.a) new com.etsy.android.lib.core.f.a() {
            public void a(k kVar) {
                FindsFragment.this.onLoadComplete(null);
                FindsFragment.this.mEmptyMessageView.setTitle(FindsFragment.this.getEmptyMessage());
                f.c(FindsFragment.TAG, "Empty");
            }
        });
        if (this.mIsDraft) {
            a.a("view_draft_content", Boolean.toString(true));
        }
        if (!TextUtils.isEmpty(this.mAnchorListing)) {
            a.a(ResponseConstants.ANCHOR_LISTING_ID, this.mAnchorListing);
        }
        String a2 = getRequestQueue().a((Object) this, (g<Result>) a.a());
        if (a2 != null) {
            this.mRequestCachedKeysUrls.add(a2);
        }
    }

    /* access modifiers changed from: private */
    public void trackPageLoad() {
        if (!this.mEventsTracked.containsKey("finds_page")) {
            getAnalyticsContext().a("finds_page", new HashMap<AnalyticsLogAttribute, Object>() {
                {
                    put(AnalyticsLogAttribute.FINDS_PAGE_ID, FindsFragment.this.mFindsPageId);
                    put(AnalyticsLogAttribute.FINDS_PAGE_PUBLISHED_ID, FindsFragment.this.mFindsPagePublishedId);
                    put(AnalyticsLogAttribute.FINDS_PAGE_SLUG, FindsFragment.this.mSlug);
                }
            });
            this.mEventsTracked.put("finds_page", Boolean.valueOf(true));
        }
    }

    /* access modifiers changed from: private */
    public void trackScrollToBottom() {
        if (this.mRecyclerView != null && !this.mEventsTracked.containsKey("scroll_to_bottom")) {
            if (((GridLayoutManager) this.mRecyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() == this.mRecyclerView.getAdapter().getItemCount() - 1) {
                this.mEventsTracked.put("scroll_to_bottom", Boolean.valueOf(true));
                getAnalyticsContext().a("scroll_to_bottom", new HashMap<AnalyticsLogAttribute, Object>() {
                    {
                        put(AnalyticsLogAttribute.FINDS_PAGE_ID, FindsFragment.this.mFindsPageId);
                        put(AnalyticsLogAttribute.FINDS_PAGE_PUBLISHED_ID, FindsFragment.this.mFindsPagePublishedId);
                        put(AnalyticsLogAttribute.FINDS_PAGE_SLUG, FindsFragment.this.mSlug);
                    }
                });
            }
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem findItem = menu.findItem(R.id.menu_share);
        if (findItem == null) {
            return;
        }
        if (!TextUtils.isEmpty(this.mShareUrl)) {
            findItem.setVisible(true);
        } else {
            findItem.setVisible(false);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_share) {
            return super.onOptionsItemSelected(menuItem);
        }
        share();
        return true;
    }

    private void share() {
        HashMap hashMap = new HashMap();
        hashMap.put(AnalyticsLogAttribute.URL, this.mShareUrl);
        getAnalyticsContext().a("share_editors_picks", hashMap);
        com.etsy.android.ui.nav.b a = e.a(getActivity()).a();
        String string = getString(R.string.share_editors_picks_subject);
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.share_editors_picks_message, this.mShareTitle));
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(this.mShareUrl);
        a.a(string, sb.toString(), this.mShareUrl, this.mShareImageUrl);
    }
}
