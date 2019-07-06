package com.contextlogic.wish.activity.dailybonus;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class DailyLoginBonusStampView extends LinearLayout {
    /* access modifiers changed from: private */
    public ImageView mDiscountEmptyTrophyView;
    private ImageView mEmptyTrophyView;
    /* access modifiers changed from: private */
    public TextView mFillerTextView;
    private LinearLayout mFinalStampDiscountContainer;
    /* access modifiers changed from: private */
    public ThemedTextView mFinalStampDiscountText;
    /* access modifiers changed from: private */
    public View mFinalStampDottedLine;
    /* access modifiers changed from: private */
    public ImageView mInnerStampView;
    /* access modifiers changed from: private */
    public boolean mIsLastStamp;
    /* access modifiers changed from: private */
    public LinearLayout mStampBackground;
    private FrameLayout mStampImageContainer;
    private int mStampNumber;
    /* access modifiers changed from: private */
    public AutoReleasableImageView mStampView;

    public DailyLoginBonusStampView(Context context) {
        super(context);
        init();
    }

    public DailyLoginBonusStampView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.daily_login_bonus_popup_stampview, this);
        this.mFillerTextView = (TextView) inflate.findViewById(R.id.daily_login_bonus_popup_stamp_filler_text);
        this.mStampView = (AutoReleasableImageView) inflate.findViewById(R.id.daily_login_bonus_popup_stamp_image);
        this.mInnerStampView = (ImageView) inflate.findViewById(R.id.daily_login_bonus_popup_inner_stamp_image);
        this.mFinalStampDottedLine = inflate.findViewById(R.id.daily_login_bonus_last_stamp_dotted_line);
        this.mFinalStampDiscountContainer = (LinearLayout) inflate.findViewById(R.id.daily_login_bonus_stamp_discount_container);
        this.mFinalStampDiscountText = (ThemedTextView) inflate.findViewById(R.id.daily_login_bonus_last_stamp_discount_text);
        this.mStampBackground = (LinearLayout) inflate.findViewById(R.id.daily_login_bonus_stamp_background);
        this.mStampImageContainer = (FrameLayout) inflate.findViewById(R.id.daily_login_bonus_popup_stamp_image_container);
        this.mEmptyTrophyView = (ImageView) inflate.findViewById(R.id.daily_login_bonus_popup_empty_trophy);
        this.mDiscountEmptyTrophyView = (ImageView) inflate.findViewById(R.id.daily_login_bonus_discount_text_empty_trophy);
    }

    public void stampWithAnimation() {
        this.mStampView.setVisibility(4);
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.stamp_pop_animation);
        loadAnimation.setStartTime(1000);
        loadAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                if (DailyLoginBonusStampView.this.mIsLastStamp) {
                    DailyLoginBonusStampView.this.mInnerStampView.setVisibility(0);
                    DailyLoginBonusStampView.this.mFillerTextView.setVisibility(8);
                    DailyLoginBonusStampView.this.mFinalStampDottedLine.setVisibility(8);
                    DailyLoginBonusStampView.this.mDiscountEmptyTrophyView.setVisibility(8);
                    DailyLoginBonusStampView.this.mFinalStampDiscountText.setTextColor(ContextCompat.getColor(DailyLoginBonusStampView.this.getContext(), R.color.daily_login_bonus_final_stamp_text_color));
                    ((LayoutParams) DailyLoginBonusStampView.this.mStampBackground.getLayoutParams()).setMargins(0, 0, 0, 0);
                    return;
                }
                DailyLoginBonusStampView.this.mStampView.setVisibility(0);
            }

            public void onAnimationEnd(Animation animation) {
                if (DailyLoginBonusStampView.this.mIsLastStamp) {
                    DailyLoginBonusStampView.this.mStampBackground.setBackgroundResource(R.drawable.daily_login_bonus_stamp_final_background);
                } else {
                    DailyLoginBonusStampView.this.mStampBackground.setBackground(null);
                }
            }
        });
        if (this.mIsLastStamp) {
            this.mStampBackground.startAnimation(loadAnimation);
        } else {
            this.mStampView.startAnimation(loadAnimation);
        }
    }

    public void stampWithoutAnimation() {
        this.mStampView.setVisibility(0);
        this.mFillerTextView.setVisibility(8);
        this.mStampBackground.setBackground(null);
    }

    public void setToRowStamp() {
        this.mFillerTextView.setTextSize(0, (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.daily_login_bonus_dialog_stamp_row_filler_text_size));
        this.mFillerTextView.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.stamp_row_stamp_filler_size);
        this.mFillerTextView.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.stamp_row_stamp_filler_size);
        this.mStampBackground.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.dotted_light));
    }

    public void setLastStamp(int i, int i2, String str, int i3) {
        this.mIsLastStamp = true;
        setStampNumber(i3);
        this.mFinalStampDottedLine.setVisibility(0);
        this.mFinalStampDiscountContainer.setVisibility(0);
        this.mStampImageContainer.getLayoutParams().width = getResources().getDimensionPixelSize(i);
        this.mStampImageContainer.getLayoutParams().height = getResources().getDimensionPixelSize(i);
        Resources resources = WishApplication.getInstance().getResources();
        StringBuilder sb = new StringBuilder();
        sb.append("daily_login_bonus_stamp_");
        sb.append(i3);
        this.mInnerStampView.setImageResource(resources.getIdentifier(sb.toString(), "drawable", WishApplication.getInstance().getPackageName()));
        this.mInnerStampView.getLayoutParams().width = getResources().getDimensionPixelSize(i2);
        this.mInnerStampView.getLayoutParams().height = getResources().getDimensionPixelSize(i2);
        this.mFinalStampDiscountText.setFontResizable(true);
        this.mFinalStampDiscountText.setText(str);
        if (ExperimentDataCenter.getInstance().shouldShowDayPrizesFiftyPercent()) {
            this.mFinalStampDiscountText.setTextSize(0, (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.daily_login_bonus_dialog_fifty_percent_text_size));
        }
    }

    public void setStampNumber(int i) {
        Resources resources = WishApplication.getInstance().getResources();
        StringBuilder sb = new StringBuilder();
        sb.append("daily_login_bonus_stamp_");
        sb.append(i);
        int identifier = resources.getIdentifier(sb.toString(), "drawable", WishApplication.getInstance().getPackageName());
        this.mStampNumber = i;
        this.mStampView.setImageResource(identifier);
        this.mFillerTextView.setText(String.valueOf(i));
    }

    public void clearStamp() {
        this.mStampView.setVisibility(8);
        this.mInnerStampView.setVisibility(8);
        this.mFillerTextView.setVisibility(0);
        this.mStampBackground.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.dotted_light));
    }

    public void setToTrophy() {
        this.mEmptyTrophyView.setVisibility(0);
        this.mFillerTextView.setVisibility(8);
        this.mStampView.setVisibility(8);
    }
}
