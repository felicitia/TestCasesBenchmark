package com.google.firebase.iid;

import android.util.Pair;
import com.google.android.gms.tasks.a;
import com.google.android.gms.tasks.f;

final /* synthetic */ class k implements a {
    private final j a;
    private final Pair b;

    k(j jVar, Pair pair) {
        this.a = jVar;
        this.b = pair;
    }

    public final Object then(f fVar) {
        return this.a.a(this.b, fVar);
    }
}
