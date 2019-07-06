package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;

public class PreverifyPaypalEmailService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(String str, String str2);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("partner/account/paypal/preverify");
        apiRequest.addParameter("paypal_email", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                if (defaultFailureCallback != null) {
                    defaultFailureCallback.onFailure(str);
                }
            }

            public void handleSuccess(ApiResponse apiResponse) {
                if (successCallback != null) {
                    final String optString = JsonUtil.optString(apiResponse.getData(), "success_title");
                    final String optString2 = JsonUtil.optString(apiResponse.getData(), "success_message");
                    PreverifyPaypalEmailService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(optString, optString2);
                        }
                    });
                }
            }
        });
    }
}
