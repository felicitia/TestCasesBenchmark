package com.threatmetrix.TrustDefender.internal;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

@SuppressLint({"HardwareIds"})
public class N {
    /* access modifiers changed from: private */

    /* renamed from: new reason: not valid java name */
    public static final String f353new = TL.m331if(N.class);

    static class A {
        /* access modifiers changed from: private */

        /* renamed from: case reason: not valid java name */
        public static final boolean f354case;
        /* access modifiers changed from: private */

        /* renamed from: char reason: not valid java name */
        public static final boolean f355char;
        /* access modifiers changed from: private */

        /* renamed from: do reason: not valid java name */
        public static final boolean f356do;
        /* access modifiers changed from: private */

        /* renamed from: else reason: not valid java name */
        public static final boolean f357else;
        /* access modifiers changed from: private */

        /* renamed from: for reason: not valid java name */
        public static final boolean f358for;
        /* access modifiers changed from: private */

        /* renamed from: if reason: not valid java name */
        public static final boolean f359if;
        /* access modifiers changed from: private */

        /* renamed from: int reason: not valid java name */
        public static final boolean f360int;
        /* access modifiers changed from: private */

        /* renamed from: new reason: not valid java name */
        public static final boolean f361new;
        /* access modifiers changed from: private */

        /* renamed from: try reason: not valid java name */
        public static final boolean f362try;

