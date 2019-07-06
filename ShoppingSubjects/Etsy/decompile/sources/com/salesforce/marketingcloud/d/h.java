package com.salesforce.marketingcloud.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.etsy.android.lib.models.ResponseConstants;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.d.a.a;
import com.salesforce.marketingcloud.d.a.c;
import com.salesforce.marketingcloud.d.a.d;
import com.salesforce.marketingcloud.d.a.e;
import com.salesforce.marketingcloud.d.a.f;
import com.salesforce.marketingcloud.d.a.g;
import com.salesforce.marketingcloud.d.a.i;
import com.salesforce.marketingcloud.j;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@RestrictTo({Scope.LIBRARY})
public class h extends b {
    private static final String d = j.a(h.class);
    private final c e;
    private final SharedPreferences f;
    private final com.salesforce.marketingcloud.d.a.j g;
    private a h;
    private f i;
    private g j;
    private com.salesforce.marketingcloud.d.a.h k;
    private i l;
    private e m;
    private c n;
    private d o;

    public h(@NonNull Context context, com.salesforce.marketingcloud.e.a aVar, @NonNull String str, @NonNull String str2) {
        super(context, aVar, str, str2);
        this.g = new com.salesforce.marketingcloud.d.a.j(context, aVar, this.a);
        this.g.b();
        this.e = new c.a(context, aVar, this.a);
        this.f = context.getSharedPreferences(a(this.a), 0);
        if (this.g.a()) {
            this.e.a();
            this.f.edit().clear().apply();
        }
    }

    private void b(com.salesforce.marketingcloud.e.a aVar) {
        this.f.edit().putString(ResponseConstants.CREATE_DATE, aVar.a(String.valueOf(System.currentTimeMillis()))).apply();
    }

    private void o() {
        d().a();
        e().edit().clear().apply();
        this.g.c();
    }

    public com.salesforce.marketingcloud.e.a a() {
        return this.c;
    }

    public final void a(InitializationStatus.a aVar) {
        if (!a(this.f)) {
            boolean contains = this.f.contains(ResponseConstants.CREATE_DATE);
            aVar.b(contains);
            if (contains) {
                try {
                    o();
                } catch (Exception e2) {
                    aVar.a((Throwable) e2);
                    aVar.c(true);
                    j.c(d, e2, "Failed to recover from encryption change.", new Object[0]);
                    return;
                }
            }
            b(this.c);
        }
        int i2 = this.f.getInt("ETStorage.version", -1);
        if (!(i2 == 0 || i2 == 1)) {
            if (1 < i2) {
                b(this.b, i2, 1);
            } else {
                a(this.b, i2, 1);
            }
            this.f.edit().putInt("ETStorage.version", 1).apply();
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(SharedPreferences sharedPreferences) {
        String string = sharedPreferences.getString(ResponseConstants.CREATE_DATE, null);
        if (string != null) {
            try {
                this.c.b(string);
                return true;
            } catch (UnsupportedEncodingException | GeneralSecurityException e2) {
                j.c(d, e2, "Failed to verify existing encryption key", new Object[0]);
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final Context b() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public void b(Context context, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public SQLiteOpenHelper c() {
        return this.g;
    }

    public c d() {
        return this.e;
    }

    public SharedPreferences e() {
        return this.f;
    }

    public final void f() {
        this.g.close();
    }

    public a g() {
        if (this.h == null) {
            this.h = new a(this.g.b());
        }
        return this.h;
    }

    public g h() {
        if (this.m == null) {
            this.m = new e(this.g.b());
        }
        return this.m;
    }

    public i i() {
        if (this.i == null) {
            this.i = new f(this.g.b());
        }
        return this.i;
    }

    public k j() {
        if (this.j == null) {
            this.j = new g(this.g.b());
        }
        return this.j;
    }

    public j k() {
        if (this.k == null) {
            this.k = new com.salesforce.marketingcloud.d.a.h(this.g.b());
        }
        return this.k;
    }

    public l l() {
        if (this.l == null) {
            this.l = new i(this.g.b(), this.b);
        }
        return this.l;
    }

    public f m() {
        if (this.n == null) {
            this.n = new c(this.g.b());
        }
        return this.n;
    }

    public e n() {
        if (this.o == null) {
            this.o = new d(this.g.b());
        }
        return this.o;
    }
}
