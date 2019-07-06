package com.facebook.login;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookServiceException;
import com.facebook.f;
import com.facebook.internal.z;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import java.util.Collection;
import java.util.Locale;

abstract class WebLoginMethodHandler extends LoginMethodHandler {
    private static final String WEB_VIEW_AUTH_HANDLER_STORE = "com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY";
    private static final String WEB_VIEW_AUTH_HANDLER_TOKEN_KEY = "TOKEN";
    private String e2e;

    /* access modifiers changed from: protected */
    public String getSSODevice() {
        return null;
    }

    /* access modifiers changed from: 0000 */
    public abstract AccessTokenSource getTokenSource();

    private static final String getRedirectUri() {
        StringBuilder sb = new StringBuilder();
        sb.append("fb");
        sb.append(f.j());
        sb.append("://authorize");
        return sb.toString();
    }

    WebLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    WebLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    /* access modifiers changed from: protected */
    public Bundle getParameters(Request request) {
        Bundle bundle = new Bundle();
        if (!z.a((Collection<T>) request.getPermissions())) {
            String join = TextUtils.join(",", request.getPermissions());
            bundle.putString("scope", join);
            addLoggingExtra("scope", join);
        }
        bundle.putString("default_audience", request.getDefaultAudience().getNativeProtocolAudience());
        bundle.putString(ResponseConstants.STATE, getClientState(request.getAuthId()));
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        String token = currentAccessToken != null ? currentAccessToken.getToken() : null;
        if (token == null || !token.equals(loadCookieToken())) {
            z.b((Context) this.loginClient.b());
            addLoggingExtra(AccessToken.ACCESS_TOKEN_KEY, "0");
        } else {
            bundle.putString(AccessToken.ACCESS_TOKEN_KEY, token);
            addLoggingExtra(AccessToken.ACCESS_TOKEN_KEY, "1");
        }
        return bundle;
    }

    /* access modifiers changed from: protected */
    public Bundle addExtraParameters(Bundle bundle, Request request) {
        bundle.putString("redirect_uri", getRedirectUri());
        bundle.putString("client_id", request.getApplicationId());
        LoginClient loginClient = this.loginClient;
        bundle.putString("e2e", LoginClient.m());
        bundle.putString("response_type", "token,signed_request");
        bundle.putString("return_scopes", "true");
        bundle.putString("auth_type", request.getAuthType());
        if (getSSODevice() != null) {
            bundle.putString("sso", getSSODevice());
        }
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onComplete(Request request, Bundle bundle, FacebookException facebookException) {
        Result result;
        String str;
        this.e2e = null;
        if (bundle != null) {
            if (bundle.containsKey("e2e")) {
                this.e2e = bundle.getString("e2e");
            }
            try {
                AccessToken createAccessTokenFromWebBundle = createAccessTokenFromWebBundle(request.getPermissions(), bundle, getTokenSource(), request.getApplicationId());
                result = Result.createTokenResult(this.loginClient.c(), createAccessTokenFromWebBundle);
                CookieSyncManager.createInstance(this.loginClient.b()).sync();
                saveCookieToken(createAccessTokenFromWebBundle.getToken());
            } catch (FacebookException e) {
                result = Result.createErrorResult(this.loginClient.c(), null, e.getMessage());
            }
        } else if (facebookException instanceof FacebookOperationCanceledException) {
            result = Result.createCancelResult(this.loginClient.c(), "User canceled log in.");
        } else {
            this.e2e = null;
            String message = facebookException.getMessage();
            if (facebookException instanceof FacebookServiceException) {
                FacebookRequestError requestError = ((FacebookServiceException) facebookException).getRequestError();
                str = String.format(Locale.ROOT, "%d", new Object[]{Integer.valueOf(requestError.getErrorCode())});
                message = requestError.toString();
            } else {
                str = null;
            }
            result = Result.createErrorResult(this.loginClient.c(), null, message, str);
        }
        if (!z.a(this.e2e)) {
            logWebLoginCompleted(this.e2e);
        }
        this.loginClient.a(result);
    }

    private String loadCookieToken() {
        return this.loginClient.b().getSharedPreferences(WEB_VIEW_AUTH_HANDLER_STORE, 0).getString(WEB_VIEW_AUTH_HANDLER_TOKEN_KEY, "");
    }

    private void saveCookieToken(String str) {
        this.loginClient.b().getSharedPreferences(WEB_VIEW_AUTH_HANDLER_STORE, 0).edit().putString(WEB_VIEW_AUTH_HANDLER_TOKEN_KEY, str).apply();
    }
}
