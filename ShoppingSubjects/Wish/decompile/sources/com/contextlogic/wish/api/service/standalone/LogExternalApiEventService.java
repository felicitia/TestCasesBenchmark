package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.api.service.compound.AuthenticationService.LoginMode;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONException;

public class LogExternalApiEventService extends SingleApiService {
    public void logApiError(LoginMode loginMode, String str, Integer num, Integer num2) {
        HashMap hashMap = new HashMap();
        if (num != null) {
            hashMap.put("google_error_code", num.toString());
        }
        if (num2 != null) {
            hashMap.put("facebook_error_code", num2.toString());
        }
        requestService(loginMode, false, str, hashMap);
    }

    public void logApiSuccess(LoginMode loginMode) {
        requestService(loginMode, true, null, null);
    }

    public void requestService(LoginMode loginMode, boolean z, String str, HashMap<String, String> hashMap) {
        ApiRequest apiRequest = new ApiRequest("log-external-api-event");
        String str2 = "unknown";
        if (loginMode == LoginMode.FACEBOOK) {
            str2 = "facebook";
        } else if (loginMode == LoginMode.GOOGLE) {
            str2 = "google";
        }
        apiRequest.addParameter("api_type", (Object) str2);
        apiRequest.addParameter("success", z);
        if (!z) {
            apiRequest.addParameter("error", (Object) str);
        }
        if (hashMap != null) {
            for (Entry entry : hashMap.entrySet()) {
                apiRequest.addParameter((String) entry.getKey(), entry.getValue());
            }
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
            }
        });
    }
}
