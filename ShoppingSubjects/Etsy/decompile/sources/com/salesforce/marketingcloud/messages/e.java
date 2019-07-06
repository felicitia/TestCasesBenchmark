package com.salesforce.marketingcloud.messages;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.location.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class e {
    /* access modifiers changed from: private */
    public static final String a = j.a(e.class);
    private boolean b;

    @RestrictTo({Scope.LIBRARY})
    public static abstract class a {
        public abstract a a(int i);

        public abstract a a(com.salesforce.marketingcloud.location.b bVar);

        public abstract a a(String str);

        public abstract e a();

        public abstract a b(int i);

        public abstract a b(String str);

        public abstract a c(int i);

        public abstract a c(String str);

        public abstract a d(int i);

        public abstract a d(String str);
    }

    @RestrictTo({Scope.LIBRARY})
    public static final class b extends e {
        private final com.salesforce.marketingcloud.location.b a;
        private final int b;

        public b(com.salesforce.marketingcloud.location.b bVar, int i) {
            this.a = bVar;
            this.b = i;
        }

        public String a() {
            return "~~m@g1c_f3nc3~~";
        }

        public com.salesforce.marketingcloud.location.b b() {
            return this.a;
        }

        public int c() {
            return this.b;
        }

        @Nullable
        public String d() {
            return null;
        }

        public int e() {
            return 0;
        }

        public int f() {
            return 0;
        }

        public int g() {
            return -1;
        }

        @Nullable
        public String h() {
            return "MagicFence";
        }

        @Nullable
        public String i() {
            return "MagicFence";
        }

        public List<c> j() {
            return Collections.emptyList();
        }

        public f m() {
            return f.a(a(), (float) (((double) c()) * 0.8d), b().a(), b().b(), 2);
        }
    }

    @RestrictTo({Scope.LIBRARY})
    public static class c {
        public final List<e> a(JSONObject jSONObject, String str) {
            List<e> emptyList = Collections.emptyList();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray(str);
                int length = jSONArray.length();
                if (length > 0) {
                    List<e> arrayList = new ArrayList<>();
                    int i = 0;
                    while (i < length) {
                        try {
                            arrayList.add(e.b(jSONArray.getJSONObject(i)));
                            i++;
                        } catch (JSONException e) {
                            e = e;
                            emptyList = arrayList;
                            j.c(e.a, e, "Unable to read regions from json payload", new Object[0]);
                            return emptyList;
                        }
                    }
                    return arrayList;
                }
            } catch (JSONException e2) {
                e = e2;
                j.c(e.a, e, "Unable to read regions from json payload", new Object[0]);
                return emptyList;
            }
            return emptyList;
        }
    }

    private static int a(int i) {
        if (i < 100) {
            return 100;
        }
        return i;
    }

    /* access modifiers changed from: private */
    public static e b(JSONObject jSONObject) {
        return i.b(jSONObject);
    }

    @RestrictTo({Scope.LIBRARY})
    public static a k() {
        return new a().a(Collections.emptyList());
    }

    @NonNull
    public abstract String a();

    @RestrictTo({Scope.LIBRARY})
    public void a(boolean z) {
        this.b = z;
    }

    @NonNull
    public abstract com.salesforce.marketingcloud.location.b b();

    public abstract int c();

    @Nullable
    public abstract String d();

    public abstract int e();

    public abstract int f();

    public abstract int g();

    @Nullable
    public abstract String h();

    @Nullable
    public abstract String i();

    @NonNull
    public abstract List<c> j();

    @RestrictTo({Scope.LIBRARY})
    public boolean l() {
        return this.b;
    }

    @RestrictTo({Scope.LIBRARY})
    public f m() {
        return f.a(a(), (float) a(c()), b().a(), b().b(), 3);
    }
}
