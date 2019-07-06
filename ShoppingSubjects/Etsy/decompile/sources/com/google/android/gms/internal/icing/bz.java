package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.ah.d;

final class bz implements bj {
    private final bl a;
    private final String b;
    private final Object[] c;
    private final int d;

    bz(bl blVar, String str, Object[] objArr) {
        this.a = blVar;
        this.b = str;
        this.c = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.d = charAt;
            return;
        }
        char c2 = charAt & 8191;
        int i = 13;
        int i2 = 1;
        while (true) {
            int i3 = i2 + 1;
            char charAt2 = str.charAt(i2);
            if (charAt2 >= 55296) {
                c2 |= (charAt2 & 8191) << i;
                i += 13;
                i2 = i3;
            } else {
                this.d = c2 | (charAt2 << i);
                return;
            }
        }
    }

    public final int a() {
        return (this.d & 1) == 1 ? d.h : d.i;
    }

    public final boolean b() {
        return (this.d & 2) == 2;
    }

    public final bl c() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public final String d() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public final Object[] e() {
        return this.c;
    }
}
