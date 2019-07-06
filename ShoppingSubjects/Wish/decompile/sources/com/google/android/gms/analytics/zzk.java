package com.google.android.gms.analytics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdd;
import com.google.android.gms.internal.measurement.zzu;
import com.google.android.gms.internal.measurement.zzz;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressLint({"StaticFieldLeak"})
public final class zzk {
    private static volatile zzk zzsa;
    private final Context zzqx;
    /* access modifiers changed from: private */
    public final List<zzn> zzsb = new CopyOnWriteArrayList();
    private final zze zzsc = new zze();
    private final zza zzsd = new zza();
    private volatile zzu zzse;
    /* access modifiers changed from: private */
    public UncaughtExceptionHandler zzsf;

    class zza extends ThreadPoolExecutor {
        public zza() {
            super(1, 1, 1, TimeUnit.MINUTES, new LinkedBlockingQueue());
            setThreadFactory(new zzb(null));
            allowCoreThreadTimeOut(true);
        }

        /* access modifiers changed from: protected */
        public final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
            return new zzm(this, runnable, t);
        }
    }

    static class zzb implements ThreadFactory {
        private static final AtomicInteger zzsj = new AtomicInteger();

        private zzb() {
        }

        /* synthetic */ zzb(zzl zzl) {
            this();
        }

        public final Thread newThread(Runnable runnable) {
            int incrementAndGet = zzsj.incrementAndGet();
            StringBuilder sb = new StringBuilder(23);
            sb.append("measurement-");
            sb.append(incrementAndGet);
            return new zzc(runnable, sb.toString());
        }
    }

    static class zzc extends Thread {
        zzc(Runnable runnable, String str) {
            super(runnable, str);
        }

        public final void run() {
            Process.setThreadPriority(10);
            super.run();
        }
    }

    private zzk(Context context) {
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zzqx = applicationContext;
    }

    public static void zzab() {
        if (!(Thread.currentThread() instanceof zzc)) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public static zzk zzb(Context context) {
        Preconditions.checkNotNull(context);
        if (zzsa == null) {
            synchronized (zzk.class) {
                if (zzsa == null) {
                    zzsa = new zzk(context);
                }
            }
        }
        return zzsa;
    }

    /* access modifiers changed from: private */
    public static void zzb(zzg zzg) {
        Preconditions.checkNotMainThread("deliver should be called from worker thread");
        Preconditions.checkArgument(zzg.zzt(), "Measurement must be submitted");
        List<zzo> zzq = zzg.zzq();
        if (!zzq.isEmpty()) {
            HashSet hashSet = new HashSet();
            for (zzo zzo : zzq) {
                Uri zzk = zzo.zzk();
                if (!hashSet.contains(zzk)) {
                    hashSet.add(zzk);
                    zzo.zzb(zzg);
                }
            }
        }
    }

    public final Context getContext() {
        return this.zzqx;
    }

    public final <V> Future<V> zza(Callable<V> callable) {
        Preconditions.checkNotNull(callable);
        if (!(Thread.currentThread() instanceof zzc)) {
            return this.zzsd.submit(callable);
        }
        FutureTask futureTask = new FutureTask(callable);
        futureTask.run();
        return futureTask;
    }

    public final void zza(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        this.zzsd.submit(runnable);
    }

    public final void zza(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzsf = uncaughtExceptionHandler;
    }

    public final zzz zzaa() {
        DisplayMetrics displayMetrics = this.zzqx.getResources().getDisplayMetrics();
        zzz zzz = new zzz();
        zzz.setLanguage(zzdd.zza(Locale.getDefault()));
        zzz.zztv = displayMetrics.widthPixels;
        zzz.zztw = displayMetrics.heightPixels;
        return zzz;
    }

    /* access modifiers changed from: 0000 */
    public final void zze(zzg zzg) {
        if (zzg.zzw()) {
            throw new IllegalStateException("Measurement prototype can't be submitted");
        } else if (zzg.zzt()) {
            throw new IllegalStateException("Measurement can only be submitted once");
        } else {
            zzg zzo = zzg.zzo();
            zzo.zzu();
            this.zzsd.execute(new zzl(this, zzo));
        }
    }

    public final zzu zzz() {
        if (this.zzse == null) {
            synchronized (this) {
                if (this.zzse == null) {
                    zzu zzu = new zzu();
                    PackageManager packageManager = this.zzqx.getPackageManager();
                    String packageName = this.zzqx.getPackageName();
                    zzu.setAppId(packageName);
                    zzu.setAppInstallerId(packageManager.getInstallerPackageName(packageName));
                    String str = null;
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(this.zzqx.getPackageName(), 0);
                        if (packageInfo != null) {
                            CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                            if (!TextUtils.isEmpty(applicationLabel)) {
                                packageName = applicationLabel.toString();
                            }
                            str = packageInfo.versionName;
                        }
                    } catch (NameNotFoundException unused) {
                        String str2 = "GAv4";
                        String str3 = "Error retrieving package info: appName set to ";
                        String valueOf = String.valueOf(packageName);
                        Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                    }
                    zzu.setAppName(packageName);
                    zzu.setAppVersion(str);
                    this.zzse = zzu;
                }
            }
        }
        return this.zzse;
    }
}
