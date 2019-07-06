package com.paypal.android.sdk.onetouch.core.fpti;

public enum TrackingPoint {
    WalletIsPresent("checkwallet", "present"),
    WalletIsAbsent("checkwallet", "absent"),
    PreflightBrowser("preflight", "browser"),
    PreflightWallet("preflight", "wallet"),
    PreflightNone("preflight", "none"),
    SwitchToBrowser("switchaway", "browser"),
    SwitchToWallet("switchaway", "wallet"),
    Cancel("switchback", "cancel"),
    Return("switchback", "return"),
    Error("switchback", "cancel", true);
    
    private final String mC;
    private final String mD;
    private final boolean mHasError;

    private TrackingPoint(String str, String str2, boolean z) {
        this.mC = str;
        this.mD = str2;
        this.mHasError = z;
    }

    private TrackingPoint(String str, String str2) {
        this(r7, r8, str, str2, false);
    }

    public String getCd() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mC);
        sb.append(":");
        sb.append(this.mD);
        return sb.toString();
    }

    public boolean hasError() {
        return this.mHasError;
    }
}
