package com.facebook.login;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.internal.v;
import com.facebook.login.LoginClient.Request;

class KatanaProxyLoginMethodHandler extends NativeAppLoginMethodHandler {
    public static final Creator<KatanaProxyLoginMethodHandler> CREATOR = new Creator<KatanaProxyLoginMethodHandler>() {
        /* renamed from: a */
        public KatanaProxyLoginMethodHandler createFromParcel(Parcel parcel) {
            return new KatanaProxyLoginMethodHandler(parcel);
        }

        /* renamed from: a */
        public KatanaProxyLoginMethodHandler[] newArray(int i) {
            return new KatanaProxyLoginMethodHandler[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public String getNameForLogging() {
        return "katana_proxy_auth";
    }

    KatanaProxyLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    /* access modifiers changed from: 0000 */
    public boolean tryAuthorize(Request request) {
        String m = LoginClient.m();
        Intent b = v.b(this.loginClient.b(), request.getApplicationId(), request.getPermissions(), m, request.isRerequest(), request.hasPublishPermission(), request.getDefaultAudience(), getClientState(request.getAuthId()), request.getAuthType());
        addLoggingExtra("e2e", m);
        return a(b, LoginClient.d());
    }

    KatanaProxyLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
