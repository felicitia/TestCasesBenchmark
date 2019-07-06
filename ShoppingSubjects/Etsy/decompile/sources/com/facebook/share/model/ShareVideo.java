package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.share.model.ShareMedia.Type;

public final class ShareVideo extends ShareMedia {
    public static final Creator<ShareVideo> CREATOR = new Creator<ShareVideo>() {
        /* renamed from: a */
        public ShareVideo createFromParcel(Parcel parcel) {
            return new ShareVideo(parcel);
        }

        /* renamed from: a */
        public ShareVideo[] newArray(int i) {
            return new ShareVideo[i];
        }
    };
    private final Uri localUrl;

    public static final class a extends com.facebook.share.model.ShareMedia.a<ShareVideo, a> {
        /* access modifiers changed from: private */
        public Uri a;

        public a a(@Nullable Uri uri) {
            this.a = uri;
            return this;
        }

        public ShareVideo a() {
            return new ShareVideo(this);
        }

        public a a(ShareVideo shareVideo) {
            if (shareVideo == null) {
                return this;
            }
            return ((a) super.a(shareVideo)).a(shareVideo.getLocalUrl());
        }

        /* access modifiers changed from: 0000 */
        public a b(Parcel parcel) {
            return a((ShareVideo) parcel.readParcelable(ShareVideo.class.getClassLoader()));
        }
    }

    public int describeContents() {
        return 0;
    }

    private ShareVideo(a aVar) {
        super((com.facebook.share.model.ShareMedia.a) aVar);
        this.localUrl = aVar.a;
    }

    ShareVideo(Parcel parcel) {
        super(parcel);
        this.localUrl = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
    }

    @Nullable
    public Uri getLocalUrl() {
        return this.localUrl;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.localUrl, 0);
    }

    public Type getMediaType() {
        return Type.VIDEO;
    }
}
