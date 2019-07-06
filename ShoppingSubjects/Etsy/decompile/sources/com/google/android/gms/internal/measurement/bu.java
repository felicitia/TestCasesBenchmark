package com.google.android.gms.internal.measurement;

import android.app.Application;
import android.content.Context;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.concurrent.atomic.AtomicReference;

public class bu implements cq {
    private static volatile bu a;
    private int A;
    private final long B;
    private final Context b;
    private final String c;
    private final v d;
    private final w e;
    private final bb f;
    private final aq g;
    private final bq h;
    private final eo i;
    private final AppMeasurement j;
    private final FirebaseAnalytics k;
    private final fg l;
    private final ao m;
    private final Clock n;
    private final dn o;
    private final cs p;
    private final n q;
    private am r;
    private dq s;
    private ag t;
    private al u;
    private bh v;
    private boolean w = false;
    private Boolean x;
    private long y;
    private int z;

    private bu(cr crVar) {
        String str;
        as asVar;
        Preconditions.checkNotNull(crVar);
        this.d = new v(crVar.a);
        ak.a(this.d);
        this.b = crVar.a;
        this.c = crVar.b;
        gi.a(this.b);
        this.n = DefaultClock.getInstance();
        this.B = this.n.currentTimeMillis();
        this.e = new w(this);
        bb bbVar = new bb(this);
        bbVar.A();
        this.f = bbVar;
        aq aqVar = new aq(this);
        aqVar.A();
        this.g = aqVar;
        fg fgVar = new fg(this);
        fgVar.A();
        this.l = fgVar;
        ao aoVar = new ao(this);
        aoVar.A();
        this.m = aoVar;
        this.q = new n(this);
        dn dnVar = new dn(this);
        dnVar.x();
        this.o = dnVar;
        cs csVar = new cs(this);
        csVar.x();
        this.p = csVar;
        this.j = new AppMeasurement(this);
        this.k = new FirebaseAnalytics(this);
        eo eoVar = new eo(this);
        eoVar.x();
        this.i = eoVar;
        bq bqVar = new bq(this);
        bqVar.A();
        this.h = bqVar;
        v vVar = this.d;
        if (this.b.getApplicationContext() instanceof Application) {
            cs h2 = h();
            if (h2.n().getApplicationContext() instanceof Application) {
                Application application = (Application) h2.n().getApplicationContext();
                if (h2.a == null) {
                    h2.a = new dl(h2, null);
                }
                application.unregisterActivityLifecycleCallbacks(h2.a);
                application.registerActivityLifecycleCallbacks(h2.a);
                asVar = h2.r().w();
                str = "Registered activity lifecycle callback";
            }
            this.h.a((Runnable) new bv(this, crVar));
        }
        asVar = r().i();
        str = "Application context is not an Application";
        asVar.a(str);
        this.h.a((Runnable) new bv(this, crVar));
    }

    private final void E() {
        if (!this.w) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }

