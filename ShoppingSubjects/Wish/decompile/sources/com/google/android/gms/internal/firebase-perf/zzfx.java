package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;

public final class zzfx {
    private final byte[] buffer;
    private int zzhx;
    private int zzhy = 64;
    private int zzhz = 67108864;
    private int zzic;
    private int zzie;
    private int zzif = Integer.MAX_VALUE;
    private final int zzsl;
    private final int zzsm;
    private int zzsn;
    private int zzso;
    private zzbo zzsp;

    public static zzfx zzj(byte[] bArr, int i, int i2) {
        return new zzfx(bArr, 0, i2);
    }

    public final int zzbs() throws IOException {
        if (this.zzso == this.zzsn) {
            this.zzie = 0;
            return 0;
        }
        this.zzie = zzck();
        if (this.zzie != 0) {
            return this.zzie;
        }
        throw new zzgf("Protocol message contained an invalid tag (zero).");
    }

    public final void zzl(int i) throws zzgf {
        if (this.zzie != i) {
            throw new zzgf("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final boolean zzm(int i) throws IOException {
        int zzbs;
        switch (i & 7) {
            case 0:
                zzck();
                return true;
            case 1:
                zzcp();
                zzcp();
                zzcp();
                zzcp();
                zzcp();
                zzcp();
                zzcp();
                zzcp();
                return true;
            case 2:
                zzq(zzck());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzcp();
                zzcp();
                zzcp();
                zzcp();
                return true;
            default:
                throw new zzgf("Protocol message tag had invalid wire type.");
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

    public final String readString() throws IOException {
        int zzck = zzck();
        if (zzck < 0) {
            throw zzgf.zzgj();
        } else if (zzck > this.zzsn - this.zzso) {
            throw zzgf.zzgi();
        } else {
            String str = new String(this.buffer, this.zzso, zzck, zzge.UTF_8);
            this.zzso += zzck;
            return str;
        }
    }

    public final void zza(zzgg zzgg) throws IOException {
        int zzck = zzck();
        if (this.zzhx >= this.zzhy) {
            throw new zzgf("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int zzo = zzo(zzck);
        this.zzhx++;
        zzgg.zza(this);
        zzl(0);
        this.zzhx--;
        zzp(zzo);
    }

    public final int zzck() throws IOException {
        byte b;
        byte zzcp = zzcp();
        if (zzcp >= 0) {
            return zzcp;
        }
        byte b2 = zzcp & Byte.MAX_VALUE;
        byte zzcp2 = zzcp();
        if (zzcp2 >= 0) {
            b = b2 | (zzcp2 << 7);
        } else {
            byte b3 = b2 | ((zzcp2 & Byte.MAX_VALUE) << 7);
            byte zzcp3 = zzcp();
            if (zzcp3 >= 0) {
                b = b3 | (zzcp3 << 14);
            } else {
                byte b4 = b3 | ((zzcp3 & Byte.MAX_VALUE) << 14);
                byte zzcp4 = zzcp();
                if (zzcp4 >= 0) {
                    b = b4 | (zzcp4 << 21);
                } else {
                    byte b5 = b4 | ((zzcp4 & Byte.MAX_VALUE) << 21);
                    byte zzcp5 = zzcp();
                    b = b5 | (zzcp5 << 28);
                    if (zzcp5 < 0) {
                        for (int i = 0; i < 5; i++) {
                            if (zzcp() >= 0) {
                                return b;
                            }
                        }
                        throw zzgf.zzgk();
                    }
                }
            }
        }
        return b;
    }

    public final long zzcl() throws IOException {
        int i = 0;
        long j = 0;
        while (i < 64) {
            byte zzcp = zzcp();
            long j2 = j | (((long) (zzcp & Byte.MAX_VALUE)) << i);
            if ((zzcp & 128) == 0) {
                return j2;
            }
            i += 7;
            j = j2;
        }
        throw zzgf.zzgk();
    }

    private zzfx(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzsl = i;
        int i3 = i2 + i;
        this.zzsn = i3;
        this.zzsm = i3;
        this.zzso = i;
    }

    public final <T extends zzcm<T, ?>> T zza(zzed<T> zzed) throws IOException {
        try {
            if (this.zzsp == null) {
                this.zzsp = zzbo.zzd(this.buffer, this.zzsl, this.zzsm);
            }
            int zzcj = this.zzsp.zzcj();
            int i = this.zzso - this.zzsl;
            if (zzcj > i) {
                throw new IOException(String.format("CodedInputStream read ahead of CodedInputByteBufferNano: %s > %s", new Object[]{Integer.valueOf(zzcj), Integer.valueOf(i)}));
            }
            this.zzsp.zzq(i - zzcj);
            this.zzsp.zzn(this.zzhy - this.zzhx);
            T t = (zzcm) this.zzsp.zza(zzed, zzbz.zzdb());
            zzm(this.zzie);
            return t;
        } catch (zzct e) {
            throw new zzgf("", e);
        }
    }

    public final int zzo(int i) throws zzgf {
        if (i < 0) {
            throw zzgf.zzgj();
        }
        int i2 = i + this.zzso;
        int i3 = this.zzif;
        if (i2 > i3) {
            throw zzgf.zzgi();
        }
        this.zzif = i2;
        zzco();
        return i3;
    }

    private final void zzco() {
        this.zzsn += this.zzic;
        int i = this.zzsn;
        if (i > this.zzif) {
            this.zzic = i - this.zzif;
            this.zzsn -= this.zzic;
            return;
        }
        this.zzic = 0;
    }

    public final void zzp(int i) {
        this.zzif = i;
        zzco();
    }

    public final int zzge() {
        if (this.zzif == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzif - this.zzso;
    }

    public final int getPosition() {
        return this.zzso - this.zzsl;
    }

    public final byte[] zzr(int i, int i2) {
        if (i2 == 0) {
            return zzgj.zztl;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.zzsl + i, bArr, 0, i2);
        return bArr;
    }

    public final void zzax(int i) {
        zzs(i, this.zzie);
    }

    /* access modifiers changed from: 0000 */
    public final void zzs(int i, int i2) {
        if (i > this.zzso - this.zzsl) {
            int i3 = this.zzso - this.zzsl;
            StringBuilder sb = new StringBuilder(50);
            sb.append("Position ");
            sb.append(i);
            sb.append(" is beyond current ");
            sb.append(i3);
            throw new IllegalArgumentException(sb.toString());
        } else if (i < 0) {
            StringBuilder sb2 = new StringBuilder(24);
            sb2.append("Bad position ");
            sb2.append(i);
            throw new IllegalArgumentException(sb2.toString());
        } else {
            this.zzso = this.zzsl + i;
            this.zzie = i2;
        }
    }

    private final byte zzcp() throws IOException {
        if (this.zzso == this.zzsn) {
            throw zzgf.zzgi();
        }
        byte[] bArr = this.buffer;
        int i = this.zzso;
        this.zzso = i + 1;
        return bArr[i];
    }

    private final void zzq(int i) throws IOException {
        if (i < 0) {
            throw zzgf.zzgj();
        } else if (this.zzso + i > this.zzif) {
            zzq(this.zzif - this.zzso);
            throw zzgf.zzgi();
        } else if (i <= this.zzsn - this.zzso) {
            this.zzso += i;
        } else {
            throw zzgf.zzgi();
        }
    }
}
