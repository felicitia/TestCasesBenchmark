package com.contextlogic.wish.ui.recyclerview.infinitescroll;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

public class InfiniteScrollListener extends OnScrollListener {
    public static LayoutManagerHelper LINEAR_LAYOUT_HELPER = new LayoutManagerHelper() {
        public int findLastVisibleItemPosition(RecyclerView recyclerView) {
            return ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
        }
    };
    private LoadMoreCallback callback;
    private LayoutManagerHelper layoutManagerHelper;

    public interface LayoutManagerHelper {
        int findLastVisibleItemPosition(RecyclerView recyclerView);
    }

    public interface LoadMoreCallback {
        void onLoadMore();
    }

    public InfiniteScrollListener(LayoutManagerHelper layoutManagerHelper2) {
        this.layoutManagerHelper = layoutManagerHelper2;
    }

    public void setLoadMoreCallback(LoadMoreCallback loadMoreCallback) {
        this.callback = loadMoreCallback;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        if (this.layoutManagerHelper.findLastVisibleItemPosition(recyclerView) == recyclerView.getAdapter().getItemCount() - 1 && this.callback != null) {
            this.callback.onLoadMore();
        }
    }
}
