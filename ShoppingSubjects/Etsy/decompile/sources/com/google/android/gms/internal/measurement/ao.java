package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.measurement.AppMeasurement.a;
import com.google.android.gms.measurement.AppMeasurement.d;
import com.google.android.gms.measurement.AppMeasurement.e;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.math3.geometry.VectorFormat;

public final class ao extends cp {
    private static final AtomicReference<String[]> a = new AtomicReference<>();
    private static final AtomicReference<String[]> b = new AtomicReference<>();
    private static final AtomicReference<String[]> c = new AtomicReference<>();

    ao(bu buVar) {
        super(buVar);
    }

    @Nullable
    private final String a(zzeu zzeu) {
        if (zzeu == null) {
            return null;
        }
        return !g() ? zzeu.toString() : a(zzeu.zzin());
    }

    @Nullable
    private static String a(String str, String[] strArr, String[] strArr2, AtomicReference<String[]> atomicReference) {
        String str2;
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        Preconditions.checkNotNull(atomicReference);
        Preconditions.checkArgument(strArr.length == strArr2.length);
        for (int i = 0; i < strArr.length; i++) {
            if (fg.b(str, strArr[i])) {
                synchronized (atomicReference) {
                    String[] strArr3 = (String[]) atomicReference.get();
                    if (strArr3 == null) {
                        strArr3 = new String[strArr2.length];
                        atomicReference.set(strArr3);
                    }
                    if (strArr3[i] == null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(strArr2[i]);
                        sb.append("(");
                        sb.append(strArr[i]);
                        sb.append(")");
                        strArr3[i] = sb.toString();
                    }
                    str2 = strArr3[i];
                }
                return str2;
            }
        }
        return str;
    }

    private final boolean g() {
        return this.q.r().a(3);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (!g()) {
            return bundle.toString();
        }
        StringBuilder sb = new StringBuilder();
        for (String str : bundle.keySet()) {
            sb.append(sb.length() != 0 ? ", " : "Bundle[{");
            sb.append(b(str));
            sb.append("=");
            sb.append(bundle.get(str));
        }
        sb.append("}]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String a(ah ahVar) {
        if (ahVar == null) {
            return null;
        }
        if (!g()) {
            return ahVar.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Event{appId='");
        sb.append(ahVar.a);
        sb.append("', name='");
        sb.append(a(ahVar.b));
        sb.append("', params=");
        sb.append(a(ahVar.e));
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String a(zzex zzex) {
        if (zzex == null) {
            return null;
        }
        if (!g()) {
            return zzex.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("origin=");
        sb.append(zzex.origin);
        sb.append(",name=");
        sb.append(a(zzex.name));
        sb.append(",params=");
        sb.append(a(zzex.zzahg));
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String a(String str) {
        if (str == null) {
            return null;
        }
        return !g() ? str : a(str, a.b, a.a, a);
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String b(String str) {
        if (str == null) {
            return null;
        }
        return !g() ? str : a(str, d.b, d.a, b);
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String c(String str) {
        if (str == null) {
            return null;
        }
        if (!g()) {
            return str;
        }
        if (!str.startsWith("_exp_")) {
            return a(str, e.b, e.a, c);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("experiment_id");
        sb.append("(");
        sb.append(str);
        sb.append(")");
        return sb.toString();
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public final /* bridge */ /* synthetic */ void d() {
        super.d();
    }

    /* access modifiers changed from: protected */
    public final boolean e() {
        return false;
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
