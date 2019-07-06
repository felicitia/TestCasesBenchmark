package com.salesforce.marketingcloud.location;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v4.content.LocalBroadcastManager;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.b;
import com.google.android.gms.location.c;
import com.google.android.gms.location.e;
import com.salesforce.marketingcloud.e.d;
import com.salesforce.marketingcloud.j;
import java.util.ArrayList;
import java.util.List;

public class LocationReceiver extends BroadcastReceiver {
    private static final String a = "com.salesforce.marketingcloud.LOCATION_UPDATE";
    private static final String b = "com.salesforce.marketingcloud.GEOFENCE_TRIGGERED";
    private static final String c = j.a(LocationReceiver.class);

    private static int a(int i) {
        int i2 = 4;
        if (i != 4) {
            i2 = 1;
            switch (i) {
                case 1:
                    break;
                case 2:
                    return 2;
                default:
                    j.a(c, "Unknown geofence transition %d", Integer.valueOf(i));
                    return -1;
            }
        }
        return i2;
    }

    private static void a(Context context, LocationResult locationResult) {
        if (locationResult == null) {
            j.a(c, "LocationResult was null.", new Object[0]);
            return;
        }
        Location lastLocation = locationResult.getLastLocation();
        if (lastLocation == null) {
            j.a(c, "LastLocation was null.", new Object[0]);
        } else {
            LocalBroadcastManager.getInstance(context).sendBroadcast(k.a(lastLocation));
        }
    }

    private static void a(Context context, e eVar) {
        LocalBroadcastManager instance;
        Intent a2;
        if (eVar == null) {
            j.a(c, "Geofencing event was null.", new Object[0]);
            return;
        }
        if (eVar.a()) {
            String a3 = c.a(eVar.b());
            j.b(c, "Geofencing event contained error: %s", a3);
            instance = LocalBroadcastManager.getInstance(context);
            a2 = k.a(eVar.b(), a3);
        } else if (eVar.d() == null || eVar.d().isEmpty()) {
            j.b(c, "GeofencingEvent without triggering geofences.", new Object[0]);
            return;
        } else {
            int c2 = eVar.c();
            j.a(c, "Geofencing event transition: %d", Integer.valueOf(c2));
            a(c2);
            ArrayList arrayList = new ArrayList();
            for (b bVar : eVar.d()) {
                j.a(c, "Triggered fence id: %s", bVar.getRequestId());
                arrayList.add(bVar.getRequestId());
            }
            instance = LocalBroadcastManager.getInstance(context);
            a2 = k.a(a(c2), (List<String>) arrayList);
        }
        instance.sendBroadcast(a2);
    }

    static boolean a(Context context) {
        return d.b(context.getPackageManager(), new Intent(context, LocationReceiver.class));
    }

    static PendingIntent b(Context context) {
        return PendingIntent.getBroadcast(context, 0, new Intent(context, LocationReceiver.class).setAction(a), 134217728);
    }

    static PendingIntent c(Context context) {
        return PendingIntent.getBroadcast(context, 1, new Intent(context, LocationReceiver.class).setAction(b), 134217728);
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null) {
            j.a(c, "onReceive - %s", intent.getAction());
            if (com.salesforce.marketingcloud.c.c() || com.salesforce.marketingcloud.c.b()) {
                if (com.salesforce.marketingcloud.c.a() != null) {
                    String action = intent.getAction();
                    char c2 = 65535;
                    int hashCode = action.hashCode();
                    if (hashCode != 22603061) {
                        if (hashCode == 1768321718 && action.equals(a)) {
                            c2 = 0;
                        }
                    } else if (action.equals(b)) {
                        c2 = 1;
                    }
                    switch (c2) {
                        case 0:
                            a(context, LocationResult.extractResult(intent));
                            break;
                        case 1:
                            a(context, e.a(intent));
                            return;
                        default:
                            return;
                    }
                }
                return;
            }
            j.d(c, "MarketingCloudSdk#init must be called in your application's onCreate", new Object[0]);
        }
    }
}
