package com.crittercism.internal;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.ResponseConstants;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ar implements bi {
    String a;
    String b;
    String c;
    String d;
    String e;
    String f;
    String g;
    int h;
    int i;
    String j;
    int k;
    String l;
    long m;
    long n;
    long o;
    public float p;

    public static class a implements com.crittercism.internal.az.b<ar> {
        private a() {
        }

        public /* synthetic */ a(byte b) {
            this();
        }

        public final /* synthetic */ bi a(File file) {
            return b(file);
        }

        public final /* synthetic */ void a(bi biVar, OutputStream outputStream) {
            ar arVar = (ar) biVar;
            try {
                outputStream.write(new JSONObject().putOpt("fileName", arVar.a).putOpt("appId", arVar.b).putOpt("deviceId", arVar.c).putOpt("sdkVersion", arVar.d).putOpt(ResponseConstants.RATE, Float.valueOf(arVar.p)).putOpt("model", arVar.e).putOpt("osVersion", arVar.f).putOpt("carrier", arVar.g).putOpt("mobileCountryCode", Integer.valueOf(arVar.h)).putOpt("mobileNetworkCode", Integer.valueOf(arVar.i)).putOpt("appVersion", arVar.j).putOpt("appVersionCode", Integer.valueOf(arVar.k)).putOpt("locale", arVar.l).putOpt("timestamp", Long.valueOf(arVar.m)).putOpt("timestampMillis", Long.valueOf(arVar.n)).putOpt("bootTime", Long.valueOf(arVar.o)).toString().getBytes("UTF8"));
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }

        private static ar b(File file) {
            try {
                JSONObject jSONObject = new JSONObject(cn.b(file));
                ar arVar = new ar(0);
                arVar.a = jSONObject.getString("fileName");
                arVar.b = jSONObject.getString("appId");
                arVar.c = jSONObject.getString("deviceId");
                arVar.d = jSONObject.getString("sdkVersion");
                arVar.p = (float) jSONObject.getDouble(ResponseConstants.RATE);
                arVar.e = jSONObject.getString("model");
                arVar.f = jSONObject.getString("osVersion");
                arVar.g = jSONObject.getString("carrier");
                arVar.h = jSONObject.getInt("mobileCountryCode");
                arVar.i = jSONObject.getInt("mobileNetworkCode");
                arVar.j = jSONObject.getString("appVersion");
                arVar.k = jSONObject.getInt("appVersionCode");
                arVar.l = jSONObject.getString("locale");
                arVar.m = jSONObject.getLong("timestamp");
                arVar.n = jSONObject.getLong("timestampMillis");
                arVar.o = jSONObject.getLong("bootTime");
                return arVar;
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    public static class b extends ce {
        public b(av avVar) {
            super(avVar);
        }

        /* access modifiers changed from: private */
        /* renamed from: b */
        public cc a(as asVar, List<? extends bi> list) {
            URL url = asVar.h;
            if (url == null) {
                cm.d("no base url for dhub config, will try reporting again later");
                return null;
            }
            URL url2 = new URL(url, "/events/v1");
            com.crittercism.internal.cc.a aVar = new com.crittercism.internal.cc.a();
            aVar.a = url2;
            aVar.b = this.b;
            try {
                for (bi biVar : list) {
                    ar arVar = (ar) biVar;
                    JSONObject b = arVar.b();
                    b.put("protocolVersion", "1.2.0");
                    JSONObject c = arVar.c();
                    c.put("current", "true");
                    JSONObject a = arVar.a();
                    if (a != null) {
                        Iterator keys = a.keys();
                        while (keys.hasNext()) {
                            String str = (String) keys.next();
                            c.putOpt(str, a.opt(str));
                        }
                    }
                    JSONObject put = new JSONObject().put("eventData", c);
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(put);
                    aVar.a("appLoad", new JSONObject().put("constants", b).put("events", jSONArray).toString());
                }
                return aVar.a();
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    public static class c extends ce {
        public c(av avVar) {
            super(avVar);
            this.b.put("CRDontRespond", "true");
        }

        /* access modifiers changed from: private */
        /* renamed from: b */
        public cc a(as asVar, List<? extends bi> list) {
            URL url = new URL(asVar.e, "/events/v1");
            com.crittercism.internal.cc.a aVar = new com.crittercism.internal.cc.a();
            aVar.a = url;
            aVar.b = this.b;
            try {
                for (bi biVar : list) {
                    ar arVar = (ar) biVar;
                    JSONObject b = arVar.b();
                    JSONObject c = arVar.c();
                    c.put("current", "true");
                    JSONObject put = new JSONObject().put("eventData", c);
                    JSONObject a = arVar.a();
                    if (a != null) {
                        put.putOpt("commonData", a);
                    }
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(put);
                    aVar.a("appLoad", new JSONObject().put("data", b).put("events", jSONArray).toString());
                }
                return aVar.a();
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    /* synthetic */ ar(byte b2) {
        this();
    }

    public ar(av avVar) {
        this.p = 1.0f;
        this.a = bh.a.a();
        this.b = avVar.e;
        this.c = avVar.h();
        this.d = "5.8.10";
        this.e = Build.MODEL;
        this.f = VERSION.RELEASE;
        this.g = avVar.b();
        this.h = avVar.c().intValue();
        this.i = avVar.d().intValue();
        this.j = avVar.a.a;
        this.k = avVar.a().intValue();
        this.l = avVar.i();
        this.m = System.nanoTime();
        this.n = System.currentTimeMillis();
        this.o = System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    private ar() {
        this.p = 1.0f;
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public JSONObject g() {
        try {
            return new JSONObject().putOpt("appID", this.b).putOpt("deviceID", this.c).putOpt("crPlatform", AppBuild.ANDROID_PLATFORM).putOpt("crVersion", this.d).putOpt(ResponseConstants.RATE, Float.valueOf(this.p)).putOpt("deviceModel", this.e).putOpt("osName", AppBuild.ANDROID_PLATFORM).putOpt("osVersion", this.f).putOpt("carrier", this.g).putOpt("mobileCountryCode", Integer.valueOf(this.h)).putOpt("mobileNetworkCode", Integer.valueOf(this.i)).putOpt("appVersion", this.j).putOpt("appVersionCode", Integer.valueOf(this.k)).putOpt("locale", this.l).putOpt("sentAt", cp.a.a());
        } catch (JSONException unused) {
            return null;
        }
    }

    public final JSONObject a() {
        try {
            return new JSONObject().putOpt("carrier", this.g).putOpt("mcc", Integer.valueOf(this.h)).putOpt("mnc", Integer.valueOf(this.i)).putOpt("locale", this.l);
        } catch (JSONException unused) {
            return null;
        }
    }

    public final JSONObject b() {
        try {
            return new JSONObject().putOpt("type", "appLoad").putOpt("appId", this.b).putOpt("appVersion", this.j).putOpt("appVersionCode", Integer.valueOf(this.k)).putOpt("deviceId", this.c).putOpt("deviceModel", this.e).putOpt("libraryVersion", this.d).putOpt(ResponseConstants.PLATFORM, AppBuild.ANDROID_PLATFORM).putOpt("systemName", AppBuild.ANDROID_PLATFORM).putOpt("systemVersion", this.f).putOpt("protocolVersion", "1.0.0").putOpt("sentAt", cp.a.a());
        } catch (JSONException unused) {
            return null;
        }
    }

    public final JSONObject c() {
        try {
            return new JSONObject().putOpt("appLoadType", Integer.valueOf(0)).putOpt(ResponseConstants.RATE, Float.valueOf(this.p)).putOpt("occurredAt", cp.a.a(new Date(this.n)));
        } catch (JSONException unused) {
            return null;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ar arVar = (ar) obj;
        if (this.h == arVar.h && this.i == arVar.i && this.b.equals(arVar.b) && this.c.equals(arVar.c) && this.d.equals(arVar.d) && Float.compare(this.p, arVar.p) == 0 && this.e.equals(arVar.e) && this.f.equals(arVar.f) && this.g.equals(arVar.g) && this.j.equals(arVar.j)) {
            return this.l.equals(arVar.l);
        }
        return false;
    }

    public final int hashCode() {
        return (31 * ((((((((((((((((((this.b.hashCode() * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + Float.floatToIntBits(this.p)) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31) + this.h) * 31) + this.i) * 31) + this.j.hashCode())) + this.l.hashCode();
    }

    public final String f() {
        return this.a;
    }
}
