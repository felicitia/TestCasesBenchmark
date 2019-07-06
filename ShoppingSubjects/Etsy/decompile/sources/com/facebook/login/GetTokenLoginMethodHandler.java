package com.facebook.login;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.internal.PlatformServiceClient.a;
import com.facebook.internal.z;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class GetTokenLoginMethodHandler extends LoginMethodHandler {
    public static final Creator<GetTokenLoginMethodHandler> CREATOR = new Creator() {
        /* renamed from: a */
        public GetTokenLoginMethodHandler createFromParcel(Parcel parcel) {
            return new GetTokenLoginMethodHandler(parcel);
        }

        /* renamed from: a */
        public GetTokenLoginMethodHandler[] newArray(int i) {
            return new GetTokenLoginMethodHandler[i];
        }
    };
    private b a;

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public String getNameForLogging() {
        return "get_token";
    }

    GetTokenLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    /* access modifiers changed from: 0000 */
    public void cancel() {
        if (this.a != null) {
            this.a.cancel();
            this.a.setCompletedListener(null);
            this.a = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean tryAuthorize(final Request request) {
        this.a = new b(this.loginClient.b(), request.getApplicationId());
        if (!this.a.start()) {
            return false;
        }
        this.loginClient.k();
        this.a.setCompletedListener(new a() {
            public void a(Bundle bundle) {
                GetTokenLoginMethodHandler.this.a(request, bundle);
            }
        });
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void a(Request request, Bundle bundle) {
        if (this.a != null) {
            this.a.setCompletedListener(null);
        }
        this.a = null;
        this.loginClient.l();
        if (bundle != null) {
            ArrayList stringArrayList = bundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS");
            Set<String> permissions = request.getPermissions();
            if (stringArrayList == null || (permissions != null && !stringArrayList.containsAll(permissions))) {
                HashSet hashSet = new HashSet();
                for (String str : permissions) {
                    if (!stringArrayList.contains(str)) {
                        hashSet.add(str);
                    }
                }
                if (!hashSet.isEmpty()) {
                    addLoggingExtra("new_permissions", TextUtils.join(",", hashSet));
                }
                request.setPermissions(hashSet);
            } else {
                c(request, bundle);
                return;
            }
        }
        this.loginClient.i();
    }

    /* access modifiers changed from: 0000 */
    public void b(Request request, Bundle bundle) {
        this.loginClient.a(Result.createTokenResult(this.loginClient.c(), createAccessTokenFromNativeLogin(bundle, AccessTokenSource.FACEBOOK_APPLICATION_SERVICE, request.getApplicationId())));
    }

    /* access modifiers changed from: 0000 */
    public void c(final Request request, final Bundle bundle) {
        String string = bundle.getString("com.facebook.platform.extra.USER_ID");
        if (string == null || string.isEmpty()) {
            this.loginClient.k();
            z.a(bundle.getString("com.facebook.platform.extra.ACCESS_TOKEN"), (z.a) new z.a() {
                public void a(JSONObject jSONObject) {
                    try {
                        bundle.putString("com.facebook.platform.extra.USER_ID", jSONObject.getString("id"));
                        GetTokenLoginMethodHandler.this.b(request, bundle);
                    } catch (JSONException e) {
                        GetTokenLoginMethodHandler.this.loginClient.b(Result.createErrorResult(GetTokenLoginMethodHandler.this.loginClient.c(), "Caught exception", e.getMessage()));
                    }
                }

                public void a(FacebookException facebookException) {
                    GetTokenLoginMethodHandler.this.loginClient.b(Result.createErrorResult(GetTokenLoginMethodHandler.this.loginClient.c(), "Caught exception", facebookException.getMessage()));
                }
            });
            return;
        }
        b(request, bundle);
    }

    GetTokenLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
