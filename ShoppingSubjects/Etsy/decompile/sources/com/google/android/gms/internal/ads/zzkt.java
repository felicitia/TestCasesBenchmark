package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzkt extends zzek implements zzks {
    public zzkt() {
        super("com.google.android.gms.ads.internal.client.IAdManager");
    }

    public static zzks zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
        return queryLocalInterface instanceof zzks ? (zzks) queryLocalInterface : new zzku(iBinder);
    }

    /* JADX WARNING: type inference failed for: r4v1 */
    /* JADX WARNING: type inference failed for: r4v2, types: [com.google.android.gms.internal.ads.zzkh] */
    /* JADX WARNING: type inference failed for: r4v4, types: [com.google.android.gms.internal.ads.zzkj] */
    /* JADX WARNING: type inference failed for: r4v6, types: [com.google.android.gms.internal.ads.zzkh] */
    /* JADX WARNING: type inference failed for: r4v7, types: [com.google.android.gms.internal.ads.zzla] */
    /* JADX WARNING: type inference failed for: r4v9, types: [com.google.android.gms.internal.ads.zzlc] */
    /* JADX WARNING: type inference failed for: r4v11, types: [com.google.android.gms.internal.ads.zzla] */
    /* JADX WARNING: type inference failed for: r4v12, types: [com.google.android.gms.internal.ads.zzke] */
    /* JADX WARNING: type inference failed for: r4v14, types: [com.google.android.gms.internal.ads.zzkg] */
    /* JADX WARNING: type inference failed for: r4v16, types: [com.google.android.gms.internal.ads.zzke] */
    /* JADX WARNING: type inference failed for: r4v17, types: [com.google.android.gms.internal.ads.zzlg] */
    /* JADX WARNING: type inference failed for: r4v19, types: [com.google.android.gms.internal.ads.zzli] */
    /* JADX WARNING: type inference failed for: r4v21, types: [com.google.android.gms.internal.ads.zzlg] */
    /* JADX WARNING: type inference failed for: r4v22, types: [com.google.android.gms.internal.ads.zzkx] */
    /* JADX WARNING: type inference failed for: r4v24, types: [com.google.android.gms.internal.ads.zzkz] */
    /* JADX WARNING: type inference failed for: r4v26, types: [com.google.android.gms.internal.ads.zzkx] */
    /* JADX WARNING: type inference failed for: r4v27 */
    /* JADX WARNING: type inference failed for: r4v28 */
    /* JADX WARNING: type inference failed for: r4v29 */
    /* JADX WARNING: type inference failed for: r4v30 */
    /* JADX WARNING: type inference failed for: r4v31 */
    /* JADX WARNING: type inference failed for: r4v32 */
    /* JADX WARNING: type inference failed for: r4v33 */
    /* JADX WARNING: type inference failed for: r4v34 */
    /* JADX WARNING: type inference failed for: r4v35 */
    /* JADX WARNING: type inference failed for: r4v36 */
    /* JADX INFO: used method not loaded: com.google.android.gms.internal.ads.aej.a(android.os.Parcel, boolean):null, types can be incorrect */
    /* JADX INFO: used method not loaded: com.google.android.gms.internal.ads.aej.a(android.os.Parcel, android.os.IInterface):null, types can be incorrect */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e0, code lost:
        r3.writeNoException();
        r3.writeString(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0117, code lost:
        r3.writeNoException();
        com.google.android.gms.internal.ads.aej.b(r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0182, code lost:
        r3.writeNoException();
        com.google.android.gms.internal.ads.aej.a(r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x018c, code lost:
        r3.writeNoException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0194, code lost:
        r3.writeNoException();
        com.google.android.gms.internal.ads.aej.a(r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x019b, code lost:
        return true;
     */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r4v1
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.google.android.gms.internal.ads.zzlc, com.google.android.gms.internal.ads.zzkj, com.google.android.gms.internal.ads.zzkh, com.google.android.gms.internal.ads.zzla, com.google.android.gms.internal.ads.zzkg, com.google.android.gms.internal.ads.zzke, com.google.android.gms.internal.ads.zzli, com.google.android.gms.internal.ads.zzlg, com.google.android.gms.internal.ads.zzkz, com.google.android.gms.internal.ads.zzkx]
      uses: [com.google.android.gms.internal.ads.zzkh, com.google.android.gms.internal.ads.zzla, com.google.android.gms.internal.ads.zzke, com.google.android.gms.internal.ads.zzlg, com.google.android.gms.internal.ads.zzkx]
      mth insns count: 125
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
    /* JADX WARNING: Unknown variable types count: 11 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchTransaction(int r1, android.os.Parcel r2, android.os.Parcel r3, int r4) throws android.os.RemoteException {
        /*
            r0 = this;
            r4 = 0
            switch(r1) {
                case 1: goto L_0x0190;
                case 2: goto L_0x0189;
                case 3: goto L_0x017e;
                case 4: goto L_0x0171;
                case 5: goto L_0x016d;
                case 6: goto L_0x0169;
                case 7: goto L_0x014b;
                case 8: goto L_0x012d;
                case 9: goto L_0x0129;
                case 10: goto L_0x0124;
                case 11: goto L_0x011f;
                case 12: goto L_0x0113;
                case 13: goto L_0x0106;
                case 14: goto L_0x00f9;
                case 15: goto L_0x00e8;
                case 16: goto L_0x0004;
                case 17: goto L_0x0004;
                case 18: goto L_0x00dc;
                case 19: goto L_0x00cf;
                case 20: goto L_0x00b0;
                case 21: goto L_0x0091;
                case 22: goto L_0x0088;
                case 23: goto L_0x0082;
                case 24: goto L_0x0075;
                case 25: goto L_0x006c;
                case 26: goto L_0x0066;
                case 27: goto L_0x0004;
                case 28: goto L_0x0004;
                case 29: goto L_0x0059;
                case 30: goto L_0x004c;
                case 31: goto L_0x0046;
                case 32: goto L_0x0040;
                case 33: goto L_0x003a;
                case 34: goto L_0x0031;
                case 35: goto L_0x002b;
                case 36: goto L_0x000c;
                case 37: goto L_0x0006;
                default: goto L_0x0004;
            }
        L_0x0004:
            r1 = 0
            return r1
        L_0x0006:
            android.os.Bundle r1 = r0.zzba()
            goto L_0x0117
        L_0x000c:
            android.os.IBinder r1 = r2.readStrongBinder()
            if (r1 != 0) goto L_0x0013
            goto L_0x0026
        L_0x0013:
            java.lang.String r2 = "com.google.android.gms.ads.internal.client.IAdMetadataListener"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.ads.zzkx
            if (r4 == 0) goto L_0x0021
            r4 = r2
            com.google.android.gms.internal.ads.zzkx r4 = (com.google.android.gms.internal.ads.zzkx) r4
            goto L_0x0026
        L_0x0021:
            com.google.android.gms.internal.ads.zzkz r4 = new com.google.android.gms.internal.ads.zzkz
            r4.<init>(r1)
        L_0x0026:
            r0.zza(r4)
            goto L_0x018c
        L_0x002b:
            java.lang.String r1 = r0.zzck()
            goto L_0x00e0
        L_0x0031:
            boolean r1 = com.google.android.gms.internal.ads.aej.a(r2)
            r0.setImmersiveMode(r1)
            goto L_0x018c
        L_0x003a:
            com.google.android.gms.internal.ads.zzkh r1 = r0.zzbx()
            goto L_0x0194
        L_0x0040:
            com.google.android.gms.internal.ads.zzla r1 = r0.zzbw()
            goto L_0x0194
        L_0x0046:
            java.lang.String r1 = r0.getAdUnitId()
            goto L_0x00e0
        L_0x004c:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzlu> r1 = com.google.android.gms.internal.ads.zzlu.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.aej.a(r2, r1)
            com.google.android.gms.internal.ads.zzlu r1 = (com.google.android.gms.internal.ads.zzlu) r1
            r0.zza(r1)
            goto L_0x018c
        L_0x0059:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzmu> r1 = com.google.android.gms.internal.ads.zzmu.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.aej.a(r2, r1)
            com.google.android.gms.internal.ads.zzmu r1 = (com.google.android.gms.internal.ads.zzmu) r1
            r0.zza(r1)
            goto L_0x018c
        L_0x0066:
            com.google.android.gms.internal.ads.zzlo r1 = r0.getVideoController()
            goto L_0x0194
        L_0x006c:
            java.lang.String r1 = r2.readString()
            r0.setUserId(r1)
            goto L_0x018c
        L_0x0075:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.internal.ads.zzahe r1 = com.google.android.gms.internal.ads.zzahf.zzz(r1)
            r0.zza(r1)
            goto L_0x018c
        L_0x0082:
            boolean r1 = r0.isLoading()
            goto L_0x0182
        L_0x0088:
            boolean r1 = com.google.android.gms.internal.ads.aej.a(r2)
            r0.setManualImpressionsEnabled(r1)
            goto L_0x018c
        L_0x0091:
            android.os.IBinder r1 = r2.readStrongBinder()
            if (r1 != 0) goto L_0x0098
            goto L_0x00ab
        L_0x0098:
            java.lang.String r2 = "com.google.android.gms.ads.internal.client.ICorrelationIdProvider"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.ads.zzlg
            if (r4 == 0) goto L_0x00a6
            r4 = r2
            com.google.android.gms.internal.ads.zzlg r4 = (com.google.android.gms.internal.ads.zzlg) r4
            goto L_0x00ab
        L_0x00a6:
            com.google.android.gms.internal.ads.zzli r4 = new com.google.android.gms.internal.ads.zzli
            r4.<init>(r1)
        L_0x00ab:
            r0.zza(r4)
            goto L_0x018c
        L_0x00b0:
            android.os.IBinder r1 = r2.readStrongBinder()
            if (r1 != 0) goto L_0x00b7
            goto L_0x00ca
        L_0x00b7:
            java.lang.String r2 = "com.google.android.gms.ads.internal.client.IAdClickListener"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.ads.zzke
            if (r4 == 0) goto L_0x00c5
            r4 = r2
            com.google.android.gms.internal.ads.zzke r4 = (com.google.android.gms.internal.ads.zzke) r4
            goto L_0x00ca
        L_0x00c5:
            com.google.android.gms.internal.ads.zzkg r4 = new com.google.android.gms.internal.ads.zzkg
            r4.<init>(r1)
        L_0x00ca:
            r0.zza(r4)
            goto L_0x018c
        L_0x00cf:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.internal.ads.zzod r1 = com.google.android.gms.internal.ads.zzoe.zzf(r1)
            r0.zza(r1)
            goto L_0x018c
        L_0x00dc:
            java.lang.String r1 = r0.getMediationAdapterClassName()
        L_0x00e0:
            r3.writeNoException()
            r3.writeString(r1)
            goto L_0x019a
        L_0x00e8:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.internal.ads.zzabc r1 = com.google.android.gms.internal.ads.zzabd.zzx(r1)
            java.lang.String r2 = r2.readString()
            r0.zza(r1, r2)
            goto L_0x018c
        L_0x00f9:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.internal.ads.zzaaw r1 = com.google.android.gms.internal.ads.zzaax.zzv(r1)
            r0.zza(r1)
            goto L_0x018c
        L_0x0106:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjn> r1 = com.google.android.gms.internal.ads.zzjn.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.aej.a(r2, r1)
            com.google.android.gms.internal.ads.zzjn r1 = (com.google.android.gms.internal.ads.zzjn) r1
            r0.zza(r1)
            goto L_0x018c
        L_0x0113:
            com.google.android.gms.internal.ads.zzjn r1 = r0.zzbk()
        L_0x0117:
            r3.writeNoException()
            com.google.android.gms.internal.ads.aej.b(r3, r1)
            goto L_0x019a
        L_0x011f:
            r0.zzbm()
            goto L_0x018c
        L_0x0124:
            r0.stopLoading()
            goto L_0x018c
        L_0x0129:
            r0.showInterstitial()
            goto L_0x018c
        L_0x012d:
            android.os.IBinder r1 = r2.readStrongBinder()
            if (r1 != 0) goto L_0x0134
            goto L_0x0147
        L_0x0134:
            java.lang.String r2 = "com.google.android.gms.ads.internal.client.IAppEventListener"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.ads.zzla
            if (r4 == 0) goto L_0x0142
            r4 = r2
            com.google.android.gms.internal.ads.zzla r4 = (com.google.android.gms.internal.ads.zzla) r4
            goto L_0x0147
        L_0x0142:
            com.google.android.gms.internal.ads.zzlc r4 = new com.google.android.gms.internal.ads.zzlc
            r4.<init>(r1)
        L_0x0147:
            r0.zza(r4)
            goto L_0x018c
        L_0x014b:
            android.os.IBinder r1 = r2.readStrongBinder()
            if (r1 != 0) goto L_0x0152
            goto L_0x0165
        L_0x0152:
            java.lang.String r2 = "com.google.android.gms.ads.internal.client.IAdListener"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.ads.zzkh
            if (r4 == 0) goto L_0x0160
            r4 = r2
            com.google.android.gms.internal.ads.zzkh r4 = (com.google.android.gms.internal.ads.zzkh) r4
            goto L_0x0165
        L_0x0160:
            com.google.android.gms.internal.ads.zzkj r4 = new com.google.android.gms.internal.ads.zzkj
            r4.<init>(r1)
        L_0x0165:
            r0.zza(r4)
            goto L_0x018c
        L_0x0169:
            r0.resume()
            goto L_0x018c
        L_0x016d:
            r0.pause()
            goto L_0x018c
        L_0x0171:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjj> r1 = com.google.android.gms.internal.ads.zzjj.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.aej.a(r2, r1)
            com.google.android.gms.internal.ads.zzjj r1 = (com.google.android.gms.internal.ads.zzjj) r1
            boolean r1 = r0.zzb(r1)
            goto L_0x0182
        L_0x017e:
            boolean r1 = r0.isReady()
        L_0x0182:
            r3.writeNoException()
            com.google.android.gms.internal.ads.aej.a(r3, r1)
            goto L_0x019a
        L_0x0189:
            r0.destroy()
        L_0x018c:
            r3.writeNoException()
            goto L_0x019a
        L_0x0190:
            com.google.android.gms.dynamic.IObjectWrapper r1 = r0.zzbj()
        L_0x0194:
            r3.writeNoException()
            com.google.android.gms.internal.ads.aej.a(r3, r1)
        L_0x019a:
            r1 = 1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzkt.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
