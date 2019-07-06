package com.contextlogic.wish.activity.feed.dealdash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.BaseProductFeedView;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.StatusDataCenter;
import com.contextlogic.wish.api.model.WishDealDashInfo;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.grid.StaggeredGridView.OnItemClickListener;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.timer.CountdownTimerView;
import com.contextlogic.wish.ui.timer.TimerTextView;
import com.contextlogic.wish.util.TabletUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class DealDashProductFeedView extends BaseProductFeedView {
    private static int COUNT_DOWN_TIME = 3;
    /* access modifiers changed from: private */
    public WishDealDashInfo mDealDashInfo;
    /* access modifiers changed from: private */
    public Handler mHandler;
    private AutoReleasableImageView mHelpButton;
    private boolean mIsSavingSpinResult = false;
    /* access modifiers changed from: private */
    public boolean mIsSpinning = false;
    private TimerTextView mNewTutorialCountdownView;
    /* access modifiers changed from: private */
    public ProductFeedFragment mProductFeedFragment;
    /* access modifiers changed from: private */
    public TextView mRibbonText;
    private RotateAnimation mRotateAnimation;
    /* access modifiers changed from: private */
    public int mSpinResult;
    /* access modifiers changed from: private */
    public TextView mSpinTextView;
    /* access modifiers changed from: private */
    public AutoReleasableImageView mSpinnerButton;
    /* access modifiers changed from: private */
    public AutoReleasableImageView mSpinnerView;
    /* access modifiers changed from: private */
    public View mSpinnerViewContainer;
    private TextView mStartButton;
    private DealDashState mState;
    private ImageView mTutorialArrowView;
    private View mTutorialContainer;
    private View mTutorialCountdownContainer;
    private TextView mTutorialCountdownTextView;
    private CountdownTimerView mTutorialCountdownView;
    private TextView mTutorialDoneButton;
    private TextView mTutorialSkipTutorialButton;
    private View mTutorialSpinnerContainerView;
    private ImageView mTutorialSpinnerView;
    /* access modifiers changed from: private */
    public DealDashTutorialState mTutorialState;
    private TextView mTutorialTextView;
    /* access modifiers changed from: private */
    public TextView mUnlockedBottomText;
    /* access modifiers changed from: private */
    public AutoReleasableImageView mUnlockedImageView;
    /* access modifiers changed from: private */
    public TextView mUnlockedTopText;
    private AutoReleasableImageView mUnlockedViewBackground;
    /* access modifiers changed from: private */
    public View mUnlockedViewContainer;

    public enum DealDashState {
        ACCESS_BLOCKED,
        ACCESS_GRANTED,
        TIMES_UP,
        PLAYING,
        COUNTDOWN
    }

    public enum DealDashTutorialState {
        INITIAL_SCREEN,
        SECOND_SCREEN,
        FINISHED
    }

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.deal_dash_product_feed;
    }

    public DealDashProductFeedView(int i, DrawerActivity drawerActivity, ProductFeedFragment productFeedFragment, WishDealDashInfo wishDealDashInfo) {
        super(i, drawerActivity, productFeedFragment);
        this.mDealDashInfo = wishDealDashInfo;
        this.mProductFeedFragment = productFeedFragment;
        setHideEmptyState(true);
        markLoadingComplete();
        updateDealDashInfo(this.mDealDashInfo);
    }

    public void initializeLoadingContentView(View view) {
        super.initializeLoadingContentView(view);
        this.mGridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(int i, View view) {
                final WishProduct item = DealDashProductFeedView.this.mAdapter.getItem(i);
                if (item != null) {
                    DealDashProductFeedView.this.mProductFeedFragment.withActivity(new ActivityTask<BaseActivity>() {
                        public void performTask(BaseActivity baseActivity) {
                            Intent intent = new Intent();
                            intent.setClass(baseActivity, ProductDetailsActivity.class);
                            ProductDetailsActivity.addInitialProduct(intent, item);
                            if (DealDashProductFeedView.this.mProductFeedFragment.hasCountdownTimer()) {
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("ArgExtraDealDashTime", DealDashProductFeedView.this.mProductFeedFragment.getCountdownDate());
                                bundle.putString("ArgDealDashCouponCode", DealDashProductFeedView.this.mDealDashInfo.getCouponCode());
                                bundle.putString("ArgDealDashPercentOffString", DealDashProductFeedView.this.mDealDashInfo.getDiscountPercentString());
                                intent.putExtras(bundle);
                            }
                            baseActivity.startActivity(intent);
                        }
                    });
                }
            }
        });
        addEmptyViewHeader();
        this.mHandler = new Handler();
        this.mSpinnerViewContainer = view.findViewById(R.id.deal_dash_spinner_view_container);
        this.mUnlockedViewContainer = view.findViewById(R.id.deal_dash_unlocked_view_container);
        this.mUnlockedViewBackground = (AutoReleasableImageView) view.findViewById(R.id.deal_dash_unlocked_view_container_background);
        if (TabletUtil.isTablet()) {
            this.mUnlockedViewBackground.setImageResource(R.drawable.dealdash_blue_card_tablet);
        } else {
            this.mUnlockedViewBackground.setImageResource(R.drawable.dealdash_blue_card);
        }
        this.mSpinnerView = (AutoReleasableImageView) view.findViewById(R.id.deal_dash_spinner);
        this.mSpinnerView.setImageResource(R.drawable.dealdash_spinner_key);
        this.mSpinnerButton = (AutoReleasableImageView) view.findViewById(R.id.deal_dash_spin_button);
        this.mRibbonText = (TextView) view.findViewById(R.id.deal_dash_ribbon_text);
        this.mHelpButton = (AutoReleasableImageView) view.findViewById(R.id.deal_dash_help);
        this.mHelpButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DealDashProductFeedView.this.showHelp();
            }
        });
        this.mUnlockedTopText = (TextView) view.findViewById(R.id.deal_dash_unlocked_top_text);
        this.mUnlockedImageView = (AutoReleasableImageView) view.findViewById(R.id.deal_dash_unlocked_image_view);
        this.mUnlockedBottomText = (TextView) view.findViewById(R.id.deal_dash_unlocked_bottom_text);
        this.mStartButton = (TextView) view.findViewById(R.id.deal_dash_unlocked_play_button);
        this.mStartButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StatusDataCenter.getInstance().refresh();
                if (!DealDashProductFeedView.this.mDealDashInfo.getSeenTutorial()) {
                    DealDashProductFeedView.this.showTutorial();
                    return;
                }
                DealDashProductFeedView.this.mTutorialState = DealDashTutorialState.FINISHED;
                DealDashProductFeedView.this.refreshTutorialState();
            }
        });
        this.mSpinTextView = (TextView) view.findViewById(R.id.deal_dash_spin_text_view);
        this.mSpinnerButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DealDashProductFeedView.this.handleSpin();
            }
        });
        setBackgroundResource(R.color.deal_dash_background);
        this.mTutorialState = DealDashTutorialState.INITIAL_SCREEN;
        this.mTutorialContainer = view.findViewById(R.id.deal_dash_tutorial_view_container);
        this.mTutorialCountdownContainer = view.findViewById(R.id.deal_dash_tutorial_countdown_container);
        this.mTutorialCountdownView = (CountdownTimerView) view.findViewById(R.id.deal_dash_tutorial_countdown_timer_top);
        this.mNewTutorialCountdownView = (TimerTextView) view.findViewById(R.id.deal_dash_2_tutorial_countdown_timer_top);
        if (ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon()) {
            this.mTutorialCountdownView.setVisibility(8);
            this.mNewTutorialCountdownView.setVisibility(0);
        }
        this.mTutorialCountdownTextView = (TextView) view.findViewById(R.id.deal_dash_tutorial_countdown_caption);
        this.mTutorialArrowView = (ImageView) view.findViewById(R.id.deal_dash_tutorial_arrow);
        this.mTutorialSpinnerContainerView = view.findViewById(R.id.deal_dash_tutorial_spin_container);
        this.mTutorialSpinnerView = (ImageView) view.findViewById(R.id.deal_dash_tutorial_spin);
        this.mTutorialSpinnerView.setImageResource(R.drawable.dealdash_spinner_key);
        this.mTutorialTextView = (TextView) view.findViewById(R.id.deal_dash_tutorial_text);
        this.mTutorialDoneButton = (TextView) view.findViewById(R.id.deal_dash_tutorial_ok);
        this.mTutorialDoneButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (DealDashProductFeedView.this.mTutorialState == DealDashTutorialState.INITIAL_SCREEN) {
                    DealDashProductFeedView.this.mTutorialState = DealDashTutorialState.SECOND_SCREEN;
                    DealDashProductFeedView.this.refreshTutorialState();
                } else if (DealDashProductFeedView.this.mTutorialState == DealDashTutorialState.SECOND_SCREEN) {
                    DealDashProductFeedView.this.mTutorialState = DealDashTutorialState.FINISHED;
                    DealDashProductFeedView.this.refreshTutorialState();
                }
            }
        });
        this.mTutorialSkipTutorialButton = (TextView) view.findViewById(R.id.deal_dash_tutorial_skip);
        this.mTutorialSkipTutorialButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DealDashProductFeedView.this.mTutorialState = DealDashTutorialState.FINISHED;
                DealDashProductFeedView.this.refreshTutorialState();
            }
        });
    }

    private void addEmptyViewHeader() {
        View view = new View(getContext());
        view.setLayoutParams(new LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.deal_dash_timer_height_space)));
        setCustomHeaderView(view);
    }

    /* access modifiers changed from: protected */
    public void loadNextPage() {
        if (this.mRequestId != null && this.mRequestId.equals("deal_dash__tab")) {
            if (this.mState == DealDashState.PLAYING) {
                super.loadNextPage();
            } else {
                markLoadingComplete();
            }
        }
    }

    public boolean hasItems() {
        if (this.mState == DealDashState.PLAYING) {
            return super.hasItems();
        }
        return true;
    }

    public DealDashState getState() {
        return this.mState;
    }

    public void updateDealDashInfo(WishDealDashInfo wishDealDashInfo) {
        this.mDealDashInfo = wishDealDashInfo;
        this.mProductFeedFragment.updateDealDashInfo(this.mDealDashInfo);
        long timeElapsed = this.mDealDashInfo.getTimeElapsed();
        long spinTimeElapsed = this.mDealDashInfo.getSpinTimeElapsed();
        if (timeElapsed < this.mDealDashInfo.getPlayTime()) {
            changeState(DealDashState.PLAYING, (((long) COUNT_DOWN_TIME) + this.mDealDashInfo.getPlayTime()) - timeElapsed);
        } else if (this.mDealDashInfo.hasPlayedToday()) {
            changeState(DealDashState.ACCESS_BLOCKED, 0);
        } else if (spinTimeElapsed <= 0 || spinTimeElapsed >= this.mDealDashInfo.getWaitTime()) {
            changeState(DealDashState.ACCESS_GRANTED);
        } else {
            changeState(DealDashState.ACCESS_GRANTED, 0, true);
        }
    }

    public void changeState(DealDashState dealDashState) {
        if (dealDashState == DealDashState.ACCESS_BLOCKED) {
            changeState(dealDashState, this.mDealDashInfo.getWaitTime());
        } else if (dealDashState == DealDashState.PLAYING) {
            changeState(dealDashState, this.mDealDashInfo.getPlayTime());
        } else {
            changeState(dealDashState, 0);
        }
    }

    public void changeState(DealDashState dealDashState, long j) {
        changeState(dealDashState, j, false);
    }

    public void changeState(DealDashState dealDashState, long j, boolean z) {
        if (this.mState != dealDashState) {
            hideAllElements();
        }
        this.mState = dealDashState;
        if (dealDashState == DealDashState.ACCESS_GRANTED) {
            if (z || this.mSpinResult > 0) {
                showUnlockedPage();
            } else {
                showSpinnerPage();
            }
        } else if (dealDashState == DealDashState.ACCESS_BLOCKED) {
            showAccessBlockedPage();
        } else if (dealDashState == DealDashState.PLAYING) {
            showPlayingPage(j);
        }
    }

    private void hideAllElements() {
        setBackgroundResource(R.color.white);
        this.mTutorialContainer.setVisibility(8);
        this.mGridView.setVisibility(8);
        this.mSpinnerViewContainer.setVisibility(8);
        this.mUnlockedViewContainer.setVisibility(8);
    }

    private void showAccessBlockedPage() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DEAL_DASH_WAIT);
        this.mHandler.post(new Runnable() {
            public void run() {
                DealDashProductFeedView.this.setBackgroundResource(R.drawable.dealdash_background);
                DealDashProductFeedView.this.mSpinnerViewContainer.setVisibility(0);
                DealDashProductFeedView.this.mSpinnerView.setImageResource(R.drawable.dealdash_spinner_key_disabled);
                DealDashProductFeedView.this.mSpinnerButton.setImageResource(R.drawable.dealdash_spin_button_inactive);
                DealDashProductFeedView.this.mSpinTextView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.dark_gray_1));
                DealDashProductFeedView.this.mRibbonText.setText(DealDashProductFeedView.this.getContext().getString(R.string.blitz_buy_ribbon_after_spin));
            }
        });
    }

    private void showSpinnerPage() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DEAL_DASH_START);
        this.mIsSpinning = false;
        this.mSpinResult = 0;
        this.mHandler.post(new Runnable() {
            public void run() {
                DealDashProductFeedView.this.setBackgroundResource(R.drawable.dealdash_background);
                DealDashProductFeedView.this.mSpinnerViewContainer.setVisibility(0);
                DealDashProductFeedView.this.mSpinnerView.setImageResource(R.drawable.dealdash_spinner_key);
                DealDashProductFeedView.this.mSpinnerButton.setImageResource(R.drawable.dealdash_spin_selector);
            }
        });
    }

    /* access modifiers changed from: private */
    public void showUnlockedPage() {
        if (!this.mIsSpinning && !this.mIsSavingSpinResult) {
            hideAllElements();
            if (this.mDealDashInfo.getSpinResult() > 0 && !this.mDealDashInfo.hasPlayedToday()) {
                this.mSpinResult = (int) this.mDealDashInfo.getSpinResult();
            }
            this.mHandler.post(new Runnable() {
                public void run() {
                    DealDashProductFeedView.this.setBackgroundResource(R.color.deal_dash_background);
                    DealDashProductFeedView.this.mUnlockedTopText.setText(String.format(DealDashProductFeedView.this.getContext().getString(R.string.blitz_buy_unlocked_product), new Object[]{Integer.valueOf(DealDashProductFeedView.this.mSpinResult)}));
                    DealDashProductFeedView.this.mUnlockedBottomText.setText(String.format(DealDashProductFeedView.this.getContext().getString(R.string.blitz_buy_product), new Object[]{Integer.valueOf(DealDashProductFeedView.this.mSpinResult)}));
                    DealDashProductFeedView.this.mUnlockedImageView.setImageResource(R.drawable.dealdash_key);
                    DealDashProductFeedView.this.mUnlockedViewContainer.setVisibility(0);
                }
            });
        }
    }

    private void showPlayingPage(long j) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DEAL_DASH_FEED);
        hideAllElements();
        this.mHandler.post(new Runnable() {
            public void run() {
                DealDashProductFeedView.this.mGridView.setVisibility(0);
            }
        });
        this.mProductFeedFragment.setCountdownTargetDate(new Date(System.currentTimeMillis() + (j * 1000)));
        updateTabAreaOffset();
        reload();
    }

    /* access modifiers changed from: private */
    public void showHelp() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DEAL_DASH_TUTORIAL);
        this.mProductFeedFragment.showDealDashHelpModal();
    }

    /* access modifiers changed from: private */
    public void handleSpin() {
        if (!this.mIsSpinning && this.mState == DealDashState.ACCESS_GRANTED) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DEAL_DASH_SPIN);
            List asList = Arrays.asList(new Integer[]{Integer.valueOf(60), Integer.valueOf(120), Integer.valueOf(180), Integer.valueOf(240), Integer.valueOf(300)});
            List asList2 = Arrays.asList(new Integer[]{Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(20), Integer.valueOf(30), Integer.valueOf(30)});
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < asList2.size(); i++) {
                int intValue = ((Integer) asList2.get(i)).intValue();
                int intValue2 = ((Integer) asList.get(i)).intValue();
                for (int i2 = 0; i2 < intValue; i2++) {
                    arrayList.add(Integer.valueOf(intValue2));
                }
            }
            int i3 = ExperimentDataCenter.getInstance().tenSecondDealDashSpinTime() ? 20 : ExperimentDataCenter.getInstance().threeSecondDealDashSpinTime() ? 6 : ExperimentDataCenter.getInstance().oneSecondDealDashSpinTime() ? 2 : 10;
            int intValue3 = ((Integer) arrayList.get(new Random().nextInt(arrayList.size()))).intValue();
            int i4 = (i3 * 360) + intValue3;
            HashMap hashMap = new HashMap();
            hashMap.put(Integer.valueOf(60), Integer.valueOf(100));
            hashMap.put(Integer.valueOf(120), Integer.valueOf(75));
            hashMap.put(Integer.valueOf(180), Integer.valueOf(50));
            hashMap.put(Integer.valueOf(240), Integer.valueOf(20));
            hashMap.put(Integer.valueOf(300), Integer.valueOf(10));
            this.mSpinResult = ((Integer) hashMap.get(Integer.valueOf(intValue3))).intValue();
            this.mIsSpinning = true;
            saveSpinResult();
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, (float) i4, 1, 0.5f, 1, 0.5f);
            this.mRotateAnimation = rotateAnimation;
            this.mRotateAnimation.setFillAfter(true);
            this.mRotateAnimation.setInterpolator(new DecelerateInterpolator());
            if (ExperimentDataCenter.getInstance().tenSecondDealDashSpinTime()) {
                this.mRotateAnimation.setDuration(10000);
            } else if (ExperimentDataCenter.getInstance().threeSecondDealDashSpinTime()) {
                this.mRotateAnimation.setDuration(3000);
            } else if (ExperimentDataCenter.getInstance().oneSecondDealDashSpinTime()) {
                this.mRotateAnimation.setDuration(1000);
            } else {
                this.mRotateAnimation.setDuration(5000);
            }
            this.mRotateAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    DealDashProductFeedView.this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            DealDashProductFeedView.this.mIsSpinning = false;
                            DealDashProductFeedView.this.showUnlockedPage();
                        }
                    }, 1000);
                }
            });
            this.mSpinnerView.startAnimation(this.mRotateAnimation);
        }
    }

    private void saveSpinResult() {
        this.mIsSavingSpinResult = true;
        this.mProductFeedFragment.saveDealDashSpinResult(this.mSpinResult);
    }

    public void handleSaveSpinResultSuccess(WishDealDashInfo wishDealDashInfo) {
        this.mIsSavingSpinResult = false;
        updateDealDashInfo(wishDealDashInfo);
    }

    public void handleSaveSpinResultFailure() {
        this.mIsSavingSpinResult = false;
        changeState(DealDashState.ACCESS_GRANTED);
    }

    /* access modifiers changed from: private */
    public void showTutorial() {
        if (this.mState == DealDashState.ACCESS_GRANTED && this.mSpinResult > 0) {
            this.mTutorialState = DealDashTutorialState.INITIAL_SCREEN;
            refreshTutorialState();
        }
    }

    /* access modifiers changed from: private */
    public void refreshTutorialState() {
        hideAllElements();
        this.mTutorialCountdownContainer.setVisibility(4);
        this.mTutorialArrowView.setVisibility(4);
        this.mTutorialSpinnerContainerView.setVisibility(8);
        if (this.mTutorialState == DealDashTutorialState.INITIAL_SCREEN) {
            this.mTutorialContainer.setVisibility(0);
            this.mTutorialCountdownContainer.setVisibility(0);
            this.mTutorialArrowView.setVisibility(0);
            if (ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon()) {
                this.mTutorialCountdownTextView.setText(null);
                this.mTutorialTextView.setText(R.string.blitz_buy_coupon_tutorial_1);
            } else {
                this.mTutorialCountdownTextView.setText(R.string.blitz_buy_countdown_text);
            }
            int playTime = ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon() ? ((int) this.mDealDashInfo.getPlayTime()) + 1 : 601;
            if (!ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon()) {
                this.mTutorialCountdownView.setVisibility(0);
                this.mNewTutorialCountdownView.setVisibility(8);
                this.mTutorialCountdownView.setup(new Date(System.currentTimeMillis() + ((long) (playTime * 1000))), getResources().getDimensionPixelSize(R.dimen.deal_dash_tutorial_timer_height), getResources().getColor(R.color.white), getResources().getColor(R.color.dark_gray_1), getResources().getColor(R.color.white), R.drawable.timer_square, true, !ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon(), null);
                this.mTutorialCountdownView.startTimer();
                this.mTutorialCountdownView.pauseTimer();
                return;
            }
            this.mTutorialCountdownContainer.setMinimumHeight(getResources().getDimensionPixelOffset(R.dimen.deal_dash_coupon_minimum_height));
            this.mTutorialCountdownView.setVisibility(8);
            this.mNewTutorialCountdownView.setVisibility(0);
            this.mNewTutorialCountdownView.setup(new Date(System.currentTimeMillis() + ((long) (playTime * 1000))), null, false);
            this.mNewTutorialCountdownView.setIsPaused(true);
        } else if (this.mTutorialState == DealDashTutorialState.SECOND_SCREEN) {
            this.mTutorialContainer.setVisibility(0);
            this.mTutorialSpinnerContainerView.setVisibility(0);
            this.mTutorialTextView.setText(getContext().getString(R.string.blitz_buy_tutorial_2));
        } else {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DEAL_DASH_START);
            startDealDash();
            this.mTutorialContainer.setVisibility(8);
        }
    }

    private void startDealDash() {
        hideAllElements();
        markNeedsReload();
        this.mProductFeedFragment.startDealDash(this.mSpinResult, ExperimentDataCenter.getInstance().shouldSeeDealDashCoupon() ? (int) this.mDealDashInfo.getPlayTime() : 600);
    }

    public void handleStartDealDashSuccess(WishDealDashInfo wishDealDashInfo) {
        updateDealDashInfo(wishDealDashInfo);
    }

    public void handleStartDealDashFailure() {
        updateDealDashInfo(this.mDealDashInfo);
    }

    public void handleTimerEnded() {
        changeState(DealDashState.ACCESS_BLOCKED);
    }
}
