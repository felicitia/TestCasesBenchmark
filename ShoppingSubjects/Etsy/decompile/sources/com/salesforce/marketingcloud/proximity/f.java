package com.salesforce.marketingcloud.proximity;

import android.support.annotation.NonNull;
import com.salesforce.marketingcloud.InitializationStatus.a;
import com.salesforce.marketingcloud.j;
import java.util.List;
import org.json.JSONObject;

class f extends g {
    private final boolean b;
    private final JSONObject c;

    public f(boolean z, JSONObject jSONObject) {
        this.b = z;
        this.c = jSONObject;
    }

    /* access modifiers changed from: protected */
    public void a(a aVar) {
        aVar.f(this.b);
    }

    public void a(@NonNull g.a aVar) {
        String str = a;
        String str2 = "registerProximityEventListener(%s) call ignored because of unsupported device.";
        Object[] objArr = new Object[1];
        objArr[0] = aVar != null ? aVar.getClass().getSimpleName() : "null";
        j.a(str, str2, objArr);
    }

    public void a(List<e> list) {
        j.a(a, "monitorBeaconRegions call ignored because of unsupported device.", new Object[0]);
    }

    public void b(@NonNull g.a aVar) {
        String str = a;
        String str2 = "unregisterProximityEventListener(%s) call ignored because of unsupported device.";
        Object[] objArr = new Object[1];
        objArr[0] = aVar != null ? aVar.getClass().getSimpleName() : "null";
        j.a(str, str2, objArr);
    }

    public void c() {
        j.a(a, "stopMonitoringBeaconRegions() call ignored because of unsupported device.", new Object[0]);
    }
}
