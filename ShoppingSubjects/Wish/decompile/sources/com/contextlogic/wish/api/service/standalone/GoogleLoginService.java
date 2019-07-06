package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.LoginService.LoginContext;
import com.contextlogic.wish.api.service.standalone.LoginService.SuccessCallback;
import com.contextlogic.wish.http.HttpCookieManager;
import com.contextlogic.wish.util.PreferenceUtil;
import org.json.JSONException;

public class GoogleLoginService extends LoginService {
    public void requestService(String str, String str2, String str3, boolean z, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("google-plus-login");
        apiRequest.addParameter("google_plus_id", (Object) str);
        apiRequest.addParameter("google_plus_server_token", (Object) str2);
        apiRequest.addParameter("email", (Object) str3);
        apiRequest.addParameter("session_refresh", z);
        String string = PreferenceUtil.getString("AppReferrer");
        if (string != null && !PreferenceUtil.getBoolean("ReferrerLoginSent")) {
            apiRequest.addParameter("app_referrer", (Object) string);
        }
        String string2 = PreferenceUtil.getString("AdminLoginCode");
        if (string2 != null) {
            apiRequest.addParameter("admin_login_code", (Object) string2);
            PreferenceUtil.setString("AdminLoginCode", null);
        }
        final LoginContext loginContext = new LoginContext();
        loginContext.googleId = str;
        HttpCookieManager.getInstance().setSessionCookie(null);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                GoogleLoginService.this.parseFailure(apiResponse, str, defaultFailureCallback);
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                GoogleLoginService.this.parseSuccess(loginContext, apiResponse, successCallback);
            }
        });
    }
}
