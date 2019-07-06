package com.onfido.android.sdk.capture.common.di;

import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.barcode.BarcodeDetector;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.b.a.a;

public final class SdkModule_ProvideBarcodeDetectorFactory implements b<BarcodeDetector> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;
    private final a<ImageUtils> c;

    public SdkModule_ProvideBarcodeDetectorFactory(SdkModule sdkModule, a<ImageUtils> aVar) {
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

    public static b<BarcodeDetector> create(SdkModule sdkModule, a<ImageUtils> aVar) {
        return new SdkModule_ProvideBarcodeDetectorFactory(sdkModule, aVar);
    }

    public BarcodeDetector get() {
        return (BarcodeDetector) d.a(this.b.provideBarcodeDetector((ImageUtils) this.c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
