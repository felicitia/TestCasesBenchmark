package com.contextlogic.wish.activity.search;

import android.os.Bundle;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.SearchAutocompleteService;
import com.contextlogic.wish.api.service.standalone.SearchLandingPageService;
import com.contextlogic.wish.api.service.standalone.SearchLandingPageService.SuccessCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import java.util.ArrayList;

public class SearchServiceFragment extends ServiceFragment<SearchActivity> {
    private SearchAutocompleteService mSearchAutocompleteService;
    private SearchLandingPageService mSearchLandingPageService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mSearchAutocompleteService = new SearchAutocompleteService();
        this.mSearchLandingPageService = new SearchLandingPageService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mSearchAutocompleteService.cancelAllRequests();
        this.mSearchLandingPageService.cancelAllRequests();
    }

    public void loadSearchContent() {
        this.mSearchLandingPageService.requestService(new SuccessCallback() {
            public void onSuccess(final ArrayList<String> arrayList, final ArrayList<WishProduct> arrayList2, final ArrayList<WishProduct> arrayList3) {
                SearchServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SearchFragment>() {
                    public void performTask(BaseActivity baseActivity, SearchFragment searchFragment) {
                        searchFragment.handleContentLoadingSuccess(arrayList, arrayList2, arrayList3);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                SearchServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SearchFragment>() {
                    public void performTask(BaseActivity baseActivity, SearchFragment searchFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                        searchFragment.handleContentLoadingErrored();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void fetchAutocompleteResults(String str) {
        this.mSearchAutocompleteService.requestService(str, new SearchAutocompleteService.SuccessCallback() {
            public void onSuccess(final ArrayList<String> arrayList) {
                SearchServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SearchFragment>() {
                    public void performTask(BaseActivity baseActivity, SearchFragment searchFragment) {
                        searchFragment.updateAutocompleteResults(arrayList);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                SearchServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SearchFragment>() {
                    public void performTask(BaseActivity baseActivity, SearchFragment searchFragment) {
                        searchFragment.updateAutocompleteResults(null);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }
}
