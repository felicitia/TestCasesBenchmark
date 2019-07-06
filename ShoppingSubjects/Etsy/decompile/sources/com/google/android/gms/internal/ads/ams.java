package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.bg;
import com.google.android.gms.ads.internal.zzal;
import com.google.android.gms.common.util.VisibleForTesting;

@bu
@VisibleForTesting
public final class ams {
    private final Context a;
    private final zzxn b;
    private final zzang c;
    private final bg d;

    ams(Context context, zzxn zzxn, zzang zzang, bg bgVar) {
        this.a = context;
        this.b = zzxn;
        this.c = zzang;
        this.d = bgVar;
    }

    @VisibleForTesting
    public final Context a() {
        return this.a.getApplicationContext();
    }

    @VisibleForTesting
    public final zzal a(String str) {
        zzal zzal = new zzal(this.a, new zzjn(), str, this.b, this.c, this.d);
        return zzal;
    }

    @VisibleForTesting
    public final zzal b(String str) {
        zzal zzal = new zzal(this.a.getApplicationContext(), new zzjn(), str, this.b, this.c, this.d);
        return zzal;
    }

    @VisibleForTesting
    public final ams b() {
        return new ams(this.a.getApplicationContext(), this.b, this.c, this.d);
    }
}
