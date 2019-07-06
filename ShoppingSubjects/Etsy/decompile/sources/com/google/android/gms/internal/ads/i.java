package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class i {
    private final boolean a;
    private final boolean b;
    private final boolean c;
    private final boolean d;
    private final boolean e;

    private i(k kVar) {
        this.a = kVar.a;
        this.b = kVar.b;
        this.c = kVar.c;
        this.d = kVar.d;
        this.e = kVar.e;
    }

    public final JSONObject a() {
        try {
            return new JSONObject().put("sms", this.a).put("tel", this.b).put("calendar", this.c).put("storePicture", this.d).put("inlineVideo", this.e);
        } catch (JSONException e2) {
            gv.b("Error occured while obtaining the MRAID capabilities.", e2);
            return null;
        }
    }
}
