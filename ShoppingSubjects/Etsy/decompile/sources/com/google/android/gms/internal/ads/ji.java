package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;

@bu
@TargetApi(17)
public final class ji {
    private static ji b;
    String a;

    private ji() {
    }

    public static ji a() {
        if (b == null) {
            b = new ji();
        }
        return b;
    }
}
