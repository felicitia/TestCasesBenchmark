package com.crittercism.internal;

public enum cl {
    StrictModeDeviceId,
    StrictModeSessionId,
    NoTLSContext,
    TLSContextInit,
    NegativeLifecycleUserflowTime;
    
    private static String f;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("5.8.10".replace(".", "-"));
        sb.append("-");
        f = sb.toString();
    }

    public final String a() {
        StringBuilder sb = new StringBuilder("error ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(f);
        sb2.append(Integer.toString(ordinal()));
        sb.append(sb2.toString());
        sb.append("; Please report this to support@apteligent.com.");
        return sb.toString();
    }
}
