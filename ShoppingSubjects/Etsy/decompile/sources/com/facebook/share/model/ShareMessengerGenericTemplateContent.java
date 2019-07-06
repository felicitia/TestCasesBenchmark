package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class ShareMessengerGenericTemplateContent extends ShareContent<ShareMessengerGenericTemplateContent, a> {
    public static final Creator<ShareMessengerGenericTemplateContent> CREATOR = new Creator<ShareMessengerGenericTemplateContent>() {
        /* renamed from: a */
        public ShareMessengerGenericTemplateContent createFromParcel(Parcel parcel) {
            return new ShareMessengerGenericTemplateContent(parcel);
        }

        /* renamed from: a */
        public ShareMessengerGenericTemplateContent[] newArray(int i) {
            return new ShareMessengerGenericTemplateContent[i];
        }
    };
    private final ShareMessengerGenericTemplateElement genericTemplateElement;
    private final ImageAspectRatio imageAspectRatio;
    private final boolean isSharable;

    public enum ImageAspectRatio {
        HORIZONTAL,
        SQUARE
    }

    public static class a extends com.facebook.share.model.ShareContent.a<ShareMessengerGenericTemplateContent, a> {
        /* access modifiers changed from: private */
        public boolean a;
        /* access modifiers changed from: private */
        public ImageAspectRatio b;
        /* access modifiers changed from: private */
        public ShareMessengerGenericTemplateElement c;

        public a a(boolean z) {
            this.a = z;
            return this;
        }

        public a a(ImageAspectRatio imageAspectRatio) {
            this.b = imageAspectRatio;
            return this;
        }

        public a a(ShareMessengerGenericTemplateElement shareMessengerGenericTemplateElement) {
            this.c = shareMessengerGenericTemplateElement;
            return this;
        }

        public a a(ShareMessengerGenericTemplateContent shareMessengerGenericTemplateContent) {
            if (shareMessengerGenericTemplateContent == null) {
                return this;
            }
            return ((a) super.a(shareMessengerGenericTemplateContent)).a(shareMessengerGenericTemplateContent.getIsSharable()).a(shareMessengerGenericTemplateContent.getImageAspectRatio()).a(shareMessengerGenericTemplateContent.getGenericTemplateElement());
        }
    }

    public int describeContents() {
        return 0;
    }

    protected ShareMessengerGenericTemplateContent(a aVar) {
        super((com.facebook.share.model.ShareContent.a) aVar);
        this.isSharable = aVar.a;
        this.imageAspectRatio = aVar.b;
        this.genericTemplateElement = aVar.c;
    }

    ShareMessengerGenericTemplateContent(Parcel parcel) {
        super(parcel);
        this.isSharable = parcel.readByte() != 0;
        this.imageAspectRatio = (ImageAspectRatio) parcel.readSerializable();
        this.genericTemplateElement = (ShareMessengerGenericTemplateElement) parcel.readParcelable(ShareMessengerGenericTemplateElement.class.getClassLoader());
    }

    public boolean getIsSharable() {
        return this.isSharable;
    }

    public ImageAspectRatio getImageAspectRatio() {
        return this.imageAspectRatio;
    }

    public ShareMessengerGenericTemplateElement getGenericTemplateElement() {
        return this.genericTemplateElement;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeByte(this.isSharable ? (byte) 1 : 0);
        parcel.writeSerializable(this.imageAspectRatio);
        parcel.writeParcelable(this.genericTemplateElement, i);
    }
}
