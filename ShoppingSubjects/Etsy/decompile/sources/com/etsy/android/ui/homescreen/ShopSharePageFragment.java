package com.etsy.android.ui.homescreen;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.core.f.b;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.m;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.s;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.cardviewelement.ListSection;
import com.etsy.android.lib.models.cardviewelement.Page;
import com.etsy.android.lib.models.homescreen.LandingPageInfo;
import com.etsy.android.lib.models.homescreen.MessageCard;
import com.etsy.android.lib.models.shopshare.ShopShareActivityFeed;
import com.etsy.android.lib.models.shopshare.ShopShareCard;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter;
import com.etsy.android.vespa.b.a;
import com.etsy.android.vespa.b.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.parceler.d;

public class ShopSharePageFragment extends CardRecyclerViewBaseFragment {
    private static final int LIMIT = 12;
    /* access modifiers changed from: private */
    public static final String TAG = f.a(ShopSharePageFragment.class);
    private static final int TRIGGER_POSITION = 6;
    LandingPageInfo mPageLink;
    protected c mPagination = new c();

    public int getLoadTriggerPosition() {
        return 6;
    }

    @NonNull
    public String getTrackingName() {
        return "shop_shares_feed";
    }

    public a getPagination() {
        return this.mPagination;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        RecyclerView recyclerView = this.mRecyclerView;
        recyclerView.setPadding(recyclerView.getPaddingLeft(), recyclerView.getResources().getDimensionPixelOffset(R.dimen.fixed_medium_large), recyclerView.getPaddingRight(), recyclerView.getPaddingBottom());
        this.mPageLink = (LandingPageInfo) d.a(getArguments().getParcelable(ResponseConstants.PAGE_LINK));
        if (this.mPageLink != null) {
            getActivity().setTitle(this.mPageLink.getPageTitle());
            if (bundle != null) {
                getAdapter().onRestoreInstanceState(bundle);
            }
            if (this.mAdapter.getItemCount() == 0) {
                resetAndLoadContent();
            }
            if (shouldShowOnboarding()) {
                showOnboarding();
            }
        }
        return onCreateView;
    }

    @NonNull
    public String getApiUrl() {
        return this.mPageLink.getAPIPath();
    }

    /* access modifiers changed from: protected */
    public void onLoadContent() {
        final HashMap hashMap = new HashMap();
        if (!isRefreshing()) {
            String offsetActivityId = getOffsetActivityId();
            if (!offsetActivityId.isEmpty()) {
                hashMap.put("offset_activity_id", offsetActivityId);
            }
        }
        hashMap.put("story_limit", Integer.toString(12));
        getRequestQueue().a((Object) this, (g<Result>) m.a(ShopShareActivityFeed.class, getApiUrl()).a(true).a((Map<String, String>) hashMap).a((com.etsy.android.lib.core.f.c<Result>) new com.etsy.android.lib.core.f.c<ShopShareActivityFeed>() {
            public void a(List<ShopShareActivityFeed> list, int i, k<ShopShareActivityFeed> kVar) {
                f.c(ShopSharePageFragment.TAG, "Success");
                if (((BaseActivity) ShopSharePageFragment.this.getActivity()) != null) {
                    if (!hashMap.containsKey("offset_activity_id")) {
                        s.a("feed.displayed");
                    }
                    ShopSharePageFragment.this.showListView();
                    BaseViewHolderFactoryRecyclerViewAdapter access$100 = ShopSharePageFragment.this.getAdapter();
                    if (ShopSharePageFragment.this.mSwipeRefreshLayout.isRefreshing()) {
                        access$100.clear();
                    }
                    ShopSharePageFragment.this.mSwipeRefreshLayout.setRefreshing(false);
                    access$100.getItemCount();
                    ShopShareActivityFeed shopShareActivityFeed = (ShopShareActivityFeed) list.get(0);
                    List shopShareCards = shopShareActivityFeed.getShopShareCards();
                    Page page = new Page();
                    ListSection listSection = new ListSection();
                    listSection.getItems().addAll(shopShareCards);
                    page.addListSection(listSection);
                    if (!shopShareActivityFeed.hasMoreActivity()) {
                        MessageCard messageCard = new MessageCard();
                        Resources resources = ShopSharePageFragment.this.getResources();
                        messageCard.setTitle(resources.getString(R.string.shop_share_feed_end_title));
                        messageCard.setSubtitle(resources.getString(R.string.shop_share_feed_end_description));
                        messageCard.setDeepLinkUrl("etsy://home/bestofetsy");
                        messageCard.setLinkTitle(resources.getString(R.string.shop_share_feed_end_link_title));
                        ListSection listSection2 = new ListSection();
                        listSection2.getItems().add(messageCard);
                        page.addListSection(listSection2);
                    }
                    ShopSharePageFragment.this.onLoadComplete(page);
                    ShopSharePageFragment.this.getPagination().onSuccess(kVar, ShopSharePageFragment.this.getAdapter().getItemCount());
                    ShopSharePageFragment.this.getPagination().setContentExhausted(!shopShareActivityFeed.hasMoreActivity());
                }
            }
        }).a((com.etsy.android.lib.core.f.a) new com.etsy.android.lib.core.f.a() {
            public void a(k kVar) {
                ShopSharePageFragment.this.showEmptyView();
            }
        }).a((b) new b() {
            public void a(int i, String str, k kVar) {
                s.a("feed.not_displayed");
                ShopSharePageFragment.this.showErrorView();
            }
        }).a());
    }

    public void onScrolledToLoadTrigger() {
        s.a("feed.paginated");
        loadContent();
    }

    /* access modifiers changed from: protected */
    public void onLoadSuccessWithOffsetPagination(int i, int i2) {
        setLoading(false);
        if (isRefreshing()) {
            this.mSwipeRefreshLayout.setRefreshing(false);
            setRefreshing(false);
        }
        if (isEmpty()) {
            showEmptyView();
        } else {
            showListView();
        }
    }

    private String getOffsetActivityId() {
        ShopShareCard shopShareCard = new ShopShareCard();
        ArrayList arrayList = new ArrayList(getAdapter().getItems());
        Collections.reverse(arrayList);
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            try {
                shopShareCard = (ShopShareCard) ((com.etsy.android.vespa.k) it.next());
                break;
            } catch (ClassCastException e) {
                f.b(TAG, (Throwable) e);
            }
        }
        return shopShareCard.getActivityId().getId();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getAdapter().onSaveInstanceState(bundle);
    }

    private boolean shouldShowOnboarding() {
        return this.mPageLink.getBooleanOption("show_onboarding") && SharedPreferencesUtility.a((Context) getActivity(), "shop-share-mobile-onboarding") && getFragmentManager().findFragmentByTag("shopShareOnboarding") == null;
    }

    private void showOnboarding() {
        e.a(getActivity()).d().e();
    }
}
