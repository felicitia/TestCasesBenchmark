package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.standalone.LoginService.LoginContext;
import com.contextlogic.wish.api.service.standalone.LoginService.SuccessCallback;
import com.contextlogic.wish.http.HttpCookieManager;
import com.contextlogic.wish.util.PreferenceUtil;
import org.json.JSONException;

public class EmailSignupService extends LoginService {
    public void requestService(String str, String str2, String str3, String str4, SuccessCallback successCallback, DefaultCodeFailureCallback defaultCodeFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("email-signup");
        apiRequest.addParameter("first_name", (Object) str);
        apiRequest.addParameter("last_name", (Object) str2);
        apiRequest.addParameter("email", (Object) str3);
        apiRequest.addParameter("password", (Object) str4);
        String string = PreferenceUtil.getString("AppReferrer");
        if (string != null && !PreferenceUtil.getBoolean("ReferrerLoginSent")) {
            apiRequest.addParameter("app_referrer", (Object) string);
        }
        final LoginContext loginContext = new LoginContext();
        loginContext.email = str3;
        HttpCookieManager.getInstance().setSessionCookie(null);
        final DefaultCodeFailureCallback defaultCodeFailureCallback2 = defaultCodeFailureCallback;
        final String str5 = str3;
        final String str6 = str4;
        final SuccessCallback successCallback2 = successCallback;
        AnonymousClass1 r1 = new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                EmailSignupService.this.parseFailureMoreDetail(apiResponse == null ? -1 : apiResponse.getCode(), str, defaultCodeFailureCallback2);
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                PreferenceUtil.setString("user_login_email", str5);
                PreferenceUtil.setString("user_login_password", str6);
                EmailSignupService.this.parseSuccess(loginContext, apiResponse, successCallback2);
            }
        };
        startService(apiRequest, r1);
    }
}
