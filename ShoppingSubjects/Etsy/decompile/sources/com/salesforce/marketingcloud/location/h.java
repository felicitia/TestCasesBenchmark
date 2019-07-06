package com.salesforce.marketingcloud.location;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.b;
import com.salesforce.marketingcloud.j;
import java.util.Arrays;
import java.util.Set;

class h {
    /* access modifiers changed from: private */
    public static final String b = j.a(h.class);
    GoogleApiClient a;
    /* access modifiers changed from: private */
    public final Set<a> c;
    private final Context d;
    /* access modifiers changed from: private */
    public volatile boolean e;
    /* access modifiers changed from: private */
    public int f;
    /* access modifiers changed from: private */
    public String g;

    interface a {
        void a();

        void a(int i);
    }

    h(Context context) {
        this.d = context;
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        this.f = instance.isGooglePlayServicesAvailable(context);
        this.g = instance.getErrorString(this.f);
        if (this.f == 0 || instance.isUserResolvableError(this.f)) {
            this.c = new ArraySet();
            return;
        }
        throw new i(this.f, instance.getErrorString(this.f));
    }

    private static b a(@NonNull f fVar) {
        int i = 1;
        if ((fVar.e() & 1) != 1) {
            i = 0;
        }
        if ((fVar.e() & 2) == 2) {
            i |= 2;
        }
        if ((fVar.e() & 4) == 4) {
            i |= 4;
        }
        return new com.google.android.gms.location.b.a().a(fVar.a()).a(fVar.c(), fVar.d(), fVar.b()).a(i).a(-1).a();
    }

    /* access modifiers changed from: 0000 */
    public int a() {
        return this.f;
    }

    /* access modifiers changed from: 0000 */
    public void a(@NonNull a aVar) {
        if (aVar != null) {
            j.a(b, "GoogleApiClient connection request ...", new Object[0]);
            if (this.a != null) {
                if (this.a.isConnected()) {
                    j.a(b, "Already connected.", new Object[0]);
                    if (aVar != null) {
                        aVar.a();
                        return;
                    }
                } else if (this.a.isConnecting()) {
                    j.a(b, "Already connecting. Adding %s to list to be notified when complete", aVar.getClass().getSimpleName());
                    synchronized (this.c) {
                        this.c.add(aVar);
                    }
                    return;
                }
                return;
            }
            synchronized (this.c) {
                this.c.add(aVar);
            }
            this.a = new Builder(this.d).addApi(com.google.android.gms.location.h.a).addConnectionCallbacks(new ConnectionCallbacks() {
                public void onConnected(@Nullable Bundle bundle) {
                    j.a(h.b, "GoogleApiClient onConnected()", new Object[0]);
                    h.this.f = 0;
                    h.this.g = "SUCCESS";
                    synchronized (h.this.c) {
                        for (a aVar : h.this.c) {
                            if (aVar != null) {
                                aVar.a();
                            }
                        }
                        h.this.c.clear();
                    }
                }

                public void onConnectionSuspended(int i) {
                    String g = h.b;
                    String str = "onConnectionSuspended(%s)";
                    Object[] objArr = new Object[1];
                    objArr[0] = i == 2 ? "CAUSE_NETWORK_LOST" : "CAUSE_SERVICE_DISCONNECTED";
                    j.a(g, str, objArr);
                }
            }).addOnConnectionFailedListener(new OnConnectionFailedListener() {
                public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                    j.a(h.b, "Failed to connect to play service. %s", connectionResult.toString());
                    h.this.f = connectionResult.getErrorCode();
                    h.this.g = connectionResult.getErrorMessage();
                    synchronized (h.this.c) {
                        for (a aVar : h.this.c) {
                            if (aVar != null) {
                                aVar.a(h.this.f);
                            }
                        }
                        h.this.c.clear();
                    }
                }
            }).build();
            this.a.connect();
        }
    }

    /* access modifiers changed from: 0000 */
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public void a(f... fVarArr) {
        String str;
        String str2;
        if (fVarArr == null || fVarArr.length == 0) {
            str = b;
            str2 = "No GeofenceRegions provided";
        } else if (this.a == null || !this.a.isConnected()) {
            str = b;
            str2 = "Not connected.  Call connect and wait for response.";
        } else {
            PendingIntent c2 = LocationReceiver.c(this.d);
            com.google.android.gms.location.GeofencingRequest.a a2 = new com.google.android.gms.location.GeofencingRequest.a().a(1);
            for (f fVar : fVarArr) {
                j.a(b, "Adding %s to geofence request", fVar.a());
                a2.a(a(fVar));
            }
            try {
                com.google.android.gms.location.h.c.a(this.a, a2.a(), c2).setResultCallback(new ResultCallback<Status>() {
                    /* renamed from: a */
                    public void onResult(@NonNull Status status) {
                        j.a(h.b, "GeofencingApi result: %s", status);
                    }
                });
                return;
            } catch (SecurityException e2) {
                j.c(b, e2, "ACCESS_FINE_LOCATION needed to request location.", new Object[0]);
                throw e2;
            }
        }
        j.a(str, str2, new Object[0]);
    }

    /* access modifiers changed from: 0000 */
    public void a(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            j.a(b, "No GeofenceRegions provided", new Object[0]);
        } else if (this.a == null || !this.a.isConnected()) {
            j.a(b, "Not connected.  Call connect and wait for response.", new Object[0]);
        } else {
            com.google.android.gms.location.h.c.a(this.a, Arrays.asList(strArr));
        }
    }

    /* access modifiers changed from: 0000 */
    public String b() {
        return this.g;
    }

    /* access modifiers changed from: 0000 */
    public boolean c() {
        return this.f == 0;
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        if (this.a != null && this.a.isConnected()) {
            this.c.clear();
            this.a.disconnect();
        }
    }

    /* access modifiers changed from: 0000 */
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public void e() {
        if (this.a == null || !this.a.isConnected()) {
            j.a(b, "Not Connected.  Call connect and wait for response.", new Object[0]);
            return;
        }
        synchronized (this) {
            if (this.e) {
                j.a(b, "Request already being made.", new Object[0]);
                return;
            }
            this.e = true;
            try {
                com.google.android.gms.location.h.b.a(this.a, LocationRequest.create().setNumUpdates(1).setPriority(100), LocationReceiver.b(this.d)).setResultCallback(new ResultCallback<Status>() {
                    /* renamed from: a */
                    public void onResult(@NonNull Status status) {
                        j.a(h.b, "FusedLocationApi result: %s", status);
                        h.this.e = false;
                    }
                });
            } catch (SecurityException e2) {
                j.c(b, e2, "ACCESS_FINE_LOCATION needed to request location.", new Object[0]);
                this.e = false;
                throw e2;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void f() {
        if (this.a == null || !this.a.isConnected()) {
            j.a(b, "Not connected.  Call connect and wait for response.", new Object[0]);
        } else {
            com.google.android.gms.location.h.c.a(this.a, LocationReceiver.c(this.d));
        }
    }
}
