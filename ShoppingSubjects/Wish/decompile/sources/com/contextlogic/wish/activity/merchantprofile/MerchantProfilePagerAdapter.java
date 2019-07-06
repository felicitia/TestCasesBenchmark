package com.contextlogic.wish.activity.merchantprofile;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.BaseProductFeedView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishMerchantTopCategory;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.viewpager.BasePagerScrollingObserver;
import com.contextlogic.wish.ui.viewpager.BasePagerViewInterface;
import java.util.ArrayList;
import java.util.Iterator;

public class MerchantProfilePagerAdapter extends PagerAdapter {
    private ImageHttpPrefetcher mCategoriesImagePrefetcher;
    private boolean mCategoriesTracked;
    private DrawerActivity mDrawerActivity;
    private MerchantProfileFragment mFragment;
    private BaseProductFeedView mLatestProductsView;
    private boolean mLatestTracked;
    private MerchantProfileCategoriesView mMerchantCategoriesView;
    private ImageHttpPrefetcher mMerchantRatingsImagePrefetcher;
    private MerchantProfileRatingsView mMerchantRatingsView;
    private ArrayList<MerchantProfilePagerView> mPagerViews = new ArrayList<>();
    private boolean mReviewsTracked;
    private ArrayList<MerchantProfileSection> mSections;
    private ViewPager mViewPager;

