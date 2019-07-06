package com.crittercism.internal;

import android.os.Build;
import android.os.Build.VERSION;
import android.util.Base64;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.ResponseConstants;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class bf implements bi {
    JSONObject a;
    JSONArray b;
    String c;
    String d;
    String e;
    public float f;

    public static class a extends ce {
        public a(av avVar) {
            super(avVar);
        }

        public final bz a(as asVar, List<? extends bi> list) {
            URL url = new URL(asVar.b, "/android_v2/handle_ndk_crashes");
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("app_id", this.a.e);
                jSONObject.put("hashed_device_id", this.a.h());
                jSONObject.put("library_version", "5.8.10");
                JSONArray jSONArray = new JSONArray();
                for (bi g : list) {
                    jSONArray.put(g.g());
                }
                jSONObject.put("crashes", jSONArray);
                return bz.a(url, jSONObject, this.b);
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    public static class b implements com.crittercism.internal.az.b<bf> {
        private b() {
        }

        public /* synthetic */ b(byte b) {
            this();
        }

        public final /* synthetic */ bi a(File file) {
            return b(file);
        }

        public final /* synthetic */ void a(bi biVar, OutputStream outputStream) {
            bf bfVar = (bf) biVar;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("appState", bfVar.a);
                jSONObject.put("breadcrumbs", bfVar.b);
                jSONObject.put("crashDumpFileName", bfVar.c);
                jSONObject.put("base64EncodedCrash", bfVar.d);
                jSONObject.put("fileName", bfVar.e);
                jSONObject.put(ResponseConstants.RATE, (double) bfVar.f);
                outputStream.write(jSONObject.toString().getBytes());
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }

        private static bf b(File file) {
            try {
                JSONObject jSONObject = new JSONObject(cn.b(file));
                bf bfVar = new bf(0);
                bfVar.a = jSONObject.getJSONObject("appState");
                bfVar.b = jSONObject.getJSONArray("breadcrumbs");
                bfVar.c = jSONObject.getString("crashDumpFileName");
                bfVar.d = jSONObject.getString("base64EncodedCrash");
                bfVar.e = jSONObject.getString("fileName");
                bfVar.f = (float) jSONObject.getDouble(ResponseConstants.RATE);
                return bfVar;
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    /* synthetic */ bf(byte b2) {
        this();
    }

    private bf(File file, ay<at> ayVar, av avVar) {
        this.f = 1.0f;
        this.e = bh.a.a();
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        byte[] bArr = new byte[((int) randomAccessFile.length())];
        randomAccessFile.read(bArr);
        this.d = new String(Base64.encode(bArr, 0));
        this.c = file.getName();
        this.a = new JSONObject();
        try {
            this.a.putOpt("app_version", avVar.a.a).putOpt("app_version_code", avVar.a()).putOpt("arch", System.getProperty("os.arch")).putOpt("carrier", avVar.b()).putOpt("mobile_country_code", avVar.c()).putOpt("mobile_network_code", avVar.d()).putOpt("disk_space_total", avVar.k()).putOpt("dpi", avVar.e()).putOpt("xdpi", Float.valueOf(avVar.f())).putOpt("ydpi", Float.valueOf(avVar.g())).putOpt("locale", avVar.i()).putOpt("model", Build.MODEL).putOpt("memory_total", av.l()).putOpt(ResponseConstants.NAME, new String()).putOpt(ResponseConstants.PLATFORM, AppBuild.ANDROID_PLATFORM).putOpt("development_platform", avVar.h).putOpt("system", "Android").putOpt("system_version", VERSION.RELEASE);
        } catch (JSONException unused) {
        }
        this.b = ayVar.a();
    }

    private bf() {
        this.f = 1.0f;
    }

    public static bf a(File file, ay<at> ayVar, av avVar) {
        bf bfVar = null;
        if (!file.exists() || !file.isDirectory()) {
            return null;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return null;
        }
        if (listFiles.length == 1) {
            File file2 = listFiles[0];
            if (file2.isFile()) {
                try {
                    bfVar = new bf(file2, ayVar, avVar);
                } catch (ThreadDeath e2) {
                    throw e2;
                } catch (Throwable unused) {
                }
            }
        }
        for (File a2 : listFiles) {
            cn.a(a2);
        }
        return bfVar;
    }

    public final String f() {
        return this.e;
    }

    public final /* synthetic */ Object g() {
        HashMap hashMap = new HashMap();
        hashMap.put("app_state", this.a);
        hashMap.put(ResponseConstants.RATE, Float.valueOf(this.f));
        hashMap.put("breadcrumbs", new JSONObject());
        hashMap.put("endpoints", new JSONArray());
        hashMap.put("systemBreadcrumbs", this.b);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("dmp_name", this.c);
        hashMap2.put("dmp_file", this.d);
        hashMap.put("ndk_dmp_info", new JSONObject(hashMap2));
        return new JSONObject(hashMap);
    }
}
