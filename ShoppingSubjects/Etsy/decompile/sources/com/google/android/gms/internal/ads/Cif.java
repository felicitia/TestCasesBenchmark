package com.google.android.gms.internal.ads;

import android.app.AlertDialog.Builder;
import android.content.Context;

/* renamed from: com.google.android.gms.internal.ads.if reason: invalid class name */
final class Cif implements Runnable {
    final /* synthetic */ Context a;
    private final /* synthetic */ String b;
    private final /* synthetic */ boolean c;
    private final /* synthetic */ boolean d;

    Cif(ie ieVar, Context context, String str, boolean z, boolean z2) {
        this.a = context;
        this.b = str;
        this.c = z;
        this.d = z2;
    }

    public final void run() {
        Builder builder = new Builder(this.a);
        builder.setMessage(this.b);
        builder.setTitle(this.c ? "Error" : "Info");
        if (this.d) {
            builder.setNeutralButton("Dismiss", null);
        } else {
            builder.setPositiveButton("Learn More", new ig(this));
            builder.setNegativeButton("Dismiss", null);
        }
        builder.create().show();
    }
}
