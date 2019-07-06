package com.etsy.android.ui.user.profile;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.core.f.a;
import com.etsy.android.lib.core.f.b;
import com.etsy.android.lib.core.f.c;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.m;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.apiv3.UserProfilePage;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.adapters.UserProfileAdapter;
import com.etsy.android.uikit.BaseRecyclerViewListFragment;
import java.util.List;
import org.parceler.d;

public class UserProfileFragment extends BaseRecyclerViewListFragment<Pair<?, Integer>> {
    private static final String PROFILE_PAGE_DATA = "profile_page_data";
    private boolean mIsSignedIn;
    private EtsyId mUserId;

    public int getLayoutId() {
        return R.layout.fragment_recyclerview_list;
    }

    /* access modifiers changed from: 0000 */
    public boolean isYou() {
        return SharedPreferencesUtility.a((Context) getActivity(), (EtsyId) getArguments().getSerializable("user_id"));
    }

    @NonNull
    public String getTrackingName() {
        return isYou() ? "your_account" : "people_account";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAdapter = new UserProfileAdapter(getActivity(), getImageBatch(), getAnalyticsContext(), isYou());
        setHasOptionsMenu(true);
    }

    @NonNull
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActivity().setTitle(R.string.user_profile);
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mUserId = (EtsyId) getArguments().getSerializable("user_id");
        UserProfileAdapter userProfileAdapter = (UserProfileAdapter) this.mAdapter;
        GridLayoutManager layoutManager = userProfileAdapter.getLayoutManager(getActivity());
        layoutManager.setSpanSizeLookup(userProfileAdapter.spanSizeLookup());
        this.mSwipeRefreshLayout.setColorSchemeResources(R.color.sk_orange_30);
        this.mRecyclerView.setLayoutManager(layoutManager);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setAdapter(this.mAdapter);
        ItemAnimator itemAnimator = this.mRecyclerView.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
        this.mRecyclerView.addItemDecoration(userProfileAdapter.getMarginDividerDecoration());
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mIsSignedIn = v.a().e();
        if (bundle == null || !bundle.containsKey(PROFILE_PAGE_DATA)) {
            loadContent();
            return;
        }
        ((UserProfileAdapter) this.mAdapter).setData((UserProfilePage) d.a(bundle.getParcelable(PROFILE_PAGE_DATA)));
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        UserProfilePage data = ((UserProfileAdapter) this.mAdapter).getData();
        if (data != null) {
            bundle.putParcelable(PROFILE_PAGE_DATA, d.a(data));
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem findItem = menu.findItem(R.id.menu_share);
        if (findItem != null) {
            findItem.setVisible(false);
        }
    }

    public void onLoadContent() {
        fetchPageInfo();
    }

    public void onPause() {
        super.onPause();
        configureListingStateReceiver(true);
    }

    public void onResume() {
        super.onResume();
        if (this.mIsSignedIn != v.a().e()) {
            this.mIsSignedIn = !this.mIsSignedIn;
            this.mAdapter.clear();
            loadContent();
        }
        configureListingStateReceiver(false);
    }

    public void onDestroy() {
        super.onDestroy();
        configureListingStateReceiver(false);
    }

    private void configureListingStateReceiver(boolean z) {
        if (this.mAdapter != null) {
            UserProfileAdapter userProfileAdapter = (UserProfileAdapter) this.mAdapter;
            if (z) {
                LocalBroadcastManager.getInstance(getActivity()).registerReceiver(userProfileAdapter.getListingStateChangedReceiver(), new IntentFilter(EtsyAction.STATE_CHANGE.getAction()));
            } else {
                LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(userProfileAdapter.getListingStateChangedReceiver());
            }
        }
    }

    private void fetchPageInfo() {
        getRequestQueue().a((Object) this, (g<Result>) m.a(UserProfilePage.class, String.format(this.mIsSignedIn ? "/etsyapps/v3/bespoke/member/users/%s/profile-page" : "/etsyapps/v3/bespoke/public/users/%s/profile-page", new Object[]{this.mUserId})).a("is_tablet", Boolean.toString(l.c((Activity) getActivity()))).a((c<Result>) new c<UserProfilePage>() {
            public void a(List<UserProfilePage> list, int i, k<UserProfilePage> kVar) {
                UserProfilePage userProfilePage = (UserProfilePage) list.get(0);
                UserProfileFragment.this.mAdapter.clear();
                ((UserProfileAdapter) UserProfileFragment.this.mAdapter).setData(userProfilePage);
                UserProfileFragment.this.stopLoad();
                UserProfileFragment.this.showListView();
            }
        }).a((a) new a() {
            public void a(k kVar) {
                UserProfileFragment.this.mAdapter.clear();
                UserProfileFragment.this.stopLoad();
                UserProfileFragment.this.showEmptyView();
            }
        }).a((b) new b() {
            public void a(int i, String str, k kVar) {
                UserProfileFragment.this.mAdapter.clear();
                UserProfileFragment.this.stopLoad();
                UserProfileFragment.this.showErrorView();
            }
        }).a());
    }

    /* access modifiers changed from: 0000 */
    public void stopLoad() {
        this.mSwipeRefreshLayout.setRefreshing(false);
        setLoading(false);
        setRefreshing(false);
    }
}
