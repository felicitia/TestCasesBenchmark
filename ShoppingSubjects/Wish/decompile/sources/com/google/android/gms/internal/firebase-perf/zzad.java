package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public final class zzad {
    private final URL zzff;

    public zzad(URL url) {
        this.zzff = url;
    }

    public final URLConnection openConnection() throws IOException {
        return this.zzff.openConnection();
    }

    public final String toString() {
        return this.zzff.toString();
    }
}
