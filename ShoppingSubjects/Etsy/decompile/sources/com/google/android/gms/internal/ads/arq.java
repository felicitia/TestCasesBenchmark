package com.google.android.gms.internal.ads;

import android.location.Location;
import android.support.annotation.Nullable;
import com.etsy.android.lib.convos.Draft;
import com.google.android.gms.ads.formats.b;
import com.google.android.gms.ads.formats.b.a;
import com.google.android.gms.ads.j;
import com.google.android.gms.ads.mediation.i;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@bu
public final class arq implements i {
    private final Date a;
    private final int b;
    private final Set<String> c;
    private final boolean d;
    private final Location e;
    private final int f;
    private final zzpl g;
    private final List<String> h = new ArrayList();
    private final boolean i;
    private final Map<String, Boolean> j = new HashMap();

    public arq(@Nullable Date date, int i2, @Nullable Set<String> set, @Nullable Location location, boolean z, int i3, zzpl zzpl, List<String> list, boolean z2) {
        Map<String, Boolean> map;
        String str;
        Boolean valueOf;
        this.a = date;
        this.b = i2;
        this.c = set;
        this.e = location;
        this.d = z;
        this.f = i3;
        this.g = zzpl;
        this.i = z2;
        String str2 = "custom:";
        if (list != null) {
            for (String str3 : list) {
                if (str3.startsWith(str2)) {
                    String[] split = str3.split(Draft.IMAGE_DELIMITER, 3);
                    if (split.length == 3) {
                        if ("true".equals(split[2])) {
                            map = this.j;
                            str = split[1];
                            valueOf = Boolean.valueOf(true);
                        } else if ("false".equals(split[2])) {
                            map = this.j;
                            str = split[1];
                            valueOf = Boolean.valueOf(false);
                        }
                        map.put(str, valueOf);
                    }
                } else {
                    this.h.add(str3);
                }
            }
        }
    }

    public final Date a() {
        return this.a;
    }

    public final int b() {
        return this.b;
    }

    public final Set<String> c() {
        return this.c;
    }

    public final Location d() {
        return this.e;
    }

    public final int e() {
        return this.f;
    }

    public final boolean f() {
        return this.d;
    }

    public final boolean g() {
        return this.i;
    }

    public final b h() {
        if (this.g == null) {
            return null;
        }
        a b2 = new a().a(this.g.zzbjn).a(this.g.zzbjo).b(this.g.zzbjp);
        if (this.g.versionCode >= 2) {
            b2.b(this.g.zzbjq);
        }
        if (this.g.versionCode >= 3 && this.g.zzbjr != null) {
            b2.a(new j(this.g.zzbjr));
        }
        return b2.a();
    }

    public final boolean i() {
        return this.h != null && (this.h.contains("2") || this.h.contains("6"));
    }

    public final boolean j() {
        return this.h != null && this.h.contains("6");
    }

    public final boolean k() {
        return this.h != null && (this.h.contains("1") || this.h.contains("6"));
    }

    public final boolean l() {
        return this.h != null && this.h.contains("3");
    }

    public final Map<String, Boolean> m() {
        return this.j;
    }
}
