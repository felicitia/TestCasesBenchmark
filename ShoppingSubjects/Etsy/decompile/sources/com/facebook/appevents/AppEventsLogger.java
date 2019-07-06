package com.facebook.appevents;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.internal.a;
import com.facebook.appevents.internal.c;
import com.facebook.f;
import com.facebook.internal.aa;
import com.facebook.internal.k;
import com.facebook.internal.t;
import com.facebook.internal.z;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;

public class AppEventsLogger {
    private static final String a = AppEventsLogger.class.getCanonicalName();
    private static ScheduledThreadPoolExecutor d;
    private static FlushBehavior e = FlushBehavior.AUTO;
    private static Object f = new Object();
    private static String g;
    private static boolean h;
    private static String i;
    private final String b;
    private final AccessTokenAppIdPair c;

    public enum FlushBehavior {
        AUTO,
        EXPLICIT_ONLY
    }

    public static void a(Application application) {
        a(application, (String) null);
    }

    public static void a(Application application, String str) {
        if (!f.a()) {
            throw new FacebookException("The Facebook sdk must be initialized before calling activateApp");
        }
        a.a();
        if (str == null) {
            str = f.j();
        }
        f.a((Context) application, str);
        a.a(application, str);
    }

    public static void a(Context context, String str) {
        if (f.m()) {
            d.execute(new Runnable(new AppEventsLogger(context, str, (AccessToken) null)) {
                final /* synthetic */ AppEventsLogger a;

                {
                    this.a = r1;
                }

                public void run() {
                    Bundle bundle = new Bundle();
                    try {
                        Class.forName("com.facebook.core.Core");
                        bundle.putInt("core_lib_included", 1);
                    } catch (ClassNotFoundException unused) {
                    }
                    try {
                        Class.forName("com.facebook.login.Login");
                        bundle.putInt("login_lib_included", 1);
                    } catch (ClassNotFoundException unused2) {
                    }
                    try {
                        Class.forName("com.facebook.share.Share");
                        bundle.putInt("share_lib_included", 1);
                    } catch (ClassNotFoundException unused3) {
                    }
                    try {
                        Class.forName("com.facebook.places.Places");
                        bundle.putInt("places_lib_included", 1);
                    } catch (ClassNotFoundException unused4) {
                    }
                    try {
                        Class.forName("com.facebook.messenger.Messenger");
                        bundle.putInt("messenger_lib_included", 1);
                    } catch (ClassNotFoundException unused5) {
                    }
                    try {
                        Class.forName("com.facebook.applinks.AppLinks");
                        bundle.putInt("applinks_lib_included", 1);
                    } catch (ClassNotFoundException unused6) {
                    }
                    try {
                        Class.forName("com.facebook.marketing.b");
                        bundle.putInt("marketing_lib_included", 1);
                    } catch (ClassNotFoundException unused7) {
                    }
                    try {
                        Class.forName("com.facebook.all.All");
                        bundle.putInt("all_lib_included", 1);
                    } catch (ClassNotFoundException unused8) {
                    }
                    try {
                        Class.forName("com.android.billingclient.api.BillingClient");
                        bundle.putInt("billing_client_lib_included", 1);
                    } catch (ClassNotFoundException unused9) {
                    }
                    try {
                        Class.forName("com.android.vending.billing.IInAppBillingService");
                        bundle.putInt("billing_service_lib_included", 1);
                    } catch (ClassNotFoundException unused10) {
                    }
                    this.a.a("fb_sdk_initialize", (Double) null, bundle);
                }
            });
        }
    }

    public static AppEventsLogger a(Context context) {
        return new AppEventsLogger(context, (String) null, (AccessToken) null);
    }

    public static AppEventsLogger b(Context context, String str) {
        return new AppEventsLogger(context, str, (AccessToken) null);
    }

    public static FlushBehavior a() {
        FlushBehavior flushBehavior;
        synchronized (f) {
            flushBehavior = e;
        }
        return flushBehavior;
    }

    public void a(String str, Bundle bundle) {
        a(str, null, bundle, false, a.a());
    }

    public void a(String str, double d2, Bundle bundle) {
        a(str, Double.valueOf(d2), bundle, false, a.a());
    }

