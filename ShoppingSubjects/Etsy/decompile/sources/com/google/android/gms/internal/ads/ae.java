package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@bu
public final class ae extends v {
    protected arf g;
    private zzxn h;
    @VisibleForTesting
    private aqx i;
    private aqz j;
    private final aky k;
    /* access modifiers changed from: private */
    public final nn l;
    /* access modifiers changed from: private */
    public boolean m;

    ae(Context context, gb gbVar, zzxn zzxn, z zVar, aky aky, nn nnVar) {
        super(context, gbVar, zVar);
        this.h = zzxn;
        this.j = gbVar.c;
        this.k = aky;
        this.l = nnVar;
    }

    /* access modifiers changed from: protected */
    public final ga a(int i2) {
        aqz aqz;
        boolean z;
        String str;
        long j2;
        zzael zzael;
        String str2;
        aqz aqz2;
        boolean z2;
        String str3;
        long j3;
        int i3;
        zzaef zzaef = this.e.a;
        zzjj zzjj = zzaef.zzccv;
        nn nnVar = this.l;
        List<String> list = this.f.zzbsn;
        List<String> list2 = this.f.zzbso;
        List<String> list3 = this.f.zzces;
        int i4 = this.f.orientation;
        long j4 = this.f.zzbsu;
        String str4 = zzaef.zzccy;
        boolean z3 = this.f.zzceq;
        aqy aqy = this.g != null ? this.g.b : null;
        zzxq zzxq = this.g != null ? this.g.c : null;
        String name = this.g != null ? this.g.d : AdMobAdapter.class.getName();
        aqz aqz3 = this.j;
        zzxa zzxa = this.g != null ? this.g.e : null;
        aqy aqy2 = aqy;
        zzxq zzxq2 = zzxq;
        long j5 = this.f.zzcer;
        zzjn zzjn = this.e.d;
        long j6 = j5;
        long j7 = this.f.zzcep;
        long j8 = this.e.f;
        long j9 = this.f.zzceu;
        String str5 = this.f.zzcev;
        JSONObject jSONObject = this.e.h;
        zzaig zzaig = this.f.zzcfe;
        List<String> list4 = this.f.zzcff;
        List<String> list5 = this.f.zzcfg;
        zzjn zzjn2 = zzjn;
        boolean z4 = this.j != null ? this.j.o : false;
        zzael zzael2 = this.f.zzcfi;
        if (this.i != null) {
            List b = this.i.b();
            zzael = zzael2;
            String str6 = "";
            if (b == null) {
                aqz = aqz3;
                str2 = str6.toString();
                str = str4;
                z = z3;
                j2 = j9;
            } else {
                Iterator it = b.iterator();
                while (it.hasNext()) {
                    Iterator it2 = it;
                    arf arf = (arf) it.next();
                    if (arf != null) {
                        j3 = j9;
                        if (arf.b == null || TextUtils.isEmpty(arf.b.d)) {
                            aqz2 = aqz3;
                            str3 = str4;
                            z2 = z3;
                        } else {
                            String valueOf = String.valueOf(str6);
                            String str7 = arf.b.d;
                            switch (arf.a) {
                                case -1:
                                    i3 = 4;
                                    break;
                                case 0:
                                    str3 = str4;
                                    z2 = z3;
                                    i3 = 0;
                                    break;
                                case 1:
                                    str3 = str4;
                                    z2 = z3;
                                    i3 = 1;
                                    break;
                                case 3:
                                    i3 = 2;
                                    break;
                                case 4:
                                    i3 = 3;
                                    break;
                                case 5:
                                    i3 = 5;
                                    break;
                                default:
                                    i3 = 6;
                                    break;
                            }
                            str3 = str4;
                            z2 = z3;
                            long j10 = arf.g;
                            aqz2 = aqz3;
                            StringBuilder sb = new StringBuilder(33 + String.valueOf(str7).length());
                            sb.append(str7);
                            sb.append(".");
                            sb.append(i3);
                            sb.append(".");
                            sb.append(j10);
                            String sb2 = sb.toString();
                            StringBuilder sb3 = new StringBuilder(1 + String.valueOf(valueOf).length() + String.valueOf(sb2).length());
                            sb3.append(valueOf);
                            sb3.append(sb2);
                            sb3.append("_");
                            str6 = sb3.toString();
                        }
                    } else {
                        aqz2 = aqz3;
                        str3 = str4;
                        z2 = z3;
                        j3 = j9;
                    }
                    it = it2;
                    j9 = j3;
                    str4 = str3;
                    z3 = z2;
                    aqz3 = aqz2;
                }
                aqz = aqz3;
                str = str4;
                z = z3;
                j2 = j9;
                str2 = str6.substring(0, Math.max(0, str6.length() - 1));
            }
        } else {
            aqz = aqz3;
            zzael = zzael2;
            str = str4;
            z = z3;
            j2 = j9;
            str2 = null;
        }
        List<String> list6 = this.f.zzbsr;
        String str8 = this.f.zzcfl;
        ahh ahh = this.e.i;
        boolean z5 = this.f.zzzl;
        boolean z6 = z5;
        ahh ahh2 = ahh;
        aqy aqy3 = aqy2;
        String str9 = str8;
        zzxq zzxq3 = zzxq2;
        aqz aqz4 = aqz;
        ga gaVar = new ga(zzjj, nnVar, list, i2, list2, list3, i4, j4, str, z, aqy3, zzxq3, name, aqz4, zzxa, j6, zzjn2, j7, j8, j2, str5, jSONObject, null, zzaig, list4, list5, z4, zzael, str2, list6, str9, ahh2, z6, this.e.j, this.f.zzcfp, this.f.zzbsp, this.f.zzzm, this.f.zzcfq);
        return gaVar;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r3v3, types: [com.google.android.gms.internal.ads.aqx] */
    /* JADX WARNING: type inference failed for: r18v0, types: [com.google.android.gms.internal.ads.arl] */
    /* JADX WARNING: type inference failed for: r5v4, types: [com.google.android.gms.internal.ads.ari] */
    /* JADX WARNING: type inference failed for: r18v2, types: [com.google.android.gms.internal.ads.arl] */
    /* JADX WARNING: type inference failed for: r5v5, types: [com.google.android.gms.internal.ads.ari] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r18v2, types: [com.google.android.gms.internal.ads.arl]
      assigns: [com.google.android.gms.internal.ads.arl, com.google.android.gms.internal.ads.ari]
      uses: [com.google.android.gms.internal.ads.arl, com.google.android.gms.internal.ads.aqx, com.google.android.gms.internal.ads.ari]
      mth insns count: 154
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/183835416.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/170174037.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0104  */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(long r33) throws com.google.android.gms.internal.ads.zzabk {
        /*
            r32 = this;
            r1 = r32
            java.lang.Object r2 = r1.d
            monitor-enter(r2)
            com.google.android.gms.internal.ads.aqz r3 = r1.j     // Catch:{ all -> 0x0177 }
            int r3 = r3.m     // Catch:{ all -> 0x0177 }
            r4 = -1
            if (r3 == r4) goto L_0x0043
            com.google.android.gms.internal.ads.ari r3 = new com.google.android.gms.internal.ads.ari     // Catch:{ all -> 0x0177 }
            android.content.Context r6 = r1.b     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.gb r4 = r1.e     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.zzaef r7 = r4.a     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.zzxn r8 = r1.h     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.aqz r9 = r1.j     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.zzaej r4 = r1.f     // Catch:{ all -> 0x0177 }
            boolean r10 = r4.zzare     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.zzaej r4 = r1.f     // Catch:{ all -> 0x0177 }
            boolean r11 = r4.zzarg     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.zzaej r4 = r1.f     // Catch:{ all -> 0x0177 }
            java.lang.String r12 = r4.zzcfj     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.akb<java.lang.Long> r4 = com.google.android.gms.internal.ads.akl.bB     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.akj r5 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x0177 }
            java.lang.Object r4 = r5.a(r4)     // Catch:{ all -> 0x0177 }
            java.lang.Long r4 = (java.lang.Long) r4     // Catch:{ all -> 0x0177 }
            long r15 = r4.longValue()     // Catch:{ all -> 0x0177 }
            r17 = 2
            com.google.android.gms.internal.ads.gb r4 = r1.e     // Catch:{ all -> 0x0177 }
            boolean r4 = r4.j     // Catch:{ all -> 0x0177 }
            r5 = r3
            r13 = r33
            r18 = r4
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r15, r17, r18)     // Catch:{ all -> 0x0177 }
            goto L_0x008a
        L_0x0043:
            com.google.android.gms.internal.ads.arl r3 = new com.google.android.gms.internal.ads.arl     // Catch:{ all -> 0x0177 }
            android.content.Context r4 = r1.b     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.gb r5 = r1.e     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.zzaef r5 = r5.a     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.zzxn r6 = r1.h     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.aqz r7 = r1.j     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.zzaej r8 = r1.f     // Catch:{ all -> 0x0177 }
            boolean r8 = r8.zzare     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.zzaej r9 = r1.f     // Catch:{ all -> 0x0177 }
            boolean r9 = r9.zzarg     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.zzaej r10 = r1.f     // Catch:{ all -> 0x0177 }
            java.lang.String r10 = r10.zzcfj     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.akb<java.lang.Long> r11 = com.google.android.gms.internal.ads.akl.bB     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.akj r12 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x0177 }
            java.lang.Object r11 = r12.a(r11)     // Catch:{ all -> 0x0177 }
            java.lang.Long r11 = (java.lang.Long) r11     // Catch:{ all -> 0x0177 }
            long r28 = r11.longValue()     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.aky r11 = r1.k     // Catch:{ all -> 0x0177 }
            com.google.android.gms.internal.ads.gb r12 = r1.e     // Catch:{ all -> 0x0177 }
            boolean r12 = r12.j     // Catch:{ all -> 0x0177 }
            r18 = r3
            r19 = r4
            r20 = r5
            r21 = r6
            r22 = r7
            r23 = r8
            r24 = r9
            r25 = r10
            r26 = r33
            r30 = r11
            r31 = r12
            r18.<init>(r19, r20, r21, r22, r23, r24, r25, r26, r28, r30, r31)     // Catch:{ all -> 0x0177 }
        L_0x008a:
            r1.i = r3     // Catch:{ all -> 0x0177 }
            monitor-exit(r2)     // Catch:{ all -> 0x0177 }
            java.util.ArrayList r2 = new java.util.ArrayList
            com.google.android.gms.internal.ads.aqz r3 = r1.j
            java.util.List<com.google.android.gms.internal.ads.aqy> r3 = r3.a
            r2.<init>(r3)
            com.google.android.gms.internal.ads.gb r3 = r1.e
            com.google.android.gms.internal.ads.zzaef r3 = r3.a
            com.google.android.gms.internal.ads.zzjj r3 = r3.zzccv
            android.os.Bundle r3 = r3.zzaqg
            java.lang.String r4 = "com.google.ads.mediation.admob.AdMobAdapter"
            r5 = 0
            if (r3 == 0) goto L_0x00b0
            android.os.Bundle r3 = r3.getBundle(r4)
            if (r3 == 0) goto L_0x00b0
            java.lang.String r6 = "_skipMediation"
            boolean r3 = r3.getBoolean(r6)
            goto L_0x00b1
        L_0x00b0:
            r3 = r5
        L_0x00b1:
            if (r3 == 0) goto L_0x00cf
            java.util.ListIterator r3 = r2.listIterator()
        L_0x00b7:
            boolean r6 = r3.hasNext()
            if (r6 == 0) goto L_0x00cf
            java.lang.Object r6 = r3.next()
            com.google.android.gms.internal.ads.aqy r6 = (com.google.android.gms.internal.ads.aqy) r6
            java.util.List<java.lang.String> r6 = r6.c
            boolean r6 = r6.contains(r4)
            if (r6 != 0) goto L_0x00b7
            r3.remove()
            goto L_0x00b7
        L_0x00cf:
            com.google.android.gms.internal.ads.aqx r3 = r1.i
            com.google.android.gms.internal.ads.arf r2 = r3.a(r2)
            r1.g = r2
            com.google.android.gms.internal.ads.arf r2 = r1.g
            int r2 = r2.a
            switch(r2) {
                case 0: goto L_0x0104;
                case 1: goto L_0x00fb;
                default: goto L_0x00de;
            }
        L_0x00de:
            com.google.android.gms.internal.ads.zzabk r2 = new com.google.android.gms.internal.ads.zzabk
            com.google.android.gms.internal.ads.arf r3 = r1.g
            int r3 = r3.a
            r4 = 40
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r4)
            java.lang.String r4 = "Unexpected mediation result: "
            r6.append(r4)
            r6.append(r3)
            java.lang.String r3 = r6.toString()
            r2.<init>(r3, r5)
            throw r2
        L_0x00fb:
            com.google.android.gms.internal.ads.zzabk r2 = new com.google.android.gms.internal.ads.zzabk
            java.lang.String r3 = "No fill from any mediation ad networks."
            r4 = 3
            r2.<init>(r3, r4)
            throw r2
        L_0x0104:
            com.google.android.gms.internal.ads.arf r2 = r1.g
            com.google.android.gms.internal.ads.aqy r2 = r2.b
            if (r2 == 0) goto L_0x0176
            com.google.android.gms.internal.ads.arf r2 = r1.g
            com.google.android.gms.internal.ads.aqy r2 = r2.b
            java.lang.String r2 = r2.o
            if (r2 == 0) goto L_0x0176
            java.util.concurrent.CountDownLatch r2 = new java.util.concurrent.CountDownLatch
            r3 = 1
            r2.<init>(r3)
            android.os.Handler r3 = com.google.android.gms.internal.ads.hd.a
            com.google.android.gms.internal.ads.af r4 = new com.google.android.gms.internal.ads.af
            r4.<init>(r1, r2)
            r3.post(r4)
            r3 = 10
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x014e }
            r2.await(r3, r6)     // Catch:{ InterruptedException -> 0x014e }
            java.lang.Object r2 = r1.d
            monitor-enter(r2)
            boolean r3 = r1.m     // Catch:{ all -> 0x014a }
            if (r3 != 0) goto L_0x0138
            com.google.android.gms.internal.ads.zzabk r3 = new com.google.android.gms.internal.ads.zzabk     // Catch:{ all -> 0x014a }
            java.lang.String r4 = "View could not be prepared"
            r3.<init>(r4, r5)     // Catch:{ all -> 0x014a }
            throw r3     // Catch:{ all -> 0x014a }
        L_0x0138:
            com.google.android.gms.internal.ads.nn r3 = r1.l     // Catch:{ all -> 0x014a }
            boolean r3 = r3.isDestroyed()     // Catch:{ all -> 0x014a }
            if (r3 == 0) goto L_0x0148
            com.google.android.gms.internal.ads.zzabk r3 = new com.google.android.gms.internal.ads.zzabk     // Catch:{ all -> 0x014a }
            java.lang.String r4 = "Assets not loaded, web view is destroyed"
            r3.<init>(r4, r5)     // Catch:{ all -> 0x014a }
            throw r3     // Catch:{ all -> 0x014a }
        L_0x0148:
            monitor-exit(r2)     // Catch:{ all -> 0x014a }
            return
        L_0x014a:
            r0 = move-exception
            r3 = r0
            monitor-exit(r2)     // Catch:{ all -> 0x014a }
            throw r3
        L_0x014e:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.internal.ads.zzabk r3 = new com.google.android.gms.internal.ads.zzabk
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r4 = 38
            java.lang.String r6 = java.lang.String.valueOf(r2)
            int r6 = r6.length()
            int r4 = r4 + r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r4)
            java.lang.String r4 = "Interrupted while waiting for latch : "
            r6.append(r4)
            r6.append(r2)
            java.lang.String r2 = r6.toString()
            r3.<init>(r2, r5)
            throw r3
        L_0x0176:
            return
        L_0x0177:
            r0 = move-exception
            r3 = r0
            monitor-exit(r2)     // Catch:{ all -> 0x0177 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.ae.a(long):void");
    }

    public final void c_() {
        synchronized (this.d) {
            super.c_();
            if (this.i != null) {
                this.i.a();
            }
        }
    }
}
