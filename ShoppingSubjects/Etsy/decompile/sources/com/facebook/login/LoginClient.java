package com.facebook.login;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.common.a.f;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.internal.aa;
import com.facebook.internal.z;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class LoginClient implements Parcelable {
    public static final Creator<LoginClient> CREATOR = new Creator<LoginClient>() {
        /* renamed from: a */
        public LoginClient createFromParcel(Parcel parcel) {
            return new LoginClient(parcel);
        }

        /* renamed from: a */
        public LoginClient[] newArray(int i) {
            return new LoginClient[i];
        }
    };
    LoginMethodHandler[] a;
    int b = -1;
    Fragment c;
    b d;
    a e;
    boolean f;
    Request g;
    Map<String, String> h;
    private c i;

    public static class Request implements Parcelable {
        public static final Creator<Request> CREATOR = new Creator<Request>() {
            /* renamed from: a */
            public Request createFromParcel(Parcel parcel) {
                return new Request(parcel);
            }

            /* renamed from: a */
            public Request[] newArray(int i) {
                return new Request[i];
            }
        };
        private final String applicationId;
        private final String authId;
        private String authType;
        private final DefaultAudience defaultAudience;
        private String deviceRedirectUriString;
        private boolean isRerequest;
        private final LoginBehavior loginBehavior;
        private Set<String> permissions;

        public int describeContents() {
            return 0;
        }

        Request(LoginBehavior loginBehavior2, Set<String> set, DefaultAudience defaultAudience2, String str, String str2, String str3) {
            this.isRerequest = false;
            this.loginBehavior = loginBehavior2;
            if (set == null) {
                set = new HashSet<>();
            }
            this.permissions = set;
            this.defaultAudience = defaultAudience2;
            this.authType = str;
            this.applicationId = str2;
            this.authId = str3;
        }

        /* access modifiers changed from: 0000 */
        public Set<String> getPermissions() {
            return this.permissions;
        }

        /* access modifiers changed from: 0000 */
        public void setPermissions(Set<String> set) {
            aa.a((Object) set, "permissions");
            this.permissions = set;
        }

        /* access modifiers changed from: 0000 */
        public LoginBehavior getLoginBehavior() {
            return this.loginBehavior;
        }

        /* access modifiers changed from: 0000 */
        public DefaultAudience getDefaultAudience() {
            return this.defaultAudience;
        }

        /* access modifiers changed from: 0000 */
        public String getApplicationId() {
            return this.applicationId;
        }

        /* access modifiers changed from: 0000 */
        public String getAuthId() {
            return this.authId;
        }

        /* access modifiers changed from: 0000 */
        public boolean isRerequest() {
            return this.isRerequest;
        }

        /* access modifiers changed from: 0000 */
        public void setRerequest(boolean z) {
            this.isRerequest = z;
        }

        /* access modifiers changed from: 0000 */
        public String getDeviceRedirectUriString() {
            return this.deviceRedirectUriString;
        }

        /* access modifiers changed from: 0000 */
        public void setDeviceRedirectUriString(String str) {
            this.deviceRedirectUriString = str;
        }

        /* access modifiers changed from: 0000 */
        public String getAuthType() {
            return this.authType;
        }

        /* access modifiers changed from: 0000 */
        public void setAuthType(String str) {
            this.authType = str;
        }

        /* access modifiers changed from: 0000 */
        public boolean hasPublishPermission() {
            for (String b : this.permissions) {
                if (d.b(b)) {
                    return true;
                }
            }
            return false;
        }

        private Request(Parcel parcel) {
            boolean z = false;
            this.isRerequest = false;
            String readString = parcel.readString();
            DefaultAudience defaultAudience2 = null;
            this.loginBehavior = readString != null ? LoginBehavior.valueOf(readString) : null;
            ArrayList arrayList = new ArrayList();
            parcel.readStringList(arrayList);
            this.permissions = new HashSet(arrayList);
            String readString2 = parcel.readString();
            if (readString2 != null) {
                defaultAudience2 = DefaultAudience.valueOf(readString2);
            }
            this.defaultAudience = defaultAudience2;
            this.applicationId = parcel.readString();
            this.authId = parcel.readString();
            if (parcel.readByte() != 0) {
                z = true;
            }
            this.isRerequest = z;
            this.deviceRedirectUriString = parcel.readString();
            this.authType = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            String str = null;
            parcel.writeString(this.loginBehavior != null ? this.loginBehavior.name() : null);
            parcel.writeStringList(new ArrayList(this.permissions));
            if (this.defaultAudience != null) {
                str = this.defaultAudience.name();
            }
            parcel.writeString(str);
            parcel.writeString(this.applicationId);
            parcel.writeString(this.authId);
            parcel.writeByte(this.isRerequest ? (byte) 1 : 0);
            parcel.writeString(this.deviceRedirectUriString);
            parcel.writeString(this.authType);
        }
    }

    public static class Result implements Parcelable {
        public static final Creator<Result> CREATOR = new Creator<Result>() {
            /* renamed from: a */
            public Result createFromParcel(Parcel parcel) {
                return new Result(parcel);
            }

            /* renamed from: a */
            public Result[] newArray(int i) {
                return new Result[i];
            }
        };
        final Code code;
        final String errorCode;
        final String errorMessage;
        public Map<String, String> loggingExtras;
        final Request request;
        final AccessToken token;

        enum Code {
            SUCCESS("success"),
            CANCEL("cancel"),
            ERROR("error");
            
            private final String loggingValue;

            private Code(String str) {
                this.loggingValue = str;
            }

            /* access modifiers changed from: 0000 */
            public String getLoggingValue() {
                return this.loggingValue;
            }
        }

        public int describeContents() {
            return 0;
        }

        Result(Request request2, Code code2, AccessToken accessToken, String str, String str2) {
            aa.a((Object) code2, ResponseConstants.CODE);
            this.request = request2;
            this.token = accessToken;
            this.errorMessage = str;
            this.code = code2;
            this.errorCode = str2;
        }

        static Result createTokenResult(Request request2, AccessToken accessToken) {
            Result result = new Result(request2, Code.SUCCESS, accessToken, null, null);
            return result;
        }

        static Result createCancelResult(Request request2, String str) {
            Result result = new Result(request2, Code.CANCEL, null, str, null);
            return result;
        }

        static Result createErrorResult(Request request2, String str, String str2) {
            return createErrorResult(request2, str, str2, null);
        }

        static Result createErrorResult(Request request2, String str, String str2, String str3) {
            Request request3 = request2;
            Result result = new Result(request3, Code.ERROR, null, TextUtils.join(": ", z.b((T[]) new String[]{str, str2})), str3);
            return result;
        }

        private Result(Parcel parcel) {
            this.code = Code.valueOf(parcel.readString());
            this.token = (AccessToken) parcel.readParcelable(AccessToken.class.getClassLoader());
            this.errorMessage = parcel.readString();
            this.errorCode = parcel.readString();
            this.request = (Request) parcel.readParcelable(Request.class.getClassLoader());
            this.loggingExtras = z.a(parcel);
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.code.name());
            parcel.writeParcelable(this.token, i);
            parcel.writeString(this.errorMessage);
            parcel.writeString(this.errorCode);
            parcel.writeParcelable(this.request, i);
            z.a(parcel, this.loggingExtras);
        }
    }

    interface a {
        void a();

        void b();
    }

    public interface b {
        void a(Result result);
    }

    public int describeContents() {
        return 0;
    }

    public LoginClient(Fragment fragment) {
        this.c = fragment;
    }

    public Fragment a() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public void a(Fragment fragment) {
        if (this.c != null) {
            throw new FacebookException("Can't set fragment once it is already set.");
        }
        this.c = fragment;
    }

    /* access modifiers changed from: 0000 */
    public FragmentActivity b() {
        return this.c.getActivity();
    }

    public Request c() {
        return this.g;
    }

    public static int d() {
        return RequestCodeOffset.Login.toRequestCode();
    }

    /* access modifiers changed from: 0000 */
    public void a(Request request) {
        if (!e()) {
            b(request);
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(Request request) {
        if (request != null) {
            if (this.g != null) {
                throw new FacebookException("Attempted to authorize while a request is pending.");
            } else if (!AccessToken.isCurrentAccessTokenActive() || h()) {
                this.g = request;
                this.a = c(request);
                i();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean e() {
        return this.g != null && this.b >= 0;
    }

    /* access modifiers changed from: 0000 */
    public void f() {
        if (this.b >= 0) {
            g().cancel();
        }
    }

    /* access modifiers changed from: 0000 */
    public LoginMethodHandler g() {
        if (this.b >= 0) {
            return this.a[this.b];
        }
        return null;
    }

    public boolean a(int i2, int i3, Intent intent) {
        if (this.g != null) {
            return g().onActivityResult(i2, i3, intent);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public LoginMethodHandler[] c(Request request) {
        ArrayList arrayList = new ArrayList();
        LoginBehavior loginBehavior = request.getLoginBehavior();
        if (loginBehavior.allowsGetTokenAuth()) {
            arrayList.add(new GetTokenLoginMethodHandler(this));
        }
        if (loginBehavior.allowsKatanaAuth()) {
            arrayList.add(new KatanaProxyLoginMethodHandler(this));
        }
        if (loginBehavior.allowsFacebookLiteAuth()) {
            arrayList.add(new FacebookLiteLoginMethodHandler(this));
        }
        if (loginBehavior.allowsCustomTabAuth()) {
            arrayList.add(new CustomTabLoginMethodHandler(this));
        }
        if (loginBehavior.allowsWebViewAuth()) {
            arrayList.add(new WebViewLoginMethodHandler(this));
        }
        if (loginBehavior.allowsDeviceAuth()) {
            arrayList.add(new DeviceAuthMethodHandler(this));
        }
        LoginMethodHandler[] loginMethodHandlerArr = new LoginMethodHandler[arrayList.size()];
        arrayList.toArray(loginMethodHandlerArr);
        return loginMethodHandlerArr;
    }

    /* access modifiers changed from: 0000 */
    public boolean h() {
        if (this.f) {
            return true;
        }
        if (a("android.permission.INTERNET") != 0) {
            FragmentActivity b2 = b();
            b(Result.createErrorResult(this.g, b2.getString(f.com_facebook_internet_permission_error_title), b2.getString(f.com_facebook_internet_permission_error_message)));
            return false;
        }
        this.f = true;
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void i() {
        if (this.b >= 0) {
            a(g().getNameForLogging(), "skipped", null, null, g().methodLoggingExtras);
        }
        while (this.a != null && this.b < this.a.length - 1) {
            this.b++;
            if (j()) {
                return;
            }
        }
        if (this.g != null) {
            n();
        }
    }

    private void n() {
        b(Result.createErrorResult(this.g, "Login attempt failed.", null));
    }

    private void a(String str, String str2, boolean z) {
        if (this.h == null) {
            this.h = new HashMap();
        }
        if (this.h.containsKey(str) && z) {
            StringBuilder sb = new StringBuilder();
            sb.append((String) this.h.get(str));
            sb.append(",");
            sb.append(str2);
            str2 = sb.toString();
        }
        this.h.put(str, str2);
    }

    /* access modifiers changed from: 0000 */
    public boolean j() {
        LoginMethodHandler g2 = g();
        if (!g2.needsInternetPermission() || h()) {
            boolean tryAuthorize = g2.tryAuthorize(this.g);
            if (tryAuthorize) {
                o().a(this.g.getAuthId(), g2.getNameForLogging());
            } else {
                o().b(this.g.getAuthId(), g2.getNameForLogging());
                a("not_tried", g2.getNameForLogging(), true);
            }
            return tryAuthorize;
        }
        a("no_internet_permission", "1", false);
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void a(Result result) {
        if (result.token == null || !AccessToken.isCurrentAccessTokenActive()) {
            b(result);
        } else {
            c(result);
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(Result result) {
        LoginMethodHandler g2 = g();
        if (g2 != null) {
            a(g2.getNameForLogging(), result, g2.methodLoggingExtras);
        }
        if (this.h != null) {
            result.loggingExtras = this.h;
        }
        this.a = null;
        this.b = -1;
        this.g = null;
        this.h = null;
        d(result);
    }

    /* access modifiers changed from: 0000 */
    public void a(b bVar) {
        this.d = bVar;
    }

    /* access modifiers changed from: 0000 */
    public void a(a aVar) {
        this.e = aVar;
    }

    /* access modifiers changed from: 0000 */
    public int a(String str) {
        return b().checkCallingOrSelfPermission(str);
    }

    /* access modifiers changed from: 0000 */
    public void c(Result result) {
        Result result2;
        if (result.token == null) {
            throw new FacebookException("Can't validate without a token");
        }
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        AccessToken accessToken = result.token;
        if (!(currentAccessToken == null || accessToken == null)) {
            try {
                if (currentAccessToken.getUserId().equals(accessToken.getUserId())) {
                    result2 = Result.createTokenResult(this.g, result.token);
                    b(result2);
                }
            } catch (Exception e2) {
                b(Result.createErrorResult(this.g, "Caught exception", e2.getMessage()));
                return;
            }
        }
        result2 = Result.createErrorResult(this.g, "User logged in as different Facebook user.", null);
        b(result2);
    }

    private c o() {
        if (this.i == null || !this.i.a().equals(this.g.getApplicationId())) {
            this.i = new c(b(), this.g.getApplicationId());
        }
        return this.i;
    }

    private void d(Result result) {
        if (this.d != null) {
            this.d.a(result);
        }
    }

    /* access modifiers changed from: 0000 */
    public void k() {
        if (this.e != null) {
            this.e.a();
        }
    }

    /* access modifiers changed from: 0000 */
    public void l() {
        if (this.e != null) {
            this.e.b();
        }
    }

    private void a(String str, Result result, Map<String, String> map) {
        a(str, result.code.getLoggingValue(), result.errorMessage, result.errorCode, map);
    }

    private void a(String str, String str2, String str3, String str4, Map<String, String> map) {
        if (this.g == null) {
            o().a("fb_mobile_login_method_complete", "Unexpected call to logCompleteLogin with null pendingAuthorizationRequest.", str);
        } else {
            o().a(this.g.getAuthId(), str, str2, str3, str4, map);
        }
    }

    static String m() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("init", System.currentTimeMillis());
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public LoginClient(Parcel parcel) {
        Parcelable[] readParcelableArray = parcel.readParcelableArray(LoginMethodHandler.class.getClassLoader());
        this.a = new LoginMethodHandler[readParcelableArray.length];
        for (int i2 = 0; i2 < readParcelableArray.length; i2++) {
            this.a[i2] = (LoginMethodHandler) readParcelableArray[i2];
            this.a[i2].setLoginClient(this);
        }
        this.b = parcel.readInt();
        this.g = (Request) parcel.readParcelable(Request.class.getClassLoader());
        this.h = z.a(parcel);
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelableArray(this.a, i2);
        parcel.writeInt(this.b);
        parcel.writeParcelable(this.g, i2);
        z.a(parcel, this.h);
    }
}
