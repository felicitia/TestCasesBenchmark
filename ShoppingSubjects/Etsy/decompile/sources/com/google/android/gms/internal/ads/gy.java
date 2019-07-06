package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.Iterator;

final class gy extends gz {
    private final /* synthetic */ Bundle a;
    private final /* synthetic */ gw b;

    gy(gw gwVar, Bundle bundle) {
        this.b = gwVar;
        this.a = bundle;
        super(null);
    }

    public final void a() {
        Iterator it = this.b.d.iterator();
        while (it.hasNext()) {
            ((ha) it.next()).a(this.a);
        }
    }
}
