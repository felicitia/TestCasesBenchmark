package com.klarna.checkout.internal.a;

import android.net.ConnectivityManager.NetworkCallback;
import android.os.Bundle;
import com.klarna.checkout.internal.a;
import com.klarna.checkout.internal.g;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class c implements a {
    public final a a;
    NetworkCallback b;
    public String c;
    public String d;
    public HashMap<String, String> e;
    public boolean f;
    public String g;
    public String h;
    public String i;
    a j = this;

    public c(a aVar) {
        this.a = aVar;
    }

    private void b(Bundle bundle, Map<String, List<String>> map) {
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : map.keySet()) {
                if (str != null && !str.equals("null")) {
                    jSONObject.put(str, ((List) map.get(str)).get(0));
                }
            }
            try {
                jSONObject.put("Set-Cookie", a().b(new URI(bundle.getString("URL"))));
            } catch (URISyntaxException e2) {
                e2.getMessage();
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("id", bundle.getString("id"));
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("headers", jSONObject);
            jSONObject3.put("status", bundle.getString("STATUS_CODE"));
            jSONObject3.put("body", bundle.getString("RESPONSE"));
            jSONObject2.put("data", jSONObject3);
            jSONObject2.put("event", "response");
            jSONObject2.put("action", "http");
            this.a.a(jSONObject2.toString(), "*", bundle.getBoolean("wasRequestFromOverlay"));
        } catch (JSONException e3) {
            e3.getMessage();
        }
    }

    public final com.klarna.checkout.internal.b.a a() {
        return this.a.c.a;
    }

    public final void a(Bundle bundle, Map<String, List<String>> map) {
        if (map != null) {
            try {
                String string = bundle.getString("requestingURL");
                if (map.containsKey("Set-Cookie") && string != null) {
                    List list = (List) map.get("Set-Cookie");
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        it.next();
                    }
                    a().a(list, new URI(bundle.getString("URL")), this.a.j, new g() {
                        public final void a(String str) {
                        }
                    });
                }
                String string2 = bundle.getString("callingEvent");
                if (string2 != null && string2.equals("request")) {
                    b(bundle, map);
                }
                if (!(this.c == null || this.b == null)) {
                    e.a(this.a.j, this.b);
                }
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
    }
}
