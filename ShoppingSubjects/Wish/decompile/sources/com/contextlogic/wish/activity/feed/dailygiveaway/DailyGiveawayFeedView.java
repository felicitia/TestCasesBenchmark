package com.contextlogic.wish.activity.feed.dailygiveaway;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.BaseProductFeedAdapter.DataProvider;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayGridAdapter.WishProductPair;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCurrentDailyGiveawayInfo;
import com.contextlogic.wish.api.model.WishInfoDailyGiveawayInfo;
import com.contextlogic.wish.api.model.WishInfoDailyGiveawayInfo.WishInfoCategory;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishUpcomingDailyGiveawayInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.bottomnavigation.BottomNavigationHelper;
import com.contextlogic.wish.ui.bottomnavigation.BottomNavigationInterface;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.listview.ListeningListView;
import com.contextlogic.wish.ui.listview.ListeningListView.ScrollViewListener;
import com.contextlogic.wish.ui.loading.LoadingFooterView;
import com.contextlogic.wish.ui.loading.LoadingFooterView.VisibilityMode;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.SlidingTabStrip.OnTabClickListener;
import com.contextlogic.wish.util.PreferenceUtil;
import java.util.ArrayList;
import java.util.HashMap;

public class DailyGiveawayFeedView extends LoadingPageView implements DailyGiveawayFeedViewInterface, ImageRestorable, LoadingPageManager {
    private DrawerActivity mBaseActivity;
    /* access modifiers changed from: private */
    public BottomNavigationHelper mBottomNavigationHelper;
    private DailyGiveawayListAdapter mCurrentAdapter;
    /* access modifiers changed from: private */
    public WishCurrentDailyGiveawayInfo mCurrentDailyGiveawayInfo;
    private int mDataIndex;
    private ThemedTextView mHeaderSubtitleText;
    private ThemedTextView mHeaderTitleText;
    private View mHeaderView;
    private DailyGiveawayInfoAdapter mInfoAdapter;
    /* access modifiers changed from: private */
    public WishInfoDailyGiveawayInfo mInfoDailyGiveawayInfo;
    private boolean mIsLoading = true;
    private ListeningListView mListView;
    private LoadingFooterView mLoadingFooter;
    private ImageHttpPrefetcher mPrefetcher = new ImageHttpPrefetcher();
    private ProductFeedFragment mProductFeedFragment;
    private DailyGiveawayState mState;
    private DailyGiveawayStatusBox mStatusBox;
    /* access modifiers changed from: private */
    public DailyGiveawaySubtab mSubtabState;
    /* access modifiers changed from: private */
    public DailyGiveawayTabStrip mTabs;
    private DailyGiveawayGridAdapter mUpcomingAdapter;
    /* access modifiers changed from: private */
    public WishUpcomingDailyGiveawayInfo mUpcomingDailyGiveawayInfo;

    public enum DailyGiveawayState {
        NOT_STARTED,
        STARTING_SOON,
        ONGOING,
        ENDED,
        ALREADY_CLAIMED,
        WINNERS_PICKED,
        RAFFLE_PRIZE_CLAIMED,
        RAFFLE_PRIZE_CHECKED_OUT,
        RAFFLE_PRIZE_CLAIM_ENDED;

        public static DailyGiveawayState fromInteger(int i) {
            switch (i) {
                case 0:
                    return NOT_STARTED;
                case 1:
                    return STARTING_SOON;
                case 2:
                    return ONGOING;
                case 3:
                    return ENDED;
                case 4:
                    return ALREADY_CLAIMED;
                case 5:
                    return WINNERS_PICKED;
                case 6:
                    return RAFFLE_PRIZE_CLAIMED;
                case 7:
                    return RAFFLE_PRIZE_CHECKED_OUT;
                case 8:
                    return RAFFLE_PRIZE_CLAIM_ENDED;
                default:
                    return NOT_STARTED;
            }
        }
    }

    public enum DailyGiveawaySubtab {
        CURRENT,
        UPCOMING,
        INFO;

