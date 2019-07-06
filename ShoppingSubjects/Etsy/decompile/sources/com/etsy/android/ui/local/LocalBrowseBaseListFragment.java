package com.etsy.android.ui.local;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.lib.models.apiv3.LocalBrowseModule;
import com.etsy.android.lib.models.apiv3.LocalBrowseResponse;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseBaseHeaderViewHolder.a;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseMarketViewHolder;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseSectionFooterViewHolder;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.BaseRecyclerViewListFragment;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.List;

public abstract class LocalBrowseBaseListFragment<T> extends BaseRecyclerViewListFragment<T> implements b {
    protected a mHeaderListener = new a() {
        public void a() {
            if (LocalBrowseBaseListFragment.this.mLocalBrowseManager != null) {
                LocalBrowseBaseListFragment.this.mLocalBrowseManager.onClickMapArea();
            }
        }
    };
    protected a mLocalBrowseManager;
    private f mMarketCardDecoration;
    protected LocalBrowseMarketViewHolder.a mMarketListener = new LocalBrowseMarketViewHolder.a() {
        public void a(LocalMarketCard localMarketCard) {
            e.a((Activity) LocalBrowseBaseListFragment.this.getActivity()).a(localMarketCard.getLocalMarketId(), false);
        }
    };
    protected LocalBrowseSectionFooterViewHolder.a mSectionListener = new LocalBrowseSectionFooterViewHolder.a() {
        public void a(LocalBrowseModule localBrowseModule) {
        }
    };

    @LayoutRes
    public int getLayoutId() {
        return R.layout.fragment_local_browse_baserecyclerview;
    }

    /* access modifiers changed from: protected */
    public abstract void getOrFetchResults();

    public void onExpandSearchArea() {
    }

    public void onInitialLocation(Location location) {
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mLocalBrowseManager = (a) activity;
            this.mLocalBrowseManager.registerUpdateListener(this);
        } catch (ClassCastException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append(activity.toString());
            sb.append(" must implement LocalBrowseManager");
            throw new ClassCastException(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    @NonNull
    public LayoutManager createLayoutManager() {
        final int integer = getResources().getInteger(R.integer.local_browse_list_columns);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), integer);
        AnonymousClass4 r2 = new SpanSizeLookup() {
            public int getSpanSize(int i) {
                return LocalBrowseBaseListFragment.this.mAdapter.getSpanSize(i, integer);
            }
        };
        r2.setSpanIndexCacheEnabled(true);
        gridLayoutManager.setSpanSizeLookup(r2);
        this.mMarketCardDecoration = new f(getActivity(), r2, true);
        return gridLayoutManager;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        onCreateView.findViewById(R.id.empty_button).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (LocalBrowseBaseListFragment.this.mLocalBrowseManager != null) {
                    LocalBrowseBaseListFragment.this.mLocalBrowseManager.expandSearchRadius();
                }
            }
        });
        AnonymousClass6 r2 = new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (LocalBrowseBaseListFragment.this.mLocalBrowseManager != null) {
                    LocalBrowseBaseListFragment.this.mLocalBrowseManager.onClickMapArea();
                }
            }
        };
        onCreateView.findViewById(R.id.empty_result_map_click_area).setOnClickListener(r2);
        onCreateView.findViewById(R.id.no_internet_map_click_area).setOnClickListener(r2);
        onCreateView.findViewById(R.id.list_bg).setVisibility(0);
        this.mRecyclerView.addItemDecoration(this.mMarketCardDecoration);
        this.mRecyclerView.addOnScrollListener(new OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                if (((BaseRecyclerViewAdapter) recyclerView.getAdapter()).getHeaderCount() <= 0 || recyclerView.getChildAdapterPosition(recyclerView.getChildAt(0)) != 0) {
                    recyclerView.setVerticalScrollBarEnabled(true);
                } else {
                    recyclerView.setVerticalScrollBarEnabled(false);
                }
            }
        });
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mLocalBrowseManager.isRequestPending()) {
            showLoadingView();
        } else {
            getOrFetchResults();
        }
    }

    public void onDetach() {
        super.onDetach();
        this.mLocalBrowseManager.unregisterUpdateListener(this);
        this.mLocalBrowseManager = null;
    }

    /* access modifiers changed from: protected */
    public void onLoadContent() {
        getOrFetchResults();
    }

    public void onBrowseRequestPending() {
        showLoadingView();
    }

    public void onBrowseResultsSuccess(LocalBrowseResponse localBrowseResponse) {
        setLoading(false);
    }

    public void onSearchResultsSuccess(List<LocalMarketCard> list) {
        setLoading(false);
    }

    public void onResultsError() {
        setLoading(false);
        showErrorView();
    }

    public void onResultsEmpty() {
        setLoading(false);
        showEmptyView();
    }

    public void onToggleListPanel() {
        if (this.mLocalBrowseManager == null) {
            return;
        }
        if (this.mLocalBrowseManager.isListPanelShowing()) {
            this.mRecyclerView.scrollToPosition(0);
        } else {
            this.mRecyclerView.setVerticalScrollBarEnabled(false);
        }
    }
}
