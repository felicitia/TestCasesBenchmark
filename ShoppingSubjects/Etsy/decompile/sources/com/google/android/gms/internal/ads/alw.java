package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.ae;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

final class alw implements ae<Object> {
    private final /* synthetic */ az a;
    private final /* synthetic */ alr b;

    alw(alr alr, az azVar) {
        this.b = alr;
        this.a = azVar;
    }

    public final void zza(Object obj, Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (String str : map.keySet()) {
                jSONObject.put(str, map.get(str));
            }
            jSONObject.put("id", this.b.b);
            this.a.a("sendMessageToNativeJs", jSONObject);
        } catch (JSONException e) {
            gv.b("Unable to dispatch sendMessageToNativeJs event", e);
        }
    }
}
