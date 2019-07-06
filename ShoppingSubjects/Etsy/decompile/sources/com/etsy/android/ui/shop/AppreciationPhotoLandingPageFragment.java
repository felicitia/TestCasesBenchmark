package com.etsy.android.ui.shop;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
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
import com.etsy.android.lib.core.i;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.m;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.AppreciationPhotoFeature;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.AppreciationPhotoLike;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.adapters.AppreciationPhotoLandingPageAdapter;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.SocialShareUtil;
import com.etsy.android.uikit.util.SocialShareUtil.ShareType;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.List;
import org.parceler.d;

public class AppreciationPhotoLandingPageFragment extends EtsyFragment {
    private static final String AP_LANDING_DATA = "ap_landing_data";
    /* access modifiers changed from: private */
    public AppreciationPhotoLandingPageAdapter mAdapter;
    /* access modifiers changed from: private */
    public AppreciationPhotoFeature mData;
    private View mErrorView;
    private boolean mIsSignedIn;
    private View mLoadingView;
    private RecyclerView mRecyclerView;
    private View mRetryButton;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setHasOptionsMenu(true);
        View inflate = layoutInflater.inflate(R.layout.fragment_appreciation_photo_landing_page, viewGroup, false);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view);
        this.mLoadingView = inflate.findViewById(R.id.loading_view);
        this.mErrorView = inflate.findViewById(R.id.no_internet);
        this.mRetryButton = this.mErrorView.findViewById(R.id.btn_retry_internet);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        int integer = getResources().getInteger(R.integer.ap_landing_page_listing_card_cols);
        AnonymousClass1 r1 = new GridLayoutManager(getActivity(), integer) {
            /* access modifiers changed from: protected */
            public int getExtraLayoutSpace(State state) {
                return 200;
            }
        };
        this.mAdapter = new AppreciationPhotoLandingPageAdapter(getActivity(), getImageBatch(), getAnalyticsContext(), integer);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.addItemDecoration(this.mAdapter.getDecoration());
        r1.setSpanSizeLookup(this.mAdapter.spanSizeLookup());
        this.mRecyclerView.setLayoutManager(r1);
        this.mRetryButton.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                AppreciationPhotoLandingPageFragment.this.fetchData();
            }
        });
        this.mIsSignedIn = v.a().e();
        if (bundle == null || !bundle.containsKey(AP_LANDING_DATA)) {
            fetchData();
        } else {
            this.mData = (AppreciationPhotoFeature) d.a(bundle.getParcelable(AP_LANDING_DATA));
            this.mAdapter.setData(this.mData);
        }
        setHasOptionsMenu(true);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mData != null) {
            bundle.putParcelable(AP_LANDING_DATA, d.a(this.mData));
        }
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
            fetchData();
        }
        configureListingStateReceiver(false);
    }

    public void onDestroy() {
        super.onDestroy();
        configureListingStateReceiver(false);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem findItem = menu.findItem(R.id.menu_share);
        if (findItem == null) {
            return;
        }
        if (this.mData != null) {
            findItem.setVisible(true);
        } else {
            findItem.setVisible(false);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_share) {
            return super.onOptionsItemSelected(menuItem);
        }
        SocialShareUtil.a("appreciation_photo_page", ShareType.APPRECIATION_PHOTO, getActivity().getLocalClassName());
        e.a((Activity) this.mActivity).a((AppreciationPhotoLike) this.mData);
        return true;
    }

    private void configureListingStateReceiver(boolean z) {
        if (this.mAdapter != null) {
            if (z) {
                LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.mAdapter.getStateBroadcastReceiver(), new IntentFilter(EtsyAction.STATE_CHANGE.getAction()));
            } else {
                LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.mAdapter.getStateBroadcastReceiver());
            }
        }
    }

    /* access modifiers changed from: private */
    public void showErrorView() {
        this.mRecyclerView.setVisibility(8);
        this.mLoadingView.setVisibility(8);
        this.mErrorView.setVisibility(0);
    }

    private void showLoadingView() {
        this.mRecyclerView.setVisibility(8);
        this.mErrorView.setVisibility(8);
        this.mLoadingView.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void showDataView() {
        this.mErrorView.setVisibility(8);
        this.mLoadingView.setVisibility(8);
        this.mRecyclerView.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void fetchData() {
        showLoadingView();
        getRequestQueue().a((g<Result>) getLandingPageData((EtsyId) getArguments().getSerializable(ResponseConstants.TRANSACTION_ID)));
    }

    private i getLandingPageData(EtsyId etsyId) {
        String str = this.mIsSignedIn ? "/etsyapps/v3/bespoke/member/transaction/" : "/etsyapps/v3/bespoke/public/transaction/";
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(etsyId);
        sb.append("/appreciation-photo");
        return m.a(AppreciationPhotoFeature.class, sb.toString()).a((a) new a() {
            public void a(k kVar) {
            }
        }).a((b) new b() {
            public void a(int i, String str, k kVar) {
                AppreciationPhotoLandingPageFragment.this.showErrorView();
            }
        }).a((c<Result>) new c<AppreciationPhotoFeature>() {
            public void a(List<AppreciationPhotoFeature> list, int i, k<AppreciationPhotoFeature> kVar) {
                if (AppreciationPhotoLandingPageFragment.this.getActivity() != null && AppreciationPhotoLandingPageFragment.this.mAdapter != null) {
                    AppreciationPhotoLandingPageFragment.this.mData = (AppreciationPhotoFeature) list.get(0);
                    AppreciationPhotoLandingPageFragment.this.mAdapter.setData(AppreciationPhotoLandingPageFragment.this.mData);
                    AppreciationPhotoLandingPageFragment.this.showDataView();
                    AppreciationPhotoLandingPageFragment.this.getActivity().invalidateOptionsMenu();
                }
            }
        }).a();
    }
}
