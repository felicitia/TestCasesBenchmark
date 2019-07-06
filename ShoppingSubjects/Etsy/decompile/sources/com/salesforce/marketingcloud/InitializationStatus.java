package com.salesforce.marketingcloud;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class InitializationStatus {

    public enum Status {
        SUCCESS,
        COMPLETED_WITH_DEGRADED_FUNCTIONALITY,
        FAILED
    }

    @RestrictTo({Scope.LIBRARY})
    public static abstract class a {
        private List<String> a;
        private String b = null;

        public abstract a a(int i);

        /* access modifiers changed from: 0000 */
        public abstract a a(Status status);

        /* access modifiers changed from: 0000 */
        public final a a(g gVar) {
            if (gVar != null) {
                if (this.a == null) {
                    this.a = new ArrayList();
                }
                this.a.add(gVar.b());
            }
            return this;
        }

        /* access modifiers changed from: 0000 */
        @Nullable
        public abstract a a(String str);

        public abstract a a(Throwable th);

        /* access modifiers changed from: 0000 */
        public abstract a a(List<String> list);

        public abstract a a(boolean z);

        /* access modifiers changed from: 0000 */
        public abstract Throwable a();

        public a b(@NonNull String str) {
            if (!TextUtils.isEmpty(this.b)) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.b);
                sb.append("\n");
                sb.append(str);
                str = sb.toString();
            }
            this.b = str;
            return this;
        }

        public abstract a b(boolean z);

        @VisibleForTesting(otherwise = 3)
        public abstract boolean b();

        public abstract a c(boolean z);

        /* access modifiers changed from: 0000 */
        public abstract boolean c();

        public abstract a d(boolean z);

        /* access modifiers changed from: 0000 */
        public abstract boolean d();

        public abstract a e(boolean z);

        /* access modifiers changed from: 0000 */
        public abstract boolean e();

        public abstract a f(boolean z);

        @VisibleForTesting
        public abstract boolean f();

        public final boolean g() {
            return a() == null;
        }

        /* access modifiers changed from: 0000 */
        public abstract InitializationStatus h();

        public final InitializationStatus i() {
            Status status = g() ? (b() || e() || c() || f() || d()) ? Status.COMPLETED_WITH_DEGRADED_FUNCTIONALITY : Status.SUCCESS : Status.FAILED;
            a(status);
            if (!TextUtils.isEmpty(this.b)) {
                a(this.b);
            }
            a(this.a == null ? Collections.emptyList() : Collections.unmodifiableList(this.a));
            return h();
        }
    }

    static a a() {
        return new a().a(false).a(-1).e(false).b(false).d(false).f(false).c(false);
    }

    static InitializationStatus b() {
        return a().a((Throwable) new IllegalStateException("Amazon devices are not supported")).i();
    }

    @NonNull
    public abstract Status c();

    @Nullable
    public abstract Throwable d();

    public abstract boolean e();

    public abstract int f();

    @Nullable
    public abstract String g();

    public abstract boolean h();

    public abstract boolean i();

    public abstract boolean j();

    public abstract boolean k();

    public abstract boolean l();

    @NonNull
    public abstract List<String> m();

    public final boolean n() {
        return c() != Status.FAILED;
    }
}
