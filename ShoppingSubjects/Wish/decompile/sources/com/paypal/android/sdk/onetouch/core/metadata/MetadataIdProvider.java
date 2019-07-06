package com.paypal.android.sdk.onetouch.core.metadata;

import android.content.Context;
import java.util.Map;

public interface MetadataIdProvider {
    void flush();

    String generatePairingId(String str);

    String init(Context context, String str, Map<String, Object> map);
}
