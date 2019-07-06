package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.LoginService.LoginContext;
import com.contextlogic.wish.api.service.standalone.LoginService.SuccessCallback;
import com.contextlogic.wish.http.HttpCookieManager;
import com.contextlogic.wish.util.PreferenceUtil;
import com.facebook.AccessToken;
import java.util.Date;
import org.json.JSONException;

public class FacebookLoginService extends LoginService {
    public void requestService(String str, boolean z, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        String token = AccessToken.getCurrentAccessToken().getToken();
        Date expires = AccessToken.getCurrentAccessToken().getExpires();
        if (token == null || token.isEmpty() || expires == null) {
            parseFailure(null, null, defaultFailureCallback);
            return;
        }
        ApiRequest apiRequest = new ApiRequest("fb-login");
        apiRequest.addParameter("fb_uid", (Object) str);
        apiRequest.addParameter("fb_access_token", (Object) token);
        apiRequest.addParameter("expires", expires.getTime() / 1000);
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
        loginContext.fbId = str;
        HttpCookieManager.getInstance().setSessionCookie(null);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                FacebookLoginService.this.parseFailure(apiResponse, str, defaultFailureCallback);
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                FacebookLoginService.this.parseSuccess(loginContext, apiResponse, successCallback);
            }
        });
    }
}
