package com.crittercism.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.NetworkRequest.Builder;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.os.EnvironmentCompat;
import com.crittercism.internal.at.c;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

public final class bu {
    ay<at> a;
    ap b;
    private b c = b.UNKNOWN;
    private ConnectivityManager d;
    private ExecutorService e;
    private a f;

    @TargetApi(21)
    class a extends NetworkCallback {
        public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        }

        public final void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
        }

        public final void onLosing(Network network, int i) {
        }

        private a() {
        }

        /* synthetic */ a(bu buVar, byte b) {
            this();
        }

        public final void onLost(Network network) {
            bu.a(bu.this);
        }

        public final void onAvailable(Network network) {
            bu.a(bu.this);
        }
    }

    enum b {
        DISCONNECTED("disconnected"),
        TWO_G("2G"),
        THREE_G("3G"),
        LTE("LTE"),
        WIFI("wifi"),
        UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN);
        
        private String g;

        private b(String str) {
            this.g = str;
        }

        public static b a(NetworkInfo networkInfo) {
            if (networkInfo == null || !networkInfo.isConnected()) {
                return DISCONNECTED;
            }
            int type = networkInfo.getType();
            if (type == 0) {
                switch (networkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return TWO_G;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return THREE_G;
                    case 13:
                        return LTE;
                }
            } else if (type == 1) {
                return WIFI;
            }
            return UNKNOWN;
        }

        public final String toString() {
            return this.g;
        }
    }

    @TargetApi(21)
    public bu(@NonNull Context context, @NonNull ExecutorService executorService, @NonNull ay<at> ayVar, @NonNull ap apVar) {
        this.e = executorService;
        this.a = ayVar;
        this.b = apVar;
        if (ao.a(context, "android.permission.ACCESS_NETWORK_STATE") && VERSION.SDK_INT >= 21) {
            this.d = (ConnectivityManager) context.getSystemService("connectivity");
            if (this.d != null) {
                NetworkRequest build = new Builder().addCapability(12).build();
                this.f = new a(this, 0);
                this.d.registerNetworkCallback(build, this.f);
            }
        }
    }

    static /* synthetic */ void a(bu buVar) {
        b a2 = b.a(buVar.d.getActiveNetworkInfo());
        if (buVar.c != a2) {
            final at atVar = null;
            if (!(buVar.c == b.UNKNOWN || a2 == b.UNKNOWN)) {
                if (buVar.c == b.DISCONNECTED) {
                    atVar = at.a(c.c, a2.toString());
                } else if (a2 == b.DISCONNECTED) {
                    atVar = at.a(c.d, buVar.c.toString());
                } else {
                    String bVar = buVar.c.toString();
                    String bVar2 = a2.toString();
                    HashMap hashMap = new HashMap();
                    hashMap.put("change", Integer.valueOf(c.e - 1));
                    hashMap.put("oldType", bVar);
                    hashMap.put("newType", bVar2);
                    atVar = new at(com.crittercism.internal.at.b.e, new JSONObject(hashMap));
                }
            }
            buVar.c = a2;
            if (atVar != null) {
                buVar.e.submit(new Runnable() {
                    public final void run() {
                        if (((Boolean) bu.this.b.a(ap.ao)).booleanValue()) {
                            bu.this.a.a(atVar);
                        }
                    }
                });
            }
        }
    }
}
