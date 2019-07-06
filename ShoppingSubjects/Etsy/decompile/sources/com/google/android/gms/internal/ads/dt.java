package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.Locale;

public final class dt {
    private String A;
    private boolean B;
    private int a;
    private boolean b;
    private boolean c;
    private int d;
    private int e;
    private int f;
    private String g;
    private int h;
    private int i;
    private int j;
    private boolean k;
    private int l;
    private double m;
    private boolean n;
    private String o;
    private String p;
    private boolean q;
    private boolean r;
    private String s;
    private boolean t;
    private boolean u;
    private String v;
    private String w;
    private float x;
    private int y;
    private int z;

    public dt(Context context) {
        PackageManager packageManager = context.getPackageManager();
        a(context);
        b(context);
        c(context);
        Locale locale = Locale.getDefault();
        boolean z2 = false;
        this.q = a(packageManager, "geo:0,0?q=donuts") != null;
        if (a(packageManager, "http://www.google.com") != null) {
            z2 = true;
        }
        this.r = z2;
        this.s = locale.getCountry();
        ajh.a();
        this.t = jp.a();
        this.u = DeviceProperties.isSidewinder(context);
        this.v = locale.getLanguage();
        this.w = b(context, packageManager);
        this.A = a(context, packageManager);
        Resources resources = context.getResources();
        if (resources != null) {
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            if (displayMetrics != null) {
                this.x = displayMetrics.density;
                this.y = displayMetrics.widthPixels;
                this.z = displayMetrics.heightPixels;
            }
        }
    }

    public dt(Context context, ds dsVar) {
        context.getPackageManager();
        a(context);
        b(context);
        c(context);
        this.o = Build.FINGERPRINT;
        this.p = Build.DEVICE;
        this.B = PlatformVersion.isAtLeastIceCreamSandwichMR1() && ala.a(context);
        this.q = dsVar.b;
        this.r = dsVar.c;
        this.s = dsVar.e;
        this.t = dsVar.f;
        this.u = dsVar.g;
        this.v = dsVar.j;
        this.w = dsVar.k;
        this.A = dsVar.l;
        this.x = dsVar.s;
        this.y = dsVar.t;
        this.z = dsVar.u;
    }

    private static ResolveInfo a(PackageManager packageManager, String str) {
        try {
            return packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)), 65536);
        } catch (Throwable th) {
            ao.i().a(th, "DeviceInfo.getResolveInfo");
            return null;
        }
    }

    private static String a(Context context, PackageManager packageManager) {
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo("com.android.vending", 128);
            if (packageInfo != null) {
                int i2 = packageInfo.versionCode;
                String str = packageInfo.packageName;
                StringBuilder sb = new StringBuilder(12 + String.valueOf(str).length());
                sb.append(i2);
                sb.append(".");
                sb.append(str);
                return sb.toString();
            }
        } catch (Exception unused) {
        }
        return null;
    }

    private final void a(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager != null) {
            try {
                this.a = audioManager.getMode();
                this.b = audioManager.isMusicActive();
                this.c = audioManager.isSpeakerphoneOn();
                this.d = audioManager.getStreamVolume(3);
                this.e = audioManager.getRingerMode();
                this.f = audioManager.getStreamVolume(2);
                return;
            } catch (Throwable th) {
                ao.i().a(th, "DeviceInfo.gatherAudioInfo");
            }
        }
        this.a = -2;
        this.b = false;
        this.c = false;
        this.d = 0;
        this.e = 0;
        this.f = 0;
    }

    private static String b(Context context, PackageManager packageManager) {
        ResolveInfo a2 = a(packageManager, "market://details?id=com.google.android.gms.ads");
        if (a2 == null) {
            return null;
        }
        ActivityInfo activityInfo = a2.activityInfo;
        if (activityInfo == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(activityInfo.packageName, 0);
            if (packageInfo != null) {
                int i2 = packageInfo.versionCode;
                String str = activityInfo.packageName;
                StringBuilder sb = new StringBuilder(12 + String.valueOf(str).length());
                sb.append(i2);
                sb.append(".");
                sb.append(str);
                return sb.toString();
            }
        } catch (NameNotFoundException unused) {
        }
        return null;
    }

    @TargetApi(16)
    private final void b(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ResponseConstants.PHONE);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.g = telephonyManager.getNetworkOperator();
        this.i = telephonyManager.getNetworkType();
        this.j = telephonyManager.getPhoneType();
        this.h = -2;
        this.k = false;
        this.l = -1;
        ao.e();
        if (hd.a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                this.h = activeNetworkInfo.getType();
                this.l = activeNetworkInfo.getDetailedState().ordinal();
            } else {
                this.h = -1;
            }
            if (VERSION.SDK_INT >= 16) {
                this.k = connectivityManager.isActiveNetworkMetered();
            }
        }
    }

    private final void c(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        boolean z2 = false;
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra("status", -1);
            this.m = (double) (((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra(ResponseConstants.SCALE, -1)));
            if (intExtra == 2 || intExtra == 5) {
                z2 = true;
            }
            this.n = z2;
            return;
        }
        this.m = -1.0d;
        this.n = false;
    }

    public final ds a() {
        int i2 = this.a;
        boolean z2 = this.q;
        boolean z3 = this.r;
        String str = this.g;
        String str2 = this.s;
        boolean z4 = this.t;
        boolean z5 = this.u;
        boolean z6 = this.b;
        boolean z7 = this.c;
        String str3 = this.v;
        String str4 = this.w;
        String str5 = this.A;
        int i3 = this.d;
        int i4 = this.h;
        int i5 = this.i;
        int i6 = i4;
        int i7 = this.j;
        int i8 = this.e;
        int i9 = this.f;
        float f2 = this.x;
        int i10 = this.y;
        int i11 = i3;
        int i12 = this.z;
        double d2 = this.m;
        boolean z8 = this.n;
        boolean z9 = this.k;
        boolean z10 = z9;
        int i13 = i11;
        int i14 = i7;
        int i15 = i8;
        int i16 = i9;
        float f3 = f2;
        int i17 = i10;
        int i18 = i12;
        int i19 = i6;
        ds dsVar = new ds(i2, z2, z3, str, str2, z4, z5, z6, z7, str3, str4, str5, i13, i19, i5, i14, i15, i16, f3, i17, i18, d2, z8, z10, this.l, this.o, this.B, this.p);
        return dsVar;
    }
}
