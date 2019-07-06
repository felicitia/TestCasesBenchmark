package com.klarna.checkout.internal.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.NetworkRequest.Builder;
import android.os.Build.VERSION;
import android.os.Handler;
import com.klarna.checkout.internal.a;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import org.json.JSONObject;

public final class e {
    static int a;
    private static List<NetworkCallback> b;

    private static String a(List<InterfaceAddress> list) {
        for (InterfaceAddress address : list) {
            InetAddress address2 = address.getAddress();
            if (address2 instanceof Inet4Address) {
                return address2.getHostAddress();
            }
        }
        return null;
    }

    public static JSONObject a(a aVar) {
        JSONObject jSONObject = new JSONObject();
        Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
            String a2 = a(networkInterface.getInterfaceAddresses());
            if (a2 != null) {
                jSONObject.put(networkInterface.getDisplayName(), a2);
            }
        }
        for (int i = 0; i < aVar.l.length(); i++) {
            JSONObject jSONObject2 = aVar.l.getJSONObject(i);
            jSONObject.put(jSONObject2.getString("name"), jSONObject2.getString("inet"));
        }
        return jSONObject;
    }

    public static void a(Activity activity, NetworkCallback networkCallback) {
        if (VERSION.SDK_INT >= 22) {
            new StringBuilder("...call to free NetworkRequest active requests = ").append(a);
            if (b == null) {
                b = new ArrayList();
            }
            b.add(networkCallback);
            int i = a - 1;
            a = i;
            if (i <= 0) {
                ConnectivityManager connectivityManager = (ConnectivityManager) activity.getApplication().getSystemService("connectivity");
                for (NetworkCallback unregisterNetworkCallback : b) {
                    try {
                        connectivityManager.unregisterNetworkCallback(unregisterNetworkCallback);
                    } catch (Exception unused) {
                    }
                }
                b = null;
                a = 0;
            }
        }
    }

    @TargetApi(21)
    public static void a(Activity activity, String str, final d dVar) {
        if (VERSION.SDK_INT >= 22) {
            final AnonymousClass2 r7 = new Runnable() {
                public final void run() {
                    dVar.a();
                }
            };
            final Handler handler = new Handler();
            handler.postDelayed(r7, 1000);
            Builder builder = new Builder();
            builder.addCapability(12);
            builder.addTransportType(0);
            NetworkRequest build = builder.build();
            ConnectivityManager connectivityManager = (ConnectivityManager) activity.getApplication().getSystemService("connectivity");
            final ConnectivityManager connectivityManager2 = connectivityManager;
            final String str2 = str;
            final Activity activity2 = activity;
            final d dVar2 = dVar;
            AnonymousClass1 r2 = new NetworkCallback() {
                @TargetApi(22)
                public final void onAvailable(Network network) {
                    e.a++;
                    LinkProperties linkProperties = connectivityManager2.getLinkProperties(network);
                    new StringBuilder("Found adaptor ").append(linkProperties.getInterfaceName());
                    if (str2 == null || linkProperties.getInterfaceName().equals(str2)) {
                        handler.removeCallbacks(r7);
                        dVar2.a(network, this);
                        StringBuilder sb = new StringBuilder("...used adaptor ");
                        sb.append(linkProperties.getInterfaceName());
                        sb.append(" active requests ");
                        sb.append(e.a);
                        return;
                    }
                    new StringBuilder("...did not match ").append(str2);
                    e.a(activity2, this);
                }

                public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                    super.onCapabilitiesChanged(network, networkCapabilities);
                }

                public final void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                    super.onLinkPropertiesChanged(network, linkProperties);
                }

                public final void onLosing(Network network, int i) {
                    super.onLosing(network, i);
                }

                public final void onLost(Network network) {
                    super.onLost(network);
                }
            };
            try {
                connectivityManager.requestNetwork(build, r2);
            } catch (SecurityException e) {
                new StringBuilder("SECEX ").append(e.getMessage());
                dVar.a();
            }
        } else {
            dVar.a();
        }
    }
}
