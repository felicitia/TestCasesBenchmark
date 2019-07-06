package com.facebook.appevents.codeless.internal;

import com.etsy.android.lib.models.ResponseConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ParameterComponent */
public final class a {
    public final String a;
    public final String b;
    public final List<PathComponent> c;
    public final String d;

    public a(JSONObject jSONObject) throws JSONException {
        this.a = jSONObject.getString(ResponseConstants.NAME);
        this.b = jSONObject.optString(ResponseConstants.VALUE);
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("path");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(new PathComponent(optJSONArray.getJSONObject(i)));
            }
        }
        this.c = arrayList;
        this.d = jSONObject.optString("path_type", "absolute");
    }
}
