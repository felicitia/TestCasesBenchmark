package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.ao;
import java.util.concurrent.TimeUnit;

@bu
public final class mp {
    private final Context a;
    private final String b;
    private final zzang c;
    @Nullable
    private final akw d;
    @Nullable
    private final aky e;
    private final ij f = new im().a("min_1", Double.MIN_VALUE, 1.0d).a("1_5", 1.0d, 5.0d).a("5_10", 5.0d, 10.0d).a("10_20", 10.0d, 20.0d).a("20_30", 20.0d, 30.0d).a("30_max", 30.0d, Double.MAX_VALUE).a();
    private final long[] g;
    private final String[] h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private zzapg n;
    private boolean o;
    private boolean p;
    private long q;

    public mp(Context context, zzang zzang, String str, @Nullable aky aky, @Nullable akw akw) {
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = false;
        this.q = -1;
        this.a = context;
        this.c = zzang;
        this.b = str;
        this.e = aky;
        this.d = akw;
        String str2 = (String) ajh.f().a(akl.u);
        if (str2 == null) {
            this.h = new String[0];
            this.g = new long[0];
            return;
        }
        String[] split = TextUtils.split(str2, ",");
        this.h = new String[split.length];
        this.g = new long[split.length];
        for (int i2 = 0; i2 < split.length; i2++) {
            try {
                this.g[i2] = Long.parseLong(split[i2]);
            } catch (NumberFormatException e2) {
                gv.c("Unable to parse frame hash target time number.", e2);
                this.g[i2] = -1;
            }
        }
    }

    public final void a() {
        if (this.i && !this.j) {
            akr.a(this.e, this.d, "vfr2");
            this.j = true;
        }
    }

    public final void a(zzapg zzapg) {
        akr.a(this.e, this.d, "vpc2");
        this.i = true;
        if (this.e != null) {
            this.e.a("vpn", zzapg.zzsp());
        }
        this.n = zzapg;
    }

    public final void b() {
        if (((Boolean) ajh.f().a(akl.t)).booleanValue() && !this.o) {
            Bundle bundle = new Bundle();
            bundle.putString("type", "native-player-metrics");
            bundle.putString("request", this.b);
            bundle.putString("player", this.n.zzsp());
            for (il ilVar : this.f.a()) {
                String valueOf = String.valueOf("fps_c_");
                String valueOf2 = String.valueOf(ilVar.a);
                bundle.putString(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), Integer.toString(ilVar.c));
                String valueOf3 = String.valueOf("fps_p_");
                String valueOf4 = String.valueOf(ilVar.a);
                bundle.putString(valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), Double.toString(ilVar.b));
            }
            for (int i2 = 0; i2 < this.g.length; i2++) {
                String str = this.h[i2];
                if (str != null) {
                    String valueOf5 = String.valueOf(Long.valueOf(this.g[i2]));
                    StringBuilder sb = new StringBuilder(3 + String.valueOf(valueOf5).length());
                    sb.append("fh_");
                    sb.append(valueOf5);
                    bundle.putString(sb.toString(), str);
                }
            }
            ao.e().a(this.a, this.c.zzcw, "gmob-apps", bundle, true);
            this.o = true;
        }
    }

    public final void b(zzapg zzapg) {
        if (this.k && !this.l) {
            if (gv.a() && !this.l) {
                gv.a("VideoMetricsMixin first frame");
            }
            akr.a(this.e, this.d, "vff2");
            this.l = true;
        }
        long nanoTime = ao.l().nanoTime();
        if (this.m && this.p && this.q != -1) {
            this.f.a(((double) TimeUnit.SECONDS.toNanos(1)) / ((double) (nanoTime - this.q)));
        }
        this.p = this.m;
        this.q = nanoTime;
        long longValue = ((Long) ajh.f().a(akl.v)).longValue();
        long currentPosition = (long) zzapg.getCurrentPosition();
        int i2 = 0;
        while (i2 < this.h.length) {
            if (this.h[i2] != null || longValue <= Math.abs(currentPosition - this.g[i2])) {
                zzapg zzapg2 = zzapg;
                i2++;
            } else {
                String[] strArr = this.h;
                int i3 = 8;
                Bitmap bitmap = zzapg.getBitmap(8, 8);
                long j2 = 63;
                long j3 = 0;
                int i4 = 0;
                while (i4 < i3) {
                    int i5 = 0;
                    long j4 = j2;
                    while (i5 < i3) {
                        int pixel = bitmap.getPixel(i5, i4);
                        long j5 = j3 | (((Color.blue(pixel) + Color.red(pixel)) + Color.green(pixel) > 128 ? 1 : 0) << ((int) j4));
                        i5++;
                        j4--;
                        j3 = j5;
                        i3 = 8;
                    }
                    i4++;
                    j2 = j4;
                    i3 = 8;
                }
                strArr[i2] = String.format("%016X", new Object[]{Long.valueOf(j3)});
                return;
            }
        }
    }

    public final void c() {
        this.m = true;
        if (this.j && !this.k) {
            akr.a(this.e, this.d, "vfp2");
            this.k = true;
        }
    }

    public final void d() {
        this.m = false;
    }
}
