package com.google.android.gms.internal.ads;

import android.support.v4.util.SimpleArrayMap;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class bc implements aq<zzos> {
    private final boolean a;

    public bc(boolean z) {
        this.a = z;
    }

    public final /* synthetic */ aln a(ai aiVar, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        SimpleArrayMap simpleArrayMap2 = new SimpleArrayMap();
        kt a2 = aiVar.a(jSONObject);
        kt a3 = aiVar.a(jSONObject, "video");
        JSONArray jSONArray = jSONObject.getJSONArray("custom_assets");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            String string = jSONObject2.getString("type");
            if ("string".equals(string)) {
                simpleArrayMap2.put(jSONObject2.getString(ResponseConstants.NAME), jSONObject2.getString("string_value"));
            } else if (ResponseConstants.IMAGE.equals(string)) {
                simpleArrayMap.put(jSONObject2.getString(ResponseConstants.NAME), aiVar.a(jSONObject2, "image_value", this.a));
            } else {
                String str = "Unknown custom asset type: ";
                String valueOf = String.valueOf(string);
                gv.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        }
        nn a4 = ai.a(a3);
        String string2 = jSONObject.getString("custom_template_id");
        SimpleArrayMap simpleArrayMap3 = new SimpleArrayMap();
        for (int i2 = 0; i2 < simpleArrayMap.size(); i2++) {
            simpleArrayMap3.put(simpleArrayMap.keyAt(i2), ((Future) simpleArrayMap.valueAt(i2)).get());
        }
        zzos zzos = new zzos(string2, simpleArrayMap3, simpleArrayMap2, (zzoj) a2.get(), a4 != null ? a4.zztm() : null, a4 != null ? a4.getView() : null);
        return zzos;
    }
}
