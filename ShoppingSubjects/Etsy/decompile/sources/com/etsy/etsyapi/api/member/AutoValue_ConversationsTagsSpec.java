package com.etsy.etsyapi.api.member;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_ConversationsTagsSpec extends C$AutoValue_ConversationsTagsSpec {
    private static final ClassLoader CL = AutoValue_ConversationsTagsSpec.class.getClassLoader();
    public static final Creator<AutoValue_ConversationsTagsSpec> CREATOR = new Creator<AutoValue_ConversationsTagsSpec>() {
        public AutoValue_ConversationsTagsSpec createFromParcel(Parcel parcel) {
            return new AutoValue_ConversationsTagsSpec(parcel);
        }

        public AutoValue_ConversationsTagsSpec[] newArray(int i) {
            return new AutoValue_ConversationsTagsSpec[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
    }

    public AutoValue_ConversationsTagsSpec() {
    }

    private AutoValue_ConversationsTagsSpec(Parcel parcel) {
        this();
    }
}
