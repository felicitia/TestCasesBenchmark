package com.contextlogic.wish.activity.rewards.redesign;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.rewards.redesign.DashboardRewardsAdapter.RedeemableRewardsType;
import com.contextlogic.wish.activity.rewards.redesign.RedeemableRewardsAdapter.Callback;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishRedeemableRewardItem;
import com.contextlogic.wish.util.ClipboardUtil;
import com.contextlogic.wish.util.FontUtil;

public class CouponRewardRowView extends FrameLayout {
    private TextView mBadgeText;
    private TextView mCouponText;
    private TextView mDiscountPercentText;
    private TextView mExpiresText;
    private TextView mSubtitleText;
    private TextView mTitleText;

    public boolean hasOverlappingRendering() {
        return false;
    }

    public CouponRewardRowView(Context context) {
        this(context, null);
    }

    public CouponRewardRowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.coupon_reward_row_view, this, true);
        this.mDiscountPercentText = (TextView) findViewById(R.id.coupon_reward_row_discount_percent);
        this.mTitleText = (TextView) findViewById(R.id.coupon_reward_row_title);
        this.mSubtitleText = (TextView) findViewById(R.id.coupon_reward_row_subtitle);
        this.mCouponText = (TextView) findViewById(R.id.coupon_reward_row_coupon);
        this.mExpiresText = (TextView) findViewById(R.id.coupon_reward_row_expiry);
        this.mBadgeText = (TextView) findViewById(R.id.coupon_reward_row_badge);
    }

    public void setItem(final WishRedeemableRewardItem wishRedeemableRewardItem, final Callback callback, RedeemableRewardsType redeemableRewardsType) {
        this.mDiscountPercentText.setText(wishRedeemableRewardItem.getDiscountBadgeText());
        this.mTitleText.setText(wishRedeemableRewardItem.getTitle());
        this.mSubtitleText.setText(wishRedeemableRewardItem.getDescription());
        if (wishRedeemableRewardItem.isDisabled() || wishRedeemableRewardItem.isExpired() || wishRedeemableRewardItem.isUsed()) {
            setAlpha(0.75f);
        } else {
            setAlpha(1.0f);
        }
        if (redeemableRewardsType == RedeemableRewardsType.DASHBOARD_USED_REWARDS) {
            this.mBadgeText.setVisibility(8);
        } else if (wishRedeemableRewardItem.getBadgeText() != null) {
            this.mBadgeText.setVisibility(0);
            this.mBadgeText.setText(wishRedeemableRewardItem.getBadgeText());
            if (wishRedeemableRewardItem.isExpiring()) {
                this.mBadgeText.getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.yellow_dark), Mode.SRC_ATOP);
            } else {
                this.mBadgeText.getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.main_primary), Mode.SRC_ATOP);
            }
        } else if (wishRedeemableRewardItem.isExpired()) {
            this.mBadgeText.setVisibility(0);
            this.mBadgeText.setText(R.string.expired);
            this.mBadgeText.getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.red_dark), Mode.SRC_ATOP);
        } else {
            this.mBadgeText.setVisibility(8);
        }
        if (TextUtils.isEmpty(wishRedeemableRewardItem.getExpiryText())) {
            this.mExpiresText.setVisibility(4);
        } else {
            this.mExpiresText.setVisibility(0);
            this.mExpiresText.setText(wishRedeemableRewardItem.getExpiryText());
        }
        if (!TextUtils.isEmpty(wishRedeemableRewardItem.getPromoCode())) {
            this.mCouponText.setVisibility(0);
            this.mCouponText.setText(wishRedeemableRewardItem.getPromoCode());
            if (redeemableRewardsType == RedeemableRewardsType.DASHBOARD_AVAILABLE_REWARDS) {
                this.mCouponText.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (ClipboardUtil.copyToClipboard(wishRedeemableRewardItem.getPromoCode())) {
                            CouponRewardRowView.this.showCopiedToast();
                        }
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_REWARDS_WALLET_COPY_COUPON);
                    }
                });
            }
        } else {
            this.mCouponText.setVisibility(8);
        }
        if (callback != null) {
            setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    callback.onClickListener(wishRedeemableRewardItem);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void showCopiedToast() {
        TextView textView;
        Drawable drawable;
        Toast makeText = Toast.makeText(getContext(), R.string.copied, 0);
        View view = makeText.getView();
        if (view == null) {
            textView = null;
        } else {
            textView = (TextView) view.findViewById(16908299);
        }
        if (view == null) {
            drawable = null;
        } else {
            drawable = view.getBackground();
        }
        if (!(drawable == null || textView == null)) {
            drawable.setColorFilter(ContextCompat.getColor(getContext(), R.color.gray1), Mode.SRC_ATOP);
            textView.setTypeface(FontUtil.getTypefaceForStyle(0));
            textView.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), R.drawable.check_white_64x44), null, null, null);
            textView.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.eight_padding));
            textView.setTextColor(-1);
        }
        makeText.show();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_REWARDS_WALLET_COUPON_REWARD);
    }
}
