package com.contextlogic.wish.activity.exampleugc;

import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetExampleReviewsService;
import com.contextlogic.wish.api.service.standalone.GetExampleReviewsService.SuccessCallback;
import java.util.ArrayList;
import java.util.HashMap;

public class ExampleUgcItemsServiceFragment extends ServiceFragment<ExampleUgcItemsActivity> {
    private GetExampleReviewsService mGetExampleReviewsService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetExampleReviewsService = new GetExampleReviewsService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetExampleReviewsService.cancelAllRequests();
    }

    public void getExampleUgcItems() {
        this.mGetExampleReviewsService.requestService(null, new SuccessCallback() {
            public void onSuccess(final ArrayList<WishRating> arrayList, final HashMap<String, String> hashMap) {
                ExampleUgcItemsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ExampleUgcItemsFragment>() {
                    public void performTask(BaseActivity baseActivity, ExampleUgcItemsFragment exampleUgcItemsFragment) {
                        exampleUgcItemsFragment.handleLoadingSuccess(arrayList, hashMap);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                ExampleUgcItemsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ExampleUgcItemsFragment>() {
                    public void performTask(BaseActivity baseActivity, ExampleUgcItemsFragment exampleUgcItemsFragment) {
                        exampleUgcItemsFragment.handleLoadingFailure(str);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }
}
