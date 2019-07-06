package com.salesforce.marketingcloud.c;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import java.util.List;
import java.util.Map;

public abstract class g implements Parcelable {

    public static abstract class a {
        public abstract a a(int i);

        public abstract a a(long j);

        public abstract a a(String str);

        public abstract a a(Map<String, List<String>> map);

        public abstract g a();

        public abstract a b(long j);

        public abstract a b(String str);
    }

    public static g a(String str, int i) {
        return g().b(str).a(i).a(0).b(0).a();
    }

    public static a g() {
        return new C0162a().a(0).b(0);
    }

    @Nullable
    public abstract String a();

    @Nullable
    public abstract String b();

    public abstract int c();

    public abstract long d();

    public abstract long e();

    @Nullable
    public abstract Map<String, List<String>> f();

    public final boolean h() {
        return c() >= 200 && c() < 300;
    }

    public long i() {
        return e() - d();
    }
}
