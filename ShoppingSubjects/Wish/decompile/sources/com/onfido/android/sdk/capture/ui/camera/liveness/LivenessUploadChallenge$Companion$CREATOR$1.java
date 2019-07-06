package com.onfido.android.sdk.capture.ui.camera.liveness;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import kotlin.jvm.internal.Intrinsics;

public final class LivenessUploadChallenge$Companion$CREATOR$1 implements Creator<LivenessUploadChallenge> {
    LivenessUploadChallenge$Companion$CREATOR$1() {
    }

    public LivenessUploadChallenge createFromParcel(Parcel parcel) {
        Intrinsics.checkParameterIsNotNull(parcel, "source");
        return new LivenessUploadChallenge(parcel);
    }

    public LivenessUploadChallenge[] newArray(int i) {
        return new LivenessUploadChallenge[i];
    }
}
