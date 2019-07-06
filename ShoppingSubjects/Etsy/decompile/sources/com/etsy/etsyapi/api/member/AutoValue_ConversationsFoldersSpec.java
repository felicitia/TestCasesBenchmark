package com.etsy.etsyapi.api.member;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_ConversationsFoldersSpec extends C$AutoValue_ConversationsFoldersSpec {
    private static final ClassLoader CL = AutoValue_ConversationsFoldersSpec.class.getClassLoader();
    public static final Creator<AutoValue_ConversationsFoldersSpec> CREATOR = new Creator<AutoValue_ConversationsFoldersSpec>() {
        public AutoValue_ConversationsFoldersSpec createFromParcel(Parcel parcel) {
            return new AutoValue_ConversationsFoldersSpec(parcel);
        }

        public AutoValue_ConversationsFoldersSpec[] newArray(int i) {
            return new AutoValue_ConversationsFoldersSpec[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
    }

    public AutoValue_ConversationsFoldersSpec() {
    }

    private AutoValue_ConversationsFoldersSpec(Parcel parcel) {
        this();
    }
}
