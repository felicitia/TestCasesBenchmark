package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@bu
public final class ev extends gq implements eu {
    private final gb a;
    private final Context b;
    private final ArrayList<em> c;
    private final List<ep> d;
    private final HashSet<String> e;
    private final Object f;
    private final eg g;
    private final long h;

    public ev(Context context, gb gbVar, eg egVar) {
        Context context2 = context;
        gb gbVar2 = gbVar;
        eg egVar2 = egVar;
        this(context2, gbVar2, egVar2, ((Long) ajh.f().a(akl.aE)).longValue());
    }

    @VisibleForTesting
    private ev(Context context, gb gbVar, eg egVar, long j) {
        this.c = new ArrayList<>();
        this.d = new ArrayList();
        this.e = new HashSet<>();
        this.f = new Object();
        this.b = context;
        this.a = gbVar;
        this.g = egVar;
        this.h = j;
    }

    private final ga a(int i, @Nullable String str, @Nullable aqy aqy) {
        boolean z;
        long j;
        String str2;
        zzjn zzjn;
        String str3;
        long j2;
        int i2;
        zzjj zzjj = this.a.a.zzccv;
        List<String> list = this.a.b.zzbsn;
        List<String> list2 = this.a.b.zzbso;
        List<String> list3 = this.a.b.zzces;
        int i3 = this.a.b.orientation;
        long j3 = this.a.b.zzbsu;
        String str4 = this.a.a.zzccy;
        boolean z2 = this.a.b.zzceq;
        aqz aqz = this.a.c;
        long j4 = this.a.b.zzcer;
        zzjn zzjn2 = this.a.d;
        long j5 = j4;
        aqz aqz2 = aqz;
        long j6 = this.a.b.zzcep;
        long j7 = this.a.f;
        long j8 = this.a.b.zzceu;
        String str5 = this.a.b.zzcev;
        JSONObject jSONObject = this.a.h;
        zzaig zzaig = this.a.b.zzcfe;
        List<String> list4 = this.a.b.zzcff;
        List<String> list5 = this.a.b.zzcfg;
        boolean z3 = this.a.b.zzcfh;
        zzael zzael = this.a.b.zzcfi;
        JSONObject jSONObject2 = jSONObject;
        StringBuilder sb = new StringBuilder("");
        if (this.d == null) {
            str3 = sb.toString();
            zzjn = zzjn2;
            z = z2;
            str2 = str5;
            j = j8;
        } else {
            Iterator it = this.d.iterator();
            while (true) {
                int i4 = 1;
                zzjn = zzjn2;
                if (it.hasNext()) {
                    ep epVar = (ep) it.next();
                    if (epVar != null) {
                        Iterator it2 = it;
                        if (!TextUtils.isEmpty(epVar.a)) {
                            String str6 = epVar.a;
                            String str7 = str5;
                            switch (epVar.b) {
                                case 3:
                                    break;
                                case 4:
                                    i4 = 2;
                                    break;
                                case 5:
                                    i4 = 4;
                                    break;
                                case 6:
                                    j2 = j8;
                                    i2 = 0;
                                    break;
                                case 7:
                                    i4 = 3;
                                    break;
                                default:
                                    i4 = 6;
                                    break;
                            }
                            j2 = j8;
                            i2 = i4;
                            long j9 = epVar.c;
                            boolean z4 = z2;
                            StringBuilder sb2 = new StringBuilder(33 + String.valueOf(str6).length());
                            sb2.append(str6);
                            sb2.append(".");
                            sb2.append(i2);
                            sb2.append(".");
                            sb2.append(j9);
                            sb.append(String.valueOf(sb2.toString()).concat("_"));
                            zzjn2 = zzjn;
                            it = it2;
                            str5 = str7;
                            j8 = j2;
                            z2 = z4;
                        } else {
                            zzjn2 = zzjn;
                            it = it2;
                        }
                    } else {
                        zzjn2 = zzjn;
                    }
                } else {
                    z = z2;
                    str2 = str5;
                    j = j8;
                    str3 = sb.substring(0, Math.max(0, sb.length() - 1));
                }
            }
        }
        List<String> list6 = this.a.b.zzbsr;
        String str8 = this.a.b.zzcfl;
        ahh ahh = this.a.i;
        boolean z5 = this.a.b.zzzl;
        boolean z6 = this.a.j;
        boolean z7 = this.a.b.zzcfp;
        List<String> list7 = this.a.b.zzbsp;
        boolean z8 = z7;
        JSONObject jSONObject3 = jSONObject2;
        boolean z9 = z6;
        zzjn zzjn3 = zzjn;
        int i5 = i;
        boolean z10 = z5;
        boolean z11 = z;
        ahh ahh2 = ahh;
        String str9 = str2;
        aqy aqy2 = aqy;
        aqz aqz3 = aqz2;
        List<String> list8 = list6;
        String str10 = str;
        long j10 = j5;
        long j11 = j6;
        long j12 = j7;
        long j13 = j;
        List<String> list9 = list8;
        String str11 = str8;
        ga gaVar = new ga(zzjj, null, list, i5, list2, list3, i3, j3, str4, z11, aqy2, null, str10, aqz3, null, j10, zzjn3, j11, j12, j13, str9, jSONObject3, null, zzaig, list4, list5, z3, zzael, str3, list9, str11, ahh2, z10, z9, z8, list7, this.a.b.zzzm, this.a.b.zzcfq);
        return gaVar;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:93:0x016e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
            r20 = this;
            r11 = r20
            com.google.android.gms.internal.ads.gb r1 = r11.a
            com.google.android.gms.internal.ads.aqz r1 = r1.c
            java.util.List<com.google.android.gms.internal.ads.aqy> r1 = r1.a
            java.util.Iterator r12 = r1.iterator()
        L_0x000c:
            boolean r1 = r12.hasNext()
            if (r1 == 0) goto L_0x00c9
            java.lang.Object r1 = r12.next()
            r13 = r1
            com.google.android.gms.internal.ads.aqy r13 = (com.google.android.gms.internal.ads.aqy) r13
            java.lang.String r14 = r13.k
            java.util.List<java.lang.String> r1 = r13.c
            java.util.Iterator r15 = r1.iterator()
        L_0x0021:
            boolean r1 = r15.hasNext()
            if (r1 == 0) goto L_0x000c
            java.lang.Object r1 = r15.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L_0x0040
            java.lang.String r2 = "com.google.ads.mediation.customevent.CustomEventAdapter"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x003e
            goto L_0x0040
        L_0x003e:
            r3 = r1
            goto L_0x004c
        L_0x0040:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00bc }
            r1.<init>(r14)     // Catch:{ JSONException -> 0x00bc }
            java.lang.String r2 = "class_name"
            java.lang.String r1 = r1.getString(r2)     // Catch:{ JSONException -> 0x00bc }
            goto L_0x003e
        L_0x004c:
            java.lang.Object r9 = r11.f
            monitor-enter(r9)
            com.google.android.gms.internal.ads.eg r1 = r11.g     // Catch:{ all -> 0x00b4 }
            com.google.android.gms.internal.ads.fa r7 = r1.a(r3)     // Catch:{ all -> 0x00b4 }
            if (r7 == 0) goto L_0x008c
            com.google.android.gms.internal.ads.zzahv r1 = r7.b()     // Catch:{ all -> 0x00b4 }
            if (r1 == 0) goto L_0x008c
            com.google.android.gms.internal.ads.zzxq r1 = r7.a()     // Catch:{ all -> 0x00b4 }
            if (r1 != 0) goto L_0x0064
            goto L_0x008c
        L_0x0064:
            com.google.android.gms.internal.ads.em r10 = new com.google.android.gms.internal.ads.em     // Catch:{ all -> 0x00b4 }
            android.content.Context r2 = r11.b     // Catch:{ all -> 0x00b4 }
            com.google.android.gms.internal.ads.gb r6 = r11.a     // Catch:{ all -> 0x00b4 }
            long r4 = r11.h     // Catch:{ all -> 0x00b4 }
            r1 = r10
            r16 = r4
            r4 = r14
            r5 = r13
            r8 = r11
            r18 = r9
            r19 = r12
            r12 = r10
            r9 = r16
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00ba }
            com.google.android.gms.internal.ads.eg r1 = r11.g     // Catch:{ all -> 0x00ba }
            com.google.android.gms.ads.internal.gmsg.k r1 = r1.a()     // Catch:{ all -> 0x00ba }
            r12.a(r1)     // Catch:{ all -> 0x00ba }
            java.util.ArrayList<com.google.android.gms.internal.ads.em> r1 = r11.c     // Catch:{ all -> 0x00ba }
            r1.add(r12)     // Catch:{ all -> 0x00ba }
        L_0x008a:
            monitor-exit(r18)     // Catch:{ all -> 0x00ba }
            goto L_0x00c5
        L_0x008c:
            r18 = r9
            r19 = r12
            java.util.List<com.google.android.gms.internal.ads.ep> r1 = r11.d     // Catch:{ all -> 0x00ba }
            com.google.android.gms.internal.ads.er r2 = new com.google.android.gms.internal.ads.er     // Catch:{ all -> 0x00ba }
            r2.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r4 = r13.d     // Catch:{ all -> 0x00ba }
            com.google.android.gms.internal.ads.er r2 = r2.b(r4)     // Catch:{ all -> 0x00ba }
            com.google.android.gms.internal.ads.er r2 = r2.a(r3)     // Catch:{ all -> 0x00ba }
            r3 = 0
            com.google.android.gms.internal.ads.er r2 = r2.a(r3)     // Catch:{ all -> 0x00ba }
            r3 = 7
            com.google.android.gms.internal.ads.er r2 = r2.a(r3)     // Catch:{ all -> 0x00ba }
            com.google.android.gms.internal.ads.ep r2 = r2.a()     // Catch:{ all -> 0x00ba }
            r1.add(r2)     // Catch:{ all -> 0x00ba }
            goto L_0x008a
        L_0x00b4:
            r0 = move-exception
            r18 = r9
        L_0x00b7:
            r1 = r0
            monitor-exit(r18)     // Catch:{ all -> 0x00ba }
            throw r1
        L_0x00ba:
            r0 = move-exception
            goto L_0x00b7
        L_0x00bc:
            r0 = move-exception
            r19 = r12
            r1 = r0
            java.lang.String r2 = "Unable to determine custom event class name, skipping..."
            com.google.android.gms.internal.ads.gv.b(r2, r1)
        L_0x00c5:
            r12 = r19
            goto L_0x0021
        L_0x00c9:
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.util.ArrayList<com.google.android.gms.internal.ads.em> r2 = r11.c
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            int r3 = r2.size()
            r4 = 0
            r5 = r4
        L_0x00d8:
            if (r5 >= r3) goto L_0x00ee
            java.lang.Object r6 = r2.get(r5)
            int r5 = r5 + 1
            com.google.android.gms.internal.ads.em r6 = (com.google.android.gms.internal.ads.em) r6
            java.lang.String r7 = r6.a
            boolean r7 = r1.add(r7)
            if (r7 == 0) goto L_0x00d8
            r6.d()
            goto L_0x00d8
        L_0x00ee:
            java.util.ArrayList<com.google.android.gms.internal.ads.em> r1 = r11.c
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            int r2 = r1.size()
        L_0x00f6:
            if (r4 >= r2) goto L_0x01a9
            java.lang.Object r3 = r1.get(r4)
            int r4 = r4 + 1
            com.google.android.gms.internal.ads.em r3 = (com.google.android.gms.internal.ads.em) r3
            java.util.concurrent.Future r5 = r3.d()     // Catch:{ InterruptedException -> 0x016e, Exception -> 0x014d }
            r5.get()     // Catch:{ InterruptedException -> 0x016e, Exception -> 0x014d }
            java.lang.Object r5 = r11.f
            monitor-enter(r5)
            java.lang.String r6 = r3.a     // Catch:{ all -> 0x0146 }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x0146 }
            if (r6 != 0) goto L_0x011b
            java.util.List<com.google.android.gms.internal.ads.ep> r6 = r11.d     // Catch:{ all -> 0x0146 }
            com.google.android.gms.internal.ads.ep r7 = r3.e()     // Catch:{ all -> 0x0146 }
            r6.add(r7)     // Catch:{ all -> 0x0146 }
        L_0x011b:
            monitor-exit(r5)     // Catch:{ all -> 0x0146 }
            java.lang.Object r6 = r11.f
            monitor-enter(r6)
            java.util.HashSet<java.lang.String> r5 = r11.e     // Catch:{ all -> 0x0142 }
            java.lang.String r7 = r3.a     // Catch:{ all -> 0x0142 }
            boolean r5 = r5.contains(r7)     // Catch:{ all -> 0x0142 }
            if (r5 == 0) goto L_0x0140
            java.lang.String r1 = r3.a     // Catch:{ all -> 0x0142 }
            com.google.android.gms.internal.ads.aqy r2 = r3.f()     // Catch:{ all -> 0x0142 }
            r3 = -2
            com.google.android.gms.internal.ads.ga r1 = r11.a(r3, r1, r2)     // Catch:{ all -> 0x0142 }
            android.os.Handler r2 = com.google.android.gms.internal.ads.jp.a     // Catch:{ all -> 0x0142 }
            com.google.android.gms.internal.ads.ew r3 = new com.google.android.gms.internal.ads.ew     // Catch:{ all -> 0x0142 }
            r3.<init>(r11, r1)     // Catch:{ all -> 0x0142 }
            r2.post(r3)     // Catch:{ all -> 0x0142 }
            monitor-exit(r6)     // Catch:{ all -> 0x0142 }
            return
        L_0x0140:
            monitor-exit(r6)     // Catch:{ all -> 0x0142 }
            goto L_0x00f6
        L_0x0142:
            r0 = move-exception
            r1 = r0
            monitor-exit(r6)     // Catch:{ all -> 0x0142 }
            throw r1
        L_0x0146:
            r0 = move-exception
            r1 = r0
            monitor-exit(r5)     // Catch:{ all -> 0x0146 }
            throw r1
        L_0x014a:
            r0 = move-exception
            r1 = r0
            goto L_0x018f
        L_0x014d:
            r0 = move-exception
            r5 = r0
            java.lang.String r6 = "Unable to resolve rewarded adapter."
            com.google.android.gms.internal.ads.gv.c(r6, r5)     // Catch:{ all -> 0x014a }
            java.lang.Object r5 = r11.f
            monitor-enter(r5)
            java.lang.String r6 = r3.a     // Catch:{ all -> 0x016a }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x016a }
            if (r6 != 0) goto L_0x0168
            java.util.List<com.google.android.gms.internal.ads.ep> r6 = r11.d     // Catch:{ all -> 0x016a }
            com.google.android.gms.internal.ads.ep r3 = r3.e()     // Catch:{ all -> 0x016a }
            r6.add(r3)     // Catch:{ all -> 0x016a }
        L_0x0168:
            monitor-exit(r5)     // Catch:{ all -> 0x016a }
            goto L_0x00f6
        L_0x016a:
            r0 = move-exception
            r1 = r0
            monitor-exit(r5)     // Catch:{ all -> 0x016a }
            throw r1
        L_0x016e:
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x014a }
            r1.interrupt()     // Catch:{ all -> 0x014a }
            java.lang.Object r1 = r11.f
            monitor-enter(r1)
            java.lang.String r2 = r3.a     // Catch:{ all -> 0x018b }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x018b }
            if (r2 != 0) goto L_0x0189
            java.util.List<com.google.android.gms.internal.ads.ep> r2 = r11.d     // Catch:{ all -> 0x018b }
            com.google.android.gms.internal.ads.ep r3 = r3.e()     // Catch:{ all -> 0x018b }
            r2.add(r3)     // Catch:{ all -> 0x018b }
        L_0x0189:
            monitor-exit(r1)     // Catch:{ all -> 0x018b }
            goto L_0x01a9
        L_0x018b:
            r0 = move-exception
            r2 = r0
            monitor-exit(r1)     // Catch:{ all -> 0x018b }
            throw r2
        L_0x018f:
            java.lang.Object r2 = r11.f
            monitor-enter(r2)
            java.lang.String r4 = r3.a     // Catch:{ all -> 0x01a5 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x01a5 }
            if (r4 != 0) goto L_0x01a3
            java.util.List<com.google.android.gms.internal.ads.ep> r4 = r11.d     // Catch:{ all -> 0x01a5 }
            com.google.android.gms.internal.ads.ep r3 = r3.e()     // Catch:{ all -> 0x01a5 }
            r4.add(r3)     // Catch:{ all -> 0x01a5 }
        L_0x01a3:
            monitor-exit(r2)     // Catch:{ all -> 0x01a5 }
            throw r1
        L_0x01a5:
            r0 = move-exception
            r1 = r0
            monitor-exit(r2)     // Catch:{ all -> 0x01a5 }
            throw r1
        L_0x01a9:
            r1 = 3
            r2 = 0
            com.google.android.gms.internal.ads.ga r1 = r11.a(r1, r2, r2)
            android.os.Handler r2 = com.google.android.gms.internal.ads.jp.a
            com.google.android.gms.internal.ads.ex r3 = new com.google.android.gms.internal.ads.ex
            r3.<init>(r11, r1)
            r2.post(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.ev.a():void");
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(ga gaVar) {
        this.g.b().zzb(gaVar);
    }

    public final void a(String str) {
        synchronized (this.f) {
            this.e.add(str);
        }
    }

    public final void a(String str, int i) {
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(ga gaVar) {
        this.g.b().zzb(gaVar);
    }

    public final void c_() {
    }
}
