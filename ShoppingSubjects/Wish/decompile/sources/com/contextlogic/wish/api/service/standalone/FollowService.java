package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.MultiApiService;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;

public class FollowService extends MultiApiService {
    public void requestService(final String str, final DefaultSuccessCallback defaultSuccessCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("user/follow");
        apiRequest.addParameter("followee_id", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    FollowService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) {
                if (defaultSuccessCallback != null) {
                    ApplicationEventManager.getInstance().triggerEvent(EventType.USER_FOLLOW, str, null);
                    FollowService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultSuccessCallback.onSuccess();
                        }
                    });
                }
            }

            public String getCallIdentifier() {
                return str;
            }
        });
    }

    /* access modifiers changed from: protected */
    public int[] getSuccessLikeErrorCodes() {
        return new int[]{10, 11};
    }
}
