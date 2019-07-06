package com.salesforce.marketingcloud.analytics.b;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.salesforce.marketingcloud.a.a.C0160a;
import com.salesforce.marketingcloud.a.b.a;
import com.salesforce.marketingcloud.analytics.e;
import com.salesforce.marketingcloud.analytics.i;
import com.salesforce.marketingcloud.analytics.l;
import com.salesforce.marketingcloud.analytics.m;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.c;
import com.salesforce.marketingcloud.c.d;
import com.salesforce.marketingcloud.c.f;
import com.salesforce.marketingcloud.c.g;
import com.salesforce.marketingcloud.d.h;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.http.entity.mime.MIME;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@RestrictTo({Scope.LIBRARY})
public final class j extends l implements a, com.salesforce.marketingcloud.analytics.j, m, f.a {
    private static final List<String> a = Collections.unmodifiableList(Arrays.asList(new String[]{"/qa/qa1s1/", "/qa/qa3s1/"}));
    private static final String b = com.salesforce.marketingcloud.j.a(j.class);
    private static final Map<String, String> c = Collections.unmodifiableMap(new ArrayMap() {
        {
            put(MIME.CONTENT_TYPE, "application/json; charset=utf-8");
            put("Connection", "close");
        }
    });
    private final Context d;
    private final b e;
    private final h f;
    private final com.salesforce.marketingcloud.a.b g;
    private final f h;
    private final com.salesforce.marketingcloud.d.a i;

