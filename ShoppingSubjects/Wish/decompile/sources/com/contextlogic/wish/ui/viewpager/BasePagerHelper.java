package com.contextlogic.wish.ui.viewpager;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.ui.bottomnavigation.BottomNavigationHelper;
import com.contextlogic.wish.ui.grid.StaggeredGridView.ScrollDirection;
import java.util.ArrayList;
import java.util.Iterator;

public class BasePagerHelper {
    protected BottomNavigationHelper mBottomNavigationHelper;
    protected boolean mCanChangeScrollDirection;
    protected ScrollDirection mCurrentScrollDirection;
    protected BaseTabStripInterface mFragment;
    private boolean mHideWithinTabArea = true;
    protected int mIndex;
    protected int mLastScrollEndPosition;
    protected BasePagerScrollingObserver mPagerScrollingObserver;
    protected boolean mPagerSettled;
    protected ArrayList<Runnable> mPagerSettledTasks;
    protected Runnable mScrollEndTask;
    protected int mStartScrollPosition;
    protected int mStartTabBarOffset;

    public BasePagerHelper(BaseTabStripInterface baseTabStripInterface, BasePagerScrollingObserver basePagerScrollingObserver, int i) {
        this.mFragment = baseTabStripInterface;
        this.mPagerScrollingObserver = basePagerScrollingObserver;
        this.mIndex = i;
        init();
    }

    private void init() {
        this.mStartScrollPosition = -1;
        this.mStartTabBarOffset = -1;
        this.mCurrentScrollDirection = ScrollDirection.UNKNOWN;
        this.mPagerSettledTasks = new ArrayList<>();
        this.mPagerSettled = true;
        this.mScrollEndTask = new Runnable() {
            public void run() {
                int currentScrollY = BasePagerHelper.this.mPagerScrollingObserver.getCurrentScrollY();
                if (BasePagerHelper.this.mLastScrollEndPosition - currentScrollY == 0) {
                    BasePagerHelper.this.handleScrollEnded();
                    return;
                }
                BasePagerHelper.this.mLastScrollEndPosition = currentScrollY;
                BasePagerHelper.this.mPagerScrollingObserver.postDelayedTask(BasePagerHelper.this.mScrollEndTask, 100);
            }
        };
    }

    public void setHideWithinTabArea(boolean z) {
        this.mHideWithinTabArea = z;
    }

    public void setBottomNavigationHelper(BottomNavigationHelper bottomNavigationHelper) {
        this.mBottomNavigationHelper = bottomNavigationHelper;
    }

    public void setupScroller(View view) {
        view.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    BasePagerHelper.this.mCanChangeScrollDirection = false;
                    BasePagerHelper.this.waitForScrollEnd();
                } else if (motionEvent.getAction() == 2) {
                    BasePagerHelper.this.mCanChangeScrollDirection = true;
                }
                return false;
            }
        });
    }

    public void waitForScrollEnd() {
        this.mPagerScrollingObserver.removeCallbacks(this.mScrollEndTask);
        this.mLastScrollEndPosition = -1;
        this.mScrollEndTask.run();
    }

    public void handleScrollEnded() {
        if (this.mStartScrollPosition != -1) {
            int currentScrollY = this.mPagerScrollingObserver.getCurrentScrollY();
            if (this.mHideWithinTabArea && currentScrollY <= this.mFragment.getTabAreaSize()) {
                this.mFragment.showTabArea(true);
            } else if (currentScrollY < this.mStartScrollPosition) {
                this.mFragment.showTabArea(true);
            } else {
                this.mFragment.hideTabArea(true);
            }
            this.mStartScrollPosition = -1;
            this.mStartTabBarOffset = -1;
            this.mCurrentScrollDirection = ScrollDirection.UNKNOWN;
        }
    }

    public void onPagerScrollUnsettled() {
        this.mPagerSettled = false;
    }

    public void onPagerScrollSettled() {
        this.mPagerSettled = true;
        processPagerSettledTasks();
    }

    public void processPagerSettledTasks() {
        if (this.mPagerSettled && this.mFragment.getCurrentIndex() == this.mIndex) {
            Iterator it = this.mPagerSettledTasks.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
            this.mPagerSettledTasks.clear();
        }
    }

    public void queuePagerSettledTask(Runnable runnable) {
        this.mPagerSettledTasks.add(runnable);
        processPagerSettledTasks();
    }

    public void handleScrollChanged(int i, int i2) {
        ScrollDirection scrollDirection;
        int max = Math.max(0, i);
        if (i2 < 0) {
            scrollDirection = ScrollDirection.UP;
        } else if (i2 == 0) {
            scrollDirection = this.mCurrentScrollDirection;
        } else {
            scrollDirection = ScrollDirection.DOWN;
        }
        if (this.mStartScrollPosition == -1 || (scrollDirection != this.mCurrentScrollDirection && this.mCanChangeScrollDirection)) {
            this.mStartScrollPosition = max - i2;
            this.mStartTabBarOffset = this.mFragment.getTabAreaOffset();
            this.mCurrentScrollDirection = scrollDirection;
        }
        int i3 = this.mStartScrollPosition - max;
        if (i3 != 0) {
            this.mFragment.setTabAreaOffset(this.mStartTabBarOffset + i3);
        }
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation() && this.mBottomNavigationHelper != null) {
            this.mBottomNavigationHelper.handleScrollChanged(i2);
        }
    }
}
