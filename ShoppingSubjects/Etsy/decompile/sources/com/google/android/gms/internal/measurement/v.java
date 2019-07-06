package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Looper;

public final class v {
    private final boolean a = false;

    v(Context context) {
    }

    public static boolean a() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
