package com.google.android.gms.internal.firebase-perf;

enum zzu {
    NETWORK("network", 10, 700, 10, 70),
    TRACE("trace", 10, 300, 10, 30);
    
    private final String zzcj;
    private final int zzck;
    private final int zzcl;
    private final int zzcm;
    private final int zzcn;

    private zzu(String str, int i, int i2, int i3, int i4) {
        this.zzcj = str;
        this.zzck = 10;
        this.zzcl = i2;
        this.zzcm = 10;
        this.zzcn = i4;
    }

    public final int zzw() {
        return this.zzck;
    }

    public final int zzx() {
        return this.zzcl;
    }

    public final int zzy() {
        return this.zzcm;
    }

    public final int zzz() {
        return this.zzcn;
    }

    public final String zzaa() {
        return String.valueOf(this.zzcj).concat("_flimit_time");
    }

    public final String zzab() {
        return String.valueOf(this.zzcj).concat("_flimit_events");
    }

    public final String zzac() {
        return String.valueOf(this.zzcj).concat("_blimit_time");
    }

    public final String zzad() {
        return String.valueOf(this.zzcj).concat("_blimit_events");
    }

    public final String toString() {
        return name();
    }
}
