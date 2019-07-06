package com.etsy.android.uikit;

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
import com.etsy.android.uikit.adapter.sectioned.SectionedIRecyclerViewItemAdapter;
import com.etsy.android.uikit.nav.b;
import com.etsy.android.uikit.ui.core.NetworkLoaderFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.e;

public abstract class BaseSectionedRecyclerViewFragment<AdapterType extends SectionedIRecyclerViewItemAdapter> extends NetworkLoaderFragment implements OnRefreshListener, d {
    protected AdapterType mAdapter;
    protected Button mEmptyButton;
    protected ImageView mEmptyImage;
    protected TextView mEmptySubtext;
    protected TextView mEmptyText;
    protected View mEmptyView;
    protected View mErrorView;
    protected LayoutManager mLayoutManager;
    protected View mLoadingView;
    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    /* access modifiers changed from: protected */
    @NonNull
    public abstract AdapterType createAdapter();

    /* access modifiers changed from: protected */
    public abstract void onRetry();

    @LayoutRes
    public int getLayoutId() {
        return k.fragment_recyclerview_list;
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
        this.mRecyclerView = (RecyclerView) inflate.findViewById(i.recycler_view);
        this.mLayoutManager = createLayoutManager();
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mAdapter = createAdapter();
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mEmptyView = inflate.findViewById(i.empty_view);
        this.mEmptyText = (TextView) inflate.findViewById(i.empty_view_text);
        this.mEmptySubtext = (TextView) inflate.findViewById(i.empty_view_subtext);
        this.mEmptyButton = (Button) inflate.findViewById(i.empty_button);
        this.mEmptyImage = (ImageView) inflate.findViewById(i.empty_image);
        this.mErrorView = inflate.findViewById(i.no_internet);
        this.mLoadingView = inflate.findViewById(i.loading_view);
        View findViewById = this.mErrorView.findViewById(i.btn_retry_internet);
        if (findViewById != null) {
            findViewById.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    BaseSectionedRecyclerViewFragment.this.onRetry();
                }
            });
        }
        return inflate;
    }

    public void onDestroyView() {
        this.mLayoutManager = null;
        this.mEmptyView = null;
        this.mErrorView = null;
        this.mLoadingView = null;
        this.mRecyclerView = null;
        this.mSwipeRefreshLayout = null;
        super.onDestroyView();
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

    public final boolean popOrGoBack() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return false;
        }
        return e.b(activity.getSupportFragmentManager(), b.b(activity));
    }
}
