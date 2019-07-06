package com.facebook.share.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.model.ShareModel;

@Deprecated
public class LikeContent implements ShareModel {
    @Deprecated
    public static final Creator<LikeContent> CREATOR = new Creator<LikeContent>() {
        /* renamed from: a */
        public LikeContent createFromParcel(Parcel parcel) {
            return new LikeContent(parcel);
        }

        /* renamed from: a */
        public LikeContent[] newArray(int i) {
            return new LikeContent[i];
        }
    };
    private final String objectId;
    private final String objectType;

    @Deprecated
    public static class a {
        /* access modifiers changed from: private */
        public String a;
        /* access modifiers changed from: private */
        public String b;

        @Deprecated
        public a a(String str) {
            this.a = str;
            return this;
        }

        @Deprecated
        public a b(String str) {
            this.b = str;
            return this;
        }

        @Deprecated
        public LikeContent a() {
            return new LikeContent(this);
        }
    }

    @Deprecated
    public int describeContents() {
        return 0;
    }

    private LikeContent(a aVar) {
        this.objectId = aVar.a;
        this.objectType = aVar.b;
    }

    @Deprecated
    LikeContent(Parcel parcel) {
        this.objectId = parcel.readString();
        this.objectType = parcel.readString();
    }

    @Deprecated
    public String getObjectId() {
        return this.objectId;
    }

    @Deprecated
    public String getObjectType() {
        return this.objectType;
    }

    @Deprecated
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.objectId);
        parcel.writeString(this.objectType);
    }
}
