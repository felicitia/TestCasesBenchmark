package com.contextlogic.wish.ui.recyclerview.infinitescroll;

public interface InfiniteDataFetcher<T> {
    void fetch(int i, int i2, InfiniteDataFinisher<T> infiniteDataFinisher);
}
