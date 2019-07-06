package com.google.android.gms.internal.icing;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zzfd {
    private static final Logger logger = Logger.getLogger(zzfd.class.getName());
    private static final Class<?> zzdm = zzbf.zzs();
    private static final boolean zzeg = zzcv();
    private static final Unsafe zzjx = zzcu();
    private static final boolean zzlt = zzi(Long.TYPE);
    private static final boolean zzlu = zzi(Integer.TYPE);
    private static final zzd zzlv;
    private static final boolean zzlw = zzcw();
    private static final long zzlx = ((long) zzg(byte[].class));
    private static final long zzly = ((long) zzg(boolean[].class));
    private static final long zzlz = ((long) zzh(boolean[].class));
    private static final long zzma = ((long) zzg(int[].class));
    private static final long zzmb = ((long) zzh(int[].class));
    private static final long zzmc = ((long) zzg(long[].class));
    private static final long zzmd = ((long) zzh(long[].class));
    private static final long zzme = ((long) zzg(float[].class));
    private static final long zzmf = ((long) zzh(float[].class));
    private static final long zzmg = ((long) zzg(double[].class));
    private static final long zzmh = ((long) zzh(double[].class));
    private static final long zzmi = ((long) zzg(Object[].class));
    private static final long zzmj = ((long) zzh(Object[].class));
    private static final long zzmk = zza(zzcx());
    private static final long zzml;
    /* access modifiers changed from: private */
    public static final boolean zzmm = (ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN);

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzfd.zzmm) {
                zzfd.zzb(obj, j, z);
            } else {
                zzfd.zzc(obj, j, z);
            }
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzfd.zzmm) {
                zzfd.zza(obj, j, b);
            } else {
                zzfd.zzb(obj, j, b);
            }
        }

        public final boolean zzl(Object obj, long j) {
            return zzfd.zzmm ? zzfd.zzr(obj, j) : zzfd.zzs(obj, j);
        }

        public final float zzm(Object obj, long j) {
            return Float.intBitsToFloat(zzj(obj, j));
        }

        public final double zzn(Object obj, long j) {
            return Double.longBitsToDouble(zzk(obj, j));
        }

        public final byte zzx(Object obj, long j) {
            return zzfd.zzmm ? zzfd.zzp(obj, j) : zzfd.zzq(obj, j);
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzfd.zzmm) {
                zzfd.zzb(obj, j, z);
            } else {
                zzfd.zzc(obj, j, z);
            }
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzfd.zzmm) {
                zzfd.zza(obj, j, b);
            } else {
                zzfd.zzb(obj, j, b);
            }
        }

        public final boolean zzl(Object obj, long j) {
            return zzfd.zzmm ? zzfd.zzr(obj, j) : zzfd.zzs(obj, j);
        }

        public final float zzm(Object obj, long j) {
            return Float.intBitsToFloat(zzj(obj, j));
        }

        public final double zzn(Object obj, long j) {
            return Double.longBitsToDouble(zzk(obj, j));
        }

        public final byte zzx(Object obj, long j) {
            return zzfd.zzmm ? zzfd.zzp(obj, j) : zzfd.zzq(obj, j);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(Object obj, long j, double d) {
            this.zzmn.putDouble(obj, j, d);
        }

        public final void zza(Object obj, long j, float f) {
            this.zzmn.putFloat(obj, j, f);
        }

        public final void zza(Object obj, long j, boolean z) {
            this.zzmn.putBoolean(obj, j, z);
        }

        public final void zze(Object obj, long j, byte b) {
            this.zzmn.putByte(obj, j, b);
        }

        public final boolean zzl(Object obj, long j) {
            return this.zzmn.getBoolean(obj, j);
        }

        public final float zzm(Object obj, long j) {
            return this.zzmn.getFloat(obj, j);
        }

        public final double zzn(Object obj, long j) {
            return this.zzmn.getDouble(obj, j);
        }

        public final byte zzx(Object obj, long j) {
            return this.zzmn.getByte(obj, j);
        }
    }

    static abstract class zzd {
        Unsafe zzmn;

        zzd(Unsafe unsafe) {
            this.zzmn = unsafe;
        }

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public final void zza(Object obj, long j, int i) {
            this.zzmn.putInt(obj, j, i);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zzmn.putLong(obj, j, j2);
        }

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zze(Object obj, long j, byte b);

        public final int zzj(Object obj, long j) {
            return this.zzmn.getInt(obj, j);
        }

        public final long zzk(Object obj, long j) {
            return this.zzmn.getLong(obj, j);
        }

        public abstract boolean zzl(Object obj, long j);

        public abstract float zzm(Object obj, long j);

        public abstract double zzn(Object obj, long j);

        public abstract byte zzx(Object obj, long j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0105  */
    static {
        /*
            java.lang.Class<com.google.android.gms.internal.icing.zzfd> r0 = com.google.android.gms.internal.icing.zzfd.class
            java.lang.String r0 = r0.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            logger = r0
            sun.misc.Unsafe r0 = zzcu()
            zzjx = r0
            java.lang.Class r0 = com.google.android.gms.internal.icing.zzbf.zzs()
            zzdm = r0
            java.lang.Class r0 = java.lang.Long.TYPE
            boolean r0 = zzi(r0)
            zzlt = r0
            java.lang.Class r0 = java.lang.Integer.TYPE
            boolean r0 = zzi(r0)
            zzlu = r0
            sun.misc.Unsafe r0 = zzjx
            r1 = 0
            if (r0 != 0) goto L_0x002f
        L_0x002d:
            r0 = r1
            goto L_0x0054
        L_0x002f:
            boolean r0 = com.google.android.gms.internal.icing.zzbf.zzr()
            if (r0 == 0) goto L_0x004d
            boolean r0 = zzlt
            if (r0 == 0) goto L_0x0041
            com.google.android.gms.internal.icing.zzfd$zzb r0 = new com.google.android.gms.internal.icing.zzfd$zzb
            sun.misc.Unsafe r2 = zzjx
            r0.<init>(r2)
            goto L_0x0054
        L_0x0041:
            boolean r0 = zzlu
            if (r0 == 0) goto L_0x002d
            com.google.android.gms.internal.icing.zzfd$zza r0 = new com.google.android.gms.internal.icing.zzfd$zza
            sun.misc.Unsafe r2 = zzjx
            r0.<init>(r2)
            goto L_0x0054
        L_0x004d:
            com.google.android.gms.internal.icing.zzfd$zzc r0 = new com.google.android.gms.internal.icing.zzfd$zzc
            sun.misc.Unsafe r2 = zzjx
            r0.<init>(r2)
        L_0x0054:
            zzlv = r0
            boolean r0 = zzcw()
            zzlw = r0
            boolean r0 = zzcv()
            zzeg = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = zzg(r0)
            long r2 = (long) r0
            zzlx = r2
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzg(r0)
            long r2 = (long) r0
            zzly = r2
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzh(r0)
            long r2 = (long) r0
            zzlz = r2
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzg(r0)
            long r2 = (long) r0
            zzma = r2
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzh(r0)
            long r2 = (long) r0
            zzmb = r2
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzg(r0)
            long r2 = (long) r0
            zzmc = r2
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzh(r0)
            long r2 = (long) r0
            zzmd = r2
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzg(r0)
            long r2 = (long) r0
            zzme = r2
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzh(r0)
            long r2 = (long) r0
            zzmf = r2
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzg(r0)
            long r2 = (long) r0
            zzmg = r2
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzh(r0)
            long r2 = (long) r0
            zzmh = r2
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzg(r0)
            long r2 = (long) r0
            zzmi = r2
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzh(r0)
            long r2 = (long) r0
            zzmj = r2
            java.lang.reflect.Field r0 = zzcx()
            long r2 = zza(r0)
            zzmk = r2
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            java.lang.String r2 = "value"
            java.lang.reflect.Field r0 = zzb(r0, r2)
            if (r0 == 0) goto L_0x00f4
            java.lang.Class r2 = r0.getType()
            java.lang.Class<char[]> r3 = char[].class
            if (r2 != r3) goto L_0x00f4
            goto L_0x00f5
        L_0x00f4:
            r0 = r1
        L_0x00f5:
            long r0 = zza(r0)
            zzml = r0
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x0105
            r0 = 1
            goto L_0x0106
        L_0x0105:
            r0 = 0
        L_0x0106:
            zzmm = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzfd.<clinit>():void");
    }

    private zzfd() {
    }

    static byte zza(byte[] bArr, long j) {
        return zzlv.zzx(bArr, zzlx + j);
    }

    private static long zza(Field field) {
        if (field == null || zzlv == null) {
            return -1;
        }
        return zzlv.zzmn.objectFieldOffset(field);
    }

    /* access modifiers changed from: private */
    public static void zza(Object obj, long j, byte b) {
        long j2 = j & -4;
        int i = ((((int) j) ^ -1) & 3) << 3;
        zza(obj, j2, ((255 & b) << i) | (zzj(obj, j2) & ((255 << i) ^ -1)));
    }

    static void zza(Object obj, long j, double d) {
        zzlv.zza(obj, j, d);
    }

    static void zza(Object obj, long j, float f) {
        zzlv.zza(obj, j, f);
    }

    static void zza(Object obj, long j, int i) {
        zzlv.zza(obj, j, i);
    }

    static void zza(Object obj, long j, long j2) {
        zzlv.zza(obj, j, j2);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzlv.zzmn.putObject(obj, j, obj2);
    }

    static void zza(Object obj, long j, boolean z) {
        zzlv.zza(obj, j, z);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzlv.zze(bArr, zzlx + j, b);
    }

    private static Field zzb(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b) {
        long j2 = j & -4;
        int i = (((int) j) & 3) << 3;
        zza(obj, j2, ((255 & b) << i) | (zzj(obj, j2) & ((255 << i) ^ -1)));
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, boolean z) {
        zza(obj, j, z ? (byte) 1 : 0);
    }

    /* access modifiers changed from: private */
    public static void zzc(Object obj, long j, boolean z) {
        zzb(obj, j, z ? (byte) 1 : 0);
    }

    static boolean zzcs() {
        return zzeg;
    }

    static boolean zzct() {
        return zzlw;
    }

    static Unsafe zzcu() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzfe());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzcv() {
        if (zzjx == null) {
            return false;
        }
        try {
            Class cls = zzjx.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zzbf.zzr()) {
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
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzcw() {
        if (zzjx == null) {
            return false;
        }
        try {
            Class cls = zzjx.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzcx() == null) {
                return false;
            }
            if (zzbf.zzr()) {
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
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    private static Field zzcx() {
        if (zzbf.zzr()) {
            Field zzb2 = zzb(Buffer.class, "effectiveDirectAddress");
            if (zzb2 != null) {
                return zzb2;
            }
        }
        Field zzb3 = zzb(Buffer.class, "address");
        if (zzb3 == null || zzb3.getType() != Long.TYPE) {
            return null;
        }
        return zzb3;
    }

    private static int zzg(Class<?> cls) {
        if (zzeg) {
            return zzlv.zzmn.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzh(Class<?> cls) {
        if (zzeg) {
            return zzlv.zzmn.arrayIndexScale(cls);
        }
        return -1;
    }

    private static boolean zzi(Class<?> cls) {
        if (!zzbf.zzr()) {
            return false;
        }
        try {
            Class<?> cls2 = zzdm;
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

    static int zzj(Object obj, long j) {
        return zzlv.zzj(obj, j);
    }

    static long zzk(Object obj, long j) {
        return zzlv.zzk(obj, j);
    }

    static boolean zzl(Object obj, long j) {
        return zzlv.zzl(obj, j);
    }

    static float zzm(Object obj, long j) {
        return zzlv.zzm(obj, j);
    }

    static double zzn(Object obj, long j) {
        return zzlv.zzn(obj, j);
    }

    static Object zzo(Object obj, long j) {
        return zzlv.zzmn.getObject(obj, j);
    }

    /* access modifiers changed from: private */
    public static byte zzp(Object obj, long j) {
        return (byte) (zzj(obj, j & -4) >>> ((int) (((j ^ -1) & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static byte zzq(Object obj, long j) {
        return (byte) (zzj(obj, j & -4) >>> ((int) ((j & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static boolean zzr(Object obj, long j) {
        return zzp(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != 0;
    }
}
