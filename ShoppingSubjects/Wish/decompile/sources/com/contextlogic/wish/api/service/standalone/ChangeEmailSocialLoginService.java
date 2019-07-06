package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class ChangeEmailSocialLoginService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(String str);
    }

    public void requestService(final String str, final SuccessCallback successCallback, final DefaultCodeFailureCallback defaultCodeFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("settings/email-change-social");
        apiRequest.addParameter("new_email", (Object) str);
        String email = ProfileDataCenter.getInstance().getEmail();
        if (email == null || !str.equalsIgnoreCase(email)) {
            startService(apiRequest, new ApiCallback() {
                public String getCallIdentifier() {
                    return null;
                }

                public void handleFailure(final ApiResponse apiResponse, final String str) {
                    if (defaultCodeFailureCallback != null) {
                        ChangeEmailSocialLoginService.this.postRunnable(new Runnable() {
                            public void run() {
                                defaultCodeFailureCallback.onFailure(str, apiResponse != null ? apiResponse.getCode() : -1);
                            }
                        });
                    }
                }

                public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                    final String optString = apiResponse.getData() == null ? null : apiResponse.getData().optString("email");
                    if (successCallback != null) {
                        ChangeEmailSocialLoginService.this.postRunnable(new Runnable() {
                            public void run() {
                                successCallback.onSuccess(optString);
                            }
                        });
                    }
                }
            });
            return;
        }
        if (successCallback != null) {
            postRunnable(new Runnable() {
                public void run() {
                    successCallback.onSuccess(str);
                }
            });
        }
    }
}
