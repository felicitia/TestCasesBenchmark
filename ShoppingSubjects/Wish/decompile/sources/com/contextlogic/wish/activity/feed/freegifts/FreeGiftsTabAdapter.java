package com.contextlogic.wish.activity.feed.freegifts;

import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.BaseProductFeedAdapter;
import com.contextlogic.wish.activity.feed.BaseProductFeedFragment;

public class FreeGiftsTabAdapter extends BaseProductFeedAdapter {
    private BaseProductFeedFragment mFragment;

    public FreeGiftsTabAdapter(DrawerActivity drawerActivity, BaseProductFeedFragment baseProductFeedFragment) {
        super(drawerActivity, baseProductFeedFragment);
        this.mFragment = baseProductFeedFragment;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        FreeGiftsTabCellView freeGiftsTabCellView;
        if (view != null) {
            freeGiftsTabCellView = (FreeGiftsTabCellView) view;
        } else {
            freeGiftsTabCellView = new FreeGiftsTabCellView(getBaseActivity(), this.mFragment);
            freeGiftsTabCellView.setImagePrefetcher(this.mImagePrefetcher);
        }
        freeGiftsTabCellView.setProduct(getItem(i));
        return freeGiftsTabCellView;
    }

    public int getItemHeight(int i, int i2) {
        return FreeGiftsTabCellView.getExpectedHeight(getItem(i), i2);
    }
}
