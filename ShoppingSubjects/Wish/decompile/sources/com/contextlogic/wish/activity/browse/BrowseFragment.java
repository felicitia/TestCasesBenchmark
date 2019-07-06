package com.contextlogic.wish.activity.browse;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.dailybonus.StampRowView;
import com.contextlogic.wish.activity.dailybonus.StampRowView.Callback;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.feed.ProductFeedFragment.DataMode;
import com.contextlogic.wish.activity.feed.newusergiftpack.GiftPackFeedHeaderView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishDailyLoginStampSpec;
import com.contextlogic.wish.dialog.bottomsheet.OrderConfirmedBottomSheetDialog;
import com.contextlogic.wish.util.PreferenceUtil;

public class BrowseFragment extends ProductFeedFragment<BrowseActivity> implements Callback {
    /* access modifiers changed from: private */
    public String mCategoryId;
    /* access modifiers changed from: private */
    public boolean mGiftDialogShown;
    /* access modifiers changed from: private */
    public boolean mPlaceholderMode;
    private OnScrollChangedListener mScrollListener;
    /* access modifiers changed from: private */
    public StampRowView mStampRow;
    private FrameLayout mStampRowClickContainer;
    /* access modifiers changed from: private */
    public FrameLayout mStampRowContainer;
    /* access modifiers changed from: private */
    public int mStampRowViewOffset;

    public boolean canShowFeedCategories() {
        return true;
    }

    public boolean isFeedFilterable() {
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mGiftDialogShown = false;
        if (bundle != null) {
            this.mGiftDialogShown = bundle.getBoolean("SavedStateGiftDialogShown");
        }
    }

