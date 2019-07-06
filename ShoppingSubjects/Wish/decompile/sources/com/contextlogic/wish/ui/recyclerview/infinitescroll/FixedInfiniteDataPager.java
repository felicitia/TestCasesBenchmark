package com.contextlogic.wish.ui.recyclerview.infinitescroll;

import java.util.List;

public class FixedInfiniteDataPager<T> implements InfiniteDataFinisher<T>, InfiniteDataPager {
    private InfiniteDataPagerCallback<T> mCallback;
    private int mCount;
    private InfiniteDataFetcher<T> mDataFetcher;
    private boolean mIsLoading;
    private boolean mNoMoreItems;
    private int mStart;

    public FixedInfiniteDataPager() {
        this(5);
    }

    public FixedInfiniteDataPager(int i) {
        this.mCount = i;
    }

    public void setDataFetcher(InfiniteDataFetcher<T> infiniteDataFetcher) {
        this.mDataFetcher = infiniteDataFetcher;
    }

    public void setCallback(InfiniteDataPagerCallback<T> infiniteDataPagerCallback) {
        this.mCallback = infiniteDataPagerCallback;
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public void fetchMore() {
        if (!this.mIsLoading && !this.mNoMoreItems) {
            this.mIsLoading = true;
            if (this.mDataFetcher != null) {
                this.mDataFetcher.fetch(this.mStart, this.mCount, this);
            }
        }
    }

    public boolean hasNoMoreItems() {
        return this.mNoMoreItems;
    }

    public void reset() {
        this.mStart = 0;
        this.mIsLoading = false;
        this.mNoMoreItems = false;
    }

    public void finish(boolean z, boolean z2, List<T> list) {
        if (this.mCallback != null) {
            this.mCallback.onLoadMoreComplete(z, this.mStart, this.mCount, list);
        }
        this.mIsLoading = false;
        this.mNoMoreItems = z2;
        if (!z) {
            this.mStart += this.mCount;
        }
    }
}