    public void a(BigDecimal bigDecimal, Currency currency, Bundle bundle) {
        if (c.b()) {
            Log.w(a, "You are logging purchase events while auto-logging of in-app purchase is enabled in the SDK. Make sure you don't log duplicate events");
        }
        a(bigDecimal, currency, bundle, false);
    }

    public void b(BigDecimal bigDecimal, Currency currency, Bundle bundle) {
        a(bigDecimal, currency, bundle, true);
    }

    private void a(BigDecimal bigDecimal, Currency currency, Bundle bundle, boolean z) {
        if (bigDecimal == null) {
            a("purchaseAmount cannot be null");
        } else if (currency == null) {
            a("currency cannot be null");
        } else {
            if (bundle == null) {
                bundle = new Bundle();
            }
            Bundle bundle2 = bundle;
            bundle2.putString("fb_currency", currency.getCurrencyCode());
            a("fb_mobile_purchase", Double.valueOf(bigDecimal.doubleValue()), bundle2, z, a.a());
            f();
        }
    }

    public void b() {
        c.a(FlushReason.EXPLICIT);
    }

    public static void c() {
        c.a();
    }

    static String d() {
        String str;
        synchronized (f) {
            str = i;
        }
        return str;
    }

    public static String e() {
        return a.b();
    }

    public void a(String str, Double d2, Bundle bundle) {
        a(str, d2, bundle, true, a.a());
    }

    private AppEventsLogger(Context context, String str, AccessToken accessToken) {
        this(z.c(context), str, accessToken);
    }

    protected AppEventsLogger(String str, String str2, AccessToken accessToken) {
        aa.a();
        this.b = str;
        if (accessToken == null) {
            accessToken = AccessToken.getCurrentAccessToken();
        }
        if (!AccessToken.isCurrentAccessTokenActive() || (str2 != null && !str2.equals(accessToken.getApplicationId()))) {
            if (str2 == null) {
                str2 = z.a(f.f());
            }
            this.c = new AccessTokenAppIdPair(null, str2);
        } else {
            this.c = new AccessTokenAppIdPair(accessToken);
        }
        h();
    }

    private static void h() {
        synchronized (f) {
            if (d == null) {
                d = new ScheduledThreadPoolExecutor(1);
                d.scheduleAtFixedRate(new Runnable() {
                    public void run() {
                        HashSet<String> hashSet = new HashSet<>();
                        for (AccessTokenAppIdPair applicationId : c.b()) {
                            hashSet.add(applicationId.getApplicationId());
                        }
                        for (String a : hashSet) {
                            k.a(a, true);
                        }
                    }
                }, 0, 86400, TimeUnit.SECONDS);
            }
        }
    }

    private void a(String str, Double d2, Bundle bundle, boolean z, @Nullable UUID uuid) {
        try {
            AppEvent appEvent = new AppEvent(this.b, str, d2, bundle, z, uuid);
            a(f.f(), appEvent, this.c);
        } catch (JSONException e2) {
            t.a(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", e2.toString());
        } catch (FacebookException e3) {
            t.a(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event: %s", e3.toString());
        }
    }

    private static void a(Context context, AppEvent appEvent, AccessTokenAppIdPair accessTokenAppIdPair) {
        c.a(accessTokenAppIdPair, appEvent);
        if (!appEvent.getIsImplicit() && !h) {
            if (appEvent.getName() == "fb_mobile_activate_app") {
                h = true;
            } else {
                t.a(LoggingBehavior.APP_EVENTS, "AppEvents", "Warning: Please call AppEventsLogger.activateApp(...)from the long-lived activity's onResume() methodbefore logging other app events.");
            }
        }
    }

    static void f() {
        if (a() != FlushBehavior.EXPLICIT_ONLY) {
            c.a(FlushReason.EAGER_FLUSHING_EVENT);
        }
    }

    private static void a(String str) {
        t.a(LoggingBehavior.DEVELOPER_ERRORS, "AppEvents", str);
    }

    static Executor g() {
        if (d == null) {
            h();
        }
        return d;
    }

    public static String b(Context context) {
        if (g == null) {
            synchronized (f) {
                if (g == null) {
                    g = context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getString("anonymousAppDeviceGUID", null);
                    if (g == null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("XZ");
                        sb.append(UUID.randomUUID().toString());
                        g = sb.toString();
                        context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putString("anonymousAppDeviceGUID", g).apply();
                    }
                }
            }
        }
        return g;
    }
}
