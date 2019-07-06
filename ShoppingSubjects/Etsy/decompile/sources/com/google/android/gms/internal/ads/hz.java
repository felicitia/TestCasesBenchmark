package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class hz implements OnClickListener {
    private final hx a;
    private final int b;
    private final int c;
    private final int d;

    hz(hx hxVar, int i, int i2, int i3) {
        this.a = hxVar;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.a(this.b, this.c, this.d, dialogInterface, i);
    }
}
