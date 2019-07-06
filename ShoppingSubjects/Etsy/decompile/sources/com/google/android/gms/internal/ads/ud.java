package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.xh.e;
import java.util.List;

public final class ud extends xh<ud, a> implements ym {
    private static volatile yw<ud> zzakh;
    /* access modifiers changed from: private */
    public static final ud zzdmt = new ud();
    private int zzdlq;
    private String zzdmr = "";
    private xm<tn> zzdms = m();

    public static final class a extends com.google.android.gms.internal.ads.xh.a<ud, a> implements ym {
        private a() {
            super(ud.zzdmt);
        }

        /* synthetic */ a(ue ueVar) {
            this();
        }

        public final a a(tn tnVar) {
            b();
            ((ud) this.a).a(tnVar);
            return this;
        }

        public final a a(String str) {
            b();
            ((ud) this.a).a(str);
            return this;
        }
    }

    static {
        xh.a(ud.class, zzdmt);
    }

    private ud() {
    }

    /* access modifiers changed from: private */
    public final void a(tn tnVar) {
        if (tnVar == null) {
            throw new NullPointerException();
        }
        if (!this.zzdms.a()) {
            xm<tn> xmVar = this.zzdms;
            int size = xmVar.size();
            this.zzdms = xmVar.a(size == 0 ? 10 : size << 1);
        }
        this.zzdms.add(tnVar);
    }

    /* access modifiers changed from: private */
    public final void a(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.zzdmr = str;
    }

    public static a b() {
        return (a) ((com.google.android.gms.internal.ads.xh.a) zzdmt.a(e.e, (Object) null, (Object) null));
    }

    /* JADX WARNING: type inference failed for: r2v11, types: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.ud>] */
    /* JADX WARNING: type inference failed for: r2v12, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v13, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.ud>] */
    /* JADX WARNING: type inference failed for: r2v16 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13, types: [com.google.android.gms.internal.ads.xh$b, com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.ud>]
      assigns: [com.google.android.gms.internal.ads.xh$b]
      uses: [com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.ud>]
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
            int[] r3 = com.google.android.gms.internal.ads.ue.a
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
            com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.ud> r2 = zzakh
            if (r2 != 0) goto L_0x002f
            java.lang.Class<com.google.android.gms.internal.ads.ud> r3 = com.google.android.gms.internal.ads.ud.class
            monitor-enter(r3)
            com.google.android.gms.internal.ads.yw<com.google.android.gms.internal.ads.ud> r2 = zzakh     // Catch:{ all -> 0x002c }
            if (r2 != 0) goto L_0x002a
            com.google.android.gms.internal.ads.xh$b r2 = new com.google.android.gms.internal.ads.xh$b     // Catch:{ all -> 0x002c }
            com.google.android.gms.internal.ads.ud r4 = zzdmt     // Catch:{ all -> 0x002c }
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
            com.google.android.gms.internal.ads.ud r2 = zzdmt
            return r2
        L_0x0033:
            r2 = 4
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            java.lang.String r0 = "zzdlq"
            r2[r3] = r0
            java.lang.String r3 = "zzdmr"
            r2[r4] = r3
            r3 = 2
            java.lang.String r4 = "zzdms"
            r2[r3] = r4
            r3 = 3
            java.lang.Class<com.google.android.gms.internal.ads.tn> r4 = com.google.android.gms.internal.ads.tn.class
            r2[r3] = r4
            java.lang.String r3 = "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0003\u0000\u0001\u0000\u0001Ȉ\u0002\u001b"
            com.google.android.gms.internal.ads.ud r4 = zzdmt
            java.lang.Object r2 = a(r4, r3, r2)
            return r2
        L_0x0052:
            com.google.android.gms.internal.ads.ud$a r2 = new com.google.android.gms.internal.ads.ud$a
            r2.<init>(r3)
            return r2
        L_0x0058:
            com.google.android.gms.internal.ads.ud r2 = new com.google.android.gms.internal.ads.ud
            r2.<init>()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.ud.a(int, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public final List<tn> a() {
        return this.zzdms;
    }
}
