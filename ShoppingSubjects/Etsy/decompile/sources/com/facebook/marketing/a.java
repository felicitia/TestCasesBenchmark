package com.facebook.marketing;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.b;
import com.facebook.f;
import com.facebook.internal.j;
import com.facebook.internal.k;
import com.facebook.internal.z;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: CodelessActivityLifecycleTracker */
public class a {
    private static final String a = a.class.getCanonicalName();
    /* access modifiers changed from: private */
    public static final ViewIndexingTrigger b = new ViewIndexingTrigger();
    /* access modifiers changed from: private */
    public static SensorManager c;
    /* access modifiers changed from: private */
    public static c d;
    /* access modifiers changed from: private */
    public static String e;
    /* access modifiers changed from: private */
    public static Boolean f = Boolean.valueOf(false);
    /* access modifiers changed from: private */
    public static volatile Boolean g = Boolean.valueOf(false);

    public static void a(Application application, String str) {
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            public void onActivityDestroyed(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityStarted(Activity activity) {
            }

            public void onActivityStopped(Activity activity) {
            }

            public void onActivityResumed(Activity activity) {
                final Context applicationContext = activity.getApplicationContext();
                final String j = f.j();
                final j a = k.a(j);
                a.c = (SensorManager) applicationContext.getSystemService("sensor");
                Sensor defaultSensor = a.c.getDefaultSensor(1);
                a.d = new c(activity);
                a.b.setOnShakeListener(new com.facebook.marketing.ViewIndexingTrigger.a() {
                    public void a(int i) {
                        if (i >= 3) {
                            a.b.resetCount();
                            com.facebook.marketing.internal.a aVar = new com.facebook.marketing.internal.a(applicationContext, j);
                            aVar.b();
                            if (a != null && a.k()) {
                                a.a(j, aVar);
                            }
                        }
                    }
                });
                a.c.registerListener(a.b, defaultSensor, 2);
                if (a != null && a.k()) {
                    a.d.a();
                }
            }

            public void onActivityPaused(Activity activity) {
                if (a.d != null) {
                    a.d.b();
                }
                if (a.c != null) {
                    a.c.unregisterListener(a.b);
                }
            }
        });
    }

    public static void a(final String str, final com.facebook.marketing.internal.a aVar) {
        if (!g.booleanValue()) {
            g = Boolean.valueOf(true);
            f.d().execute(new Runnable() {
                public void run() {
                    boolean z = true;
                    GraphRequest a2 = GraphRequest.a((AccessToken) null, String.format(Locale.US, "%s/app_indexing_session", new Object[]{str}), (JSONObject) null, (b) null);
                    Bundle e = a2.e();
                    if (e == null) {
                        e = new Bundle();
                    }
                    com.facebook.internal.b a3 = com.facebook.internal.b.a(f.f());
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(Build.MODEL != null ? Build.MODEL : "");
                    if (a3 == null || a3.b() == null) {
                        jSONArray.put("");
                    } else {
                        jSONArray.put(a3.b());
                    }
                    jSONArray.put("0");
                    jSONArray.put(com.facebook.marketing.internal.b.b() ? "1" : "0");
                    Locale a4 = z.a();
                    StringBuilder sb = new StringBuilder();
                    sb.append(a4.getLanguage());
                    sb.append("_");
                    sb.append(a4.getCountry());
                    jSONArray.put(sb.toString());
                    String jSONArray2 = jSONArray.toString();
                    e.putString("device_session_id", a.a());
                    e.putString("extinfo", jSONArray2);
                    a2.a(e);
                    if (a2 != null) {
                        JSONObject b2 = a2.i().b();
                        if (b2 == null || !b2.optBoolean("is_app_indexing_enabled", false)) {
                            z = false;
                        }
                        a.f = Boolean.valueOf(z);
                        if (!a.f.booleanValue()) {
                            a.e = null;
                        } else {
                            aVar.c();
                            a.d.a();
                        }
                    }
                    a.g = Boolean.valueOf(false);
                }
            });
        }
    }

    public static String a() {
        if (e == null) {
            e = UUID.randomUUID().toString();
        }
        return e;
    }

    public static boolean b() {
        return f.booleanValue();
    }

    public static void a(Boolean bool) {
        f = bool;
    }
}
