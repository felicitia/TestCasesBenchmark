package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Looper;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.tasks.c;
import com.google.android.gms.tasks.f;
import com.google.android.gms.tasks.g;
import com.google.android.gms.tasks.i;
import com.google.firebase.a;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FirebaseInstanceId {
    static final Executor a = ac.a;
    private static final long b = TimeUnit.HOURS.toSeconds(8);
    private static p c;
    private static final Executor d = Executors.newCachedThreadPool();
    @VisibleForTesting
    private static ScheduledThreadPoolExecutor e;
    private static final Executor f;
    private final a g;
    private final g h;
    private IRpc i;
    private final j j;
    private final t k;
    private boolean l;
    private boolean m;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue());
        f = threadPoolExecutor;
    }

    FirebaseInstanceId(a aVar) {
        this(aVar, new g(aVar.a()));
    }

    private FirebaseInstanceId(a aVar, g gVar) {
        this.j = new j();
        this.l = false;
        if (g.a(aVar) == null) {
            throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
        }
        synchronized (FirebaseInstanceId.class) {
            if (c == null) {
                c = new p(aVar.a());
            }
        }
        this.g = aVar;
        this.h = gVar;
        if (this.i == null) {
            IRpc iRpc = (IRpc) aVar.a(IRpc.class);
            if (iRpc == null) {
                iRpc = new ad(aVar, gVar, f);
            }
            this.i = iRpc;
        }
        this.i = this.i;
        this.k = new t(c);
        this.m = m();
        if (i()) {
            j();
        }
    }

    public static FirebaseInstanceId a() {
        return getInstance(a.d());
    }

    private final <T> T a(f<T> fVar) throws IOException {
        try {
            return i.a(fVar, 30000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof IOException) {
                if ("INSTANCE_ID_RESET".equals(cause.getMessage())) {
                    g();
                }
                throw ((IOException) cause);
            } else if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else {
                throw new IOException(e2);
            }
        } catch (InterruptedException | TimeoutException unused) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
    }

    static void a(Runnable runnable, long j2) {
        synchronized (FirebaseInstanceId.class) {
            if (e == null) {
                e = new ScheduledThreadPoolExecutor(1);
            }
            e.schedule(runnable, j2, TimeUnit.SECONDS);
        }
    }

    private static String c(String str) {
        return (str.isEmpty() || str.equalsIgnoreCase(AppMeasurement.FCM_ORIGIN) || str.equalsIgnoreCase("gcm")) ? "*" : str;
    }

    static boolean f() {
        return Log.isLoggable("FirebaseInstanceId", 3) || (VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseInstanceId", 3));
    }

    @Keep
    public static synchronized FirebaseInstanceId getInstance(@NonNull a aVar) {
        FirebaseInstanceId firebaseInstanceId;
        synchronized (FirebaseInstanceId.class) {
            firebaseInstanceId = (FirebaseInstanceId) aVar.a(FirebaseInstanceId.class);
        }
        return firebaseInstanceId;
    }

    private final void j() {
        q d2 = d();
        if (d2 == null || d2.b(this.h.b()) || this.k.a()) {
            k();
        }
    }

    private final synchronized void k() {
        if (!this.l) {
            a(0);
        }
    }

    private static String l() {
        return g.a(c.b("").a());
    }

    private final boolean m() {
        Context a2 = this.g.a();
        SharedPreferences sharedPreferences = a2.getSharedPreferences("com.google.firebase.messaging", 0);
        if (sharedPreferences.contains("auto_init")) {
            return sharedPreferences.getBoolean("auto_init", true);
        }
        try {
            PackageManager packageManager = a2.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(a2.getPackageName(), 128);
                if (!(applicationInfo == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey("firebase_messaging_auto_init_enabled"))) {
                    return applicationInfo.metaData.getBoolean("firebase_messaging_auto_init_enabled");
                }
            }
        } catch (NameNotFoundException unused) {
        }
        return n();
    }

    private final boolean n() {
        try {
            Class.forName("com.google.firebase.messaging.FirebaseMessaging");
            return true;
        } catch (ClassNotFoundException unused) {
            Context a2 = this.g.a();
            Intent intent = new Intent("com.google.firebase.MESSAGING_EVENT");
            intent.setPackage(a2.getPackageName());
            ResolveInfo resolveService = a2.getPackageManager().resolveService(intent, 0);
            return (resolveService == null || resolveService.serviceInfo == null) ? false : true;
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ f a(String str, String str2, String str3) {
        return this.i.getToken(str, str2, str3);
    }

    @WorkerThread
    public String a(String str, String str2) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        String c2 = c(str2);
        g gVar = new g();
        Executor executor = d;
        z zVar = new z(this, str, str2, gVar, c2);
        executor.execute(zVar);
        return (String) a(gVar.a());
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void a(long j2) {
        r rVar = new r(this, this.h, this.k, Math.min(Math.max(30, j2 << 1), b));
        a((Runnable) rVar, j2);
        this.l = true;
    }

    /* access modifiers changed from: 0000 */
    public final void a(String str) throws IOException {
        q d2 = d();
        if (d2 == null || d2.b(this.h.b())) {
            throw new IOException("token not available");
        }
        a(this.i.subscribeToTopic(l(), d2.a, str));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(String str, String str2, g gVar, f fVar) {
        if (fVar.b()) {
            String str3 = (String) fVar.d();
            c.a("", str, str2, str3, this.h.b());
            gVar.a(str3);
            return;
        }
        gVar.a(fVar.e());
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(String str, String str2, g gVar, String str3) {
        q a2 = c.a("", str, str2);
        if (a2 == null || a2.b(this.h.b())) {
            this.j.a(str, str3, new aa(this, l(), str, str3)).a(d, (c<TResult>) new ab<TResult>(this, str, str3, gVar));
            return;
        }
        gVar.a(a2.a);
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void a(boolean z) {
        this.l = z;
    }

    /* access modifiers changed from: 0000 */
    public final a b() {
        return this.g;
    }

    /* access modifiers changed from: 0000 */
    public final void b(String str) throws IOException {
        q d2 = d();
        if (d2 == null || d2.b(this.h.b())) {
            throw new IOException("token not available");
        }
        a(this.i.unsubscribeFromTopic(l(), d2.a, str));
    }

    @WorkerThread
    public String c() {
        j();
        return l();
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final q d() {
        return c.a("", g.a(this.g), "*");
    }

    /* access modifiers changed from: 0000 */
    public final String e() throws IOException {
        return a(g.a(this.g), "*");
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void g() {
        c.b();
        if (i()) {
            k();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void h() {
        c.c("");
        k();
    }

    @VisibleForTesting
    public final synchronized boolean i() {
        return this.m;
    }
}
