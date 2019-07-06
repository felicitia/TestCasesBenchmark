package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.ao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@bu
public final class arl implements aqx {
    private final zzaef a;
    private final zzxn b;
    private final Context c;
    private final Object d = new Object();
    private final aqz e;
    private final boolean f;
    private final long g;
    private final long h;
    private final aky i;
    private final boolean j;
    private final String k;
    private boolean l = false;
    private arc m;
    private List<arf> n = new ArrayList();
    private final boolean o;

    public arl(Context context, zzaef zzaef, zzxn zzxn, aqz aqz, boolean z, boolean z2, String str, long j2, long j3, aky aky, boolean z3) {
        this.c = context;
        this.a = zzaef;
        this.b = zzxn;
        this.e = aqz;
        this.f = z;
        this.j = z2;
        this.k = str;
        this.g = j2;
        this.h = j3;
        this.i = aky;
        this.o = z3;
    }

    public final arf a(List<aqy> list) {
        Object obj;
        Throwable th;
        arf arf;
        gv.b("Starting mediation.");
        ArrayList arrayList = new ArrayList();
        akw a2 = this.i.a();
        zzjn zzjn = this.a.zzacv;
        int[] iArr = new int[2];
        if (zzjn.zzard != null) {
            ao.x();
            if (arh.a(this.k, iArr)) {
                int i2 = iArr[0];
                int i3 = iArr[1];
                zzjn[] zzjnArr = zzjn.zzard;
                int length = zzjnArr.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        break;
                    }
                    zzjn zzjn2 = zzjnArr[i4];
                    if (i2 == zzjn2.width && i3 == zzjn2.height) {
                        zzjn = zzjn2;
                        break;
                    }
                    i4++;
                }
            }
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            aqy aqy = (aqy) it.next();
            String str = "Trying mediation network: ";
            String valueOf = String.valueOf(aqy.b);
            gv.d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            Iterator it2 = aqy.c.iterator();
            while (true) {
                if (it2.hasNext()) {
                    String str2 = (String) it2.next();
                    akw a3 = this.i.a();
                    Object obj2 = this.d;
                    synchronized (obj2) {
                        try {
                            if (this.l) {
                                try {
                                    arf = new arf(-1);
                                } catch (Throwable th2) {
                                    th = th2;
                                    obj = obj2;
                                    throw th;
                                }
                            } else {
                                Iterator it3 = it;
                                Iterator it4 = it2;
                                akw akw = a2;
                                akw akw2 = a3;
                                r12 = r12;
                                ArrayList arrayList2 = arrayList;
                                arc arc = r12;
                                obj = obj2;
                                try {
                                    arc arc2 = new arc(this.c, str2, this.b, this.e, aqy, this.a.zzccv, zzjn, this.a.zzacr, this.f, this.j, this.a.zzadj, this.a.zzads, this.a.zzcdk, this.a.zzcef, this.o);
                                    this.m = arc;
                                    arf a4 = this.m.a(this.g, this.h);
                                    this.n.add(a4);
                                    if (a4.a == 0) {
                                        gv.b("Adapter succeeded.");
                                        this.i.a("mediation_network_succeed", str2);
                                        ArrayList arrayList3 = arrayList2;
                                        if (!arrayList3.isEmpty()) {
                                            this.i.a("mediation_networks_fail", TextUtils.join(",", arrayList3));
                                        }
                                        this.i.a(akw2, "mls");
                                        this.i.a(akw, "ttm");
                                        return a4;
                                    }
                                    akw akw3 = akw;
                                    akw akw4 = akw2;
                                    ArrayList arrayList4 = arrayList2;
                                    arrayList4.add(str2);
                                    this.i.a(akw4, "mlf");
                                    if (a4.c != null) {
                                        hd.a.post(new arm(this, a4));
                                    }
                                    arrayList = arrayList4;
                                    a2 = akw3;
                                    it = it3;
                                    it2 = it4;
                                } catch (Throwable th3) {
                                    th = th3;
                                    th = th;
                                    throw th;
                                }
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            obj = obj2;
                            th = th;
                            throw th;
                        }
                    }
                    return arf;
                }
            }
        }
        ArrayList arrayList5 = arrayList;
        if (!arrayList5.isEmpty()) {
            this.i.a("mediation_networks_fail", TextUtils.join(",", arrayList5));
        }
        return new arf(1);
    }

    public final void a() {
        synchronized (this.d) {
            this.l = true;
            if (this.m != null) {
                this.m.a();
            }
        }
    }

    public final List<arf> b() {
        return this.n;
    }
}
