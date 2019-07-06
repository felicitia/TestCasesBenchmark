package com.contextlogic.wish.activity.rewards.redesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishRedeemableRewardItem;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class RedeemRewardDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    private ThemedTextView mCancelRedeemButton;
    private ThemedTextView mDescription;
    private ThemedButton mRedeemButton;
    private RewardsRowItem mRewardsRowItem;
    private ThemedTextView mTitle;
    /* access modifiers changed from: private */
    public WishRedeemableRewardItem mWishRedeemableRewardItem;

    public static RedeemRewardDialogFragment<BaseActivity> createRedeemRewardDialog(WishRedeemableRewardItem wishRedeemableRewardItem) {
        RedeemRewardDialogFragment<BaseActivity> redeemRewardDialogFragment = new RedeemRewardDialogFragment<>();
        Bundle bundle = new Bundle();
        bundle.putParcelable("ArgumentRedeemableReward", wishRedeemableRewardItem);
        redeemRewardDialogFragment.setArguments(bundle);
        return redeemRewardDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mWishRedeemableRewardItem = (WishRedeemableRewardItem) getArguments().getParcelable("ArgumentRedeemableReward");
        if (this.mWishRedeemableRewardItem == null) {
            return null;
        }
        View inflate = layoutInflater.inflate(R.layout.redeem_reward_dialog_fragment, viewGroup, false);
        this.mRewardsRowItem = (RewardsRowItem) inflate.findViewById(R.id.redeem_reward_dialog_row_item);
        this.mRewardsRowItem.setItem(this.mWishRedeemableRewardItem, null);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.redeem_reward_dialog_title);
        this.mDescription = (ThemedTextView) inflate.findViewById(R.id.redeem_reward_dialog_description);
        this.mRedeemButton = (ThemedButton) inflate.findViewById(R.id.redeem_reward_dialog_redeem_button);
        this.mCancelRedeemButton = (ThemedTextView) inflate.findViewById(R.id.redeem_reward_dialog_cancel_redeem_button);
        this.mTitle.setText(this.mWishRedeemableRewardItem.getDialogTitle());
        this.mDescription.setText(this.mWishRedeemableRewardItem.getDialogDescription());
        this.mRedeemButton.setText(this.mWishRedeemableRewardItem.getRedeemButtonText());
        this.mCancelRedeemButton.setText(this.mWishRedeemableRewardItem.getCancelRedeemButtonText());
        this.mRedeemButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                RedeemRewardDialogFragment.this.handleRedeem(RedeemRewardDialogFragment.this.mWishRedeemableRewardItem.getRewardType());
            }
        });
        this.mCancelRedeemButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                RedeemRewardDialogFragment.this.handleCancel();
            }
        });
        return inflate;
    }

    /* access modifiers changed from: private */
    public void handleRedeem(final int i) {
        withServiceFragment(new ServiceTask<BaseActivity, RewardsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, RewardsServiceFragment rewardsServiceFragment) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_REWARDS_REDEEM_REWARD);
                rewardsServiceFragment.redeemReward(i);
                RedeemRewardDialogFragment.this.cancel();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleCancel() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_REWARDS_CANCEL_REDEEM);
        cancel();
    }
}
