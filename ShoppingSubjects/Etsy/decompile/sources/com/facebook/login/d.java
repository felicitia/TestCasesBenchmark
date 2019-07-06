package com.facebook.login;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.etsy.android.lib.models.editable.EditableListing;
import com.facebook.AccessToken;
import com.facebook.FacebookActivity;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.e;
import com.facebook.f;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.internal.aa;
import com.facebook.internal.m;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* compiled from: LoginManager */
public class d {
    private static final Set<String> a = a();
    private static volatile d b;
    private LoginBehavior c = LoginBehavior.NATIVE_WITH_FALLBACK;
    private DefaultAudience d = DefaultAudience.FRIENDS;
    private final SharedPreferences e;
    private String f = "rerequest";

    /* compiled from: LoginManager */
    private static class a implements g {
        private final Activity a;

        a(Activity activity) {
            aa.a((Object) activity, "activity");
            this.a = activity;
        }

        public void a(Intent intent, int i) {
            this.a.startActivityForResult(intent, i);
        }

        public Activity a() {
            return this.a;
        }
    }

    /* compiled from: LoginManager */
    private static class b implements g {
        private final m a;

        b(m mVar) {
            aa.a((Object) mVar, "fragment");
            this.a = mVar;
        }

        public void a(Intent intent, int i) {
            this.a.a(intent, i);
        }

        public Activity a() {
            return this.a.c();
        }
    }

    /* compiled from: LoginManager */
    private static class c {
        private static c a;

        /* access modifiers changed from: private */
        public static synchronized c b(Context context) {
            synchronized (c.class) {
                if (context == null) {
                    context = f.f();
                }
                if (context == null) {
                    return null;
                }
                if (a == null) {
                    a = new c(context, f.j());
                }
                c cVar = a;
                return cVar;
            }
        }
    }

    d() {
        aa.a();
        this.e = f.f().getSharedPreferences("com.facebook.loginManager", 0);
    }

    public static d c() {
        if (b == null) {
            synchronized (d.class) {
                if (b == null) {
                    b = new d();
                }
            }
        }
        return b;
    }

    public void a(com.facebook.d dVar, final e<e> eVar) {
        if (!(dVar instanceof CallbackManagerImpl)) {
            throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
        }
        ((CallbackManagerImpl) dVar).b(RequestCodeOffset.Login.toRequestCode(), new com.facebook.internal.CallbackManagerImpl.a() {
            public boolean a(int i, Intent intent) {
                return d.this.a(i, intent, eVar);
            }
        });
    }

    public void a(com.facebook.d dVar) {
        if (!(dVar instanceof CallbackManagerImpl)) {
            throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
        }
        ((CallbackManagerImpl) dVar).a(RequestCodeOffset.Login.toRequestCode());
    }

    /* access modifiers changed from: 0000 */
    public boolean a(int i, Intent intent) {
        return a(i, intent, null);
    }

    /* access modifiers changed from: 0000 */
    public boolean a(int i, Intent intent, e<e> eVar) {
        Map map;
        Code code;
        boolean z;
        AccessToken accessToken;
        Request request;
        Request request2;
        Map map2;
        AccessToken accessToken2;
        int i2 = i;
        Intent intent2 = intent;
        Code code2 = Code.ERROR;
        FacebookException facebookException = null;
        boolean z2 = false;
        if (intent2 != null) {
            Result result = (Result) intent2.getParcelableExtra("com.facebook.LoginFragment:Result");
            if (result != null) {
                Request request3 = result.request;
                Code code3 = result.code;
                if (i2 != -1) {
                    if (i2 == 0) {
                        z2 = true;
                    }
                    accessToken2 = null;
                } else if (result.code == Code.SUCCESS) {
                    accessToken2 = result.token;
                } else {
                    facebookException = new FacebookAuthorizationException(result.errorMessage);
                    accessToken2 = null;
                }
                map2 = result.loggingExtras;
                Code code4 = code3;
                request2 = request3;
                code2 = code4;
            } else {
                accessToken2 = null;
                map2 = null;
                request2 = null;
            }
            map = map2;
            code = code2;
            z = z2;
            Request request4 = request2;
            accessToken = accessToken2;
            request = request4;
        } else if (i2 == 0) {
            code = Code.CANCEL;
            z = true;
            request = null;
            accessToken = null;
            map = null;
        } else {
            code = code2;
            request = null;
            accessToken = null;
            map = null;
            z = false;
        }
        if (facebookException == null && accessToken == null && !z) {
            facebookException = new FacebookException("Unexpected call to LoginManager.onActivityResult");
        }
        a(null, code, map, facebookException, true, request);
        a(accessToken, request, facebookException, z, eVar);
        return true;
    }

    public d a(LoginBehavior loginBehavior) {
        this.c = loginBehavior;
        return this;
    }

    public d a(DefaultAudience defaultAudience) {
        this.d = defaultAudience;
        return this;
    }

    public d a(String str) {
        this.f = str;
        return this;
    }

    public void d() {
        AccessToken.setCurrentAccessToken(null);
        Profile.setCurrentProfile(null);
        a(false);
    }

    public void a(Fragment fragment, Collection<String> collection) {
        a(new m(fragment), collection);
    }

