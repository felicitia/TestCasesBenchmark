package com.etsy.etsyapi.api.member;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_ConversationsGetSpec extends C$AutoValue_ConversationsGetSpec {
    private static final ClassLoader CL = AutoValue_ConversationsGetSpec.class.getClassLoader();
    public static final Creator<AutoValue_ConversationsGetSpec> CREATOR = new Creator<AutoValue_ConversationsGetSpec>() {
        public AutoValue_ConversationsGetSpec createFromParcel(Parcel parcel) {
            return new AutoValue_ConversationsGetSpec(parcel);
        }

        public AutoValue_ConversationsGetSpec[] newArray(int i) {
            return new AutoValue_ConversationsGetSpec[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public AutoValue_ConversationsGetSpec(Integer num, Boolean bool, Boolean bool2, Integer num2, Integer num3, Boolean bool3, Boolean bool4, String str) {
        super(num, bool, bool2, num2, num3, bool3, bool4, str);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(since());
        parcel.writeValue(is_visible());
        parcel.writeValue(in_inbox());
        parcel.writeValue(limit());
        parcel.writeValue(offset());
        parcel.writeValue(include_read());
        parcel.writeValue(friendly_timestamp());
        parcel.writeValue(tag_identifier());
    }

    private AutoValue_ConversationsGetSpec(Parcel parcel) {
        this((Integer) parcel.readValue(CL), (Boolean) parcel.readValue(CL), (Boolean) parcel.readValue(CL), (Integer) parcel.readValue(CL), (Integer) parcel.readValue(CL), (Boolean) parcel.readValue(CL), (Boolean) parcel.readValue(CL), (String) parcel.readValue(CL));
    }
}
