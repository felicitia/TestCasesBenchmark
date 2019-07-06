package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.ae;
import java.util.Map;
import org.json.JSONObject;

final class bj implements ae<nn> {
    private final /* synthetic */ nn a;
    private final /* synthetic */ le b;
    private final /* synthetic */ bd c;

    bj(bd bdVar, nn nnVar, le leVar) {
        this.c = bdVar;
        this.a = nnVar;
        this.b = leVar;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        boolean z;
        JSONObject jSONObject;
        try {
            String str = (String) map.get("success");
            String str2 = (String) map.get("failure");
            if (!TextUtils.isEmpty(str2)) {
                jSONObject = new JSONObject(str2);
                z = false;
            } else {
                JSONObject jSONObject2 = new JSONObject(str);
                z = true;
                jSONObject = jSONObject2;
            }
            if (this.c.h.equals(jSONObject.optString("ads_id", ""))) {
                this.a.zzb("/nativeAdPreProcess", this);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("success", z);
                jSONObject3.put("json", jSONObject);
                this.b.b(jSONObject3);
            }
        } catch (Throwable th) {
            gv.b("Error while preprocessing json.", th);
            this.b.a(th);
        }
    }
}
