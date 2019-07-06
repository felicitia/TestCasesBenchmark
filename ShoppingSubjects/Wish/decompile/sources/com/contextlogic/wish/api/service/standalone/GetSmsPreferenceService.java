package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;

public class GetSmsPreferenceService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(boolean z);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("settings/get"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetSmsPreferenceService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) {
                if (apiResponse.hasData()) {
                    final boolean optBoolean = apiResponse.getData().optBoolean("user_sms_blocked", false);
                    if (successCallback != null) {
                        GetSmsPreferenceService.this.postRunnable(new Runnable() {
                            public void run() {
                                successCallback.onSuccess(optBoolean);
                            }
                        });
                    }
                }
            }
        });
    }
}
