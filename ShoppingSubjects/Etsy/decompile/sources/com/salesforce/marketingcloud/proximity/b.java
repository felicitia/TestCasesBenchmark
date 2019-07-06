package com.salesforce.marketingcloud.proximity;

import android.app.Application;
import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.ArrayMap;
import com.salesforce.marketingcloud.j;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;

class b implements BeaconConsumer, MonitorNotifier {
    private static final String a = j.a(b.class);
    private final BeaconManager b;
    private final Context c;
    private final List<e> d = new ArrayList();
    private final Map<String, Region> e = new ArrayMap();
    private final LocalBroadcastManager f;
    private boolean g;
    private boolean h;
    private BackgroundPowerSaver i;

    b(Context context) {
        this.c = context;
        this.f = LocalBroadcastManager.getInstance(context);
        this.b = BeaconManager.getInstanceForApplication(context);
        this.b.setEnableScheduledScanJobs(true);
        this.b.getBeaconParsers().add(new BeaconParser("iBeacon").setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));
        this.b.setBackgroundScanPeriod(5000);
        this.b.setBackgroundBetweenScanPeriod(10000);
        this.b.addMonitorNotifier(this);
    }

    @VisibleForTesting
    static Region a(e eVar) {
        return new Region(eVar.a(), Identifier.fromUuid(UUID.fromString(eVar.b())), Identifier.fromInt(eVar.c()), Identifier.fromInt(eVar.d()));
    }

    private void c() {
        this.h = true;
        this.b.bind(this);
        j.b(a, "Waiting for BeaconService connection", new Object[0]);
    }

    private void d() {
        j.a(a, "clearAllMonitoredRegions", new Object[0]);
        if (!this.e.isEmpty()) {
            j.a(a, "Stop monitoring %d BeaconRegions", Integer.valueOf(this.e.size()));
            for (Region region : this.e.values()) {
                if (region != null) {
                    try {
                        this.b.stopMonitoringBeaconsInRegion(region);
                    } catch (Exception e2) {
                        j.a(a, e2, "Failed to stop monitoring %s", region);
                    }
                }
            }
            this.e.clear();
        }
    }

    public void a() {
        j.b(a, "stopMonitoring()", new Object[0]);
        synchronized (this.d) {
            if (this.g) {
                d();
                this.b.unbind(this);
                this.b.removeMonitorNotifier(this);
                if (this.i != null) {
                    ((Application) this.c.getApplicationContext()).unregisterActivityLifecycleCallbacks(this.i);
                }
                this.g = false;
            } else {
                this.d.clear();
            }
        }
    }

    public void a(@NonNull List<e> list) {
        j.b(a, "monitorBeaconRegions() - [%d regions]", Integer.valueOf(list.size()));
        if (!list.isEmpty()) {
            synchronized (this.d) {
                this.d.clear();
                this.d.addAll(list);
                if (this.g) {
                    b();
                } else {
                    j.a(a, "Not yet connected.  Will register Beacons once complete.", new Object[0]);
                    if (!this.h) {
                        c();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        d();
        if (this.d != null && !this.d.isEmpty()) {
            for (e eVar : this.d) {
                try {
                    Region a2 = a(eVar);
                    this.e.put(eVar.a(), a2);
                    j.a(a, "Now monitoring [%s]", eVar.toString());
                    this.b.startMonitoringBeaconsInRegion(a2);
                } catch (RemoteException e2) {
                    j.c(a, e2, "Unable to monitor region [%s]", eVar.toString());
                }
            }
            this.d.clear();
        }
    }
}
