package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class fy implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ le b;

    fy(fx fxVar, Context context, le leVar) {
        this.a = context;
        this.b = leVar;
    }

    public final void run() {
        try {
            this.b.b(AdvertisingIdClient.getAdvertisingIdInfo(this.a));
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e) {
            this.b.a(e);
            ka.b("Exception while getting advertising Id info", e);
        }
    }
}
