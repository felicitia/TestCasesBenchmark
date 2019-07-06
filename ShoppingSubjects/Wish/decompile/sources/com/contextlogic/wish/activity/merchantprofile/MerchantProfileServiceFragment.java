package com.contextlogic.wish.activity.merchantprofile;

import android.os.Bundle;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.activity.productdetails.FilterType;
import com.contextlogic.wish.api.model.WishMerchantTopCategory;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.api.model.WishRatingSummary;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetMerchantFeedService;
import com.contextlogic.wish.api.service.standalone.GetMerchantRatingsService;
import com.contextlogic.wish.api.service.standalone.GetMerchantRatingsService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.GetMerchantTopCategoriesService;
import com.contextlogic.wish.api.service.standalone.GetMerchantTopCategoriesService.FeedContext;
import java.util.ArrayList;

public class MerchantProfileServiceFragment extends BaseProductFeedServiceFragment {
    private GetMerchantFeedService mGetMerchantFeedService;
    private GetMerchantRatingsService mGetMerchantRatingsService;
    private GetMerchantTopCategoriesService mGetMerchantTopCategoriesService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetMerchantRatingsService = new GetMerchantRatingsService();
        this.mGetMerchantFeedService = new GetMerchantFeedService();
        this.mGetMerchantTopCategoriesService = new GetMerchantTopCategoriesService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetMerchantRatingsService.cancelAllRequests();
        this.mGetMerchantFeedService.cancelAllRequests();
        this.mGetMerchantTopCategoriesService.cancelAllRequests();
    }

    public void loadMerchantRatings(String str, int i, int i2) {
        this.mGetMerchantRatingsService.requestService(str, i, i2, FilterType.ALL, new SuccessCallback() {
            public void onSuccess(WishRatingSummary wishRatingSummary, final ArrayList<WishRating> arrayList, int i, final int i2, final boolean z) {
                MerchantProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, MerchantProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, MerchantProfileFragment merchantProfileFragment) {
                        merchantProfileFragment.handleRatingsLoaded(arrayList, z, i2);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                MerchantProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, MerchantProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, MerchantProfileFragment merchantProfileFragment) {
                        merchantProfileFragment.handleRatingsFailed();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadMerchantTopCategories(FeedContext feedContext) {
        this.mGetMerchantTopCategoriesService.requestService(feedContext, new GetMerchantTopCategoriesService.SuccessCallback() {
            public void onSuccess(final ArrayList<WishMerchantTopCategory> arrayList, final String str) {
                MerchantProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, MerchantProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, MerchantProfileFragment merchantProfileFragment) {
                        merchantProfileFragment.handleMerchantTopCategoriesLoaded(arrayList, str);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                MerchantProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, MerchantProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, MerchantProfileFragment merchantProfileFragment) {
                        merchantProfileFragment.handleMerchantTopCategoriesFailed();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public boolean isMerchantRatingsPending() {
        return this.mGetMerchantRatingsService.isPending();
    }

    public void cancelLoadingMerchantRatings() {
        this.mGetMerchantRatingsService.cancelAllRequests();
    }

    public void cancelLoadingMerchantTopCategories() {
        this.mGetMerchantTopCategoriesService.cancelAllRequests();
    }
}
