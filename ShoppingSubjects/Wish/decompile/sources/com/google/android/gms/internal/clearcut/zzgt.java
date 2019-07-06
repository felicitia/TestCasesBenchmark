package com.google.android.gms.internal.clearcut;

public final class zzgt {

    public static final class zza extends zzcg<zza, C0002zza> implements zzdq {
        private static volatile zzdz<zza> zzbg;
        /* access modifiers changed from: private */
        public static final zza zzbil = new zza();

        /* renamed from: com.google.android.gms.internal.clearcut.zzgt$zza$zza reason: collision with other inner class name */
        public static final class C0002zza extends com.google.android.gms.internal.clearcut.zzcg.zza<zza, C0002zza> implements zzdq {
            private C0002zza() {
                super(zza.zzbil);
            }

            /* synthetic */ C0002zza(zzgu zzgu) {
                this();
            }
        }

        public enum zzb implements zzcj {
            NO_RESTRICTION(0),
            SIDEWINDER_DEVICE(1),
            LATCHSKY_DEVICE(2);
            
            private static final zzck<zzb> zzbq = null;
            private final int value;

            static {
                zzbq = new zzgv();
            }

            private zzb(int i) {
                this.value = i;
            }

            public static zzb zzbe(int i) {
                switch (i) {
                    case 0:
                        return NO_RESTRICTION;
                    case 1:
                        return SIDEWINDER_DEVICE;
                    case 2:
                        return LATCHSKY_DEVICE;
                    default:
                        return null;
                }
            }

            public static zzck<zzb> zzd() {
                return zzbq;
            }

            public final int zzc() {
                return this.value;
            }
        }

        static {
            zzcg.zza(zza.class, zzbil);
        }

        private zza() {
        }

        /* JADX WARNING: type inference failed for: r1v10, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgt$zza>] */
        /* JADX WARNING: type inference failed for: r1v11, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r1v12, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgt$zza>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
        /* JADX WARNING: type inference failed for: r1v15 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v12, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgt$zza>, com.google.android.gms.internal.clearcut.zzcg$zzb]
          assigns: [com.google.android.gms.internal.clearcut.zzcg$zzb]
          uses: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgt$zza>]
          mth insns count: 34
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
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at jadx.core.ProcessClass$$Lambda$69/1017384824.accept(Unknown Source)
        	at java.util.ArrayList.forEach(ArrayList.java:1249)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
        	at jadx.api.JadxDecompiler$$Lambda$28/1037163664.run(Unknown Source)
         */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object zza(int r1, java.lang.Object r2, java.lang.Object r3) {
            /*
                r0 = this;
                int[] r2 = com.google.android.gms.internal.clearcut.zzgu.zzba
                r3 = 1
                int r1 = r1 - r3
                r1 = r2[r1]
                r2 = 0
                switch(r1) {
                    case 1: goto L_0x0042;
                    case 2: goto L_0x003c;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0030;
                    case 5: goto L_0x0016;
                    case 6: goto L_0x0011;
                    case 7: goto L_0x0010;
                    default: goto L_0x000a;
                }
            L_0x000a:
                java.lang.UnsupportedOperationException r1 = new java.lang.UnsupportedOperationException
                r1.<init>()
                throw r1
            L_0x0010:
                return r2
            L_0x0011:
                java.lang.Byte r1 = java.lang.Byte.valueOf(r3)
                return r1
            L_0x0016:
                com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgt$zza> r1 = zzbg
                if (r1 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.clearcut.zzgt$zza> r2 = com.google.android.gms.internal.clearcut.zzgt.zza.class
                monitor-enter(r2)
                com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgt$zza> r1 = zzbg     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x002a
                com.google.android.gms.internal.clearcut.zzcg$zzb r1 = new com.google.android.gms.internal.clearcut.zzcg$zzb     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.clearcut.zzgt$zza r3 = zzbil     // Catch:{ all -> 0x002c }
                r1.<init>(r3)     // Catch:{ all -> 0x002c }
                zzbg = r1     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r2)     // Catch:{ all -> 0x002c }
                return r1
            L_0x002c:
                r1 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x002c }
                throw r1
            L_0x002f:
                return r1
            L_0x0030:
                com.google.android.gms.internal.clearcut.zzgt$zza r1 = zzbil
                return r1
            L_0x0033:
                java.lang.String r1 = "\u0001\u0000"
                com.google.android.gms.internal.clearcut.zzgt$zza r3 = zzbil
                java.lang.Object r1 = zza(r3, r1, r2)
                return r1
            L_0x003c:
                com.google.android.gms.internal.clearcut.zzgt$zza$zza r1 = new com.google.android.gms.internal.clearcut.zzgt$zza$zza
                r1.<init>(r2)
                return r1
            L_0x0042:
                com.google.android.gms.internal.clearcut.zzgt$zza r1 = new com.google.android.gms.internal.clearcut.zzgt$zza
                r1.<init>()
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzgt.zza.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }
    }
}
