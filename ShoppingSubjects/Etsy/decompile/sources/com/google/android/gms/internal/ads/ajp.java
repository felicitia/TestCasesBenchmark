package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.a;
import com.google.android.gms.ads.doubleclick.e;
import com.google.android.gms.ads.f;
import com.google.android.gms.ads.reward.b;
import com.google.android.gms.ads.reward.c;
import com.google.android.gms.common.util.VisibleForTesting;

@bu
public final class ajp {
    private final zzxm a;
    private final Context b;
    private final aix c;
    private a d;
    private ait e;
    private zzks f;
    private String g;
    private c h;
    private com.google.android.gms.ads.doubleclick.a i;
    private com.google.android.gms.ads.doubleclick.c j;
    private f k;
    private b l;
    private boolean m;
    private boolean n;

    public ajp(Context context) {
        this(context, aix.a, null);
    }

    @VisibleForTesting
    private ajp(Context context, aix aix, e eVar) {
        this.a = new zzxm();
        this.b = context;
        this.c = aix;
    }

    private final void b(String str) {
        if (this.f == null) {
            StringBuilder sb = new StringBuilder(63 + String.valueOf(str).length());
            sb.append("The ad unit ID must be set on InterstitialAd before ");
            sb.append(str);
            sb.append(" is called.");
            throw new IllegalStateException(sb.toString());
        }
    }

    public final Bundle a() {
        try {
            if (this.f != null) {
                return this.f.zzba();
            }
        } catch (RemoteException e2) {
            ka.d("#008 Must be called on the main UI thread.", e2);
        }
        return new Bundle();
    }

    public final void a(a aVar) {
        try {
            this.d = aVar;
            if (this.f != null) {
                this.f.zza((zzkh) aVar != null ? new zzjf(aVar) : null);
            }
        } catch (RemoteException e2) {
            ka.d("#008 Must be called on the main UI thread.", e2);
        }
    }

    public final void a(b bVar) {
        try {
            this.l = bVar;
            if (this.f != null) {
                this.f.zza((zzahe) bVar != null ? new zzahj(bVar) : null);
            }
        } catch (RemoteException e2) {
            ka.d("#008 Must be called on the main UI thread.", e2);
        }
    }

    public final void a(c cVar) {
        try {
            this.h = cVar;
            if (this.f != null) {
                this.f.zza((zzkx) cVar != null ? new zzji(cVar) : null);
            }
        } catch (RemoteException e2) {
            ka.d("#008 Must be called on the main UI thread.", e2);
        }
    }

    public final void a(ait ait) {
        try {
            this.e = ait;
            if (this.f != null) {
                this.f.zza((zzke) ait != null ? new zzje(ait) : null);
            }
        } catch (RemoteException e2) {
            ka.d("#008 Must be called on the main UI thread.", e2);
        }
    }

    public final void a(ajk ajk) {
        try {
            if (this.f == null) {
                String str = "loadAd";
                if (this.g == null) {
                    b(str);
                }
                zzjn zzhx = this.m ? zzjn.zzhx() : new zzjn();
                aiz b2 = ajh.b();
                Context context = this.b;
                ajc ajc = new ajc(b2, context, zzhx, this.g, this.a);
                this.f = (zzks) aiz.a(context, false, (a<T>) ajc);
                if (this.d != null) {
                    this.f.zza((zzkh) new zzjf(this.d));
                }
                if (this.e != null) {
                    this.f.zza((zzke) new zzje(this.e));
                }
                if (this.h != null) {
                    this.f.zza((zzkx) new zzji(this.h));
                }
                if (this.i != null) {
                    this.f.zza((zzla) new zzjp(this.i));
                }
                if (this.j != null) {
                    this.f.zza((zzod) new zzog(this.j));
                }
                if (this.k != null) {
                    this.f.zza((zzlg) this.k.a());
                }
                if (this.l != null) {
                    this.f.zza((zzahe) new zzahj(this.l));
                }
                this.f.setImmersiveMode(this.n);
            }
            if (this.f.zzb(aix.a(this.b, ajk))) {
                this.a.zzj(ajk.j());
            }
        } catch (RemoteException e2) {
            ka.d("#008 Must be called on the main UI thread.", e2);
        }
    }

    public final void a(String str) {
        if (this.g != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
        }
        this.g = str;
    }

    public final void a(boolean z) {
        this.m = true;
    }

    public final void b() {
        try {
            b("show");
            this.f.showInterstitial();
        } catch (RemoteException e2) {
            ka.d("#008 Must be called on the main UI thread.", e2);
        }
    }

    public final void b(boolean z) {
        try {
            this.n = z;
            if (this.f != null) {
                this.f.setImmersiveMode(z);
            }
        } catch (RemoteException e2) {
            ka.d("#008 Must be called on the main UI thread.", e2);
        }
    }
}
