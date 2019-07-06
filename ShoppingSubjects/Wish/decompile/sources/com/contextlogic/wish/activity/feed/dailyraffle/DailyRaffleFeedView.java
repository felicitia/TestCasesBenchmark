package com.contextlogic.wish.activity.feed.dailyraffle;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.BaseProductFeedAdapter.DataProvider;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayFeedView.DailyGiveawayState;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayFeedView.DailyGiveawaySubtab;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayFeedViewInterface;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayGridAdapter;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayGridAdapter.WishProductPair;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayInfoAdapter;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCurrentDailyGiveawayInfo;
import com.contextlogic.wish.api.model.WishInfoDailyGiveawayInfo;
import com.contextlogic.wish.api.model.WishInfoDailyGiveawayInfo.WishInfoCategory;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishUpcomingDailyGiveawayInfo;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.bottomnavigation.BottomNavigationHelper;
import com.contextlogic.wish.ui.bottomnavigation.BottomNavigationInterface;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.listview.ListeningListView;
import com.contextlogic.wish.ui.listview.ListeningListView.ScrollViewListener;
import com.contextlogic.wish.ui.loading.LoadingFooterView;
import com.contextlogic.wish.ui.loading.LoadingFooterView.VisibilityMode;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.timer.BaseCountdownTimerView.DoneCallback;
import com.contextlogic.wish.ui.timer.CountdownTimerView;
import com.contextlogic.wish.util.PreferenceUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DailyRaffleFeedView extends LoadingPageView implements DailyGiveawayFeedViewInterface, DailyRaffleTabSelectedCallback, ImageRestorable, LoadingPageManager {
    private DrawerActivity mBaseActivity;
    /* access modifiers changed from: private */
    public BottomNavigationHelper mBottomNavigationHelper;
    private DailyRaffleListAdapter mCurrentAdapter;
    /* access modifiers changed from: private */
    public WishCurrentDailyGiveawayInfo mCurrentDailyGiveawayInfo;
    private ThemedTextView mHeaderMessageText;
    private ThemedTextView mHeaderSubtitleText;
    private ThemedTextView mHeaderTitleText;
    private DailyGiveawayInfoAdapter mInfoAdapter;
    /* access modifiers changed from: private */
    public WishInfoDailyGiveawayInfo mInfoDailyGiveawayInfo;
    private boolean mIsLoading = true;
    private ListeningListView mListView;
    private LoadingFooterView mLoadingFooter;
    private ImageHttpPrefetcher mPrefetcher = new ImageHttpPrefetcher();
    private ProductFeedFragment mProductFeedFragment;
    /* access modifiers changed from: private */
    public TextView mRaffleStatusBoxText;
    private TextView mRaffleStatusBoxText2;
    /* access modifiers changed from: private */
    public CountdownTimerView mRaffleStatusCounter;
    private DailyGiveawayState mState;
    private AutoReleasableImageView mStatusBoxIcon;
    private DailyGiveawaySubtab mSubtabState;
    private DailyGiveawayGridAdapter mUpcomingAdapter;
    /* access modifiers changed from: private */
    public WishUpcomingDailyGiveawayInfo mUpcomingDailyGiveawayInfo;
    private DailyRaffleWinState mWinState;

    public enum DailyRaffleWinState {
        NOT_DECIDED,
        LOSE,
        DISCOUNT_WON,
        FREE;

        public static DailyRaffleWinState fromInteger(int i) {
            switch (i) {
                case 0:
                    return NOT_DECIDED;
                case 1:
                    return LOSE;
                case 2:
                    return DISCOUNT_WON;
                case 3:
                    return FREE;
                default:
                    return NOT_DECIDED;
            }
        }
    }

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.daily_raffle_product_feed;
    }

    public DailyRaffleFeedView(DrawerActivity drawerActivity, ProductFeedFragment productFeedFragment) {
        super(drawerActivity);
        this.mBaseActivity = drawerActivity;
        this.mProductFeedFragment = productFeedFragment;
        setLoadingPageManager(this);
    }

    public void initializeLoadingContentView(View view) {
        this.mListView = (ListeningListView) view.findViewById(R.id.product_feed_gridview);
        this.mSubtabState = DailyGiveawaySubtab.CURRENT;
        setHideErrors(false);
        init();
        this.mProductFeedFragment.loadCurrentDailyGiveaway();
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation() && (this.mProductFeedFragment instanceof BottomNavigationInterface)) {
            this.mBottomNavigationHelper = new BottomNavigationHelper(this.mProductFeedFragment);
            this.mListView.setScrollViewListener(new ScrollViewListener() {
                public void onScrollChanged(int i, int i2) {
                    DailyRaffleFeedView.this.mBottomNavigationHelper.handleScrollChanged(i2);
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

    public DailyRaffleWinState getWinState() {
        return this.mWinState;
    }

    public int getPercentageOff() {
        if (this.mCurrentDailyGiveawayInfo != null) {
            return this.mCurrentDailyGiveawayInfo.getPercentageOff();
        }
        return -1;
    }

    private void init() {
        initAdapters();
        initGrid();
    }

    private void initGrid() {
        View inflate = inflate(this.mBaseActivity, R.layout.daily_raffle_header, null);
        this.mHeaderTitleText = (ThemedTextView) inflate.findViewById(R.id.daily_raffle_header_title);
        this.mHeaderSubtitleText = (ThemedTextView) inflate.findViewById(R.id.daily_raffle_header_subtitle);
        ((LinearLayout) inflate.findViewById(R.id.daily_raffle_header_status_box)).setVisibility(0);
        this.mStatusBoxIcon = (AutoReleasableImageView) inflate.findViewById(R.id.daily_raffle_header_status_giftbox_icon);
        this.mRaffleStatusBoxText = (TextView) inflate.findViewById(R.id.daily_raffle_status_box_text_1);
        this.mRaffleStatusBoxText2 = (TextView) inflate.findViewById(R.id.daily_raffle_status_box_text_2);
        this.mRaffleStatusCounter = (CountdownTimerView) inflate.findViewById(R.id.daily_raffle_status_box_timer);
        this.mHeaderMessageText = (ThemedTextView) inflate.findViewById(R.id.daily_raffle_header_message);
        ((DailyRaffleTabStrip) inflate.findViewById(R.id.daily_raffle_new_tab)).setup(this);
        this.mListView.addHeaderView(inflate);
        this.mLoadingFooter = new LoadingFooterView(this.mBaseActivity);
        this.mLoadingFooter.setReserveSpaceWhenHidden(false);
        this.mListView.addFooterView(this.mLoadingFooter);
    }

    public void onTabSelected(int i) {
        this.mSubtabState = DailyGiveawaySubtab.fromInteger(i);
        refreshGridView(true);
        if (this.mSubtabState == DailyGiveawaySubtab.CURRENT) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_CURRENT, getGiveawayImpressionInfo());
        } else if (this.mSubtabState == DailyGiveawaySubtab.UPCOMING) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_UPCOMING, getGiveawayImpressionInfo());
        } else if (this.mSubtabState == DailyGiveawaySubtab.INFO) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_INFO, getGiveawayImpressionInfo());
        }
    }

    private void initAdapters() {
        DailyRaffleListAdapter dailyRaffleListAdapter = new DailyRaffleListAdapter(this.mBaseActivity, this.mProductFeedFragment, this, this.mListView, this.mPrefetcher);
        this.mCurrentAdapter = dailyRaffleListAdapter;
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
            this.mWinState = this.mCurrentDailyGiveawayInfo.getWinState();
            if (this.mState == DailyGiveawayState.ONGOING) {
                PreferenceUtil.setLong("LastDailyGiveawayFeedTime", System.currentTimeMillis());
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
        this.mListView.setDividerHeight(getContext().getResources().getDimensionPixelOffset(R.dimen.sixteen_padding));
        if (this.mCurrentDailyGiveawayInfo != null) {
            updateHeaderView(this.mCurrentDailyGiveawayInfo.getTitle(), this.mCurrentDailyGiveawayInfo.getMessage(), this.mCurrentDailyGiveawayInfo.getSubtitle());
            this.mCurrentAdapter.setSelected(this.mCurrentDailyGiveawayInfo.getSelectedItemCid());
            HashMap hashMap = new HashMap();
            hashMap.put("raffle_state", this.mState == null ? "No state" : this.mState.name());
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DAILY_RAFFLE_STATE, hashMap);
            if (this.mState == DailyGiveawayState.ONGOING || this.mState == DailyGiveawayState.ALREADY_CLAIMED) {
                this.mRaffleStatusBoxText.setText(this.mCurrentDailyGiveawayInfo.getRaffleCloseMessage());
                this.mRaffleStatusCounter.setVisibility(0);
                this.mRaffleStatusCounter.setDigitPadding(0).disableExpiredText().setColonPadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.four_padding)).setup(this.mCurrentDailyGiveawayInfo.getRaffleTimeEnd(), getResources().getDimensionPixelSize(R.dimen.add_to_cart_offer_group_buy_row_timer_height), getResources().getColor(R.color.transparent), getResources().getColor(R.color.main_primary), getResources().getColor(R.color.main_primary), CountdownTimerView.NO_BACKGROUND, false, true, new DoneCallback() {
                    public void onCountdownEnd() {
                        DailyRaffleFeedView.this.setRaffleClosedText();
                        DailyRaffleFeedView.this.reload();
                    }
                });
                this.mRaffleStatusCounter.startTimer();
                this.mStatusBoxIcon.setVisibility(0);
            } else if (this.mState == DailyGiveawayState.ENDED) {
                setRaffleClosedText();
                this.mStatusBoxIcon.setVisibility(8);
            } else if (this.mState == DailyGiveawayState.WINNERS_PICKED || this.mState == DailyGiveawayState.RAFFLE_PRIZE_CLAIMED || this.mState == DailyGiveawayState.RAFFLE_PRIZE_CHECKED_OUT || this.mState == DailyGiveawayState.RAFFLE_PRIZE_CLAIM_ENDED) {
                this.mRaffleStatusBoxText.setText(this.mCurrentDailyGiveawayInfo.getStatusTitle());
                this.mRaffleStatusBoxText2.setVisibility(8);
                this.mRaffleStatusCounter.setVisibility(8);
                this.mStatusBoxIcon.setVisibility(8);
            } else if (this.mState == DailyGiveawayState.NOT_STARTED || this.mState == DailyGiveawayState.STARTING_SOON) {
                this.mRaffleStatusBoxText.setText(this.mCurrentDailyGiveawayInfo.getRaffleStatusMessage());
                this.mRaffleStatusBoxText2.setVisibility(8);
                this.mRaffleStatusCounter.setVisibility(8);
                this.mStatusBoxIcon.setVisibility(0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void setRaffleClosedText() {
        this.mRaffleStatusBoxText.setText(this.mCurrentDailyGiveawayInfo.getWinnersBeingChosenMessage2());
        this.mRaffleStatusBoxText2.setText(this.mCurrentDailyGiveawayInfo.getWinnersBeingChosenMessage1());
        this.mRaffleStatusBoxText2.setVisibility(0);
        this.mRaffleStatusCounter.setVisibility(0);
        this.mRaffleStatusCounter.setDigitPadding(0).disableExpiredText().setColonPadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.four_padding)).setup(this.mCurrentDailyGiveawayInfo.getRaffleChooseWinnersTime(), getResources().getDimensionPixelSize(R.dimen.add_to_cart_offer_group_buy_row_timer_height), getResources().getColor(R.color.transparent), getResources().getColor(R.color.main_primary), getResources().getColor(R.color.main_primary), CountdownTimerView.NO_BACKGROUND, false, true, new DoneCallback() {
            public void onCountdownEnd() {
                DailyRaffleFeedView.this.mRaffleStatusBoxText.setText(DailyRaffleFeedView.this.mCurrentDailyGiveawayInfo.getStatusTitle());
                DailyRaffleFeedView.this.mRaffleStatusCounter.setVisibility(8);
                DailyRaffleFeedView.this.reload();
            }
        });
        this.mRaffleStatusCounter.startTimer();
    }

    /* access modifiers changed from: 0000 */
    public HashMap<String, String> getGiveawayImpressionInfo() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("GiveawayType", "DailyRaffle");
        return hashMap;
    }

    private void openUpcomingTab() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DAILY_GIVEAWAY_UPCOMING, getGiveawayImpressionInfo());
        updateUpcomingAdapter();
        this.mListView.setAdapter(this.mUpcomingAdapter);
        this.mUpcomingAdapter.notifyDataSetChanged();
        this.mListView.setDividerHeight(WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.zero_padding));
        if (this.mCurrentDailyGiveawayInfo != null) {
            updateHeaderView(this.mCurrentDailyGiveawayInfo.getTitle(), this.mCurrentDailyGiveawayInfo.getMessage(), this.mCurrentDailyGiveawayInfo.getSubtitle());
        }
    }

    private void openInfoTab() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DAILY_GIVEAWAY_INFO, getGiveawayImpressionInfo());
        updateInfoAdapter();
        this.mListView.setAdapter(this.mInfoAdapter);
        this.mInfoAdapter.notifyDataSetChanged();
        this.mListView.setDividerHeight(WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.sixteen_padding));
        if (this.mCurrentDailyGiveawayInfo != null) {
            updateHeaderView(this.mCurrentDailyGiveawayInfo.getTitle(), this.mCurrentDailyGiveawayInfo.getMessage(), this.mCurrentDailyGiveawayInfo.getSubtitle());
        }
    }

    private void refreshGridView(boolean z) {
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

    private void updateHeaderView(String str, String str2, String str3) {
        this.mHeaderTitleText.setText(str);
        this.mHeaderMessageText.setText(str2);
        if (!TextUtils.isEmpty(str3)) {
            this.mHeaderSubtitleText.setVisibility(0);
            this.mHeaderSubtitleText.setText(str3);
        }
    }

    private void updateCurrentAdapter() {
        if (this.mCurrentAdapter == null) {
            DailyRaffleListAdapter dailyRaffleListAdapter = new DailyRaffleListAdapter(this.mBaseActivity, this.mProductFeedFragment, this, this.mListView, this.mPrefetcher);
            this.mCurrentAdapter = dailyRaffleListAdapter;
        }
        if (!(this.mCurrentAdapter.getState() == this.mState && this.mCurrentAdapter.getWinState() == this.mWinState)) {
            this.mCurrentAdapter.refreshState();
        }
        this.mCurrentAdapter.setDataProvider(new DataProvider() {
            public ArrayList<WishProduct> getData() {
                return DailyRaffleFeedView.this.mCurrentDailyGiveawayInfo != null ? DailyRaffleFeedView.this.mCurrentDailyGiveawayInfo.getProducts() : new ArrayList<>();
            }
        });
    }

    private void updateUpcomingAdapter() {
        if (this.mUpcomingAdapter == null) {
            this.mUpcomingAdapter = new DailyGiveawayGridAdapter(this.mBaseActivity, this.mProductFeedFragment, this.mListView, this.mPrefetcher);
        }
        this.mUpcomingAdapter.setDataProvider(new DailyGiveawayGridAdapter.DataProvider() {
            public ArrayList<WishProductPair> getData() {
                return DailyRaffleFeedView.this.mUpcomingDailyGiveawayInfo != null ? DailyRaffleFeedView.this.mUpcomingDailyGiveawayInfo.getProducts() : new ArrayList<>();
            }
        });
    }

    private void updateInfoAdapter() {
        if (this.mInfoAdapter == null) {
            this.mInfoAdapter = new DailyGiveawayInfoAdapter(this.mBaseActivity);
        }
        this.mInfoAdapter.setDataProvider(new DailyGiveawayInfoAdapter.DataProvider() {
            public ArrayList<WishInfoCategory> getData() {
                return DailyRaffleFeedView.this.mInfoDailyGiveawayInfo != null ? DailyRaffleFeedView.this.mInfoDailyGiveawayInfo.getQuestions() : new ArrayList<>();
            }
        });
    }

    public HashMap<String, List<WishUser>> getWinnerDict() {
        if (this.mCurrentDailyGiveawayInfo != null) {
            return this.mCurrentDailyGiveawayInfo.getWinnerDict();
        }
        return null;
    }

    public Date getTimeLeftToClaimPrize() {
        if (this.mCurrentDailyGiveawayInfo != null) {
            return this.mCurrentDailyGiveawayInfo.getTimeLeftToClaim();
        }
        return null;
    }

    public String getRaffleBannerText() {
        if (this.mCurrentDailyGiveawayInfo != null) {
            return this.mCurrentDailyGiveawayInfo.getRaffleBannerText();
        }
        return null;
    }
}
