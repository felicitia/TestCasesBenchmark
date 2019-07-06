package com.salesforce.marketingcloud.messages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.util.ArraySet;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.a.a.C0160a;
import com.salesforce.marketingcloud.a.b.a;
import com.salesforce.marketingcloud.b.b;
import com.salesforce.marketingcloud.b.c;
import com.salesforce.marketingcloud.c.f;
import com.salesforce.marketingcloud.h;
import com.salesforce.marketingcloud.location.g;
import com.salesforce.marketingcloud.location.j;
import com.salesforce.marketingcloud.location.k;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.notifications.d;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@RestrictTo({Scope.LIBRARY})
public class n implements a, b, h, g, j, f, l.a, l.b {
    static final String a = com.salesforce.marketingcloud.j.a(n.class);
    private final com.salesforce.marketingcloud.a.b b;
    private final k c;
    private final com.salesforce.marketingcloud.proximity.g d;
    /* access modifiers changed from: private */
    public final com.salesforce.marketingcloud.d.h e;
    private final com.salesforce.marketingcloud.b f;
    private final String g;
    private final Context h;
    private final d i;
    private final c j;
    private final f k;
    private final Set<f.a> l = new ArraySet();
    private final Set<f.b> m = new ArraySet();
    private final Set<f.c> n = new ArraySet();
    private final AtomicBoolean o = new AtomicBoolean(false);
    private com.salesforce.marketingcloud.messages.b.d p;
    private com.salesforce.marketingcloud.messages.d.d q;

    @RestrictTo({Scope.LIBRARY})
    public n(@NonNull Context context, @NonNull com.salesforce.marketingcloud.b bVar, @NonNull com.salesforce.marketingcloud.d.h hVar, @NonNull String str, @NonNull k kVar, @NonNull com.salesforce.marketingcloud.proximity.g gVar, @NonNull c cVar, @NonNull com.salesforce.marketingcloud.a.b bVar2, @NonNull f fVar, @NonNull d dVar, f.c cVar2) {
        this.h = (Context) com.salesforce.marketingcloud.e.f.a(context, "Context was null");
        this.e = (com.salesforce.marketingcloud.d.h) com.salesforce.marketingcloud.e.f.a(hVar, "Storage was null");
        this.c = (k) com.salesforce.marketingcloud.e.f.a(kVar, "LocationManager was null");
        this.d = (com.salesforce.marketingcloud.proximity.g) com.salesforce.marketingcloud.e.f.a(gVar, "ProximityManager was null");
        this.i = (d) com.salesforce.marketingcloud.e.f.a(dVar, "NotificationManager was null");
        this.b = (com.salesforce.marketingcloud.a.b) com.salesforce.marketingcloud.e.f.a(bVar2, "AlarmScheduler was null");
        this.j = (c) com.salesforce.marketingcloud.e.f.a(cVar, "BehaviorManager was null");
        this.k = (f) com.salesforce.marketingcloud.e.f.a(fVar, "RequestManager was null");
        this.g = (String) com.salesforce.marketingcloud.e.f.a(str, "DeviceId was null");
        this.f = (com.salesforce.marketingcloud.b) com.salesforce.marketingcloud.e.f.a(bVar, "MarketingCloudConfig was null");
        this.n.add(com.salesforce.marketingcloud.e.f.a(cVar2, "RegionAnalyticEventListener is null."));
    }

    private void a(int i2, e eVar) {
        synchronized (this.n) {
            if (!this.n.isEmpty()) {
                for (f.c cVar : this.n) {
                    if (cVar != null) {
                        try {
                            cVar.a(i2, eVar);
                        } catch (Exception e2) {
                            com.salesforce.marketingcloud.j.c(a, e2, "%s threw an exception while processing the region (%s) transition (%d)", cVar.getClass().getName(), eVar.a(), Integer.valueOf(i2));
                        }
                    }
                }
            }
        }
    }

