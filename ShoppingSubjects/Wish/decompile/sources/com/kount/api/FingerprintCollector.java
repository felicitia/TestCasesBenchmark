package com.kount.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;

class FingerprintCollector extends CollectorTaskBase {
    protected Context context = null;

    enum DeviceIDType {
        ANDROID_ID("ANDROID_ID"),
        ANDROID_SERIAL("ANDROID_SERIAL"),
        MAC_HASH("MAC_HASH"),
        UID("UID");
        
        private final String name;

        private DeviceIDType(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }
    }

    static String internalName() {
        return "collector_device_cookie";
    }

    /* access modifiers changed from: 0000 */
    public String getName() {
        return "Fingerprint Collector";
    }

    FingerprintCollector(Object obj, Context context2) {
        super(obj);
        this.context = context2;
    }

    /* access modifiers changed from: 0000 */
    public String getInternalName() {
        return internalName();
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0104  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void collect() {
        /*
            r6 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            android.content.Context r1 = r6.context
            android.content.ContentResolver r1 = r1.getContentResolver()
            java.lang.String r2 = "android_id"
            java.lang.String r1 = android.provider.Settings.Secure.getString(r1, r2)
            java.lang.String r2 = "Android ID = %s"
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r5 = 0
            r4[r5] = r1
            java.lang.String r2 = java.lang.String.format(r2, r4)
            r6.debugMessage(r2)
            com.kount.api.FingerprintCollector$DeviceIDType r2 = com.kount.api.FingerprintCollector.DeviceIDType.ANDROID_ID
            java.lang.String r2 = r2.toString()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            com.kount.api.FingerprintCollector$DeviceIDType r5 = com.kount.api.FingerprintCollector.DeviceIDType.ANDROID_ID
            java.lang.String r5 = r5.toString()
            r4.append(r5)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            java.lang.String r1 = com.kount.api.HashUtils.convertToSha256Hash(r1)
            r0.put(r2, r1)
            com.kount.api.FingerprintCollector$DeviceIDType r1 = com.kount.api.FingerprintCollector.DeviceIDType.ANDROID_SERIAL
            java.lang.String r1 = r1.toString()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            com.kount.api.FingerprintCollector$DeviceIDType r4 = com.kount.api.FingerprintCollector.DeviceIDType.ANDROID_SERIAL
            java.lang.String r4 = r4.toString()
            r2.append(r4)
            java.lang.String r4 = android.os.Build.SERIAL
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            java.lang.String r2 = com.kount.api.HashUtils.convertToSha256Hash(r2)
            r0.put(r1, r2)
            java.lang.String r1 = r6.getMACAddresses()
            if (r1 == 0) goto L_0x008e
            com.kount.api.FingerprintCollector$DeviceIDType r2 = com.kount.api.FingerprintCollector.DeviceIDType.MAC_HASH
            java.lang.String r2 = r2.toString()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            com.kount.api.FingerprintCollector$DeviceIDType r5 = com.kount.api.FingerprintCollector.DeviceIDType.MAC_HASH
            java.lang.String r5 = r5.toString()
            r4.append(r5)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            java.lang.String r1 = com.kount.api.HashUtils.convertToSha256Hash(r1)
            r0.put(r2, r1)
        L_0x008e:
            java.lang.String r1 = r6.readCookies()
            r2 = 0
            if (r1 == 0) goto L_0x00c3
            com.kount.api.FingerprintCollector$DeviceIDType r4 = com.kount.api.FingerprintCollector.DeviceIDType.UID
            java.lang.String r4 = r4.toString()
            boolean r4 = r1.contains(r4)
            if (r4 == 0) goto L_0x00c3
            com.kount.api.FingerprintCollector$DeviceIDType r4 = com.kount.api.FingerprintCollector.DeviceIDType.UID     // Catch:{ IndexOutOfBoundsException -> 0x00c3 }
            java.lang.String r4 = r4.toString()     // Catch:{ IndexOutOfBoundsException -> 0x00c3 }
            int r4 = r1.indexOf(r4)     // Catch:{ IndexOutOfBoundsException -> 0x00c3 }
            com.kount.api.FingerprintCollector$DeviceIDType r5 = com.kount.api.FingerprintCollector.DeviceIDType.UID     // Catch:{ IndexOutOfBoundsException -> 0x00c3 }
            java.lang.String r5 = r5.toString()     // Catch:{ IndexOutOfBoundsException -> 0x00c3 }
            int r5 = r5.length()     // Catch:{ IndexOutOfBoundsException -> 0x00c3 }
            int r4 = r4 + r5
            int r4 = r4 + 3
            r5 = 34
            int r5 = r1.indexOf(r5, r4)     // Catch:{ IndexOutOfBoundsException -> 0x00c3 }
            java.lang.String r4 = r1.substring(r4, r5)     // Catch:{ IndexOutOfBoundsException -> 0x00c3 }
            goto L_0x00c4
        L_0x00c3:
            r4 = r2
        L_0x00c4:
            if (r4 != 0) goto L_0x00e7
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            com.kount.api.FingerprintCollector$DeviceIDType r5 = com.kount.api.FingerprintCollector.DeviceIDType.UID
            java.lang.String r5 = r5.toString()
            r4.append(r5)
            java.util.UUID r5 = java.util.UUID.randomUUID()
            java.lang.String r5 = r5.toString()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.String r4 = com.kount.api.HashUtils.convertToSha256Hash(r4)
        L_0x00e7:
            com.kount.api.FingerprintCollector$DeviceIDType r5 = com.kount.api.FingerprintCollector.DeviceIDType.UID
            java.lang.String r5 = r5.toString()
            r0.put(r5, r4)
            org.json.JSONObject r4 = new org.json.JSONObject
            r4.<init>(r0)
            com.kount.api.PostKey r0 = com.kount.api.PostKey.DEVICE_COOKIE
            java.lang.String r0 = r0.toString()
            java.lang.String r5 = r4.toString()
            r6.addDataPoint(r0, r5)
            if (r1 == 0) goto L_0x010d
            com.kount.api.PostKey r0 = com.kount.api.PostKey.OLD_DEVICE_COOKIE
            java.lang.String r0 = r0.toString()
            r6.addDataPoint(r0, r1)
        L_0x010d:
            java.lang.String r0 = r4.toString()
            r6.saveCookies(r0)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)
            r6.callCompletionHandler(r0, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kount.api.FingerprintCollector.collect():void");
    }

    /* access modifiers changed from: protected */
    public String readCookies() {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("k_prefs", 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("lic", null);
        }
        return null;
    }

    private void saveCookies(String str) {
        Editor edit = this.context.getSharedPreferences("k_prefs", 0).edit();
        edit.putString("lic", str);
        edit.apply();
    }

    private String getMACAddresses() {
        ArrayList arrayList = new ArrayList();
        try {
            WifiInfo connectionInfo = ((WifiManager) this.context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                String macAddress = connectionInfo.getMacAddress();
                if (macAddress != null) {
                    arrayList.add(macAddress.replace(":", ""));
                }
            }
            debugMessage("Wi-Fi not enabled, skipping.");
        } catch (SecurityException unused) {
            debugMessage("Wi-Fi permission denied.");
        }
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
                if (networkInterface.isUp()) {
                    String str = "Network:%s, virtual:%s.";
                    Object[] objArr = new Object[2];
                    objArr[0] = networkInterface.getName();
                    objArr[1] = networkInterface.isVirtual() ? "true" : "false";
                    debugMessage(String.format(str, objArr));
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress != null) {
                        StringBuilder sb = new StringBuilder();
                        for (byte valueOf : hardwareAddress) {
                            sb.append(String.format("%02X", new Object[]{Byte.valueOf(valueOf)}));
                        }
                        arrayList.add(sb.toString());
                    }
                }
            }
        } catch (SocketException unused2) {
            debugMessage("Bad socket connection, skipping.");
            addSoftError(SoftError.SERVICE_UNAVAILABLE.toString());
        } catch (SecurityException unused3) {
            debugMessage("Permission denied, skipping.");
            addSoftError(SoftError.PERMISSION_DENIED.toString());
        }
        Collections.sort(arrayList);
        if (arrayList.size() <= 0) {
            return null;
        }
        StringBuilder sb2 = new StringBuilder("{");
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb2.append((String) it.next());
            sb2.append(",");
        }
        sb2.append("}");
        return sb2.toString();
    }
}
