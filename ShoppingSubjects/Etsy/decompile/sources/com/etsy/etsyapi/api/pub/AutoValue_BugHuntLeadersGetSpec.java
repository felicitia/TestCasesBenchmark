package com.etsy.etsyapi.api.pub;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_BugHuntLeadersGetSpec extends C$AutoValue_BugHuntLeadersGetSpec {
    private static final ClassLoader CL = AutoValue_BugHuntLeadersGetSpec.class.getClassLoader();
    public static final Creator<AutoValue_BugHuntLeadersGetSpec> CREATOR = new Creator<AutoValue_BugHuntLeadersGetSpec>() {
        public AutoValue_BugHuntLeadersGetSpec createFromParcel(Parcel parcel) {
            return new AutoValue_BugHuntLeadersGetSpec(parcel);
        }

        public AutoValue_BugHuntLeadersGetSpec[] newArray(int i) {
            return new AutoValue_BugHuntLeadersGetSpec[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
    }

    public AutoValue_BugHuntLeadersGetSpec() {
    }

    private AutoValue_BugHuntLeadersGetSpec(Parcel parcel) {
        this();
    }
}
