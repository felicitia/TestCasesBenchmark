package com.google.android.gms.internal.measurement;

final class ev extends ae {
    private final /* synthetic */ ey a;
    private final /* synthetic */ eu b;

    ev(eu euVar, cq cqVar, ey eyVar) {
        this.b = euVar;
        this.a = eyVar;
        super(cqVar);
    }

    public final void a() {
        this.b.f();
        this.b.r().w().a("Starting upload from DelayedRunnable");
        this.a.j();
    }
}
