package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.DeviceUtil;

public class ServerPingService extends SingleApiService {
    public void requestService(String str, String str2, String str3, String str4, final DefaultSuccessCallback defaultSuccessCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("mobile/app/ping");
        apiRequest.addParameter("type", (Object) DeviceUtil.getClientId());
        apiRequest.addParameter("device_id", (Object) str3);
        apiRequest.addParameter("phone_carrier", (Object) str4);
        if (str != null) {
            apiRequest.addParameter("referrer", (Object) str);
        }
        if (str2 != null) {
            apiRequest.addParameter("advertiser_id", (Object) str2);
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    ServerPingService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) {
                if (defaultSuccessCallback != null) {
                    ServerPingService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultSuccessCallback.onSuccess();
                        }
                    });
                }
            }
        });
    }
}
