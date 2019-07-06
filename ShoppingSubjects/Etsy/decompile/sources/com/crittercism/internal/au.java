package com.crittercism.internal;

import android.support.annotation.NonNull;
import com.etsy.android.lib.requests.HttpUtil;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import org.apache.http.entity.mime.MIME;
import org.json.JSONException;
import org.json.JSONObject;

public final class au implements bi {
    private av a;
    private String b = bh.a.a();

    public static class a extends ce {
        public a(av avVar) {
            super(avVar);
        }

        public final bz a(as asVar, List<? extends bi> list) {
            JSONObject jSONObject;
            list.size();
            if (list.size() == 0) {
                cm.d("received no config requests. empty payload request will be generated.");
                jSONObject = new JSONObject();
            } else {
                if (list.size() >= 2) {
                    cm.d("expected only one config request event.");
                }
                jSONObject = ((au) list.get(0)).g();
                if (jSONObject == null) {
                    cm.d("config request returned null json. sending empty payload");
                    jSONObject = new JSONObject();
                }
            }
            URL url = new URL(asVar.d, "/v0/config");
            HashMap hashMap = new HashMap();
            hashMap.put(MIME.CONTENT_TYPE, HttpUtil.JSON_CONTENT_TYPE);
            hashMap.put("CRAppId", this.a.e);
            hashMap.put("CRVersion", "5.8.10");
            hashMap.put("CRProtocolVersion", "1.2.0");
            return bz.a(url, jSONObject, hashMap);
        }
    }

    public au(@NonNull av avVar) {
        this.a = avVar;
    }

    public final String f() {
        return this.b;
    }

    /* renamed from: a */
    public final JSONObject g() {
        try {
            return new JSONObject().putOpt("app_id", this.a.e).putOpt("device_id", this.a.h()).putOpt("library_version", "5.8.10").putOpt("protocol_version", "1.2.0");
        } catch (JSONException unused) {
            return null;
        }
    }
}
