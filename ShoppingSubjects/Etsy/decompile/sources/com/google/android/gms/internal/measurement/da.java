package com.google.android.gms.internal.measurement;

import com.google.android.gms.measurement.AppMeasurement.ConditionalUserProperty;

final class da implements Runnable {
    private final /* synthetic */ ConditionalUserProperty a;
    private final /* synthetic */ cs b;

    da(cs csVar, ConditionalUserProperty conditionalUserProperty) {
        this.b = csVar;
        this.a = conditionalUserProperty;
    }

    public final void run() {
        this.b.e(this.a);
    }
}
