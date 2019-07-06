package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import org.json.JSONException;

public class GetBraintreeClientTokenService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(String str);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("braintree/client-token"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetBraintreeClientTokenService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                final String string = apiResponse.getData().getString("client_token");
                if (successCallback != null) {
                    GetBraintreeClientTokenService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(string);
                        }
                    });
                }
            }
        });
    }
}
