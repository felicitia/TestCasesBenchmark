package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.c.a.a;

public final class SdkModule_ProvideAnalyticsApiFactory implements b<a> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;
    private final com.onfido.b.a.a<Context> c;

    public SdkModule_ProvideAnalyticsApiFactory(SdkModule sdkModule, com.onfido.b.a.a<Context> aVar) {
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

    public static b<a> create(SdkModule sdkModule, com.onfido.b.a.a<Context> aVar) {
        return new SdkModule_ProvideAnalyticsApiFactory(sdkModule, aVar);
    }

    public a get() {
        return (a) d.a(this.b.provideAnalyticsApi((Context) this.c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
