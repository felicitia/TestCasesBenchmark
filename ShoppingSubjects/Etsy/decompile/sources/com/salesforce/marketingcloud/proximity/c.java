package com.salesforce.marketingcloud.proximity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.ArraySet;
import com.salesforce.marketingcloud.MCProximityService;
import com.salesforce.marketingcloud.e.d;
import com.salesforce.marketingcloud.e.f;
import com.salesforce.marketingcloud.e.g;
import com.salesforce.marketingcloud.j;
import java.util.List;
import java.util.Set;
import org.altbeacon.beacon.service.BeaconService;

class c extends g {
    private final Context b;
    private final Set<com.salesforce.marketingcloud.proximity.g.a> c = new ArraySet();
    private BroadcastReceiver d;
    private b e;
    private int f;
    private int g;

    private class a extends BroadcastReceiver {
        private a() {
        }

        public void onReceive(Context context, Intent intent) {
            String str;
            String str2;
            if (intent == null) {
                str = g.a;
                str2 = "Received null intent.";
            } else {
                String action = intent.getAction();
                if (action == null) {
                    str = g.a;
                    str2 = "Received null action";
                } else {
                    char c = 65535;
                    int hashCode = action.hashCode();
                    if (hashCode != 351071323) {
                        if (hashCode == 1959909049 && action.equals("com.salesforce.marketingcloud.proximity.BEACON_REGION_EXITED")) {
                            c = 1;
                        }
                    } else if (action.equals("com.salesforce.marketingcloud.proximity.BEACON_REGION_ENTERED")) {
                        c = 0;
                    }
                    switch (c) {
                        case 0:
                            c.this.a((e) intent.getParcelableExtra("beaconRegion"));
                            return;
                        case 1:
                            c.this.b((e) intent.getParcelableExtra("beaconRegion"));
                            return;
                        default:
                            j.b(g.a, "Received unknown action: ", action);
                            return;
                    }
                }
            }
            j.a(str, str2, new Object[0]);
        }
    }

    public c(@NonNull Context context) {
        f.a(context, "Context is null");
        this.b = context;
        if (!d.a(context.getPackageManager(), new Intent(context, BeaconService.class))) {
            throw new IllegalStateException("AltBeacon service not found");
        } else if (g.a() && g.a(context)) {
            this.e = new b(context);
        } else if (!d.a(context.getPackageManager(), MCProximityService.a(context))) {
            throw new IllegalStateException("ProximityService not found");
        }
    }

    /* access modifiers changed from: protected */
    public void a(com.salesforce.marketingcloud.InitializationStatus.a aVar) {
        aVar.f(false);
        this.d = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.salesforce.marketingcloud.proximity.BEACON_REGION_ENTERED");
        intentFilter.addAction("com.salesforce.marketingcloud.proximity.BEACON_REGION_EXITED");
        LocalBroadcastManager.getInstance(this.b).registerReceiver(this.d, intentFilter);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void a(e eVar) {
        synchronized (this.c) {
            this.f++;
            if (!this.c.isEmpty() && eVar != null) {
                j.c(a, "Entered %s", eVar);
                for (com.salesforce.marketingcloud.proximity.g.a aVar : this.c) {
                    if (aVar != null) {
                        aVar.a(eVar);
                    }
                }
            }
        }
    }

    public void a(@NonNull com.salesforce.marketingcloud.proximity.g.a aVar) {
        synchronized (this.c) {
            if (aVar != null) {
                try {
                    this.c.add(aVar);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void a(List<e> list) {
        j.c(a, "monitorBeaconRegions(%d region)", Integer.valueOf(list.size()));
        if (!(this.b == null || list == null)) {
            if (this.e != null) {
                this.e.a(list);
            } else if (this.b.startService(MCProximityService.a(this.b, list)) == null) {
                j.b(a, "ProximityService not found.  Unable to monitor BeaconRegions.", new Object[0]);
            }
        }
    }

    public void a(boolean z) {
        c();
        if (this.b != null && this.d != null) {
            LocalBroadcastManager.getInstance(this.b).unregisterReceiver(this.d);
        }
    }

    public boolean a() {
        return true;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void b(e eVar) {
        synchronized (this.c) {
            this.g++;
            if (!this.c.isEmpty() && eVar != null) {
                j.c(a, "Exited %s", eVar);
                for (com.salesforce.marketingcloud.proximity.g.a aVar : this.c) {
                    if (aVar != null) {
                        aVar.b(eVar);
                    }
                }
            }
        }
    }

    public void b(@NonNull com.salesforce.marketingcloud.proximity.g.a aVar) {
        synchronized (this.c) {
            this.c.remove(aVar);
        }
    }

    public void c() {
        if (this.e != null) {
            this.e.a();
            return;
        }
        if (this.b != null && this.b.stopService(MCProximityService.a(this.b))) {
            j.a(a, "Stopping ProximityService.", new Object[0]);
        }
    }
}
