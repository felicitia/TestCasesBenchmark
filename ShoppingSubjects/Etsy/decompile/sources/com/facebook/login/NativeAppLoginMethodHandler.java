package com.facebook.login;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.internal.x;
import com.facebook.internal.z;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;

abstract class NativeAppLoginMethodHandler extends LoginMethodHandler {
    /* access modifiers changed from: 0000 */
    public abstract boolean tryAuthorize(Request request);

    NativeAppLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    NativeAppLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    /* access modifiers changed from: 0000 */
    public boolean onActivityResult(int i, int i2, Intent intent) {
        Result result;
        Request c = this.loginClient.c();
        if (intent == null) {
            result = Result.createCancelResult(c, "Operation canceled");
        } else if (i2 == 0) {
            result = b(c, intent);
        } else if (i2 != -1) {
            result = Result.createErrorResult(c, "Unexpected resultCode from authorization.", null);
        } else {
            result = a(c, intent);
        }
        if (result != null) {
            this.loginClient.a(result);
        } else {
            this.loginClient.i();
        }
        return true;
    }

    private Result a(Request request, Intent intent) {
        Bundle extras = intent.getExtras();
        String a = a(extras);
        String obj = extras.get("error_code") != null ? extras.get("error_code").toString() : null;
        String b = b(extras);
        String string = extras.getString("e2e");
        if (!z.a(string)) {
            logWebLoginCompleted(string);
        }
        if (a == null && obj == null && b == null) {
            try {
                return Result.createTokenResult(request, createAccessTokenFromWebBundle(request.getPermissions(), extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB, request.getApplicationId()));
            } catch (FacebookException e) {
                return Result.createErrorResult(request, null, e.getMessage());
            }
        } else if (x.a.contains(a)) {
            return null;
        } else {
            if (x.b.contains(a)) {
                return Result.createCancelResult(request, null);
            }
            return Result.createErrorResult(request, a, b, obj);
        }
    }

    private Result b(Request request, Intent intent) {
        Bundle extras = intent.getExtras();
        String a = a(extras);
        String obj = extras.get("error_code") != null ? extras.get("error_code").toString() : null;
        if ("CONNECTION_FAILURE".equals(obj)) {
            return Result.createErrorResult(request, a, b(extras), obj);
        }
        return Result.createCancelResult(request, a);
    }

    private String a(Bundle bundle) {
        String string = bundle.getString("error");
        return string == null ? bundle.getString("error_type") : string;
    }

    private String b(Bundle bundle) {
        String string = bundle.getString(ResponseConstants.ERROR_MESSAGE);
        return string == null ? bundle.getString("error_description") : string;
    }

    /* access modifiers changed from: protected */
    public boolean a(Intent intent, int i) {
        if (intent == null) {
            return false;
        }
        try {
            this.loginClient.a().startActivityForResult(intent, i);
            return true;
        } catch (ActivityNotFoundException unused) {
            return false;
        }
    }
}
