package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class ShareMessengerMediaTemplateContent extends ShareContent<ShareMessengerMediaTemplateContent, a> {
    public static final Creator<ShareMessengerMediaTemplateContent> CREATOR = new Creator<ShareMessengerMediaTemplateContent>() {
        /* renamed from: a */
        public ShareMessengerMediaTemplateContent createFromParcel(Parcel parcel) {
            return new ShareMessengerMediaTemplateContent(parcel);
        }

        /* renamed from: a */
        public ShareMessengerMediaTemplateContent[] newArray(int i) {
            return new ShareMessengerMediaTemplateContent[i];
        }
    };
    private final String attachmentId;
    private final ShareMessengerActionButton button;
    private final MediaType mediaType;
    private final Uri mediaUrl;

    public enum MediaType {
        IMAGE,
        VIDEO
    }

    public static class a extends com.facebook.share.model.ShareContent.a<ShareMessengerMediaTemplateContent, a> {
        /* access modifiers changed from: private */
        public MediaType a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public Uri c;
        /* access modifiers changed from: private */
        public ShareMessengerActionButton d;

        public a a(MediaType mediaType) {
            this.a = mediaType;
            return this;
        }

        public a a(String str) {
            this.b = str;
            return this;
        }

        public a b(Uri uri) {
            this.c = uri;
            return this;
        }

        public a a(ShareMessengerActionButton shareMessengerActionButton) {
            this.d = shareMessengerActionButton;
            return this;
        }

        public a a(ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent) {
            if (shareMessengerMediaTemplateContent == null) {
                return this;
            }
            return ((a) super.a(shareMessengerMediaTemplateContent)).a(shareMessengerMediaTemplateContent.getMediaType()).a(shareMessengerMediaTemplateContent.getAttachmentId()).b(shareMessengerMediaTemplateContent.getMediaUrl()).a(shareMessengerMediaTemplateContent.getButton());
        }
    }

    public int describeContents() {
        return 0;
    }

    private ShareMessengerMediaTemplateContent(a aVar) {
        super((com.facebook.share.model.ShareContent.a) aVar);
        this.mediaType = aVar.a;
        this.attachmentId = aVar.b;
        this.mediaUrl = aVar.c;
        this.button = aVar.d;
    }

    ShareMessengerMediaTemplateContent(Parcel parcel) {
        super(parcel);
        this.mediaType = (MediaType) parcel.readSerializable();
        this.attachmentId = parcel.readString();
        this.mediaUrl = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.button = (ShareMessengerActionButton) parcel.readParcelable(ShareMessengerActionButton.class.getClassLoader());
    }

    public MediaType getMediaType() {
        return this.mediaType;
    }

    public String getAttachmentId() {
        return this.attachmentId;
    }

    public Uri getMediaUrl() {
        return this.mediaUrl;
    }

    public ShareMessengerActionButton getButton() {
        return this.button;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.mediaType);
        parcel.writeString(this.attachmentId);
        parcel.writeParcelable(this.mediaUrl, i);
        parcel.writeParcelable(this.button, i);
    }
}
