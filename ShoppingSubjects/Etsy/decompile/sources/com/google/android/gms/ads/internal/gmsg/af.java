package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.aoe;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.hd;
import java.util.Map;
import org.json.JSONObject;

final class af implements Runnable {
    final /* synthetic */ aoe a;
    private final /* synthetic */ Map b;
    private final /* synthetic */ HttpClient c;

    af(HttpClient httpClient, Map map, aoe aoe) {
        this.c = httpClient;
        this.b = map;
        this.a = aoe;
    }

    public final void run() {
        gv.b("Received Http request.");
        try {
            JSONObject send = this.c.send(new JSONObject((String) this.b.get("http_request")));
            if (send == null) {
                gv.c("Response should not be null.");
            } else {
                hd.a.post(new ag(this, send));
            }
        } catch (Exception e) {
            gv.b("Error converting request to json.", e);
        }
    }
}
