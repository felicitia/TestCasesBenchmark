package com.google.firebase.iid;

import com.google.android.gms.tasks.f;

final /* synthetic */ class aa implements l {
    private final FirebaseInstanceId a;
    private final String b;
    private final String c;
    private final String d;

    aa(FirebaseInstanceId firebaseInstanceId, String str, String str2, String str3) {
        this.a = firebaseInstanceId;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public final f a() {
        return this.a.a(this.b, this.c, this.d);
    }
}
