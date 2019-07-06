package com.contextlogic.wish.activity.commercecash;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCommerceCashHelpInfo;
import com.contextlogic.wish.api.model.WishCommerceCashHistory;
import com.contextlogic.wish.api.model.WishCommerceCashSpecs;
import com.contextlogic.wish.api.model.WishCommerceCashUserInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.viewpager.SafeViewPager;
import java.util.ArrayList;
import java.util.Iterator;

public class CommerceCashPagerAdapter extends PagerAdapter {
    private boolean mAddCashTracked;
    private CommerceCashAddCashView mAddCashView;
    private final CommerceCashFragment mCommerceCashFragment;
    private final DrawerActivity mDrawerActivity;
    private boolean mHelpInfoTracked;
    private CommerceCashHelpView mHelpView;
    private boolean mHistoryTracked;
    private CommerceCashHistoryView mHistoryView;
    private ArrayList<CommerceCashPagerView> mPagerViews = new ArrayList<>();
    private ArrayList<CommerceCashSection> mSections = new ArrayList<>();
    private final SafeViewPager mViewPager;

    private enum CommerceCashSection {
        ADD_CASH,
        INFO,
        HISTORY
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public CommerceCashPagerAdapter(DrawerActivity drawerActivity, CommerceCashFragment commerceCashFragment, SafeViewPager safeViewPager) {
        this.mDrawerActivity = drawerActivity;
        this.mCommerceCashFragment = commerceCashFragment;
        this.mViewPager = safeViewPager;
        this.mViewPager.setOffscreenPageLimit(3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0052, code lost:
        r2 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object instantiateItem(android.view.ViewGroup r5, int r6) {
        /*
            r4 = this;
            java.util.ArrayList<com.contextlogic.wish.activity.commercecash.CommerceCashPagerAdapter$CommerceCashSection> r0 = r4.mSections
            java.lang.Object r0 = r0.get(r6)
            com.contextlogic.wish.activity.commercecash.CommerceCashPagerAdapter$CommerceCashSection r0 = (com.contextlogic.wish.activity.commercecash.CommerceCashPagerAdapter.CommerceCashSection) r0
            int[] r1 = com.contextlogic.wish.activity.commercecash.CommerceCashPagerAdapter.AnonymousClass1.$SwitchMap$com$contextlogic$wish$activity$commercecash$CommerceCashPagerAdapter$CommerceCashSection
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 1
            r2 = 0
            switch(r0) {
                case 1: goto L_0x003f;
                case 2: goto L_0x002b;
                case 3: goto L_0x0017;
                default: goto L_0x0015;
            }
        L_0x0015:
            r0 = 0
            goto L_0x0053
        L_0x0017:
            com.contextlogic.wish.activity.commercecash.CommerceCashHistoryView r0 = r4.mHistoryView
            if (r0 != 0) goto L_0x0028
            com.contextlogic.wish.activity.commercecash.CommerceCashHistoryView r0 = new com.contextlogic.wish.activity.commercecash.CommerceCashHistoryView
            com.contextlogic.wish.activity.DrawerActivity r1 = r4.mDrawerActivity
            r0.<init>(r1)
            r1 = r0
            com.contextlogic.wish.activity.commercecash.CommerceCashHistoryView r1 = (com.contextlogic.wish.activity.commercecash.CommerceCashHistoryView) r1
            r4.mHistoryView = r1
            goto L_0x0053
        L_0x0028:
            com.contextlogic.wish.activity.commercecash.CommerceCashHistoryView r0 = r4.mHistoryView
            goto L_0x0052
        L_0x002b:
            com.contextlogic.wish.activity.commercecash.CommerceCashHelpView r0 = r4.mHelpView
            if (r0 != 0) goto L_0x003c
            com.contextlogic.wish.activity.commercecash.CommerceCashHelpView r0 = new com.contextlogic.wish.activity.commercecash.CommerceCashHelpView
            com.contextlogic.wish.activity.DrawerActivity r1 = r4.mDrawerActivity
            r0.<init>(r1)
            r1 = r0
            com.contextlogic.wish.activity.commercecash.CommerceCashHelpView r1 = (com.contextlogic.wish.activity.commercecash.CommerceCashHelpView) r1
            r4.mHelpView = r1
            goto L_0x0053
        L_0x003c:
            com.contextlogic.wish.activity.commercecash.CommerceCashHelpView r0 = r4.mHelpView
            goto L_0x0052
        L_0x003f:
            com.contextlogic.wish.activity.commercecash.CommerceCashAddCashView r0 = r4.mAddCashView
            if (r0 != 0) goto L_0x0050
            com.contextlogic.wish.activity.commercecash.CommerceCashAddCashView r0 = new com.contextlogic.wish.activity.commercecash.CommerceCashAddCashView
            com.contextlogic.wish.activity.DrawerActivity r1 = r4.mDrawerActivity
            r0.<init>(r1)
            r1 = r0
            com.contextlogic.wish.activity.commercecash.CommerceCashAddCashView r1 = (com.contextlogic.wish.activity.commercecash.CommerceCashAddCashView) r1
            r4.mAddCashView = r1
            goto L_0x0053
        L_0x0050:
            com.contextlogic.wish.activity.commercecash.CommerceCashAddCashView r0 = r4.mAddCashView
        L_0x0052:
            r2 = 1
        L_0x0053:
            com.contextlogic.wish.activity.commercecash.CommerceCashFragment r1 = r4.mCommerceCashFragment
            r0.setup(r6, r1)
            android.view.ViewGroup$LayoutParams r1 = new android.view.ViewGroup$LayoutParams
            r3 = -1
            r1.<init>(r3, r3)
            r0.setLayoutParams(r1)
            if (r2 != 0) goto L_0x0068
            java.util.ArrayList<com.contextlogic.wish.activity.commercecash.CommerceCashPagerView> r1 = r4.mPagerViews
            r1.add(r0)
        L_0x0068:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r0.setTag(r6)
            r5.addView(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.commercecash.CommerceCashPagerAdapter.instantiateItem(android.view.ViewGroup, int):java.lang.Object");
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public CharSequence getPageTitle(int i) {
        if (this.mSections == null || i >= this.mSections.size()) {
            return "";
        }
        switch ((CommerceCashSection) this.mSections.get(i)) {
            case ADD_CASH:
                return WishApplication.getInstance().getResources().getString(R.string.add_cash);
            case INFO:
                if (ExperimentDataCenter.getInstance().shouldSeePayNearMe()) {
                    return WishApplication.getInstance().getResources().getString(R.string.how_this_works);
                }
                return WishApplication.getInstance().getResources().getString(R.string.info);
            case HISTORY:
                return WishApplication.getInstance().getResources().getString(R.string.history);
            default:
                return "";
        }
    }

    public int getCount() {
        return this.mSections.size();
    }

    /* access modifiers changed from: 0000 */
    public void updatePages() {
        this.mSections = new ArrayList<>();
        this.mSections.add(CommerceCashSection.ADD_CASH);
        this.mSections.add(CommerceCashSection.INFO);
        if (ExperimentDataCenter.getInstance().shouldShowCommerceCashHistory()) {
            this.mSections.add(CommerceCashSection.HISTORY);
        }
        notifyDataSetChanged();
    }

    public int getItemPosition(Object obj) {
        if (!(obj instanceof CommerceCashPagerView)) {
            return -2;
        }
        int indexOf = this.mPagerViews.indexOf(obj);
        if (indexOf == -1) {
            indexOf = -2;
        }
        return indexOf;
    }

    public void handleLoadingAddCashInfoSuccess(WishCommerceCashSpecs wishCommerceCashSpecs, WishCommerceCashUserInfo wishCommerceCashUserInfo) {
        if (this.mAddCashView != null) {
            if (wishCommerceCashSpecs != null && wishCommerceCashSpecs.getPurchaseOptions() != null && wishCommerceCashSpecs.getPurchaseOptions().isEmpty() && this.mSections.contains(CommerceCashSection.ADD_CASH)) {
                this.mSections.remove(CommerceCashSection.ADD_CASH);
                this.mPagerViews.remove(this.mAddCashView);
                notifyDataSetChanged();
                this.mCommerceCashFragment.hideAddCashTab();
            }
            this.mAddCashView.handleLoadingSuccess(wishCommerceCashSpecs, wishCommerceCashUserInfo);
        }
    }

    public void handleLoadingHelpInfoSuccess(WishCommerceCashHelpInfo wishCommerceCashHelpInfo) {
        if (this.mHelpView != null) {
            this.mHelpView.handleLoadingSuccess(wishCommerceCashHelpInfo);
        }
    }

    public void handleLoadingHistorySuccess(WishCommerceCashHistory wishCommerceCashHistory) {
        if (this.mHistoryView != null) {
            this.mHistoryView.handleLoadingSuccess(wishCommerceCashHistory);
        }
    }

    public void handleLoadingAddCashInfoFailure() {
        if (this.mAddCashView != null) {
            this.mAddCashView.handleLoadingFailure();
        }
    }

    public void handleLoadingHelpInfoFailure() {
        if (this.mHelpView != null) {
            this.mHelpView.handleLoadingFailure();
        }
    }

    public void handleLoadingHistoryFailure() {
        if (this.mHistoryView != null) {
            this.mHistoryView.handleLoadingFailure();
        }
    }

    public void scrollOffset(int i, int i2) {
        for (int i3 = 0; i3 < this.mPagerViews.size(); i3++) {
            if (i3 != i2) {
                ((CommerceCashPagerView) this.mPagerViews.get(i3)).scrollPage(i);
            }
        }
    }

    public void handlePageSelected(int i) {
        if (this.mSections != null && i < this.mSections.size()) {
            switch ((CommerceCashSection) this.mSections.get(i)) {
                case ADD_CASH:
                    if (!this.mAddCashTracked) {
                        this.mAddCashTracked = true;
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_COMMERCE_CASH_ADD_CASH_TAB);
                        break;
                    }
                    break;
                case INFO:
                    if (!this.mHelpInfoTracked) {
                        this.mHelpInfoTracked = true;
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_COMMERCE_CASH_HELP_INFO_TAB);
                        break;
                    }
                    break;
                case HISTORY:
                    if (!this.mHistoryTracked) {
                        this.mHistoryTracked = true;
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_COMMERCE_CASH_HISTORY_TAB);
                    }
                    this.mCommerceCashFragment.setTabAreaOffset(0);
                    break;
            }
            this.mCommerceCashFragment.refreshTabStripFontColors();
        }
    }

    public void prepareForReload() {
        Iterator it = this.mPagerViews.iterator();
        while (it.hasNext()) {
            ((CommerceCashPagerView) it.next()).handleReload();
        }
    }
}
