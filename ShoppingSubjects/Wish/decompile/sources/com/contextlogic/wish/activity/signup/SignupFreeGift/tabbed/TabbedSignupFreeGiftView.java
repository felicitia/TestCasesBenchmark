package com.contextlogic.wish.activity.signup.SignupFreeGift.tabbed;

import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.signup.SignupFreeGift.SignupFreeGiftActivity;
import com.contextlogic.wish.activity.signup.SignupFreeGift.SignupFreeGiftBaseView;
import com.contextlogic.wish.activity.signup.SignupFreeGift.SignupFreeGiftFragment;
import com.contextlogic.wish.activity.signup.SignupFreeGift.tabbed.FreeGiftsPagerAdapter.FreeGiftsSection;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishGenderedSignupFreeGifts;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishSignupFreeGifts;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.viewpager.BaseTabStripInterface;
import com.contextlogic.wish.ui.viewpager.PagerSlidingTabStrip;
import com.contextlogic.wish.ui.viewpager.SafeViewPager;
import com.contextlogic.wish.util.FontUtil;
import com.crashlytics.android.Crashlytics;
import java.util.ArrayList;
import java.util.List;

public class TabbedSignupFreeGiftView extends SignupFreeGiftBaseView implements BaseTabStripInterface {
    private int mBannerHeight;
    private SignupFreeGiftHeaderView mBannerView;
    private int mCurrentOffset;
    private final SignupFreeGiftFragment mFragment;
    /* access modifiers changed from: private */
    public SafeViewPager mFreeGiftPager;
    private WishGenderedSignupFreeGifts mFreeGifts;
    private ImageHttpPrefetcher mImagePrefetcher;
    /* access modifiers changed from: private */
    public OnPageChangeListener mPageScrollListener;
    private FreeGiftsPagerAdapter mPagerAdapter;
    private int mRestoredIndex;
    private int mTabBarHeight;
    /* access modifiers changed from: private */
    public PagerSlidingTabStrip mTabStrip;
    private View mTabStripContainer;
    /* access modifiers changed from: private */
    public View mTabStripShadow;

    public TabbedSignupFreeGiftView(SignupFreeGiftActivity signupFreeGiftActivity, SignupFreeGiftFragment signupFreeGiftFragment, Bundle bundle) {
        super(signupFreeGiftFragment, signupFreeGiftActivity, bundle);
        this.mFragment = signupFreeGiftFragment;
    }

    /* access modifiers changed from: protected */
    public void initializeUi(Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.signup_free_gift_tabbed_view, this);
        new LayoutParams(-1, (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics()));
        this.mTabStripContainer = inflate.findViewById(R.id.fragment_signup_free_gift_feed_viewpager_tab_container);
        this.mTabStripShadow = inflate.findViewById(R.id.fragment_signup_free_gift_feed_shadow);
        this.mTabStrip = (PagerSlidingTabStrip) inflate.findViewById(R.id.fragment_signup_free_gift_feed_viewpager_tabs);
        this.mTabStrip.setAllCaps(false);
        this.mTabStrip.setTabPaddingLeftRight(getResources().getDimensionPixelSize(R.dimen.sixteen_padding));
        this.mTabStrip.setShouldExpand(true);
        this.mBannerView = (SignupFreeGiftHeaderView) inflate.findViewById(R.id.fragment_signup_free_gift_feed_banner);
        this.mFreeGiftPager = (SafeViewPager) inflate.findViewById(R.id.fragment_signup_free_gift_feed_viewpager);
        this.mFreeGiftPager.setOffscreenPageLimit(2);
        this.mPageScrollListener = new OnPageChangeListener() {
            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                TabbedSignupFreeGiftView.this.handlePageSelected(i);
            }

