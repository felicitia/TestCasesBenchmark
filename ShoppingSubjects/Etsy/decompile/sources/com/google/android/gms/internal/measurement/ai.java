package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;

final class ai {
    final String a;
    final String b;
    final long c;
    final long d;
    final long e;
    final long f;
    final Long g;
    final Long h;
    final Boolean i;

    ai(String str, String str2, long j, long j2, long j3, long j4, Long l, Long l2, Boolean bool) {
        long j5 = j;
        long j6 = j2;
        long j7 = j4;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        boolean z = false;
        Preconditions.checkArgument(j5 >= 0);
        Preconditions.checkArgument(j6 >= 0);
        if (j7 >= 0) {
            z = true;
        }
        Preconditions.checkArgument(z);
        this.a = str;
        this.b = str2;
        this.c = j5;
        this.d = j6;
        this.e = j3;
        this.f = j7;
        this.g = l;
        this.h = l2;
        this.i = bool;
    }

    /* access modifiers changed from: 0000 */
    public final ai a() {
        String str = this.a;
        String str2 = this.b;
        long j = this.c + 1;
        long j2 = this.d + 1;
        long j3 = this.e;
        long j4 = this.f;
        long j5 = j4;
        ai aiVar = new ai(str, str2, j, j2, j3, j5, this.g, this.h, this.i);
        return aiVar;
    }

    /* access modifiers changed from: 0000 */
    public final ai a(long j) {
        ai aiVar = new ai(this.a, this.b, this.c, this.d, j, this.f, this.g, this.h, this.i);
        return aiVar;
    }

    /* access modifiers changed from: 0000 */
    public final ai a(Long l, Long l2, Boolean bool) {
        ai aiVar = new ai(this.a, this.b, this.c, this.d, this.e, this.f, l, l2, (bool == null || bool.booleanValue()) ? bool : null);
        return aiVar;
    }

    /* access modifiers changed from: 0000 */
    public final ai b(long j) {
        ai aiVar = new ai(this.a, this.b, this.c, this.d, this.e, j, this.g, this.h, this.i);
        return aiVar;
    }
}
