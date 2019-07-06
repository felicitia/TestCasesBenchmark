package com.google.android.gms.ads.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.hd;
import com.google.android.gms.internal.ads.zzjj;
import java.lang.ref.WeakReference;
import org.apache.commons.lang3.time.DateUtils;

@bu
public final class af {
    private final ah a;
    private final Runnable b;
    /* access modifiers changed from: private */
    @Nullable
    public zzjj c;
    /* access modifiers changed from: private */
    public boolean d;
    private boolean e;
    private long f;

    public af(zza zza) {
        this(zza, new ah(hd.a));
    }

    @VisibleForTesting
    private af(zza zza, ah ahVar) {
        this.d = false;
        this.e = false;
        this.f = 0;
        this.a = ahVar;
        this.b = new ag(this, new WeakReference(zza));
    }

    public final void a() {
        this.d = false;
        this.a.a(this.b);
    }

    public final void a(zzjj zzjj) {
        this.c = zzjj;
    }

    public final void a(zzjj zzjj, long j) {
        if (this.d) {
            gv.e("An ad refresh is already scheduled.");
            return;
        }
        this.c = zzjj;
        this.d = true;
        this.f = j;
        if (!this.e) {
            StringBuilder sb = new StringBuilder(65);
            sb.append("Scheduling ad refresh ");
            sb.append(j);
            sb.append(" milliseconds from now.");
            gv.d(sb.toString());
            this.a.a(this.b, j);
        }
    }

    public final void b() {
        this.e = true;
        if (this.d) {
            this.a.a(this.b);
        }
    }

    public final void b(zzjj zzjj) {
        a(zzjj, (long) DateUtils.MILLIS_PER_MINUTE);
    }

    public final void c() {
        this.e = false;
        if (this.d) {
            this.d = false;
            a(this.c, this.f);
        }
    }

    public final void d() {
        this.e = false;
        this.d = false;
        if (!(this.c == null || this.c.extras == null)) {
            this.c.extras.remove("_ad");
        }
        a(this.c, 0);
    }

    public final boolean e() {
        return this.d;
    }
}
