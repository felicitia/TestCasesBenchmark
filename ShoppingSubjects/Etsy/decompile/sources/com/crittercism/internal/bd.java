package com.crittercism.internal;

import com.etsy.android.lib.models.ResponseConstants;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public final class bd implements bi {
    String a;
    String b;

    public static class a extends ce {
        public a(av avVar) {
            super(avVar);
        }

        public final bz a(as asVar, List<? extends bi> list) {
            URL url = new URL(asVar.b, "/android_v2/update_user_metadata");
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("app_id", this.a.e);
                jSONObject.put("hashed_device_id", this.a.h());
                jSONObject.put("library_version", "5.8.10");
                jSONObject.put("development_platform", this.a.h);
                JSONObject jSONObject2 = new JSONObject();
                for (bi biVar : list) {
                    bd bdVar = (bd) biVar;
                    jSONObject2.put(bdVar.a, bdVar.b);
                }
                jSONObject.put(ResponseConstants.METADATA, jSONObject2);
                return bz.a(url, jSONObject, this.b);
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    public bd(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final String f() {
        return this.a;
    }

    public final Object g() {
        throw new UnsupportedOperationException();
    }
}
