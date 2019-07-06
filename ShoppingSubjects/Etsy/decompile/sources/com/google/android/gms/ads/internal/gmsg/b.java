package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.le;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class b implements ae<Object> {
    @VisibleForTesting
    private final HashMap<String, le<JSONObject>> a = new HashMap<>();

    public final Future<JSONObject> a(String str) {
        le leVar = new le();
        this.a.put(str, leVar);
        return leVar;
    }

    public final void b(String str) {
        le leVar = (le) this.a.get(str);
        if (leVar == null) {
            gv.c("Could not find the ad request for the corresponding ad response.");
            return;
        }
        if (!leVar.isDone()) {
            leVar.cancel(true);
        }
        this.a.remove(str);
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = (String) map.get("request_id");
        String str2 = (String) map.get("fetched_ad");
        gv.b("Received ad from the cache.");
        le leVar = (le) this.a.get(str);
        if (leVar == null) {
            gv.c("Could not find the ad request for the corresponding ad response.");
            return;
        }
        try {
            leVar.b(new JSONObject(str2));
        } catch (JSONException e) {
            gv.b("Failed constructing JSON object from value passed from javascript", e);
            leVar.b(null);
        } finally {
            this.a.remove(str);
        }
    }
}