    public void initializeLoadingContentView(View view) {
        super.initializeLoadingContentView(view);
        withActivity(new ActivityTask<BrowseActivity>() {
            public void performTask(BrowseActivity browseActivity) {
                BrowseFragment.this.mCategoryId = browseActivity.getCategoryId();
                BrowseFragment.this.mPlaceholderMode = browseActivity.getPlaceholderMode();
            }
        });
        this.mStampRow = new StampRowView(getContext());
        this.mStampRow.setLayoutParams(new LayoutParams(-1, -2));
        this.mStampRow.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.cool_gray5));
        this.mStampRowContainer = new FrameLayout(getContext());
        this.mStampRowContainer.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.mStampRowClickContainer = new FrameLayout(getContext());
        this.mStampRowClickContainer.setLayoutParams(new LayoutParams(-1, -1));
        this.mStampRowContainer.addView(this.mStampRow);
        this.mStampRowContainer.addView(this.mStampRowClickContainer);
        this.mStampRowViewOffset = 0;
    }

    public void handleResume() {
        super.handleResume();
        startFreeGiftDialogIfNecessary();
    }

    private void startFreeGiftDialogIfNecessary() {
        withServiceFragment(new ServiceTask<BrowseActivity, BaseProductFeedServiceFragment>() {
            public void performTask(BrowseActivity browseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                boolean z = true;
                if (browseActivity.canShowFreeGiftDialog() && !BrowseFragment.this.mGiftDialogShown) {
                    BrowseFragment.this.mGiftDialogShown = true;
                    if (browseActivity.getShippingInfo() != null) {
                        z = false;
                    }
                    if (!ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView() || z) {
                        baseProductFeedServiceFragment.showFreeGiftConfirmedDialog(browseActivity.getGiftConfirmedProduct(), browseActivity.getSignupCart());
                        return;
                    }
                    OrderConfirmedBottomSheetDialog.create(browseActivity).setTitle(BrowseFragment.this.getString(R.string.order_confirmed)).setOrderConfirmedInformation(PreferenceUtil.getString("user_login_email"), browseActivity.getShippingInfo().getFormattedString(false)).autoDismiss().show();
                } else if (browseActivity.canShowOrderConfirmedDialog() && !BrowseFragment.this.mGiftDialogShown) {
                    BrowseFragment.this.mGiftDialogShown = true;
                    baseProductFeedServiceFragment.showOrderConfirmedDialog(browseActivity.getShippingInfo());
                } else if (browseActivity.canShowGiftInCartDialog() && !BrowseFragment.this.mGiftDialogShown) {
                    BrowseFragment.this.mGiftDialogShown = true;
                    baseProductFeedServiceFragment.showGiftInCartDialog(browseActivity.getGiftConfirmedProduct());
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean canSeeFloatingInviteButton() {
        return ExperimentDataCenter.getInstance().canSeeInviteCouponButton() && !PreferenceUtil.getBoolean("NeverShowInviteCouponPopup");
    }

    public void handleSaveInstanceState(Bundle bundle) {
        super.handleSaveInstanceState(bundle);
        bundle.putBoolean("SavedStateGiftDialogShown", this.mGiftDialogShown);
    }

    public DataMode getDataMode() {
        return DataMode.FilteredFeed;
    }

    /* access modifiers changed from: protected */
    public String getMainRequestId() {
        return this.mCategoryId;
    }

    /* access modifiers changed from: protected */
    public boolean isPlaceholderMode() {
        return this.mPlaceholderMode;
    }

    public void showStampRowDialog(final WishDailyLoginStampSpec wishDailyLoginStampSpec, boolean z) {
        if (this.mStampRow != null) {
            if (z) {
                this.mStampRowViewOffset = ((int) this.mStampRowContainer.getY()) + GiftPackFeedHeaderView.getTotalHeight() + getTabAreaSize();
                this.mStampRowContainer.setY((float) this.mStampRowViewOffset);
                this.mTabAreaContainer.setMinimumHeight((GiftPackFeedHeaderView.getTotalHeight() + getTabAreaSize()) * 2);
            }
            this.mStampRow.setup(wishDailyLoginStampSpec);
            this.mStampRow.setCallback(this);
            this.mStampRowClickContainer.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    BrowseFragment.this.mStampRow.setLeaveVisible(true);
                    BrowseFragment.this.withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
                        public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                            baseProductFeedServiceFragment.showDailyLoginDialog(wishDailyLoginStampSpec, new DialogInterface() {
                                public void cancel() {
                                    BrowseFragment.this.mStampRow.setLeaveVisible(false);
                                }

                                public void dismiss() {
                                    BrowseFragment.this.mStampRow.setLeaveVisible(false);
                                }
                            });
                        }
                    });
                }
            });
            this.mTabAreaContainer.addView(this.mStampRowContainer);
            if (z) {
                this.mScrollListener = new OnScrollChangedListener() {
                    public void onScrollChanged() {
                        if (BrowseFragment.this.mAdapter.getCurrentFeedView() != null) {
                            int currentScrollY = BrowseFragment.this.mAdapter.getCurrentFeedView().getCurrentScrollY();
                            if (currentScrollY > 0) {
                                BrowseFragment.this.mStampRowContainer.setTranslationY((float) ((BrowseFragment.this.mStampRowViewOffset - BrowseFragment.this.getTabAreaOffset()) - currentScrollY));
                            }
                        }
                    }
                };
                this.mTabAreaContainer.getViewTreeObserver().addOnScrollChangedListener(this.mScrollListener);
            }
            withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
                public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                    baseProductFeedServiceFragment.showDailyLoginStampRowDialog(wishDailyLoginStampSpec);
                }
            });
        }
    }

    public void onPause() {
        super.onPause();
        if (this.mStampRow != null && this.mTabAreaContainer != null) {
            this.mStampRow.cancelAnimations();
            this.mTabAreaContainer.removeView(this.mStampRowContainer);
            if (this.mScrollListener != null) {
                this.mTabAreaContainer.getViewTreeObserver().removeOnScrollChangedListener(this.mScrollListener);
            }
        }
    }

    public StampRowView getStampRow() {
        return this.mStampRow;
    }

    public float getYPositionInContainer() {
        if (this.mStampRowContainer != null) {
            return this.mStampRowContainer.getY();
        }
        return 0.0f;
    }

    public void removeRowFromContainer() {
        if (this.mTabAreaContainer != null && this.mStampRowContainer != null) {
            this.mTabAreaContainer.removeView(this.mStampRowContainer);
            if (this.mScrollListener != null) {
                this.mTabAreaContainer.getViewTreeObserver().removeOnScrollChangedListener(this.mScrollListener);
            }
        }
    }

    public boolean onBackPressed() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BACK_ON_BROWSE);
        if (!ExperimentDataCenter.getInstance().shouldReloadFeedOnBackPress() || getBaseActivity() == null) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BACK_ON_BROWSE_LEAVE_APP);
            return false;
        }
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BACK_ON_BROWSE_REFRESH_ATTEMPT);
        return super.onBackPressed();
    }
}
