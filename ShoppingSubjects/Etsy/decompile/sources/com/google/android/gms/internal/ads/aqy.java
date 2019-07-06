package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.ao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class aqy {
    public final String a;
    public final String b;
    public final List<String> c;
    public final String d;
    public final String e;
    public final List<String> f;
    public final List<String> g;
    public final List<String> h;
    public final List<String> i;
    public final List<String> j;
    public final String k;
    public final List<String> l;
    public final List<String> m;
    public final List<String> n;
    @Nullable
    public final String o;
    @Nullable
    public final String p;
    public final String q;
    @Nullable
    public final List<String> r;
    public final String s;
    public final long t;
    @Nullable
    private final String u;

    public aqy(String str, String str2, List<String> list, String str3, String str4, List<String> list2, List<String> list3, List<String> list4, List<String> list5, String str5, String str6, List<String> list6, List<String> list7, List<String> list8, String str7, String str8, String str9, List<String> list9, String str10, List<String> list10, String str11, long j2) {
        this.a = str;
        this.b = null;
        this.c = list;
        this.d = null;
        this.e = null;
        this.f = list2;
        this.g = list3;
        this.h = list4;
        this.i = list5;
        this.k = str5;
        this.l = list6;
        this.m = list7;
        this.n = list8;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.j = list10;
        this.u = null;
        this.t = -1;
    }

    public aqy(JSONObject jSONObject) throws JSONException {
        List<String> list;
        this.b = jSONObject.optString("id");
        JSONArray jSONArray = jSONObject.getJSONArray("adapters");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            arrayList.add(jSONArray.getString(i2));
        }
        this.c = Collections.unmodifiableList(arrayList);
        this.d = jSONObject.optString("allocation_id", null);
        ao.x();
        this.f = arh.a(jSONObject, "clickurl");
        ao.x();
        this.g = arh.a(jSONObject, "imp_urls");
        ao.x();
        this.h = arh.a(jSONObject, "downloaded_imp_urls");
        ao.x();
        this.j = arh.a(jSONObject, "fill_urls");
        ao.x();
        this.l = arh.a(jSONObject, "video_start_urls");
        ao.x();
        this.n = arh.a(jSONObject, "video_complete_urls");
        ao.x();
        List<String> a2 = arh.a(jSONObject, "video_reward_urls");
        if (!((Boolean) ajh.f().a(akl.ax)).booleanValue()) {
            a2 = this.n;
        }
        this.m = a2;
        JSONObject optJSONObject = jSONObject.optJSONObject("ad");
        if (optJSONObject != null) {
            ao.x();
            list = arh.a(optJSONObject, "manual_impression_urls");
        } else {
            list = null;
        }
        this.i = list;
        this.a = optJSONObject != null ? optJSONObject.toString() : null;
        JSONObject optJSONObject2 = jSONObject.optJSONObject("data");
        this.k = optJSONObject2 != null ? optJSONObject2.toString() : null;
        this.e = optJSONObject2 != null ? optJSONObject2.optString("class_name") : null;
        this.o = jSONObject.optString("html_template", null);
        this.p = jSONObject.optString("ad_base_url", null);
        JSONObject optJSONObject3 = jSONObject.optJSONObject("assets");
        this.q = optJSONObject3 != null ? optJSONObject3.toString() : null;
        ao.x();
        this.r = arh.a(jSONObject, "template_ids");
        JSONObject optJSONObject4 = jSONObject.optJSONObject("ad_loader_options");
        this.s = optJSONObject4 != null ? optJSONObject4.toString() : null;
        this.u = jSONObject.optString("response_type", null);
        this.t = jSONObject.optLong("ad_network_timeout_millis", -1);
    }

    public final boolean a() {
        return ResponseConstants.BANNER.equalsIgnoreCase(this.u);
    }

    public final boolean b() {
        return "native".equalsIgnoreCase(this.u);
    }
}
