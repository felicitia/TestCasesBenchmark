package com.facebook.share.model;

import android.os.Bundle;
import android.os.Parcel;

public abstract class ShareMedia implements ShareModel {
    private final Bundle params;

    public int describeContents() {
        return 0;
    }

    ShareMedia(Parcel parcel) {
        this.params = parcel.readBundle();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.params);
    }
}
