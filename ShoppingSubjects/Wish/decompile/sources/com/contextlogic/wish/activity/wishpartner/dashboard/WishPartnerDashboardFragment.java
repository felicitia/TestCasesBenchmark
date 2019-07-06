package com.contextlogic.wish.activity.wishpartner.dashboard;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.LoadingUiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarItem;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.Theme;
import com.contextlogic.wish.activity.wishpartner.dashboard.WishPartnerPagerAdapter.DashboardSections;
import com.contextlogic.wish.activity.wishpartner.learnmore.WishPartnerLearnMoreActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishPartnerSummary;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.viewpager.PagerSlidingTabStrip;
import com.contextlogic.wish.ui.viewpager.SafeViewPager;

public class WishPartnerDashboardFragment extends LoadingUiFragment<WishPartnerDashboardActivity> {
    /* access modifiers changed from: private */
    public AppBarLayout mAppBarLayout;
    /* access modifiers changed from: private */
    public View mBackground;
    /* access modifiers changed from: private */
    public int mBackgroundHeight;
    /* access modifiers changed from: private */
    public String mCommunityOffset;
    /* access modifiers changed from: private */
    public WishPartnerHeaderView mHeaderView;
    private WishPartnerSummary mInitialContent;
    private boolean mLoadingCommunity;
    private boolean mLoadingComplete;
    private boolean mLoadingContentErrored;
    private boolean mLoadingRecentlyEarned;
    private boolean mLoggedCommunity;
    private boolean mLoggedRecentEarnings;
    /* access modifiers changed from: private */
    public int mMinHeaderHeight;
    private boolean mNoMoreCommunityItems;
    private boolean mNoMoreRecentEarningItems;
    private OnPageChangeListener mPageChangeListener;
    /* access modifiers changed from: private */
    public WishPartnerPagerAdapter mPagerAdapter;
    /* access modifiers changed from: private */
    public String mRecentEventsOffset;
    /* access modifiers changed from: private */
    public ThemedTextView mSmallAmount;
    private PagerSlidingTabStrip mTabStrip;
    /* access modifiers changed from: private */
    public SafeViewPager mViewPager;

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.wish_partner_dashboard_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public void initializeLoadingContentView(View view) {
        this.mViewPager = (SafeViewPager) view.findViewById(R.id.wish_partner_dashboard_fragment_viewpager);
        this.mTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.wish_partner_dashboard_fragment_tabs);
        this.mHeaderView = (WishPartnerHeaderView) view.findViewById(R.id.wish_partner_dashboard_fragment_header);
        this.mSmallAmount = (ThemedTextView) view.findViewById(R.id.wish_partner_dashboard_fragment_small_amount);
        this.mSmallAmount.setAlpha(0.0f);
        this.mSmallAmount.setFontResizable(true);
        this.mAppBarLayout = (AppBarLayout) view.findViewById(R.id.wish_partner_dashboard_fragment_appbar);
        this.mBackground = view.findViewById(R.id.wish_partner_dashboard_fragment_background);
        this.mBackgroundHeight = getResources().getDimensionPixelOffset(R.dimen.wish_partner_dashboard_background_height);
        this.mMinHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.wish_partner_dashboard_background_header_height);
        this.mBackgroundHeight -= this.mMinHeaderHeight;
        this.mLoadingContentErrored = false;
        setUpAppBar();
        this.mPageChangeListener = new OnPageChangeListener() {
            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                WishPartnerDashboardFragment.this.handlePageSelected(i);
            }

            public void onPageScrollStateChanged(int i) {
                if (i == 0) {
                    WishPartnerDashboardFragment.this.onPagerScrollSettled();
                } else {
                    WishPartnerDashboardFragment.this.onPagerScrollUnsettled();
                }
            }
        };
        withActivity(new ActivityTask<WishPartnerDashboardActivity>() {
            public void performTask(WishPartnerDashboardActivity wishPartnerDashboardActivity) {
                ActionBarManager actionBarManager = wishPartnerDashboardActivity.getActionBarManager();
                actionBarManager.addActionBarItem(ActionBarItem.createLeranMoreBarItem(actionBarManager));
            }
        });
        initializeValues();
    }

    public void loadInitialContent() {
        withServiceFragment(new ServiceTask<BaseActivity, WishPartnerServiceFragment>() {
            public void performTask(BaseActivity baseActivity, WishPartnerServiceFragment wishPartnerServiceFragment) {
                wishPartnerServiceFragment.initialSetup();
            }
        });
    }

    public boolean hasItems() {
        return this.mInitialContent != null;
    }

    private void setUpAppBar() {
        if (this.mAppBarLayout != null) {
            this.mAppBarLayout.addOnOffsetChangedListener(new OnOffsetChangedListener() {
                public void onOffsetChanged(final AppBarLayout appBarLayout, final int i) {
                    WishPartnerDashboardFragment.this.mAppBarLayout.post(new Runnable() {
                        public void run() {
                            float abs = Math.abs(((float) i) / ((float) appBarLayout.getTotalScrollRange()));
                            WishPartnerDashboardFragment.this.mHeaderView.setAlpha(1.0f - (1.5f * abs));
                            WishPartnerDashboardFragment.this.mSmallAmount.setAlpha(-(1.0f - (2.0f * abs)));
                            LayoutParams layoutParams = (LayoutParams) WishPartnerDashboardFragment.this.mBackground.getLayoutParams();
                            double d = (double) abs;
                            if (d == 0.0d) {
                                layoutParams.height = WishPartnerDashboardFragment.this.mBackgroundHeight + WishPartnerDashboardFragment.this.mMinHeaderHeight;
                            } else if (d >= 1.0d) {
                                layoutParams.height = WishPartnerDashboardFragment.this.mMinHeaderHeight;
                            } else {
                                layoutParams.height = (int) (((float) (WishPartnerDashboardFragment.this.mBackgroundHeight + WishPartnerDashboardFragment.this.mMinHeaderHeight)) - (((float) WishPartnerDashboardFragment.this.mBackgroundHeight) * abs));
                            }
                            WishPartnerDashboardFragment.this.mBackground.setLayoutParams(layoutParams);
                        }
                    });
                }
            });
        }
    }

    private void initializeValues() {
        this.mLoadingComplete = false;
        this.mRecentEventsOffset = null;
        this.mCommunityOffset = null;
        this.mNoMoreCommunityItems = false;
        this.mNoMoreRecentEarningItems = false;
        this.mLoggedCommunity = false;
        this.mLoggedRecentEarnings = false;
        this.mLoadingRecentlyEarned = true;
        this.mLoadingCommunity = false;
        loadInitialContent();
    }

    /* access modifiers changed from: private */
    public void onPagerScrollUnsettled() {
        if (this.mPagerAdapter != null && !this.mLoadingContentErrored) {
            this.mPagerAdapter.onPagerScrollUnsettled();
        }
    }

    /* access modifiers changed from: private */
    public void onPagerScrollSettled() {
        if (this.mPagerAdapter != null && !this.mLoadingContentErrored) {
            this.mPagerAdapter.onPagerScrollSettled();
        }
    }

    public void handleResume() {
        super.handleResume();
        reload();
    }

    public void handleReload() {
        reload();
    }

    public void switchToPosition(int i, boolean z) {
        if (i >= 0 && i < this.mPagerAdapter.getCount()) {
            this.mViewPager.setCurrentItem(i, z);
            loadTabData();
        }
    }

    public void initializeTabs() {
        customizeTabStrip();
        this.mTabStrip.setViewPager(this.mViewPager);
        this.mTabStrip.setOnPageChangeListener(this.mPageChangeListener);
        refreshTabStripFontColors();
    }

    private void customizeTabStrip() {
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.tab_strip_indicator_height);
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.tab_strip_text_size);
        this.mTabStrip.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.mTabStrip.setIndicatorColorResource(R.color.white);
        this.mTabStrip.setIndicatorPadding(getResources().getDimensionPixelOffset(R.dimen.twelve_padding));
        this.mTabStrip.setDividerColorResource(R.color.transparent);
        this.mTabStrip.setTextColorResource(R.color.white);
        this.mTabStrip.setDividerPadding(getResources().getDimensionPixelOffset(R.dimen.four_padding));
        this.mTabStrip.setUnderlineHeight(0);
        this.mTabStrip.setIndicatorHeight(dimensionPixelOffset);
        this.mTabStrip.setTextSize(dimensionPixelOffset2);
    }

    public void refreshTabStripFontColors() {
        LinearLayout linearLayout = (LinearLayout) this.mTabStrip.getChildAt(0);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i);
            if (i == this.mViewPager.getCurrentItem()) {
                textView.setTextColor(getResources().getColor(R.color.white));
                textView.setTypeface(null, 1);
            } else {
                textView.setTextColor(getResources().getColor(R.color.very_dark_translucent_white));
                textView.setTypeface(null, 0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void handlePageSelected(int i) {
        switchToPosition(i, false);
        refreshTabStripFontColors();
    }

    public void handleInitialLoadingSuccess(final WishPartnerSummary wishPartnerSummary) {
        this.mInitialContent = wishPartnerSummary;
        this.mSmallAmount.setText(wishPartnerSummary.getDashboardInfo().getBalance().toFormattedString(false, false));
        this.mHeaderView.setup(wishPartnerSummary, this);
        this.mRecentEventsOffset = wishPartnerSummary.getNextOffset();
        this.mNoMoreRecentEarningItems = wishPartnerSummary.isNoMoreItems();
        withActivity(new ActivityTask<WishPartnerDashboardActivity>() {
            public void performTask(WishPartnerDashboardActivity wishPartnerDashboardActivity) {
                WishPartnerDashboardFragment wishPartnerDashboardFragment = WishPartnerDashboardFragment.this;
                WishPartnerPagerAdapter wishPartnerPagerAdapter = new WishPartnerPagerAdapter(wishPartnerDashboardActivity, WishPartnerDashboardFragment.this, WishPartnerDashboardFragment.this.mViewPager, wishPartnerSummary.getDashboardInfo().getCommunityTabText(), wishPartnerSummary.getDashboardInfo().getMainTabText());
                wishPartnerDashboardFragment.mPagerAdapter = wishPartnerPagerAdapter;
                WishPartnerDashboardFragment.this.mViewPager.setAdapter(WishPartnerDashboardFragment.this.mPagerAdapter);
            }
        });
        initializeTabs();
        if (!this.mLoggedRecentEarnings) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_WISH_PARTNER_DASHBOARD_RECENTLY_EARNED);
            this.mLoggedRecentEarnings = true;
        }
        this.mLoadingComplete = true;
        this.mLoadingRecentlyEarned = false;
        getLoadingPageView().markLoadingComplete();
    }

    public void handleLoadingFailed(final String str) {
        withActivity(new ActivityTask<WishPartnerDashboardActivity>() {
            public void performTask(WishPartnerDashboardActivity wishPartnerDashboardActivity) {
                wishPartnerDashboardActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                if (WishPartnerDashboardFragment.this.mPagerAdapter != null) {
                    WishPartnerDashboardFragment.this.mPagerAdapter.setLoadingFailed();
                }
            }
        });
    }

    public void handleInitialLoadingFailed(String str) {
        handleLoadingFailed(str);
        getHandler().post(new Runnable() {
            public void run() {
                WishPartnerDashboardFragment.this.getLoadingPageView().markLoadingErrored();
            }
        });
        this.mLoadingContentErrored = true;
    }

    public void setAdapterLoaded() {
        ((WishPartnerDashboardActivity) getBaseActivity()).getActionBarManager().setTheme(Theme.TRANSPARENT_BACKGROUND_LIGHT_BUTTONS);
        this.mPagerAdapter.handleRecentEarningsLoaded(this.mInitialContent.getEvents(), this.mInitialContent.getEmpryStateSpec());
    }

    private void reload() {
        if (this.mLoadingComplete || this.mLoadingContentErrored) {
            this.mRecentEventsOffset = null;
            this.mCommunityOffset = null;
            this.mNoMoreCommunityItems = false;
            this.mNoMoreRecentEarningItems = false;
            this.mLoadingRecentlyEarned = false;
            this.mLoadingCommunity = false;
            if (this.mLoadingContentErrored) {
                this.mInitialContent = null;
                loadInitialContent();
                this.mLoadingContentErrored = false;
                return;
            }
            switchToPosition(DashboardSections.RECENT_EARNINGS.ordinal(), false);
            initializeTabs();
            this.mPagerAdapter.reload();
            this.mPagerAdapter.setNoMoreResentEarningsItems(false);
            this.mPagerAdapter.setNoMoreCommunityItems(false);
            loadTabData();
        }
    }

    public void handleCommunityLoadingSuccess(WishPartnerSummary wishPartnerSummary) {
        this.mCommunityOffset = wishPartnerSummary.getNextOffset();
        this.mNoMoreCommunityItems = wishPartnerSummary.isNoMoreItems();
        if (!this.mLoggedCommunity) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_WISH_PARTNER_DASHBOARD_COMMUNITY);
            this.mLoggedCommunity = true;
        }
        this.mPagerAdapter.handleRecentCommunityLoaded(wishPartnerSummary.getEvents());
        this.mLoadingCommunity = false;
    }

    public void handleRecentEarningsLoadingSuccess(WishPartnerSummary wishPartnerSummary) {
        this.mRecentEventsOffset = wishPartnerSummary.getNextOffset();
        this.mNoMoreRecentEarningItems = wishPartnerSummary.isNoMoreItems();
        this.mHeaderView.update(wishPartnerSummary, this);
        this.mSmallAmount.setText(wishPartnerSummary.getDashboardInfo().getBalance().toFormattedString(false, false));
        this.mPagerAdapter.handleRecentEarningsLoaded(wishPartnerSummary.getEvents(), wishPartnerSummary.getEmpryStateSpec());
        this.mLoadingRecentlyEarned = false;
    }

    public void loadTabData() {
        if (this.mViewPager.getCurrentItem() == DashboardSections.RECENT_EARNINGS.ordinal()) {
            if (!this.mNoMoreRecentEarningItems && !this.mLoadingRecentlyEarned) {
                this.mLoadingRecentlyEarned = true;
                withServiceFragment(new ServiceTask<BaseActivity, WishPartnerServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerServiceFragment wishPartnerServiceFragment) {
                        wishPartnerServiceFragment.getWishRecentEvents(WishPartnerDashboardFragment.this.mRecentEventsOffset);
                    }
                });
            } else if (this.mNoMoreRecentEarningItems) {
                this.mPagerAdapter.setNoMoreResentEarningsItems(true);
            }
        } else if (!this.mNoMoreCommunityItems && !this.mLoadingCommunity) {
            this.mLoadingCommunity = true;
            withServiceFragment(new ServiceTask<BaseActivity, WishPartnerServiceFragment>() {
                public void performTask(BaseActivity baseActivity, WishPartnerServiceFragment wishPartnerServiceFragment) {
                    wishPartnerServiceFragment.getWishCommunityEvents(WishPartnerDashboardFragment.this.mCommunityOffset);
                }
            });
        } else if (this.mNoMoreCommunityItems) {
            this.mPagerAdapter.setNoMoreCommunityItems(true);
        }
    }

    public boolean handleActionBarItemSelected(int i) {
        if (i != 2002) {
            return super.handleActionBarItemSelected(i);
        }
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_DASHBOARD_LEARN_MORE);
        withActivity(new ActivityTask<WishPartnerDashboardActivity>() {
            public void performTask(WishPartnerDashboardActivity wishPartnerDashboardActivity) {
                Intent intent = new Intent();
                intent.setClass(wishPartnerDashboardActivity, WishPartnerLearnMoreActivity.class);
                wishPartnerDashboardActivity.startActivity(intent);
            }
        });
        return true;
    }
}
