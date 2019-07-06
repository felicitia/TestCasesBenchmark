package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

@bu
public final class ij {
    private final String[] a;
    private final double[] b;
    private final double[] c;
    private final int[] d;
    private int e;

    private ij(im imVar) {
        int size = imVar.b.size();
        this.a = (String[]) imVar.a.toArray(new String[size]);
        this.b = a(imVar.b);
        this.c = a(imVar.c);
        this.d = new int[size];
        this.e = 0;
    }

    private static double[] a(List<Double> list) {
        double[] dArr = new double[list.size()];
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = ((Double) list.get(i)).doubleValue();
        }
        return dArr;
    }

    public final List<il> a() {
        ArrayList arrayList = new ArrayList(this.a.length);
        for (int i = 0; i < this.a.length; i++) {
            il ilVar = new il(this.a[i], this.c[i], this.b[i], ((double) this.d[i]) / ((double) this.e), this.d[i]);
            arrayList.add(ilVar);
        }
        return arrayList;
    }

    public final void a(double d2) {
        this.e++;
        int i = 0;
        while (i < this.c.length) {
            if (this.c[i] <= d2 && d2 < this.b[i]) {
                int[] iArr = this.d;
                iArr[i] = iArr[i] + 1;
            }
            if (d2 >= this.c[i]) {
                i++;
            } else {
                return;
            }
        }
    }
}
