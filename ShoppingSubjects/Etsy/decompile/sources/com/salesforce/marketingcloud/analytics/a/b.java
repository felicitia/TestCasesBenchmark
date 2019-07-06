package com.salesforce.marketingcloud.analytics.a;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import com.salesforce.marketingcloud.analytics.l;
import com.salesforce.marketingcloud.d.h;
import com.salesforce.marketingcloud.e.f;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.messages.e;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestrictTo({Scope.LIBRARY})
public final class b extends l {
    private static final String a = j.a(b.class);
    private final h b;

    public b(@NonNull h hVar) {
        this.b = (h) f.a(hVar, "MCStorage may not be null.");
    }

    public static void a(h hVar, boolean z) {
        if (z) {
            hVar.g().d(0);
        }
    }

    private void a(@NonNull e eVar, Date date) {
        try {
            this.b.g().a(com.salesforce.marketingcloud.analytics.e.a(date, 0, eVar.g() == 1 ? 11 : 13, Collections.singletonList(eVar.a()), false), this.b.a());
        } catch (Exception e) {
            j.c(a, e, "Failed to record EtAnalyticItem for startTimeInRegion", new Object[0]);
        }
    }

    private void b(@NonNull e eVar, @NonNull Date date) {
        try {
            List<com.salesforce.marketingcloud.analytics.e> a2 = this.b.g().a(eVar, this.b.a());
            if (!a2.isEmpty()) {
                for (com.salesforce.marketingcloud.analytics.e eVar2 : a2) {
                    int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(date.getTime() - eVar2.b().getTime());
                    if (seconds > 0) {
                        eVar2.b(seconds);
                        eVar2.a(true);
                        this.b.g().b(eVar2, this.b.a());
                    }
                }
            }
        } catch (Exception e) {
            j.c(a, e, "Failed to record EtAnalyticItem for stopTimeInRegion.", new Object[0]);
        }
    }

    public void a(long j) {
        if (!this.b.g().e(0)) {
            try {
                this.b.g().a(com.salesforce.marketingcloud.analytics.e.a(new Date(j), 0, 4), this.b.a());
            } catch (Exception e) {
                j.c(a, e, "Failed to create our EtAnalyticItem for TimeInApp.", new Object[0]);
            }
        }
    }

    public void a(@NonNull e eVar) {
        Date date = new Date();
        a(eVar, date);
        try {
            this.b.g().a(com.salesforce.marketingcloud.analytics.e.a(date, 0, eVar.g() == 1 ? 6 : 12, Collections.singletonList(eVar.a()), true), this.b.a());
        } catch (Exception e) {
            j.c(a, e, "Failed to record EtAnalyticItem for Geofence region entry.", new Object[0]);
        }
    }

    public void a(boolean z) {
        if (z) {
            this.b.g().d(0);
        }
    }

    public void b(long j) {
        try {
            for (com.salesforce.marketingcloud.analytics.e eVar : this.b.g().b()) {
                int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(j - eVar.b().getTime());
                if (seconds > 0) {
                    eVar.b(seconds);
                    eVar.a(true);
                    this.b.g().b(eVar, this.b.a());
                }
            }
        } catch (Exception e) {
            j.c(a, e, "Failed to update our EtAnalytic TimeInApp.", new Object[0]);
        }
    }

    public void b(@NonNull e eVar) {
        Date date = new Date();
        b(eVar, date);
        if (eVar.g() != 3) {
            try {
                this.b.g().a(com.salesforce.marketingcloud.analytics.e.a(date, 0, 7, Collections.singletonList(eVar.a()), true), this.b.a());
            } catch (Exception e) {
                j.c(a, e, "Failed to record EtAnalyticItem for Geofence region exit.", new Object[0]);
            }
        }
    }

    public void b(@NonNull NotificationMessage notificationMessage) {
        try {
            if (this.b.g().e(0)) {
                b(0);
            }
            this.b.g().a(com.salesforce.marketingcloud.analytics.e.a(new Date(), 0, 5, Arrays.asList(TextUtils.isEmpty(notificationMessage.regionId()) ? new String[]{notificationMessage.id()} : new String[]{notificationMessage.id(), notificationMessage.regionId()}), false), this.b.a());
        } catch (Exception e) {
            j.c(a, e, "Failed to store EtAnalyticItem for message opened.", new Object[0]);
        }
    }

    public void c(long j) {
        try {
            List<com.salesforce.marketingcloud.analytics.e> d = this.b.g().d(this.b.a());
            if (!d.isEmpty()) {
                for (com.salesforce.marketingcloud.analytics.e eVar : d) {
                    int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(j - eVar.b().getTime());
                    if (seconds > 0) {
                        eVar.b(seconds);
                        eVar.a(true);
                        this.b.g().b(eVar, this.b.a());
                    }
                }
            }
        } catch (Exception e) {
            j.c(a, e, "Failed to update local storage for stopTimeInAllRegions.", new Object[0]);
        }
    }
}
