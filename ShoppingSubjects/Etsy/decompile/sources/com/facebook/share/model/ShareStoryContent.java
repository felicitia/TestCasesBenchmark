package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ShareStoryContent extends ShareContent<ShareStoryContent, a> {
    public static final Creator<ShareStoryContent> CREATOR = new Creator<ShareStoryContent>() {
        /* renamed from: a */
        public ShareStoryContent createFromParcel(Parcel parcel) {
            return new ShareStoryContent(parcel);
        }

        /* renamed from: a */
        public ShareStoryContent[] newArray(int i) {
            return new ShareStoryContent[i];
        }
    };
    private final String mAttributionLink;
    private final ShareMedia mBackgroundAsset;
    @Nullable
    private final List<String> mBackgroundColorList;
    private final SharePhoto mStickerAsset;

    public static final class a extends com.facebook.share.model.ShareContent.a<ShareStoryContent, a> {
        static final String a = "ShareStoryContent$a";
        /* access modifiers changed from: private */
        public ShareMedia b;
        /* access modifiers changed from: private */
        public SharePhoto c;
        /* access modifiers changed from: private */
        public List<String> d;
        /* access modifiers changed from: private */
        public String e;

        public a a(ShareMedia shareMedia) {
            this.b = shareMedia;
            return this;
        }

        public a a(SharePhoto sharePhoto) {
            this.c = sharePhoto;
            return this;
        }

        public a b(List<String> list) {
            this.d = list;
            return this;
        }

        public a a(String str) {
            this.e = str;
            return this;
        }

        public a a(ShareStoryContent shareStoryContent) {
            if (shareStoryContent == null) {
                return this;
            }
            return ((a) super.a(shareStoryContent)).a(shareStoryContent.getBackgroundAsset()).a(shareStoryContent.getStickerAsset()).b(shareStoryContent.getBackgroundColorList()).a(shareStoryContent.getAttributionLink());
        }
    }

    public int describeContents() {
        return 0;
    }

    private ShareStoryContent(a aVar) {
        super((com.facebook.share.model.ShareContent.a) aVar);
        this.mBackgroundAsset = aVar.b;
        this.mStickerAsset = aVar.c;
        this.mBackgroundColorList = aVar.d;
        this.mAttributionLink = aVar.e;
    }

    ShareStoryContent(Parcel parcel) {
        super(parcel);
        this.mBackgroundAsset = (ShareMedia) parcel.readParcelable(ShareMedia.class.getClassLoader());
        this.mStickerAsset = (SharePhoto) parcel.readParcelable(SharePhoto.class.getClassLoader());
        this.mBackgroundColorList = readUnmodifiableStringList(parcel);
        this.mAttributionLink = parcel.readString();
    }

    public ShareMedia getBackgroundAsset() {
        return this.mBackgroundAsset;
    }

    public SharePhoto getStickerAsset() {
        return this.mStickerAsset;
    }

    @Nullable
    public List<String> getBackgroundColorList() {
        if (this.mBackgroundColorList == null) {
            return null;
        }
        return Collections.unmodifiableList(this.mBackgroundColorList);
    }

    public String getAttributionLink() {
        return this.mAttributionLink;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.mBackgroundAsset, 0);
        parcel.writeParcelable(this.mStickerAsset, 0);
        parcel.writeStringList(this.mBackgroundColorList);
        parcel.writeString(this.mAttributionLink);
    }

    @Nullable
    private List<String> readUnmodifiableStringList(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        if (arrayList.isEmpty()) {
            return null;
        }
        return Collections.unmodifiableList(arrayList);
    }
}
