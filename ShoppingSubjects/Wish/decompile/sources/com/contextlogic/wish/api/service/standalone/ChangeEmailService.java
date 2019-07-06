package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import org.json.JSONException;

public class ChangeEmailService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(String str);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultCodeFailureCallback defaultCodeFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("settings/email-change");
        apiRequest.addParameter("new_email", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(final ApiResponse apiResponse, final String str) {
                if (defaultCodeFailureCallback != null) {
                    ChangeEmailService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultCodeFailureCallback.onFailure(str, apiResponse != null ? apiResponse.getCode() : -1);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                final String optString = apiResponse.getData() == null ? null : apiResponse.getData().optString("email");
                if (successCallback != null) {
                    ChangeEmailService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(optString);
                        }
                    });
                }
            }
        });
    }
}
