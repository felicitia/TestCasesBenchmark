package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class ShareOpenGraphObject extends ShareOpenGraphValueContainer<ShareOpenGraphObject, a> {
    public static final Creator<ShareOpenGraphObject> CREATOR = new Creator<ShareOpenGraphObject>() {
        /* renamed from: a */
        public ShareOpenGraphObject createFromParcel(Parcel parcel) {
            return new ShareOpenGraphObject(parcel);
        }

        /* renamed from: a */
        public ShareOpenGraphObject[] newArray(int i) {
            return new ShareOpenGraphObject[i];
        }
    };

    public static final class a extends com.facebook.share.model.ShareOpenGraphValueContainer.a<ShareOpenGraphObject, a> {
    }

    private ShareOpenGraphObject(a aVar) {
        super((com.facebook.share.model.ShareOpenGraphValueContainer.a<P, E>) aVar);
    }

    ShareOpenGraphObject(Parcel parcel) {
        super(parcel);
    }
}
