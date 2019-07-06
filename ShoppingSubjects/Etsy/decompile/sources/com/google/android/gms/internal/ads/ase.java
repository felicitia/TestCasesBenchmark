package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.google.android.gms.ads.internal.ao;

final class ase implements OnClickListener {
    private final /* synthetic */ asd a;

    ase(asd asd) {
        this.a = asd;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        Intent b = this.a.b();
        ao.e();
        hd.a(this.a.b, b);
    }
}
