package com.google.android.gms.internal.firebase-perf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.perf.FirebasePerformance;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class zzh {
    @SuppressLint({"StaticFieldLeak"})
    private static zzh zzba;
    private final ThreadPoolExecutor zzbb;
    private FirebaseApp zzbc;
    private FirebasePerformance zzbd;
    private FirebaseInstanceId zzbe = null;
    private Context zzbf;
    private ClearcutLogger zzbg = null;
    private String zzbh;
    private zzam zzbi;
    private zzt zzbj = null;
    private zze zzbk = null;
    private boolean zzbl;

    public static zzh zzo() {
        if (zzba == null) {
            synchronized (zzh.class) {
                if (zzba == null) {
                    try {
                        FirebaseApp.getInstance();
                        zzh zzh = new zzh(null, null, null, null, null);
                        zzba = zzh;
                    } catch (IllegalStateException unused) {
                        return null;
                    }
                }
            }
        }
        return zzba;
    }

    private zzh(ThreadPoolExecutor threadPoolExecutor, ClearcutLogger clearcutLogger, zzt zzt, zze zze, FirebaseInstanceId firebaseInstanceId) {
        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, new LinkedBlockingQueue());
        threadPoolExecutor2.allowCoreThreadTimeOut(true);
        this.zzbb = threadPoolExecutor2;
        this.zzbb.execute(new zzi(this));
    }

    public final void zza(zzat zzat, int i) {
        try {
            byte[] zzb = zzgg.zzb(zzat);
            zzat zzat2 = new zzat();
            zzgg.zza(zzat2, zzb);
            this.zzbb.execute(new zzj(this, zzat2, i));
        } catch (zzgf e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 35);
            sb.append("Clone TraceMetric throws exception ");
            sb.append(valueOf);
            Log.w("FirebasePerformance", sb.toString());
        }
    }

    public final void zza(zzap zzap, int i) {
        try {
            byte[] zzb = zzgg.zzb(zzap);
            zzap zzap2 = new zzap();
            zzgg.zza(zzap2, zzb);
            this.zzbb.execute(new zzk(this, zzap2, i));
        } catch (zzgf e) {
            Log.w("FirebasePerformance", "Clone NetworkRequestMetric throws exception", e);
        }
    }

    public final void zzb(boolean z) {
        this.zzbb.execute(new zzl(this, z));
    }

    /* access modifiers changed from: private */
    public final void zzp() {
        this.zzbc = FirebaseApp.getInstance();
        this.zzbd = FirebasePerformance.getInstance();
        this.zzbf = this.zzbc.getApplicationContext();
        this.zzbh = this.zzbc.getOptions().getApplicationId();
        this.zzbi = new zzam();
        this.zzbi.zzfx = this.zzbh;
        this.zzbi.zzfy = zzq();
        this.zzbi.zzfz = new zzal();
        this.zzbi.zzfz.packageName = this.zzbf.getPackageName();
        this.zzbi.zzfz.zzfw = "1.0.0.206222422";
        this.zzbi.zzfz.versionName = zzd(this.zzbf);
        if (this.zzbg == null) {
            try {
                this.zzbg = ClearcutLogger.anonymousLogger(this.zzbf, "FIREPERF");
            } catch (SecurityException unused) {
                Log.i("FirebasePerformance", "Caught SecurityException while init ClearcutLogger.");
                this.zzbg = null;
            }
        }
        if (this.zzbj == null) {
            zzt zzt = new zzt(this.zzbf, this.zzbh, 100, 500);
            this.zzbj = zzt;
        }
        if (this.zzbk == null) {
            this.zzbk = zze.zzg();
        }
        this.zzbl = zzae.zzf(this.zzbf);
    }

    private final void zza(zzar zzar) {
        if (this.zzbg != null && this.zzbd.isPerformanceCollectionEnabled()) {
            if (this.zzbi.zzfy == null || this.zzbi.zzfy.isEmpty()) {
                this.zzbi.zzfy = zzq();
            }
            boolean z = false;
            if (this.zzbi.zzfy == null || this.zzbi.zzfy.isEmpty()) {
                Log.w("FirebasePerformance", "App Instance ID is null or empty, dropping the log.");
                return;
            }
            Context context = this.zzbf;
            ArrayList arrayList = new ArrayList();
            if (zzar.zzgr != null) {
                arrayList.add(new zzn(zzar.zzgr));
            }
            if (zzar.zzgs != null) {
                arrayList.add(new zzm(zzar.zzgs, context));
            }
            if (zzar.zzbi != null) {
                arrayList.add(new zzg(zzar.zzbi));
            }
            if (!arrayList.isEmpty()) {
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (true) {
                    if (i >= size) {
                        z = true;
                        break;
                    }
                    Object obj = arrayList2.get(i);
                    i++;
                    if (!((zzo) obj).zzn()) {
                        break;
                    }
                }
            } else {
                Log.d("FirebasePerformance", "No validators found for PerfMetric.");
            }
            if (!z) {
                Log.i("FirebasePerformance", "Unable to process an HTTP request/response due to missing or invalid values. See earlier log statements for additional information on the specific invalid/missing values.");
            } else if (!this.zzbj.zzb(zzar)) {
                if (zzar.zzgs != null) {
                    this.zzbk.zza(zzy.NETWORK_TRACE_EVENT_RATE_LIMITED.toString(), 1);
                } else if (zzar.zzgr != null) {
                    this.zzbk.zza(zzy.TRACE_EVENT_RATE_LIMITED.toString(), 1);
                }
                if (this.zzbl) {
                    if (zzar.zzgs != null) {
                        String str = "FirebasePerformance";
                        String str2 = "Rate Limited NetworkRequestMetric - ";
                        String valueOf = String.valueOf(zzar.zzgs.url);
                        Log.i(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                        return;
                    }
                    String str3 = "FirebasePerformance";
                    String str4 = "Rate Limited TraceMetric - ";
                    String valueOf2 = String.valueOf(zzar.zzgr.name);
                    Log.i(str3, valueOf2.length() != 0 ? str4.concat(valueOf2) : new String(str4));
                }
            } else {
                try {
                    this.zzbg.newEvent(zzgg.zzb(zzar)).log();
                } catch (SecurityException unused) {
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zzb(zzat zzat, int i) {
        if (this.zzbd.isPerformanceCollectionEnabled()) {
            int i2 = 0;
            if (this.zzbl) {
                Log.i("FirebasePerformance", String.format("Logging TraceMetric - %s %dms", new Object[]{zzat.name, Long.valueOf((zzat.zzgz == null ? 0 : zzat.zzgz.longValue()) / 1000)}));
            }
            if (!zzt.zzu()) {
                zzat.zzgp = null;
                if (this.zzbl) {
                    Log.i("FirebasePerformance", String.format("Sessions are disabled. Dropping all sessions from trace - %s", new Object[]{zzat.name}));
                }
            }
            zzar zzar = new zzar();
            zzar.zzbi = this.zzbi;
            zzar.zzbi.zzgb = Integer.valueOf(i);
            zzar.zzgr = zzat;
            Map attributes = this.zzbd.getAttributes();
            if (!attributes.isEmpty()) {
                zzar.zzbi.zzgc = new zzan[attributes.size()];
                for (String str : attributes.keySet()) {
                    String str2 = (String) attributes.get(str);
                    zzan zzan = new zzan();
                    zzan.key = str;
                    zzan.value = str2;
                    int i3 = i2 + 1;
                    zzar.zzbi.zzgc[i2] = zzan;
                    i2 = i3;
                }
            }
            zza(zzar);
        }
    }

    /* access modifiers changed from: private */
    public final void zzb(zzap zzap, int i) {
        long j;
        if (this.zzbd.isPerformanceCollectionEnabled()) {
            if (this.zzbl) {
                long j2 = 0;
                if (zzap.zzgn == null) {
                    j = 0;
                } else {
                    j = zzap.zzgn.longValue();
                }
                if (zzap.zzgg != null) {
                    j2 = zzap.zzgg.longValue();
                }
                Log.i("FirebasePerformance", String.format("Logging NetworkRequestMetric - %s %db %dms,", new Object[]{zzap.url, Long.valueOf(j2), Long.valueOf(j / 1000)}));
            }
            if (!zzt.zzu()) {
                zzap.zzgp = null;
                if (this.zzbl) {
                    Log.i("FirebasePerformance", String.format("Sessions are disabled. Dropping all sessions from network request - %s", new Object[]{zzap.url}));
                }
            }
            zzar zzar = new zzar();
            zzar.zzbi = this.zzbi;
            zzar.zzbi.zzgb = Integer.valueOf(i);
            zzar.zzgs = zzap;
            zza(zzar);
        }
    }

    public final void zzc(boolean z) {
        this.zzbj.zzb(z);
    }

    private static String zzd(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException unused) {
            return "";
        }
    }

    private final String zzq() {
        if (!this.zzbd.isPerformanceCollectionEnabled()) {
            return null;
        }
        if (this.zzbe == null) {
            this.zzbe = FirebaseInstanceId.getInstance();
        }
        if (this.zzbe != null) {
            return this.zzbe.getId();
        }
        return null;
    }
}
