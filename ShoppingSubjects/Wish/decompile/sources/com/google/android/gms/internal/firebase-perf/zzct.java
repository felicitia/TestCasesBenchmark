package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;

public class zzct extends IOException {
    private zzdt zznb = null;

    public zzct(String str) {
        super(str);
    }

    public final zzct zzg(zzdt zzdt) {
        this.zznb = zzdt;
        return this;
    }

    static zzct zzdx() {
        return new zzct("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzct zzdy() {
        return new zzct("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzct zzdz() {
        return new zzct("CodedInputStream encountered a malformed varint.");
    }

    static zzct zzea() {
        return new zzct("Protocol message end-group tag did not match expected tag.");
    }

    static zzcu zzeb() {
        return new zzcu("Protocol message tag had invalid wire type.");
    }

    static zzct zzec() {
        return new zzct("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    static zzct zzed() {
        return new zzct("Failed to parse the message.");
    }

    static zzct zzee() {
        return new zzct("Protocol message had invalid UTF-8.");
    }
}
