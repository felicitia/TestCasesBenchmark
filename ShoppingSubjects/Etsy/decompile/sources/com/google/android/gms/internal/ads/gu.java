package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class gu extends gq {
    private Context a;

    gu(Context context) {
        this.a = context;
    }

    public final void a() {
        boolean z;
        try {
            z = AdvertisingIdClient.getIsAdIdFakeForDebugLogging(this.a);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e) {
            gv.b("Fail to get isAdIdFakeForDebugLogging", e);
            z = false;
        }
        jt.a(z);
        StringBuilder sb = new StringBuilder(43);
        sb.append("Update ad debug logging enablement as ");
        sb.append(z);
        gv.e(sb.toString());
    }

    public final void c_() {
    }
}
