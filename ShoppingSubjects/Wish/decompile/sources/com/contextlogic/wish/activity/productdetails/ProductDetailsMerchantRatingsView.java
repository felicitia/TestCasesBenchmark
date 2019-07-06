package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.content.Intent;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.merchantprofile.MerchantProfileActivity;
import com.contextlogic.wish.activity.productdetails.ProductDetailsFragment.BooleanWrapper;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import java.util.ArrayList;

public class ProductDetailsMerchantRatingsView extends ProductDetailsRatingsView {
    /* access modifiers changed from: private */
    public String mMerchantId;

    public ProductDetailsMerchantRatingsView(Context context) {
        super(context);
    }

    public void setup(WishProduct wishProduct, int i, ProductDetailsFragment productDetailsFragment, ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mMerchantId = wishProduct.getMerchantId();
        super.setup(wishProduct, i, productDetailsFragment, imageHttpPrefetcher);
    }

    /* access modifiers changed from: protected */
    public void setUpFilters() {
        if (ExperimentDataCenter.getInstance().shouldSeeProductRatingFilter()) {
            this.mFilterButtons = new ArrayList();
            addFilterButton(FilterType.ALL).setChecked(true);
            addFilterButton(FilterType.RATING_5);
            addFilterButton(FilterType.RATING_4);
            addFilterButton(FilterType.RATING_3);
            addFilterButton(FilterType.RATING_2);
            addFilterButton(FilterType.RATING_1);
            this.mFilterGroup.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public String getActionButtonText() {
        return this.mFragment.getString(R.string.ratings_visit_store);
    }

    /* access modifiers changed from: protected */
    public boolean isNetworkRequestPending() {
        final BooleanWrapper booleanWrapper = new BooleanWrapper(true);
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                booleanWrapper.state = productDetailsServiceFragment.isMerchantRatingsPending();
            }
        });
        return booleanWrapper.state;
    }

    /* access modifiers changed from: protected */
    public void cancelNetworkRequest() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                productDetailsServiceFragment.cancelLoadingMerchantRatings();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void performNetworkRequest() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, ProductDetailsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsServiceFragment productDetailsServiceFragment) {
                productDetailsServiceFragment.loadMerchantRatings(ProductDetailsMerchantRatingsView.this.mMerchantId, ProductDetailsMerchantRatingsView.this.mNextOffset, 25, ProductDetailsMerchantRatingsView.this.getSelectedFilterType());
            }
        });
    }

    public void handleActionClick() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_RATINGS_VISIT_STORE);
        this.mFragment.withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                Intent intent = new Intent();
                intent.setClass(WishApplication.getInstance(), MerchantProfileActivity.class);
                intent.putExtra(MerchantProfileActivity.EXTRA_MERCHANT, ProductDetailsMerchantRatingsView.this.mRatingSummary.getName());
                intent.putExtra(MerchantProfileActivity.EXTRA_MERCHANT_ID, ProductDetailsMerchantRatingsView.this.mMerchantId);
                productDetailsActivity.startActivity(intent);
            }
        });
    }
}
