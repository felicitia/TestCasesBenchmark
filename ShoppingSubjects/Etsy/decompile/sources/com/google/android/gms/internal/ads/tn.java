package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.xh.e;

public final class tn extends xh<tn, a> implements ym {
    private static volatile yw<tn> zzakh;
    /* access modifiers changed from: private */
    public static final tn zzdlp = new tn();
    private String zzdks = "";
    private String zzdll = "";
    private int zzdlm;
    private boolean zzdln;
    private String zzdlo = "";

    public static final class a extends com.google.android.gms.internal.ads.xh.a<tn, a> implements ym {
        private a() {
            super(tn.zzdlp);
        }

        /* synthetic */ a(to toVar) {
            this();
        }

        public final a a(int i) {
            b();
            ((tn) this.a).b(0);
            return this;
        }

        public final a a(String str) {
            b();
            ((tn) this.a).a(str);
            return this;
        }

        public final a a(boolean z) {
            b();
            ((tn) this.a).a(true);
            return this;
        }

        public final a b(String str) {
            b();
            ((tn) this.a).b(str);
            return this;
        }

        public final a c(String str) {
            b();
            ((tn) this.a).c(str);
            return this;
        }
    }

    static {
        xh.a(tn.class, zzdlp);
    }

    private tn() {
    }

    /* access modifiers changed from: private */
    public final void a(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.zzdll = str;
    }

    /* access modifiers changed from: private */
    public final void a(boolean z) {
        this.zzdln = z;
    }

    /* access modifiers changed from: private */
    public final void b(int i) {
        this.zzdlm = i;
    }

    /* access modifiers changed from: private */
    public final void b(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.zzdks = str;
    }

    /* access modifiers changed from: private */
    public final void c(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.zzdlo = str;
    }

    public static a f() {
        return (a) ((com.google.android.gms.internal.ads.xh.a) zzdlp.a(e.e, (Object) null, (Object) null));
    }

    /* JADX WARNING: type inference failed for: r2v11, types: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tn>] */
    /* JADX WARNING: type inference failed for: r2v12, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v13, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tn>] */
    /* JADX WARNING: type inference failed for: r2v16 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tn>]
      assigns: [com.google.android.gms.internal.ads.xh$b]
      uses: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tn>]
      mth insns count: 45
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
            int[] r3 = com.google.android.gms.internal.ads.to.a
            r4 = 1
            int r2 = r2 - r4
            r2 = r3[r2]
            r3 = 0
            switch(r2) {
                case 1: goto L_0x005d;
                case 2: goto L_0x0057;
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
            com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tn> r2 = zzakh
            if (r2 != 0) goto L_0x002f
            java.lang.Class<com.google.android.gms.internal.ads.tn> r3 = com.google.android.gms.internal.ads.tn.class
            monitor-enter(r3)
            com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.tn> r2 = zzakh     // Catch:{ all -> 0x002c }
            if (r2 != 0) goto L_0x002a
            com.google.android.gms.internal.ads.xh$b r2 = new com.google.android.gms.internal.ads.xh$b     // Catch:{ all -> 0x002c }
            com.google.android.gms.internal.ads.tn r4 = zzdlp     // Catch:{ all -> 0x002c }
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
            com.google.android.gms.internal.ads.tn r2 = zzdlp
            return r2
        L_0x0033:
            r2 = 5
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            java.lang.String r0 = "zzdll"
            r2[r3] = r0
            java.lang.String r3 = "zzdks"
            r2[r4] = r3
            r3 = 2
            java.lang.String r4 = "zzdlm"
            r2[r3] = r4
            r3 = 3
            java.lang.String r4 = "zzdln"
            r2[r3] = r4
            r3 = 4
            java.lang.String r4 = "zzdlo"
            r2[r3] = r4
            java.lang.String r3 = "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0006\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u000b\u0004\u0007\u0005Ȉ"
            com.google.android.gms.internal.ads.tn r4 = zzdlp
            java.lang.Object r2 = a(r4, r3, r2)
            return r2
        L_0x0057:
            com.google.android.gms.internal.ads.tn$a r2 = new com.google.android.gms.internal.ads.tn$a
            r2.<init>(r3)
            return r2
        L_0x005d:
            com.google.android.gms.internal.ads.tn r2 = new com.google.android.gms.internal.ads.tn
            r2.<init>()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.tn.a(int, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public final String a() {
        return this.zzdll;
    }

    public final String b() {
        return this.zzdks;
    }

    public final int c() {
        return this.zzdlm;
    }

    public final boolean d() {
        return this.zzdln;
    }

    public final String e() {
        return this.zzdlo;
    }
}
