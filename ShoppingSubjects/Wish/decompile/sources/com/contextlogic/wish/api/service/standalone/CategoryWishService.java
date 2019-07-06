package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.MultiApiService;

public class CategoryWishService extends MultiApiService {
    public void requestService(final String str, boolean z, final DefaultSuccessCallback defaultSuccessCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("mobile/signup-wish-category");
        apiRequest.addParameter("category_id", (Object) str);
        apiRequest.addParameter("from_signup_flow", z);
        startService(apiRequest, new ApiCallback() {
            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    CategoryWishService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) {
                if (defaultSuccessCallback != null) {
                    CategoryWishService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultSuccessCallback.onSuccess();
                        }
                    });
                }
            }

            public String getCallIdentifier() {
                return str;
            }
        });
    }

    /* access modifiers changed from: protected */
    public int[] getSuccessLikeErrorCodes() {
        return new int[]{10};
    }
}
