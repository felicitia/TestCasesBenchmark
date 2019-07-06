package com.facebook.internal;

import android.net.Uri;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.EnumSet;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: FetchedAppSettings */
public final class j {
    private boolean a;
    private String b;
    private boolean c;
    private boolean d;
    private int e;
    private EnumSet<SmartLoginOption> f;
    private Map<String, Map<String, a>> g;
    private boolean h;
    private h i;
    private String j;
    private String k;
    private boolean l;
    private boolean m;
    private String n;
    private JSONArray o;

    /* compiled from: FetchedAppSettings */
    public static class a {
        private String a;
        private String b;
        private Uri c;
        private int[] d;

        public static a a(JSONObject jSONObject) {
            String optString = jSONObject.optString(ResponseConstants.NAME);
            Uri uri = null;
            if (z.a(optString)) {
                return null;
            }
            String[] split = optString.split("\\|");
            if (split.length != 2) {
                return null;
            }
            String str = split[0];
            String str2 = split[1];
            if (z.a(str) || z.a(str2)) {
                return null;
            }
            String optString2 = jSONObject.optString("url");
            if (!z.a(optString2)) {
                uri = Uri.parse(optString2);
            }
            return new a(str, str2, uri, a(jSONObject.optJSONArray("versions")));
        }

        private static int[] a(JSONArray jSONArray) {
            if (jSONArray == null) {
                return null;
            }
            int length = jSONArray.length();
            int[] iArr = new int[length];
            for (int i = 0; i < length; i++) {
                int optInt = jSONArray.optInt(i, -1);
                if (optInt == -1) {
                    String optString = jSONArray.optString(i);
                    if (!z.a(optString)) {
                        try {
                            optInt = Integer.parseInt(optString);
                        } catch (NumberFormatException e) {
                            z.a("FacebookSDK", (Exception) e);
                            optInt = -1;
                        }
                    }
                }
                iArr[i] = optInt;
            }
            return iArr;
        }

        private a(String str, String str2, Uri uri, int[] iArr) {
            this.a = str;
            this.b = str2;
            this.c = uri;
            this.d = iArr;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public Uri c() {
            return this.c;
        }

        public int[] d() {
            return this.d;
        }
    }

    public j(boolean z, String str, boolean z2, boolean z3, int i2, EnumSet<SmartLoginOption> enumSet, Map<String, Map<String, a>> map, boolean z4, h hVar, String str2, String str3, boolean z5, boolean z6, JSONArray jSONArray, String str4) {
        this.a = z;
        this.b = str;
        this.c = z2;
        this.d = z3;
        this.g = map;
        this.i = hVar;
        this.e = i2;
        this.h = z4;
        this.f = enumSet;
        this.j = str2;
        this.k = str3;
        this.l = z5;
        this.m = z6;
        this.o = jSONArray;
        this.n = str4;
    }

    public boolean a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public boolean f() {
        return this.h;
    }

    public EnumSet<SmartLoginOption> g() {
        return this.f;
    }

    public Map<String, Map<String, a>> h() {
        return this.g;
    }

    public h i() {
        return this.i;
    }

    public boolean j() {
        return this.l;
    }

    public boolean k() {
        return this.m;
    }

    public JSONArray l() {
        return this.o;
    }

    public String m() {
        return this.n;
    }

    public static a a(String str, String str2, String str3) {
        if (z.a(str2) || z.a(str3)) {
            return null;
        }
        j a2 = k.a(str);
        if (a2 != null) {
            Map map = (Map) a2.h().get(str2);
            if (map != null) {
                return (a) map.get(str3);
            }
        }
        return null;
    }
}
