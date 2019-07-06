package com.google.android.gms.internal.clearcut;

public final class zzap {

    public static final class zza extends zzcg<zza, C0000zza> implements zzdq {
        private static volatile zzdz<zza> zzbg;
        /* access modifiers changed from: private */
        public static final zza zzes = new zza();
        private int zzbb;
        private int zzel;
        private int zzem;
        private int zzen;
        private int zzeo;
        private int zzep;
        private int zzeq;
        private int zzer;

        /* renamed from: com.google.android.gms.internal.clearcut.zzap$zza$zza reason: collision with other inner class name */
        public static final class C0000zza extends com.google.android.gms.internal.clearcut.zzcg.zza<zza, C0000zza> implements zzdq {
            private C0000zza() {
                super(zza.zzes);
            }

            /* synthetic */ C0000zza(zzaq zzaq) {
                this();
            }
        }

        public enum zzb implements zzcj {
            UNKNOWN(0),
            ON(1),
            OFF(2);
            
            private static final zzck<zzb> zzbq = null;
            private final int value;

            static {
                zzbq = new zzar();
            }

            private zzb(int i) {
                this.value = i;
            }

            public static zzck<zzb> zzd() {
                return zzbq;
            }

            public static zzb zze(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN;
                    case 1:
                        return ON;
                    case 2:
                        return OFF;
                    default:
                        return null;
                }
            }

            public final int zzc() {
                return this.value;
            }
        }

        static {
            zzcg.zza(zza.class, zzes);
        }

        private zza() {
        }

        /* JADX WARNING: type inference failed for: r2v11, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzap$zza>] */
        /* JADX WARNING: type inference failed for: r2v12, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v13, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzap$zza>] */
        /* JADX WARNING: type inference failed for: r2v16 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzap$zza>]
          assigns: [com.google.android.gms.internal.clearcut.zzcg$zzb]
          uses: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzap$zza>]
          mth insns count: 65
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
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/410251182.accept(Unknown Source)
        	at java.util.ArrayList.forEach(ArrayList.java:1249)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
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
                int[] r3 = com.google.android.gms.internal.clearcut.zzaq.zzba
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x00a5;
                    case 2: goto L_0x009f;
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
                com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzap$zza> r2 = zzbg
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.clearcut.zzap$zza> r3 = com.google.android.gms.internal.clearcut.zzap.zza.class
                monitor-enter(r3)
                com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzap$zza> r2 = zzbg     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.clearcut.zzcg$zzb r2 = new com.google.android.gms.internal.clearcut.zzcg$zzb     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.clearcut.zzap$zza r4 = zzes     // Catch:{ all -> 0x002c }
                r2.<init>(r4)     // Catch:{ all -> 0x002c }
                zzbg = r2     // Catch:{ all -> 0x002c }
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
                com.google.android.gms.internal.clearcut.zzap$zza r2 = zzes
                return r2
            L_0x0033:
                r2 = 15
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbb"
                r2[r3] = r0
                java.lang.String r3 = "zzel"
                r2[r4] = r3
                r3 = 2
                com.google.android.gms.internal.clearcut.zzck r4 = com.google.android.gms.internal.clearcut.zzap.zza.zzb.zzd()
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzem"
                r2[r3] = r4
                r3 = 4
                com.google.android.gms.internal.clearcut.zzck r4 = com.google.android.gms.internal.clearcut.zzap.zza.zzb.zzd()
                r2[r3] = r4
                r3 = 5
                java.lang.String r4 = "zzen"
                r2[r3] = r4
                r3 = 6
                com.google.android.gms.internal.clearcut.zzck r4 = com.google.android.gms.internal.clearcut.zzap.zza.zzb.zzd()
                r2[r3] = r4
                r3 = 7
                java.lang.String r4 = "zzeo"
                r2[r3] = r4
                r3 = 8
                com.google.android.gms.internal.clearcut.zzck r4 = com.google.android.gms.internal.clearcut.zzap.zza.zzb.zzd()
                r2[r3] = r4
                r3 = 9
                java.lang.String r4 = "zzep"
                r2[r3] = r4
                r3 = 10
                com.google.android.gms.internal.clearcut.zzck r4 = com.google.android.gms.internal.clearcut.zzap.zza.zzb.zzd()
                r2[r3] = r4
                r3 = 11
                java.lang.String r4 = "zzeq"
                r2[r3] = r4
                r3 = 12
                com.google.android.gms.internal.clearcut.zzck r4 = com.google.android.gms.internal.clearcut.zzap.zza.zzb.zzd()
                r2[r3] = r4
                r3 = 13
                java.lang.String r4 = "zzer"
                r2[r3] = r4
                r3 = 14
                com.google.android.gms.internal.clearcut.zzck r4 = com.google.android.gms.internal.clearcut.zzap.zza.zzb.zzd()
                r2[r3] = r4
                java.lang.String r3 = "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\b\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001\u0003\f\u0002\u0004\f\u0003\u0005\f\u0004\u0006\f\u0005\u0007\f\u0006"
                com.google.android.gms.internal.clearcut.zzap$zza r4 = zzes
                java.lang.Object r2 = zza(r4, r3, r2)
                return r2
            L_0x009f:
                com.google.android.gms.internal.clearcut.zzap$zza$zza r2 = new com.google.android.gms.internal.clearcut.zzap$zza$zza
                r2.<init>(r3)
                return r2
            L_0x00a5:
                com.google.android.gms.internal.clearcut.zzap$zza r2 = new com.google.android.gms.internal.clearcut.zzap$zza
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzap.zza.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }
    }
}
