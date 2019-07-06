package com.contextlogic.wish.ui.recyclerview.infinitescroll;

public interface InfiniteDataPager {
    void fetchMore();

    boolean hasNoMoreItems();

    boolean isLoading();

    void reset();
}
