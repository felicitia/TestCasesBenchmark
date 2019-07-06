package com.google.android.gms.ads;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.aix;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.ajk;
import com.google.android.gms.internal.ads.ka;
import com.google.android.gms.internal.ads.zzjf;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzkk;
import com.google.android.gms.internal.ads.zzkn;
import com.google.android.gms.internal.ads.zzpl;
import com.google.android.gms.internal.ads.zzqw;
import com.google.android.gms.internal.ads.zzqz;
import com.google.android.gms.internal.ads.zzrl;
import com.google.android.gms.internal.ads.zzrx;
import com.google.android.gms.internal.ads.zzry;
import com.google.android.gms.internal.ads.zzrz;
import com.google.android.gms.internal.ads.zzsa;
import com.google.android.gms.internal.ads.zzsd;
import com.google.android.gms.internal.ads.zzxm;
import com.google.android.gms.internal.ads.zzxn;

public class b {
    private final aix a;
    private final Context b;
    private final zzkk c;

    public static class a {
        private final Context a;
        private final zzkn b;

        private a(Context context, zzkn zzkn) {
            this.a = context;
            this.b = zzkn;
        }

        public a(Context context, String str) {
            this((Context) Preconditions.checkNotNull(context, "context cannot be null"), ajh.b().a(context, str, (zzxn) new zzxm()));
        }

        public a a(a aVar) {
            try {
                this.b.zzb((zzkh) new zzjf(aVar));
                return this;
            } catch (RemoteException e) {
                ka.c("Failed to set AdListener.", e);
                return this;
            }
        }

        public a a(com.google.android.gms.ads.formats.b bVar) {
            try {
                this.b.zza(new zzpl(bVar));
                return this;
            } catch (RemoteException e) {
                ka.c("Failed to specify native ad options", e);
                return this;
            }
        }

        public a a(com.google.android.gms.ads.formats.d.a aVar) {
            try {
                this.b.zza((zzqw) new zzrx(aVar));
                return this;
            } catch (RemoteException e) {
                ka.c("Failed to add app install ad listener", e);
                return this;
            }
        }

        public a a(com.google.android.gms.ads.formats.e.a aVar) {
            try {
                this.b.zza((zzqz) new zzry(aVar));
                return this;
            } catch (RemoteException e) {
                ka.c("Failed to add content ad listener", e);
                return this;
            }
        }

        public a a(com.google.android.gms.ads.formats.h.a aVar) {
            try {
                this.b.zza((zzrl) new zzsd(aVar));
                return this;
            } catch (RemoteException e) {
                ka.c("Failed to add google native ad listener", e);
                return this;
            }
        }

        public a a(String str, com.google.android.gms.ads.formats.f.b bVar, com.google.android.gms.ads.formats.f.a aVar) {
            try {
                this.b.zza(str, new zzsa(bVar), aVar == null ? null : new zzrz(aVar));
                return this;
            } catch (RemoteException e) {
                ka.c("Failed to add custom template ad listener", e);
                return this;
            }
        }

        public b a() {
            try {
                return new b(this.a, this.b.zzdh());
            } catch (RemoteException e) {
                ka.b("Failed to build AdLoader.", e);
                return null;
            }
        }
    }

    b(Context context, zzkk zzkk) {
        this(context, zzkk, aix.a);
    }

    private b(Context context, zzkk zzkk, aix aix) {
        this.b = context;
        this.c = zzkk;
        this.a = aix;
    }

    private final void a(ajk ajk) {
        try {
            this.c.zzd(aix.a(this.b, ajk));
        } catch (RemoteException e) {
            ka.b("Failed to load ad.", e);
        }
    }

    @RequiresPermission("android.permission.INTERNET")
    public void a(c cVar) {
        a(cVar.a());
    }
}
