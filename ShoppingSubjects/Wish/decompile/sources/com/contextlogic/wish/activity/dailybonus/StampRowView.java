package com.contextlogic.wish.activity.dailybonus;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.feed.BaseFeedHeaderView;
import com.contextlogic.wish.api.model.WishDailyLoginStampSpec;
import java.util.ArrayList;

public class StampRowView extends BaseFeedHeaderView {
    /* access modifiers changed from: private */
    public Callback mCallback;
    private TextView mDeadlineText;
    private Runnable mDisappearanceRunnable = new Runnable() {
        public void run() {
            StampRowView.this.clearAnimation();
            if (!StampRowView.this.mLeaveVisible) {
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) (-StampRowView.this.getHeight()));
                translateAnimation.setDuration(500);
                translateAnimation.setAnimationListener(new AnimationListener() {
                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        StampRowView.this.mCallback.removeRowFromContainer();
                    }
                });
                StampRowView.this.startAnimation(translateAnimation);
            }
        }
    };
    private View mDivider;
    private WishDailyLoginStampSpec mInfo;
    /* access modifiers changed from: private */
    public boolean mLeaveVisible;
    private TextView mRowTitle;
    private DailyLoginBonusStampView mStamp1;
    private DailyLoginBonusStampView mStamp2;
    private DailyLoginBonusStampView mStamp3;
    private DailyLoginBonusStampView mStamp4;
    private DailyLoginBonusStampView mStamp5;
    private DailyLoginBonusStampView mStamp6;
    private DailyLoginBonusStampView mStamp7;
    private LinearLayout mStampRowViewContainer;
    private LinearLayout mStampScrollLinearLayout;
    private ScrollView mStampScrollView;
    private ArrayList<DailyLoginBonusStampView> mStampViews;
    private View mTextContainer;

    public interface Callback {
        float getYPositionInContainer();

        void removeRowFromContainer();

        void showStampRowDialog(WishDailyLoginStampSpec wishDailyLoginStampSpec, boolean z);
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public StampRowView(Context context) {
        super(context);
        init();
    }

    public StampRowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        setVisibility(8);
        int i = 0;
        this.mLeaveVisible = false;
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.daily_login_bonus_stamp_row_view, this);
        this.mDeadlineText = (TextView) inflate.findViewById(R.id.daily_login_bonus_stamp_row_deadline_text);
        this.mRowTitle = (TextView) inflate.findViewById(R.id.daily_login_bonus_stamp_row_discount_title);
        this.mStamp1 = (DailyLoginBonusStampView) inflate.findViewById(R.id.stamp_row_stamp_1);
        this.mStamp2 = (DailyLoginBonusStampView) inflate.findViewById(R.id.stamp_row_stamp_2);
        this.mStamp3 = (DailyLoginBonusStampView) inflate.findViewById(R.id.stamp_row_stamp_3);
        this.mStamp4 = (DailyLoginBonusStampView) inflate.findViewById(R.id.stamp_row_stamp_4);
        this.mStamp5 = (DailyLoginBonusStampView) inflate.findViewById(R.id.stamp_row_stamp_5);
        this.mStamp6 = (DailyLoginBonusStampView) inflate.findViewById(R.id.stamp_row_stamp_6);
        this.mStamp7 = (DailyLoginBonusStampView) inflate.findViewById(R.id.stamp_row_stamp_7);
        this.mStampViews = new ArrayList<>();
        this.mStampViews.add(this.mStamp1);
        this.mStampViews.add(this.mStamp2);
        this.mStampViews.add(this.mStamp3);
        this.mStampViews.add(this.mStamp4);
        this.mStampViews.add(this.mStamp5);
        this.mStampViews.add(this.mStamp6);
        this.mStampViews.add(this.mStamp7);
        while (i < this.mStampViews.size()) {
            if (i == this.mStampViews.size() - 1) {
                ((DailyLoginBonusStampView) this.mStampViews.get(i)).setToTrophy();
            }
            int i2 = i + 1;
            ((DailyLoginBonusStampView) this.mStampViews.get(i)).setStampNumber(i2);
            ((DailyLoginBonusStampView) this.mStampViews.get(i)).setToRowStamp();
            i = i2;
        }
        this.mStampScrollLinearLayout = (LinearLayout) inflate.findViewById(R.id.stamp_row_linear_layout);
        this.mStampScrollView = (ScrollView) inflate.findViewById(R.id.stamp_row_scrollview);
        this.mStampRowViewContainer = (LinearLayout) inflate.findViewById(R.id.stamp_row_row_container);
        this.mDivider = inflate.findViewById(R.id.stamp_row_divider_view);
        this.mTextContainer = inflate.findViewById(R.id.daily_login_bonus_stamp_text_container);
    }

    public void setup(WishDailyLoginStampSpec wishDailyLoginStampSpec) {
        setVisibility(0);
        this.mInfo = wishDailyLoginStampSpec;
        TextView textView = this.mDeadlineText;
        StringBuilder sb = new StringBuilder();
        sb.append(getResources().getString(R.string.daily_bonus_offer_ends));
        sb.append(" ");
        sb.append(this.mInfo.getExpiryDate());
        textView.setText(sb.toString());
        this.mRowTitle.setText(this.mInfo.getRowTitle());
        clearStamps();
        int stampNumber = this.mInfo.getStampNumber();
        for (int i = 0; i < stampNumber - 1; i++) {
            if (i < this.mStampViews.size()) {
                ((DailyLoginBonusStampView) this.mStampViews.get(i)).stampWithoutAnimation();
            }
        }
    }

    public void setup(int i, boolean z, int i2, int i3, int i4) {
        setVisibility(0);
        if (z) {
            this.mDivider.setVisibility(8);
            this.mTextContainer.setVisibility(8);
        }
        if (i2 >= 0) {
            this.mStampScrollLinearLayout.setDividerPadding(i2);
            this.mStampScrollLinearLayout.setShowDividers(2);
            for (int i5 = 0; i5 < this.mStampViews.size(); i5++) {
                ((View) this.mStampViews.get(i5)).setLayoutParams(new LayoutParams(i4, i3));
            }
        }
        clearStamps();
        for (int i6 = 0; i6 < i; i6++) {
            if (i6 < this.mStampViews.size()) {
                ((DailyLoginBonusStampView) this.mStampViews.get(i6)).stampWithoutAnimation();
            }
        }
    }

    private void clearStamps() {
        for (int i = 0; i < this.mStampViews.size(); i++) {
            if (i == this.mStampViews.size() - 1) {
                ((DailyLoginBonusStampView) this.mStampViews.get(i)).setToTrophy();
            } else {
                ((DailyLoginBonusStampView) this.mStampViews.get(i)).clearStamp();
            }
        }
    }

    public void setLeaveVisible(boolean z) {
        this.mLeaveVisible = z;
        if (!z) {
            setupAutomaticDisappearance();
        }
    }

    public void setupAutomaticDisappearance() {
        postDelayed(this.mDisappearanceRunnable, 1500);
    }

    public DailyLoginBonusStampView getStamp(int i) {
        if (this.mStampViews == null || i > this.mStampViews.size()) {
            return null;
        }
        return (DailyLoginBonusStampView) this.mStampViews.get(i - 1);
    }

    public float getXOffsetForStamps() {
        return getX() + this.mStampScrollLinearLayout.getX() + this.mStampScrollView.getX() + this.mStampRowViewContainer.getX();
    }

    public float getYOffsetForStamps() {
        return getY() + this.mStampScrollLinearLayout.getY() + this.mStampScrollView.getY() + this.mStampRowViewContainer.getY() + (this.mCallback != null ? this.mCallback.getYPositionInContainer() : 0.0f);
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void cancelAnimations() {
        removeCallbacks(this.mDisappearanceRunnable);
    }
}
