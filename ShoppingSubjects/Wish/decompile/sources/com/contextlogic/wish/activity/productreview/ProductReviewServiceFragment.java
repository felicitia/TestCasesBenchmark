package com.contextlogic.wish.activity.productreview;

import android.os.Bundle;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishProductReviewItem;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetProductReviewInfoService;
import com.contextlogic.wish.api.service.standalone.GetProductReviewInfoService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.UploadProductReviewService;
import java.util.ArrayList;

public class ProductReviewServiceFragment extends ServiceFragment<ProductReviewActivity> {
    private GetProductReviewInfoService mProductReviewInfoService;
    private UploadProductReviewService mUploadProductReviewService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mProductReviewInfoService = new GetProductReviewInfoService();
        this.mUploadProductReviewService = new UploadProductReviewService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mProductReviewInfoService.cancelAllRequests();
        this.mUploadProductReviewService.cancelAllRequests();
    }

    public void getItemsWithoutUgc() {
        this.mProductReviewInfoService.requestService(new SuccessCallback() {
            public void onSuccess(final ArrayList<WishProductReviewItem> arrayList) {
                ProductReviewServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductReviewFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductReviewFragment productReviewFragment) {
                        productReviewFragment.handleInitialItemsLoadingSuccess(arrayList);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                ProductReviewServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductReviewFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductReviewFragment productReviewFragment) {
                        productReviewFragment.handleInitialItemsLoadingFailure(str);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void uploadRating(Bundle bundle) {
        this.mUploadProductReviewService.requestService((WishProductReviewItem) bundle.getParcelable(ProductReviewPagerView.PRODUCT_KEY), bundle.getInt(ProductReviewPagerView.PRODUCT_STAR_RATING_KEY), bundle.getInt(ProductReviewPagerView.PRODUCT_SIZE_CHOICE_KEY), bundle.getString(ProductReviewPagerView.PRODUCT_RATING_COMMENT_KEY), bundle.getInt(ProductReviewPagerView.MERCHANT_STAR_RATING_KEY), bundle.getString(ProductReviewPagerView.MERCHANT_RATING_COMMENT_KEY), bundle.getString(ProductReviewPagerView.PRODUCT_UPLOADED_IMAGE_NAME_KEY), bundle.getString(ProductReviewPagerView.PRODUCT_UPLOADED_VIDEO_ID_KEY), new UploadProductReviewService.SuccessCallback() {
            public void onSuccess(final ArrayList<WishProductReviewItem> arrayList, final int i) {
                ProductReviewServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductReviewFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductReviewFragment productReviewFragment) {
                        productReviewFragment.handleReviewSubmissionSuccess(arrayList, i);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                ProductReviewServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductReviewFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductReviewFragment productReviewFragment) {
                        productReviewFragment.handleReviewSubmissionFailure(str);
                    }
                });
            }
        });
    }
}
