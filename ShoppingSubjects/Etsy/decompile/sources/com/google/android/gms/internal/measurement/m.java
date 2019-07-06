package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class m {
    public static final int[] a = new int[0];
    public static final long[] b = new long[0];
    public static final String[] c = new String[0];
    public static final byte[] d = new byte[0];
    private static final int e = 11;
    private static final int f = 12;
    private static final int g = 16;
    private static final int h = 26;
    private static final float[] i = new float[0];
    private static final double[] j = new double[0];
    private static final boolean[] k = new boolean[0];
    private static final byte[][] l = new byte[0][];

    public static final int a(c cVar, int i2) throws IOException {
        int i3 = cVar.i();
        cVar.b(i2);
        int i4 = 1;
        while (cVar.a() == i2) {
            cVar.b(i2);
            i4++;
        }
        cVar.b(i3, i2);
        return i4;
    }
}
