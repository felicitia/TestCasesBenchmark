package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import org.apache.commons.lang3.time.DateUtils;

final class et {
    private final Clock a;
    private long b;

    public et(Clock clock) {
        Preconditions.checkNotNull(clock);
        this.a = clock;
    }

    public final void a() {
        this.b = this.a.elapsedRealtime();
    }

    public final boolean a(long j) {
        return this.b == 0 || this.a.elapsedRealtime() - this.b >= DateUtils.MILLIS_PER_HOUR;
    }

    public final void b() {
        this.b = 0;
    }
}