    public enum MerchantProfileSection {
        LATEST,
        CATEGORIES,
        REVIEWS
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public MerchantProfilePagerAdapter(DrawerActivity drawerActivity, MerchantProfileFragment merchantProfileFragment, ViewPager viewPager) {
        this.mViewPager = viewPager;
        this.mDrawerActivity = drawerActivity;
        this.mFragment = merchantProfileFragment;
    }

    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r0v7, types: [com.contextlogic.wish.activity.merchantprofile.MerchantProfileLatestView, com.contextlogic.wish.activity.feed.BaseProductFeedView] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r0v12 */
    /* JADX WARNING: type inference failed for: r0v13 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object instantiateItem(android.view.ViewGroup r7, int r8) {
        /*
            r6 = this;
            java.util.ArrayList<com.contextlogic.wish.activity.merchantprofile.MerchantProfilePagerAdapter$MerchantProfileSection> r0 = r6.mSections
            java.lang.Object r0 = r0.get(r8)
            com.contextlogic.wish.activity.merchantprofile.MerchantProfilePagerAdapter$MerchantProfileSection r0 = (com.contextlogic.wish.activity.merchantprofile.MerchantProfilePagerAdapter.MerchantProfileSection) r0
            int[] r1 = com.contextlogic.wish.activity.merchantprofile.MerchantProfilePagerAdapter.AnonymousClass1.$SwitchMap$com$contextlogic$wish$activity$merchantprofile$MerchantProfilePagerAdapter$MerchantProfileSection
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 0
            switch(r0) {
                case 1: goto L_0x0050;
                case 2: goto L_0x0033;
                case 3: goto L_0x0016;
                default: goto L_0x0014;
            }
        L_0x0014:
            r0 = r1
            goto L_0x0067
        L_0x0016:
            com.contextlogic.wish.activity.merchantprofile.MerchantProfileRatingsView r0 = new com.contextlogic.wish.activity.merchantprofile.MerchantProfileRatingsView
            com.contextlogic.wish.activity.DrawerActivity r2 = r6.mDrawerActivity
            r0.<init>(r2)
            com.contextlogic.wish.activity.merchantprofile.MerchantProfileFragment r2 = r6.mFragment
            com.contextlogic.wish.activity.BaseActivity r2 = r2.getBaseActivity()
            com.contextlogic.wish.activity.merchantprofile.MerchantProfileActivity r2 = (com.contextlogic.wish.activity.merchantprofile.MerchantProfileActivity) r2
            java.lang.String r2 = r2.getMerchantId()
            com.contextlogic.wish.activity.merchantprofile.MerchantProfileFragment r3 = r6.mFragment
            com.contextlogic.wish.http.ImageHttpPrefetcher r4 = r6.mMerchantRatingsImagePrefetcher
            r0.setup(r2, r8, r3, r4)
            r6.mMerchantRatingsView = r0
            goto L_0x0067
        L_0x0033:
            com.contextlogic.wish.activity.merchantprofile.MerchantProfileCategoriesView r0 = new com.contextlogic.wish.activity.merchantprofile.MerchantProfileCategoriesView
            com.contextlogic.wish.activity.DrawerActivity r2 = r6.mDrawerActivity
            r0.<init>(r2)
            com.contextlogic.wish.activity.merchantprofile.MerchantProfileFragment r2 = r6.mFragment
            com.contextlogic.wish.activity.BaseActivity r2 = r2.getBaseActivity()
            com.contextlogic.wish.activity.merchantprofile.MerchantProfileActivity r2 = (com.contextlogic.wish.activity.merchantprofile.MerchantProfileActivity) r2
            java.lang.String r2 = r2.getMerchantId()
            com.contextlogic.wish.activity.merchantprofile.MerchantProfileFragment r3 = r6.mFragment
            com.contextlogic.wish.http.ImageHttpPrefetcher r4 = r6.mCategoriesImagePrefetcher
            r0.setup(r2, r8, r3, r4)
            r6.mMerchantCategoriesView = r0
            goto L_0x0067
        L_0x0050:
            com.contextlogic.wish.activity.merchantprofile.MerchantProfileLatestView r0 = new com.contextlogic.wish.activity.merchantprofile.MerchantProfileLatestView
            com.contextlogic.wish.activity.merchantprofile.MerchantProfileFragment r2 = r6.mFragment
            com.contextlogic.wish.activity.BaseActivity r2 = r2.getBaseActivity()
            com.contextlogic.wish.activity.DrawerActivity r2 = (com.contextlogic.wish.activity.DrawerActivity) r2
            com.contextlogic.wish.activity.merchantprofile.MerchantProfileFragment r3 = r6.mFragment
            r0.<init>(r8, r2, r3)
            r6.mLatestProductsView = r0
            r0.handleResume()
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x0067:
            if (r0 == 0) goto L_0x006a
            r1 = r0
        L_0x006a:
            if (r0 == 0) goto L_0x0071
            java.util.ArrayList<com.contextlogic.wish.activity.merchantprofile.MerchantProfilePagerView> r2 = r6.mPagerViews
            r2.add(r0)
        L_0x0071:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r1.setTag(r8)
            android.view.ViewGroup$LayoutParams r8 = new android.view.ViewGroup$LayoutParams
            r0 = -1
            r8.<init>(r0, r0)
            r7.addView(r1, r8)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.merchantprofile.MerchantProfilePagerAdapter.instantiateItem(android.view.ViewGroup, int):java.lang.Object");
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        BasePagerViewInterface basePagerViewInterface = (BasePagerViewInterface) obj;
        basePagerViewInterface.cleanup();
        viewGroup.removeView((View) basePagerViewInterface);
        if (i == MerchantProfileSection.REVIEWS.ordinal()) {
            this.mMerchantRatingsImagePrefetcher.cancelPrefetching();
        } else if (i == MerchantProfileSection.CATEGORIES.ordinal()) {
            this.mCategoriesImagePrefetcher.cancelPrefetching();
        }
    }

    public void setMerchantRatingsImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mMerchantRatingsImagePrefetcher = imageHttpPrefetcher;
    }

    public void setCategoriesImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mCategoriesImagePrefetcher = imageHttpPrefetcher;
    }

    public BaseProductFeedView getLatestProductsView() {
        return this.mLatestProductsView;
    }

    public void updatePages(boolean z) {
        this.mSections = new ArrayList<>();
        this.mSections.add(MerchantProfileSection.LATEST);
        if (z) {
            this.mSections.add(MerchantProfileSection.CATEGORIES);
        }
        this.mSections.add(MerchantProfileSection.REVIEWS);
        notifyDataSetChanged();
    }

    public void handleLatestLoadingSuccess(ArrayList<WishProduct> arrayList, int i, boolean z) {
        BaseProductFeedView baseProductFeedView = this.mLatestProductsView;
        if (baseProductFeedView != null) {
            baseProductFeedView.handleLoadingSuccess(arrayList, i, z);
        }
    }

    public void handleMerchantTopCategoriesLoaded(ArrayList<WishMerchantTopCategory> arrayList, String str) {
        if (this.mMerchantCategoriesView != null) {
            this.mMerchantCategoriesView.onSuccess(arrayList, str);
        }
    }

    public void handleTopMerchantCategoriesFailed() {
        if (this.mMerchantCategoriesView != null) {
            this.mMerchantCategoriesView.onFailure();
        }
    }

    public void handleRatingsLoaded(ArrayList<WishRating> arrayList, boolean z, int i) {
        if (this.mMerchantRatingsView != null) {
            this.mMerchantRatingsView.onSuccess(arrayList, z, i);
        }
    }

    public void handleRatingsFailed() {
        if (this.mMerchantRatingsView != null) {
            this.mMerchantRatingsView.onFailure();
        }
    }

    public void scrollOffset(int i, int i2) {
        if (!(i2 == 0 || this.mLatestProductsView == null)) {
            ((MerchantProfileLatestView) this.mLatestProductsView).scrollOffset(i);
        }
        int i3 = 0;
        while (i3 < this.mPagerViews.size()) {
            int i4 = i3 + 1;
            if (i2 != i4) {
                ((MerchantProfilePagerView) this.mPagerViews.get(i3)).scrollOffset(i);
            }
            i3 = i4;
        }
    }

    public void releaseImages() {
        for (int i = 0; i < getCount(); i++) {
            BasePagerViewInterface basePagerViewInterface = (BasePagerViewInterface) this.mViewPager.findViewWithTag(Integer.valueOf(i));
            if (basePagerViewInterface != null) {
                basePagerViewInterface.releaseImages();
            }
        }
    }

    public void restoreImages() {
        for (int i = 0; i < getCount(); i++) {
            BasePagerViewInterface basePagerViewInterface = (BasePagerViewInterface) this.mViewPager.findViewWithTag(Integer.valueOf(i));
            if (basePagerViewInterface != null) {
                basePagerViewInterface.restoreImages();
            }
        }
    }

    public void cleanup() {
        for (int i = 0; i < getCount(); i++) {
            BasePagerViewInterface basePagerViewInterface = (BasePagerViewInterface) this.mViewPager.findViewWithTag(Integer.valueOf(i));
            if (basePagerViewInterface != null) {
                basePagerViewInterface.cleanup();
            }
        }
    }

    public void onPagerScrollUnsettled() {
        for (int i = 0; i < getCount(); i++) {
            BasePagerScrollingObserver basePagerScrollingObserver = (BasePagerScrollingObserver) this.mViewPager.findViewWithTag(Integer.valueOf(i));
            if (basePagerScrollingObserver != null) {
                basePagerScrollingObserver.onPagerScrollUnsettled();
            }
        }
    }

    public void onPagerScrollSettled() {
        for (int i = 0; i < getCount(); i++) {
            BasePagerScrollingObserver basePagerScrollingObserver = (BasePagerScrollingObserver) this.mViewPager.findViewWithTag(Integer.valueOf(i));
            if (basePagerScrollingObserver != null) {
                basePagerScrollingObserver.onPagerScrollSettled();
            }
        }
        int currentIndex = this.mFragment.getCurrentIndex();
        if (this.mSections != null && currentIndex < this.mSections.size()) {
            switch ((MerchantProfileSection) this.mSections.get(currentIndex)) {
                case LATEST:
                    if (!this.mLatestTracked) {
                        this.mLatestTracked = true;
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MERCHANT_LATEST_TAB);
                        return;
                    }
                    return;
                case CATEGORIES:
                    if (!this.mCategoriesTracked) {
                        this.mCategoriesTracked = true;
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MERCHANT_CATEGORIES_TAB);
                        return;
                    }
                    return;
                case REVIEWS:
                    if (!this.mReviewsTracked) {
                        this.mReviewsTracked = true;
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MERCHANT_REVIEWS_TAB);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public int getCount() {
        if (this.mSections == null) {
            return 0;
        }
        return this.mSections.size();
    }

    public String getPageTitle(int i) {
        if (this.mSections == null || i >= this.mSections.size()) {
            return "";
        }
        switch ((MerchantProfileSection) this.mSections.get(i)) {
            case LATEST:
                return this.mDrawerActivity.getString(R.string.latest);
            case CATEGORIES:
                return this.mDrawerActivity.getString(R.string.categories);
            case REVIEWS:
                return this.mDrawerActivity.getString(R.string.reviews);
            default:
                return "";
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        if (this.mLatestProductsView != null) {
            bundle.putBundle(this.mFragment.getPagedDataSavedInstanceStateKey(this.mLatestProductsView.getDataIndex()), this.mLatestProductsView.getSavedInstanceState());
        }
        Iterator it = this.mPagerViews.iterator();
        while (it.hasNext()) {
            MerchantProfilePagerView merchantProfilePagerView = (MerchantProfilePagerView) it.next();
            Bundle bundle2 = new Bundle();
            bundle2.putInt("SavedStateFirstItemPosition", merchantProfilePagerView.getFirstItemPosition());
            bundle.putBundle(this.mFragment.getPagedDataSavedInstanceStateKey(merchantProfilePagerView.getIndex()), bundle2);
        }
    }
}
