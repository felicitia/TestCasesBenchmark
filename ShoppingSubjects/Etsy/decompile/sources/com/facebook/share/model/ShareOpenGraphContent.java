package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public final class ShareOpenGraphContent extends ShareContent<ShareOpenGraphContent, a> {
    public static final Creator<ShareOpenGraphContent> CREATOR = new Creator<ShareOpenGraphContent>() {
        /* renamed from: a */
        public ShareOpenGraphContent createFromParcel(Parcel parcel) {
            return new ShareOpenGraphContent(parcel);
        }

        /* renamed from: a */
        public ShareOpenGraphContent[] newArray(int i) {
            return new ShareOpenGraphContent[i];
        }
    };
    private final ShareOpenGraphAction action;
    private final String previewPropertyName;

    public static final class a extends com.facebook.share.model.ShareContent.a<ShareOpenGraphContent, a> {
        /* access modifiers changed from: private */
        public ShareOpenGraphAction a;
        /* access modifiers changed from: private */
        public String b;

        public a a(@Nullable ShareOpenGraphAction shareOpenGraphAction) {
            ShareOpenGraphAction shareOpenGraphAction2;
            if (shareOpenGraphAction == null) {
                shareOpenGraphAction2 = null;
            } else {
                shareOpenGraphAction2 = new com.facebook.share.model.ShareOpenGraphAction.a().a(shareOpenGraphAction).a();
            }
            this.a = shareOpenGraphAction2;
            return this;
        }

        public a a(@Nullable String str) {
            this.b = str;
            return this;
        }

        public a a(ShareOpenGraphContent shareOpenGraphContent) {
            if (shareOpenGraphContent == null) {
                return this;
            }
            return ((a) super.a(shareOpenGraphContent)).a(shareOpenGraphContent.getAction()).a(shareOpenGraphContent.getPreviewPropertyName());
        }
    }

    public int describeContents() {
        return 0;
    }

    private ShareOpenGraphContent(a aVar) {
        super((com.facebook.share.model.ShareContent.a) aVar);
        this.action = aVar.a;
        this.previewPropertyName = aVar.b;
    }

    ShareOpenGraphContent(Parcel parcel) {
        super(parcel);
        this.action = new com.facebook.share.model.ShareOpenGraphAction.a().a(parcel).a();
        this.previewPropertyName = parcel.readString();
    }

    @Nullable
    public ShareOpenGraphAction getAction() {
        return this.action;
    }

    @Nullable
    public String getPreviewPropertyName() {
        return this.previewPropertyName;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.action, 0);
        parcel.writeString(this.previewPropertyName);
    }
}
