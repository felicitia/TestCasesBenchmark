package com.google.android.gms.internal.firebase-perf;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class zzc extends zzf {
    private String zzaa;
    private Long zzab;
    private Long zzac;
    private Long zzad;
    private Long zzae;
    private zzaq[] zzaf;
    /* access modifiers changed from: private */
    public final List<zzr> zzag;
    private BroadcastReceiver zzah;
    private zzh zzq;
    private boolean zzr;
    private long zzs;
    private boolean zzt;
    private String zzu;
    private Integer zzv;
    private Long zzw;
    private Long zzx;
    private Integer zzy;
    private Integer zzz;

    public static zzc zza(zzh zzh) {
        return new zzc(zzh);
    }

    private zzc(zzh zzh) {
        this(zzh, zze.zzg());
    }

    private zzc(zzh zzh, zze zze) {
        super(zze);
        this.zzr = false;
        this.zzs = -1;
        this.zzt = false;
        this.zzah = new zzd(this);
        this.zzq = zzh;
        zzl();
        this.zzag = new ArrayList();
    }

    public final zzc zza(String str) {
        if (str != null) {
            this.zzu = zzae.zzb(zzae.zzj(str), 2000);
        }
        return this;
    }

    /* access modifiers changed from: 0000 */
    public final boolean isStopped() {
        return this.zzae != null;
    }

    /* access modifiers changed from: 0000 */
    public final boolean hasStarted() {
        return this.zzab != null;
    }

    public final zzc zzb(String str) {
        if (str != null) {
            String upperCase = str.toUpperCase();
            char c = 65535;
            switch (upperCase.hashCode()) {
                case -531492226:
                    if (upperCase.equals("OPTIONS")) {
                        c = 6;
                        break;
                    }
                    break;
                case 70454:
                    if (upperCase.equals("GET")) {
                        c = 0;
                        break;
                    }
                    break;
                case 79599:
                    if (upperCase.equals("PUT")) {
                        c = 1;
                        break;
                    }
                    break;
                case 2213344:
                    if (upperCase.equals("HEAD")) {
                        c = 4;
                        break;
                    }
                    break;
                case 2461856:
                    if (upperCase.equals("POST")) {
                        c = 2;
                        break;
                    }
                    break;
                case 75900968:
                    if (upperCase.equals("PATCH")) {
                        c = 5;
                        break;
                    }
                    break;
                case 80083237:
                    if (upperCase.equals("TRACE")) {
                        c = 7;
                        break;
                    }
                    break;
                case 1669334218:
                    if (upperCase.equals("CONNECT")) {
                        c = 8;
                        break;
                    }
                    break;
                case 2012838315:
                    if (upperCase.equals("DELETE")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.zzv = Integer.valueOf(1);
                    break;
                case 1:
                    this.zzv = Integer.valueOf(2);
                    break;
                case 2:
                    this.zzv = Integer.valueOf(3);
                    break;
                case 3:
                    this.zzv = Integer.valueOf(4);
                    break;
                case 4:
                    this.zzv = Integer.valueOf(5);
                    break;
                case 5:
                    this.zzv = Integer.valueOf(6);
                    break;
                case 6:
                    this.zzv = Integer.valueOf(7);
                    break;
                case 7:
                    this.zzv = Integer.valueOf(8);
                    break;
                case 8:
                    this.zzv = Integer.valueOf(9);
                    break;
                default:
                    this.zzv = Integer.valueOf(0);
                    break;
            }
        }
        return this;
    }

    public final zzc zza(long j) {
        this.zzw = Long.valueOf(j);
        return this;
    }

    public final zzc zzb(long j) {
        this.zzx = Long.valueOf(j);
        return this;
    }

    public final zzc zza(int i) {
        this.zzz = Integer.valueOf(i);
        return this;
    }

    public final Integer zzc() {
        return this.zzz;
    }

    public final zzc zzc(String str) {
        if (str != null) {
            this.zzaa = str;
        }
        return this;
    }

    public final zzc zzc(long j) {
        this.zzab = Long.valueOf(j);
        this.zzag.add(zzw.zzae().zzaf());
        zzw.zzae();
        LocalBroadcastManager.getInstance(zzw.zzag()).registerReceiver(this.zzah, new IntentFilter("SessionIdUpdate"));
        return this;
    }

    public final zzc zzd(long j) {
        this.zzac = Long.valueOf(j);
        return this;
    }

    public final zzc zze(long j) {
        this.zzs = j;
        this.zzad = Long.valueOf(j);
        return this;
    }

    public final zzc zzd() {
        this.zzy = Integer.valueOf(1);
        return this;
    }

    public final long zze() {
        return this.zzs;
    }

    public final zzc zzf(long j) {
        this.zzae = Long.valueOf(j);
        return this;
    }

    public final zzap zzf() {
        zzw.zzae();
        LocalBroadcastManager.getInstance(zzw.zzag()).unregisterReceiver(this.zzah);
        zzm();
        zzap zzap = new zzap();
        zzap.url = this.zzu;
        zzap.zzge = this.zzv;
        zzap.zzgf = this.zzw;
        zzap.zzgg = this.zzx;
        zzap.zzgh = this.zzy;
        zzap.zzgi = this.zzz;
        zzap.zzgj = this.zzaa;
        zzap.zzgk = this.zzab;
        zzap.zzgl = this.zzac;
        zzap.zzgm = this.zzad;
        zzap.zzgn = this.zzae;
        zzap.zzgo = this.zzaf;
        zzap.zzgp = new zzas[this.zzag.size()];
        for (int i = 0; i < this.zzag.size(); i++) {
            zzap.zzgp[i] = ((zzr) this.zzag.get(i)).zzt();
        }
        if (!this.zzr) {
            if (this.zzq != null) {
                this.zzq.zza(zzap, zzh());
            }
            this.zzr = true;
        } else if (this.zzt) {
            Log.i("FirebasePerformance", "This metric has already been queued for transmission.  Please create a new HttpMetric for each request/response");
        }
        return zzap;
    }
}
