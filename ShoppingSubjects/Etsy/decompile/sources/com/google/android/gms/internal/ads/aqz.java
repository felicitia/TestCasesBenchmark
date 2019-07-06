package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.ao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class aqz {
    public final List<aqy> a;
    public final long b;
    public final List<String> c;
    public final List<String> d;
    public final List<String> e;
    public final List<String> f;
    public final List<String> g;
    public final boolean h;
    public final String i;
    public final long j;
    public final String k;
    public final int l;
    public final int m;
    public final long n;
    public final boolean o;
    public final boolean p;
    public final boolean q;
    public int r;
    public int s;
    public boolean t;

    public aqz(String str) throws JSONException {
        this(new JSONObject(str));
    }

    public aqz(List<aqy> list, long j2, List<String> list2, List<String> list3, List<String> list4, List<String> list5, List<String> list6, boolean z, String str, long j3, int i2, int i3, String str2, int i4, int i5, long j4, boolean z2) {
        this.a = list;
        this.b = j2;
        this.c = list2;
        this.d = list3;
        this.e = list4;
        this.f = list5;
        this.g = list6;
        this.h = z;
        this.i = str;
        this.j = -1;
        this.r = 0;
        this.s = 1;
        this.k = null;
        this.l = 0;
        this.m = -1;
        this.n = -1;
        this.o = false;
        this.p = false;
        this.q = false;
        this.t = false;
    }

    public aqz(JSONObject jSONObject) throws JSONException {
        if (gv.a(2)) {
            String str = "Mediation Response JSON: ";
            String valueOf = String.valueOf(jSONObject.toString(2));
            gv.a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        JSONArray jSONArray = jSONObject.getJSONArray("ad_networks");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        int i2 = -1;
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            aqy aqy = new aqy(jSONArray.getJSONObject(i3));
            boolean z = true;
            if (aqy.a()) {
                this.t = true;
            }
            arrayList.add(aqy);
            if (i2 < 0) {
                Iterator it = aqy.c.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (((String) it.next()).equals("com.google.ads.mediation.admob.AdMobAdapter")) {
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    i2 = i3;
                }
            }
        }
        this.r = i2;
        this.s = jSONArray.length();
        this.a = Collections.unmodifiableList(arrayList);
        this.i = jSONObject.optString("qdata");
        this.m = jSONObject.optInt("fs_model_type", -1);
        long j2 = -1;
        this.n = jSONObject.optLong("timeout_ms", -1);
        JSONObject optJSONObject = jSONObject.optJSONObject("settings");
        if (optJSONObject != null) {
            this.b = optJSONObject.optLong("ad_network_timeout_millis", -1);
            ao.x();
            this.c = arh.a(optJSONObject, "click_urls");
            ao.x();
            this.d = arh.a(optJSONObject, "imp_urls");
            ao.x();
            this.e = arh.a(optJSONObject, "downloaded_imp_urls");
            ao.x();
            this.f = arh.a(optJSONObject, "nofill_urls");
            ao.x();
            this.g = arh.a(optJSONObject, "remote_ping_urls");
            this.h = optJSONObject.optBoolean("render_in_browser", false);
            long optLong = optJSONObject.optLong("refresh", -1);
            if (optLong > 0) {
                j2 = 1000 * optLong;
            }
            this.j = j2;
            zzaig zza = zzaig.zza(optJSONObject.optJSONArray("rewards"));
            if (zza == null) {
                this.k = null;
                this.l = 0;
            } else {
                this.k = zza.type;
                this.l = zza.zzcmk;
            }
            this.o = optJSONObject.optBoolean("use_displayed_impression", false);
            this.p = optJSONObject.optBoolean("allow_pub_rendered_attribution", false);
            this.q = optJSONObject.optBoolean("allow_pub_owned_ad_view", false);
            return;
        }
        this.b = -1;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.j = -1;
        this.k = null;
        this.l = 0;
        this.o = false;
        this.h = false;
        this.p = false;
        this.q = false;
    }
}
