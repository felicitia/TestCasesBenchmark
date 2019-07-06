package com.google.android.gms.internal.icing;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

@VisibleForTesting
public final class ds {
    private final String a;
    private String b;
    private boolean c;
    private int d = 1;
    private boolean e;
    private final List<zzn> f = new ArrayList();
    private String g;

    public ds(String str) {
        this.a = str;
    }

    public final ds a(String str) {
        this.b = str;
        return this;
    }

    public final ds a(boolean z) {
        this.c = true;
        return this;
    }

    public final zzs a() {
        zzs zzs = new zzs(this.a, this.b, this.c, this.d, this.e, null, (zzn[]) this.f.toArray(new zzn[this.f.size()]), this.g, null);
        return zzs;
    }

    public final ds b(String str) {
        this.g = str;
        return this;
    }

    public final ds b(boolean z) {
        this.e = true;
        return this;
    }
}
