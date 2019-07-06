package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.util.Log;

public final class ShareLinkContent extends ShareContent<ShareLinkContent, a> {
    public static final Creator<ShareLinkContent> CREATOR = new Creator<ShareLinkContent>() {
        /* renamed from: a */
        public ShareLinkContent createFromParcel(Parcel parcel) {
            return new ShareLinkContent(parcel);
        }

        /* renamed from: a */
        public ShareLinkContent[] newArray(int i) {
            return new ShareLinkContent[i];
        }
    };
    @Deprecated
    private final String contentDescription;
    @Deprecated
    private final String contentTitle;
    @Deprecated
    private final Uri imageUrl;
    private final String quote;

    public static final class a extends com.facebook.share.model.ShareContent.a<ShareLinkContent, a> {
        static final String a = "ShareLinkContent$a";
        /* access modifiers changed from: private */
        @Deprecated
        public String b;
        /* access modifiers changed from: private */
        @Deprecated
        public String c;
        /* access modifiers changed from: private */
        @Deprecated
        public Uri d;
        /* access modifiers changed from: private */
        public String e;

        @Deprecated
        public a a(@Nullable String str) {
            Log.w(a, "This method does nothing. ContentDescription is deprecated in Graph API 2.9.");
            return this;
        }

        @Deprecated
        public a b(@Nullable String str) {
            Log.w(a, "This method does nothing. ContentTitle is deprecated in Graph API 2.9.");
            return this;
        }

        @Deprecated
        public a b(@Nullable Uri uri) {
            Log.w(a, "This method does nothing. ImageUrl is deprecated in Graph API 2.9.");
            return this;
        }

        public a c(@Nullable String str) {
            this.e = str;
            return this;
        }

        public ShareLinkContent a() {
            return new ShareLinkContent(this);
        }

        public a a(ShareLinkContent shareLinkContent) {
            if (shareLinkContent == null) {
                return this;
            }
            return ((a) super.a(shareLinkContent)).a(shareLinkContent.getContentDescription()).b(shareLinkContent.getImageUrl()).b(shareLinkContent.getContentTitle()).c(shareLinkContent.getQuote());
        }
    }

    public int describeContents() {
        return 0;
    }

    private ShareLinkContent(a aVar) {
        super((com.facebook.share.model.ShareContent.a) aVar);
        this.contentDescription = aVar.b;
        this.contentTitle = aVar.c;
        this.imageUrl = aVar.d;
        this.quote = aVar.e;
    }

    ShareLinkContent(Parcel parcel) {
        super(parcel);
        this.contentDescription = parcel.readString();
        this.contentTitle = parcel.readString();
        this.imageUrl = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.quote = parcel.readString();
    }

    @Deprecated
    public String getContentDescription() {
        return this.contentDescription;
    }

    @Nullable
    @Deprecated
    public String getContentTitle() {
        return this.contentTitle;
    }

    @Nullable
    @Deprecated
    public Uri getImageUrl() {
        return this.imageUrl;
    }

    @Nullable
    public String getQuote() {
        return this.quote;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.contentDescription);
        parcel.writeString(this.contentTitle);
        parcel.writeParcelable(this.imageUrl, 0);
        parcel.writeString(this.quote);
    }
}
