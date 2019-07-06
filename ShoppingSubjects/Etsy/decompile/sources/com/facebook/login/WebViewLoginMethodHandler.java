package com.facebook.login;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.app.FragmentActivity;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.internal.FacebookDialogFragment;
import com.facebook.internal.WebDialog;
import com.facebook.internal.WebDialog.c;
import com.facebook.internal.z;
import com.facebook.login.LoginClient.Request;

class WebViewLoginMethodHandler extends WebLoginMethodHandler {
    public static final Creator<WebViewLoginMethodHandler> CREATOR = new Creator<WebViewLoginMethodHandler>() {
        /* renamed from: a */
        public WebViewLoginMethodHandler createFromParcel(Parcel parcel) {
            return new WebViewLoginMethodHandler(parcel);
        }

        /* renamed from: a */
        public WebViewLoginMethodHandler[] newArray(int i) {
            return new WebViewLoginMethodHandler[i];
        }
    };
    private WebDialog a;
    private String b;

    static class a extends com.facebook.internal.WebDialog.a {
        private String a;
        private String b;
        private String c = "fbconnect://success";

        public a(Context context, String str, Bundle bundle) {
            super(context, str, "oauth", bundle);
        }

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a a(boolean z) {
            this.c = z ? "fbconnect://chrome_os_success" : "fbconnect://success";
            return this;
        }

        public a b(String str) {
            this.b = str;
            return this;
        }

        public WebDialog a() {
            Bundle e = e();
            e.putString("redirect_uri", this.c);
            e.putString("client_id", b());
            e.putString("e2e", this.a);
            e.putString("response_type", "token,signed_request");
            e.putString("return_scopes", "true");
            e.putString("auth_type", this.b);
            return WebDialog.newInstance(c(), "oauth", e, d(), f());
        }
    }

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public String getNameForLogging() {
        return "web_view";
    }

    /* access modifiers changed from: 0000 */
    public boolean needsInternetPermission() {
        return true;
    }

    WebViewLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    /* access modifiers changed from: 0000 */
    public AccessTokenSource getTokenSource() {
        return AccessTokenSource.WEB_VIEW;
    }

    /* access modifiers changed from: 0000 */
    public void cancel() {
        if (this.a != null) {
            this.a.cancel();
            this.a = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean tryAuthorize(final Request request) {
        Bundle parameters = getParameters(request);
        AnonymousClass1 r1 = new c() {
            public void a(Bundle bundle, FacebookException facebookException) {
                WebViewLoginMethodHandler.this.a(request, bundle, facebookException);
            }
        };
        this.b = LoginClient.m();
        addLoggingExtra("e2e", this.b);
        FragmentActivity b2 = this.loginClient.b();
        this.a = new a(b2, request.getApplicationId(), parameters).a(this.b).a(z.f(b2)).b(request.getAuthType()).a(r1).a();
        FacebookDialogFragment facebookDialogFragment = new FacebookDialogFragment();
        facebookDialogFragment.setRetainInstance(true);
        facebookDialogFragment.setDialog(this.a);
        facebookDialogFragment.show(b2.getSupportFragmentManager(), FacebookDialogFragment.TAG);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void a(Request request, Bundle bundle, FacebookException facebookException) {
        super.onComplete(request, bundle, facebookException);
    }

    WebViewLoginMethodHandler(Parcel parcel) {
        super(parcel);
        this.b = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.b);
    }
}
