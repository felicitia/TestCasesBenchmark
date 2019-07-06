package com.contextlogic.wish.api.service;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishReturnPolicyInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import java.text.ParseException;
import org.json.JSONException;

public class GetReturnPolicyInfoService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishReturnPolicyInfo wishReturnPolicyInfo);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("return-policy/get-info"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetReturnPolicyInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishReturnPolicyInfo wishReturnPolicyInfo = new WishReturnPolicyInfo(apiResponse.getData());
                if (successCallback != null) {
                    GetReturnPolicyInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishReturnPolicyInfo);
                        }
                    });
                }
            }
        });
    }
}
