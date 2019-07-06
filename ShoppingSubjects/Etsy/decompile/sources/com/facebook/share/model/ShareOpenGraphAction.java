package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public final class ShareOpenGraphAction extends ShareOpenGraphValueContainer<ShareOpenGraphAction, a> {
    public static final Creator<ShareOpenGraphAction> CREATOR = new Creator<ShareOpenGraphAction>() {
        /* renamed from: a */
        public ShareOpenGraphAction createFromParcel(Parcel parcel) {
            return new ShareOpenGraphAction(parcel);
        }

        /* renamed from: a */
        public ShareOpenGraphAction[] newArray(int i) {
            return new ShareOpenGraphAction[i];
        }
    };

    public static final class a extends com.facebook.share.model.ShareOpenGraphValueContainer.a<ShareOpenGraphAction, a> {
        public a a(String str) {
            a("og:type", str);
            return this;
        }

        public ShareOpenGraphAction a() {
            return new ShareOpenGraphAction(this);
        }

        public a a(ShareOpenGraphAction shareOpenGraphAction) {
            if (shareOpenGraphAction == null) {
                return this;
            }
            return ((a) super.a(shareOpenGraphAction)).a(shareOpenGraphAction.getActionType());
        }

        /* access modifiers changed from: 0000 */
        public a a(Parcel parcel) {
            return a((ShareOpenGraphAction) parcel.readParcelable(ShareOpenGraphAction.class.getClassLoader()));
        }
    }

    private ShareOpenGraphAction(a aVar) {
        super((com.facebook.share.model.ShareOpenGraphValueContainer.a<P, E>) aVar);
    }

    ShareOpenGraphAction(Parcel parcel) {
        super(parcel);
    }

    @Nullable
    public String getActionType() {
        return getString("og:type");
    }
}
