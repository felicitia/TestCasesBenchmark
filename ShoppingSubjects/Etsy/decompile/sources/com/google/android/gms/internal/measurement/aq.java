package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.support.annotation.GuardedBy;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.etsy.android.lib.convos.Draft;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;

public final class aq extends cp {
    /* access modifiers changed from: private */
    public char a = 0;
    /* access modifiers changed from: private */
    public long b = -1;
    @GuardedBy("this")
    private String c;
    private final as d = new as(this, 6, false, false);
    private final as e = new as(this, 6, true, false);
    private final as f = new as(this, 6, false, true);
    private final as g = new as(this, 5, false, false);
    private final as h = new as(this, 5, true, false);
    private final as i = new as(this, 5, false, true);
    private final as j = new as(this, 4, false, false);
    private final as k = new as(this, 3, false, false);
    private final as l = new as(this, 2, false, false);

    aq(bu buVar) {
        super(buVar);
    }

    private final String C() {
        String str;
        synchronized (this) {
            if (this.c == null) {
                this.c = w.e();
            }
            str = this.c;
        }
        return str;
    }

    protected static Object a(String str) {
        if (str == null) {
            return null;
        }
        return new at(str);
    }

    @VisibleForTesting
    private static String a(boolean z, Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf((long) ((Integer) obj).intValue());
        }
        int i2 = 0;
        if (obj instanceof Long) {
            if (!z) {
                return String.valueOf(obj);
            }
            Long l2 = (Long) obj;
            if (Math.abs(l2.longValue()) < 100) {
                return String.valueOf(obj);
            }
            String str = String.valueOf(obj).charAt(0) == '-' ? "-" : "";
            String valueOf = String.valueOf(Math.abs(l2.longValue()));
            long round = Math.round(Math.pow(10.0d, (double) (valueOf.length() - 1)));
            long round2 = Math.round(Math.pow(10.0d, (double) valueOf.length()) - 1.0d);
            StringBuilder sb = new StringBuilder(43 + String.valueOf(str).length() + String.valueOf(str).length());
            sb.append(str);
            sb.append(round);
            sb.append("...");
            sb.append(str);
            sb.append(round2);
            return sb.toString();
        } else if (obj instanceof Boolean) {
            return String.valueOf(obj);
        } else {
            if (!(obj instanceof Throwable)) {
                return obj instanceof at ? ((at) obj).a : z ? "-" : String.valueOf(obj);
            }
            Throwable th = (Throwable) obj;
            StringBuilder sb2 = new StringBuilder(z ? th.getClass().getName() : th.toString());
            String b2 = b(AppMeasurement.class.getCanonicalName());
            String b3 = b(bu.class.getCanonicalName());
            StackTraceElement[] stackTrace = th.getStackTrace();
            int length = stackTrace.length;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                StackTraceElement stackTraceElement = stackTrace[i2];
                if (!stackTraceElement.isNativeMethod()) {
                    String className = stackTraceElement.getClassName();
                    if (className != null) {
                        String b4 = b(className);
                        if (b4.equals(b2) || b4.equals(b3)) {
                            sb2.append(": ");
                            sb2.append(stackTraceElement);
                        }
                    } else {
                        continue;
                    }
                }
                i2++;
            }
            return sb2.toString();
        }
    }

    static String a(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            str = "";
        }
        String a2 = a(z, obj);
        String a3 = a(z, obj2);
        String a4 = a(z, obj3);
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(a2)) {
            sb.append(str2);
            sb.append(a2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(a3)) {
            sb.append(str2);
            sb.append(a3);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(a4)) {
            sb.append(str2);
            sb.append(a4);
        }
        return sb.toString();
    }

    private static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public final void a(int i2, String str) {
        Log.println(i2, C(), str);
    }

    /* access modifiers changed from: protected */
    public final void a(int i2, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        String str2;
        if (!z && a(i2)) {
            a(i2, a(false, str, obj, obj2, obj3));
        }
        if (!z2 && i2 >= 5) {
            Preconditions.checkNotNull(str);
            bq g2 = this.q.g();
            if (g2 == null) {
                str2 = "Scheduler not set. Not logging error/warn";
            } else if (!g2.y()) {
                str2 = "Scheduler not initialized. Not logging error/warn";
            } else {
                if (i2 < 0) {
                    i2 = 0;
                }
                if (i2 >= 9) {
                    i2 = 8;
                }
                ar arVar = new ar(this, i2, str, obj, obj2, obj3);
                g2.a((Runnable) arVar);
            }
            a(6, str2);
        }
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public final boolean a(int i2) {
        return Log.isLoggable(C(), i2);
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
        return false;
    }

    public final as g() {
        return this.e;
    }

    public final as h() {
        return this.f;
    }

    public final as h_() {
        return this.d;
    }

    public final as i() {
        return this.g;
    }

    public final as j() {
        return this.i;
    }

    public final as k() {
        return this.j;
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

    public final as v() {
        return this.k;
    }

    public final as w() {
        return this.l;
    }

    public final String x() {
        Pair<String, Long> a2 = s().b.a();
        if (a2 == null || a2 == bb.a) {
            return null;
        }
        String valueOf = String.valueOf(a2.second);
        String str = (String) a2.first;
        StringBuilder sb = new StringBuilder(1 + String.valueOf(valueOf).length() + String.valueOf(str).length());
        sb.append(valueOf);
        sb.append(Draft.IMAGE_DELIMITER);
        sb.append(str);
        return sb.toString();
    }
}
