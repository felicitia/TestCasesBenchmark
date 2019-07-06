package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Base64;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.internal.AppEventsLoggerUtility;
import com.facebook.appevents.internal.AppEventsLoggerUtility.GraphAPIActivityType;
import com.facebook.internal.aa;
import com.facebook.internal.b;
import com.facebook.internal.s;
import com.facebook.internal.x;
import com.facebook.internal.z;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;

/* compiled from: FacebookSdk */
public final class f {
    private static final String a = f.class.getCanonicalName();
    private static final HashSet<LoggingBehavior> b = new HashSet<>(Arrays.asList(new LoggingBehavior[]{LoggingBehavior.DEVELOPER_ERRORS}));
    private static Executor c = null;
    /* access modifiers changed from: private */
    public static volatile String d = null;
    private static volatile String e = null;
    private static volatile String f = null;
    private static volatile Boolean g = null;
    private static volatile Boolean h = null;
    private static volatile String i = "facebook.com";
    private static AtomicLong j = new AtomicLong(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
    private static volatile boolean k = false;
    private static boolean l = false;
    private static s<File> m = null;
    /* access modifiers changed from: private */
    public static Context n = null;
    private static int o = 64206;
    private static final Object p = new Object();
    private static String q = x.d();
    private static final BlockingQueue<Runnable> r = new LinkedBlockingQueue(10);
    private static final ThreadFactory s = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(0);

        public Thread newThread(Runnable runnable) {
            StringBuilder sb = new StringBuilder();
            sb.append("FacebookSdk #");
            sb.append(this.a.incrementAndGet());
            return new Thread(runnable, sb.toString());
        }
    };
    private static Boolean t = Boolean.valueOf(false);

    /* compiled from: FacebookSdk */
    public interface a {
        void a();
    }

    public static String h() {
        return "4.35.0";
    }

