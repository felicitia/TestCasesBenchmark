package com.google.android.gms.internal.ads;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class aql {
    public static final aqk<JSONObject> a = new aqn();
    private static final Charset b = Charset.forName("UTF-8");
    private static final aqi<InputStream> c = aqm.a;

    static final /* synthetic */ InputStream a(JSONObject jSONObject) throws JSONException {
        return new ByteArrayInputStream(jSONObject.toString().getBytes(b));
    }
}
