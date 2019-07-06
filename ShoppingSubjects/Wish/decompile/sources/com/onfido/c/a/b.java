package com.onfido.c.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.onfido.c.a.a.f;
import com.onfido.c.a.b.b.d;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;

public class b extends t {

    public static class a extends t {
        a() {
        }

        /* renamed from: a */
        public a b(String str, Object obj) {
            super.b(str, obj);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public void a(String str, boolean z) {
            if (z && !com.onfido.c.a.b.b.a((CharSequence) str)) {
                put("advertisingId", str);
            }
            put("adTrackingEnabled", Boolean.valueOf(z));
        }
    }

    b(Map<String, Object> map) {
        super(map);
    }

    static synchronized b a(Context context, s sVar, boolean z) {
        b bVar;
        synchronized (b.class) {
            bVar = new b(new d());
            bVar.a(context);
            bVar.a(sVar);
            bVar.a(context, z);
            bVar.d();
            StringBuilder sb = new StringBuilder();
            sb.append(Locale.getDefault().getLanguage());
            sb.append("-");
            sb.append(Locale.getDefault().getCountry());
            bVar.put("locale", sb.toString());
            bVar.b(context);
            bVar.e();
            bVar.c(context);
            a((Map<String, Object>) bVar, "userAgent", (CharSequence) System.getProperty("http.agent"));
            a((Map<String, Object>) bVar, "timezone", (CharSequence) TimeZone.getDefault().getID());
        }
        return bVar;
    }

    static void a(Map<String, Object> map, String str, CharSequence charSequence) {
        if (com.onfido.c.a.b.b.a(charSequence)) {
            charSequence = "undefined";
        }
        map.put(str, charSequence);
    }

    public b a() {
        return new b(Collections.unmodifiableMap(new LinkedHashMap(this)));
    }

    /* renamed from: a */
    public b b(String str, Object obj) {
        super.b(str, obj);
        return this;
    }

    /* access modifiers changed from: 0000 */
    public void a(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            Map a2 = com.onfido.c.a.b.b.a();
            a(a2, "name", packageInfo.applicationInfo.loadLabel(packageManager));
            a(a2, "version", (CharSequence) packageInfo.versionName);
            a(a2, "namespace", (CharSequence) packageInfo.packageName);
            a2.put("build", Integer.valueOf(packageInfo.versionCode));
            put("app", a2);
        } catch (NameNotFoundException unused) {
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Context context, CountDownLatch countDownLatch, f fVar) {
        if (com.onfido.c.a.b.b.a("com.google.android.gms.ads.identifier.AdvertisingIdClient")) {
            new h(this, countDownLatch, fVar).execute(new Context[]{context});
            return;
        }
        fVar.c("Not collecting advertising ID because com.google.android.gms.ads.identifier.AdvertisingIdClient was not found on the classpath.", new Object[0]);
        countDownLatch.countDown();
    }

    /* access modifiers changed from: 0000 */
    public void a(Context context, boolean z) {
        a aVar = new a();
        aVar.put("id", z ? com.onfido.c.a.b.b.a(context) : b().d());
        aVar.put("manufacturer", Build.MANUFACTURER);
        aVar.put("model", Build.MODEL);
        aVar.put("name", Build.DEVICE);
        put("device", aVar);
    }

    /* access modifiers changed from: 0000 */
    public void a(s sVar) {
        put("traits", sVar.b());
    }

    public s b() {
        return (s) a("traits", s.class);
    }

    /* access modifiers changed from: 0000 */
    public void b(Context context) {
        Map a2 = com.onfido.c.a.b.b.a();
        if (com.onfido.c.a.b.b.a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            ConnectivityManager connectivityManager = (ConnectivityManager) com.onfido.c.a.b.b.c(context, "connectivity");
            if (connectivityManager != null) {
                boolean z = true;
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
                a2.put("wifi", Boolean.valueOf(networkInfo != null && networkInfo.isConnected()));
                NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(7);
                a2.put("bluetooth", Boolean.valueOf(networkInfo2 != null && networkInfo2.isConnected()));
                NetworkInfo networkInfo3 = connectivityManager.getNetworkInfo(0);
                String str = "cellular";
                if (networkInfo3 == null || !networkInfo3.isConnected()) {
                    z = false;
                }
                a2.put(str, Boolean.valueOf(z));
            }
        }
        TelephonyManager telephonyManager = (TelephonyManager) com.onfido.c.a.b.b.c(context, "phone");
        if (telephonyManager != null) {
            a2.put("carrier", telephonyManager.getNetworkOperatorName());
        } else {
            a2.put("carrier", "unknown");
        }
        put("network", a2);
    }

    public a c() {
        return (a) a("device", a.class);
    }

    /* access modifiers changed from: 0000 */
    public void c(Context context) {
        Map a2 = com.onfido.c.a.b.b.a();
        Display defaultDisplay = ((WindowManager) com.onfido.c.a.b.b.c(context, "window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        a2.put("density", Float.valueOf(displayMetrics.density));
        a2.put("height", Integer.valueOf(displayMetrics.heightPixels));
        a2.put("width", Integer.valueOf(displayMetrics.widthPixels));
        put("screen", a2);
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        Map a2 = com.onfido.c.a.b.b.a();
        a2.put("name", "analytics-android");
        a2.put("version", "4.3.1");
        put("library", a2);
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        Map a2 = com.onfido.c.a.b.b.a();
        a2.put("name", "Android");
        a2.put("version", VERSION.RELEASE);
        put("os", a2);
    }
}
