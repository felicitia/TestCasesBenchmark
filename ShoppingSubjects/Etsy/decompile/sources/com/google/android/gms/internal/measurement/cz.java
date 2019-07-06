package com.google.android.gms.internal.measurement;

import com.google.android.gms.measurement.AppMeasurement.ConditionalUserProperty;

final class cz implements Runnable {
    private final /* synthetic */ ConditionalUserProperty a;
    private final /* synthetic */ cs b;

    cz(cs csVar, ConditionalUserProperty conditionalUserProperty) {
        this.b = csVar;
        this.a = conditionalUserProperty;
    }

    public final void run() {
        this.b.d(this.a);
    }
}
