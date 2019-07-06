package com.paypal.android.sdk.onetouch.core.metadata;

import java.io.Closeable;
import java.io.IOException;

public final class af {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
