package com.contextlogic.wish.activity.signup.SignupFreeGift.tabbed;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.signup.SignupFreeGift.SignupFreeGiftFragment;
import com.contextlogic.wish.activity.signup.SignupFreeGift.SignupFreeGiftServiceFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.ui.grid.StaggeredGridView;
import com.contextlogic.wish.ui.grid.StaggeredGridView.OnItemClickListener;
import com.contextlogic.wish.ui.grid.StaggeredGridView.OnScrollListener;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.viewpager.BasePagerScrollingObserver;

public class SignupFreeGiftFeedView extends LinearLayout implements ImageRestorable, BasePagerScrollingObserver {
    private StaggeredGridView mGridView;
    private SignupFreeGiftPagerHelper mPagerHelper;

    public SignupFreeGiftFeedView(int i, BaseActivity baseActivity, final SignupFreeGiftFragment signupFreeGiftFragment, final SignupFreeGiftAdapter signupFreeGiftAdapter) {
        super(baseActivity);
        this.mPagerHelper = new SignupFreeGiftPagerHelper(signupFreeGiftFragment, this, i);
        this.mGridView = new StaggeredGridView(baseActivity);
        this.mGridView.setLayoutParams(new LayoutParams(-1, -1));
        this.mGridView.setBackgroundResource(R.color.white);
        View view = new View(getContext());
        view.setLayoutParams(new LayoutParams(0, signupFreeGiftFragment.getTabAreaSize()));
        this.mGridView.setHeaderView(view);
        this.mGridView.setAdapter(signupFreeGiftAdapter);
        addView(this.mGridView);
        this.mPagerHelper.setupScroller(this.mGridView);
        this.mGridView.setOnScrollListener(new OnScrollListener() {
            public void onScrollChanged(int i, int i2, int i3, int i4) {
                SignupFreeGiftFeedView.this.handleScrollChanged(i, i2);
            }
        });
        this.mGridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(int i, View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_CLAIM_BUTTON);
                final WishProduct item = signupFreeGiftAdapter.getItem(i);
                if (item != null) {
                    signupFreeGiftFragment.withServiceFragment(new ServiceTask<BaseActivity, SignupFreeGiftServiceFragment>() {
                        public void performTask(BaseActivity baseActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                            signupFreeGiftServiceFragment.showAddToCart(item);
                        }
                    });
                }
            }
        });
    }

    public void onPagerScrollUnsettled() {
        this.mPagerHelper.onPagerScrollUnsettled();
    }

    public void onPagerScrollSettled() {
        this.mPagerHelper.onPagerScrollSettled();
    }

    public void handleScrollChanged(int i, int i2) {
        if (this.mPagerHelper != null) {
            this.mPagerHelper.handleScrollChanged(i, i2);
        }
    }

    public int getCurrentScrollY() {
        if (this.mGridView != null) {
            return this.mGridView.getScrollY();
        }
        return 0;
    }

    public void postDelayedTask(Runnable runnable, int i) {
        this.mGridView.postDelayed(runnable, (long) i);
    }

    public void scrollOffset(int i) {
        if (this.mGridView != null) {
            this.mGridView.smoothScrollBy(0, -i);
        }
    }

    public void releaseImages() {
        if (this.mGridView != null) {
            this.mGridView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mGridView != null) {
            this.mGridView.restoreImages();
        }
    }
}