    private void a(com.salesforce.marketingcloud.location.b bVar) {
        if (!c() || this.p == null || bVar == null) {
            com.salesforce.marketingcloud.j.b(a, "Tried to update geofence messages, but was not enabled.", new Object[0]);
            return;
        }
        this.p.a(this.h, bVar, this.g, this.f, (l.b) this);
    }

    private void a(com.salesforce.marketingcloud.location.b bVar, int i2) {
        if (k()) {
            try {
                e.b bVar2 = new e.b(bVar, i2);
                this.e.j().a((e) bVar2, this.e.a());
                this.c.a(bVar2.m());
            } catch (Exception e2) {
                com.salesforce.marketingcloud.j.c(a, e2, "Unable to set magic region", new Object[0]);
            }
        }
    }

    private void b(com.salesforce.marketingcloud.location.b bVar) {
        if (!a() || this.q == null || bVar == null) {
            com.salesforce.marketingcloud.j.b(a, "Tried to update proximity messages, but was not enabled.", new Object[0]);
            return;
        }
        this.q.a(this.h, bVar, this.g, this.f, (l.b) this);
    }

    private void b(d dVar) {
        if (dVar instanceof com.salesforce.marketingcloud.messages.b.b) {
            synchronized (this.l) {
                if (!this.l.isEmpty()) {
                    for (f.a aVar : this.l) {
                        if (aVar != null) {
                            try {
                                aVar.a((com.salesforce.marketingcloud.messages.b.b) dVar);
                            } catch (Exception e2) {
                                com.salesforce.marketingcloud.j.c(a, e2, "%s threw an exception while processing the geofence response", aVar.getClass().getName());
                            }
                        }
                    }
                }
            }
        } else if (dVar instanceof com.salesforce.marketingcloud.messages.d.b) {
            synchronized (this.m) {
                if (!this.m.isEmpty()) {
                    for (f.b bVar : this.m) {
                        if (bVar != null) {
                            try {
                                bVar.a((com.salesforce.marketingcloud.messages.d.b) dVar);
                            } catch (Exception e3) {
                                com.salesforce.marketingcloud.j.c(a, e3, "%s threw an exception while processing the proximity response", bVar.getClass().getName());
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean b(boolean z) {
        if (!c(z)) {
            return false;
        }
        com.salesforce.marketingcloud.j.a(a, "Enabling proximity messaging.", new Object[0]);
        if (!z) {
            if (this.e != null) {
                this.e.e().edit().putBoolean("et_proximity_enabled_key", true).apply();
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("com.salesforce.marketingcloud.messaging.ENABLED", true);
            c.a(this.h, com.salesforce.marketingcloud.b.a.BEHAVIOR_CUSTOMER_PROXIMITY_MESSAGING_TOGGLED, bundle);
        } else {
            this.q.c();
        }
        this.q.a();
        return g();
    }

    private boolean c(boolean z) {
        if (!z && a()) {
            com.salesforce.marketingcloud.j.b(a, "Proximity messaging is already enabled.", new Object[0]);
            return false;
        } else if (!this.f.h() || this.q == null) {
            com.salesforce.marketingcloud.j.b(a, "Proximity messaging was not enabled while configuring the SDK.  Messaging will not be enabled.", new Object[0]);
            return false;
        } else if (!this.q.d() || !this.c.a()) {
            com.salesforce.marketingcloud.j.b(a, "Proximity messaging was not enabled due to device limitation.", new Object[0]);
            return false;
        } else if (k()) {
            return true;
        } else {
            com.salesforce.marketingcloud.j.b(a, "Missing %s", "android.permission.ACCESS_FINE_LOCATION");
            return false;
        }
    }

    private synchronized boolean d(boolean z) {
        if (!e(z)) {
            return false;
        }
        com.salesforce.marketingcloud.j.a(a, "Enabling geofence messaging", new Object[0]);
        if (!z) {
            if (this.e != null) {
                this.e.e().edit().putBoolean("et_geo_enabled_key", true).apply();
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("com.salesforce.marketingcloud.messaging.ENABLED", true);
            c.a(this.h, com.salesforce.marketingcloud.b.a.BEHAVIOR_CUSTOMER_FENCE_MESSAGING_TOGGLED, bundle);
        }
        this.p.a();
        return g();
    }

    private boolean e(boolean z) {
        if (!z && c()) {
            com.salesforce.marketingcloud.j.b(a, "Geofence messaging is already enabled", new Object[0]);
            return false;
        } else if (!this.f.g() || this.p == null) {
            com.salesforce.marketingcloud.j.b(a, "Geofence was not enabled while configuring the SDK.  Messaging will not be enabled", new Object[0]);
            return false;
        } else if (!this.p.d()) {
            com.salesforce.marketingcloud.j.b(a, "Geofence messaging was not enabled due to device limitation.", new Object[0]);
            return false;
        } else if (k()) {
            return true;
        } else {
            com.salesforce.marketingcloud.j.b(a, "Missing %s", "android.permission.ACCESS_FINE_LOCATION");
            return false;
        }
    }

    private void f() {
        d();
        e();
    }

    @SuppressLint({"MissingPermission"})
    private boolean g() {
        if (this.p == null && this.q == null) {
            return false;
        }
        if (!this.o.compareAndSet(false, true)) {
            return true;
        }
        try {
            this.c.a((j) this);
            return true;
        } catch (Exception e2) {
            com.salesforce.marketingcloud.j.c(a, e2, "Unable to request location update", new Object[0]);
            f();
            return false;
        }
    }

    private void h() {
        if (this.e != null) {
            a(this.e.h().a(this.e.a()));
        }
    }

    private void i() {
        if (this.e != null) {
            b(this.e.h().a(this.e.a()));
        }
    }

    private void j() {
        if (c() && e(true)) {
            this.p.c();
        }
        if (a() && c(true)) {
            this.q.c();
        }
    }

    private boolean k() {
        if (this.h == null) {
            return false;
        }
        return com.salesforce.marketingcloud.e.d.a(this.h, "android.permission.ACCESS_FINE_LOCATION");
    }

    public final synchronized void a(int i2) {
        if (com.salesforce.marketingcloud.d.b(i2, 32)) {
            e();
            this.p = null;
            com.salesforce.marketingcloud.messages.b.d.a(this.e, this.c, this.b, this.k, com.salesforce.marketingcloud.d.c(i2, 32));
            this.b.a(C0160a.FETCH_FENCE_MESSAGES, C0160a.FETCH_FENCE_MESSAGES_DAILY);
        } else if (this.p == null && this.f.g()) {
            b((InitializationStatus.a) null);
        }
        if (com.salesforce.marketingcloud.d.b(i2, 64)) {
            d();
            this.q = null;
            com.salesforce.marketingcloud.messages.d.d.a(this.e, this.d, this.b, this.k, com.salesforce.marketingcloud.d.c(i2, 64));
            this.b.a(C0160a.FETCH_BEACON_MESSAGES, C0160a.FETCH_BEACON_MESSAGES_DAILY);
        } else if (this.q == null && this.f.h()) {
            a((InitializationStatus.a) null);
        }
        if (com.salesforce.marketingcloud.d.b(i2, 96)) {
            this.c.b((g) this);
            this.c.b((j) this);
            this.j.a((b) this);
            this.e.h().a();
        } else {
            this.j.a((b) this, EnumSet.of(com.salesforce.marketingcloud.b.a.BEHAVIOR_DEVICE_BOOT_COMPLETE, com.salesforce.marketingcloud.b.a.BEHAVIOR_APP_PACKAGE_REPLACED, com.salesforce.marketingcloud.b.a.BEHAVIOR_DEVICE_SHUTDOWN, com.salesforce.marketingcloud.b.a.BEHAVIOR_APP_FOREGROUNDED));
            this.c.a((g) this);
        }
    }

    @RestrictTo({Scope.LIBRARY})
    public final void a(int i2, @Nullable String str) {
        com.salesforce.marketingcloud.j.b(a, "Region error %d - %s", Integer.valueOf(i2), str);
    }

    @RestrictTo({Scope.LIBRARY})
    public final void a(Location location) {
        this.o.set(false);
        if (location != null) {
            try {
                com.salesforce.marketingcloud.location.b a2 = com.salesforce.marketingcloud.location.b.a(location.getLatitude(), location.getLongitude());
                this.e.h().a(a2, this.e.a());
                a(a2, 5000);
                a(a2);
                b(a2);
            } catch (Exception e2) {
                com.salesforce.marketingcloud.j.c(a, e2, "Unable to make geofence message request after location update", new Object[0]);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(@Nullable InitializationStatus.a aVar) {
        com.salesforce.marketingcloud.messages.d.d dVar = new com.salesforce.marketingcloud.messages.d.d(this.e, this.d, this.b, this.k, this);
        this.q = dVar;
        this.b.a((a) this, C0160a.FETCH_BEACON_MESSAGES, C0160a.FETCH_BEACON_MESSAGES_DAILY);
        if (a()) {
            if (!b(true)) {
                d();
            }
            aVar.d(!k());
        }
    }

    public final synchronized void a(InitializationStatus.a aVar, int i2) {
        if (!com.salesforce.marketingcloud.d.a(i2, 32) || !this.f.g()) {
            this.p = null;
        } else {
            b(aVar);
        }
        if (!com.salesforce.marketingcloud.d.a(i2, 64) || !this.f.h()) {
            this.q = null;
        } else {
            a(aVar);
        }
        if (!(this.p == null && this.q == null)) {
            this.j.a((b) this, EnumSet.of(com.salesforce.marketingcloud.b.a.BEHAVIOR_DEVICE_BOOT_COMPLETE, com.salesforce.marketingcloud.b.a.BEHAVIOR_APP_PACKAGE_REPLACED, com.salesforce.marketingcloud.b.a.BEHAVIOR_DEVICE_SHUTDOWN, com.salesforce.marketingcloud.b.a.BEHAVIOR_APP_FOREGROUNDED));
            this.c.a((g) this);
        }
    }

    @RestrictTo({Scope.LIBRARY})
    public final void a(@NonNull C0160a aVar) {
        switch (aVar) {
            case FETCH_FENCE_MESSAGES:
            case FETCH_FENCE_MESSAGES_DAILY:
                h();
                return;
            case FETCH_BEACON_MESSAGES:
            case FETCH_BEACON_MESSAGES_DAILY:
                i();
                return;
            default:
                return;
        }
    }

    @RestrictTo({Scope.LIBRARY})
    public final void a(com.salesforce.marketingcloud.b.a aVar, Bundle bundle) {
        if (aVar != null) {
            switch (aVar) {
                case BEHAVIOR_DEVICE_BOOT_COMPLETE:
                    this.e.j().a();
                    break;
                case BEHAVIOR_APP_PACKAGE_REPLACED:
                    break;
                case BEHAVIOR_DEVICE_SHUTDOWN:
                    this.e.j().a();
                    return;
                case BEHAVIOR_APP_FOREGROUNDED:
                    h();
                    i();
                    return;
                default:
                    return;
            }
            j();
        }
    }

    @RestrictTo({Scope.LIBRARY})
    public final void a(d dVar) {
        if (dVar != null) {
            b(dVar);
            com.salesforce.marketingcloud.e.a a2 = this.e.a();
            e a3 = this.e.j().a(a2);
            if (a3 != null) {
                try {
                    if (dVar.b() != a3.c()) {
                    }
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.j.c(a, e2, "Failed to updated radius for magic region.", new Object[0]);
                }
                return;
            }
            e.b bVar = new e.b(dVar.a(), dVar.b());
            this.e.j().a((e) bVar, a2);
            this.c.a(bVar.m());
        }
    }

    @RestrictTo({Scope.LIBRARY})
    public void a(e eVar) {
        a(1, eVar);
    }

    @RestrictTo({Scope.LIBRARY})
    public final void a(e eVar, final c cVar) {
        if (eVar != null && cVar != null) {
            com.salesforce.marketingcloud.j.a(a, "showMessage(%s, %s)", eVar.a(), cVar.a());
            NotificationMessage a2 = NotificationMessage.a(this.i, cVar, eVar);
            if (a2 != null && k.a(cVar, this.e)) {
                try {
                    k.b(cVar, this.e);
                    this.i.a(a2, (d.a) new d.a() {
                        public void a(int i) {
                            if (i != -1) {
                                try {
                                    cVar.a(i);
                                    n.this.e.i().a(cVar, n.this.e.a());
                                } catch (Exception e) {
                                    com.salesforce.marketingcloud.j.c(n.a, e, "Unable to update message id with notification id.", new Object[0]);
                                }
                            }
                        }
                    });
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.j.c(a, e2, "Failed to show message", new Object[0]);
                }
            }
        }
    }

    @SuppressLint({"MissingPermission"})
    @RestrictTo({Scope.LIBRARY})
    public final void a(@NonNull String str, int i2) {
        if (i2 == 2 && "~~m@g1c_f3nc3~~".equals(str)) {
            com.salesforce.marketingcloud.j.a(a, "MagicRegion exited", new Object[0]);
            if (k()) {
                this.c.a((j) this);
            } else {
                com.salesforce.marketingcloud.j.b(a, "MagicRegion exited, but was missing location permission.", new Object[0]);
                f();
            }
        }
    }

    public void a(boolean z) {
    }

    public final boolean a() {
        return this.f.h() && this.e != null && this.e.e().getBoolean("et_proximity_enabled_key", false);
    }

    @NonNull
    public final String b() {
        return "RegionMessageManager";
    }

    @RestrictTo({Scope.LIBRARY})
    public final void b(int i2) {
        com.salesforce.marketingcloud.j.b(a, "onLocationError(%d)", Integer.valueOf(i2));
        this.o.set(false);
        f();
    }

    /* access modifiers changed from: 0000 */
    public void b(@Nullable InitializationStatus.a aVar) {
        com.salesforce.marketingcloud.messages.b.d dVar = new com.salesforce.marketingcloud.messages.b.d(this.e, this.c, this.b, this.k, this);
        this.p = dVar;
        this.b.a((a) this, C0160a.FETCH_FENCE_MESSAGES, C0160a.FETCH_FENCE_MESSAGES_DAILY);
        if (c()) {
            if (!d(true)) {
                e();
            }
            if (aVar != null) {
                aVar.d(!k());
            }
        }
    }

    @RestrictTo({Scope.LIBRARY})
    public void b(e eVar) {
        a(2, eVar);
    }

    public final boolean c() {
        return this.f.g() && this.e != null && this.e.e().getBoolean("et_geo_enabled_key", false);
    }

    public final synchronized void d() {
        com.salesforce.marketingcloud.j.b(a, "Diabling proximity messaging", new Object[0]);
        if (a()) {
            if (this.e != null) {
                this.e.e().edit().putBoolean("et_proximity_enabled_key", false).apply();
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("com.salesforce.marketingcloud.messaging.ENABLED", false);
            c.a(this.h, com.salesforce.marketingcloud.b.a.BEHAVIOR_CUSTOMER_PROXIMITY_MESSAGING_TOGGLED, bundle);
            if (this.q != null) {
                this.q.b();
            }
        }
    }

    public final synchronized void e() {
        com.salesforce.marketingcloud.j.b(a, "Diabling geofence messaging", new Object[0]);
        if (c()) {
            if (this.e != null) {
                this.e.e().edit().putBoolean("et_geo_enabled_key", false).apply();
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("com.salesforce.marketingcloud.messaging.ENABLED", false);
            c.a(this.h, com.salesforce.marketingcloud.b.a.BEHAVIOR_CUSTOMER_FENCE_MESSAGING_TOGGLED, bundle);
            if (this.p != null) {
                this.p.b();
            }
        }
    }
}
