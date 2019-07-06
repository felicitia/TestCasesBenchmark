package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ClassUtils;

@bu
public final class aky {
    @VisibleForTesting
    private boolean a;
    private final List<akw> b = new LinkedList();
    private final Map<String, String> c = new LinkedHashMap();
    private final Object d = new Object();
    private String e;
    @Nullable
    private aky f;

    public aky(boolean z, String str, String str2) {
        this.a = z;
        this.c.put(ResponseConstants.ACTION, str);
        this.c.put("ad_format", str2);
    }

    public final akw a() {
        return a(ao.l().elapsedRealtime());
    }

    @Nullable
    public final akw a(long j) {
        if (!this.a) {
            return null;
        }
        return new akw(j, null, null);
    }

    public final void a(@Nullable aky aky) {
        synchronized (this.d) {
            this.f = aky;
        }
    }

    public final void a(String str) {
        if (this.a) {
            synchronized (this.d) {
                this.e = str;
            }
        }
    }

    public final void a(String str, String str2) {
        if (this.a && !TextUtils.isEmpty(str2)) {
            ako b2 = ao.i().b();
            if (b2 != null) {
                synchronized (this.d) {
                    aks a2 = b2.a(str);
                    Map<String, String> map = this.c;
                    map.put(str, a2.a((String) map.get(str), str2));
                }
            }
        }
    }

    public final boolean a(akw akw, long j, String... strArr) {
        synchronized (this.d) {
            for (String akw2 : strArr) {
                this.b.add(new akw(j, akw2, akw));
            }
        }
        return true;
    }

    public final boolean a(@Nullable akw akw, String... strArr) {
        if (!this.a || akw == null) {
            return false;
        }
        return a(akw, ao.l().elapsedRealtime(), strArr);
    }

    public final String b() {
        String sb;
        StringBuilder sb2 = new StringBuilder();
        synchronized (this.d) {
            for (akw akw : this.b) {
                long a2 = akw.a();
                String b2 = akw.b();
                akw c2 = akw.c();
                if (c2 != null && a2 > 0) {
                    long a3 = a2 - c2.a();
                    sb2.append(b2);
                    sb2.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                    sb2.append(a3);
                    sb2.append(',');
                }
            }
            this.b.clear();
            if (!TextUtils.isEmpty(this.e)) {
                sb2.append(this.e);
            } else if (sb2.length() > 0) {
                sb2.setLength(sb2.length() - 1);
            }
            sb = sb2.toString();
        }
        return sb;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final Map<String, String> c() {
        synchronized (this.d) {
            ako b2 = ao.i().b();
            if (b2 != null) {
                if (this.f != null) {
                    Map<String, String> a2 = b2.a(this.c, this.f.c());
                    return a2;
                }
            }
            Map<String, String> map = this.c;
            return map;
        }
    }

    public final akw d() {
        synchronized (this.d) {
        }
        return null;
    }
}
