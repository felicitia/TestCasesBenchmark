package com.contextlogic.wish.activity.notifications;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.LoadingUiFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishNotification;
import com.contextlogic.wish.api.model.WishTag;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.ui.viewpager.PagerSlidingTabStrip;
import java.util.ArrayList;
import java.util.Iterator;

public class NotificationsFragment<A extends DrawerActivity> extends LoadingUiFragment<A> {
    private ArrayList<WishTag> mCategories;
    private ViewPager mNotificationViewPager;
    private ArrayList<WishNotification> mNotifications;
    private OnPageChangeListener mPageScrollListener;
    private NotificationsPagerAdapter mPagerAdapter;
    private int mSelectedTab;
    private PagerSlidingTabStrip mTabStrip;

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.notifications_fragment;
    }

    public void initializeLoadingContentView(View view) {
        this.mTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.notifications_fragment_viewpager_tabs);
        this.mNotificationViewPager = (ViewPager) view.findViewById(R.id.notifications_fragment_view_page);
        this.mPagerAdapter = new NotificationsPagerAdapter((DrawerActivity) getBaseActivity(), this);
        this.mNotificationViewPager.setAdapter(this.mPagerAdapter);
        this.mNotificationViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                NotificationsFragment.this.handlePageSelected(i);
            }
        });
        initializeValues();
    }

    private void initializeValues() {
        this.mSelectedTab = 0;
        this.mCategories = new ArrayList<>();
        this.mNotifications = new ArrayList<>();
        if (getSavedInstanceState() != null) {
            this.mCategories = StateStoreCache.getInstance().getParcelableList(getSavedInstanceState(), "SavedCategories", WishTag.class);
            this.mSelectedTab = getSavedInstanceState().getInt("SavedStateCurrentIndex");
            if (this.mCategories != null && this.mCategories.size() > 0) {
                handleLoadingCategoriesSuccess(this.mCategories);
                setSelectedTab(this.mSelectedTab);
            }
        }
    }

    public void setSelectedTab(int i) {
        this.mNotificationViewPager.setCurrentItem(i);
        handlePageSelected(i);
    }

    public void handleSaveInstanceState(Bundle bundle) {
        if (getLoadingPageView() != null && getLoadingPageView().isLoadingComplete()) {
            bundle.putString("SavedCategories", StateStoreCache.getInstance().storeParcelableList(this.mCategories));
            bundle.putInt("SavedStateCurrentIndex", this.mSelectedTab);
            this.mPagerAdapter.handleSaveInstanceState(bundle);
        }
    }

    public void loadCategories() {
        withServiceFragment(new ServiceTask<BaseActivity, NotificationsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, NotificationsServiceFragment notificationsServiceFragment) {
                notificationsServiceFragment.loadCategories();
            }
        });
    }

    public int getCurrentIndex() {
        return this.mSelectedTab;
    }

    public void handleLoadingCategoriesSuccess(ArrayList<WishTag> arrayList) {
        this.mCategories = new ArrayList<>();
        this.mCategories.addAll(arrayList);
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.updateCategories(this.mCategories);
        }
        getLoadingPageView().markLoadingComplete();
        updateUi();
    }

    public void handleLoadingNotificationsSuccess(int i, ArrayList<WishNotification> arrayList, int i2) {
        if (this.mPagerAdapter.getNotificationView(i) != null) {
            this.mPagerAdapter.getNotificationView(i).handleLoadingSuccess(arrayList, i2);
        }
    }

    public void updateNotifications(ArrayList<WishNotification> arrayList) {
        this.mNotifications = arrayList;
    }

    public void handleLoadingErrored(int i) {
        getLoadingPageView().markLoadingErrored();
        if (this.mPagerAdapter.getNotificationView(i) != null) {
            this.mPagerAdapter.getNotificationView(i).handleLoadingFailure();
        }
    }

    private void updateUi() {
        this.mTabStrip.setVisibility(0);
        this.mNotificationViewPager.setOffscreenPageLimit(this.mCategories.size() - 1);
        customizeTabStrip();
        this.mTabStrip.setViewPager(this.mNotificationViewPager);
        this.mTabStrip.setOnPageChangeListener(this.mPageScrollListener);
        refreshTabStripFontColors();
    }

    public void markNotificationViewed(final WishNotification wishNotification) {
        withServiceFragment(new ServiceTask<BaseActivity, NotificationsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, NotificationsServiceFragment notificationsServiceFragment) {
                notificationsServiceFragment.markNotificationViewed(wishNotification.getNotificationNumber(), wishNotification.getBucketNumber());
            }
        });
    }

    private void customizeTabStrip() {
        if (getBaseActivity() != null && ((DrawerActivity) getBaseActivity()).getActionBarManager() != null) {
            ((DrawerActivity) getBaseActivity()).getActionBarManager().stylizeTabStrip(this.mTabStrip);
        }
    }

    private void refreshTabStripFontColors() {
        if (getBaseActivity() != null && ((DrawerActivity) getBaseActivity()).getActionBarManager() != null) {
            ((DrawerActivity) getBaseActivity()).getActionBarManager().refreshTabStripFontColors(this.mTabStrip, this.mNotificationViewPager.getCurrentItem());
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.onDestroy();
        }
    }

    public boolean hasItems() {
        return this.mCategories != null && this.mCategories.size() > 0;
    }

    public void handleReload() {
        if (!getLoadingPageView().isLoadingComplete()) {
            loadCategories();
            return;
        }
        getLoadingPageView().markLoadingComplete();
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.notifyDataSetChanged();
        }
    }

    public void handleResume() {
        super.handleResume();
        if (!getLoadingPageView().isLoadingComplete()) {
            handleReload();
        }
        if (this.mPagerAdapter != null && this.mPagerAdapter.getNotificationView(this.mSelectedTab) != null) {
            this.mPagerAdapter.getNotificationView(this.mSelectedTab).handleResume();
        }
    }

    public void restoreImages() {
        if (this.mPagerAdapter != null) {
            Iterator it = this.mPagerAdapter.getNotificationsViews().iterator();
            while (it.hasNext()) {
                ((NotificationsView) it.next()).restoreImages();
            }
        }
    }

    public void releaseImages() {
        if (this.mPagerAdapter != null) {
            Iterator it = this.mPagerAdapter.getNotificationsViews().iterator();
            while (it.hasNext()) {
                ((NotificationsView) it.next()).releaseImages();
            }
        }
    }

    public void onPause() {
        super.onPause();
        if (this.mPagerAdapter != null) {
            Iterator it = this.mPagerAdapter.getNotificationsViews().iterator();
            while (it.hasNext()) {
                ((NotificationsView) it.next()).onPause();
            }
        }
    }

    public void handlePageSelected(int i) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NOTIFICATION_V2_CHANGE_TAB);
        this.mSelectedTab = i;
        if (this.mPagerAdapter != null) {
            NotificationsView notificationView = this.mPagerAdapter.getNotificationView(i);
            if (notificationView != null) {
                notificationView.loadFirstPage();
            }
        }
        refreshTabStripFontColors();
    }

    public ArrayList<WishTag> getCategories() {
        return this.mCategories;
    }

    public String getPagedDataSavedInstanceStateKey(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("SavedStateData_");
        sb.append(i);
        return sb.toString();
    }

    public Bundle getSavedInstanceState(int i) {
        if (getSavedInstanceState() != null) {
            return getSavedInstanceState().getBundle(getPagedDataSavedInstanceStateKey(i));
        }
        return null;
    }

    public void clearSavedInstanceState(int i) {
        if (getSavedInstanceState() != null) {
            getSavedInstanceState().remove(getPagedDataSavedInstanceStateKey(i));
        }
    }
}
