package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.WeakHashMap;

@bu
public final class aen implements aev {
    private final Object a = new Object();
    private final WeakHashMap<ga, zzet> b = new WeakHashMap<>();
    private final ArrayList<zzet> c = new ArrayList<>();
    private final Context d;
    private final zzang e;
    private final apg f;

    public aen(Context context, zzang zzang) {
        this.d = context.getApplicationContext();
        this.e = zzang;
        this.f = new apg(context.getApplicationContext(), zzang, (String) ajh.f().a(akl.a));
    }

    private final boolean e(ga gaVar) {
        boolean z;
        synchronized (this.a) {
            zzet zzet = (zzet) this.b.get(gaVar);
            z = zzet != null && zzet.zzge();
        }
        return z;
    }

    public final void a(ga gaVar) {
        synchronized (this.a) {
            zzet zzet = (zzet) this.b.get(gaVar);
            if (zzet != null) {
                zzet.zzgc();
            }
        }
    }

    public final void a(zzet zzet) {
        synchronized (this.a) {
            if (!zzet.zzge()) {
                this.c.remove(zzet);
                Iterator it = this.b.entrySet().iterator();
                while (it.hasNext()) {
                    if (((Entry) it.next()).getValue() == zzet) {
                        it.remove();
                    }
                }
            }
        }
    }

    public final void a(zzjn zzjn, ga gaVar) {
        a(zzjn, gaVar, gaVar.b.getView());
    }

    public final void a(zzjn zzjn, ga gaVar, View view) {
        a(zzjn, gaVar, (afx) new aet(view, gaVar), (nn) null);
    }

    public final void a(zzjn zzjn, ga gaVar, View view, nn nnVar) {
        a(zzjn, gaVar, (afx) new aet(view, gaVar), nnVar);
    }

    public final void a(zzjn zzjn, ga gaVar, afx afx, @Nullable nn nnVar) {
        zzet zzet;
        synchronized (this.a) {
            if (e(gaVar)) {
                zzet = (zzet) this.b.get(gaVar);
            } else {
                zzet zzet2 = new zzet(this.d, zzjn, gaVar, this.e, afx);
                zzet2.zza((aev) this);
                this.b.put(gaVar, zzet2);
                this.c.add(zzet2);
                zzet = zzet2;
            }
            zzet.zza(nnVar != null ? new aew(zzet, nnVar) : new afa(zzet, this.f, this.d));
        }
    }

    public final void b(ga gaVar) {
        synchronized (this.a) {
            zzet zzet = (zzet) this.b.get(gaVar);
            if (zzet != null) {
                zzet.stop();
            }
        }
    }

    public final void c(ga gaVar) {
        synchronized (this.a) {
            zzet zzet = (zzet) this.b.get(gaVar);
            if (zzet != null) {
                zzet.pause();
            }
        }
    }

    public final void d(ga gaVar) {
        synchronized (this.a) {
            zzet zzet = (zzet) this.b.get(gaVar);
            if (zzet != null) {
                zzet.resume();
            }
        }
    }
}