            public void onPageScrollStateChanged(int i) {
                if (i == 0) {
                    TabbedSignupFreeGiftView.this.onPagerScrollSettled();
                } else {
                    TabbedSignupFreeGiftView.this.onPagerScrollUnsettled();
                }
            }
        };
        this.mRestoredIndex = -1;
        if (bundle != null) {
            this.mRestoredIndex = bundle.getInt("SavedStateTabIndex");
        }
        initializeValues(bundle);
    }

    private void initializeValues(Bundle bundle) {
        if (bundle != null) {
            this.mFreeGifts = (WishGenderedSignupFreeGifts) StateStoreCache.getInstance().getParcelable(bundle, "SavedStateData", WishSignupFreeGifts.class);
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        if (this.mFreeGifts != null) {
            bundle.putString("SavedStateData", StateStoreCache.getInstance().storeParcelable(this.mFreeGifts));
        }
    }

    public void setupFreeGifts(WishSignupFreeGifts wishSignupFreeGifts) {
        this.mImagePrefetcher = new ImageHttpPrefetcher();
        if (!(wishSignupFreeGifts instanceof WishGenderedSignupFreeGifts)) {
            Crashlytics.logException(new IllegalArgumentException("Expected WishGenderedSignupFreeGifts"));
            this.mFragment.handleClose();
            return;
        }
        WishGenderedSignupFreeGifts wishGenderedSignupFreeGifts = (WishGenderedSignupFreeGifts) wishSignupFreeGifts;
        this.mFreeGifts = wishGenderedSignupFreeGifts;
        this.mBannerHeight = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.login_fragment_free_gift_header_height);
        if (ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            this.mBannerHeight = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.login_fragment_free_gift_header_height_redesign);
        }
        this.mTabBarHeight = this.mBannerHeight + this.mFragment.getResources().getDimensionPixelSize(R.dimen.login_fragment_free_gift_header_tab_height);
        this.mBannerView.setup(this.mFragment, wishSignupFreeGifts);
        this.mPagerAdapter = new FreeGiftsPagerAdapter(this.mFragment, this, this.mFreeGiftPager, this.mImagePrefetcher);
        this.mFreeGiftPager.setAdapter(this.mPagerAdapter);
        initializeTabs();
        String defaultGenderTab = wishGenderedSignupFreeGifts.getDefaultGenderTab();
        switchToPosition(defaultGenderTab.equals("male") ? 1 : 0, false);
        WishAnalyticsLogger.trackEvent(defaultGenderTab.equals("male") ? WishAnalyticsEvent.IMPRESSION_GENDER_FREE_GIFT_MALE : WishAnalyticsEvent.IMPRESSION_GENDER_FREE_GIFT_FEMALE);
    }

    public void refreshUi() {
        if (this.mFragment.getFreeGifts() != null) {
            setupFreeGifts(this.mFragment.getFreeGifts());
        }
    }

    public void releaseImages() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.releaseImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.cancelPrefetching();
        }
    }

    public void restoreImages() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.restoreImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.resumePrefetching();
        }
    }

    public void recycle() {
        releaseImages();
    }

    public boolean onBackPressed() {
        return !this.mFragment.startedFromNotification();
    }

    public List<WishProduct> getFemaleFreeGifts() {
        return this.mFreeGifts != null ? this.mFreeGifts.getFemaleFreeGifts() : new ArrayList();
    }

    public List<WishProduct> getMaleFreeGifts() {
        return this.mFreeGifts != null ? this.mFreeGifts.getMaleFreeGifts() : new ArrayList();
    }

    public void showTabArea(boolean z) {
        scrollOffset();
    }

    public void hideTabArea(boolean z) {
        scrollOffset();
    }

    public void scrollOffset() {
        int i = this.mCurrentOffset;
        this.mCurrentOffset = getTabAreaOffset();
        if (this.mCurrentOffset - i == 0) {
            return;
        }
        if (this.mCurrentOffset >= 0) {
            this.mPagerAdapter.scrollOffset(Math.max(0 - i, 0), getCurrentIndex());
            this.mCurrentOffset = 0;
        } else if (this.mCurrentOffset <= getBannerOffset()) {
            this.mPagerAdapter.scrollOffset(Math.min(getBannerOffset() - i, 0), getCurrentIndex());
            this.mCurrentOffset = getBannerOffset();
        } else {
            this.mPagerAdapter.scrollOffset(this.mCurrentOffset - i, getCurrentIndex());
        }
    }

    public int getTabAreaOffset() {
        if (this.mTabStripContainer != null) {
            return ((RelativeLayout.LayoutParams) this.mTabStripContainer.getLayoutParams()).topMargin;
        }
        return 0;
    }

    public void setTabAreaOffset(int i) {
        if (this.mTabStripContainer != null) {
            this.mTabStripContainer.clearAnimation();
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mTabStripContainer.getLayoutParams();
            layoutParams.topMargin = Math.min(Math.max(i, getBannerOffset()), 0);
            this.mTabStripContainer.setLayoutParams(layoutParams);
        }
    }

    public int getBannerOffset() {
        return this.mBannerHeight * -1;
    }

    public int getTabAreaSize() {
        return this.mTabBarHeight;
    }

    public int getCurrentIndex() {
        return this.mFreeGiftPager.getCurrentItem();
    }

    /* access modifiers changed from: private */
    public void onPagerScrollUnsettled() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.onPagerScrollUnsettled();
        }
    }

    /* access modifiers changed from: private */
    public void onPagerScrollSettled() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.onPagerScrollSettled();
        }
    }

    /* access modifiers changed from: private */
    public void goToRestoredTab() {
        if (this.mPagerAdapter != null) {
            switchToPosition(this.mRestoredIndex, false);
        }
    }

    public void switchToPosition(int i, boolean z) {
        if (i >= 0 && i < this.mPagerAdapter.getCount()) {
            this.mFreeGiftPager.setCurrentItem(i, z);
        }
    }

    public void initializeTabs() {
        this.mFragment.withActivity(new ActivityTask<SignupFreeGiftActivity>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                TabbedSignupFreeGiftView.this.customizeTabStrip();
                TabbedSignupFreeGiftView.this.mTabStrip.setViewPager(TabbedSignupFreeGiftView.this.mFreeGiftPager);
                TabbedSignupFreeGiftView.this.mTabStrip.setOnPageChangeListener(TabbedSignupFreeGiftView.this.mPageScrollListener);
                TabbedSignupFreeGiftView.this.refreshTabStripFontColors();
                TabbedSignupFreeGiftView.this.goToRestoredTab();
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshTabStripFontColors() {
        LinearLayout linearLayout = (LinearLayout) this.mTabStrip.getChildAt(0);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i);
            if (i == this.mFreeGiftPager.getCurrentItem()) {
                textView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.gray1));
                textView.setTypeface(FontUtil.getTypefaceForStyle(1), 1);
            } else {
                textView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.gray4));
                textView.setTypeface(FontUtil.getTypefaceForStyle(0), 0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void customizeTabStrip() {
        this.mFragment.withActivity(new ActivityTask<SignupFreeGiftActivity>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                int dimensionPixelOffset = signupFreeGiftActivity.getResources().getDimensionPixelOffset(R.dimen.tab_strip_indicator_height_thin);
                int dimensionPixelOffset2 = signupFreeGiftActivity.getResources().getDimensionPixelOffset(R.dimen.text_size_subtitle);
                TabbedSignupFreeGiftView.this.mTabStrip.setBackgroundResource(R.color.gray7);
                TabbedSignupFreeGiftView.this.mTabStrip.setDividerColorResource(R.color.gray7);
                TabbedSignupFreeGiftView.this.mTabStrip.setUnderlineHeight(signupFreeGiftActivity.getResources().getDimensionPixelOffset(R.dimen.divider));
                TabbedSignupFreeGiftView.this.mTabStripShadow.setVisibility(8);
                TabbedSignupFreeGiftView.this.mTabStrip.setIndicatorHeight(dimensionPixelOffset);
                TabbedSignupFreeGiftView.this.mTabStrip.setTextSize(dimensionPixelOffset2);
                TabbedSignupFreeGiftView.this.mTabStrip.setUnderlineColorResource(R.color.cool_gray4);
                TabbedSignupFreeGiftView.this.mTabStrip.setIndicatorColorResource(R.color.main_primary);
                TabbedSignupFreeGiftView.this.mTabStrip.setupRefreshColors(R.color.gray1, R.color.gray4);
            }
        });
    }

    /* access modifiers changed from: private */
    public void handlePageSelected(int i) {
        refreshTabStripFontColors();
        this.mCurrentOffset = getTabAreaOffset();
        this.mPagerAdapter.scrollOffset(-1, -1);
        FreeGiftsSection fromInt = FreeGiftsSection.fromInt(i);
        if (fromInt != null) {
            switch (fromInt) {
                case MALE:
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_GENDER_FREE_GIFT_MALE);
                    break;
                case FEMALE:
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_GENDER_FREE_GIFT_FEMALE);
                    break;
            }
        }
    }
}
