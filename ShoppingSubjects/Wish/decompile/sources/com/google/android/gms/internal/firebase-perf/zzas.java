package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;

public final class zzas extends zzga<zzas> {
    private static volatile zzas[] zzgu;
    public String zzgv;
    public int[] zzgw;

    public static zzas[] zzba() {
        if (zzgu == null) {
            synchronized (zzge.zzta) {
                if (zzgu == null) {
                    zzgu = new zzas[0];
                }
            }
        }
        return zzgu;
    }

    public zzas() {
        this.zzgv = null;
        this.zzgw = zzgj.zzon;
        this.zzss = null;
        this.zztb = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzas)) {
            return false;
        }
        zzas zzas = (zzas) obj;
        if (this.zzgv == null) {
            if (zzas.zzgv != null) {
                return false;
            }
        } else if (!this.zzgv.equals(zzas.zzgv)) {
            return false;
        }
        if (!zzge.equals(this.zzgw, zzas.zzgw)) {
            return false;
        }
        if (this.zzss == null || this.zzss.isEmpty()) {
            return zzas.zzss == null || zzas.zzss.isEmpty();
        }
        return this.zzss.equals(zzas.zzss);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.zzgv == null ? 0 : this.zzgv.hashCode())) * 31) + zzge.hashCode(this.zzgw)) * 31;
        if (this.zzss != null && !this.zzss.isEmpty()) {
            i = this.zzss.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzfy zzfy) throws IOException {
        if (this.zzgv != null) {
            zzfy.zza(1, this.zzgv);
        }
        if (this.zzgw != null && this.zzgw.length > 0) {
            for (int zzc : this.zzgw) {
                zzfy.zzc(2, zzc);
            }
        }
        super.zza(zzfy);
    }

    /* access modifiers changed from: protected */
    public final int zzax() {
        int zzax = super.zzax();
        if (this.zzgv != null) {
            zzax += zzfy.zzb(1, this.zzgv);
        }
        if (this.zzgw == null || this.zzgw.length <= 0) {
            return zzax;
        }
        int i = 0;
        for (int zzaa : this.zzgw) {
            i += zzfy.zzaa(zzaa);
        }
        return zzax + i + (this.zzgw.length * 1);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzas zza(zzfx zzfx) throws IOException {
        while (true) {
            int zzbs = zzfx.zzbs();
            if (zzbs == 0) {
                return this;
            }
            if (zzbs == 10) {
                this.zzgv = zzfx.readString();
            } else if (zzbs == 16) {
                int zzb = zzgj.zzb(zzfx, 16);
                int[] iArr = new int[zzb];
                int i = 0;
                for (int i2 = 0; i2 < zzb; i2++) {
                    if (i2 != 0) {
                        zzfx.zzbs();
                    }
                    int position = zzfx.getPosition();
                    try {
                        iArr[i] = zzao.zze(zzfx.zzck());
                        i++;
                    } catch (IllegalArgumentException unused) {
                        zzfx.zzax(position);
                        zza(zzfx, zzbs);
                    }
                }
                if (i != 0) {
                    int length = this.zzgw == null ? 0 : this.zzgw.length;
                    if (length == 0 && i == iArr.length) {
                        this.zzgw = iArr;
                    } else {
                        int[] iArr2 = new int[(length + i)];
                        if (length != 0) {
                            System.arraycopy(this.zzgw, 0, iArr2, 0, length);
                        }
                        System.arraycopy(iArr, 0, iArr2, length, i);
                        this.zzgw = iArr2;
                    }
                }
            } else if (zzbs == 18) {
                int zzo = zzfx.zzo(zzfx.zzck());
                int position2 = zzfx.getPosition();
                int i3 = 0;
                while (zzfx.zzge() > 0) {
                    try {
                        zzao.zze(zzfx.zzck());
                        i3++;
                    } catch (IllegalArgumentException unused2) {
                    }
                }
                if (i3 != 0) {
                    zzfx.zzax(position2);
                    int length2 = this.zzgw == null ? 0 : this.zzgw.length;
                    int[] iArr3 = new int[(i3 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzgw, 0, iArr3, 0, length2);
                    }
                    while (zzfx.zzge() > 0) {
                        int position3 = zzfx.getPosition();
                        try {
                            iArr3[length2] = zzao.zze(zzfx.zzck());
                            length2++;
                        } catch (IllegalArgumentException unused3) {
                            zzfx.zzax(position3);
                            zza(zzfx, 16);
                        }
                    }
                    this.zzgw = iArr3;
                }
                zzfx.zzp(zzo);
            } else if (!super.zza(zzfx, zzbs)) {
                return this;
            }
        }
    }
}