    public void a(android.app.Fragment fragment, Collection<String> collection) {
        a(new m(fragment), collection);
    }

    private void a(m mVar, Collection<String> collection) {
        b(collection);
        a((g) new b(mVar), a(collection));
    }

    public void a(Activity activity, Collection<String> collection) {
        b(collection);
        a((g) new a(activity), a(collection));
    }

    public void b(Fragment fragment, Collection<String> collection) {
        b(new m(fragment), collection);
    }

    public void b(android.app.Fragment fragment, Collection<String> collection) {
        b(new m(fragment), collection);
    }

    private void b(m mVar, Collection<String> collection) {
        c(collection);
        a((g) new b(mVar), a(collection));
    }

    public void b(Activity activity, Collection<String> collection) {
        c(collection);
        a((g) new a(activity), a(collection));
    }

    private void b(Collection<String> collection) {
        if (collection != null) {
            for (String str : collection) {
                if (b(str)) {
                    throw new FacebookException(String.format("Cannot pass a publish or manage permission (%s) to a request for read authorization", new Object[]{str}));
                }
            }
        }
    }

    private void c(Collection<String> collection) {
        if (collection != null) {
            for (String str : collection) {
                if (!b(str)) {
                    throw new FacebookException(String.format("Cannot pass a read permission (%s) to a request for publish authorization", new Object[]{str}));
                }
            }
        }
    }

    static boolean b(String str) {
        return str != null && (str.startsWith(EditableListing.REQUEST_PARAM_PUBLISH) || str.startsWith("manage") || a.contains(str));
    }

    private static Set<String> a() {
        return Collections.unmodifiableSet(new LoginManager$2());
    }

    /* access modifiers changed from: protected */
    public Request a(Collection<String> collection) {
        Request request = new Request(this.c, Collections.unmodifiableSet(collection != null ? new HashSet(collection) : new HashSet()), this.d, this.f, f.j(), UUID.randomUUID().toString());
        request.setRerequest(AccessToken.isCurrentAccessTokenActive());
        return request;
    }

    private void a(g gVar, Request request) throws FacebookException {
        a((Context) gVar.a(), request);
        CallbackManagerImpl.a(RequestCodeOffset.Login.toRequestCode(), new com.facebook.internal.CallbackManagerImpl.a() {
            public boolean a(int i, Intent intent) {
                return d.this.a(i, intent);
            }
        });
        if (!b(gVar, request)) {
            FacebookException facebookException = new FacebookException("Log in attempt failed: FacebookActivity could not be started. Please make sure you added FacebookActivity to the AndroidManifest.");
            a(gVar.a(), Code.ERROR, null, facebookException, false, request);
            throw facebookException;
        }
    }

    private void a(Context context, Request request) {
        c a2 = c.b(context);
        if (a2 != null && request != null) {
            a2.a(request);
        }
    }

    private void a(Context context, Code code, Map<String, String> map, Exception exc, boolean z, Request request) {
        c a2 = c.b(context);
        if (a2 != null) {
            if (request == null) {
                a2.c("fb_mobile_login_complete", "Unexpected call to logCompleteLogin with null pendingAuthorizationRequest.");
            } else {
                HashMap hashMap = new HashMap();
                hashMap.put("try_login_activity", z ? "1" : "0");
                a2.a(request.getAuthId(), hashMap, code, map, exc);
            }
        }
    }

    private boolean b(g gVar, Request request) {
        Intent a2 = a(request);
        if (!a(a2)) {
            return false;
        }
        try {
            gVar.a(a2, LoginClient.d());
            return true;
        } catch (ActivityNotFoundException unused) {
            return false;
        }
    }

    private boolean a(Intent intent) {
        if (f.f().getPackageManager().resolveActivity(intent, 0) != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public Intent a(Request request) {
        Intent intent = new Intent();
        intent.setClass(f.f(), FacebookActivity.class);
        intent.setAction(request.getLoginBehavior().toString());
        Bundle bundle = new Bundle();
        bundle.putParcelable("request", request);
        intent.putExtra("com.facebook.LoginFragment:Request", bundle);
        return intent;
    }

    static e a(Request request, AccessToken accessToken) {
        Set permissions = request.getPermissions();
        HashSet hashSet = new HashSet(accessToken.getPermissions());
        if (request.isRerequest()) {
            hashSet.retainAll(permissions);
        }
        HashSet hashSet2 = new HashSet(permissions);
        hashSet2.removeAll(hashSet);
        return new e(accessToken, hashSet, hashSet2);
    }

    private void a(AccessToken accessToken, Request request, FacebookException facebookException, boolean z, e<e> eVar) {
        if (accessToken != null) {
            AccessToken.setCurrentAccessToken(accessToken);
            Profile.fetchProfileForCurrentAccessToken();
        }
        if (eVar != null) {
            e a2 = accessToken != null ? a(request, accessToken) : null;
            if (z || (a2 != null && a2.b().size() == 0)) {
                eVar.a();
            } else if (facebookException != null) {
                eVar.a(facebookException);
            } else if (accessToken != null) {
                a(true);
                eVar.a(a2);
            }
        }
    }

    private void a(boolean z) {
        Editor edit = this.e.edit();
        edit.putBoolean("express_login_allowed", z);
        edit.apply();
    }
}
