package com.google.android.gms.internal.clearcut;

public final class zzt {

    public static final class zza extends zzcg<zza, C0005zza> implements zzdq {
        /* access modifiers changed from: private */
        public static final zza zzbf = new zza();
        private static volatile zzdz<zza> zzbg;
        private int zzbb;
        private int zzbc;
        private int zzbd;
        private int zzbe;

        /* renamed from: com.google.android.gms.internal.clearcut.zzt$zza$zza reason: collision with other inner class name */
        public static final class C0005zza extends com.google.android.gms.internal.clearcut.zzcg.zza<zza, C0005zza> implements zzdq {
            private C0005zza() {
                super(zza.zzbf);
            }

            /* synthetic */ C0005zza(zzu zzu) {
                this();
            }
        }

        public enum zzb implements zzcj {
            ARCH_UNKNOWN(0),
            ARCH_NON_NATIVE(1),
            ARCH_ARMV5(2),
            ARCH_ARMV7(4),
            ARCH_ARM64(5),
            ARCH_MIPS(6),
            ARCH_MIPS_64(7),
            ARCH_X86(8),
            ARCH_X86_64(9);
            
            private static final zzck<zzb> zzbq = null;
            private final int value;

            static {
                zzbq = new zzv();
            }

            private zzb(int i) {
                this.value = i;
            }

