package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class ShareHashtag implements ShareModel {
    public static final Creator<ShareHashtag> CREATOR = new Creator<ShareHashtag>() {
        /* renamed from: a */
        public ShareHashtag createFromParcel(Parcel parcel) {
            return new ShareHashtag(parcel);
        }

        /* renamed from: a */
        public ShareHashtag[] newArray(int i) {
            return new ShareHashtag[i];
        }
    };
    private final String hashtag;

    public static class a {
        /* access modifiers changed from: private */
        public String a;

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a a(ShareHashtag shareHashtag) {
            return shareHashtag == null ? this : a(shareHashtag.getHashtag());
        }

        /* access modifiers changed from: 0000 */
        public a a(Parcel parcel) {
            return a((ShareHashtag) parcel.readParcelable(ShareHashtag.class.getClassLoader()));
        }

        public ShareHashtag a() {
            return new ShareHashtag(this);
        }
    }

    public int describeContents() {
        return 0;
    }

    private ShareHashtag(a aVar) {
        this.hashtag = aVar.a;
    }

    ShareHashtag(Parcel parcel) {
        this.hashtag = parcel.readString();
    }

    public String getHashtag() {
        return this.hashtag;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.hashtag);
    }
}
