package com.contextlogic.wish.activity.rewards.redesign;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.rewards.redesign.RedeemableRewardsAdapter.Callback;
import com.contextlogic.wish.api.model.WishRedeemableRewardItem;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class RewardsRowItem extends FrameLayout {
    private ThemedTextView mDescriptionText;
    private View mDisabledContainer;
    private ThemedTextView mDiscountBadge;
    private ThemedTextView mLeftBadgeText;
    private ThemedTextView mRightBadgeText;
    private ThemedTextView mTitleText;

    public RewardsRowItem(Context context) {
        this(context, null);
    }

    public RewardsRowItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.rewards_fragment_redesign_row, this, true);
        this.mLeftBadgeText = (ThemedTextView) inflate.findViewById(R.id.rewards_fragment_redesign_row_left_badge);
        this.mRightBadgeText = (ThemedTextView) inflate.findViewById(R.id.rewards_fragment_redesign_row_right_badge);
        this.mDiscountBadge = (ThemedTextView) inflate.findViewById(R.id.rewards_fragment_redesign_row_discount);
        this.mDiscountBadge.setFontResizable(true);
        this.mDiscountBadge.setMaxLines(1);
        this.mTitleText = (ThemedTextView) inflate.findViewById(R.id.rewards_fragment_redesign_row_title);
        this.mTitleText.setFontResizable(true);
        this.mTitleText.setMaxLines(1);
        this.mDescriptionText = (ThemedTextView) inflate.findViewById(R.id.rewards_fragment_redesign_row_expiry_date);
        this.mDescriptionText.setFontResizable(true);
        this.mDescriptionText.setMaxLines(1);
        this.mDisabledContainer = inflate.findViewById(R.id.rewards_fragment_redesign_row_disabled_container);
    }

    public void setItem(final WishRedeemableRewardItem wishRedeemableRewardItem, final Callback callback) {
        this.mTitleText.setTextSize(0, (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.text_size_subtitle));
        this.mTitleText.setText(wishRedeemableRewardItem.getTitle());
        this.mDescriptionText.setTextSize(0, (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.text_size_body));
        this.mDescriptionText.setText(wishRedeemableRewardItem.getDescription());
        this.mDiscountBadge.setTextSize(0, (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.rewards_dashboard_item_discount_text_size));
        this.mDiscountBadge.setText(wishRedeemableRewardItem.getDiscountBadgeText());
        if (wishRedeemableRewardItem.isDisabled()) {
            this.mDisabledContainer.setVisibility(0);
        } else {
            this.mDisabledContainer.setVisibility(8);
        }
        if (wishRedeemableRewardItem.getBadgeText() != null) {
            this.mLeftBadgeText.setText(wishRedeemableRewardItem.getBadgeText());
            this.mLeftBadgeText.setVisibility(0);
            if (wishRedeemableRewardItem.isExpiring()) {
                this.mLeftBadgeText.setBackgroundResource(R.drawable.rewards_left_badge_red_bg);
            } else {
                this.mLeftBadgeText.setBackgroundResource(R.drawable.rewards_left_badge_blue_bg);
            }
        } else {
            this.mLeftBadgeText.setVisibility(8);
        }
        if (wishRedeemableRewardItem.getPointsText() != null) {
            this.mRightBadgeText.setText(wishRedeemableRewardItem.getPointsText());
            this.mRightBadgeText.setVisibility(0);
        } else {
            this.mRightBadgeText.setVisibility(8);
        }
        if (callback != null) {
            setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    callback.onClickListener(wishRedeemableRewardItem);
                }
            });
        }
    }
}
