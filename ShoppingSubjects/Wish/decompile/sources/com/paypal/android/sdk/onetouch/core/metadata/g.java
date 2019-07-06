package com.paypal.android.sdk.onetouch.core.metadata;

import android.location.Location;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class g {
    public String A;
    public Boolean B;
    public String C;
    public String D;
    public Boolean E;
    public String F;
    public String G;
    public long H;
    public long I;
    public String J;
    public Boolean K;
    public Integer L;
    public int M = -1;
    public int N = -1;
    public String O;
    public int P = -1;
    public String Q;
    public Boolean R;
    public Boolean S;
    public String T;
    public long U;
    public long V;
    public String W;
    public String X;
    public String Y;
    public String Z;
    public String a;
    public String aa;
    public String ab;
    public String ac;
    public String ad;
    public String ae;
    public List<String> af;
    public Map<String, Object> ag;
    private String aj = "full";
    public String b;
    public String c;
    public int d = -1;
    public String e;
    public int f = -1;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public long n = -1;
    public String o;
    public List<String> p;
    public List<String> q;
    public String r;
    public String s;
    public String t;
    public Location u;
    public int v = -1;
    public String w;
    public String x = "Android";
    public String y;
    public String z;

    private static JSONObject a(Location location) {
        if (location != null) {
            try {
                StringBuilder sb = new StringBuilder("{\"lat\":");
                sb.append(location.getLatitude());
                sb.append(",\"lng\":");
                sb.append(location.getLongitude());
                sb.append(",\"acc\":");
                sb.append(location.getAccuracy());
                sb.append(",\"timestamp\":");
                sb.append(location.getTime());
                sb.append("}");
                return new JSONObject(sb.toString());
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    private void a(JSONObject jSONObject) {
        if (this.ag != null) {
            for (Entry entry : this.ag.entrySet()) {
                try {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                } catch (JSONException e2) {
                    ai.a((String) null, (String) null, (Throwable) e2);
                }
            }
        }
    }

    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("app_guid", this.a);
            jSONObject.put("app_id", this.b);
            jSONObject.put("app_version", this.c);
            jSONObject.put("base_station_id", this.d == -1 ? null : Integer.valueOf(this.d));
            jSONObject.put("bssid", this.e);
            jSONObject.put("bssid_array", this.af == null ? null : new JSONArray(this.af));
            jSONObject.put("cell_id", this.f == -1 ? null : Integer.valueOf(this.f));
            jSONObject.put("comp_version", this.g);
            jSONObject.put("conf_url", this.h);
            jSONObject.put("conf_version", this.i);
            jSONObject.put("conn_type", this.j);
            jSONObject.put("device_id", this.k);
            jSONObject.put("dc_id", this.ad);
            jSONObject.put("device_model", this.l);
            jSONObject.put("device_name", this.m);
            jSONObject.put("device_uptime", this.n == -1 ? null : Long.valueOf(this.n));
            jSONObject.put("ip_addrs", this.o);
            jSONObject.put("ip_addresses", this.p == null ? null : new JSONArray(this.p));
            jSONObject.put("known_apps", this.q == null ? null : new JSONArray(this.q));
            jSONObject.put("linker_id", this.r);
            jSONObject.put("locale_country", this.s);
            jSONObject.put("locale_lang", this.t);
            jSONObject.put("location", a(this.u));
            jSONObject.put("location_area_code", this.v == -1 ? null : Integer.valueOf(this.v));
            jSONObject.put("mac_addrs", this.w);
            jSONObject.put("os_type", this.x);
            jSONObject.put("os_version", this.y);
            jSONObject.put("payload_type", this.aj);
            jSONObject.put("phone_type", this.z);
            jSONObject.put("risk_comp_session_id", this.A);
            jSONObject.put("roaming", this.B);
            jSONObject.put("sim_operator_name", "".equals(this.C) ? null : this.C);
            jSONObject.put("sim_serial_number", this.D);
            jSONObject.put("sms_enabled", this.E);
            jSONObject.put("ssid", this.F);
            jSONObject.put("cdma_network_id", this.N == -1 ? null : Integer.valueOf(this.N));
            jSONObject.put("cdma_system_id", this.M == -1 ? null : Integer.valueOf(this.M));
            jSONObject.put("subscriber_id", this.G);
            jSONObject.put("timestamp", this.H);
            jSONObject.put("total_storage_space", this.I);
            jSONObject.put("tz_name", this.J);
            jSONObject.put("ds", this.K);
            jSONObject.put("tz", this.L);
            jSONObject.put("network_operator", this.O);
            jSONObject.put("source_app", this.P == -1 ? null : Integer.valueOf(this.P));
            jSONObject.put("source_app_version", this.Q);
            jSONObject.put("is_emulator", this.R);
            jSONObject.put("is_rooted", this.S);
            jSONObject.put("pairing_id", this.T);
            jSONObject.put("app_first_install_time", this.U);
            jSONObject.put("app_last_update_time", this.V);
            jSONObject.put("android_id", this.W);
            jSONObject.put("serial_number", this.Y);
            jSONObject.put("notif_token", this.X);
            jSONObject.put("bluetooth_mac_addrs", null);
            jSONObject.put("gsf_id", this.Z);
            jSONObject.put("VPN_setting", this.ab);
            jSONObject.put("proxy_setting", this.aa);
            jSONObject.put("c", this.ac);
            jSONObject.put("pm", this.ae);
            a(jSONObject);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public final JSONObject a(g gVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("is_emulator", gVar.R);
            jSONObject.put("is_rooted", gVar.S);
            jSONObject.put("app_guid", gVar.a);
            jSONObject.put("risk_comp_session_id", gVar.A);
            jSONObject.put("timestamp", gVar.H);
            jSONObject.put("payload_type", "incremental");
            jSONObject.put("source_app", gVar.P);
            jSONObject.put("pairing_id", gVar.T);
            a(jSONObject);
            if (this.b != null && !this.b.equals(gVar.b)) {
                jSONObject.put("app_id", gVar.b);
            }
            if (this.c != null && !this.c.equals(gVar.c)) {
                jSONObject.put("app_version", gVar.c);
            }
            if (this.d != gVar.d) {
                jSONObject.put("base_station_id", gVar.d);
            }
            if (this.e != null && !this.e.equals(gVar.e)) {
                jSONObject.put("bssid", gVar.e);
            }
            if (this.f != gVar.f) {
                jSONObject.put("cell_id", gVar.f);
            }
            if (this.g != null && !this.g.equals(gVar.g)) {
                jSONObject.put("comp_version", gVar.g);
            }
            if (this.i != null && !this.i.equals(gVar.i)) {
                jSONObject.put("conf_version", gVar.i);
            }
            if (this.h != null && !this.h.equals(gVar.h)) {
                jSONObject.put("conf_url", gVar.h);
            }
            if (this.j != null && !this.j.equals(gVar.j)) {
                jSONObject.put("conn_type", gVar.j);
            }
            if (this.k != null && !this.k.equals(gVar.k)) {
                jSONObject.put("device_id", gVar.k);
            }
            if (this.l != null && !this.l.equals(gVar.l)) {
                jSONObject.put("device_model", gVar.l);
            }
            if (this.m != null && !this.m.equals(gVar.m)) {
                jSONObject.put("device_name", gVar.m);
            }
            if (this.n != gVar.n) {
                jSONObject.put("device_uptime", gVar.n);
            }
            if (this.o != null && !this.o.equals(gVar.o)) {
                jSONObject.put("ip_addrs", gVar.o);
            }
            if (!(this.p == null || gVar.p == null || this.p.toString().equals(gVar.p.toString()))) {
                jSONObject.put("ip_addresses", new JSONArray(gVar.p));
            }
            if (!(this.q == null || gVar.q == null || this.q.toString().equals(gVar.q.toString()))) {
                jSONObject.put("known_apps", new JSONArray(gVar.q));
            }
            if (this.r != null && !this.r.equals(gVar.r)) {
                jSONObject.put("linker_id", gVar.r);
            }
            if (this.s != null && !this.s.equals(gVar.s)) {
                jSONObject.put("locale_country", gVar.s);
            }
            if (this.t != null && !this.t.equals(gVar.t)) {
                jSONObject.put("locale_lang", gVar.t);
            }
            if (!(this.u == null || gVar.u == null || this.u.toString().equals(gVar.u.toString()))) {
                jSONObject.put("location", a(gVar.u));
            }
            if (this.v != gVar.v) {
                jSONObject.put("location_area_code", gVar.v);
            }
            if (this.w != null && !this.w.equals(gVar.w)) {
                jSONObject.put("mac_addrs", gVar.w);
            }
            if (this.x != null && !this.x.equals(gVar.x)) {
                jSONObject.put("os_type", gVar.x);
            }
            if (this.y != null && !this.y.equals(gVar.y)) {
                jSONObject.put("os_version", gVar.y);
            }
            if (this.z != null && !this.z.equals(gVar.z)) {
                jSONObject.put("phone_type", gVar.z);
            }
            if (this.B != null && this.B.equals(gVar.B)) {
                jSONObject.put("roaming", gVar.B);
            }
            if (this.C != null && !this.C.equals(gVar.C)) {
                jSONObject.put("sim_operator_name", gVar.C);
            }
            if (this.D != null && !this.D.equals(gVar.D)) {
                jSONObject.put("sim_serial_number", gVar.D);
            }
            if (this.E != null && this.E.equals(gVar.E)) {
                jSONObject.put("sms_enabled", gVar.E);
            }
            if (this.F != null && !this.F.equals(gVar.F)) {
                jSONObject.put("ssid", gVar.F);
            }
            if (this.N != gVar.N) {
                jSONObject.put("cdma_network_id", gVar.N);
            }
            if (this.M != gVar.M) {
                jSONObject.put("cdma_system_id", gVar.M);
            }
            if (this.G != null && !this.G.equals(gVar.G)) {
                jSONObject.put("subscriber_id", gVar.G);
            }
            if (this.I != gVar.I) {
                jSONObject.put("total_storage_space", gVar.I);
            }
            if (this.J != null && !this.J.equals(gVar.J)) {
                jSONObject.put("tz_name", gVar.J);
            }
            if (this.K != null && !this.K.equals(gVar.K)) {
                jSONObject.put("ds", gVar.K);
            }
            if (this.L != null && !this.L.equals(gVar.L)) {
                jSONObject.put("tz", gVar.L);
            }
            if (this.O != null && !this.O.equals(gVar.O)) {
                jSONObject.put("network_operator", gVar.O);
            }
            if (this.Q != null && !this.Q.equals(gVar.Q)) {
                jSONObject.put("source_app_version", gVar.Q);
            }
            if (this.U != gVar.U) {
                jSONObject.put("app_first_install_time", gVar.U);
            }
            if (this.V != gVar.V) {
                jSONObject.put("app_last_update_time", gVar.V);
            }
            if (this.W != null && !this.W.equals(gVar.W)) {
                jSONObject.put("android_id", gVar.W);
            }
            if (this.Y != null && !this.Y.equals(gVar.Y)) {
                jSONObject.put("serial_number", gVar.Y);
            }
            if (this.X != null && !this.X.equals(gVar.X)) {
                jSONObject.put("notif_token", gVar.X);
            }
            if (this.Z != null && !this.Z.equals(gVar.Z)) {
                jSONObject.put("gsf_id", gVar.Z);
            }
            if (this.ab != null && !this.ab.equals(gVar.ab)) {
                jSONObject.put("VPN_setting", gVar.ab);
            }
            if (this.aa != null && !this.aa.equals(gVar.aa)) {
                jSONObject.put("proxy_setting", gVar.aa);
            }
            if (this.ac != null && !this.ac.equals(gVar.ac)) {
                jSONObject.put("c", gVar.ac);
            }
            if (this.ae != null && !this.ae.equals(gVar.ae)) {
                jSONObject.put("pm", gVar.ae);
            }
            if (this.af != null && !this.af.equals(gVar.af)) {
                jSONObject.put("bssid_arr", gVar.af);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
