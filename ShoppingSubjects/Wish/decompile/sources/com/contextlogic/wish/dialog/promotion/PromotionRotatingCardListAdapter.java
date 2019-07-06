package com.contextlogic.wish.dialog.promotion;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishPromotionRotatingSpec.SmallCardSpec;
import com.contextlogic.wish.ui.listview.HorizontalListView.Adapter;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.TabletUtil;
import java.util.ArrayList;

public class PromotionRotatingCardListAdapter extends Adapter {
    private ArrayList<SmallCardSpec> mCardSpecs;
    private Context mContext;
    private int mHeight;
    private int mInnerMargin;
    private int mOuterMargin;
    private int mWidth;

    public PromotionRotatingCardListAdapter(Context context, ArrayList<SmallCardSpec> arrayList) {
        this.mContext = context;
        this.mCardSpecs = arrayList;
        this.mInnerMargin = context.getResources().getDimensionPixelSize(R.dimen.promotion_rotating_small_card_list_inner_margin);
        this.mOuterMargin = context.getResources().getDimensionPixelSize(R.dimen.promotion_rotating_small_card_list_outer_margin);
        this.mHeight = context.getResources().getDimensionPixelSize(R.dimen.promotion_rotating_small_card_height);
        float f = (!DisplayUtil.isLandscape() || !TabletUtil.isTablet()) ? 2.0f : 4.0f;
        if (((float) getCount()) > f) {
            f += 0.25f;
        }
        this.mWidth = (int) (((float) (DisplayUtil.getDisplayWidth() - getMarginSpace((int) f))) / f);
    }

    private int getMarginSpace(int i) {
        return getLeadMargin() + getTrailingMargin() + ((i - 1) * getItemMargin());
    }

    public int getExpectedListWidth() {
        return getMarginSpace(getCount()) + (getCount() * this.mWidth);
    }

    public int getItemWidth(int i) {
        return this.mWidth;
    }

    public int getItemHeight(int i) {
        return this.mHeight;
    }

    public int getItemMargin() {
        return this.mInnerMargin;
    }

    public int getLeadMargin() {
        return this.mOuterMargin;
    }

    public int getTrailingMargin() {
        return this.mOuterMargin;
    }

    public int getCount() {
        if (this.mCardSpecs == null) {
            return 0;
        }
        return this.mCardSpecs.size();
    }

    public SmallCardSpec getItem(int i) {
        if (i < getCount()) {
            return (SmallCardSpec) this.mCardSpecs.get(i);
        }
        return null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        PromotionRotatingSmallCardView promotionRotatingSmallCardView;
        SmallCardSpec item = getItem(i);
        if (item == null) {
            return null;
        }
        if (view != null) {
            promotionRotatingSmallCardView = (PromotionRotatingSmallCardView) view;
        } else {
            promotionRotatingSmallCardView = new PromotionRotatingSmallCardView(this.mContext);
        }
        promotionRotatingSmallCardView.setup(item);
        return promotionRotatingSmallCardView;
    }
}
