package com.salesforce.marketingcloud;

import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.salesforce.marketingcloud.notifications.c.C0173c;

public abstract class b {

    public static abstract class a {
        @NonNull
        public abstract a a(@DrawableRes int i);

        @NonNull
        public abstract a a(com.salesforce.marketingcloud.notifications.c.a aVar);

        @NonNull
        public abstract a a(C0173c cVar);

        @NonNull
        public abstract a a(String str);

        @NonNull
        public abstract a a(boolean z);

        /* access modifiers changed from: 0000 */
        public abstract b a();

        @NonNull
        public abstract a b(String str);

        @NonNull
        public abstract a b(boolean z);

        @NonNull
        public final b b() {
            b a = a();
            if (!a.b().toLowerCase().matches("[0-9a-f]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")) {
                throw new IllegalArgumentException("The applicationId is not a valid UUID.");
            } else if (a.c().length() != 24) {
                throw new IllegalArgumentException("The accessToken must be 24 characters.");
            } else if (a.d() == null || !a.d().trim().isEmpty()) {
                return a;
            } else {
                throw new IllegalArgumentException("The senderId cannot be empty.");
            }
        }

        @NonNull
        public abstract a c(@NonNull String str);

        @NonNull
        public abstract a c(boolean z);

        @NonNull
        public abstract a d(@NonNull String str);

        @NonNull
        public abstract a d(boolean z);

        @Deprecated
        @NonNull
        public a e(boolean z) {
            return f(z);
        }

        @NonNull
        public abstract a f(boolean z);
    }

    @NonNull
    public static a a() {
        return new a().a(false).b(false).e(false).c(false).d(false).a(0);
    }

    /* access modifiers changed from: 0000 */
    public final boolean a(b bVar) {
        return bVar == null || !b().equals(bVar.b()) || !c().equals(bVar.c());
    }

    @NonNull
    public abstract String b();

    @NonNull
    public abstract String c();

    @Nullable
    public abstract String d();

    public abstract boolean e();

    public abstract boolean f();

    public abstract boolean g();

    public abstract boolean h();

    public abstract boolean i();

    @DrawableRes
    public abstract int j();

    @Nullable
    public abstract String k();

    @Nullable
    public abstract Class<? extends Activity> l();

    @Nullable
    public abstract Class<? extends Activity> m();

    @Nullable
    public abstract Class<? extends Activity> n();

    @Nullable
    public abstract com.salesforce.marketingcloud.notifications.c.a o();

    @Nullable
    public abstract C0173c p();

    @Nullable
    public abstract com.salesforce.marketingcloud.notifications.c.b q();

    @Nullable
    public abstract String r();
}
