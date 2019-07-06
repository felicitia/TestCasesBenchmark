package com.crittercism.internal;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.NetworkRequest.Builder;
import android.os.Build.VERSION;
import android.os.Process;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.webkit.WebView;
import com.crittercism.app.CrashData;
import com.crittercism.app.CrittercismCallback;
import com.crittercism.app.CrittercismConfig;
import com.crittercism.app.CrittercismNDK;
import com.crittercism.internal.b.C0017b;
import com.crittercism.internal.cj.f;
import com.crittercism.internal.cj.g;
import com.etsy.android.lib.models.ResponseConstants;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import org.json.JSONArray;
import org.json.JSONObject;

public final class am {
    private ak A;
    private bt B;
    private CrittercismConfig C;
    private as D;
    private bu E;
    private bv F;
    private Set<WebView> G = new HashSet();
    private Date H;
    private Date I = new Date();
    Application a;
    ay<aq> b;
    ay<bf> c;
    ay<aq> d;
    ay<at> e;
    ay<bd> f = null;
    List<cd> g = new LinkedList();
    cd h;
    ScheduledExecutorService i = co.a("crittercism networking");
    public ScheduledExecutorService j = co.b("crittercism data");
    ap k;
    protected d l;
    cf m;
    ck n;
    public bs o;
    public bs p;
    av q;
    private String r = null;
    private ay<au> s;
    private ay<ax> t;
    private ay<aw> u;
    private ay<ar> v;
    private ay<ar> w;
    private ay<b> x;
    private ay<cj> y;
    private final ca z;

    class a implements Runnable {
        private a() {
        }

        /* synthetic */ a(am amVar, byte b) {
            this();
        }

        public final void run() {
            try {
                c cVar = new c(am.this.a);
                d dVar = am.this.l;
                boolean a2 = t.a(dVar, cVar);
                StringBuilder sb = new StringBuilder("Http network insights installation: ");
                sb.append(a2);
                cm.d(sb.toString());
                boolean z = false;
                boolean z2 = VERSION.SDK_INT >= 19 ? m.a(dVar, cVar) : VERSION.SDK_INT >= 14 ? l.a(dVar, cVar) : false;
                StringBuilder sb2 = new StringBuilder("Https network insights installation: ");
                sb2.append(z2);
                cm.d(sb2.toString());
                if (z2) {
                    z = i.a(dVar, cVar);
                    StringBuilder sb3 = new StringBuilder("Network insights provider service instrumented: ");
                    sb3.append(z);
                    cm.d(sb3.toString());
                }
                if (a2 || z2 || z) {
                    cm.c("installed service monitoring");
                }
            } catch (Exception e) {
                StringBuilder sb4 = new StringBuilder("Exception in installApm: ");
                sb4.append(e.getClass().getName());
                cm.d(sb4.toString());
                cm.a((Throwable) e);
            }
        }
    }

    class b implements Runnable {
        private boolean b;

        public b(boolean z) {
            this.b = z;
        }

        private void a(ay<at> ayVar) {
            LinkedList linkedList;
            List b2 = ayVar.b();
            if (b2 instanceof LinkedList) {
                linkedList = (LinkedList) b2;
            } else {
                linkedList = new LinkedList(b2);
            }
            Iterator descendingIterator = linkedList.descendingIterator();
            boolean z = false;
            while (descendingIterator.hasNext()) {
                at atVar = (at) descendingIterator.next();
                if (z) {
                    ayVar.a(atVar.a);
                } else if (atVar.c == com.crittercism.internal.at.b.a) {
                    z = true;
                }
            }
            String[] strArr = {"network_bcs", "previous_bcs", "current_bcs", "system_bcs"};
            for (int i = 0; i < 4; i++) {
                cn.a(az.a(am.this.a, strArr[i]));
            }
        }

