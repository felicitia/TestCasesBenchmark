package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.social.google.GoogleManager;

public class PushUnregistrationService extends SingleApiService {
    private String getPushNotificationType() {
        if (GoogleManager.getInstance().isPlayServicesAvailable()) {
            return "3";
        }
        return null;
    }

    public void requestService(String str, String str2, final DefaultSuccessCallback defaultSuccessCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("mobile/push/unregister");
        apiRequest.addParameter("token", (Object) str2);
        apiRequest.addParameter("type", (Object) getPushNotificationType());
        if (str != null) {
            apiRequest.addParameter("user_id", (Object) str);
        }
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
                if (defaultSuccessCallback != null) {
                    PushUnregistrationService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultSuccessCallback.onSuccess();
                        }
                    });
                }
            }
        });
    }
}
