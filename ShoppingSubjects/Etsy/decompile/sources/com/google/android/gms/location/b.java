package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.location.zzbh;

@VisibleForTesting
public interface b {

    @VisibleForTesting
    public static final class a {
        private String a = null;
        private int b = 0;
        private long c = Long.MIN_VALUE;
        private short d = -1;
        private double e;
        private double f;
        private float g;
        private int h = 0;
        private int i = -1;

        public final a a(double d2, double d3, float f2) {
            this.d = 1;
            this.e = d2;
            this.f = d3;
            this.g = f2;
            return this;
        }

        public final a a(int i2) {
            this.b = i2;
            return this;
        }

        public final a a(long j) {
            if (j < 0) {
                this.c = -1;
                return this;
            }
            this.c = SystemClock.elapsedRealtime() + j;
            return this;
        }

        public final a a(String str) {
            this.a = str;
            return this;
        }

        public final b a() {
            if (this.a == null) {
                throw new IllegalArgumentException("Request ID not set.");
            } else if (this.b == 0) {
                throw new IllegalArgumentException("Transitions types not set.");
            } else if ((this.b & 4) != 0 && this.i < 0) {
                throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
            } else if (this.c == Long.MIN_VALUE) {
                throw new IllegalArgumentException("Expiration not set.");
            } else if (this.d == -1) {
                throw new IllegalArgumentException("Geofence region not set.");
            } else if (this.h < 0) {
                throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
            } else {
                zzbh zzbh = new zzbh(this.a, this.b, 1, this.e, this.f, this.g, this.c, this.h, this.i);
                return zzbh;
            }
        }
    }

    String getRequestId();
}
