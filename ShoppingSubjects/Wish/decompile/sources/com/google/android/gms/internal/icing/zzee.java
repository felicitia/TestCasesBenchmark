package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzck.zzd;

final class zzee implements zzdp {
    private final int flags;
    private final String info;
    private final Object[] zzjz;
    private final zzdr zzkc;

    zzee(zzdr zzdr, String str, Object[] objArr) {
        this.zzkc = zzdr;
        this.info = str;
        this.zzjz = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.flags = charAt;
            return;
        }
        char c = charAt & 8191;
        int i = 13;
        int i2 = 1;
        while (true) {
            int i3 = i2 + 1;
            char charAt2 = str.charAt(i2);
            if (charAt2 >= 55296) {
                c |= (charAt2 & 8191) << i;
                i += 13;
                i2 = i3;
            } else {
                this.flags = c | (charAt2 << i);
                return;
            }
        }
    }

    public final int zzbs() {
        return (this.flags & 1) == 1 ? zzd.zzib : zzd.zzic;
    }

    public final boolean zzbt() {
        return (this.flags & 2) == 2;
    }

    public final zzdr zzbu() {
        return this.zzkc;
    }

    /* access modifiers changed from: 0000 */
    public final String zzcb() {
        return this.info;
    }

    /* access modifiers changed from: 0000 */
    public final Object[] zzcc() {
        return this.zzjz;
    }
}
