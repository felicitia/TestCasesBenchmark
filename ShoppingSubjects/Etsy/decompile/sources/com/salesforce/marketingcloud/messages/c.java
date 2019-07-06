package com.salesforce.marketingcloud.messages;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import com.salesforce.marketingcloud.j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class c {
    /* access modifiers changed from: private */
    public static final String a = j.a(c.class);
    private int b;
    private Date c;
    private int d;
    private int e;
    private Date f;

    public static abstract class a {
        @RestrictTo({Scope.LIBRARY})
        public static a a(String str, String str2) {
            if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
                return new h(str, str2);
            }
            throw new IllegalStateException("Invalid media provided.");
        }

        @Nullable
        public abstract String a();

        @Nullable
        public abstract String b();
    }

    public static abstract class b {
        public abstract b a(int i);

        public abstract b a(a aVar);

        public abstract b a(String str);

        public abstract b a(Date date);

        public abstract b a(Map<String, String> map);

        public abstract b a(boolean z);

        public abstract c a();

        public abstract b b(int i);

        public abstract b b(String str);

        public abstract b b(Date date);

        public abstract b c(int i);

        public abstract b c(String str);

        public abstract b d(int i);

        public abstract b d(String str);

        public abstract b e(int i);

        public abstract b e(String str);

        public abstract b f(int i);

        public abstract b f(String str);

        public abstract b g(int i);

        public abstract b g(String str);
    }

    /* renamed from: com.salesforce.marketingcloud.messages.c$c reason: collision with other inner class name */
    static class C0171c {
        C0171c() {
        }

        public a a(JSONObject jSONObject, String str) {
            JSONObject optJSONObject = jSONObject.optJSONObject(str);
            if (optJSONObject != null) {
                try {
                    return a.a(optJSONObject.optString("androidUrl"), optJSONObject.optString("alt"));
                } catch (Exception e) {
                    j.c(c.a, e, "Unable to create media object from json: ", optJSONObject);
                }
            }
            return null;
        }
    }

    static class d {
        d() {
        }

        public final List<c> a(JSONObject jSONObject, String str) {
            List<c> emptyList = Collections.emptyList();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray(str);
                int length = jSONArray.length();
                if (length > 0) {
                    List<c> arrayList = new ArrayList<>();
                    for (int i = 0; i < length; i++) {
                        try {
                            arrayList.add(c.a(jSONArray.getJSONObject(i)));
                        } catch (Exception e) {
                            try {
                                j.b(c.a, e, "Unable to create message", new Object[0]);
                            } catch (JSONException e2) {
                                e = e2;
                                emptyList = arrayList;
                            }
                        }
                    }
                    return arrayList;
                }
            } catch (JSONException e3) {
                e = e3;
                j.c(c.a, e, "Unable to read messages from json payload", new Object[0]);
                return emptyList;
            }
            return emptyList;
        }
    }

    @RestrictTo({Scope.LIBRARY})
    public static c a(JSONObject jSONObject) {
        return g.b(jSONObject);
    }

    public static b t() {
        return new C0166a();
    }

    @NonNull
    public abstract String a();

    @RestrictTo({Scope.LIBRARY})
    public final void a(int i) {
        this.b = i;
    }

    @RestrictTo({Scope.LIBRARY})
    public final void a(Date date) {
        this.c = date;
    }

    @Nullable
    public abstract String b();

    @RestrictTo({Scope.LIBRARY})
    public final void b(int i) {
        this.d = i;
    }

    @RestrictTo({Scope.LIBRARY})
    public final void b(Date date) {
        this.f = date;
    }

    @NonNull
    public abstract String c();

    @RestrictTo({Scope.LIBRARY})
    public final void c(int i) {
        this.e = i;
    }

    @Nullable
    public abstract String d();

    @Nullable
    public abstract a e();

    @Nullable
    public abstract Date f();

    @Nullable
    public abstract Date g();

    public abstract int h();

    public abstract int i();

    @Nullable
    public abstract String j();

    public abstract int k();

    public abstract int l();

    public abstract int m();

    public abstract boolean n();

    public abstract int o();

    public abstract int p();

    @Nullable
    public abstract String q();

    @Nullable
    public abstract Map<String, String> r();

    @Nullable
    public abstract String s();

    public final int u() {
        return this.b;
    }

    @Nullable
    public final Date v() {
        return this.c;
    }

    public final int w() {
        return this.d;
    }

    public final int x() {
        return this.e;
    }

    @Nullable
    public final Date y() {
        return this.f;
    }
}
