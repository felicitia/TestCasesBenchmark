package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.MultiApiService;

public class CategoryUnwishService extends MultiApiService {
    public void requestService(final String str, final DefaultSuccessCallback defaultSuccessCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("mobile/signup-unwish-category");
        apiRequest.addParameter("category_id", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (apiResponse == null || apiResponse.getCode() != 10) {
                    if (defaultFailureCallback != null) {
                        CategoryUnwishService.this.postRunnable(new Runnable() {
                            public void run() {
                                defaultFailureCallback.onFailure(str);
                            }
                        });
                    }
                } else if (defaultSuccessCallback != null) {
                    CategoryUnwishService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultSuccessCallback.onSuccess();
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) {
                if (defaultSuccessCallback != null) {
                    CategoryUnwishService.this.postRunnable(new Runnable() {
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
}
