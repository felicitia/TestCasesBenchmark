package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.productdetails.ProductDetailsFragment.BooleanWrapper;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.http.ImageHttpPrefetcher;

public class ProductDetailsProductRatingsView extends ProductDetailsRatingsView {
    /* access modifiers changed from: private */
    public String mProductId;

    /* access modifiers changed from: protected */
    public String getActionButtonText() {
        return null;
    }

    public void handleActionClick() {
    }

    public boolean shouldShowUpvote() {
        return true;
    }

    public ProductDetailsProductRatingsView(Context context) {
        super(context);
    }

    public void setup(WishProduct wishProduct, int i, ProductDetailsFragment productDetailsFragment, ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mProductId = wishProduct.getProductId();
        super.setup(wishProduct, i, productDetailsFragment, imageHttpPrefetcher);
    }

    /* access modifiers changed from: protected */
    public void setUpFilters() {
        if (ExperimentDataCenter.getInstance().shouldSeeProductRatingFilter()) {
            addFilterButton(FilterType.ALL).setChecked(true);
            addFilterButton(FilterType.PHOTO);
            addFilterButton(FilterType.TOP);
            addFilterButton(FilterType.RATING_5);
            addFilterButton(FilterType.RATING_4);
            addFilterButton(FilterType.RATING_3);
            addFilterButton(FilterType.RATING_2);
            addFilterButton(FilterType.RATING_1);
            this.mFilterGroup.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isNetworkRequestPending() {
        final BooleanWrapper booleanWrapper = new BooleanWrapper(true);
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                booleanWrapper.state = productDetailsServiceFragment.isProductRatingsPending();
            }
        });
        return booleanWrapper.state;
    }

    /* access modifiers changed from: protected */
    public void cancelNetworkRequest() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                productDetailsServiceFragment.cancelLoadingProductRatings();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void performNetworkRequest() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                productDetailsServiceFragment.loadProductRatings(ProductDetailsProductRatingsView.this.mProductId, ProductDetailsProductRatingsView.this.mNextOffset, 20, ProductDetailsProductRatingsView.this.getSelectedFilterType());
            }
        });
    }
}
