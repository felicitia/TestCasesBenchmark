package com.crittercism.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.ResponseConstants;
import java.math.BigInteger;
import org.json.JSONException;
import org.json.JSONObject;

public final class av {
    public ak a;
    Context b;
    ao c;
    ba d;
    public String e;
    public ci f;
    public String g;
    public String h = AppBuild.ANDROID_PLATFORM;
    private cf i;
    private a j;

    interface a {
        String a();

        String b();

        String c();

        String d();
    }

    static class b implements a {
        private b() {
        }

        /* synthetic */ b(byte b) {
            this();
        }

        public final String b() {
            int i = VERSION.SDK_INT;
            if (VERSION.SDK_INT < 18) {
                return null;
            }
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return BigInteger.valueOf(statFs.getAvailableBlocksLong()).multiply(BigInteger.valueOf(statFs.getBlockSizeLong())).toString();
        }

        public final String a() {
            int i = VERSION.SDK_INT;
            if (VERSION.SDK_INT < 18) {
                return null;
            }
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return BigInteger.valueOf(statFs.getBlockCountLong()).multiply(BigInteger.valueOf(statFs.getBlockSizeLong())).toString();
        }

        public final String d() {
            int i = VERSION.SDK_INT;
            if (VERSION.SDK_INT < 18) {
                return null;
            }
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return BigInteger.valueOf(statFs.getAvailableBlocksLong()).multiply(BigInteger.valueOf(statFs.getBlockSizeLong())).toString();
        }

        public final String c() {
            int i = VERSION.SDK_INT;
            if (VERSION.SDK_INT < 18) {
                return null;
            }
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return BigInteger.valueOf(statFs.getBlockCountLong()).multiply(BigInteger.valueOf(statFs.getBlockSizeLong())).toString();
        }
    }

    static class c implements a {
        private c() {
        }

        /* synthetic */ c(byte b) {
            this();
        }

        public final String b() {
            int i = VERSION.SDK_INT;
            if (VERSION.SDK_INT >= 18) {
                return null;
            }
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return BigInteger.valueOf((long) statFs.getAvailableBlocks()).multiply(BigInteger.valueOf((long) statFs.getBlockSize())).toString();
        }

        public final String a() {
            int i = VERSION.SDK_INT;
            if (VERSION.SDK_INT >= 18) {
                return null;
            }
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return BigInteger.valueOf((long) statFs.getBlockCount()).multiply(BigInteger.valueOf((long) statFs.getBlockSize())).toString();
        }

        public final String d() {
            int i = VERSION.SDK_INT;
            if (VERSION.SDK_INT >= 18) {
                return null;
            }
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return BigInteger.valueOf((long) statFs.getAvailableBlocks()).multiply(BigInteger.valueOf((long) statFs.getBlockSize())).toString();
        }

        public final String c() {
            int i = VERSION.SDK_INT;
            if (VERSION.SDK_INT >= 18) {
                return null;
            }
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return BigInteger.valueOf((long) statFs.getBlockCount()).multiply(BigInteger.valueOf((long) statFs.getBlockSize())).toString();
        }
    }

    public av(ak akVar, Context context, ao aoVar, String str) {
        this.a = akVar;
        this.b = context;
        this.c = aoVar;
        this.d = new bb();
        this.e = str;
        this.i = new cf(context);
        this.f = new ci(context);
        if (VERSION.SDK_INT >= 18) {
            this.j = new b(0);
        } else {
            this.j = new c(0);
        }
        try {
            Class.forName("UnityPlayerActivity");
            this.h = "unity";
        } catch (ClassNotFoundException unused) {
        }
    }

    public final Integer a() {
        return Integer.valueOf(this.a.b);
    }

    public final String b() {
        try {
            return ((TelephonyManager) this.b.getSystemService(ResponseConstants.PHONE)).getNetworkOperatorName();
        } catch (Exception unused) {
            return "";
        }
    }

    public final Integer c() {
        int i2 = 0;
        try {
            String networkOperator = ((TelephonyManager) this.b.getSystemService(ResponseConstants.PHONE)).getNetworkOperator();
            if (networkOperator != null) {
                i2 = Integer.parseInt(networkOperator.substring(0, 3));
            }
        } catch (Exception unused) {
        }
        return Integer.valueOf(i2);
    }

    public final Integer d() {
        Integer valueOf = Integer.valueOf(0);
        try {
            String networkOperator = ((TelephonyManager) this.b.getSystemService(ResponseConstants.PHONE)).getNetworkOperator();
            return networkOperator != null ? Integer.valueOf(Integer.parseInt(networkOperator.substring(3))) : valueOf;
        } catch (Exception unused) {
            return valueOf;
        }
    }

    public final Float e() {
        return Float.valueOf(this.b.getResources().getDisplayMetrics().density);
    }

    public final float f() {
        return this.b.getResources().getDisplayMetrics().xdpi;
    }

    public final float g() {
        return this.b.getResources().getDisplayMetrics().ydpi;
    }

    public final String h() {
        return this.i != null ? this.i.a() : "";
    }

    public final String i() {
        String language = this.b.getResources().getConfiguration().locale.getLanguage();
        return (language == null || language.length() == 0) ? "en" : language;
    }

    public final String j() {
        try {
            return this.j.b();
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable unused) {
            return null;
        }
    }

    public final String k() {
        try {
            return this.j.a();
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Long l() {
        return Long.valueOf(Runtime.getRuntime().maxMemory());
    }

    public final String m() {
        try {
            return this.j.d();
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable unused) {
            return null;
        }
    }

    public final String n() {
        try {
            return this.j.c();
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final JSONObject a(int i2) {
        if (!this.c.b || !ConnectivityManager.isNetworkTypeValid(i2)) {
            return null;
        }
        NetworkInfo networkInfo = ((ConnectivityManager) this.b.getSystemService("connectivity")).getNetworkInfo(i2);
        JSONObject jSONObject = new JSONObject();
        if (networkInfo != null) {
            try {
                jSONObject.put("available", networkInfo.isAvailable());
                jSONObject.put("connected", networkInfo.isConnected());
                if (!networkInfo.isConnected()) {
                    jSONObject.put("connecting", networkInfo.isConnectedOrConnecting());
                }
                jSONObject.put("failover", networkInfo.isFailover());
                if (i2 == 0) {
                    jSONObject.put("roaming", networkInfo.isRoaming());
                }
            } catch (JSONException unused) {
                return null;
            }
        } else {
            jSONObject.put("available", false);
            jSONObject.put("connected", false);
            jSONObject.put("connecting", false);
            jSONObject.put("failover", false);
            if (i2 == 0) {
                jSONObject.put("roaming", false);
            }
        }
        return jSONObject;
    }
}
