package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONException;
import org.json.JSONObject;

final class gx extends gz {
    private final /* synthetic */ Context a;
    private final /* synthetic */ gw b;

    gx(gw gwVar, Context context) {
        this.b = gwVar;
        this.a = context;
        super(null);
    }

    public final void a() {
        SharedPreferences sharedPreferences = this.a.getSharedPreferences("admob", 0);
        Editor edit = sharedPreferences.edit();
        synchronized (this.b.b) {
            this.b.e = sharedPreferences;
            this.b.a = edit;
            this.b.f = gw.n();
            this.b.g = this.b.e.getBoolean("use_https", this.b.g);
            this.b.s = this.b.e.getBoolean("content_url_opted_out", this.b.s);
            this.b.h = this.b.e.getString("content_url_hashes", this.b.h);
            this.b.j = this.b.e.getBoolean("auto_collect_location", this.b.j);
            this.b.t = this.b.e.getBoolean("content_vertical_opted_out", this.b.t);
            this.b.i = this.b.e.getString("content_vertical_hashes", this.b.i);
            this.b.p = this.b.e.getInt("version_code", this.b.p);
            this.b.k = this.b.e.getString("app_settings_json", this.b.k);
            this.b.l = this.b.e.getLong("app_settings_last_update_ms", this.b.l);
            this.b.m = this.b.e.getLong("app_last_background_time_ms", this.b.m);
            this.b.o = this.b.e.getInt("request_in_session_count", this.b.o);
            this.b.n = this.b.e.getLong("first_ad_req_time_ms", this.b.n);
            this.b.q = this.b.e.getStringSet("never_pool_slots", this.b.q);
            try {
                this.b.r = new JSONObject(this.b.e.getString("native_advanced_settings", "{}"));
            } catch (JSONException e) {
                gv.c("Could not convert native advanced settings to json object", e);
            }
            this.b.a(this.b.p());
        }
    }
}
