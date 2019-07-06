package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.xh.e;

public final class sv extends xh<sv, a> implements ym {
    private static volatile yw<sv> zzakh;
    /* access modifiers changed from: private */
    public static final sv zzdjt = new sv();
    private int zzdih;
    private sr zzdjj;
    private zzbah zzdjr = zzbah.zzdpq;
    private zzbah zzdjs = zzbah.zzdpq;

    public static final class a extends com.google.android.gms.internal.ads.xh.a<sv, a> implements ym {
        private a() {
            super(sv.zzdjt);
        }

        /* synthetic */ a(sw swVar) {
            this();
        }

        public final a a(int i) {
            b();
            ((sv) this.a).b(0);
            return this;
        }

        public final a a(sr srVar) {
            b();
            ((sv) this.a).a(srVar);
            return this;
        }

        public final a a(zzbah zzbah) {
            b();
            ((sv) this.a).b(zzbah);
            return this;
        }

        public final a b(zzbah zzbah) {
            b();
            ((sv) this.a).c(zzbah);
            return this;
        }
    }

    static {
        xh.a(sv.class, zzdjt);
    }

    private sv() {
    }

    public static sv a(zzbah zzbah) throws zzbbu {
        return (sv) xh.a(zzdjt, zzbah);
    }

    /* access modifiers changed from: private */
    public final void a(sr srVar) {
        if (srVar == null) {
            throw new NullPointerException();
        }
        this.zzdjj = srVar;
    }

    /* access modifiers changed from: private */
    public final void b(int i) {
        this.zzdih = i;
    }

    /* access modifiers changed from: private */
    public final void b(zzbah zzbah) {
        if (zzbah == null) {
            throw new NullPointerException();
        }
        this.zzdjr = zzbah;
    }

    /* access modifiers changed from: private */
    public final void c(zzbah zzbah) {
        if (zzbah == null) {
            throw new NullPointerException();
        }
        this.zzdjs = zzbah;
    }

    public static a e() {
        return (a) ((com.google.android.gms.internal.ads.xh.a) zzdjt.a(e.e, (Object) null, (Object) null));
    }

    public static sv f() {
        return zzdjt;
    }

    public final int a() {
        return this.zzdih;
    }

    /* JADX WARNING: type inference failed for: r2v11, types: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.sv>] */
    /* JADX WARNING: type inference failed for: r2v12, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v13, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.sv>] */
    /* JADX WARNING: type inference failed for: r2v16 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.sv>]
      assigns: [com.google.android.gms.internal.ads.xh$b]
      uses: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.sv>]
      mth insns count: 43
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
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$38/2083670723.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object a(int r2, java.lang.Object r3, java.lang.Object r4) {
        /*
            r1 = this;
            int[] r3 = com.google.android.gms.internal.ads.sw.a
            r4 = 1
            int r2 = r2 - r4
            r2 = r3[r2]
            r3 = 0
            switch(r2) {
                case 1: goto L_0x0058;
                case 2: goto L_0x0052;
                case 3: goto L_0x0033;
                case 4: goto L_0x0030;
                case 5: goto L_0x0016;
                case 6: goto L_0x0011;
                case 7: goto L_0x0010;
                default: goto L_0x000a;
            }
        L_0x000a:
            java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
            r2.<init>()
            throw r2
        L_0x0010:
            return r3
        L_0x0011:
            java.lang.Byte r2 = java.lang.Byte.valueOf(r4)
            return r2
        L_0x0016:
            com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.sv> r2 = zzakh
            if (r2 != 0) goto L_0x002f
            java.lang.Class<com.google.android.gms.internal.ads.sv> r3 = com.google.android.gms.internal.ads.sv.class
            monitor-enter(r3)
            com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.sv> r2 = zzakh     // Catch:{ all -> 0x002c }
            if (r2 != 0) goto L_0x002a
            com.google.android.gms.internal.ads.xh$b r2 = new com.google.android.gms.internal.ads.xh$b     // Catch:{ all -> 0x002c }
            com.google.android.gms.internal.ads.sv r4 = zzdjt     // Catch:{ all -> 0x002c }
            r2.<init>(r4)     // Catch:{ all -> 0x002c }
            zzakh = r2     // Catch:{ all -> 0x002c }
        L_0x002a:
            monitor-exit(r3)     // Catch:{ all -> 0x002c }
            return r2
        L_0x002c:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x002c }
            throw r2
        L_0x002f:
            return r2
        L_0x0030:
            com.google.android.gms.internal.ads.sv r2 = zzdjt
            return r2
        L_0x0033:
            r2 = 4
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            java.lang.String r0 = "zzdih"
            r2[r3] = r0
            java.lang.String r3 = "zzdjj"
            r2[r4] = r3
            r3 = 2
            java.lang.String r4 = "zzdjr"
            r2[r3] = r4
            r3 = 3
            java.lang.String r4 = "zzdjs"
            r2[r3] = r4
            java.lang.String r3 = "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0005\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n\u0004\n"
            com.google.android.gms.internal.ads.sv r4 = zzdjt
            java.lang.Object r2 = a(r4, r3, r2)
            return r2
        L_0x0052:
            com.google.android.gms.internal.ads.sv$a r2 = new com.google.android.gms.internal.ads.sv$a
            r2.<init>(r3)
            return r2
        L_0x0058:
            com.google.android.gms.internal.ads.sv r2 = new com.google.android.gms.internal.ads.sv
            r2.<init>()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.sv.a(int, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public final sr b() {
        return this.zzdjj == null ? sr.d() : this.zzdjj;
    }

    public final zzbah c() {
        return this.zzdjr;
    }

    public final zzbah d() {
        return this.zzdjs;
    }
}
