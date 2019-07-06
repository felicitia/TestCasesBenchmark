package com.paypal.android.sdk.onetouch.core.metadata;

import android.content.Context;
import java.util.Map;

public class MetadataIdProviderImpl implements MetadataIdProvider {
    private h a = h.h();

    public void flush() {
        this.a.b();
    }

    public String generatePairingId(String str) {
        return this.a.b(str);
    }

    public String init(Context context, String str, Map<String, Object> map) {
        return this.a.a(context, str, m.MSDK, "3.1.4", map);
    }
}
