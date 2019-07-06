package com.kount.api;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import java.util.Date;
import java.util.List;
import java.util.Locale;

class LocationCollector extends CollectorTaskBase implements LocationListener {
    private Location currentLocation;
    private boolean foundLocation;
    private final LocationManager locationManager;

    static String internalName() {
        return "collector_geo_loc";
    }

    /* access modifiers changed from: 0000 */
    public String getName() {
        return "Location Collector";
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    LocationCollector(Object obj, Context context) {
        super(obj);
        this.locationManager = (LocationManager) context.getSystemService("location");
    }

    /* access modifiers changed from: 0000 */
    public String getInternalName() {
        return internalName();
    }

    /* access modifiers changed from: 0000 */
    public void collect() {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        boolean isProviderEnabled = isProviderEnabled("gps");
        boolean isProviderEnabled2 = isProviderEnabled("network");
        boolean isProviderEnabled3 = isProviderEnabled("passive");
        if ((isProviderEnabled || isProviderEnabled2) && isProviderEnabled3) {
            List<String> locationProviders = getLocationProviders();
            if (locationProviders == null || locationProviders.isEmpty()) {
                debugMessage("Required providers not available for collection");
                addSoftError(SoftError.SERVICE_UNAVAILABLE.toString());
                callCompletionHandler(Boolean.valueOf(false), null);
                return;
            }
            Date date = new Date();
            for (String str : locationProviders) {
                debugMessage(String.format("Requesting last known location from provider %s", new Object[]{str}));
                checkLocation(getLastKnownLocation(str), str, date);
            }
            if (!this.foundLocation) {
                debugMessage("No last known location found, querying for location");
                if (isProviderEnabled) {
                    makeLocationRequest("gps");
                    checkLocation(getLastKnownLocation("gps"), "gps", date);
                }
                if (!this.foundLocation && isProviderEnabled2) {
                    makeLocationRequest("network");
                    checkLocation(getLastKnownLocation("network"), "network", date);
                }
                if (!this.foundLocation) {
                    callCompletionHandler(Boolean.valueOf(false), null);
                    return;
                }
            }
            wrapUpLocationCollection();
            return;
        }
        debugMessage("Required providers not available for collection");
        addSoftError(SoftError.SERVICE_UNAVAILABLE.toString());
        callCompletionHandler(Boolean.valueOf(false), null);
    }

    private void checkLocation(Location location, String str, Date date) {
        if (location != null) {
            debugMessage(String.format(Locale.US, "Got location for %s: %f, %f, %f", new Object[]{str, Double.valueOf(location.getLongitude()), Double.valueOf(location.getLatitude()), Float.valueOf(location.getAccuracy())}));
            long time = date.getTime() - location.getTime();
            debugMessage(String.format(Locale.US, "Age: %d", new Object[]{Long.valueOf(time)}));
            if (time < 86400000 && isBetterLocation(location, this.currentLocation)) {
                StringBuilder sb = new StringBuilder();
                sb.append(location.getProvider());
                sb.append(" is a better location provider");
                debugMessage(sb.toString());
                this.currentLocation = location;
                this.foundLocation = true;
                return;
            }
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(" found a null location");
        debugMessage(sb2.toString());
    }

    private boolean isBetterLocation(Location location, Location location2) {
        boolean z = true;
        if (location2 == null) {
            return true;
        }
        int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
        StringBuilder sb = new StringBuilder();
        sb.append("Accuracy delta is ");
        sb.append(accuracy);
        debugMessage(sb.toString());
        if (accuracy >= 0) {
            z = false;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public boolean isProviderEnabled(String str) {
        boolean z;
        try {
            z = this.locationManager.isProviderEnabled(str);
            if (z) {
                try {
                    debugMessage(String.format("%s provider exists and is enabled", new Object[]{str}));
                } catch (Exception unused) {
                    debugMessage(String.format("%s provider does not exist or is not enabled", new Object[]{str}));
                    return z;
                }
            } else {
                debugMessage(String.format("%s provider does not exist or is not enabled", new Object[]{str}));
            }
        } catch (Exception unused2) {
            z = false;
            debugMessage(String.format("%s provider does not exist or is not enabled", new Object[]{str}));
            return z;
        }
        return z;
    }

    private void wrapUpLocationCollection() {
        stopListeningForLocationUpdates();
        if (this.currentLocation != null) {
            addDataPoint(PostKey.LOCATION_LATITUDE.toString(), Double.toString(this.currentLocation.getLatitude()));
            addDataPoint(PostKey.LOCATION_LONGITUDE.toString(), Double.toString(this.currentLocation.getLongitude()));
            addDataPoint(PostKey.LOCATION_DATE.toString(), Long.toString(this.currentLocation.getTime() / 1000));
            this.foundLocation = true;
        } else {
            debugMessage("No Location found.");
        }
        callCompletionHandler(Boolean.valueOf(true), null);
    }

    private void stopListeningForLocationUpdates() {
        try {
            this.locationManager.removeUpdates(this);
        } catch (SecurityException e) {
            debugMessage(String.format("SecurityException: %s", new Object[]{e.getMessage()}));
        }
    }

    public void onLocationChanged(Location location) {
        if (isBetterLocation(location, this.currentLocation)) {
            this.currentLocation = location;
        }
        stopListeningForLocationUpdates();
        wrapUpLocationCollection();
    }

    /* access modifiers changed from: protected */
    public List<String> getLocationProviders() {
        return this.locationManager.getProviders(true);
    }

    /* access modifiers changed from: protected */
    public Location getLastKnownLocation(String str) {
        try {
            return this.locationManager.getLastKnownLocation(str);
        } catch (SecurityException e) {
            debugMessage(String.format("SecurityException: %s", new Object[]{e.getMessage()}));
            addSoftError(SoftError.PERMISSION_DENIED.toString());
            callCompletionHandler(Boolean.valueOf(false), null);
            return null;
        } catch (IllegalArgumentException e2) {
            debugMessage(String.format("IllegalArgumentException from %s", new Object[]{e2.getMessage()}));
            addSoftError(SoftError.LOCATION_COLLECTOR_UNAVAILABLE.toString());
            callCompletionHandler(Boolean.valueOf(false), null);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void makeLocationRequest(String str) {
        try {
            if (this.locationManager.isProviderEnabled(str)) {
                debugMessage(String.format("Requesting location from %s", new Object[]{str}));
                this.locationManager.requestSingleUpdate(str, this, null);
                return;
            }
            throw new IllegalArgumentException(String.format("Provider %s does not exist or is not enabled", new Object[]{str}));
        } catch (SecurityException e) {
            debugMessage(String.format("SecurityException: %s", new Object[]{e.getMessage()}));
            addSoftError(SoftError.PERMISSION_DENIED.toString());
            callCompletionHandler(Boolean.valueOf(false), null);
        } catch (IllegalArgumentException e2) {
            debugMessage(String.format("IllegalArgumentException from %s: %s", new Object[]{str, e2.getMessage()}));
            addSoftError(SoftError.LOCATION_COLLECTOR_UNAVAILABLE.toString());
            callCompletionHandler(Boolean.valueOf(false), null);
        }
    }
}
