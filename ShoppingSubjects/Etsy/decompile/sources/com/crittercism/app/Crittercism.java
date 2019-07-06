package com.crittercism.app;

import android.content.Context;
import android.location.Location;
import android.webkit.WebView;
import com.crittercism.internal.am;
import com.crittercism.internal.an;
import com.crittercism.internal.ap.e;
import com.crittercism.internal.at;
import com.crittercism.internal.bm;
import com.crittercism.internal.cm;
import com.etsy.android.lib.models.ResponseConstants;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class Crittercism {
    private static volatile am a;
    private static final List<CrittercismCallback<CrashData>> b = new ArrayList();

    public enum LoggingLevel {
        Silent,
        Error,
        Warning,
        Info
    }

    private Crittercism() {
    }

    public static synchronized void initialize(Context context, String str) {
        synchronized (Crittercism.class) {
            initialize(context, str, new CrittercismConfig());
        }
    }

    public static void sendAppLoadData() {
        if (a == null) {
            a("sendAppLoadData");
            return;
        }
        try {
            am amVar = a;
            if (amVar.o != null) {
                amVar.o.g();
            }
            if (amVar.p != null) {
                amVar.p.g();
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    public static void logHandledException(Throwable th) {
        if (a == null) {
            a("logHandledException");
        } else if (th == null) {
            cm.b("Invalid input to Crittercism.logHandledException(): null exception parameter");
        } else {
            try {
                a.a(th);
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th2) {
                cm.b(th2);
            }
        }
    }

    public static void logNetworkRequest(String str, URL url, long j, long j2, long j3, int i, Exception exc) {
        if (a == null) {
            a("logNetworkRequest");
        } else if (url == null) {
            cm.a("Null URL provided. Endpoint will not be logged");
        } else {
            try {
                String str2 = str;
                a.a(str2, url.toExternalForm(), j, j2, j3, i, new bm(exc));
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                cm.b(th);
            }
        }
    }

    public static boolean didCrashOnLastLoad() {
        if (a == null) {
            a("didCrashOnLoad");
            return false;
        }
        try {
            return a.b();
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
            return false;
        }
    }

    public static void getPreviousSessionCrashData(CrittercismCallback<CrashData> crittercismCallback) {
        if (crittercismCallback == null) {
            cm.b("Crittercism.getPreviousSessionCrashData() given invalid input parameter: null crashListener");
            return;
        }
        try {
            if (a == null) {
                synchronized (b) {
                    b.add(crittercismCallback);
                }
                return;
            }
            a.a(crittercismCallback);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    public static void instrumentWebView(WebView webView) {
        if (a == null) {
            a("instrumentWebView");
        } else if (webView == null) {
            cm.b("WebView was null. Skipping instrumentation.");
        } else {
            try {
                a.a(webView);
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                cm.b(th);
            }
        }
    }

    public static boolean getOptOutStatus() {
        if (a == null) {
            return false;
        }
        try {
            return a.a();
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
            return false;
        }
    }

    public static void setOptOutStatus(boolean z) {
        if (a == null) {
            a("setOptOutStatus");
            return;
        }
        try {
            am amVar = a;
            amVar.j.submit(new Runnable(z) {
                final /* synthetic */ boolean a;

                {
                    this.a = r2;
                }

                public final void run() {
                    cg cgVar = new cg(am.this.a);
                    cgVar.a.edit().putBoolean("isOptedOut", this.a).commit();
                    am.this.k.a((e<T>) ap.c, Boolean.valueOf(!this.a));
                    am.this.k.a((e<T>) ap.m, Boolean.valueOf(!this.a));
                    am.this.k.a((e<T>) ap.w, Boolean.valueOf(!this.a));
                    am.this.k.a((e<T>) ap.r, Boolean.valueOf(!this.a));
                    am.this.k.a((e<T>) ap.B, Boolean.valueOf(!this.a));
                    am.this.k.a((e<T>) ap.U, Boolean.valueOf(!this.a));
                    am.this.k.a((e<T>) ap.ac, Boolean.valueOf(!this.a));
                    am.this.k.a((e<T>) ap.ah, Boolean.valueOf(!this.a));
                    am.this.k.a((e<T>) ap.aq, Boolean.valueOf(!this.a));
                    am.this.k.a((e<T>) ap.am, Boolean.valueOf(!this.a));
                    am.this.k.a((e<T>) ap.an, Boolean.valueOf(!this.a));
                    am.this.k.a((e<T>) ap.ao, Boolean.valueOf(!this.a));
                    am.this.k.a((e<T>) ap.ap, Boolean.valueOf(!this.a));
                    for (cd a2 : am.this.g) {
                        a2.a(this.a);
                    }
                }
            });
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    public static void setMetadata(JSONObject jSONObject) {
        if (a == null) {
            a("setMetadata");
        } else if (jSONObject == null) {
            cm.b("Invalid input to Crittercism.setMetadata(): null metadata parameter");
        } else {
            try {
                a.a(jSONObject);
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                cm.b(th);
            }
        }
    }

    public static void setUsername(String str) {
        if (a == null) {
            a("setUsername");
        } else if (str == null) {
            cm.b("Crittercism.setUsername() given invalid parameter: null");
        } else {
            try {
                a.a(new JSONObject().put(ResponseConstants.USERNAME, str));
            } catch (JSONException e) {
                cm.b("Crittercism.setUsername()", e);
            } catch (ThreadDeath e2) {
                throw e2;
            } catch (Throwable th) {
                cm.b(th);
            }
        }
    }

    public static void leaveBreadcrumb(String str) {
        if (a == null) {
            a("leaveBreadcrumb");
        } else if (str == null) {
            cm.b("Cannot leave null breadcrumb", new NullPointerException());
        } else {
            try {
                a.a(at.a(str));
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                cm.b(th);
            }
        }
    }

    public static void beginUserflow(String str) {
        if (a == null) {
            a("beginUserflow");
        } else if (str == null) {
            cm.b("Invalid input to beginUserflow(): null userflow name");
        } else {
            try {
                a.a(str);
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                cm.b(th);
            }
        }
    }

    @Deprecated
    public static void beginTransaction(String str) {
        if (a == null) {
            a("beginTransaction");
        } else if (str == null) {
            cm.b("Invalid input to beginTransaction(): null name");
        } else {
            beginUserflow(str);
        }
    }

    public static void endUserflow(String str) {
        if (a == null) {
            a("endUserflow");
        } else if (str == null) {
            cm.b("Invalid input to endUserflow(): null userflow name");
        } else {
            try {
                a.b(str);
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                cm.b(th);
            }
        }
    }

    @Deprecated
    public static void endTransaction(String str) {
        if (a == null) {
            a("endTransaction");
        } else if (str == null) {
            cm.b("Invalid input to endTransaction(): null name");
        } else {
            endUserflow(str);
        }
    }

    public static void failUserflow(String str) {
        if (a == null) {
            a("failUserflow");
        } else if (str == null) {
            cm.b("Invalid input to failUserflow(): null name");
        } else {
            try {
                a.c(str);
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                cm.b(th);
            }
        }
    }

    @Deprecated
    public static void failTransaction(String str) {
        if (a == null) {
            a("failTransaction");
        } else if (str == null) {
            cm.b("Invalid input to failTransaction(): null name");
        } else {
            failUserflow(str);
        }
    }

    public static void cancelUserflow(String str) {
        if (a == null) {
            a("cancelUserflow");
        } else if (str == null) {
            cm.b("Invalid input to cancelUserflow(): null name");
        } else {
            try {
                a.d(str);
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                cm.b(th);
            }
        }
    }

    @Deprecated
    public static void cancelTransaction(String str) {
        if (a == null) {
            a("cancelTransaction");
        } else if (str == null) {
            cm.b("Invalid input to cancelTransaction(): null name");
        } else {
            cancelUserflow(str);
        }
    }

    public static void setUserflowValue(String str, int i) {
        if (a == null) {
            a("setUserflowValue");
        } else if (str == null) {
            cm.b("Invalid input to setUserflowValue(): null name");
        } else {
            try {
                a.a(str, i);
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                cm.b(th);
            }
        }
    }

    @Deprecated
    public static void setTransactionValue(String str, int i) {
        if (a == null) {
            a("setTransactionValue");
        } else if (str == null) {
            cm.b("Invalid input to setTransactionValue(): null name");
        } else {
            setUserflowValue(str, i);
        }
    }

    public static int getUserflowValue(String str) {
        if (a == null) {
            a("getUserflowValue");
            return -1;
        } else if (str == null) {
            cm.b("Invalid input to getUserflowValue(): null name");
            return -1;
        } else {
            try {
                return a.e(str);
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                cm.b(th);
                return -1;
            }
        }
    }

    @Deprecated
    public static int getTransactionValue(String str) {
        if (a == null) {
            a("getTransactionValue");
            return -1;
        } else if (str != null) {
            return getUserflowValue(str);
        } else {
            cm.b("Invalid input to getTransactionValue(): null name");
            return -1;
        }
    }

    public static void updateLocation(Location location) {
        try {
            an.a(location);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    public static void setLoggingLevel(LoggingLevel loggingLevel) {
        try {
            cm.a(loggingLevel);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    private static void a(String str) {
        StringBuilder sb = new StringBuilder("Must initialize Crittercism before calling ");
        sb.append(Crittercism.class.getName());
        sb.append(".");
        sb.append(str);
        sb.append("(). Request is being ignored...");
        cm.b(sb.toString(), new IllegalStateException());
    }

    /* JADX INFO: used method not loaded: com.crittercism.internal.am.a(com.crittercism.app.CrittercismCallback):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        r8 = b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0109, code lost:
        monitor-enter(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
        r9 = b.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0114, code lost:
        if (r9.hasNext() == false) goto L_0x0122;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0116, code lost:
        a.a((com.crittercism.app.CrittercismCallback) r9.next());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0122, code lost:
        b.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0127, code lost:
        monitor-exit(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0129, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void initialize(android.content.Context r8, java.lang.String r9, com.crittercism.app.CrittercismConfig r10) {
        /*
            java.lang.Class<com.crittercism.app.Crittercism> r0 = com.crittercism.app.Crittercism.class
            monitor-enter(r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            java.lang.String r2 = "Initializing Crittercism 5.8.10 for App ID "
            r1.<init>(r2)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            r1.append(r9)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            java.lang.String r1 = r1.toString()     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            com.crittercism.internal.cm.c(r1)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            if (r8 != 0) goto L_0x001d
            java.lang.String r8 = "Crittercism.initialize() given a null context parameter"
            com.crittercism.internal.cm.b(r8)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            monitor-exit(r0)
            return
        L_0x001d:
            boolean r1 = r8 instanceof android.app.Application     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            if (r1 == 0) goto L_0x0025
            r1 = r8
            android.app.Application r1 = (android.app.Application) r1     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            goto L_0x004d
        L_0x0025:
            boolean r1 = r8 instanceof android.app.Activity     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            if (r1 == 0) goto L_0x0031
            r1 = r8
            android.app.Activity r1 = (android.app.Activity) r1     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            android.app.Application r1 = r1.getApplication()     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            goto L_0x004d
        L_0x0031:
            boolean r1 = r8 instanceof android.app.Service     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            if (r1 == 0) goto L_0x003d
            r1 = r8
            android.app.Service r1 = (android.app.Service) r1     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            android.app.Application r1 = r1.getApplication()     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            goto L_0x004d
        L_0x003d:
            android.content.Context r1 = r8.getApplicationContext()     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            boolean r1 = r1 instanceof android.app.Application     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            if (r1 == 0) goto L_0x004c
            android.content.Context r1 = r8.getApplicationContext()     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            android.app.Application r1 = (android.app.Application) r1     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            goto L_0x004d
        L_0x004c:
            r1 = 0
        L_0x004d:
            if (r1 != 0) goto L_0x006f
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            java.lang.String r10 = "Crittercism.initialize() expects the input Context to be an instanceof Application. Received '"
            r9.<init>(r10)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            java.lang.Class r8 = r8.getClass()     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            java.lang.String r8 = r8.getName()     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            r9.append(r8)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            java.lang.String r8 = "'. Crittercism will no be initialized."
            r9.append(r8)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            java.lang.String r8 = r9.toString()     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            com.crittercism.internal.cm.b(r8)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            monitor-exit(r0)
            return
        L_0x006f:
            if (r9 != 0) goto L_0x0078
            java.lang.String r8 = "Crittercism.initialize() given a null appId parameter"
            com.crittercism.internal.cm.b(r8)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            monitor-exit(r0)
            return
        L_0x0078:
            if (r10 != 0) goto L_0x0081
            java.lang.String r8 = "Crittercism.initialize() given a null CrittercismConfiguration. Crittercism will not be initialized"
            com.crittercism.internal.cm.b(r8)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            monitor-exit(r0)
            return
        L_0x0081:
            int r8 = android.os.Build.VERSION.SDK_INT     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            r2 = 14
            if (r8 >= r2) goto L_0x008e
            java.lang.String r8 = "Crittercism is not supported for Android API less than 14 (ICS). Crittercism will not be enabled"
            com.crittercism.internal.cm.b(r8)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            monitor-exit(r0)
            return
        L_0x008e:
            java.lang.String r8 = "android.permission.INTERNET"
            boolean r8 = com.crittercism.internal.ao.a(r1, r8)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            if (r8 != 0) goto L_0x009d
            java.lang.String r8 = "Crittercism requires INTERNET permission. Please add android.permission.INTERNET to your AndroidManifest.xml. Crittercism will not be initialized."
            com.crittercism.internal.cm.a(r8)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            monitor-exit(r0)
            return
        L_0x009d:
            boolean r8 = r10.allowsCellularAccess()     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            if (r8 != 0) goto L_0x00b2
            java.lang.String r8 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r8 = com.crittercism.internal.ao.a(r1, r8)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            if (r8 != 0) goto L_0x00b2
            java.lang.String r8 = "Crittercism requires adding android.permission.ACCESS_NETWORK_STATE to your AndroidManifest.xml when setting CrittercismConfig.setAllowsCellularAccess(false). Crittercism will not be initialized."
            com.crittercism.internal.cm.a(r8)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            monitor-exit(r0)
            return
        L_0x00b2:
            boolean r8 = com.crittercism.internal.as.a(r9)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            if (r8 != 0) goto L_0x00d0
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            java.lang.String r10 = "Crittercism.initialize() given an invalid app ID '"
            r8.<init>(r10)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            r8.append(r9)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            java.lang.String r9 = "'"
            r8.append(r9)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            java.lang.String r8 = r8.toString()     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            com.crittercism.internal.cm.a(r8)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            monitor-exit(r0)
            return
        L_0x00d0:
            java.lang.Class<com.crittercism.app.Crittercism> r8 = com.crittercism.app.Crittercism.class
            monitor-enter(r8)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            com.crittercism.internal.am r2 = a     // Catch:{ all -> 0x012d }
            if (r2 == 0) goto L_0x00df
            java.lang.String r9 = "Crittercism has already been initialized. Subsequent calls to initialize() are ignored."
            com.crittercism.internal.cm.b(r9)     // Catch:{ all -> 0x012d }
            monitor-exit(r8)     // Catch:{ all -> 0x012d }
            monitor-exit(r0)
            return
        L_0x00df:
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x012d }
            com.crittercism.internal.am r4 = new com.crittercism.internal.am     // Catch:{ all -> 0x012d }
            r4.<init>(r1, r9, r10)     // Catch:{ all -> 0x012d }
            a = r4     // Catch:{ all -> 0x012d }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x012d }
            java.lang.String r10 = "Crittercism initialized in "
            r9.<init>(r10)     // Catch:{ all -> 0x012d }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x012d }
            long r6 = r4 - r2
            r9.append(r6)     // Catch:{ all -> 0x012d }
            java.lang.String r10 = "ms"
            r9.append(r10)     // Catch:{ all -> 0x012d }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x012d }
            com.crittercism.internal.cm.d(r9)     // Catch:{ all -> 0x012d }
            monitor-exit(r8)     // Catch:{ all -> 0x012d }
            java.util.List<com.crittercism.app.CrittercismCallback<com.crittercism.app.CrashData>> r8 = b     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            monitor-enter(r8)     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
            java.util.List<com.crittercism.app.CrittercismCallback<com.crittercism.app.CrashData>> r9 = b     // Catch:{ all -> 0x012a }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x012a }
        L_0x0110:
            boolean r10 = r9.hasNext()     // Catch:{ all -> 0x012a }
            if (r10 == 0) goto L_0x0122
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x012a }
            com.crittercism.app.CrittercismCallback r10 = (com.crittercism.app.CrittercismCallback) r10     // Catch:{ all -> 0x012a }
            com.crittercism.internal.am r1 = a     // Catch:{ all -> 0x012a }
            r1.a(r10)     // Catch:{ all -> 0x012a }
            goto L_0x0110
        L_0x0122:
            java.util.List<com.crittercism.app.CrittercismCallback<com.crittercism.app.CrashData>> r9 = b     // Catch:{ all -> 0x012a }
            r9.clear()     // Catch:{ all -> 0x012a }
            monitor-exit(r8)     // Catch:{ all -> 0x012a }
            monitor-exit(r0)
            return
        L_0x012a:
            r9 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x012a }
            throw r9     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
        L_0x012d:
            r9 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x012d }
            throw r9     // Catch:{ ThreadDeath -> 0x0138, Throwable -> 0x0132 }
        L_0x0130:
            r8 = move-exception
            goto L_0x013a
        L_0x0132:
            r8 = move-exception
            com.crittercism.internal.cm.b(r8)     // Catch:{ all -> 0x0130 }
            monitor-exit(r0)
            return
        L_0x0138:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0130 }
        L_0x013a:
            monitor-exit(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crittercism.app.Crittercism.initialize(android.content.Context, java.lang.String, com.crittercism.app.CrittercismConfig):void");
    }
}
