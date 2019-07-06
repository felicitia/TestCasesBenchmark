package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg.zzg;
import java.util.List;

public final class zzgw {

    public static final class zza extends zzcg<zza, C0003zza> implements zzdq {
        private static volatile zzdz<zza> zzbg;
        /* access modifiers changed from: private */
        public static final zza zzbir = new zza();
        private zzcn<zzb> zzbiq = zzbb();

        /* renamed from: com.google.android.gms.internal.clearcut.zzgw$zza$zza reason: collision with other inner class name */
        public static final class C0003zza extends com.google.android.gms.internal.clearcut.zzcg.zza<zza, C0003zza> implements zzdq {
            private C0003zza() {
                super(zza.zzbir);
            }

            /* synthetic */ C0003zza(zzgx zzgx) {
                this();
            }
        }

        public static final class zzb extends zzcg<zzb, C0004zza> implements zzdq {
            private static volatile zzdz<zzb> zzbg;
            /* access modifiers changed from: private */
            public static final zzb zzbiv = new zzb();
            private int zzbb;
            private String zzbis = "";
            private long zzbit;
            private long zzbiu;
            private int zzya;

            /* renamed from: com.google.android.gms.internal.clearcut.zzgw$zza$zzb$zza reason: collision with other inner class name */
            public static final class C0004zza extends com.google.android.gms.internal.clearcut.zzcg.zza<zzb, C0004zza> implements zzdq {
                private C0004zza() {
                    super(zzb.zzbiv);
                }

                /* synthetic */ C0004zza(zzgx zzgx) {
                    this();
                }

                public final C0004zza zzn(String str) {
                    zzbf();
                    ((zzb) this.zzjt).zzm(str);
                    return this;
                }

                public final C0004zza zzr(long j) {
                    zzbf();
                    ((zzb) this.zzjt).zzp(j);
                    return this;
                }

                public final C0004zza zzs(long j) {
                    zzbf();
                    ((zzb) this.zzjt).zzq(j);
                    return this;
                }
            }

            static {
                zzcg.zza(zzb.class, zzbiv);
            }

            private zzb() {
            }

            public static C0004zza zzfz() {
                return (C0004zza) ((com.google.android.gms.internal.clearcut.zzcg.zza) zzbiv.zza(zzg.zzkh, (Object) null, (Object) null));
            }

            /* access modifiers changed from: private */
            public final void zzm(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.zzbb |= 2;
                this.zzbis = str;
            }

            /* access modifiers changed from: private */
            public final void zzp(long j) {
                this.zzbb |= 4;
                this.zzbit = j;
            }

            /* access modifiers changed from: private */
            public final void zzq(long j) {
                this.zzbb |= 8;
                this.zzbiu = j;
            }

            public final int getEventCode() {
                return this.zzya;
            }

