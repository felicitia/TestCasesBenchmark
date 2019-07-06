package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;

public class GetPhoneResetKeyService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(String str, String str2);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("settings/get-phone-reset-key"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetPhoneResetKeyService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) {
                boolean z;
                if (apiResponse != null && apiResponse.hasData()) {
                    final String optString = apiResponse.getData().optString("reset_key");
                    final String optString2 = apiResponse.getData().optString("short_code");
                    if (successCallback != null) {
                        z = true;
                        GetPhoneResetKeyService.this.postRunnable(new Runnable() {
                            public void run() {
                                successCallback.onSuccess(optString, optString2);
                            }
                        });
                        if (!z && defaultFailureCallback != null) {
                            defaultFailureCallback.onFailure(null);
                            return;
                        }
                    }
                }
                z = false;
                if (!z) {
                }
            }
        });
    }
}
