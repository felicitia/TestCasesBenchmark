package com.salesforce.marketingcloud.location;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.i;
import com.salesforce.marketingcloud.j;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@RestrictTo({Scope.LIBRARY})
public abstract class k extends i {
    protected static final String a = j.a(k.class);

    @RestrictTo({Scope.LIBRARY})
    public static Intent a(int i, String str) {
        return new Intent("com.salesforce.marketingcloud.location.GEOFENCE_ERROR").putExtra("extra_error_code", i).putExtra("extra_error_message", str);
    }

    @RestrictTo({Scope.LIBRARY})
    public static Intent a(int i, @NonNull List<String> list) {
        Intent intent = new Intent("com.salesforce.marketingcloud.location.GEOFENCE_EVENT");
        intent.putExtra("extra_transition", i);
        if (list instanceof ArrayList) {
            intent.putStringArrayListExtra("extra_fence_ids", (ArrayList) list);
            return intent;
        }
        intent.putStringArrayListExtra("extra_fence_ids", new ArrayList(list));
        return intent;
    }

    public static Intent a(@NonNull Location location) {
        return new Intent("com.salesforce.marketingcloud.location.LOCATION_UPDATE").putExtra("extra_location", location);
    }

    public static k a(Context context, b bVar) {
        Exception e;
        boolean a2 = LocationReceiver.a(context);
        if (!a2 || (!bVar.g() && !bVar.h())) {
            e = null;
        } else {
            try {
                return new l(context, bVar);
            } catch (Exception e2) {
                e = e2;
                j.c(a, e, "Unable to create real instance of %s", "LocationManager");
            }
        }
        return new e(bVar, a2, e);
    }

    protected static JSONObject a(b bVar, boolean z, Exception exc) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject = a(jSONObject2, bVar);
            try {
                jSONObject.put("serviceAvailable", z);
                if (exc != null) {
                    jSONObject.put("exceptionMessage", exc.getMessage());
                    return jSONObject;
                }
            } catch (Exception e) {
                e = e;
                j.c(a, e, "Error creating fake LocationManager state.", new Object[0]);
                return jSONObject;
            }
        } catch (Exception e2) {
            e = e2;
            jSONObject = jSONObject2;
            j.c(a, e, "Error creating fake LocationManager state.", new Object[0]);
            return jSONObject;
        }
        return jSONObject;
    }

    private static JSONObject a(JSONObject jSONObject, b bVar) {
        jSONObject.put("geofencingEnabled", bVar.g());
        jSONObject.put("proximityEnabled", bVar.h());
        return jSONObject;
    }

    public abstract void a(g gVar);

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public abstract void a(j jVar);

    public abstract void a(f... fVarArr);

    public abstract void a(String... strArr);

    public boolean a() {
        return false;
    }

    @NonNull
    public final String b() {
        return "LocationManager";
    }

    public abstract void b(g gVar);

    public abstract void b(j jVar);
}
