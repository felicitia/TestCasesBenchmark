package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.b.a.a;

public final class SdkModule_ProvideTelephonyManagerFactory implements b<TelephonyManager> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;
    private final a<Context> c;

    public SdkModule_ProvideTelephonyManagerFactory(SdkModule sdkModule, a<Context> aVar) {
        if (a || sdkModule != null) {
            this.b = sdkModule;
            if (a || aVar != null) {
                this.c = aVar;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static b<TelephonyManager> create(SdkModule sdkModule, a<Context> aVar) {
        return new SdkModule_ProvideTelephonyManagerFactory(sdkModule, aVar);
    }

    public TelephonyManager get() {
        return (TelephonyManager) d.a(this.b.provideTelephonyManager((Context) this.c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
