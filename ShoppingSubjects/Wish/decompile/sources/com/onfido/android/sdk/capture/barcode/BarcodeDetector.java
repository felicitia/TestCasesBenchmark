package com.onfido.android.sdk.capture.barcode;

import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.d.b.b;
import kotlin.jvm.internal.Intrinsics;

public class BarcodeDetector {
    private final b a;
    private final ImageUtils b;

    public BarcodeDetector(b bVar, ImageUtils imageUtils) {
        Intrinsics.checkParameterIsNotNull(bVar, "pdF417Reader");
        Intrinsics.checkParameterIsNotNull(imageUtils, "imageUtils");
        this.a = bVar;
        this.b = imageUtils;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0093, code lost:
        r4.a.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0098, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x007e, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r0 = io.reactivex.Observable.just(java.lang.Boolean.valueOf(false));
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, "Observable.just(false)");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0080 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public io.reactivex.Observable<java.lang.Boolean> detect(com.onfido.android.sdk.capture.ui.camera.CaptureFrameData r5) {
        /*
            r4 = this;
            java.lang.String r0 = "frameData"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            com.onfido.android.sdk.capture.utils.ImageUtils r0 = r4.b
            byte[] r1 = r5.getData()
            int r2 = r5.getFrameWidth()
            int r3 = r5.getFrameHeight()
            int[] r0 = r0.getPixelsForByteArray(r1, r2, r3)
            com.onfido.d.i r1 = new com.onfido.d.i
            int r2 = r5.getFrameHeight()
            int r3 = r5.getFrameWidth()
            r1.<init>(r2, r3, r0)
            com.onfido.android.sdk.capture.ui.camera.RectData r0 = r5.getOuterRect()
            int r0 = r0.getLeft()
            com.onfido.android.sdk.capture.ui.camera.RectData r2 = r5.getOuterRect()
            int r2 = r2.getTop()
            com.onfido.android.sdk.capture.ui.camera.RectData r3 = r5.getOuterRect()
            int r3 = r3.getWidth()
            com.onfido.android.sdk.capture.ui.camera.RectData r5 = r5.getOuterRect()
            int r5 = r5.getHeight()
            com.onfido.d.g r5 = r1.a(r0, r2, r3, r5)
            com.onfido.d.c r0 = new com.onfido.d.c
            com.onfido.d.a.f r1 = new com.onfido.d.a.f
            r1.<init>(r5)
            com.onfido.d.b r1 = (com.onfido.d.b) r1
            r0.<init>(r1)
            r5 = 0
            com.onfido.d.e r1 = com.onfido.d.e.c     // Catch:{ j -> 0x0080 }
            com.onfido.d.a r2 = com.onfido.d.a.k     // Catch:{ j -> 0x0080 }
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)     // Catch:{ j -> 0x0080 }
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r2)     // Catch:{ j -> 0x0080 }
            java.util.Map r1 = kotlin.collections.MapsKt.mapOf(r1)     // Catch:{ j -> 0x0080 }
            com.onfido.d.b.b r2 = r4.a     // Catch:{ j -> 0x0080 }
            com.onfido.d.k r0 = r2.a(r0, r1)     // Catch:{ j -> 0x0080 }
            if (r0 == 0) goto L_0x006f
            r0 = 1
            goto L_0x0070
        L_0x006f:
            r0 = 0
        L_0x0070:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ j -> 0x0080 }
            io.reactivex.Observable r0 = io.reactivex.Observable.just(r0)     // Catch:{ j -> 0x0080 }
            java.lang.String r1 = "Observable.just(rawResult != null)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)     // Catch:{ j -> 0x0080 }
            goto L_0x008d
        L_0x007e:
            r5 = move-exception
            goto L_0x0093
        L_0x0080:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ all -> 0x007e }
            io.reactivex.Observable r0 = io.reactivex.Observable.just(r5)     // Catch:{ all -> 0x007e }
            java.lang.String r5 = "Observable.just(false)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r5)     // Catch:{ all -> 0x007e }
        L_0x008d:
            com.onfido.d.b.b r5 = r4.a
            r5.a()
            return r0
        L_0x0093:
            com.onfido.d.b.b r0 = r4.a
            r0.a()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.barcode.BarcodeDetector.detect(com.onfido.android.sdk.capture.ui.camera.CaptureFrameData):io.reactivex.Observable");
    }
}
