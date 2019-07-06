package com.android.riskifiedbeacon;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import com.android.riskifiedbeacon.RxBeaconInterface.RXCoordinate;

public class RxLocationListener implements LocationListener {
    RxBeacon RXBeacon;

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public RxLocationListener(RxBeacon rxBeacon) {
        this.RXBeacon = rxBeacon;
    }

    public void onLocationChanged(Location location) {
        this.RXBeacon.gotLocationInfo(new RXCoordinate(Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude())));
    }
}
