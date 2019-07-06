package com.etsy.android.uikit;

import android.os.Bundle;
import com.etsy.android.uikit.adapter.EndlessRecyclerViewAdapter;
import com.etsy.android.vespa.e;
import java.util.List;

public abstract class EndlessRecyclerViewListFragment<T> extends BaseRecyclerViewListFragment<T> implements e, e {
    private static final String KEY_CONTENT_EXHAUSTED = "key_content_exhausted";
    private static final String KEY_OFFSET = "key_offset";
    private boolean mIsContentExhausted;
    private int mOffset;

    public int getLoadTriggerPosition() {
        return 12;
    }

    /* access modifiers changed from: protected */
    public abstract void onLoadContent();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mIsContentExhausted = bundle.getBoolean(KEY_CONTENT_EXHAUSTED, false);
            this.mOffset = bundle.getInt(KEY_OFFSET, 0);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(KEY_CONTENT_EXHAUSTED, this.mIsContentExhausted);
        bundle.putInt(KEY_OFFSET, this.mOffset);
    }

    public boolean canLoadContent() {
        return !this.mIsContentExhausted && super.canLoadContent();
    }

    /* access modifiers changed from: protected */
    public void setContentExhausted(boolean z) {
        this.mIsContentExhausted = z;
    }

    /* access modifiers changed from: protected */
    public void onLoadSuccess(List<T> list, int i) {
        if (this.mAdapter.getDataItemCount() > 0 && !isRefreshing()) {
            stopEndless();
            removeEndlessError();
        }
        this.mOffset += list.size();
        super.onLoadSuccess(list, i);
        if (this.mOffset >= i) {
            this.mIsContentExhausted = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onLoadFailure() {
        if (this.mAdapter.getDataItemCount() <= 0) {
            super.onLoadFailure();
        } else if (isRefreshing()) {
            super.onLoadFailure();
        } else {
            setLoading(false);
            showEndlessError();
        }
    }

    /* access modifiers changed from: protected */
    public void onPreLoadContent() {
        if (this.mAdapter.getDataItemCount() <= 0 || isRefreshing()) {
            super.onPreLoadContent();
        } else {
            startEndless();
        }
    }

    public void onScrolledToLoadTrigger() {
        loadContent();
    }

    public void startEndless() {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.post(new Runnable() {
                public void run() {
                    EndlessRecyclerViewListFragment.this.mAdapter.addFooter(EndlessRecyclerViewAdapter.VIEW_TYPE_ENDLESS_LOADING);
                }
            });
        }
    }

    public void stopEndless() {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.post(new Runnable() {
                public void run() {
                    EndlessRecyclerViewListFragment.this.mAdapter.removeFooter(EndlessRecyclerViewAdapter.VIEW_TYPE_ENDLESS_LOADING);
                }
            });
        }
    }

    public void showEndlessError() {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.post(new Runnable() {
                public void run() {
                    EndlessRecyclerViewListFragment.this.mAdapter.addFooter(EndlessRecyclerViewAdapter.VIEW_TYPE_ENDLESS_ERROR);
                }
            });
        }
    }

    public void removeEndlessError() {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.post(new Runnable() {
                public void run() {
                    EndlessRecyclerViewListFragment.this.mAdapter.removeFooter(EndlessRecyclerViewAdapter.VIEW_TYPE_ENDLESS_ERROR);
                }
            });
        }
    }

    public int getApiOffset() {
        return this.mOffset;
    }

    public void setApiOffset(int i) {
        this.mOffset = i;
    }

    /* access modifiers changed from: protected */
    public void resetAndLoadContent() {
        setApiOffset(0);
        setLoading(false);
        this.mIsContentExhausted = false;
        loadContent();
    }

    public void onRefresh() {
        if (!isRefreshing()) {
            this.mOffset = 0;
            this.mIsContentExhausted = false;
            super.onRefresh();
        }
    }
}
