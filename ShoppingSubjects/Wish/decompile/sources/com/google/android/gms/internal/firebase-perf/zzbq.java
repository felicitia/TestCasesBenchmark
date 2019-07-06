package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;
import java.util.Arrays;

final class zzbq extends zzbo {
    private final byte[] buffer;
    private final boolean immutable;
    private int limit;
    private int pos;
    private int zzic;
    private int zzid;
    private int zzie;
    private int zzif;

    private zzbq(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzif = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzid = this.pos;
        this.immutable = z;
    }

    public final int zzbs() throws IOException {
        if (zzci()) {
            this.zzie = 0;
            return 0;
        }
        this.zzie = zzck();
        if ((this.zzie >>> 3) != 0) {
            return this.zzie;
        }
        throw new zzct("Protocol message contained an invalid tag (zero).");
    }

    public final void zzl(int i) throws zzct {
        if (this.zzie != i) {
            throw zzct.zzea();
        }
    }

    public final boolean zzm(int i) throws IOException {
        int zzbs;
        int i2 = 0;
        switch (i & 7) {
            case 0:
                if (this.limit - this.pos >= 10) {
                    while (i2 < 10) {
                        byte[] bArr = this.buffer;
                        int i3 = this.pos;
                        this.pos = i3 + 1;
                        if (bArr[i3] < 0) {
                            i2++;
                        }
                    }
                    throw zzct.zzdz();
                }
                while (i2 < 10) {
                    if (zzcp() < 0) {
                        i2++;
                    }
                }
                throw zzct.zzdz();
                return true;
            case 1:
                zzq(8);
                return true;
            case 2:
                zzq(zzck());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzq(4);
                return true;
            default:
                throw zzct.zzeb();
        }
        do {
            zzbs = zzbs();
            if (zzbs != 0) {
            }
            zzl(((i >>> 3) << 3) | 4);
            return true;
        } while (zzm(zzbs));
        zzl(((i >>> 3) << 3) | 4);
        return true;
    }

    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zzcn());
    }

    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zzcm());
    }

    public final long zzbt() throws IOException {
        return zzcl();
    }

    public final long zzbu() throws IOException {
        return zzcl();
    }

    public final int zzbv() throws IOException {
        return zzck();
    }

    public final long zzbw() throws IOException {
        return zzcn();
    }

    public final int zzbx() throws IOException {
        return zzcm();
    }

    public final boolean zzby() throws IOException {
        return zzcl() != 0;
    }

    public final String readString() throws IOException {
        int zzck = zzck();
        if (zzck > 0 && zzck <= this.limit - this.pos) {
            String str = new String(this.buffer, this.pos, zzck, zzco.UTF_8);
            this.pos += zzck;
            return str;
        } else if (zzck == 0) {
            return "";
        } else {
            if (zzck < 0) {
                throw zzct.zzdy();
            }
            throw zzct.zzdx();
        }
    }

    public final String zzbz() throws IOException {
        int zzck = zzck();
        if (zzck > 0 && zzck <= this.limit - this.pos) {
            String zzh = zzfj.zzh(this.buffer, this.pos, zzck);
            this.pos += zzck;
            return zzh;
        } else if (zzck == 0) {
            return "";
        } else {
            if (zzck <= 0) {
                throw zzct.zzdy();
            }
            throw zzct.zzdx();
        }
    }

    public final <T extends zzdt> T zza(zzed<T> zzed, zzbz zzbz) throws IOException {
        int zzck = zzck();
        if (this.zzhx >= this.zzhy) {
            throw zzct.zzec();
        }
        int zzo = zzo(zzck);
        this.zzhx++;
        T t = (zzdt) zzed.zza(this, zzbz);
        zzl(0);
        this.zzhx--;
        zzp(zzo);
        return t;
    }

    public final zzbd zzca() throws IOException {
        byte[] bArr;
        int zzck = zzck();
        if (zzck > 0 && zzck <= this.limit - this.pos) {
            zzbd zzb = zzbd.zzb(this.buffer, this.pos, zzck);
            this.pos += zzck;
            return zzb;
        } else if (zzck == 0) {
            return zzbd.zzho;
        } else {
            if (zzck > 0 && zzck <= this.limit - this.pos) {
                int i = this.pos;
                this.pos += zzck;
                bArr = Arrays.copyOfRange(this.buffer, i, this.pos);
            } else if (zzck > 0) {
                throw zzct.zzdx();
            } else if (zzck == 0) {
                bArr = zzco.EMPTY_BYTE_ARRAY;
            } else {
                throw zzct.zzdy();
            }
            return zzbd.zzb(bArr);
        }
    }

    public final int zzcb() throws IOException {
        return zzck();
    }

    public final int zzcc() throws IOException {
        return zzck();
    }

    public final int zzcd() throws IOException {
        return zzcm();
    }

    public final long zzce() throws IOException {
        return zzcn();
    }

    public final int zzcf() throws IOException {
        int zzck = zzck();
        return (-(zzck & 1)) ^ (zzck >>> 1);
    }

    public final long zzcg() throws IOException {
        long zzcl = zzcl();
        return (zzcl >>> 1) ^ (-(zzcl & 1));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0068, code lost:
        if (r1[r2] >= 0) goto L_0x006a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzck() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.pos
            int r1 = r5.limit
            if (r1 == r0) goto L_0x006d
            byte[] r1 = r5.buffer
            int r2 = r0 + 1
            byte r0 = r1[r0]
            if (r0 < 0) goto L_0x0011
            r5.pos = r2
            return r0
        L_0x0011:
            int r3 = r5.limit
            int r3 = r3 - r2
            r4 = 9
            if (r3 < r4) goto L_0x006d
            int r3 = r2 + 1
            byte r2 = r1[r2]
            int r2 = r2 << 7
            r0 = r0 ^ r2
            if (r0 >= 0) goto L_0x0024
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
            goto L_0x006a
        L_0x0024:
            int r2 = r3 + 1
            byte r3 = r1[r3]
            int r3 = r3 << 14
            r0 = r0 ^ r3
            if (r0 < 0) goto L_0x0031
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L_0x002f:
            r3 = r2
            goto L_0x006a
        L_0x0031:
            int r3 = r2 + 1
            byte r2 = r1[r2]
            int r2 = r2 << 21
            r0 = r0 ^ r2
            if (r0 >= 0) goto L_0x003f
            r1 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r1
            goto L_0x006a
        L_0x003f:
            int r2 = r3 + 1
            byte r3 = r1[r3]
            int r4 = r3 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r3 >= 0) goto L_0x002f
            int r3 = r2 + 1
            byte r2 = r1[r2]
            if (r2 >= 0) goto L_0x006a
            int r2 = r3 + 1
            byte r3 = r1[r3]
            if (r3 >= 0) goto L_0x002f
            int r3 = r2 + 1
            byte r2 = r1[r2]
            if (r2 >= 0) goto L_0x006a
            int r2 = r3 + 1
            byte r3 = r1[r3]
            if (r3 >= 0) goto L_0x002f
            int r3 = r2 + 1
            byte r1 = r1[r2]
            if (r1 < 0) goto L_0x006d
        L_0x006a:
            r5.pos = r3
            return r0
        L_0x006d:
            long r0 = r5.zzch()
            int r0 = (int) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzbq.zzck():int");
    }

    private final long zzcl() throws IOException {
        long j;
        int i;
        long j2;
        long j3;
        long j4;
        int i2 = this.pos;
        if (this.limit != i2) {
            byte[] bArr = this.buffer;
            int i3 = i2 + 1;
            byte b = bArr[i2];
            if (b >= 0) {
                this.pos = i3;
                return (long) b;
            } else if (this.limit - i3 >= 9) {
                int i4 = i3 + 1;
                byte b2 = b ^ (bArr[i3] << 7);
                if (b2 < 0) {
                    j3 = (long) (b2 ^ Byte.MIN_VALUE);
                } else {
                    int i5 = i4 + 1;
                    byte b3 = b2 ^ (bArr[i4] << 14);
                    if (b3 >= 0) {
                        j4 = (long) (b3 ^ 16256);
                        i = i5;
                        j = j4;
                        this.pos = i;
                        return j;
                    }
                    i4 = i5 + 1;
                    byte b4 = b3 ^ (bArr[i5] << 21);
                    if (b4 < 0) {
                        j3 = (long) (b4 ^ -2080896);
                    } else {
                        long j5 = (long) b4;
                        int i6 = i4 + 1;
                        long j6 = j5 ^ (((long) bArr[i4]) << 28);
                        if (j6 >= 0) {
                            j2 = j6 ^ 266354560;
                        } else {
                            int i7 = i6 + 1;
                            long j7 = j6 ^ (((long) bArr[i6]) << 35);
                            if (j7 < 0) {
                                j = j7 ^ -34093383808L;
                            } else {
                                i6 = i7 + 1;
                                long j8 = j7 ^ (((long) bArr[i7]) << 42);
                                if (j8 >= 0) {
                                    j2 = j8 ^ 4363953127296L;
                                } else {
                                    i7 = i6 + 1;
                                    long j9 = j8 ^ (((long) bArr[i6]) << 49);
                                    if (j9 < 0) {
                                        j = j9 ^ -558586000294016L;
                                    } else {
                                        int i8 = i7 + 1;
                                        long j10 = (j9 ^ (((long) bArr[i7]) << 56)) ^ 71499008037633920L;
                                        if (j10 < 0) {
                                            int i9 = i8 + 1;
                                            if (((long) bArr[i8]) >= 0) {
                                                i8 = i9;
                                            }
                                        }
                                        j = j10;
                                        this.pos = i;
                                        return j;
                                    }
                                }
                            }
                            i = i7;
                            this.pos = i;
                            return j;
                        }
                        j = j2;
                        this.pos = i;
                        return j;
                    }
                }
                j4 = j3;
                i = i4;
                j = j4;
                this.pos = i;
                return j;
            }
        }
        return zzch();
    }

    /* access modifiers changed from: 0000 */
    public final long zzch() throws IOException {
        long j = 0;
        int i = 0;
        while (i < 64) {
            byte zzcp = zzcp();
            long j2 = j | (((long) (zzcp & Byte.MAX_VALUE)) << i);
            if ((zzcp & 128) == 0) {
                return j2;
            }
            i += 7;
            j = j2;
        }
        throw zzct.zzdz();
    }

    private final int zzcm() throws IOException {
        int i = this.pos;
        if (this.limit - i < 4) {
            throw zzct.zzdx();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    private final long zzcn() throws IOException {
        int i = this.pos;
        if (this.limit - i < 8) {
            throw zzct.zzdx();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    public final int zzo(int i) throws zzct {
        if (i < 0) {
            throw zzct.zzdy();
        }
        int zzcj = i + zzcj();
        int i2 = this.zzif;
        if (zzcj > i2) {
            throw zzct.zzdx();
        }
        this.zzif = zzcj;
        zzco();
        return i2;
    }

    private final void zzco() {
        this.limit += this.zzic;
        int i = this.limit - this.zzid;
        if (i > this.zzif) {
            this.zzic = i - this.zzif;
            this.limit -= this.zzic;
            return;
        }
        this.zzic = 0;
    }

    public final void zzp(int i) {
        this.zzif = i;
        zzco();
    }

    public final boolean zzci() throws IOException {
        return this.pos == this.limit;
    }

    public final int zzcj() {
        return this.pos - this.zzid;
    }

    private final byte zzcp() throws IOException {
        if (this.pos == this.limit) {
            throw zzct.zzdx();
        }
        byte[] bArr = this.buffer;
        int i = this.pos;
        this.pos = i + 1;
        return bArr[i];
    }

    public final void zzq(int i) throws IOException {
        if (i >= 0 && i <= this.limit - this.pos) {
            this.pos += i;
        } else if (i < 0) {
            throw zzct.zzdy();
        } else {
            throw zzct.zzdx();
        }
    }
}
