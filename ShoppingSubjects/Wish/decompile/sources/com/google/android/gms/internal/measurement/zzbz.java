package com.google.android.gms.internal.measurement;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbz extends zzar {
    private boolean zzyf;
    private boolean zzyg;
    private final AlarmManager zzyh = ((AlarmManager) getContext().getSystemService("alarm"));
    private Integer zzyi;

    protected zzbz(zzat zzat) {
        super(zzat);
    }

    private final int getJobId() {
        if (this.zzyi == null) {
            String str = "analytics";
            String valueOf = String.valueOf(getContext().getPackageName());
            this.zzyi = Integer.valueOf((valueOf.length() != 0 ? str.concat(valueOf) : new String(str)).hashCode());
        }
        return this.zzyi.intValue();
    }

    private final PendingIntent zzek() {
        Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        intent.setComponent(new ComponentName(getContext(), "com.google.android.gms.analytics.AnalyticsReceiver"));
        return PendingIntent.getBroadcast(getContext(), 0, intent, 0);
    }

    public final void cancel() {
        this.zzyg = false;
        this.zzyh.cancel(zzek());
        if (VERSION.SDK_INT >= 24) {
            JobScheduler jobScheduler = (JobScheduler) getContext().getSystemService("jobscheduler");
            zza("Cancelling job. JobID", Integer.valueOf(getJobId()));
            jobScheduler.cancel(getJobId());
        }
    }

    /* access modifiers changed from: protected */
    public final void zzac() {
        try {
            cancel();
            if (zzbu.zzdw() > 0) {
                ActivityInfo receiverInfo = getContext().getPackageManager().getReceiverInfo(new ComponentName(getContext(), "com.google.android.gms.analytics.AnalyticsReceiver"), 2);
                if (receiverInfo != null && receiverInfo.enabled) {
                    zzq("Receiver registered for local dispatch.");
                    this.zzyf = true;
                }
            }
        } catch (NameNotFoundException unused) {
        }
    }

    public final boolean zzef() {
        return this.zzyg;
    }

    public final boolean zzei() {
        return this.zzyf;
    }

    public final void zzej() {
        zzch();
        Preconditions.checkState(this.zzyf, "Receiver not registered");
        long zzdw = zzbu.zzdw();
        if (zzdw > 0) {
            cancel();
            long elapsedRealtime = zzbt().elapsedRealtime() + zzdw;
            this.zzyg = true;
            if (VERSION.SDK_INT >= 24) {
                zzq("Scheduling upload with JobScheduler");
                JobScheduler jobScheduler = (JobScheduler) getContext().getSystemService("jobscheduler");
                Builder builder = new Builder(getJobId(), new ComponentName(getContext(), "com.google.android.gms.analytics.AnalyticsJobService"));
                builder.setMinimumLatency(zzdw);
                builder.setOverrideDeadline(zzdw << 1);
                PersistableBundle persistableBundle = new PersistableBundle();
                persistableBundle.putString("action", "com.google.android.gms.analytics.ANALYTICS_DISPATCH");
                builder.setExtras(persistableBundle);
                JobInfo build = builder.build();
                zza("Scheduling job. JobID", Integer.valueOf(getJobId()));
                jobScheduler.schedule(build);
                return;
            }
            zzq("Scheduling upload with AlarmManager");
            this.zzyh.setInexactRepeating(2, elapsedRealtime, zzdw, zzek());
        }
    }
}
