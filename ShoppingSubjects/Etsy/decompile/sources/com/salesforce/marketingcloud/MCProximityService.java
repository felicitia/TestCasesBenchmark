package com.salesforce.marketingcloud;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.ArrayMap;
import com.salesforce.marketingcloud.proximity.e;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;

public class MCProximityService extends Service {
    private static final String a = "regions";
    private static final String b = j.a(MCProximityService.class);
    private static final String c = "m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24";
    private final Map<String, e> d = new ArrayMap();
    private List<e> e;
    private BeaconManager f;
    private BackgroundPowerSaver g;
    private Looper h;
    private a i;
    private int j;

    private final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            MCProximityService.this.a((Intent) message.obj, message.arg1);
        }
    }

    @RestrictTo({Scope.LIBRARY})
    public static Intent a(@NonNull Context context) {
        return new Intent(context, MCProximityService.class);
    }

    @RestrictTo({Scope.LIBRARY})
    public static Intent a(@NonNull Context context, @NonNull List<e> list) {
        Intent intent = new Intent(context, MCProximityService.class);
        if (list != null && !list.isEmpty()) {
            if (list instanceof ArrayList) {
                intent.putParcelableArrayListExtra(a, (ArrayList) list);
                return intent;
            }
            intent.putParcelableArrayListExtra(a, new ArrayList(list));
        }
        return intent;
    }

    private static Region a(e eVar) {
        return new Region(eVar.a(), Identifier.fromUuid(UUID.fromString(eVar.b())), Identifier.fromInt(eVar.c()), Identifier.fromInt(eVar.d()));
    }

    private void a(List<e> list) {
        this.e = list;
        if (this.f != null && this.f.isBound(this)) {
            j.a(b, "Already connected - start monitoring %d BeaconRegions", Integer.valueOf(list.size()));
            c();
        }
        try {
            j.a(b, "Connecting to BeaconManager service", new Object[0]);
            this.f = BeaconManager.getInstanceForApplication(this);
            this.g = new BackgroundPowerSaver(this);
            this.f.getBeaconParsers().add(new BeaconParser().setBeaconLayout(c));
            this.f.setBackgroundScanPeriod(5000);
            this.f.setBackgroundBetweenScanPeriod(10000);
            this.f.bind(this);
        } catch (Exception e2) {
            j.c(b, e2, "Issue encountered trying to monitor beacons.", new Object[0]);
            stopSelf(this.j);
        }
    }

    private void b() {
        if (this.f != null && this.f.isBound(this)) {
            d();
            this.f.unbind(this);
        }
        if (this.g != null) {
            getApplication().unregisterActivityLifecycleCallbacks(this.g);
        }
    }

    private void c() {
        if (this.f == null || !this.f.isBound(this)) {
            j.b(b, "Attempted to monitor before connecting.", new Object[0]);
            return;
        }
        d();
        if (this.e != null && !this.e.isEmpty()) {
            for (e eVar : this.e) {
                if (eVar != null) {
                    try {
                        this.f.startMonitoringBeaconsInRegion(a(eVar));
                        this.d.put(eVar.a(), eVar);
                    } catch (Exception e2) {
                        j.a(b, e2, "Failed to start monitoring %s", eVar);
                    }
                }
            }
            this.e = null;
        }
    }

    private void d() {
        j.a(b, "clearAllMonitoredRegions", new Object[0]);
        if (!this.d.isEmpty()) {
            j.a(b, "Stop monitoring %d BeaconRegions", Integer.valueOf(this.d.size()));
            for (e eVar : this.d.values()) {
                if (eVar != null) {
                    try {
                        this.f.stopMonitoringBeaconsInRegion(a(eVar));
                    } catch (Exception e2) {
                        j.a(b, e2, "Failed to stop monitoring %s", eVar);
                    }
                }
            }
            this.d.clear();
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    @VisibleForTesting
    public void a(@Nullable Intent intent, int i2) {
        this.j = i2;
        if (intent == null) {
            j.a(b, "onHandleIntent - intent was null", new Object[0]);
            stopSelf(this.j);
            return;
        }
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra(a);
        if (parcelableArrayListExtra == null || parcelableArrayListExtra.isEmpty()) {
            j.a(b, "clearMonitoredBeacons - nothing being monitored - stopping service.", new Object[0]);
            stopSelf(this.j);
            return;
        }
        a((List<e>) parcelableArrayListExtra);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public boolean a() {
        boolean z = false;
        if (c.c() || c.b()) {
            try {
                if (c.a() != null) {
                    z = true;
                }
                return z;
            } catch (Exception e2) {
                j.c(b, e2, "Failed while waiting for SDK to initialize", new Object[0]);
                return false;
            }
        } else {
            j.d(b, "MarketingCloudSdk#init must be called in your application's onCreate", new Object[0]);
            return false;
        }
    }

    public final void didDetermineStateForRegion(int i2, Region region) {
    }

    public final void didEnterRegion(Region region) {
        j.a(b, "didEnterRegion(%s)", region);
        if (!this.d.isEmpty() && region != null) {
            e eVar = (e) this.d.get(region.getUniqueId());
            if (eVar == null) {
                j.b(b, "Triggered region [%s] is not being monitored", region);
            } else if (a()) {
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("com.salesforce.marketingcloud.proximity.BEACON_REGION_ENTERED").putExtra("beaconRegion", eVar));
            }
        }
    }

    public void didExitRegion(Region region) {
        j.a(b, "didExitRegion(%s)", region);
        if (!this.d.isEmpty() && region != null) {
            e eVar = (e) this.d.get(region.getUniqueId());
            if (eVar == null) {
                j.b(b, "Triggered region [%s] is not being monitored", region);
            } else if (a()) {
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("com.salesforce.marketingcloud.proximity.BEACON_REGION_EXITED").putExtra("beaconRegion", eVar));
            }
        }
    }

    public final void onBeaconServiceConnect() {
        j.a(b, "onBeaconServiceConnect", new Object[0]);
        this.f.addMonitorNotifier(this);
        c();
    }

    @Nullable
    public final IBinder onBind(Intent intent) {
        return null;
    }

    public final void onCreate() {
        HandlerThread handlerThread = new HandlerThread("SFMC_ProximityService", 10);
        handlerThread.start();
        this.h = handlerThread.getLooper();
        this.i = new a(this.h);
    }

    public final void onDestroy() {
        this.h.quit();
        b();
    }

    public final int onStartCommand(Intent intent, int i2, int i3) {
        j.a(b, "onStartCommand i:[%s] f:[%d] id:[%d]", intent, Integer.valueOf(i2), Integer.valueOf(i3));
        Message obtainMessage = this.i.obtainMessage();
        obtainMessage.arg1 = i3;
        obtainMessage.obj = intent;
        this.i.sendMessage(obtainMessage);
        return 3;
    }
}
