package com.google.android.gms.internal.measurement;

import android.content.BroadcastReceiver.PendingResult;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import com.google.android.gms.common.internal.Preconditions;

public final class bl {
    private final bo a;

    public bl(bo boVar) {
        Preconditions.checkNotNull(boVar);
        this.a = boVar;
    }

    public static boolean a(Context context) {
        Preconditions.checkNotNull(context);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            ActivityInfo receiverInfo = packageManager.getReceiverInfo(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementReceiver"), 0);
            if (receiverInfo != null && receiverInfo.enabled) {
                return true;
            }
            return false;
        } catch (NameNotFoundException unused) {
        }
    }

    @MainThread
    public final void a(Context context, Intent intent) {
        bu a2 = bu.a(context, null, null);
        aq r = a2.r();
        if (intent == null) {
            r.i().a("Receiver called with null intent");
            return;
        }
        a2.u();
        String action = intent.getAction();
        r.w().a("Local receiver got", action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            className.setAction("com.google.android.gms.measurement.UPLOAD");
            r.w().a("Starting wakeful intent.");
            this.a.doStartService(context, className);
            return;
        }
        if ("com.android.vending.INSTALL_REFERRER".equals(action)) {
            try {
                a2.q().a((Runnable) new bm(this, a2, r));
            } catch (Exception e) {
                r.i().a("Install Referrer Reporter encountered a problem", e);
            }
            PendingResult doGoAsync = this.a.doGoAsync();
            String stringExtra = intent.getStringExtra("referrer");
            if (stringExtra == null) {
                r.w().a("Install referrer extras are null");
                if (doGoAsync != null) {
                    doGoAsync.finish();
                }
                return;
            }
            r.k().a("Install referrer extras are", stringExtra);
            if (!stringExtra.contains("?")) {
                String str = "?";
                String valueOf = String.valueOf(stringExtra);
                stringExtra = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
            }
            Bundle a3 = a2.k().a(Uri.parse(stringExtra));
            if (a3 == null) {
                r.w().a("No campaign defined in install referrer broadcast");
                if (doGoAsync != null) {
                    doGoAsync.finish();
                }
            } else {
                long longExtra = intent.getLongExtra("referrer_timestamp_seconds", 0) * 1000;
                if (longExtra == 0) {
                    r.i().a("Install referrer is missing timestamp");
                }
                bq q = a2.q();
                bn bnVar = new bn(this, a2, longExtra, a3, context, r, doGoAsync);
                q.a((Runnable) bnVar);
            }
        }
    }
}