        public final void run() {
            CrashData crashData;
            am.this.k.a(ap.V);
            am.this.m.a();
            cn.a(new File(am.this.a.getFilesDir(), "com.crittercism/pending"));
            SharedPreferences sharedPreferences = am.this.a.getSharedPreferences("com.crittercism.usersettings", 0);
            bf bfVar = null;
            if (!sharedPreferences.getBoolean("crashedOnLastLoad", false)) {
                crashData = null;
            } else {
                String string = sharedPreferences.getString("crashName", "");
                String string2 = sharedPreferences.getString("crashReason", "");
                long j = sharedPreferences.getLong("crashDate", 0);
                crashData = new CrashData(string, string2, j != 0 ? new Date(j) : null);
            }
            ch.a = crashData;
            ch.a(am.this.a, null);
            ci ciVar = new ci(am.this.a);
            ciVar.b.edit().putInt("sessionIDSetting", ciVar.b.getInt("sessionIDSetting", 0) + 1).commit();
            if (!this.b) {
                bfVar = bf.a(CrittercismNDK.crashDumpDirectory(am.this.a), am.this.e, am.this.q);
            }
            a(am.this.e);
            if (!this.b) {
                try {
                    CrittercismNDK.installNdkLib(am.this.a);
                } catch (Throwable th) {
                    StringBuilder sb = new StringBuilder("Exception installing ndk library: ");
                    sb.append(th.getClass().getName());
                    cm.d(sb.toString());
                }
            }
            Application application = am.this.a;
            String str = am.this.q.e;
            StringBuilder sb2 = new StringBuilder("com.crittercism.");
            sb2.append(str);
            sb2.append(".usermetadata");
            application.getSharedPreferences(sb2.toString(), 0).edit().clear().commit();
            am.this.a(am.this.k);
            if (bfVar != null) {
                if (((Boolean) am.this.k.a(ap.w)).booleanValue()) {
                    bfVar.f = ((Float) am.this.k.a(ap.A)).floatValue();
                    am.this.c.a(bfVar);
                }
                ch.a = new CrashData("NDK crash", "", new Date());
            }
        }
    }

    static class c implements Runnable {
        private CrittercismCallback<CrashData> a;
        private CrashData b;

        public c(CrittercismCallback<CrashData> crittercismCallback, CrashData crashData) {
            this.a = crittercismCallback;
            this.b = crashData;
        }

        public final void run() {
            this.a.onDataReceived(this.b);
        }
    }

    static class d implements Runnable {
        private ca a;
        private Context b;

        public d(ca caVar, Context context) {
            this.a = caVar;
            this.b = context;
        }

        public final void run() {
            this.a.a = ca.a();
        }
    }

