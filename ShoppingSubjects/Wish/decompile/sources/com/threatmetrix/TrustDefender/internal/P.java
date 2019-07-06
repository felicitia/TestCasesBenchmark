package com.threatmetrix.TrustDefender.internal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.os.Looper;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.util.SparseIntArray;
import com.threatmetrix.TrustDefender.internal.D2.E;
import com.threatmetrix.TrustDefender.internal.K7.W;
import java.io.File;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class P {

    /* renamed from: for reason: not valid java name */
    private static final Method f474for = D2.m42do(D2.m38do(E.SYSTEM_PROPERTIES), "get", String.class);

    /* renamed from: if reason: not valid java name */
    private static final String f475if = TL.m331if(P.class);

    /* renamed from: new reason: not valid java name */
    private static final SparseIntArray f476new = new SparseIntArray(5);

    static class I {

        /* renamed from: for reason: not valid java name */
        int f477for = 0;

        /* renamed from: new reason: not valid java name */
        int f478new = 0;

        I() {
        }
    }

    enum L {
        CDMA("CDMA", 3),
        GSM("GSM", 1),
        LTE("LTE", 0),
        UNKOWN("OTHER", 99),
        WCDMA("UMTS", 2);
        

        /* renamed from: else reason: not valid java name */
        final String f485else;

        /* renamed from: try reason: not valid java name */
        final int f486try;

        private L(String str, int i) {
            this.f485else = str;
            this.f486try = i;
        }
    }

    public static class O {

        /* renamed from: for reason: not valid java name */
        public final Context f487for;

        public O(Context context) {
            this.f487for = context;
        }
    }

    /* renamed from: byte reason: not valid java name */
    static String m235byte() {
        return "android";
    }

    /* renamed from: do reason: not valid java name */
    static boolean m245do() {
        return true;
    }

    /* renamed from: else reason: not valid java name */
    static String m246else() {
        return "Android";
    }

    P() {
    }

    static {
        Class cls = D2.m38do(E.DEVICE_POLICY_MANAGER);
        Object obj = D2.m43for(cls, "ENCRYPTION_STATUS_UNSUPPORTED");
        if (obj != null) {
            f476new.put(((Integer) obj).intValue(), 1);
        }
        Object obj2 = D2.m43for(cls, "ENCRYPTION_STATUS_INACTIVE");
        if (obj2 != null) {
            f476new.put(((Integer) obj2).intValue(), 2);
        }
        Object obj3 = D2.m43for(cls, "ENCRYPTION_STATUS_ACTIVATING");
        if (obj3 != null) {
            f476new.put(((Integer) obj3).intValue(), 4);
        }
        Object obj4 = D2.m43for(cls, "ENCRYPTION_STATUS_ACTIVE");
        if (obj4 != null) {
            f476new.put(((Integer) obj4).intValue(), 8);
        }
        Object obj5 = D2.m43for(cls, "ENCRYPTION_STATUS_ACTIVE_DEFAULT_KEY");
        if (obj5 != null) {
            f476new.put(((Integer) obj5).intValue(), 32);
        }
        Object obj6 = D2.m43for(cls, "ENCRYPTION_STATUS_ACTIVE_PER_USER");
        if (obj6 != null) {
            f476new.put(((Integer) obj6).intValue(), 64);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00d4, code lost:
        if (r12.equalsIgnoreCase(com.threatmetrix.TrustDefender.internal.N.I.C0012I.f387do) != false) goto L_0x01c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ea, code lost:
        if (r12.equalsIgnoreCase(java.lang.String.valueOf(com.threatmetrix.TrustDefender.internal.N.I.f378for)) != false) goto L_0x01c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00fc, code lost:
        if (r12.equalsIgnoreCase(com.threatmetrix.TrustDefender.internal.N.I.f376do) != false) goto L_0x01c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x010e, code lost:
        if (r12.equalsIgnoreCase(com.threatmetrix.TrustDefender.internal.N.I.f380if) != false) goto L_0x01c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0120, code lost:
        if (r12.equalsIgnoreCase(com.threatmetrix.TrustDefender.internal.N.I.f381int) != false) goto L_0x01c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0132, code lost:
        if (r12.equalsIgnoreCase(com.threatmetrix.TrustDefender.internal.N.I.f372case) != false) goto L_0x01c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0144, code lost:
        if (r12.equalsIgnoreCase(com.threatmetrix.TrustDefender.internal.N.I.f385try) != false) goto L_0x01c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01a3, code lost:
        if (r12.equalsIgnoreCase(r14) != false) goto L_0x01c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01b1, code lost:
        if (r12.equalsIgnoreCase(r15) != false) goto L_0x01c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01bf, code lost:
        if (r12.equalsIgnoreCase(r4) != false) goto L_0x01c1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01c8  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01cc  */
    /* renamed from: if reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.util.List<java.lang.String> m258if(com.threatmetrix.TrustDefender.internal.P.O r17, java.util.List<java.lang.String> r18) throws java.lang.InterruptedException {
        /*
            r0 = r17
            r1 = r18
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            if (r1 == 0) goto L_0x02be
            boolean r3 = r18.isEmpty()
            if (r3 == 0) goto L_0x0013
            goto L_0x02be
        L_0x0013:
            com.threatmetrix.TrustDefender.internal.PH r3 = com.threatmetrix.TrustDefender.internal.PH.m275do()
            int r4 = r18.size()
            java.lang.String[] r4 = new java.lang.String[r4]
            java.lang.Object[] r4 = r1.toArray(r4)
            java.lang.String[] r4 = (java.lang.String[]) r4
            java.lang.String[] r3 = r3.m289for(r4)
            r4 = 0
            java.util.Map r5 = m259if(r0, r4)
            java.lang.String r6 = com.threatmetrix.TrustDefender.internal.N.I.f380if
            com.threatmetrix.TrustDefender.internal.N$R r7 = new com.threatmetrix.TrustDefender.internal.N$R
            com.threatmetrix.TrustDefender.internal.N r8 = new com.threatmetrix.TrustDefender.internal.N
            r8.<init>()
            android.content.Context r0 = r0.f487for
            r7.<init>(r0)
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r8 = 0
            r9 = 0
        L_0x0041:
            int r10 = r18.size()
            r11 = 1
            if (r8 >= r10) goto L_0x01d5
            java.lang.Object r10 = r1.get(r8)
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Thread r12 = java.lang.Thread.currentThread()
            boolean r12 = r12.isInterrupted()
            if (r12 == 0) goto L_0x005e
            java.lang.InterruptedException r0 = new java.lang.InterruptedException
            r0.<init>()
            throw r0
        L_0x005e:
            java.lang.String r12 = "file://"
            boolean r12 = r10.startsWith(r12)
            if (r12 == 0) goto L_0x007c
            java.net.URI r11 = new java.net.URI     // Catch:{ URISyntaxException -> 0x01d0 }
            r11.<init>(r10)     // Catch:{ URISyntaxException -> 0x01d0 }
            java.lang.String r12 = r11.getScheme()     // Catch:{ URISyntaxException -> 0x01d0 }
            if (r12 == 0) goto L_0x01c5
            java.io.File r12 = new java.io.File     // Catch:{ URISyntaxException -> 0x01d0 }
            r12.<init>(r11)     // Catch:{ URISyntaxException -> 0x01d0 }
            boolean r11 = r12.exists()     // Catch:{ URISyntaxException -> 0x01d0 }
            goto L_0x01c6
        L_0x007c:
            java.lang.String r12 = "tags://"
            boolean r12 = r10.startsWith(r12)
            r13 = 7
            if (r12 == 0) goto L_0x0097
            java.lang.String r11 = r10.substring(r13)
            if (r6 == 0) goto L_0x01c5
            boolean r12 = com.threatmetrix.TrustDefender.internal.NK.m203byte(r11)
            if (r12 == 0) goto L_0x01c5
            boolean r11 = r6.contains(r11)
            goto L_0x01c6
        L_0x0097:
            java.lang.String r12 = "pkg://"
            boolean r12 = r10.startsWith(r12)
            if (r12 == 0) goto L_0x00ac
            r11 = 6
            java.lang.String r11 = r10.substring(r11)
            int r12 = com.threatmetrix.TrustDefender.internal.N.W.f430do
            boolean r11 = r7.m170int(r11, r12)
            goto L_0x01c6
        L_0x00ac:
            java.lang.String r12 = "prop://"
            boolean r12 = r10.startsWith(r12)
            r14 = 2
            if (r12 == 0) goto L_0x015f
            java.lang.String r12 = r10.substring(r13)
            java.lang.String r13 = "\\?"
            java.lang.String[] r12 = r12.split(r13, r14)
            int r13 = r12.length
            if (r13 < r14) goto L_0x01d0
            r13 = r12[r4]
            r12 = r12[r11]
            java.lang.String r14 = "ro.build.version.codename"
            boolean r14 = r13.equals(r14)
            if (r14 == 0) goto L_0x00d8
            java.lang.String r13 = com.threatmetrix.TrustDefender.internal.N.I.C0012I.f387do
            boolean r12 = r12.equalsIgnoreCase(r13)
            if (r12 == 0) goto L_0x0148
            goto L_0x01c1
        L_0x00d8:
            java.lang.String r14 = "ro.build.date.utc"
            boolean r14 = r13.equals(r14)
            if (r14 == 0) goto L_0x00ee
            long r13 = com.threatmetrix.TrustDefender.internal.N.I.f378for
            java.lang.String r13 = java.lang.String.valueOf(r13)
            boolean r12 = r12.equalsIgnoreCase(r13)
            if (r12 == 0) goto L_0x0148
            goto L_0x01c1
        L_0x00ee:
            java.lang.String r14 = "ro.build.type"
            boolean r14 = r13.equals(r14)
            if (r14 == 0) goto L_0x0100
            java.lang.String r13 = com.threatmetrix.TrustDefender.internal.N.I.f376do
            boolean r12 = r12.equalsIgnoreCase(r13)
            if (r12 == 0) goto L_0x0148
            goto L_0x01c1
        L_0x0100:
            java.lang.String r14 = "ro.build.tags"
            boolean r14 = r13.equals(r14)
            if (r14 == 0) goto L_0x0112
            java.lang.String r13 = com.threatmetrix.TrustDefender.internal.N.I.f380if
            boolean r12 = r12.equalsIgnoreCase(r13)
            if (r12 == 0) goto L_0x0148
            goto L_0x01c1
        L_0x0112:
            java.lang.String r14 = "ro.build.host"
            boolean r14 = r13.equals(r14)
            if (r14 == 0) goto L_0x0124
            java.lang.String r13 = com.threatmetrix.TrustDefender.internal.N.I.f381int
            boolean r12 = r12.equalsIgnoreCase(r13)
            if (r12 == 0) goto L_0x0148
            goto L_0x01c1
        L_0x0124:
            java.lang.String r14 = "ro.build.user"
            boolean r14 = r13.equals(r14)
            if (r14 == 0) goto L_0x0136
            java.lang.String r13 = com.threatmetrix.TrustDefender.internal.N.I.f372case
            boolean r12 = r12.equalsIgnoreCase(r13)
            if (r12 == 0) goto L_0x0148
            goto L_0x01c1
        L_0x0136:
            java.lang.String r14 = "ro.build.id"
            boolean r14 = r13.equals(r14)
            if (r14 == 0) goto L_0x014a
            java.lang.String r13 = com.threatmetrix.TrustDefender.internal.N.I.f385try
            boolean r12 = r12.equalsIgnoreCase(r13)
            if (r12 == 0) goto L_0x0148
            goto L_0x01c1
        L_0x0148:
            r11 = 0
            goto L_0x015d
        L_0x014a:
            java.lang.Object r14 = r0.get(r13)
            java.util.Map r14 = (java.util.Map) r14
            if (r14 != 0) goto L_0x015a
            java.util.HashMap r14 = new java.util.HashMap
            r14.<init>()
            r0.put(r13, r14)
        L_0x015a:
            r14.put(r12, r10)
        L_0x015d:
            r4 = r11
            goto L_0x01c5
        L_0x015f:
            java.lang.String r12 = "id://"
            boolean r12 = r10.startsWith(r12)
            if (r12 == 0) goto L_0x01c4
            r12 = 5
            java.lang.String r12 = r10.substring(r12)
            java.lang.String r13 = "\\?"
            java.lang.String[] r12 = r12.split(r13, r14)
            int r13 = r12.length
            if (r13 != r14) goto L_0x01c4
            int r13 = r5.size()
            if (r13 <= 0) goto L_0x01c4
            r13 = r12[r4]
            r12 = r12[r11]
            java.lang.String r14 = "ln"
            java.lang.Object r14 = r5.get(r14)
            java.lang.String r14 = (java.lang.String) r14
            java.lang.String r15 = "si"
            java.lang.Object r15 = r5.get(r15)
            java.lang.String r15 = (java.lang.String) r15
            java.lang.String r4 = "di"
            java.lang.Object r4 = r5.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r11 = "phone"
            boolean r11 = r11.equalsIgnoreCase(r13)
            if (r11 == 0) goto L_0x01a5
            boolean r11 = r12.equalsIgnoreCase(r14)
            if (r11 != 0) goto L_0x01c1
        L_0x01a5:
            java.lang.String r11 = "imsi"
            boolean r11 = r11.equalsIgnoreCase(r13)
            if (r11 == 0) goto L_0x01b3
            boolean r11 = r12.equalsIgnoreCase(r15)
            if (r11 != 0) goto L_0x01c1
        L_0x01b3:
            java.lang.String r11 = "imei"
            boolean r11 = r11.equalsIgnoreCase(r13)
            if (r11 == 0) goto L_0x01c4
            boolean r4 = r12.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x01c4
        L_0x01c1:
            r4 = 0
            r11 = 1
            goto L_0x01c6
        L_0x01c4:
            r4 = 0
        L_0x01c5:
            r11 = 0
        L_0x01c6:
            if (r11 == 0) goto L_0x01cc
            r2.add(r10)
            goto L_0x01d0
        L_0x01cc:
            if (r4 != 0) goto L_0x01d0
            int r9 = r9 + 1
        L_0x01d0:
            int r8 = r8 + 1
            r4 = 0
            goto L_0x0041
        L_0x01d5:
            boolean r4 = r0.isEmpty()
            if (r4 != 0) goto L_0x026f
            java.lang.String r4 = "/default.prop"
            java.util.Set r5 = r0.keySet()
            java.lang.String r6 = "="
            java.util.Map r4 = m252for(r4, r5, r6)
            java.lang.String r5 = "/system/build.prop"
            java.util.Set r6 = r0.keySet()
            java.lang.String r7 = "="
            java.util.Map r5 = m252for(r5, r6, r7)
            r4.putAll(r5)
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x01fe:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x026f
            java.lang.Object r5 = r0.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r6 = r5.getKey()
            java.lang.Object r6 = r4.get(r6)
            java.lang.String r6 = (java.lang.String) r6
            if (r6 != 0) goto L_0x0233
            java.lang.Object r6 = r5.getKey()
            java.lang.String r6 = (java.lang.String) r6
            java.lang.reflect.Method r7 = f474for
            r8 = 1
            java.lang.Object[] r10 = new java.lang.Object[r8]
            r11 = 0
            r10[r11] = r6
            r6 = 0
            java.lang.Object r7 = com.threatmetrix.TrustDefender.internal.D2.m39do(r6, r7, r10)
            java.lang.String r7 = (java.lang.String) r7
            boolean r10 = com.threatmetrix.TrustDefender.internal.NK.m203byte(r7)
            if (r10 == 0) goto L_0x0235
            r6 = r7
            goto L_0x0235
        L_0x0233:
            r8 = 1
            r11 = 0
        L_0x0235:
            if (r6 == 0) goto L_0x0261
            java.lang.Object r7 = r5.getValue()
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r6 = r7.get(r6)
            java.lang.String r6 = (java.lang.String) r6
            if (r6 == 0) goto L_0x024a
            r2.add(r6)
            r6 = 1
            goto L_0x024b
        L_0x024a:
            r6 = 0
        L_0x024b:
            java.lang.Object r7 = r5.getValue()
            java.util.Map r7 = (java.util.Map) r7
            java.lang.String r10 = "nil"
            java.lang.Object r7 = r7.get(r10)
            java.lang.String r7 = (java.lang.String) r7
            if (r7 == 0) goto L_0x0262
            int r6 = r6 + 1
            r2.add(r7)
            goto L_0x0262
        L_0x0261:
            r6 = 0
        L_0x0262:
            java.lang.Object r5 = r5.getValue()
            java.util.Map r5 = (java.util.Map) r5
            int r5 = r5.size()
            int r5 = r5 - r6
            int r9 = r9 + r5
            goto L_0x01fe
        L_0x026f:
            r11 = 0
            java.lang.String r0 = f475if
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "matched "
            r4.<init>(r5)
            r4.append(r9)
            java.lang.String r5 = "/"
            r4.append(r5)
            int r1 = r18.size()
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            com.threatmetrix.TrustDefender.internal.TL.m338new(r0, r1)
            if (r3 == 0) goto L_0x02a9
            int r0 = r3.length
            if (r0 <= 0) goto L_0x02a9
            int r0 = r3.length
        L_0x0295:
            if (r11 >= r0) goto L_0x02a9
            r1 = r3[r11]
            java.lang.String r4 = "a"
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r1 = r4.concat(r1)
            r2.add(r1)
            int r11 = r11 + 1
            goto L_0x0295
        L_0x02a9:
            java.util.Collections.sort(r2)
            boolean r0 = r2.isEmpty()
            if (r0 != 0) goto L_0x02bd
            boolean r0 = com.threatmetrix.TrustDefender.internal.TL.m339new()
            if (r0 == 0) goto L_0x02bd
            java.lang.String r0 = ";"
            com.threatmetrix.TrustDefender.internal.NK.m217int(r2, r0)
        L_0x02bd:
            return r2
        L_0x02be:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.P.m258if(com.threatmetrix.TrustDefender.internal.P$O, java.util.List):java.util.List");
    }

    /* renamed from: int reason: not valid java name */
    static String m261int(StringBuilder sb) throws InterruptedException {
        if (PH.m275do().f494char) {
            List list = PH.m275do().m299int("/system/fonts");
            if (list == null || list.isEmpty() || list.size() != 2) {
                return null;
            }
            String str = (String) list.get(0);
            sb.append((String) list.get(1));
            return str;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        String str2 = ".ttf";
        String[] list2 = new File("/system/fonts/").list();
        if (list2 != null) {
            for (String str3 : list2) {
                if (str3 != null && str3.endsWith(str2)) {
                    StringBuilder sb2 = new StringBuilder(str3);
                    arrayList.add(sb2.substring(0, sb2.length() - 4));
                }
            }
        }
        StringBuilder sb3 = new StringBuilder();
        for (String append : arrayList) {
            sb3.append(append);
        }
        sb.append(arrayList.size());
        return NK.m208do(sb3.toString());
    }

    /* renamed from: for reason: not valid java name */
    static String m250for() {
        Locale locale = Locale.getDefault();
        StringBuilder sb = new StringBuilder();
        String language = locale.getLanguage();
        if (language != null) {
            sb.append(language);
            String country = locale.getCountry();
            if (country != null) {
                sb.append("-");
                sb.append(country);
            }
        }
        return sb.toString();
    }

    /* renamed from: new reason: not valid java name */
    static String m267new() {
        Locale locale = Locale.getDefault();
        StringBuilder sb = new StringBuilder();
        String language = locale.getLanguage();
        if (language != null) {
            sb.append(language);
            String country = locale.getCountry();
            if (country != null) {
                sb.append("_");
                sb.append(country);
            }
        }
        return sb.toString();
    }

    /* renamed from: this reason: not valid java name */
    private static String m268this() {
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, new String[]{"Processor", "Hardware", "Serial"});
        HashSet hashSet = new HashSet();
        hashSet.addAll(arrayList);
        Map map = m252for("/proc/cpuinfo", hashSet, ":");
        TL.m338new(f475if, "getCPUInfo returned: ".concat(String.valueOf(map)));
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str = (String) map.get((String) it.next());
            if (str != null) {
                sb.append(str);
                sb.append(",");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        return sb.toString().trim();
    }

    /* renamed from: if reason: not valid java name */
    static String m256if(long j, long j2) throws InterruptedException {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(j));
        sb.append("-");
        sb.append(j2);
        String str = NK.m208do(sb.toString());
        TL.m338new(f475if, "getDeviceState: ".concat(String.valueOf(str)));
        return str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0068 A[SYNTHETIC, Splitter:B:28:0x0068] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006e A[SYNTHETIC, Splitter:B:34:0x006e] */
    /* renamed from: for reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.Map<java.lang.String, java.lang.String> m252for(java.lang.String r6, java.util.Set<java.lang.String> r7, java.lang.String r8) {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            if (r7 == 0) goto L_0x0071
            java.io.File r1 = new java.io.File
            r1.<init>(r6)
            boolean r6 = r1.exists()
            if (r6 == 0) goto L_0x0071
            r6 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x006c, all -> 0x0063 }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ IOException -> 0x006c, all -> 0x0063 }
            r3.<init>(r1)     // Catch:{ IOException -> 0x006c, all -> 0x0063 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x006c, all -> 0x0063 }
        L_0x001d:
            java.lang.String r6 = r2.readLine()     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            if (r6 == 0) goto L_0x005b
            java.lang.String r1 = ""
            java.util.List r6 = com.threatmetrix.TrustDefender.internal.NK.m220int(r6, r8)     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            boolean r3 = r6.isEmpty()     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            if (r3 != 0) goto L_0x001d
            r3 = 0
            java.lang.Object r3 = r6.get(r3)     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            java.lang.String r3 = r3.trim()     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            int r4 = r3.length()     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            if (r4 == 0) goto L_0x001d
            boolean r4 = r7.contains(r3)     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            if (r4 == 0) goto L_0x001d
            int r4 = r6.size()     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            r5 = 1
            if (r4 <= r5) goto L_0x0057
            java.lang.Object r6 = r6.get(r5)     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            java.lang.String r1 = r6.trim()     // Catch:{ IOException -> 0x0061, all -> 0x005f }
        L_0x0057:
            r0.put(r3, r1)     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            goto L_0x001d
        L_0x005b:
            r2.close()     // Catch:{ IOException -> 0x0071 }
            goto L_0x0071
        L_0x005f:
            r6 = move-exception
            goto L_0x0066
        L_0x0061:
            r6 = r2
            goto L_0x006c
        L_0x0063:
            r7 = move-exception
            r2 = r6
            r6 = r7
        L_0x0066:
            if (r2 == 0) goto L_0x006b
            r2.close()     // Catch:{ IOException -> 0x006b }
        L_0x006b:
            throw r6
        L_0x006c:
            if (r6 == 0) goto L_0x0071
            r6.close()     // Catch:{ IOException -> 0x0071 }
        L_0x0071:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.P.m252for(java.lang.String, java.util.Set, java.lang.String):java.util.Map");
    }

    /* renamed from: for reason: not valid java name */
    static String m251for(O o) throws InterruptedException {
        StringBuilder sb = new StringBuilder();
        if (Thread.currentThread().isInterrupted()) {
            return "";
        }
        try {
            Object systemService = o.f487for.getApplicationContext().getSystemService("phone");
            if (systemService != null) {
                if (systemService instanceof TelephonyManager) {
                    TelephonyManager telephonyManager = (TelephonyManager) systemService;
                    String str = "Unknown";
                    if (telephonyManager.getPhoneType() == 1) {
                        str = telephonyManager.getNetworkOperatorName();
                    }
                    sb.append(str);
                    sb.append(telephonyManager.getSimCountryIso());
                    J6 j6 = new J6(Environment.getDataDirectory().getPath());
                    sb.append(((((float) (j6.m102int() * j6.m101if())) / 1024.0f) / 1024.0f) / 1024.0f);
                    Z z = new Z(o);
                    int i = z.m455new();
                    int i2 = z.m454do();
                    if (i >= i2) {
                        sb.append(i);
                        sb.append("x");
                        sb.append(i2);
                    } else {
                        sb.append(i2);
                        sb.append("x");
                        sb.append(i);
                    }
                    sb.append(m268this());
                    HashSet hashSet = new HashSet();
                    Collections.addAll(hashSet, new String[]{"MemTotal"});
                    Map map = m252for("/proc/meminfo", hashSet, ":");
                    TL.m338new(f475if, "getMemInfo returned: ".concat(String.valueOf(map)));
                    String str2 = (String) map.get("MemTotal");
                    if (str2 == null) {
                        str2 = "";
                    }
                    sb.append(str2);
                    sb.append(I.f377else);
                    sb.append(" ");
                    sb.append(I.f374char);
                    sb.append(" ");
                    sb.append(I.f382long);
                    sb.append(" ");
                    sb.append(I.f370break);
                    sb.append(" ");
                    sb.append(C0012I.f389int);
                    return NK.m208do(sb.toString());
                }
            }
            return "";
        } catch (SecurityException unused) {
            return "";
        } catch (Exception e) {
            TL.m338new(f475if, e.toString());
            return "";
        }
    }

    /* renamed from: int reason: not valid java name */
    static boolean m262int(I i) {
        TimeZone timeZone = TimeZone.getDefault();
        if (timeZone == null) {
            return false;
        }
        i.f477for = timeZone.getRawOffset() / 60000;
        i.f478new = timeZone.getDSTSavings() / 60000;
        String str = f475if;
        StringBuilder sb = new StringBuilder("getTimeZoneInfo: dstDiff=");
        sb.append(i.f478new);
        sb.append(" gmfOffset=");
        sb.append(i.f477for);
        TL.m338new(str, sb.toString());
        return true;
    }

    /* renamed from: new reason: not valid java name */
    static Z2 m266new(O o) throws InterruptedException {
        return HZ.m74for(o.f487for);
    }

    /* renamed from: int reason: not valid java name */
    static long m260int() {
        long currentTimeMillis = (System.currentTimeMillis() - S.m171for()) / 1000;
        TL.m338new(f475if, " getBootTime: ".concat(String.valueOf(currentTimeMillis)));
        return currentTimeMillis;
    }

    /* renamed from: if reason: not valid java name */
    static String m257if(O o) {
        L l = new L(o);
        String str = l.f411int != null ? l.f411int.packageName : "";
        B b = new B(o.f487for, str, W.f433int);
        int i = (!W.f429char || b.f363do == null) ? -1 : b.f363do.versionCode;
        String str2 = (!W.f428case || b.f363do == null) ? null : b.f363do.versionName;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(":");
        if (str2 == null) {
            str2 = "-";
        }
        sb.append(str2);
        sb.append(":");
        sb.append(i == -1 ? "-" : Integer.valueOf(i));
        String str3 = PH.m275do().m291if();
        sb.append(":");
        if (str3 == null) {
            str3 = "-";
        }
        sb.append(str3);
        String str4 = f475if;
        StringBuilder sb2 = new StringBuilder("Application Info ");
        sb2.append(sb.toString());
        TL.m338new(str4, sb2.toString());
        return sb.toString();
    }

    /* renamed from: if reason: not valid java name */
    static long m255if() {
        long j;
        long j2;
        J6 j6 = new J6(Environment.getDataDirectory().getPath());
        long j3 = 0;
        if (J6.f243for != null) {
            Long l = (Long) J6.m39do(j6.f247int, J6.f243for, new Object[0]);
            if (l != null) {
                j = l.longValue();
                j2 = j6.m101if();
                if (!(j == 0 || j2 == 0)) {
                    j3 = ((((j * j2) >> 20) << 20) / 10) * 10;
                }
                TL.m338new(f475if, "Free space on the phone ".concat(String.valueOf(j3)));
                return j3;
            }
        }
        if (J6.f241do != null) {
            Integer num = (Integer) J6.m39do(j6.f247int, J6.f241do, new Object[0]);
            if (num != null) {
                j = (long) num.intValue();
                j2 = j6.m101if();
                j3 = ((((j * j2) >> 20) << 20) / 10) * 10;
                TL.m338new(f475if, "Free space on the phone ".concat(String.valueOf(j3)));
                return j3;
            }
        }
        j = 0;
        j2 = j6.m101if();
        j3 = ((((j * j2) >> 20) << 20) / 10) * 10;
        TL.m338new(f475if, "Free space on the phone ".concat(String.valueOf(j3)));
        return j3;
    }

    /* renamed from: case reason: not valid java name */
    static long m237case() {
        J6 j6 = new J6(Environment.getDataDirectory().getPath());
        return j6.m102int() * j6.m101if();
    }

    /* renamed from: int reason: not valid java name */
    static boolean m263int(O o) {
        String str = Z.m198for(o.f487for.getContentResolver(), "mock_location");
        return str != null && str.equals("1");
    }

    @TargetApi(11)
    /* renamed from: do reason: not valid java name */
    static int m243do(O o) {
        if (C0012I.f388for < W.f402if) {
            return 1;
        }
        int i = f476new.get(M.m141do(o.f487for));
        if (i != 0) {
            return i;
        }
        return 16;
    }

    /* JADX WARNING: Removed duplicated region for block: B:105:0x01e5  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x01ea A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0190  */
    /* renamed from: byte reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String m236byte(com.threatmetrix.TrustDefender.internal.P.O r8) {
        /*
            boolean r0 = com.threatmetrix.TrustDefender.internal.N.Y.m189do()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            android.content.Context r0 = r8.f487for     // Catch:{ SecurityException -> 0x01f7, Exception -> 0x01ec }
            android.content.Context r0 = r0.getApplicationContext()     // Catch:{ SecurityException -> 0x01f7, Exception -> 0x01ec }
            java.lang.String r2 = "phone"
            java.lang.Object r0 = r0.getSystemService(r2)     // Catch:{ SecurityException -> 0x01f7, Exception -> 0x01ec }
            if (r0 == 0) goto L_0x01eb
            boolean r2 = r0 instanceof android.telephony.TelephonyManager     // Catch:{ SecurityException -> 0x01f7, Exception -> 0x01ec }
            if (r2 != 0) goto L_0x001c
            goto L_0x01eb
        L_0x001c:
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch:{ SecurityException -> 0x01f7, Exception -> 0x01ec }
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            java.lang.String r3 = r0.getNetworkOperator()     // Catch:{ SecurityException -> 0x0073, Exception -> 0x0069 }
            java.lang.String r4 = r0.getNetworkCountryIso()     // Catch:{ SecurityException -> 0x0073, Exception -> 0x0069 }
            java.lang.String r5 = r0.getNetworkOperatorName()     // Catch:{ SecurityException -> 0x0073, Exception -> 0x0069 }
            if (r3 == 0) goto L_0x0042
            java.lang.String r6 = ""
            java.lang.String r7 = r3.trim()     // Catch:{ SecurityException -> 0x0073, Exception -> 0x0069 }
            boolean r6 = r6.equals(r7)     // Catch:{ SecurityException -> 0x0073, Exception -> 0x0069 }
            if (r6 != 0) goto L_0x0042
            java.lang.String r6 = "no"
            r2.put(r6, r3)     // Catch:{ SecurityException -> 0x0073, Exception -> 0x0069 }
        L_0x0042:
            if (r5 == 0) goto L_0x0055
            java.lang.String r3 = ""
            java.lang.String r6 = r5.trim()     // Catch:{ SecurityException -> 0x0073, Exception -> 0x0069 }
            boolean r3 = r3.equals(r6)     // Catch:{ SecurityException -> 0x0073, Exception -> 0x0069 }
            if (r3 != 0) goto L_0x0055
            java.lang.String r3 = "non"
            r2.put(r3, r5)     // Catch:{ SecurityException -> 0x0073, Exception -> 0x0069 }
        L_0x0055:
            if (r4 == 0) goto L_0x0073
            java.lang.String r3 = ""
            java.lang.String r5 = r4.trim()     // Catch:{ SecurityException -> 0x0073, Exception -> 0x0069 }
            boolean r3 = r3.equals(r5)     // Catch:{ SecurityException -> 0x0073, Exception -> 0x0069 }
            if (r3 != 0) goto L_0x0073
            java.lang.String r3 = "nc_iso"
            r2.put(r3, r4)     // Catch:{ SecurityException -> 0x0073, Exception -> 0x0069 }
            goto L_0x0073
        L_0x0069:
            r3 = move-exception
            java.lang.String r4 = f475if
            java.lang.String r3 = r3.toString()
            com.threatmetrix.TrustDefender.internal.TL.m338new(r4, r3)
        L_0x0073:
            com.threatmetrix.TrustDefender.internal.N$R r3 = new com.threatmetrix.TrustDefender.internal.N$R
            com.threatmetrix.TrustDefender.internal.N r4 = new com.threatmetrix.TrustDefender.internal.N
            r4.<init>()
            android.content.Context r5 = r8.f487for
            r3.<init>(r5)
            java.lang.String r4 = "android.permission.ACCESS_FINE_LOCATION"
            android.content.Context r5 = r8.f487for
            java.lang.String r5 = r5.getPackageName()
            boolean r4 = r3.m169if(r4, r5)
            java.lang.String r5 = "android.permission.ACCESS_COARSE_LOCATION"
            android.content.Context r6 = r8.f487for
            java.lang.String r6 = r6.getPackageName()
            boolean r3 = r3.m169if(r5, r6)
            if (r4 != 0) goto L_0x009b
            if (r3 == 0) goto L_0x01de
        L_0x009b:
            int r3 = com.threatmetrix.TrustDefender.internal.N.I.C0012I.f388for
            int r4 = com.threatmetrix.TrustDefender.internal.N.I.W.f404long
            if (r3 < r4) goto L_0x00ac
            android.content.Context r8 = r8.f487for
            java.util.Map r8 = com.threatmetrix.TrustDefender.internal.D.m33for(r8)
            if (r8 == 0) goto L_0x00ac
            r2.putAll(r8)
        L_0x00ac:
            boolean r8 = com.threatmetrix.TrustDefender.internal.N.Y.m191for()
            r3 = -1
            if (r8 != 0) goto L_0x00b9
            boolean r8 = com.threatmetrix.TrustDefender.internal.N.Y.m195new()
            if (r8 == 0) goto L_0x014c
        L_0x00b9:
            android.telephony.CellLocation r8 = r0.getCellLocation()     // Catch:{ SecurityException -> 0x00c8, Exception -> 0x00be }
            goto L_0x00c9
        L_0x00be:
            r8 = move-exception
            java.lang.String r4 = f475if
            java.lang.String r8 = r8.toString()
            com.threatmetrix.TrustDefender.internal.TL.m338new(r4, r8)
        L_0x00c8:
            r8 = r1
        L_0x00c9:
            if (r8 == 0) goto L_0x014c
            boolean r4 = r8 instanceof android.telephony.gsm.GsmCellLocation
            if (r4 == 0) goto L_0x0103
            android.telephony.CellLocation r8 = r0.getCellLocation()
            android.telephony.gsm.GsmCellLocation r8 = (android.telephony.gsm.GsmCellLocation) r8
            int r4 = r8.getLac()
            if (r4 == r3) goto L_0x00e4
            java.lang.String r5 = "lac"
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r2.put(r5, r4)
        L_0x00e4:
            int r4 = r8.getCid()
            if (r4 == r3) goto L_0x00f3
            java.lang.String r5 = "cid"
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r2.put(r5, r4)
        L_0x00f3:
            int r8 = r8.getPsc()
            if (r8 == r3) goto L_0x014c
            java.lang.String r4 = "psc"
            java.lang.String r8 = java.lang.String.valueOf(r8)
            r2.put(r4, r8)
            goto L_0x014c
        L_0x0103:
            boolean r8 = r8 instanceof android.telephony.cdma.CdmaCellLocation
            if (r8 == 0) goto L_0x014c
            android.telephony.CellLocation r8 = r0.getCellLocation()
            android.telephony.cdma.CdmaCellLocation r8 = (android.telephony.cdma.CdmaCellLocation) r8
            int r4 = r8.getSystemId()
            if (r4 == r3) goto L_0x011c
            java.lang.String r5 = "sid"
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r2.put(r5, r4)
        L_0x011c:
            int r4 = r8.getBaseStationId()
            if (r4 == r3) goto L_0x012b
            java.lang.String r5 = "bsid"
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r2.put(r5, r4)
        L_0x012b:
            int r4 = r8.getBaseStationLatitude()
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L_0x013d
            java.lang.String r6 = "bs_lat"
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r2.put(r6, r4)
        L_0x013d:
            int r8 = r8.getBaseStationLongitude()
            if (r8 == r5) goto L_0x014c
            java.lang.String r4 = "bs_lng"
            java.lang.String r8 = java.lang.String.valueOf(r8)
            r2.put(r4, r8)
        L_0x014c:
            boolean r8 = com.threatmetrix.TrustDefender.internal.N.Y.m193if()
            if (r8 == 0) goto L_0x01de
            java.lang.Class<android.telephony.TelephonyManager> r8 = android.telephony.TelephonyManager.class
            java.lang.String r4 = "getNeighboringCellInfo"
            r5 = 0
            java.lang.Class[] r6 = new java.lang.Class[r5]     // Catch:{ SecurityException -> 0x0179, Exception -> 0x016e }
            java.lang.reflect.Method r8 = com.threatmetrix.TrustDefender.internal.D2.m42do(r8, r4, r6)     // Catch:{ SecurityException -> 0x0179, Exception -> 0x016e }
            if (r8 == 0) goto L_0x017d
            java.lang.Object[] r4 = new java.lang.Object[r5]     // Catch:{ SecurityException -> 0x0179, Exception -> 0x016e }
            java.lang.Object r8 = com.threatmetrix.TrustDefender.internal.D2.m39do(r0, r8, r4)     // Catch:{ SecurityException -> 0x0179, Exception -> 0x016e }
            if (r8 == 0) goto L_0x017d
            boolean r0 = r8 instanceof java.util.List     // Catch:{ SecurityException -> 0x0179, Exception -> 0x016e }
            if (r0 == 0) goto L_0x017d
            java.util.List r8 = (java.util.List) r8     // Catch:{ SecurityException -> 0x0179, Exception -> 0x016e }
            goto L_0x017e
        L_0x016e:
            r8 = move-exception
            java.lang.String r0 = f475if
            java.lang.String r8 = r8.toString()
            com.threatmetrix.TrustDefender.internal.TL.m338new(r0, r8)
            goto L_0x017d
        L_0x0179:
            r8 = move-exception
            r8.getMessage()
        L_0x017d:
            r8 = r1
        L_0x017e:
            if (r8 == 0) goto L_0x01de
            int r0 = r8.size()
            if (r0 <= 0) goto L_0x01de
            java.util.Iterator r8 = r8.iterator()
        L_0x018a:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x01de
            java.lang.Object r0 = r8.next()
            android.telephony.NeighboringCellInfo r0 = (android.telephony.NeighboringCellInfo) r0
            int r4 = r0.getCid()
            if (r4 == r3) goto L_0x018a
            int r4 = r0.getRssi()
            r5 = 99
            if (r4 == r5) goto L_0x018a
            int r4 = r0.getCid()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.String r5 = "cid"
            java.lang.Object r5 = r2.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            boolean r4 = r4.equalsIgnoreCase(r5)
            if (r4 != 0) goto L_0x01d0
            int r4 = r0.getCid()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.String r5 = "bsid"
            java.lang.Object r5 = r2.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            boolean r4 = r4.equalsIgnoreCase(r5)
            if (r4 == 0) goto L_0x018a
        L_0x01d0:
            java.lang.String r4 = "sl_ASU"
            int r0 = r0.getRssi()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r2.put(r4, r0)
            goto L_0x018a
        L_0x01de:
            int r8 = r2.size()
            r0 = 3
            if (r8 < r0) goto L_0x01ea
            java.lang.String r8 = r2.toString()
            return r8
        L_0x01ea:
            return r1
        L_0x01eb:
            return r1
        L_0x01ec:
            r8 = move-exception
            java.lang.String r0 = f475if
            java.lang.String r8 = r8.toString()
            com.threatmetrix.TrustDefender.internal.TL.m338new(r0, r8)
            return r1
        L_0x01f7:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.P.m236byte(com.threatmetrix.TrustDefender.internal.P$O):java.lang.String");
    }

    /* renamed from: char reason: not valid java name */
    static String m241char(O o) {
        return J.m97int(o.f487for);
    }

    /* renamed from: else reason: not valid java name */
    static boolean m247else(O o) {
        return J.m93if(o.f487for);
    }

    /* renamed from: try reason: not valid java name */
    static String m271try(O o) {
        Map map = m259if(o, true);
        return map.size() > 0 ? NK.m209do(map) : "";
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x007c A[Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }] */
    /* renamed from: case reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String m238case(com.threatmetrix.TrustDefender.internal.P.O r6) {
        /*
            boolean r0 = com.threatmetrix.TrustDefender.internal.N.Y.m189do()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            com.threatmetrix.TrustDefender.internal.N$R r0 = new com.threatmetrix.TrustDefender.internal.N$R
            com.threatmetrix.TrustDefender.internal.N r2 = new com.threatmetrix.TrustDefender.internal.N
            r2.<init>()
            android.content.Context r3 = r6.f487for
            r0.<init>(r3)
            java.lang.String r2 = "android.permission.ACCESS_FINE_LOCATION"
            android.content.Context r3 = r6.f487for
            java.lang.String r3 = r3.getPackageName()
            boolean r2 = r0.m169if(r2, r3)
            java.lang.String r3 = "android.permission.ACCESS_COARSE_LOCATION"
            android.content.Context r4 = r6.f487for
            java.lang.String r4 = r4.getPackageName()
            boolean r0 = r0.m169if(r3, r4)
            if (r2 != 0) goto L_0x0034
            if (r0 == 0) goto L_0x0031
            goto L_0x0034
        L_0x0031:
            r0 = r1
            goto L_0x0102
        L_0x0034:
            int r0 = com.threatmetrix.TrustDefender.internal.N.I.C0012I.f388for
            int r2 = com.threatmetrix.TrustDefender.internal.N.I.W.f404long
            if (r0 < r2) goto L_0x0041
            android.content.Context r0 = r6.f487for
            java.lang.String r0 = com.threatmetrix.TrustDefender.internal.D.m34if(r0)
            goto L_0x0042
        L_0x0041:
            r0 = r1
        L_0x0042:
            boolean r2 = com.threatmetrix.TrustDefender.internal.NK.m215if(r0)
            if (r2 == 0) goto L_0x0102
            boolean r2 = com.threatmetrix.TrustDefender.internal.N.Y.m191for()
            if (r2 != 0) goto L_0x0054
            boolean r2 = com.threatmetrix.TrustDefender.internal.N.Y.m195new()
            if (r2 == 0) goto L_0x0102
        L_0x0054:
            android.content.Context r6 = r6.f487for     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            android.content.Context r6 = r6.getApplicationContext()     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            java.lang.String r2 = "phone"
            java.lang.Object r6 = r6.getSystemService(r2)     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            if (r6 == 0) goto L_0x00f2
            boolean r2 = r6 instanceof android.telephony.TelephonyManager     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            if (r2 != 0) goto L_0x0068
            goto L_0x00f2
        L_0x0068:
            android.telephony.TelephonyManager r6 = (android.telephony.TelephonyManager) r6     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            android.telephony.CellLocation r2 = r6.getCellLocation()     // Catch:{ SecurityException -> 0x0079, Exception -> 0x006f }
            goto L_0x007a
        L_0x006f:
            r2 = move-exception
            java.lang.String r3 = f475if     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            java.lang.String r2 = r2.toString()     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            com.threatmetrix.TrustDefender.internal.TL.m338new(r3, r2)     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
        L_0x0079:
            r2 = r1
        L_0x007a:
            if (r2 == 0) goto L_0x0102
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            r3.<init>()     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            boolean r4 = r2 instanceof android.telephony.gsm.GsmCellLocation     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            r5 = -1
            if (r4 == 0) goto L_0x00b2
            android.telephony.CellLocation r6 = r6.getCellLocation()     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            android.telephony.gsm.GsmCellLocation r6 = (android.telephony.gsm.GsmCellLocation) r6     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            int r2 = r6.getCid()     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            int r6 = r6.getLac()     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            if (r2 != r5) goto L_0x0099
            if (r6 != r5) goto L_0x0099
            return r1
        L_0x0099:
            java.lang.String r1 = "GSM:"
            r3.append(r1)     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            if (r2 == r5) goto L_0x00a3
            r3.append(r2)     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
        L_0x00a3:
            java.lang.String r1 = ":::"
            r3.append(r1)     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            if (r6 == r5) goto L_0x00ad
            r3.append(r6)     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
        L_0x00ad:
            java.lang.String r6 = r3.toString()     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            return r6
        L_0x00b2:
            boolean r2 = r2 instanceof android.telephony.cdma.CdmaCellLocation     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            if (r2 == 0) goto L_0x0102
            android.telephony.CellLocation r6 = r6.getCellLocation()     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            android.telephony.cdma.CdmaCellLocation r6 = (android.telephony.cdma.CdmaCellLocation) r6     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            int r2 = r6.getBaseStationId()     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            int r4 = r6.getSystemId()     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            int r6 = r6.getNetworkId()     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            if (r2 != r5) goto L_0x00cf
            if (r4 != r5) goto L_0x00cf
            if (r6 != r5) goto L_0x00cf
            return r1
        L_0x00cf:
            java.lang.String r1 = "CDMA:"
            r3.append(r1)     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            if (r2 == r5) goto L_0x00d9
            r3.append(r2)     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
        L_0x00d9:
            java.lang.String r1 = ":"
            r3.append(r1)     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            if (r4 == r5) goto L_0x00e3
            r3.append(r4)     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
        L_0x00e3:
            java.lang.String r1 = ":"
            r3.append(r1)     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            if (r6 == r5) goto L_0x00ed
            r3.append(r6)     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
        L_0x00ed:
            java.lang.String r6 = r3.toString()     // Catch:{ SecurityException -> 0x00fe, Exception -> 0x00f3 }
            return r6
        L_0x00f2:
            return r1
        L_0x00f3:
            r6 = move-exception
            java.lang.String r1 = f475if
            java.lang.String r6 = r6.toString()
            com.threatmetrix.TrustDefender.internal.TL.m338new(r1, r6)
            goto L_0x0102
        L_0x00fe:
            r6 = move-exception
            r6.getMessage()
        L_0x0102:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.P.m238case(com.threatmetrix.TrustDefender.internal.P$O):java.lang.String");
    }

    /* renamed from: long reason: not valid java name */
    static String m265long(O o) {
        String str = Q.m305int() ? "A" : "";
        return C0012I.f388for >= W.f395class ? str.concat(H.m65if(o.f487for)) : str;
    }

    /* renamed from: try reason: not valid java name */
    static I m270try() {
        return YV.m453int();
    }

    @SuppressLint({"HardwareIds"})
    /* renamed from: if reason: not valid java name */
    private static Map<String, String> m259if(O o, boolean z) {
        HashMap hashMap = new HashMap();
        if (new R(o.f487for).m169if("android.permission.READ_PHONE_STATE", o.f487for.getPackageName())) {
            try {
                Object systemService = o.f487for.getApplicationContext().getSystemService("phone");
                if (systemService != null && (systemService instanceof TelephonyManager)) {
                    TelephonyManager telephonyManager = (TelephonyManager) systemService;
                    NK.m225new(telephonyManager.getDeviceId(), z, "di", hashMap);
                    NK.m225new(telephonyManager.getLine1Number(), z, "ln", hashMap);
                    NK.m225new(telephonyManager.getSimSerialNumber(), z, "ss", hashMap);
                    NK.m225new(telephonyManager.getSubscriberId(), z, "si", hashMap);
                    NK.m225new(telephonyManager.getVoiceMailNumber(), z, "vn", hashMap);
                    NK.m225new(telephonyManager.getDeviceSoftwareVersion(), false, "sv", hashMap);
                    String voiceMailAlphaTag = telephonyManager.getVoiceMailAlphaTag();
                    if (NK.m203byte(voiceMailAlphaTag) && !voiceMailAlphaTag.equalsIgnoreCase("VoiceMail")) {
                        hashMap.put("VMAlphaTag", voiceMailAlphaTag);
                    }
                    if (C0012I.f388for >= 22) {
                        hashMap.putAll(XC.m402int(o.f487for, z));
                    }
                }
            } catch (SecurityException unused) {
            } catch (Exception e) {
                TL.m338new(f475if, e.toString());
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|(1:3)|4|5|(1:7)|8|9|(2:11|12)|13|15) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x000f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x001e */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0024 A[Catch:{ Exception -> 0x0029 }] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x001d A[RETURN] */
    /* renamed from: goto reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String m254goto(com.threatmetrix.TrustDefender.internal.P.O r2) {
        /*
            android.content.Context r0 = r2.f487for     // Catch:{ RuntimeException -> 0x000f }
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch:{ RuntimeException -> 0x000f }
            java.lang.String r1 = "device_name"
            java.lang.String r0 = android.provider.Settings.System.getString(r0, r1)     // Catch:{ RuntimeException -> 0x000f }
            if (r0 == 0) goto L_0x000f
            return r0
        L_0x000f:
            android.content.Context r2 = r2.f487for     // Catch:{ RuntimeException -> 0x001e }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ RuntimeException -> 0x001e }
            java.lang.String r0 = "bluetooth_name"
            java.lang.String r2 = android.provider.Settings.Secure.getString(r2, r0)     // Catch:{ RuntimeException -> 0x001e }
            if (r2 == 0) goto L_0x001e
            return r2
        L_0x001e:
            android.bluetooth.BluetoothAdapter r2 = android.bluetooth.BluetoothAdapter.getDefaultAdapter()     // Catch:{ Exception -> 0x0029 }
            if (r2 == 0) goto L_0x0029
            java.lang.String r2 = r2.getName()     // Catch:{ Exception -> 0x0029 }
            return r2
        L_0x0029:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.P.m254goto(com.threatmetrix.TrustDefender.internal.P$O):java.lang.String");
    }

    /* renamed from: void reason: not valid java name */
    static String m273void(O o) {
        String[] split;
        L l = new L(o);
        String str = l.f411int != null ? l.f411int.dataDir : "";
        if (PH.m275do().m277char(str) == 1) {
            return "Cloned";
        }
        for (String str2 : str.split("/")) {
            if (str2.contains(".") && !str2.equalsIgnoreCase(o.f487for.getPackageName())) {
                return "Cloned";
            }
        }
        String[] packagesForUid = o.f487for.getPackageManager().getPackagesForUid(l.f411int != null ? l.f411int.uid : -1);
        return (packagesForUid == null || packagesForUid.length <= 1) ? "Not Cloned" : "Cloned";
    }

    /* renamed from: break reason: not valid java name */
    static boolean m234break(O o) {
        Context context = o.f487for;
        if (C0012I.f388for >= W.f406this) {
            return G.m64new(context);
        }
        if (!D.m155if()) {
            return true;
        }
        try {
            Object systemService = context.getSystemService("power");
            if (systemService != null) {
                if (systemService instanceof PowerManager) {
                    return ((PowerManager) systemService).isScreenOn();
                }
            }
            return true;
        } catch (SecurityException unused) {
            return true;
        } catch (Exception e) {
            TL.m338new(f475if, e.toString());
            return true;
        }
    }

    /* renamed from: do reason: not valid java name */
    public static com.threatmetrix.TrustDefender.internal.K7.O m244do(Location location, boolean z) {
        Float f = null;
        if (location == null) {
            return null;
        }
        boolean z2 = VW.m347new(location);
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        if (location.hasAccuracy()) {
            f = Float.valueOf(location.getAccuracy());
        }
        com.threatmetrix.TrustDefender.internal.K7.O o = new com.threatmetrix.TrustDefender.internal.K7.O(latitude, longitude, f, z2, z);
        return o;
    }

    /* renamed from: char reason: not valid java name */
    static String m240char() {
        StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
        if (stackTrace.length <= 0) {
            return null;
        }
        String className = stackTrace[stackTrace.length - 1].getClassName();
        if (className.equals("com.android.internal.os.ZygoteInit") || className.equals("dalvik.system.NativeStart")) {
            return null;
        }
        int lastIndexOf = className.lastIndexOf(".");
        return lastIndexOf != -1 ? className.substring(lastIndexOf + 1, className.length()) : className;
    }

    /* renamed from: this reason: not valid java name */
    public static byte[] m269this(O o) {
        StringBuilder sb = new StringBuilder();
        if (NK.m203byte(I.f371byte)) {
            sb.append(I.f371byte);
        }
        if (NK.m203byte(I.f382long)) {
            sb.append(Build.PRODUCT);
        }
        if (NK.m203byte(I.f386void)) {
            sb.append(Build.BOARD);
        }
        if (NK.m203byte(JG.m109int(o))) {
            sb.append(JG.m109int(o));
        }
        if (NK.m203byte(JG.m108if(o))) {
            sb.append(JG.m108if(o));
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(sb.toString().getBytes());
            return instance.digest();
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    /* renamed from: const reason: not valid java name */
    static String m242const(O o) {
        if (C0012I.f388for >= W.f392case) {
            return Z.m199if(o.f487for.getContentResolver(), "adb_enabled");
        }
        return Z.m198for(o.f487for.getContentResolver(), "adb_enabled");
    }

    /* renamed from: float reason: not valid java name */
    static String m249float(O o) {
        if (C0012I.f388for >= W.f392case) {
            return Z.m199if(o.f487for.getContentResolver(), "development_settings_enabled");
        }
        return C0012I.f388for == W.f394char ? Z.m198for(o.f487for.getContentResolver(), "development_settings_enabled") : "";
    }

    /* renamed from: final reason: not valid java name */
    static String m248final(O o) {
        L l = new L(o);
        boolean z = false;
        if (((l.f411int != null ? l.f411int.flags : 0) & 2) != 0) {
            z = true;
        }
        return String.valueOf(z);
    }

    /* renamed from: void reason: not valid java name */
    static String m272void() {
        return String.valueOf(Debug.isDebuggerConnected());
    }

    /* renamed from: goto reason: not valid java name */
    static String m253goto() {
        return Q.m306new();
    }

    /* renamed from: break reason: not valid java name */
    static boolean m233break() {
        return Q.m305int();
    }

    /* renamed from: long reason: not valid java name */
    static W m264long() throws InterruptedException {
        W w = PH.m275do().m284else();
        if (w != null) {
            return w;
        }
        return HM.m69do();
    }

    /* renamed from: catch reason: not valid java name */
    static String m239catch(O o) {
        L l = new L(o);
        return l.f411int != null ? l.f411int.sourceDir : "";
    }
}
