package com.etsy.android.ui.local;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.StringRes;
import com.etsy.android.R;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.legacy.b;
import com.etsy.android.lib.util.j;
import com.etsy.android.lib.util.x;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.g;
import com.google.android.gms.location.h;

/* compiled from: LocalLocationApiManager */
public class d implements ConnectionCallbacks, OnConnectionFailedListener, g {
    private static final String a = f.a(d.class);
    @Nullable
    private a b;
    @Nullable
    private Location c;
    @NonNull
    private GoogleApiClient d;
    private boolean e = false;

    /* compiled from: LocalLocationApiManager */
    interface a {
        void hideEnableLocationMessage();

        void onLocationReceived(Location location);

        void showEnableLocationMessage();

        void showGooglePlayErrorMessage(@StringRes int i);

        void showGooglePlayResolution(ConnectionResult connectionResult);

        void showWaitingForLocationMessage();
    }

    public void onConnectionSuspended(int i) {
    }

    public d(@NonNull Activity activity, @Nullable a aVar) {
        this.b = aVar;
        this.d = new Builder(activity).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(h.a).build();
    }

    public void a() {
        if (!this.e) {
            this.d.connect();
        }
    }

    public void b() {
        if (this.d.isConnected()) {
            h.b.a(this.d, this);
        }
        this.d.disconnect();
    }

    public void c() {
        this.b = null;
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"})
    public void d() {
        this.c = null;
        f.c(a, "refreshing Local location");
        if (this.d.isConnected()) {
            e();
        } else {
            a();
        }
    }

    public void onConnected(Bundle bundle) {
        this.e = false;
        e();
    }

    private void e() {
        if (this.b != null && this.d.getContext() != null && x.a(this.d.getContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 && x.a(this.d.getContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            Location a2 = h.b.a(this.d);
            if (a2 != null) {
                this.b.hideEnableLocationMessage();
                a(a2);
            } else if (!j.a(this.d.getContext().getApplicationContext())) {
                f.c(a, "Location not enabled on device");
                this.b.showEnableLocationMessage();
            } else {
                this.b.showWaitingForLocationMessage();
                f.c(a, "Connected to Google API with location services enabled, but no location available");
                b.a().b(a, "Connected to Google API with location services enabled, but no location available");
                LocationRequest locationRequest = new LocationRequest();
                locationRequest.setPriority(104);
                h.b.a(this.d, locationRequest, (g) this);
            }
        }
    }

    public void a(Location location) {
        if (location != null) {
            h.b.a(this.d, this);
            f.c(a, "New location obtained");
        }
        if (this.c == null && location != null && this.b != null) {
            EtsyApplication.get().getAnalyticsTracker().a("location_obtained", new LocalLocationApiManager$1(this, location));
            this.c = e.a(location);
            this.b.hideEnableLocationMessage();
            this.b.onLocationReceived(location);
        }
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        String str = a;
        StringBuilder sb = new StringBuilder();
        sb.append("Google API maps client failed. Connection result: ");
        sb.append(connectionResult);
        f.d(str, sb.toString());
        if (!this.e) {
            this.e = true;
            a(connectionResult);
        }
    }

    private void a(ConnectionResult connectionResult) {
        int i;
        if (this.b != null) {
            if (connectionResult.hasResolution()) {
                this.b.showGooglePlayResolution(connectionResult);
            } else {
                switch (connectionResult.getErrorCode()) {
                    case 2:
                        i = R.string.update_google_play_services;
                        break;
                    case 3:
                        i = R.string.enable_google_play_services;
                        break;
                    default:
                        i = R.string.get_google_play_services;
                        break;
                }
                this.b.showGooglePlayErrorMessage(i);
            }
        }
    }
}
