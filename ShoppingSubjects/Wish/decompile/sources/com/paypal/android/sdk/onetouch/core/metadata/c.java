package com.paypal.android.sdk.onetouch.core.metadata;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    private static final String a = "c";
    private Context g;
    private String h;
    private JSONObject i;
    private boolean j;

    public c(Context context, String str) {
        this(context, str, 0);
    }

    private c(Context context, String str, byte b) {
        ai.a(a, "entering Configuration url loading");
        this.g = context;
        this.h = str;
        String p = p();
        if ("".equals(p)) {
            StringBuilder sb = new StringBuilder("No valid config found for ");
            sb.append(str);
            throw new IOException(sb.toString());
        }
        ai.a(a, "entering saveConfigDataToDisk");
        try {
            File file = new File(this.g.getFilesDir(), "CONFIG_DATA");
            File file2 = new File(this.g.getFilesDir(), "CONFIG_TIME");
            n.a(file, p);
            n.a(file2, String.valueOf(System.currentTimeMillis()));
            ai.a(a, "leaving saveConfigDataToDisk successfully");
        } catch (IOException e) {
            new StringBuilder("Failed to write config data: ").append(e.toString());
        }
        this.i = new JSONObject(p);
        ai.a(a, "leaving Configuration url loading");
    }

    public c(Context context, boolean z) {
        this.g = context;
        this.h = null;
        this.j = z;
        StringBuilder sb = new StringBuilder("confIsUpdatable=");
        sb.append(Boolean.toString(this.j));
        ai.a(3, "PRD", sb.toString());
        this.i = j();
        ai.a(a, "Configuation initialize, dumping config");
        ai.a(a, this.i);
    }

    private JSONObject a(String str) {
        try {
            ai.a(a, "entering getIncrementalConfig");
            JSONObject jSONObject = new JSONObject(ai.b(this.g, str));
            ai.a(a, "leaving getIncrementalConfig");
            return jSONObject;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Error while loading prdc Config ");
            sb.append(str);
            ai.a(6, "PRD", sb.toString(), (Throwable) e);
            return null;
        }
    }

    private static JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            ai.a(a, "entering mergeConfig");
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                String str2 = a;
                StringBuilder sb = new StringBuilder("overridding ");
                sb.append(str);
                ai.a(str2, sb.toString());
                jSONObject.put(str, jSONObject2.get(str));
            }
            ai.a(a, "leaving mergeConfig");
            return jSONObject;
        } catch (Exception e) {
            ai.a(6, "PRD", "Error encountered while applying prdc Config", (Throwable) e);
            return null;
        }
    }

    private JSONObject j() {
        try {
            JSONObject m = m();
            if (m != null) {
                if (ai.b(m.optString("conf_version", ""), "3.0")) {
                    if (this.j) {
                        if (System.currentTimeMillis() > Long.parseLong(s()) + (m.optLong("conf_refresh_time_interval", 0) * 1000)) {
                            o();
                        }
                    }
                    ai.a(a, "Using cached config");
                    return m;
                }
                r();
            }
            JSONObject l = l();
            if (l == null) {
                Log.e(a, "default Configuration loading failed,Using hardcoded config");
                return n();
            }
            String a2 = ai.a(this.g, "prdc", (String) null);
            if (a2 == null) {
                if (this.j) {
                    o();
                }
                ai.a(3, "PRD", "prdc field not configured, using default config");
                return l;
            }
            StringBuilder sb = new StringBuilder("prdc field is configured, loading path:");
            sb.append(a2);
            ai.a(3, "PRD", sb.toString());
            JSONObject a3 = a(a2);
            if (a3 == null) {
                ai.a(6, "PRD", "prdc Configuration loading failed, using default config");
                return l;
            }
            JSONObject a4 = a(l, a3);
            if (a4 == null) {
                ai.a(6, "PRD", "applying prdc Configuration failed, using default config");
                return l;
            }
            ai.a(3, "PRD", "prdc configuration loaded successfully");
            return a4;
        } catch (Exception e) {
            ai.a(6, "PRD", "Severe Error while loading config, using hard code version", (Throwable) e);
            return n();
        }
    }

    private static JSONObject l() {
        ai.a(a, "entering getDefaultConfigurations");
        try {
            String str = new String(Base64.decode("eyAiY29uZl92ZXJzaW9uIjogIjMuMCIsImFzeW5jX3VwZGF0ZV90aW1lX2ludGVydmFsIjogMzYwMCwgImZvcmNlZF9mdWxsX3VwZGF0ZV90aW1lX2ludGVydmFsIjogMTgwMCwgImNvbmZfcmVmcmVzaF90aW1lX2ludGVydmFsIjogODY0MDAsICJhbmRyb2lkX2FwcHNfdG9fY2hlY2siOiBbICJjb20uZWJheS5jbGFzc2lmaWVkcy9jb20uZWJheS5hcHAuTWFpblRhYkFjdGl2aXR5IiwgImNvbS5lYmF5Lm1vYmlsZS9jb20uZWJheS5tb2JpbGUuYWN0aXZpdGllcy5lQmF5IiwgImNvbS5lYmF5LnJlZGxhc2VyL2NvbS5lYmF5LnJlZGxhc2VyLlNjYW5uZWRJdGVtc0FjdGl2aXR5IiwgImNvbS5taWxvLmFuZHJvaWQvY29tLm1pbG8uYW5kcm9pZC5hY3Rpdml0eS5Ib21lQWN0aXZpdHkiLCAiY29tLnBheXBhbC5hbmRyb2lkLnAycG1vYmlsZS9jb20ucGF5cGFsLmFuZHJvaWQucDJwbW9iaWxlLmFjdGl2aXR5LlNlbmRNb25leUFjdGl2aXR5IiwgImNvbS5yZW50L2NvbS5yZW50LmFjdGl2aXRpZXMuc2Vzc2lvbi5BY3Rpdml0eUhvbWUiLCAiY29tLnN0dWJodWIvY29tLnN0dWJodWIuQWJvdXQiLCAiY29tLnVsb2NhdGUvY29tLnVsb2NhdGUuYWN0aXZpdGllcy5TZXR0aW5ncyIsICJjb20ubm9zaHVmb3UuYW5kcm9pZC5zdS9jb20ubm9zaHVmb3UuYW5kcm9pZC5zdS5TdSIsICJzdGVyaWNzb24uYnVzeWJveC9zdGVyaWNzb24uYnVzeWJveC5BY3Rpdml0eS5NYWluQWN0aXZpdHkiLCAib3JnLnByb3h5ZHJvaWQvb3JnLnByb3h5ZHJvaWQuUHJveHlEcm9pZCIsICJjb20uYWVkLmRyb2lkdnBuL2NvbS5hZWQuZHJvaWR2cG4uTWFpbkdVSSIsICJuZXQub3BlbnZwbi5vcGVudnBuL25ldC5vcGVudnBuLm9wZW52cG4uT3BlblZQTkNsaWVudCIsICJjb20ucGhvbmVhcHBzOTkuYWFiaXByb3h5L2NvbS5waG9uZWFwcHM5OS5hYWJpcHJveHkuT3Jib3QiLCAiY29tLmV2YW5oZS5wcm94eW1hbmFnZXIucHJvL2NvbS5ldmFuaGUucHJveHltYW5hZ2VyLk1haW5BY3Rpdml0eSIsICJjb20uZXZhbmhlLnByb3h5bWFuYWdlci9jb20uZXZhbmhlLnByb3h5bWFuYWdlci5NYWluQWN0aXZpdHkiLCAiY29tLmFuZHJvbW8uZGV2MzA5MzYuYXBwNzYxOTgvY29tLmFuZHJvbW8uZGV2MzA5MzYuYXBwNzYxOTguQW5kcm9tb0Rhc2hib2FyZEFjdGl2aXR5IiwgImNvbS5tZ3JhbmphLmF1dG9wcm94eV9saXRlL2NvbS5tZ3JhbmphLmF1dG9wcm94eV9saXRlLlByb3h5TGlzdEFjdGl2aXR5IiwgImNvbS52cG5vbmVjbGljay5hbmRyb2lkL2NvbS52cG5vbmVjbGljay5hbmRyb2lkLk1haW5BY3Rpdml0eSIsICJuZXQuaGlkZW1hbi9uZXQuaGlkZW1hbi5TdGFydGVyQWN0aXZpdHkiLCAiY29tLmRvZW50ZXIuYW5kcm9pZC52cG4uZml2ZXZwbi9jb20uZG9lbnRlci5hbmRyb2lkLnZwbi5maXZldnBuLkZpdmVWcG4iLCAiY29tLnRpZ2VydnBucy5hbmRyb2lkL2NvbS50aWdlcnZwbnMuYW5kcm9pZC5NYWluQWN0aXZpdHkiLCAiY29tLnBhbmRhcG93LnZwbi9jb20ucGFuZGFwb3cudnBuLlBhbmRhUG93IiwgImNvbS5leHByZXNzdnBuLnZwbi9jb20uZXhwcmVzc3Zwbi52cG4uTWFpbkFjdGl2aXR5IiwgImNvbS5sb25kb250cnVzdG1lZGlhLnZwbi9jb20ubG9uZG9udHJ1c3RtZWRpYS52cG4uVnBuU2VydmljZUFjdGl2aXR5IiwgImZyLm1lbGVjb20uVlBOUFBUUC52MTAxL2ZyLm1lbGVjb20uVlBOUFBUUC52MTAxLlNwbGFzaFNjcmVlbiIsICJjb20uY2hlY2twb2ludC5WUE4vY29tLmNoZWNrcG9pbnQuVlBOLk1haW5IYW5kbGVyIiwgImNvbS50dW5uZWxiZWFyLmFuZHJvaWQvY29tLnR1bm5lbGJlYXIuYW5kcm9pZC5UYmVhck1haW5BY3Rpdml0eSIsICJkZS5ibGlua3Qub3BlbnZwbi9kZS5ibGlua3Qub3BlbnZwbi5NYWluQWN0aXZpdHkiLCAib3JnLmFqZWplLmZha2Vsb2NhdGlvbi9vcmcuYWplamUuZmFrZWxvY2F0aW9uLkZha2UiLCAiY29tLmxleGEuZmFrZWdwcy9jb20ubGV4YS5mYWtlZ3BzLlBpY2tQb2ludCIsICJjb20uZm9yZ290dGVucHJvamVjdHMubW9ja2xvY2F0aW9ucy9jb20uZm9yZ290dGVucHJvamVjdHMubW9ja2xvY2F0aW9ucy5NYWluIiwgImtyLndvb3QwcGlhLmdwcy9rci53b290MHBpYS5ncHMuQ2F0Y2hNZUlmVUNhbiIsICJjb20ubXkuZmFrZS5sb2NhdGlvbi9jb20ubXkuZmFrZS5sb2NhdGlvbi5jb20ubXkuZmFrZS5sb2NhdGlvbiIsICJqcC5uZXRhcnQuYXJzdGFsa2luZy9qcC5uZXRhcnQuYXJzdGFsa2luZy5NeVByZWZlcmVuY2VBY3Rpdml0eSIsICJsb2NhdGlvblBsYXkuR1BTQ2hlYXRGcmVlL2xvY2F0aW9uUGxheS5HUFNDaGVhdEZyZWUuQWN0aXZpdHlTbWFydExvY2F0aW9uIiwgIm9yZy5nb29kZXYubGF0aXR1ZGUvb3JnLmdvb2Rldi5sYXRpdHVkZS5MYXRpdHVkZUFjdGl2aXR5IiwgImNvbS5zY2hlZmZzYmxlbmQuZGV2aWNlc3Bvb2YvY29tLnNjaGVmZnNibGVuZC5kZXZpY2VzcG9vZi5EZXZpY2VTcG9vZkFjdGl2aXR5IiwgImNvbS5wcm94eUJyb3dzZXIvY29tLnByb3h5QnJvd3Nlci5OZXdQcm94eUJyb3dzZXJBY3Rpdml0eSIsICJjb20uaWNlY29sZGFwcHMucHJveHlzZXJ2ZXJwcm8vY29tLmljZWNvbGRhcHBzLnByb3h5c2VydmVycHJvLnZpZXdTdGFydCIsICJob3RzcG90c2hpZWxkLmFuZHJvaWQudnBuL2NvbS5hbmNob3JmcmVlLnVpLkhvdFNwb3RTaGllbGQiLCAiY29tLmRvZW50ZXIub25ldnBuL2NvbS5kb2VudGVyLm9uZXZwbi5WcG5TZXR0aW5ncyIsICJjb20ueWVzdnBuLmVuL2NvbS55ZXN2cG4uTWFpblRhYiIsICJjb20ub2ZmaWNld3l6ZS5wbHV0b3Zwbi9jb20ub2ZmaWNld3l6ZS5wbHV0b3Zwbi5Ib21lQWN0aXZpdHkiLCAib3JnLmFqZWplLmxvY2F0aW9uc3Bvb2ZlcnByby9vcmcuYWplamUubG9jYXRpb25zcG9vZmVycHJvLkZha2UiLCAibG9jYXRpb25QbGF5LkdQU0NoZWF0L2xvY2F0aW9uUGxheS5HUFNDaGVhdC5BY3Rpdml0eVNtYXJ0TG9jYXRpb24iIF0sICJsb2NhdGlvbl9taW5fYWNjdXJhY3kiOiA1MDAsICJsb2NhdGlvbl9tYXhfY2FjaGVfYWdlIjogMTgwMCwgInNlbmRfb25fYXBwX3N0YXJ0IjogMSwgImVuZHBvaW50X3VybCI6ICJodHRwczovL3N2Y3MucGF5cGFsLmNvbS9BY2Nlc3NDb250cm9sL0xvZ1Jpc2tNZXRhZGF0YSIsICJpbnRlcm5hbF9jYWNoZV9tYXhfYWdlIjogMzAsICJjb21wX3RpbWVvdXQiOiA2MDAgfQ==", 0), "UTF-8");
            ai.a(a, "leaving getDefaultConfigurations, Default Conf load succeed");
            return new JSONObject(str);
        } catch (Exception e) {
            ai.a(6, "PRD", "Read default config file exception.", (Throwable) e);
            ai.a(a, "leaving getDefaultConfigurations,returning null");
            return null;
        }
    }

    private JSONObject m() {
        ai.a(a, "entering getCachedConfiguration");
        try {
            String q = q();
            if (!"".equals(q)) {
                ai.a(a, "leaving getCachedConfiguration,cached config load succeed");
                return new JSONObject(q);
            }
        } catch (Exception e) {
            ai.a(a, "JSON Exception in creating config file", (Throwable) e);
        }
        ai.a(a, "leaving getCachedConfiguration,cached config load failed");
        return null;
    }

    private static JSONObject n() {
        ai.a(a, "entering getHardcodedConfig");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("conf_version", "3.0");
            jSONObject.put("async_update_time_interval", 3600);
            jSONObject.put("forced_full_update_time_interval", 1800);
            jSONObject.put("conf_refresh_time_interval", 86400);
            jSONObject.put("location_min_accuracy", 500);
            jSONObject.put("location_max_cache_age", 1800);
            jSONObject.put("endpoint_url", "https://svcs.paypal.com/AccessControl/LogRiskMetadata");
        } catch (JSONException unused) {
        }
        ai.a(a, "leaving getHardcodedConfig");
        return jSONObject;
    }

    private static void o() {
        ai.a(a, "Loading web config");
        h.h().i();
    }

    private String p() {
        InputStream inputStream;
        ai.a(a, "entering getRemoteConfig");
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            inputStream = FirebasePerfUrlConnection.openStream(new URL(this.h));
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            sb.append(readLine);
                        } else {
                            af.a(inputStream);
                            af.a(bufferedReader2);
                            ai.a(a, "leaving getRemoteConfig successfully");
                            return sb.toString();
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        af.a(inputStream);
                        af.a(bufferedReader);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                af.a(inputStream);
                af.a(bufferedReader);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
            af.a(inputStream);
            af.a(bufferedReader);
            throw th;
        }
    }

    private String q() {
        try {
            return n.a(new File(this.g.getFilesDir(), "CONFIG_DATA"));
        } catch (IOException e) {
            ai.a(a, "Load cached config failed", (Throwable) e);
            return "";
        }
    }

    private boolean r() {
        try {
            ai.a(a, "entering deleteCachedConfigDataFromDisk");
            File file = new File(this.g.getFilesDir(), "CONFIG_DATA");
            File file2 = new File(this.g.getFilesDir(), "CONFIG_TIME");
            if (file.exists()) {
                ai.a(a, "cached Config Data found, deleting");
                file.delete();
            }
            if (file2.exists()) {
                ai.a(a, "cached Config Time found, deleting");
                file.delete();
            }
            ai.a(a, "leaving deleteCachedConfigDataFromDisk");
            return true;
        } catch (Exception e) {
            ai.a(a, "error encountered while deleteCachedConfigData", (Throwable) e);
            return false;
        }
    }

    private String s() {
        try {
            return n.a(new File(this.g.getFilesDir(), "CONFIG_TIME"));
        } catch (IOException unused) {
            return "";
        }
    }

    public final String a() {
        return this.h;
    }

    public final String b() {
        return this.i.optString("conf_version", "");
    }

    public final long c() {
        return this.i.optLong("async_update_time_interval", 0);
    }

    public final long d() {
        return this.i.optLong("forced_full_update_time_interval", 0);
    }

    public final long e() {
        return this.i.optLong("comp_timeout", 0);
    }

    public final List<String> f() {
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = this.i.optJSONArray("android_apps_to_check");
        int i2 = 0;
        while (optJSONArray != null && i2 < optJSONArray.length()) {
            arrayList.add(optJSONArray.getString(i2));
            i2++;
        }
        return arrayList;
    }

    public final String g() {
        return this.i.optString("endpoint_url", null);
    }

    public final boolean h() {
        return this.i.optBoolean("endpoint_is_stage", false);
    }

    public final ag i() {
        try {
            String optString = this.i.optString("CDS", "");
            if (optString != null) {
                if (!"".equals(optString)) {
                    ai.a(3, "PRD", "CDS field was found");
                    return new ag(optString.trim());
                }
            }
            ai.a(3, "PRD", "No CDS is configured, enabling all variables");
            return ag.a;
        } catch (Exception e) {
            ai.a(6, "PRD", "Failed to decode CDS", (Throwable) e);
            return ag.a;
        }
    }
}
