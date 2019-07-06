package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.b.a.a;

public final class SdkModule_ProvideRuntimePermissionsManagerFactory implements b<RuntimePermissionsManager> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;
    private final a<Context> c;

    public SdkModule_ProvideRuntimePermissionsManagerFactory(SdkModule sdkModule, a<Context> aVar) {
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

    public static b<RuntimePermissionsManager> create(SdkModule sdkModule, a<Context> aVar) {
        return new SdkModule_ProvideRuntimePermissionsManagerFactory(sdkModule, aVar);
    }

    public RuntimePermissionsManager get() {
        return (RuntimePermissionsManager) d.a(this.b.provideRuntimePermissionsManager((Context) this.c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
