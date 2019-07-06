package com.google.android.gms.iid;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.AndroidUtilsLight;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Deprecated
public class a {
    private static Map<String, a> a = new ArrayMap();
    private static long b = TimeUnit.DAYS.toSeconds(7);
    private static k c;
    private static f d;
    private static String e;
    private Context f;
    private String g = "";

    private a(Context context, String str) {
        this.f = context.getApplicationContext();
        this.g = str;
    }

    static int a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e2) {
            String valueOf = String.valueOf(e2);
            StringBuilder sb = new StringBuilder(38 + String.valueOf(valueOf).length());
            sb.append("Never happens: can't find own package ");
            sb.append(valueOf);
            Log.w("InstanceID", sb.toString());
            return 0;
        }
    }

    @KeepForSdk
    public static synchronized a a(Context context, Bundle bundle) {
        a aVar;
        synchronized (a.class) {
            String string = bundle == null ? "" : bundle.getString("subtype");
            if (string == null) {
                string = "";
            }
            Context applicationContext = context.getApplicationContext();
            if (c == null) {
                String packageName = applicationContext.getPackageName();
                StringBuilder sb = new StringBuilder(73 + String.valueOf(packageName).length());
                sb.append("Instance ID SDK is deprecated, ");
                sb.append(packageName);
                sb.append(" should update to use Firebase Instance ID");
                Log.w("InstanceID", sb.toString());
                c = new k(applicationContext);
                d = new f(applicationContext);
            }
            e = Integer.toString(a(applicationContext));
            aVar = (a) a.get(string);
            if (aVar == null) {
                aVar = new a(applicationContext, string);
                a.put(string, aVar);
            }
        }
        return aVar;
    }

    static String a(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance(AndroidUtilsLight.DIGEST_ALGORITHM_SHA1).digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) (112 + (digest[0] & 15));
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException unused) {
            Log.w("InstanceID", "Unexpected error, device missing required algorithms");
            return null;
        }
    }

    public static k b() {
        return c;
    }

    static String b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e2) {
            String valueOf = String.valueOf(e2);
            StringBuilder sb = new StringBuilder(38 + String.valueOf(valueOf).length());
            sb.append("Never happens: can't find own package ");
            sb.append(valueOf);
            Log.w("InstanceID", sb.toString());
            return null;
        }
    }

    @Deprecated
    public static a c(Context context) {
        return a(context, (Bundle) null);
    }

    private final KeyPair c() {
        return c.c(this.g).a();
    }

    /* access modifiers changed from: 0000 */
    public final void a() {
        c.d(this.g);
    }

    @Deprecated
    public void a(String str, String str2) throws IOException {
        a(str, str2, null);
    }

    public final void a(String str, String str2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        c.b(this.g, str, str2);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("sender", str);
        if (str2 != null) {
            bundle.putString("scope", str2);
        }
        bundle.putString("subscription", str);
        bundle.putString("delete", "1");
        bundle.putString("X-delete", "1");
        bundle.putString("subtype", "".equals(this.g) ? str : this.g);
        String str3 = "X-subtype";
        if (!"".equals(this.g)) {
            str = this.g;
        }
        bundle.putString(str3, str);
        f.a(d.a(bundle, c()));
    }

    @Deprecated
    public String b(String str, String str2, Bundle bundle) throws IOException {
        String str3;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        String str4 = null;
        String a2 = c.a("appVersion");
        boolean z = true;
        if (a2 != null && a2.equals(e)) {
            String a3 = c.a("lastToken");
            if (a3 != null) {
                if ((System.currentTimeMillis() / 1000) - Long.valueOf(Long.parseLong(a3)).longValue() <= b) {
                    z = false;
                }
            }
        }
        if (!z) {
            str4 = c.a(this.g, str, str2);
        }
        if (str4 == null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            str3 = c(str, str2, bundle);
            if (str3 != null) {
                c.a(this.g, str, str2, str3, e);
                return str3;
            }
        } else {
            str3 = str4;
        }
        return str3;
    }

    public final String c(String str, String str2, Bundle bundle) throws IOException {
        if (str2 != null) {
            bundle.putString("scope", str2);
        }
        bundle.putString("sender", str);
        String str3 = "".equals(this.g) ? str : this.g;
        if (!bundle.containsKey("legacy.register")) {
            bundle.putString("subscription", str);
            bundle.putString("subtype", str3);
            bundle.putString("X-subscription", str);
            bundle.putString("X-subtype", str3);
        }
        String a2 = f.a(d.a(bundle, c()));
        if (!"RST".equals(a2) && !a2.startsWith("RST|")) {
            return a2;
        }
        InstanceIDListenerService.zzd(this.f, c);
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }
}