    public am(Application application, String str, CrittercismConfig crittercismConfig) {
        ay<ar> ayVar;
        ay<ar> ayVar2;
        ay<at> ayVar3;
        ay<aq> ayVar4;
        ay<aq> ayVar5;
        ay<b> ayVar6;
        ay<bf> ayVar7;
        ay<cj> ayVar8;
        ay<bd> ayVar9;
        this.r = str;
        this.a = application;
        this.C = new CrittercismConfig(crittercismConfig);
        this.A = new ak(this.a, this.C);
        this.m = new cf(this.a);
        this.k = new ap(this.a, this.r);
        this.q = new av(this.A, this.a, new ao(this.a, this.C), this.r);
        this.D = new as(this.r, this.q, this.k);
        boolean a2 = a((Context) this.a);
        this.s = new bc(1);
        this.t = new bc(1);
        this.u = new bc(1);
        Application application2 = this.a;
        if (a2) {
            ayVar = new bg<>();
        } else {
            ayVar = new az<>(application2, "app_loads_2", new com.crittercism.internal.ar.a(0), 10);
        }
        this.v = ayVar;
        Application application3 = this.a;
        if (a2) {
            ayVar2 = new bg<>();
        } else {
            ayVar2 = new az<>(application3, "app_loads_dhub", new com.crittercism.internal.ar.a(0), 10);
        }
        this.w = ayVar2;
        Application application4 = this.a;
        if (a2) {
            ayVar3 = new bc<>(10);
        } else {
            ayVar3 = new az<>(application4, "breadcrumbs", new com.crittercism.internal.at.a(0), Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        }
        this.e = ayVar3;
        Application application5 = this.a;
        if (a2) {
            ayVar4 = new bc<>(1);
        } else {
            ayVar4 = new az<>(application5, "exceptions", new com.crittercism.internal.aq.a(0), 5);
        }
        this.b = ayVar4;
        Application application6 = this.a;
        if (a2) {
            ayVar5 = new bc<>(1);
        } else {
            ayVar5 = new az<>(application6, "sdk_crashes", new com.crittercism.internal.aq.a(0), 5);
        }
        this.d = ayVar5;
        Application application7 = this.a;
        if (a2) {
            ayVar6 = new bc<>(5);
        } else {
            ayVar6 = new az<>(application7, "network_statistics", new C0017b(0), 50);
        }
        this.x = ayVar6;
        Application application8 = this.a;
        if (a2) {
            ayVar7 = new bg<>();
        } else {
            ayVar7 = new az<>(application8, "ndk_crashes", new com.crittercism.internal.bf.b(0), 5);
        }
        this.c = ayVar7;
        Application application9 = this.a;
        if (a2) {
            ayVar8 = new bc<>(5);
        } else {
            ayVar8 = new az<>(application9, "finished_txns", new g(0), 50);
        }
        this.y = ayVar8;
        Application application10 = this.a;
        String str2 = this.r;
        if (a2) {
            ayVar9 = new bg<>();
        } else {
            ayVar9 = new be<>(application10, str2);
        }
        this.f = ayVar9;
        List<String> uRLBlacklistPatterns = crittercismConfig.getURLBlacklistPatterns();
        uRLBlacklistPatterns.add(this.D.a.getHost());
        uRLBlacklistPatterns.add(this.D.b.getHost());
        uRLBlacklistPatterns.add(this.D.d.getHost());
        uRLBlacklistPatterns.add(this.D.c.getHost());
        com.crittercism.internal.d.a aVar = new com.crittercism.internal.d.a();
        aVar.a = this.j;
        aVar.b = uRLBlacklistPatterns;
        aVar.c = crittercismConfig.getPreserveQueryStringPatterns();
        aVar.d = this.x;
        aVar.e = this.e;
        aVar.f = this.k;
        d dVar = new d(aVar.a, aVar.b, aVar.c, aVar.d, aVar.e, aVar.f, 0);
        this.l = dVar;
        this.n = new ck(this.a, this.j, this.y, this.k);
        this.z = new ca();
        Thread thread = new Thread(new d(this.z, this.a));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e2) {
            cm.b((Throwable) e2);
        }
        this.j.submit(new b(a2));
        try {
            this.H = new Date(br.f());
        } catch (IOException e3) {
            cm.a((Throwable) e3);
        }
        if (this.H != null) {
            a(at.a(this.H));
        } else {
            a(at.a(this.I));
        }
        if (this.C.isServiceMonitoringEnabled() && ((Boolean) this.k.a(ap.a)).booleanValue() && (VERSION.SDK_INT <= 23 || ((Boolean) this.k.a(ap.b)).booleanValue())) {
            Thread thread2 = new Thread(new a(this, 0));
            thread2.start();
            try {
                thread2.join();
            } catch (InterruptedException e4) {
                cm.b((Throwable) e4);
            }
        }
        Thread.setDefaultUncaughtExceptionHandler(new al(Thread.getDefaultUncaughtExceptionHandler()) {
            public final void a(Throwable th) {
                am amVar = am.this;
                am amVar2 = amVar;
                Throwable th2 = th;
                AnonymousClass3 r0 = new Runnable(th2, Thread.currentThread().getId(), new Date(), Thread.getAllStackTraces()) {
                    final /* synthetic */ Throwable a;
                    final /* synthetic */ long b;
                    final /* synthetic */ Date c;
                    final /* synthetic */ Map d;

                    {
                        this.a = r2;
                        this.b = r3;
                        this.c = r5;
                        this.d = r6;
                    }

                    public final void run() {
                        aq aqVar = new aq(this.a, am.this.q, this.b);
                        aqVar.h = cp.a.a(this.c);
                        Map map = this.d;
                        aqVar.g = new JSONArray();
                        for (Entry entry : map.entrySet()) {
                            HashMap hashMap = new HashMap();
                            Thread thread = (Thread) entry.getKey();
                            if (thread.getId() != aqVar.a) {
                                hashMap.put(ResponseConstants.NAME, thread.getName());
                                hashMap.put("id", Long.valueOf(thread.getId()));
                                hashMap.put(ResponseConstants.STATE, thread.getState().name());
                                JSONArray jSONArray = new JSONArray();
                                StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) entry.getValue();
                                for (StackTraceElement stackTraceElement : stackTraceElementArr) {
                                    jSONArray.put(stackTraceElement.toString());
                                }
                                hashMap.put("stacktrace", jSONArray);
                                aqVar.g.put(new JSONObject(hashMap));
                            }
                        }
                        aqVar.a(am.this.e);
                        aqVar.a(am.this.n.a());
                        if (((Boolean) am.this.k.a(ap.m)).booleanValue()) {
                            aqVar.m = ((Float) am.this.k.a(ap.q)).floatValue();
                            am.this.d.a(aqVar);
                        }
                        ch.a(am.this.a, new CrashData(aqVar.d, aqVar.e, this.c));
                    }
                };
                try {
                    amVar.j.submit(r0).get();
                    cd cdVar = amVar.h;
                    ScheduledFuture scheduledFuture = cdVar.f;
                    if (scheduledFuture != null) {
                        scheduledFuture.get();
                    }
                    Future future = cdVar.g;
                    if (future != null) {
                        future.get();
                    }
                    Future future2 = cdVar.h;
                    if (future2 != null) {
                        future2.get();
                    }
                } catch (InterruptedException e) {
                    cm.b((Throwable) e);
                } catch (ExecutionException e2) {
                    cm.b((Throwable) e2);
                }
            }
        });
        bs bsVar = new bs(this.a, this.j, this.v, new ar(this.q), this.k, ap.U, ap.Y, crittercismConfig.delaySendingAppLoad(), this.n, this.H);
        this.o = bsVar;
        Application application11 = this.a;
        ScheduledExecutorService scheduledExecutorService = this.j;
        ay<ar> ayVar10 = this.w;
        ar arVar = new ar(this.q);
        bs bsVar2 = new bs(application11, scheduledExecutorService, ayVar10, arVar, this.k, ap.ac, ap.ag, crittercismConfig.delaySendingAppLoad(), null, this.H);
        this.p = bsVar2;
        this.B = new bt(this.a, this.j, this.e, this.k);
        this.E = new bu(this.a, this.j, this.e, this.k);
        if (VERSION.SDK_INT >= 14) {
            this.F = new bv(this.a, this.q);
        }
        Date date = new Date();
        HashMap hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "Initialized Crittercism 5.8.10");
        a(new at(date, com.crittercism.internal.at.b.d, new JSONObject(hashMap)));
    }

    private static boolean a(Context context) {
        int myUid = Process.myUid();
        int myPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        int i2 = 0;
        for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            if (runningAppProcessInfo.uid == myUid) {
                i2++;
            }
        }
        if (i2 <= 1) {
            return false;
        }
        for (RunningServiceInfo runningServiceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (runningServiceInfo.pid == myPid) {
                return true;
            }
        }
        return false;
    }

    public final void a(String str, String str2, long j2, long j3, long j4, int i2, bm bmVar) {
        long currentTimeMillis = System.currentTimeMillis() - j2;
        if (str == null) {
            cm.a("Null HTTP request method provided. Endpoint will not be logged.");
        } else if (str2 == null) {
            cm.a("Null url provided. Endpoint will not be logged");
        } else if (j3 < 0 || j4 < 0) {
            cm.a("Invalid byte values. Bytes need to be non-negative. Endpoint will not be logged.");
        } else if (j2 < 0 || currentTimeMillis < 0) {
            StringBuilder sb = new StringBuilder("Invalid latency '");
            sb.append(j2);
            sb.append("'. Endpoint will not be logged.");
            cm.a(sb.toString());
        } else {
            c cVar = new c(this.a);
            b bVar = new b();
            bVar.j = str.toUpperCase();
            bVar.a(str2);
            bVar.a(j3);
            bVar.b(j4);
            bVar.i = i2;
            bVar.o = a.a(cVar.a);
            bVar.c(currentTimeMillis);
            bVar.d(currentTimeMillis + j2);
            bVar.k = bmVar;
            if (an.b()) {
                bVar.a(an.a());
            }
            this.l.a(bVar, com.crittercism.internal.b.c.LOG_ENDPOINT);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
        r0 = new com.crittercism.internal.ct(r6, r6.l, r6.a);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        new com.crittercism.internal.cs();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        if (android.os.Build.VERSION.SDK_INT > 15) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0037, code lost:
        r1 = com.crittercism.internal.cs.a(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0040, code lost:
        if (android.os.Build.VERSION.SDK_INT > 18) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0042, code lost:
        r1 = com.crittercism.internal.cs.b(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0047, code lost:
        r1 = com.crittercism.internal.cs.c(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004b, code lost:
        r7.setWebViewClient(new com.crittercism.internal.cr(r1, r0.b, r0.c, r0.d));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0061, code lost:
        if (r7.getSettings().getJavaScriptEnabled() == false) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0063, code lost:
        r7.addJavascriptInterface(new com.crittercism.webview.CritterJSInterface(r0.a), "_crttr");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0070, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0071, code lost:
        com.crittercism.internal.cm.b((java.lang.Throwable) r7);
        com.crittercism.internal.cm.a("Failed to find WebViewClient. WebView will not be instrumented.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0079, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007a, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007b, code lost:
        com.crittercism.internal.cm.b(r7.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0082, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(android.webkit.WebView r7) {
        /*
            r6 = this;
            android.os.Looper r0 = android.os.Looper.myLooper()
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            if (r0 == r1) goto L_0x0010
            java.lang.String r7 = "Crittercism.instrumentWebView(WebView) must be called on the main UI thread"
            com.crittercism.internal.cm.a(r7)
            return
        L_0x0010:
            java.util.Set<android.webkit.WebView> r0 = r6.G
            monitor-enter(r0)
            java.util.Set<android.webkit.WebView> r1 = r6.G     // Catch:{ all -> 0x0083 }
            boolean r1 = r1.contains(r7)     // Catch:{ all -> 0x0083 }
            if (r1 == 0) goto L_0x001d
            monitor-exit(r0)     // Catch:{ all -> 0x0083 }
            return
        L_0x001d:
            java.util.Set<android.webkit.WebView> r1 = r6.G     // Catch:{ all -> 0x0083 }
            r1.add(r7)     // Catch:{ all -> 0x0083 }
            monitor-exit(r0)     // Catch:{ all -> 0x0083 }
            com.crittercism.internal.ct r0 = new com.crittercism.internal.ct
            com.crittercism.internal.d r1 = r6.l
            android.app.Application r2 = r6.a
            r0.<init>(r6, r1, r2)
            com.crittercism.internal.cs r1 = new com.crittercism.internal.cs     // Catch:{ bk -> 0x007a }
            r1.<init>()     // Catch:{ bk -> 0x007a }
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ bk -> 0x0070 }
            r2 = 15
            if (r1 > r2) goto L_0x003c
            android.webkit.WebViewClient r1 = com.crittercism.internal.cs.a(r7)     // Catch:{ bk -> 0x0070 }
            goto L_0x004b
        L_0x003c:
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ bk -> 0x0070 }
            r2 = 18
            if (r1 > r2) goto L_0x0047
            android.webkit.WebViewClient r1 = com.crittercism.internal.cs.b(r7)     // Catch:{ bk -> 0x0070 }
            goto L_0x004b
        L_0x0047:
            android.webkit.WebViewClient r1 = com.crittercism.internal.cs.c(r7)     // Catch:{ bk -> 0x0070 }
        L_0x004b:
            com.crittercism.internal.cr r2 = new com.crittercism.internal.cr
            com.crittercism.internal.d r3 = r0.b
            com.crittercism.internal.c r4 = r0.c
            java.lang.String r5 = r0.d
            r2.<init>(r1, r3, r4, r5)
            r7.setWebViewClient(r2)
            android.webkit.WebSettings r1 = r7.getSettings()
            boolean r1 = r1.getJavaScriptEnabled()
            if (r1 == 0) goto L_0x006f
            com.crittercism.webview.CritterJSInterface r1 = new com.crittercism.webview.CritterJSInterface
            com.crittercism.internal.am r0 = r0.a
            r1.<init>(r0)
            java.lang.String r0 = "_crttr"
            r7.addJavascriptInterface(r1, r0)
        L_0x006f:
            return
        L_0x0070:
            r7 = move-exception
            com.crittercism.internal.cm.b(r7)
            java.lang.String r7 = "Failed to find WebViewClient. WebView will not be instrumented."
            com.crittercism.internal.cm.a(r7)
            return
        L_0x007a:
            r7 = move-exception
            java.lang.String r7 = r7.getMessage()
            com.crittercism.internal.cm.b(r7)
            return
        L_0x0083:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0083 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crittercism.internal.am.a(android.webkit.WebView):void");
    }

    public final synchronized void a(final Throwable th) {
        if (th == null) {
            cm.b("Calling logHandledException with a null java.lang.Throwable. Nothing will be reported to Crittercism");
            return;
        }
        final long id = Thread.currentThread().getId();
        this.j.execute(new Runnable() {
            public final void run() {
                if (((Boolean) am.this.k.a(ap.r)).booleanValue()) {
                    aq aqVar = new aq(th, am.this.q, id);
                    if (((Boolean) am.this.k.a(ap.aq)).booleanValue()) {
                        String str = aqVar.d;
                        String str2 = aqVar.e;
                        HashMap hashMap = new HashMap();
                        String str3 = ResponseConstants.NAME;
                        if (str == null) {
                            str = "";
                        }
                        hashMap.put(str3, str);
                        String str4 = ResponseConstants.REASON;
                        if (str2 == null) {
                            str2 = "";
                        }
                        hashMap.put(str4, str2);
                        am.this.e.a(new at(com.crittercism.internal.at.b.g, new JSONObject(hashMap)));
                    }
                    aqVar.a(am.this.e);
                    aqVar.m = ((Float) am.this.k.a(ap.v)).floatValue();
                    am.this.b.a(aqVar);
                }
            }
        });
    }

    public final void a(final at atVar) {
        this.j.execute(new Runnable() {
            public final void run() {
                am.this.e.a(atVar);
            }
        });
    }

    public final void a(ap apVar) {
        ap apVar2 = apVar;
        if (this.z.a == null) {
            cm.d("unable to initialize reporters");
            return;
        }
        boolean a2 = new cg(this.a).a();
        cd cdVar = new cd(this.D, this.j, this.i, this.z, this.s, new com.crittercism.internal.au.a(this.q), "CONFIG", apVar2, ap.H, ap.J, ap.I);
        ap apVar3 = apVar2;
        bw bwVar = new bw(this.D.b, this.a, this.q, apVar3, this.z, bo.a(this.q, this.s, this.j, apVar2, ap.G));
        cdVar.d = bwVar;
        this.g.add(cdVar);
        if (((Boolean) apVar2.a(ap.G)).booleanValue()) {
            this.s.a(new au(this.q));
        }
        cd cdVar2 = new cd(this.D, this.j, this.i, this.z, this.t, new com.crittercism.internal.ax.a(this.q), "DH-REGION", apVar2, ap.P, ap.R, ap.Q);
        cdVar2.d = new by(apVar2, this.D, bo.b(this.q, this.t, this.j, apVar2, ap.O));
        this.g.add(cdVar2);
        if (((Boolean) apVar2.a(ap.O)).booleanValue()) {
            this.t.a(new ax(this.q));
        }
        cd cdVar3 = new cd(this.D, this.j, this.i, this.z, this.u, new com.crittercism.internal.aw.a(this.q), "DH-CONFIG", apVar2, ap.L, ap.N, ap.M);
        cdVar3.d = new bx(apVar2, bo.c(this.q, this.u, this.j, apVar2, ap.K));
        this.g.add(cdVar3);
        if (((Boolean) apVar2.a(ap.K)).booleanValue()) {
            this.u.a(new aw(this.q));
        }
        ap apVar4 = apVar2;
        cd cdVar4 = new cd(this.D, this.j, this.i, this.z, this.v, new com.crittercism.internal.ar.c(this.q), "EVENTS", apVar4, ap.Z, ap.ab, ap.aa);
        this.g.add(cdVar4);
        cd cdVar5 = new cd(this.D, this.j, this.i, this.z, this.w, new com.crittercism.internal.ar.b(this.q), "DH-APPLOADS", apVar4, ap.ad, ap.af, ap.ae);
        this.g.add(cdVar5);
        List<cd> list = this.g;
        boolean z2 = a2;
        cd cdVar6 = r1;
        cd cdVar7 = new cd(this.D, this.j, this.i, this.z, this.b, new com.crittercism.internal.aq.b(this.q, "exceptions", "/android_v2/handle_exceptions"), "EXCEPTIONS", apVar2, ap.s, ap.u, ap.t);
        list.add(cdVar6);
        ap apVar5 = apVar2;
        cd cdVar8 = new cd(this.D, this.j, this.i, this.z, this.d, new com.crittercism.internal.aq.b(this.q, "crashes", "/android_v2/handle_crashes"), "CRASHES", apVar5, ap.n, ap.o, ap.p);
        this.h = cdVar8;
        this.g.add(this.h);
        List<cd> list2 = this.g;
        cd cdVar9 = new cd(this.D, this.j, this.i, this.z, this.c, new com.crittercism.internal.bf.a(this.q), "NDK", apVar5, ap.x, ap.z, ap.y);
        list2.add(cdVar9);
        List<cd> list3 = this.g;
        cd cdVar10 = new cd(this.D, this.j, this.i, this.z, this.f, new com.crittercism.internal.bd.a(this.q), "METADATA", apVar5, ap.C, ap.E, ap.D);
        list3.add(cdVar10);
        List<cd> list4 = this.g;
        cd cdVar11 = new cd(this.D, this.j, this.i, this.z, this.x, new com.crittercism.internal.b.a(this.q), "APM", apVar5, ap.d, ap.f, ap.e);
        list4.add(cdVar11);
        List<cd> list5 = this.g;
        cd cdVar12 = new cd(this.D, this.j, this.i, this.z, this.y, new f(this.q, this.e), "USERFLOWS", apVar5, ap.ai, ap.ak, ap.aj);
        list5.add(cdVar12);
        ConnectivityManager connectivityManager = null;
        if (ao.a((Context) this.a, "android.permission.ACCESS_NETWORK_STATE")) {
            connectivityManager = (ConnectivityManager) this.a.getSystemService("connectivity");
        }
        for (cd cdVar13 : this.g) {
            cdVar13.j = connectivityManager;
            if (cdVar13.j != null && VERSION.SDK_INT >= 21) {
                Builder builder = new Builder();
                builder.addCapability(12);
                if (!cdVar13.i) {
                    builder.addTransportType(1);
                }
                NetworkRequest build = builder.build();
                cdVar13.k = new NetworkCallback() {
                    public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                    }

                    public final void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                    }

                    public final void onLost(Network network) {
                    }

                    public final void onAvailable(Network network) {
                        if (cd.this.e) {
                            cd.this.b();
                        }
                    }

                    public final void onLosing(Network network, int i) {
                        StringBuilder sb = new StringBuilder("onLosing: ");
                        sb.append(network);
                        cm.d(sb.toString());
                    }
                };
                connectivityManager.registerNetworkCallback(build, (NetworkCallback) cdVar13.k);
            }
            cdVar13.i = this.C.allowsCellularAccess();
            cdVar13.a(z2);
        }
    }

    public final boolean a() {
        try {
            return ((Boolean) this.j.submit(new Callable<Boolean>() {
                public final /* synthetic */ Object call() {
                    return Boolean.valueOf(new cg(am.this.a).a());
                }
            }).get()).booleanValue();
        } catch (InterruptedException e2) {
            cm.b((Throwable) e2);
            return false;
        } catch (ExecutionException e3) {
            cm.b((Throwable) e3);
            return false;
        }
    }

    public final boolean b() {
        try {
            return ((Boolean) this.j.submit(new Callable<Boolean>() {
                public final /* synthetic */ Object call() {
                    return Boolean.valueOf(ch.a != null);
                }
            }).get()).booleanValue();
        } catch (InterruptedException e2) {
            cm.b((Throwable) e2);
            return false;
        } catch (ExecutionException e3) {
            cm.b((Throwable) e3);
            return false;
        }
    }

    public final void a(final CrittercismCallback<CrashData> crittercismCallback) {
        this.j.execute(new Runnable() {
            public final void run() {
                CrashData crashData = ch.a;
                if (crashData != null) {
                    crashData = crashData.copy();
                }
                am.this.i.execute(new c(crittercismCallback, crashData));
            }
        });
    }

    public final void a(final JSONObject jSONObject) {
        this.j.execute(new Runnable() {
            public final void run() {
                if (((Boolean) am.this.k.a(ap.B)).booleanValue()) {
                    ((be) am.this.f).a(jSONObject);
                }
            }
        });
    }

    public final void a(String str) {
        ck ckVar = this.n;
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (ckVar.a) {
            ckVar.a.remove(str);
            if (ckVar.a.size() >= 50) {
                StringBuilder sb = new StringBuilder("Aborting beginUserflow(");
                sb.append(str);
                sb.append("). Maximum number of userflows exceeded.");
                cm.b(sb.toString());
                return;
            }
            long longValue = ((Long) ckVar.d.a(ap.a(str, ((Long) ckVar.d.a(ap.ar)).longValue()))).longValue();
            com.crittercism.internal.cj.a aVar = new com.crittercism.internal.cj.a();
            aVar.a = str;
            aVar.b = currentTimeMillis;
            aVar.c = -1;
            aVar.d = longValue;
            ckVar.a.put(str, aVar.a());
            StringBuilder sb2 = new StringBuilder("Added userflow: ");
            sb2.append(str);
            cm.d(sb2.toString());
        }
    }

    public final void b(String str) {
        this.n.a(str, System.currentTimeMillis());
    }

    public final void c(String str) {
        ck ckVar = this.n;
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (ckVar.a) {
            cj cjVar = (cj) ckVar.a.remove(str);
            if (cjVar == null) {
                StringBuilder sb = new StringBuilder("failUserflow(");
                sb.append(str);
                sb.append("): no such userflow");
                cm.b(sb.toString());
                return;
            }
            cjVar.a(com.crittercism.internal.cj.d.e, currentTimeMillis);
            ckVar.b.submit(new Runnable(cjVar) {
                final /* synthetic */ cj a;

                {
                    this.a = r2;
                }

                public final void run() {
                    if (((Boolean) ck.this.d.a(ap.ah)).booleanValue()) {
                        this.a.j = ((Float) ck.this.d.a(ap.al)).floatValue();
                        ck.this.c.a(this.a);
                    }
                }
            });
        }
    }

    public final void d(String str) {
        ck ckVar = this.n;
        synchronized (ckVar.a) {
            ckVar.a.remove(str);
        }
    }

    public final void a(String str, int i2) {
        ck ckVar = this.n;
        synchronized (ckVar.a) {
            cj cjVar = (cj) ckVar.a.get(str);
            if (cjVar == null) {
                StringBuilder sb = new StringBuilder("setUserflowValue(");
                sb.append(str);
                sb.append("): no such userflow");
                cm.b(sb.toString());
                return;
            }
            cjVar.c = i2;
        }
    }

    public final int e(String str) {
        return this.n.a(str);
    }
}
