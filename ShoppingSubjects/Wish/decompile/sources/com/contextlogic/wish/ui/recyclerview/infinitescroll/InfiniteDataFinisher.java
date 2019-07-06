package com.contextlogic.wish.ui.recyclerview.infinitescroll;

import java.util.List;

public interface InfiniteDataFinisher<T> {
    void finish(boolean z, boolean z2, List<T> list);
}
