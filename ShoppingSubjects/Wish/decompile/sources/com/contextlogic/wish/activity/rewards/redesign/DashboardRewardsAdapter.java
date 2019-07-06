package com.contextlogic.wish.activity.rewards.redesign;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.rewards.redesign.RedeemableRewardsAdapter.Callback;
import com.contextlogic.wish.api.model.WishRedeemableRewardItem;
import com.contextlogic.wish.ui.listview.ListViewTabStrip.TextTabProvider;

public class DashboardRewardsAdapter extends RewardsAdapter implements TextTabProvider {
    private boolean mAnimateDashboard = false;
    private Callback mCallback;
    private final RewardsFragment mFragment;
    private final RedeemableRewardsType mRewardsType;
    private final String mTitle;

    public enum RedeemableRewardsType {
        DASHBOARD_AVAILABLE_REWARDS,
        DASHBOARD_USED_REWARDS,
        REDEEMABLE_REWARDS
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public boolean isEnabled(int i) {
        return true;
    }

    public DashboardRewardsAdapter(RewardsFragment rewardsFragment, String str, RedeemableRewardsType redeemableRewardsType) {
        this.mFragment = rewardsFragment;
        this.mTitle = str;
        this.mRewardsType = redeemableRewardsType;
    }

    public void setOnClickListener(Callback callback) {
        this.mCallback = callback;
    }

    public int getItemViewType(int i) {
        WishRedeemableRewardItem item = getItem(i);
        return (item == null || TextUtils.isEmpty(item.getPromoCode())) ? 0 : 1;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        RewardsRowItem rewardsRowItem;
        CouponRewardRowView couponRewardRowView;
        WishRedeemableRewardItem item = getItem(i);
        if (item == null) {
            return null;
        }
        if (getItemViewType(i) == 1) {
            if (view == null) {
                couponRewardRowView = new CouponRewardRowView(this.mFragment.getContext());
            } else {
                couponRewardRowView = (CouponRewardRowView) view;
            }
            if (this.mAnimateDashboard && i == 0) {
                couponRewardRowView.startAnimation(AnimationUtils.loadAnimation(couponRewardRowView.getContext(), R.anim.scale_popup));
                this.mAnimateDashboard = false;
            }
            couponRewardRowView.setItem(item, this.mCallback, this.mRewardsType);
            return couponRewardRowView;
        }
        if (view == null || !(view instanceof RewardsRowItem)) {
            rewardsRowItem = new RewardsRowItem(this.mFragment.getContext());
        } else {
            rewardsRowItem = (RewardsRowItem) view;
        }
        rewardsRowItem.setItem(item, this.mCallback);
        return rewardsRowItem;
    }

    public String getPageTitle() {
        return this.mTitle;
    }

    public void setAnimateDashboard(boolean z) {
        this.mAnimateDashboard = z;
    }
}
