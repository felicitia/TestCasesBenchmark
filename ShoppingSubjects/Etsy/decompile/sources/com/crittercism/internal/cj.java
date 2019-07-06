package com.crittercism.internal;

import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.ResponseConstants;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class cj implements bi {
    String a;
    long b;
    public int c;
    long d;
    long e;
    int f;
    int g;
    String h;
    List<b> i;
    float j;

    public static class a {
        public String a;
        public long b;
        public int c;
        public long d;
        int e = e.a;

        public final cj a() {
            cj cjVar = new cj(this.a, this.b, this.c, this.d, this.e);
            return cjVar;
        }
    }

    static class b implements Comparable<b> {
        public int a;
        public long b;

        public final /* bridge */ /* synthetic */ int compareTo(@NonNull Object obj) {
            b bVar = (b) obj;
            if (this.b < bVar.b) {
                return -1;
            }
            return this.b == bVar.b ? 0 : 1;
        }

        public b(int i, long j) {
            this.a = i;
            this.b = j;
        }

        public b(JSONObject jSONObject) {
            this.a = c.a()[jSONObject.getInt("type")];
            this.b = jSONObject.getLong("time");
        }
    }

    enum c {
        ;

        static {
            c = new int[]{a, b};
        }

        public static int[] a() {
            return (int[]) c.clone();
        }
    }

    public enum d {
        ;

        static {
            j = new int[]{a, b, c, d, e, f, g, h, i};
        }

        public static int[] a() {
            return (int[]) j.clone();
        }
    }

    public enum e {
        ;

        static {
            d = new int[]{a, b, c};
        }

        public static int[] a() {
            return (int[]) d.clone();
        }
    }

    public static class f extends ce {
        private av c;
        private ay<at> d;

        public f(av avVar, ay<at> ayVar) {
            super(avVar);
            this.c = avVar;
            this.d = ayVar;
        }

        public final bz a(as asVar, List<? extends bi> list) {
            StringBuilder sb = new StringBuilder();
            sb.append(asVar.c);
            sb.append("/api/v1/transactions");
            URL url = new URL(sb.toString());
            try {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("appID", this.c.e);
                jSONObject2.put("deviceID", this.c.h());
                jSONObject2.put("crPlatform", AppBuild.ANDROID_PLATFORM);
                jSONObject2.put("developmentPlatform", this.c.h);
                jSONObject2.put("crVersion", "5.8.10");
                jSONObject2.put("deviceModel", Build.MODEL);
                jSONObject2.put("osName", "Android");
                jSONObject2.put("osVersion", VERSION.RELEASE);
                jSONObject2.put("carrier", this.c.b());
                jSONObject2.put("mobileCountryCode", this.c.c());
                jSONObject2.put("mobileNetworkCode", this.c.d());
                jSONObject2.put("appVersion", this.c.a.a);
                jSONObject2.put("locale", this.c.i());
                jSONObject.put("appState", jSONObject2);
                JSONArray jSONArray = new JSONArray();
                for (bi g : list) {
                    jSONArray.put(g.g());
                }
                jSONObject.put(ResponseConstants.TRANSACTIONS, jSONArray);
                if (a(list)) {
                    jSONObject.put("systemBreadcrumbs", this.d.a());
                    jSONObject.put("breadcrumbs", new JSONObject());
                    jSONObject.put("endpoints", new JSONArray());
                }
                return bz.a(url, jSONObject, this.b);
            } catch (JSONException e) {
                throw ((IOException) new IOException(e.getMessage()).initCause(e));
            }
        }

        private static boolean a(List<? extends bi> list) {
            for (bi biVar : list) {
                int i = ((cj) biVar).f;
                if (i != d.c && i != d.i && i != d.h) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class g implements com.crittercism.internal.az.b<cj> {
        private g() {
        }

        public /* synthetic */ g(byte b) {
            this();
        }

        public final /* synthetic */ bi a(File file) {
            return b(file);
        }

        public final /* synthetic */ void a(bi biVar, OutputStream outputStream) {
            cj cjVar = (cj) biVar;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(ResponseConstants.NAME, cjVar.a);
                jSONObject.put(ResponseConstants.STATE, cjVar.f - 1);
                jSONObject.put("timeout", cjVar.b);
                jSONObject.put(ResponseConstants.VALUE, cjVar.c);
                jSONObject.put("startTime", cjVar.d);
                jSONObject.put("endTime", cjVar.e);
                jSONObject.put("sequenceNumber", cjVar.h);
                jSONObject.put(ResponseConstants.RATE, (double) cjVar.j);
                jSONObject.put("type", cjVar.g - 1);
                JSONArray jSONArray = new JSONArray();
                for (b bVar : cjVar.i) {
                    jSONArray.put(new JSONObject().put("type", bVar.a - 1).put("time", bVar.b));
                }
                jSONObject.put("lifeCycleTransitions", jSONArray);
                outputStream.write(jSONObject.toString().getBytes("UTF8"));
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }

        private static cj b(File file) {
            try {
                JSONObject jSONObject = new JSONObject(cn.b(file));
                cj cjVar = new cj(0);
                cjVar.a = jSONObject.getString(ResponseConstants.NAME);
                cjVar.f = d.a()[jSONObject.getInt(ResponseConstants.STATE)];
                cjVar.b = jSONObject.getLong("timeout");
                cjVar.c = jSONObject.getInt(ResponseConstants.VALUE);
                cjVar.d = jSONObject.getLong("startTime");
                cjVar.e = jSONObject.getLong("endTime");
                cjVar.h = jSONObject.getString("sequenceNumber");
                cjVar.j = (float) jSONObject.getDouble(ResponseConstants.RATE);
                cjVar.g = e.a()[jSONObject.getInt("type")];
                JSONArray jSONArray = jSONObject.getJSONArray("lifeCycleTransitions");
                for (int i = 0; i < jSONArray.length(); i++) {
                    cjVar.i.add(new b(jSONArray.getJSONObject(i)));
                }
                return cjVar;
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    /* synthetic */ cj(byte b2) {
        this();
    }

    public cj(String str, long j2, int i2, long j3, int i3) {
        this.b = -1;
        this.c = -1;
        this.e = -1;
        this.f = d.b;
        this.g = e.a;
        this.h = bh.a.a();
        this.i = new LinkedList();
        this.j = 1.0f;
        if (str.length() > 255) {
            str = str.substring(0, 255);
        }
        this.a = str;
        this.c = i2;
        this.d = j2;
        this.b = j3;
        this.g = i3;
    }

    private cj() {
        this.b = -1;
        this.c = -1;
        this.e = -1;
        this.f = d.b;
        this.g = e.a;
        this.h = bh.a.a();
        this.i = new LinkedList();
        this.j = 1.0f;
    }

    public final void a(int i2, long j2) {
        if (this.f == d.b) {
            this.e = j2;
            if (a() > this.b) {
                this.f = d.f;
            } else {
                this.f = i2;
            }
        }
    }

    public final long a() {
        long j2 = this.e;
        long j3 = 0;
        if (this.e < 0) {
            j2 = System.currentTimeMillis();
        }
        Collections.sort(this.i);
        Iterator it = this.i.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar.b < this.d) {
                it.remove();
            } else if (bVar.b > j2) {
                it.remove();
            }
        }
        if (this.f == d.f) {
            return this.b;
        }
        if (this.g == e.c || this.i.size() == 0) {
            return j2 - this.d;
        }
        int i2 = c.b;
        if (((b) this.i.get(0)).a == c.b) {
            i2 = c.a;
        }
        long j4 = this.d;
        for (b bVar2 : this.i) {
            if (bVar2.b >= this.d) {
                if (bVar2.b > j2) {
                    break;
                }
                if (i2 == c.b) {
                    j3 += bVar2.b - j4;
                }
                j4 = bVar2.b;
                i2 = bVar2.a;
            }
        }
        return i2 == c.b ? j3 + (j2 - j4) : j3;
    }

    /* renamed from: b */
    public final JSONArray g() {
        Object obj;
        try {
            JSONArray put = new JSONArray().put(this.a).put(this.f - 1).put(((double) this.b) / 1000.0d);
            if (this.c == -1) {
                obj = JSONObject.NULL;
            } else {
                obj = Integer.valueOf(this.c);
            }
            JSONArray put2 = put.put(obj).put(new JSONObject()).put(cp.a.a(new Date(this.d))).put(cp.a.a(new Date(this.e)));
            try {
                if (VERSION.SDK_INT >= 14) {
                    put2.put(((double) a()) / 1000.0d);
                    return put2;
                }
                put2.put(JSONObject.NULL);
                return put2;
            } catch (JSONException unused) {
                return put2;
            }
        } catch (JSONException unused2) {
            return null;
        }
    }

    public final String f() {
        return this.h;
    }
}
