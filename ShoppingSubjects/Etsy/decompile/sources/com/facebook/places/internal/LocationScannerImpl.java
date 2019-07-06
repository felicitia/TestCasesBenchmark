package com.facebook.places.internal;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.HandlerThread;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.internal.aa;
import com.facebook.places.internal.ScannerException.Type;
import java.util.ArrayList;
import java.util.List;

public class LocationScannerImpl implements LocationListener {
    private static final float MIN_DISTANCE_BETWEEN_UPDATES = 0.0f;
    private static final long MIN_TIME_BETWEEN_UPDATES = 100;
    private Context context;
    private List<String> enabledProviders;
    private Location freshLocation;
    private LocationManager locationManager;
    private a params;
    private final Object scanLock = new Object();

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public LocationScannerImpl(Context context2, a aVar) {
        this.context = context2;
        this.params = aVar;
        this.locationManager = (LocationManager) context2.getSystemService(ResponseConstants.LOCATION);
    }

    public void initAndCheckEligibility() throws ScannerException {
        String[] a;
        if (!aa.b(this.context)) {
            throw new ScannerException(Type.PERMISSION_DENIED);
        }
        this.enabledProviders = new ArrayList(this.params.a().length);
        for (String str : this.params.a()) {
            if (this.locationManager.isProviderEnabled(str)) {
                this.enabledProviders.add(str);
            }
        }
        if (this.enabledProviders.isEmpty()) {
            throw new ScannerException(Type.DISABLED);
        }
    }

    private Location getLastLocation(String str) {
        Location lastKnownLocation = this.locationManager.getLastKnownLocation(str);
        if (lastKnownLocation != null) {
            if (System.currentTimeMillis() - lastKnownLocation.getTime() < this.params.d()) {
                return lastKnownLocation;
            }
        }
        return null;
    }

    public Location getLocation() throws ScannerException {
        for (String lastLocation : this.enabledProviders) {
            Location lastLocation2 = getLastLocation(lastLocation);
            if (lastLocation2 != null) {
                return lastLocation2;
            }
        }
        return getFreshLocation();
    }

    /* JADX INFO: finally extract failed */
    private Location getFreshLocation() throws ScannerException {
        this.freshLocation = null;
        HandlerThread handlerThread = new HandlerThread("LocationScanner");
        try {
            handlerThread.start();
            for (String requestLocationUpdates : this.enabledProviders) {
                this.locationManager.requestLocationUpdates(requestLocationUpdates, MIN_TIME_BETWEEN_UPDATES, 0.0f, this, handlerThread.getLooper());
            }
            try {
                synchronized (this.scanLock) {
                    this.scanLock.wait(this.params.c());
                }
            } catch (Exception unused) {
            }
            this.locationManager.removeUpdates(this);
            handlerThread.quit();
            if (this.freshLocation != null) {
                return this.freshLocation;
            }
            throw new ScannerException(Type.TIMEOUT);
        } catch (Throwable th) {
            this.locationManager.removeUpdates(this);
            handlerThread.quit();
            throw th;
        }
    }

    public void onLocationChanged(Location location) {
        if (this.freshLocation == null && location.getAccuracy() < this.params.b()) {
            synchronized (this.scanLock) {
                this.freshLocation = location;
                this.scanLock.notify();
            }
        }
    }
}
