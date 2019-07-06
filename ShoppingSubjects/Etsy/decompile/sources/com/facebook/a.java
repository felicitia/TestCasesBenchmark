package com.facebook;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.internal.aa;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AccessTokenCache */
class a {
    private final SharedPreferences a;
    private final C0118a b;
    private h c;

    /* renamed from: com.facebook.a$a reason: collision with other inner class name */
    /* compiled from: AccessTokenCache */
    static class C0118a {
        C0118a() {
        }

        public h a() {
            return new h(f.f());
        }
    }

    a(SharedPreferences sharedPreferences, C0118a aVar) {
        this.a = sharedPreferences;
        this.b = aVar;
    }

    public a() {
        this(f.f().getSharedPreferences("com.facebook.AccessTokenManager.SharedPreferences", 0), new C0118a());
    }

    public AccessToken a() {
        if (c()) {
            return d();
        }
        if (!e()) {
            return null;
        }
        AccessToken f = f();
        if (f == null) {
            return f;
        }
        a(f);
        g().b();
        return f;
    }

    public void a(AccessToken accessToken) {
        aa.a((Object) accessToken, "accessToken");
        try {
            this.a.edit().putString("com.facebook.AccessTokenManager.CachedAccessToken", accessToken.toJSONObject().toString()).apply();
        } catch (JSONException unused) {
        }
    }

    public void b() {
        this.a.edit().remove("com.facebook.AccessTokenManager.CachedAccessToken").apply();
        if (e()) {
            g().b();
        }
    }

    private boolean c() {
        return this.a.contains("com.facebook.AccessTokenManager.CachedAccessToken");
    }

    private AccessToken d() {
        String string = this.a.getString("com.facebook.AccessTokenManager.CachedAccessToken", null);
        if (string == null) {
            return null;
        }
        try {
            return AccessToken.createFromJSONObject(new JSONObject(string));
        } catch (JSONException unused) {
            return null;
        }
    }

    private boolean e() {
        return f.c();
    }

    private AccessToken f() {
        Bundle a2 = g().a();
        if (a2 == null || !h.a(a2)) {
            return null;
        }
        return AccessToken.createFromLegacyCache(a2);
    }

    private h g() {
        if (this.c == null) {
            synchronized (this) {
                if (this.c == null) {
                    this.c = this.b.a();
                }
            }
        }
        return this.c;
    }
}
