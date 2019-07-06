package com.google.android.gms.internal.icing;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzcm {
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final byte[] zzij;
    private static final ByteBuffer zzik;
    private static final zzbr zzil;

    static {
        byte[] bArr = new byte[0];
        zzij = bArr;
        zzik = ByteBuffer.wrap(bArr);
        byte[] bArr2 = zzij;
        zzil = zzbr.zza(bArr2, 0, bArr2.length, false);
    }

    static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static int hashCode(byte[] bArr) {
        int length = bArr.length;
        int zza = zza(length, bArr, 0, length);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 = (i4 * 31) + bArr[i5];
        }
        return i4;
    }

    static Object zza(Object obj, Object obj2) {
        return ((zzdr) obj).zzaz().zza((zzdr) obj2).zzbb();
    }

    static <T> T zza(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static boolean zzd(byte[] bArr) {
        return zzff.zzd(bArr);
    }

    public static String zze(byte[] bArr) {
        return new String(bArr, UTF_8);
    }

    static boolean zzf(zzdr zzdr) {
        return false;
    }

    public static int zzg(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int zzk(long j) {
        return (int) (j ^ (j >>> 32));
    }
}
