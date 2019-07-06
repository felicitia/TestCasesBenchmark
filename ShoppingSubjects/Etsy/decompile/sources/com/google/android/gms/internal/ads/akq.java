package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.text.TextUtils;

@bu
public final class akq {
    public static void a(ako ako, @Nullable akn akn) {
        if (akn.b() == null) {
            throw new IllegalArgumentException("Context can't be null. Please set up context in CsiConfiguration.");
        } else if (TextUtils.isEmpty(akn.c())) {
            throw new IllegalArgumentException("AfmaVersion can't be null or empty. Please set up afmaVersion in CsiConfiguration.");
        } else {
            ako.a(akn.b(), akn.c(), akn.a(), akn.d());
        }
    }
}
