package com.contextlogic.wish.activity.dailybonus;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.Theme;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.activity.notifications.NotificationsActivity;
import com.contextlogic.wish.activity.rewards.RewardsActivity;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishDailyLoginStampSpec;
import com.contextlogic.wish.api.model.WishNotification.NotificationDisplayType;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import com.contextlogic.wish.util.PreferenceUtil;
import com.contextlogic.wish.util.StringUtil;
import java.util.ArrayList;

public class DailyLoginBonusFragment extends UiFragment<DailyLoginBonusActivity> {
    private TextView mExpiryDate;
    private DailyLoginBonusStampView mStamp1;
    private DailyLoginBonusStampView mStamp2;
    private DailyLoginBonusStampView mStamp3;
    private DailyLoginBonusStampView mStamp4;
    private DailyLoginBonusStampView mStamp5;
    private DailyLoginBonusStampView mStamp6;
    private DailyLoginBonusStampView mStamp7;
    private ArrayList<DailyLoginBonusStampView> mStampViews;
    private TextView mSubtitleText;
    private FrameLayout mTextContainer;
    private TextView mTitleText;
    private TextView mViewCouponText;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.daily_login_bonus_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void handleResume() {
        super.handleResume();
        withServiceFragment(new ServiceTask<BaseActivity, DailyLoginBonusServiceFragment>() {
            public void performTask(BaseActivity baseActivity, DailyLoginBonusServiceFragment dailyLoginBonusServiceFragment) {
                dailyLoginBonusServiceFragment.loadStamps();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        ((DailyLoginBonusActivity) getBaseActivity()).getActionBarManager().setTheme(Theme.TRANSPARENT_BACKGROUND_LIGHT_BUTTONS);
        this.mTextContainer = (FrameLayout) findViewById(R.id.daily_login_bonus_fragment_text_holder);
        if (((DailyLoginBonusActivity) getBaseActivity()).getSupportActionBar() != null) {
            ((LayoutParams) this.mTextContainer.getLayoutParams()).setMargins(0, ((DailyLoginBonusActivity) getBaseActivity()).getSupportActionBar().getHeight(), 0, 0);
        }
        if (!PreferenceUtil.getBoolean("SawDailyLoginScreen")) {
            PreferenceUtil.setBoolean("SawDailyLoginScreen", true);
            ApplicationEventManager.getInstance().triggerEvent(EventType.BADGE_SECTION_VIEWED, MenuFragment.MENU_KEY_DAILY_LOGIN_BONUS, null);
        }
        this.mSubtitleText = (TextView) findViewById(R.id.daily_login_bonus_subtitle_text);
        this.mExpiryDate = (TextView) findViewById(R.id.daily_login_bonus_expiry_date_text);
        this.mTitleText = (TextView) findViewById(R.id.daily_login_bonus_title_text);
        this.mViewCouponText = (TextView) findViewById(R.id.daily_login_bonus_view_coupon_text);
        if (ExperimentDataCenter.getInstance().showDailyLoginBonusLinkToReward()) {
            this.mViewCouponText.setVisibility(0);
            if (ExperimentDataCenter.getInstance().isRewardWalletEnabled()) {
                this.mViewCouponText.setText(R.string.daily_login_bonus_view_coupons_wallet);
                this.mViewCouponText.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        DailyLoginBonusFragment.this.startRewardWallet();
                    }
                });
            } else {
                this.mViewCouponText.setText(R.string.daily_login_bonus_view_coupons_notifications);
                this.mViewCouponText.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        DailyLoginBonusFragment.this.startNotifications();
                    }
                });
            }
        } else {
            this.mViewCouponText.setVisibility(8);
        }
        this.mStamp1 = (DailyLoginBonusStampView) findViewById(R.id.daily_login_bonus_stamp_1);
        this.mStamp2 = (DailyLoginBonusStampView) findViewById(R.id.daily_login_bonus_stamp_2);
        this.mStamp3 = (DailyLoginBonusStampView) findViewById(R.id.daily_login_bonus_stamp_3);
        this.mStamp4 = (DailyLoginBonusStampView) findViewById(R.id.daily_login_bonus_stamp_4);
        this.mStamp5 = (DailyLoginBonusStampView) findViewById(R.id.daily_login_bonus_stamp_5);
        this.mStamp6 = (DailyLoginBonusStampView) findViewById(R.id.daily_login_bonus_stamp_6);
        this.mStamp7 = (DailyLoginBonusStampView) findViewById(R.id.daily_login_bonus_stamp_7);
        this.mStampViews = new ArrayList<>();
        this.mStampViews.add(this.mStamp1);
        this.mStampViews.add(this.mStamp2);
        this.mStampViews.add(this.mStamp3);
        this.mStampViews.add(this.mStamp4);
        this.mStampViews.add(this.mStamp5);
        this.mStampViews.add(this.mStamp6);
        this.mStampViews.add(this.mStamp7);
    }

    /* access modifiers changed from: private */
    public void startNotifications() {
        withActivity(new ActivityTask<DailyLoginBonusActivity>() {
            public void performTask(DailyLoginBonusActivity dailyLoginBonusActivity) {
                Intent intent = new Intent();
                intent.setClass(dailyLoginBonusActivity, NotificationsActivity.class);
                intent.putExtra("ExtraAutoscrollNotificationType", NotificationDisplayType.DAILY_LOGIN_BONUS.getValue());
                dailyLoginBonusActivity.startActivity(intent);
            }
        });
    }

    /* access modifiers changed from: private */
    public void startRewardWallet() {
        withActivity(new ActivityTask<DailyLoginBonusActivity>() {
            public void performTask(DailyLoginBonusActivity dailyLoginBonusActivity) {
                Intent intent = new Intent();
                intent.setClass(dailyLoginBonusActivity, RewardsActivity.class);
                dailyLoginBonusActivity.startActivity(intent);
            }
        });
    }

    public void handleStampInfoLoaded(WishDailyLoginStampSpec wishDailyLoginStampSpec) {
        if (wishDailyLoginStampSpec != null) {
            this.mTitleText.setText(wishDailyLoginStampSpec.getDiscountText());
            this.mSubtitleText.setText(wishDailyLoginStampSpec.getTitleText());
            int i = 0;
            this.mExpiryDate.setText(StringUtil.boldSubstring(getResources().getString(R.string.daily_login_complete_by_text, new Object[]{wishDailyLoginStampSpec.getExpiryDate()}), wishDailyLoginStampSpec.getExpiryDate()));
            for (int i2 = 0; i2 < this.mStampViews.size(); i2++) {
                if (i2 == this.mStampViews.size() - 1) {
                    ((DailyLoginBonusStampView) this.mStampViews.get(i2)).setLastStamp(R.dimen.daily_login_bonus_dashboard_filler_size, R.dimen.daily_login_bonus_dashboard_stamp_size, wishDailyLoginStampSpec.getFinalStampText(), i2 + 1);
                } else {
                    ((DailyLoginBonusStampView) this.mStampViews.get(i2)).setStampNumber(i2 + 1);
                }
            }
            while (i < wishDailyLoginStampSpec.getStampNumber() && i < this.mStampViews.size()) {
                if (i < this.mStampViews.size() - 1) {
                    ((DailyLoginBonusStampView) this.mStampViews.get(i)).stampWithoutAnimation();
                } else {
                    ((DailyLoginBonusStampView) this.mStampViews.get(i)).stampWithAnimation();
                }
                i++;
            }
        }
    }
}
