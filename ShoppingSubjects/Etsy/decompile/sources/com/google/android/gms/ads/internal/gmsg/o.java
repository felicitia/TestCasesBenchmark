package com.google.android.gms.ads.internal.gmsg;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.internal.ads.ack;
import com.google.android.gms.internal.ads.aoe;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.fu;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.jd;
import com.google.android.gms.internal.ads.mo;
import com.google.android.gms.internal.ads.mu;
import com.google.android.gms.internal.ads.mv;
import com.google.android.gms.internal.ads.nn;
import com.google.android.gms.internal.ads.of;
import com.google.android.gms.internal.ads.ok;
import com.google.android.gms.internal.ads.om;
import com.google.android.gms.internal.ads.on;
import com.google.android.gms.internal.ads.zzcj;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class o {
    public static final ae<nn> a = p.a;
    public static final ae<nn> b = q.a;
    public static final ae<nn> c = r.a;
    public static final ae<nn> d = new w();
    public static final ae<nn> e = new x();
    public static final ae<nn> f = s.a;
    public static final ae<Object> g = new y();
    public static final ae<nn> h = new z();
    public static final ae<nn> i = t.a;
    public static final ae<nn> j = new aa();
    public static final ae<nn> k = new ab();
    public static final ae<mo> l = new mu();
    public static final ae<mo> m = new mv();
    public static final ae<nn> n = new n();
    public static final g o = new g();
    public static final ae<nn> p = new ac();
    public static final ae<nn> q = new ad();
    public static final ae<nn> r = new v();
    private static final ae<Object> s = new u();

    static final /* synthetic */ void a(aoe aoe, Map map) {
        String str = (String) map.get("u");
        if (str == null) {
            gv.e("URL missing from click GMSG.");
            return;
        }
        Uri parse = Uri.parse(str);
        try {
            ack zzui = ((ok) aoe).zzui();
            if (zzui != null && zzui.a(parse)) {
                parse = zzui.a(parse, ((of) aoe).getContext(), ((on) aoe).getView(), ((of) aoe).zzto());
            }
        } catch (zzcj unused) {
            String str2 = "Unable to append parameter to URL: ";
            String valueOf = String.valueOf(str);
            gv.e(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        of ofVar = (of) aoe;
        new jd(ofVar.getContext(), ((om) aoe).zztq().zzcw, fu.a(parse, ofVar.getContext()).toString()).h();
    }

    static final /* synthetic */ void a(of ofVar, Map map) {
        String str = (String) map.get("u");
        if (str == null) {
            gv.e("URL missing from httpTrack GMSG.");
        } else {
            new jd(ofVar.getContext(), ((om) ofVar).zztq().zzcw, str).h();
        }
    }

    static final /* synthetic */ void a(ok okVar, Map map) {
        String str = (String) map.get("tx");
        String str2 = (String) map.get("ty");
        String str3 = (String) map.get("td");
        try {
            int parseInt = Integer.parseInt(str);
            int parseInt2 = Integer.parseInt(str2);
            int parseInt3 = Integer.parseInt(str3);
            ack zzui = okVar.zzui();
            if (zzui != null) {
                zzui.a().a(parseInt, parseInt2, parseInt3);
            }
        } catch (NumberFormatException unused) {
            gv.e("Could not parse touch parameters from gmsg.");
        }
    }

    static final /* synthetic */ void b(of ofVar, Map map) {
        String str;
        PackageManager packageManager = ofVar.getContext().getPackageManager();
        try {
            try {
                JSONArray jSONArray = new JSONObject((String) map.get("data")).getJSONArray("intents");
                JSONObject jSONObject = new JSONObject();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    try {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                        String optString = jSONObject2.optString("id");
                        String optString2 = jSONObject2.optString("u");
                        String optString3 = jSONObject2.optString("i");
                        String optString4 = jSONObject2.optString("m");
                        String optString5 = jSONObject2.optString("p");
                        String optString6 = jSONObject2.optString("c");
                        jSONObject2.optString("f");
                        jSONObject2.optString("e");
                        String optString7 = jSONObject2.optString("intent_url");
                        Intent intent = null;
                        if (!TextUtils.isEmpty(optString7)) {
                            try {
                                intent = Intent.parseUri(optString7, 0);
                            } catch (URISyntaxException e2) {
                                String str2 = "Error parsing the url: ";
                                String valueOf = String.valueOf(optString7);
                                gv.b(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e2);
                            }
                        }
                        boolean z = true;
                        if (intent == null) {
                            intent = new Intent();
                            if (!TextUtils.isEmpty(optString2)) {
                                intent.setData(Uri.parse(optString2));
                            }
                            if (!TextUtils.isEmpty(optString3)) {
                                intent.setAction(optString3);
                            }
                            if (!TextUtils.isEmpty(optString4)) {
                                intent.setType(optString4);
                            }
                            if (!TextUtils.isEmpty(optString5)) {
                                intent.setPackage(optString5);
                            }
                            if (!TextUtils.isEmpty(optString6)) {
                                String[] split = optString6.split("/", 2);
                                if (split.length == 2) {
                                    intent.setComponent(new ComponentName(split[0], split[1]));
                                }
                            }
                        }
                        if (packageManager.resolveActivity(intent, 65536) == null) {
                            z = false;
                        }
                        try {
                            jSONObject.put(optString, z);
                        } catch (JSONException e3) {
                            e = e3;
                            str = "Error constructing openable urls response.";
                        }
                    } catch (JSONException e4) {
                        e = e4;
                        str = "Error parsing the intent data.";
                        gv.b(str, e);
                    }
                }
                ((aoe) ofVar).zza("openableIntents", jSONObject);
            } catch (JSONException unused) {
                ((aoe) ofVar).zza("openableIntents", new JSONObject());
            }
        } catch (JSONException unused2) {
            ((aoe) ofVar).zza("openableIntents", new JSONObject());
        }
    }

    static final /* synthetic */ void c(of ofVar, Map map) {
        String str = (String) map.get("urls");
        if (TextUtils.isEmpty(str)) {
            gv.e("URLs missing in canOpenURLs GMSG.");
            return;
        }
        String[] split = str.split(",");
        HashMap hashMap = new HashMap();
        PackageManager packageManager = ofVar.getContext().getPackageManager();
        for (String str2 : split) {
            String[] split2 = str2.split(";", 2);
            boolean z = true;
            if (packageManager.resolveActivity(new Intent(split2.length > 1 ? split2[1].trim() : "android.intent.action.VIEW", Uri.parse(split2[0].trim())), 65536) == null) {
                z = false;
            }
            hashMap.put(str2, Boolean.valueOf(z));
        }
        ((aoe) ofVar).zza("openableURLs", (Map<String, ?>) hashMap);
    }
}
