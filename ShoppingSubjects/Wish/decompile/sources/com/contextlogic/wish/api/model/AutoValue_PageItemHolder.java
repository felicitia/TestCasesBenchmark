package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_PageItemHolder extends C$AutoValue_PageItemHolder {
    public static final Creator<AutoValue_PageItemHolder> CREATOR = new Creator<AutoValue_PageItemHolder>() {
        public AutoValue_PageItemHolder createFromParcel(Parcel parcel) {
            return new AutoValue_PageItemHolder(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? parcel.readString() : null);
        }

        public AutoValue_PageItemHolder[] newArray(int i) {
            return new AutoValue_PageItemHolder[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    AutoValue_PageItemHolder(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getTitle());
        parcel.writeString(getBody());
        parcel.writeString(getImgUrl());
        if (getAction() == null) {
            parcel.writeInt(1);
            return;
        }
        parcel.writeInt(0);
        parcel.writeString(getAction());
    }
}
