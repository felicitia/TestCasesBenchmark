package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SharePhotoContent extends ShareContent<SharePhotoContent, a> {
    public static final Creator<SharePhotoContent> CREATOR = new Creator<SharePhotoContent>() {
        /* renamed from: a */
        public SharePhotoContent createFromParcel(Parcel parcel) {
            return new SharePhotoContent(parcel);
        }

        /* renamed from: a */
        public SharePhotoContent[] newArray(int i) {
            return new SharePhotoContent[i];
        }
    };
    private final List<SharePhoto> photos;

    public static class a extends com.facebook.share.model.ShareContent.a<SharePhotoContent, a> {
        /* access modifiers changed from: private */
        public final List<SharePhoto> a = new ArrayList();

        public a a(@Nullable SharePhoto sharePhoto) {
            if (sharePhoto != null) {
                this.a.add(new com.facebook.share.model.SharePhoto.a().a(sharePhoto).c());
            }
            return this;
        }

        public a b(@Nullable List<SharePhoto> list) {
            if (list != null) {
                for (SharePhoto a2 : list) {
                    a(a2);
                }
            }
            return this;
        }

        public SharePhotoContent a() {
            return new SharePhotoContent(this);
        }

        public a a(SharePhotoContent sharePhotoContent) {
            if (sharePhotoContent == null) {
                return this;
            }
            return ((a) super.a(sharePhotoContent)).b(sharePhotoContent.getPhotos());
        }

        public a c(@Nullable List<SharePhoto> list) {
            this.a.clear();
            b(list);
            return this;
        }
    }

    public int describeContents() {
        return 0;
    }

    private SharePhotoContent(a aVar) {
        super((com.facebook.share.model.ShareContent.a) aVar);
        this.photos = Collections.unmodifiableList(aVar.a);
    }

    SharePhotoContent(Parcel parcel) {
        super(parcel);
        this.photos = Collections.unmodifiableList(com.facebook.share.model.SharePhoto.a.c(parcel));
    }

    @Nullable
    public List<SharePhoto> getPhotos() {
        return this.photos;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        com.facebook.share.model.SharePhoto.a.a(parcel, i, this.photos);
    }
}