    public static bu a(Context context, String str, String str2) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (a == null) {
            synchronized (bu.class) {
                if (a == null) {
                    a = new bu(new cr(context, null));
                }
            }
        }
        return a;
    }

    private static void a(co coVar) {
        if (coVar == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void a(cr crVar) {
        String str;
        as asVar;
        q().d();
        w.e();
        ag agVar = new ag(this);
        agVar.A();
        this.t = agVar;
        al alVar = new al(this);
        alVar.x();
        this.u = alVar;
        am amVar = new am(this);
        amVar.x();
        this.r = amVar;
        dq dqVar = new dq(this);
        dqVar.x();
        this.s = dqVar;
        this.l.B();
        this.f.B();
        this.v = new bh(this);
        this.u.y();
        r().k().a("App measurement is starting up, version", Long.valueOf(this.e.f()));
        v vVar = this.d;
        r().k().a("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        v vVar2 = this.d;
        String C = alVar.C();
        if (k().h(C)) {
            asVar = r().k();
            str = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
        } else {
            asVar = r().k();
            String str2 = "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ";
            String valueOf = String.valueOf(C);
            str = valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2);
        }
        asVar.a(str);
        r().v().a("Debug-level message logging enabled");
        if (this.z != this.A) {
            r().h_().a("Not all components initialized", Integer.valueOf(this.z), Integer.valueOf(this.A));
        }
        this.w = true;
    }

    private static void b(cp cpVar) {
        if (cpVar == null) {
            throw new IllegalStateException("Component not created");
        } else if (!cpVar.y()) {
            String valueOf = String.valueOf(cpVar.getClass());
            StringBuilder sb = new StringBuilder(27 + String.valueOf(valueOf).length());
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    private static void b(s sVar) {
        if (sVar == null) {
            throw new IllegalStateException("Component not created");
        } else if (!sVar.v()) {
            String valueOf = String.valueOf(sVar.getClass());
            StringBuilder sb = new StringBuilder(27 + String.valueOf(valueOf).length());
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public final void A() {
        v vVar = this.d;
    }

    /* access modifiers changed from: 0000 */
    public final void B() {
        v vVar = this.d;
        throw new IllegalStateException("Unexpected call on client side");
    }

    /* access modifiers changed from: 0000 */
    public final void C() {
        this.A++;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final boolean D() {
        E();
        q().d();
        if (this.x == null || this.y == 0 || (this.x != null && !this.x.booleanValue() && Math.abs(this.n.elapsedRealtime() - this.y) > 1000)) {
            this.y = this.n.elapsedRealtime();
            v vVar = this.d;
            boolean z2 = false;
            if (k().f("android.permission.INTERNET") && k().f("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.b).isCallerInstantApp() || this.e.x() || (bl.a(this.b) && fg.a(this.b, false)))) {
                z2 = true;
            }
            this.x = Boolean.valueOf(z2);
            if (this.x.booleanValue()) {
                this.x = Boolean.valueOf(k().e(w().D()));
            }
        }
        return this.x.booleanValue();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void a() {
        q().d();
        if (c().c.a() == 0) {
            c().c.a(this.n.currentTimeMillis());
        }
        if (Long.valueOf(c().h.a()).longValue() == 0) {
            r().w().a("Persisting first open", Long.valueOf(this.B));
            c().h.a(this.B);
        }
        if (D()) {
            v vVar = this.d;
            if (!TextUtils.isEmpty(w().D())) {
                String g2 = c().g();
                if (g2 == null) {
                    c().c(w().D());
                } else if (!g2.equals(w().D())) {
                    r().k().a("Rechecking which service to use due to a GMP App Id change");
                    c().j();
                    this.s.H();
                    this.s.F();
                    c().c(w().D());
                    c().h.a(this.B);
                    c().j.a(null);
                }
            }
            h().a(c().j.a());
            v vVar2 = this.d;
            if (!TextUtils.isEmpty(w().D())) {
                boolean y2 = y();
                if (!c().w() && !this.e.h()) {
                    c().d(!y2);
                }
                if (!this.e.k(w().C()) || y2) {
                    h().J();
                }
                t().a(new AtomicReference<>());
            }
        } else if (y()) {
            if (!k().f("android.permission.INTERNET")) {
                r().h_().a("App is missing INTERNET permission");
            }
            if (!k().f("android.permission.ACCESS_NETWORK_STATE")) {
                r().h_().a("App is missing ACCESS_NETWORK_STATE permission");
            }
            v vVar3 = this.d;
            if (!Wrappers.packageManager(this.b).isCallerInstantApp() && !this.e.x()) {
                if (!bl.a(this.b)) {
                    r().h_().a("AppMeasurementReceiver not registered/enabled");
                }
                if (!fg.a(this.b, false)) {
                    r().h_().a("AppMeasurementService not registered/enabled");
                }
            }
            r().h_().a("Uploading is not possible. App measurement disabled");
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(cp cpVar) {
        this.z++;
    }

    /* access modifiers changed from: 0000 */
    public final void a(s sVar) {
        this.z++;
    }

    public final w b() {
        return this.e;
    }

    public final bb c() {
        a((co) this.f);
        return this.f;
    }

    public final aq d() {
        if (this.g == null || !this.g.y()) {
            return null;
        }
        return this.g;
    }

    public final eo e() {
        b((s) this.i);
        return this.i;
    }

    public final bh f() {
        return this.v;
    }

    /* access modifiers changed from: 0000 */
    public final bq g() {
        return this.h;
    }

    public final cs h() {
        b((s) this.p);
        return this.p;
    }

    public final AppMeasurement i() {
        return this.j;
    }

    public final FirebaseAnalytics j() {
        return this.k;
    }

    public final fg k() {
        a((co) this.l);
        return this.l;
    }

    public final ao l() {
        a((co) this.m);
        return this.m;
    }

    public final Clock m() {
        return this.n;
    }

    public final Context n() {
        return this.b;
    }

    public final am o() {
        b((s) this.r);
        return this.r;
    }

    public final String p() {
        return this.c;
    }

    public final bq q() {
        b((cp) this.h);
        return this.h;
    }

    public final aq r() {
        b((cp) this.g);
        return this.g;
    }

    public final dn s() {
        b((s) this.o);
        return this.o;
    }

    public final dq t() {
        b((s) this.s);
        return this.s;
    }

    public final v u() {
        return this.d;
    }

    public final ag v() {
        b((cp) this.t);
        return this.t;
    }

    public final al w() {
        b((s) this.u);
        return this.u;
    }

    public final n x() {
        if (this.q != null) {
            return this.q;
        }
        throw new IllegalStateException("Component not created");
    }

    @WorkerThread
    public final boolean y() {
        q().d();
        E();
        boolean z2 = false;
        if (this.e.h()) {
            return false;
        }
        Boolean i2 = this.e.i();
        if (i2 != null) {
            z2 = i2.booleanValue();
        } else if (!GoogleServices.isMeasurementExplicitlyDisabled()) {
            z2 = true;
        }
        return c().c(z2);
    }

    /* access modifiers changed from: 0000 */
    public final long z() {
        Long valueOf = Long.valueOf(c().h.a());
        return valueOf.longValue() == 0 ? this.B : Math.min(this.B, valueOf.longValue());
    }
}
