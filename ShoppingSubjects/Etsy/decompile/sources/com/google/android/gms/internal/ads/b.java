package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class b implements OnClickListener {
    private final /* synthetic */ asd a;

    b(asd asd) {
        this.a = asd;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.a("Operation denied by user.");
    }
}
