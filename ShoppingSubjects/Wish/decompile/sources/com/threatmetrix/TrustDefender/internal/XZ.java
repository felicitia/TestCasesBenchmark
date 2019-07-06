package com.threatmetrix.TrustDefender.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.HandlerThread;

public class XZ implements com.threatmetrix.TrustDefender.internal.XU.L {
    /* access modifiers changed from: private */

    /* renamed from: do reason: not valid java name */
    public static final String f681do = TL.m331if(XZ.class);

    /* renamed from: break reason: not valid java name */
    private final HandlerThread f682break = new HandlerThread("THMLocationHandler");

    /* renamed from: byte reason: not valid java name */
    private boolean f683byte = false;

    /* renamed from: case reason: not valid java name */
    private LocationManager f684case;

    /* renamed from: char reason: not valid java name */
    private com.threatmetrix.TrustDefender.internal.K7.O f685char;

    /* renamed from: else reason: not valid java name */
    private boolean f686else = false;

    /* renamed from: for reason: not valid java name */
    private int f687for;

    /* renamed from: goto reason: not valid java name */
    private U6 f688goto;

    /* renamed from: if reason: not valid java name */
    private boolean f689if = false;

    /* renamed from: int reason: not valid java name */
    private long f690int;

    /* renamed from: long reason: not valid java name */
    private MR f691long;

    /* renamed from: new reason: not valid java name */
    private long f692new;

    /* renamed from: try reason: not valid java name */
    private Context f693try;

    /* renamed from: void reason: not valid java name */
    private int f694void = L.f696for;

    class I extends BroadcastReceiver {
        I() {
        }

        public final void onReceive(Context context, Intent intent) {
            if ("android.intent.action.BATTERY_LOW".equals(intent.getAction())) {
                XZ.this.m442new();
            } else {
                XZ.this.m440int();
            }
        }
    }

    enum L {
        ;
        

        /* renamed from: for reason: not valid java name */
        public static final int f696for = 1;

        /* renamed from: if reason: not valid java name */
        public static final int f697if = 2;

        /* renamed from: int reason: not valid java name */
        public static final int f698int = 3;

        static {
            f699new = new int[]{f696for, f697if, f698int};
        }

        public static int[] values$21109e51() {
            return (int[]) f699new.clone();
        }
    }

    class O extends BroadcastReceiver {
        O() {
        }

        public final void onReceive(Context context, Intent intent) {
            if (T.m178new()) {
                try {
                    Object systemService = context.getSystemService("connectivity");
                    if (systemService != null && (systemService instanceof ConnectivityManager)) {
                        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
                        if (!(activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting())) {
                            XZ.this.m442new();
                            return;
                        }
                        XZ.this.m440int();
                    }
                } catch (SecurityException unused) {
                    XZ.f681do;
                } catch (Exception e) {
                    TL.m338new(XZ.f681do, e.toString());
                }
            }
        }
    }

