package com.threatmetrix.TrustDefender.internal;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import com.threatmetrix.TrustDefender.internal.D2.E;
import java.lang.reflect.Field;
import java.util.Locale;

public class HZ {

    /* renamed from: if reason: not valid java name */
    private static final String f178if = TL.m331if(HZ.class);

    HZ() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00e4  */
    /* renamed from: for reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.threatmetrix.TrustDefender.internal.Z2 m74for(android.content.Context r12) throws java.lang.InterruptedException {
        /*
            com.threatmetrix.TrustDefender.internal.Z2 r0 = new com.threatmetrix.TrustDefender.internal.Z2
            r0.<init>()
            com.threatmetrix.TrustDefender.internal.PH r1 = com.threatmetrix.TrustDefender.internal.PH.m275do()
            java.lang.String[] r1 = r1.m288for()
            boolean r1 = r0.m457for(r1)
            if (r1 != 0) goto L_0x013a
            java.lang.String[] r1 = m76int(r12)
            boolean r1 = r0.m457for(r1)
            if (r1 != 0) goto L_0x013a
            boolean r1 = com.threatmetrix.TrustDefender.internal.N.T.m175for()
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L_0x00dd
            android.content.IntentFilter r1 = new android.content.IntentFilter
            r1.<init>()
            java.lang.String r6 = "android.net.wifi.STATE_CHANGE"
            r1.addAction(r6)
            android.content.Intent r1 = r12.registerReceiver(r5, r1)
            if (r1 == 0) goto L_0x00dd
            r6 = 4
            java.lang.String[] r6 = new java.lang.String[r6]
            java.lang.String r7 = "wifiInfo"
            android.os.Parcelable r7 = r1.getParcelableExtra(r7)
            android.net.wifi.WifiInfo r7 = (android.net.wifi.WifiInfo) r7
            r8 = 17
            r9 = 0
            if (r7 == 0) goto L_0x008f
            java.lang.String r1 = r7.getBSSID()
            if (r1 == 0) goto L_0x0063
            int r10 = r1.length()
            if (r10 < r8) goto L_0x0063
            java.lang.String r8 = "00:00:00:00:00:00"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x005c
            goto L_0x0063
        L_0x005c:
            java.util.Locale r8 = java.util.Locale.US
            java.lang.String r1 = r1.toUpperCase(r8)
            goto L_0x0064
        L_0x0063:
            r1 = r5
        L_0x0064:
            java.lang.String r8 = r7.getSSID()
            java.lang.String r8 = m77new(r8)
            int r7 = r7.getRssi()
            if (r1 == 0) goto L_0x0074
            r6[r9] = r1
        L_0x0074:
            if (r8 == 0) goto L_0x0078
            r6[r4] = r8
        L_0x0078:
            java.lang.String r1 = java.lang.String.valueOf(r7)
            r6[r3] = r1
            com.threatmetrix.TrustDefender.internal.Z2$W r1 = com.threatmetrix.TrustDefender.internal.Z2.W.WIFI
            java.lang.String r1 = r1.f740byte
            r6[r2] = r1
            r1 = r6[r9]
            if (r1 == 0) goto L_0x00de
            r1 = r6[r4]
            if (r1 == 0) goto L_0x00de
            r1 = r6[r3]
            goto L_0x00de
        L_0x008f:
            android.os.Bundle r1 = r1.getExtras()
            java.lang.String r7 = "networkInfo"
            java.lang.Object r7 = r1.get(r7)
            android.net.NetworkInfo r7 = (android.net.NetworkInfo) r7
            if (r7 == 0) goto L_0x00de
            android.net.NetworkInfo$State r10 = r7.getState()
            android.net.NetworkInfo$State r11 = android.net.NetworkInfo.State.CONNECTED
            if (r10 != r11) goto L_0x00de
            java.lang.String r7 = r7.getExtraInfo()
            java.lang.String r7 = m77new(r7)
            java.lang.String r10 = "bssid"
            java.lang.Object r1 = r1.get(r10)
            java.lang.String r1 = (java.lang.String) r1
            if (r1 == 0) goto L_0x00cd
            int r10 = r1.length()
            if (r10 < r8) goto L_0x00cd
            java.lang.String r8 = "00:00:00:00:00:00"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x00c6
            goto L_0x00cd
        L_0x00c6:
            java.util.Locale r8 = java.util.Locale.US
            java.lang.String r1 = r1.toUpperCase(r8)
            goto L_0x00ce
        L_0x00cd:
            r1 = r5
        L_0x00ce:
            if (r1 == 0) goto L_0x00d2
            r6[r9] = r1
        L_0x00d2:
            if (r7 == 0) goto L_0x00d6
            r6[r4] = r7
        L_0x00d6:
            com.threatmetrix.TrustDefender.internal.Z2$W r1 = com.threatmetrix.TrustDefender.internal.Z2.W.WIFI
            java.lang.String r1 = r1.f740byte
            r6[r2] = r1
            goto L_0x00de
        L_0x00dd:
            r6 = r5
        L_0x00de:
            boolean r1 = r0.m457for(r6)
            if (r1 != 0) goto L_0x00eb
            java.lang.String[] r1 = m73do(r12)
            r0.m457for(r1)
        L_0x00eb:
            java.lang.String r1 = r0.f727if
            if (r1 != 0) goto L_0x013a
            boolean r1 = com.threatmetrix.TrustDefender.internal.N.Y.m192goto()
            if (r1 == 0) goto L_0x013a
            java.lang.String r1 = "phone"
            java.lang.Object r1 = r12.getSystemService(r1)     // Catch:{ SecurityException -> 0x0134, Exception -> 0x011c }
            if (r1 == 0) goto L_0x0113
            boolean r6 = r1 instanceof android.telephony.TelephonyManager     // Catch:{ SecurityException -> 0x0134, Exception -> 0x011c }
            if (r6 == 0) goto L_0x0113
            android.telephony.TelephonyManager r1 = (android.telephony.TelephonyManager) r1     // Catch:{ SecurityException -> 0x0134, Exception -> 0x011c }
            int r1 = r1.getDataState()     // Catch:{ SecurityException -> 0x0134, Exception -> 0x011c }
            if (r1 == r3) goto L_0x010d
            if (r1 == r4) goto L_0x010d
            if (r1 != r2) goto L_0x0113
        L_0x010d:
            com.threatmetrix.TrustDefender.internal.Z2$W r1 = com.threatmetrix.TrustDefender.internal.Z2.W.CELLULAR     // Catch:{ SecurityException -> 0x0134, Exception -> 0x011c }
            java.lang.String r1 = r1.f740byte     // Catch:{ SecurityException -> 0x0134, Exception -> 0x011c }
            r0.f727if = r1     // Catch:{ SecurityException -> 0x0134, Exception -> 0x011c }
        L_0x0113:
            r0.f729new = r5
            r0.f724do = r5
            r0.f728int = r5
            goto L_0x013a
        L_0x011a:
            r12 = move-exception
            goto L_0x012d
        L_0x011c:
            r1 = move-exception
            java.lang.String r2 = f178if     // Catch:{ all -> 0x011a }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x011a }
            com.threatmetrix.TrustDefender.internal.TL.m338new(r2, r1)     // Catch:{ all -> 0x011a }
            r0.f729new = r5
            r0.f724do = r5
            r0.f728int = r5
            goto L_0x013a
        L_0x012d:
            r0.f729new = r5
            r0.f724do = r5
            r0.f728int = r5
            throw r12
        L_0x0134:
            r0.f729new = r5
            r0.f724do = r5
            r0.f728int = r5
        L_0x013a:
            java.lang.String r1 = r0.f729new
            java.lang.String r12 = m75if(r12, r1)
            r0.f725else = r12
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.HZ.m74for(android.content.Context):com.threatmetrix.TrustDefender.internal.Z2");
    }

    /* renamed from: do reason: not valid java name */
    private static String m72do(int i) {
        Field[] fieldArr = D2.m46if(D2.m38do(E.CONNECTIVITY_MANAGER));
        if (fieldArr != null) {
            for (Field field : fieldArr) {
                if (field.getType() == Integer.TYPE) {
                    try {
                        if (field.getInt(null) == i) {
                            String name = field.getName();
                            if (NK.m203byte(name) && name.startsWith("TYPE_")) {
                                if (NK.m215if(name)) {
                                    return null;
                                }
                                String trim = name.trim();
                                if (NK.m215if(trim)) {
                                    return null;
                                }
                                String lowerCase = trim.toLowerCase(Locale.US);
                                if (lowerCase.startsWith("type")) {
                                    lowerCase = lowerCase.replaceFirst("type", "");
                                }
                                return lowerCase.replaceAll("_", " ").trim();
                            }
                        } else {
                            continue;
                        }
                    } catch (IllegalAccessException | IllegalArgumentException unused) {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    /* renamed from: do reason: not valid java name */
    private static String[] m73do(Context context) {
        String str = null;
        if (!T.m177int()) {
            return null;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        Intent registerReceiver = context.registerReceiver(null, intentFilter);
        if (registerReceiver == null) {
            return null;
        }
        String[] strArr = new String[4];
        int intExtra = registerReceiver.getIntExtra("networkType", -99);
        Bundle extras = registerReceiver.getExtras();
        if (extras != null) {
            NetworkInfo networkInfo = (NetworkInfo) extras.get("networkInfo");
            if (networkInfo != null) {
                if (networkInfo.getState() != State.CONNECTED) {
                    return null;
                }
                if (intExtra == -99) {
                    try {
                        intExtra = networkInfo.getType();
                    } catch (SecurityException unused) {
                        return null;
                    } catch (Exception e) {
                        TL.m338new(f178if, e.toString());
                        return null;
                    }
                }
            }
            String str2 = m72do(intExtra);
            if (str2 == null) {
                TL.m329for(f178if, "Unexpected connection type code {}", String.valueOf(intExtra));
            } else if (intExtra == 0 || str2.contains(W.MOBILE.f740byte)) {
                strArr[3] = W.CELLULAR.f740byte;
            } else if (intExtra == 1 || str2.contains(W.WIFI.f740byte)) {
                strArr[3] = W.WIFI.f740byte;
                String str3 = networkInfo == null ? null : m77new(networkInfo.getExtraInfo());
                String str4 = (String) extras.get("bssid");
                if (str4 != null && str4.length() >= 17 && !"00:00:00:00:00:00".equals(str4)) {
                    str = str4.toUpperCase(Locale.US);
                }
                if (str != null) {
                    strArr[0] = str;
                }
                if (str3 != null) {
                    strArr[1] = str3;
                }
            } else if (intExtra == 7 || str2.contains(W.BLUETOOTH.f740byte)) {
                strArr[3] = W.BLUETOOTH.f740byte;
            } else if (intExtra == 9 || str2.contains(W.ETHERNET.f740byte)) {
                strArr[3] = W.ETHERNET.f740byte;
            } else {
                TL.m329for(f178if, "Unexpected connection type {}", str2);
                strArr[3] = str2;
            }
        }
        return strArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0035 A[Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0036 A[Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005b A[Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005c A[Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }] */
    /* renamed from: int reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String[] m76int(android.content.Context r5) {
        /*
            boolean r0 = com.threatmetrix.TrustDefender.internal.N.T.m173do()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            com.threatmetrix.TrustDefender.internal.N$R r0 = new com.threatmetrix.TrustDefender.internal.N$R
            com.threatmetrix.TrustDefender.internal.N r2 = new com.threatmetrix.TrustDefender.internal.N
            r2.<init>()
            r0.<init>(r5)
            java.lang.String r2 = "android.permission.ACCESS_WIFI_STATE"
            java.lang.String r3 = r5.getPackageName()
            boolean r0 = r0.m169if(r2, r3)
            if (r0 == 0) goto L_0x008f
            android.content.Context r5 = r5.getApplicationContext()     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            java.lang.String r0 = "wifi"
            java.lang.Object r5 = r5.getSystemService(r0)     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            if (r5 == 0) goto L_0x0032
            boolean r0 = r5 instanceof android.net.wifi.WifiManager     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            if (r0 != 0) goto L_0x002f
            goto L_0x0032
        L_0x002f:
            android.net.wifi.WifiManager r5 = (android.net.wifi.WifiManager) r5     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            goto L_0x0033
        L_0x0032:
            r5 = r1
        L_0x0033:
            if (r5 != 0) goto L_0x0036
            return r1
        L_0x0036:
            android.net.wifi.WifiInfo r5 = r5.getConnectionInfo()     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            java.lang.String r0 = r5.getBSSID()     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            if (r0 == 0) goto L_0x0058
            int r2 = r0.length()     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            r3 = 17
            if (r2 < r3) goto L_0x0058
            java.lang.String r2 = "00:00:00:00:00:00"
            boolean r2 = r2.equals(r0)     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            if (r2 == 0) goto L_0x0051
            goto L_0x0058
        L_0x0051:
            java.util.Locale r2 = java.util.Locale.US     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            java.lang.String r0 = r0.toUpperCase(r2)     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            goto L_0x0059
        L_0x0058:
            r0 = r1
        L_0x0059:
            if (r0 != 0) goto L_0x005c
            return r1
        L_0x005c:
            java.lang.String r2 = r5.getSSID()     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            java.lang.String r2 = m77new(r2)     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            if (r2 != 0) goto L_0x0067
            return r1
        L_0x0067:
            int r5 = r5.getRssi()     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            r3 = 4
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            r4 = 0
            r3[r4] = r0     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            r0 = 1
            r3[r0] = r2     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            r0 = 2
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            r3[r0] = r5     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            r5 = 3
            com.threatmetrix.TrustDefender.internal.Z2$W r0 = com.threatmetrix.TrustDefender.internal.Z2.W.WIFI     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            java.lang.String r0 = r0.f740byte     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            r3[r5] = r0     // Catch:{ SecurityException -> 0x008e, Exception -> 0x0083 }
            return r3
        L_0x0083:
            r5 = move-exception
            java.lang.String r0 = f178if
            java.lang.String r5 = r5.toString()
            com.threatmetrix.TrustDefender.internal.TL.m338new(r0, r5)
            return r1
        L_0x008e:
            return r1
        L_0x008f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.HZ.m76int(android.content.Context):java.lang.String[]");
    }

    /* renamed from: new reason: not valid java name */
    private static String m77new(String str) {
        if (str == null || str.contains("unknown ssid") || str.length() <= 0) {
            return null;
        }
        if (str.charAt(0) == '\"') {
            str = str.substring(1);
        }
        if (str.length() > 0 && str.charAt(str.length() - 1) == '\"') {
            str = str.substring(0, str.length() - 1);
        }
        if (!str.isEmpty()) {
            return NK.m204case(str);
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0034 A[Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0035 A[Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007c A[Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007e A[Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }] */
    /* renamed from: if reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m75if(android.content.Context r7, java.lang.String r8) {
        /*
            com.threatmetrix.TrustDefender.internal.N$R r0 = new com.threatmetrix.TrustDefender.internal.N$R
            com.threatmetrix.TrustDefender.internal.N r1 = new com.threatmetrix.TrustDefender.internal.N
            r1.<init>()
            r0.<init>(r7)
            boolean r1 = com.threatmetrix.TrustDefender.internal.N.T.m176if()
            r2 = 0
            if (r1 == 0) goto L_0x00a8
            java.lang.String r1 = "android.permission.ACCESS_WIFI_STATE"
            java.lang.String r3 = r7.getPackageName()
            boolean r0 = r0.m169if(r1, r3)
            if (r0 == 0) goto L_0x00a8
            android.content.Context r7 = r7.getApplicationContext()     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            java.lang.String r0 = "wifi"
            java.lang.Object r7 = r7.getSystemService(r0)     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            if (r7 == 0) goto L_0x0031
            boolean r0 = r7 instanceof android.net.wifi.WifiManager     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            if (r0 != 0) goto L_0x002e
            goto L_0x0031
        L_0x002e:
            android.net.wifi.WifiManager r7 = (android.net.wifi.WifiManager) r7     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            goto L_0x0032
        L_0x0031:
            r7 = r2
        L_0x0032:
            if (r7 != 0) goto L_0x0035
            return r2
        L_0x0035:
            java.util.List r7 = r7.getScanResults()     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            int r1 = r7.size()     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            int r1 = r1 * 30
            r0.<init>(r1)     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            r1 = 1
            java.util.Iterator r7 = r7.iterator()     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
        L_0x0049:
            boolean r3 = r7.hasNext()     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            if (r3 == 0) goto L_0x0099
            java.lang.Object r3 = r7.next()     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            java.lang.String r4 = r3.BSSID     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            if (r4 == 0) goto L_0x0071
            int r5 = r4.length()     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            r6 = 17
            if (r5 < r6) goto L_0x0071
            java.lang.String r5 = "00:00:00:00:00:00"
            boolean r5 = r5.equals(r4)     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            if (r5 == 0) goto L_0x006a
            goto L_0x0071
        L_0x006a:
            java.util.Locale r5 = java.util.Locale.US     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            java.lang.String r4 = r4.toUpperCase(r5)     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            goto L_0x0072
        L_0x0071:
            r4 = r2
        L_0x0072:
            if (r4 == 0) goto L_0x0049
            boolean r5 = r4.equals(r8)     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            if (r5 != 0) goto L_0x0049
            if (r1 == 0) goto L_0x007e
            r1 = 0
            goto L_0x0083
        L_0x007e:
            java.lang.String r5 = ":"
            r0.append(r5)     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
        L_0x0083:
            java.lang.String r5 = ":"
            java.lang.String r6 = ""
            java.lang.String r4 = r4.replaceAll(r5, r6)     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            r0.append(r4)     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            java.lang.String r4 = ";"
            r0.append(r4)     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            int r3 = r3.level     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            r0.append(r3)     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            goto L_0x0049
        L_0x0099:
            java.lang.String r7 = r0.toString()     // Catch:{ SecurityException -> 0x00a8, Exception -> 0x009e }
            return r7
        L_0x009e:
            r7 = move-exception
            java.lang.String r8 = f178if
            java.lang.String r7 = r7.toString()
            com.threatmetrix.TrustDefender.internal.TL.m338new(r8, r7)
        L_0x00a8:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.HZ.m75if(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0033 A[Catch:{ SecurityException -> 0x0042, Exception -> 0x0037 }] */
    /* renamed from: new reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m78new(android.content.Context r3) {
        /*
            com.threatmetrix.TrustDefender.internal.N$R r0 = new com.threatmetrix.TrustDefender.internal.N$R
            com.threatmetrix.TrustDefender.internal.N r1 = new com.threatmetrix.TrustDefender.internal.N
            r1.<init>()
            r0.<init>(r3)
            boolean r1 = com.threatmetrix.TrustDefender.internal.N.T.m174else()
            if (r1 == 0) goto L_0x0043
            java.lang.String r1 = "android.permission.CHANGE_WIFI_STATE"
            java.lang.String r2 = r3.getPackageName()
            boolean r0 = r0.m169if(r1, r2)
            if (r0 == 0) goto L_0x0043
            android.content.Context r3 = r3.getApplicationContext()     // Catch:{ SecurityException -> 0x0042, Exception -> 0x0037 }
            java.lang.String r0 = "wifi"
            java.lang.Object r3 = r3.getSystemService(r0)     // Catch:{ SecurityException -> 0x0042, Exception -> 0x0037 }
            if (r3 == 0) goto L_0x0030
            boolean r0 = r3 instanceof android.net.wifi.WifiManager     // Catch:{ SecurityException -> 0x0042, Exception -> 0x0037 }
            if (r0 != 0) goto L_0x002d
            goto L_0x0030
        L_0x002d:
            android.net.wifi.WifiManager r3 = (android.net.wifi.WifiManager) r3     // Catch:{ SecurityException -> 0x0042, Exception -> 0x0037 }
            goto L_0x0031
        L_0x0030:
            r3 = 0
        L_0x0031:
            if (r3 == 0) goto L_0x0036
            r3.startScan()     // Catch:{ SecurityException -> 0x0042, Exception -> 0x0037 }
        L_0x0036:
            return
        L_0x0037:
            r3 = move-exception
            java.lang.String r0 = f178if
            java.lang.String r3 = r3.toString()
            com.threatmetrix.TrustDefender.internal.TL.m338new(r0, r3)
            goto L_0x0043
        L_0x0042:
            return
        L_0x0043:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.HZ.m78new(android.content.Context):void");
    }
}
