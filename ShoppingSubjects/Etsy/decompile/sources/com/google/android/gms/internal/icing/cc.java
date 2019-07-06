package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.util.List;

final class cc {
    private static final Class<?> a = d();
    private static final cr<?, ?> b = a(false);
    private static final cr<?, ?> c = a(true);
    private static final cr<?, ?> d = new ct();

    static int a(int i, Object obj, ca caVar) {
        return obj instanceof at ? zzbu.a(i, (at) obj) : zzbu.b(i, (bl) obj, caVar);
    }

    static int a(int i, List<?> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int e = zzbu.e(i) * size;
        if (list instanceof aw) {
            aw awVar = (aw) list;
            while (i2 < size) {
                Object b2 = awVar.b(i2);
                e += b2 instanceof zzbi ? zzbu.b((zzbi) b2) : zzbu.b((String) b2);
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                e += obj instanceof zzbi ? zzbu.b((zzbi) obj) : zzbu.b((String) obj);
                i2++;
            }
        }
        return e;
    }

    static int a(int i, List<?> list, ca caVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int e = zzbu.e(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            e += obj instanceof at ? zzbu.a((at) obj) : zzbu.a((bl) obj, caVar);
        }
        return e;
    }

    static int a(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return a(list) + (list.size() * zzbu.e(i));
    }

    static int a(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof bb) {
            bb bbVar = (bb) list;
            i = 0;
            while (i2 < size) {
                i += zzbu.d(bbVar.b(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbu.d(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static cr<?, ?> a() {
        return b;
    }

    private static cr<?, ?> a(boolean z) {
        try {
            Class e = e();
            if (e == null) {
                return null;
            }
            return (cr) e.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void a(int i, List<String> list, df dfVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.a(i, list);
        }
    }

    public static void a(int i, List<?> list, df dfVar, ca caVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.a(i, list, caVar);
        }
    }

    public static void a(int i, List<Double> list, df dfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.g(i, list, z);
        }
    }

    static <T> void a(bg bgVar, T t, T t2, long j) {
        cy.a((Object) t, j, bgVar.a(cy.f(t, j), cy.f(t2, j)));
    }

    static <T, UT, UB> void a(cr<UT, UB> crVar, T t, T t2) {
        crVar.a((Object) t, crVar.b(crVar.a(t), crVar.a(t2)));
    }

    static <T, FT extends ae<FT>> void a(z<FT> zVar, T t, T t2) {
        ac a2 = zVar.a((Object) t2);
        if (!a2.b()) {
            zVar.b(t).a(a2);
        }
    }

    public static void a(Class<?> cls) {
        if (!ah.class.isAssignableFrom(cls) && a != null && !a.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static int b(int i, List<zzbi> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int e = size * zzbu.e(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            e += zzbu.b((zzbi) list.get(i2));
        }
        return e;
    }

    static int b(int i, List<bl> list, ca caVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzbu.c(i, (bl) list.get(i3), caVar);
        }
        return i2;
    }

    static int b(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return b(list) + (size * zzbu.e(i));
    }

    static int b(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof bb) {
            bb bbVar = (bb) list;
            i = 0;
            while (i2 < size) {
                i += zzbu.e(bbVar.b(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbu.e(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static cr<?, ?> b() {
        return c;
    }

    public static void b(int i, List<zzbi> list, df dfVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.b(i, list);
        }
    }

    public static void b(int i, List<?> list, df dfVar, ca caVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.b(i, list, caVar);
        }
    }

    public static void b(int i, List<Float> list, df dfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.f(i, list, z);
        }
    }

    static int c(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return c(list) + (size * zzbu.e(i));
    }

    static int c(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof bb) {
            bb bbVar = (bb) list;
            i = 0;
            while (i2 < size) {
                i += zzbu.f(bbVar.b(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbu.f(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static cr<?, ?> c() {
        return d;
    }

    public static void c(int i, List<Long> list, df dfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.c(i, list, z);
        }
    }

    static int d(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return d(list) + (size * zzbu.e(i));
    }

    static int d(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof ai) {
            ai aiVar = (ai) list;
            i = 0;
            while (i2 < size) {
                i += zzbu.k(aiVar.b(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbu.k(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    private static Class<?> d() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void d(int i, List<Long> list, df dfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.d(i, list, z);
        }
    }

    static int e(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return e(list) + (size * zzbu.e(i));
    }

    static int e(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof ai) {
            ai aiVar = (ai) list;
            i = 0;
            while (i2 < size) {
                i += zzbu.f(aiVar.b(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbu.f(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    private static Class<?> e() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void e(int i, List<Long> list, df dfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.n(i, list, z);
        }
    }

    static int f(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return f(list) + (size * zzbu.e(i));
    }

    static int f(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof ai) {
            ai aiVar = (ai) list;
            i = 0;
            while (i2 < size) {
                i += zzbu.g(aiVar.b(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbu.g(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void f(int i, List<Long> list, df dfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.e(i, list, z);
        }
    }

    static int g(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return g(list) + (size * zzbu.e(i));
    }

    static int g(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof ai) {
            ai aiVar = (ai) list;
            i = 0;
            while (i2 < size) {
                i += zzbu.h(aiVar.b(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbu.h(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void g(int i, List<Long> list, df dfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.l(i, list, z);
        }
    }

    static int h(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbu.i(i, 0);
    }

    static int h(List<?> list) {
        return list.size() << 2;
    }

    public static void h(int i, List<Integer> list, df dfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.a(i, list, z);
        }
    }

    static int i(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbu.g(i, 0);
    }

    static int i(List<?> list) {
        return list.size() << 3;
    }

    public static void i(int i, List<Integer> list, df dfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.j(i, list, z);
        }
    }

    static int j(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbu.b(i, true);
    }

    static int j(List<?> list) {
        return list.size();
    }

    public static void j(int i, List<Integer> list, df dfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.m(i, list, z);
        }
    }

    public static void k(int i, List<Integer> list, df dfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.b(i, list, z);
        }
    }

    public static void l(int i, List<Integer> list, df dfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.k(i, list, z);
        }
    }

    public static void m(int i, List<Integer> list, df dfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.h(i, list, z);
        }
    }

    public static void n(int i, List<Boolean> list, df dfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dfVar.i(i, list, z);
        }
    }
}
