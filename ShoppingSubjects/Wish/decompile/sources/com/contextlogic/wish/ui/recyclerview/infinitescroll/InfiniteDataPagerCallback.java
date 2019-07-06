package com.contextlogic.wish.ui.recyclerview.infinitescroll;

import java.util.List;

public interface InfiniteDataPagerCallback<T> {
    void onLoadMoreComplete(boolean z, int i, int i2, List<T> list);
}
