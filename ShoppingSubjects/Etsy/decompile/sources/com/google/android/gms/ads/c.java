package com.google.android.gms.ads;

import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.b;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.ajk;
import com.google.android.gms.internal.ads.ajl;
import java.util.Date;

@VisibleForTesting
public final class c {
    private final ajk a;

    @VisibleForTesting
    public static final class a {
        /* access modifiers changed from: private */
        public final ajl a = new ajl();

        public a() {
            this.a.b("B3EEABB8EE11C2BE770B684D95219ECB");
        }

        public final a a(int i) {
            this.a.a(i);
            return this;
        }

        public final a a(Location location) {
            this.a.a(location);
            return this;
        }

        public final a a(Class<? extends b> cls, Bundle bundle) {
            this.a.a(cls, bundle);
            if (cls.equals(AdMobAdapter.class) && bundle.getBoolean("_emulatorLiveAds")) {
                this.a.c("B3EEABB8EE11C2BE770B684D95219ECB");
            }
            return this;
        }

        public final a a(String str) {
            this.a.a(str);
            return this;
        }

        public final a a(Date date) {
            this.a.a(date);
            return this;
        }

        public final a a(boolean z) {
            this.a.a(z);
            return this;
        }

        public final c a() {
            return new c(this);
        }

        public final a b(String str) {
            this.a.b(str);
            return this;
        }

        public final a b(boolean z) {
            this.a.b(z);
            return this;
        }
    }

    private c(a aVar) {
        this.a = new ajk(aVar.a);
    }

    public final ajk a() {
        return this.a;
    }
}
