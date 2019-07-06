package com.contextlogic.wish.activity.signup.SignupFreeGift.tabbed;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.signup.SignupFreeGift.SignupFreeGiftFragment;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.viewpager.BasePagerScrollingObserver;
import java.util.ArrayList;

public class FreeGiftsPagerAdapter extends PagerAdapter {
    private final SignupFreeGiftAdapter mAdapter = new SignupFreeGiftAdapter(this.mSignupFragment);
    private final ArrayList<FreeGiftsSection> mSections;
    private SignupFreeGiftFragment mSignupFragment;
    private final TabbedSignupFreeGiftView mSignupFreeGiftView;
    private ViewPager mViewPager;

    public enum FreeGiftsSection {
        FEMALE(0),
        MALE(1);
        
        private int mValue;

        private FreeGiftsSection(int i) {
            this.mValue = i;
        }

        public static FreeGiftsSection fromInt(int i) {
            if (i == 0) {
                return FEMALE;
            }
            if (i == 1) {
                return MALE;
            }
            return FEMALE;
        }
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public FreeGiftsPagerAdapter(SignupFreeGiftFragment signupFreeGiftFragment, TabbedSignupFreeGiftView tabbedSignupFreeGiftView, ViewPager viewPager, ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mSignupFragment = signupFreeGiftFragment;
        this.mViewPager = viewPager;
        this.mSignupFreeGiftView = tabbedSignupFreeGiftView;
        this.mAdapter.setImagePrefetcher(imageHttpPrefetcher);
        this.mSections = new ArrayList<>();
        if (tabbedSignupFreeGiftView.getFemaleFreeGifts() != null && tabbedSignupFreeGiftView.getFemaleFreeGifts().size() > 0) {
            this.mSections.add(FreeGiftsSection.FEMALE);
        }
        if (tabbedSignupFreeGiftView.getMaleFreeGifts() != null && tabbedSignupFreeGiftView.getMaleFreeGifts().size() > 0) {
            this.mSections.add(FreeGiftsSection.MALE);
        }
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        FreeGiftsSection freeGiftsSection = (FreeGiftsSection) this.mSections.get(i);
        SignupFreeGiftAdapter signupFreeGiftAdapter = new SignupFreeGiftAdapter(this.mSignupFragment);
        switch (freeGiftsSection) {
            case FEMALE:
                signupFreeGiftAdapter.setItems(this.mSignupFreeGiftView.getFemaleFreeGifts());
                break;
            case MALE:
                signupFreeGiftAdapter.setItems(this.mSignupFreeGiftView.getMaleFreeGifts());
                break;
        }
        SignupFreeGiftFeedView signupFreeGiftFeedView = new SignupFreeGiftFeedView(i, this.mSignupFragment.getBaseActivity(), this.mSignupFragment, signupFreeGiftAdapter);
        signupFreeGiftFeedView.setTag(Integer.valueOf(i));
        viewGroup.addView(signupFreeGiftFeedView, new LayoutParams(-1, -1));
        return signupFreeGiftFeedView;
    }

    public int getCount() {
        if (this.mSections == null) {
            return 0;
        }
        return this.mSections.size();
    }

    public void releaseImages() {
        for (int i = 0; i < getCount(); i++) {
            SignupFreeGiftFeedView signupFreeGiftFeedView = (SignupFreeGiftFeedView) this.mViewPager.findViewWithTag(Integer.valueOf(i));
            if (signupFreeGiftFeedView != null) {
                signupFreeGiftFeedView.releaseImages();
            }
        }
    }

    public void restoreImages() {
        for (int i = 0; i < getCount(); i++) {
            SignupFreeGiftFeedView signupFreeGiftFeedView = (SignupFreeGiftFeedView) this.mViewPager.findViewWithTag(Integer.valueOf(i));
            if (signupFreeGiftFeedView != null) {
                signupFreeGiftFeedView.restoreImages();
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
    }

    public void scrollOffset(int i, int i2) {
        for (int i3 = 0; i3 < getCount(); i3++) {
            SignupFreeGiftFeedView signupFreeGiftFeedView = (SignupFreeGiftFeedView) this.mViewPager.findViewWithTag(Integer.valueOf(i3));
            if (signupFreeGiftFeedView != null) {
                signupFreeGiftFeedView.scrollOffset(i);
            }
        }
    }

    public String getPageTitle(int i) {
        if (this.mSections == null || i >= this.mSections.size()) {
            return "";
        }
        switch ((FreeGiftsSection) this.mSections.get(i)) {
            case FEMALE:
                return WishApplication.getInstance().getString(R.string.freegift_women);
            case MALE:
                return WishApplication.getInstance().getString(R.string.freegift_men);
            default:
                return "";
        }
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((SignupFreeGiftFeedView) obj);
    }
}