    /* renamed from: new reason: not valid java name */
    public final void m442new() {
        if (this.f686else && !this.f689if) {
            if (this.f691long != null) {
                MR mr = this.f691long;
                mr.f346int = true;
                mr.m143int();
            }
            boolean z = false;
            if (this.f691long != null) {
                if (this.f691long.f339case.get() > 0) {
                    z = true;
                }
            }
            if (!z) {
                m438for();
                TL.m338new(f681do, "paused NON-FUSED location services");
            }
            this.f689if = true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        if ((r3.f691long.f339case.get() > 0) != false) goto L_0x002a;
     */
    /* renamed from: int reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void m440int() {
        /*
            r3 = this;
            boolean r0 = r3.f686else
            if (r0 == 0) goto L_0x0038
            boolean r0 = r3.f689if
            if (r0 == 0) goto L_0x0038
            com.threatmetrix.TrustDefender.internal.MR r0 = r3.f691long
            r1 = 0
            if (r0 == 0) goto L_0x0014
            com.threatmetrix.TrustDefender.internal.MR r0 = r3.f691long
            r0.f346int = r1
            r0.m143int()
        L_0x0014:
            com.threatmetrix.TrustDefender.internal.MR r0 = r3.f691long
            r2 = 1
            if (r0 == 0) goto L_0x0029
            com.threatmetrix.TrustDefender.internal.MR r0 = r3.f691long
            java.util.concurrent.atomic.AtomicInteger r0 = r0.f339case
            int r0 = r0.get()
            if (r0 <= 0) goto L_0x0025
            r0 = 1
            goto L_0x0026
        L_0x0025:
            r0 = 0
        L_0x0026:
            if (r0 == 0) goto L_0x0029
            goto L_0x002a
        L_0x0029:
            r2 = 0
        L_0x002a:
            if (r2 != 0) goto L_0x0036
            java.lang.String r0 = f681do
            java.lang.String r2 = "resuming NON-FUSED location services"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r0, r2)
            r3.m434char()
        L_0x0036:
            r3.f689if = r1
        L_0x0038:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.XZ.m440int():void");
    }

    /* renamed from: if reason: not valid java name */
    public final com.threatmetrix.TrustDefender.internal.K7.O m439if() {
        com.threatmetrix.TrustDefender.internal.K7.O o = this.f685char;
        if (o != null) {
            return o;
        }
        if (this.f688goto == null || !this.f686else) {
            return null;
        }
        U6 u6 = this.f688goto;
        Location location = u6.f550if != null ? new Location(u6.f550if) : null;
        if (location == null || (location.getLatitude() == 0.0d && location.getLongitude() == 0.0d)) {
            return null;
        }
        return P.m244do(location, false);
    }

    /* renamed from: do reason: not valid java name */
    public final void m437do(com.threatmetrix.TrustDefender.internal.K7.O o) {
        if (o != null) {
            this.f685char = o;
        } else {
            this.f685char = null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public final void m438for() {
        LocationManager locationManager = this.f684case;
        if (((this.f684case == null || this.f688goto == null) ? false : true) && locationManager != null) {
            try {
                locationManager.removeUpdates(this.f688goto);
            } catch (SecurityException unused) {
            } catch (Exception e) {
                TL.m338new(f681do, e.toString());
            }
        }
    }

    /* renamed from: else reason: not valid java name */
    private Location m436else() {
        TL.m338new(f681do, "Attempting to find an existing location to prime things");
        LocationManager locationManager = this.f684case;
        Location location = null;
        if (locationManager == null) {
            return null;
        }
        try {
            long j = 0;
            float f = Float.MAX_VALUE;
            for (String str : locationManager.getAllProviders()) {
                if (str != null) {
                    TL.m338new(f681do, "getLastLocation() : ".concat(String.valueOf(str)));
                    if (this.f694void != L.f698int || str.equals("network")) {
                        Location lastKnownLocation = locationManager.getLastKnownLocation(str);
                        if (lastKnownLocation != null) {
                            String str2 = f681do;
                            StringBuilder sb = new StringBuilder("getLastLocation() : ");
                            sb.append(lastKnownLocation.getProvider());
                            sb.append(":");
                            sb.append(lastKnownLocation.getLatitude());
                            sb.append(":");
                            sb.append(lastKnownLocation.getLongitude());
                            sb.append(":");
                            sb.append(lastKnownLocation.getAccuracy());
                            TL.m338new(str2, sb.toString());
                            float accuracy = lastKnownLocation.getAccuracy();
                            long time = lastKnownLocation.getTime();
                            if (time > this.f690int && accuracy < f) {
                                location = lastKnownLocation;
                                f = accuracy;
                            } else if (time < this.f690int && f == Float.MAX_VALUE && time > j) {
                                location = lastKnownLocation;
                            }
                            j = time;
                        }
                    }
                }
            }
        } catch (SecurityException unused) {
        } catch (Exception e) {
            TL.m338new(f681do, e.toString());
        }
        return location;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0175, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0177, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01bf, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01c0, code lost:
        com.threatmetrix.TrustDefender.internal.TL.m338new(f681do, r1.toString());
        r15.f684case = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01cb, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0024 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x014c A[Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01b4 A[Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01bf A[ExcHandler: Exception (r1v1 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:52:0x0116] */
    /* renamed from: char reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m434char() {
        /*
            r15 = this;
            boolean r0 = r15.f686else
            if (r0 == 0) goto L_0x01cf
            com.threatmetrix.TrustDefender.internal.U6 r0 = r15.f688goto
            if (r0 != 0) goto L_0x000a
            goto L_0x01cf
        L_0x000a:
            com.threatmetrix.TrustDefender.internal.MR r0 = r15.f691long
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0021
            com.threatmetrix.TrustDefender.internal.MR r0 = r15.f691long
            java.util.concurrent.atomic.AtomicInteger r0 = r0.f339case
            int r0 = r0.get()
            if (r0 <= 0) goto L_0x001c
            r0 = 1
            goto L_0x001d
        L_0x001c:
            r0 = 0
        L_0x001d:
            if (r0 == 0) goto L_0x0021
            r0 = 1
            goto L_0x0022
        L_0x0021:
            r0 = 0
        L_0x0022:
            if (r0 == 0) goto L_0x0025
            return
        L_0x0025:
            java.lang.String r0 = f681do
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "registerLocationServices: low power "
            r3.<init>(r4)
            long r4 = r15.f690int
            r3.append(r4)
            java.lang.String r4 = " high power "
            r3.append(r4)
            long r4 = r15.f692new
            r3.append(r4)
            java.lang.String r4 = " with accuracy "
            r3.append(r4)
            int r4 = r15.f687for
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.threatmetrix.TrustDefender.internal.TL.m338new(r0, r3)
            r0 = 0
            android.content.Context r3 = r15.f693try     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r4 = "location"
            java.lang.Object r3 = r3.getSystemService(r4)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            if (r3 == 0) goto L_0x01b7
            boolean r4 = r3 instanceof android.location.LocationManager     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            if (r4 != 0) goto L_0x005f
            goto L_0x01b7
        L_0x005f:
            android.location.LocationManager r3 = (android.location.LocationManager) r3     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r15.f684case = r3     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            com.threatmetrix.TrustDefender.internal.U6 r4 = r15.f688goto     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r3.removeUpdates(r4)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            android.location.Criteria r4 = new android.location.Criteria     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r4.<init>()     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            int r5 = r15.f687for     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r4.setAccuracy(r5)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r4.setAltitudeRequired(r2)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r4.setBearingAccuracy(r2)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r4.setCostAllowed(r2)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r4.setSpeedAccuracy(r2)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r4.setSpeedRequired(r2)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r4.setVerticalAccuracy(r2)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            android.location.Criteria r12 = new android.location.Criteria     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r12.<init>()     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r12.setPowerRequirement(r1)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r5 = 2
            r12.setAccuracy(r5)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r5 = r3.getBestProvider(r4, r1)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            if (r5 == 0) goto L_0x00a5
            java.lang.String r6 = f681do     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r7 = "fine provider: "
            java.lang.String r8 = java.lang.String.valueOf(r5)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r7 = r7.concat(r8)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            com.threatmetrix.TrustDefender.internal.TL.m338new(r6, r7)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
        L_0x00a5:
            java.lang.String r6 = r3.getBestProvider(r12, r1)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            if (r6 == 0) goto L_0x00ba
            java.lang.String r7 = f681do     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r8 = "course provider: "
            java.lang.String r9 = java.lang.String.valueOf(r6)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r8 = r8.concat(r9)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            com.threatmetrix.TrustDefender.internal.TL.m338new(r7, r8)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
        L_0x00ba:
            if (r5 != 0) goto L_0x00c8
            if (r6 != 0) goto L_0x00c8
            r15.f684case = r0     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r1 = f681do     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r2 = "Unable to find location provider, possibly incorrect permissions. Check that android.permission.ACCESS_COARSE_LOCATION or android.permission.ACCESS_FINE_LOCATION is set"
            com.threatmetrix.TrustDefender.internal.TL.m332if(r1, r2)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            return
        L_0x00c8:
            android.location.Location r7 = r15.m436else()     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            if (r5 == 0) goto L_0x00d6
            if (r6 == 0) goto L_0x00d6
            boolean r5 = r5.equals(r6)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r13 = r5
            goto L_0x00d7
        L_0x00d6:
            r13 = 0
        L_0x00d7:
            if (r7 == 0) goto L_0x00de
            com.threatmetrix.TrustDefender.internal.U6 r5 = r15.f688goto     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r5.onLocationChanged(r7)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
        L_0x00de:
            int r5 = r15.f694void     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            int r6 = com.threatmetrix.TrustDefender.internal.XZ.L.f696for     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            if (r5 == r6) goto L_0x014a
            long r6 = r15.f690int     // Catch:{ IllegalArgumentException -> 0x012f, SecurityException -> 0x0113, Exception -> 0x01bf }
            r8 = 0
            com.threatmetrix.TrustDefender.internal.U6 r10 = r15.f688goto     // Catch:{ IllegalArgumentException -> 0x012f, SecurityException -> 0x0113, Exception -> 0x01bf }
            android.os.HandlerThread r5 = r15.f682break     // Catch:{ IllegalArgumentException -> 0x012f, SecurityException -> 0x0113, Exception -> 0x01bf }
            android.os.Looper r11 = r5.getLooper()     // Catch:{ IllegalArgumentException -> 0x012f, SecurityException -> 0x0113, Exception -> 0x01bf }
            r5 = r3
            r9 = r12
            r5.requestLocationUpdates(r6, r8, r9, r10, r11)     // Catch:{ IllegalArgumentException -> 0x012f, SecurityException -> 0x0113, Exception -> 0x01bf }
            java.lang.String r2 = f681do     // Catch:{ IllegalArgumentException -> 0x0110, SecurityException -> 0x010d, Exception -> 0x01bf }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0110, SecurityException -> 0x010d, Exception -> 0x01bf }
            java.lang.String r6 = "GenericLocationManager created: "
            r5.<init>(r6)     // Catch:{ IllegalArgumentException -> 0x0110, SecurityException -> 0x010d, Exception -> 0x01bf }
            java.lang.String r6 = r3.getBestProvider(r12, r1)     // Catch:{ IllegalArgumentException -> 0x0110, SecurityException -> 0x010d, Exception -> 0x01bf }
            r5.append(r6)     // Catch:{ IllegalArgumentException -> 0x0110, SecurityException -> 0x010d, Exception -> 0x01bf }
            java.lang.String r5 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x0110, SecurityException -> 0x010d, Exception -> 0x01bf }
            com.threatmetrix.TrustDefender.internal.TL.m338new(r2, r5)     // Catch:{ IllegalArgumentException -> 0x0110, SecurityException -> 0x010d, Exception -> 0x01bf }
            r2 = 1
            goto L_0x014a
        L_0x010d:
            r2 = move-exception
            r5 = 1
            goto L_0x0116
        L_0x0110:
            r2 = move-exception
            r5 = 1
            goto L_0x0132
        L_0x0113:
            r5 = move-exception
            r2 = r5
            r5 = 0
        L_0x0116:
            java.lang.String r6 = f681do     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r8 = "Failed to register locationServices: "
            r7.<init>(r8)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r2 = r2.toString()     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r7.append(r2)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r2 = r7.toString()     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            com.threatmetrix.TrustDefender.internal.TL.m325do(r6, r2)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
        L_0x012d:
            r2 = r5
            goto L_0x014a
        L_0x012f:
            r5 = move-exception
            r2 = r5
            r5 = 0
        L_0x0132:
            java.lang.String r6 = f681do     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r8 = "Failed to register locationServices: "
            r7.<init>(r8)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r2 = r2.toString()     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r7.append(r2)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r2 = r7.toString()     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            com.threatmetrix.TrustDefender.internal.TL.m325do(r6, r2)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            goto L_0x012d
        L_0x014a:
            if (r13 != 0) goto L_0x01b1
            int r5 = r15.f694void     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            int r6 = com.threatmetrix.TrustDefender.internal.XZ.L.f696for     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            if (r5 == r6) goto L_0x01b1
            long r6 = r15.f692new     // Catch:{ IllegalArgumentException -> 0x0195, SecurityException -> 0x0179, Exception -> 0x01bf }
            r8 = 0
            com.threatmetrix.TrustDefender.internal.U6 r10 = r15.f688goto     // Catch:{ IllegalArgumentException -> 0x0195, SecurityException -> 0x0179, Exception -> 0x01bf }
            r11 = 0
            r5 = r3
            r9 = r4
            r5.requestLocationUpdates(r6, r8, r9, r10, r11)     // Catch:{ IllegalArgumentException -> 0x0195, SecurityException -> 0x0179, Exception -> 0x01bf }
            java.lang.String r2 = f681do     // Catch:{ IllegalArgumentException -> 0x0177, SecurityException -> 0x0175, Exception -> 0x01bf }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0177, SecurityException -> 0x0175, Exception -> 0x01bf }
            java.lang.String r6 = "GenericLocationManager created: "
            r5.<init>(r6)     // Catch:{ IllegalArgumentException -> 0x0177, SecurityException -> 0x0175, Exception -> 0x01bf }
            java.lang.String r3 = r3.getBestProvider(r4, r1)     // Catch:{ IllegalArgumentException -> 0x0177, SecurityException -> 0x0175, Exception -> 0x01bf }
            r5.append(r3)     // Catch:{ IllegalArgumentException -> 0x0177, SecurityException -> 0x0175, Exception -> 0x01bf }
            java.lang.String r3 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x0177, SecurityException -> 0x0175, Exception -> 0x01bf }
            com.threatmetrix.TrustDefender.internal.TL.m338new(r2, r3)     // Catch:{ IllegalArgumentException -> 0x0177, SecurityException -> 0x0175, Exception -> 0x01bf }
            goto L_0x01b2
        L_0x0175:
            r2 = move-exception
            goto L_0x017d
        L_0x0177:
            r2 = move-exception
            goto L_0x0199
        L_0x0179:
            r1 = move-exception
            r14 = r2
            r2 = r1
            r1 = r14
        L_0x017d:
            java.lang.String r3 = f681do     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r5 = "Failed to register locationServices: "
            r4.<init>(r5)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r2 = r2.toString()     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r4.append(r2)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r2 = r4.toString()     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            com.threatmetrix.TrustDefender.internal.TL.m325do(r3, r2)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            goto L_0x01b2
        L_0x0195:
            r1 = move-exception
            r14 = r2
            r2 = r1
            r1 = r14
        L_0x0199:
            java.lang.String r3 = f681do     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r5 = "Failed to register locationServices: "
            r4.<init>(r5)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r2 = r2.toString()     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            r4.append(r2)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r2 = r4.toString()     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            com.threatmetrix.TrustDefender.internal.TL.m325do(r3, r2)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            goto L_0x01b2
        L_0x01b1:
            r1 = r2
        L_0x01b2:
            if (r1 != 0) goto L_0x01b6
            r15.f684case = r0     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
        L_0x01b6:
            return
        L_0x01b7:
            java.lang.String r1 = f681do     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            java.lang.String r2 = "Location Manager is not available"
            com.threatmetrix.TrustDefender.internal.TL.m332if(r1, r2)     // Catch:{ SecurityException -> 0x01cc, Exception -> 0x01bf }
            return
        L_0x01bf:
            r1 = move-exception
            java.lang.String r2 = f681do
            java.lang.String r1 = r1.toString()
            com.threatmetrix.TrustDefender.internal.TL.m338new(r2, r1)
            r15.f684case = r0
            return
        L_0x01cc:
            r15.f684case = r0
            return
        L_0x01cf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.XZ.m434char():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ce, code lost:
        if (r0 != false) goto L_0x00d0;
     */
    /* renamed from: int reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void m441int(com.threatmetrix.TrustDefender.Config r14) {
        /*
            r13 = this;
            boolean r0 = r14.m_registerForLocationServices
            boolean r1 = com.threatmetrix.TrustDefender.internal.N.K.m163for()
            r2 = 0
            if (r1 == 0) goto L_0x000f
            boolean r1 = com.threatmetrix.TrustDefender.internal.N.K.m162do()
            if (r1 != 0) goto L_0x0011
        L_0x000f:
            r13.f686else = r2
        L_0x0011:
            r13.f686else = r0
            boolean r0 = r14.m_disableLocSerOnBatteryLow
            r13.f683byte = r0
            long r0 = r14.m_lowPowerUpdateTime
            long r3 = r14.m_highPowerUpdateTime
            int r5 = r14.m_accuracy
            android.content.Context r6 = r14.m_context
            if (r6 != 0) goto L_0x0023
            r14 = 0
            goto L_0x002f
        L_0x0023:
            com.threatmetrix.TrustDefender.internal.P$O r6 = new com.threatmetrix.TrustDefender.internal.P$O
            android.content.Context r14 = r14.m_context
            android.content.Context r14 = r14.getApplicationContext()
            r6.<init>(r14)
            r14 = r6
        L_0x002f:
            if (r14 == 0) goto L_0x0117
            android.content.Context r14 = r14.f487for
            boolean r6 = com.threatmetrix.TrustDefender.internal.N.K.m162do()
            if (r6 != 0) goto L_0x003b
            r13.f686else = r2
        L_0x003b:
            boolean r6 = r13.f686else
            if (r6 == 0) goto L_0x0117
            android.os.HandlerThread r6 = r13.f682break
            r6.start()
            r13.f693try = r14
            r13.f690int = r0
            r13.f692new = r3
            r13.f687for = r5
            com.threatmetrix.TrustDefender.internal.U6 r0 = new com.threatmetrix.TrustDefender.internal.U6
            r0.<init>()
            r13.f688goto = r0
            com.threatmetrix.TrustDefender.internal.N$R r0 = new com.threatmetrix.TrustDefender.internal.N$R
            com.threatmetrix.TrustDefender.internal.N r1 = new com.threatmetrix.TrustDefender.internal.N
            r1.<init>()
            r0.<init>(r14)
            java.lang.String r1 = "android.permission.ACCESS_COARSE_LOCATION"
            java.lang.String r3 = r14.getPackageName()
            boolean r1 = r0.m169if(r1, r3)
            if (r1 == 0) goto L_0x006d
            int r1 = com.threatmetrix.TrustDefender.internal.XZ.L.f698int
            r13.f694void = r1
        L_0x006d:
            java.lang.String r1 = "android.permission.ACCESS_FINE_LOCATION"
            java.lang.String r14 = r14.getPackageName()
            boolean r14 = r0.m169if(r1, r14)
            if (r14 == 0) goto L_0x007d
            int r14 = com.threatmetrix.TrustDefender.internal.XZ.L.f697if
            r13.f694void = r14
        L_0x007d:
            com.threatmetrix.TrustDefender.internal.MR r14 = new com.threatmetrix.TrustDefender.internal.MR     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r14.<init>()     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r13.f691long = r14     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            com.threatmetrix.TrustDefender.internal.MR r14 = r13.f691long     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            android.content.Context r3 = r13.f693try     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            long r4 = r13.f692new     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            long r6 = r13.f690int     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            int r8 = r13.f687for     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            com.threatmetrix.TrustDefender.internal.U6 r0 = r13.f688goto     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            android.os.HandlerThread r1 = r13.f682break     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r14.f348new = r13     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r14.f343for = r0     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r14.f345if = r1     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            java.util.concurrent.atomic.AtomicInteger r0 = r14.f339case     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r0.set(r2)     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            com.threatmetrix.TrustDefender.internal.MR$W r10 = new com.threatmetrix.TrustDefender.internal.MR$W     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r10.<init>(r14, r2)     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            com.threatmetrix.TrustDefender.internal.MR$W r11 = new com.threatmetrix.TrustDefender.internal.MR$W     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r11.<init>(r14, r2)     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            android.os.Handler r12 = new android.os.Handler     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            android.os.Looper r0 = r1.getLooper()     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r12.<init>(r0)     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r9 = r14
            com.threatmetrix.TrustDefender.internal.J$L r0 = com.threatmetrix.TrustDefender.internal.J.m91if(r3, r4, r6, r8, r9, r10, r11, r12)     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r14.f341do = r0     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            com.threatmetrix.TrustDefender.internal.J$L r14 = r14.f341do     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r0 = 1
            if (r14 == 0) goto L_0x00be
            r14 = 1
            goto L_0x00bf
        L_0x00be:
            r14 = 0
        L_0x00bf:
            r13.m434char()     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            if (r14 != 0) goto L_0x00d0
            android.location.LocationManager r14 = r13.f684case     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            if (r14 == 0) goto L_0x00cd
            com.threatmetrix.TrustDefender.internal.U6 r14 = r13.f688goto     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            if (r14 == 0) goto L_0x00cd
            goto L_0x00ce
        L_0x00cd:
            r0 = 0
        L_0x00ce:
            if (r0 == 0) goto L_0x0106
        L_0x00d0:
            android.content.IntentFilter r14 = new android.content.IntentFilter     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r14.<init>()     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            java.lang.String r0 = "android.net.conn.CONNECTIVITY_CHANGE"
            r14.addAction(r0)     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            com.threatmetrix.TrustDefender.internal.XZ$O r0 = new com.threatmetrix.TrustDefender.internal.XZ$O     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r0.<init>()     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            android.content.Context r1 = r13.f693try     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r1.registerReceiver(r0, r14)     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            boolean r14 = r13.f683byte     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            if (r14 == 0) goto L_0x0106
            android.content.IntentFilter r14 = new android.content.IntentFilter     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r14.<init>()     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            java.lang.String r0 = "android.intent.action.BATTERY_LOW"
            r14.addAction(r0)     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            java.lang.String r0 = "android.intent.action.BATTERY_OKAY"
            r14.addAction(r0)     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            java.lang.String r0 = "android.intent.action.ACTION_POWER_CONNECTED"
            r14.addAction(r0)     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            com.threatmetrix.TrustDefender.internal.XZ$I r0 = new com.threatmetrix.TrustDefender.internal.XZ$I     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r0.<init>()     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            android.content.Context r1 = r13.f693try     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
            r1.registerReceiver(r0, r14)     // Catch:{ SecurityException -> 0x0114, Exception -> 0x0107 }
        L_0x0106:
            return
        L_0x0107:
            r14 = move-exception
            java.lang.String r0 = f681do
            java.lang.String r14 = r14.toString()
            com.threatmetrix.TrustDefender.internal.TL.m338new(r0, r14)
            r13.f686else = r2
            goto L_0x0117
        L_0x0114:
            r13.f686else = r2
            return
        L_0x0117:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.XZ.m441int(com.threatmetrix.TrustDefender.Config):void");
    }
}
