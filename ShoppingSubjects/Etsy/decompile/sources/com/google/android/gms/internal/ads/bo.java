package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@bu
public final class bo implements bs {
    private static final Object a = new Object();
    @VisibleForTesting
    private static bs b;
    @VisibleForTesting
    private static bs c;
    private final Object d;
    private final Context e;
    private final WeakHashMap<Thread, Boolean> f;
    private final ExecutorService g;
    private final zzang h;

    private bo(Context context) {
        this(context, zzang.zzsl());
    }

    private bo(Context context, zzang zzang) {
        this.d = new Object();
        this.f = new WeakHashMap<>();
        this.g = Executors.newCachedThreadPool();
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.e = context;
        this.h = zzang;
    }

    @VisibleForTesting
    private final Builder a(String str, String str2, String str3, int i) {
        boolean z;
        try {
            z = Wrappers.packageManager(this.e).isCallerInstantApp();
        } catch (Throwable th) {
            ka.b("Error fetching instant app info", th);
            z = false;
        }
        String str4 = EnvironmentCompat.MEDIA_UNKNOWN;
        try {
            str4 = this.e.getPackageName();
        } catch (Throwable unused) {
            ka.e("Cannot obtain package name, proceeding.");
        }
        Builder appendQueryParameter = new Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("is_aia", Boolean.toString(z)).appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", VERSION.RELEASE).appendQueryParameter("api", String.valueOf(VERSION.SDK_INT));
        String str5 = "device";
        String str6 = Build.MANUFACTURER;
        String str7 = Build.MODEL;
        if (!str7.startsWith(str6)) {
            StringBuilder sb = new StringBuilder(1 + String.valueOf(str6).length() + String.valueOf(str7).length());
            sb.append(str6);
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(str7);
            str7 = sb.toString();
        }
        return appendQueryParameter.appendQueryParameter(str5, str7).appendQueryParameter("js", this.h.zzcw).appendQueryParameter("appid", str4).appendQueryParameter("exceptiontype", str).appendQueryParameter("stacktrace", str2).appendQueryParameter("eids", TextUtils.join(",", akl.a())).appendQueryParameter("exceptionkey", str3).appendQueryParameter("cl", "191880412").appendQueryParameter("rc", "dev").appendQueryParameter("session_id", ajh.c()).appendQueryParameter("sampling_rate", Integer.toString(i)).appendQueryParameter("pb_tm", String.valueOf(ajh.f().a(akl.dj)));
    }

    public static bs a(Context context) {
        synchronized (a) {
            if (b == null) {
                if (((Boolean) ajh.f().a(akl.b)).booleanValue()) {
                    b = new bo(context);
                } else {
                    b = new bt();
                }
            }
        }
        return b;
    }

    public static bs a(Context context, zzang zzang) {
        synchronized (a) {
            if (c == null) {
                if (((Boolean) ajh.f().a(akl.b)).booleanValue()) {
                    bo boVar = new bo(context, zzang);
                    Thread thread = Looper.getMainLooper().getThread();
                    if (thread != null) {
                        synchronized (boVar.d) {
                            boVar.f.put(thread, Boolean.valueOf(true));
                        }
                        thread.setUncaughtExceptionHandler(new bq(boVar, thread.getUncaughtExceptionHandler()));
                    }
                    Thread.setDefaultUncaughtExceptionHandler(new bp(boVar, Thread.getDefaultUncaughtExceptionHandler()));
                    c = boVar;
                } else {
                    c = new bt();
                }
            }
        }
        return c;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003f, code lost:
        if (r3 == false) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.Thread r10, java.lang.Throwable r11) {
        /*
            r9 = this;
            r10 = 1
            r0 = 0
            if (r11 == 0) goto L_0x0042
            r1 = r11
            r2 = r0
            r3 = r2
        L_0x0007:
            if (r1 == 0) goto L_0x003d
            java.lang.StackTraceElement[] r4 = r1.getStackTrace()
            int r5 = r4.length
            r6 = r3
            r3 = r2
            r2 = r0
        L_0x0011:
            if (r2 >= r5) goto L_0x0036
            r7 = r4[r2]
            java.lang.String r8 = r7.getClassName()
            boolean r8 = com.google.android.gms.internal.ads.jp.b(r8)
            if (r8 == 0) goto L_0x0020
            r3 = r10
        L_0x0020:
            java.lang.Class r8 = r9.getClass()
            java.lang.String r8 = r8.getName()
            java.lang.String r7 = r7.getClassName()
            boolean r7 = r8.equals(r7)
            if (r7 == 0) goto L_0x0033
            r6 = r10
        L_0x0033:
            int r2 = r2 + 1
            goto L_0x0011
        L_0x0036:
            java.lang.Throwable r1 = r1.getCause()
            r2 = r3
            r3 = r6
            goto L_0x0007
        L_0x003d:
            if (r2 == 0) goto L_0x0042
            if (r3 != 0) goto L_0x0042
            goto L_0x0043
        L_0x0042:
            r10 = r0
        L_0x0043:
            if (r10 == 0) goto L_0x004c
            java.lang.String r10 = ""
            r0 = 1065353216(0x3f800000, float:1.0)
            r9.a(r11, r10, r0)
        L_0x004c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.bo.a(java.lang.Thread, java.lang.Throwable):void");
    }

    public final void a(Throwable th, String str) {
        a(th, str, 1.0f);
    }

    public final void a(Throwable th, String str, float f2) {
        if (jp.a(th) != null) {
            String name = th.getClass().getName();
            StringWriter stringWriter = new StringWriter();
            vo.a(th, new PrintWriter(stringWriter));
            String stringWriter2 = stringWriter.toString();
            int i = 0;
            int i2 = 1;
            boolean z = Math.random() < ((double) f2);
            if (f2 > 0.0f) {
                i2 = (int) (1.0f / f2);
            }
            if (z) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(a(name, stringWriter2, str, i2).toString());
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    this.g.submit(new br(this, new kb(), (String) obj));
                }
            }
        }
    }
}
