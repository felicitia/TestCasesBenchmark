package com.google.android.gms.internal.firebase-perf;

public final class zzgk {

    public static final class zza extends zzcm<zza, C0006zza> implements zzdv {
        private static volatile zzed<zza> zzfm;
        /* access modifiers changed from: private */
        public static final zza zzto = new zza();
        private int zzfh;
        private int zztm = -1;
        private int zztn;

        /* renamed from: com.google.android.gms.internal.firebase-perf.zzgk$zza$zza reason: collision with other inner class name */
        public static final class C0006zza extends com.google.android.gms.internal.firebase-perf.zzcm.zza<zza, C0006zza> implements zzdv {
            private C0006zza() {
                super(zza.zzto);
            }

            /* synthetic */ C0006zza(zzgl zzgl) {
                this();
            }
        }

        public enum zzb implements zzcp {
            UNKNOWN_MOBILE_SUBTYPE(0),
            GPRS(1),
            EDGE(2),
            UMTS(3),
            CDMA(4),
            EVDO_0(5),
            EVDO_A(6),
            RTT(7),
            HSDPA(8),
            HSUPA(9),
            HSPA(10),
            IDEN(11),
            EVDO_B(12),
            LTE(13),
            EHRPD(14),
            HSPAP(15),
            GSM(16),
            TD_SCDMA(17),
            IWLAN(18),
            LTE_CA(19),
            COMBINED(100);
            
            private static final zzcq<zzb> zzuk = null;
            private final int value;

            public final int zzdi() {
                return this.value;
            }

            public static zzb zzbd(int i) {
                if (i == 100) {
                    return COMBINED;
                }
                switch (i) {
                    case 0:
                        return UNKNOWN_MOBILE_SUBTYPE;
                    case 1:
                        return GPRS;
                    case 2:
                        return EDGE;
                    case 3:
                        return UMTS;
                    case 4:
                        return CDMA;
                    case 5:
                        return EVDO_0;
                    case 6:
                        return EVDO_A;
                    case 7:
                        return RTT;
                    case 8:
                        return HSDPA;
                    case 9:
                        return HSUPA;
                    case 10:
                        return HSPA;
                    case 11:
                        return IDEN;
                    case 12:
                        return EVDO_B;
                    case 13:
                        return LTE;
                    case 14:
                        return EHRPD;
                    case 15:
                        return HSPAP;
                    case 16:
                        return GSM;
                    case 17:
                        return TD_SCDMA;
                    case 18:
                        return IWLAN;
                    case 19:
                        return LTE_CA;
                    default:
                        return null;
                }
            }

            public static zzcr zzgm() {
                return zzgn.zzum;
            }

            private zzb(int i) {
                this.value = i;
            }

