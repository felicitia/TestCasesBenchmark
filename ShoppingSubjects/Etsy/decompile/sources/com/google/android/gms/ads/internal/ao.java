package com.google.android.gms.ads.internal;

import android.os.Build.VERSION;
import com.google.android.gms.ads.internal.overlay.a;
import com.google.android.gms.ads.internal.overlay.h;
import com.google.android.gms.ads.internal.overlay.l;
import com.google.android.gms.ads.internal.overlay.m;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.ads.aga;
import com.google.android.gms.internal.ads.agx;
import com.google.android.gms.internal.ads.agy;
import com.google.android.gms.internal.ads.ahg;
import com.google.android.gms.internal.ads.akq;
import com.google.android.gms.internal.ads.anx;
import com.google.android.gms.internal.ads.aoq;
import com.google.android.gms.internal.ads.aqh;
import com.google.android.gms.internal.ads.arh;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.bv;
import com.google.android.gms.internal.ads.du;
import com.google.android.gms.internal.ads.fq;
import com.google.android.gms.internal.ads.gf;
import com.google.android.gms.internal.ads.go;
import com.google.android.gms.internal.ads.hd;
import com.google.android.gms.internal.ads.hj;
import com.google.android.gms.internal.ads.ho;
import com.google.android.gms.internal.ads.hp;
import com.google.android.gms.internal.ads.hq;
import com.google.android.gms.internal.ads.hr;
import com.google.android.gms.internal.ads.hs;
import com.google.android.gms.internal.ads.hu;
import com.google.android.gms.internal.ads.hv;
import com.google.android.gms.internal.ads.ie;
import com.google.android.gms.internal.ads.jb;
import com.google.android.gms.internal.ads.jc;
import com.google.android.gms.internal.ads.jl;
import com.google.android.gms.internal.ads.lf;
import com.google.android.gms.internal.ads.lm;
import com.google.android.gms.internal.ads.my;
import com.google.android.gms.internal.ads.nt;
import com.google.android.gms.internal.ads.o;
import com.google.android.gms.internal.ads.y;

@bu
public final class ao {
    private static final Object a = new Object();
    private static ao b;
    private final w A;
    private final o B;
    private final ahg C;
    private final fq D;
    private final my E;
    private final lm F;
    private final aoq G;
    private final hv H;
    private final jl I;
    private final go J;
    private final a c = new a();
    private final bv d = new bv();
    private final h e = new h();
    private final y f = new y();
    private final hd g = new hd();
    private final nt h = new nt();
    private final hj i;
    private final aga j;
    private final gf k;
    private final agx l;
    private final agy m;
    private final Clock n;
    private final d o;
    private final akq p;
    private final ie q;
    private final du r;
    private final lf s;
    private final anx t;
    private final aqh u;
    private final jb v;
    private final l w;
    private final m x;
    private final arh y;
    private final jc z;

    static {
        ao aoVar = new ao();
        synchronized (a) {
            b = aoVar;
        }
    }

    protected ao() {
        int i2 = VERSION.SDK_INT;
        hj hjVar = i2 >= 21 ? new hu() : i2 >= 19 ? new hs() : i2 >= 18 ? new hq() : i2 >= 17 ? new hp() : i2 >= 16 ? new hr() : new ho();
        this.i = hjVar;
        this.j = new aga();
        this.k = new gf();
        this.J = new go();
        this.l = new agx();
        this.m = new agy();
        this.n = DefaultClock.getInstance();
        this.o = new d();
        this.p = new akq();
        this.q = new ie();
        this.r = new du();
        this.G = new aoq();
        this.s = new lf();
        this.t = new anx();
        this.u = new aqh();
        this.v = new jb();
        this.w = new l();
        this.x = new m();
        this.y = new arh();
        this.z = new jc();
        this.A = new w();
        this.B = new o();
        this.C = new ahg();
        this.D = new fq();
        this.E = new my();
        this.F = new lm();
        this.H = new hv();
        this.I = new jl();
    }

    public static lm A() {
        return F().F;
    }

    public static fq B() {
        return F().D;
    }

    public static aoq C() {
        return F().G;
    }

    public static hv D() {
        return F().H;
    }

    public static jl E() {
        return F().I;
    }

    private static ao F() {
        ao aoVar;
        synchronized (a) {
            aoVar = b;
        }
        return aoVar;
    }

    public static bv a() {
        return F().d;
    }

    public static a b() {
        return F().c;
    }

    public static h c() {
        return F().e;
    }

    public static y d() {
        return F().f;
    }

    public static hd e() {
        return F().g;
    }

    public static nt f() {
        return F().h;
    }

    public static hj g() {
        return F().i;
    }

    public static aga h() {
        return F().j;
    }

    public static gf i() {
        return F().k;
    }

    public static go j() {
        return F().J;
    }

    public static agy k() {
        return F().m;
    }

    public static Clock l() {
        return F().n;
    }

    public static d m() {
        return F().o;
    }

    public static akq n() {
        return F().p;
    }

    public static ie o() {
        return F().q;
    }

    public static du p() {
        return F().r;
    }

    public static lf q() {
        return F().s;
    }

    public static anx r() {
        return F().t;
    }

    public static aqh s() {
        return F().u;
    }

    public static jb t() {
        return F().v;
    }

    public static o u() {
        return F().B;
    }

    public static l v() {
        return F().w;
    }

    public static m w() {
        return F().x;
    }

    public static arh x() {
        return F().y;
    }

    public static jc y() {
        return F().z;
    }

    public static my z() {
        return F().E;
    }
}
