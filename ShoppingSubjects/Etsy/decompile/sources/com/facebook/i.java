package com.facebook;

import android.content.SharedPreferences;
import com.facebook.internal.aa;
import com.google.android.gms.common.Scopes;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProfileCache */
final class i {
    private final SharedPreferences a = f.f().getSharedPreferences("com.facebook.AccessTokenManager.SharedPreferences", 0);

    i() {
    }

    /* access modifiers changed from: 0000 */
    public Profile a() {
        String string = this.a.getString("com.facebook.ProfileManager.CachedProfile", null);
        if (string != null) {
            try {
                return new Profile(new JSONObject(string));
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void a(Profile profile) {
        aa.a((Object) profile, Scopes.PROFILE);
        JSONObject jSONObject = profile.toJSONObject();
        if (jSONObject != null) {
            this.a.edit().putString("com.facebook.ProfileManager.CachedProfile", jSONObject.toString()).apply();
        }
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        this.a.edit().remove("com.facebook.ProfileManager.CachedProfile").apply();
    }
}
