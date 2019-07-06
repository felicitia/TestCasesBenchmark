package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.l;
import com.google.android.gms.ads.mediation.m;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzzp extends zzzk {
    private final pq zzbvi;

    public zzzp(pq pqVar) {
        this.zzbvi = pqVar;
    }

    private static Bundle zzbt(String str) throws RemoteException {
        String str2 = "Server parameters: ";
        String valueOf = String.valueOf(str);
        ka.e(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        try {
            Bundle bundle = new Bundle();
            if (str == null) {
                return bundle;
            }
            JSONObject jSONObject = new JSONObject(str);
            Bundle bundle2 = new Bundle();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str3 = (String) keys.next();
                bundle2.putString(str3, jSONObject.getString(str3));
            }
            return bundle2;
        } catch (JSONException e) {
            ka.b("", e);
            throw new RemoteException();
        }
    }

    public final zzlo getVideoController() {
        if (!(this.zzbvi instanceof m)) {
            return null;
        }
        try {
            return ((m) this.zzbvi).getVideoController();
        } catch (Throwable th) {
            ka.b("", th);
            return null;
        }
    }

    public final void showInterstitial() throws RemoteException {
        po poVar = null;
        try {
            poVar.a();
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.ads.asa, com.google.android.gms.internal.ads.pt] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [com.google.android.gms.internal.ads.asa, com.google.android.gms.internal.ads.pt]
      assigns: [com.google.android.gms.internal.ads.asa]
      uses: [com.google.android.gms.internal.ads.pt]
      mth insns count: 52
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
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.dynamic.IObjectWrapper r6, java.lang.String r7, android.os.Bundle r8, com.google.android.gms.internal.ads.zzzm r9) throws android.os.RemoteException {
        /*
            r5 = this;
            com.google.android.gms.internal.ads.asa r0 = new com.google.android.gms.internal.ads.asa     // Catch:{ Throwable -> 0x006e }
            r0.<init>(r5, r9)     // Catch:{ Throwable -> 0x006e }
            com.google.android.gms.internal.ads.pq r9 = r5.zzbvi     // Catch:{ Throwable -> 0x006e }
            com.google.android.gms.internal.ads.ps r1 = new com.google.android.gms.internal.ads.ps     // Catch:{ Throwable -> 0x006e }
            java.lang.Object r6 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r6)     // Catch:{ Throwable -> 0x006e }
            android.content.Context r6 = (android.content.Context) r6     // Catch:{ Throwable -> 0x006e }
            r2 = -1
            int r3 = r7.hashCode()     // Catch:{ Throwable -> 0x006e }
            r4 = -1396342996(0xffffffffacc57f2c, float:-5.6131957E-12)
            if (r3 == r4) goto L_0x0047
            r4 = -1052618729(0xffffffffc1425017, float:-12.144553)
            if (r3 == r4) goto L_0x003d
            r4 = -239580146(0xfffffffff1b84c0e, float:-1.82519E30)
            if (r3 == r4) goto L_0x0033
            r4 = 604727084(0x240b672c, float:3.022821E-17)
            if (r3 == r4) goto L_0x0029
            goto L_0x0050
        L_0x0029:
            java.lang.String r3 = "interstitial"
            boolean r7 = r7.equals(r3)     // Catch:{ Throwable -> 0x006e }
            if (r7 == 0) goto L_0x0050
            r2 = 1
            goto L_0x0050
        L_0x0033:
            java.lang.String r3 = "rewarded"
            boolean r7 = r7.equals(r3)     // Catch:{ Throwable -> 0x006e }
            if (r7 == 0) goto L_0x0050
            r2 = 2
            goto L_0x0050
        L_0x003d:
            java.lang.String r3 = "native"
            boolean r7 = r7.equals(r3)     // Catch:{ Throwable -> 0x006e }
            if (r7 == 0) goto L_0x0050
            r2 = 3
            goto L_0x0050
        L_0x0047:
            java.lang.String r3 = "banner"
            boolean r7 = r7.equals(r3)     // Catch:{ Throwable -> 0x006e }
            if (r7 == 0) goto L_0x0050
            r2 = 0
        L_0x0050:
            switch(r2) {
                case 0: goto L_0x005f;
                case 1: goto L_0x005c;
                case 2: goto L_0x0059;
                case 3: goto L_0x0056;
                default: goto L_0x0053;
            }     // Catch:{ Throwable -> 0x006e }
        L_0x0053:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException     // Catch:{ Throwable -> 0x006e }
            goto L_0x0068
        L_0x0056:
            int r7 = com.google.android.gms.internal.ads.pr.d     // Catch:{ Throwable -> 0x006e }
            goto L_0x0061
        L_0x0059:
            int r7 = com.google.android.gms.internal.ads.pr.c     // Catch:{ Throwable -> 0x006e }
            goto L_0x0061
        L_0x005c:
            int r7 = com.google.android.gms.internal.ads.pr.b     // Catch:{ Throwable -> 0x006e }
            goto L_0x0061
        L_0x005f:
            int r7 = com.google.android.gms.internal.ads.pr.a     // Catch:{ Throwable -> 0x006e }
        L_0x0061:
            r1.<init>(r6, r7, r8)     // Catch:{ Throwable -> 0x006e }
            r9.a(r1, r0)     // Catch:{ Throwable -> 0x006e }
            return
        L_0x0068:
            java.lang.String r7 = "Internal Error"
            r6.<init>(r7)     // Catch:{ Throwable -> 0x006e }
            throw r6     // Catch:{ Throwable -> 0x006e }
        L_0x006e:
            r6 = move-exception
            java.lang.String r7 = "Error generating signals for RTB"
            com.google.android.gms.internal.ads.ka.b(r7, r6)
            android.os.RemoteException r6 = new android.os.RemoteException
            r6.<init>()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzzp.zza(com.google.android.gms.dynamic.IObjectWrapper, java.lang.String, android.os.Bundle, com.google.android.gms.internal.ads.zzzm):void");
    }

    public final void zza(byte[] bArr, String str, Bundle bundle, IObjectWrapper iObjectWrapper, zzzf zzzf, zzxt zzxt, zzjn zzjn) throws RemoteException {
        try {
            ary ary = new ary(this, zzzf, zzxt);
            pq pqVar = this.zzbvi;
            new pp((Context) ObjectWrapper.unwrap(iObjectWrapper), bArr, zzbt(str), bundle);
            l.a(zzjn.width, zzjn.height, zzjn.zzarb);
            ary.a(String.valueOf(pqVar.getClass().getSimpleName()).concat(" does not support banner."));
        } catch (Throwable th) {
            ka.b("Adapter failed to render banner ad.", th);
            throw new RemoteException();
        }
    }

    public final void zza(byte[] bArr, String str, Bundle bundle, IObjectWrapper iObjectWrapper, zzzh zzzh, zzxt zzxt) throws RemoteException {
        try {
            arz arz = new arz(this, zzzh, zzxt);
            pq pqVar = this.zzbvi;
            new pp((Context) ObjectWrapper.unwrap(iObjectWrapper), bArr, zzbt(str), bundle);
            arz.a(String.valueOf(pqVar.getClass().getSimpleName()).concat(" does not support interstitial."));
        } catch (Throwable th) {
            ka.b("Adapter failed to render interstitial ad.", th);
            throw new RemoteException();
        }
    }

    public final zzzt zznc() throws RemoteException {
        return zzzt.zza(this.zzbvi.b());
    }

    public final zzzt zznd() throws RemoteException {
        return zzzt.zza(this.zzbvi.a());
    }
}
