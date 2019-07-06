package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class mn {
    private final boolean a;
    private final int b;
    private final int c;
    private final int d;
    private final String e;
    private final int f;
    private final int g;
    private final int h;
    private final boolean i;

    public mn(String str) {
        JSONObject jSONObject = null;
        if (str != null) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException unused) {
            }
        }
        this.a = a(jSONObject, "aggressive_media_codec_release", akl.B);
        this.b = b(jSONObject, "byte_buffer_precache_limit", akl.m);
        this.c = b(jSONObject, "exo_cache_buffer_size", akl.p);
        this.d = b(jSONObject, "exo_connect_timeout_millis", akl.i);
        this.e = c(jSONObject, "exo_player_version", akl.h);
        this.f = b(jSONObject, "exo_read_timeout_millis", akl.j);
        this.g = b(jSONObject, "load_check_interval_bytes", akl.k);
        this.h = b(jSONObject, "player_precache_limit", akl.l);
        this.i = a(jSONObject, "use_cache_data_source", akl.cH);
    }

    private static boolean a(JSONObject jSONObject, String str, akb<Boolean> akb) {
        if (jSONObject != null) {
            try {
                return jSONObject.getBoolean(str);
            } catch (JSONException unused) {
            }
        }
        return ((Boolean) ajh.f().a(akb)).booleanValue();
    }

    private static int b(JSONObject jSONObject, String str, akb<Integer> akb) {
        if (jSONObject != null) {
            try {
                return jSONObject.getInt(str);
            } catch (JSONException unused) {
            }
        }
        return ((Integer) ajh.f().a(akb)).intValue();
    }

    private static String c(JSONObject jSONObject, String str, akb<String> akb) {
        if (jSONObject != null) {
            try {
                return jSONObject.getString(str);
            } catch (JSONException unused) {
            }
        }
        return (String) ajh.f().a(akb);
    }
}
