package com.salesforce.marketingcloud.messages.d;

import android.content.Context;
import android.support.annotation.NonNull;
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
import com.salesforce.marketingcloud.d.k;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.messages.c;
import com.salesforce.marketingcloud.messages.l;
import com.salesforce.marketingcloud.messages.m;
import com.salesforce.marketingcloud.proximity.g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

@RestrictTo({Scope.LIBRARY})
public final class d implements a, l, g.a {
    private static final String a = j.a(d.class);
    private final h b;
    private final g c;
    private final b d;
    private final f e;
    private final l.a f;
    private l.b g;

    public d(@NonNull h hVar, @NonNull g gVar, @NonNull b bVar, @NonNull f fVar, @NonNull l.a aVar) {
        this.b = (h) com.salesforce.marketingcloud.e.f.a(hVar, "Storage was null");
        this.c = (g) com.salesforce.marketingcloud.e.f.a(gVar, "ProximityManager was null");
        this.d = (b) com.salesforce.marketingcloud.e.f.a(bVar, "AlarmScheduler was null");
        this.e = (f) com.salesforce.marketingcloud.e.f.a(fVar, "RequestManager was null");
        this.f = (l.a) com.salesforce.marketingcloud.e.f.a(aVar, "RegionMessageHandler is null");
        fVar.a(com.salesforce.marketingcloud.c.d.PROXIMITY_MESSAGES, (a) this);
    }

    public static void a(h hVar, g gVar, b bVar, f fVar, boolean z) {
        gVar.c();
        if (z) {
            hVar.k().a(3);
            hVar.j().c(3);
            hVar.i().a(5);
        }
        fVar.a(com.salesforce.marketingcloud.c.d.PROXIMITY_MESSAGES);
        bVar.c(C0160a.FETCH_BEACON_MESSAGES, C0160a.FETCH_BEACON_MESSAGES_DAILY);
    }

    public void a() {
        this.c.a((g.a) this);
    }

    public void a(Context context, com.salesforce.marketingcloud.location.b bVar, String str, com.salesforce.marketingcloud.b bVar2, l.b bVar3) {
        this.g = bVar3;
        try {
            this.e.a(com.salesforce.marketingcloud.c.d.PROXIMITY_MESSAGES.a(bVar2, com.salesforce.marketingcloud.c.d.a(bVar2.b(), str, bVar)));
        } catch (Exception e2) {
            j.c(a, e2, "Failed to update proximity messages", new Object[0]);
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
                aVarArr = new C0160a[]{C0160a.FETCH_BEACON_MESSAGES};
            }
        } else {
            j.c(a, "Request failed: %d - %s", Integer.valueOf(gVar.c()), gVar.b());
            bVar = this.d;
            aVarArr = new C0160a[]{C0160a.FETCH_BEACON_MESSAGES};
            bVar.b(aVarArr);
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void a(b bVar) {
        j.c(a, "Proximity message request contained %d regions", Integer.valueOf(bVar.c().size()));
        this.d.d(C0160a.FETCH_BEACON_MESSAGES, C0160a.FETCH_BEACON_MESSAGES_DAILY);
        this.d.b(C0160a.FETCH_BEACON_MESSAGES_DAILY);
        if (this.g != null) {
            this.g.a(bVar);
        }
        com.salesforce.marketingcloud.d.j k = this.b.k();
        k.a(3);
        k j = this.b.j();
        List b2 = j.b(3);
        j.c(3);
        i i = this.b.i();
        com.salesforce.marketingcloud.e.a a2 = this.b.a();
        List emptyList = Collections.emptyList();
        if (!bVar.c().isEmpty()) {
            emptyList = new ArrayList();
            for (com.salesforce.marketingcloud.messages.e eVar : bVar.c()) {
                try {
                    for (c cVar : eVar.j()) {
                        com.salesforce.marketingcloud.messages.k.a(cVar, i, a2);
                        i.a(cVar, a2);
                        k.a(m.a(eVar.a(), cVar.a()));
                    }
                    eVar.a(b2.contains(eVar.a()));
                    j.a(eVar, a2);
                    emptyList.add(com.salesforce.marketingcloud.proximity.e.a(eVar));
                } catch (Exception e2) {
                    j.c(a, e2, "Unable to start monitoring proximity region: %s", eVar.a());
                }
            }
        }
        this.c.a(emptyList);
    }

    public void a(@NonNull com.salesforce.marketingcloud.proximity.e eVar) {
        j.a(a, "Proximity region (%s) entered.", eVar.a());
        try {
            k j = this.b.j();
            com.salesforce.marketingcloud.messages.e a2 = j.a(eVar.a(), this.b.a());
            if (a2 == null) {
                j.b(a, "BeaconRegion [%s] did not have matching Region in storage.", eVar);
            } else if (!a2.l()) {
                a2.a(true);
                j.a(a2.a(), true);
                this.f.a(a2);
                List<m> b2 = this.b.k().b(eVar.a());
                if (!b2.isEmpty()) {
                    i i = this.b.i();
                    com.salesforce.marketingcloud.e.a a3 = this.b.a();
                    for (m b3 : b2) {
                        this.f.a(a2, i.a(b3.b(), a3));
                    }
                }
            } else {
                j.b(a, "Ignoring entry event.  Already inside BeaconRegion [%s]", eVar);
            }
        } catch (Exception e2) {
            j.c(a, e2, "Proximity region (%s) was entered, but failed to check for associated message", eVar.a());
        }
    }

    public void b() {
        this.c.c();
        this.c.b(this);
        k j = this.b.j();
        j.a();
        this.b.k().a(3);
        j.c(3);
    }

    public void b(@NonNull com.salesforce.marketingcloud.proximity.e eVar) {
        j.a(a, "Proximity region (%s) exited.", eVar.a());
        k j = this.b.j();
        com.salesforce.marketingcloud.messages.e a2 = this.b.j().a(eVar.a(), this.b.a());
        if (a2 == null) {
            j.b(a, "BeaconRegion [%s] did not have matching Region in storage.", eVar);
        } else if (a2.l()) {
            a2.a(false);
            this.f.b(a2);
            j.a(a2.a(), false);
        } else {
            j.b(a, "Ignoring exit event.  Was not inside BeaconRegion [%s]", eVar);
        }
    }

    public void c() {
        j.c(a, "monitorStoredRegions", new Object[0]);
        try {
            List<com.salesforce.marketingcloud.messages.e> a2 = this.b.j().a(3, this.b.a());
            if (!a2.isEmpty()) {
                ArrayList arrayList = new ArrayList(a2.size());
                for (com.salesforce.marketingcloud.messages.e a3 : a2) {
                    arrayList.add(com.salesforce.marketingcloud.proximity.e.a(a3));
                }
                this.c.a((List<com.salesforce.marketingcloud.proximity.e>) arrayList);
            }
        } catch (Exception unused) {
            j.e(a, "Unable to monitor stored proximity regions.", new Object[0]);
        }
    }

    public boolean d() {
        return this.c.a();
    }
}
