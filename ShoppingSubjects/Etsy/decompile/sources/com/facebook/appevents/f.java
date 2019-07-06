package com.facebook.appevents;

import android.content.Context;
import android.os.Bundle;
import com.facebook.GraphRequest;
import com.facebook.appevents.internal.AppEventsLoggerUtility;
import com.facebook.appevents.internal.AppEventsLoggerUtility.GraphAPIActivityType;
import com.facebook.internal.b;
import com.facebook.internal.z;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SessionEventsState */
class f {
    private List<AppEvent> a = new ArrayList();
    private List<AppEvent> b = new ArrayList();
    private int c;
    private b d;
    private String e;
    private final int f = 1000;

    public f(b bVar, String str) {
        this.d = bVar;
        this.e = str;
    }

    public synchronized void a(AppEvent appEvent) {
        if (this.a.size() + this.b.size() >= 1000) {
            this.c++;
        } else {
            this.a.add(appEvent);
        }
    }

    public synchronized int a() {
        return this.a.size();
    }

    public synchronized void a(boolean z) {
        if (z) {
            try {
                this.a.addAll(this.b);
            } catch (Throwable th) {
                throw th;
            }
        }
        this.b.clear();
        this.c = 0;
    }

    public int a(GraphRequest graphRequest, Context context, boolean z, boolean z2) {
        synchronized (this) {
            int i = this.c;
            this.b.addAll(this.a);
            this.a.clear();
            JSONArray jSONArray = new JSONArray();
            for (AppEvent appEvent : this.b) {
                if (!appEvent.isChecksumValid()) {
                    z.b("Event with invalid checksum: %s", appEvent.toString());
                } else if (z || !appEvent.getIsImplicit()) {
                    jSONArray.put(appEvent.getJSONObject());
                }
            }
            if (jSONArray.length() == 0) {
                return 0;
            }
            a(graphRequest, context, i, jSONArray, z2);
            return jSONArray.length();
        }
    }

    public synchronized List<AppEvent> b() {
        List<AppEvent> list;
        list = this.a;
        this.a = new ArrayList();
        return list;
    }

    private void a(GraphRequest graphRequest, Context context, int i, JSONArray jSONArray, boolean z) {
        JSONObject jSONObject;
        try {
            jSONObject = AppEventsLoggerUtility.a(GraphAPIActivityType.CUSTOM_APP_EVENTS, this.d, this.e, z, context);
            if (this.c > 0) {
                jSONObject.put("num_skipped_events", i);
            }
        } catch (JSONException unused) {
            jSONObject = new JSONObject();
        }
        graphRequest.a(jSONObject);
        Bundle e2 = graphRequest.e();
        if (e2 == null) {
            e2 = new Bundle();
        }
        String jSONArray2 = jSONArray.toString();
        if (jSONArray2 != null) {
            e2.putByteArray("custom_events_file", a(jSONArray2));
            graphRequest.a((Object) jSONArray2);
        }
        graphRequest.a(e2);
    }

    private byte[] a(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            z.a("Encoding exception: ", (Exception) e2);
            return null;
        }
    }
}
