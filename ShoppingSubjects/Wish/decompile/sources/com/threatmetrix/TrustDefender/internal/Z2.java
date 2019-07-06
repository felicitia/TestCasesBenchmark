package com.threatmetrix.TrustDefender.internal;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

class Z2 {

    /* renamed from: case reason: not valid java name */
    private static final String f722case = TL.m331if(Z2.class);

    /* renamed from: char reason: not valid java name */
    String f723char = null;

    /* renamed from: do reason: not valid java name */
    String f724do = null;

    /* renamed from: else reason: not valid java name */
    String f725else = null;

    /* renamed from: for reason: not valid java name */
    String f726for = null;

    /* renamed from: if reason: not valid java name */
    String f727if = null;

    /* renamed from: int reason: not valid java name */
    String f728int = null;

    /* renamed from: new reason: not valid java name */
    String f729new = null;

    /* renamed from: try reason: not valid java name */
    String f730try = null;

    static class E {

        /* renamed from: for reason: not valid java name */
        final InetAddress[] f731for;

        /* renamed from: int reason: not valid java name */
        final String f732int;

        /* renamed from: new reason: not valid java name */
        final String f733new;

        public E(String str, String str2, InetAddress[] inetAddressArr) {
            this.f732int = str;
            this.f733new = str2;
            this.f731for = inetAddressArr;
        }
    }

    enum W {
        BLUETOOTH("bluetooth tethering"),
        CELLULAR("cellular"),
        MOBILE("mobile"),
        WIFI("wifi"),
        ETHERNET("ethernet");
        

        /* renamed from: byte reason: not valid java name */
        final String f740byte;

        private W(String str) {
            this.f740byte = str;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public final boolean m457for(String[] strArr) {
        if (strArr != null && strArr.length >= 4) {
            if (this.f729new == null && strArr[0] != null) {
                this.f729new = strArr[0];
            }
            if (this.f724do == null && strArr[1] != null) {
                this.f724do = strArr[1];
            }
            if (this.f728int == null && strArr[2] != null) {
                this.f728int = strArr[2];
            }
            if (this.f727if == null && strArr[3] != null) {
                this.f727if = strArr[3];
            }
        }
        return (this.f729new == null || this.f724do == null || this.f728int == null || this.f727if == null) ? false : true;
    }

    /* renamed from: for reason: not valid java name */
    private static void m456for(InetAddress inetAddress, String str, Map<String, String> map, Map<String, String> map2) {
        if (!inetAddress.isLoopbackAddress()) {
            if (inetAddress instanceof Inet4Address) {
                map.put(inetAddress.getHostAddress(), str);
            } else if (inetAddress instanceof Inet6Address) {
                String hostAddress = inetAddress.getHostAddress();
                int indexOf = hostAddress.indexOf("%");
                if (indexOf > 0) {
                    hostAddress = hostAddress.substring(0, indexOf);
                }
                map2.put(hostAddress, str);
            }
        }
    }

    Z2() {
        TreeMap treeMap = new TreeMap();
        TreeMap treeMap2 = new TreeMap();
        TreeMap treeMap3 = new TreeMap();
        E[] eArr = PH.m275do().m276byte();
        if (eArr == null || eArr.length <= 0) {
            try {
                Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
                if (networkInterfaces != null) {
                    while (networkInterfaces.hasMoreElements()) {
                        NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
                        String name = networkInterface.getName();
                        if (networkInterface.getHardwareAddress() != null) {
                            String str = NK.m219int(networkInterface.getHardwareAddress());
                            if (NK.m227void(str)) {
                                treeMap3.put(str, name);
                            }
                        }
                        if (!name.startsWith("dummy")) {
                            Enumeration inetAddresses = networkInterface.getInetAddresses();
                            while (inetAddresses.hasMoreElements()) {
                                m456for((InetAddress) inetAddresses.nextElement(), name, treeMap, treeMap2);
                            }
                        }
                    }
                }
            } catch (SocketException unused) {
            }
        } else {
            for (E e : eArr) {
                if (NK.m203byte(e.f733new)) {
                    treeMap3.put(e.f733new, e.f732int);
                }
                if (!e.f732int.startsWith("dummy")) {
                    InetAddress[] inetAddressArr = e.f731for;
                    if (inetAddressArr.length > 0) {
                        for (InetAddress inetAddress : inetAddressArr) {
                            m456for(inetAddress, e.f732int, treeMap, treeMap2);
                        }
                    }
                }
            }
        }
        this.f726for = NK.m209do((Map<String, String>) treeMap);
        this.f723char = NK.m209do((Map<String, String>) treeMap2);
        this.f730try = NK.m209do((Map<String, String>) treeMap3);
    }
}
