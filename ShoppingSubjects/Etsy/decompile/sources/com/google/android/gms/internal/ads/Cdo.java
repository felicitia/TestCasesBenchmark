package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.ao;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@bu
/* renamed from: com.google.android.gms.internal.ads.do reason: invalid class name */
public final class Cdo {
    private boolean A = false;
    private zzael B;
    private boolean C = false;
    private String D;
    private List<String> E;
    private boolean F;
    private String G;
    private zzaiq H;
    private boolean I;
    private boolean J;
    private boolean K;
    private boolean L;
    private String M;
    private final zzaef N;
    private String a;
    private String b;
    private String c;
    private List<String> d;
    private String e;
    private String f;
    private String g;
    private List<String> h;
    private List<String> i;
    private long j = -1;
    private boolean k = false;
    private final long l = -1;
    private List<String> m;
    private long n = -1;
    private int o = -1;
    private boolean p = false;
    private boolean q = false;
    private boolean r = false;
    private boolean s = true;
    private boolean t = true;
    private String u = "";
    private boolean v = false;
    private boolean w = false;
    private zzaig x;
    private List<String> y;
    private List<String> z;

    public Cdo(zzaef zzaef, String str) {
        this.b = str;
        this.N = zzaef;
    }

    private static String a(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (String) list.get(0);
    }

    private static long b(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        if (list != null && !list.isEmpty()) {
            String str2 = (String) list.get(0);
            try {
                return (long) (Float.parseFloat(str2) * 1000.0f);
            } catch (NumberFormatException unused) {
                StringBuilder sb = new StringBuilder(36 + String.valueOf(str).length() + String.valueOf(str2).length());
                sb.append("Could not parse float from ");
                sb.append(str);
                sb.append(" header: ");
                sb.append(str2);
                gv.e(sb.toString());
            }
        }
        return -1;
    }

    private static List<String> c(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        if (list != null && !list.isEmpty()) {
            String str2 = (String) list.get(0);
            if (str2 != null) {
                return Arrays.asList(str2.trim().split("\\s+"));
            }
        }
        return null;
    }

    private static boolean d(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        if (list == null || list.isEmpty()) {
            return false;
        }
        return Boolean.valueOf((String) list.get(0)).booleanValue();
    }

    public final zzaej a(long j2, boolean z2) {
        zzaef zzaef = this.N;
        String str = this.b;
        String str2 = this.c;
        List<String> list = this.d;
        List<String> list2 = this.h;
        long j3 = this.j;
        boolean z3 = this.k;
        List<String> list3 = this.m;
        long j4 = this.n;
        int i2 = this.o;
        String str3 = this.a;
        String str4 = this.f;
        String str5 = this.g;
        String str6 = str3;
        boolean z4 = this.p;
        boolean z5 = this.q;
        boolean z6 = this.r;
        boolean z7 = this.s;
        String str7 = this.u;
        boolean z8 = this.v;
        boolean z9 = this.w;
        zzaig zzaig = this.x;
        List<String> list4 = this.y;
        List<String> list5 = this.z;
        boolean z10 = this.A;
        zzael zzael = this.B;
        boolean z11 = this.C;
        String str8 = this.D;
        List<String> list6 = this.E;
        boolean z12 = this.F;
        String str9 = this.G;
        zzaiq zzaiq = this.H;
        String str10 = this.e;
        boolean z13 = this.t;
        boolean z14 = this.I;
        boolean z15 = this.J;
        int i3 = z2 ? 2 : 1;
        String str11 = str6;
        long j5 = j2;
        zzaej zzaej = new zzaej(zzaef, str, str2, list, list2, j3, z3, -1, list3, j4, i2, str11, j5, str4, str5, z4, z5, z6, z7, false, str7, z8, z9, zzaig, list4, list5, z10, zzael, z11, str8, list6, z12, str9, zzaiq, str10, z13, z14, z15, i3, this.K, this.i, this.L, this.M);
        return zzaej;
    }

    public final void a(String str, Map<String, List<String>> map, String str2) {
        this.c = str2;
        a(map);
    }

