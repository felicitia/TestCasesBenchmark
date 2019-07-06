package com.contextlogic.wish.ui.viewpager;

import com.contextlogic.wish.ui.view.WishStateRefresher;

public interface BasePagerViewInterface extends WishStateRefresher {
    void cleanup();

    void releaseImages();

    void restoreImages();
}
