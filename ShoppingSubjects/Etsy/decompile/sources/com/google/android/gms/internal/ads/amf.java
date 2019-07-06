package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Collections;
import java.util.Map;

public abstract class amf<T> implements Comparable<amf<T>> {
    /* access modifiers changed from: private */
    public final a a;
    private final int b;
    private final String c;
    private final int d;
    private final Object e;
    private arn f;
    private Integer g;
    private apa h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private s m;
    private acb n;
    private ana o;

    public amf(int i2, String str, arn arn) {
        this.a = a.a ? new a() : null;
        this.e = new Object();
        this.i = true;
        int i3 = 0;
        this.j = false;
        this.k = false;
        this.l = false;
        this.n = null;
        this.b = i2;
        this.c = str;
        this.f = arn;
        this.m = new agu();
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                String host = parse.getHost();
                if (host != null) {
                    i3 = host.hashCode();
                }
            }
        }
        this.d = i3;
    }

    public final amf<?> a(int i2) {
        this.g = Integer.valueOf(i2);
        return this;
    }

    public final amf<?> a(acb acb) {
        this.n = acb;
        return this;
    }

    public final amf<?> a(apa apa) {
        this.h = apa;
        return this;
    }

    /* access modifiers changed from: protected */
    public abstract arb<T> a(all all);

    /* access modifiers changed from: 0000 */
    public final void a(ana ana) {
        synchronized (this.e) {
            this.o = ana;
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(arb<?> arb) {
        ana ana;
        synchronized (this.e) {
            ana = this.o;
        }
        if (ana != null) {
            ana.a(this, arb);
        }
    }

    public final void a(zzae zzae) {
        arn arn;
        synchronized (this.e) {
            arn = this.f;
        }
        if (arn != null) {
            arn.a(zzae);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a(T t);

    public byte[] a() throws zza {
        return null;
    }

    public Map<String, String> b() throws zza {
        return Collections.emptyMap();
    }

    public final void b(String str) {
        if (a.a) {
            this.a.a(str, Thread.currentThread().getId());
        }
    }

    public final int c() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public final void c(String str) {
        if (this.h != null) {
            this.h.b(this);
        }
        if (a.a) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new amj(this, str, id));
            } else {
                this.a.a(str, id);
                this.a.a(toString());
            }
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        amf amf = (amf) obj;
        zzu zzu = zzu.NORMAL;
        zzu zzu2 = zzu.NORMAL;
        return zzu == zzu2 ? this.g.intValue() - amf.g.intValue() : zzu2.ordinal() - zzu.ordinal();
    }

    public final int d() {
        return this.d;
    }

    public final String e() {
        return this.c;
    }

    public final acb f() {
        return this.n;
    }

    public final boolean g() {
        synchronized (this.e) {
        }
        return false;
    }

    public final boolean h() {
        return this.i;
    }

    public final int i() {
        return this.m.a();
    }

    public final s j() {
        return this.m;
    }

    public final void k() {
        synchronized (this.e) {
            this.k = true;
        }
    }

    public final boolean l() {
        boolean z;
        synchronized (this.e) {
            z = this.k;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public final void m() {
        ana ana;
        synchronized (this.e) {
            ana = this.o;
        }
        if (ana != null) {
            ana.a(this);
        }
    }

    public String toString() {
        String str = "0x";
        String valueOf = String.valueOf(Integer.toHexString(this.d));
        String concat = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        String str2 = "[ ] ";
        String str3 = this.c;
        String valueOf2 = String.valueOf(zzu.NORMAL);
        String valueOf3 = String.valueOf(this.g);
        StringBuilder sb = new StringBuilder(3 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(concat).length() + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append(str2);
        sb.append(str3);
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(concat);
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(valueOf2);
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(valueOf3);
        return sb.toString();
    }
}
