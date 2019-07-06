package com.google.android.gms.internal.measurement;

import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;

final class t {
    private long A;
    private long B;
    private final bu a;
    private final String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private long g;
    private long h;
    private long i;
    private String j;
    private long k;
    private String l;
    private long m;
    private long n;
    private boolean o;
    private long p;
    private boolean q;
    private boolean r;
    private long s;
    private long t;
    private long u;
    private long v;
    private long w;
    private long x;
    private String y;
    private boolean z;

    @WorkerThread
    t(bu buVar, String str) {
        Preconditions.checkNotNull(buVar);
        Preconditions.checkNotEmpty(str);
        this.a = buVar;
        this.b = str;
        this.a.q().d();
    }

    @WorkerThread
    public final long A() {
        this.a.q().d();
        return this.p;
    }

    @WorkerThread
    public final boolean B() {
        this.a.q().d();
        return this.q;
    }

    @WorkerThread
    public final boolean C() {
        this.a.q().d();
        return this.r;
    }

    @WorkerThread
    public final void a() {
        this.a.q().d();
        this.z = false;
    }

    @WorkerThread
    public final void a(long j2) {
        this.a.q().d();
        this.z |= this.h != j2;
        this.h = j2;
    }

    @WorkerThread
    public final void a(String str) {
        this.a.q().d();
        this.z |= !fg.b(this.c, str);
        this.c = str;
    }

    @WorkerThread
    public final void a(boolean z2) {
        this.a.q().d();
        this.z |= this.o != z2;
        this.o = z2;
    }

    @WorkerThread
    public final String b() {
        this.a.q().d();
        return this.b;
    }

    @WorkerThread
    public final void b(long j2) {
        this.a.q().d();
        this.z |= this.i != j2;
        this.i = j2;
    }

    @WorkerThread
    public final void b(String str) {
        this.a.q().d();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.z |= !fg.b(this.d, str);
        this.d = str;
    }

    @WorkerThread
    public final void b(boolean z2) {
        this.a.q().d();
        this.z = this.q != z2;
        this.q = z2;
    }

    @WorkerThread
    public final String c() {
        this.a.q().d();
        return this.c;
    }

    @WorkerThread
    public final void c(long j2) {
        this.a.q().d();
        this.z |= this.k != j2;
        this.k = j2;
    }

    @WorkerThread
    public final void c(String str) {
        this.a.q().d();
        this.z |= !fg.b(this.e, str);
        this.e = str;
    }

    @WorkerThread
    public final void c(boolean z2) {
        this.a.q().d();
        this.z = this.r != z2;
        this.r = z2;
    }

    @WorkerThread
    public final String d() {
        this.a.q().d();
        return this.d;
    }

    @WorkerThread
    public final void d(long j2) {
        this.a.q().d();
        this.z |= this.m != j2;
        this.m = j2;
    }

    @WorkerThread
    public final void d(String str) {
        this.a.q().d();
        this.z |= !fg.b(this.f, str);
        this.f = str;
    }

    @WorkerThread
    public final String e() {
        this.a.q().d();
        return this.e;
    }

    @WorkerThread
    public final void e(long j2) {
        this.a.q().d();
        this.z |= this.n != j2;
        this.n = j2;
    }

    @WorkerThread
    public final void e(String str) {
        this.a.q().d();
        this.z |= !fg.b(this.j, str);
        this.j = str;
    }

    @WorkerThread
    public final String f() {
        this.a.q().d();
        return this.f;
    }

    @WorkerThread
    public final void f(long j2) {
        boolean z2 = false;
        Preconditions.checkArgument(j2 >= 0);
        this.a.q().d();
        boolean z3 = this.z;
        if (this.g != j2) {
            z2 = true;
        }
        this.z = z2 | z3;
        this.g = j2;
    }

    @WorkerThread
    public final void f(String str) {
        this.a.q().d();
        this.z |= !fg.b(this.l, str);
        this.l = str;
    }

    @WorkerThread
    public final long g() {
        this.a.q().d();
        return this.h;
    }

    @WorkerThread
    public final void g(long j2) {
        this.a.q().d();
        this.z |= this.A != j2;
        this.A = j2;
    }

    @WorkerThread
    public final void g(String str) {
        this.a.q().d();
        this.z |= !fg.b(this.y, str);
        this.y = str;
    }

    @WorkerThread
    public final long h() {
        this.a.q().d();
        return this.i;
    }

    @WorkerThread
    public final void h(long j2) {
        this.a.q().d();
        this.z |= this.B != j2;
        this.B = j2;
    }

    @WorkerThread
    public final String i() {
        this.a.q().d();
        return this.j;
    }

    @WorkerThread
    public final void i(long j2) {
        this.a.q().d();
        this.z |= this.s != j2;
        this.s = j2;
    }

    @WorkerThread
    public final long j() {
        this.a.q().d();
        return this.k;
    }

    @WorkerThread
    public final void j(long j2) {
        this.a.q().d();
        this.z |= this.t != j2;
        this.t = j2;
    }

    @WorkerThread
    public final String k() {
        this.a.q().d();
        return this.l;
    }

    @WorkerThread
    public final void k(long j2) {
        this.a.q().d();
        this.z |= this.u != j2;
        this.u = j2;
    }

    @WorkerThread
    public final long l() {
        this.a.q().d();
        return this.m;
    }

    @WorkerThread
    public final void l(long j2) {
        this.a.q().d();
        this.z |= this.v != j2;
        this.v = j2;
    }

    @WorkerThread
    public final long m() {
        this.a.q().d();
        return this.n;
    }

    @WorkerThread
    public final void m(long j2) {
        this.a.q().d();
        this.z |= this.x != j2;
        this.x = j2;
    }

    @WorkerThread
    public final void n(long j2) {
        this.a.q().d();
        this.z |= this.w != j2;
        this.w = j2;
    }

    @WorkerThread
    public final boolean n() {
        this.a.q().d();
        return this.o;
    }

    @WorkerThread
    public final long o() {
        this.a.q().d();
        return this.g;
    }

    @WorkerThread
    public final void o(long j2) {
        this.a.q().d();
        this.z |= this.p != j2;
        this.p = j2;
    }

    @WorkerThread
    public final long p() {
        this.a.q().d();
        return this.A;
    }

    @WorkerThread
    public final long q() {
        this.a.q().d();
        return this.B;
    }

    @WorkerThread
    public final void r() {
        this.a.q().d();
        long j2 = this.g + 1;
        if (j2 > 2147483647L) {
            this.a.r().i().a("Bundle index overflow. appId", aq.a(this.b));
            j2 = 0;
        }
        this.z = true;
        this.g = j2;
    }

    @WorkerThread
    public final long s() {
        this.a.q().d();
        return this.s;
    }

    @WorkerThread
    public final long t() {
        this.a.q().d();
        return this.t;
    }

    @WorkerThread
    public final long u() {
        this.a.q().d();
        return this.u;
    }

    @WorkerThread
    public final long v() {
        this.a.q().d();
        return this.v;
    }

    @WorkerThread
    public final long w() {
        this.a.q().d();
        return this.x;
    }

    @WorkerThread
    public final long x() {
        this.a.q().d();
        return this.w;
    }

    @WorkerThread
    public final String y() {
        this.a.q().d();
        return this.y;
    }

    @WorkerThread
    public final String z() {
        this.a.q().d();
        String str = this.y;
        g((String) null);
        return str;
    }
}
