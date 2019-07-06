package com.contextlogic.wish.activity.dailybonus;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishDailyLoginStampSpec;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.util.StringUtil;
import java.util.ArrayList;

public class DailyLoginBonusDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    /* access modifiers changed from: private */
    public TextView mButton;
    /* access modifiers changed from: private */
    public LinearLayout mContainer;
    private boolean mCouponWon;
    private TextView mDeadlineDateText;
    private TextView mDescriptionText;
    private DialogInterface mDialogListener;
    private TextView mDiscountTitleText;
    private DailyLoginBonusStampView mStamp1;
    private DailyLoginBonusStampView mStamp2;
    private DailyLoginBonusStampView mStamp3;
    private DailyLoginBonusStampView mStamp4;
    private DailyLoginBonusStampView mStamp5;
    private DailyLoginBonusStampView mStamp6;
    private DailyLoginBonusStampView mStamp7;
    /* access modifiers changed from: private */
    public ImageView mStampEarnedImage;
    private TextView mStampEarnedText;
    /* access modifiers changed from: private */
    public int mStampNumber;
    private ArrayList<DailyLoginBonusStampView> mStampViews;
    /* access modifiers changed from: private */
    public LinearLayout mTopContainer;

    public static DailyLoginBonusDialogFragment<BaseActivity> createDailyLoginBonusDialogFragment(WishDailyLoginStampSpec wishDailyLoginStampSpec) {
        DailyLoginBonusDialogFragment<BaseActivity> dailyLoginBonusDialogFragment = new DailyLoginBonusDialogFragment<>();
        Bundle bundle = new Bundle();
        bundle.putParcelable("ArgumentDailyLoginStampSpec", wishDailyLoginStampSpec);
        dailyLoginBonusDialogFragment.setArguments(bundle);
        return dailyLoginBonusDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.daily_login_bonus_popup_dialog, viewGroup);
        this.mStampEarnedImage = (ImageView) inflate.findViewById(R.id.daily_login_bonus_stamp_image);
        this.mStampEarnedText = (TextView) inflate.findViewById(R.id.daily_login_bonus_stamp_earned_text);
        this.mButton = (TextView) inflate.findViewById(R.id.daily_login_bonus_dialog_button);
        this.mContainer = (LinearLayout) inflate.findViewById(R.id.daily_login_bonus_dialog_content_container);
        this.mTopContainer = (LinearLayout) inflate.findViewById(R.id.daily_login_bonus_dialog_top_container);
        this.mDiscountTitleText = (TextView) inflate.findViewById(R.id.daily_login_bonus_title_text);
        this.mDescriptionText = (TextView) inflate.findViewById(R.id.daily_login_bonus_description);
        this.mDeadlineDateText = (TextView) inflate.findViewById(R.id.daily_login_bonus_deadline_date_text);
        this.mStamp1 = (DailyLoginBonusStampView) inflate.findViewById(R.id.daily_login_bonus_stamp_1);
        this.mStamp2 = (DailyLoginBonusStampView) inflate.findViewById(R.id.daily_login_bonus_stamp_2);
        this.mStamp3 = (DailyLoginBonusStampView) inflate.findViewById(R.id.daily_login_bonus_stamp_3);
        this.mStamp4 = (DailyLoginBonusStampView) inflate.findViewById(R.id.daily_login_bonus_stamp_4);
        this.mStamp5 = (DailyLoginBonusStampView) inflate.findViewById(R.id.daily_login_bonus_stamp_5);
        this.mStamp6 = (DailyLoginBonusStampView) inflate.findViewById(R.id.daily_login_bonus_stamp_6);
        this.mStamp7 = (DailyLoginBonusStampView) inflate.findViewById(R.id.daily_login_bonus_stamp_7);
        this.mStampViews = new ArrayList<>();
        this.mStampViews.add(this.mStamp1);
        this.mStampViews.add(this.mStamp2);
        this.mStampViews.add(this.mStamp3);
        this.mStampViews.add(this.mStamp4);
        this.mStampViews.add(this.mStamp5);
        this.mStampViews.add(this.mStamp6);
        this.mStampViews.add(this.mStamp7);
        final WishDailyLoginStampSpec wishDailyLoginStampSpec = (WishDailyLoginStampSpec) getArguments().getParcelable("ArgumentDailyLoginStampSpec");
        for (int i = 0; i < this.mStampViews.size(); i++) {
            if (i == this.mStampViews.size() - 1) {
                ((DailyLoginBonusStampView) this.mStampViews.get(i)).setLastStamp(R.dimen.daily_login_bonus_dialog_filler_size, R.dimen.daily_login_bonus_dialog_stamp_size, wishDailyLoginStampSpec.getFinalStampText(), i + 1);
            } else {
                ((DailyLoginBonusStampView) this.mStampViews.get(i)).setStampNumber(i + 1);
            }
        }
        this.mCouponWon = wishDailyLoginStampSpec.getCouponWon();
        this.mDeadlineDateText.setText(StringUtil.boldSubstring(getResources().getString(R.string.daily_login_complete_by_text, new Object[]{wishDailyLoginStampSpec.getExpiryDate()}), wishDailyLoginStampSpec.getExpiryDate()));
        this.mDiscountTitleText.setText(wishDailyLoginStampSpec.getDiscountText());
        this.mDescriptionText.setText(wishDailyLoginStampSpec.getDescription());
        if (this.mCouponWon) {
            this.mButton.setText(getString(R.string.redeem_prize));
            this.mDescriptionText.setVisibility(8);
            this.mButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_DAILY_LOGIN_BONUS_DIALOG_REDEEM_PRIZE);
                    DailyLoginBonusDialogFragment.this.withServiceFragment(new ServiceTask<BaseActivity, ServiceFragment>() {
                        public void performTask(BaseActivity baseActivity, ServiceFragment serviceFragment) {
                            serviceFragment.handleDailyLoginBonusCouponDialogFragment(wishDailyLoginStampSpec);
                            DailyLoginBonusDialogFragment.this.cancel();
                        }
                    });
                }
            });
            this.mDeadlineDateText.setText(wishDailyLoginStampSpec.getCouponWonText());
        } else {
            this.mButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_DAILY_LOGIN_BONUS_DIALOG_COLLECT_STAMP);
                    DailyLoginBonusDialogFragment.this.cancel();
                }
            });
        }
        this.mStampNumber = wishDailyLoginStampSpec.getStampNumber();
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void onPopInEnded() {
        if (getContext() != null) {
            this.mStampEarnedImage.clearAnimation();
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(400);
            alphaAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    DailyLoginBonusDialogFragment.this.mTopContainer.setAlpha(1.0f);
                    DailyLoginBonusDialogFragment.this.mButton.setAlpha(1.0f);
                }
            });
            AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation2.setDuration(400);
            alphaAnimation2.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                    DailyLoginBonusDialogFragment.this.populateMainStampEarned(DailyLoginBonusDialogFragment.this.mStampNumber);
                }

                public void onAnimationEnd(Animation animation) {
                    DailyLoginBonusDialogFragment.this.mContainer.setAlpha(1.0f);
                    DailyLoginBonusDialogFragment.this.fillStampEarned(DailyLoginBonusDialogFragment.this.mStampNumber);
                }
            });
            Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.stamp_popout_animation);
            loadAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                    DailyLoginBonusDialogFragment.this.mStampEarnedImage.setVisibility(0);
                }
            });
            this.mStampEarnedImage.startAnimation(loadAnimation);
            this.mTopContainer.startAnimation(alphaAnimation);
            this.mContainer.startAnimation(alphaAnimation2);
        }
    }

    private String stampImageToFileName(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("daily_login_bonus_stamp_");
        sb.append(i);
        return sb.toString();
    }

    private String stampEarnedToResName(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("stamp_earned_");
        sb.append(i);
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public void populateMainStampEarned(int i) {
        int identifier = WishApplication.getInstance().getResources().getIdentifier(stampImageToFileName(i), "drawable", getBaseActivity().getApplicationContext().getPackageName());
        int identifier2 = WishApplication.getInstance().getResources().getIdentifier(stampEarnedToResName(i), "string", getBaseActivity().getApplicationContext().getPackageName());
        if (this.mCouponWon) {
            identifier = R.drawable.daily_login_bonus_stamp_7;
            identifier2 = R.string.stamp_earned_7;
        }
        if (identifier != 0) {
            this.mStampEarnedImage.setImageResource(identifier);
        }
        if (identifier2 != 0) {
            this.mStampEarnedText.setText(getString(identifier2));
        }
    }

    /* access modifiers changed from: private */
    public void fillStampEarned(int i) {
        if (this.mCouponWon) {
            ((DailyLoginBonusStampView) this.mStampViews.get(this.mStampViews.size() - 1)).stampWithAnimation();
            for (int i2 = 0; i2 < this.mStampViews.size(); i2++) {
                if (i2 == this.mStampViews.size() - 1) {
                    ((DailyLoginBonusStampView) this.mStampViews.get(i2)).stampWithAnimation();
                } else {
                    ((DailyLoginBonusStampView) this.mStampViews.get(i2)).stampWithoutAnimation();
                }
            }
            return;
        }
        for (int i3 = 1; i3 <= i; i3++) {
            if (i3 == i) {
                int i4 = i3 - 1;
                if (i4 < this.mStampViews.size()) {
                    ((DailyLoginBonusStampView) this.mStampViews.get(i4)).stampWithAnimation();
                }
            }
            int i5 = i3 - 1;
            if (i5 < this.mStampViews.size()) {
                ((DailyLoginBonusStampView) this.mStampViews.get(i5)).stampWithoutAnimation();
            }
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.mDialogListener != null) {
            this.mDialogListener.dismiss();
        }
    }

    public void setDismissListener(DialogInterface dialogInterface) {
        this.mDialogListener = dialogInterface;
    }
}
