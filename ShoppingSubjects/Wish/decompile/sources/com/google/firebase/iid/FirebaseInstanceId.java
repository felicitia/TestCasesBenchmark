package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Looper;
import android.support.annotation.Keep;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FirebaseInstanceId {
    static final Executor zzah = zzn.zzba;
    private static final long zzai = TimeUnit.HOURS.toSeconds(8);
    private static zzaq zzaj;
    private static final Executor zzak = Executors.newCachedThreadPool();
    private static ScheduledThreadPoolExecutor zzal;
    private static final Executor zzam;
    private final FirebaseApp zzan;
    private final zzah zzao;
    private IRpc zzap;
    private final zzak zzaq;
    private final zzau zzar;
    private boolean zzas;
    private boolean zzat;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue());
        zzam = threadPoolExecutor;
    }

    FirebaseInstanceId(FirebaseApp firebaseApp) {
        this(firebaseApp, new zzah(firebaseApp.getApplicationContext()));
    }

    private FirebaseInstanceId(FirebaseApp firebaseApp, zzah zzah2) {
        this.zzaq = new zzak();
        this.zzas = false;
        if (zzah.zza(firebaseApp) == null) {
            throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
        }
        synchronized (FirebaseInstanceId.class) {
            if (zzaj == null) {
                zzaj = new zzaq(firebaseApp.getApplicationContext());
            }
        }
        this.zzan = firebaseApp;
        this.zzao = zzah2;
        if (this.zzap == null) {
            IRpc iRpc = (IRpc) firebaseApp.get(IRpc.class);
            if (iRpc == null) {
                iRpc = new zzo(firebaseApp, zzah2, zzam);
            }
            this.zzap = iRpc;
        }
        this.zzap = this.zzap;
        this.zzar = new zzau(zzaj);
        this.zzat = zzl();
        if (zzn()) {
            zzd();
        }
    }

    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    @Keep
    public static synchronized FirebaseInstanceId getInstance(FirebaseApp firebaseApp) {
        FirebaseInstanceId firebaseInstanceId;
        synchronized (FirebaseInstanceId.class) {
            firebaseInstanceId = (FirebaseInstanceId) firebaseApp.get(FirebaseInstanceId.class);
        }
        return firebaseInstanceId;
    }

    private final synchronized void startSync() {
        if (!this.zzas) {
            zza(0);
        }
    }

    private final <T> T zza(Task<T> task) throws IOException {
        try {
            return Tasks.await(task, 30000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException) {
                if ("INSTANCE_ID_RESET".equals(cause.getMessage())) {
                    zzj();
                }
                throw ((IOException) cause);
            } else if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else {
                throw new IOException(e);
            }
        } catch (InterruptedException | TimeoutException unused) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
    }

    static void zza(Runnable runnable, long j) {
        synchronized (FirebaseInstanceId.class) {
            if (zzal == null) {
                zzal = new ScheduledThreadPoolExecutor(1);
            }
            zzal.schedule(runnable, j, TimeUnit.SECONDS);
        }
    }

    private static String zzd(String str) {
        return (str.isEmpty() || str.equalsIgnoreCase(AppMeasurement.FCM_ORIGIN) || str.equalsIgnoreCase("gcm")) ? "*" : str;
    }

    private final void zzd() {
        zzar zzg = zzg();
        if (zzg == null || zzg.zzj(this.zzao.zzx()) || this.zzar.zzaj()) {
            startSync();
        }
    }

    private static String zzf() {
        return zzah.zza(zzaj.zzg("").getKeyPair());
    }

    static boolean zzi() {
        return Log.isLoggable("FirebaseInstanceId", 3) || (VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseInstanceId", 3));
    }

    private final boolean zzl() {
        Context applicationContext = this.zzan.getApplicationContext();
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.google.firebase.messaging", 0);
        if (sharedPreferences.contains("auto_init")) {
            return sharedPreferences.getBoolean("auto_init", true);
        }
        try {
            PackageManager packageManager = applicationContext.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128);
                if (!(applicationInfo == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey("firebase_messaging_auto_init_enabled"))) {
                    return applicationInfo.metaData.getBoolean("firebase_messaging_auto_init_enabled");
                }
            }
        } catch (NameNotFoundException unused) {
        }
        return zzm();
    }

    private final boolean zzm() {
        try {
            Class.forName("com.google.firebase.messaging.FirebaseMessaging");
            return true;
        } catch (ClassNotFoundException unused) {
            Context applicationContext = this.zzan.getApplicationContext();
            Intent intent = new Intent("com.google.firebase.MESSAGING_EVENT");
            intent.setPackage(applicationContext.getPackageName());
            ResolveInfo resolveService = applicationContext.getPackageManager().resolveService(intent, 0);
            return (resolveService == null || resolveService.serviceInfo == null) ? false : true;
        }
    }

    public String getId() {
        zzd();
        return zzf();
    }

    public String getToken() {
        zzar zzg = zzg();
        if (zzg == null || zzg.zzj(this.zzao.zzx())) {
            startSync();
        }
        if (zzg != null) {
            return zzg.zzcz;
        }
        return null;
    }

    public String getToken(String str, String str2) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        String zzd = zzd(str2);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        Executor executor = zzak;
        zzk zzk = new zzk(this, str, str2, taskCompletionSource, zzd);
        executor.execute(zzk);
        return (String) zza(taskCompletionSource.getTask());
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Task zza(String str, String str2, String str3) {
        return this.zzap.getToken(str, str2, str3);
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void zza(long j) {
        zzas zzas2 = new zzas(this, this.zzao, this.zzar, Math.min(Math.max(30, j << 1), zzai));
        zza(zzas2, j);
        this.zzas = true;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(String str, String str2, TaskCompletionSource taskCompletionSource, Task task) {
        if (task.isSuccessful()) {
            String str3 = (String) task.getResult();
            zzaj.zza("", str, str2, str3, this.zzao.zzx());
            taskCompletionSource.setResult(str3);
            return;
        }
        taskCompletionSource.setException(task.getException());
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(String str, String str2, TaskCompletionSource taskCompletionSource, String str3) {
        zzar zzc = zzaj.zzc("", str, str2);
        if (zzc == null || zzc.zzj(this.zzao.zzx())) {
            this.zzaq.zza(str, str3, new zzl(this, zzf(), str, str3)).addOnCompleteListener(zzak, new zzm(this, str, str3, taskCompletionSource));
            return;
        }
        taskCompletionSource.setResult(zzc.zzcz);
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void zza(boolean z) {
        this.zzas = z;
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(String str) throws IOException {
        zzar zzg = zzg();
        if (zzg == null || zzg.zzj(this.zzao.zzx())) {
            throw new IOException("token not available");
        }
        zza(this.zzap.subscribeToTopic(zzf(), zzg.zzcz, str));
    }

    /* access modifiers changed from: 0000 */
    public final void zzc(String str) throws IOException {
        zzar zzg = zzg();
        if (zzg == null || zzg.zzj(this.zzao.zzx())) {
            throw new IOException("token not available");
        }
        zza(this.zzap.unsubscribeFromTopic(zzf(), zzg.zzcz, str));
    }

    /* access modifiers changed from: 0000 */
    public final FirebaseApp zze() {
        return this.zzan;
    }

    /* access modifiers changed from: 0000 */
    public final zzar zzg() {
        return zzaj.zzc("", zzah.zza(this.zzan), "*");
    }

    /* access modifiers changed from: 0000 */
    public final String zzh() throws IOException {
        return getToken(zzah.zza(this.zzan), "*");
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void zzj() {
        zzaj.zzaf();
        if (zzn()) {
            startSync();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzk() {
        zzaj.zzh("");
        startSync();
    }

    public final synchronized boolean zzn() {
        return this.zzat;
    }
}
