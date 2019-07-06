package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class ShareMessengerGenericTemplateElement implements ShareModel {
    public static final Creator<ShareMessengerGenericTemplateElement> CREATOR = new Creator<ShareMessengerGenericTemplateElement>() {
        /* renamed from: a */
        public ShareMessengerGenericTemplateElement createFromParcel(Parcel parcel) {
            return new ShareMessengerGenericTemplateElement(parcel);
        }

        /* renamed from: a */
        public ShareMessengerGenericTemplateElement[] newArray(int i) {
            return new ShareMessengerGenericTemplateElement[i];
        }
    };
    /* access modifiers changed from: private */
    public final ShareMessengerActionButton button;
    /* access modifiers changed from: private */
    public final ShareMessengerActionButton defaultAction;
    /* access modifiers changed from: private */
    public final Uri imageUrl;
    /* access modifiers changed from: private */
    public final String subtitle;
    /* access modifiers changed from: private */
    public final String title;

    public static class a {
        /* access modifiers changed from: private */
        public String a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public Uri c;
        /* access modifiers changed from: private */
        public ShareMessengerActionButton d;
        /* access modifiers changed from: private */
        public ShareMessengerActionButton e;
    }

    public int describeContents() {
        return 0;
    }

    private ShareMessengerGenericTemplateElement(a aVar) {
        this.title = aVar.a;
        this.subtitle = aVar.b;
        this.imageUrl = aVar.c;
        this.defaultAction = aVar.d;
        this.button = aVar.e;
    }

    ShareMessengerGenericTemplateElement(Parcel parcel) {
        this.title = parcel.readString();
        this.subtitle = parcel.readString();
        this.imageUrl = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.defaultAction = (ShareMessengerActionButton) parcel.readParcelable(ShareMessengerActionButton.class.getClassLoader());
        this.button = (ShareMessengerActionButton) parcel.readParcelable(ShareMessengerActionButton.class.getClassLoader());
    }

    public String getTitle() {
        return this.title;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public Uri getImageUrl() {
        return this.imageUrl;
    }

    public ShareMessengerActionButton getDefaultAction() {
        return this.defaultAction;
    }

    public ShareMessengerActionButton getButton() {
        return this.button;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.subtitle);
        parcel.writeParcelable(this.imageUrl, i);
        parcel.writeParcelable(this.defaultAction, i);
        parcel.writeParcelable(this.button, i);
    }
}
