package com.google.android.gms.internal.ads;

import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

final /* synthetic */ class alt implements op {
    private final als a;
    private final Map b;
    private final az c;

    alt(als als, Map map, az azVar) {
        this.a = als;
        this.b = map;
        this.c = azVar;
    }

    public final void a(boolean z) {
        als als = this.a;
        Map map = this.b;
        az azVar = this.c;
        als.a.b = (String) map.get("id");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("messageType", "htmlLoaded");
            jSONObject.put("id", als.a.b);
            azVar.a("sendMessageToNativeJs", jSONObject);
        } catch (JSONException e) {
            gv.b("Unable to dispatch sendMessageToNativeJs event", e);
        }
    }
}
