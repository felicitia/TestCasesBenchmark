package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzaca {
    private final byte[] buffer;
    private int zzbtk = 64;
    private int zzbtl = 67108864;
    private int zzbtp;
    private int zzbtr = Integer.MAX_VALUE;
    private final int zzbyw;
    private final int zzbyx;
    private int zzbyy;
    private int zzbyz;
    private int zzbza;
    private int zzbzb;

    private zzaca(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzbyw = i;
        int i3 = i2 + i;
        this.zzbyy = i3;
        this.zzbyx = i3;
        this.zzbyz = i;
    }

    public static zzaca zza(byte[] bArr, int i, int i2) {
        return new zzaca(bArr, 0, i2);
    }

    private final void zzan(int i) throws IOException {
        if (i < 0) {
            throw zzaci.zzvx();
        } else if (this.zzbyz + i > this.zzbtr) {
            zzan(this.zzbtr - this.zzbyz);
            throw zzaci.zzvw();
        } else if (i <= this.zzbyy - this.zzbyz) {
            this.zzbyz += i;
        } else {
            throw zzaci.zzvw();
        }
    }

    public static zzaca zzi(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }

    private final void zztp() {
        this.zzbyy += this.zzbtp;
        int i = this.zzbyy;
        if (i > this.zzbtr) {
            this.zzbtp = i - this.zzbtr;
            this.zzbyy -= this.zzbtp;
            return;
        }
        this.zzbtp = 0;
    }

    private final byte zzvs() throws IOException {
        if (this.zzbyz == this.zzbyy) {
            throw zzaci.zzvw();
        }
        byte[] bArr = this.buffer;
        int i = this.zzbyz;
        this.zzbyz = i + 1;
        return bArr[i];
    }

    public final int getPosition() {
        return this.zzbyz - this.zzbyw;
    }

    public final String readString() throws IOException {
        int zzvn = zzvn();
        if (zzvn < 0) {
            throw zzaci.zzvx();
        } else if (zzvn > this.zzbyy - this.zzbyz) {
            throw zzaci.zzvw();
        } else {
            String str = new String(this.buffer, this.zzbyz, zzvn, zzach.UTF_8);
            this.zzbyz += zzvn;
            return str;
        }
    }

    public final void zza(zzacj zzacj) throws IOException {
        int zzvn = zzvn();
        if (this.zzbzb >= this.zzbtk) {
            throw zzaci.zzvz();
        }
        int zzaf = zzaf(zzvn);
        this.zzbzb++;
        zzacj.zzb(this);
        zzaj(0);
        this.zzbzb--;
        zzal(zzaf);
    }

    public final void zza(zzacj zzacj, int i) throws IOException {
        if (this.zzbzb >= this.zzbtk) {
            throw zzaci.zzvz();
        }
        this.zzbzb++;
        zzacj.zzb(this);
        zzaj((i << 3) | 4);
        this.zzbzb--;
    }

    public final int zzaf(int i) throws zzaci {
        if (i < 0) {
            throw zzaci.zzvx();
        }
        int i2 = i + this.zzbyz;
        int i3 = this.zzbtr;
        if (i2 > i3) {
            throw zzaci.zzvw();
        }
        this.zzbtr = i2;
        zztp();
        return i3;
    }

    public final void zzaj(int i) throws zzaci {
        if (this.zzbza != i) {
            throw new zzaci("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final boolean zzak(int i) throws IOException {
        int zzvl;
        switch (i & 7) {
            case 0:
                zzvn();
                return true;
            case 1:
                zzvq();
                return true;
            case 2:
                zzan(zzvn());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzvp();
                return true;
            default:
                throw new zzaci("Protocol message tag had invalid wire type.");
        }
        do {
            zzvl = zzvl();
            if (zzvl != 0) {
            }
            zzaj(((i >>> 3) << 3) | 4);
            return true;
        } while (zzak(zzvl));
        zzaj(((i >>> 3) << 3) | 4);
        return true;
    }

    public final void zzal(int i) {
        this.zzbtr = i;
        zztp();
    }

    public final void zzam(int i) {
        zzd(i, this.zzbza);
    }

    public final byte[] zzc(int i, int i2) {
        if (i2 == 0) {
            return zzacm.zzbzz;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.zzbyw + i, bArr, 0, i2);
        return bArr;
    }

    /* access modifiers changed from: 0000 */
    public final void zzd(int i, int i2) {
        if (i > this.zzbyz - this.zzbyw) {
            int i3 = this.zzbyz - this.zzbyw;
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
            this.zzbyz = this.zzbyw + i;
            this.zzbza = i2;
        }
    }

    public final int zzvl() throws IOException {
        if (this.zzbyz == this.zzbyy) {
            this.zzbza = 0;
            return 0;
        }
        this.zzbza = zzvn();
        if (this.zzbza != 0) {
            return this.zzbza;
        }
        throw new zzaci("Protocol message contained an invalid tag (zero).");
    }

    public final boolean zzvm() throws IOException {
        return zzvn() != 0;
    }

    public final int zzvn() throws IOException {
        int i;
        byte zzvs = zzvs();
        if (zzvs >= 0) {
            return zzvs;
        }
        byte b = zzvs & Byte.MAX_VALUE;
        byte zzvs2 = zzvs();
        if (zzvs2 >= 0) {
            i = zzvs2 << 7;
        } else {
            b |= (zzvs2 & Byte.MAX_VALUE) << 7;
            byte zzvs3 = zzvs();
            if (zzvs3 >= 0) {
                i = zzvs3 << 14;
            } else {
                b |= (zzvs3 & Byte.MAX_VALUE) << 14;
                byte zzvs4 = zzvs();
                if (zzvs4 >= 0) {
                    i = zzvs4 << 21;
                } else {
                    byte b2 = b | ((zzvs4 & Byte.MAX_VALUE) << 21);
                    byte zzvs5 = zzvs();
                    byte b3 = b2 | (zzvs5 << 28);
                    if (zzvs5 >= 0) {
                        return b3;
                    }
                    for (int i2 = 0; i2 < 5; i2++) {
                        if (zzvs() >= 0) {
                            return b3;
                        }
                    }
                    throw zzaci.zzvy();
                }
            }
        }
        return b | i;
    }

    public final long zzvo() throws IOException {
        int i = 0;
        long j = 0;
        while (i < 64) {
            byte zzvs = zzvs();
            long j2 = j | (((long) (zzvs & Byte.MAX_VALUE)) << i);
            if ((zzvs & 128) == 0) {
                return j2;
            }
            i += 7;
            j = j2;
        }
        throw zzaci.zzvy();
    }

    public final int zzvp() throws IOException {
        return (zzvs() & 255) | ((zzvs() & 255) << 8) | ((zzvs() & 255) << 16) | ((zzvs() & 255) << 24);
    }

    public final long zzvq() throws IOException {
        return (((long) zzvs()) & 255) | ((((long) zzvs()) & 255) << 8) | ((((long) zzvs()) & 255) << 16) | ((((long) zzvs()) & 255) << 24) | ((((long) zzvs()) & 255) << 32) | ((((long) zzvs()) & 255) << 40) | ((((long) zzvs()) & 255) << 48) | ((((long) zzvs()) & 255) << 56);
    }

    public final int zzvr() {
        if (this.zzbtr == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzbtr - this.zzbyz;
    }
}
