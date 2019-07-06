package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;

public final class zzgf extends IOException {
    public zzgf(String str) {
        super(str);
    }

    public zzgf(String str, Exception exc) {
        super(str, exc);
    }

    static zzgf zzgi() {
        return new zzgf("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzgf zzgj() {
        return new zzgf("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzgf zzgk() {
        return new zzgf("CodedInputStream encountered a malformed varint.");
    }
}
