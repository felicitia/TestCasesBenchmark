package com.contextlogic.wish.activity.rewards.redesign;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.dialog.popupanimation.PopupAnimationDialogFragment;

public class RedeemableRewardsConfirmationDialogFragment<A extends BaseActivity> extends PopupAnimationDialogFragment<A> {
    private String mDescriptionText;
    private String mTitleText;

    /* access modifiers changed from: protected */
    public int getLayout() {
        return R.layout.reward_added_popup_animation_holder;
    }

    public static RedeemableRewardsConfirmationDialogFragment<BaseActivity> createRedeemableRewardsConfirmationDialogFragment(String str, String str2) {
        RedeemableRewardsConfirmationDialogFragment<BaseActivity> redeemableRewardsConfirmationDialogFragment = new RedeemableRewardsConfirmationDialogFragment<>();
        Bundle bundle = new Bundle();
        bundle.putString("ArgumentTitle", str);
        bundle.putString("ArgumentDescription", str2);
        redeemableRewardsConfirmationDialogFragment.setArguments(bundle);
        return redeemableRewardsConfirmationDialogFragment;
    }

    /* access modifiers changed from: protected */
    public void handleArguments(Bundle bundle) {
        this.mTitleText = bundle.getString("ArgumentTitle");
        this.mDescriptionText = bundle.getString("ArgumentDescription");
    }

    /* access modifiers changed from: protected */
    public View getPopupView() {
        return new RewardAddedPopupView(getContext(), this.mTitleText, this.mDescriptionText);
    }

    /* access modifiers changed from: protected */
    public ViewGroup getPopupHolder(View view) {
        return (ViewGroup) view.findViewById(R.id.item_added_popup_holder);
    }

    /* access modifiers changed from: protected */
    public int getPopupHeight() {
        return getResources().getDimensionPixelOffset(R.dimen.reward_added_dialog_height);
    }

    /* access modifiers changed from: protected */
    public OnClickListener getPopupClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                RedeemableRewardsConfirmationDialogFragment.this.makeSelection(new Bundle());
            }
        };
    }
}
