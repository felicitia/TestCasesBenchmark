package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ShareMediaContent extends ShareContent<ShareMediaContent, a> {
    public static final Creator<ShareMediaContent> CREATOR = new Creator<ShareMediaContent>() {
        /* renamed from: a */
        public ShareMediaContent createFromParcel(Parcel parcel) {
            return new ShareMediaContent(parcel);
        }

        /* renamed from: a */
        public ShareMediaContent[] newArray(int i) {
            return new ShareMediaContent[i];
        }
    };
    private final List<ShareMedia> media;

    public static class a extends com.facebook.share.model.ShareContent.a<ShareMediaContent, a> {
        /* access modifiers changed from: private */
        public final List<ShareMedia> a = new ArrayList();

        public a a(@Nullable ShareMedia shareMedia) {
            Object obj;
            if (shareMedia != null) {
                if (shareMedia instanceof SharePhoto) {
                    obj = new com.facebook.share.model.SharePhoto.a().a((SharePhoto) shareMedia).c();
                } else if (shareMedia instanceof ShareVideo) {
                    obj = new com.facebook.share.model.ShareVideo.a().a((ShareVideo) shareMedia).a();
                } else {
                    throw new IllegalArgumentException("medium must be either a SharePhoto or ShareVideo");
                }
                this.a.add(obj);
            }
            return this;
        }

        public a b(@Nullable List<ShareMedia> list) {
            if (list != null) {
                for (ShareMedia a2 : list) {
                    a(a2);
                }
            }
            return this;
        }

        public a a(ShareMediaContent shareMediaContent) {
            if (shareMediaContent == null) {
                return this;
            }
            return ((a) super.a(shareMediaContent)).b(shareMediaContent.getMedia());
        }
    }

    public int describeContents() {
        return 0;
    }

    private ShareMediaContent(a aVar) {
        super((com.facebook.share.model.ShareContent.a) aVar);
        this.media = Collections.unmodifiableList(aVar.a);
    }

    ShareMediaContent(Parcel parcel) {
        super(parcel);
        this.media = Arrays.asList((ShareMedia[]) parcel.readParcelableArray(ShareMedia.class.getClassLoader()));
    }

    @Nullable
    public List<ShareMedia> getMedia() {
        return this.media;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelableArray((ShareMedia[]) this.media.toArray(), i);
    }
}
