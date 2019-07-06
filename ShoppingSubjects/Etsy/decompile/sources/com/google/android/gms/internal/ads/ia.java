package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class ia implements OnClickListener {
    private final hx a;
    private final String b;

    ia(hx hxVar, String str) {
        this.a = hxVar;
        this.b = str;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.a(this.b, dialogInterface, i);
    }
}
