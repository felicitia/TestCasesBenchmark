package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

final class akm implements Callable<Void> {
    private final /* synthetic */ Context a;

    akm(Context context) {
        this.a = context;
    }

    public final /* synthetic */ Object call() throws Exception {
        ajh.f().a(this.a);
        return null;
    }
}
