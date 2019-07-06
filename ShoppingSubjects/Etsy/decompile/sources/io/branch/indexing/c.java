package io.branch.indexing;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.etsy.android.lib.models.editable.EditableListing;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ContentDiscoveryManifest */
public class c {
    private static c a;
    private JSONObject b;
    private String c;
    private int d = 0;
    private int e = 1;
    private int f = 0;
    private boolean g = false;
    private JSONArray h;
    private SharedPreferences i;
    private final String j = "BNC_CD_MANIFEST";

    /* compiled from: ContentDiscoveryManifest */
    class a {
        final JSONObject a;
        private boolean c;
        private int d;
        private int e = 15;

        /* access modifiers changed from: 0000 */
        public int a() {
            return this.d;
        }

        /* access modifiers changed from: 0000 */
        public int b() {
            return this.e;
        }

        a(JSONObject jSONObject) {
            this.a = jSONObject;
            if (jSONObject.has("h")) {
                try {
                    this.c = !jSONObject.getBoolean("h");
                } catch (JSONException e2) {
                    com.google.a.a.a.a.a.a.a(e2);
                }
            }
            try {
                if (jSONObject.has("dri")) {
                    this.d = jSONObject.getInt("dri");
                }
                if (jSONObject.has("mdr")) {
                    this.e = jSONObject.getInt("mdr");
                }
            } catch (JSONException e3) {
                com.google.a.a.a.a.a.a.a(e3);
            }
        }

        /* access modifiers changed from: 0000 */
        public JSONArray c() {
            if (this.a.has("ck")) {
                try {
                    return this.a.getJSONArray("ck");
                } catch (JSONException e2) {
                    com.google.a.a.a.a.a.a.a(e2);
                }
            }
            return null;
        }

        /* access modifiers changed from: 0000 */
        public boolean d() {
            return this.c;
        }

        /* access modifiers changed from: 0000 */
        public boolean e() {
            JSONArray c2 = c();
            return c2 != null && c2.length() == 0;
        }
    }

    private c(Context context) {
        this.i = context.getSharedPreferences("bnc_content_discovery_manifest_storage", 0);
        b(context);
    }

    public static c a(Context context) {
        if (a == null) {
            a = new c(context);
        }
        return a;
    }

    private void f() {
        this.i.edit().putString("BNC_CD_MANIFEST", this.b.toString()).apply();
    }

    private void b(Context context) {
        String string = this.i.getString("BNC_CD_MANIFEST", null);
        if (string != null) {
            try {
                this.b = new JSONObject(string);
                if (this.b.has("mv")) {
                    this.c = this.b.getString("mv");
                }
                if (this.b.has("m")) {
                    this.h = this.b.getJSONArray("m");
                }
            } catch (JSONException unused) {
                this.b = new JSONObject();
            }
        } else {
            this.b = new JSONObject();
        }
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject.has("cd")) {
            this.g = true;
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("cd");
                if (jSONObject2.has("mv")) {
                    this.c = jSONObject2.getString("mv");
                }
                if (jSONObject2.has("mhl")) {
                    this.e = jSONObject2.getInt("mhl");
                }
                if (jSONObject2.has("m")) {
                    this.h = jSONObject2.getJSONArray("m");
                }
                if (jSONObject2.has("mtl")) {
                    int i2 = jSONObject2.getInt("mtl");
                    if (i2 > 0) {
                        this.d = i2;
                    }
                }
                if (jSONObject2.has("mps")) {
                    this.f = jSONObject2.getInt("mps");
                }
                this.b.put("mv", this.c);
                this.b.put("m", this.h);
                f();
            } catch (JSONException unused) {
            }
        } else {
            this.g = false;
        }
    }

    /* access modifiers changed from: 0000 */
    public a a(Activity activity) {
        if (this.h == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        sb.append(activity.getClass().getSimpleName());
        String sb2 = sb.toString();
        int i2 = 0;
        while (i2 < this.h.length()) {
            try {
                JSONObject jSONObject = this.h.getJSONObject(i2);
                if (jSONObject.has("p") && jSONObject.getString("p").equals(sb2)) {
                    return new a(jSONObject);
                }
                i2++;
            } catch (JSONException unused) {
                return null;
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public boolean a() {
        return this.g;
    }

    /* access modifiers changed from: 0000 */
    public int b() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public int c() {
        return this.f;
    }

    /* access modifiers changed from: 0000 */
    public int d() {
        return this.e;
    }

    public String e() {
        if (TextUtils.isEmpty(this.c)) {
            return EditableListing.LISTING_ID_DEVICE_DRAFT;
        }
        return this.c;
    }
}
