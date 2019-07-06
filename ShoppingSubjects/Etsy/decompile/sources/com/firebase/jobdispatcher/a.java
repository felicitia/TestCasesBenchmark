package com.firebase.jobdispatcher;

import android.support.annotation.VisibleForTesting;

/* compiled from: Constraint */
public final class a {
    @VisibleForTesting
    static final int[] a = {2, 1, 4, 8};

    static int a(int[] iArr) {
        if (iArr == null) {
            return 0;
        }
        int i = 0;
        for (int i2 : iArr) {
            i |= i2;
        }
        return i;
    }

    static int[] a(int i) {
        int[] iArr;
        int[] iArr2 = a;
        int length = iArr2.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = iArr2[i3];
            i2 += (i & i4) == i4 ? 1 : 0;
        }
        int[] iArr3 = new int[i2];
        int i5 = 0;
        for (int i6 : a) {
            if ((i & i6) == i6) {
                int i7 = i5 + 1;
                iArr3[i5] = i6;
                i5 = i7;
            }
        }
        return iArr3;
    }
}
