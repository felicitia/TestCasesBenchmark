package com.salesforce.marketingcloud.analytics.a;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.salesforce.marketingcloud.a.a.C0160a;
import com.salesforce.marketingcloud.a.b.a;
import com.salesforce.marketingcloud.analytics.e;
import com.salesforce.marketingcloud.analytics.i;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.c.d;
import com.salesforce.marketingcloud.c.f;
import com.salesforce.marketingcloud.d.h;
import com.salesforce.marketingcloud.e.g;
import com.salesforce.marketingcloud.j;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class c implements a, f.a {
    private static final String a = j.a(c.class);
    private final b b;
    private final String c;
    private final h d;
    private final f e;
    private final com.salesforce.marketingcloud.a.b f;

    public c(b bVar, String str, h hVar, f fVar, com.salesforce.marketingcloud.a.b bVar2) {
        this.b = (b) com.salesforce.marketingcloud.e.f.a(bVar, "Config is null");
        this.c = (String) com.salesforce.marketingcloud.e.f.a(str, "DeviceId is null");
        this.d = (h) com.salesforce.marketingcloud.e.f.a(hVar, "MCStorage is null");
        this.e = (f) com.salesforce.marketingcloud.e.f.a(fVar, "RequestManager is null");
        this.f = (com.salesforce.marketingcloud.a.b) com.salesforce.marketingcloud.e.f.a(bVar2, "AlarmScheduler is null");
        fVar.a(d.ET_ANALYTICS, (f.a) this);
        bVar2.a((a) this, C0160a.ET_ANALYTICS);
    }

    private JSONArray a(String str, String str2, List<e> list) {
        JSONArray jSONArray = new JSONArray();
        for (e eVar : list) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("etAppId", str);
                jSONObject.put("deviceId", str2);
                jSONObject.put("eventDate", g.a(eVar.b()));
                jSONObject.put(ResponseConstants.VALUE, eVar.e());
                jSONObject.put("analyticTypes", new JSONArray(Collections.singletonList(Integer.valueOf(eVar.d()))));
                jSONObject.put("objectIds", new JSONArray(eVar.f()));
                String i = eVar.i();
                if (!TextUtils.isEmpty(i)) {
                    jSONObject.put("requestId", i);
                }
                jSONArray.put(jSONObject);
            } catch (Exception e2) {
                j.c(a, e2, "Failed to update EtAnalyticItem or convert it to JSON for transmission.", new Object[0]);
            }
        }
        return jSONArray;
    }

    private void b() {
        List a2 = this.d.g().a();
        if (!a2.isEmpty()) {
            this.e.a(d.ET_ANALYTICS.a(this.b, a(this.b.b(), this.c, a2).toString()).a(i.a(a2)));
            return;
        }
        this.f.c(C0160a.ET_ANALYTICS);
    }

    public void a() {
        this.e.a(d.ET_ANALYTICS);
        this.f.c(C0160a.ET_ANALYTICS);
        this.f.a(C0160a.ET_ANALYTICS);
    }

    public void a(@NonNull C0160a aVar) {
        if (aVar == C0160a.ET_ANALYTICS) {
            b();
        }
    }

    public void a(com.salesforce.marketingcloud.c.e eVar, com.salesforce.marketingcloud.c.g gVar) {
        if (gVar.h()) {
            this.f.d(C0160a.ET_ANALYTICS);
            if (eVar.j() != null) {
                for (String parseInt : i.a(eVar.j())) {
                    this.d.g().a(Integer.parseInt(parseInt));
                }
                return;
            }
            return;
        }
        j.c(a, "Request failed: %d - %s", Integer.valueOf(gVar.c()), gVar.b());
        this.f.b(C0160a.ET_ANALYTICS);
    }
}
