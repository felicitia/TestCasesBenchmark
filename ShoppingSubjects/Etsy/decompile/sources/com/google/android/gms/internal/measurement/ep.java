package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.support.annotation.WorkerThread;

final class ep extends ae {
    private final /* synthetic */ eo a;

    ep(eo eoVar, cq cqVar) {
        this.a = eoVar;
        super(cqVar);
    }

    @WorkerThread
    public final void a() {
        eo eoVar = this.a;
        eoVar.d();
        eoVar.r().w().a("Session started, time", Long.valueOf(eoVar.m().elapsedRealtime()));
        eoVar.s().m.a(false);
        eoVar.f().b("auto", "_s", new Bundle());
        eoVar.s().n.a(eoVar.m().currentTimeMillis());
    }
}
