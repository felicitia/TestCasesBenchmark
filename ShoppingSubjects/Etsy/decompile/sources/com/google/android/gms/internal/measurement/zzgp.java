package com.google.android.gms.internal.measurement;

import android.os.Binder;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public final class zzgp extends zzfb {
    /* access modifiers changed from: private */
    public final ey zzalo;
    private Boolean zzapb;
    @Nullable
    private String zzapc;

    public zzgp(ey eyVar) {
        this(eyVar, null);
    }

    private zzgp(ey eyVar, @Nullable String str) {
        Preconditions.checkNotNull(eyVar);
        this.zzalo = eyVar;
        this.zzapc = null;
    }

    @BinderThread
    private final void zzb(zzeb zzeb, boolean z) {
        Preconditions.checkNotNull(zzeb);
        zzc(zzeb.packageName, false);
        this.zzalo.h().e(zzeb.zzafa);
    }

    @BinderThread
    private final void zzc(String str, boolean z) {
        boolean z2;
        if (TextUtils.isEmpty(str)) {
            this.zzalo.r().h_().a("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        if (z) {
            try {
                if (this.zzapb == null) {
                    if (!"com.google.android.gms".equals(this.zzapc) && !UidVerifier.isGooglePlayServicesUid(this.zzalo.n(), Binder.getCallingUid())) {
                        if (!GoogleSignatureVerifier.getInstance(this.zzalo.n()).isUidGoogleSigned(Binder.getCallingUid())) {
                            z2 = false;
                            this.zzapb = Boolean.valueOf(z2);
                        }
                    }
                    z2 = true;
                    this.zzapb = Boolean.valueOf(z2);
                }
                if (this.zzapb.booleanValue()) {
                    return;
                }
            } catch (SecurityException e) {
                this.zzalo.r().h_().a("Measurement Service called with invalid calling package. appId", aq.a(str));
                throw e;
            }
        }
        if (this.zzapc == null && GooglePlayServicesUtilLight.uidHasPackageName(this.zzalo.n(), Binder.getCallingUid(), str)) {
            this.zzapc = str;
        }
        if (!str.equals(this.zzapc)) {
            throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
        }
    }

    @VisibleForTesting
    private final void zze(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (!((Boolean) ak.aa.b()).booleanValue() || !this.zzalo.q().g()) {
            this.zzalo.q().a(runnable);
        } else {
            runnable.run();
        }
    }

    @BinderThread
    public final List<zzka> zza(zzeb zzeb, boolean z) {
        zzb(zzeb, false);
        try {
            List<ff> list = (List) this.zzalo.q().a((Callable<V>) new cl<V>(this, zzeb)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (ff ffVar : list) {
                if (z || !fg.g(ffVar.c)) {
                    arrayList.add(new zzka(ffVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzalo.r().h_().a("Failed to get user attributes. appId", aq.a(zzeb.packageName), e);
            return null;
        }
    }

    @BinderThread
    public final List<zzef> zza(String str, String str2, zzeb zzeb) {
        zzb(zzeb, false);
        try {
            return (List) this.zzalo.q().a((Callable<V>) new cd<V>(this, zzeb, str, str2)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zzalo.r().h_().a("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final List<zzka> zza(String str, String str2, String str3, boolean z) {
        zzc(str, true);
        try {
            List<ff> list = (List) this.zzalo.q().a((Callable<V>) new cc<V>(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (ff ffVar : list) {
                if (z || !fg.g(ffVar.c)) {
                    arrayList.add(new zzka(ffVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzalo.r().h_().a("Failed to get user attributes. appId", aq.a(str), e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final List<zzka> zza(String str, String str2, boolean z, zzeb zzeb) {
        zzb(zzeb, false);
        try {
            List<ff> list = (List) this.zzalo.q().a((Callable<V>) new cb<V>(this, zzeb, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (ff ffVar : list) {
                if (z || !fg.g(ffVar.c)) {
                    arrayList.add(new zzka(ffVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzalo.r().h_().a("Failed to get user attributes. appId", aq.a(zzeb.packageName), e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final void zza(long j, String str, String str2, String str3) {
        cn cnVar = new cn(this, str2, str3, str, j);
        zze(cnVar);
    }

    @BinderThread
    public final void zza(zzeb zzeb) {
        zzb(zzeb, false);
        zze(new cm(this, zzeb));
    }

    @BinderThread
    public final void zza(zzef zzef, zzeb zzeb) {
        Preconditions.checkNotNull(zzef);
        Preconditions.checkNotNull(zzef.zzage);
        zzb(zzeb, false);
        zzef zzef2 = new zzef(zzef);
        zzef2.packageName = zzeb.packageName;
        zze(zzef.zzage.getValue() == null ? new bx(this, zzef2, zzeb) : new by(this, zzef2, zzeb));
    }

    @BinderThread
    public final void zza(zzex zzex, zzeb zzeb) {
        Preconditions.checkNotNull(zzex);
        zzb(zzeb, false);
        zze(new cg(this, zzex, zzeb));
    }

    @BinderThread
    public final void zza(zzex zzex, String str, String str2) {
        Preconditions.checkNotNull(zzex);
        Preconditions.checkNotEmpty(str);
        zzc(str, true);
        zze(new ch(this, zzex, str));
    }

    @BinderThread
    public final void zza(zzka zzka, zzeb zzeb) {
        Preconditions.checkNotNull(zzka);
        zzb(zzeb, false);
        zze(zzka.getValue() == null ? new cj(this, zzka, zzeb) : new ck(this, zzka, zzeb));
    }

    @BinderThread
    public final byte[] zza(zzex zzex, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzex);
        zzc(str, true);
        this.zzalo.r().v().a("Log and bundle. event", this.zzalo.g().a(zzex.name));
        long nanoTime = this.zzalo.m().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zzalo.q().b((Callable<V>) new ci<V>(this, zzex, str)).get();
            if (bArr == null) {
                this.zzalo.r().h_().a("Log and bundle returned null. appId", aq.a(str));
                bArr = new byte[0];
            }
            this.zzalo.r().v().a("Log and bundle processed. event, size, time_ms", this.zzalo.g().a(zzex.name), Integer.valueOf(bArr.length), Long.valueOf((this.zzalo.m().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.zzalo.r().h_().a("Failed to log and bundle. appId, event, error", aq.a(str), this.zzalo.g().a(zzex.name), e);
            return null;
        }
    }

    @BinderThread
    public final void zzb(zzeb zzeb) {
        zzb(zzeb, false);
        zze(new bw(this, zzeb));
    }

    @BinderThread
    public final void zzb(zzef zzef) {
        Preconditions.checkNotNull(zzef);
        Preconditions.checkNotNull(zzef.zzage);
        zzc(zzef.packageName, true);
        zzef zzef2 = new zzef(zzef);
        zze(zzef.zzage.getValue() == null ? new bz(this, zzef2) : new ca(this, zzef2));
    }

    @BinderThread
    public final String zzc(zzeb zzeb) {
        zzb(zzeb, false);
        return this.zzalo.d(zzeb);
    }

    @BinderThread
    public final void zzd(zzeb zzeb) {
        zzc(zzeb.packageName, false);
        zze(new cf(this, zzeb));
    }

    @BinderThread
    public final List<zzef> zze(String str, String str2, String str3) {
        zzc(str, true);
        try {
            return (List) this.zzalo.q().a((Callable<V>) new ce<V>(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zzalo.r().h_().a("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }
}