            /* JADX WARNING: type inference failed for: r0v23, types: [com.google.android.gms.internal.firebase-perf.zzcq<com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb>, com.google.android.gms.internal.firebase-perf.zzgm] */
            /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v23, types: [com.google.android.gms.internal.firebase-perf.zzcq<com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb>, com.google.android.gms.internal.firebase-perf.zzgm]
              assigns: [com.google.android.gms.internal.firebase-perf.zzgm]
              uses: [com.google.android.gms.internal.firebase-perf.zzcq<com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb>]
              mth insns count: 110
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
            /* JADX WARNING: Unknown variable types count: 1 */
            static {
                /*
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "UNKNOWN_MOBILE_SUBTYPE"
                    r2 = 0
                    r0.<init>(r1, r2, r2)
                    UNKNOWN_MOBILE_SUBTYPE = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "GPRS"
                    r3 = 1
                    r0.<init>(r1, r3, r3)
                    GPRS = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "EDGE"
                    r4 = 2
                    r0.<init>(r1, r4, r4)
                    EDGE = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "UMTS"
                    r5 = 3
                    r0.<init>(r1, r5, r5)
                    UMTS = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "CDMA"
                    r6 = 4
                    r0.<init>(r1, r6, r6)
                    CDMA = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "EVDO_0"
                    r7 = 5
                    r0.<init>(r1, r7, r7)
                    EVDO_0 = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "EVDO_A"
                    r8 = 6
                    r0.<init>(r1, r8, r8)
                    EVDO_A = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "RTT"
                    r9 = 7
                    r0.<init>(r1, r9, r9)
                    RTT = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "HSDPA"
                    r10 = 8
                    r0.<init>(r1, r10, r10)
                    HSDPA = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "HSUPA"
                    r11 = 9
                    r0.<init>(r1, r11, r11)
                    HSUPA = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "HSPA"
                    r12 = 10
                    r0.<init>(r1, r12, r12)
                    HSPA = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "IDEN"
                    r13 = 11
                    r0.<init>(r1, r13, r13)
                    IDEN = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "EVDO_B"
                    r14 = 12
                    r0.<init>(r1, r14, r14)
                    EVDO_B = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "LTE"
                    r15 = 13
                    r0.<init>(r1, r15, r15)
                    LTE = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "EHRPD"
                    r15 = 14
                    r0.<init>(r1, r15, r15)
                    EHRPD = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "HSPAP"
                    r15 = 15
                    r14 = 15
                    r0.<init>(r1, r15, r14)
                    HSPAP = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "GSM"
                    r14 = 16
                    r15 = 16
                    r0.<init>(r1, r14, r15)
                    GSM = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "TD_SCDMA"
                    r14 = 17
                    r15 = 17
                    r0.<init>(r1, r14, r15)
                    TD_SCDMA = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "IWLAN"
                    r14 = 18
                    r15 = 18
                    r0.<init>(r1, r14, r15)
                    IWLAN = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "LTE_CA"
                    r14 = 19
                    r15 = 19
                    r0.<init>(r1, r14, r15)
                    LTE_CA = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb
                    java.lang.String r1 = "COMBINED"
                    r14 = 20
                    r15 = 100
                    r0.<init>(r1, r14, r15)
                    COMBINED = r0
                    r0 = 21
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb[] r0 = new com.google.android.gms.internal.firebase-perf.zzgk.zza.zzb[r0]
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = UNKNOWN_MOBILE_SUBTYPE
                    r0[r2] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = GPRS
                    r0[r3] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = EDGE
                    r0[r4] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = UMTS
                    r0[r5] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = CDMA
                    r0[r6] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = EVDO_0
                    r0[r7] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = EVDO_A
                    r0[r8] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = RTT
                    r0[r9] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = HSDPA
                    r0[r10] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = HSUPA
                    r0[r11] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = HSPA
                    r0[r12] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = IDEN
                    r0[r13] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = EVDO_B
                    r2 = 12
                    r0[r2] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = LTE
                    r2 = 13
                    r0[r2] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = EHRPD
                    r2 = 14
                    r0[r2] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = HSPAP
                    r2 = 15
                    r0[r2] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = GSM
                    r2 = 16
                    r0[r2] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = TD_SCDMA
                    r2 = 17
                    r0[r2] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = IWLAN
                    r2 = 18
                    r0[r2] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = LTE_CA
                    r2 = 19
                    r0[r2] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzb r1 = COMBINED
                    r2 = 20
                    r0[r2] = r1
                    zzul = r0
                    com.google.android.gms.internal.firebase-perf.zzgm r0 = new com.google.android.gms.internal.firebase-perf.zzgm
                    r0.<init>()
                    zzuk = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzgk.zza.zzb.<clinit>():void");
            }
        }

        public enum zzc implements zzcp {
            NONE(-1),
            MOBILE(0),
            WIFI(1),
            MOBILE_MMS(2),
            MOBILE_SUPL(3),
            MOBILE_DUN(4),
            MOBILE_HIPRI(5),
            WIMAX(6),
            BLUETOOTH(7),
            DUMMY(8),
            ETHERNET(9),
            MOBILE_FOTA(10),
            MOBILE_IMS(11),
            MOBILE_CBS(12),
            WIFI_P2P(13),
            MOBILE_IA(14),
            MOBILE_EMERGENCY(15),
            PROXY(16),
            VPN(17);
            
            private static final zzcq<zzc> zzuk = null;
            private final int value;

            public final int zzdi() {
                return this.value;
            }

            public static zzc zzbe(int i) {
                switch (i) {
                    case -1:
                        return NONE;
                    case 0:
                        return MOBILE;
                    case 1:
                        return WIFI;
                    case 2:
                        return MOBILE_MMS;
                    case 3:
                        return MOBILE_SUPL;
                    case 4:
                        return MOBILE_DUN;
                    case 5:
                        return MOBILE_HIPRI;
                    case 6:
                        return WIMAX;
                    case 7:
                        return BLUETOOTH;
                    case 8:
                        return DUMMY;
                    case 9:
                        return ETHERNET;
                    case 10:
                        return MOBILE_FOTA;
                    case 11:
                        return MOBILE_IMS;
                    case 12:
                        return MOBILE_CBS;
                    case 13:
                        return WIFI_P2P;
                    case 14:
                        return MOBILE_IA;
                    case 15:
                        return MOBILE_EMERGENCY;
                    case 16:
                        return PROXY;
                    case 17:
                        return VPN;
                    default:
                        return null;
                }
            }

            public static zzcr zzgm() {
                return zzgp.zzum;
            }

            private zzc(int i) {
                this.value = i;
            }

            /* JADX WARNING: type inference failed for: r0v21, types: [com.google.android.gms.internal.firebase-perf.zzgo, com.google.android.gms.internal.firebase-perf.zzcq<com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc>] */
            /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v21, types: [com.google.android.gms.internal.firebase-perf.zzgo, com.google.android.gms.internal.firebase-perf.zzcq<com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc>]
              assigns: [com.google.android.gms.internal.firebase-perf.zzgo]
              uses: [com.google.android.gms.internal.firebase-perf.zzcq<com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc>]
              mth insns count: 100
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
            /* JADX WARNING: Unknown variable types count: 1 */
            static {
                /*
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "NONE"
                    r2 = 0
                    r3 = -1
                    r0.<init>(r1, r2, r3)
                    NONE = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "MOBILE"
                    r3 = 1
                    r0.<init>(r1, r3, r2)
                    MOBILE = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "WIFI"
                    r4 = 2
                    r0.<init>(r1, r4, r3)
                    WIFI = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "MOBILE_MMS"
                    r5 = 3
                    r0.<init>(r1, r5, r4)
                    MOBILE_MMS = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "MOBILE_SUPL"
                    r6 = 4
                    r0.<init>(r1, r6, r5)
                    MOBILE_SUPL = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "MOBILE_DUN"
                    r7 = 5
                    r0.<init>(r1, r7, r6)
                    MOBILE_DUN = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "MOBILE_HIPRI"
                    r8 = 6
                    r0.<init>(r1, r8, r7)
                    MOBILE_HIPRI = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "WIMAX"
                    r9 = 7
                    r0.<init>(r1, r9, r8)
                    WIMAX = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "BLUETOOTH"
                    r10 = 8
                    r0.<init>(r1, r10, r9)
                    BLUETOOTH = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "DUMMY"
                    r11 = 9
                    r0.<init>(r1, r11, r10)
                    DUMMY = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "ETHERNET"
                    r12 = 10
                    r0.<init>(r1, r12, r11)
                    ETHERNET = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "MOBILE_FOTA"
                    r13 = 11
                    r0.<init>(r1, r13, r12)
                    MOBILE_FOTA = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "MOBILE_IMS"
                    r14 = 12
                    r0.<init>(r1, r14, r13)
                    MOBILE_IMS = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "MOBILE_CBS"
                    r15 = 13
                    r0.<init>(r1, r15, r14)
                    MOBILE_CBS = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "WIFI_P2P"
                    r14 = 14
                    r0.<init>(r1, r14, r15)
                    WIFI_P2P = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "MOBILE_IA"
                    r15 = 15
                    r0.<init>(r1, r15, r14)
                    MOBILE_IA = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "MOBILE_EMERGENCY"
                    r15 = 16
                    r14 = 15
                    r0.<init>(r1, r15, r14)
                    MOBILE_EMERGENCY = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "PROXY"
                    r14 = 17
                    r0.<init>(r1, r14, r15)
                    PROXY = r0
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc
                    java.lang.String r1 = "VPN"
                    r14 = 18
                    r15 = 17
                    r0.<init>(r1, r14, r15)
                    VPN = r0
                    r0 = 19
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc[] r0 = new com.google.android.gms.internal.firebase-perf.zzgk.zza.zzc[r0]
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = NONE
                    r0[r2] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = MOBILE
                    r0[r3] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = WIFI
                    r0[r4] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = MOBILE_MMS
                    r0[r5] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = MOBILE_SUPL
                    r0[r6] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = MOBILE_DUN
                    r0[r7] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = MOBILE_HIPRI
                    r0[r8] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = WIMAX
                    r0[r9] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = BLUETOOTH
                    r0[r10] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = DUMMY
                    r0[r11] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = ETHERNET
                    r0[r12] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = MOBILE_FOTA
                    r0[r13] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = MOBILE_IMS
                    r2 = 12
                    r0[r2] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = MOBILE_CBS
                    r2 = 13
                    r0[r2] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = WIFI_P2P
                    r2 = 14
                    r0[r2] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = MOBILE_IA
                    r2 = 15
                    r0[r2] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = MOBILE_EMERGENCY
                    r2 = 16
                    r0[r2] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = PROXY
                    r2 = 17
                    r0[r2] = r1
                    com.google.android.gms.internal.firebase-perf.zzgk$zza$zzc r1 = VPN
                    r2 = 18
                    r0[r2] = r1
                    zzvg = r0
                    com.google.android.gms.internal.firebase-perf.zzgo r0 = new com.google.android.gms.internal.firebase-perf.zzgo
                    r0.<init>()
                    zzuk = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzgk.zza.zzc.<clinit>():void");
            }
        }

        private zza() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzgl.zzfn[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0006zza(null);
                case 3:
                    Object[] objArr = {"zzfh", "zztm", zzc.zzgm(), "zztn", zzb.zzgm()};
                    return zza((zzdt) zzto, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001", objArr);
                case 4:
                    return zzto;
                case 5:
                    zzed<zza> zzed = zzfm;
                    if (zzed == null) {
                        synchronized (zza.class) {
                            zzed = zzfm;
                            if (zzed == null) {
                                zzed = new com.google.android.gms.internal.firebase-perf.zzcm.zzb<>(zzto);
                                zzfm = zzed;
                            }
                        }
                    }
                    return zzed;
                case 6:
                    return Byte.valueOf(1);
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzcm.zza(zza.class, zzto);
        }
    }
}
