package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public final class acj extends aci {
    private acj(Context context, String str, boolean z) {
        super(context, str, z);
    }

    public static acj a(String str, Context context, boolean z) {
        a(context, z);
        return new acj(context, str, z);
    }

    /* access modifiers changed from: protected */
    public final List<Callable<Void>> a(acy acy, vy vyVar, ta taVar) {
        if (acy.c() == null || !this.r) {
            return super.a(acy, vyVar, taVar);
        }
        int n = acy.n();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(super.a(acy, vyVar, taVar));
        adr adr = new adr(acy, "1QeH3Cf7T53ayw17Ebbo9YTdhU+IFx0X5nCtC5gZQym4uicOVPXxYWmMK9k58i8n", "bHJRpFJ+2R5LAbYQUBDMyfYpLd1oiGixlpIqMJOBQPY=", vyVar, n, 24);
        arrayList.add(adr);
        return arrayList;
    }
}
