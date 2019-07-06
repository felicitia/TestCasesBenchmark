package com.google.android.gms.internal.firebase-perf;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

final class zzfh {
    private static final Logger logger = Logger.getLogger(zzfh.class.getName());
    private static final Class<?> zzhk = zzba.zzbl();
    private static final boolean zzik = zzfy();
    private static final Unsafe zzoo = zzfx();
    private static final boolean zzql = zzj(Long.TYPE);
    private static final boolean zzqm = zzj(Integer.TYPE);
    private static final zzd zzqn;
    private static final boolean zzqo = zzfz();
    /* access modifiers changed from: private */
    public static final long zzqp = ((long) zzh(byte[].class));
    private static final long zzqq = ((long) zzh(boolean[].class));
    private static final long zzqr = ((long) zzi(boolean[].class));
    private static final long zzqs = ((long) zzh(int[].class));
    private static final long zzqt = ((long) zzi(int[].class));
    private static final long zzqu = ((long) zzh(long[].class));
    private static final long zzqv = ((long) zzi(long[].class));
    private static final long zzqw = ((long) zzh(float[].class));
    private static final long zzqx = ((long) zzi(float[].class));
    private static final long zzqy = ((long) zzh(double[].class));
    private static final long zzqz = ((long) zzi(double[].class));
    private static final long zzra = ((long) zzh(Object[].class));
    private static final long zzrb = ((long) zzi(Object[].class));
    private static final long zzrc;
    /* access modifiers changed from: private */
    public static final boolean zzrd = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(long j, byte b) {
            Memory.pokeByte((int) (j & -1), b);
        }

