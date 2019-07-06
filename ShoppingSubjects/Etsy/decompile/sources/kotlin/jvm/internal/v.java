package kotlin.jvm.internal;

import kotlin.jvm.a.a;
import kotlin.jvm.a.b;
import kotlin.jvm.a.c;
import kotlin.jvm.a.d;
import kotlin.jvm.a.e;
import kotlin.jvm.a.f;
import kotlin.jvm.a.g;
import kotlin.jvm.a.h;
import kotlin.jvm.a.i;
import kotlin.jvm.a.j;
import kotlin.jvm.a.k;
import kotlin.jvm.a.l;
import kotlin.jvm.a.m;
import kotlin.jvm.a.n;
import kotlin.jvm.a.o;
import kotlin.jvm.a.p;
import kotlin.jvm.a.q;
import kotlin.jvm.a.r;
import kotlin.jvm.a.s;
import kotlin.jvm.a.t;
import kotlin.jvm.a.u;
import kotlin.jvm.a.w;

/* compiled from: TypeIntrinsics */
public class v {
    private static <T extends Throwable> T a(T t) {
        return p.a(t, v.class.getName());
    }

    public static void a(Object obj, String str) {
        String name = obj == null ? "null" : obj.getClass().getName();
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" cannot be cast to ");
        sb.append(str);
        a(sb.toString());
    }

    public static void a(String str) {
        throw a(new ClassCastException(str));
    }

    public static ClassCastException a(ClassCastException classCastException) {
        throw ((ClassCastException) a((T) classCastException));
    }

    public static int a(Object obj) {
        if (obj instanceof FunctionBase) {
            return ((FunctionBase) obj).getArity();
        }
        if (obj instanceof a) {
            return 0;
        }
        if (obj instanceof b) {
            return 1;
        }
        if (obj instanceof m) {
            return 2;
        }
        if (obj instanceof q) {
            return 3;
        }
        if (obj instanceof r) {
            return 4;
        }
        if (obj instanceof s) {
            return 5;
        }
        if (obj instanceof t) {
            return 6;
        }
        if (obj instanceof u) {
            return 7;
        }
        if (obj instanceof kotlin.jvm.a.v) {
            return 8;
        }
        if (obj instanceof w) {
            return 9;
        }
        if (obj instanceof c) {
            return 10;
        }
        if (obj instanceof d) {
            return 11;
        }
        if (obj instanceof e) {
            return 12;
        }
        if (obj instanceof f) {
            return 13;
        }
        if (obj instanceof g) {
            return 14;
        }
        if (obj instanceof h) {
            return 15;
        }
        if (obj instanceof i) {
            return 16;
        }
        if (obj instanceof j) {
            return 17;
        }
        if (obj instanceof k) {
            return 18;
        }
        if (obj instanceof l) {
            return 19;
        }
        if (obj instanceof n) {
            return 20;
        }
        if (obj instanceof o) {
            return 21;
        }
        return obj instanceof p ? 22 : -1;
    }

    public static boolean a(Object obj, int i) {
        return (obj instanceof kotlin.a) && a(obj) == i;
    }

    public static Object b(Object obj, int i) {
        if (obj != null && !a(obj, i)) {
            StringBuilder sb = new StringBuilder();
            sb.append("kotlin.jvm.functions.Function");
            sb.append(i);
            a(obj, sb.toString());
        }
        return obj;
    }
}
