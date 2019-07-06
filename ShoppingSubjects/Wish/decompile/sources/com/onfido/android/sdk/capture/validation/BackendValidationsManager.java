package com.onfido.android.sdk.capture.validation;

import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.api.client.ValidationLevel;
import com.onfido.api.client.ValidationType;
import com.onfido.api.client.data.DocSide;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

public class BackendValidationsManager {
    public static final Companion Companion = new Companion(null);
    private int a;
    private final NativeDetector b;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BackendValidationsManager(NativeDetector nativeDetector) {
        Intrinsics.checkParameterIsNotNull(nativeDetector, "nativeDetector");
        this.b = nativeDetector;
    }

    public final int getRejectedUploads() {
        return this.a;
    }

    public Map<ValidationType, ValidationLevel> getRequiredDocumentValidations(DocSide docSide) {
        Intrinsics.checkParameterIsNotNull(docSide, "documentSide");
        HashMap hashMap = new HashMap();
        if (!this.b.hasNativeLibrary()) {
            hashMap.put(ValidationType.GLARE, ValidationLevel.WARNING);
        }
        if (this.a < 1 && Intrinsics.areEqual(docSide, DocSide.FRONT)) {
            hashMap.put(ValidationType.DOCUMENT, ValidationLevel.ERROR);
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x001a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasGlareWarning(com.onfido.api.client.data.DocumentUpload r2) {
        /*
            r1 = this;
            java.lang.String r0 = "documentUpload"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            com.onfido.api.client.data.DocumentValidationWarningsBundle r2 = r2.getWarningsBundle()
            r0 = 1
            if (r2 == 0) goto L_0x0017
            com.onfido.api.client.data.DocumentValidationWarning r2 = r2.getDetectGlare()
            if (r2 == 0) goto L_0x0017
            boolean r2 = r2.isValid()
            goto L_0x0018
        L_0x0017:
            r2 = 1
        L_0x0018:
            if (r2 != 0) goto L_0x001b
            return r0
        L_0x001b:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.validation.BackendValidationsManager.hasGlareWarning(com.onfido.api.client.data.DocumentUpload):boolean");
    }

    public void onValidationError() {
        this.a++;
    }

    public final void setRejectedUploads(int i) {
        this.a = i;
    }

    public boolean shouldPerformFaceValidation() {
        return this.a < 1;
    }
}
