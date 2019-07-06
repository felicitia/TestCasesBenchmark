package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.gmsg.k;
import com.google.android.gms.ads.internal.zzbw;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;
import java.util.Map;

@bu
public final class eg {
    private static final zzxm a = new zzxm();
    private final zzxn b;
    private final zzbw c;
    private final Map<String, fa> d = new HashMap();
    private final et e;
    private final k f;
    private final z g;

    public eg(zzbw zzbw, zzxn zzxn, et etVar, k kVar, z zVar) {
        this.c = zzbw;
        this.b = zzxn;
        this.e = etVar;
        this.f = kVar;
        this.g = zVar;
    }

    public static boolean a(ga gaVar, ga gaVar2) {
        return true;
    }

    public final k a() {
        return this.f;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003d  */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.fa a(java.lang.String r5) {
        /*
            r4 = this;
            java.util.Map<java.lang.String, com.google.android.gms.internal.ads.fa> r0 = r4.d
            java.lang.Object r0 = r0.get(r5)
            com.google.android.gms.internal.ads.fa r0 = (com.google.android.gms.internal.ads.fa) r0
            if (r0 != 0) goto L_0x0045
            com.google.android.gms.internal.ads.zzxn r1 = r4.b     // Catch:{ Exception -> 0x002b }
            java.lang.String r2 = "com.google.ads.mediation.admob.AdMobAdapter"
            boolean r2 = r2.equals(r5)     // Catch:{ Exception -> 0x002b }
            if (r2 == 0) goto L_0x0016
            com.google.android.gms.internal.ads.zzxm r1 = a     // Catch:{ Exception -> 0x002b }
        L_0x0016:
            com.google.android.gms.internal.ads.fa r2 = new com.google.android.gms.internal.ads.fa     // Catch:{ Exception -> 0x002b }
            com.google.android.gms.internal.ads.zzxq r1 = r1.zzbm(r5)     // Catch:{ Exception -> 0x002b }
            com.google.android.gms.internal.ads.et r3 = r4.e     // Catch:{ Exception -> 0x002b }
            r2.<init>(r1, r3)     // Catch:{ Exception -> 0x002b }
            java.util.Map<java.lang.String, com.google.android.gms.internal.ads.fa> r0 = r4.d     // Catch:{ Exception -> 0x0028 }
            r0.put(r5, r2)     // Catch:{ Exception -> 0x0028 }
            r0 = r2
            return r0
        L_0x0028:
            r1 = move-exception
            r0 = r2
            goto L_0x002c
        L_0x002b:
            r1 = move-exception
        L_0x002c:
            java.lang.String r2 = "Fail to instantiate adapter "
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r3 = r5.length()
            if (r3 == 0) goto L_0x003d
            java.lang.String r5 = r2.concat(r5)
            goto L_0x0042
        L_0x003d:
            java.lang.String r5 = new java.lang.String
            r5.<init>(r2)
        L_0x0042:
            com.google.android.gms.internal.ads.gv.c(r5, r1)
        L_0x0045:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.eg.a(java.lang.String):com.google.android.gms.internal.ads.fa");
    }

    public final zzaig a(zzaig zzaig) {
        if (!(this.c.zzacw == null || this.c.zzacw.r == null || TextUtils.isEmpty(this.c.zzacw.r.k))) {
            zzaig = new zzaig(this.c.zzacw.r.k, this.c.zzacw.r.l);
        }
        if (!(this.c.zzacw == null || this.c.zzacw.o == null)) {
            ao.x();
            arh.a(this.c.zzrt, this.c.zzacr.zzcw, this.c.zzacw.o.m, this.c.zzadr, zzaig);
        }
        return zzaig;
    }

    public final void a(@NonNull Context context) {
        for (fa a2 : this.d.values()) {
            try {
                a2.a().zzi(ObjectWrapper.wrap(context));
            } catch (RemoteException e2) {
                gv.b("Unable to call Adapter.onContextChanged.", e2);
            }
        }
    }

    public final void a(boolean z) {
        fa a2 = a(this.c.zzacw.q);
        if (!(a2 == null || a2.a() == null)) {
            try {
                a2.a().setImmersiveMode(z);
                a2.a().showVideo();
            } catch (RemoteException e2) {
                gv.d("#007 Could not call remote method.", e2);
            }
        }
    }

    public final z b() {
        return this.g;
    }

    public final void c() {
        this.c.zzadv = 0;
        zzbw zzbw = this.c;
        ao.d();
        ev evVar = new ev(this.c.zzrt, this.c.zzacx, this);
        String str = "AdRenderer: ";
        String valueOf = String.valueOf(evVar.getClass().getName());
        gv.b(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        evVar.c();
        zzbw.zzacu = evVar;
    }

    public final void d() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        for (String str : this.d.keySet()) {
            try {
                fa faVar = (fa) this.d.get(str);
                if (!(faVar == null || faVar.a() == null)) {
                    faVar.a().pause();
                }
            } catch (RemoteException e2) {
                gv.d("#007 Could not call remote method.", e2);
            }
        }
    }

    public final void e() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        for (String str : this.d.keySet()) {
            try {
                fa faVar = (fa) this.d.get(str);
                if (!(faVar == null || faVar.a() == null)) {
                    faVar.a().resume();
                }
            } catch (RemoteException e2) {
                gv.d("#007 Could not call remote method.", e2);
            }
        }
    }

    public final void f() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        for (String str : this.d.keySet()) {
            try {
                fa faVar = (fa) this.d.get(str);
                if (!(faVar == null || faVar.a() == null)) {
                    faVar.a().destroy();
                }
            } catch (RemoteException e2) {
                gv.d("#007 Could not call remote method.", e2);
            }
        }
    }

    public final void g() {
        if (this.c.zzacw != null && this.c.zzacw.o != null) {
            ao.x();
            arh.a(this.c.zzrt, this.c.zzacr.zzcw, this.c.zzacw, this.c.zzacp, false, this.c.zzacw.o.l);
        }
    }

    public final void h() {
        if (this.c.zzacw != null && this.c.zzacw.o != null) {
            ao.x();
            arh.a(this.c.zzrt, this.c.zzacr.zzcw, this.c.zzacw, this.c.zzacp, false, this.c.zzacw.o.n);
        }
    }
}
