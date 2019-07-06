package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import java.util.Arrays;
import org.json.JSONObject;

/* compiled from: JobInvocation */
final class n implements o {
    @NonNull
    private final String a;
    @NonNull
    private final String b;
    @NonNull
    private final q c;
    private final boolean d;
    private final int e;
    @NonNull
    private final int[] f;
    @NonNull
    private final Bundle g;
    private final t h;
    private final boolean i;
    private final v j;

    /* compiled from: JobInvocation */
    static final class a {
        /* access modifiers changed from: private */
        @NonNull
        public String a;
        /* access modifiers changed from: private */
        @NonNull
        public String b;
        /* access modifiers changed from: private */
        @NonNull
        public q c;
        /* access modifiers changed from: private */
        public boolean d;
        /* access modifiers changed from: private */
        public int e;
        /* access modifiers changed from: private */
        @NonNull
        public int[] f;
        /* access modifiers changed from: private */
        @NonNull
        public final Bundle g = new Bundle();
        /* access modifiers changed from: private */
        public t h;
        /* access modifiers changed from: private */
        public boolean i;
        /* access modifiers changed from: private */
        public v j;

        a() {
        }

        /* access modifiers changed from: 0000 */
        public n a() {
            if (this.a != null && this.b != null && this.c != null) {
                return new n(this);
            }
            throw new IllegalArgumentException("Required fields were not populated.");
        }

        public a a(@NonNull String str) {
            this.a = str;
            return this;
        }

        public a b(@NonNull String str) {
            this.b = str;
            return this;
        }

        public a a(@NonNull q qVar) {
            this.c = qVar;
            return this;
        }

        public a a(boolean z) {
            this.d = z;
            return this;
        }

        public a a(int i2) {
            this.e = i2;
            return this;
        }

        public a a(@NonNull int[] iArr) {
            this.f = iArr;
            return this;
        }

        public a a(Bundle bundle) {
            if (bundle != null) {
                this.g.putAll(bundle);
            }
            return this;
        }

        public a a(t tVar) {
            this.h = tVar;
            return this;
        }

        public a b(boolean z) {
            this.i = z;
            return this;
        }

        public a a(v vVar) {
            this.j = vVar;
            return this;
        }
    }

    private n(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.h = aVar.h;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.i = aVar.i;
        this.j = aVar.j;
    }

    @NonNull
    public String i() {
        return this.b;
    }

    @NonNull
    public String e() {
        return this.a;
    }

    @NonNull
    public q f() {
        return this.c;
    }

    public int g() {
        return this.e;
    }

    public boolean h() {
        return this.d;
    }

    @NonNull
    public int[] a() {
        return this.f;
    }

    @NonNull
    public Bundle b() {
        return this.g;
    }

    @NonNull
    public t c() {
        return this.h;
    }

    public boolean d() {
        return this.i;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        n nVar = (n) obj;
        if (!this.a.equals(nVar.a) || !this.b.equals(nVar.b)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (31 * this.a.hashCode()) + this.b.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JobInvocation{tag='");
        sb.append(JSONObject.quote(this.a));
        sb.append('\'');
        sb.append(", service='");
        sb.append(this.b);
        sb.append('\'');
        sb.append(", trigger=");
        sb.append(this.c);
        sb.append(", recurring=");
        sb.append(this.d);
        sb.append(", lifetime=");
        sb.append(this.e);
        sb.append(", constraints=");
        sb.append(Arrays.toString(this.f));
        sb.append(", extras=");
        sb.append(this.g);
        sb.append(", retryStrategy=");
        sb.append(this.h);
        sb.append(", replaceCurrent=");
        sb.append(this.i);
        sb.append(", triggerReason=");
        sb.append(this.j);
        sb.append('}');
        return sb.toString();
    }
}
