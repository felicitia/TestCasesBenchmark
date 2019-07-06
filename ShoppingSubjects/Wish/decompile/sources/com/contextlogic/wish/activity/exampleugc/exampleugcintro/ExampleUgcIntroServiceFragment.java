package com.contextlogic.wish.activity.exampleugc.exampleugcintro;

import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetExampleReviewsService;
import com.contextlogic.wish.api.service.standalone.GetExampleReviewsService.SuccessCallback;
import java.util.ArrayList;
import java.util.HashMap;

public class ExampleUgcIntroServiceFragment extends ServiceFragment<ExampleUgcIntroActivity> {
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

    public void getExampleUgcItems(Integer num) {
        this.mGetExampleReviewsService.requestService(num, new SuccessCallback() {
            public void onSuccess(final ArrayList<WishRating> arrayList, final HashMap<String, String> hashMap) {
                ExampleUgcIntroServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ExampleUgcIntroFragment>() {
                    public void performTask(BaseActivity baseActivity, ExampleUgcIntroFragment exampleUgcIntroFragment) {
                        exampleUgcIntroFragment.handleLoadingSuccess(arrayList, hashMap);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                ExampleUgcIntroServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ExampleUgcIntroFragment>() {
                    public void performTask(BaseActivity baseActivity, ExampleUgcIntroFragment exampleUgcIntroFragment) {
                        exampleUgcIntroFragment.handleLoadingFailure(str);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }
}
