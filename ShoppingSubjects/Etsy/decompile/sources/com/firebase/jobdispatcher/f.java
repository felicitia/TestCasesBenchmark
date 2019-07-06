package com.firebase.jobdispatcher;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/* compiled from: GooglePlayDriver */
public final class f implements c {
    private final r a;
    private final Context b;
    private final PendingIntent c;
    private final h d;
    private final boolean e = true;

    public boolean b() {
        return true;
    }

    public f(Context context) {
        this.b = context;
        this.c = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
        this.d = new h();
        this.a = new b(context);
    }

    public int a(@NonNull k kVar) {
        GooglePlayReceiver.onSchedule(kVar);
        this.b.sendBroadcast(a((o) kVar));
        return 0;
    }

    public int a(@NonNull String str) {
        this.b.sendBroadcast(b(str));
        return 0;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public Intent b(@NonNull String str) {
        Intent c2 = c("CANCEL_TASK");
        c2.putExtra("tag", str);
        c2.putExtra("component", new ComponentName(this.b, c()));
        return c2;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public Class<GooglePlayReceiver> c() {
        return GooglePlayReceiver.class;
    }

    @NonNull
    public r a() {
        return this.a;
    }

    @NonNull
    private Intent a(o oVar) {
        Intent c2 = c("SCHEDULE_TASK");
        c2.putExtras(this.d.a(oVar, c2.getExtras()));
        return c2;
    }

    @NonNull
    private Intent c(String str) {
        Intent intent = new Intent("com.google.android.gms.gcm.ACTION_SCHEDULE");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("scheduler_action", str);
        intent.putExtra("app", this.c);
        intent.putExtra("source", 8);
        intent.putExtra("source_version", 1);
        return intent;
    }
}
