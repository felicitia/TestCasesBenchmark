package com.google.android.gms.ads.internal;

import android.view.View;
import android.view.View.OnClickListener;

final class az implements OnClickListener {
    private final /* synthetic */ bh a;
    private final /* synthetic */ aw b;

    az(aw awVar, bh bhVar) {
        this.b = awVar;
        this.a = bhVar;
    }

    public final void onClick(View view) {
        this.a.a();
        if (this.b.b != null) {
            this.b.b.c();
        }
    }
}
