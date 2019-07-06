package com.etsy.android.ui.homescreen;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.etsy.android.R;
import com.etsy.android.lib.config.g;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.cardviewelement.BasicSectionHeader;
import com.etsy.android.lib.models.homescreen.HomescreenTab;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.j;
import com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter;
import com.etsy.android.vespa.b.b;
import com.etsy.android.vespa.k;
import java.util.HashMap;
import java.util.Map;
import org.parceler.d;

public class HomescreenFragment extends CardRecyclerViewBaseFragment {
    public static final String ARG_TAB_DATA = "tab_data";
    private static final String TAG = f.a(HomescreenFragment.class);
    private a mHomescreenScrollListener;
    private IntentFilter mIntentFilter = new IntentFilter(EtsyAction.RECENTLY_VIEWED_CLEAR.getAction());
    boolean mNeedsRefresh = false;
    protected b mPagination = new b();
    private BroadcastReceiver mRecentlyViewedBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(EtsyAction.RECENTLY_VIEWED_CLEAR.getAction())) {
                HomescreenFragment.this.mNeedsRefresh = true;
            }
        }
    };
    private HomescreenTab mTabData;

    private class a extends OnScrollListener {
        private int b;

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        }

        private a() {
            this.b = -1;
        }

        public void a() {
            this.b = -1;
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            if (HomescreenFragment.this.getUserVisibleHint()) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                int findFirstCompletelyVisibleItemPosition = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
                int findLastCompletelyVisibleItemPosition = gridLayoutManager.findLastCompletelyVisibleItemPosition();
                BaseViewHolderFactoryRecyclerViewAdapter baseViewHolderFactoryRecyclerViewAdapter = (BaseViewHolderFactoryRecyclerViewAdapter) recyclerView.getAdapter();
                if (baseViewHolderFactoryRecyclerViewAdapter != null) {
                    if (this.b == -1) {
                        int i3 = 0;
                        while (true) {
                            if (i3 >= baseViewHolderFactoryRecyclerViewAdapter.getItemCount()) {
                                break;
                            } else if (baseViewHolderFactoryRecyclerViewAdapter.getItemViewType(i3) == R.id.view_type_section_header) {
                                this.b = i3;
                                break;
                            } else {
                                i3++;
                            }
                        }
                    }
                    if (!(findFirstCompletelyVisibleItemPosition == -1 || findLastCompletelyVisibleItemPosition == -1)) {
                        if (this.b <= -1 || this.b >= baseViewHolderFactoryRecyclerViewAdapter.getItemCount() || ((k) baseViewHolderFactoryRecyclerViewAdapter.getItem(this.b)).getViewType() != R.id.view_type_section_header) {
                            this.b = -1;
                        } else {
                            while (findFirstCompletelyVisibleItemPosition <= findLastCompletelyVisibleItemPosition) {
                                if (baseViewHolderFactoryRecyclerViewAdapter.getItemViewType(findFirstCompletelyVisibleItemPosition) == R.id.view_type_section_header) {
                                    if (findFirstCompletelyVisibleItemPosition > this.b) {
                                        String analyticsName = ((BasicSectionHeader) baseViewHolderFactoryRecyclerViewAdapter.getItem(this.b)).getAnalyticsName();
                                        w analyticsContext = HomescreenFragment.this.getAnalyticsContext();
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("scrolled_past_");
                                        sb.append(analyticsName);
                                        analyticsContext.a(sb.toString(), null);
                                        this.b = findFirstCompletelyVisibleItemPosition;
                                    }
                                } else if (this.b < baseViewHolderFactoryRecyclerViewAdapter.getItemCount() && findFirstCompletelyVisibleItemPosition == baseViewHolderFactoryRecyclerViewAdapter.getItemCount() - 1) {
                                    String analyticsName2 = ((BasicSectionHeader) baseViewHolderFactoryRecyclerViewAdapter.getItem(this.b)).getAnalyticsName();
                                    w analyticsContext2 = HomescreenFragment.this.getAnalyticsContext();
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("scrolled_past_");
                                    sb2.append(analyticsName2);
                                    analyticsContext2.a(sb2.toString(), null);
                                    this.b = baseViewHolderFactoryRecyclerViewAdapter.getItemCount();
                                }
                                findFirstCompletelyVisibleItemPosition++;
                            }
                        }
                    }
                }
            }
        }
    }

    public com.etsy.android.vespa.b.a getPagination() {
        return this.mPagination;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mTabData = (HomescreenTab) d.a(getArguments().getParcelable(ARG_TAB_DATA));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mHomescreenScrollListener = new a();
        this.mRecyclerView.addOnScrollListener(this.mHomescreenScrollListener);
        return onCreateView;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        j.a(this.mRecyclerView.getViewTreeObserver(), (OnGlobalLayoutListener) new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (HomescreenFragment.this.mRecyclerView != null && HomescreenFragment.this.mRecyclerView.getChildCount() > 0) {
                    j.b(HomescreenFragment.this.mRecyclerView.getViewTreeObserver(), (OnGlobalLayoutListener) this);
                    FragmentActivity activity = HomescreenFragment.this.getActivity();
                    if (activity instanceof TrackingBaseActivity) {
                        ((TrackingBaseActivity) activity).getGraphiteTimerManager().a("homescreen_tabs.%s.time_to_results_displayed", String.format("homescreen_tabs.%s.time_to_results_displayed", new Object[]{HomescreenFragment.this.getTrackingName()}));
                    }
                }
            }
        });
    }

    public void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
        this.mNeedsRefresh = getAdapter().getItemCount() == 0;
    }

    public void onResume() {
        super.onResume();
        if (this.mNeedsRefresh) {
            forceRefresh();
        }
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.mRecentlyViewedBroadcastReceiver);
    }

    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.mRecentlyViewedBroadcastReceiver, this.mIntentFilter);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mRecentlyViewedBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.mRecentlyViewedBroadcastReceiver);
        }
    }

    public void forceRefresh() {
        removeCachedResponses();
        getAdapter().clear();
        resetAndLoadContent();
    }

    public void onRefresh() {
        super.onRefresh();
        if (this.mHomescreenScrollListener != null) {
            this.mHomescreenScrollListener.a();
        }
    }

    public String getApiUrl() {
        return this.mTabData.getApiUrl();
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getRequestParams() {
        HashMap hashMap = new HashMap();
        if (!this.mTabData.getNeedsRecentlyViewedIds()) {
            return hashMap;
        }
        hashMap.put("is_tablet", Boolean.toString((l.b((Activity) getActivity()) && l.f((Activity) getActivity())) || (l.c((Activity) getActivity()) && l.e((Activity) getActivity()))));
        hashMap.put("device_id", g.a().b());
        hashMap.putAll(b.a().c());
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public void onLoadContent() {
        if (this.mTabData == null) {
            f.c(TAG, "Page ID has not been set for this tab yet.");
            return;
        }
        this.mNeedsRefresh = false;
        if (!v.a().e() && this.mTabData.showSignIn()) {
            onLoadSuccess(null);
            showSignInView();
        } else if (this.mTabData.getPage() != null) {
            onLoadComplete(this.mTabData.getPage());
            this.mTabData.clearPage();
            if (!TextUtils.isEmpty(this.mTabData.getNextPath())) {
                this.mPagination.onSuccess(this.mTabData.getNextPath(), getAdapter().getItemCount());
                this.mTabData.clearNextPath();
            }
        } else {
            super.onLoadContent();
        }
    }

    /* access modifiers changed from: 0000 */
    public void showSignInView() {
        this.mEmptyMessageView.setTitle(getString(R.string.homescreen_empty_signed_out));
        this.mEmptyMessageView.setImage(R.drawable.empty_signedout_door);
        this.mEmptyMessageView.setButtonText((int) R.string.get_started);
        this.mEmptyMessageView.setButtonClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                HomescreenFragment.this.mEmptyMessageView.setVisibility(8);
                ((com.etsy.android.ui.nav.b) e.a(HomescreenFragment.this.getActivity()).a().a((com.etsy.android.lib.logger.j) HomescreenFragment.this)).b(true);
            }
        });
        showEmptyView();
    }
}
