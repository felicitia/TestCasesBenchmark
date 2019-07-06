package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public final class ShareVideoContent extends ShareContent<ShareVideoContent, a> implements ShareModel {
    public static final Creator<ShareVideoContent> CREATOR = new Creator<ShareVideoContent>() {
        /* renamed from: a */
        public ShareVideoContent createFromParcel(Parcel parcel) {
            return new ShareVideoContent(parcel);
        }

        /* renamed from: a */
        public ShareVideoContent[] newArray(int i) {
            return new ShareVideoContent[i];
        }
    };
    private final String contentDescription;
    private final String contentTitle;
    private final SharePhoto previewPhoto;
    private final ShareVideo video;

    public static final class a extends com.facebook.share.model.ShareContent.a<ShareVideoContent, a> {
        /* access modifiers changed from: private */
        public String a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public SharePhoto c;
        /* access modifiers changed from: private */
        public ShareVideo d;

        public a a(@Nullable String str) {
            this.a = str;
            return this;
        }

        public a b(@Nullable String str) {
            this.b = str;
            return this;
        }

        public a a(@Nullable SharePhoto sharePhoto) {
            SharePhoto sharePhoto2;
            if (sharePhoto == null) {
                sharePhoto2 = null;
            } else {
                sharePhoto2 = new com.facebook.share.model.SharePhoto.a().a(sharePhoto).c();
            }
            this.c = sharePhoto2;
            return this;
        }

        public a a(@Nullable ShareVideo shareVideo) {
            if (shareVideo == null) {
                return this;
            }
            this.d = new com.facebook.share.model.ShareVideo.a().a(shareVideo).a();
            return this;
        }

        public a a(ShareVideoContent shareVideoContent) {
            if (shareVideoContent == null) {
                return this;
            }
            return ((a) super.a(shareVideoContent)).a(shareVideoContent.getContentDescription()).b(shareVideoContent.getContentTitle()).a(shareVideoContent.getPreviewPhoto()).a(shareVideoContent.getVideo());
        }
    }

    public int describeContents() {
        return 0;
    }

    private ShareVideoContent(a aVar) {
        super((com.facebook.share.model.ShareContent.a) aVar);
        this.contentDescription = aVar.a;
        this.contentTitle = aVar.b;
        this.previewPhoto = aVar.c;
        this.video = aVar.d;
    }

    ShareVideoContent(Parcel parcel) {
        super(parcel);
        this.contentDescription = parcel.readString();
        this.contentTitle = parcel.readString();
        com.facebook.share.model.SharePhoto.a b = new com.facebook.share.model.SharePhoto.a().b(parcel);
        if (b.a() == null && b.b() == null) {
            this.previewPhoto = null;
        } else {
            this.previewPhoto = b.c();
        }
        this.video = new com.facebook.share.model.ShareVideo.a().b(parcel).a();
    }

    @Nullable
    public String getContentDescription() {
        return this.contentDescription;
    }

    @Nullable
    public String getContentTitle() {
        return this.contentTitle;
    }

    @Nullable
    public SharePhoto getPreviewPhoto() {
        return this.previewPhoto;
    }

    @Nullable
    public ShareVideo getVideo() {
        return this.video;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.contentDescription);
        parcel.writeString(this.contentTitle);
        parcel.writeParcelable(this.previewPhoto, 0);
        parcel.writeParcelable(this.video, 0);
    }
}
