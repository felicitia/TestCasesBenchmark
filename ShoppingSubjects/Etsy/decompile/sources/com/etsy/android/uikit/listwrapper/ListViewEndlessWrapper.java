package com.etsy.android.uikit.listwrapper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.etsy.android.lib.util.a;
import com.etsy.android.uikit.listwrapper.a.C0109a;
import com.etsy.android.uikit.util.FocusSafeScrollListener;
import com.etsy.android.uikit.util.TrackingOnClickListener;

@Deprecated
public class ListViewEndlessWrapper implements OnScrollListener, a {
    private View mFooter;
    private View mFooterError;
    private boolean mHasRequestedMore;
    private boolean mIsErrorAdded;
    private boolean mIsFooterAdded;
    protected ListView mListView;
    /* access modifiers changed from: private */
    public C0109a mLoadMoreListener;
    private int mOffset = 2;
    private OnScrollListener mOnScrollListener;

    public ListViewEndlessWrapper(ListView listView, int i, int i2, int i3) {
        this.mListView = listView;
        LayoutInflater from = LayoutInflater.from(listView.getContext());
        this.mFooter = from.inflate(i, null);
        this.mFooter.setEnabled(false);
        this.mFooter.setClickable(false);
        this.mFooterError = from.inflate(i2, null);
        this.mFooterError.setEnabled(false);
        this.mFooterError.setClickable(false);
        this.mFooterError.findViewById(i3).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (ListViewEndlessWrapper.this.mLoadMoreListener != null) {
                    ListViewEndlessWrapper.this.startEndless();
                    ListViewEndlessWrapper.this.requestLoadMore();
                }
            }
        });
        Context context = listView.getContext();
        if (context instanceof Activity) {
            listView.setOnScrollListener(new FocusSafeScrollListener((Activity) context) {
                public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                    super.onScroll(absListView, i, i2, i3);
                    ListViewEndlessWrapper.this.onScroll(absListView, i, i2, i3);
                }

                public void onScrollStateChanged(AbsListView absListView, int i) {
                    super.onScrollStateChanged(absListView, i);
                    ListViewEndlessWrapper.this.onScrollStateChanged(absListView, i);
                }
            });
        } else {
            listView.setOnScrollListener(this);
        }
        if (a.b()) {
            this.mListView.addFooterView(new View(this.mListView.getContext()));
        }
    }

    public void setLoadMoreListener(C0109a aVar) {
        this.mLoadMoreListener = aVar;
    }

    public void setOffset(int i) {
        this.mOffset = i;
    }

    public void startEndless() {
        removeEndlessError();
        if (!this.mIsFooterAdded && this.mListView.getCount() > 0) {
            this.mIsFooterAdded = true;
            this.mListView.addFooterView(this.mFooter);
        }
        this.mHasRequestedMore = false;
    }

    public void stopEndless() {
        if (this.mIsFooterAdded) {
            this.mListView.removeFooterView(this.mFooter);
            this.mIsFooterAdded = false;
        }
        this.mHasRequestedMore = false;
    }

    public void showEndlessError() {
        stopEndless();
        if (!this.mIsErrorAdded && this.mListView.getCount() > 0) {
            this.mIsErrorAdded = true;
            this.mListView.addFooterView(this.mFooterError);
        }
        this.mHasRequestedMore = false;
    }

    public void removeEndlessError() {
        if (this.mIsErrorAdded) {
            this.mListView.removeFooterView(this.mFooterError);
            this.mIsErrorAdded = false;
        }
    }

    public boolean isEndlessLoading() {
        return this.mIsFooterAdded;
    }

    public void setAdapter(ListAdapter listAdapter) {
        this.mListView.setAdapter(listAdapter);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.mOnScrollListener != null) {
            this.mOnScrollListener.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.mIsFooterAdded && this.mLoadMoreListener != null && !this.mHasRequestedMore && i + i2 + this.mOffset >= i3) {
            requestLoadMore();
        }
        if (this.mOnScrollListener != null) {
            this.mOnScrollListener.onScroll(absListView, i, i2, i3);
        }
    }

    /* access modifiers changed from: private */
    public void requestLoadMore() {
        this.mHasRequestedMore = true;
        this.mLoadMoreListener.onLoadMoreItems();
    }
}
