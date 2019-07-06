package com.salesforce.marketingcloud.location;

import com.salesforce.marketingcloud.InitializationStatus.a;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.j;
import org.json.JSONObject;

final class e extends k {
    private final JSONObject b;
    private final boolean c;
    private final Exception d;
    private final boolean e;
    private final boolean f;

    e(b bVar, boolean z, Exception exc) {
        this.e = bVar.g();
        this.f = bVar.h();
        this.c = z;
        this.d = exc;
        this.b = a(bVar, z, exc);
    }

    /* access modifiers changed from: protected */
    public void a(a aVar) {
        if (this.e || this.f) {
            aVar.a(true);
            if (this.d != null) {
                aVar.b(this.d.getMessage());
                if (this.d instanceof i) {
                    aVar.a(((i) this.d).a());
                    return;
                }
            } else if (!this.c) {
                aVar.b("SERVICE_NOT_DECLARED_IN_MANIFEST");
            }
            return;
        }
        aVar.a(false);
    }

    public void a(g gVar) {
        j.d(a, "LocationManager unavailable. registerForGeofenceRegionEvents ignored", new Object[0]);
    }

    public void a(j jVar) {
        j.d(a, "LocationManager unavailable. registerForLocationUpdate ignored", new Object[0]);
    }

    public void a(f... fVarArr) {
        j.d(a, "LocationManager unavailable. monitorGeofences ignored", new Object[0]);
    }

    public void a(String... strArr) {
        j.d(a, "LocationManager unavailable. unmonitorGeofences ignored", new Object[0]);
    }

    public void b(g gVar) {
        j.d(a, "LocationManager unavailable. unregisterForGeofenceRegionEvents ignored", new Object[0]);
    }

    public void b(j jVar) {
        j.d(a, "LocationManager unavailable. unregisterForLocationUpdate ignored", new Object[0]);
    }
}
