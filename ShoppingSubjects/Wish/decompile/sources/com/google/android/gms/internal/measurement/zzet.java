package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;

final class zzet {
    final String name;
    final long zzahh;
    final long zzahi;
    final long zzahj;
    final long zzahk;
    final Long zzahl;
    final Long zzahm;
    final Boolean zzahn;
    final String zzth;

    zzet(String str, String str2, long j, long j2, long j3, long j4, Long l, Long l2, Boolean bool) {
        long j5 = j;
        long j6 = j2;
        long j7 = j4;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        boolean z = false;
        Preconditions.checkArgument(j5 >= 0);
        Preconditions.checkArgument(j6 >= 0);
        if (j7 >= 0) {
            z = true;
        }
        Preconditions.checkArgument(z);
        this.zzth = str;
        this.name = str2;
        this.zzahh = j5;
        this.zzahi = j6;
        this.zzahj = j3;
        this.zzahk = j7;
        this.zzahl = l;
        this.zzahm = l2;
        this.zzahn = bool;
    }

    /* access modifiers changed from: 0000 */
    public final zzet zza(Long l, Long l2, Boolean bool) {
        zzet zzet = new zzet(this.zzth, this.name, this.zzahh, this.zzahi, this.zzahj, this.zzahk, l, l2, (bool == null || bool.booleanValue()) ? bool : null);
        return zzet;
    }

    /* access modifiers changed from: 0000 */
    public final zzet zzah(long j) {
        zzet zzet = new zzet(this.zzth, this.name, this.zzahh, this.zzahi, j, this.zzahk, this.zzahl, this.zzahm, this.zzahn);
        return zzet;
    }

    /* access modifiers changed from: 0000 */
    public final zzet zzai(long j) {
        zzet zzet = new zzet(this.zzth, this.name, this.zzahh, this.zzahi, this.zzahj, j, this.zzahl, this.zzahm, this.zzahn);
        return zzet;
    }

    /* access modifiers changed from: 0000 */
    public final zzet zzim() {
        String str = this.zzth;
        String str2 = this.name;
        long j = this.zzahh + 1;
        long j2 = this.zzahi + 1;
        long j3 = this.zzahj;
        long j4 = this.zzahk;
        long j5 = j4;
        zzet zzet = new zzet(str, str2, j, j2, j3, j5, this.zzahl, this.zzahm, this.zzahn);
        return zzet;
    }
}
