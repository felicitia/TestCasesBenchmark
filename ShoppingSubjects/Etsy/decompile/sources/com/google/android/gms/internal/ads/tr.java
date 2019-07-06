package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.xh.e;

public final class tr extends xh<tr, a> implements ym {
    private static volatile yw<tr> zzakh;
    /* access modifiers changed from: private */
    public static final tr zzdlz = new tr();
    private int zzdlq;
    private int zzdlr;
    private xm<b> zzdly = m();

    public static final class a extends com.google.android.gms.internal.ads.xh.a<tr, a> implements ym {
        private a() {
            super(tr.zzdlz);
        }

        /* synthetic */ a(ts tsVar) {
            this();
        }

        public final a a(int i) {
            b();
            ((tr) this.a).b(i);
            return this;
        }

        public final a a(b bVar) {
            b();
            ((tr) this.a).a(bVar);
            return this;
        }
    }

    public static final class b extends xh<b, a> implements ym {
        private static volatile yw<b> zzakh;
        /* access modifiers changed from: private */
        public static final b zzdma = new b();
        private String zzdks = "";
        private int zzdlj;
        private int zzdlv;
        private int zzdlw;

        public static final class a extends com.google.android.gms.internal.ads.xh.a<b, a> implements ym {
            private a() {
                super(b.zzdma);
            }

            /* synthetic */ a(ts tsVar) {
                this();
            }

            public final a a(int i) {
                b();
                ((b) this.a).b(i);
                return this;
            }

            public final a a(zzaxl zzaxl) {
                b();
                ((b) this.a).a(zzaxl);
                return this;
            }

            public final a a(zzayd zzayd) {
                b();
                ((b) this.a).a(zzayd);
                return this;
            }

            public final a a(String str) {
                b();
                ((b) this.a).a(str);
                return this;
            }
        }

        static {
            xh.a(b.class, zzdma);
        }

        private b() {
        }

        public static a a() {
            return (a) ((com.google.android.gms.internal.ads.xh.a) zzdma.a(e.e, (Object) null, (Object) null));
        }

        /* access modifiers changed from: private */
        public final void a(zzaxl zzaxl) {
            if (zzaxl == null) {
                throw new NullPointerException();
            }
            this.zzdlv = zzaxl.zzhq();
        }

        /* access modifiers changed from: private */
        public final void a(zzayd zzayd) {
            if (zzayd == null) {
                throw new NullPointerException();
            }
            this.zzdlj = zzayd.zzhq();
        }

        /* access modifiers changed from: private */
        public final void a(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzdks = str;
        }

        /* access modifiers changed from: private */
        public final void b(int i) {
            this.zzdlw = i;
        }

        /* JADX WARNING: type inference failed for: r2v11, types: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tr$b>] */
        /* JADX WARNING: type inference failed for: r2v12, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v13, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tr$b>] */
        /* JADX WARNING: type inference failed for: r2v16 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tr$b>]
          assigns: [com.google.android.gms.internal.ads.xh$b]
          uses: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tr$b>]
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
                int[] r3 = com.google.android.gms.internal.ads.ts.a
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
                com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tr$b> r2 = zzakh
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.ads.tr$b> r3 = com.google.android.gms.internal.ads.tr.b.class
                monitor-enter(r3)
                com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tr$b> r2 = zzakh     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.ads.xh$b r2 = new com.google.android.gms.internal.ads.xh$b     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.ads.tr$b r4 = zzdma     // Catch:{ all -> 0x002c }
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
                com.google.android.gms.internal.ads.tr$b r2 = zzdma
                return r2
            L_0x0033:
                r2 = 4
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzdks"
                r2[r3] = r0
                java.lang.String r3 = "zzdlv"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzdlw"
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzdlj"
                r2[r3] = r4
                java.lang.String r3 = "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0005\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f"
                com.google.android.gms.internal.ads.tr$b r4 = zzdma
                java.lang.Object r2 = a(r4, r3, r2)
                return r2
            L_0x0052:
                com.google.android.gms.internal.ads.tr$b$a r2 = new com.google.android.gms.internal.ads.tr$b$a
                r2.<init>(r3)
                return r2
            L_0x0058:
                com.google.android.gms.internal.ads.tr$b r2 = new com.google.android.gms.internal.ads.tr$b
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.tr.b.a(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }
    }

    static {
        xh.a(tr.class, zzdlz);
    }

    private tr() {
    }

    public static a a() {
        return (a) ((com.google.android.gms.internal.ads.xh.a) zzdlz.a(e.e, (Object) null, (Object) null));
    }

    /* access modifiers changed from: private */
    public final void a(b bVar) {
        if (bVar == null) {
            throw new NullPointerException();
        }
        if (!this.zzdly.a()) {
            xm<b> xmVar = this.zzdly;
            int size = xmVar.size();
            this.zzdly = xmVar.a(size == 0 ? 10 : size << 1);
        }
        this.zzdly.add(bVar);
    }

    /* access modifiers changed from: private */
    public final void b(int i) {
        this.zzdlr = i;
    }

    /* JADX WARNING: type inference failed for: r2v11, types: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tr>] */
    /* JADX WARNING: type inference failed for: r2v12, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v13, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tr>] */
    /* JADX WARNING: type inference failed for: r2v16 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tr>]
      assigns: [com.google.android.gms.internal.ads.xh$b]
      uses: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tr>]
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
            int[] r3 = com.google.android.gms.internal.ads.ts.a
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
            com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tr> r2 = zzakh
            if (r2 != 0) goto L_0x002f
            java.lang.Class<com.google.android.gms.internal.ads.tr> r3 = com.google.android.gms.internal.ads.tr.class
            monitor-enter(r3)
            com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tr> r2 = zzakh     // Catch:{ all -> 0x002c }
            if (r2 != 0) goto L_0x002a
            com.google.android.gms.internal.ads.xh$b r2 = new com.google.android.gms.internal.ads.xh$b     // Catch:{ all -> 0x002c }
            com.google.android.gms.internal.ads.tr r4 = zzdlz     // Catch:{ all -> 0x002c }
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
            com.google.android.gms.internal.ads.tr r2 = zzdlz
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
            java.lang.String r4 = "zzdly"
            r2[r3] = r4
            r3 = 3
            java.lang.Class<com.google.android.gms.internal.ads.tr$b> r4 = com.google.android.gms.internal.ads.tr.b.class
            r2[r3] = r4
            java.lang.String r3 = "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0003\u0000\u0001\u0000\u0001\u000b\u0002\u001b"
            com.google.android.gms.internal.ads.tr r4 = zzdlz
            java.lang.Object r2 = a(r4, r3, r2)
            return r2
        L_0x0052:
            com.google.android.gms.internal.ads.tr$a r2 = new com.google.android.gms.internal.ads.tr$a
            r2.<init>(r3)
            return r2
        L_0x0058:
            com.google.android.gms.internal.ads.tr r2 = new com.google.android.gms.internal.ads.tr
            r2.<init>()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.tr.a(int, java.lang.Object, java.lang.Object):java.lang.Object");
    }
}
