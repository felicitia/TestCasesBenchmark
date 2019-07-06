package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishNotificationPreference;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.SingleApiService;

public class SaveNotificationPreferencesService extends SingleApiService {

    public enum PreferenceType {
        EMAIL,
        PUSH
    }

    public void requestService(WishNotificationPreference wishNotificationPreference, DefaultSuccessCallback defaultSuccessCallback, DefaultFailureCallback defaultFailureCallback) {
        requestService(wishNotificationPreference.getIndex(), wishNotificationPreference.isPreferenceSelected(PreferenceType.EMAIL.ordinal()).booleanValue(), wishNotificationPreference.isPreferenceSelected(PreferenceType.PUSH.ordinal()).booleanValue(), false, defaultSuccessCallback, defaultFailureCallback);
    }

    public void requestService(int i, boolean z, boolean z2, boolean z3, final DefaultSuccessCallback defaultSuccessCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("settings/set");
        apiRequest.addParameter("setting_value", (Object) "grouped");
        apiRequest.addParameter("setting_id", i);
        apiRequest.addParameter("setting_email_value", z);
        apiRequest.addParameter("setting_push_value", z2);
        apiRequest.addParameter("is_master_value", z3);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    SaveNotificationPreferencesService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) {
                if (defaultSuccessCallback != null) {
                    SaveNotificationPreferencesService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultSuccessCallback.onSuccess();
                        }
                    });
                }
            }
        });
    }
}
