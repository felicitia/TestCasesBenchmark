package com.etsy.android.ui.user;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.i;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.CirclesRequest;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.util.m;
import com.etsy.android.ui.EtsyLoadingListFragment;
import com.etsy.android.ui.adapters.CardListAdapter;
import java.util.HashMap;

public class CirclesFragment extends EtsyLoadingListFragment {
    private static final int BATCH_SIZE = 30;
    private static final int MAX_FAVORITE_LISTINGS = 4;
    /* access modifiers changed from: private */
    public CardListAdapter mAdapter;
    /* access modifiers changed from: private */
    public boolean mIsTypeFollowing;
    /* access modifiers changed from: private */
    public int mOffset = 0;
    /* access modifiers changed from: private */
    public EtsyId mUserId;
    private String mUserLoginName;
    private View mView;

    private class a extends i<User> {
        private boolean c;

        public a(boolean z) {
            this.c = z;
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<User> a() {
            CirclesRequest circlesRequest;
            if (CirclesFragment.this.mIsTypeFollowing && CirclesFragment.this.isYou()) {
                circlesRequest = CirclesRequest.getMembersOfMyCircle();
            } else if (CirclesFragment.this.mIsTypeFollowing) {
                circlesRequest = CirclesRequest.getMembersOfUsersCircle(CirclesFragment.this.mUserId);
            } else if (CirclesFragment.this.isYou()) {
                circlesRequest = CirclesRequest.getCirclesContainingMe();
            } else {
                circlesRequest = CirclesRequest.getCirclesContainingUser(CirclesFragment.this.mUserId);
            }
            HashMap hashMap = new HashMap();
            hashMap.put("fields", "user_id,login_name,follower_count");
            hashMap.put("includes", com.etsy.android.ui.util.a.d(4));
            hashMap.put("limit", "30");
            StringBuilder sb = new StringBuilder();
            sb.append(CirclesFragment.this.mOffset);
            sb.append("");
            hashMap.put("offset", sb.toString());
            circlesRequest.addParams(hashMap);
            return circlesRequest;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k<User> kVar) {
            if (this.c) {
                CirclesFragment.this.mAdapter.clear();
            }
            CirclesFragment.this.stopPullToRefresh();
            CirclesFragment.this.mOffset = CirclesFragment.this.mOffset + m.a(kVar, CirclesFragment.this.mAdapter, CirclesFragment.this);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        setPullToRefreshEnabled(true);
        this.mUserId = (EtsyId) getArguments().getSerializable("user_id");
        this.mUserLoginName = getArguments().getString(ResponseConstants.USERNAME, "");
        if (this.mUserId == null) {
            this.mUserId = new EtsyId();
        }
        this.mIsTypeFollowing = getArguments().getBoolean("CIRCLE_FOLLOWING");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mView = super.onCreateView(layoutInflater, viewGroup, bundle);
        setEmptyText((CharSequence) getEmptyString());
        this.mListView.setDivider(null);
        return this.mView;
    }

    private String getEmptyString() {
        if (isYou()) {
            return getString(this.mIsTypeFollowing ? R.string.empty_following_self : R.string.empty_followers_self);
        }
        return String.format(getString(this.mIsTypeFollowing ? R.string.empty_following_user : R.string.empty_followers_user), new Object[]{this.mUserLoginName});
    }

    /* access modifiers changed from: private */
    public boolean isYou() {
        return !this.mUserId.hasId() || v.a().m().equals(this.mUserId);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mAdapter == null) {
            this.mAdapter = new CardListAdapter(this.mActivity, R.layout.list_item_card_standard, getImageBatch(), getAnalyticsContext());
            showLoadingView();
            onLoadMoreItems();
        }
        this.mAdapter.notifyDataSetChanged();
        setListAdapter(this.mAdapter);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void onRetryClicked() {
        showLoadingView();
        onLoadMoreItems();
    }

    public void onLoadMoreItems() {
        getRequestQueue().a((Object) this, (g<Result>) new a<Result>(false));
    }

    /* access modifiers changed from: protected */
    public void onPullToRefresh() {
        this.mOffset = 0;
        stopEndless();
        getRequestQueue().a((Object) this, (g<Result>) new a<Result>(true));
    }
}
