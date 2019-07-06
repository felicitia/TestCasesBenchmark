package com.paypal.android.sdk.onetouch.core.metadata;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import org.json.JSONObject;

public class h implements LocationListener {
    private static e O = new e();
    private static final Object P = new Object();
    private static h Q = null;
    public static u b = null;
    /* access modifiers changed from: private */
    public static final String h = "h";
    private g A;
    private g B;
    private String C;
    private Map<String, Object> D;
    private Location E;
    /* access modifiers changed from: private */
    public Timer F;
    /* access modifiers changed from: private */
    public Handler G;
    private m H;
    private String I;
    private String J;
    private boolean K;
    private String L;
    /* access modifiers changed from: private */
    public Context r;
    private String s;
    private long t;
    /* access modifiers changed from: private */
    public long u;
    /* access modifiers changed from: private */
    public int v;
    /* access modifiers changed from: private */
    public int w;
    private long x;
    /* access modifiers changed from: private */
    public String y;
    private c z;

    private h() {
    }

    private static long a(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            if (VERSION.SDK_INT > 8) {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
            }
            String str = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
            if (str != null) {
                return new File(str).lastModified();
            }
            return 0;
        } catch (NameNotFoundException unused) {
        }
    }

    private static String a(TelephonyManager telephonyManager) {
        try {
            return telephonyManager.getSimOperatorName();
        } catch (SecurityException e) {
            ai.a(h, "Known SecurityException on some devices", (Throwable) e);
            return null;
        }
    }

    private static ArrayList<String> a(WifiManager wifiManager) {
        ArrayList<String> arrayList = new ArrayList<>();
        List scanResults = wifiManager.getScanResults();
        if (scanResults == null || scanResults.size() == 0) {
            return null;
        }
        String bssid = wifiManager.getConnectionInfo().getBSSID();
        if (bssid == null || bssid.equals("00:00:00:00:00:00")) {
            return null;
        }
        int i = -1;
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < scanResults.size(); i3++) {
            if (!bssid.equals(((ScanResult) scanResults.get(i3)).BSSID)) {
                int i4 = ((ScanResult) scanResults.get(i3)).level;
                if (i2 < i4) {
                    i = i3;
                    i2 = i4;
                }
            }
        }
        arrayList.add(bssid);
        if (i != -1) {
            arrayList.add(((ScanResult) scanResults.get(i)).BSSID);
        }
        return arrayList;
    }

    private void a(c cVar) {
        this.z = cVar;
        ai.a(h, "Configuration loaded");
        String str = h;
        StringBuilder sb = new StringBuilder("URL:     ");
        sb.append(this.z.a());
        ai.a(str, sb.toString());
        String str2 = h;
        StringBuilder sb2 = new StringBuilder("Version: ");
        sb2.append(this.z.b());
        ai.a(str2, sb2.toString());
        n();
        this.F = new Timer();
        long c = this.z.c();
        long d = this.z.d();
        long e = this.z.e();
        String str3 = h;
        StringBuilder sb3 = new StringBuilder("Sending logRiskMetadata every ");
        sb3.append(c);
        sb3.append(" seconds.");
        ai.a(str3, sb3.toString());
        String str4 = h;
        StringBuilder sb4 = new StringBuilder("sessionTimeout set to ");
        sb4.append(d);
        sb4.append(" seconds.");
        ai.a(str4, sb4.toString());
        String str5 = h;
        StringBuilder sb5 = new StringBuilder("compTimeout set to    ");
        sb5.append(e);
        sb5.append(" seconds.");
        ai.a(str5, sb5.toString());
        this.t = c * 1000;
        this.u = e * 1000;
        l.a(d * 1000);
        m();
    }

    private void a(g gVar, g gVar2) {
        if (gVar != null) {
            gVar.ag = this.D;
            JSONObject a = gVar2 != null ? gVar.a(gVar2) : gVar.a();
            HashMap hashMap = new HashMap();
            hashMap.put("appGuid", this.s);
            hashMap.put("libraryVersion", d());
            hashMap.put("additionalData", a.toString());
            String str = h;
            StringBuilder sb = new StringBuilder("Dyson Risk Data ");
            sb.append(a.toString());
            ai.a(str, sb.toString());
            if (this.z != null) {
                String g = this.z.g();
                boolean h2 = this.z.h();
                String str2 = h;
                StringBuilder sb2 = new StringBuilder("new LogRiskMetadataRequest to: ");
                sb2.append(g);
                ai.a(str2, sb2.toString());
                String str3 = h;
                StringBuilder sb3 = new StringBuilder("endpointIsStage: ");
                sb3.append(h2);
                sb3.append(" (using SSL: ");
                sb3.append(!h2);
                sb3.append(")");
                ai.a(str3, sb3.toString());
                ab.a().a(new w(g, hashMap, this.G, !h2));
            }
        }
    }

    private static long b(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            if (VERSION.SDK_INT > 8) {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
            }
            String str = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
            if (str != null) {
                return new File(str).lastModified();
            }
            return 0;
        } catch (NameNotFoundException unused) {
        }
    }

    static /* synthetic */ boolean c(h hVar) {
        return System.currentTimeMillis() - hVar.x > hVar.u;
    }

    static /* synthetic */ void f(h hVar) {
        if (hVar.B != null) {
            String str = h;
            StringBuilder sb = new StringBuilder();
            sb.append(hVar.C);
            sb.append(" update not sent correctly, retrying...");
            ai.a(str, sb.toString());
            if ("full".equals(hVar.C)) {
                hVar.a(hVar.B, (g) null);
                return;
            }
            hVar.a(hVar.B, hVar.w());
        } else if (!l.c() || hVar.A == null) {
            l.a();
            hVar.C = "full";
            g w2 = hVar.w();
            hVar.a(w2, (g) null);
            hVar.B = w2;
        } else {
            hVar.C = "incremental";
            g w3 = hVar.w();
            hVar.a(hVar.A, w3);
            hVar.B = w3;
        }
    }

    public static h h() {
        h hVar;
        synchronized (P) {
            if (Q == null) {
                Q = new h();
            }
            hVar = Q;
        }
        return hVar;
    }

    private static String k() {
        return ai.b(Boolean.FALSE.booleanValue());
    }

    private String l() {
        StringBuilder sb = new StringBuilder("https://b.stats.paypal.com/counter.cgi?p=");
        if (this.H == null || this.H == m.UNKNOWN) {
            return "Beacon not recognize host app";
        }
        int a = this.H.a();
        if (this.J == null) {
            return "Beacon pairing id empty";
        }
        sb.append(this.J);
        sb.append("&i=");
        String b2 = ai.b();
        if (b2.equals("")) {
            try {
                sb.append(e.a("emptyIp"));
                sb.append("&t=");
            } catch (IOException e) {
                ai.a(h, "error reading property file", (Throwable) e);
            }
        } else {
            sb.append(b2);
            sb.append("&t=");
        }
        sb.append(String.valueOf(System.currentTimeMillis() / 1000));
        sb.append("&a=");
        sb.append(a);
        String str = h;
        StringBuilder sb2 = new StringBuilder("Beacon Request URL ");
        sb2.append(sb.toString());
        ai.a(str, sb2.toString());
        s sVar = new s(sb.toString(), this.s, this.I, ai.a(this.r), this.G);
        ab.a().a(sVar);
        return sb.toString();
    }

    private void m() {
        if (this.z != null && this.K) {
            n();
            this.F = new Timer();
            ai.a(h, "Starting LogRiskMetadataTask");
            this.F.scheduleAtFixedRate(new i(this), 0, this.t);
        }
    }

    private void n() {
        if (this.F != null) {
            this.F.cancel();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c0, code lost:
        r2.z = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:492:?, code lost:
        r7 = null;
        com.paypal.android.sdk.onetouch.core.metadata.ai.a(h, "knownApps error", (java.lang.Throwable) null);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:491:0x08a8 */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x01d2 A[Catch:{ Exception -> 0x01e6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0205 A[Catch:{ Exception -> 0x020a }] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0229 A[Catch:{ Exception -> 0x022e }] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x024d A[Catch:{ Exception -> 0x025c }] */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x027b A[Catch:{ Exception -> 0x0282 }] */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x02a7 A[Catch:{ Exception -> 0x02ae }] */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02cd A[Catch:{ Exception -> 0x02d4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x02f4 A[Catch:{ Exception -> 0x02ff }] */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x031e A[Catch:{ Exception -> 0x0329 }] */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0348 A[Catch:{ Exception -> 0x0353 }] */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x0372 A[Catch:{ Exception -> 0x037d }] */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x039c A[Catch:{ Exception -> 0x03a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x03c6 A[Catch:{ Exception -> 0x03d1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007d A[Catch:{ Exception -> 0x09db }] */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x03f0 A[Catch:{ Exception -> 0x03f7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:239:0x0416 A[Catch:{ Exception -> 0x0421 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0080 A[Catch:{ Exception -> 0x09db }] */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0440 A[Catch:{ Exception -> 0x044b }] */
    /* JADX WARNING: Removed duplicated region for block: B:261:0x046a A[Catch:{ Exception -> 0x046f }] */
    /* JADX WARNING: Removed duplicated region for block: B:269:0x048e A[Catch:{ Exception -> 0x0493 }] */
    /* JADX WARNING: Removed duplicated region for block: B:277:0x04b2 A[Catch:{ Exception -> 0x04b9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:285:0x04d8 A[Catch:{ Exception -> 0x04e0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:293:0x04ff A[Catch:{ Exception -> 0x0506 }] */
    /* JADX WARNING: Removed duplicated region for block: B:301:0x0525 A[Catch:{ Exception -> 0x0530 }] */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x054f A[Catch:{ Exception -> 0x055a }] */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x0579 A[Catch:{ Exception -> 0x0589 }] */
    /* JADX WARNING: Removed duplicated region for block: B:329:0x05a8 A[Catch:{ Exception -> 0x05b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:340:0x05d1 A[Catch:{ Exception -> 0x05dc }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a0 A[Catch:{ Exception -> 0x09db }] */
    /* JADX WARNING: Removed duplicated region for block: B:351:0x05fb A[Catch:{ Exception -> 0x0600 }] */
    /* JADX WARNING: Removed duplicated region for block: B:359:0x061f A[Catch:{ Exception -> 0x0626 }] */
    /* JADX WARNING: Removed duplicated region for block: B:367:0x0645 A[Catch:{ Exception -> 0x0655 }] */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x0674 A[Catch:{ Exception -> 0x067b }] */
    /* JADX WARNING: Removed duplicated region for block: B:383:0x069a A[Catch:{ Exception -> 0x06af }] */
    /* JADX WARNING: Removed duplicated region for block: B:389:0x06aa A[Catch:{ Exception -> 0x06af }] */
    /* JADX WARNING: Removed duplicated region for block: B:397:0x06ce A[Catch:{ Exception -> 0x06e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:405:0x0700 A[Catch:{ Exception -> 0x070b }] */
    /* JADX WARNING: Removed duplicated region for block: B:416:0x072a A[Catch:{ Exception -> 0x0735 }] */
    /* JADX WARNING: Removed duplicated region for block: B:427:0x0754 A[Catch:{ Exception -> 0x075b }] */
    /* JADX WARNING: Removed duplicated region for block: B:435:0x077a A[Catch:{ Exception -> 0x0797 }] */
    /* JADX WARNING: Removed duplicated region for block: B:440:0x0794  */
    /* JADX WARNING: Removed duplicated region for block: B:449:0x07b8 A[Catch:{ Exception -> 0x07c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00be A[Catch:{ Exception -> 0x09db }] */
    /* JADX WARNING: Removed duplicated region for block: B:457:0x07e6 A[Catch:{ Exception -> 0x07f9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:465:0x0818 A[Catch:{ Exception -> 0x0823 }] */
    /* JADX WARNING: Removed duplicated region for block: B:473:0x0842 A[Catch:{ Exception -> 0x084d }] */
    /* JADX WARNING: Removed duplicated region for block: B:481:0x086c A[Catch:{ Exception -> 0x08bc }] */
    /* JADX WARNING: Removed duplicated region for block: B:496:0x08b8 A[Catch:{ Exception -> 0x08bc }] */
    /* JADX WARNING: Removed duplicated region for block: B:505:0x08db A[Catch:{ Exception -> 0x08e4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00e7 A[Catch:{ Exception -> 0x09db }] */
    /* JADX WARNING: Removed duplicated region for block: B:513:0x0903 A[Catch:{ Exception -> 0x090c }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00e9 A[Catch:{ Exception -> 0x09db }] */
    /* JADX WARNING: Removed duplicated region for block: B:521:0x092b A[Catch:{ Exception -> 0x0934 }] */
    /* JADX WARNING: Removed duplicated region for block: B:529:0x0953 A[Catch:{ Exception -> 0x095a }] */
    /* JADX WARNING: Removed duplicated region for block: B:537:0x0979 A[Catch:{ Exception -> 0x0980 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00f9 A[Catch:{ Exception -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x011f A[Catch:{ Exception -> 0x0124 }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x014e  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x018b A[Catch:{ Exception -> 0x0190 }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01af A[Catch:{ Exception -> 0x01b3 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.paypal.android.sdk.onetouch.core.metadata.g w() {
        /*
            r18 = this;
            r1 = r18
            android.content.Context r2 = r1.r
            r3 = 0
            if (r2 != 0) goto L_0x0008
            return r3
        L_0x0008:
            com.paypal.android.sdk.onetouch.core.metadata.g r2 = new com.paypal.android.sdk.onetouch.core.metadata.g
            r2.<init>()
            com.paypal.android.sdk.onetouch.core.metadata.c r4 = r1.z     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ag r4 = r4.i()     // Catch:{ Exception -> 0x09db }
            android.content.Context r5 = r1.r     // Catch:{ Exception -> 0x09db }
            java.lang.String r6 = "phone"
            java.lang.Object r5 = r5.getSystemService(r6)     // Catch:{ Exception -> 0x09db }
            android.telephony.TelephonyManager r5 = (android.telephony.TelephonyManager) r5     // Catch:{ Exception -> 0x09db }
            android.content.Context r6 = r1.r     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = "wifi"
            java.lang.Object r6 = r6.getSystemService(r7)     // Catch:{ Exception -> 0x09db }
            android.net.wifi.WifiManager r6 = (android.net.wifi.WifiManager) r6     // Catch:{ Exception -> 0x09db }
            android.content.Context r7 = r1.r     // Catch:{ Exception -> 0x09db }
            java.lang.String r8 = "connectivity"
            java.lang.Object r7 = r7.getSystemService(r8)     // Catch:{ Exception -> 0x09db }
            android.net.ConnectivityManager r7 = (android.net.ConnectivityManager) r7     // Catch:{ Exception -> 0x09db }
            android.content.Context r8 = r1.r     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = "android.permission.ACCESS_WIFI_STATE"
            boolean r8 = com.paypal.android.sdk.onetouch.core.metadata.ai.a(r8, r9)     // Catch:{ Exception -> 0x09db }
            if (r8 == 0) goto L_0x0040
            android.net.wifi.WifiInfo r8 = r6.getConnectionInfo()     // Catch:{ Exception -> 0x09db }
            goto L_0x0041
        L_0x0040:
            r8 = r3
        L_0x0041:
            android.content.Context r9 = r1.r     // Catch:{ Exception -> 0x09db }
            java.lang.String r10 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r9 = com.paypal.android.sdk.onetouch.core.metadata.ai.a(r9, r10)     // Catch:{ Exception -> 0x09db }
            if (r9 == 0) goto L_0x0050
            android.net.NetworkInfo r7 = r7.getActiveNetworkInfo()     // Catch:{ Exception -> 0x09db }
            goto L_0x0051
        L_0x0050:
            r7 = r3
        L_0x0051:
            android.content.Context r9 = r1.r     // Catch:{ Exception -> 0x09db }
            java.lang.String r10 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r9 = com.paypal.android.sdk.onetouch.core.metadata.ai.a(r9, r10)     // Catch:{ Exception -> 0x09db }
            if (r9 != 0) goto L_0x0068
            android.content.Context r9 = r1.r     // Catch:{ Exception -> 0x09db }
            java.lang.String r11 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r9 = com.paypal.android.sdk.onetouch.core.metadata.ai.a(r9, r11)     // Catch:{ Exception -> 0x09db }
            if (r9 == 0) goto L_0x0066
            goto L_0x0068
        L_0x0066:
            r9 = 0
            goto L_0x0069
        L_0x0068:
            r9 = 1
        L_0x0069:
            android.content.Context r11 = r1.r     // Catch:{ Exception -> 0x09db }
            java.lang.String r12 = "android.permission.READ_PHONE_STATE"
            boolean r11 = com.paypal.android.sdk.onetouch.core.metadata.ai.a(r11, r12)     // Catch:{ Exception -> 0x09db }
            java.util.Date r12 = new java.util.Date     // Catch:{ Exception -> 0x09db }
            r12.<init>()     // Catch:{ Exception -> 0x09db }
            int r13 = r5.getPhoneType()     // Catch:{ Exception -> 0x09db }
            switch(r13) {
                case 0: goto L_0x00be;
                case 1: goto L_0x00a0;
                case 2: goto L_0x0080;
                default: goto L_0x007d;
            }     // Catch:{ Exception -> 0x09db }
        L_0x007d:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            goto L_0x00c3
        L_0x0080:
            java.lang.String r13 = "cdma"
            r2.z = r13     // Catch:{ Exception -> 0x09db }
            if (r9 == 0) goto L_0x009d
            android.telephony.CellLocation r13 = r5.getCellLocation()     // Catch:{ SecurityException -> 0x0093 }
            java.lang.Class<android.telephony.cdma.CdmaCellLocation> r14 = android.telephony.cdma.CdmaCellLocation.class
            java.lang.Object r13 = com.paypal.android.sdk.onetouch.core.metadata.ai.a(r13, r14)     // Catch:{ SecurityException -> 0x0093 }
            android.telephony.cdma.CdmaCellLocation r13 = (android.telephony.cdma.CdmaCellLocation) r13     // Catch:{ SecurityException -> 0x0093 }
            goto L_0x009e
        L_0x0093:
            r0 = move-exception
            r13 = r0
            java.lang.String r14 = h     // Catch:{ Exception -> 0x09db }
            java.lang.String r15 = "Known SecurityException on some devices: "
        L_0x0099:
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r14, r15, r13)     // Catch:{ Exception -> 0x09db }
            goto L_0x00d9
        L_0x009d:
            r13 = r3
        L_0x009e:
            r14 = r3
            goto L_0x00db
        L_0x00a0:
            java.lang.String r13 = "gsm"
            r2.z = r13     // Catch:{ Exception -> 0x09db }
            if (r9 == 0) goto L_0x00ba
            android.telephony.CellLocation r13 = r5.getCellLocation()     // Catch:{ SecurityException -> 0x00b3 }
            java.lang.Class<android.telephony.gsm.GsmCellLocation> r14 = android.telephony.gsm.GsmCellLocation.class
            java.lang.Object r13 = com.paypal.android.sdk.onetouch.core.metadata.ai.a(r13, r14)     // Catch:{ SecurityException -> 0x00b3 }
            android.telephony.gsm.GsmCellLocation r13 = (android.telephony.gsm.GsmCellLocation) r13     // Catch:{ SecurityException -> 0x00b3 }
            goto L_0x00bb
        L_0x00b3:
            r0 = move-exception
            r13 = r0
            java.lang.String r14 = h     // Catch:{ Exception -> 0x09db }
            java.lang.String r15 = "Known SecurityException on some devices: "
            goto L_0x0099
        L_0x00ba:
            r13 = r3
        L_0x00bb:
            r14 = r13
            r13 = r3
            goto L_0x00db
        L_0x00be:
            java.lang.String r13 = "none"
        L_0x00c0:
            r2.z = r13     // Catch:{ Exception -> 0x09db }
            goto L_0x00d9
        L_0x00c3:
            java.lang.String r14 = "unknown ("
            r13.<init>(r14)     // Catch:{ Exception -> 0x09db }
            int r14 = r5.getPhoneType()     // Catch:{ Exception -> 0x09db }
            r13.append(r14)     // Catch:{ Exception -> 0x09db }
            java.lang.String r14 = ")"
            r13.append(r14)     // Catch:{ Exception -> 0x09db }
            java.lang.String r13 = r13.toString()     // Catch:{ Exception -> 0x09db }
            goto L_0x00c0
        L_0x00d9:
            r13 = r3
            r14 = r13
        L_0x00db:
            java.lang.String r15 = "3.5.7.release"
            r2.g = r15     // Catch:{ Exception -> 0x09db }
            java.lang.String r15 = r1.y     // Catch:{ Exception -> 0x09db }
            r2.h = r15     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.c r15 = r1.z     // Catch:{ Exception -> 0x09db }
            if (r15 != 0) goto L_0x00e9
            r15 = r3
            goto L_0x00ef
        L_0x00e9:
            com.paypal.android.sdk.onetouch.core.metadata.c r15 = r1.z     // Catch:{ Exception -> 0x09db }
            java.lang.String r15 = r15.b()     // Catch:{ Exception -> 0x09db }
        L_0x00ef:
            r2.i = r15     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r15 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataOsType     // Catch:{ Exception -> 0x00fe }
            boolean r15 = r4.a(r15)     // Catch:{ Exception -> 0x00fe }
            if (r15 != 0) goto L_0x00fb
            r2.x = r3     // Catch:{ Exception -> 0x00fe }
        L_0x00fb:
            r16 = r12
            goto L_0x0117
        L_0x00fe:
            r0 = move-exception
            r15 = r0
            java.lang.String r10 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            r16 = r12
            java.lang.String r12 = "Exception Thrown in "
            r3.<init>(r12)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r12 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataOsType     // Catch:{ Exception -> 0x09db }
            r3.append(r12)     // Catch:{ Exception -> 0x09db }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r10, r3, r15)     // Catch:{ Exception -> 0x09db }
        L_0x0117:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataAppGuid     // Catch:{ Exception -> 0x0124 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x0124 }
            if (r3 == 0) goto L_0x013b
            java.lang.String r3 = r1.s     // Catch:{ Exception -> 0x0124 }
            r2.a = r3     // Catch:{ Exception -> 0x0124 }
            goto L_0x013b
        L_0x0124:
            r0 = move-exception
            r3 = r0
            java.lang.String r10 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r15 = "Exception Thrown in "
            r12.<init>(r15)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r15 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataAppGuid     // Catch:{ Exception -> 0x09db }
            r12.append(r15)     // Catch:{ Exception -> 0x09db }
            java.lang.String r12 = r12.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r10, r12, r3)     // Catch:{ Exception -> 0x09db }
        L_0x013b:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataTimestamp     // Catch:{ Exception -> 0x0151 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x0151 }
            if (r3 == 0) goto L_0x014e
            r17 = r11
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x014c }
            r2.H = r10     // Catch:{ Exception -> 0x014c }
            goto L_0x016a
        L_0x014c:
            r0 = move-exception
            goto L_0x0154
        L_0x014e:
            r17 = r11
            goto L_0x016a
        L_0x0151:
            r0 = move-exception
            r17 = r11
        L_0x0154:
            r3 = r0
            java.lang.String r10 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r12 = "Exception Thrown in "
            r11.<init>(r12)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r12 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataTimestamp     // Catch:{ Exception -> 0x09db }
            r11.append(r12)     // Catch:{ Exception -> 0x09db }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r10, r11, r3)     // Catch:{ Exception -> 0x09db }
        L_0x016a:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            r3.<init>()     // Catch:{ Exception -> 0x09db }
            java.lang.String r10 = r1.s     // Catch:{ Exception -> 0x09db }
            r3.append(r10)     // Catch:{ Exception -> 0x09db }
            long r10 = r2.H     // Catch:{ Exception -> 0x09db }
            r3.append(r10)     // Catch:{ Exception -> 0x09db }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x09db }
            java.lang.String r3 = com.paypal.android.sdk.onetouch.core.metadata.ai.a(r3)     // Catch:{ Exception -> 0x09db }
            r2.ad = r3     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataPairingId     // Catch:{ Exception -> 0x0190 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x0190 }
            if (r3 == 0) goto L_0x01a7
            java.lang.String r3 = r1.J     // Catch:{ Exception -> 0x0190 }
            r2.T = r3     // Catch:{ Exception -> 0x0190 }
            goto L_0x01a7
        L_0x0190:
            r0 = move-exception
            r3 = r0
            java.lang.String r10 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r12 = "Exception Thrown in "
            r11.<init>(r12)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r12 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataPairingId     // Catch:{ Exception -> 0x09db }
            r11.append(r12)     // Catch:{ Exception -> 0x09db }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r10, r11, r3)     // Catch:{ Exception -> 0x09db }
        L_0x01a7:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataPhoneType     // Catch:{ Exception -> 0x01b3 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x01b3 }
            if (r3 != 0) goto L_0x01ca
            r3 = 0
            r2.z = r3     // Catch:{ Exception -> 0x01b3 }
            goto L_0x01ca
        L_0x01b3:
            r0 = move-exception
            r3 = r0
            java.lang.String r10 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r12 = "Exception Thrown in "
            r11.<init>(r12)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r12 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataPhoneType     // Catch:{ Exception -> 0x09db }
            r11.append(r12)     // Catch:{ Exception -> 0x09db }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r10, r11, r3)     // Catch:{ Exception -> 0x09db }
        L_0x01ca:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataSourceApp     // Catch:{ Exception -> 0x01e6 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x01e6 }
            if (r3 == 0) goto L_0x01fd
            com.paypal.android.sdk.onetouch.core.metadata.m r3 = r1.H     // Catch:{ Exception -> 0x01e6 }
            if (r3 != 0) goto L_0x01df
            com.paypal.android.sdk.onetouch.core.metadata.m r3 = com.paypal.android.sdk.onetouch.core.metadata.m.UNKNOWN     // Catch:{ Exception -> 0x01e6 }
            int r3 = r3.a()     // Catch:{ Exception -> 0x01e6 }
        L_0x01dc:
            r2.P = r3     // Catch:{ Exception -> 0x01e6 }
            goto L_0x01fd
        L_0x01df:
            com.paypal.android.sdk.onetouch.core.metadata.m r3 = r1.H     // Catch:{ Exception -> 0x01e6 }
            int r3 = r3.a()     // Catch:{ Exception -> 0x01e6 }
            goto L_0x01dc
        L_0x01e6:
            r0 = move-exception
            r3 = r0
            java.lang.String r10 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r12 = "Exception Thrown in "
            r11.<init>(r12)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r12 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataSourceApp     // Catch:{ Exception -> 0x09db }
            r11.append(r12)     // Catch:{ Exception -> 0x09db }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r10, r11, r3)     // Catch:{ Exception -> 0x09db }
        L_0x01fd:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataSourceAppVersion     // Catch:{ Exception -> 0x020a }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x020a }
            if (r3 == 0) goto L_0x0221
            java.lang.String r3 = r1.I     // Catch:{ Exception -> 0x020a }
            r2.Q = r3     // Catch:{ Exception -> 0x020a }
            goto L_0x0221
        L_0x020a:
            r0 = move-exception
            r3 = r0
            java.lang.String r10 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r12 = "Exception Thrown in "
            r11.<init>(r12)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r12 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataSourceAppVersion     // Catch:{ Exception -> 0x09db }
            r11.append(r12)     // Catch:{ Exception -> 0x09db }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r10, r11, r3)     // Catch:{ Exception -> 0x09db }
        L_0x0221:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataNotifToken     // Catch:{ Exception -> 0x022e }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x022e }
            if (r3 == 0) goto L_0x0245
            java.lang.String r3 = r1.L     // Catch:{ Exception -> 0x022e }
            r2.X = r3     // Catch:{ Exception -> 0x022e }
            goto L_0x0245
        L_0x022e:
            r0 = move-exception
            r3 = r0
            java.lang.String r10 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r12 = "Exception Thrown in "
            r11.<init>(r12)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r12 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataNotifToken     // Catch:{ Exception -> 0x09db }
            r11.append(r12)     // Catch:{ Exception -> 0x09db }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r10, r11, r3)     // Catch:{ Exception -> 0x09db }
        L_0x0245:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataAndroidId     // Catch:{ Exception -> 0x025c }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x025c }
            if (r3 == 0) goto L_0x0273
            android.content.Context r3 = r1.r     // Catch:{ Exception -> 0x025c }
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch:{ Exception -> 0x025c }
            java.lang.String r10 = "android_id"
            java.lang.String r3 = android.provider.Settings.Secure.getString(r3, r10)     // Catch:{ Exception -> 0x025c }
            r2.W = r3     // Catch:{ Exception -> 0x025c }
            goto L_0x0273
        L_0x025c:
            r0 = move-exception
            r3 = r0
            java.lang.String r10 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r12 = "Exception Thrown in "
            r11.<init>(r12)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r12 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataAndroidId     // Catch:{ Exception -> 0x09db }
            r11.append(r12)     // Catch:{ Exception -> 0x09db }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r10, r11, r3)     // Catch:{ Exception -> 0x09db }
        L_0x0273:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataDeviceUptime     // Catch:{ Exception -> 0x0282 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x0282 }
            if (r3 == 0) goto L_0x0299
            long r10 = android.os.SystemClock.uptimeMillis()     // Catch:{ Exception -> 0x0282 }
            r2.n = r10     // Catch:{ Exception -> 0x0282 }
            goto L_0x0299
        L_0x0282:
            r0 = move-exception
            r3 = r0
            java.lang.String r10 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r12 = "Exception Thrown in "
            r11.<init>(r12)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r12 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataDeviceUptime     // Catch:{ Exception -> 0x09db }
            r11.append(r12)     // Catch:{ Exception -> 0x09db }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r10, r11, r3)     // Catch:{ Exception -> 0x09db }
        L_0x0299:
            android.content.Context r3 = r1.r     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.a r3 = com.paypal.android.sdk.onetouch.core.metadata.ai.a(r3)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r10 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataAppId     // Catch:{ Exception -> 0x02ae }
            boolean r10 = r4.a(r10)     // Catch:{ Exception -> 0x02ae }
            if (r10 == 0) goto L_0x02c5
            java.lang.String r10 = r3.a()     // Catch:{ Exception -> 0x02ae }
            r2.b = r10     // Catch:{ Exception -> 0x02ae }
            goto L_0x02c5
        L_0x02ae:
            r0 = move-exception
            r10 = r0
            java.lang.String r11 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r15 = "Exception Thrown in "
            r12.<init>(r15)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r15 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataAppId     // Catch:{ Exception -> 0x09db }
            r12.append(r15)     // Catch:{ Exception -> 0x09db }
            java.lang.String r12 = r12.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r11, r12, r10)     // Catch:{ Exception -> 0x09db }
        L_0x02c5:
            com.paypal.android.sdk.onetouch.core.metadata.ah r10 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataAppVersion     // Catch:{ Exception -> 0x02d4 }
            boolean r10 = r4.a(r10)     // Catch:{ Exception -> 0x02d4 }
            if (r10 == 0) goto L_0x02eb
            java.lang.String r3 = r3.b()     // Catch:{ Exception -> 0x02d4 }
            r2.c = r3     // Catch:{ Exception -> 0x02d4 }
            goto L_0x02eb
        L_0x02d4:
            r0 = move-exception
            r3 = r0
            java.lang.String r10 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r12 = "Exception Thrown in "
            r11.<init>(r12)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r12 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataAppVersion     // Catch:{ Exception -> 0x09db }
            r11.append(r12)     // Catch:{ Exception -> 0x09db }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r10, r11, r3)     // Catch:{ Exception -> 0x09db }
        L_0x02eb:
            r3 = -1
            com.paypal.android.sdk.onetouch.core.metadata.ah r10 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataBaseStationId     // Catch:{ Exception -> 0x02ff }
            boolean r10 = r4.a(r10)     // Catch:{ Exception -> 0x02ff }
            if (r10 == 0) goto L_0x0316
            if (r13 != 0) goto L_0x02f8
            r10 = -1
            goto L_0x02fc
        L_0x02f8:
            int r10 = r13.getBaseStationId()     // Catch:{ Exception -> 0x02ff }
        L_0x02fc:
            r2.d = r10     // Catch:{ Exception -> 0x02ff }
            goto L_0x0316
        L_0x02ff:
            r0 = move-exception
            r10 = r0
            java.lang.String r11 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r15 = "Exception Thrown in "
            r12.<init>(r15)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r15 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataBaseStationId     // Catch:{ Exception -> 0x09db }
            r12.append(r15)     // Catch:{ Exception -> 0x09db }
            java.lang.String r12 = r12.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r11, r12, r10)     // Catch:{ Exception -> 0x09db }
        L_0x0316:
            com.paypal.android.sdk.onetouch.core.metadata.ah r10 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataCdmaNetworkId     // Catch:{ Exception -> 0x0329 }
            boolean r10 = r4.a(r10)     // Catch:{ Exception -> 0x0329 }
            if (r10 == 0) goto L_0x0340
            if (r13 != 0) goto L_0x0322
            r10 = -1
            goto L_0x0326
        L_0x0322:
            int r10 = r13.getNetworkId()     // Catch:{ Exception -> 0x0329 }
        L_0x0326:
            r2.N = r10     // Catch:{ Exception -> 0x0329 }
            goto L_0x0340
        L_0x0329:
            r0 = move-exception
            r10 = r0
            java.lang.String r11 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r15 = "Exception Thrown in "
            r12.<init>(r15)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r15 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataCdmaNetworkId     // Catch:{ Exception -> 0x09db }
            r12.append(r15)     // Catch:{ Exception -> 0x09db }
            java.lang.String r12 = r12.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r11, r12, r10)     // Catch:{ Exception -> 0x09db }
        L_0x0340:
            com.paypal.android.sdk.onetouch.core.metadata.ah r10 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataCdmaSystemId     // Catch:{ Exception -> 0x0353 }
            boolean r10 = r4.a(r10)     // Catch:{ Exception -> 0x0353 }
            if (r10 == 0) goto L_0x036a
            if (r13 != 0) goto L_0x034c
            r10 = -1
            goto L_0x0350
        L_0x034c:
            int r10 = r13.getSystemId()     // Catch:{ Exception -> 0x0353 }
        L_0x0350:
            r2.M = r10     // Catch:{ Exception -> 0x0353 }
            goto L_0x036a
        L_0x0353:
            r0 = move-exception
            r10 = r0
            java.lang.String r11 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r13 = "Exception Thrown in "
            r12.<init>(r13)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r13 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataCdmaSystemId     // Catch:{ Exception -> 0x09db }
            r12.append(r13)     // Catch:{ Exception -> 0x09db }
            java.lang.String r12 = r12.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r11, r12, r10)     // Catch:{ Exception -> 0x09db }
        L_0x036a:
            com.paypal.android.sdk.onetouch.core.metadata.ah r10 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataBssid     // Catch:{ Exception -> 0x037d }
            boolean r10 = r4.a(r10)     // Catch:{ Exception -> 0x037d }
            if (r10 == 0) goto L_0x0394
            if (r8 != 0) goto L_0x0376
            r10 = 0
            goto L_0x037a
        L_0x0376:
            java.lang.String r10 = r8.getBSSID()     // Catch:{ Exception -> 0x037d }
        L_0x037a:
            r2.e = r10     // Catch:{ Exception -> 0x037d }
            goto L_0x0394
        L_0x037d:
            r0 = move-exception
            r10 = r0
            java.lang.String r11 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r13 = "Exception Thrown in "
            r12.<init>(r13)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r13 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataBssid     // Catch:{ Exception -> 0x09db }
            r12.append(r13)     // Catch:{ Exception -> 0x09db }
            java.lang.String r12 = r12.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r11, r12, r10)     // Catch:{ Exception -> 0x09db }
        L_0x0394:
            com.paypal.android.sdk.onetouch.core.metadata.ah r10 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataBssidArray     // Catch:{ Exception -> 0x03a7 }
            boolean r10 = r4.a(r10)     // Catch:{ Exception -> 0x03a7 }
            if (r10 == 0) goto L_0x03be
            if (r9 == 0) goto L_0x03a3
            java.util.ArrayList r6 = a(r6)     // Catch:{ Exception -> 0x03a7 }
            goto L_0x03a4
        L_0x03a3:
            r6 = 0
        L_0x03a4:
            r2.af = r6     // Catch:{ Exception -> 0x03a7 }
            goto L_0x03be
        L_0x03a7:
            r0 = move-exception
            r6 = r0
            java.lang.String r9 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r11 = "Exception Thrown in "
            r10.<init>(r11)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r11 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataBssidArray     // Catch:{ Exception -> 0x09db }
            r10.append(r11)     // Catch:{ Exception -> 0x09db }
            java.lang.String r10 = r10.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r9, r10, r6)     // Catch:{ Exception -> 0x09db }
        L_0x03be:
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataCellId     // Catch:{ Exception -> 0x03d1 }
            boolean r6 = r4.a(r6)     // Catch:{ Exception -> 0x03d1 }
            if (r6 == 0) goto L_0x03e8
            if (r14 != 0) goto L_0x03ca
            r6 = -1
            goto L_0x03ce
        L_0x03ca:
            int r6 = r14.getCid()     // Catch:{ Exception -> 0x03d1 }
        L_0x03ce:
            r2.f = r6     // Catch:{ Exception -> 0x03d1 }
            goto L_0x03e8
        L_0x03d1:
            r0 = move-exception
            r6 = r0
            java.lang.String r9 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r11 = "Exception Thrown in "
            r10.<init>(r11)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r11 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataCellId     // Catch:{ Exception -> 0x09db }
            r10.append(r11)     // Catch:{ Exception -> 0x09db }
            java.lang.String r10 = r10.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r9, r10, r6)     // Catch:{ Exception -> 0x09db }
        L_0x03e8:
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataNetworkOperator     // Catch:{ Exception -> 0x03f7 }
            boolean r6 = r4.a(r6)     // Catch:{ Exception -> 0x03f7 }
            if (r6 == 0) goto L_0x040e
            java.lang.String r6 = r5.getNetworkOperator()     // Catch:{ Exception -> 0x03f7 }
            r2.O = r6     // Catch:{ Exception -> 0x03f7 }
            goto L_0x040e
        L_0x03f7:
            r0 = move-exception
            r6 = r0
            java.lang.String r9 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r11 = "Exception Thrown in "
            r10.<init>(r11)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r11 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataNetworkOperator     // Catch:{ Exception -> 0x09db }
            r10.append(r11)     // Catch:{ Exception -> 0x09db }
            java.lang.String r10 = r10.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r9, r10, r6)     // Catch:{ Exception -> 0x09db }
        L_0x040e:
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataConnType     // Catch:{ Exception -> 0x0421 }
            boolean r6 = r4.a(r6)     // Catch:{ Exception -> 0x0421 }
            if (r6 == 0) goto L_0x0438
            if (r7 != 0) goto L_0x041a
            r6 = 0
            goto L_0x041e
        L_0x041a:
            java.lang.String r6 = r7.getTypeName()     // Catch:{ Exception -> 0x0421 }
        L_0x041e:
            r2.j = r6     // Catch:{ Exception -> 0x0421 }
            goto L_0x0438
        L_0x0421:
            r0 = move-exception
            r6 = r0
            java.lang.String r7 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r10 = "Exception Thrown in "
            r9.<init>(r10)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r10 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataConnType     // Catch:{ Exception -> 0x09db }
            r9.append(r10)     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r7, r9, r6)     // Catch:{ Exception -> 0x09db }
        L_0x0438:
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataDeviceId     // Catch:{ Exception -> 0x044b }
            boolean r6 = r4.a(r6)     // Catch:{ Exception -> 0x044b }
            if (r6 == 0) goto L_0x0462
            if (r17 == 0) goto L_0x0447
            java.lang.String r6 = r5.getDeviceId()     // Catch:{ Exception -> 0x044b }
            goto L_0x0448
        L_0x0447:
            r6 = 0
        L_0x0448:
            r2.k = r6     // Catch:{ Exception -> 0x044b }
            goto L_0x0462
        L_0x044b:
            r0 = move-exception
            r6 = r0
            java.lang.String r7 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r10 = "Exception Thrown in "
            r9.<init>(r10)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r10 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataDeviceId     // Catch:{ Exception -> 0x09db }
            r9.append(r10)     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r7, r9, r6)     // Catch:{ Exception -> 0x09db }
        L_0x0462:
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataDeviceModel     // Catch:{ Exception -> 0x046f }
            boolean r6 = r4.a(r6)     // Catch:{ Exception -> 0x046f }
            if (r6 == 0) goto L_0x0486
            java.lang.String r6 = android.os.Build.MODEL     // Catch:{ Exception -> 0x046f }
            r2.l = r6     // Catch:{ Exception -> 0x046f }
            goto L_0x0486
        L_0x046f:
            r0 = move-exception
            r6 = r0
            java.lang.String r7 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r10 = "Exception Thrown in "
            r9.<init>(r10)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r10 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataDeviceModel     // Catch:{ Exception -> 0x09db }
            r9.append(r10)     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r7, r9, r6)     // Catch:{ Exception -> 0x09db }
        L_0x0486:
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataDeviceName     // Catch:{ Exception -> 0x0493 }
            boolean r6 = r4.a(r6)     // Catch:{ Exception -> 0x0493 }
            if (r6 == 0) goto L_0x04aa
            java.lang.String r6 = android.os.Build.DEVICE     // Catch:{ Exception -> 0x0493 }
            r2.m = r6     // Catch:{ Exception -> 0x0493 }
            goto L_0x04aa
        L_0x0493:
            r0 = move-exception
            r6 = r0
            java.lang.String r7 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r10 = "Exception Thrown in "
            r9.<init>(r10)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r10 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataDeviceName     // Catch:{ Exception -> 0x09db }
            r9.append(r10)     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r7, r9, r6)     // Catch:{ Exception -> 0x09db }
        L_0x04aa:
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataIpAddrs     // Catch:{ Exception -> 0x04b9 }
            boolean r6 = r4.a(r6)     // Catch:{ Exception -> 0x04b9 }
            if (r6 == 0) goto L_0x04d0
            java.lang.String r6 = com.paypal.android.sdk.onetouch.core.metadata.ai.b()     // Catch:{ Exception -> 0x04b9 }
            r2.o = r6     // Catch:{ Exception -> 0x04b9 }
            goto L_0x04d0
        L_0x04b9:
            r0 = move-exception
            r6 = r0
            java.lang.String r7 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r10 = "Exception Thrown in "
            r9.<init>(r10)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r10 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataIpAddrs     // Catch:{ Exception -> 0x09db }
            r9.append(r10)     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r7, r9, r6)     // Catch:{ Exception -> 0x09db }
        L_0x04d0:
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataIpAddresses     // Catch:{ Exception -> 0x04e0 }
            boolean r6 = r4.a(r6)     // Catch:{ Exception -> 0x04e0 }
            if (r6 == 0) goto L_0x04f7
            r6 = 1
            java.util.List r7 = com.paypal.android.sdk.onetouch.core.metadata.ai.a(r6)     // Catch:{ Exception -> 0x04e0 }
            r2.p = r7     // Catch:{ Exception -> 0x04e0 }
            goto L_0x04f7
        L_0x04e0:
            r0 = move-exception
            r6 = r0
            java.lang.String r7 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r10 = "Exception Thrown in "
            r9.<init>(r10)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r10 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataIpAddresses     // Catch:{ Exception -> 0x09db }
            r9.append(r10)     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r7, r9, r6)     // Catch:{ Exception -> 0x09db }
        L_0x04f7:
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataLinkerId     // Catch:{ Exception -> 0x0506 }
            boolean r6 = r4.a(r6)     // Catch:{ Exception -> 0x0506 }
            if (r6 == 0) goto L_0x051d
            java.lang.String r6 = com.paypal.android.sdk.onetouch.core.metadata.ai.a()     // Catch:{ Exception -> 0x0506 }
            r2.r = r6     // Catch:{ Exception -> 0x0506 }
            goto L_0x051d
        L_0x0506:
            r0 = move-exception
            r6 = r0
            java.lang.String r7 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r10 = "Exception Thrown in "
            r9.<init>(r10)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r10 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataLinkerId     // Catch:{ Exception -> 0x09db }
            r9.append(r10)     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r7, r9, r6)     // Catch:{ Exception -> 0x09db }
        L_0x051d:
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataLocaleCountry     // Catch:{ Exception -> 0x0530 }
            boolean r6 = r4.a(r6)     // Catch:{ Exception -> 0x0530 }
            if (r6 == 0) goto L_0x0547
            java.util.Locale r6 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x0530 }
            java.lang.String r6 = r6.getCountry()     // Catch:{ Exception -> 0x0530 }
            r2.s = r6     // Catch:{ Exception -> 0x0530 }
            goto L_0x0547
        L_0x0530:
            r0 = move-exception
            r6 = r0
            java.lang.String r7 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r10 = "Exception Thrown in "
            r9.<init>(r10)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r10 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataLocaleCountry     // Catch:{ Exception -> 0x09db }
            r9.append(r10)     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r7, r9, r6)     // Catch:{ Exception -> 0x09db }
        L_0x0547:
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataLocaleLang     // Catch:{ Exception -> 0x055a }
            boolean r6 = r4.a(r6)     // Catch:{ Exception -> 0x055a }
            if (r6 == 0) goto L_0x0571
            java.util.Locale r6 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x055a }
            java.lang.String r6 = r6.getLanguage()     // Catch:{ Exception -> 0x055a }
            r2.t = r6     // Catch:{ Exception -> 0x055a }
            goto L_0x0571
        L_0x055a:
            r0 = move-exception
            r6 = r0
            java.lang.String r7 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r10 = "Exception Thrown in "
            r9.<init>(r10)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r10 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataLocaleLang     // Catch:{ Exception -> 0x09db }
            r9.append(r10)     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r7, r9, r6)     // Catch:{ Exception -> 0x09db }
        L_0x0571:
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataLocation     // Catch:{ Exception -> 0x0589 }
            boolean r6 = r4.a(r6)     // Catch:{ Exception -> 0x0589 }
            if (r6 == 0) goto L_0x05a0
            android.location.Location r6 = r1.E     // Catch:{ Exception -> 0x0589 }
            if (r6 != 0) goto L_0x057f
            r6 = 0
            goto L_0x0586
        L_0x057f:
            android.location.Location r6 = new android.location.Location     // Catch:{ Exception -> 0x0589 }
            android.location.Location r7 = r1.E     // Catch:{ Exception -> 0x0589 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x0589 }
        L_0x0586:
            r2.u = r6     // Catch:{ Exception -> 0x0589 }
            goto L_0x05a0
        L_0x0589:
            r0 = move-exception
            r6 = r0
            java.lang.String r7 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r10 = "Exception Thrown in "
            r9.<init>(r10)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r10 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataLocation     // Catch:{ Exception -> 0x09db }
            r9.append(r10)     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r7, r9, r6)     // Catch:{ Exception -> 0x09db }
        L_0x05a0:
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataLocationAreaCode     // Catch:{ Exception -> 0x05b2 }
            boolean r6 = r4.a(r6)     // Catch:{ Exception -> 0x05b2 }
            if (r6 == 0) goto L_0x05c9
            if (r14 != 0) goto L_0x05ab
            goto L_0x05af
        L_0x05ab:
            int r3 = r14.getLac()     // Catch:{ Exception -> 0x05b2 }
        L_0x05af:
            r2.v = r3     // Catch:{ Exception -> 0x05b2 }
            goto L_0x05c9
        L_0x05b2:
            r0 = move-exception
            r3 = r0
            java.lang.String r6 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = "Exception Thrown in "
            r7.<init>(r9)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r9 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataLocationAreaCode     // Catch:{ Exception -> 0x09db }
            r7.append(r9)     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r6, r7, r3)     // Catch:{ Exception -> 0x09db }
        L_0x05c9:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataMacAddrs     // Catch:{ Exception -> 0x05dc }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x05dc }
            if (r3 == 0) goto L_0x05f3
            if (r8 != 0) goto L_0x05d5
            r3 = 0
            goto L_0x05d9
        L_0x05d5:
            java.lang.String r3 = r8.getMacAddress()     // Catch:{ Exception -> 0x05dc }
        L_0x05d9:
            r2.w = r3     // Catch:{ Exception -> 0x05dc }
            goto L_0x05f3
        L_0x05dc:
            r0 = move-exception
            r3 = r0
            java.lang.String r6 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = "Exception Thrown in "
            r7.<init>(r9)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r9 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataMacAddrs     // Catch:{ Exception -> 0x09db }
            r7.append(r9)     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r6, r7, r3)     // Catch:{ Exception -> 0x09db }
        L_0x05f3:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataOsType     // Catch:{ Exception -> 0x0600 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x0600 }
            if (r3 == 0) goto L_0x0617
            java.lang.String r3 = android.os.Build.VERSION.RELEASE     // Catch:{ Exception -> 0x0600 }
            r2.y = r3     // Catch:{ Exception -> 0x0600 }
            goto L_0x0617
        L_0x0600:
            r0 = move-exception
            r3 = r0
            java.lang.String r6 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = "Exception Thrown in "
            r7.<init>(r9)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r9 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataOsType     // Catch:{ Exception -> 0x09db }
            r7.append(r9)     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r6, r7, r3)     // Catch:{ Exception -> 0x09db }
        L_0x0617:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataRiskCompSessionId     // Catch:{ Exception -> 0x0626 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x0626 }
            if (r3 == 0) goto L_0x063d
            java.lang.String r3 = com.paypal.android.sdk.onetouch.core.metadata.l.b()     // Catch:{ Exception -> 0x0626 }
            r2.A = r3     // Catch:{ Exception -> 0x0626 }
            goto L_0x063d
        L_0x0626:
            r0 = move-exception
            r3 = r0
            java.lang.String r6 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = "Exception Thrown in "
            r7.<init>(r9)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r9 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataRiskCompSessionId     // Catch:{ Exception -> 0x09db }
            r7.append(r9)     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r6, r7, r3)     // Catch:{ Exception -> 0x09db }
        L_0x063d:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataRoaming     // Catch:{ Exception -> 0x0655 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x0655 }
            if (r3 == 0) goto L_0x066c
            android.telephony.ServiceState r3 = new android.telephony.ServiceState     // Catch:{ Exception -> 0x0655 }
            r3.<init>()     // Catch:{ Exception -> 0x0655 }
            boolean r3 = r3.getRoaming()     // Catch:{ Exception -> 0x0655 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x0655 }
            r2.B = r3     // Catch:{ Exception -> 0x0655 }
            goto L_0x066c
        L_0x0655:
            r0 = move-exception
            r3 = r0
            java.lang.String r6 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = "Exception Thrown in "
            r7.<init>(r9)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r9 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataRoaming     // Catch:{ Exception -> 0x09db }
            r7.append(r9)     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r6, r7, r3)     // Catch:{ Exception -> 0x09db }
        L_0x066c:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataSimOperatorName     // Catch:{ Exception -> 0x067b }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x067b }
            if (r3 == 0) goto L_0x0692
            java.lang.String r3 = a(r5)     // Catch:{ Exception -> 0x067b }
            r2.C = r3     // Catch:{ Exception -> 0x067b }
            goto L_0x0692
        L_0x067b:
            r0 = move-exception
            r3 = r0
            java.lang.String r6 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = "Exception Thrown in "
            r7.<init>(r9)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r9 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataSimOperatorName     // Catch:{ Exception -> 0x09db }
            r7.append(r9)     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r6, r7, r3)     // Catch:{ Exception -> 0x09db }
        L_0x0692:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataSerialNumber     // Catch:{ Exception -> 0x06af }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x06af }
            if (r3 == 0) goto L_0x06a4
            if (r17 == 0) goto L_0x06a1
            java.lang.String r3 = r5.getSimSerialNumber()     // Catch:{ Exception -> 0x06af }
            goto L_0x06a2
        L_0x06a1:
            r3 = 0
        L_0x06a2:
            r2.D = r3     // Catch:{ Exception -> 0x06af }
        L_0x06a4:
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x06af }
            r6 = 9
            if (r3 < r6) goto L_0x06c6
            java.lang.String r3 = android.os.Build.SERIAL     // Catch:{ Exception -> 0x06af }
            r2.Y = r3     // Catch:{ Exception -> 0x06af }
            goto L_0x06c6
        L_0x06af:
            r0 = move-exception
            r3 = r0
            java.lang.String r6 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = "Exception Thrown in "
            r7.<init>(r9)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r9 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataSerialNumber     // Catch:{ Exception -> 0x09db }
            r7.append(r9)     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r6, r7, r3)     // Catch:{ Exception -> 0x09db }
        L_0x06c6:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataSmsEnabled     // Catch:{ Exception -> 0x06e1 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x06e1 }
            if (r3 == 0) goto L_0x06f8
            android.content.Context r3 = r1.r     // Catch:{ Exception -> 0x06e1 }
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch:{ Exception -> 0x06e1 }
            java.lang.String r6 = "android.hardware.telephony"
            boolean r3 = r3.hasSystemFeature(r6)     // Catch:{ Exception -> 0x06e1 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x06e1 }
            r2.E = r3     // Catch:{ Exception -> 0x06e1 }
            goto L_0x06f8
        L_0x06e1:
            r0 = move-exception
            r3 = r0
            java.lang.String r6 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r9 = "Exception Thrown in "
            r7.<init>(r9)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r9 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataSmsEnabled     // Catch:{ Exception -> 0x09db }
            r7.append(r9)     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r6, r7, r3)     // Catch:{ Exception -> 0x09db }
        L_0x06f8:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataSsid     // Catch:{ Exception -> 0x070b }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x070b }
            if (r3 == 0) goto L_0x0722
            if (r8 != 0) goto L_0x0704
            r3 = 0
            goto L_0x0708
        L_0x0704:
            java.lang.String r3 = r8.getSSID()     // Catch:{ Exception -> 0x070b }
        L_0x0708:
            r2.F = r3     // Catch:{ Exception -> 0x070b }
            goto L_0x0722
        L_0x070b:
            r0 = move-exception
            r3 = r0
            java.lang.String r6 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r8 = "Exception Thrown in "
            r7.<init>(r8)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r8 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataSsid     // Catch:{ Exception -> 0x09db }
            r7.append(r8)     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r6, r7, r3)     // Catch:{ Exception -> 0x09db }
        L_0x0722:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataSubscriberId     // Catch:{ Exception -> 0x0735 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x0735 }
            if (r3 == 0) goto L_0x074c
            if (r17 == 0) goto L_0x0731
            java.lang.String r3 = r5.getSubscriberId()     // Catch:{ Exception -> 0x0735 }
            goto L_0x0732
        L_0x0731:
            r3 = 0
        L_0x0732:
            r2.G = r3     // Catch:{ Exception -> 0x0735 }
            goto L_0x074c
        L_0x0735:
            r0 = move-exception
            r3 = r0
            java.lang.String r5 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataSubscriberId     // Catch:{ Exception -> 0x09db }
            r6.append(r7)     // Catch:{ Exception -> 0x09db }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r5, r6, r3)     // Catch:{ Exception -> 0x09db }
        L_0x074c:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataTotalStorageSpace     // Catch:{ Exception -> 0x075b }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x075b }
            if (r3 == 0) goto L_0x0772
            long r5 = com.paypal.android.sdk.onetouch.core.metadata.ai.c()     // Catch:{ Exception -> 0x075b }
            r2.I = r5     // Catch:{ Exception -> 0x075b }
            goto L_0x0772
        L_0x075b:
            r0 = move-exception
            r3 = r0
            java.lang.String r5 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataTotalStorageSpace     // Catch:{ Exception -> 0x09db }
            r6.append(r7)     // Catch:{ Exception -> 0x09db }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r5, r6, r3)     // Catch:{ Exception -> 0x09db }
        L_0x0772:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataTzName     // Catch:{ Exception -> 0x0797 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x0797 }
            if (r3 == 0) goto L_0x0794
            java.util.TimeZone r3 = java.util.TimeZone.getDefault()     // Catch:{ Exception -> 0x0797 }
            java.util.TimeZone r5 = java.util.TimeZone.getDefault()     // Catch:{ Exception -> 0x0797 }
            r6 = r16
            boolean r5 = r5.inDaylightTime(r6)     // Catch:{ Exception -> 0x0792 }
            java.util.Locale r7 = java.util.Locale.ENGLISH     // Catch:{ Exception -> 0x0792 }
            r8 = 1
            java.lang.String r3 = r3.getDisplayName(r5, r8, r7)     // Catch:{ Exception -> 0x0792 }
            r2.J = r3     // Catch:{ Exception -> 0x0792 }
            goto L_0x07b0
        L_0x0792:
            r0 = move-exception
            goto L_0x079a
        L_0x0794:
            r6 = r16
            goto L_0x07b0
        L_0x0797:
            r0 = move-exception
            r6 = r16
        L_0x079a:
            r3 = r0
            java.lang.String r5 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r8 = "Exception Thrown in "
            r7.<init>(r8)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r8 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataTzName     // Catch:{ Exception -> 0x09db }
            r7.append(r8)     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r5, r7, r3)     // Catch:{ Exception -> 0x09db }
        L_0x07b0:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataIsDaylightSaving     // Catch:{ Exception -> 0x07c7 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x07c7 }
            if (r3 == 0) goto L_0x07de
            java.util.TimeZone r3 = java.util.TimeZone.getDefault()     // Catch:{ Exception -> 0x07c7 }
            boolean r3 = r3.inDaylightTime(r6)     // Catch:{ Exception -> 0x07c7 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x07c7 }
            r2.K = r3     // Catch:{ Exception -> 0x07c7 }
            goto L_0x07de
        L_0x07c7:
            r0 = move-exception
            r3 = r0
            java.lang.String r5 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r8 = "Exception Thrown in "
            r7.<init>(r8)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r8 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataIsDaylightSaving     // Catch:{ Exception -> 0x09db }
            r7.append(r8)     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r5, r7, r3)     // Catch:{ Exception -> 0x09db }
        L_0x07de:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataTimeZoneOffset     // Catch:{ Exception -> 0x07f9 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x07f9 }
            if (r3 == 0) goto L_0x0810
            java.util.TimeZone r3 = java.util.TimeZone.getDefault()     // Catch:{ Exception -> 0x07f9 }
            long r5 = r6.getTime()     // Catch:{ Exception -> 0x07f9 }
            int r3 = r3.getOffset(r5)     // Catch:{ Exception -> 0x07f9 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Exception -> 0x07f9 }
            r2.L = r3     // Catch:{ Exception -> 0x07f9 }
            goto L_0x0810
        L_0x07f9:
            r0 = move-exception
            r3 = r0
            java.lang.String r5 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataTimeZoneOffset     // Catch:{ Exception -> 0x09db }
            r6.append(r7)     // Catch:{ Exception -> 0x09db }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r5, r6, r3)     // Catch:{ Exception -> 0x09db }
        L_0x0810:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataIsEmulator     // Catch:{ Exception -> 0x0823 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x0823 }
            if (r3 == 0) goto L_0x083a
            boolean r3 = com.paypal.android.sdk.onetouch.core.metadata.o.a()     // Catch:{ Exception -> 0x0823 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x0823 }
            r2.R = r3     // Catch:{ Exception -> 0x0823 }
            goto L_0x083a
        L_0x0823:
            r0 = move-exception
            r3 = r0
            java.lang.String r5 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataIsEmulator     // Catch:{ Exception -> 0x09db }
            r6.append(r7)     // Catch:{ Exception -> 0x09db }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r5, r6, r3)     // Catch:{ Exception -> 0x09db }
        L_0x083a:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataIsRooted     // Catch:{ Exception -> 0x084d }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x084d }
            if (r3 == 0) goto L_0x0864
            boolean r3 = com.paypal.android.sdk.onetouch.core.metadata.p.a()     // Catch:{ Exception -> 0x084d }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x084d }
            r2.S = r3     // Catch:{ Exception -> 0x084d }
            goto L_0x0864
        L_0x084d:
            r0 = move-exception
            r3 = r0
            java.lang.String r5 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataIsRooted     // Catch:{ Exception -> 0x09db }
            r6.append(r7)     // Catch:{ Exception -> 0x09db }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r5, r6, r3)     // Catch:{ Exception -> 0x09db }
        L_0x0864:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataKnownApps     // Catch:{ Exception -> 0x08bc }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x08bc }
            if (r3 == 0) goto L_0x08d3
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x08bc }
            r3.<init>()     // Catch:{ Exception -> 0x08bc }
            com.paypal.android.sdk.onetouch.core.metadata.c r5 = r1.z     // Catch:{ Exception -> 0x08bc }
            if (r5 == 0) goto L_0x08b1
            com.paypal.android.sdk.onetouch.core.metadata.c r5 = r1.z     // Catch:{ Exception -> 0x08bc }
            java.util.List r5 = r5.f()     // Catch:{ Exception -> 0x08bc }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ Exception -> 0x08a8 }
        L_0x087f:
            boolean r6 = r5.hasNext()     // Catch:{ Exception -> 0x08a8 }
            if (r6 == 0) goto L_0x08b1
            java.lang.Object r6 = r5.next()     // Catch:{ Exception -> 0x08a8 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x08a8 }
            android.content.Context r7 = r1.r     // Catch:{ Exception -> 0x08a8 }
            android.content.pm.PackageManager r7 = r7.getPackageManager()     // Catch:{ Exception -> 0x08a8 }
            android.content.Intent r8 = new android.content.Intent     // Catch:{ Exception -> 0x08a8 }
            r8.<init>()     // Catch:{ Exception -> 0x08a8 }
            android.content.ComponentName r9 = android.content.ComponentName.unflattenFromString(r6)     // Catch:{ Exception -> 0x08a8 }
            android.content.Intent r8 = r8.setComponent(r9)     // Catch:{ Exception -> 0x08a8 }
            boolean r7 = com.paypal.android.sdk.onetouch.core.metadata.ai.a(r7, r8)     // Catch:{ Exception -> 0x08a8 }
            if (r7 == 0) goto L_0x087f
            r3.add(r6)     // Catch:{ Exception -> 0x08a8 }
            goto L_0x087f
        L_0x08a8:
            java.lang.String r5 = h     // Catch:{ Exception -> 0x08bc }
            java.lang.String r6 = "knownApps error"
            r7 = 0
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r5, r6, r7)     // Catch:{ Exception -> 0x08bc }
            goto L_0x08b2
        L_0x08b1:
            r7 = 0
        L_0x08b2:
            int r5 = r3.size()     // Catch:{ Exception -> 0x08bc }
            if (r5 != 0) goto L_0x08b9
            r3 = r7
        L_0x08b9:
            r2.q = r3     // Catch:{ Exception -> 0x08bc }
            goto L_0x08d3
        L_0x08bc:
            r0 = move-exception
            r3 = r0
            java.lang.String r5 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataKnownApps     // Catch:{ Exception -> 0x09db }
            r6.append(r7)     // Catch:{ Exception -> 0x09db }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r5, r6, r3)     // Catch:{ Exception -> 0x09db }
        L_0x08d3:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataAppFirstInstallTime     // Catch:{ Exception -> 0x08e4 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x08e4 }
            if (r3 == 0) goto L_0x08fb
            android.content.Context r3 = r1.r     // Catch:{ Exception -> 0x08e4 }
            long r5 = a(r3)     // Catch:{ Exception -> 0x08e4 }
            r2.U = r5     // Catch:{ Exception -> 0x08e4 }
            goto L_0x08fb
        L_0x08e4:
            r0 = move-exception
            r3 = r0
            java.lang.String r5 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataAppFirstInstallTime     // Catch:{ Exception -> 0x09db }
            r6.append(r7)     // Catch:{ Exception -> 0x09db }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r5, r6, r3)     // Catch:{ Exception -> 0x09db }
        L_0x08fb:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataAppLastUpdateTime     // Catch:{ Exception -> 0x090c }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x090c }
            if (r3 == 0) goto L_0x0923
            android.content.Context r3 = r1.r     // Catch:{ Exception -> 0x090c }
            long r5 = b(r3)     // Catch:{ Exception -> 0x090c }
            r2.V = r5     // Catch:{ Exception -> 0x090c }
            goto L_0x0923
        L_0x090c:
            r0 = move-exception
            r3 = r0
            java.lang.String r5 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataAppLastUpdateTime     // Catch:{ Exception -> 0x09db }
            r6.append(r7)     // Catch:{ Exception -> 0x09db }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r5, r6, r3)     // Catch:{ Exception -> 0x09db }
        L_0x0923:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataGsfId     // Catch:{ Exception -> 0x0934 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x0934 }
            if (r3 == 0) goto L_0x094b
            android.content.Context r3 = r1.r     // Catch:{ Exception -> 0x0934 }
            java.lang.String r3 = com.paypal.android.sdk.onetouch.core.metadata.ai.b(r3)     // Catch:{ Exception -> 0x0934 }
            r2.Z = r3     // Catch:{ Exception -> 0x0934 }
            goto L_0x094b
        L_0x0934:
            r0 = move-exception
            r3 = r0
            java.lang.String r5 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataGsfId     // Catch:{ Exception -> 0x09db }
            r6.append(r7)     // Catch:{ Exception -> 0x09db }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r5, r6, r3)     // Catch:{ Exception -> 0x09db }
        L_0x094b:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataVPNSetting     // Catch:{ Exception -> 0x095a }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x095a }
            if (r3 == 0) goto L_0x0971
            java.lang.String r3 = com.paypal.android.sdk.onetouch.core.metadata.ai.e()     // Catch:{ Exception -> 0x095a }
            r2.ab = r3     // Catch:{ Exception -> 0x095a }
            goto L_0x0971
        L_0x095a:
            r0 = move-exception
            r3 = r0
            java.lang.String r5 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataVPNSetting     // Catch:{ Exception -> 0x09db }
            r6.append(r7)     // Catch:{ Exception -> 0x09db }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r5, r6, r3)     // Catch:{ Exception -> 0x09db }
        L_0x0971:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataProxySetting     // Catch:{ Exception -> 0x0980 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x0980 }
            if (r3 == 0) goto L_0x0997
            java.lang.String r3 = com.paypal.android.sdk.onetouch.core.metadata.ai.d()     // Catch:{ Exception -> 0x0980 }
            r2.aa = r3     // Catch:{ Exception -> 0x0980 }
            goto L_0x0997
        L_0x0980:
            r0 = move-exception
            r3 = r0
            java.lang.String r5 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataProxySetting     // Catch:{ Exception -> 0x09db }
            r6.append(r7)     // Catch:{ Exception -> 0x09db }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r5, r6, r3)     // Catch:{ Exception -> 0x09db }
        L_0x0997:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataCounter     // Catch:{ Exception -> 0x09b7 }
            boolean r3 = r4.a(r3)     // Catch:{ Exception -> 0x09b7 }
            if (r3 == 0) goto L_0x09ce
            int r3 = r2.P     // Catch:{ Exception -> 0x09b7 }
            com.paypal.android.sdk.onetouch.core.metadata.m r4 = com.paypal.android.sdk.onetouch.core.metadata.m.PAYPAL     // Catch:{ Exception -> 0x09b7 }
            int r4 = r4.a()     // Catch:{ Exception -> 0x09b7 }
            if (r3 != r4) goto L_0x09ce
            android.content.Context r3 = r1.r     // Catch:{ Exception -> 0x09b7 }
            com.paypal.android.sdk.onetouch.core.metadata.ai.c(r3)     // Catch:{ Exception -> 0x09b7 }
            android.content.Context r3 = r1.r     // Catch:{ Exception -> 0x09b7 }
            java.lang.String r3 = com.paypal.android.sdk.onetouch.core.metadata.ai.d(r3)     // Catch:{ Exception -> 0x09b7 }
            r2.ac = r3     // Catch:{ Exception -> 0x09b7 }
            goto L_0x09ce
        L_0x09b7:
            r0 = move-exception
            r3 = r0
            java.lang.String r4 = h     // Catch:{ Exception -> 0x09db }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x09db }
            java.lang.String r6 = "Exception Thrown in "
            r5.<init>(r6)     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.ah.PPRiskDataCounter     // Catch:{ Exception -> 0x09db }
            r5.append(r6)     // Catch:{ Exception -> 0x09db }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x09db }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r4, r5, r3)     // Catch:{ Exception -> 0x09db }
        L_0x09ce:
            java.util.Map<java.lang.String, java.lang.Object> r3 = r1.D     // Catch:{ Exception -> 0x09db }
            r2.ag = r3     // Catch:{ Exception -> 0x09db }
            java.lang.String r3 = r1.J     // Catch:{ Exception -> 0x09db }
            java.lang.String r3 = com.paypal.android.sdk.onetouch.core.metadata.ai.b(r3)     // Catch:{ Exception -> 0x09db }
            r2.ae = r3     // Catch:{ Exception -> 0x09db }
            return r2
        L_0x09db:
            r0 = move-exception
            r3 = r0
            java.lang.String r4 = h
            java.lang.String r5 = "Exception Thrown in During Collection"
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r4, r5, r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.paypal.android.sdk.onetouch.core.metadata.h.w():com.paypal.android.sdk.onetouch.core.metadata.g");
    }

    public final String a(Context context, String str, m mVar, String str2, Map<String, Object> map) {
        String str3;
        String a = ai.a(map, "RISK_MANAGER_CONF_URL", (String) null);
        String a2 = ai.a(map, "RISK_MANAGER_PAIRING_ID", (String) null);
        this.L = ai.a(map, "RISK_MANAGER_NOTIF_TOKEN", (String) null);
        b = (u) ai.a(map, u.class, "RISK_MANAGER_NETWORK_ADAPTER", new y());
        boolean a3 = ai.a(map, "RISK_MANAGER_IS_DISABLE_REMOTE_CONFIG", Boolean.valueOf(false));
        this.K = false;
        this.r = context;
        this.s = ai.c(context, str);
        if (mVar == null) {
            this.H = m.UNKNOWN;
        } else {
            this.H = mVar;
        }
        this.I = str2;
        this.A = null;
        this.B = null;
        this.w = 0;
        this.v = 0;
        if (a2 == null || a2.trim().length() == 0) {
            str3 = k();
        } else {
            ai.a(3, "PRD", "Using custom pairing id");
            str3 = a2.trim();
        }
        this.J = str3;
        if (a == null) {
            a = "https://www.paypalobjects.com/webstatic/risk/dyson_config_android_v3.json";
        }
        try {
            this.y = a;
            ai.a(h, "Host activity detected");
            this.x = System.currentTimeMillis();
            if (this.G == null) {
                this.G = new k(this);
                LocationManager locationManager = (LocationManager) this.r.getSystemService("location");
                if (locationManager != null) {
                    onLocationChanged(ai.a(locationManager));
                    if (locationManager.isProviderEnabled("network")) {
                        locationManager.requestLocationUpdates("network", 3600000, 10.0f, this);
                    }
                }
            }
            n();
        } catch (Exception e) {
            ai.a(h, (String) null, (Throwable) e);
        }
        l();
        a(new c(this.r, !a3));
        return this.J;
    }

    public final String a(String str, Map<String, Object> map) {
        String str2;
        this.D = null;
        if (str != null && this.J != null && str.equals(this.J)) {
            return str;
        }
        if (str == null || str.trim().length() == 0) {
            str2 = k();
        } else {
            str2 = str.trim();
            ai.a(3, "PRD", "Using custom pairing id");
        }
        this.J = str2;
        b();
        l();
        return str2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0060, code lost:
        com.paypal.android.sdk.onetouch.core.metadata.ai.a(r5, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0063, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b5, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001f, code lost:
        com.paypal.android.sdk.onetouch.core.metadata.ai.a(r1, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(android.os.Message r5) {
        /*
            r4 = this;
            r0 = 0
            int r1 = r5.what     // Catch:{ Exception -> 0x00e4 }
            switch(r1) {
                case 0: goto L_0x00d0;
                case 1: goto L_0x00b6;
                case 2: goto L_0x0077;
                default: goto L_0x0006;
            }     // Catch:{ Exception -> 0x00e4 }
        L_0x0006:
            switch(r1) {
                case 10: goto L_0x0064;
                case 11: goto L_0x005c;
                case 12: goto L_0x0052;
                default: goto L_0x0009;
            }     // Catch:{ Exception -> 0x00e4 }
        L_0x0009:
            switch(r1) {
                case 20: goto L_0x003f;
                case 21: goto L_0x0023;
                case 22: goto L_0x000d;
                default: goto L_0x000c;
            }     // Catch:{ Exception -> 0x00e4 }
        L_0x000c:
            return
        L_0x000d:
            java.lang.String r1 = h     // Catch:{ Exception -> 0x00e4 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r3 = "Beacon returned: "
            r2.<init>(r3)     // Catch:{ Exception -> 0x00e4 }
            java.lang.Object r5 = r5.obj     // Catch:{ Exception -> 0x00e4 }
            r2.append(r5)     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r5 = r2.toString()     // Catch:{ Exception -> 0x00e4 }
        L_0x001f:
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r1, r5)     // Catch:{ Exception -> 0x00e4 }
            return
        L_0x0023:
            java.lang.String r1 = h     // Catch:{ Exception -> 0x00e4 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r3 = "BeaconRequest failed "
            r2.<init>(r3)     // Catch:{ Exception -> 0x00e4 }
            java.lang.Object r5 = r5.obj     // Catch:{ Exception -> 0x00e4 }
            java.lang.Exception r5 = (java.lang.Exception) r5     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r5 = r5.getMessage()     // Catch:{ Exception -> 0x00e4 }
            r2.append(r5)     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r5 = r2.toString()     // Catch:{ Exception -> 0x00e4 }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r1, r5)     // Catch:{ Exception -> 0x00e4 }
            return
        L_0x003f:
            java.lang.String r1 = h     // Catch:{ Exception -> 0x00e4 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r3 = "Beacon URL: "
            r2.<init>(r3)     // Catch:{ Exception -> 0x00e4 }
            java.lang.Object r5 = r5.obj     // Catch:{ Exception -> 0x00e4 }
            r2.append(r5)     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r5 = r2.toString()     // Catch:{ Exception -> 0x00e4 }
            goto L_0x001f
        L_0x0052:
            java.lang.Object r5 = r5.obj     // Catch:{ Exception -> 0x00e4 }
            com.paypal.android.sdk.onetouch.core.metadata.c r5 = (com.paypal.android.sdk.onetouch.core.metadata.c) r5     // Catch:{ Exception -> 0x00e4 }
            if (r5 == 0) goto L_0x00b5
            r4.a(r5)     // Catch:{ Exception -> 0x00e4 }
            return
        L_0x005c:
            java.lang.String r5 = h     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r1 = "LoadConfigurationRequest failed."
        L_0x0060:
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r5, r1)     // Catch:{ Exception -> 0x00e4 }
            return
        L_0x0064:
            java.lang.String r1 = h     // Catch:{ Exception -> 0x00e4 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r3 = "Load Configuration URL: "
            r2.<init>(r3)     // Catch:{ Exception -> 0x00e4 }
            java.lang.Object r5 = r5.obj     // Catch:{ Exception -> 0x00e4 }
            r2.append(r5)     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r5 = r2.toString()     // Catch:{ Exception -> 0x00e4 }
            goto L_0x001f
        L_0x0077:
            java.lang.Object r5 = r5.obj     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r1 = h     // Catch:{ Exception -> 0x00e4 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r3 = "LogRiskMetadataRequest Server returned: "
            r2.<init>(r3)     // Catch:{ Exception -> 0x00e4 }
            r2.append(r5)     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00e4 }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r1, r2)     // Catch:{ Exception -> 0x00e4 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r2 = "?"
            r1.<init>(r2)     // Catch:{ Exception -> 0x00e4 }
            r1.append(r5)     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r5 = r1.toString()     // Catch:{ Exception -> 0x00e4 }
            android.net.Uri r5 = android.net.Uri.parse(r5)     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r1 = "responseEnvelope.ack"
            java.lang.String r5 = r5.getQueryParameter(r1)     // Catch:{ UnsupportedOperationException -> 0x00a7 }
            goto L_0x00a8
        L_0x00a7:
            r5 = r0
        L_0x00a8:
            java.lang.String r1 = "Success"
            boolean r5 = r1.equals(r5)     // Catch:{ Exception -> 0x00e4 }
            if (r5 == 0) goto L_0x00b5
            java.lang.String r5 = h     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r1 = "LogRiskMetadataRequest Success"
            goto L_0x0060
        L_0x00b5:
            return
        L_0x00b6:
            java.lang.String r1 = h     // Catch:{ Exception -> 0x00e4 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r3 = "LogRiskMetadataRequest failed."
            r2.<init>(r3)     // Catch:{ Exception -> 0x00e4 }
            java.lang.Object r5 = r5.obj     // Catch:{ Exception -> 0x00e4 }
            java.lang.Exception r5 = (java.lang.Exception) r5     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r5 = r5.getMessage()     // Catch:{ Exception -> 0x00e4 }
            r2.append(r5)     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r5 = r2.toString()     // Catch:{ Exception -> 0x00e4 }
            goto L_0x001f
        L_0x00d0:
            java.lang.String r1 = h     // Catch:{ Exception -> 0x00e4 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r3 = "Dyson Async URL: "
            r2.<init>(r3)     // Catch:{ Exception -> 0x00e4 }
            java.lang.Object r5 = r5.obj     // Catch:{ Exception -> 0x00e4 }
            r2.append(r5)     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r5 = r2.toString()     // Catch:{ Exception -> 0x00e4 }
            goto L_0x001f
        L_0x00e4:
            r5 = move-exception
            java.lang.String r1 = h
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r1, r0, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.paypal.android.sdk.onetouch.core.metadata.h.a(android.os.Message):void");
    }

    public final String b(String str) {
        return a(str, null);
    }

    public final void b() {
        l.a();
        this.A = w();
        a(this.A, (g) null);
    }

    public final String d() {
        return String.format(Locale.US, "Dyson/%S (%S %S)", new Object[]{"3.5.7.release", "Android", VERSION.RELEASE});
    }

    public final void i() {
        new Timer().schedule(new j(this), 0);
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            this.E = new Location(location);
            String str = h;
            StringBuilder sb = new StringBuilder("Location Update: ");
            sb.append(location.toString());
            ai.a(str, sb.toString());
        }
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
