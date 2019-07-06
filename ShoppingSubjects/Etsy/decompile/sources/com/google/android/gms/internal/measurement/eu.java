package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import android.support.v4.app.NotificationCompat;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.util.Clock;

public final class eu extends ex {
    private final AlarmManager b = ((AlarmManager) n().getSystemService(NotificationCompat.CATEGORY_ALARM));
    private final ae c;
    private Integer d;

    protected eu(ey eyVar) {
        super(eyVar);
        this.c = new ev(this, eyVar.o(), eyVar);
    }

    @TargetApi(24)
    private final void j() {
        JobScheduler jobScheduler = (JobScheduler) n().getSystemService("jobscheduler");
        r().w().a("Cancelling job. JobID", Integer.valueOf(k()));
        jobScheduler.cancel(k());
    }

    private final int k() {
        if (this.d == null) {
            String str = "measurement";
            String valueOf = String.valueOf(n().getPackageName());
            this.d = Integer.valueOf((valueOf.length() != 0 ? str.concat(valueOf) : new String(str)).hashCode());
        }
        return this.d.intValue();
    }

    private final PendingIntent v() {
        Intent className = new Intent().setClassName(n(), "com.google.android.gms.measurement.AppMeasurementReceiver");
        className.setAction("com.google.android.gms.measurement.UPLOAD");
        return PendingIntent.getBroadcast(n(), 0, className, 0);
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    public final void a(long j) {
        I();
        u();
        if (!bl.a(n())) {
            r().v().a("Receiver not registered/enabled");
        }
        u();
        if (!fg.a(n(), false)) {
            r().v().a("Service not registered/enabled");
        }
        f();
        long elapsedRealtime = m().elapsedRealtime() + j;
        if (j < Math.max(0, ((Long) ak.F.b()).longValue()) && !this.c.b()) {
            r().w().a("Scheduling upload with DelayedRunnable");
            this.c.a(j);
        }
        u();
        if (VERSION.SDK_INT >= 24) {
            r().w().a("Scheduling upload with JobScheduler");
            JobScheduler jobScheduler = (JobScheduler) n().getSystemService("jobscheduler");
            Builder builder = new Builder(k(), new ComponentName(n(), "com.google.android.gms.measurement.AppMeasurementJobService"));
            builder.setMinimumLatency(j);
            builder.setOverrideDeadline(j << 1);
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString(ResponseConstants.ACTION, "com.google.android.gms.measurement.UPLOAD");
            builder.setExtras(persistableBundle);
            JobInfo build = builder.build();
            r().w().a("Scheduling job. JobID", Integer.valueOf(k()));
            jobScheduler.schedule(build);
            return;
        }
        r().w().a("Scheduling upload with AlarmManager");
        this.b.setInexactRepeating(2, elapsedRealtime, Math.max(((Long) ak.A.b()).longValue(), j), v());
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public final /* bridge */ /* synthetic */ void d() {
        super.d();
    }

    public final /* bridge */ /* synthetic */ z d_() {
        return super.d_();
    }

    /* access modifiers changed from: protected */
    public final boolean e() {
        this.b.cancel(v());
        if (VERSION.SDK_INT >= 24) {
            j();
        }
        return false;
    }

    public final /* bridge */ /* synthetic */ u e_() {
        return super.e_();
    }

    public final void f() {
        I();
        this.b.cancel(v());
        this.c.c();
        if (VERSION.SDK_INT >= 24) {
            j();
        }
    }

    public final /* bridge */ /* synthetic */ fe f_() {
        return super.f_();
    }

    public final /* bridge */ /* synthetic */ ag l() {
        return super.l();
    }

    public final /* bridge */ /* synthetic */ Clock m() {
        return super.m();
    }

    public final /* bridge */ /* synthetic */ Context n() {
        return super.n();
    }

    public final /* bridge */ /* synthetic */ ao o() {
        return super.o();
    }

    public final /* bridge */ /* synthetic */ fg p() {
        return super.p();
    }

    public final /* bridge */ /* synthetic */ bq q() {
        return super.q();
    }

    public final /* bridge */ /* synthetic */ aq r() {
        return super.r();
    }

    public final /* bridge */ /* synthetic */ bb s() {
        return super.s();
    }

    public final /* bridge */ /* synthetic */ w t() {
        return super.t();
    }

    public final /* bridge */ /* synthetic */ v u() {
        return super.u();
    }
}
