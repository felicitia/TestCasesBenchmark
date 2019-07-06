package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.a;
import com.google.android.gms.ads.d;
import com.google.android.gms.ads.doubleclick.c;
import com.google.android.gms.ads.f;
import com.google.android.gms.ads.i;
import com.google.android.gms.ads.j;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.atomic.AtomicBoolean;

@bu
public final class ajm {
    private final zzxm a;
    private final aix b;
    private final AtomicBoolean c;
    /* access modifiers changed from: private */
    public final i d;
    @VisibleForTesting
    private final aji e;
    private ait f;
    private a g;
    private d[] h;
    private com.google.android.gms.ads.doubleclick.a i;
    private f j;
    private zzks k;
    private c l;
    private j m;
    private String n;
    private ViewGroup o;
    private int p;
    private boolean q;

    public ajm(ViewGroup viewGroup) {
        this(viewGroup, null, false, aix.a, 0);
    }

    public ajm(ViewGroup viewGroup, int i2) {
        this(viewGroup, null, false, aix.a, i2);
    }

    public ajm(ViewGroup viewGroup, AttributeSet attributeSet, boolean z) {
        this(viewGroup, attributeSet, z, aix.a, 0);
    }

    public ajm(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, int i2) {
        this(viewGroup, attributeSet, false, aix.a, i2);
    }

    @VisibleForTesting
    private ajm(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, aix aix, int i2) {
        this(viewGroup, attributeSet, z, aix, null, i2);
    }

    @VisibleForTesting
    private ajm(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, aix aix, zzks zzks, int i2) {
        this.a = new zzxm();
        this.d = new i();
        this.e = new ajn(this);
        this.o = viewGroup;
        this.b = aix;
        this.k = null;
        this.c = new AtomicBoolean(false);
        this.p = i2;
        if (attributeSet != null) {
            Context context = viewGroup.getContext();
            try {
                aiy aiy = new aiy(context, attributeSet);
                this.h = aiy.a(z);
                this.n = aiy.a();
                if (viewGroup.isInEditMode()) {
                    jp a2 = ajh.a();
                    d dVar = this.h[0];
                    int i3 = this.p;
                    zzjn zzjn = new zzjn(context, dVar);
                    zzjn.zzarg = a(i3);
                    a2.a(viewGroup, zzjn, "Ads by Google");
                }
            } catch (IllegalArgumentException e2) {
                ajh.a().a(viewGroup, new zzjn(context, d.a), e2.getMessage(), e2.getMessage());
            }
        }
    }

    private static zzjn a(Context context, d[] dVarArr, int i2) {
        zzjn zzjn = new zzjn(context, dVarArr);
        zzjn.zzarg = a(i2);
        return zzjn;
    }

    private static boolean a(int i2) {
        return i2 == 1;
    }

    public final void a() {
        try {
            if (this.k != null) {
                this.k.destroy();
            }
        } catch (RemoteException e2) {
            ka.d("#007 Could not call remote method.", e2);
        }
    }

    public final void a(a aVar) {
        this.g = aVar;
        this.e.a(aVar);
    }

    public final void a(com.google.android.gms.ads.doubleclick.a aVar) {
        try {
            this.i = aVar;
            if (this.k != null) {
                this.k.zza((zzla) aVar != null ? new zzjp(aVar) : null);
            }
        } catch (RemoteException e2) {
            ka.d("#007 Could not call remote method.", e2);
        }
    }

    public final void a(c cVar) {
        this.l = cVar;
        try {
            if (this.k != null) {
                this.k.zza((zzod) cVar != null ? new zzog(cVar) : null);
            }
        } catch (RemoteException e2) {
            ka.d("#007 Could not call remote method.", e2);
        }
    }

    public final void a(f fVar) {
        this.j = fVar;
        try {
            if (this.k != null) {
                this.k.zza((zzlg) this.j == null ? null : this.j.a());
            }
        } catch (RemoteException e2) {
            ka.d("#007 Could not call remote method.", e2);
        }
    }

    public final void a(j jVar) {
        this.m = jVar;
        try {
            if (this.k != null) {
                this.k.zza(jVar == null ? null : new zzmu(jVar));
            }
        } catch (RemoteException e2) {
            ka.d("#007 Could not call remote method.", e2);
        }
    }

    public final void a(ait ait) {
        try {
            this.f = ait;
            if (this.k != null) {
                this.k.zza((zzke) ait != null ? new zzje(ait) : null);
            }
        } catch (RemoteException e2) {
            ka.d("#007 Could not call remote method.", e2);
        }
    }