        public static DailyGiveawaySubtab fromInteger(int i) {
            switch (i) {
                case 0:
                    return CURRENT;
                case 1:
                    return UPCOMING;
                case 2:
                    return INFO;
                default:
                    return CURRENT;
            }
        }
    }

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.daily_giveaway_product_feed;
    }

    public DailyGiveawayFeedView(int i, DrawerActivity drawerActivity, ProductFeedFragment productFeedFragment) {
        super(drawerActivity);
        this.mDataIndex = i;
        this.mBaseActivity = drawerActivity;
        this.mProductFeedFragment = productFeedFragment;
        setLoadingPageManager(this);
    }

    public void initializeLoadingContentView(View view) {
        this.mListView = (ListeningListView) view.findViewById(R.id.product_feed_gridview);
        this.mListView.setBackgroundResource(R.color.gray7);
        this.mSubtabState = DailyGiveawaySubtab.CURRENT;
        setHideErrors(false);
        init();
        this.mProductFeedFragment.loadCurrentDailyGiveaway();
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation() && (this.mProductFeedFragment instanceof BottomNavigationInterface)) {
            this.mBottomNavigationHelper = new BottomNavigationHelper(this.mProductFeedFragment);
            this.mListView.setScrollViewListener(new ScrollViewListener() {
                public void onScrollChanged(int i, int i2) {
                    DailyGiveawayFeedView.this.mBottomNavigationHelper.handleScrollChanged(i2);
                }
            });
        }
    }

    public void handleReload() {
        this.mIsLoading = true;
        if (this.mSubtabState == DailyGiveawaySubtab.CURRENT) {
            this.mProductFeedFragment.loadCurrentDailyGiveaway();
        } else if (this.mSubtabState == DailyGiveawaySubtab.UPCOMING) {
            this.mProductFeedFragment.loadUpcomingDailyGiveaway();
        } else if (this.mSubtabState == DailyGiveawaySubtab.INFO) {
            this.mProductFeedFragment.loadInfoDailyGiveaway();
        }
    }

    public boolean hasItems() {
        return !isLoadingErrored() && !((this.mCurrentDailyGiveawayInfo == null || this.mCurrentDailyGiveawayInfo.getProducts() == null || this.mCurrentDailyGiveawayInfo.getProducts().size() <= 0) && ((this.mUpcomingDailyGiveawayInfo == null || this.mUpcomingDailyGiveawayInfo.getProducts() == null || this.mUpcomingDailyGiveawayInfo.getProducts().size() <= 0) && this.mInfoDailyGiveawayInfo == null));
    }

    public void releaseImages() {
        if (this.mCurrentAdapter != null) {
            this.mCurrentAdapter.releaseImages();
        }
        if (this.mUpcomingAdapter != null) {
            this.mUpcomingAdapter.releaseImages();
        }
        if (this.mPrefetcher != null) {
            this.mPrefetcher.cancelPrefetching();
        }
    }

    public void restoreImages() {
        if (this.mCurrentAdapter != null) {
            this.mCurrentAdapter.restoreImages();
        }
        if (this.mUpcomingAdapter != null) {
            this.mUpcomingAdapter.restoreImages();
        }
        if (this.mPrefetcher != null) {
            this.mPrefetcher.resumePrefetching();
        }
    }

    public DailyGiveawayState getState() {
        return this.mState;
    }

    private void init() {
        initAdapters();
        initGrid();
        initTabs();
    }

    private void initGrid() {
        this.mHeaderView = inflate(this.mBaseActivity, R.layout.daily_giveaway_header, null);
        this.mHeaderTitleText = (ThemedTextView) this.mHeaderView.findViewById(R.id.daily_giveaway_header_title);
        this.mHeaderSubtitleText = (ThemedTextView) this.mHeaderView.findViewById(R.id.daily_giveaway_header_subtitle);
        this.mTabs = (DailyGiveawayTabStrip) this.mHeaderView.findViewById(R.id.daily_giveaway_tab_strip);
        this.mStatusBox = (DailyGiveawayStatusBox) this.mHeaderView.findViewById(R.id.daily_giveaway_status_box);
        this.mListView.addHeaderView(this.mHeaderView);
        this.mLoadingFooter = new LoadingFooterView(this.mBaseActivity);
        this.mLoadingFooter.setReserveSpaceWhenHidden(false);
        this.mListView.addFooterView(this.mLoadingFooter);
    }

    private void initTabs() {
        this.mTabs.setOnTabClickListener(new OnTabClickListener() {
            public void onTabSelected(int i) {
                DailyGiveawayFeedView.this.mSubtabState = DailyGiveawaySubtab.fromInteger(i);
                DailyGiveawayFeedView.this.mTabs.selectTab(i);
                DailyGiveawayFeedView.this.refreshGridView(true);
                if (DailyGiveawayFeedView.this.mSubtabState == DailyGiveawaySubtab.CURRENT) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_CURRENT, DailyGiveawayFeedView.this.getGiveawayImpressionInfo());
                } else if (DailyGiveawayFeedView.this.mSubtabState == DailyGiveawaySubtab.UPCOMING) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_UPCOMING, DailyGiveawayFeedView.this.getGiveawayImpressionInfo());
                } else if (DailyGiveawayFeedView.this.mSubtabState == DailyGiveawaySubtab.INFO) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_INFO, DailyGiveawayFeedView.this.getGiveawayImpressionInfo());
                }
            }
        });
    }

    private void initAdapters() {
        DailyGiveawayListAdapter dailyGiveawayListAdapter = new DailyGiveawayListAdapter(this.mBaseActivity, this.mProductFeedFragment, this, this.mListView, this.mPrefetcher);
        this.mCurrentAdapter = dailyGiveawayListAdapter;
        this.mUpcomingAdapter = new DailyGiveawayGridAdapter(this.mBaseActivity, this.mProductFeedFragment, this.mListView, this.mPrefetcher);
        this.mInfoAdapter = new DailyGiveawayInfoAdapter(this.mBaseActivity);
    }

    public void handleCurrentDailyGiveawaySuccess(WishCurrentDailyGiveawayInfo wishCurrentDailyGiveawayInfo) {
        updateCurrentDailyGiveawayInfo(wishCurrentDailyGiveawayInfo);
    }

    public void handleCurrentDailyGiveawayFailure() {
        updateCurrentDailyGiveawayInfo(null);
    }

    public void handleUpcomingDailyGiveawaySuccess(WishUpcomingDailyGiveawayInfo wishUpcomingDailyGiveawayInfo) {
        updateUpcomingDailyGiveawayInfo(wishUpcomingDailyGiveawayInfo);
    }

    public void handleUpcomingDailyGiveawayFailure() {
        updateUpcomingDailyGiveawayInfo(null);
    }

    public void handleInfoDailyGiveawaySuccess(WishInfoDailyGiveawayInfo wishInfoDailyGiveawayInfo) {
        updateInfoDailyGiveawayInfo(wishInfoDailyGiveawayInfo);
    }

    public void handleInfoDailyGiveawayFailure() {
        updateInfoDailyGiveawayInfo(null);
    }

    public void updateCurrentDailyGiveawayInfo(WishCurrentDailyGiveawayInfo wishCurrentDailyGiveawayInfo) {
        if (wishCurrentDailyGiveawayInfo != null) {
            this.mCurrentDailyGiveawayInfo = wishCurrentDailyGiveawayInfo;
            this.mState = this.mCurrentDailyGiveawayInfo.getState();
            if (this.mState == DailyGiveawayState.ONGOING) {
                PreferenceUtil.setLong("LastDailyGiveawayFeedTime", System.currentTimeMillis());
            }
            this.mStatusBox.setState(this.mState, this.mSubtabState, this.mCurrentDailyGiveawayInfo.getStatusTitle(), this.mCurrentDailyGiveawayInfo.getStatusMessage());
            if (this.mCurrentDailyGiveawayInfo.getClaimedProduct() != null) {
                this.mStatusBox.setClaimedProduct(this.mCurrentDailyGiveawayInfo.getClaimedProduct());
            }
            if (this.mSubtabState == DailyGiveawaySubtab.CURRENT && this.mIsLoading) {
                this.mIsLoading = false;
                markLoadingComplete();
                openCurrentTab();
                refreshGridView(false);
            }
        } else if (this.mSubtabState == DailyGiveawaySubtab.CURRENT && this.mIsLoading) {
            this.mIsLoading = false;
            markLoadingErrored();
            refreshGridView(false);
            this.mLoadingFooter.setVisibilityMode(VisibilityMode.NO_MORE_ITEMS);
        }
    }

    public void updateUpcomingDailyGiveawayInfo(WishUpcomingDailyGiveawayInfo wishUpcomingDailyGiveawayInfo) {
        if (wishUpcomingDailyGiveawayInfo != null) {
            this.mUpcomingDailyGiveawayInfo = wishUpcomingDailyGiveawayInfo;
            if (this.mSubtabState == DailyGiveawaySubtab.UPCOMING && this.mIsLoading) {
                this.mIsLoading = false;
                markLoadingComplete();
                openUpcomingTab();
                refreshGridView(false);
            }
        } else if (this.mSubtabState == DailyGiveawaySubtab.UPCOMING && this.mIsLoading) {
            this.mIsLoading = false;
            markLoadingErrored();
            refreshGridView(false);
        }
    }

    public void updateInfoDailyGiveawayInfo(WishInfoDailyGiveawayInfo wishInfoDailyGiveawayInfo) {
        if (wishInfoDailyGiveawayInfo != null) {
            this.mInfoDailyGiveawayInfo = wishInfoDailyGiveawayInfo;
            if (this.mSubtabState == DailyGiveawaySubtab.INFO && this.mIsLoading) {
                this.mIsLoading = false;
                markLoadingComplete();
                openInfoTab();
                refreshGridView(false);
            }
        } else if (this.mSubtabState == DailyGiveawaySubtab.INFO && this.mIsLoading) {
            this.mIsLoading = false;
            markLoadingErrored();
            refreshGridView(false);
        }
    }

    private void openCurrentTab() {
        updateCurrentAdapter();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DAILY_GIVEAWAY_CURRENT, this.mCurrentAdapter.getExtraInfo());
        this.mListView.setAdapter(this.mCurrentAdapter);
        this.mCurrentAdapter.notifyDataSetChanged();
        this.mListView.setDivider(new ColorDrawable(WishApplication.getInstance().getResources().getColor(R.color.daily_giveaway_cloud_color_background)));
        this.mListView.setDividerHeight(WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.eight_padding));
        if (this.mCurrentDailyGiveawayInfo != null) {
            updateHeaderView(DailyGiveawaySubtab.CURRENT, this.mCurrentDailyGiveawayInfo.getTitle(), this.mCurrentDailyGiveawayInfo.getMessage());
        } else {
            updateHeaderStatusBox(DailyGiveawaySubtab.CURRENT);
        }
    }

    /* access modifiers changed from: 0000 */
    public HashMap<String, String> getGiveawayImpressionInfo() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("GiveawayType", "DailyGiveaway");
        return hashMap;
    }

    private void openUpcomingTab() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DAILY_GIVEAWAY_UPCOMING, getGiveawayImpressionInfo());
        updateUpcomingAdapter();
        this.mListView.setAdapter(this.mUpcomingAdapter);
        this.mUpcomingAdapter.notifyDataSetChanged();
        this.mListView.setDividerHeight(WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.zero_padding));
        if (this.mUpcomingDailyGiveawayInfo != null) {
            updateHeaderView(DailyGiveawaySubtab.UPCOMING, this.mUpcomingDailyGiveawayInfo.getTitle(), this.mUpcomingDailyGiveawayInfo.getMessage());
        } else {
            updateHeaderStatusBox(DailyGiveawaySubtab.UPCOMING);
        }
    }

    private void openInfoTab() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DAILY_GIVEAWAY_INFO, getGiveawayImpressionInfo());
        updateInfoAdapter();
        this.mListView.setAdapter(this.mInfoAdapter);
        this.mInfoAdapter.notifyDataSetChanged();
        this.mListView.setDivider(new ColorDrawable(WishApplication.getInstance().getResources().getColor(R.color.daily_giveaway_cloud_color_background)));
        this.mListView.setDividerHeight(WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.eight_padding));
        if (this.mInfoDailyGiveawayInfo != null) {
            updateHeaderView(DailyGiveawaySubtab.INFO, this.mInfoDailyGiveawayInfo.getTitle(), this.mInfoDailyGiveawayInfo.getMessage());
        } else {
            updateHeaderStatusBox(DailyGiveawaySubtab.INFO);
        }
    }

    /* access modifiers changed from: private */
    public void refreshGridView(boolean z) {
        if (z) {
            this.mLoadingFooter.setVisibilityMode(VisibilityMode.LOADING);
            if (this.mSubtabState == DailyGiveawaySubtab.CURRENT) {
                this.mCurrentAdapter.setDataProvider(new DataProvider() {
                    public ArrayList<WishProduct> getData() {
                        return new ArrayList<>();
                    }
                });
                this.mCurrentAdapter.notifyDataSetChanged();
                this.mListView.setAdapter(this.mCurrentAdapter);
            } else if (this.mSubtabState == DailyGiveawaySubtab.UPCOMING) {
                this.mUpcomingAdapter.setDataProvider(new DailyGiveawayGridAdapter.DataProvider() {
                    public ArrayList<WishProductPair> getData() {
                        return new ArrayList<>();
                    }
                });
                this.mUpcomingAdapter.notifyDataSetChanged();
                this.mListView.setAdapter(this.mUpcomingAdapter);
            } else if (this.mSubtabState == DailyGiveawaySubtab.INFO) {
                this.mInfoAdapter.setDataProvider(new DailyGiveawayInfoAdapter.DataProvider() {
                    public ArrayList<WishInfoCategory> getData() {
                        return new ArrayList<>();
                    }
                });
                this.mInfoAdapter.notifyDataSetChanged();
                this.mListView.setAdapter(this.mInfoAdapter);
            }
            handleReload();
            return;
        }
        this.mLoadingFooter.setVisibilityMode(VisibilityMode.HIDDEN);
    }

    private void updateHeaderView(DailyGiveawaySubtab dailyGiveawaySubtab, String str, String str2) {
        this.mHeaderTitleText.setText(str);
        this.mHeaderSubtitleText.setText(str2);
        updateHeaderStatusBox(dailyGiveawaySubtab);
    }

    private void updateHeaderStatusBox(DailyGiveawaySubtab dailyGiveawaySubtab) {
        if (dailyGiveawaySubtab != DailyGiveawaySubtab.CURRENT || this.mState == DailyGiveawayState.ONGOING) {
            this.mStatusBox.setVisibility(8);
        } else {
            this.mStatusBox.setVisibility(0);
        }
    }

    private void updateCurrentAdapter() {
        if (this.mCurrentAdapter == null) {
            DailyGiveawayListAdapter dailyGiveawayListAdapter = new DailyGiveawayListAdapter(this.mBaseActivity, this.mProductFeedFragment, this, this.mListView, this.mPrefetcher);
            this.mCurrentAdapter = dailyGiveawayListAdapter;
        }
        if (this.mCurrentAdapter.getState() != this.mState) {
            this.mCurrentAdapter.refreshState();
        }
        this.mCurrentAdapter.setDataProvider(new DataProvider() {
            public ArrayList<WishProduct> getData() {
                return DailyGiveawayFeedView.this.mCurrentDailyGiveawayInfo != null ? DailyGiveawayFeedView.this.mCurrentDailyGiveawayInfo.getProducts() : new ArrayList<>();
            }
        });
    }

    private void updateUpcomingAdapter() {
        if (this.mUpcomingAdapter == null) {
            this.mUpcomingAdapter = new DailyGiveawayGridAdapter(this.mBaseActivity, this.mProductFeedFragment, this.mListView, this.mPrefetcher);
        }
        this.mUpcomingAdapter.setDataProvider(new DailyGiveawayGridAdapter.DataProvider() {
            public ArrayList<WishProductPair> getData() {
                return DailyGiveawayFeedView.this.mUpcomingDailyGiveawayInfo != null ? DailyGiveawayFeedView.this.mUpcomingDailyGiveawayInfo.getProducts() : new ArrayList<>();
            }
        });
    }

    private void updateInfoAdapter() {
        if (this.mInfoAdapter == null) {
            this.mInfoAdapter = new DailyGiveawayInfoAdapter(this.mBaseActivity);
        }
        this.mInfoAdapter.setDataProvider(new DailyGiveawayInfoAdapter.DataProvider() {
            public ArrayList<WishInfoCategory> getData() {
                return DailyGiveawayFeedView.this.mInfoDailyGiveawayInfo != null ? DailyGiveawayFeedView.this.mInfoDailyGiveawayInfo.getQuestions() : new ArrayList<>();
            }
        });
    }
}
