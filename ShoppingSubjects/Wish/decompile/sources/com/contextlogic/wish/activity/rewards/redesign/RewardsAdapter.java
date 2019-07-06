package com.contextlogic.wish.activity.rewards.redesign;

import android.widget.BaseAdapter;
import com.contextlogic.wish.api.model.WishRedeemableRewardItem;
import java.util.List;

public abstract class RewardsAdapter extends BaseAdapter {
    private List<WishRedeemableRewardItem> mRedeemableRewardItems;

    public abstract void setAnimateDashboard(boolean z);

    public void setWishRewardItems(List<WishRedeemableRewardItem> list) {
        this.mRedeemableRewardItems = list;
    }

    public int getCount() {
        if (this.mRedeemableRewardItems != null) {
            return this.mRedeemableRewardItems.size();
        }
        return 0;
    }

    public WishRedeemableRewardItem getItem(int i) {
        if (this.mRedeemableRewardItems != null) {
            return (WishRedeemableRewardItem) this.mRedeemableRewardItems.get(i);
        }
        return null;
    }
}
