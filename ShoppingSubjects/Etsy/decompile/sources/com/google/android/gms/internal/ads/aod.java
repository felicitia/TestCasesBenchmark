package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;

@bu
final class aod {
    private static final aob a = aob.a();
    private static final float b = ((Float) ajh.f().a(akl.bf)).floatValue();
    private static final long c = ((Long) ajh.f().a(akl.bd)).longValue();
    private static final float d = ((Float) ajh.f().a(akl.bg)).floatValue();
    private static final long e = ((Long) ajh.f().a(akl.be)).longValue();

    @VisibleForTesting
    private static int a(long j, int i) {
        return (int) ((j >>> (4 * (i % 16))) & 15);
    }

    static boolean a() {
        int h = a.h();
        int i = a.i();
        int g = a.g() + a.f();
        int i2 = Integer.MAX_VALUE;
        int i3 = (h >= 16 || e == 0) ? d != 0.0f ? ((int) (d * ((float) h))) + 1 : Integer.MAX_VALUE : a(e, h);
        if (i <= i3) {
            if (h < 16 && c != 0) {
                i2 = a(c, h);
            } else if (b != 0.0f) {
                i2 = (int) (b * ((float) h));
            }
            if (g <= i2) {
                return true;
            }
        }
        return false;
    }
}
