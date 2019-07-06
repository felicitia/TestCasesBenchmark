package com.salesforce.marketingcloud.messages.b;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import com.salesforce.marketingcloud.a.a.C0160a;
import com.salesforce.marketingcloud.a.b;
import com.salesforce.marketingcloud.c.e;
import com.salesforce.marketingcloud.c.f;
import com.salesforce.marketingcloud.c.f.a;
import com.salesforce.marketingcloud.d.h;
import com.salesforce.marketingcloud.d.i;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.location.g;
import com.salesforce.marketingcloud.location.k;
import com.salesforce.marketingcloud.messages.c;
import com.salesforce.marketingcloud.messages.l;
import com.salesforce.marketingcloud.messages.m;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

@RestrictTo({Scope.LIBRARY})
public final class d implements a, g, l {
    private static final String a = j.a(d.class);
    private final k b;
    private final h c;
    private final b d;
    private final f e;
    private final l.a f;
    private l.b g;
    private AtomicBoolean h = new AtomicBoolean(false);

    public d(@NonNull h hVar, @NonNull k kVar, @NonNull b bVar, @NonNull f fVar, @NonNull l.a aVar) {
        this.c = (h) com.salesforce.marketingcloud.e.f.a(hVar, "Storage was null");
        this.b = (k) com.salesforce.marketingcloud.e.f.a(kVar, "LocationManager was null");
        this.d = (b) com.salesforce.marketingcloud.e.f.a(bVar, "AlarmScheduler was null");
        this.e = (f) com.salesforce.marketingcloud.e.f.a(fVar, "RequestManager was null");
        this.f = (l.a) com.salesforce.marketingcloud.e.f.a(aVar, "RegionMessageHandler is null");
        fVar.a(com.salesforce.marketingcloud.c.d.GEOFENCE_MESSAGE, (a) this);
    }

    public static void a(h hVar, k kVar, b bVar, f fVar, boolean z) {
        List a2 = hVar.j().a(1);
        if (!a2.isEmpty()) {
            kVar.a((String[]) a2.toArray(new String[a2.size()]));
        }
        if (z) {
            hVar.k().a(1);
            hVar.j().c(1);
            i i = hVar.i();
            i.a(3);
            i.a(4);
        }
        fVar.a(com.salesforce.marketingcloud.c.d.GEOFENCE_MESSAGE);
        bVar.c(C0160a.FETCH_FENCE_MESSAGES, C0160a.FETCH_FENCE_MESSAGES_DAILY);
    }

    public void a() {
        this.b.a((g) this);
    }

    public void a(int i, @Nullable String str) {
        j.b(a, "Region error %d - %s", Integer.valueOf(i), str);
    }

    public void a(Context context, com.salesforce.marketingcloud.location.b bVar, String str, com.salesforce.marketingcloud.b bVar2, l.b bVar3) {
        this.g = bVar3;
        try {
            this.e.a(com.salesforce.marketingcloud.c.d.GEOFENCE_MESSAGE.a(bVar2, com.salesforce.marketingcloud.c.d.a(bVar2.b(), str, bVar)));
        } catch (Exception e2) {
            j.c(a, e2, "Failed to update geofence messages", new Object[0]);
        }
    }

