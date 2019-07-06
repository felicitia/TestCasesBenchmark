package com.google.android.gms.internal.firebase-perf;

public final class zzao {
    public static int zze(int i) {
        if (i >= 0 && i <= 1) {
            return i;
        }
        StringBuilder sb = new StringBuilder(48);
        sb.append(i);
        sb.append(" is not a valid enum SessionVerbosity");
        throw new IllegalArgumentException(sb.toString());
    }
}
