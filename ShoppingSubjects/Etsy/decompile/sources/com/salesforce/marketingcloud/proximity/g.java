package com.salesforce.marketingcloud.proximity;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import com.salesforce.marketingcloud.i;
import com.salesforce.marketingcloud.j;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class g extends i {
    protected static final String a = j.a(g.class);

    public interface a {
        void a(@NonNull e eVar);

        void b(@NonNull e eVar);
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r4v4, types: [java.lang.Boolean] */
    /* JADX WARNING: type inference failed for: r9v0 */
    /* JADX WARNING: type inference failed for: r4v6, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 5 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.salesforce.marketingcloud.proximity.g a(android.content.Context r10, com.salesforce.marketingcloud.b r11) {
        /*
            boolean r0 = r11.h()
            r1 = 0
            if (r0 == 0) goto L_0x005c
            boolean r0 = d()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            boolean r2 = r0.booleanValue()
            if (r2 == 0) goto L_0x005a
            boolean r2 = a(r10)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            boolean r3 = r2.booleanValue()
            if (r3 == 0) goto L_0x0058
            r3 = 0
            java.lang.String r4 = "org.altbeacon.beacon.BeaconManager"
            java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException -> 0x0046 }
            com.salesforce.marketingcloud.proximity.c r4 = new com.salesforce.marketingcloud.proximity.c     // Catch:{ IllegalStateException -> 0x002f }
            r4.<init>(r10)     // Catch:{ IllegalStateException -> 0x002f }
            return r4
        L_0x002f:
            r10 = move-exception
            java.lang.String r4 = r10.getMessage()     // Catch:{ ClassNotFoundException -> 0x0046 }
            java.lang.String r5 = a     // Catch:{ ClassNotFoundException -> 0x0043 }
            java.lang.String r6 = "Unable to create real instance of %s"
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ ClassNotFoundException -> 0x0043 }
            java.lang.String r8 = "ProximityManager"
            r7[r3] = r8     // Catch:{ ClassNotFoundException -> 0x0043 }
            com.salesforce.marketingcloud.j.c(r5, r10, r6, r7)     // Catch:{ ClassNotFoundException -> 0x0043 }
            goto L_0x005f
        L_0x0043:
            r10 = move-exception
            r1 = r4
            goto L_0x0047
        L_0x0046:
            r10 = move-exception
        L_0x0047:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r3)
            java.lang.String r5 = a
            java.lang.String r6 = "If you wish to use proximity messenger then you need to add the AltBeacon dependency."
            java.lang.Object[] r3 = new java.lang.Object[r3]
            com.salesforce.marketingcloud.j.c(r5, r10, r6, r3)
            r9 = r4
            r4 = r1
            r1 = r9
            goto L_0x005f
        L_0x0058:
            r4 = r1
            goto L_0x005f
        L_0x005a:
            r2 = r1
            goto L_0x005e
        L_0x005c:
            r0 = r1
            r2 = r0
        L_0x005e:
            r4 = r2
        L_0x005f:
            com.salesforce.marketingcloud.proximity.f r10 = new com.salesforce.marketingcloud.proximity.f
            boolean r3 = r11.h()
            boolean r11 = r11.h()
            org.json.JSONObject r11 = a(r11, r2, r0, r1, r4)
            r10.<init>(r3, r11)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.proximity.g.a(android.content.Context, com.salesforce.marketingcloud.b):com.salesforce.marketingcloud.proximity.g");
    }

    private static JSONObject a(JSONObject jSONObject, boolean z) {
        jSONObject.put("proximityEnabled", z);
        return jSONObject;
    }

    protected static JSONObject a(boolean z, Boolean bool, Boolean bool2, Boolean bool3, String str) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject = a(jSONObject2, z);
            try {
                jSONObject.put("correctAndroidVersion", bool2);
                jSONObject.put("hardwareAvailable", bool);
                jSONObject.put("libraryDeclared", bool3);
                if (str != null) {
                    jSONObject.put("serviceMissing", str);
                    return jSONObject;
                }
            } catch (JSONException e) {
                e = e;
                j.c(a, e, "Error creating fake ProximityManager state.", new Object[0]);
                return jSONObject;
            }
        } catch (JSONException e2) {
            e = e2;
            jSONObject = jSONObject2;
            j.c(a, e, "Error creating fake ProximityManager state.", new Object[0]);
            return jSONObject;
        }
        return jSONObject;
    }

    @TargetApi(18)
    protected static boolean a(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    protected static boolean d() {
        return VERSION.SDK_INT >= 18;
    }

    public abstract void a(@NonNull a aVar);

    public abstract void a(List<e> list);

    public boolean a() {
        return false;
    }

    @NonNull
    public final String b() {
        return "ProximityManager";
    }

    public abstract void b(@NonNull a aVar);

    public abstract void c();
}
