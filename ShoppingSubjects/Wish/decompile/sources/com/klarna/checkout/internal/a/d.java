package com.klarna.checkout.internal.a;

import android.annotation.TargetApi;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.Network;

public interface d {
    void a();

    @TargetApi(21)
    void a(Network network, NetworkCallback networkCallback);
}