    @Deprecated
    public static synchronized void a(Context context) {
        synchronized (f.class) {
            a(context, (a) null);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0011, code lost:
        return;
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(final android.content.Context r3, final com.facebook.f.a r4) {
        /*
            java.lang.Class<com.facebook.f> r0 = com.facebook.f.class
            monitor-enter(r0)
            java.lang.Boolean r1 = t     // Catch:{ all -> 0x0081 }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x0012
            if (r4 == 0) goto L_0x0010
            r4.a()     // Catch:{ all -> 0x0081 }
        L_0x0010:
            monitor-exit(r0)
            return
        L_0x0012:
            java.lang.String r1 = "applicationContext"
            com.facebook.internal.aa.a(r3, r1)     // Catch:{ all -> 0x0081 }
            r1 = 0
            com.facebook.internal.aa.b(r3, r1)     // Catch:{ all -> 0x0081 }
            com.facebook.internal.aa.a(r3, r1)     // Catch:{ all -> 0x0081 }
            android.content.Context r1 = r3.getApplicationContext()     // Catch:{ all -> 0x0081 }
            n = r1     // Catch:{ all -> 0x0081 }
            android.content.Context r1 = n     // Catch:{ all -> 0x0081 }
            c(r1)     // Catch:{ all -> 0x0081 }
            java.lang.String r1 = d     // Catch:{ all -> 0x0081 }
            boolean r1 = com.facebook.internal.z.a(r1)     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x0039
            com.facebook.FacebookException r3 = new com.facebook.FacebookException     // Catch:{ all -> 0x0081 }
            java.lang.String r4 = "A valid Facebook app id must be set in the AndroidManifest.xml or set by calling FacebookSdk.setApplicationId before initializing the sdk."
            r3.<init>(r4)     // Catch:{ all -> 0x0081 }
            throw r3     // Catch:{ all -> 0x0081 }
        L_0x0039:
            android.content.Context r1 = n     // Catch:{ all -> 0x0081 }
            boolean r1 = r1 instanceof android.app.Application     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x0050
            java.lang.Boolean r1 = g     // Catch:{ all -> 0x0081 }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x0050
            android.content.Context r1 = n     // Catch:{ all -> 0x0081 }
            android.app.Application r1 = (android.app.Application) r1     // Catch:{ all -> 0x0081 }
            java.lang.String r2 = d     // Catch:{ all -> 0x0081 }
            com.facebook.appevents.internal.a.a(r1, r2)     // Catch:{ all -> 0x0081 }
        L_0x0050:
            r1 = 1
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x0081 }
            t = r1     // Catch:{ all -> 0x0081 }
            com.facebook.internal.k.a()     // Catch:{ all -> 0x0081 }
            com.facebook.internal.v.b()     // Catch:{ all -> 0x0081 }
            android.content.Context r1 = n     // Catch:{ all -> 0x0081 }
            com.facebook.internal.BoltsMeasurementEventListener.getInstance(r1)     // Catch:{ all -> 0x0081 }
            com.facebook.internal.s r1 = new com.facebook.internal.s     // Catch:{ all -> 0x0081 }
            com.facebook.f$2 r2 = new com.facebook.f$2     // Catch:{ all -> 0x0081 }
            r2.<init>()     // Catch:{ all -> 0x0081 }
            r1.<init>(r2)     // Catch:{ all -> 0x0081 }
            m = r1     // Catch:{ all -> 0x0081 }
            java.util.concurrent.FutureTask r1 = new java.util.concurrent.FutureTask     // Catch:{ all -> 0x0081 }
            com.facebook.f$3 r2 = new com.facebook.f$3     // Catch:{ all -> 0x0081 }
            r2.<init>(r4, r3)     // Catch:{ all -> 0x0081 }
            r1.<init>(r2)     // Catch:{ all -> 0x0081 }
            java.util.concurrent.Executor r3 = d()     // Catch:{ all -> 0x0081 }
            r3.execute(r1)     // Catch:{ all -> 0x0081 }
            monitor-exit(r0)
            return
        L_0x0081:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.f.a(android.content.Context, com.facebook.f$a):void");
    }

    public static synchronized boolean a() {
        boolean booleanValue;
        synchronized (f.class) {
            booleanValue = t.booleanValue();
        }
        return booleanValue;
    }

    public static boolean a(LoggingBehavior loggingBehavior) {
        boolean z;
        synchronized (b) {
            z = b() && b.contains(loggingBehavior);
        }
        return z;
    }

    public static boolean b() {
        return k;
    }

    public static boolean c() {
        return l;
    }

    public static Executor d() {
        synchronized (p) {
            if (c == null) {
                c = AsyncTask.THREAD_POOL_EXECUTOR;
            }
        }
        return c;
    }

    public static String e() {
        return i;
    }

    public static Context f() {
        aa.a();
        return n;
    }

    public static String g() {
        z.b(a, String.format("getGraphApiVersion: %s", new Object[]{q}));
        return q;
    }

    public static void a(Context context, final String str) {
        final Context applicationContext = context.getApplicationContext();
        d().execute(new Runnable() {
            public void run() {
                f.b(applicationContext, str);
            }
        });
    }

    static void b(Context context, String str) {
        if (context == null || str == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        }
        try {
            b a2 = b.a(context);
            SharedPreferences sharedPreferences = context.getSharedPreferences("com.facebook.sdk.attributionTracking", 0);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("ping");
            String sb2 = sb.toString();
            long j2 = sharedPreferences.getLong(sb2, 0);
            GraphRequest a3 = GraphRequest.a((AccessToken) null, String.format("%s/activities", new Object[]{str}), AppEventsLoggerUtility.a(GraphAPIActivityType.MOBILE_INSTALL_EVENT, a2, AppEventsLogger.b(context), b(context), context), (GraphRequest.b) null);
            if (j2 == 0) {
                a3.i();
                Editor edit = sharedPreferences.edit();
                edit.putLong(sb2, System.currentTimeMillis());
                edit.apply();
            }
        } catch (JSONException e2) {
            throw new FacebookException("An error occurred while publishing install.", (Throwable) e2);
        } catch (Exception e3) {
            z.a("Facebook-publish", e3);
        }
    }

    public static boolean b(Context context) {
        aa.a();
        return context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getBoolean("limitEventUsage", false);
    }

    public static long i() {
        aa.a();
        return j.get();
    }

    static void c(Context context) {
        if (context != null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo != null && applicationInfo.metaData != null) {
                    if (d == null) {
                        Object obj = applicationInfo.metaData.get("com.facebook.sdk.ApplicationId");
                        if (obj instanceof String) {
                            String str = (String) obj;
                            if (str.toLowerCase(Locale.ROOT).startsWith("fb")) {
                                d = str.substring(2);
                            } else {
                                d = str;
                            }
                        } else if (obj instanceof Integer) {
                            throw new FacebookException("App Ids cannot be directly placed in the manifest.They must be prefixed by 'fb' or be placed in the string resource file.");
                        }
                    }
                    if (e == null) {
                        e = applicationInfo.metaData.getString("com.facebook.sdk.ApplicationName");
                    }
                    if (f == null) {
                        f = applicationInfo.metaData.getString("com.facebook.sdk.ClientToken");
                    }
                    if (o == 64206) {
                        o = applicationInfo.metaData.getInt("com.facebook.sdk.CallbackOffset", 64206);
                    }
                    if (g == null) {
                        g = Boolean.valueOf(applicationInfo.metaData.getBoolean("com.facebook.sdk.AutoLogAppEventsEnabled", true));
                    }
                    if (h == null) {
                        h = Boolean.valueOf(applicationInfo.metaData.getBoolean("com.facebook.sdk.CodelessDebugLogEnabled", false));
                    }
                }
            } catch (NameNotFoundException unused) {
            }
        }
    }

    public static String d(Context context) {
        aa.a();
        if (context == null) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 64);
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                return null;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                instance.update(packageInfo.signatures[0].toByteArray());
                return Base64.encodeToString(instance.digest(), 9);
            } catch (NoSuchAlgorithmException unused) {
                return null;
            }
        } catch (NameNotFoundException unused2) {
            return null;
        }
    }

    public static String j() {
        aa.a();
        return d;
    }

    public static String k() {
        aa.a();
        return e;
    }

    public static String l() {
        aa.a();
        return f;
    }

    public static boolean m() {
        aa.a();
        return g.booleanValue();
    }

    public static boolean n() {
        aa.a();
        return h.booleanValue();
    }

    public static File o() {
        aa.a();
        return (File) m.a();
    }

    public static int p() {
        aa.a();
        return o;
    }

    public static boolean a(int i2) {
        return i2 >= o && i2 < o + 100;
    }
}
