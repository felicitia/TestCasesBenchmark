package com.google.android.gms.internal.ads;

public final class rx extends xh<rx, a> implements ym {
    private static volatile yw<rx> zzakh;
    /* access modifiers changed from: private */
    public static final rx zzdiu = new rx();
    private int zzdit;

    public static final class a extends com.google.android.gms.internal.ads.xh.a<rx, a> implements ym {
        private a() {
            super(rx.zzdiu);
        }

        /* synthetic */ a(ry ryVar) {
            this();
        }
    }

    static {
        xh.a(rx.class, zzdiu);
    }

    private rx() {
    }

    public static rx b() {
        return zzdiu;
    }

    public final int a() {
        return this.zzdit;
    }

    /* JADX WARNING: type inference failed for: r1v10, types: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.rx>] */
    /* JADX WARNING: type inference failed for: r1v11, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v12, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.rx>] */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v12, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.rx>]
      assigns: [com.google.android.gms.internal.ads.xh$b]
      uses: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.rx>]
      mth insns count: 37
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
    public final java.lang.Object a(int r1, java.lang.Object r2, java.lang.Object r3) {
        /*
            r0 = this;
            int[] r2 = com.google.android.gms.internal.ads.ry.a
            r3 = 1
            int r1 = r1 - r3
            r1 = r2[r1]
            r2 = 0
            switch(r1) {
                case 1: goto L_0x0049;
                case 2: goto L_0x0043;
                case 3: goto L_0x0033;
                case 4: goto L_0x0030;
                case 5: goto L_0x0016;
                case 6: goto L_0x0011;
                case 7: goto L_0x0010;
                default: goto L_0x000a;
            }
        L_0x000a:
            java.lang.UnsupportedOperationException r1 = new java.lang.UnsupportedOperationException
            r1.<init>()
            throw r1
        L_0x0010:
            return r2
        L_0x0011:
            java.lang.Byte r1 = java.lang.Byte.valueOf(r3)
            return r1
        L_0x0016:
            com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.rx> r1 = zzakh
            if (r1 != 0) goto L_0x002f
            java.lang.Class<com.google.android.gms.internal.ads.rx> r2 = com.google.android.gms.internal.ads.rx.class
            monitor-enter(r2)
            com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.rx> r1 = zzakh     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x002a
            com.google.android.gms.internal.ads.xh$b r1 = new com.google.android.gms.internal.ads.xh$b     // Catch:{ all -> 0x002c }
            com.google.android.gms.internal.ads.rx r3 = zzdiu     // Catch:{ all -> 0x002c }
            r1.<init>(r3)     // Catch:{ all -> 0x002c }
            zzakh = r1     // Catch:{ all -> 0x002c }
        L_0x002a:
            monitor-exit(r2)     // Catch:{ all -> 0x002c }
            return r1
        L_0x002c:
            r1 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x002c }
            throw r1
        L_0x002f:
            return r1
        L_0x0030:
            com.google.android.gms.internal.ads.rx r1 = zzdiu
            return r1
        L_0x0033:
            java.lang.Object[] r1 = new java.lang.Object[r3]
            r2 = 0
            java.lang.String r3 = "zzdit"
            r1[r2] = r3
            java.lang.String r2 = "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0002\u0000\u0000\u0000\u0001\u000b"
            com.google.android.gms.internal.ads.rx r3 = zzdiu
            java.lang.Object r1 = a(r3, r2, r1)
            return r1
        L_0x0043:
            com.google.android.gms.internal.ads.rx$a r1 = new com.google.android.gms.internal.ads.rx$a
            r1.<init>(r2)
            return r1
        L_0x0049:
            com.google.android.gms.internal.ads.rx r1 = new com.google.android.gms.internal.ads.rx
            r1.<init>()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.rx.a(int, java.lang.Object, java.lang.Object):java.lang.Object");
    }
}
