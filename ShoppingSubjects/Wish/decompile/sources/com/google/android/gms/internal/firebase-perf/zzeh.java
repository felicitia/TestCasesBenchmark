package com.google.android.gms.internal.firebase-perf;

import com.google.android.gms.internal.firebase-perf.zzcm.zze;

final class zzeh implements zzdr {
    private final int flags;
    private final String info;
    private final Object[] zzoq;
    private final zzdt zzot;

    zzeh(zzdt zzdt, String str, Object[] objArr) {
        this.zzot = zzdt;
        this.info = str;
        this.zzoq = objArr;
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

    /* access modifiers changed from: 0000 */
    public final String zzfc() {
        return this.info;
    }

    /* access modifiers changed from: 0000 */
    public final Object[] zzfd() {
        return this.zzoq;
    }

    public final zzdt zzev() {
        return this.zzot;
    }

    public final int zzet() {
        return (this.flags & 1) == 1 ? zze.zzmr : zze.zzms;
    }

    public final boolean zzeu() {
        return (this.flags & 2) == 2;
    }
}
