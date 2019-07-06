package com.google.android.gms.internal.firebase-perf;

enum zzci {
    SCALAR(false),
    VECTOR(true),
    PACKED_VECTOR(true),
    MAP(false);
    
    private final boolean zzlw;

    private zzci(boolean z) {
        this.zzlw = z;
    }
}
