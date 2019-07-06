package com.onfido.android.sdk.capture.ui.camera.liveness;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessChallenge.MovementType;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessChallenge.Type;
import java.io.Serializable;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

public final class LivenessUploadChallenge implements Parcelable, Serializable {
    public static final Creator<LivenessUploadChallenge> CREATOR = new LivenessUploadChallenge$Companion$CREATOR$1();
    public static final Companion Companion = new Companion(null);
    private final Type a;
    private final Object b;
    private long c;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public LivenessUploadChallenge(Parcel parcel) {
        Serializable serializable;
        Intrinsics.checkParameterIsNotNull(parcel, "source");
        this.a = Type.values()[parcel.readInt()];
        switch (this.a) {
            case RECITE:
                serializable = (Serializable) parcel.createIntArray();
                break;
            case MOVEMENT:
                serializable = parcel.readSerializable();
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        Intrinsics.checkExpressionValueIsNotNull(serializable, "when (type) {\n          …dSerializable()\n        }");
        this.b = serializable;
        this.c = parcel.readLong();
    }

    public LivenessUploadChallenge(Type type, Object obj) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(obj, "query");
        this.a = type;
        this.b = obj;
    }

    public int describeContents() {
        return 0;
    }

    public final long getEndChallengeTimestamp() {
        return this.c;
    }

    public final Object getQuery() {
        return this.b;
    }

    public final Type getType() {
        return this.a;
    }

    public final void setEndChallengeTimestamp(long j) {
        this.c = j;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "dest");
        parcel.writeInt(this.a.ordinal());
        Object obj = this.b;
        if (obj instanceof int[]) {
            parcel.writeIntArray((int[]) this.b);
        } else if (obj instanceof MovementType) {
            parcel.writeSerializable((Serializable) this.b);
        }
        parcel.writeLong(this.c);
    }
}