            /* JADX WARNING: type inference failed for: r2v11, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza$zzb>] */
            /* JADX WARNING: type inference failed for: r2v12, types: [java.lang.Object] */
            /* JADX WARNING: type inference failed for: r2v13, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza$zzb>] */
            /* JADX WARNING: type inference failed for: r2v16 */
            /* access modifiers changed from: protected */
            /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza$zzb>]
              assigns: [com.google.android.gms.internal.clearcut.zzcg$zzb]
              uses: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza$zzb>]
              mth insns count: 45
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
                    int[] r3 = com.google.android.gms.internal.clearcut.zzgx.zzba
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
                    com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza$zzb> r2 = zzbg
                    if (r2 != 0) goto L_0x002f
                    java.lang.Class<com.google.android.gms.internal.clearcut.zzgw$zza$zzb> r3 = com.google.android.gms.internal.clearcut.zzgw.zza.zzb.class
                    monitor-enter(r3)
                    com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza$zzb> r2 = zzbg     // Catch:{ all -> 0x002c }
                    if (r2 != 0) goto L_0x002a
                    com.google.android.gms.internal.clearcut.zzcg$zzb r2 = new com.google.android.gms.internal.clearcut.zzcg$zzb     // Catch:{ all -> 0x002c }
                    com.google.android.gms.internal.clearcut.zzgw$zza$zzb r4 = zzbiv     // Catch:{ all -> 0x002c }
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
                    com.google.android.gms.internal.clearcut.zzgw$zza$zzb r2 = zzbiv
                    return r2
                L_0x0033:
                    r2 = 5
                    java.lang.Object[] r2 = new java.lang.Object[r2]
                    r3 = 0
                    java.lang.String r0 = "zzbb"
                    r2[r3] = r0
                    java.lang.String r3 = "zzya"
                    r2[r4] = r3
                    r3 = 2
                    java.lang.String r4 = "zzbis"
                    r2[r3] = r4
                    r3 = 3
                    java.lang.String r4 = "zzbit"
                    r2[r3] = r4
                    r3 = 4
                    java.lang.String r4 = "zzbiu"
                    r2[r3] = r4
                    java.lang.String r3 = "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0005\u0000\u0000\u0000\u0001\u0004\u0000\u0002\b\u0001\u0003\u0002\u0002\u0004\u0002\u0003"
                    com.google.android.gms.internal.clearcut.zzgw$zza$zzb r4 = zzbiv
                    java.lang.Object r2 = zza(r4, r3, r2)
                    return r2
                L_0x0057:
                    com.google.android.gms.internal.clearcut.zzgw$zza$zzb$zza r2 = new com.google.android.gms.internal.clearcut.zzgw$zza$zzb$zza
                    r2.<init>(r3)
                    return r2
                L_0x005d:
                    com.google.android.gms.internal.clearcut.zzgw$zza$zzb r2 = new com.google.android.gms.internal.clearcut.zzgw$zza$zzb
                    r2.<init>()
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzgw.zza.zzb.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
            }

            public final boolean zzfv() {
                return (this.zzbb & 1) == 1;
            }

            public final String zzfw() {
                return this.zzbis;
            }

            public final long zzfx() {
                return this.zzbit;
            }

            public final long zzfy() {
                return this.zzbiu;
            }
        }

        static {
            zzcg.zza(zza.class, zzbir);
        }

        private zza() {
        }

        public static zza zzft() {
            return zzbir;
        }

        public static zza zzi(byte[] bArr) throws zzco {
            return (zza) zzcg.zzb(zzbir, bArr);
        }

        /* JADX WARNING: type inference failed for: r2v11, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza>] */
        /* JADX WARNING: type inference failed for: r2v12, types: [java.lang.Object] */
        /* JADX WARNING: type inference failed for: r2v13, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
        /* JADX WARNING: type inference failed for: r2v16 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v13, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza>, com.google.android.gms.internal.clearcut.zzcg$zzb]
          assigns: [com.google.android.gms.internal.clearcut.zzcg$zzb]
          uses: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza>]
          mth insns count: 39
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
                int[] r3 = com.google.android.gms.internal.clearcut.zzgx.zzba
                r4 = 1
                int r2 = r2 - r4
                r2 = r3[r2]
                r3 = 0
                switch(r2) {
                    case 1: goto L_0x004e;
                    case 2: goto L_0x0048;
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
                com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza> r2 = zzbg
                if (r2 != 0) goto L_0x002f
                java.lang.Class<com.google.android.gms.internal.clearcut.zzgw$zza> r3 = com.google.android.gms.internal.clearcut.zzgw.zza.class
                monitor-enter(r3)
                com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza> r2 = zzbg     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x002a
                com.google.android.gms.internal.clearcut.zzcg$zzb r2 = new com.google.android.gms.internal.clearcut.zzcg$zzb     // Catch:{ all -> 0x002c }
                com.google.android.gms.internal.clearcut.zzgw$zza r4 = zzbir     // Catch:{ all -> 0x002c }
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
                com.google.android.gms.internal.clearcut.zzgw$zza r2 = zzbir
                return r2
            L_0x0033:
                r2 = 2
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.String r0 = "zzbiq"
                r2[r3] = r0
                java.lang.Class<com.google.android.gms.internal.clearcut.zzgw$zza$zzb> r3 = com.google.android.gms.internal.clearcut.zzgw.zza.zzb.class
                r2[r4] = r3
                java.lang.String r3 = "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0002\u0000\u0001\u0000\u0001\u001b"
                com.google.android.gms.internal.clearcut.zzgw$zza r4 = zzbir
                java.lang.Object r2 = zza(r4, r3, r2)
                return r2
            L_0x0048:
                com.google.android.gms.internal.clearcut.zzgw$zza$zza r2 = new com.google.android.gms.internal.clearcut.zzgw$zza$zza
                r2.<init>(r3)
                return r2
            L_0x004e:
                com.google.android.gms.internal.clearcut.zzgw$zza r2 = new com.google.android.gms.internal.clearcut.zzgw$zza
                r2.<init>()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzgw.zza.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        public final List<zzb> zzfs() {
            return this.zzbiq;
        }
    }
}
