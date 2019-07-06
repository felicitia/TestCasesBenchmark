package com.google.android.gms.internal.icing;

/* renamed from: com.google.android.gms.internal.icing.do reason: invalid class name */
public final class Cdo {

    /* renamed from: com.google.android.gms.internal.icing.do$a */
    public static final class a extends ah<a, C0139a> implements bn {
        /* access modifiers changed from: private */
        public static final a zzol = new a();
        private static volatile bw<a> zzom;
        private int zzog;
        private boolean zzoh;
        private int zzoi;
        private String zzoj = "";
        private ao<b> zzok = h();

        /* renamed from: com.google.android.gms.internal.icing.do$a$a reason: collision with other inner class name */
        public static final class C0139a extends com.google.android.gms.internal.icing.ah.a<a, C0139a> implements bn {
            private C0139a() {
                super(a.zzol);
            }

            /* synthetic */ C0139a(dp dpVar) {
                this();
            }
        }

        static {
            ah.a(a.class, zzol);
        }

        private a() {
        }

        public static a n() {
            return zzol;
        }

        /* JADX WARNING: type inference failed for: r2v11, types: [com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$a>] */
        /* JADX WARNING: type inference failed for: r2v12, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v13, types: [com.google.android.gms.internal.icing.ah$b, com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$a>] */
        /* JADX WARNING: type inference failed for: r2v16 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13, types: [com.google.android.gms.internal.icing.ah$b, com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$a>]
          assigns: [com.google.android.gms.internal.icing.ah$b]
          uses: [com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$a>]
          mth insns count: 47
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
                int[] r3 = com.google.android.gms.internal.icing.dp.a
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0062;
                    case 2: goto L_0x005c;
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
                com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$a> r2 = zzom
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.icing.do$a> r3 = com.google.android.gms.internal.icing.Cdo.a.class
                monitor-enter(r3)
                com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$a> r2 = zzom     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.icing.ah$b r2 = new com.google.android.gms.internal.icing.ah$b     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.icing.do$a r4 = zzol     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzom = r2     // Catch:{ all -> 0x002c }
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
                com.google.android.gms.internal.icing.do$a r2 = zzol
                return r2
            L_0x0033:
                r2 = 6
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzog"
                r2[r3] = r0
                java.lang.String r3 = "zzoh"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzoi"
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzoj"
                r2[r3] = r4
                r3 = 4
                java.lang.String r4 = "zzok"
                r2[r3] = r4
                r3 = 5
                java.lang.Class<com.google.android.gms.internal.icing.do$b> r4 = com.google.android.gms.internal.icing.Cdo.b.class
                r2[r3] = r4
                java.lang.String r3 = "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u0007\u0000\u0002\u0004\u0001\u0003\b\u0002\u0004\u001b"
                com.google.android.gms.internal.icing.do$a r4 = zzol
                java.lang.Object r2 = a(r4, r3, r2)
                return r2
            L_0x005c:
                com.google.android.gms.internal.icing.do$a$a r2 = new com.google.android.gms.internal.icing.do$a$a
                r2.<init>(r3)
                return r2
            L_0x0062:
                com.google.android.gms.internal.icing.do$a r2 = new com.google.android.gms.internal.icing.do$a
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.Cdo.a.a(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        public final boolean k() {
            return this.zzoh;
        }

        public final int l() {
            return this.zzoi;
        }

        public final String m() {
            return this.zzoj;
        }
    }

    /* renamed from: com.google.android.gms.internal.icing.do$b */
    public static final class b extends ah<b, a> implements bn {
        private static volatile bw<b> zzom;
        /* access modifiers changed from: private */
        public static final b zzou = new b();
        private int zzog;
        private String zzon = "";
        private ak zzoo = g();
        private an zzop = e();
        private ao<String> zzoq = ah.h();
        private ao<c> zzor = h();
        private zzbi zzos = zzbi.zzdq;
        private al zzot = f();

        /* renamed from: com.google.android.gms.internal.icing.do$b$a */
        public static final class a extends com.google.android.gms.internal.icing.ah.a<b, a> implements bn {
            private a() {
                super(b.zzou);
            }

            /* synthetic */ a(dp dpVar) {
                this();
            }
        }

        static {
            ah.a(b.class, zzou);
        }

        private b() {
        }

