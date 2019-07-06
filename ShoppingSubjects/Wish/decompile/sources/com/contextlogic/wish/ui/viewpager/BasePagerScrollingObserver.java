package com.contextlogic.wish.ui.viewpager;

public interface BasePagerScrollingObserver {
    int getCurrentScrollY();

    void onPagerScrollSettled();

    void onPagerScrollUnsettled();

    void postDelayedTask(Runnable runnable, int i);

    boolean removeCallbacks(Runnable runnable);
}
