package com.google.firebase.iid;

import com.google.android.gms.tasks.c;
import com.google.android.gms.tasks.f;
import com.google.android.gms.tasks.g;

final /* synthetic */ class ab implements c {
    private final FirebaseInstanceId a;
    private final String b;
    private final String c;
    private final g d;

    ab(FirebaseInstanceId firebaseInstanceId, String str, String str2, g gVar) {
        this.a = firebaseInstanceId;
        this.b = str;
        this.c = str2;
        this.d = gVar;
    }

    public final void onComplete(f fVar) {
        this.a.a(this.b, this.c, this.d, fVar);
    }
}
