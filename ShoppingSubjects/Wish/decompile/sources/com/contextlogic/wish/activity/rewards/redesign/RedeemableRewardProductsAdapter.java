package com.contextlogic.wish.activity.rewards.redesign;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishRewardsRedeemableInfo;
import com.contextlogic.wish.ui.grid.StaggeredGridView.Adapter;
import com.contextlogic.wish.util.ValueUtil;
import java.util.List;

public class RedeemableRewardProductsAdapter extends Adapter {
    private int mAvailablePoints;
    private Context mContext;
    private OnClickListener mOnClickListener;
    private List<WishProduct> mProducts;

    public interface OnClickListener {
        void onImageClicked(WishProduct wishProduct);

        void onRedeemItemClicked(WishProduct wishProduct);
    }

    public RedeemableRewardProductsAdapter(Context context) {
        this.mContext = context;
    }

    public void setup(WishRewardsRedeemableInfo wishRewardsRedeemableInfo, OnClickListener onClickListener) {
        this.mProducts = wishRewardsRedeemableInfo.getRedeemableProducts();
        this.mAvailablePoints = wishRewardsRedeemableInfo.getAvailablePoints();
        this.mOnClickListener = onClickListener;
    }

    public int getCount() {
        if (this.mProducts == null) {
            return 0;
        }
        return this.mProducts.size();
    }

    public WishProduct getItem(int i) {
        if (i < getCount()) {
            return (WishProduct) this.mProducts.get(i);
        }
        return null;
    }

    public int getItemHeight(int i, int i2) {
        return i2 + ((int) ValueUtil.convertDpToPx(64.0f));
    }

    public int getItemMargin() {
        return (int) ValueUtil.convertDpToPx(16.0f);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        RedeemableRewardsProductTileView redeemableRewardsProductTileView;
        if (view instanceof RedeemableRewardsProductTileView) {
            redeemableRewardsProductTileView = (RedeemableRewardsProductTileView) view;
        } else {
            redeemableRewardsProductTileView = new RedeemableRewardsProductTileView(this.mContext);
        }
        WishProduct item = getItem(i);
        redeemableRewardsProductTileView.setup(item, this.mOnClickListener, i);
        redeemableRewardsProductTileView.setEnabled(item.getValueInPointsForVariation(item.getDefaultCommerceVariationId()) > 0 && item.getValueInPointsForVariation(item.getDefaultCommerceVariationId()) <= this.mAvailablePoints);
        return redeemableRewardsProductTileView;
    }
}
