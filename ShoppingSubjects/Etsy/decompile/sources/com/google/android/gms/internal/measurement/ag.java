package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.util.Clock;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class ag extends cp {
    private long a;
    private String b;
    private Boolean c;

    ag(bu buVar) {
        super(buVar);
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    public final boolean a(Context context) {
        if (this.c == null) {
            u();
            this.c = Boolean.valueOf(false);
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    packageManager.getPackageInfo("com.google.android.gms", 128);
                    this.c = Boolean.valueOf(true);
                }
            } catch (NameNotFoundException unused) {
            }
        }
        return this.c.booleanValue();
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public final /* bridge */ /* synthetic */ void d() {
        super.d();
    }

    /* access modifiers changed from: protected */
    public final boolean e() {
        Calendar instance = Calendar.getInstance();
        this.a = TimeUnit.MINUTES.convert((long) (instance.get(15) + instance.get(16)), TimeUnit.MILLISECONDS);
        Locale locale = Locale.getDefault();
        String lowerCase = locale.getLanguage().toLowerCase(Locale.ENGLISH);
        String lowerCase2 = locale.getCountry().toLowerCase(Locale.ENGLISH);
        StringBuilder sb = new StringBuilder(1 + String.valueOf(lowerCase).length() + String.valueOf(lowerCase2).length());
        sb.append(lowerCase);
        sb.append("-");
        sb.append(lowerCase2);
        this.b = sb.toString();
        return false;
    }

    public final String g() {
        z();
        return this.b;
    }

    public final long g_() {
        z();
        return this.a;
    }

    public final /* bridge */ /* synthetic */ ag l() {
        return super.l();
    }

    public final /* bridge */ /* synthetic */ Clock m() {
        return super.m();
    }

    public final /* bridge */ /* synthetic */ Context n() {
        return super.n();
    }

    public final /* bridge */ /* synthetic */ ao o() {
        return super.o();
    }

    public final /* bridge */ /* synthetic */ fg p() {
        return super.p();
    }

    public final /* bridge */ /* synthetic */ bq q() {
        return super.q();
    }

    public final /* bridge */ /* synthetic */ aq r() {
        return super.r();
    }

    public final /* bridge */ /* synthetic */ bb s() {
        return super.s();
    }

    public final /* bridge */ /* synthetic */ w t() {
        return super.t();
    }

    public final /* bridge */ /* synthetic */ v u() {
        return super.u();
    }
}
