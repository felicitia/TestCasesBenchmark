package com.onfido.android.sdk.capture.ui.camera.liveness;

import com.onfido.android.sdk.capture.ui.options.BaseOptions;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

public final class LivenessConfirmationOptions extends BaseOptions {
    private final String a;
    private final LivenessUploadChallenge[] b;

    public LivenessConfirmationOptions(String str, LivenessUploadChallenge[] livenessUploadChallengeArr) {
        Intrinsics.checkParameterIsNotNull(str, "videoFilePath");
        Intrinsics.checkParameterIsNotNull(livenessUploadChallengeArr, "livenessUploadChallenges");
        this.a = str;
        this.b = livenessUploadChallengeArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r2.b, r3.b) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationOptions
            if (r0 == 0) goto L_0x001d
            com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationOptions r3 = (com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationOptions) r3
            java.lang.String r0 = r2.a
            java.lang.String r1 = r3.a
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            com.onfido.android.sdk.capture.ui.camera.liveness.LivenessUploadChallenge[] r0 = r2.b
            com.onfido.android.sdk.capture.ui.camera.liveness.LivenessUploadChallenge[] r3 = r3.b
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r3 = 0
            return r3
        L_0x001f:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationOptions.equals(java.lang.Object):boolean");
    }

    public final LivenessUploadChallenge[] getLivenessUploadChallenges() {
        return this.b;
    }

    public final String getVideoFilePath() {
        return this.a;
    }

    public int hashCode() {
        String str = this.a;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        LivenessUploadChallenge[] livenessUploadChallengeArr = this.b;
        if (livenessUploadChallengeArr != null) {
            i = Arrays.hashCode(livenessUploadChallengeArr);
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LivenessConfirmationOptions(videoFilePath=");
        sb.append(this.a);
        sb.append(", livenessUploadChallenges=");
        sb.append(Arrays.toString(this.b));
        sb.append(")");
        return sb.toString();
    }
}
