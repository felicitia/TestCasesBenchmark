package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* compiled from: Job */
public final class k implements o {
    private final String a;
    private final String b;
    private final q c;
    private final t d;
    private final int e;
    private final boolean f;
    private final int[] g;
    private final boolean h;
    private final Bundle i;

    /* compiled from: Job */
    public static final class a implements o {
        private final ValidationEnforcer a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public Bundle c;
        /* access modifiers changed from: private */
        public String d;
        /* access modifiers changed from: private */
        public q e = u.a;
        /* access modifiers changed from: private */
        public int f = 1;
        /* access modifiers changed from: private */
        public int[] g;
        /* access modifiers changed from: private */
        public t h = t.a;
        /* access modifiers changed from: private */
        public boolean i = false;
        /* access modifiers changed from: private */
        public boolean j = false;

        a(ValidationEnforcer validationEnforcer) {
            this.a = validationEnforcer;
        }

        a(ValidationEnforcer validationEnforcer, o oVar) {
            this.a = validationEnforcer;
            this.d = oVar.e();
            this.b = oVar.i();
            this.e = oVar.f();
            this.j = oVar.h();
            this.f = oVar.g();
            this.g = oVar.a();
            this.c = oVar.b();
            this.h = oVar.c();
        }

        public a a(boolean z) {
            this.i = z;
            return this;
        }

        public k j() {
            this.a.b(this);
            return new k(this);
        }

        @NonNull
        public String i() {
            return this.b;
        }

        public a a(Class<? extends JobService> cls) {
            this.b = cls == null ? null : cls.getName();
            return this;
        }

        @NonNull
        public String e() {
            return this.d;
        }

        public a a(String str) {
            this.d = str;
            return this;
        }

        @NonNull
        public q f() {
            return this.e;
        }

        public a a(q qVar) {
            this.e = qVar;
            return this;
        }

        public int g() {
            return this.f;
        }

        public boolean h() {
            return this.j;
        }

        public a b(boolean z) {
            this.j = z;
            return this;
        }

        public int[] a() {
            return this.g == null ? new int[0] : this.g;
        }

        public a a(int... iArr) {
            this.g = iArr;
            return this;
        }

        @Nullable
        public Bundle b() {
            return this.c;
        }

        @NonNull
        public t c() {
            return this.h;
        }

        public a a(t tVar) {
            this.h = tVar;
            return this;
        }

        public boolean d() {
            return this.i;
        }
    }

    private k(a aVar) {
        this.a = aVar.b;
        this.i = aVar.c == null ? null : new Bundle(aVar.c);
        this.b = aVar.d;
        this.c = aVar.e;
        this.d = aVar.h;
        this.e = aVar.f;
        this.f = aVar.j;
        this.g = aVar.g != null ? aVar.g : new int[0];
        this.h = aVar.i;
    }

    @NonNull
    public int[] a() {
        return this.g;
    }

    @Nullable
    public Bundle b() {
        return this.i;
    }

    @NonNull
    public t c() {
        return this.d;
    }

    public boolean d() {
        return this.h;
    }

    @NonNull
    public String e() {
        return this.b;
    }

    @NonNull
    public q f() {
        return this.c;
    }

    public int g() {
        return this.e;
    }

    public boolean h() {
        return this.f;
    }

    @NonNull
    public String i() {
        return this.a;
    }
}
