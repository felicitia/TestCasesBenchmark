package com.google.android.gms.internal.ads;

import com.etsy.android.lib.models.ResponseConstants;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class aab {
    private static final Logger a = Logger.getLogger(aab.class.getName());
    private static final Unsafe b = c();
    private static final Class<?> c = wb.b();
    private static final boolean d = c(Long.TYPE);
    private static final boolean e = c(Integer.TYPE);
    private static final d f;
    private static final boolean g = f();
    private static final boolean h = e();
    private static final long i = ((long) a(byte[].class));
    private static final long j = ((long) a(boolean[].class));
    private static final long k = ((long) b(boolean[].class));
    private static final long l = ((long) a(int[].class));
    private static final long m = ((long) b(int[].class));
    private static final long n = ((long) a(long[].class));
    private static final long o = ((long) b(long[].class));
    private static final long p = ((long) a(float[].class));
    private static final long q = ((long) b(float[].class));
    private static final long r = ((long) a(double[].class));
    private static final long s = ((long) b(double[].class));
    private static final long t = ((long) a(Object[].class));
    private static final long u = ((long) b(Object[].class));
    private static final long v = b(g());
    private static final long w;
    /* access modifiers changed from: private */
    public static final boolean x = (ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN);

    static final class a extends d {
        a(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte a(Object obj, long j) {
            return aab.x ? aab.k(obj, j) : aab.l(obj, j);
        }

        public final void a(Object obj, long j, byte b) {
            if (aab.x) {
                aab.c(obj, j, b);
            } else {
                aab.d(obj, j, b);
            }
        }

        public final void a(Object obj, long j, double d) {
            a(obj, j, Double.doubleToLongBits(d));
        }

        public final void a(Object obj, long j, float f) {
            a(obj, j, Float.floatToIntBits(f));
        }

        public final void a(Object obj, long j, boolean z) {
            if (aab.x) {
                aab.d(obj, j, z);
            } else {
                aab.e(obj, j, z);
            }
        }

        public final boolean b(Object obj, long j) {
            return aab.x ? aab.m(obj, j) : aab.n(obj, j);
        }

        public final float c(Object obj, long j) {
            return Float.intBitsToFloat(e(obj, j));
        }

        public final double d(Object obj, long j) {
            return Double.longBitsToDouble(f(obj, j));
        }
    }

    static final class b extends d {
        b(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte a(Object obj, long j) {
            return aab.x ? aab.k(obj, j) : aab.l(obj, j);
        }

        public final void a(Object obj, long j, byte b) {
            if (aab.x) {
                aab.c(obj, j, b);
            } else {
                aab.d(obj, j, b);
            }
        }

        public final void a(Object obj, long j, double d) {
            a(obj, j, Double.doubleToLongBits(d));
        }

        public final void a(Object obj, long j, float f) {
            a(obj, j, Float.floatToIntBits(f));
        }

        public final void a(Object obj, long j, boolean z) {
            if (aab.x) {
                aab.d(obj, j, z);
            } else {
                aab.e(obj, j, z);
            }
        }

        public final boolean b(Object obj, long j) {
            return aab.x ? aab.m(obj, j) : aab.n(obj, j);
        }

        public final float c(Object obj, long j) {
            return Float.intBitsToFloat(e(obj, j));
        }

        public final double d(Object obj, long j) {
            return Double.longBitsToDouble(f(obj, j));
        }
    }

    static final class c extends d {
        c(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte a(Object obj, long j) {
            return this.a.getByte(obj, j);
        }

        public final void a(Object obj, long j, byte b) {
            this.a.putByte(obj, j, b);
        }

        public final void a(Object obj, long j, double d) {
            this.a.putDouble(obj, j, d);
        }

        public final void a(Object obj, long j, float f) {
            this.a.putFloat(obj, j, f);
        }

        public final void a(Object obj, long j, boolean z) {
            this.a.putBoolean(obj, j, z);
        }

        public final boolean b(Object obj, long j) {
            return this.a.getBoolean(obj, j);
        }

        public final float c(Object obj, long j) {
            return this.a.getFloat(obj, j);
        }

        public final double d(Object obj, long j) {
            return this.a.getDouble(obj, j);
        }
    }

    static abstract class d {
        Unsafe a;

        d(Unsafe unsafe) {
            this.a = unsafe;
        }

        public abstract byte a(Object obj, long j);

        public final long a(Field field) {
            return this.a.objectFieldOffset(field);
        }

        public abstract void a(Object obj, long j, byte b);

        public abstract void a(Object obj, long j, double d);

        public abstract void a(Object obj, long j, float f);

        public final void a(Object obj, long j, int i) {
            this.a.putInt(obj, j, i);
        }

        public final void a(Object obj, long j, long j2) {
            this.a.putLong(obj, j, j2);
        }

        public abstract void a(Object obj, long j, boolean z);

        public abstract boolean b(Object obj, long j);

        public abstract float c(Object obj, long j);

        public abstract double d(Object obj, long j);

        public final int e(Object obj, long j) {
            return this.a.getInt(obj, j);
        }

        public final long f(Object obj, long j) {
            return this.a.getLong(obj, j);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0105  */
    static {
        /*
            java.lang.Class<com.google.android.gms.internal.ads.aab> r0 = com.google.android.gms.internal.ads.aab.class
            java.lang.String r0 = r0.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            a = r0
            sun.misc.Unsafe r0 = c()
            b = r0
            java.lang.Class r0 = com.google.android.gms.internal.ads.wb.b()
            c = r0
            java.lang.Class r0 = java.lang.Long.TYPE
            boolean r0 = c(r0)
            d = r0
            java.lang.Class r0 = java.lang.Integer.TYPE
            boolean r0 = c(r0)
            e = r0
            sun.misc.Unsafe r0 = b
            r1 = 0
            if (r0 != 0) goto L_0x002f
        L_0x002d:
            r0 = r1
            goto L_0x0054
        L_0x002f:
            boolean r0 = com.google.android.gms.internal.ads.wb.a()
            if (r0 == 0) goto L_0x004d
            boolean r0 = d
            if (r0 == 0) goto L_0x0041
            com.google.android.gms.internal.ads.aab$b r0 = new com.google.android.gms.internal.ads.aab$b
            sun.misc.Unsafe r2 = b
            r0.<init>(r2)
            goto L_0x0054
        L_0x0041:
            boolean r0 = e
            if (r0 == 0) goto L_0x002d
            com.google.android.gms.internal.ads.aab$a r0 = new com.google.android.gms.internal.ads.aab$a
            sun.misc.Unsafe r2 = b
            r0.<init>(r2)
            goto L_0x0054
        L_0x004d:
            com.google.android.gms.internal.ads.aab$c r0 = new com.google.android.gms.internal.ads.aab$c
            sun.misc.Unsafe r2 = b
            r0.<init>(r2)
        L_0x0054:
            f = r0
            boolean r0 = f()
            g = r0
            boolean r0 = e()
            h = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = a(r0)
            long r2 = (long) r0
            i = r2
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = a(r0)
            long r2 = (long) r0
            j = r2
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = b(r0)
            long r2 = (long) r0
            k = r2
            java.lang.Class<int[]> r0 = int[].class
            int r0 = a(r0)
            long r2 = (long) r0
            l = r2
            java.lang.Class<int[]> r0 = int[].class
            int r0 = b(r0)
            long r2 = (long) r0
            m = r2
            java.lang.Class<long[]> r0 = long[].class
            int r0 = a(r0)
            long r2 = (long) r0
            n = r2
            java.lang.Class<long[]> r0 = long[].class
            int r0 = b(r0)
            long r2 = (long) r0
            o = r2
            java.lang.Class<float[]> r0 = float[].class
            int r0 = a(r0)
            long r2 = (long) r0
            p = r2
            java.lang.Class<float[]> r0 = float[].class
            int r0 = b(r0)
            long r2 = (long) r0
            q = r2
            java.lang.Class<double[]> r0 = double[].class
            int r0 = a(r0)
            long r2 = (long) r0
            r = r2
            java.lang.Class<double[]> r0 = double[].class
            int r0 = b(r0)
            long r2 = (long) r0
            s = r2
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = a(r0)
            long r2 = (long) r0
            t = r2
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = b(r0)
            long r2 = (long) r0
            u = r2
            java.lang.reflect.Field r0 = g()
            long r2 = b(r0)
            v = r2
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            java.lang.String r2 = "value"
            java.lang.reflect.Field r0 = a(r0, r2)
            if (r0 == 0) goto L_0x00f4
            java.lang.Class r2 = r0.getType()
            java.lang.Class<char[]> r3 = char[].class
            if (r2 != r3) goto L_0x00f4
            goto L_0x00f5
        L_0x00f4:
            r0 = r1
        L_0x00f5:
            long r0 = b(r0)
            w = r0
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x0105
            r0 = 1
            goto L_0x0106
        L_0x0105:
            r0 = 0
        L_0x0106:
            x = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.aab.<clinit>():void");
    }

    private aab() {
    }

    static byte a(byte[] bArr, long j2) {
        return f.a(bArr, i + j2);
    }

    private static int a(Class<?> cls) {
        if (h) {
            return f.a.arrayBaseOffset(cls);
        }
        return -1;
    }

    static int a(Object obj, long j2) {
        return f.e(obj, j2);
    }

    static long a(Field field) {
        return f.a(field);
    }

    private static Field a(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    static void a(Object obj, long j2, double d2) {
        f.a(obj, j2, d2);
    }

    static void a(Object obj, long j2, float f2) {
        f.a(obj, j2, f2);
    }

    static void a(Object obj, long j2, int i2) {
        f.a(obj, j2, i2);
    }

    static void a(Object obj, long j2, long j3) {
        f.a(obj, j2, j3);
    }

    static void a(Object obj, long j2, Object obj2) {
        f.a.putObject(obj, j2, obj2);
    }

    static void a(Object obj, long j2, boolean z) {
        f.a(obj, j2, z);
    }

    static void a(byte[] bArr, long j2, byte b2) {
        f.a((Object) bArr, i + j2, b2);
    }

    static boolean a() {
        return h;
    }

    private static int b(Class<?> cls) {
        if (h) {
            return f.a.arrayIndexScale(cls);
        }
        return -1;
    }

    static long b(Object obj, long j2) {
        return f.f(obj, j2);
    }

    private static long b(Field field) {
        if (field == null || f == null) {
            return -1;
        }
        return f.a(field);
    }

    static boolean b() {
        return g;
    }

    static Unsafe c() {
        try {
            return (Unsafe) AccessController.doPrivileged(new aac());
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void c(Object obj, long j2, byte b2) {
        long j3 = j2 & -4;
        int i2 = ((((int) j2) ^ -1) & 3) << 3;
        a(obj, j3, ((255 & b2) << i2) | (a(obj, j3) & ((255 << i2) ^ -1)));
    }

    private static boolean c(Class<?> cls) {
        if (!wb.a()) {
            return false;
        }
        try {
            Class<?> cls2 = c;
            cls2.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls2.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls2.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls2.getMethod("peekByte", new Class[]{cls});
            cls2.getMethod("pokeByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            cls2.getMethod("peekByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    static boolean c(Object obj, long j2) {
        return f.b(obj, j2);
    }

    static float d(Object obj, long j2) {
        return f.c(obj, j2);
    }

    /* access modifiers changed from: private */
    public static void d(Object obj, long j2, byte b2) {
        long j3 = j2 & -4;
        int i2 = (((int) j2) & 3) << 3;
        a(obj, j3, ((255 & b2) << i2) | (a(obj, j3) & ((255 << i2) ^ -1)));
    }

    /* access modifiers changed from: private */
    public static void d(Object obj, long j2, boolean z) {
        c(obj, j2, z ? (byte) 1 : 0);
    }

    static double e(Object obj, long j2) {
        return f.d(obj, j2);
    }

    /* access modifiers changed from: private */
    public static void e(Object obj, long j2, boolean z) {
        d(obj, j2, z ? (byte) 1 : 0);
    }

    private static boolean e() {
        if (b == null) {
            return false;
        }
        try {
            Class cls = b.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (wb.a()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getBoolean", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
            cls.getMethod("getFloat", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putFloat", new Class[]{Object.class, Long.TYPE, Float.TYPE});
            cls.getMethod("getDouble", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putDouble", new Class[]{Object.class, Long.TYPE, Double.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(71 + String.valueOf(valueOf).length());
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            a.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    static Object f(Object obj, long j2) {
        return f.a.getObject(obj, j2);
    }

    private static boolean f() {
        if (b == null) {
            return false;
        }
        try {
            Class cls = b.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (g() == null) {
                return false;
            }
            if (wb.a()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getInt", new Class[]{Long.TYPE});
            cls.getMethod("putInt", new Class[]{Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Long.TYPE});
            cls.getMethod("putLong", new Class[]{Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(71 + String.valueOf(valueOf).length());
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            a.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    private static Field g() {
        if (wb.a()) {
            Field a2 = a(Buffer.class, "effectiveDirectAddress");
            if (a2 != null) {
                return a2;
            }
        }
        Field a3 = a(Buffer.class, ResponseConstants.ADDRESS);
        if (a3 == null || a3.getType() != Long.TYPE) {
            return null;
        }
        return a3;
    }

    /* access modifiers changed from: private */
    public static byte k(Object obj, long j2) {
        return (byte) (a(obj, j2 & -4) >>> ((int) (((j2 ^ -1) & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static byte l(Object obj, long j2) {
        return (byte) (a(obj, j2 & -4) >>> ((int) ((j2 & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static boolean m(Object obj, long j2) {
        return k(obj, j2) != 0;
    }

    /* access modifiers changed from: private */
    public static boolean n(Object obj, long j2) {
        return l(obj, j2) != 0;
    }
}
