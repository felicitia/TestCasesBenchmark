package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.List;

public final class bh {
    @VisibleForTesting
    volatile zzr a;
    /* access modifiers changed from: private */
    public final bu b;
    @VisibleForTesting
    private ServiceConnection c;

    bh(bu buVar) {
        this.b = buVar;
    }

    @VisibleForTesting
    private final boolean c() {
        boolean z = false;
        try {
            PackageManagerWrapper packageManager = Wrappers.packageManager(this.b.n());
            if (packageManager == null) {
                this.b.r().k().a("Failed to retrieve Package Manager to check Play Store compatibility");
                return false;
            }
            if (packageManager.getPackageInfo("com.android.vending", 128).versionCode >= 80837300) {
                z = true;
            }
            return z;
        } catch (Exception e) {
            this.b.r().k().a("Failed to retrieve Play Store version", e);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void a() {
        this.b.u();
        this.b.q().d();
        if (!c()) {
            this.b.r().k().a("Install Referrer Reporter is not available");
            this.c = null;
            return;
        }
        this.c = new bj(this);
        this.b.r().k().a("Install Referrer Reporter is initializing");
        this.b.q().d();
        Intent intent = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
        intent.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
        PackageManager packageManager = this.b.n().getPackageManager();
        if (packageManager == null) {
            this.b.r().i().a("Failed to obtain Package Manager to verify binding conditions");
            return;
        }
        List queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            this.b.r().k().a("Play Service for fetching Install Referrer is unavailable on device");
            return;
        }
        ResolveInfo resolveInfo = (ResolveInfo) queryIntentServices.get(0);
        if (resolveInfo.serviceInfo != null) {
            String str = resolveInfo.serviceInfo.packageName;
            if (resolveInfo.serviceInfo.name == null || this.c == null || !"com.android.vending".equals(str) || !c()) {
                this.b.r().k().a("Play Store missing or incompatible. Version 8.3.73 or later required");
            } else {
                try {
                    this.b.r().k().a("Install Referrer Service is", ConnectionTracker.getInstance().bindService(this.b.n(), new Intent(intent), this.c, 1) ? "available" : "not available");
                } catch (Exception e) {
                    this.b.r().h_().a("Exception occurred while binding to Install Referrer Service", e.getMessage());
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    @VisibleForTesting
    public final void a(Bundle bundle) {
        as h_;
        String str;
        this.b.q().d();
        if (bundle != null) {
            long j = bundle.getLong("install_begin_timestamp_seconds", 0) * 1000;
            if (j == 0) {
                h_ = this.b.r().h_();
                str = "Service response is missing Install Referrer install timestamp";
            } else {
                String string = bundle.getString("install_referrer");
                if (string == null || string.isEmpty()) {
                    h_ = this.b.r().h_();
                    str = "No referrer defined in install referrer response";
                } else {
                    this.b.r().w().a("InstallReferrer API result", string);
                    fg k = this.b.k();
                    String str2 = "?";
                    String valueOf = String.valueOf(string);
                    Bundle a2 = k.a(Uri.parse(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2)));
                    if (a2 == null) {
                        h_ = this.b.r().h_();
                        str = "No campaign params defined in install referrer result";
                    } else {
                        String string2 = a2.getString("medium");
                        if (string2 != null && !"(not set)".equalsIgnoreCase(string2) && !"organic".equalsIgnoreCase(string2)) {
                            long j2 = bundle.getLong("referrer_click_timestamp_seconds", 0) * 1000;
                            if (j2 == 0) {
                                h_ = this.b.r().h_();
                                str = "Install Referrer is missing click timestamp for ad campaign";
                            } else {
                                a2.putLong("click_timestamp", j2);
                            }
                        }
                        if (j == this.b.c().i.a()) {
                            h_ = this.b.r().w();
                            str = "Campaign has already been logged";
                        } else {
                            a2.putString("_cis", "referrer API");
                            this.b.c().i.a(j);
                            this.b.h().a("auto", "_cmp", a2);
                            if (this.c != null) {
                                ConnectionTracker.getInstance().unbindService(this.b.n(), this.c);
                            }
                            return;
                        }
                    }
                }
            }
            h_.a(str);
        }
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    @WorkerThread
    @VisibleForTesting
    public final Bundle b() {
        this.b.q().d();
        if (this.a == null) {
            this.b.r().i().a("Attempting to use Install Referrer Service while it is not initialized");
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("package_name", this.b.n().getPackageName());
        try {
            Bundle zza = this.a.zza(bundle);
            if (zza != null) {
                return zza;
            }
            this.b.r().h_().a("Install Referrer Service returned a null response");
            return null;
        } catch (Exception e) {
            this.b.r().h_().a("Exception occurred while retrieving the Install Referrer", e.getMessage());
            return null;
        }
    }
}
