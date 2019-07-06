package com.contextlogic.wish.activity.commercecash;

import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCommerceCashHelpInfo;
import com.contextlogic.wish.api.model.WishCommerceCashHistory;
import com.contextlogic.wish.api.model.WishCommerceCashSpecs;
import com.contextlogic.wish.api.model.WishCommerceCashUserInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.ui.viewpager.BaseTabStripInterface;
import com.contextlogic.wish.ui.viewpager.PagerSlidingTabStrip;
import com.contextlogic.wish.ui.viewpager.SafeViewPager;

public class CommerceCashFragment extends UiFragment<CommerceCashActivity> implements BaseTabStripInterface {
    private int mBannerHeight;
    private CommerceCashBannerView mBannerView;
    private CommerceCashPagerAdapter mCommerceCashPagerAdapter;
    private SafeViewPager mCommerceCashViewPager;
    private int mCurrentOffset;
    private OnPageChangeListener mPageScrollListener;
    private int mTabBarHeight;
    /* access modifiers changed from: private */
    public PagerSlidingTabStrip mTabStrip;
    private View mTabStripContainer;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.commerce_cash_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPageScrollListener = new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                CommerceCashFragment.this.handlePageSelected(i);
            }
        };
        this.mBannerHeight = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.commerce_cash_main_banner_height);
        this.mTabBarHeight = this.mBannerHeight + WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.tab_bar_height);
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mTabStrip = (PagerSlidingTabStrip) findViewById(R.id.commerce_cash_fragment_viewpager_tabs);
        this.mTabStripContainer = findViewById(R.id.commerce_cash_fragment_viewpager_tab_container);
        this.mBannerView = (CommerceCashBannerView) findViewById(R.id.commerce_cash_fragment_banner);
        this.mCommerceCashViewPager = (SafeViewPager) findViewById(R.id.commerce_cash_fragment_viewpager);
        this.mCommerceCashPagerAdapter = new CommerceCashPagerAdapter((DrawerActivity) getActivity(), this, this.mCommerceCashViewPager);
        this.mCommerceCashViewPager.setAdapter(this.mCommerceCashPagerAdapter);
        this.mCommerceCashPagerAdapter.updatePages();
        this.mTabStrip.setViewPager(this.mCommerceCashViewPager);
        this.mTabStrip.setOnPageChangeListener(this.mPageScrollListener);
        customizeTabStrip();
        refreshTabStripFontColors();
        loadPages();
    }

    public int getBannerOffset() {
        return this.mBannerHeight * -1;
    }

    /* access modifiers changed from: protected */
    public void handleResume() {
        super.handleResume();
        this.mCommerceCashPagerAdapter.prepareForReload();
        loadPages();
        if (ExperimentDataCenter.getInstance().shouldShowCommerceCashHistory()) {
            loadHistory(0, 15);
        }
    }

    public void loadPages() {
        hideLoadingSpinner();
        withServiceFragment(new ServiceTask<BaseActivity, CommerceCashServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CommerceCashServiceFragment commerceCashServiceFragment) {
                commerceCashServiceFragment.getCommerceCashInfo();
            }
        });
    }

    public void loadHistory(final int i, final int i2) {
        withServiceFragment(new ServiceTask<BaseActivity, CommerceCashServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CommerceCashServiceFragment commerceCashServiceFragment) {
                commerceCashServiceFragment.getCommerceCashEvents(i, i2);
            }
        });
    }

    public void showErrorMessage(final String str) {
        withVerifiedAuthenticationActivity(new ActivityTask<CommerceCashActivity>() {
            public void performTask(CommerceCashActivity commerceCashActivity) {
                commerceCashActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
            }
        });
    }

    public void showLoadingSpinner() {
        withVerifiedAuthenticationActivity(new ActivityTask<CommerceCashActivity>() {
            public void performTask(CommerceCashActivity commerceCashActivity) {
                commerceCashActivity.showLoadingDialog();
            }
        });
    }

    public void hideLoadingSpinner() {
        withVerifiedAuthenticationActivity(new ActivityTask<CommerceCashActivity>() {
            public void performTask(CommerceCashActivity commerceCashActivity) {
                commerceCashActivity.hideLoadingDialog();
            }
        });
    }

    public void handleLoadingInfoSuccess(WishCommerceCashSpecs wishCommerceCashSpecs, WishCommerceCashUserInfo wishCommerceCashUserInfo, WishCommerceCashHelpInfo wishCommerceCashHelpInfo) {
        if (this.mCommerceCashPagerAdapter != null) {
            this.mTabStripContainer.setVisibility(0);
            this.mBannerView.setup(wishCommerceCashUserInfo);
            this.mCommerceCashPagerAdapter.handleLoadingAddCashInfoSuccess(wishCommerceCashSpecs, wishCommerceCashUserInfo);
            this.mCommerceCashPagerAdapter.handleLoadingHelpInfoSuccess(wishCommerceCashHelpInfo);
        }
    }

    public void handleLoadingInfoFailure() {
        if (this.mCommerceCashPagerAdapter != null) {
            this.mTabStripContainer.setVisibility(8);
            this.mCommerceCashPagerAdapter.handleLoadingAddCashInfoFailure();
            this.mCommerceCashPagerAdapter.handleLoadingHelpInfoFailure();
        }
    }

    public void handleLoadingHistorySuccess(WishCommerceCashHistory wishCommerceCashHistory) {
        if (this.mCommerceCashPagerAdapter != null) {
            this.mTabStripContainer.setVisibility(0);
            this.mCommerceCashPagerAdapter.handleLoadingHistorySuccess(wishCommerceCashHistory);
        }
    }

    public void handleLoadingHistoryFailure() {
        if (this.mCommerceCashPagerAdapter != null) {
            this.mCommerceCashPagerAdapter.handleLoadingHistoryFailure();
        }
    }

    private void customizeTabStrip() {
        withActivity(new ActivityTask<CommerceCashActivity>() {
            public void performTask(CommerceCashActivity commerceCashActivity) {
                int dimensionPixelOffset = commerceCashActivity.getResources().getDimensionPixelOffset(R.dimen.tab_strip_indicator_height);
                int dimensionPixelOffset2 = commerceCashActivity.getResources().getDimensionPixelOffset(R.dimen.tab_strip_text_size);
                CommerceCashFragment.this.mTabStrip.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.transparent));
                CommerceCashFragment.this.mTabStrip.setIndicatorColorResource(R.color.white);
                CommerceCashFragment.this.mTabStrip.setDividerColorResource(R.color.transparent);
                CommerceCashFragment.this.mTabStrip.setTextColorResource(R.color.white);
                CommerceCashFragment.this.mTabStrip.setUnderlineHeight(0);
                CommerceCashFragment.this.mTabStrip.setIndicatorHeight(dimensionPixelOffset);
                CommerceCashFragment.this.mTabStrip.setTextSize(dimensionPixelOffset2);
            }
        });
    }

    public void refreshTabStripFontColors() {
        LinearLayout linearLayout = (LinearLayout) this.mTabStrip.getChildAt(0);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i);
            if (i == this.mCommerceCashViewPager.getCurrentItem()) {
                textView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.white));
                textView.setTypeface(null, 1);
            } else {
                textView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.very_dark_translucent_white));
                textView.setTypeface(null, 0);
            }
        }
    }

    public void scrollOffset() {
        int i = this.mCurrentOffset;
        this.mCurrentOffset = getTabAreaOffset();
        if (this.mCurrentOffset - i == 0) {
            return;
        }
        if (this.mCurrentOffset >= 0) {
            this.mCommerceCashPagerAdapter.scrollOffset(Math.max(0 - i, 0), getCurrentIndex());
            this.mCurrentOffset = 0;
        } else if (this.mCurrentOffset <= getBannerOffset()) {
            this.mCommerceCashPagerAdapter.scrollOffset(Math.min(getBannerOffset() - i, 0), getCurrentIndex());
            this.mCurrentOffset = getBannerOffset();
        } else {
            this.mCommerceCashPagerAdapter.scrollOffset(this.mCurrentOffset - i, getCurrentIndex());
        }
    }

    /* access modifiers changed from: private */
    public void handlePageSelected(int i) {
        if (this.mCommerceCashPagerAdapter != null) {
            this.mCommerceCashPagerAdapter.handlePageSelected(i);
        }
    }

    public void showTabArea(boolean z) {
        scrollOffset();
    }

    public void hideTabArea(boolean z) {
        scrollOffset();
    }

    public int getTabAreaOffset() {
        return ((LayoutParams) this.mTabStripContainer.getLayoutParams()).topMargin;
    }

    public void setTabAreaOffset(int i) {
        this.mTabStripContainer.clearAnimation();
        LayoutParams layoutParams = (LayoutParams) this.mTabStripContainer.getLayoutParams();
        layoutParams.topMargin = Math.min(Math.max(i, getBannerOffset()), 0);
        this.mTabStripContainer.setLayoutParams(layoutParams);
    }

    public int getTabAreaSize() {
        return this.mTabBarHeight;
    }

    public int getCurrentIndex() {
        return this.mCommerceCashViewPager.getCurrentItem();
    }

    public void hideAddCashTab() {
        this.mTabStrip.notifyDataSetChanged();
    }
}