    /* renamed from: com.salesforce.marketingcloud.analytics.b.j$2 reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[C0160a.values().length];

        static {
            try {
                a[C0160a.PI_ANALYTICS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public j(@NonNull Context context, @NonNull b bVar, @NonNull h hVar, @NonNull com.salesforce.marketingcloud.a.b bVar2, @NonNull f fVar) {
        this.d = (Context) com.salesforce.marketingcloud.e.f.a(context, "Context may not be null.");
        this.e = (b) com.salesforce.marketingcloud.e.f.a(bVar, "MarketingCloudConfig may not be null.");
        this.f = (h) com.salesforce.marketingcloud.e.f.a(hVar, "MCStorage may not be null.");
        this.g = (com.salesforce.marketingcloud.a.b) com.salesforce.marketingcloud.e.f.a(bVar2, "AlarmScheduler may not be null.");
        this.h = (f) com.salesforce.marketingcloud.e.f.a(fVar, "RequestManager may not be null.");
        this.i = hVar.g();
        fVar.a(d.PI_ANALYTICS, (f.a) this);
        bVar2.a((a) this, C0160a.PI_ANALYTICS);
    }

    static String a(JSONObject jSONObject, List<e> list) {
        JSONObject optJSONObject = jSONObject.optJSONObject("payload");
        String str = "{}";
        if (optJSONObject != null) {
            JSONArray jSONArray = new JSONArray();
            for (e eVar : list) {
                try {
                    if (eVar.h() != null) {
                        jSONArray.put(new JSONObject(eVar.h()));
                    }
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.j.c(b, e2, "Failed to add the PI AnalyticItem Event to the event list.", new Object[0]);
                }
            }
            if (jSONArray.length() > 0) {
                try {
                    optJSONObject.put("events", jSONArray);
                    str = jSONObject.toString();
                } catch (Exception e3) {
                    com.salesforce.marketingcloud.j.c(b, e3, "Failed to add the PI AnalyticItem Events to the payload.", new Object[0]);
                }
                optJSONObject.remove("events");
            }
        }
        return str;
    }

    public static void a(h hVar, com.salesforce.marketingcloud.a.b bVar, f fVar, boolean z) {
        if (z) {
            hVar.g().b(1);
        }
        bVar.c(C0160a.PI_ANALYTICS);
        fVar.a(d.PI_ANALYTICS);
    }

    private void a(String[] strArr, String str, String str2) {
        this.g.d(C0160a.PI_ANALYTICS);
        this.f.d().a("et_user_id_cache", str);
        this.f.d().a("et_session_id_cache", str2);
        if (strArr != null) {
            for (String parseInt : strArr) {
                try {
                    this.f.g().a(Integer.parseInt(parseInt));
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.j.c(b, e2, "Failed to delete transmitted PI Analytics from local storage.", new Object[0]);
                }
            }
        }
    }

    @VisibleForTesting
    static boolean a(b bVar, f fVar, Object[] objArr, Object[] objArr2, List<e> list, JSONObject jSONObject) {
        List[] a2;
        if (list.isEmpty()) {
            return false;
        }
        for (List list2 : a(list)) {
            fVar.a(d.PI_ANALYTICS.a(bVar, objArr, objArr2, a(jSONObject, list2), c).a(i.a(list2)));
        }
        return true;
    }

    @VisibleForTesting
    static List<e>[] a(List<e> list) {
        int size = list.size();
        List<e>[] listArr = new List[((int) Math.ceil(((double) size) / 100.0d))];
        int i2 = 0;
        int i3 = size;
        while (i3 > 0) {
            int i4 = i2 * 100;
            int i5 = i2 + 1;
            int i6 = i5 * 100;
            if (i6 > size) {
                i6 = i3 + i4;
            }
            listArr[i2] = list.subList(i4, i6);
            i3 -= 100;
            i2 = i5;
        }
        return listArr;
    }

    private void b() {
        if (this.f != null && this.f.g() != null) {
            Object[] c2 = c();
            Object[] d2 = d();
            JSONObject a2 = a();
            JSONObject jSONObject = a2;
            boolean a3 = a(this.e, this.h, c2, d2, this.f.g().b(this.f.a()), jSONObject);
            boolean a4 = a(this.e, this.h, c2, d2, this.f.g().a(this.f.a()), jSONObject);
            if (!a3 && !a4) {
                this.g.c(C0160a.PI_ANALYTICS);
            }
        }
    }

    private Object[] c() {
        if (a.contains(System.getProperty("com.salesforce.marketingcloud.ENV", null))) {
            return new Object[]{"stage."};
        }
        return new Object[]{""};
    }

    private Object[] d() {
        String property = System.getProperty("com.salesforce.marketingcloud.ENV", null);
        if (a.contains(property)) {
            return new Object[]{property};
        }
        return new Object[]{"/"};
    }

    private void e() {
        long j = this.f.e().getLong("et_background_time_cache", -1);
        if (j != -1) {
            this.f.e().edit().remove("et_background_time_cache").apply();
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(j);
            Calendar instance2 = Calendar.getInstance();
            instance2.add(12, -30);
            if (instance.before(instance2)) {
                this.f.d().a("et_session_id_cache");
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public JSONObject a() {
        c a2 = c.a();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("api_key", "849f26e2-2df6-11e4-ab12-14109fdc48df");
            jSONObject.put("app_id", this.e.b());
            String b2 = this.f.d().b("et_user_id_cache", null);
            if (!TextUtils.isEmpty(b2)) {
                jSONObject.put("user_id", b2);
            }
            String b3 = this.f.d().b("et_session_id_cache", null);
            if (!TextUtils.isEmpty(b3)) {
                jSONObject.put("session_id", b3);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("app_name", this.d.getPackageName());
            com.salesforce.marketingcloud.registration.d g2 = a2.g();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("device_id", g2.c());
            String a3 = g2.a();
            if (!TextUtils.isEmpty(a3)) {
                jSONObject3.put("email", a3);
            }
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("push_enabled", a2.f().c());
            jSONObject3.put("details", jSONObject4);
            if (a2.e().c() || a2.e().a()) {
                com.salesforce.marketingcloud.location.b a4 = this.f.h().a(this.f.a());
                if (a4 != null) {
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put(ResponseConstants.LATITUDE, a4.a());
                    jSONObject5.put(ResponseConstants.LONGITUDE, a4.b());
                    jSONObject3.put(ResponseConstants.LOCATION, jSONObject5);
                }
            }
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("manufacturer", Build.MANUFACTURER);
            jSONObject6.put(ResponseConstants.PLATFORM, "Android");
            jSONObject6.put(ResponseConstants.PLATFORM_VERSION, VERSION.RELEASE);
            jSONObject6.put("device_type", Build.MODEL);
            jSONObject3.put("device", jSONObject6);
            jSONObject2.put("user_info", jSONObject3);
            jSONObject.put("payload", jSONObject2);
            return jSONObject;
        } catch (JSONException e2) {
            com.salesforce.marketingcloud.j.c(b, e2, "Failed to construct PiWama payload JSON Object.", new Object[0]);
            return new JSONObject();
        }
    }

    public void a(long j) {
        Date date = new Date(j);
        e();
        if (!this.f.g().e(1)) {
            try {
                e a2 = e.a(date, 1, 5);
                a2.a(i.a(date, false, Collections.emptyList()).e().toString());
                this.i.a(a2, this.f.a());
            } catch (Exception e2) {
                com.salesforce.marketingcloud.j.c(b, e2, "Failed to create WamaItem for TimeInApp.", new Object[0]);
            }
        }
    }

    public void a(@NonNull C0160a aVar) {
        if (AnonymousClass2.a[aVar.ordinal()] == 1) {
            b();
        }
    }

    public void a(com.salesforce.marketingcloud.c.e eVar, g gVar) {
        C0160a[] aVarArr;
        com.salesforce.marketingcloud.a.b bVar;
        if (gVar.h()) {
            try {
                JSONObject jSONObject = new JSONObject(gVar.a());
                a(i.a(eVar.j()), jSONObject.getString("user_id"), jSONObject.getString("session_id"));
            } catch (Exception e2) {
                com.salesforce.marketingcloud.j.c(b, e2, "Error parsing response.", new Object[0]);
                bVar = this.g;
                aVarArr = new C0160a[]{C0160a.PI_ANALYTICS};
            }
        } else {
            com.salesforce.marketingcloud.j.c(b, "Request failed: %d - %s", Integer.valueOf(gVar.c()), gVar.b());
            bVar = this.g;
            aVarArr = new C0160a[]{C0160a.PI_ANALYTICS};
            bVar.b(aVarArr);
        }
    }

    public void a(boolean z) {
        if (this.g != null) {
            this.g.a(C0160a.PI_ANALYTICS);
        }
        if (this.h != null) {
            this.h.a(d.PI_ANALYTICS);
        }
    }

    public void b(long j) {
        this.f.e().edit().putLong("et_background_time_cache", j).apply();
        try {
            for (e eVar : this.i.c(this.f.a())) {
                int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(j - eVar.b().getTime());
                if (seconds > 0) {
                    eVar.b(seconds);
                    eVar.a(true);
                    this.i.b(eVar, this.f.a());
                }
            }
            e a2 = e.a(new Date(j), 1, 2);
            a2.a(true);
            a2.a(g.a(new Date(j)).d().toString());
            this.i.a(a2, this.f.a());
        } catch (Exception e2) {
            com.salesforce.marketingcloud.j.c(b, e2, "Failed to update our PiWama TimeInApp.", new Object[0]);
        }
        this.g.b(C0160a.PI_ANALYTICS);
    }

    public void b(@NonNull NotificationMessage notificationMessage) {
        List list;
        try {
            if (this.f.g().e(1)) {
                b(0);
            }
            Date date = new Date();
            String id = notificationMessage.id();
            String regionId = notificationMessage.regionId();
            if (TextUtils.isEmpty(regionId)) {
                list = Collections.singletonList(id);
            } else {
                list = Arrays.asList(new String[]{id, regionId});
            }
            e a2 = e.a(date, 1, 5, list, false);
            a2.a(i.a(date, true, a2.f()).e().toString());
            this.i.a(a2, this.f.a());
        } catch (Exception e2) {
            com.salesforce.marketingcloud.j.c(b, e2, "Failed to store our WamaItem for message opened.", new Object[0]);
        }
    }
}
