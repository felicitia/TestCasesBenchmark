package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement.a;
import java.io.IOException;
import java.util.Map;

public final class bp extends ex implements y {
    @VisibleForTesting
    private static int b = 65535;
    @VisibleForTesting
    private static int c = 2;
    private final Map<String, Map<String, String>> d = new ArrayMap();
    private final Map<String, Map<String, Boolean>> e = new ArrayMap();
    private final Map<String, Map<String, Boolean>> f = new ArrayMap();
    private final Map<String, fq> g = new ArrayMap();
    private final Map<String, Map<String, Integer>> h = new ArrayMap();
    private final Map<String, String> i = new ArrayMap();

    bp(ey eyVar) {
        super(eyVar);
    }

    @WorkerThread
    private final fq a(String str, byte[] bArr) {
        if (bArr == null) {
            return new fq();
        }
        c a = c.a(bArr, 0, bArr.length);
        fq fqVar = new fq();
        try {
            fqVar.a(a);
            r().w().a("Parsed config. version, gmp_app_id", fqVar.c, fqVar.d);
            return fqVar;
        } catch (IOException e2) {
            r().i().a("Unable to merge remote config. appId", aq.a(str), e2);
            return new fq();
        }
    }

    private static Map<String, String> a(fq fqVar) {
        fr[] frVarArr;
        ArrayMap arrayMap = new ArrayMap();
        if (!(fqVar == null || fqVar.e == null)) {
            for (fr frVar : fqVar.e) {
                if (frVar != null) {
                    arrayMap.put(frVar.c, frVar.d);
                }
            }
        }
        return arrayMap;
    }

    private final void a(String str, fq fqVar) {
        fp[] fpVarArr;
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        if (!(fqVar == null || fqVar.f == null)) {
            for (fp fpVar : fqVar.f) {
                if (TextUtils.isEmpty(fpVar.c)) {
                    r().i().a("EventConfig contained null event name");
                } else {
                    String a = a.a(fpVar.c);
                    if (!TextUtils.isEmpty(a)) {
                        fpVar.c = a;
                    }
                    arrayMap.put(fpVar.c, fpVar.d);
                    arrayMap2.put(fpVar.c, fpVar.e);
                    if (fpVar.f != null) {
                        if (fpVar.f.intValue() < c || fpVar.f.intValue() > b) {
                            r().i().a("Invalid sampling rate. Event name, sample rate", fpVar.c, fpVar.f);
                        } else {
                            arrayMap3.put(fpVar.c, fpVar.f);
                        }
                    }
                }
            }
        }
        this.e.put(str, arrayMap);
        this.f.put(str, arrayMap2);
        this.h.put(str, arrayMap3);
    }

    @WorkerThread
    private final void g(String str) {
        I();
        d();
        Preconditions.checkNotEmpty(str);
        if (this.g.get(str) == null) {
            byte[] d2 = d_().d(str);
            if (d2 == null) {
                this.d.put(str, null);
                this.e.put(str, null);
                this.f.put(str, null);
                this.g.put(str, null);
                this.i.put(str, null);
                this.h.put(str, null);
                return;
            }
            fq a = a(str, d2);
            this.d.put(str, a(a));
            a(str, a);
            this.g.put(str, a);
            this.i.put(str, null);
        }
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final fq a(String str) {
        I();
        d();
        Preconditions.checkNotEmpty(str);
        g(str);
        return (fq) this.g.get(str);
    }

    @WorkerThread
    public final String a(String str, String str2) {
        d();
        g(str);
        Map map = (Map) this.d.get(str);
        if (map != null) {
            return (String) map.get(str2);
        }
        return null;
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final boolean a(String str, byte[] bArr, String str2) {
        I();
        d();
        Preconditions.checkNotEmpty(str);
        fq a = a(str, bArr);
        if (a == null) {
            return false;
        }
        a(str, a);
        this.g.put(str, a);
        this.i.put(str, str2);
        this.d.put(str, a(a));
        e_().a(str, a.g);
        try {
            a.g = null;
            byte[] bArr2 = new byte[a.d()];
            a.a(d.a(bArr2, 0, bArr2.length));
            bArr = bArr2;
        } catch (IOException e2) {
            r().i().a("Unable to serialize reduced-size config. Storing full config instead. appId", aq.a(str), e2);
        }
        z d_ = d_();
        Preconditions.checkNotEmpty(str);
        d_.d();
        d_.I();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        try {
            if (((long) d_.i().update("apps", contentValues, "app_id = ?", new String[]{str})) == 0) {
                d_.r().h_().a("Failed to update remote config (got 0). appId", aq.a(str));
                return true;
            }
        } catch (SQLiteException e3) {
            d_.r().h_().a("Error storing remote config. appId", aq.a(str), e3);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final String b(String str) {
        d();
        return (String) this.i.get(str);
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean b(String str, String str2) {
        d();
        g(str);
        if (e(str) && fg.g(str2)) {
            return true;
        }
        if (f(str) && fg.a(str2)) {
            return true;
        }
        Map map = (Map) this.e.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void c(String str) {
        d();
        this.i.put(str, null);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean c(String str, String str2) {
        d();
        g(str);
        if ("ecommerce_purchase".equals(str2)) {
            return true;
        }
        Map map = (Map) this.f.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final int d(String str, String str2) {
        d();
        g(str);
        Map map = (Map) this.h.get(str);
        if (map == null) {
            return 1;
        }
        Integer num = (Integer) map.get(str2);
        if (num == null) {
            return 1;
        }
        return num.intValue();
    }

    public final /* bridge */ /* synthetic */ void d() {
        super.d();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void d(String str) {
        d();
        this.g.remove(str);
    }

    public final /* bridge */ /* synthetic */ z d_() {
        return super.d_();
    }

    /* access modifiers changed from: protected */
    public final boolean e() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public final boolean e(String str) {
        return "1".equals(a(str, "measurement.upload.blacklist_internal"));
    }

    public final /* bridge */ /* synthetic */ u e_() {
        return super.e_();
    }

    /* access modifiers changed from: 0000 */
    public final boolean f(String str) {
        return "1".equals(a(str, "measurement.upload.blacklist_public"));
    }

    public final /* bridge */ /* synthetic */ fe f_() {
        return super.f_();
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
