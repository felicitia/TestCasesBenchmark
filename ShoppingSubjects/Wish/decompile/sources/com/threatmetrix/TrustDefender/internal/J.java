package com.threatmetrix.TrustDefender.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class J {

    /* renamed from: do reason: not valid java name */
    static final W f184do;
    /* access modifiers changed from: private */

    /* renamed from: if reason: not valid java name */
    public static final String f185if = TL.m331if(J.class);

    /* renamed from: new reason: not valid java name */
    private static String f186new;

    static class E implements InvocationHandler {

        /* renamed from: if reason: not valid java name */
        final O f187if;

        E(O o) {
            this.f187if = o;
        }

        public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if ("onConnected".equals(method.getName())) {
                if (this.f187if != null) {
                    this.f187if.m100new();
                }
                return null;
            } else if ("onConnectionSuspended".equals(method.getName())) {
                if (objArr != null && objArr.length > 0) {
                    String str = J.f185if;
                    StringBuilder sb = new StringBuilder("Connection to the Google Service is suspended, the error code is ");
                    sb.append(objArr[0]);
                    TL.m325do(str, sb.toString());
                }
                if (this.f187if != null) {
                    this.f187if.m99if();
                }
                return null;
            } else if (!"onConnectionFailed".equals(method.getName())) {
                return J.m92if(this, method, objArr);
            } else {
                if (objArr != null && objArr.length > 0) {
                    String str2 = J.f185if;
                    StringBuilder sb2 = new StringBuilder("Connection to the Google Service is failed. The error code is ");
                    sb2.append(objArr[0]);
                    TL.m325do(str2, sb2.toString());
                }
                if (this.f187if != null) {
                    this.f187if.m99if();
                }
                return null;
            }
        }
    }

    static class L {

        /* renamed from: do reason: not valid java name */
        final Object f188do;

        /* renamed from: for reason: not valid java name */
        final Object f189for;

        /* renamed from: if reason: not valid java name */
        final Object f190if;

        /* renamed from: int reason: not valid java name */
        final Object f191int;

        /* renamed from: new reason: not valid java name */
        final Object f192new;

        /* access modifiers changed from: 0000 */
        /* renamed from: new reason: not valid java name */
        public final void m98new(Object obj, Object obj2, InvocationHandler invocationHandler, Looper looper) {
            W w = J.f184do;
            if (w != null) {
                Object obj3 = D2.m39do(D2.m40do(w.f208final), w.f221package, this.f192new, obj, obj2, looper);
                if (obj3 != null) {
                    Object obj4 = D2.m47new(w.f218long.getClassLoader(), new Class[]{w.f218long}, invocationHandler);
                    D2.m39do(obj3, w.f193abstract, obj4);
                }
            }
        }

        L(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            this.f192new = obj;
            this.f190if = obj2;
            this.f191int = obj3;
            this.f189for = obj4;
            this.f188do = obj5;
        }
    }

    interface O {
        /* renamed from: if reason: not valid java name */
        void m99if();

        /* renamed from: new reason: not valid java name */
        void m100new();
    }

    static class W {

        /* renamed from: abstract reason: not valid java name */
        final Method f193abstract;

        /* renamed from: boolean reason: not valid java name */
        final Method f194boolean;

        /* renamed from: break reason: not valid java name */
        final Class<?> f195break;

        /* renamed from: byte reason: not valid java name */
        final Class<?> f196byte;

        /* renamed from: case reason: not valid java name */
        final Class<?> f197case;

        /* renamed from: catch reason: not valid java name */
        final Field f198catch;

        /* renamed from: char reason: not valid java name */
        final Class<?> f199char;

        /* renamed from: class reason: not valid java name */
        final Field f200class;

        /* renamed from: const reason: not valid java name */
        final Field f201const;

        /* renamed from: continue reason: not valid java name */
        final Method f202continue;

        /* renamed from: default reason: not valid java name */
        final Method f203default;

        /* renamed from: do reason: not valid java name */
        final Class<?> f204do;

        /* renamed from: double reason: not valid java name */
        final Method f205double;

        /* renamed from: else reason: not valid java name */
        final Class<?> f206else;

        /* renamed from: extends reason: not valid java name */
        final Method f207extends;

        /* renamed from: final reason: not valid java name */
        final Field f208final;

        /* renamed from: finally reason: not valid java name */
        final Method f209finally;

        /* renamed from: float reason: not valid java name */
        final Field f210float;

        /* renamed from: for reason: not valid java name */
        final Class<?> f211for;

        /* renamed from: goto reason: not valid java name */
        final Class<?> f212goto;

        /* renamed from: if reason: not valid java name */
        final Class<?> f213if;

        /* renamed from: implements reason: not valid java name */
        final Object f214implements;

        /* renamed from: import reason: not valid java name */
        final Method f215import;

        /* renamed from: int reason: not valid java name */
        final Class<?> f216int;

        /* renamed from: interface reason: not valid java name */
        final Method f217interface;

        /* renamed from: long reason: not valid java name */
        final Class<?> f218long;

        /* renamed from: native reason: not valid java name */
        final Method f219native;

        /* renamed from: new reason: not valid java name */
        final Class<?> f220new;

        /* renamed from: package reason: not valid java name */
        final Method f221package;

        /* renamed from: private reason: not valid java name */
        final Method f222private;

        /* renamed from: protected reason: not valid java name */
        final Object f223protected;

        /* renamed from: public reason: not valid java name */
        final Method f224public;

        /* renamed from: return reason: not valid java name */
        final Method f225return;

        /* renamed from: short reason: not valid java name */
        final Field f226short;

        /* renamed from: static reason: not valid java name */
        final Method f227static;

        /* renamed from: strictfp reason: not valid java name */
        final Object f228strictfp;

        /* renamed from: super reason: not valid java name */
        final Method f229super;

        /* renamed from: switch reason: not valid java name */
        final Method f230switch;

        /* renamed from: this reason: not valid java name */
        final Class<?> f231this;

        /* renamed from: throw reason: not valid java name */
        final Field f232throw;

        /* renamed from: throws reason: not valid java name */
        final Method f233throws;

        /* renamed from: transient reason: not valid java name */
        final Method f234transient;

        /* renamed from: try reason: not valid java name */
        final Class<?> f235try;

        /* renamed from: void reason: not valid java name */
        final Class<?> f236void;

        /* renamed from: volatile reason: not valid java name */
        final Method f237volatile;

        /* renamed from: while reason: not valid java name */
        final Field f238while;

        W() throws Exception {
            Method method;
            Class<?> cls = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_PLAY_UTILS);
            Class<?> cls2 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_AVAILABILITY);
            Class<?> cls3 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_API_BUILDER);
            Class<?> cls4 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_CONNECTION_CALL_BACK);
            Class<?> cls5 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_FAILED_CALL_BACK);
            Class<?> cls6 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_LOCATION_SERVICES);
            Class<?> cls7 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_API);
            Class<?> cls8 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_API_CLIENT);
            Class<?> cls9 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_LOCATION_REQUEST);
            Class<?> cls10 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_LOCATION_LISTENER);
            Class<?> cls11 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_PENDING_RESULT);
            Class<?> cls12 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_STATUS);
            Class<?> cls13 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_RESULT_CALL_BACK);
            Class<?> cls14 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_SAFETY_NET);
            Class cls15 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_SAFETY_NET_API);
            Class<?> cls16 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_SAFETY_NET_ATTEST_RESULT);
            if ((cls == null && cls2 == null) || cls3 == null || cls4 == null || cls5 == null || cls6 == null || cls7 == null || cls8 == null || cls9 == null || cls10 == null || cls11 == null || cls12 == null || cls13 == null || cls14 == null || cls15 == null || cls16 == null) {
                throw new Exception("");
            }
            this.f204do = cls;
            this.f216int = cls2;
            this.f220new = cls3;
            this.f211for = cls4;
            this.f213if = cls5;
            this.f197case = cls6;
            this.f235try = cls7;
            this.f199char = cls8;
            this.f195break = cls9;
            this.f212goto = cls10;
            this.f231this = cls11;
            this.f236void = cls12;
            this.f218long = cls13;
            this.f206else = cls14;
            this.f196byte = cls16;
            Field field = D2.m41do(this.f206else, "API");
            Field field2 = D2.m41do(this.f206else, "SafetyNetApi");
            Field field3 = D2.m41do(this.f197case, "API");
            Field field4 = D2.m41do(this.f197case, "FusedLocationApi");
            Field field5 = D2.m41do(this.f195break, "PRIORITY_NO_POWER");
            Field field6 = D2.m41do(this.f195break, "PRIORITY_LOW_POWER");
            Field field7 = D2.m41do(this.f195break, "PRIORITY_HIGH_ACCURACY");
            Field field8 = D2.m41do(this.f195break, "PRIORITY_BALANCED_POWER_ACCURACY");
            if (field3 == null || field4 == null || field5 == null || field6 == null || field7 == null || field8 == null || field == null || field2 == null) {
                throw new Exception("");
            }
            this.f198catch = field;
            this.f200class = field2;
            this.f201const = field3;
            this.f208final = field4;
            this.f210float = field5;
            this.f226short = field6;
            this.f232throw = field7;
            this.f238while = field8;
            Method method2 = D2.m44for(this.f220new, "build", new Class[0]);
            Method method3 = D2.m44for(this.f220new, "addApi", this.f235try);
            Method method4 = D2.m44for(this.f220new, "addConnectionCallbacks", this.f211for);
            Method method5 = D2.m44for(this.f220new, "addOnConnectionFailedListener", this.f213if);
            Method method6 = D2.m44for(this.f220new, "setHandler", Handler.class);
            Method method7 = D2.m44for(this.f199char, "connect", new Class[0]);
            Method method8 = D2.m44for(this.f199char, "disconnect", new Class[0]);
            Method method9 = D2.m44for(this.f236void, "isSuccess", new Class[0]);
            Method method10 = D2.m44for(this.f231this, "setResultCallback", this.f218long);
            Method method11 = D2.m44for(this.f195break, "setPriority", Integer.TYPE);
            Method method12 = D2.m44for(this.f195break, "setInterval", Long.TYPE);
            Method method13 = method6;
            Method method14 = D2.m44for(this.f195break, "setFastestInterval", Long.TYPE);
            Method method15 = method10;
            Method method16 = D2.m44for(this.f208final.getType(), "getLastLocation", this.f199char);
            Method method17 = D2.m44for(this.f208final.getType(), "requestLocationUpdates", this.f199char, this.f195break, this.f212goto, Looper.class);
            Method method18 = D2.m44for(this.f208final.getType(), "removeLocationUpdates", this.f199char, this.f212goto);
            Method method19 = D2.m44for(this.f200class.getType(), "attest", this.f199char, byte[].class);
            Method method20 = D2.m44for(this.f196byte, "getStatus", new Class[0]);
            Method method21 = D2.m44for(this.f196byte, "getJwsResult", new Class[0]);
            if (method2 == null || method7 == null || method9 == null || method8 == null || method3 == null || method11 == null || method12 == null || method14 == null || method4 == null || method5 == null || method15 == null || method16 == null || method17 == null || method18 == null || method19 == null || method20 == null || method21 == null || method13 == null) {
                throw new Exception("");
            }
            Method method22 = method21;
            Method method23 = method20;
            Method method24 = D2.m44for(this.f216int, "isGooglePlayServicesAvailable", Context.class);
            Method method25 = D2.m44for(this.f216int, "getInstance", new Class[0]);
            if (method25 == null) {
                method = D2.m44for(this.f204do, "isGooglePlayServicesAvailable", Context.class);
                if (method == null) {
                    throw new Exception("");
                }
            } else {
                method = method24;
            }
            this.f224public = method2;
            this.f230switch = method7;
            this.f217interface = method9;
            this.f203default = method8;
            this.f215import = method3;
            this.f225return = method13;
            this.f222private = method11;
            this.f194boolean = method12;
            this.f233throws = method14;
            this.f219native = method4;
            this.f227static = method5;
            this.f193abstract = method15;
            this.f229super = method;
            this.f205double = method25;
            this.f221package = method17;
            this.f202continue = method18;
            this.f209finally = method16;
            this.f207extends = method19;
            this.f234transient = method23;
            this.f237volatile = method22;
            Object obj = D2.m40do(this.f210float);
            Object obj2 = D2.m40do(this.f226short);
            Object obj3 = D2.m40do(this.f232throw);
            Object obj4 = D2.m40do(this.f238while);
            if (obj == null || obj2 == null || obj3 == null || obj4 == null) {
                throw new Exception("");
            }
            this.f228strictfp = obj;
            this.f223protected = obj3;
            this.f214implements = obj4;
        }
    }

    J() {
    }

    static {
        W w;
        try {
            w = new W();
        } catch (Exception unused) {
            w = null;
        }
        f184do = w;
    }

    /* renamed from: if reason: not valid java name */
    static boolean m93if(Context context) {
        W w = f184do;
        if (w == null) {
            return false;
        }
        Object obj = D2.m39do((Object) null, w.f205double, new Object[0]);
        if (obj != null) {
            Object obj2 = D2.m39do(obj, w.f229super, context);
            if (obj2 != null && obj2.equals(Integer.valueOf(0))) {
                return true;
            }
        }
        Object obj3 = D2.m39do((Object) null, w.f229super, context);
        if (obj3 == null || !obj3.equals(Integer.valueOf(0))) {
            return false;
        }
        return true;
    }

    /* renamed from: int reason: not valid java name */
    private static Object m95int(Context context, O o, Handler handler) {
        W w = f184do;
        if (w == null || !m93if(context)) {
            return null;
        }
        E e = new E(o);
        Object obj = D2.m45if(w.f220new, new Class[]{Context.class}, new Object[]{context});
        if (obj == null) {
            return null;
        }
        Object obj2 = D2.m47new(w.f211for.getClassLoader(), new Class[]{w.f211for}, e);
        Object obj3 = D2.m39do(obj, w.f219native, obj2);
        if (obj3 == null) {
            return null;
        }
        Object obj4 = D2.m47new(w.f213if.getClassLoader(), new Class[]{w.f213if}, e);
        Object obj5 = D2.m39do(obj3, w.f227static, obj4);
        if (obj5 == null) {
            return null;
        }
        Object obj6 = D2.m39do(obj5, w.f215import, D2.m40do(w.f201const));
        if (obj6 == null) {
            return null;
        }
        Object obj7 = D2.m39do(obj6, w.f215import, D2.m40do(w.f198catch));
        if (obj7 == null) {
            return null;
        }
        if (handler != null) {
            obj7 = D2.m39do(obj7, w.f225return, handler);
            if (obj7 == null) {
                return null;
            }
        }
        Object obj8 = D2.m39do(obj7, w.f224public, new Object[0]);
        if (obj8 == null) {
            return null;
        }
        D2.m39do(obj8, w.f230switch, new Object[0]);
        return obj8;
    }

    /* renamed from: if reason: not valid java name */
    static Object m92if(Object obj, Method method, Object[] objArr) {
        if ("toString".equals(method.getName())) {
            return method.getName();
        }
        if ("hashCode".equals(method.getName())) {
            return Integer.valueOf(obj.hashCode());
        }
        if (!"equals".equals(method.getName())) {
            return null;
        }
        boolean z = true;
        if (objArr != null && objArr.length == 2 && !objArr[0].equals(objArr[1])) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* renamed from: if reason: not valid java name */
    static L m91if(Context context, long j, long j2, int i, O o, InvocationHandler invocationHandler, InvocationHandler invocationHandler2, Handler handler) {
        W w = f184do;
        if (w == null) {
            return null;
        }
        Object obj = m95int(context, o, handler);
        if (obj == null) {
            return null;
        }
        W w2 = f184do;
        Object valueOf = Integer.valueOf(102);
        if (w2 != null) {
            switch (i) {
                case 1:
                case 2:
                    valueOf = w2.f214implements;
                    break;
                case 3:
                    valueOf = w2.f223protected;
                    break;
                default:
                    valueOf = w2.f214implements;
                    break;
            }
        }
        Object obj2 = m94int(j, j2, ((Integer) valueOf).intValue());
        Object obj3 = m94int(j, j2, ((Integer) w.f228strictfp).intValue());
        if (obj2 == null || obj3 == null) {
            return null;
        }
        L l = new L(obj, obj2, obj3, D2.m47new(w.f212goto.getClassLoader(), new Class[]{w.f212goto}, invocationHandler), D2.m47new(w.f212goto.getClassLoader(), new Class[]{w.f212goto}, invocationHandler2));
        return l;
    }

    /* renamed from: int reason: not valid java name */
    static String m97int(Context context) {
        if (f186new == null && m93if(context)) {
            Class cls = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_ADVERTISING_ID);
            if (cls == null) {
                return null;
            }
            Class cls2 = D2.m38do(com.threatmetrix.TrustDefender.internal.D2.E.GOOGLE_ADVERTISING_INFO);
            if (cls2 == null) {
                return null;
            }
            Method method = D2.m44for(cls2, "getId", new Class[0]);
            if (method == null) {
                return null;
            }
            Method method2 = D2.m44for(cls, "getAdvertisingIdInfo", Context.class);
            if (method2 == null) {
                return null;
            }
            Object obj = D2.m39do((Object) null, method2, context);
            if (obj != null) {
                Object obj2 = D2.m39do(obj, method, new Object[0]);
                if (obj2 != null) {
                    String valueOf = String.valueOf(obj2);
                    f186new = valueOf;
                    return valueOf;
                }
            }
        }
        return f186new;
    }

    /* renamed from: for reason: not valid java name */
    static void m89for(Context context, byte[] bArr, InvocationHandler invocationHandler) {
        W w = f184do;
        Object obj = m95int(context, (O) null, (Handler) null);
        if (w != null && obj != null) {
            Object obj2 = D2.m39do(D2.m40do(w.f200class), w.f207extends, obj, bArr);
            Object obj3 = D2.m47new(w.f218long.getClassLoader(), new Class[]{w.f218long}, invocationHandler);
            D2.m39do(obj2, w.f193abstract, obj3);
        }
    }

    /* renamed from: int reason: not valid java name */
    private static Object m94int(long j, long j2, int i) {
        W w = f184do;
        if (w == null) {
            return null;
        }
        Object obj = D2.m45if(w.f195break, null, null);
        D2.m39do(obj, w.f194boolean, Long.valueOf(j));
        D2.m39do(obj, w.f233throws, Long.valueOf(j2));
        D2.m39do(obj, w.f222private, Integer.valueOf(i));
        return obj;
    }

    /* renamed from: for reason: not valid java name */
    static boolean m90for(Object obj) {
        W w = f184do;
        if (w == null) {
            return false;
        }
        Object obj2 = D2.m39do(obj, w.f234transient, new Object[0]);
        if (obj2 == null) {
            return false;
        }
        Object obj3 = D2.m39do(obj2, w.f217interface, new Object[0]);
        if (obj3 == null || !(obj3 instanceof Boolean) || !((Boolean) obj3).booleanValue()) {
            return false;
        }
        return true;
    }
}