            public static zzb zza(int i) {
                switch (i) {
                    case 0:
                        return ARCH_UNKNOWN;
                    case 1:
                        return ARCH_NON_NATIVE;
                    case 2:
                        return ARCH_ARMV5;
                    case 4:
                        return ARCH_ARMV7;
                    case 5:
                        return ARCH_ARM64;
                    case 6:
                        return ARCH_MIPS;
                    case 7:
                        return ARCH_MIPS_64;
                    case 8:
                        return ARCH_X86;
                    case 9:
                        return ARCH_X86_64;
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

        public enum zzc implements zzcj {
            BUILD_TYPE_UNKNOWN(0),
            BUILD_TYPE_PROD(1),
            BUILD_TYPE_INTERNAL(2),
            BUILD_TYPE_PRODLMP(3),
            BUILD_TYPE_THINGS(4),
            BUILD_TYPE_PRODMNC(5),
            BUILD_TYPE_WEARABLE(6),
            BUILD_TYPE_AUTO(7),
            BUILD_TYPE_SIDEWINDERMNC(8),
            BUILD_TYPE_ATV(9),
            BUILD_TYPE_PRODPIX(10),
            BUILD_TYPE_PRODPI(11);
            
            private static final zzck<zzc> zzbq = null;
            private final int value;

            static {
                zzbq = new zzw();
            }

            private zzc(int i) {
                this.value = i;
            }

            public static zzc zzc(int i) {
                switch (i) {
                    case 0:
                        return BUILD_TYPE_UNKNOWN;
                    case 1:
                        return BUILD_TYPE_PROD;
                    case 2:
                        return BUILD_TYPE_INTERNAL;
                    case 3:
                        return BUILD_TYPE_PRODLMP;
                    case 4:
                        return BUILD_TYPE_THINGS;
                    case 5:
                        return BUILD_TYPE_PRODMNC;
                    case 6:
                        return BUILD_TYPE_WEARABLE;
                    case 7:
                        return BUILD_TYPE_AUTO;
                    case 8:
                        return BUILD_TYPE_SIDEWINDERMNC;
                    case 9:
                        return BUILD_TYPE_ATV;
                    case 10:
                        return BUILD_TYPE_PRODPIX;
                    case 11:
                        return BUILD_TYPE_PRODPI;
                    default:
                        return null;
                }
            }

            public static zzck<zzc> zzd() {
                return zzbq;
            }

            public final int zzc() {
                return this.value;
            }
        }

        public enum zzd implements zzcj {
            DENSITY_UNKNOWN(0),
            DENSITY_ALLDPI(1),
            DENSITY_LDPI(2),
            DENSITY_MDPI(3),
            DENSITY_TVDPI(4),
            DENSITY_HDPI(5),
            DENSITY_XHDPI(7),
            DENSITY_DPI400(8),
            DENSITY_XXHDPI(9),
            DENSITY_XXXHDPI(10);
            
            private static final zzck<zzd> zzbq = null;
            private final int value;

            static {
                zzbq = new zzx();
            }

            private zzd(int i) {
                this.value = i;
            }

            public static zzck<zzd> zzd() {
                return zzbq;
            }

            public static zzd zzd(int i) {
                switch (i) {
                    case 0:
                        return DENSITY_UNKNOWN;
                    case 1:
                        return DENSITY_ALLDPI;
                    case 2:
                        return DENSITY_LDPI;
                    case 3:
                        return DENSITY_MDPI;
                    case 4:
                        return DENSITY_TVDPI;
                    case 5:
                        return DENSITY_HDPI;
                    case 7:
                        return DENSITY_XHDPI;
                    case 8:
                        return DENSITY_DPI400;
                    case 9:
                        return DENSITY_XXHDPI;
                    case 10:
                        return DENSITY_XXXHDPI;
                    default:
                        return null;
                }
            }

            public final int zzc() {
                return this.value;
            }
        }

        static {
            zzcg.zza(zza.class, zzbf);
        }

        private zza() {
        }

        /* JADX WARNING: type inference failed for: r2v11, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzt$zza>] */
        /* JADX WARNING: type inference failed for: r2v12, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v13, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzt$zza>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
        /* JADX WARNING: type inference failed for: r2v16 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzt$zza>, com.google.android.gms.internal.clearcut.zzcg$zzb]
          assigns: [com.google.android.gms.internal.clearcut.zzcg$zzb]
          uses: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzt$zza>]
          mth insns count: 49
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
        public final java.lang.Object zza(int r2, java.lang.Object r3, java.lang.Object r4) {
            /*
                r1 = this;
                int[] r3 = com.google.android.gms.internal.clearcut.zzu.zzba
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x006d;
                    case 2: goto L_0x0067;
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
                com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzt$zza> r2 = zzbg
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.clearcut.zzt$zza> r3 = com.google.android.gms.internal.clearcut.zzt.zza.class
                monitor-enter(r3)
                com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzt$zza> r2 = zzbg     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.clearcut.zzcg$zzb r2 = new com.google.android.gms.internal.clearcut.zzcg$zzb     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.clearcut.zzt$zza r4 = zzbf     // Catch:{ all -> 0x002c }
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
                com.google.android.gms.internal.clearcut.zzt$zza r2 = zzbf
                return r2
            L_0x0033:
                r2 = 7
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbb"
                r2[r3] = r0
                java.lang.String r3 = "zzbc"
                r2[r4] = r3
                r3 = 2
                com.google.android.gms.internal.clearcut.zzck r4 = com.google.android.gms.internal.clearcut.zzt.zza.zzc.zzd()
                r2[r3] = r4
                r3 = 3
                java.lang.String r4 = "zzbd"
                r2[r3] = r4
                r3 = 4
                com.google.android.gms.internal.clearcut.zzck r4 = com.google.android.gms.internal.clearcut.zzt.zza.zzb.zzd()
                r2[r3] = r4
                r3 = 5
                java.lang.String r4 = "zzbe"
                r2[r3] = r4
                r3 = 6
                com.google.android.gms.internal.clearcut.zzck r4 = com.google.android.gms.internal.clearcut.zzt.zza.zzd.zzd()
                r2[r3] = r4
                java.lang.String r3 = "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001\u0003\f\u0002"
                com.google.android.gms.internal.clearcut.zzt$zza r4 = zzbf
                java.lang.Object r2 = zza(r4, r3, r2)
                return r2
            L_0x0067:
                com.google.android.gms.internal.clearcut.zzt$zza$zza r2 = new com.google.android.gms.internal.clearcut.zzt$zza$zza
                r2.<init>(r3)
                return r2
            L_0x006d:
                com.google.android.gms.internal.clearcut.zzt$zza r2 = new com.google.android.gms.internal.clearcut.zzt$zza
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzt.zza.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }
    }
}
