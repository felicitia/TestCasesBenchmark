package com.google.android.gms.internal.ads;

import java.util.List;

public final class tp extends xh<tp, a> implements ym {
    private static volatile yw<tp> zzakh;
    /* access modifiers changed from: private */
    public static final tp zzdlt = new tp();
    private int zzdlq;
    private int zzdlr;
    private xm<b> zzdls = m();

    public static final class a extends com.google.android.gms.internal.ads.xh.a<tp, a> implements ym {
        private a() {
            super(tp.zzdlt);
        }

        /* synthetic */ a(tq tqVar) {
            this();
        }
    }

    public static final class b extends xh<b, a> implements ym {
        private static volatile yw<b> zzakh;
        /* access modifiers changed from: private */
        public static final b zzdlx = new b();
        private int zzdlj;
        private zzaxi zzdlu;
        private int zzdlv;
        private int zzdlw;

        public static final class a extends com.google.android.gms.internal.ads.xh.a<b, a> implements ym {
            private a() {
                super(b.zzdlx);
            }

            /* synthetic */ a(tq tqVar) {
                this();
            }
        }

        static {
            xh.a(b.class, zzdlx);
        }

        private b() {
        }

        /* JADX WARNING: type inference failed for: r2v11, types: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tp$b>] */
        /* JADX WARNING: type inference failed for: r2v12, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v13, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tp$b>] */
        /* JADX WARNING: type inference failed for: r2v16 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tp$b>]
          assigns: [com.google.android.gms.internal.ads.xh$b]
          uses: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tp$b>]
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
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at jadx.core.dex.visitors.DepthTraversal$$Lambda$32/1463058548.accept(Unknown Source)
        	at java.util.ArrayList.forEach(ArrayList.java:1249)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
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
                int[] r3 = com.google.android.gms.internal.ads.tq.a
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
                com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tp$b> r2 = zzakh
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.ads.tp$b> r3 = com.google.android.gms.internal.ads.tp.b.class
                monitor-enter(r3)
                com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tp$b> r2 = zzakh     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.ads.xh$b r2 = new com.google.android.gms.internal.ads.xh$b     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.ads.tp$b r4 = zzdlx     // Catch:{ all -> 0x002c }
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
                com.google.android.gms.internal.ads.tp$b r2 = zzdlx
                return r2
            L_0x0033:
                r2 = 4
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzdlu"
                r2[r3] = r0
                java.lang.String r3 = "zzdlv"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzdlw"
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzdlj"
                r2[r3] = r4
                java.lang.String r3 = "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0005\u0000\u0000\u0000\u0001\t\u0002\f\u0003\u000b\u0004\f"
                com.google.android.gms.internal.ads.tp$b r4 = zzdlx
                java.lang.Object r2 = a(r4, r3, r2)
                return r2
            L_0x0052:
                com.google.android.gms.internal.ads.tp$b$a r2 = new com.google.android.gms.internal.ads.tp$b$a
                r2.<init>(r3)
                return r2
            L_0x0058:
                com.google.android.gms.internal.ads.tp$b r2 = new com.google.android.gms.internal.ads.tp$b
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.tp.b.a(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        public final boolean a() {
            return this.zzdlu != null;
        }

        public final zzaxi b() {
            return this.zzdlu == null ? zzaxi.e() : this.zzdlu;
        }

        public final zzaxl c() {
            zzaxl zzax = zzaxl.zzax(this.zzdlv);
            return zzax == null ? zzaxl.UNRECOGNIZED : zzax;
        }

        public final int d() {
            return this.zzdlw;
        }

        public final zzayd e() {
            zzayd zzbg = zzayd.zzbg(this.zzdlj);
            return zzbg == null ? zzayd.UNRECOGNIZED : zzbg;
        }
    }

    static {
        xh.a(tp.class, zzdlt);
    }

    private tp() {
    }

    public static tp a(byte[] bArr) throws zzbbu {
        return (tp) xh.a(zzdlt, bArr);
    }

    public final int a() {
        return this.zzdlr;
    }

    /* JADX WARNING: type inference failed for: r2v11, types: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tp>] */
    /* JADX WARNING: type inference failed for: r2v12, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v13, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tp>] */
    /* JADX WARNING: type inference failed for: r2v16 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tp>]
      assigns: [com.google.android.gms.internal.ads.xh$b]
      uses: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tp>]
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
            int[] r3 = com.google.android.gms.internal.ads.tq.a
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
            com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tp> r2 = zzakh
            if (r2 != 0) goto L_0x002f
            java.lang.Class<com.google.android.gms.internal.ads.tp> r3 = com.google.android.gms.internal.ads.tp.class
            monitor-enter(r3)
            com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tp> r2 = zzakh     // Catch:{ all -> 0x002c }
            if (r2 != 0) goto L_0x002a
            com.google.android.gms.internal.ads.xh$b r2 = new com.google.android.gms.internal.ads.xh$b     // Catch:{ all -> 0x002c }
            com.google.android.gms.internal.ads.tp r4 = zzdlt     // Catch:{ all -> 0x002c }
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
            com.google.android.gms.internal.ads.tp r2 = zzdlt
            return r2
        L_0x0033:
            r2 = 4
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            java.lang.String r0 = "zzdlq"
            r2[r3] = r0
            java.lang.String r3 = "zzdlr"
            r2[r4] = r3
            r3 = 2
            java.lang.String r4 = "zzdls"
            r2[r3] = r4
            r3 = 3
            java.lang.Class<com.google.android.gms.internal.ads.tp$b> r4 = com.google.android.gms.internal.ads.tp.b.class
            r2[r3] = r4
            java.lang.String r3 = "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0003\u0000\u0001\u0000\u0001\u000b\u0002\u001b"
            com.google.android.gms.internal.ads.tp r4 = zzdlt
            java.lang.Object r2 = a(r4, r3, r2)
            return r2
        L_0x0052:
            com.google.android.gms.internal.ads.tp$a r2 = new com.google.android.gms.internal.ads.tp$a
            r2.<init>(r3)
            return r2
        L_0x0058:
            com.google.android.gms.internal.ads.tp r2 = new com.google.android.gms.internal.ads.tp
            r2.<init>()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.tp.a(int, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public final List<b> b() {
        return this.zzdls;
    }

    public final int c() {
        return this.zzdls.size();
    }
}
