package com.google.android.gms.internal.clearcut;

import java.io.IOException;

public final class zzgy extends zzfu<zzgy> implements Cloneable {
    private String[] zzbiw;
    private String[] zzbix;
    private int[] zzbiy;
    private long[] zzbiz;
    private long[] zzbja;

    public zzgy() {
        this.zzbiw = zzgb.zzsc;
        this.zzbix = zzgb.zzsc;
        this.zzbiy = zzgb.zzrx;
        this.zzbiz = zzgb.zzry;
        this.zzbja = zzgb.zzry;
        this.zzrj = null;
        this.zzrs = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzgb */
    public final zzgy clone() {
        try {
            zzgy zzgy = (zzgy) super.clone();
            if (this.zzbiw != null && this.zzbiw.length > 0) {
                zzgy.zzbiw = (String[]) this.zzbiw.clone();
            }
            if (this.zzbix != null && this.zzbix.length > 0) {
                zzgy.zzbix = (String[]) this.zzbix.clone();
            }
            if (this.zzbiy != null && this.zzbiy.length > 0) {
                zzgy.zzbiy = (int[]) this.zzbiy.clone();
            }
            if (this.zzbiz != null && this.zzbiz.length > 0) {
                zzgy.zzbiz = (long[]) this.zzbiz.clone();
            }
            if (this.zzbja != null && this.zzbja.length > 0) {
                zzgy.zzbja = (long[]) this.zzbja.clone();
            }
            return zzgy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgy)) {
            return false;
        }
        zzgy zzgy = (zzgy) obj;
        if (zzfy.equals((Object[]) this.zzbiw, (Object[]) zzgy.zzbiw) && zzfy.equals((Object[]) this.zzbix, (Object[]) zzgy.zzbix) && zzfy.equals(this.zzbiy, zzgy.zzbiy) && zzfy.equals(this.zzbiz, zzgy.zzbiz) && zzfy.equals(this.zzbja, zzgy.zzbja)) {
            return (this.zzrj == null || this.zzrj.isEmpty()) ? zzgy.zzrj == null || zzgy.zzrj.isEmpty() : this.zzrj.equals(zzgy.zzrj);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((((getClass().getName().hashCode() + 527) * 31) + zzfy.hashCode((Object[]) this.zzbiw)) * 31) + zzfy.hashCode((Object[]) this.zzbix)) * 31) + zzfy.hashCode(this.zzbiy)) * 31) + zzfy.hashCode(this.zzbiz)) * 31) + zzfy.hashCode(this.zzbja)) * 31) + ((this.zzrj == null || this.zzrj.isEmpty()) ? 0 : this.zzrj.hashCode());
    }

    public final void zza(zzfs zzfs) throws IOException {
        if (this.zzbiw != null && this.zzbiw.length > 0) {
            for (String str : this.zzbiw) {
                if (str != null) {
                    zzfs.zza(1, str);
                }
            }
        }
        if (this.zzbix != null && this.zzbix.length > 0) {
            for (String str2 : this.zzbix) {
                if (str2 != null) {
                    zzfs.zza(2, str2);
                }
            }
        }
        if (this.zzbiy != null && this.zzbiy.length > 0) {
            for (int zzc : this.zzbiy) {
                zzfs.zzc(3, zzc);
            }
        }
        if (this.zzbiz != null && this.zzbiz.length > 0) {
            for (long zzi : this.zzbiz) {
                zzfs.zzi(4, zzi);
            }
        }
        if (this.zzbja != null && this.zzbja.length > 0) {
            for (long zzi2 : this.zzbja) {
                zzfs.zzi(5, zzi2);
            }
        }
        super.zza(zzfs);
    }

    /* access modifiers changed from: protected */
    public final int zzen() {
        int zzen = super.zzen();
        if (this.zzbiw != null && this.zzbiw.length > 0) {
            int i = 0;
            int i2 = 0;
            for (String str : this.zzbiw) {
                if (str != null) {
                    i2++;
                    i += zzfs.zzh(str);
                }
            }
            zzen = zzen + i + (i2 * 1);
        }
        if (this.zzbix != null && this.zzbix.length > 0) {
            int i3 = 0;
            int i4 = 0;
            for (String str2 : this.zzbix) {
                if (str2 != null) {
                    i4++;
                    i3 += zzfs.zzh(str2);
                }
            }
            zzen = zzen + i3 + (i4 * 1);
        }
        if (this.zzbiy != null && this.zzbiy.length > 0) {
            int i5 = 0;
            for (int zzs : this.zzbiy) {
                i5 += zzfs.zzs(zzs);
            }
            zzen = zzen + i5 + (this.zzbiy.length * 1);
        }
        if (this.zzbiz != null && this.zzbiz.length > 0) {
            int i6 = 0;
            for (long zzo : this.zzbiz) {
                i6 += zzfs.zzo(zzo);
            }
            zzen = zzen + i6 + (this.zzbiz.length * 1);
        }
        if (this.zzbja == null || this.zzbja.length <= 0) {
            return zzen;
        }
        int i7 = 0;
        for (long zzo2 : this.zzbja) {
            i7 += zzfs.zzo(zzo2);
        }
        return zzen + i7 + (this.zzbja.length * 1);
    }

    public final /* synthetic */ zzfu zzeo() throws CloneNotSupportedException {
        return (zzgy) clone();
    }

    public final /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzgy) clone();
    }
}
