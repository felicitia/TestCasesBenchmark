package com.onfido.c.a;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.onfido.c.a.a.b;
import com.onfido.c.a.a.c;
import com.onfido.c.a.a.d;
import com.onfido.c.a.a.e;
import com.onfido.c.a.a.f;
import com.onfido.c.a.a.g;
import com.onfido.c.a.a.h;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class a {
    static final Handler a = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown handler message received: ");
            sb.append(message.what);
            throw new AssertionError(sb.toString());
        }
    };
    static final List<String> b = new ArrayList(1);
    static volatile a c;
    static final n d = new n();
    private final c A;
    private List<com.onfido.c.a.a.e.a> B;
    private Map<String, e<?>> C;
    final ExecutorService e;
    final r f;
    final k g;
    final a h;
    final b i;
    final String j;
    final e k;
    final d l;
    final g m;
    final ActivityLifecycleCallbacks n;
    m o;
    final String p;
    final int q;
    final long r;
    final Map<String, Boolean> s = new ConcurrentHashMap();
    volatile boolean t;
    private final Application u;
    private final List<j> v;
    private final f w;
    private final a x;
    private final CountDownLatch y;
    private final ExecutorService z;

    /* renamed from: com.onfido.c.a.a$a reason: collision with other inner class name */
    public static class C0009a {
        private final Application a;
        private String b;
        private boolean c = true;
        private int d = 20;
        private long e = 30000;
        private k f;
        private String g;
        private b h;
        private ExecutorService i;
        private ExecutorService j;
        private f k;
        private final List<com.onfido.c.a.a.e.a> l = new ArrayList();
        private List<j> m;
        private boolean n = false;
        private boolean o = false;
        private boolean p = false;
        private g q;

        public C0009a(Context context, String str) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            } else if (!com.onfido.c.a.b.b.a(context, "android.permission.INTERNET")) {
                throw new IllegalArgumentException("INTERNET permission is required.");
            } else {
                this.a = (Application) context.getApplicationContext();
                if (this.a == null) {
                    throw new IllegalArgumentException("Application context must not be null.");
                } else if (com.onfido.c.a.b.b.a((CharSequence) str)) {
                    throw new IllegalArgumentException("writeKey must not be null or empty.");
                } else {
                    this.b = str;
                }
            }
        }

        public C0009a a(f fVar) {
            if (fVar == null) {
                throw new IllegalArgumentException("ConnectionFactory must not be null.");
            }
            this.k = fVar;
            return this;
        }

        /* JADX INFO: finally extract failed */
        public a a() {
            if (com.onfido.c.a.b.b.a((CharSequence) this.g)) {
                this.g = this.b;
            }
            synchronized (a.b) {
                try {
                    if (a.b.contains(this.g)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Duplicate analytics client created with tag: ");
                        sb.append(this.g);
                        sb.append(". If you want to use multiple Analytics clients, use a different writeKey or set a tag via the builder during construction.");
                        throw new IllegalStateException(sb.toString());
                    }
                    a.b.add(this.g);
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            if (this.f == null) {
                this.f = new k();
            }
            if (this.h == null) {
                this.h = b.NONE;
            }
            if (this.i == null) {
                this.i = new com.onfido.c.a.b.b.a();
            }
            if (this.k == null) {
                this.k = new f();
            }
            if (this.q == null) {
                this.q = g.a();
            }
            r rVar = new r();
            d dVar = d.a;
            e eVar = new e(this.b, this.k);
            a aVar = new a(this.a, dVar, this.g);
            c cVar = new c(com.onfido.c.a.b.b.d(this.a, this.g), "opt-out", false);
            a aVar2 = new a(this.a, dVar, this.g);
            if (!aVar2.b() || aVar2.a() == null) {
                aVar2.a(s.a());
            }
            f a2 = f.a(this.h);
            b a3 = b.a((Context) this.a, (s) aVar2.a(), this.c);
            CountDownLatch countDownLatch = new CountDownLatch(1);
            a3.a((Context) this.a, countDownLatch, a2);
            ArrayList arrayList = new ArrayList(this.l.size() + 1);
            arrayList.add(q.a);
            arrayList.addAll(this.l);
            List a4 = com.onfido.c.a.b.b.a(this.m);
            ExecutorService executorService = this.j;
            if (executorService == null) {
                executorService = Executors.newSingleThreadExecutor();
            }
            ExecutorService executorService2 = executorService;
            Application application = this.a;
            ExecutorService executorService3 = this.i;
            k kVar = this.f;
            String str = this.g;
            List unmodifiableList = Collections.unmodifiableList(arrayList);
            String str2 = this.b;
            c cVar2 = cVar;
            int i2 = this.d;
            d dVar2 = dVar;
            a aVar3 = aVar;
            long j2 = this.e;
            String str3 = str2;
            CountDownLatch countDownLatch2 = countDownLatch;
            long j3 = j2;
            a aVar4 = new a(application, executorService3, rVar, aVar2, a3, kVar, a2, str, unmodifiableList, eVar, dVar2, aVar3, str3, i2, j3, executorService2, this.n, countDownLatch2, this.o, this.p, cVar2, this.q, a4);
            return aVar4;
        }
    }

    public enum b {
        NONE,
        INFO,
        DEBUG,
        BASIC,
        VERBOSE
    }

    a(Application application, ExecutorService executorService, r rVar, a aVar, b bVar, k kVar, f fVar, String str, List<com.onfido.c.a.a.e.a> list, e eVar, d dVar, a aVar2, String str2, int i2, long j2, ExecutorService executorService2, boolean z2, CountDownLatch countDownLatch, boolean z3, boolean z4, c cVar, g gVar, List<j> list2) {
        Application application2 = application;
        f fVar2 = fVar;
        String str3 = str;
        final ExecutorService executorService3 = executorService2;
        this.u = application2;
        this.e = executorService;
        this.f = rVar;
        this.h = aVar;
        this.i = bVar;
        this.g = kVar;
        this.w = fVar2;
        this.j = str3;
        this.k = eVar;
        this.l = dVar;
        this.x = aVar2;
        this.p = str2;
        this.q = i2;
        this.r = j2;
        this.y = countDownLatch;
        this.A = cVar;
        this.B = list;
        this.z = executorService3;
        this.m = gVar;
        this.v = list2;
        i();
        executorService3.submit(new Runnable() {
            public void run() {
                a.this.o = a.this.e();
                if (com.onfido.c.a.b.b.a((Map) a.this.o)) {
                    a.this.o = m.a(new t().b("integrations", (Object) new t().b("Segment.io", (Object) new t().b("apiKey", (Object) a.this.p))));
                }
                a.a.post(new Runnable() {
                    public void run() {
                        a.this.a(a.this.o);
                    }
                });
            }
        });
        fVar2.c("Created analytics client for project with tag:%s.", str3);
        final boolean z5 = z2;
        final boolean z6 = z4;
        final boolean z7 = z3;
        AnonymousClass6 r0 = new ActivityLifecycleCallbacks() {
            final AtomicBoolean a = new AtomicBoolean(false);

            public void onActivityCreated(Activity activity, Bundle bundle) {
                if (!this.a.getAndSet(true) && z5) {
                    a.this.b();
                    if (z6) {
                        executorService3.submit(new Runnable() {
                            public void run() {
                                a.this.a();
                            }
                        });
                    }
                }
                a.this.a(i.a(activity, bundle));
            }

            public void onActivityDestroyed(Activity activity) {
                a.this.a(i.e(activity));
            }

            public void onActivityPaused(Activity activity) {
                a.this.a(i.c(activity));
            }

            public void onActivityResumed(Activity activity) {
                a.this.a(i.b(activity));
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                a.this.a(i.b(activity, bundle));
            }

            public void onActivityStarted(Activity activity) {
                if (z7) {
                    a.this.a(activity);
                }
                a.this.a(i.a(activity));
            }

            public void onActivityStopped(Activity activity) {
                a.this.a(i.d(activity));
            }
        };
        this.n = r0;
        application2.registerActivityLifecycleCallbacks(this.n);
    }

    static PackageInfo a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Package not found: ");
            sb.append(context.getPackageName());
            throw new AssertionError(sb.toString());
        }
    }

    private void f() {
        try {
            this.y.await(15, TimeUnit.SECONDS);
        } catch (InterruptedException e2) {
            this.w.a(e2, "Thread interrupted while waiting for advertising ID.", new Object[0]);
        }
        if (this.y.getCount() == 1) {
            this.w.c("Advertising ID may not be collected because the API did not respond within 15 seconds.", new Object[0]);
        }
    }

    private void g() {
        if (this.t) {
            throw new IllegalStateException("Cannot enqueue messages after client is shutdown.");
        }
    }

    private m h() {
        try {
            m mVar = (m) this.e.submit(new Callable<m>() {
                /* renamed from: a */
                public m call() {
                    a aVar;
                    Throwable th;
                    try {
                        aVar = a.this.k.c();
                        try {
                            m a2 = m.a(a.this.l.a((Reader) com.onfido.c.a.b.b.a(aVar.b)));
                            com.onfido.c.a.b.b.a((Closeable) aVar);
                            return a2;
                        } catch (Throwable th2) {
                            th = th2;
                            com.onfido.c.a.b.b.a((Closeable) aVar);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        aVar = null;
                        th = th4;
                        com.onfido.c.a.b.b.a((Closeable) aVar);
                        throw th;
                    }
                }
            }).get();
            this.x.a(mVar);
            return mVar;
        } catch (InterruptedException e2) {
            this.w.a(e2, "Thread interrupted while fetching settings.", new Object[0]);
            return null;
        } catch (ExecutionException e3) {
            this.w.a(e3, "Unable to fetch settings. Retrying in %s ms.", Long.valueOf(60000));
            return null;
        }
    }

    private void i() {
        SharedPreferences d2 = com.onfido.c.a.b.b.d(this.u, this.j);
        c cVar = new c(d2, "namespaceSharedPreferences", true);
        if (cVar.a()) {
            com.onfido.c.a.b.b.a(this.u.getSharedPreferences("analytics-android", 0), d2);
            cVar.a(false);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        c cVar = new c(com.onfido.c.a.b.b.d(this.u, this.j), "tracked_attribution", false);
        if (!cVar.a()) {
            f();
            a aVar = null;
            try {
                a aVar2 = this.k.b();
                try {
                    this.l.a((Map<?, ?>) this.i, (Writer) new BufferedWriter(new OutputStreamWriter(aVar2.c)));
                    a("Install Attributed", new n(this.l.a((Reader) com.onfido.c.a.b.b.a(com.onfido.c.a.b.b.a(aVar2.a)))));
                    cVar.a(true);
                    com.onfido.c.a.b.b.a((Closeable) aVar2);
                } catch (IOException e2) {
                    e = e2;
                    aVar = aVar2;
                    try {
                        this.w.a(e, "Unable to track attribution information. Retrying on next launch.", new Object[0]);
                        com.onfido.c.a.b.b.a((Closeable) aVar);
                    } catch (Throwable th) {
                        th = th;
                        aVar2 = aVar;
                        com.onfido.c.a.b.b.a((Closeable) aVar2);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    com.onfido.c.a.b.b.a((Closeable) aVar2);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                this.w.a(e, "Unable to track attribution information. Retrying on next launch.", new Object[0]);
                com.onfido.c.a.b.b.a((Closeable) aVar);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Activity activity) {
        PackageManager packageManager = activity.getPackageManager();
        try {
            a((String) null, packageManager.getActivityInfo(activity.getComponentName(), 128).loadLabel(packageManager).toString());
        } catch (NameNotFoundException e2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Activity Not Found: ");
            sb.append(e2.toString());
            throw new AssertionError(sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(com.onfido.c.a.a.b.a<?, ?> aVar, k kVar) {
        f();
        b a2 = this.i.a();
        aVar.a((Map<String, ?>) a2);
        aVar.a(a2.b().d());
        aVar.b(kVar.a());
        String c2 = a2.b().c();
        if (!com.onfido.c.a.b.b.a((CharSequence) c2)) {
            aVar.b(c2);
        }
        a(aVar.b());
    }

    /* access modifiers changed from: 0000 */
    public void a(b bVar) {
        if (!this.A.a()) {
            this.w.a("Created payload %s.", bVar);
            new p(0, bVar, this.v, this).a(bVar);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(final i iVar) {
        if (!this.t) {
            this.z.submit(new Runnable() {
                public void run() {
                    a.a.post(new Runnable() {
                        public void run() {
                            a.this.b(iVar);
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(m mVar) {
        t d2 = mVar.d();
        this.C = new LinkedHashMap(this.B.size());
        for (int i2 = 0; i2 < this.B.size(); i2++) {
            com.onfido.c.a.a.e.a aVar = (com.onfido.c.a.a.e.a) this.B.get(i2);
            String a2 = aVar.a();
            t a3 = d2.a(a2);
            if (com.onfido.c.a.b.b.a((Map) a3)) {
                this.w.c("Integration %s is not enabled.", a2);
            } else {
                e a4 = aVar.a(a3, this);
                if (a4 == null) {
                    this.w.b("Factory %s couldn't create integration.", aVar);
                } else {
                    this.C.put(a2, a4);
                    this.s.put(a2, Boolean.valueOf(false));
                }
            }
        }
        this.B = null;
    }

    public void a(String str, n nVar) {
        a(str, nVar, null);
    }

    public void a(final String str, final n nVar, final k kVar) {
        g();
        if (com.onfido.c.a.b.b.a((CharSequence) str)) {
            throw new IllegalArgumentException("event must not be null or empty.");
        }
        this.z.submit(new Runnable() {
            public void run() {
                a.this.a((com.onfido.c.a.a.b.a<?, ?>) new com.onfido.c.a.a.h.a().c(str).c((Map<String, ?>) nVar == null ? a.d : nVar), kVar == null ? a.this.g : kVar);
            }
        });
    }

    public void a(String str, String str2) {
        a(str, str2, null, null);
    }

    public void a(String str, String str2, n nVar, k kVar) {
        g();
        if (!com.onfido.c.a.b.b.a((CharSequence) str) || !com.onfido.c.a.b.b.a((CharSequence) str2)) {
            ExecutorService executorService = this.z;
            final k kVar2 = kVar;
            final n nVar2 = nVar;
            final String str3 = str2;
            final String str4 = str;
            AnonymousClass9 r1 = new Runnable() {
                public void run() {
                    a.this.a((com.onfido.c.a.a.b.a<?, ?>) new com.onfido.c.a.a.g.a().c(str3).d(str4).c((Map<String, ?>) nVar2 == null ? a.d : nVar2), kVar2 == null ? a.this.g : kVar2);
                }
            };
            executorService.submit(r1);
            return;
        }
        throw new IllegalArgumentException("either category or name must be provided.");
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        PackageInfo a2 = a((Context) this.u);
        String str = a2.versionName;
        int i2 = a2.versionCode;
        SharedPreferences d2 = com.onfido.c.a.b.b.d(this.u, this.j);
        String string = d2.getString("version", null);
        int i3 = d2.getInt("build", -1);
        if (i3 == -1) {
            a("Application Installed", new n().b("version", str).b("build", Integer.valueOf(i2)));
        } else if (i2 != i3) {
            a("Application Updated", new n().b("version", str).b("build", Integer.valueOf(i2)).b("previous_version", string).b("previous_build", Integer.valueOf(i3)));
        }
        a("Application Opened", new n().b("version", str).b("build", Integer.valueOf(i2)));
        Editor edit = d2.edit();
        edit.putString("version", str);
        edit.putInt("build", i2);
        edit.apply();
    }

    /* access modifiers changed from: 0000 */
    public void b(b bVar) {
        final i iVar;
        this.w.a("Running payload %s.", bVar);
        switch (bVar.b()) {
            case identify:
                iVar = i.a((d) bVar);
                break;
            case alias:
                iVar = i.a((com.onfido.c.a.a.a) bVar);
                break;
            case group:
                iVar = i.a((c) bVar);
                break;
            case track:
                iVar = i.a((h) bVar);
                break;
            case screen:
                iVar = i.a((g) bVar);
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("unknown type ");
                sb.append(bVar.b());
                throw new AssertionError(sb.toString());
        }
        a.post(new Runnable() {
            public void run() {
                a.this.b(iVar);
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void b(i iVar) {
        for (Entry entry : this.C.entrySet()) {
            String str = (String) entry.getKey();
            long nanoTime = System.nanoTime();
            iVar.a(str, (e) entry.getValue(), this.o);
            long nanoTime2 = System.nanoTime() - nanoTime;
            this.f.a(str, TimeUnit.NANOSECONDS.toMillis(nanoTime2));
            this.w.c("Ran %s on integration %s in %d ns.", iVar, str, Long.valueOf(nanoTime2));
        }
    }

    public Application c() {
        return this.u;
    }

    public f d() {
        return this.w;
    }

    /* access modifiers changed from: 0000 */
    public m e() {
        m mVar = (m) this.x.a();
        if (com.onfido.c.a.b.b.a((Map) mVar)) {
            return h();
        }
        if (mVar.a() + 86400000 > System.currentTimeMillis()) {
            return mVar;
        }
        m h2 = h();
        return com.onfido.c.a.b.b.a((Map) h2) ? mVar : h2;
    }
}
