package com.contextlogic.wish.activity.dailybonus;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.browse.BrowseFragment;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishDailyLoginStampSpec;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.Margin;
import com.contextlogic.wish.util.DisplayUtil;

public class DailyLoginBonusStampPopupDialog<A extends BaseActivity> extends BaseDialogFragment {
    private TextView mMenuPromptText;
    private boolean mNotShownOnFirstRow;
    /* access modifiers changed from: private */
    public boolean mShowRowAnimation;
    /* access modifiers changed from: private */
    public LinearLayout mStampContainer;
    /* access modifiers changed from: private */
    public int mStampDelay;
    private TextView mStampEarnedAboveText;
    private ImageView mStampEarnedImage;
    private TextView mStampEarnedText;
    private int mStampNumber;
    private LinearLayout mSubtitleContainer;
    private TextView mSubtitleText;
    private LinearLayout mTextContainer;
    private TextView mTitleAbove;
    private TextView mTitleBelow;

    public boolean onBackPressed() {
        return true;
    }

    public static DailyLoginBonusStampPopupDialog<BaseActivity> createDailyLoginBonusStampPopupDialog(WishDailyLoginStampSpec wishDailyLoginStampSpec, boolean z) {
        DailyLoginBonusStampPopupDialog<BaseActivity> dailyLoginBonusStampPopupDialog = new DailyLoginBonusStampPopupDialog<>();
        Bundle bundle = new Bundle();
        bundle.putInt("ArgumentStampNumber", wishDailyLoginStampSpec.getStampNumber());
        bundle.putBoolean("ArgumentShowAnimation", z);
        bundle.putString("ArgumentSubtitle", wishDailyLoginStampSpec.getDiscountTitleText());
        bundle.putBoolean("ArgumentNotShownOnMainFeed", wishDailyLoginStampSpec.notShownOnMainFeed());
        bundle.putString("ArgumentMenuPrompt", wishDailyLoginStampSpec.getMenuPrompt());
        bundle.putInt("ArgumentStampFadeDelay", wishDailyLoginStampSpec.getStampFadeDelay());
        dailyLoginBonusStampPopupDialog.setArguments(bundle);
        return dailyLoginBonusStampPopupDialog;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.daily_login_bonus_stamp_popup_dialog, viewGroup);
        this.mStampNumber = getArguments().getInt("ArgumentStampNumber");
        this.mShowRowAnimation = getArguments().getBoolean("ArgumentShowAnimation");
        this.mNotShownOnFirstRow = getArguments().getBoolean("ArgumentNotShownOnMainFeed");
        this.mStampDelay = getArguments().getInt("ArgumentStampFadeDelay");
        this.mStampEarnedImage = (ImageView) inflate.findViewById(R.id.daily_login_bonus_stamp_earned_image);
        this.mStampEarnedText = (TextView) inflate.findViewById(R.id.daily_login_bonus_stamp_earned_text);
        this.mTitleAbove = (TextView) inflate.findViewById(R.id.daily_login_bonus_title_above_stamp);
        this.mTitleBelow = (TextView) inflate.findViewById(R.id.daily_login_bonus_title_below_stamp);
        this.mSubtitleText = (TextView) inflate.findViewById(R.id.daily_login_bonus_subtitle);
        this.mStampEarnedAboveText = (TextView) inflate.findViewById(R.id.daily_login_bonus_stamp_earned_text_above);
        this.mMenuPromptText = (TextView) inflate.findViewById(R.id.daily_login_bonus_menu_prompt_text);
        this.mTextContainer = (LinearLayout) inflate.findViewById(R.id.daily_login_bonus_stamp_text_container);
        this.mStampContainer = (LinearLayout) inflate.findViewById(R.id.daily_login_bonus_stamp_earned_container);
        this.mSubtitleContainer = (LinearLayout) inflate.findViewById(R.id.daily_login_bonus_subtitle_above);
        Resources resources = WishApplication.getInstance().getResources();
        StringBuilder sb = new StringBuilder();
        sb.append("daily_login_bonus_stamp_");
        sb.append(this.mStampNumber);
        int identifier = resources.getIdentifier(sb.toString(), "drawable", getBaseActivity().getApplicationContext().getPackageName());
        Resources resources2 = WishApplication.getInstance().getResources();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("stamp_earned_");
        sb2.append(this.mStampNumber);
        int identifier2 = resources2.getIdentifier(sb2.toString(), "string", getBaseActivity().getApplicationContext().getPackageName());
        if (identifier != 0) {
            this.mStampEarnedImage.setImageResource(identifier);
        }
        if (identifier2 != 0) {
            this.mStampEarnedText.setText(identifier2);
            if (ExperimentDataCenter.getInstance().shouldSeeDailyLoginAfterNewUserSplash()) {
                this.mStampEarnedText.setVisibility(0);
            }
        }
        if (ExperimentDataCenter.getInstance().shouldSeeDailyLoginAfterNewUserSplash()) {
            this.mTitleAbove.setText(R.string.daily_login_bonus);
            this.mTitleAbove.setVisibility(0);
            if (getArguments().getString("ArgumentSubtitle") != null) {
                this.mTitleBelow.setText(getArguments().getString("ArgumentSubtitle"));
            } else {
                this.mTitleBelow.setVisibility(8);
            }
        } else if (this.mNotShownOnFirstRow) {
            if (getArguments().getString("ArgumentMenuPrompt") != null) {
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.dailybonus_icon);
                int dimensionPixelSize = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.daily_login_not_on_main_feed_menu_prompt_image_size);
                drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                this.mMenuPromptText.setCompoundDrawables(drawable, null, null, null);
                this.mMenuPromptText.setText(getArguments().getString("ArgumentMenuPrompt"));
                this.mMenuPromptText.setVisibility(0);
            }
            this.mTitleAbove.setText(R.string.daily_login_bonus);
            this.mTitleAbove.setVisibility(0);
            this.mTextContainer.setVisibility(8);
            this.mSubtitleContainer.setVisibility(0);
            if (getArguments().getString("ArgumentSubtitle") != null) {
                this.mSubtitleText.setText(getArguments().getString("ArgumentSubtitle"));
            }
            if (identifier2 != 0) {
                this.mStampEarnedAboveText.setText(identifier2);
            }
        }
        inflate.setLayoutParams(new LayoutParams(getDialogWidth(), -1));
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void onPopInEnded() {
        animateStampToStampRow(this.mStampNumber);
    }

    private void animateStampToStampRow(final int i) {
        withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(final BaseActivity baseActivity) {
                final StampRowView stampRow = baseActivity.getUiFragment("FragmentTagMainContent") instanceof BrowseFragment ? ((BrowseFragment) baseActivity.getUiFragment("FragmentTagMainContent")).getStampRow() : null;
                DailyLoginBonusStampPopupDialog.this.mStampContainer.postDelayed(new Runnable() {
                    public void run() {
                        if (DailyLoginBonusStampPopupDialog.this.getBaseActivity() != null && (baseActivity instanceof DrawerActivity) && ((DrawerActivity) baseActivity).isMenuOpen()) {
                            ((DrawerActivity) baseActivity).closeMenu();
                        }
                        if (stampRow == null || !DailyLoginBonusStampPopupDialog.this.mShowRowAnimation) {
                            DailyLoginBonusStampPopupDialog.this.cancel();
                        } else {
                            DailyLoginBonusStampPopupDialog.this.animateStamp(stampRow, i);
                        }
                    }
                }, (long) DailyLoginBonusStampPopupDialog.this.mStampDelay);
            }
        });
    }

    public void stripToStampView() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        this.mTextContainer.startAnimation(alphaAnimation);
    }

    public void animateStamp(final StampRowView stampRowView, int i) {
        stripToStampView();
        final DailyLoginBonusStampView stamp = stampRowView.getStamp(i);
        if (stamp == null) {
            cancel();
            return;
        }
        float width = ((float) stamp.getWidth()) / ((float) this.mStampEarnedImage.getWidth());
        float height = ((float) stamp.getHeight()) / ((float) this.mStampEarnedImage.getHeight());
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, (stamp.getX() + stampRowView.getXOffsetForStamps()) - this.mStampContainer.getX(), 0.0f, (stamp.getY() + stampRowView.getYOffsetForStamps()) - this.mStampContainer.getY());
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, width, 1.0f, height);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.setDuration(500);
        animationSet.setFillAfter(true);
        animationSet.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                stamp.stampWithoutAnimation();
                stampRowView.setupAutomaticDisappearance();
                DailyLoginBonusStampPopupDialog.this.cancel();
            }
        });
        this.mStampContainer.startAnimation(animationSet);
    }

    public Margin getDialogMargin() {
        int i;
        int i2 = 0;
        if (getBaseActivity() != null) {
            if (getBaseActivity().getSupportActionBar() != null) {
                i2 = getBaseActivity().getSupportActionBar().getHeight();
            }
            i = i2 + DisplayUtil.getStatusBarHeight();
        } else {
            i = 0;
        }
        Margin margin = new Margin(0, i, 0, 0);
        return margin;
    }

    public int getDialogWidth() {
        int displayWidth = DisplayUtil.getDisplayWidth();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.bottom_dialog_fragment_max_width);
        return displayWidth > dimensionPixelSize ? dimensionPixelSize : displayWidth;
    }
}
