package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.net.http.SslError;

@TargetApi(14)
public class ho extends hm {
    public final String a(SslError sslError) {
        return sslError.getUrl();
    }

    public int f() {
        return 1;
    }
}
