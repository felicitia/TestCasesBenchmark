package com.facebook.login;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.internal.v;
import com.facebook.login.LoginClient.Request;
import java.util.Collection;

class FacebookLiteLoginMethodHandler extends NativeAppLoginMethodHandler {
    public static final Creator<FacebookLiteLoginMethodHandler> CREATOR = new Creator<FacebookLiteLoginMethodHandler>() {
        /* renamed from: a */
        public FacebookLiteLoginMethodHandler createFromParcel(Parcel parcel) {
            return new FacebookLiteLoginMethodHandler(parcel);
        }

        /* renamed from: a */
        public FacebookLiteLoginMethodHandler[] newArray(int i) {
            return new FacebookLiteLoginMethodHandler[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public String getNameForLogging() {
        return "fb_lite_login";
    }

    FacebookLiteLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    /* access modifiers changed from: 0000 */
    public boolean tryAuthorize(Request request) {
        String m = LoginClient.m();
        Intent a = v.a((Context) this.loginClient.b(), request.getApplicationId(), (Collection<String>) request.getPermissions(), m, request.isRerequest(), request.hasPublishPermission(), request.getDefaultAudience(), getClientState(request.getAuthId()), request.getAuthType());
        addLoggingExtra("e2e", m);
        return a(a, LoginClient.d());
    }

    FacebookLiteLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
