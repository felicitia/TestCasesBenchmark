package com.facebook.share.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.model.ShareContent;

public class ShareFeedContent extends ShareContent<ShareFeedContent, a> {
    public static final Creator<ShareFeedContent> CREATOR = new Creator<ShareFeedContent>() {
        /* renamed from: a */
        public ShareFeedContent createFromParcel(Parcel parcel) {
            return new ShareFeedContent(parcel);
        }

        /* renamed from: a */
        public ShareFeedContent[] newArray(int i) {
            return new ShareFeedContent[i];
        }
    };
    private final String link;
    private final String linkCaption;
    private final String linkDescription;
    private final String linkName;
    private final String mediaSource;
    private final String picture;
    private final String toId;

    public static final class a extends com.facebook.share.model.ShareContent.a<ShareFeedContent, a> {
        /* access modifiers changed from: private */
        public String a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d;
        /* access modifiers changed from: private */
        public String e;
        /* access modifiers changed from: private */
        public String f;
        /* access modifiers changed from: private */
        public String g;

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a b(String str) {
            this.b = str;
            return this;
        }

        public a c(String str) {
            this.c = str;
            return this;
        }

        public a d(String str) {
            this.d = str;
            return this;
        }

        public a e(String str) {
            this.e = str;
            return this;
        }

        public a f(String str) {
            this.f = str;
            return this;
        }

        public a g(String str) {
            this.g = str;
            return this;
        }

        public a a(ShareFeedContent shareFeedContent) {
            if (shareFeedContent == null) {
                return this;
            }
            return ((a) super.a(shareFeedContent)).a(shareFeedContent.getToId()).b(shareFeedContent.getLink()).c(shareFeedContent.getLinkName()).d(shareFeedContent.getLinkCaption()).e(shareFeedContent.getLinkDescription()).f(shareFeedContent.getPicture()).g(shareFeedContent.getMediaSource());
        }
    }

    public int describeContents() {
        return 0;
    }

    private ShareFeedContent(a aVar) {
        super((com.facebook.share.model.ShareContent.a) aVar);
        this.toId = aVar.a;
        this.link = aVar.b;
        this.linkName = aVar.c;
        this.linkCaption = aVar.d;
        this.linkDescription = aVar.e;
        this.picture = aVar.f;
        this.mediaSource = aVar.g;
    }

    ShareFeedContent(Parcel parcel) {
        super(parcel);
        this.toId = parcel.readString();
        this.link = parcel.readString();
        this.linkName = parcel.readString();
        this.linkCaption = parcel.readString();
        this.linkDescription = parcel.readString();
        this.picture = parcel.readString();
        this.mediaSource = parcel.readString();
    }

    public String getToId() {
        return this.toId;
    }

    public String getLink() {
        return this.link;
    }

    public String getLinkName() {
        return this.linkName;
    }

    public String getLinkCaption() {
        return this.linkCaption;
    }

    public String getLinkDescription() {
        return this.linkDescription;
    }

    public String getPicture() {
        return this.picture;
    }

    public String getMediaSource() {
        return this.mediaSource;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.toId);
        parcel.writeString(this.link);
        parcel.writeString(this.linkName);
        parcel.writeString(this.linkCaption);
        parcel.writeString(this.linkDescription);
        parcel.writeString(this.picture);
        parcel.writeString(this.mediaSource);
    }
}
