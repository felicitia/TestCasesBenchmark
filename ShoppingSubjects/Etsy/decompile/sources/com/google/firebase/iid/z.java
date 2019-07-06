package com.google.firebase.iid;

import com.google.android.gms.tasks.g;

final /* synthetic */ class z implements Runnable {
    private final FirebaseInstanceId a;
    private final String b;
    private final String c;
    private final g d;
    private final String e;

    z(FirebaseInstanceId firebaseInstanceId, String str, String str2, g gVar, String str3) {
        this.a = firebaseInstanceId;
        this.b = str;
        this.c = str2;
        this.d = gVar;
        this.e = str3;
    }

    public final void run() {
        this.a.a(this.b, this.c, this.d, this.e);
    }
}
