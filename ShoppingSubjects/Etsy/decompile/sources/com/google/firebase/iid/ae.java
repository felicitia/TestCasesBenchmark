package com.google.firebase.iid;

import android.os.Bundle;
import com.google.android.gms.tasks.g;

final /* synthetic */ class ae implements Runnable {
    private final ad a;
    private final Bundle b;
    private final g c;

    ae(ad adVar, Bundle bundle, g gVar) {
        this.a = adVar;
        this.b = bundle;
        this.c = gVar;
    }

    public final void run() {
        this.a.a(this.b, this.c);
    }
}
