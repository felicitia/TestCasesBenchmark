package com.contextlogic.wish.activity.feed.newusergiftpack;

import android.content.Context;
import android.view.View;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.CollapsableFeedHeaderView;
import com.contextlogic.wish.activity.feed.CollapsableFeedHeaderView.QuadraticClipInterpolator;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.api.model.NewUserGiftPackSpec.LargeHeaderSpec;
import com.contextlogic.wish.api.model.NewUserGiftPackSpec.SmallHeaderSpec;

public class GiftPackFeedCollapsableHeaderView extends CollapsableFeedHeaderView {
    private GiftPackFeedHeaderView mLargeHeaderView;
    private GiftPackFeedSmallHeaderView mSmallHeaderView;

    public boolean addViewsToParent() {
        return false;
    }

    public GiftPackFeedCollapsableHeaderView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setInterpolator(new QuadraticClipInterpolator(0.75f));
    }

    public void setup(ProductFeedFragment<DrawerActivity> productFeedFragment, SmallHeaderSpec smallHeaderSpec, LargeHeaderSpec largeHeaderSpec) {
        this.mSmallHeaderView = new GiftPackFeedSmallHeaderView(getContext());
        this.mLargeHeaderView = new GiftPackFeedHeaderView(getContext());
        this.mSmallHeaderView.setup(smallHeaderSpec, productFeedFragment);
        this.mLargeHeaderView.setup(largeHeaderSpec, productFeedFragment);
        updateUI(false);
    }

    public View getExpandedView() {
        return this.mLargeHeaderView;
    }

    public View getCollapsedView() {
        return this.mSmallHeaderView;
    }

    public void releaseImages() {
        if (this.mLargeHeaderView != null) {
            this.mLargeHeaderView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mLargeHeaderView != null) {
            this.mLargeHeaderView.restoreImages();
        }
    }
}
