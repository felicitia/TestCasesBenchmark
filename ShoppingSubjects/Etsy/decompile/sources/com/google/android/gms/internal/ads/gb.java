package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import org.json.JSONObject;

@bu
public final class gb {
    public final zzaef a;
    public final zzaej b;
    public final aqz c;
    @Nullable
    public final zzjn d;
    public final int e;
    public final long f;
    public final long g;
    @Nullable
    public final JSONObject h;
    public final ahh i;
    public final boolean j;

    public gb(zzaef zzaef, zzaej zzaej, aqz aqz, zzjn zzjn, int i2, long j2, long j3, JSONObject jSONObject, ahh ahh, @Nullable Boolean bool) {
        this.a = zzaef;
        this.b = zzaej;
        this.c = aqz;
        this.d = zzjn;
        this.e = i2;
        this.f = j2;
        this.g = j3;
        this.h = jSONObject;
        this.i = ahh;
        boolean z = bool != null ? bool.booleanValue() : jh.a(zzaef.zzccv);
        this.j = z;
    }

    public gb(zzaef zzaef, zzaej zzaej, aqz aqz, zzjn zzjn, int i2, long j2, long j3, JSONObject jSONObject, ahl ahl) {
        this.a = zzaef;
        this.b = zzaej;
        this.c = null;
        this.d = null;
        this.e = i2;
        this.f = j2;
        this.g = j3;
        this.h = null;
        this.i = new ahh(ahl);
        this.j = false;
    }
}
