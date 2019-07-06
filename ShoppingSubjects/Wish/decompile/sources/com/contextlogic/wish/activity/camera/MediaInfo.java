package com.contextlogic.wish.activity.camera;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class MediaInfo implements Parcelable {
    public static final Creator<MediaInfo> CREATOR = new Creator<MediaInfo>() {
        public MediaInfo createFromParcel(Parcel parcel) {
            return new MediaInfo(parcel);
        }

        public MediaInfo[] newArray(int i) {
            return new MediaInfo[i];
        }
    };
    private int mType;
    private Uri mUri;

    public int describeContents() {
        return 0;
    }

    public MediaInfo(int i, Uri uri) {
        this.mType = i;
        this.mUri = uri;
    }

    private MediaInfo(Parcel parcel) {
        this.mType = parcel.readInt();
        this.mUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
    }

    public int getType() {
        return this.mType;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mType);
        parcel.writeParcelable(this.mUri, 0);
    }
}
