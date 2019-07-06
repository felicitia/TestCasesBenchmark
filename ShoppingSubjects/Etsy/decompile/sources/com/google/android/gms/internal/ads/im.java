package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

public final class im {
    /* access modifiers changed from: private */
    public final List<String> a = new ArrayList();
    /* access modifiers changed from: private */
    public final List<Double> b = new ArrayList();
    /* access modifiers changed from: private */
    public final List<Double> c = new ArrayList();

    public final ij a() {
        return new ij(this);
    }

    public final im a(String str, double d, double d2) {
        int i = 0;
        while (i < this.a.size()) {
            double doubleValue = ((Double) this.c.get(i)).doubleValue();
            double doubleValue2 = ((Double) this.b.get(i)).doubleValue();
            if (d < doubleValue || (doubleValue == d && d2 < doubleValue2)) {
                break;
            }
            i++;
        }
        this.a.add(i, str);
        this.c.add(i, Double.valueOf(d));
        this.b.add(i, Double.valueOf(d2));
        return this;
    }
}
