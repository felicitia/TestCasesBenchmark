package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class dx extends dz {
    private final Object a = new Object();
    private final Context b;
    @Nullable
    private SharedPreferences c;
    private final aqg<JSONObject, JSONObject> d;

    public dx(Context context, aqg<JSONObject, JSONObject> aqg) {
        this.b = context.getApplicationContext();
        this.d = aqg;
    }

    public final kt<Void> a() {
        synchronized (this.a) {
            if (this.c == null) {
                this.c = this.b.getSharedPreferences("google_ads_flags_meta", 0);
            }
        }
        if (ao.l().currentTimeMillis() - this.c.getLong("js_last_update", 0) < ((Long) ajh.f().a(akl.bU)).longValue()) {
            return ki.a(null);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("js", zzang.zzsl().zzcw);
            jSONObject.put("mf", ajh.f().a(akl.bV));
            jSONObject.put("cl", "191880412");
            jSONObject.put("rapid_rc", "dev");
            jSONObject.put("rapid_rollup", BaseHttpRequest.HEAD);
            jSONObject.put("dynamite_version", ModuleDescriptor.MODULE_VERSION);
            return ki.a(this.d.b(jSONObject), (ke<A, B>) new dy<A,B>(this), kz.b);
        } catch (JSONException e) {
            gv.b("Unable to populate SDK Core Constants parameters.", e);
            return ki.a(null);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Void a(JSONObject jSONObject) {
        akl.a(this.b, 1, jSONObject);
        this.c.edit().putLong("js_last_update", ao.l().currentTimeMillis()).apply();
        return null;
    }
}
