package com.etsy.android.uikit;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.util.aj;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.nav.b;
import com.etsy.android.uikit.ui.core.NetworkLoaderFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.e;
import java.util.List;

public abstract class BaseRecyclerViewListFragment<T> extends NetworkLoaderFragment implements OnRefreshListener, d {
    private int[] mAccentColors;
    /* access modifiers changed from: protected */
    public BaseRecyclerViewAdapter<T> mAdapter;
    protected Button mEmptyButton;
    protected ImageView mEmptyImage;
    protected TextView mEmptySubtext;
    protected TextView mEmptyText;
    /* access modifiers changed from: protected */
    public View mEmptyView;
    protected View mErrorView;
    private boolean mLoading;
    protected View mLoadingView;
    /* access modifiers changed from: protected */
    public RecyclerView mRecyclerView;
    private boolean mRefreshing;
    /* access modifiers changed from: protected */
    public SwipeRefreshLayout mSwipeRefreshLayout;

    /* access modifiers changed from: protected */
    public abstract void onLoadContent();

    @LayoutRes
    public int getLayoutId() {
        return k.fragment_baserecyclerview;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @NonNull
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(getLayoutId(), viewGroup, false);
        this.mSwipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(i.swipe_refresh_layout);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
        this.mSwipeRefreshLayout.setColorSchemeColors(getColorSchemeColors());
        this.mRecyclerView = (RecyclerView) inflate.findViewById(i.recycler_view);
        this.mRecyclerView.setLayoutManager(createLayoutManager());
        this.mRecyclerView.setAdapter(this.mAdapter);
        initEmptyStateViews(inflate);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void initEmptyStateViews(View view) {
        this.mEmptyView = view.findViewById(i.empty_view);
        this.mEmptyText = (TextView) view.findViewById(i.empty_view_text);
        this.mEmptySubtext = (TextView) view.findViewById(i.empty_view_subtext);
        this.mEmptyButton = (Button) view.findViewById(i.empty_button);
        this.mEmptyImage = (ImageView) view.findViewById(i.empty_image);
        this.mErrorView = view.findViewById(i.no_internet);
        this.mLoadingView = view.findViewById(i.loading_view);
        View findViewById = this.mErrorView.findViewById(i.btn_retry_internet);
        if (findViewById != null) {
            findViewById.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    BaseRecyclerViewListFragment.this.onRetry();
                }
            });
        }
    }

    public void onDestroyView() {
        this.mEmptyView = null;
        this.mErrorView = null;
        this.mLoadingView = null;
        this.mRecyclerView = null;
        this.mSwipeRefreshLayout = null;
        super.onDestroyView();
    }

    /* access modifiers changed from: protected */
    public int[] getColorSchemeColors() {
        if (this.mAccentColors == null || this.mAccentColors.length == 0) {
            this.mAccentColors = new int[]{aj.a((Context) getActivity())};
        }
        return this.mAccentColors;
    }

    public void setColorSchemeColors(int[] iArr) {
        this.mAccentColors = iArr;
    }

    /* access modifiers changed from: protected */
    public void onLoadSuccess(List<T> list, int i) {
        setLoading(false);
        if (this.mRefreshing) {
            setRefreshing(false);
            this.mAdapter.clearData();
        }
        this.mAdapter.addItems(list);
        if (isEmpty()) {
            showEmptyView();
        } else {
            showListView();
        }
    }

    /* access modifiers changed from: protected */
    public boolean isEmpty() {
        return this.mAdapter.getDataItemCount() == 0;
    }

    /* access modifiers changed from: protected */
    public void onLoadFailure() {
        setLoading(false);
        if (this.mRefreshing) {
            setRefreshing(false);
        }
        showErrorView();
    }

    /* access modifiers changed from: protected */
    public void onPreLoadContent() {
        if (!this.mRefreshing) {
            showLoadingView();
        }
    }

    public boolean canLoadContent() {
        return !this.mLoading;
    }

    public boolean isLoading() {
        return this.mLoading;
    }

    public boolean isRefreshing() {
        return this.mRefreshing;
    }

    /* access modifiers changed from: protected */
    public void setRefreshing(boolean z) {
        this.mRefreshing = z;
        if (this.mSwipeRefreshLayout != null) {
            this.mSwipeRefreshLayout.setRefreshing(z);
        }
    }

    /* access modifiers changed from: protected */
    public final void loadContent() {
        if (canLoadContent()) {
            onPreLoadContent();
            setLoading(true);
            onLoadContent();
        }
    }

    public void displayLoadingView(boolean z) {
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(z ? 0 : 8);
        }
    }

    public void setLoading(boolean z) {
        this.mLoading = z;
    }

    public void showLoadingView() {
        if (this.mEmptyView != null) {
            this.mEmptyView.setVisibility(8);
        }
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(8);
        }
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(0);
        }
        if (this.mRecyclerView != null) {
            this.mRecyclerView.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void onRetry() {
        loadContent();
    }

    public void showEmptyView() {
        if (this.mEmptyView != null) {
            this.mEmptyView.setVisibility(0);
        }
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(8);
        }
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(8);
        }
        if (this.mRecyclerView != null) {
            this.mRecyclerView.setVisibility(8);
        }
    }

    public void showListView() {
        if (this.mEmptyView != null) {
            this.mEmptyView.setVisibility(8);
        }
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(8);
        }
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(8);
        }
        if (this.mRecyclerView != null) {
            this.mRecyclerView.setVisibility(0);
        }
    }

    public void showErrorView() {
        if (this.mEmptyView != null) {
            this.mEmptyView.setVisibility(8);
        }
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(0);
        }
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(8);
        }
        if (this.mRecyclerView != null) {
            this.mRecyclerView.setVisibility(8);
        }
    }

    public void setEmptyView(View view) {
        ViewGroup viewGroup = (ViewGroup) this.mEmptyView.getParent();
        int indexOfChild = viewGroup.indexOfChild(this.mEmptyView);
        viewGroup.removeView(this.mEmptyView);
        viewGroup.addView(view, indexOfChild);
        this.mEmptyView = view;
    }

    public void onRefresh() {
        if (!this.mRefreshing) {
            setRefreshing(true);
            loadContent();
        }
    }

    public final boolean popOrGoBack() {
        FragmentActivity activity = getActivity();
        return activity != null && e.b(activity.getSupportFragmentManager(), b.b(activity));
    }
}