    public final void a(Map<String, List<String>> map) {
        int a2;
        this.a = a(map, "X-Afma-Ad-Size");
        this.G = a(map, "X-Afma-Ad-Slot-Size");
        List<String> c2 = c(map, "X-Afma-Click-Tracking-Urls");
        if (c2 != null) {
            this.d = c2;
        }
        this.e = a(map, "X-Afma-Debug-Signals");
        List list = (List) map.get("X-Afma-Debug-Dialog");
        if (list != null && !list.isEmpty()) {
            this.f = (String) list.get(0);
        }
        List<String> c3 = c(map, "X-Afma-Tracking-Urls");
        if (c3 != null) {
            this.h = c3;
        }
        List<String> c4 = c(map, "X-Afma-Downloaded-Impression-Urls");
        if (c4 != null) {
            this.i = c4;
        }
        long b2 = b(map, "X-Afma-Interstitial-Timeout");
        if (b2 != -1) {
            this.j = b2;
        }
        this.k |= d(map, "X-Afma-Mediation");
        List<String> c5 = c(map, "X-Afma-Manual-Tracking-Urls");
        if (c5 != null) {
            this.m = c5;
        }
        long b3 = b(map, "X-Afma-Refresh-Rate");
        if (b3 != -1) {
            this.n = b3;
        }
        List list2 = (List) map.get("X-Afma-Orientation");
        if (list2 != null && !list2.isEmpty()) {
            String str = (String) list2.get(0);
            if ("portrait".equalsIgnoreCase(str)) {
                a2 = ao.g().b();
            } else if ("landscape".equalsIgnoreCase(str)) {
                a2 = ao.g().a();
            }
            this.o = a2;
        }
        this.g = a(map, "X-Afma-ActiveView");
        List list3 = (List) map.get("X-Afma-Use-HTTPS");
        if (list3 != null && !list3.isEmpty()) {
            this.r = Boolean.valueOf((String) list3.get(0)).booleanValue();
        }
        this.p |= d(map, "X-Afma-Custom-Rendering-Allowed");
        this.q = "native".equals(a(map, "X-Afma-Ad-Format"));
        List list4 = (List) map.get("X-Afma-Content-Url-Opted-Out");
        if (list4 != null && !list4.isEmpty()) {
            this.s = Boolean.valueOf((String) list4.get(0)).booleanValue();
        }
        List list5 = (List) map.get("X-Afma-Content-Vertical-Opted-Out");
        if (list5 != null && !list5.isEmpty()) {
            this.t = Boolean.valueOf((String) list5.get(0)).booleanValue();
        }
        List list6 = (List) map.get("X-Afma-Gws-Query-Id");
        if (list6 != null && !list6.isEmpty()) {
            this.u = (String) list6.get(0);
        }
        String a3 = a(map, "X-Afma-Fluid");
        if (a3 != null && a3.equals(ResponseConstants.HEIGHT)) {
            this.v = true;
        }
        this.w = "native_express".equals(a(map, "X-Afma-Ad-Format"));
        this.x = zzaig.zzce(a(map, "X-Afma-Rewards"));
        if (this.y == null) {
            this.y = c(map, "X-Afma-Reward-Video-Start-Urls");
        }
        if (this.z == null) {
            this.z = c(map, "X-Afma-Reward-Video-Complete-Urls");
        }
        this.A |= d(map, "X-Afma-Use-Displayed-Impression");
        this.C |= d(map, "X-Afma-Auto-Collect-Location");
        this.D = a(map, "Set-Cookie");
        String a4 = a(map, "X-Afma-Auto-Protection-Configuration");
        if (a4 == null || TextUtils.isEmpty(a4)) {
            Builder buildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204").buildUpon();
            buildUpon.appendQueryParameter("id", "gmob-apps-blocked-navigation");
            if (!TextUtils.isEmpty(this.f)) {
                buildUpon.appendQueryParameter("debugDialog", this.f);
            }
            boolean booleanValue = ((Boolean) ajh.f().a(akl.g)).booleanValue();
            String builder = buildUpon.toString();
            StringBuilder sb = new StringBuilder(31 + String.valueOf(builder).length());
            sb.append(builder);
            sb.append("&navigationURL={NAVIGATION_URL}");
            this.B = new zzael(booleanValue, Arrays.asList(new String[]{sb.toString()}));
        } else {
            try {
                this.B = zzael.zzl(new JSONObject(a4));
            } catch (JSONException e2) {
                gv.c("Error parsing configuration JSON", e2);
                this.B = new zzael();
            }
        }
        List<String> c6 = c(map, "X-Afma-Remote-Ping-Urls");
        if (c6 != null) {
            this.E = c6;
        }
        String a5 = a(map, "X-Afma-Safe-Browsing");
        if (!TextUtils.isEmpty(a5)) {
            try {
                this.H = zzaiq.zzo(new JSONObject(a5));
            } catch (JSONException e3) {
                gv.c("Error parsing safe browsing header", e3);
            }
        }
        this.F |= d(map, "X-Afma-Render-In-Browser");
        String a6 = a(map, "X-Afma-Pool");
        if (!TextUtils.isEmpty(a6)) {
            try {
                this.I = new JSONObject(a6).getBoolean("never_pool");
            } catch (JSONException e4) {
                gv.c("Error parsing interstitial pool header", e4);
            }
        }
        this.J = d(map, "X-Afma-Custom-Close-Blocked");
        this.K = d(map, "X-Afma-Enable-Omid");
        this.L = d(map, "X-Afma-Disable-Closable-Area");
        this.M = a(map, "X-Afma-Omid-Settings");
    }
}
