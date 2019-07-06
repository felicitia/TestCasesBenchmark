package com.contextlogic.wish.activity.imageviewer;

import android.os.Bundle;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.standalone.RemoveUpvoteReviewService;
import com.contextlogic.wish.api.service.standalone.UpvoteReviewService;

public class ImageViewerServiceFragment extends BaseProductFeedServiceFragment {
    private RemoveUpvoteReviewService mRemoveUpvoteReviewService;
    private UpvoteReviewService mUpvoteReviewService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mRemoveUpvoteReviewService = new RemoveUpvoteReviewService();
        this.mUpvoteReviewService = new UpvoteReviewService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mRemoveUpvoteReviewService.cancelAllRequests();
        this.mUpvoteReviewService.cancelAllRequests();
    }

    public void removeProductRatingUpvote(String str) {
        this.mRemoveUpvoteReviewService.requestService(str, new DefaultSuccessCallback() {
            public void onSuccess() {
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
            }
        });
    }

    public void productRatingUpvote(String str) {
        this.mUpvoteReviewService.requestService(str, new DefaultSuccessCallback() {
            public void onSuccess() {
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
            }
        });
    }
}