    public final void a(ajk ajk) {
        Object a2;
        try {
            if (this.k == null) {
                if ((this.h == null || this.n == null) && this.k == null) {
                    throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
                }
                Context context = this.o.getContext();
                zzjn a3 = a(context, this.h, this.p);
                if ("search_v2".equals(a3.zzarb)) {
                    a2 = aiz.a(context, false, (a<T>) new ajb<T>(ajh.b(), context, a3, this.n));
                } else {
                    aja aja = new aja(ajh.b(), context, a3, this.n, this.a);
                    a2 = aiz.a(context, false, (a<T>) aja);
                }
                this.k = (zzks) a2;
                this.k.zza((zzkh) new zzjf(this.e));
                if (this.f != null) {
                    this.k.zza((zzke) new zzje(this.f));
                }
                if (this.i != null) {
                    this.k.zza((zzla) new zzjp(this.i));
                }
                if (this.l != null) {
                    this.k.zza((zzod) new zzog(this.l));
                }
                if (this.j != null) {
                    this.k.zza((zzlg) this.j.a());
                }
                if (this.m != null) {
                    this.k.zza(new zzmu(this.m));
                }
                this.k.setManualImpressionsEnabled(this.q);
                try {
                    IObjectWrapper zzbj = this.k.zzbj();
                    if (zzbj != null) {
                        this.o.addView((View) ObjectWrapper.unwrap(zzbj));
                    }
                } catch (RemoteException e2) {
                    ka.d("#007 Could not call remote method.", e2);
                }
            }
            if (this.k.zzb(aix.a(this.o.getContext(), ajk))) {
                this.a.zzj(ajk.j());
            }
        } catch (RemoteException e3) {
            ka.d("#007 Could not call remote method.", e3);
        }
    }

    public final void a(String str) {
        if (this.n != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
        }
        this.n = str;
    }

    public final void a(boolean z) {
        this.q = z;
        try {
            if (this.k != null) {
                this.k.setManualImpressionsEnabled(this.q);
            }
        } catch (RemoteException e2) {
            ka.d("#007 Could not call remote method.", e2);
        }
    }

    public final void a(d... dVarArr) {
        if (this.h != null) {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        }
        b(dVarArr);
    }

    public final boolean a(zzks zzks) {
        if (zzks == null) {
            return false;
        }
        try {
            IObjectWrapper zzbj = zzks.zzbj();
            if (zzbj == null || ((View) ObjectWrapper.unwrap(zzbj)).getParent() != null) {
                return false;
            }
            this.o.addView((View) ObjectWrapper.unwrap(zzbj));
            this.k = zzks;
            return true;
        } catch (RemoteException e2) {
            ka.d("#007 Could not call remote method.", e2);
            return false;
        }
    }

    public final a b() {
        return this.g;
    }

    public final void b(d... dVarArr) {
        this.h = dVarArr;
        try {
            if (this.k != null) {
                this.k.zza(a(this.o.getContext(), this.h, this.p));
            }
        } catch (RemoteException e2) {
            ka.d("#007 Could not call remote method.", e2);
        }
        this.o.requestLayout();
    }

    public final d c() {
        try {
            if (this.k != null) {
                zzjn zzbk = this.k.zzbk();
                if (zzbk != null) {
                    return zzbk.zzhy();
                }
            }
        } catch (RemoteException e2) {
            ka.d("#007 Could not call remote method.", e2);
        }
        if (this.h != null) {
            return this.h[0];
        }
        return null;
    }

    public final d[] d() {
        return this.h;
    }

    public final String e() {
        if (this.n == null && this.k != null) {
            try {
                this.n = this.k.getAdUnitId();
            } catch (RemoteException e2) {
                ka.d("#007 Could not call remote method.", e2);
            }
        }
        return this.n;
    }

    public final com.google.android.gms.ads.doubleclick.a f() {
        return this.i;
    }

    public final c g() {
        return this.l;
    }

    public final void h() {
        try {
            if (this.k != null) {
                this.k.pause();
            }
        } catch (RemoteException e2) {
            ka.d("#007 Could not call remote method.", e2);
        }
    }

    public final void i() {
        if (!this.c.getAndSet(true)) {
            try {
                if (this.k != null) {
                    this.k.zzbm();
                }
            } catch (RemoteException e2) {
                ka.d("#007 Could not call remote method.", e2);
            }
        }
    }

    public final void j() {
        try {
            if (this.k != null) {
                this.k.resume();
            }
        } catch (RemoteException e2) {
            ka.d("#007 Could not call remote method.", e2);
        }
    }

    public final String k() {
        try {
            if (this.k != null) {
                return this.k.zzck();
            }
        } catch (RemoteException e2) {
            ka.d("#007 Could not call remote method.", e2);
        }
        return null;
    }

    public final boolean l() {
        try {
            if (this.k != null) {
                return this.k.isLoading();
            }
        } catch (RemoteException e2) {
            ka.d("#007 Could not call remote method.", e2);
        }
        return false;
    }

    public final i m() {
        return this.d;
    }

    public final zzlo n() {
        if (this.k == null) {
            return null;
        }
        try {
            return this.k.getVideoController();
        } catch (RemoteException e2) {
            ka.d("#007 Could not call remote method.", e2);
            return null;
        }
    }

    public final j o() {
        return this.m;
    }
}