        /* JADX WARNING: type inference failed for: r2v11, types: [com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$b>] */
        /* JADX WARNING: type inference failed for: r2v12, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v13, types: [com.google.android.gms.internal.icing.ah$b, com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$b>] */
        /* JADX WARNING: type inference failed for: r2v16 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13, types: [com.google.android.gms.internal.icing.ah$b, com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$b>]
          assigns: [com.google.android.gms.internal.icing.ah$b]
          uses: [com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$b>]
          mth insns count: 53
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
                int[] r3 = com.google.android.gms.internal.icing.dp.a
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0073;
                    case 2: goto L_0x006d;
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
                com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$b> r2 = zzom
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.icing.do$b> r3 = com.google.android.gms.internal.icing.Cdo.b.class
                monitor-enter(r3)
                com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$b> r2 = zzom     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.icing.ah$b r2 = new com.google.android.gms.internal.icing.ah$b     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.icing.do$b r4 = zzou     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzom = r2     // Catch:{ all -> 0x002c }
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
                com.google.android.gms.internal.icing.do$b r2 = zzou
                return r2
            L_0x0033:
                r2 = 9
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzog"
                r2[r3] = r0
                java.lang.String r3 = "zzon"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzoo"
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzop"
                r2[r3] = r4
                r3 = 4
                java.lang.String r4 = "zzoq"
                r2[r3] = r4
                r3 = 5
                java.lang.String r4 = "zzor"
                r2[r3] = r4
                r3 = 6
                java.lang.Class<com.google.android.gms.internal.icing.do$c> r4 = com.google.android.gms.internal.icing.Cdo.c.class
                r2[r3] = r4
                r3 = 7
                java.lang.String r4 = "zzos"
                r2[r3] = r4
                r3 = 8
                java.lang.String r4 = "zzot"
                r2[r3] = r4
                java.lang.String r3 = "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0005\u0000\u0001\b\u0000\u0002\u0019\u0003\u0014\u0004\u001a\u0005\u001b\u0006\n\u0001\u0007\u0012"
                com.google.android.gms.internal.icing.do$b r4 = zzou
                java.lang.Object r2 = a(r4, r3, r2)
                return r2
            L_0x006d:
                com.google.android.gms.internal.icing.do$b$a r2 = new com.google.android.gms.internal.icing.do$b$a
                r2.<init>(r3)
                return r2
            L_0x0073:
                com.google.android.gms.internal.icing.do$b r2 = new com.google.android.gms.internal.icing.do$b
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.Cdo.b.a(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }
    }

    /* renamed from: com.google.android.gms.internal.icing.do$c */
    public static final class c extends ah<c, a> implements bn {
        private static volatile bw<c> zzom;
        /* access modifiers changed from: private */
        public static final c zzoy = new c();
        private int zzog;
        private ao<b> zzok = h();
        private String zzov = "";
        private String zzow = "";
        private a zzox;

        /* renamed from: com.google.android.gms.internal.icing.do$c$a */
        public static final class a extends com.google.android.gms.internal.icing.ah.a<c, a> implements bn {
            private a() {
                super(c.zzoy);
            }

            /* synthetic */ a(dp dpVar) {
                this();
            }
        }

        static {
            ah.a(c.class, zzoy);
        }

        private c() {
        }

        /* JADX WARNING: type inference failed for: r2v11, types: [com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$c>] */
        /* JADX WARNING: type inference failed for: r2v12, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v13, types: [com.google.android.gms.internal.icing.ah$b, com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$c>] */
        /* JADX WARNING: type inference failed for: r2v16 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13, types: [com.google.android.gms.internal.icing.ah$b, com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$c>]
          assigns: [com.google.android.gms.internal.icing.ah$b]
          uses: [com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$c>]
          mth insns count: 47
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
                int[] r3 = com.google.android.gms.internal.icing.dp.a
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x0062;
                    case 2: goto L_0x005c;
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
                com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$c> r2 = zzom
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.icing.do$c> r3 = com.google.android.gms.internal.icing.Cdo.c.class
                monitor-enter(r3)
                com.google.android.gms.internal.icing.bw<com.google.android.gms.internal.icing.do$c> r2 = zzom     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.icing.ah$b r2 = new com.google.android.gms.internal.icing.ah$b     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.icing.do$c r4 = zzoy     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzom = r2     // Catch:{ all -> 0x002c }
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
                com.google.android.gms.internal.icing.do$c r2 = zzoy
                return r2
            L_0x0033:
                r2 = 6
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzog"
                r2[r3] = r0
                java.lang.String r3 = "zzov"
                r2[r4] = r3
                r3 = 2
                java.lang.String r4 = "zzow"
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzok"
                r2[r3] = r4
                r3 = 4
                java.lang.Class<com.google.android.gms.internal.icing.do$b> r4 = com.google.android.gms.internal.icing.Cdo.b.class
                r2[r3] = r4
                r3 = 5
                java.lang.String r4 = "zzox"
                r2[r3] = r4
                java.lang.String r3 = "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u001b\u0004\t\u0002"
                com.google.android.gms.internal.icing.do$c r4 = zzoy
                java.lang.Object r2 = a(r4, r3, r2)
                return r2
            L_0x005c:
                com.google.android.gms.internal.icing.do$c$a r2 = new com.google.android.gms.internal.icing.do$c$a
                r2.<init>(r3)
                return r2
            L_0x0062:
                com.google.android.gms.internal.icing.do$c r2 = new com.google.android.gms.internal.icing.do$c
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.Cdo.c.a(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }
    }
}
