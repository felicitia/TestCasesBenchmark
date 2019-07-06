package com.etsy.android.messaging;

import android.os.Bundle;
import com.etsy.android.lib.models.ResponseConstants;
import io.reactivex.q;
import io.reactivex.subjects.PublishSubject;
import kotlin.jvm.internal.p;

/* compiled from: NotificationRepo.kt */
public final class h {
    private final PublishSubject<f> a;

    public h() {
        PublishSubject<f> a2 = PublishSubject.a();
        p.a((Object) a2, "PublishSubject.create()");
        this.a = a2;
    }

    public final q<f> a() {
        return this.a;
    }

    public final void a(f fVar) {
        p.b(fVar, "notificationData");
        this.a.onNext(fVar);
    }

    public final void a(Bundle bundle) {
        p.b(bundle, "extras");
        String string = bundle.getString("t");
        if (string == null) {
            string = "";
        }
        String string2 = bundle.getString(ResponseConstants.USERNAME);
        if (string2 == null) {
            string2 = "";
        }
        a(new f(string, string2));
    }
}
