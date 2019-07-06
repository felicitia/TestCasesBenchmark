package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.LoginService.LoginContext;
import com.contextlogic.wish.api.service.standalone.LoginService.SuccessCallback;
import com.contextlogic.wish.http.HttpCookieManager;
import com.contextlogic.wish.util.PreferenceUtil;
import org.json.JSONException;

public class EmailLoginService extends LoginService {
    public void requestService(String str, String str2, boolean z, SuccessCallback successCallback, DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("email-login");
        apiRequest.addParameter("email", (Object) str);
        apiRequest.addParameter("password", (Object) str2);
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
        loginContext.email = str;
        HttpCookieManager.getInstance().setSessionCookie(null);
        final DefaultFailureCallback defaultFailureCallback2 = defaultFailureCallback;
        final String str3 = str;
        final String str4 = str2;
        final SuccessCallback successCallback2 = successCallback;
        AnonymousClass1 r2 = new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                EmailLoginService.this.parseFailure(apiResponse, str, defaultFailureCallback2);
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                PreferenceUtil.setString("user_login_email", str3);
                PreferenceUtil.setString("user_login_password", str4);
                EmailLoginService.this.parseSuccess(loginContext, apiResponse, successCallback2);
            }
        };
        startService(apiRequest, r2);
    }
}
