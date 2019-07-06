package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

@bu
public final class ga {
    public final String A;
    public final String B;
    @Nullable
    public final aln C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    @Nullable
    public final List<String> J;
    public final ahh K;
    public final boolean L;
    public final boolean M;
    public final boolean N;
    public final boolean O;
    private final zzael P;
    private final long Q;
    private final long R;
    private final String S;
    public final zzjj a;
    @Nullable
    public final nn b;
    public final List<String> c;
    public final int d;
    public final List<String> e;
    public final List<String> f;
    public final List<String> g;
    public final int h;
    public final long i;
    public final String j;
    public final JSONObject k;
    public final boolean l;
    public boolean m;
    public final boolean n;
    @Nullable
    public final aqy o;
    @Nullable
    public final zzxq p;
    @Nullable
    public final String q;
    public final aqz r;
    @Nullable
    public final zzxa s;
    @Nullable
    public final String t;
    public final zzjn u;
    @Nullable
    public final zzaig v;
    @Nullable
    public final List<String> w;
    @Nullable
    public final List<String> x;
    public final long y;
    public final long z;

    public ga(gb gbVar, @Nullable nn nnVar, @Nullable aqy aqy, @Nullable zzxq zzxq, @Nullable String str, @Nullable zzxa zzxa, @Nullable aln aln, @Nullable String str2) {
        gb gbVar2 = gbVar;
        zzjj zzjj = gbVar2.a.zzccv;
        List<String> list = gbVar2.b.zzbsn;
        int i2 = gbVar2.e;
        List<String> list2 = gbVar2.b.zzbso;
        List<String> list3 = gbVar2.b.zzces;
        int i3 = gbVar2.b.orientation;
        long j2 = gbVar2.b.zzbsu;
        String str3 = gbVar2.a.zzccy;
        boolean z2 = gbVar2.b.zzceq;
        aqz aqz = gbVar2.c;
        long j3 = gbVar2.b.zzcer;
        zzjn zzjn = gbVar2.d;
        long j4 = j3;
        long j5 = gbVar2.b.zzcep;
        long j6 = gbVar2.f;
        long j7 = gbVar2.g;
        String str4 = gbVar2.b.zzcev;
        long j8 = j7;
        JSONObject jSONObject = gbVar2.h;
        zzaig zzaig = gbVar2.b.zzcfe;
        String str5 = str4;
        List<String> list4 = gbVar2.b.zzcff;
        List<String> list5 = gbVar2.b.zzcff;
        boolean z3 = gbVar2.b.zzcfh;
        zzael zzael = gbVar2.b.zzcfi;
        List<String> list6 = gbVar2.b.zzbsr;
        String str6 = gbVar2.b.zzcfl;
        JSONObject jSONObject2 = jSONObject;
        ahh ahh = gbVar2.i;
        String str7 = str6;
        boolean z4 = gbVar2.b.zzzl;
        ahh ahh2 = ahh;
        boolean z5 = gbVar2.j;
        boolean z6 = z4;
        boolean z7 = gbVar2.b.zzcfp;
        List<String> list7 = gbVar2.b.zzbsp;
        boolean z8 = gbVar2.b.zzzm;
        JSONObject jSONObject3 = jSONObject2;
        ahh ahh3 = ahh2;
        aqz aqz2 = aqz;
        long j9 = j4;
        long j10 = j5;
        long j11 = j6;
        long j12 = j8;
        this(zzjj, null, list, i2, list2, list3, i3, j2, str3, z2, null, null, null, aqz2, null, j9, zzjn, j10, j11, j12, str5, jSONObject3, null, zzaig, list4, list5, z3, zzael, null, list6, str7, ahh3, z6, z5, z7, list7, z8, gbVar2.b.zzcfq);
    }

    public ga(zzjj zzjj, @Nullable nn nnVar, List<String> list, int i2, List<String> list2, List<String> list3, int i3, long j2, String str, boolean z2, @Nullable aqy aqy, @Nullable zzxq zzxq, @Nullable String str2, aqz aqz, @Nullable zzxa zzxa, long j3, zzjn zzjn, long j4, long j5, long j6, String str3, JSONObject jSONObject, @Nullable aln aln, zzaig zzaig, List<String> list4, List<String> list5, boolean z3, zzael zzael, @Nullable String str4, List<String> list6, String str5, ahh ahh, boolean z4, boolean z5, boolean z6, List<String> list7, boolean z7, String str6) {
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = false;
        this.H = false;
        this.I = false;
        this.a = zzjj;
        this.b = nnVar;
        this.c = a(list);
        this.d = i2;
        this.e = a(list2);
        this.g = a(list3);
        this.h = i3;
        this.i = j2;
        this.j = str;
        this.n = z2;
        this.o = aqy;
        this.p = zzxq;
        this.q = str2;
        this.r = aqz;
        this.s = zzxa;
        this.Q = j3;
        this.u = zzjn;
        this.R = j4;
        this.y = j5;
        this.z = j6;
        this.A = str3;
        this.k = jSONObject;
        this.C = aln;
        this.v = zzaig;
        this.w = a(list4);
        this.x = a(list5);
        this.l = z3;
        this.P = zzael;
        this.t = str4;
        this.J = a(list6);
        this.B = str5;
        this.K = ahh;
        this.L = z4;
        this.M = z5;
        this.N = z6;
        this.f = a(list7);
        this.O = z7;
        this.S = str6;
    }

    @Nullable
    private static <T> List<T> a(@Nullable List<T> list) {
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public final boolean a() {
        if (this.b == null || this.b.zzuf() == null) {
            return false;
        }
        return this.b.zzuf().zzfz();
    }
}
