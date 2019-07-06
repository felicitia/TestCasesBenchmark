package com.salesforce.marketingcloud.analytics.b;

import android.support.annotation.NonNull;
import com.salesforce.marketingcloud.j;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

abstract class i extends h {
    /* access modifiers changed from: private */
    public static final String a = j.a(i.class);

    static abstract class a {
        a() {
        }

        /* access modifiers changed from: 0000 */
        public abstract boolean a();

        /* access modifiers changed from: 0000 */
        public abstract List<String> b();

        /* access modifiers changed from: 0000 */
        public abstract JSONObject c();
    }

    static class b {
        b() {
        }

        public void a(JSONObject jSONObject, String str, a aVar) {
            try {
                jSONObject.put(str, aVar.c());
            } catch (JSONException e) {
                j.c(i.a, e, "Failed to convert ObjectIds into JSONArray for PiOpenEvent payload.", new Object[0]);
            }
        }
    }

    i() {
    }

    public static i a(@NonNull Date date, boolean z, @NonNull List<String> list) {
        return new e("track_event", "app_open", date, new f(z, list));
    }

    public abstract a d();

    public abstract JSONObject e();
}