        public final byte zzy(Object obj, long j) {
            if (zzfh.zzrd) {
                return zzfh.zzq(obj, j);
            }
            return zzfh.zzr(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzfh.zzrd) {
                zzfh.zza(obj, j, b);
            } else {
                zzfh.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zzfh.zzrd) {
                return zzfh.zzs(obj, j);
            }
            return zzfh.zzt(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzfh.zzrd) {
                zzfh.zzb(obj, j, z);
            } else {
                zzfh.zzc(obj, j, z);
            }
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(byte[] bArr, long j, long j2, long j3) {
            Memory.pokeByteArray((int) (j2 & -1), bArr, (int) j, (int) j3);
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(long j, byte b) {
            Memory.pokeByte(j, b);
        }

        public final byte zzy(Object obj, long j) {
            if (zzfh.zzrd) {
                return zzfh.zzq(obj, j);
            }
            return zzfh.zzr(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzfh.zzrd) {
                zzfh.zza(obj, j, b);
            } else {
                zzfh.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zzfh.zzrd) {
                return zzfh.zzs(obj, j);
            }
            return zzfh.zzt(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzfh.zzrd) {
                zzfh.zzb(obj, j, z);
            } else {
                zzfh.zzc(obj, j, z);
            }
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(byte[] bArr, long j, long j2, long j3) {
            Memory.pokeByteArray(j2, bArr, (int) j, (int) j3);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(long j, byte b) {
            this.zzre.putByte(j, b);
        }

        public final byte zzy(Object obj, long j) {
            return this.zzre.getByte(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            this.zzre.putByte(obj, j, b);
        }

        public final boolean zzm(Object obj, long j) {
            return this.zzre.getBoolean(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            this.zzre.putBoolean(obj, j, z);
        }

        public final float zzn(Object obj, long j) {
            return this.zzre.getFloat(obj, j);
        }

        public final void zza(Object obj, long j, float f) {
            this.zzre.putFloat(obj, j, f);
        }

        public final double zzo(Object obj, long j) {
            return this.zzre.getDouble(obj, j);
        }

        public final void zza(Object obj, long j, double d) {
            this.zzre.putDouble(obj, j, d);
        }

        public final void zza(byte[] bArr, long j, long j2, long j3) {
            this.zzre.copyMemory(bArr, zzfh.zzqp + j, null, j2, j3);
        }
    }

    static abstract class zzd {
        Unsafe zzre;

        zzd(Unsafe unsafe) {
            this.zzre = unsafe;
        }

        public abstract void zza(long j, byte b);

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zza(byte[] bArr, long j, long j2, long j3);

        public abstract void zze(Object obj, long j, byte b);

        public abstract boolean zzm(Object obj, long j);

        public abstract float zzn(Object obj, long j);

        public abstract double zzo(Object obj, long j);

        public abstract byte zzy(Object obj, long j);

        public final int zzk(Object obj, long j) {
            return this.zzre.getInt(obj, j);
        }

        public final void zzb(Object obj, long j, int i) {
            this.zzre.putInt(obj, j, i);
        }

        public final long zzl(Object obj, long j) {
            return this.zzre.getLong(obj, j);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zzre.putLong(obj, j, j2);
        }
    }

    private zzfh() {
    }

    static boolean zzfv() {
        return zzik;
    }

    static boolean zzfw() {
        return zzqo;
    }

    private static int zzh(Class<?> cls) {
        if (zzik) {
            return zzqn.zzre.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzi(Class<?> cls) {
        if (zzik) {
            return zzqn.zzre.arrayIndexScale(cls);
        }
        return -1;
    }

    static int zzk(Object obj, long j) {
        return zzqn.zzk(obj, j);
    }

    static void zzb(Object obj, long j, int i) {
        zzqn.zzb(obj, j, i);
    }

    static long zzl(Object obj, long j) {
        return zzqn.zzl(obj, j);
    }

    static void zza(Object obj, long j, long j2) {
        zzqn.zza(obj, j, j2);
    }

    static boolean zzm(Object obj, long j) {
        return zzqn.zzm(obj, j);
    }

    static void zza(Object obj, long j, boolean z) {
        zzqn.zza(obj, j, z);
    }

    static float zzn(Object obj, long j) {
        return zzqn.zzn(obj, j);
    }

    static void zza(Object obj, long j, float f) {
        zzqn.zza(obj, j, f);
    }

    static double zzo(Object obj, long j) {
        return zzqn.zzo(obj, j);
    }

    static void zza(Object obj, long j, double d) {
        zzqn.zza(obj, j, d);
    }

    static Object zzp(Object obj, long j) {
        return zzqn.zzre.getObject(obj, j);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzqn.zzre.putObject(obj, j, obj2);
    }

    static byte zza(byte[] bArr, long j) {
        return zzqn.zzy(bArr, zzqp + j);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzqn.zze(bArr, zzqp + j, b);
    }

    static void zza(byte[] bArr, long j, long j2, long j3) {
        zzqn.zza(bArr, j, j2, j3);
    }

    static void zza(long j, byte b) {
        zzqn.zza(j, b);
    }

    static long zzb(ByteBuffer byteBuffer) {
        return zzqn.zzl(byteBuffer, zzrc);
    }

    static Unsafe zzfx() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzfi());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzfy() {
        if (zzoo == null) {
            return false;
        }
        try {
            Class cls = zzoo.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zzba.zzbk()) {
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

    private static boolean zzfz() {
        if (zzoo == null) {
            return false;
        }
        try {
            Class cls = zzoo.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzga() == null) {
                return false;
            }
            if (zzba.zzbk()) {
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

    private static boolean zzj(Class<?> cls) {
        if (!zzba.zzbk()) {
            return false;
        }
        try {
            Class<?> cls2 = zzhk;
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

    private static Field zzga() {
        if (zzba.zzbk()) {
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
    public static byte zzq(Object obj, long j) {
        return (byte) (zzk(obj, j & -4) >>> ((int) (((j ^ -1) & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static byte zzr(Object obj, long j) {
        return (byte) (zzk(obj, j & -4) >>> ((int) ((j & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static void zza(Object obj, long j, byte b) {
        long j2 = j & -4;
        int i = ((((int) j) ^ -1) & 3) << 3;
        zzb(obj, j2, ((255 & b) << i) | (zzk(obj, j2) & ((255 << i) ^ -1)));
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b) {
        long j2 = j & -4;
        int i = (((int) j) & 3) << 3;
        zzb(obj, j2, ((255 & b) << i) | (zzk(obj, j2) & ((255 << i) ^ -1)));
    }

    /* access modifiers changed from: private */
    public static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static boolean zzt(Object obj, long j) {
        return zzr(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, boolean z) {
        zza(obj, j, z ? (byte) 1 : 0);
    }

    /* access modifiers changed from: private */
    public static void zzc(Object obj, long j, boolean z) {
        zzb(obj, j, z ? (byte) 1 : 0);
    }

    static {
        zzd zzd2 = null;
        if (zzoo != null) {
            if (!zzba.zzbk()) {
                zzd2 = new zzc(zzoo);
            } else if (zzql) {
                zzd2 = new zzb(zzoo);
            } else if (zzqm) {
                zzd2 = new zza(zzoo);
            }
        }
        zzqn = zzd2;
        Field zzga = zzga();
        zzrc = (zzga == null || zzqn == null) ? -1 : zzqn.zzre.objectFieldOffset(zzga);
    }
}
