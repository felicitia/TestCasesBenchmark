package com.facebook.marketing;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.b;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.f;
import com.facebook.internal.t;
import com.facebook.internal.z;
import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ViewIndexer */
public class c {
    /* access modifiers changed from: private */
    public static final String a = c.class.getCanonicalName();
    /* access modifiers changed from: private */
    public final Handler b = new Handler(Looper.getMainLooper());
    private WeakReference<Activity> c;
    /* access modifiers changed from: private */
    public Timer d;
    /* access modifiers changed from: private */
    public String e = null;
    /* access modifiers changed from: private */
    public final com.facebook.marketing.internal.a f = new com.facebook.marketing.internal.a(f.f(), f.j());

    /* compiled from: ViewIndexer */
    private static class a implements Callable<String> {
        private WeakReference<View> a;

        public a(View view) {
            this.a = new WeakReference<>(view);
        }

        /* renamed from: a */
        public String call() throws Exception {
            View view = (View) this.a.get();
            if (view == null || view.getWidth() == 0 || view.getHeight() == 0) {
                return "";
            }
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.RGB_565);
            view.draw(new Canvas(createBitmap));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            createBitmap.compress(CompressFormat.JPEG, 10, byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        }
    }

    public c(Activity activity) {
        this.c = new WeakReference<>(activity);
    }

