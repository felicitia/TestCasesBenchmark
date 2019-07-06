package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;

public final class zzgj {
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final int[] zzon = new int[0];
    private static final int zztc = 11;
    private static final int zztd = 12;
    private static final int zzte = 16;
    private static final int zztf = 26;
    private static final long[] zztg = new long[0];
    private static final float[] zzth = new float[0];
    private static final double[] zzti = new double[0];
    private static final boolean[] zztj = new boolean[0];
    private static final byte[][] zztk = new byte[0][];
    public static final byte[] zztl = new byte[0];

    public static final int zzb(zzfx zzfx, int i) throws IOException {
        int position = zzfx.getPosition();
        zzfx.zzm(i);
        int i2 = 1;
        while (zzfx.zzbs() == i) {
            zzfx.zzm(i);
            i2++;
        }
        zzfx.zzs(position, i);
        return i2;
    }
}
