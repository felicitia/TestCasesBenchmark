package com.paypal.android.sdk.data.collector;

import android.content.Context;
import com.paypal.android.sdk.onetouch.core.metadata.MetadataIdProvider;
import com.paypal.android.sdk.onetouch.core.metadata.MetadataIdProviderImpl;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class PayPalDataCollector {
    /* access modifiers changed from: private */
    public static MetadataIdProvider sMetadataIdProvider;

    public static String getClientMetadataId(Context context) {
        return getClientMetadataId(context, null);
    }

    public static String getClientMetadataId(Context context, String str) {
        return getClientMetadataId(context, InstallationIdentifier.getInstallationGUID(context), str);
    }

    static String getClientMetadataId(Context context, String str, String str2) {
        Map map;
        if (sMetadataIdProvider != null) {
            return sMetadataIdProvider.generatePairingId(str2);
        }
        if (context == null) {
            return "";
        }
        sMetadataIdProvider = new MetadataIdProviderImpl();
        if (str2 != null) {
            map = new HashMap();
            map.put("RISK_MANAGER_PAIRING_ID", str2);
        } else {
            map = Collections.emptyMap();
        }
        String init = sMetadataIdProvider.init(context.getApplicationContext(), str, map);
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            public void run() {
                PayPalDataCollector.sMetadataIdProvider.flush();
            }
        });
        return init;
    }
}
