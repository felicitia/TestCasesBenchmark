package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.NotificationUtil;
import com.google.firebase.iid.FirebaseInstanceId;

public class PushRegistrationService extends SingleApiService {
    public void requestService(String str, final DefaultSuccessCallback defaultSuccessCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("mobile/push/register");
        apiRequest.addParameter("token", (Object) str);
        apiRequest.addParameter("type", (Object) NotificationUtil.getPushNotificationType());
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    PushRegistrationService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) {
                if (defaultSuccessCallback != null) {
                    PushRegistrationService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultSuccessCallback.onSuccess();
                        }
                    });
                }
            }
        });
    }

    public static void registerPushNotificationToken() {
        String token = FirebaseInstanceId.getInstance().getToken();
        if (token != null) {
            if (AuthenticationDataCenter.getInstance().isLoggedIn()) {
                new PushRegistrationService().requestService(token, null, null);
            }
            if (WishApplication.getInstance().isWishApp()) {
                new PreSignupPushRegistrationService().requestService(token, null, null);
            }
        }
    }
}
