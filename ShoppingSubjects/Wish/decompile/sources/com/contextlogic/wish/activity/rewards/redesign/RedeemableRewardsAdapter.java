package com.contextlogic.wish.activity.rewards.redesign;

import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.api.model.WishRedeemableRewardItem;
import com.contextlogic.wish.ui.listview.ListViewTabStrip.TextTabProvider;

public class RedeemableRewardsAdapter extends RewardsAdapter implements TextTabProvider {
    private Callback mCallback;
    private final RewardsFragment mFragment;
    private final String mTitle;

    public interface Callback {
        void onClickListener(WishRedeemableRewardItem wishRedeemableRewardItem);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public boolean isEnabled(int i) {
        return true;
    }

    public void setAnimateDashboard(boolean z) {
    }

    public RedeemableRewardsAdapter(RewardsFragment rewardsFragment, String str) {
        this.mFragment = rewardsFragment;
        this.mTitle = str;
    }

    public void setOnClickListener(Callback callback) {
        this.mCallback = callback;
    }

    public RewardsRowItem getView(int i, View view, ViewGroup viewGroup) {
        RewardsRowItem rewardsRowItem;
        if (view == null || !(view instanceof RewardsRowItem)) {
            rewardsRowItem = new RewardsRowItem(this.mFragment.getContext());
        } else {
            rewardsRowItem = (RewardsRowItem) view;
        }
        rewardsRowItem.setItem(getItem(i), this.mCallback);
        return rewardsRowItem;
    }

    public String getPageTitle() {
        return this.mTitle;
    }
}
