package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.ao;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final /* synthetic */ class aop {
    public static void a(aoo aoo, String str, String str2) {
        StringBuilder sb = new StringBuilder(3 + String.valueOf(str).length() + String.valueOf(str2).length());
        sb.append(str);
        sb.append("(");
        sb.append(str2);
        sb.append(");");
        aoo.zzbe(sb.toString());
    }

    public static void a(aoo aoo, String str, Map map) {
        try {
            aoo.zza(str, ao.e().a(map));
        } catch (JSONException unused) {
            gv.e("Could not convert parameters to JSON.");
        }
    }

    public static void a(aoo aoo, String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        aoo.a(str, jSONObject.toString());
    }

    public static void b(aoo aoo, String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(str);
        sb.append("'");
        sb.append(",");
        sb.append(jSONObject2);
        sb.append(");");
        String str2 = "Dispatching AFMA event: ";
        String valueOf = String.valueOf(sb.toString());
        gv.b(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        aoo.zzbe(sb.toString());
    }
}
