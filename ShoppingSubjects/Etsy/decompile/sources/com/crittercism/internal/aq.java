package com.crittercism.internal;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug;
import android.os.Debug.MemoryInfo;
import android.view.Display;
import android.view.WindowManager;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.ResponseConstants;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class aq implements bi {
    public long a;
    JSONArray b;
    JSONArray c;
    public String d;
    public String e;
    JSONArray f;
    public JSONArray g;
    public String h;
    JSONObject i;
    int j;
    boolean k;
    String l;
    public float m;

    public static class a implements com.crittercism.internal.az.b<aq> {
        private a() {
        }

        public /* synthetic */ a(byte b) {
            this();
        }

        public final /* synthetic */ bi a(File file) {
            return b(file);
        }

        public final /* synthetic */ void a(bi biVar, OutputStream outputStream) {
            aq aqVar = (aq) biVar;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("currentThreadID", aqVar.a);
                jSONObject.put("breadcrumbs", aqVar.b);
                jSONObject.put("txns", aqVar.c);
                jSONObject.put("exceptionName", aqVar.d);
                jSONObject.put("exceptionReason", aqVar.e);
                jSONObject.put("stacktrace", aqVar.f);
                jSONObject.put("threads", aqVar.g);
                jSONObject.put("ts", aqVar.h);
                jSONObject.put(ResponseConstants.RATE, (double) aqVar.m);
                jSONObject.put("appState", aqVar.i);
                jSONObject.put("suspectLineIndex", aqVar.j);
                jSONObject.put("isPluginException", aqVar.k);
                jSONObject.put("fileName", aqVar.l);
                outputStream.write(jSONObject.toString().getBytes());
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }

        private static aq b(File file) {
            try {
                JSONObject jSONObject = new JSONObject(cn.b(file));
                aq aqVar = new aq(0);
                aqVar.a = jSONObject.getLong("currentThreadID");
                aqVar.b = jSONObject.getJSONArray("breadcrumbs");
                aqVar.c = jSONObject.getJSONArray("txns");
                aqVar.d = jSONObject.getString("exceptionName");
                aqVar.e = jSONObject.getString("exceptionReason");
                aqVar.f = jSONObject.getJSONArray("stacktrace");
                aqVar.g = jSONObject.optJSONArray("threads");
                aqVar.h = jSONObject.getString("ts");
                aqVar.m = (float) jSONObject.getDouble(ResponseConstants.RATE);
                aqVar.i = jSONObject.getJSONObject("appState");
                aqVar.j = jSONObject.getInt("suspectLineIndex");
                aqVar.k = jSONObject.getBoolean("isPluginException");
                aqVar.l = jSONObject.getString("fileName");
                return aqVar;
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    public static class b extends ce {
        private String c;
        private String d;

        public b(av avVar, String str, String str2) {
            super(avVar);
            this.c = str;
            this.d = str2;
        }

        public final bz a(as asVar, List<? extends bi> list) {
            URL url = new URL(asVar.b, this.d);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("app_id", this.a.e);
                jSONObject.put("hashed_device_id", this.a.h());
                jSONObject.put("library_version", "5.8.10");
                jSONObject.put("sent_at", cp.a.a());
                JSONArray jSONArray = new JSONArray();
                for (bi g : list) {
                    jSONArray.put(g.g());
                }
                jSONObject.put(this.c, jSONArray);
                return bz.a(url, jSONObject, this.b);
            } catch (JSONException e) {
                throw ((IOException) new IOException(e.getMessage()).initCause(e));
            }
        }
    }

    /* synthetic */ aq(byte b2) {
        this();
    }

    public aq(Throwable th, av avVar, long j2) {
        Object obj;
        av avVar2 = avVar;
        this.b = new JSONArray();
        this.c = new JSONArray();
        this.e = "";
        this.f = new JSONArray();
        this.h = cp.a.a();
        this.j = -1;
        this.k = false;
        this.l = bh.a.a();
        this.m = 1.0f;
        this.k = th instanceof bq;
        this.l = bh.a.a();
        this.i = new JSONObject();
        try {
            JSONObject putOpt = this.i.putOpt("activity", avVar2.g).putOpt("app_version", avVar2.a.a).putOpt("app_version_code", avVar.a()).putOpt("arch", System.getProperty("os.arch"));
            String str = "battery_level";
            JSONArray jSONArray = null;
            Intent registerReceiver = avVar2.b.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerReceiver != null) {
                Double valueOf = Double.valueOf(1.0d);
                int intExtra = registerReceiver.getIntExtra("level", -1);
                double intExtra2 = (double) registerReceiver.getIntExtra(ResponseConstants.SCALE, -1);
                obj = (intExtra < 0 || intExtra2 <= 0.0d) ? valueOf : Double.valueOf(((double) intExtra) / intExtra2);
            } else {
                obj = null;
            }
            JSONObject putOpt2 = putOpt.putOpt(str, obj).putOpt("carrier", avVar.b()).putOpt("mobile_country_code", avVar.c()).putOpt("mobile_network_code", avVar.d()).putOpt("disk_space_free", avVar.j()).putOpt("disk_space_total", avVar.k()).putOpt("dpi", avVar.e()).putOpt("xdpi", Float.valueOf(avVar.f())).putOpt("ydpi", Float.valueOf(avVar.g())).putOpt("locale", avVar.i());
            String str2 = "logcat";
            if (avVar2.c.a) {
                jSONArray = avVar2.d.a();
            }
            MemoryInfo memoryInfo = new MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
            JSONObject putOpt3 = putOpt2.putOpt(str2, jSONArray).putOpt("memory_usage", Integer.valueOf((memoryInfo.dalvikPss + memoryInfo.nativePss + memoryInfo.otherPss) * 1024)).putOpt("memory_total", av.l()).putOpt("mobile_network", avVar2.a(0)).putOpt("model", Build.MODEL).putOpt(ResponseConstants.NAME, new String());
            String str3 = "orientation";
            int i2 = avVar2.b.getResources().getConfiguration().orientation;
            if (i2 == 0) {
                Display defaultDisplay = ((WindowManager) avVar2.b.getSystemService("window")).getDefaultDisplay();
                i2 = defaultDisplay.getWidth() == defaultDisplay.getHeight() ? 3 : defaultDisplay.getWidth() > defaultDisplay.getHeight() ? 2 : 1;
            }
            putOpt3.putOpt(str3, Integer.valueOf(i2)).putOpt(ResponseConstants.PLATFORM, AppBuild.ANDROID_PLATFORM).putOpt("sd_space_free", avVar.m()).putOpt("sd_space_total", avVar.n()).putOpt(ResponseConstants.PLATFORM, AppBuild.ANDROID_PLATFORM).putOpt("development_platform", avVar2.h).putOpt("system", "Android").putOpt("system_version", VERSION.RELEASE).putOpt("wifi", avVar2.a(1));
        } catch (JSONException unused) {
        }
        this.b = new JSONArray();
        this.a = j2;
        this.d = a(th);
        if (th.getMessage() != null) {
            this.e = th.getMessage();
        }
        if (!this.k) {
            this.j = c(th);
        }
        for (String put : b(th)) {
            this.f.put(put);
        }
    }

    private aq() {
        this.b = new JSONArray();
        this.c = new JSONArray();
        this.e = "";
        this.f = new JSONArray();
        this.h = cp.a.a();
        this.j = -1;
        this.k = false;
        this.l = bh.a.a();
        this.m = 1.0f;
    }

    public final void a(ay<at> ayVar) {
        this.b = ayVar.a();
    }

    public final void a(Collection<cj> collection) {
        this.c = new JSONArray();
        for (cj b2 : collection) {
            this.c.put(b2.g());
        }
    }

    private String a(Throwable th) {
        String name;
        if (this.k) {
            return ((bq) th).a;
        }
        while (true) {
            name = th.getClass().getName();
            Throwable cause = th.getCause();
            if (cause == null || cause == th) {
                return name;
            }
            th = cause;
        }
        return name;
    }

    private static String[] b(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        while (true) {
            com.google.a.a.a.a.a.a.a(th, printWriter);
            Throwable cause = th.getCause();
            if (cause != null && cause != th) {
                th = cause;
            }
        }
        return stringWriter.toString().split("\n");
    }

    private static int c(Throwable th) {
        boolean z;
        StackTraceElement[] stackTrace = th.getStackTrace();
        int i2 = 0;
        while (i2 < stackTrace.length) {
            try {
                Class cls = Class.forName(stackTrace[i2].getClassName());
                ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
                while (true) {
                    if (systemClassLoader == null) {
                        z = false;
                        break;
                    } else if (cls.getClassLoader() == systemClassLoader) {
                        z = true;
                        break;
                    } else {
                        systemClassLoader = systemClassLoader.getParent();
                    }
                }
                if (!z) {
                    return i2 + 1;
                }
                i2++;
            } catch (ClassNotFoundException unused) {
            }
        }
        return -1;
    }

    public final String f() {
        return this.l;
    }

    public final /* synthetic */ Object g() {
        HashMap hashMap = new HashMap();
        hashMap.put("app_state", this.i);
        hashMap.put(ResponseConstants.PLATFORM, AppBuild.ANDROID_PLATFORM);
        hashMap.put("ts", this.h);
        hashMap.put(ResponseConstants.RATE, Float.valueOf(this.m));
        hashMap.put("exception_name", this.d);
        hashMap.put("exception_reason", this.e);
        if (!this.k) {
            hashMap.put("suspect_line_index", Integer.valueOf(this.j));
        }
        hashMap.put("unsymbolized_stacktrace", this.f);
        hashMap.put("current_thread_id", Long.valueOf(this.a));
        if (this.g != null) {
            hashMap.put("threads", this.g);
        }
        hashMap.put("systemBreadcrumbs", this.b);
        if (this.c != null && this.c.length() > 0) {
            hashMap.put(ResponseConstants.TRANSACTIONS, this.c);
        }
        return new JSONObject(hashMap);
    }
}
