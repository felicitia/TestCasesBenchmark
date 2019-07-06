package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg.zzc;
import com.google.android.gms.internal.clearcut.zzcg.zzd;

public final class zzgc extends zzd<zzgc, zza> implements zzdq {
    private static volatile zzdz<zzgc> zzbg;
    /* access modifiers changed from: private */
    public static final zzgc zzsg = new zzgc();
    private byte zzsf = 2;

    public static final class zza extends zzc<zzgc, zza> implements zzdq {
        private zza() {
            super(zzgc.zzsg);
        }

        /* synthetic */ zza(zzgd zzgd) {
            this();
        }
    }

    static {
        zzcg.zza(zzgc.class, zzsg);
    }

    private zzgc() {
    }

    public static zzgc zzer() {
        return zzsg;
    }

    /* JADX WARNING: type inference failed for: r2v10, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgc>] */
    /* JADX WARNING: type inference failed for: r2v11, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgc>] */
    /* JADX WARNING: type inference failed for: r2v17 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v12, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgc>]
      assigns: [com.google.android.gms.internal.clearcut.zzcg$zzb]
      uses: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgc>]
      mth insns count: 40
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/871566395.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$34/1534130292.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1037163664.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
        /*
            r1 = this;
            int[] r4 = com.google.android.gms.internal.clearcut.zzgd.zzba
            r0 = 1
            int r2 = r2 - r0
            r2 = r4[r2]
            r4 = 0
            switch(r2) {
                case 1: goto L_0x004a;
                case 2: goto L_0x0044;
                case 3: goto L_0x003b;
                case 4: goto L_0x0038;
                case 5: goto L_0x001e;
                case 6: goto L_0x0017;
                case 7: goto L_0x0010;
                default: goto L_0x000a;
            }
        L_0x000a:
            java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
            r2.<init>()
            throw r2
        L_0x0010:
            if (r3 != 0) goto L_0x0013
            r0 = 0
        L_0x0013:
            byte r2 = (byte) r0
            r1.zzsf = r2
            return r4
        L_0x0017:
            byte r2 = r1.zzsf
            java.lang.Byte r2 = java.lang.Byte.valueOf(r2)
            return r2
        L_0x001e:
            com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgc> r2 = zzbg
            if (r2 != 0) goto L_0x0037
            java.lang.Class<com.google.android.gms.internal.clearcut.zzgc> r3 = com.google.android.gms.internal.clearcut.zzgc.class
            monitor-enter(r3)
            com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgc> r2 = zzbg     // Catch:{ all -> 0x0034 }
            if (r2 != 0) goto L_0x0032
            com.google.android.gms.internal.clearcut.zzcg$zzb r2 = new com.google.android.gms.internal.clearcut.zzcg$zzb     // Catch:{ all -> 0x0034 }
            com.google.android.gms.internal.clearcut.zzgc r4 = zzsg     // Catch:{ all -> 0x0034 }
            r2.<init>(r4)     // Catch:{ all -> 0x0034 }
            zzbg = r2     // Catch:{ all -> 0x0034 }
        L_0x0032:
            monitor-exit(r3)     // Catch:{ all -> 0x0034 }
            return r2
        L_0x0034:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0034 }
            throw r2
        L_0x0037:
            return r2
        L_0x0038:
            com.google.android.gms.internal.clearcut.zzgc r2 = zzsg
            return r2
        L_0x003b:
            java.lang.String r2 = "\u0003\u0000"
            com.google.android.gms.internal.clearcut.zzgc r3 = zzsg
            java.lang.Object r2 = zza(r3, r2, r4)
            return r2
        L_0x0044:
            com.google.android.gms.internal.clearcut.zzgc$zza r2 = new com.google.android.gms.internal.clearcut.zzgc$zza
            r2.<init>(r4)
            return r2
        L_0x004a:
            com.google.android.gms.internal.clearcut.zzgc r2 = new com.google.android.gms.internal.clearcut.zzgc
            r2.<init>()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzgc.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
    }
}
