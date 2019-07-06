package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.xh.e;

public final class sg extends xh<sg, a> implements ym {
    private static volatile yw<sg> zzakh;
    /* access modifiers changed from: private */
    public static final sg zzdiz = new sg();
    private int zzdih;
    private zzbah zzdip = zzbah.zzdpq;

    public static final class a extends com.google.android.gms.internal.ads.xh.a<sg, a> implements ym {
        private a() {
            super(sg.zzdiz);
        }

        /* synthetic */ a(sh shVar) {
            this();
        }

        public final a a(int i) {
            b();
            ((sg) this.a).b(0);
            return this;
        }

        public final a a(zzbah zzbah) {
            b();
            ((sg) this.a).b(zzbah);
            return this;
        }
    }

    static {
        xh.a(sg.class, zzdiz);
    }

    private sg() {
    }

    public static sg a(zzbah zzbah) throws zzbbu {
        return (sg) xh.a(zzdiz, zzbah);
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
        this.zzdip = zzbah;
    }

    public static a c() {
        return (a) ((com.google.android.gms.internal.ads.xh.a) zzdiz.a(e.e, (Object) null, (Object) null));
    }

    public final int a() {
        return this.zzdih;
    }

    /* JADX WARNING: type inference failed for: r2v11, types: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.sg>] */
    /* JADX WARNING: type inference failed for: r2v12, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v13, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.sg>] */
    /* JADX WARNING: type inference failed for: r2v16 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.sg>]
      assigns: [com.google.android.gms.internal.ads.xh$b]
      uses: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.sg>]
      mth insns count: 39
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
            int[] r3 = com.google.android.gms.internal.ads.sh.a
            r4 = 1
            int r2 = r2 - r4
            r2 = r3[r2]
            r3 = 0
            switch(r2) {
                case 1: goto L_0x004e;
                case 2: goto L_0x0048;
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
            com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.sg> r2 = zzakh
            if (r2 != 0) goto L_0x002f
            java.lang.Class<com.google.android.gms.internal.ads.sg> r3 = com.google.android.gms.internal.ads.sg.class
            monitor-enter(r3)
            com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.sg> r2 = zzakh     // Catch:{ all -> 0x002c }
            if (r2 != 0) goto L_0x002a
            com.google.android.gms.internal.ads.xh$b r2 = new com.google.android.gms.internal.ads.xh$b     // Catch:{ all -> 0x002c }
            com.google.android.gms.internal.ads.sg r4 = zzdiz     // Catch:{ all -> 0x002c }
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
            com.google.android.gms.internal.ads.sg r2 = zzdiz
            return r2
        L_0x0033:
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            java.lang.String r0 = "zzdih"
            r2[r3] = r0
            java.lang.String r3 = "zzdip"
            r2[r4] = r3
            java.lang.String r3 = "\u0000\u0002\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u000b\u0003\n"
            com.google.android.gms.internal.ads.sg r4 = zzdiz
            java.lang.Object r2 = a(r4, r3, r2)
            return r2
        L_0x0048:
            com.google.android.gms.internal.ads.sg$a r2 = new com.google.android.gms.internal.ads.sg$a
            r2.<init>(r3)
            return r2
        L_0x004e:
            com.google.android.gms.internal.ads.sg r2 = new com.google.android.gms.internal.ads.sg
            r2.<init>()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.sg.a(int, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public final zzbah b() {
        return this.zzdip;
    }
}
