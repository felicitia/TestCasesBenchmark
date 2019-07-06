package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.NotificationUtil;

public class PreSignupPushRegistrationService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(String str);
    }

    public void requestService(final String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("mobile/push/save-token");
        apiRequest.addParameter("token", (Object) str);
        apiRequest.addParameter("type", (Object) NotificationUtil.getPushNotificationType());
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    PreSignupPushRegistrationService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) {
                if (successCallback != null) {
                    PreSignupPushRegistrationService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(str);
                        }
                    });
                }
            }
        });
    }
}
