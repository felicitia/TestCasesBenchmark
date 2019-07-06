package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.SingleApiService;

public class OnfidoCreateCheckService extends SingleApiService {
    public void requestService(String str, final DefaultSuccessCallback defaultSuccessCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("onfido/create-check");
        apiRequest.addParameter("applicant_id", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                OnfidoCreateCheckService.this.postRunnable(new Runnable() {
                    public void run() {
                        if (defaultFailureCallback != null) {
                            defaultFailureCallback.onFailure(str);
                        }
                    }
                });
            }

            public void handleSuccess(ApiResponse apiResponse) {
                OnfidoCreateCheckService.this.postRunnable(new Runnable() {
                    public void run() {
                        if (defaultSuccessCallback != null) {
                            defaultSuccessCallback.onSuccess();
                        }
                    }
                });
            }
        });
    }
}
