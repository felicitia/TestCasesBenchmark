package com.facebook.login;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;

class DeviceAuthMethodHandler extends LoginMethodHandler {
    public static final Creator<DeviceAuthMethodHandler> CREATOR = new Creator() {
        /* renamed from: a */
        public DeviceAuthMethodHandler createFromParcel(Parcel parcel) {
            return new DeviceAuthMethodHandler(parcel);
        }

        /* renamed from: a */
        public DeviceAuthMethodHandler[] newArray(int i) {
            return new DeviceAuthMethodHandler[i];
        }
    };
    private static ScheduledThreadPoolExecutor a;

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public String getNameForLogging() {
        return "device_auth";
    }

    DeviceAuthMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    /* access modifiers changed from: 0000 */
    public boolean tryAuthorize(Request request) {
        a(request);
        return true;
    }

    private void a(Request request) {
        DeviceAuthDialog deviceAuthDialog = new DeviceAuthDialog();
        deviceAuthDialog.show(this.loginClient.b().getSupportFragmentManager(), "login_with_facebook");
        deviceAuthDialog.startLogin(request);
    }

    public void a() {
        this.loginClient.a(Result.createCancelResult(this.loginClient.c(), "User canceled log in."));
    }

    public void a(Exception exc) {
        this.loginClient.a(Result.createErrorResult(this.loginClient.c(), null, exc.getMessage()));
    }

    public void a(String str, String str2, String str3, Collection<String> collection, Collection<String> collection2, AccessTokenSource accessTokenSource, Date date, Date date2) {
        AccessToken accessToken = new AccessToken(str, str2, str3, collection, collection2, accessTokenSource, date, date2);
        this.loginClient.a(Result.createTokenResult(this.loginClient.c(), accessToken));
    }

    public static synchronized ScheduledThreadPoolExecutor b() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
        synchronized (DeviceAuthMethodHandler.class) {
            if (a == null) {
                a = new ScheduledThreadPoolExecutor(1);
            }
            scheduledThreadPoolExecutor = a;
        }
        return scheduledThreadPoolExecutor;
    }

    protected DeviceAuthMethodHandler(Parcel parcel) {
        super(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