    public void a(e eVar, com.salesforce.marketingcloud.c.g gVar) {
        C0160a[] aVarArr;
        b bVar;
        if (gVar.h()) {
            try {
                a(b.a(new JSONObject(gVar.a())));
            } catch (Exception e2) {
                j.c(a, e2, "Error parsing response.", new Object[0]);
                bVar = this.d;
                aVarArr = new C0160a[]{C0160a.FETCH_FENCE_MESSAGES};
            }
        } else {
            j.c(a, "Request failed: %d - %s", Integer.valueOf(gVar.c()), gVar.b());
            bVar = this.d;
            aVarArr = new C0160a[]{C0160a.FETCH_FENCE_MESSAGES};
            bVar.b(aVarArr);
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void a(b bVar) {
        j.c(a, "Geofence message request contained %d regions", Integer.valueOf(bVar.c().size()));
        this.d.d(C0160a.FETCH_FENCE_MESSAGES, C0160a.FETCH_FENCE_MESSAGES_DAILY);
        this.d.b(C0160a.FETCH_FENCE_MESSAGES_DAILY);
        if (this.g != null) {
            this.g.a(bVar);
        }
        com.salesforce.marketingcloud.d.j k = this.c.k();
        k.a(1);
        com.salesforce.marketingcloud.d.k j = this.c.j();
        List a2 = j.a(1);
        j.c(1);
        i i = this.c.i();
        com.salesforce.marketingcloud.e.a a3 = this.c.a();
        if (!bVar.c().isEmpty()) {
            ArrayList<com.salesforce.marketingcloud.messages.e> arrayList = new ArrayList<>();
            for (com.salesforce.marketingcloud.messages.e eVar : bVar.c()) {
                try {
                    for (c cVar : eVar.j()) {
                        com.salesforce.marketingcloud.messages.k.a(cVar, i, a3);
                        i.a(cVar, a3);
                        k.a(m.a(eVar.a(), cVar.a()));
                    }
                    if (!a2.remove(eVar.a())) {
                        arrayList.add(eVar);
                    }
                    j.a(eVar, a3);
                } catch (Exception e2) {
                    j.c(a, e2, "Unable to start monitoring geofence region: %s", eVar.a());
                }
            }
            for (com.salesforce.marketingcloud.messages.e eVar2 : arrayList) {
                this.b.a(eVar2.m());
            }
        }
        if (!a2.isEmpty()) {
            this.b.a((String[]) a2.toArray(new String[a2.size()]));
        }
        this.h.set(true);
    }

    public void a(@NonNull String str, int i) {
        j.a(a, "Geofence (%s - %s) was tripped.", str, Integer.valueOf(i));
        if (i == 4) {
            j.a(a, "Dwell transition ignore for %s", str);
            return;
        }
        try {
            com.salesforce.marketingcloud.messages.e a2 = this.c.j().a(str, this.c.a());
            if (a2 == null) {
                j.c(a, "Removing stale geofence from being monitored.", new Object[0]);
                this.b.a(str);
                return;
            }
            if (i == 1) {
                this.f.a(a2);
            } else {
                this.f.b(a2);
            }
            List<m> b2 = this.c.k().b(str);
            if (!b2.isEmpty()) {
                i i2 = this.c.i();
                com.salesforce.marketingcloud.e.a a3 = this.c.a();
                for (m b3 : b2) {
                    c a4 = i2.a(b3.b(), a3);
                    if ((i == 1 && a4.h() == 3) || (i == 2 && a4.h() == 4)) {
                        this.f.a(a2, a4);
                    }
                }
                return;
            }
            j.a(a, "No regionMessages found for %s", str);
        } catch (Exception e2) {
            j.c(a, e2, "Geofence (%s - %d) was tripped, but failed to check for associated message", str, Integer.valueOf(i));
        }
    }

    public void b() {
        if (this.b != null) {
            this.b.b((g) this);
            if (this.c != null) {
                List a2 = this.c.j().a(1);
                if (!a2.isEmpty()) {
                    this.b.a((String[]) a2.toArray(new String[a2.size()]));
                }
                this.c.k().a(1);
                this.c.j().c(1);
            }
        }
        this.h.set(false);
    }

    public void c() {
        if (this.h.get()) {
            j.a(a, "Attempt to monitor fences from DB ignored, because they're already monitored.", new Object[0]);
        }
        j.a(a, "monitorStoredRegions", new Object[0]);
        try {
            List<com.salesforce.marketingcloud.messages.e> a2 = this.c.j().a(1, this.c.a());
            if (!a2.isEmpty()) {
                for (com.salesforce.marketingcloud.messages.e eVar : a2) {
                    this.b.a(eVar.m());
                }
            }
        } catch (Exception e2) {
            j.c(a, e2, "Unable to monitor stored geofence regions.", new Object[0]);
        }
    }

    public boolean d() {
        return this.b.a();
    }
}
