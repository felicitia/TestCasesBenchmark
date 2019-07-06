package com.threatmetrix.TrustDefender.internal;

import java.net.InetAddress;
import java.net.UnknownHostException;

class S implements Runnable {

    /* renamed from: do reason: not valid java name */
    private static final String f526do = TL.m331if(S.class);

    /* renamed from: if reason: not valid java name */
    private final String f527if;

    S(String str) {
        this.f527if = str;
    }

    public void run() {
        try {
            TL.m338new(f526do, "Starting DNS lookup");
            InetAddress.getByName(this.f527if);
            TL.m338new(f526do, "DNS lookup complete");
        } catch (UnknownHostException unused) {
            TL.m338new(f526do, "Failed DNS lookup");
        }
    }
}
