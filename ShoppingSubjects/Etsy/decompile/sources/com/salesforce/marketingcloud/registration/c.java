package com.salesforce.marketingcloud.registration;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.e.d;
import com.salesforce.marketingcloud.e.g;
import com.salesforce.marketingcloud.e.h;
import com.salesforce.marketingcloud.j;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.json.JSONObject;

public abstract class c {
    private int a;

    @RestrictTo({Scope.LIBRARY})
    public static abstract class a {
        private int a;

        public abstract a a(int i);

        /* access modifiers changed from: 0000 */
        public final a a(@NonNull b bVar, @NonNull Context context, @NonNull String str) {
            b(str);
            j(bVar.b());
            a(g.b());
            a(TimeZone.getDefault().inDaylightTime(new Date()));
            k(Locale.getDefault().toString());
            h("Android");
            f(VERSION.RELEASE);
            i(String.format(Locale.ENGLISH, "%s %s", new Object[]{Build.MANUFACTURER, Build.MODEL}));
            d(h.a());
            e(d.a(context));
            return this;
        }

        public abstract a a(String str);

        public abstract a a(Map<String, String> map);

        public abstract a a(Set<String> set);

        public abstract a a(boolean z);

        /* access modifiers changed from: 0000 */
        public abstract c a();

        public abstract a b(String str);

        public abstract a b(boolean z);

        public final c b() {
            c a2 = a();
            a2.a(this.a);
            return a2;
        }

        public abstract a c(String str);

        public abstract a c(boolean z);

        public abstract a d(String str);

        public abstract a d(boolean z);

        public abstract a e(String str);

        public abstract a f(String str);

        public abstract a g(String str);

        public abstract a h(String str);

        public abstract a i(String str);

        public abstract a j(String str);

        public abstract a k(String str);
    }

    static c a(JSONObject jSONObject) {
        return f.b(jSONObject);
    }

    public static a s() {
        return new C0174a();
    }

    @Nullable
    public abstract String a();

    public final void a(int i) {
        this.a = i;
    }

    @NonNull
    public abstract String b();

    @Nullable
    public abstract String c();

    @NonNull
    public abstract String d();

    @NonNull
    public abstract String e();

    public abstract boolean f();

    public abstract boolean g();

    public abstract boolean h();

    @NonNull
    public abstract String i();

    public abstract boolean j();

    public abstract int k();

    @Nullable
    public abstract String l();

    @NonNull
    public abstract String m();

    @NonNull
    public abstract String n();

    @NonNull
    public abstract String o();

    @NonNull
    public abstract String p();

    @NonNull
    public abstract Set<String> q();

    @NonNull
    public abstract Map<String, String> r();

    public final int t() {
        return this.a;
    }

    public abstract JSONObject u();

    public String v() {
        try {
            return u().put("registrationDateUtc", g.a(new Date())).toString();
        } catch (Exception e) {
            j.c("Registration", e, "Unable to create registration request payload", new Object[0]);
            return null;
        }
    }
}
