package com.usebutton.merchant;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.support.v4.os.EnvironmentCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.etsy.android.lib.models.AppBuild;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: DeviceManagerImpl */
final class i implements h {
    private static final String c = "i";
    private static h e;
    @VisibleForTesting
    k<Long> a = new k<Long>() {
        /* renamed from: a */
        public Long b() {
            return Long.valueOf(System.currentTimeMillis());
        }
    };
    @VisibleForTesting
    k<Date> b = new k<Date>() {
        /* renamed from: a */
        public Date b() {
            return new Date();
        }
    };
    private final Context d;

    private String g() {
        return "1.0.0-beta-3";
    }

    private int h() {
        return 1;
    }

    static h a(Context context) {
        if (e == null) {
            e = new i(context);
        }
        return e;
    }

    @VisibleForTesting
    i(Context context) {
        this.d = context;
    }

    @Nullable
    @WorkerThread
    public String a() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.d).getId();
        } catch (IOException e2) {
            Log.e(c, "Error has occurred", e2);
            return null;
        } catch (GooglePlayServicesNotAvailableException e3) {
            Log.e(c, "Error has occurred", e3);
            return null;
        } catch (GooglePlayServicesRepairableException e4) {
            Log.e(c, "Error has occurred", e4);
            return null;
        }
    }

    @WorkerThread
    public boolean b() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.d).isLimitAdTrackingEnabled();
        } catch (IOException e2) {
            Log.e(c, "Error has occurred", e2);
            return false;
        } catch (GooglePlayServicesNotAvailableException e3) {
            Log.e(c, "Error has occurred", e3);
            return false;
        } catch (GooglePlayServicesRepairableException e4) {
            Log.e(c, "Error has occurred", e4);
            return false;
        } catch (IllegalStateException e5) {
            Log.e(c, "Error has occurred", e5);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public String f() {
        WindowManager windowManager = (WindowManager) this.d.getSystemService("window");
        if (windowManager == null) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        Display defaultDisplay = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return String.format(Locale.US, "%dx%d", new Object[]{Integer.valueOf(displayMetrics.widthPixels), Integer.valueOf(displayMetrics.heightPixels)});
    }

    public Map<String, String> c() {
        HashMap hashMap = new HashMap();
        hashMap.put("timezone", Calendar.getInstance().getTimeZone().getID());
        hashMap.put("os", AppBuild.ANDROID_PLATFORM);
        hashMap.put("os_version", VERSION.RELEASE);
        hashMap.put("device", String.format("%s %s", new Object[]{Build.MANUFACTURER, Build.MODEL}));
        hashMap.put("screen", f());
        Locale locale = Locale.getDefault();
        hashMap.put("country", locale.getCountry());
        hashMap.put("language", locale.getLanguage());
        return hashMap;
    }

    public boolean d() {
        PackageInfo q = q();
        return q != null && q.firstInstallTime + TimeUnit.HOURS.toMillis(12) < ((Long) this.a.b()).longValue();
    }

    public String e() {
        StringBuilder sb = new StringBuilder();
        sb.append("com.usebutton.merchant/");
        sb.append(g());
        sb.append('+');
        sb.append(h());
        sb.append(' ');
        sb.append("(Android ");
        sb.append(i());
        sb.append(VectorFormat.DEFAULT_SEPARATOR);
        sb.append(j());
        sb.append(' ');
        sb.append(k());
        sb.append(VectorFormat.DEFAULT_SEPARATOR);
        sb.append(l());
        sb.append('/');
        sb.append(m());
        sb.append('+');
        sb.append(n());
        sb.append(VectorFormat.DEFAULT_SEPARATOR);
        sb.append(String.format(Locale.US, "Scale/%.1f; ", new Object[]{Float.valueOf(o())}));
        Locale p = p();
        sb.append(p.getLanguage());
        sb.append('_');
        sb.append(p.getCountry().toLowerCase());
        sb.append(')');
        return sb.toString();
    }

    private String i() {
        return VERSION.RELEASE;
    }

    private String j() {
        return Build.MANUFACTURER;
    }

    private String k() {
        return Build.MODEL;
    }

    @Nullable
    private String l() {
        PackageInfo q = q();
        if (q != null) {
            return q.packageName;
        }
        return null;
    }

    @Nullable
    private String m() {
        PackageInfo q = q();
        if (q != null) {
            return q.versionName;
        }
        return null;
    }

    private int n() {
        PackageInfo q = q();
        if (q != null) {
            return q.versionCode;
        }
        return -1;
    }

    private float o() {
        return this.d.getResources().getDisplayMetrics().density;
    }

    private Locale p() {
        return Locale.getDefault();
    }

    @Nullable
    private PackageInfo q() {
        try {
            return this.d.getPackageManager().getPackageInfo(this.d.getPackageName(), 0);
        } catch (NameNotFoundException unused) {
            return null;
        }
    }
}