        static {
            Class cls = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.SHARED_PREFERENCES);
            boolean z = true;
            f358for = cls != null;
            Class cls2 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.SHARED_PREFERENCES_EDITOR);
            f359if = cls2 != null;
            f356do = D2.m44for(cls, "getInt", String.class, Integer.TYPE) != null;
            f360int = D2.m44for(cls, "getLong", String.class, Long.TYPE) != null;
            f361new = D2.m44for(cls, "getString", String.class, String.class) != null;
            f357else = D2.m44for(cls2, "putInt", String.class, Integer.TYPE) != null;
            f354case = D2.m44for(cls2, "putLong", String.class, Long.TYPE) != null;
            f362try = D2.m44for(cls2, "putString", String.class, String.class) != null;
            if (D2.m44for(cls2, "apply", new Class[0]) == null) {
                z = false;
            }
            f355char = z;
        }
    }

    class B {

        /* renamed from: do reason: not valid java name */
        PackageInfo f363do = null;

        B(Context context, String str, int i) {
            if (W.f427byte && W.f431for) {
                try {
                    this.f363do = context.getPackageManager().getPackageInfo(str, i);
                } catch (NameNotFoundException unused) {
                    N.f353new;
                } catch (SecurityException unused2) {
                    N.f353new;
                } catch (Exception e) {
                    TL.m338new(N.f353new, e.toString());
                }
            }
        }
    }

    static class D {

        /* renamed from: for reason: not valid java name */
        private static final boolean f365for;

        /* renamed from: int reason: not valid java name */
        private static final boolean f366int;

        D() {
        }

        static {
            Class cls = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.POWER_MANAGER);
            boolean z = false;
            f365for = D2.m44for(cls, "isInteractive", new Class[0]) != null;
            if (D2.m44for(cls, "isScreenOn", new Class[0]) != null) {
                z = true;
            }
            f366int = z;
        }

        /* renamed from: if reason: not valid java name */
        static boolean m155if() {
            return f366int;
        }

        /* renamed from: new reason: not valid java name */
        static boolean m156new() {
            return f365for;
        }
    }

    static class E {

        /* renamed from: for reason: not valid java name */
        private static final boolean f367for;

        /* renamed from: if reason: not valid java name */
        private static final boolean f368if;

        /* renamed from: new reason: not valid java name */
        private static final boolean f369new;

        E() {
        }

        static {
            Class cls = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.CERTIFICATE);
            Class cls2 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.KEY_PAIR);
            Class cls3 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.KEY);
            Class cls4 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.KEY_STORE);
            Class cls5 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.LOAD_STORE_PARAM);
            Class cls6 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.PROTECTION_PARAM);
            Class cls7 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.KEY_ENTRY);
            Class cls8 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.PRIVATE_KEY_ENTRY);
            Class cls9 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.PRIVATE_KEY);
            Class cls10 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.KEY_PAIR_GENERATOR);
            Class cls11 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.ALG_PARAMETER_SPEC);
            Class cls12 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.KEY_CHAIN);
            Class cls13 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.SIGNATURE);
            Class cls14 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.KEY_PAIR_GEN_SPEC);
            Class cls15 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.KEY_PAIR_GEN_SPEC_BUILDER);
            Class cls16 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.X_500_PRINCIPAL);
            Class cls17 = cls14;
            Class cls18 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.KEY_GEN_PARAM_SPEC);
            Class cls19 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.KEY_GEN_PARAM_SPEC_BUILDER);
            Class cls20 = cls7;
            Class cls21 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.KEY_FACTORY);
            Class cls22 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.KEY_INFO);
            Class cls23 = cls19;
            Class cls24 = cls16;
            boolean z = D2.m44for(cls4, "getInstance", String.class) != null;
            Class cls25 = cls15;
            boolean z2 = D2.m44for(cls4, "load", cls5) != null;
            boolean z3 = D2.m44for(cls4, "getEntry", String.class, cls6) != null;
            boolean z4 = D2.m44for(cls4, "getCertificate", String.class) != null;
            boolean z5 = D2.m44for(cls4, "getCreationDate", String.class) != null;
            boolean z6 = D2.m44for(cls8, "getPrivateKey", new Class[0]) != null;
            boolean z7 = D2.m44for(cls3, "getAlgorithm", new Class[0]) != null;
            boolean z8 = D2.m44for(cls2, "getPrivate", new Class[0]) != null;
            boolean z9 = D2.m44for(cls2, "getPublic", new Class[0]) != null;
            boolean z10 = D2.m44for(cls10, "generateKeyPair", new Class[0]) != null;
            boolean z11 = D2.m44for(cls, "getPublicKey", new Class[0]) != null;
            boolean z12 = D2.m44for(cls10, "getInstance", String.class, String.class) != null;
            boolean z13 = D2.m44for(cls10, "initialize", cls11) != null;
            boolean z14 = D2.m44for(cls13, "getInstance", String.class) != null;
            boolean z15 = D2.m44for(cls13, "initSign", cls9) != null;
            boolean z16 = D2.m44for(cls13, "update", byte[].class) != null;
            boolean z17 = D2.m44for(cls13, "sign", new Class[0]) != null;
            boolean z18 = D2.m44for(cls12, "isKeyAlgorithmSupported", String.class) != null;
            boolean z19 = z16;
            Class cls26 = cls25;
            boolean z20 = D2.m44for(cls26, "setAlias", String.class) != null;
            boolean z21 = D2.m44for(cls26, "setSubject", cls24) != null;
            boolean z22 = D2.m44for(cls26, "setSerialNumber", BigInteger.class) != null;
            boolean z23 = D2.m44for(cls26, "setStartDate", Date.class) != null;
            boolean z24 = D2.m44for(cls26, "setEndDate", Date.class) != null;
            boolean z25 = D2.m44for(cls26, "setKeyType", String.class) != null;
            Class cls27 = cls23;
            boolean z26 = D2.m44for(cls27, "setDigests", String[].class) != null;
            boolean z27 = D2.m44for(cls12, "isBoundKeyAlgorithm", String.class) != null;
            boolean z28 = D2.m44for(cls27, "setSignaturePaddings", String[].class) != null;
            boolean z29 = true;
            Class cls28 = cls21;
            boolean z30 = D2.m44for(cls28, "getInstance", String.class, String.class) != null;
            boolean z31 = D2.m44for(cls28, "getKeySpec", cls3, Class.class) != null;
            boolean z32 = D2.m44for(cls22, "isInsideSecureHardware", new Class[0]) != null;
            boolean z33 = cls20 != null && cls8 != null && cls9 != null && z && z2 && z3 && z6 && z4 && z5 && z7 && z8 && z9 && z11 && z10 && z12 && z13 && z18;
            f368if = z14 && z15 && z19 && z17;
            f367for = C0012I.f388for >= 18 && z33 && cls17 != null && z20 && z21 && z22 && z23 && z24 && z25 && z27;
            if (C0012I.f388for < 23 || !z33 || cls18 == null || !z26 || !z28 || !z30 || !z31 || !z32) {
                z29 = false;
            }
            f369new = z29;
        }

        /* renamed from: do reason: not valid java name */
        static boolean m157do() {
            return f367for || f369new;
        }

        /* renamed from: new reason: not valid java name */
        static boolean m160new() {
            return f367for;
        }

        /* renamed from: int reason: not valid java name */
        static boolean m159int() {
            return f369new;
        }

        /* renamed from: for reason: not valid java name */
        static boolean m158for() {
            return f368if;
        }
    }

    static class I {

        /* renamed from: break reason: not valid java name */
        static final String f370break = (D2.m41do(f373catch, "MANUFACTURER") != null ? Build.MANUFACTURER : null);

        /* renamed from: byte reason: not valid java name */
        static final String f371byte = (D2.m41do(f373catch, "SERIAL") != null ? Build.SERIAL : null);

        /* renamed from: case reason: not valid java name */
        static final String f372case = (D2.m41do(f373catch, "USER") != null ? Build.USER : null);

        /* renamed from: catch reason: not valid java name */
        private static final Class<?> f373catch;

        /* renamed from: char reason: not valid java name */
        static final String f374char = (D2.m41do(f373catch, "MODEL") != null ? Build.MODEL : null);

        /* renamed from: const reason: not valid java name */
        static final boolean f375const;

        /* renamed from: do reason: not valid java name */
        static final String f376do = (D2.m41do(f373catch, "TYPE") != null ? Build.TYPE : null);

        /* renamed from: else reason: not valid java name */
        static final String f377else = (D2.m41do(f373catch, "DEVICE") != null ? Build.DEVICE : null);

        /* renamed from: for reason: not valid java name */
        static final long f378for;

        /* renamed from: goto reason: not valid java name */
        static final String f379goto = (D2.m41do(f373catch, "DISPLAY") != null ? Build.DISPLAY : null);

        /* renamed from: if reason: not valid java name */
        static final String f380if = (D2.m41do(f373catch, "TAGS") != null ? Build.TAGS : null);

        /* renamed from: int reason: not valid java name */
        static final String f381int = (D2.m41do(f373catch, "HOST") != null ? Build.HOST : null);

        /* renamed from: long reason: not valid java name */
        static final String f382long = (D2.m41do(f373catch, "PRODUCT") != null ? Build.PRODUCT : null);

        /* renamed from: new reason: not valid java name */
        static final String f383new = (D2.m41do(f373catch, "BRAND") != null ? Build.BRAND : null);

        /* renamed from: this reason: not valid java name */
        static final Method f384this = D2.m44for(f373catch, "getSerial", new Class[0]);

        /* renamed from: try reason: not valid java name */
        static final String f385try = (D2.m41do(f373catch, "ID") != null ? Build.ID : null);

        /* renamed from: void reason: not valid java name */
        static final String f386void;

        /* renamed from: com.threatmetrix.TrustDefender.internal.N$I$I reason: collision with other inner class name */
        static class C0012I {

            /* renamed from: do reason: not valid java name */
            static final String f387do;

            /* renamed from: for reason: not valid java name */
            static final int f388for;

            /* renamed from: int reason: not valid java name */
            static final String f389int;

            C0012I() {
            }

            static {
                Class cls = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.VERSION);
                String str = null;
                f389int = D2.m41do(cls, "RELEASE") != null ? VERSION.RELEASE : null;
                f388for = D2.m41do(cls, "SDK_INT") != null ? VERSION.SDK_INT : -1;
                if (D2.m41do(cls, "CODENAME") != null) {
                    str = VERSION.CODENAME;
                }
                f387do = str;
            }
        }

        static class W {

            /* renamed from: break reason: not valid java name */
            static final int f390break = 22;

            /* renamed from: byte reason: not valid java name */
            static final int f391byte = 13;

            /* renamed from: case reason: not valid java name */
            static final int f392case = 17;

            /* renamed from: catch reason: not valid java name */
            static final int f393catch = 25;

            /* renamed from: char reason: not valid java name */
            static final int f394char = 16;

            /* renamed from: class reason: not valid java name */
            static final int f395class = 23;

            /* renamed from: const reason: not valid java name */
            static final int f396const = 24;

            /* renamed from: do reason: not valid java name */
            static final int f397do = 12;

            /* renamed from: else reason: not valid java name */
            static final int f398else = 14;

            /* renamed from: float reason: not valid java name */
            private static final Class<?> f399float;

            /* renamed from: for reason: not valid java name */
            static final int f400for = 9;

            /* renamed from: goto reason: not valid java name */
            static final int f401goto = 21;

            /* renamed from: if reason: not valid java name */
            static final int f402if = 11;

            /* renamed from: int reason: not valid java name */
            static final int f403int = 10;

            /* renamed from: long reason: not valid java name */
            static final int f404long = 18;

            /* renamed from: new reason: not valid java name */
            static final int f405new = 8;

            /* renamed from: this reason: not valid java name */
            static final int f406this = 20;

            /* renamed from: try reason: not valid java name */
            static final int f407try = 15;

            /* renamed from: void reason: not valid java name */
            static final int f408void = 19;

            W() {
            }

            static {
                Class<?> cls = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.VERSION_CODES);
                f399float = cls;
                D2.m41do(cls, "FROYO");
                D2.m41do(f399float, "GINGERBREAD");
                D2.m41do(f399float, "GINGERBREAD_MR1");
                D2.m41do(f399float, "HONEYCOMB");
                D2.m41do(f399float, "HONEYCOMB_MR1");
                D2.m41do(f399float, "HONEYCOMB_MR2");
                D2.m41do(f399float, "ICE_CREAM_SANDWICH");
                D2.m41do(f399float, "ICE_CREAM_SANDWICH_MR1");
                D2.m41do(f399float, "JELLY_BEAN");
                D2.m41do(f399float, "JELLY_BEAN_MR1");
                D2.m41do(f399float, "JELLY_BEAN_MR2");
                D2.m41do(f399float, "KITKAT");
                D2.m41do(f399float, "KITKAT_WATCH");
                D2.m41do(f399float, "LOLLIPOP");
                D2.m41do(f399float, "LOLLIPOP_MR1");
                D2.m41do(f399float, "M");
                D2.m41do(f399float, "N");
                D2.m41do(f399float, "N_MR1");
            }
        }

        I() {
        }

        static {
            Class<?> cls = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.BUILD);
            f373catch = cls;
            f378for = D2.m41do(cls, "TIME") != null ? Build.TIME : Long.MAX_VALUE;
            String str = null;
            if (D2.m41do(f373catch, "BOARD") != null) {
                str = Build.BOARD;
            }
            f386void = str;
            boolean z = false;
            if (C0012I.f388for >= W.f394char && C0012I.f388for <= W.f408void) {
                z = true;
            }
            f375const = z;
        }

        /* renamed from: for reason: not valid java name */
        static String m161for() {
            if (f384this != null) {
                Object obj = D2.m39do((Object) null, f384this, new Object[0]);
                if (obj != null) {
                    return (String) obj;
                }
            }
            return null;
        }
    }

    public static class K {

        /* renamed from: do reason: not valid java name */
        private static final boolean f409do;

        /* renamed from: if reason: not valid java name */
        private static final boolean f410if;

        K() {
        }

        static {
            boolean z;
            boolean z2;
            Class cls = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.CRITERIA);
            Class cls2 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.LOCATION);
            Class cls3 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.LOCATION_PROVIDER);
            Class cls4 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.LOCATION_LISTENER);
            boolean z3 = D2.m44for(cls, "setAccuracy", Integer.TYPE) != null;
            boolean z4 = D2.m44for(cls, "setAltitudeRequired", Boolean.TYPE) != null;
            boolean z5 = D2.m44for(cls, "setBearingAccuracy", Integer.TYPE) != null;
            boolean z6 = D2.m44for(cls, "setCostAllowed", Boolean.TYPE) != null;
            boolean z7 = D2.m44for(cls, "setSpeedAccuracy", Integer.TYPE) != null;
            boolean z8 = D2.m44for(cls, "setSpeedRequired", Boolean.TYPE) != null;
            boolean z9 = D2.m44for(cls, "setVerticalAccuracy", Integer.TYPE) != null;
            boolean z10 = D2.m44for(cls, "setPowerRequirement", Integer.TYPE) != null;
            boolean z11 = D2.m44for(cls2, "getTime", new Class[0]) != null;
            boolean z12 = D2.m44for(cls2, "getProvider", new Class[0]) != null;
            boolean z13 = D2.m44for(cls2, "getAccuracy", new Class[0]) != null;
            boolean z14 = D2.m44for(cls2, "getLatitude", new Class[0]) != null;
            boolean z15 = D2.m44for(cls2, "getLongitude", new Class[0]) != null;
            boolean z16 = D2.m41do(cls, "NO_REQUIREMENT") != null;
            boolean z17 = D2.m41do(cls, "POWER_LOW") != null;
            if (D2.m41do(cls, "ACCURACY_LOW") != null) {
                z = z15;
                z2 = true;
            } else {
                z = z15;
                z2 = false;
            }
            boolean z18 = D2.m41do(cls, "ACCURACY_COARSE") != null;
            boolean z19 = D2.m41do(cls3, "AVAILABLE") != null;
            boolean z20 = D2.m41do(cls3, "TEMPORARILY_UNAVAILABLE") != null;
            boolean z21 = D2.m41do(cls3, "OUT_OF_SERVICE") != null;
            f409do = z3 && z4 && z5 && z6 && z7 && z8 && z9 && z10 && z16 && z17 && z2 && z18;
            f410if = cls4 != null && z11 && z12 && z14 && z && z13 && z19 && z20 && z21;
        }

        /* renamed from: for reason: not valid java name */
        static boolean m163for() {
            return f409do;
        }

        /* renamed from: do reason: not valid java name */
        public static boolean m162do() {
            return f410if;
        }
    }

    static class L {

        /* renamed from: int reason: not valid java name */
        ApplicationInfo f411int = null;

        L(com.threatmetrix.TrustDefender.internal.P.O o) {
            if (W.f432if && W.f434new) {
                this.f411int = o.f487for.getApplicationInfo();
            }
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.internal.N$N reason: collision with other inner class name */
    static class C0013N {

        /* renamed from: do reason: not valid java name */
        private static final boolean f412do;

        /* renamed from: for reason: not valid java name */
        private static final boolean f413for;

        /* renamed from: if reason: not valid java name */
        private static final boolean f414if;

        C0013N() {
        }

        static {
            Class cls = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.SECRET_KEY_SPEC);
            Class cls2 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.CIPHER);
            Class cls3 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.KEY);
            Class cls4 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.PRIVATE_KEY);
            Class cls5 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.PUBLIC_KEY);
            Class cls6 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.KEY_FACTORY);
            Class cls7 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.KEY_SPEC);
            Class cls8 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.PKCS8_ENC_KEY_SPEC);
            Class cls9 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.X509_ENC_KEY_SPEC);
            Class cls10 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.EC_GEN_KEY_SPEC);
            Class cls11 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.RSA_GEN_KEY_SPEC);
            Class cls12 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.KEY_PAIR);
            Class cls13 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.KEY_PAIR_GENERATOR);
            Class cls14 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.ALG_PARAMETER_SPEC);
            Class cls15 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.SIGNATURE);
            Class cls16 = cls5;
            Class cls17 = cls10;
            boolean z = D2.m44for(cls2, "getInstance", String.class, String.class) != null;
            Class cls18 = cls9;
            boolean z2 = D2.m44for(cls2, "init", Integer.TYPE, cls3) != null;
            boolean z3 = D2.m44for(cls2, "doFinal", byte[].class) != null;
            boolean z4 = D2.m44for(cls6, "getInstance", String.class) != null;
            boolean z5 = D2.m44for(cls6, "generatePrivate", cls7) != null;
            boolean z6 = D2.m44for(cls6, "generatePublic", cls7) != null;
            boolean z7 = D2.m44for(cls12, "getPrivate", new Class[0]) != null;
            boolean z8 = D2.m44for(cls12, "getPublic", new Class[0]) != null;
            boolean z9 = D2.m44for(cls13, "getInstance", String.class, String.class) != null;
            boolean z10 = D2.m44for(cls13, "initialize", cls14) != null;
            boolean z11 = D2.m44for(cls13, "generateKeyPair", new Class[0]) != null;
            boolean z12 = z8;
            boolean z13 = D2.m44for(cls15, "getInstance", String.class) != null;
            boolean z14 = z7;
            boolean z15 = D2.m44for(cls15, "initSign", cls4) != null;
            boolean z16 = z11;
            boolean z17 = D2.m44for(cls15, "update", byte[].class) != null;
            boolean z18 = D2.m44for(cls15, "sign", new Class[0]) != null;
            boolean z19 = D2.m41do(cls11, "F0") != null;
            f412do = z13 && z15 && z17 && z18;
            f413for = cls != null && cls8 != null && cls18 != null && z && z2 && z3 && z4 && z5 && z6;
            f414if = (cls17 != null || (cls11 != null && z19)) && cls4 != null && cls16 != null && z9 && z10 && z16 && z14 && z12 && z2 && z3;
        }

        /* renamed from: for reason: not valid java name */
        public static boolean m164for() {
            return f413for;
        }

        /* renamed from: if reason: not valid java name */
        public static boolean m165if() {
            return f414if;
        }

        /* renamed from: int reason: not valid java name */
        public static boolean m166int() {
            return f412do;
        }
    }

    static class O {

        /* renamed from: if reason: not valid java name */
        static final boolean f415if;

        O() {
        }

        static {
            boolean z = false;
            if (D2.m44for(D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.DEVICE_POLICY_MANAGER), "getStorageEncryptionStatus", new Class[0]) != null) {
                z = true;
            }
            f415if = z;
        }
    }

    public static class Q {

        /* renamed from: new reason: not valid java name */
        private static final boolean f416new;

        Q() {
        }

        static {
            Class cls = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.WEB_VIEW);
            Class cls2 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.WEB_VIEW_CLIENT);
            Class cls3 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.WEB_SETTINGS);
            Class cls4 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.WEB_SETTINGS_PLUGIN);
            Class cls5 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.WEB_CHROME_CLIENT);
            boolean z = true;
            boolean z2 = D2.m44for(D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.JS_RESULT), "confirm", new Class[0]) != null;
            boolean z3 = D2.m44for(cls, "destroy", new Class[0]) != null;
            boolean z4 = D2.m44for(cls, "loadUrl", String.class) != null;
            boolean z5 = D2.m44for(cls, "loadData", String.class, String.class, String.class) != null;
            boolean z6 = D2.m44for(cls, "getSettings", new Class[0]) != null;
            boolean z7 = D2.m44for(cls, "setWebViewClient", cls2) != null;
            boolean z8 = D2.m44for(cls, "setWebChromeClient", cls5) != null;
            boolean z9 = D2.m44for(cls3, "getUserAgentString", new Class[0]) != null;
            boolean z10 = D2.m44for(cls3, "setJavaScriptEnabled", Boolean.TYPE) != null;
            boolean z11 = D2.m41do(cls4, "ON") != null;
            if (cls2 == null || cls5 == null || !z2 || !z3 || !z4 || !z5 || !z6 || !z7 || !z8 || !z9 || !z10 || !z11) {
                z = false;
            }
            f416new = z;
        }

        /* renamed from: for reason: not valid java name */
        public static boolean m167for() {
            return f416new;
        }
    }

    class R {

        /* renamed from: do reason: not valid java name */
        private PackageManager f417do = null;

        R(Context context) {
            if (W.f431for) {
                try {
                    this.f417do = context.getPackageManager();
                } catch (SecurityException unused) {
                    N.f353new;
                } catch (Exception e) {
                    TL.m338new(N.f353new, e.toString());
                }
            }
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: if reason: not valid java name */
        public final boolean m169if(String str, String str2) {
            if (W.f435try && this.f417do != null) {
                try {
                    if (this.f417do.checkPermission(str, str2) == 0) {
                        return true;
                    }
                    return false;
                } catch (SecurityException unused) {
                    N.f353new;
                } catch (Exception e) {
                    TL.m338new(N.f353new, e.toString());
                }
            }
            return false;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: int reason: not valid java name */
        public final boolean m170int(String str, int i) {
            if (W.f431for && W.f427byte && this.f417do != null) {
                try {
                    this.f417do.getPackageInfo(str, i);
                    return true;
                } catch (NameNotFoundException unused) {
                    N.f353new;
                } catch (SecurityException unused2) {
                    N.f353new;
                } catch (Exception e) {
                    TL.m338new(N.f353new, e.toString());
                }
            }
            return false;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: if reason: not valid java name */
        public final ArrayList<String> m168if() {
            ArrayList<String> arrayList = new ArrayList<>();
            if (W.f431for && W.f432if && this.f417do != null) {
                try {
                    for (ApplicationInfo applicationInfo : this.f417do.getInstalledApplications(0)) {
                        if (!applicationInfo.sourceDir.startsWith("/system/app") && !applicationInfo.sourceDir.startsWith("/system/priv-app")) {
                            arrayList.add(applicationInfo.sourceDir);
                        }
                    }
                } catch (SecurityException unused) {
                    N.f353new;
                } catch (Exception e) {
                    TL.m338new(N.f353new, e.toString());
                }
            }
            arrayList.add("/system/app");
            arrayList.add("/system/priv-app");
            return arrayList;
        }
    }

    static class S {

        /* renamed from: if reason: not valid java name */
        private static final boolean f419if;

        /* renamed from: new reason: not valid java name */
        private static final boolean f420new;

        S() {
        }

        static {
            Class cls = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.SYSTEM_CLOCK);
            boolean z = false;
            f419if = D2.m44for(cls, "uptimeMillis", new Class[0]) != null;
            if (D2.m44for(cls, "elapsedRealtime", new Class[0]) != null) {
                z = true;
            }
            f420new = z;
        }

        /* renamed from: if reason: not valid java name */
        static long m172if() {
            if (f419if) {
                return SystemClock.uptimeMillis();
            }
            return 0;
        }

        /* renamed from: for reason: not valid java name */
        static long m171for() {
            if (f420new) {
                return SystemClock.elapsedRealtime();
            }
            return 0;
        }
    }

    static class T {

        /* renamed from: byte reason: not valid java name */
        private static final boolean f421byte;

        /* renamed from: do reason: not valid java name */
        private static final boolean f422do;

        /* renamed from: for reason: not valid java name */
        private static final boolean f423for;

        /* renamed from: if reason: not valid java name */
        private static final boolean f424if;

        /* renamed from: int reason: not valid java name */
        private static final boolean f425int;

        /* renamed from: new reason: not valid java name */
        private static final boolean f426new;

        T() {
        }

        static {
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4;
            Class cls = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.CONNECTIVITY_MANAGER);
            Class cls2 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.NETWORK_INFO);
            Class cls3 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.WIFI_INFO);
            Class cls4 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.WIFI_MANAGER);
            Class cls5 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.STATE);
            boolean z5 = D2.m44for(cls, "getActiveNetworkInfo", new Class[0]) != null;
            boolean z6 = D2.m44for(cls2, "getState", new Class[0]) != null;
            boolean z7 = D2.m44for(cls2, "getType", new Class[0]) != null;
            boolean z8 = D2.m44for(cls2, "getExtraInfo", new Class[0]) != null;
            boolean z9 = D2.m44for(cls3, "getBSSID", new Class[0]) != null;
            boolean z10 = D2.m44for(cls3, "getSSID", new Class[0]) != null;
            boolean z11 = D2.m44for(cls3, "getRssi", new Class[0]) != null;
            boolean z12 = D2.m44for(cls4, "getConnectionInfo", new Class[0]) != null;
            boolean z13 = D2.m44for(cls2, "isConnectedOrConnecting", new Class[0]) != null;
            boolean z14 = D2.m41do(cls, "CONNECTIVITY_ACTION") != null;
            boolean z15 = D2.m41do(cls, "TYPE_MOBILE") != null;
            boolean z16 = D2.m41do(cls, "TYPE_WIFI") != null;
            if (D2.m41do(cls, "TYPE_BLUETOOTH") != null) {
                z = z12;
                z2 = true;
            } else {
                z = z12;
                z2 = false;
            }
            boolean z17 = D2.m41do(cls, "TYPE_ETHERNET") != null;
            if (D2.m41do(cls4, "NETWORK_STATE_CHANGED_ACTION") != null) {
                z3 = z11;
                z4 = true;
            } else {
                z3 = z11;
                z4 = false;
            }
            boolean z18 = D2.m41do(cls5, "CONNECTED") != null;
            f422do = z5 && z13;
            boolean z19 = D2.m44for(cls4, "getScanResults", new Class[0]) != null;
            f425int = z19;
            f421byte = z19 && D2.m44for(cls4, "startScan", new Class[0]) != null;
            f423for = z14 && z18 && z6 && z8 && z7 && z15 && z16 && (C0012I.f388for < W.f391byte || (z17 && z2));
            f424if = z4 && z18 && z9 && z10 && z3 && z6 && z8;
            f426new = z && z9 && z10 && z3;
        }

        /* renamed from: new reason: not valid java name */
        static boolean m178new() {
            return f422do;
        }

        /* renamed from: int reason: not valid java name */
        static boolean m177int() {
            return f423for;
        }

        /* renamed from: for reason: not valid java name */
        static boolean m175for() {
            return f424if;
        }

        /* renamed from: do reason: not valid java name */
        static boolean m173do() {
            return f426new;
        }

        /* renamed from: if reason: not valid java name */
        static boolean m176if() {
            return f425int;
        }

        /* renamed from: else reason: not valid java name */
        static boolean m174else() {
            return f421byte;
        }
    }

    static class W {
        /* access modifiers changed from: private */

        /* renamed from: byte reason: not valid java name */
        public static final boolean f427byte;
        /* access modifiers changed from: private */

        /* renamed from: case reason: not valid java name */
        public static final boolean f428case;
        /* access modifiers changed from: private */

        /* renamed from: char reason: not valid java name */
        public static final boolean f429char;

        /* renamed from: do reason: not valid java name */
        static final int f430do = 1;
        /* access modifiers changed from: private */

        /* renamed from: for reason: not valid java name */
        public static final boolean f431for;
        /* access modifiers changed from: private */

        /* renamed from: if reason: not valid java name */
        public static final boolean f432if = (D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.APPLICATION_INFO) != null);

        /* renamed from: int reason: not valid java name */
        static final int f433int = 128;
        /* access modifiers changed from: private */

        /* renamed from: new reason: not valid java name */
        public static final boolean f434new;
        /* access modifiers changed from: private */

        /* renamed from: try reason: not valid java name */
        public static final boolean f435try;

        W() {
        }

        static {
            Class cls = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.PACKAGE_MANAGER);
            boolean z = false;
            f431for = cls != null;
            Class cls2 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.PACKAGE_INFO);
            f427byte = cls2 != null;
            f435try = D2.m44for(cls, "checkPermission", String.class, String.class) != null;
            f429char = D2.m41do(cls2, "versionCode") != null;
            f428case = D2.m41do(cls2, "versionName") != null;
            if (D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.PACKAGE_ITEM_INFO) != null) {
                z = true;
            }
            f434new = z;
        }
    }

    static class Y {

        /* renamed from: byte reason: not valid java name */
        private static final Class<?> f436byte = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.SUBSCRIPTION_MANAGER);

        /* renamed from: case reason: not valid java name */
        private static final Class<?> f437case = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GSM_CELL_LOCATION);

        /* renamed from: char reason: not valid java name */
        private static final Class<?> f438char = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.CDMA_CELL_LOCATION);

        /* renamed from: do reason: not valid java name */
        private static final Class<?> f439do = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.CELL_SIGNAL_STRENGTH);

        /* renamed from: else reason: not valid java name */
        private static final boolean f440else = m196new(com.threatmetrix.TrustDefender.internal.D2.E.CELL_INFO_CDMA, com.threatmetrix.TrustDefender.internal.D2.E.CELL_IDENTITY_CDMA);

        /* renamed from: for reason: not valid java name */
        private static final Class<?> f441for = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.TELEPHONY_MANAGER);

        /* renamed from: goto reason: not valid java name */
        private static final boolean f442goto;

        /* renamed from: if reason: not valid java name */
        private static final Class<?> f443if = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.NEIGHBOR_CELL_INFO);

        /* renamed from: int reason: not valid java name */
        private static final Class<?> f444int = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.SUBSCRIPTION_INFO);

        /* renamed from: new reason: not valid java name */
        private static final Class<?> f445new = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.CELL_INFO);

        /* renamed from: this reason: not valid java name */
        private static final boolean f446this = m196new(com.threatmetrix.TrustDefender.internal.D2.E.CELL_INFO_WCDMA, com.threatmetrix.TrustDefender.internal.D2.E.CELL_IDENTITY_WCDMA);

        /* renamed from: try reason: not valid java name */
        private static final boolean f447try = m196new(com.threatmetrix.TrustDefender.internal.D2.E.CELL_INFO_GSM, com.threatmetrix.TrustDefender.internal.D2.E.CELL_IDENTITY_GSM);

        /* renamed from: void reason: not valid java name */
        private static final boolean f448void = m196new(com.threatmetrix.TrustDefender.internal.D2.E.CELL_INFO_LTE, com.threatmetrix.TrustDefender.internal.D2.E.CELL_IDENTITY_LTE);

        Y() {
        }

        /* renamed from: new reason: not valid java name */
        private static boolean m196new(com.threatmetrix.TrustDefender.internal.D2.E e, com.threatmetrix.TrustDefender.internal.D2.E e2) {
            Class cls = D2.m38do(e);
            if (D2.m38do(e2) == null || D2.m42do(cls, "getCellSignalStrength", new Class[0]) == null || D2.m42do(cls, "getCellIdentity", new Class[0]) == null) {
                return false;
            }
            return true;
        }

        static {
            boolean z = false;
            if (!(D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.CELL_LOCATION) == null || D2.m44for(f441for, "getCellLocation", new Class[0]) == null)) {
                z = true;
            }
            f442goto = z;
        }

        /* renamed from: int reason: not valid java name */
        static boolean m194int() {
            if (f441for == null || f439do == null || f445new == null || D2.m44for(f445new, "isRegistered", new Class[0]) == null || D2.m44for(f441for, "getAllCellInfo", new Class[0]) == null) {
                return false;
            }
            return true;
        }

        /* renamed from: do reason: not valid java name */
        static boolean m189do() {
            if (f441for == null || D2.m44for(f441for, "getNetworkOperator", new Class[0]) == null || D2.m44for(f441for, "getNetworkCountryIso", new Class[0]) == null || D2.m42do((Class) f441for, "getNetworkOperatorName", new Class[0]) == null) {
                return false;
            }
            return true;
        }

        /* renamed from: for reason: not valid java name */
        static boolean m191for() {
            if (!f442goto || D2.m44for(f438char, "getSystemId", new Class[0]) == null || D2.m44for(f438char, "getBaseStationId", new Class[0]) == null || D2.m44for(f438char, "getBaseStationLatitude", new Class[0]) == null || D2.m44for(f438char, "getBaseStationLongitude", new Class[0]) == null) {
                return false;
            }
            return true;
        }

        /* renamed from: new reason: not valid java name */
        static boolean m195new() {
            if (!f442goto || D2.m44for(f437case, "getCid", new Class[0]) == null || D2.m44for(f437case, "getLac", new Class[0]) == null || D2.m44for(f437case, "getPsc", new Class[0]) == null) {
                return false;
            }
            return true;
        }

        /* renamed from: if reason: not valid java name */
        static boolean m193if() {
            if (f443if == null || D2.m44for(f443if, "getCid", new Class[0]) == null || D2.m44for(f443if, "getRssi", new Class[0]) == null) {
                return false;
            }
            return true;
        }

        /* renamed from: else reason: not valid java name */
        static boolean m190else() {
            if (f436byte == null || f444int == null || D2.m44for(f444int, "getSimSlotIndex", new Class[0]) == null || D2.m44for(f444int, "getCarrierName", new Class[0]) == null || D2.m44for(f444int, "getDisplayName", new Class[0]) == null || D2.m44for(f444int, "getIccId", new Class[0]) == null || D2.m44for(f444int, "getNumber", new Class[0]) == null || D2.m44for(f444int, "getCountryIso", new Class[0]) == null || D2.m44for(f444int, "getDataRoaming", new Class[0]) == null || D2.m44for(f436byte, "getActiveSubscriptionInfoList", new Class[0]) == null) {
                return false;
            }
            return true;
        }

        /* renamed from: byte reason: not valid java name */
        static boolean m186byte() {
            return f446this;
        }

        /* renamed from: case reason: not valid java name */
        static boolean m187case() {
            return f447try;
        }

        /* renamed from: try reason: not valid java name */
        static boolean m197try() {
            return f448void;
        }

        /* renamed from: char reason: not valid java name */
        static boolean m188char() {
            return f440else;
        }

        /* renamed from: goto reason: not valid java name */
        static boolean m192goto() {
            if (f441for == null || D2.m44for(f441for, "getDataState", new Class[0]) == null || D2.m41do(f441for, "DATA_CONNECTED") == null || D2.m41do(f441for, "DATA_CONNECTING") == null || D2.m41do(f441for, "DATA_SUSPENDED") == null) {
                return false;
            }
            return true;
        }
    }

    static class Z {

        /* renamed from: byte reason: not valid java name */
        private static final boolean f449byte;

        /* renamed from: char reason: not valid java name */
        private static final boolean f450char;

        /* renamed from: do reason: not valid java name */
        private static final boolean f451do;

        /* renamed from: for reason: not valid java name */
        private static final boolean f452for;

        /* renamed from: if reason: not valid java name */
        private static final boolean f453if;

        /* renamed from: int reason: not valid java name */
        private static final boolean f454int;

        /* renamed from: new reason: not valid java name */
        private static final boolean f455new;

        /* renamed from: try reason: not valid java name */
        private static final boolean f456try;

        Z() {
        }

        static {
            Class cls = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.SETTING_SECURE);
            boolean z = false;
            f454int = D2.m44for(cls, "getString", ContentResolver.class, String.class) != null;
            f453if = D2.m41do(cls, "ANDROID_ID") != null;
            f455new = D2.m41do(cls, "ALLOW_MOCK_LOCATION") != null;
            f452for = D2.m41do(cls, "ADB_ENABLED") != null;
            f451do = D2.m41do(cls, "DEVELOPMENT_SETTINGS_ENABLED") != null;
            Class cls2 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.SETTING_GLOBAL);
            f449byte = D2.m44for(cls2, "getString", ContentResolver.class, String.class) != null;
            f456try = D2.m41do(cls2, "ADB_ENABLED") != null;
            if (D2.m41do(cls2, "DEVELOPMENT_SETTINGS_ENABLED") != null) {
                z = true;
            }
            f450char = z;
        }

        /* renamed from: for reason: not valid java name */
        static String m198for(ContentResolver contentResolver, String str) {
            if (contentResolver == null || NK.m215if(str) || !f454int) {
                return null;
            }
            try {
                String string = Secure.getString(contentResolver, str);
                if (string != null) {
                    return string;
                }
                if ("android_id".equals(str) && f453if) {
                    return Secure.getString(contentResolver, "android_id");
                }
                if ("mock_location".equals(str) && f455new) {
                    return Secure.getString(contentResolver, "mock_location");
                }
                if ("adb_enabled".equals(str) && f452for) {
                    return Secure.getString(contentResolver, "adb_enabled");
                }
                if ("development_settings_enabled".equals(str) && f451do && C0012I.f388for >= W.f394char) {
                    return Secure.getString(contentResolver, "development_settings_enabled");
                }
                return null;
            } catch (SecurityException unused) {
                N.f353new;
            } catch (Exception e) {
                TL.m338new(N.f353new, e.toString());
            }
        }

        /* renamed from: if reason: not valid java name */
        static String m199if(ContentResolver contentResolver, String str) {
            if (contentResolver == null || NK.m215if(str) || !f449byte) {
                return null;
            }
            try {
                if ("adb_enabled".equals(str) && f456try) {
                    return Global.getString(contentResolver, "adb_enabled");
                }
                if ("development_settings_enabled".equals(str) && f450char) {
                    return Global.getString(contentResolver, "development_settings_enabled");
                }
                return null;
            } catch (SecurityException unused) {
                N.f353new;
            } catch (Exception e) {
                TL.m338new(N.f353new, e.toString());
            }
        }
    }

    N() {
    }
}
