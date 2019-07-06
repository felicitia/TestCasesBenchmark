package com.etsy.android.ui;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;
import com.etsy.android.R;
import com.etsy.android.uikit.e;
import com.etsy.android.uikit.listwrapper.a.C0109a;
import com.etsy.android.uikit.view.SwipeRefreshListView;

@Deprecated
public abstract class EtsyLoadingListFragment extends EtsyCommonListFragment implements e, C0109a {
    private boolean mIsEndlessRunning;
    private boolean mIsPullToRefreshEnabled;
    private int mOffset;
    protected SwipeRefreshListView mSwipeRefreshListView;

    /* access modifiers changed from: protected */
    public void onPullToRefresh() {
    }

    public EtsyLoadingListFragment() {
        super(R.layout.fragment_loading_list);
    }

    public EtsyLoadingListFragment(int i) {
        super(i);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    /* access modifiers changed from: protected */
    public void onCreateListView(View view) {
        super.onCreateListView(view);
        this.mSwipeRefreshListView = (SwipeRefreshListView) view.findViewById(R.id.list_view);
        this.mSwipeRefreshListView.setColorSchemeResources(R.color.sk_orange_30);
        getListView().setSelector(new ColorDrawable(getResources().getColor(17170445)));
        setupLoadingList();
    }

    /* access modifiers changed from: protected */
    public void setupLoadingList() {
        if (this.mIsPullToRefreshEnabled) {
            this.mSwipeRefreshListView.setRefreshing(false);
            this.mSwipeRefreshListView.setOnRefreshListener(new OnRefreshListener() {
                public void onRefresh() {
                    EtsyLoadingListFragment.this.onPullToRefresh();
                }
            });
        } else {
            this.mSwipeRefreshListView.setEnabled(false);
        }
        this.mListView = this.mSwipeRefreshListView.getListView();
    }

    public void onStart() {
        super.onStart();
        if (this.mListView != null) {
            if (this.mIsEndlessRunning) {
                this.mSwipeRefreshListView.startEndless();
            }
            this.mSwipeRefreshListView.setLoadMoreListener(this);
        }
    }

    public void onStop() {
        super.onStop();
        this.mIsEndlessRunning = this.mSwipeRefreshListView.isEndlessLoading();
    }

    public int getApiOffset() {
        return this.mOffset;
    }

    public void setApiOffset(int i) {
        this.mOffset = i;
    }

    public void incrementApiOffset(int i) {
        this.mOffset += i;
    }

    public void startEndless() {
        this.mSwipeRefreshListView.startEndless();
    }

    public void stopEndless() {
        this.mSwipeRefreshListView.stopEndless();
    }

    public void showEndlessError() {
        this.mSwipeRefreshListView.showEndlessError();
    }

    public void removeEndlessError() {
        this.mSwipeRefreshListView.removeEndlessError();
    }

    public void startPullToRefresh() {
        onPullToRefresh();
        this.mSwipeRefreshListView.setRefreshing(true);
    }

    public void stopPullToRefresh() {
        this.mSwipeRefreshListView.setRefreshing(false);
    }

    public boolean isPullToRefreshRunning() {
        return this.mSwipeRefreshListView.isRefreshing();
    }

    public boolean isPullToRefreshEnabled() {
        return this.mIsPullToRefreshEnabled;
    }

    public void setPullToRefreshEnabled(boolean z) {
        this.mIsPullToRefreshEnabled = z;
    }

    public void showLoadingView() {
        this.mSwipeRefreshListView.stopEndless();
        super.showLoadingView();
    }

    public void showEmptyView() {
        this.mSwipeRefreshListView.stopEndless();
        super.showEmptyView();
    }

    public void showErrorView() {
        this.mSwipeRefreshListView.stopEndless();
        super.showErrorView();
    }
}
