package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class h implements OnClickListener {
    private final /* synthetic */ f a;

    h(f fVar) {
        this.a = fVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.a("User canceled the download.");
    }
}
