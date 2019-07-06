package com.google.android.gms.internal.icing;

import java.util.ArrayList;
import java.util.List;

public final class zzt {
    private final String name;
    private int weight = 1;
    private String zzaa;
    private boolean zzab;
    private boolean zzac;
    private String zzaf;
    private final List<zzn> zzah = new ArrayList();

    public zzt(String str) {
        this.name = str;
    }

    public final zzs zzb() {
        zzs zzs = new zzs(this.name, this.zzaa, this.zzab, this.weight, this.zzac, null, (zzn[]) this.zzah.toArray(new zzn[this.zzah.size()]), this.zzaf, null);
        return zzs;
    }

    public final zzt zzb(boolean z) {
        this.zzab = true;
        return this;
    }

    public final zzt zzc(String str) {
        this.zzaa = str;
        return this;
    }
}
