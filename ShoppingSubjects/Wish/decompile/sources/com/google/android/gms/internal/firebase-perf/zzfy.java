package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzfy {
    private final ByteBuffer zzip;
    private zzbt zzsq;
    private int zzsr;

    private zzfy(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    public static int zzah(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & -268435456) == 0 ? 4 : 5;
    }

    private zzfy(ByteBuffer byteBuffer) {
        this.zzip = byteBuffer;
        this.zzip.order(ByteOrder.LITTLE_ENDIAN);
    }

    public static zzfy zzg(byte[] bArr) {
        return zzk(bArr, 0, bArr.length);
    }

    public static zzfy zzk(byte[] bArr, int i, int i2) {
        return new zzfy(bArr, 0, i2);
    }

    public final void zzi(int i, long j) throws IOException {
        zzb(i, 0);
        zzw(j);
    }

    public final void zzc(int i, int i2) throws IOException {
        zzb(i, 0);
        if (i2 >= 0) {
            zzaz(i2);
        } else {
            zzw((long) i2);
        }
    }

    public final void zza(int i, String str) throws IOException {
        zzb(i, 2);
        try {
            int zzah = zzah(str.length());
            if (zzah == zzah(str.length() * 3)) {
                int position = this.zzip.position();
                if (this.zzip.remaining() < zzah) {
                    throw new zzfz(position + zzah, this.zzip.limit());
                }
                this.zzip.position(position + zzah);
                zzd((CharSequence) str, this.zzip);
                int position2 = this.zzip.position();
                this.zzip.position(position);
                zzaz((position2 - position) - zzah);
                this.zzip.position(position2);
                return;
            }
            zzaz(zza(str));
            zzd((CharSequence) str, this.zzip);
        } catch (BufferOverflowException e) {
            zzfz zzfz = new zzfz(this.zzip.position(), this.zzip.limit());
            zzfz.initCause(e);
            throw zzfz;
        }
    }

    public final void zza(int i, zzgg zzgg) throws IOException {
        zzb(i, 2);
        if (zzgg.zztb < 0) {
            zzgg.zzdg();
        }
        zzaz(zzgg.zztb);
        zzgg.zza(this);
    }

    public final void zze(int i, zzdt zzdt) throws IOException {
        if (this.zzsq == null) {
            this.zzsq = zzbt.zza(this.zzip);
            this.zzsr = this.zzip.position();
        } else if (this.zzsr != this.zzip.position()) {
            this.zzsq.write(this.zzip.array(), this.zzsr, this.zzip.position() - this.zzsr);
            this.zzsr = this.zzip.position();
        }
        zzbt zzbt = this.zzsq;
        zzbt.zza(4, zzdt);
        zzbt.flush();
        this.zzsr = this.zzip.position();
    }

    private static int zza(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                StringBuilder sb = new StringBuilder(39);
                                sb.append("Unpaired surrogate at index ");
                                sb.append(i2);
                                throw new IllegalArgumentException(sb.toString());
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        long j = ((long) i3) + 4294967296L;
        StringBuilder sb2 = new StringBuilder(54);
        sb2.append("UTF-8 length does not fit in int: ");
        sb2.append(j);
        throw new IllegalArgumentException(sb2.toString());
    }

    private static void zzd(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        int i2;
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        int i3 = 0;
        if (byteBuffer.hasArray()) {
            try {
                byte[] array = byteBuffer.array();
                int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
                int remaining = byteBuffer.remaining();
                int length = charSequence.length();
                int i4 = remaining + arrayOffset;
                while (i3 < length) {
                    int i5 = i3 + arrayOffset;
                    if (i5 >= i4) {
                        break;
                    }
                    char charAt = charSequence.charAt(i3);
                    if (charAt >= 128) {
                        break;
                    }
                    array[i5] = (byte) charAt;
                    i3++;
                }
                if (i3 == length) {
                    i = arrayOffset + length;
                } else {
                    i = arrayOffset + i3;
                    while (i3 < length) {
                        char charAt2 = charSequence.charAt(i3);
                        if (charAt2 < 128 && i < i4) {
                            i2 = i + 1;
                            array[i] = (byte) charAt2;
                        } else if (charAt2 < 2048 && i <= i4 - 2) {
                            int i6 = i + 1;
                            array[i] = (byte) ((charAt2 >>> 6) | 960);
                            i = i6 + 1;
                            array[i6] = (byte) ((charAt2 & '?') | 128);
                            i3++;
                        } else if ((charAt2 < 55296 || 57343 < charAt2) && i <= i4 - 3) {
                            int i7 = i + 1;
                            array[i] = (byte) ((charAt2 >>> 12) | 480);
                            int i8 = i7 + 1;
                            array[i7] = (byte) (((charAt2 >>> 6) & 63) | 128);
                            i2 = i8 + 1;
                            array[i8] = (byte) ((charAt2 & '?') | 128);
                        } else if (i <= i4 - 4) {
                            int i9 = i3 + 1;
                            if (i9 != charSequence.length()) {
                                char charAt3 = charSequence.charAt(i9);
                                if (!Character.isSurrogatePair(charAt2, charAt3)) {
                                    i3 = i9;
                                } else {
                                    int codePoint = Character.toCodePoint(charAt2, charAt3);
                                    int i10 = i + 1;
                                    array[i] = (byte) ((codePoint >>> 18) | 240);
                                    int i11 = i10 + 1;
                                    array[i10] = (byte) (((codePoint >>> 12) & 63) | 128);
                                    int i12 = i11 + 1;
                                    array[i11] = (byte) (((codePoint >>> 6) & 63) | 128);
                                    i = i12 + 1;
                                    array[i12] = (byte) ((codePoint & 63) | 128);
                                    i3 = i9;
                                    i3++;
                                }
                            }
                            int i13 = i3 - 1;
                            StringBuilder sb = new StringBuilder(39);
                            sb.append("Unpaired surrogate at index ");
                            sb.append(i13);
                            throw new IllegalArgumentException(sb.toString());
                        } else {
                            StringBuilder sb2 = new StringBuilder(37);
                            sb2.append("Failed writing ");
                            sb2.append(charAt2);
                            sb2.append(" at index ");
                            sb2.append(i);
                            throw new ArrayIndexOutOfBoundsException(sb2.toString());
                        }
                        i = i2;
                        i3++;
                    }
                }
                byteBuffer.position(i - byteBuffer.arrayOffset());
            } catch (ArrayIndexOutOfBoundsException e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        } else {
            int length2 = charSequence.length();
            while (i3 < length2) {
                char charAt4 = charSequence.charAt(i3);
                if (charAt4 < 128) {
                    byteBuffer.put((byte) charAt4);
                } else if (charAt4 < 2048) {
                    byteBuffer.put((byte) ((charAt4 >>> 6) | 960));
                    byteBuffer.put((byte) ((charAt4 & '?') | 128));
                } else if (charAt4 < 55296 || 57343 < charAt4) {
                    byteBuffer.put((byte) ((charAt4 >>> 12) | 480));
                    byteBuffer.put((byte) (((charAt4 >>> 6) & 63) | 128));
                    byteBuffer.put((byte) ((charAt4 & '?') | 128));
                } else {
                    int i14 = i3 + 1;
                    if (i14 != charSequence.length()) {
                        char charAt5 = charSequence.charAt(i14);
                        if (!Character.isSurrogatePair(charAt4, charAt5)) {
                            i3 = i14;
                        } else {
                            int codePoint2 = Character.toCodePoint(charAt4, charAt5);
                            byteBuffer.put((byte) ((codePoint2 >>> 18) | 240));
                            byteBuffer.put((byte) (((codePoint2 >>> 12) & 63) | 128));
                            byteBuffer.put((byte) (((codePoint2 >>> 6) & 63) | 128));
                            byteBuffer.put((byte) ((codePoint2 & 63) | 128));
                            i3 = i14;
                        }
                    }
                    int i15 = i3 - 1;
                    StringBuilder sb3 = new StringBuilder(39);
                    sb3.append("Unpaired surrogate at index ");
                    sb3.append(i15);
                    throw new IllegalArgumentException(sb3.toString());
                }
                i3++;
            }
        }
    }

    public static int zzd(int i, long j) {
        int zzz = zzz(i);
        int i2 = (j & -128) == 0 ? 1 : (j & -16384) == 0 ? 2 : (j & -2097152) == 0 ? 3 : (j & -268435456) == 0 ? 4 : (j & -34359738368L) == 0 ? 5 : (j & -4398046511104L) == 0 ? 6 : (j & -562949953421312L) == 0 ? 7 : (j & -72057594037927936L) == 0 ? 8 : (j & Long.MIN_VALUE) == 0 ? 9 : 10;
        return zzz + i2;
    }

    public static int zzg(int i, int i2) {
        return zzz(i) + zzaa(i2);
    }

    public static int zzb(int i, String str) {
        int zzz = zzz(i);
        int zza = zza(str);
        return zzz + zzah(zza) + zza;
    }

    public static int zzb(int i, zzgg zzgg) {
        int zzz = zzz(i);
        int zzdg = zzgg.zzdg();
        return zzz + zzah(zzdg) + zzdg;
    }

    public static int zzaa(int i) {
        if (i >= 0) {
            return zzah(i);
        }
        return 10;
    }

    public final void zzgf() {
        if (this.zzip.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[]{Integer.valueOf(this.zzip.remaining())}));
        }
    }

    public final void zzk(byte b) throws IOException {
        if (!this.zzip.hasRemaining()) {
            throw new zzfz(this.zzip.position(), this.zzip.limit());
        }
        this.zzip.put(b);
    }

    private final void zzay(int i) throws IOException {
        byte b = (byte) i;
        if (!this.zzip.hasRemaining()) {
            throw new zzfz(this.zzip.position(), this.zzip.limit());
        }
        this.zzip.put(b);
    }

    public final void zzh(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.zzip.remaining() >= length) {
            this.zzip.put(bArr, 0, length);
            return;
        }
        throw new zzfz(this.zzip.position(), this.zzip.limit());
    }

    public final void zzb(int i, int i2) throws IOException {
        zzaz((i << 3) | i2);
    }

    public static int zzz(int i) {
        return zzah(i << 3);
    }

    public final void zzaz(int i) throws IOException {
        while ((i & -128) != 0) {
            zzay((i & 127) | 128);
            i >>>= 7;
        }
        zzay(i);
    }

    private final void zzw(long j) throws IOException {
        while ((j & -128) != 0) {
            zzay((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzay((int) j);
    }
}
