package com.google.android.gms.internal.ads;

public abstract class zzaeo extends zzek implements zzaen {
    public zzaeo() {
        super("com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    /* JADX WARNING: type inference failed for: r5v1 */
    /* JADX WARNING: type inference failed for: r5v2, types: [com.google.android.gms.internal.ads.zzaeq] */
    /* JADX WARNING: type inference failed for: r5v5, types: [com.google.android.gms.internal.ads.zzaes] */
    /* JADX WARNING: type inference failed for: r5v6, types: [com.google.android.gms.internal.ads.zzaeq] */
    /* JADX WARNING: type inference failed for: r5v7, types: [com.google.android.gms.internal.ads.zzaet] */
    /* JADX WARNING: type inference failed for: r5v10, types: [com.google.android.gms.internal.ads.zzaeu] */
    /* JADX WARNING: type inference failed for: r5v11, types: [com.google.android.gms.internal.ads.zzaet] */
    /* JADX WARNING: type inference failed for: r5v12, types: [com.google.android.gms.internal.ads.zzaet] */
    /* JADX WARNING: type inference failed for: r5v15, types: [com.google.android.gms.internal.ads.zzaeu] */
    /* JADX WARNING: type inference failed for: r5v16, types: [com.google.android.gms.internal.ads.zzaet] */
    /* JADX WARNING: type inference failed for: r5v17 */
    /* JADX WARNING: type inference failed for: r5v18 */
    /* JADX WARNING: type inference failed for: r5v19 */
    /* JADX WARNING: type inference failed for: r5v20 */
    /* JADX WARNING: type inference failed for: r5v21 */
    /* JADX WARNING: type inference failed for: r5v22 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r5v1
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.google.android.gms.internal.ads.zzaeu, com.google.android.gms.internal.ads.zzaes, com.google.android.gms.internal.ads.zzaeq, com.google.android.gms.internal.ads.zzaet]
      uses: [com.google.android.gms.internal.ads.zzaeq, com.google.android.gms.internal.ads.zzaet]
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
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 7 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchTransaction(int r2, android.os.Parcel r3, android.os.Parcel r4, int r5) throws android.os.RemoteException {
        /*
            r1 = this;
            r5 = 0
            switch(r2) {
                case 1: goto L_0x0078;
                case 2: goto L_0x0050;
                case 3: goto L_0x0004;
                case 4: goto L_0x002b;
                case 5: goto L_0x0006;
                default: goto L_0x0004;
            }
        L_0x0004:
            r2 = 0
            return r2
        L_0x0006:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzaey> r2 = com.google.android.gms.internal.ads.zzaey.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.ads.aej.a(r3, r2)
            com.google.android.gms.internal.ads.zzaey r2 = (com.google.android.gms.internal.ads.zzaey) r2
            android.os.IBinder r3 = r3.readStrongBinder()
            if (r3 != 0) goto L_0x0015
            goto L_0x0027
        L_0x0015:
            java.lang.String r5 = "com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener"
            android.os.IInterface r5 = r3.queryLocalInterface(r5)
            boolean r0 = r5 instanceof com.google.android.gms.internal.ads.zzaet
            if (r0 == 0) goto L_0x0022
            com.google.android.gms.internal.ads.zzaet r5 = (com.google.android.gms.internal.ads.zzaet) r5
            goto L_0x0027
        L_0x0022:
            com.google.android.gms.internal.ads.zzaeu r5 = new com.google.android.gms.internal.ads.zzaeu
            r5.<init>(r3)
        L_0x0027:
            r1.zzb(r2, r5)
            goto L_0x0074
        L_0x002b:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzaey> r2 = com.google.android.gms.internal.ads.zzaey.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.ads.aej.a(r3, r2)
            com.google.android.gms.internal.ads.zzaey r2 = (com.google.android.gms.internal.ads.zzaey) r2
            android.os.IBinder r3 = r3.readStrongBinder()
            if (r3 != 0) goto L_0x003a
            goto L_0x004c
        L_0x003a:
            java.lang.String r5 = "com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener"
            android.os.IInterface r5 = r3.queryLocalInterface(r5)
            boolean r0 = r5 instanceof com.google.android.gms.internal.ads.zzaet
            if (r0 == 0) goto L_0x0047
            com.google.android.gms.internal.ads.zzaet r5 = (com.google.android.gms.internal.ads.zzaet) r5
            goto L_0x004c
        L_0x0047:
            com.google.android.gms.internal.ads.zzaeu r5 = new com.google.android.gms.internal.ads.zzaeu
            r5.<init>(r3)
        L_0x004c:
            r1.zza(r2, r5)
            goto L_0x0074
        L_0x0050:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzaef> r2 = com.google.android.gms.internal.ads.zzaef.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.ads.aej.a(r3, r2)
            com.google.android.gms.internal.ads.zzaef r2 = (com.google.android.gms.internal.ads.zzaef) r2
            android.os.IBinder r3 = r3.readStrongBinder()
            if (r3 != 0) goto L_0x005f
            goto L_0x0071
        L_0x005f:
            java.lang.String r5 = "com.google.android.gms.ads.internal.request.IAdResponseListener"
            android.os.IInterface r5 = r3.queryLocalInterface(r5)
            boolean r0 = r5 instanceof com.google.android.gms.internal.ads.zzaeq
            if (r0 == 0) goto L_0x006c
            com.google.android.gms.internal.ads.zzaeq r5 = (com.google.android.gms.internal.ads.zzaeq) r5
            goto L_0x0071
        L_0x006c:
            com.google.android.gms.internal.ads.zzaes r5 = new com.google.android.gms.internal.ads.zzaes
            r5.<init>(r3)
        L_0x0071:
            r1.zza(r2, r5)
        L_0x0074:
            r4.writeNoException()
            goto L_0x008a
        L_0x0078:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzaef> r2 = com.google.android.gms.internal.ads.zzaef.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.ads.aej.a(r3, r2)
            com.google.android.gms.internal.ads.zzaef r2 = (com.google.android.gms.internal.ads.zzaef) r2
            com.google.android.gms.internal.ads.zzaej r2 = r1.zzb(r2)
            r4.writeNoException()
            com.google.android.gms.internal.ads.aej.b(r4, r2)
        L_0x008a:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaeo.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