    public void a() {
        final Activity activity = (Activity) this.c.get();
        if (activity != null) {
            final String simpleName = activity.getClass().getSimpleName();
            f.j();
            final AnonymousClass1 r2 = new TimerTask() {
                /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
                /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0063 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r6 = this;
                        android.app.Activity r0 = r0     // Catch:{ Exception -> 0x0078 }
                        android.view.Window r0 = r0.getWindow()     // Catch:{ Exception -> 0x0078 }
                        android.view.View r0 = r0.getDecorView()     // Catch:{ Exception -> 0x0078 }
                        android.view.View r0 = r0.getRootView()     // Catch:{ Exception -> 0x0078 }
                        boolean r1 = com.facebook.marketing.a.b()     // Catch:{ Exception -> 0x0078 }
                        if (r1 != 0) goto L_0x0015
                        return
                    L_0x0015:
                        java.util.concurrent.FutureTask r1 = new java.util.concurrent.FutureTask     // Catch:{ Exception -> 0x0078 }
                        com.facebook.marketing.c$a r2 = new com.facebook.marketing.c$a     // Catch:{ Exception -> 0x0078 }
                        r2.<init>(r0)     // Catch:{ Exception -> 0x0078 }
                        r1.<init>(r2)     // Catch:{ Exception -> 0x0078 }
                        com.facebook.marketing.c r2 = com.facebook.marketing.c.this     // Catch:{ Exception -> 0x0078 }
                        android.os.Handler r2 = r2.b     // Catch:{ Exception -> 0x0078 }
                        r2.post(r1)     // Catch:{ Exception -> 0x0078 }
                        java.lang.String r2 = ""
                        r3 = 1
                        java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x0035 }
                        java.lang.Object r1 = r1.get(r3, r5)     // Catch:{ Exception -> 0x0035 }
                        java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x0035 }
                        goto L_0x0040
                    L_0x0035:
                        r1 = move-exception
                        java.lang.String r3 = com.facebook.marketing.c.a     // Catch:{ Exception -> 0x0078 }
                        java.lang.String r4 = "Failed to take screenshot."
                        android.util.Log.e(r3, r4, r1)     // Catch:{ Exception -> 0x0078 }
                        r1 = r2
                    L_0x0040:
                        org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x0078 }
                        r2.<init>()     // Catch:{ Exception -> 0x0078 }
                        java.lang.String r3 = "screenname"
                        java.lang.String r4 = r1     // Catch:{ JSONException -> 0x0063 }
                        r2.put(r3, r4)     // Catch:{ JSONException -> 0x0063 }
                        java.lang.String r3 = "screenshot"
                        r2.put(r3, r1)     // Catch:{ JSONException -> 0x0063 }
                        org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0063 }
                        r1.<init>()     // Catch:{ JSONException -> 0x0063 }
                        org.json.JSONObject r0 = com.facebook.appevents.codeless.internal.c.b(r0)     // Catch:{ JSONException -> 0x0063 }
                        r1.put(r0)     // Catch:{ JSONException -> 0x0063 }
                        java.lang.String r0 = "view"
                        r2.put(r0, r1)     // Catch:{ JSONException -> 0x0063 }
                        goto L_0x006c
                    L_0x0063:
                        java.lang.String r0 = com.facebook.marketing.c.a     // Catch:{ Exception -> 0x0078 }
                        java.lang.String r1 = "Failed to create JSONObject"
                        android.util.Log.e(r0, r1)     // Catch:{ Exception -> 0x0078 }
                    L_0x006c:
                        java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x0078 }
                        com.facebook.marketing.c r1 = com.facebook.marketing.c.this     // Catch:{ Exception -> 0x0078 }
                        java.lang.String r2 = r1     // Catch:{ Exception -> 0x0078 }
                        r1.a(r0, r2)     // Catch:{ Exception -> 0x0078 }
                        goto L_0x0082
                    L_0x0078:
                        r0 = move-exception
                        java.lang.String r1 = com.facebook.marketing.c.a
                        java.lang.String r2 = "UI Component tree indexing failure!"
                        android.util.Log.e(r1, r2, r0)
                    L_0x0082:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.marketing.c.AnonymousClass1.run():void");
                }
            };
            f.d().execute(new Runnable() {
                public void run() {
                    try {
                        if (c.this.d != null) {
                            c.this.d.cancel();
                        }
                        c.this.e = null;
                        c.this.d = new Timer();
                        c.this.d.scheduleAtFixedRate(r2, 0, 1000);
                    } catch (Exception e) {
                        Log.e(c.a, "Error scheduling indexing job", e);
                    }
                }
            });
        }
    }

    public void b() {
        Activity activity = (Activity) this.c.get();
        if (!(activity == null || this.d == null)) {
            try {
                this.d.cancel();
                this.d = null;
                if (a.b()) {
                    this.f.c(activity.getClass().getCanonicalName());
                }
            } catch (Exception e2) {
                Log.e(a, "Error unscheduling indexing job", e2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(final String str, final String str2) {
        f.d().execute(new Runnable() {
            public void run() {
                String j = f.j();
                String b2 = z.b(str);
                AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
                if (b2 == null || !b2.equals(c.this.e)) {
                    c.this.f.a(str2);
                    GraphRequest a2 = c.b(str, currentAccessToken, j);
                    if (a2 != null) {
                        GraphResponse i = a2.i();
                        try {
                            JSONObject b3 = i.b();
                            if (b3 != null) {
                                if (b3.has("success") && b3.getString("success") == "true") {
                                    t.a(LoggingBehavior.APP_EVENTS, c.a, "Successfully send UI component tree to server");
                                    c.this.e = b2;
                                    c.this.f.b(str2);
                                }
                                if (b3.has("is_app_indexing_enabled")) {
                                    a.a(Boolean.valueOf(b3.getBoolean("is_app_indexing_enabled")));
                                }
                            } else {
                                String c2 = c.a;
                                StringBuilder sb = new StringBuilder();
                                sb.append("Error sending UI component tree to Facebook: ");
                                sb.append(i.a());
                                Log.e(c2, sb.toString());
                            }
                        } catch (JSONException e) {
                            Log.e(c.a, "Error decoding server response.", e);
                        }
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    @Nullable
    public static GraphRequest b(String str, AccessToken accessToken, String str2) {
        if (str == null) {
            return null;
        }
        GraphRequest a2 = GraphRequest.a(accessToken, String.format(Locale.US, "%s/app_indexing", new Object[]{str2}), (JSONObject) null, (b) null);
        Bundle e2 = a2.e();
        if (e2 == null) {
            e2 = new Bundle();
        }
        e2.putString("tree", str);
        e2.putString("app_version", com.facebook.marketing.internal.b.a());
        e2.putString(ResponseConstants.PLATFORM, AppBuild.ANDROID_PLATFORM);
        e2.putString("device_session_id", a.a());
        a2.a(e2);
        a2.a((b) new b() {
            public void a(GraphResponse graphResponse) {
                t.a(LoggingBehavior.APP_EVENTS, c.a, "App index sent to FB!");
            }
        });
        return a2;
    }
}
