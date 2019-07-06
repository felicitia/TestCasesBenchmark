package com.stripe;

import java.net.PasswordAuthentication;
import java.net.Proxy;

public abstract class Stripe {
    private static volatile String apiBase = "https://api.stripe.com";
    public static volatile String apiKey;
    public static volatile String apiVersion;
    private static volatile Proxy connectionProxy;
    private static volatile PasswordAuthentication proxyCredential;

    public static String getApiBase() {
        return apiBase;
    }

    public static Proxy getConnectionProxy() {
        return connectionProxy;
    }

    public static PasswordAuthentication getProxyCredential() {
        return proxyCredential;
    }
}
