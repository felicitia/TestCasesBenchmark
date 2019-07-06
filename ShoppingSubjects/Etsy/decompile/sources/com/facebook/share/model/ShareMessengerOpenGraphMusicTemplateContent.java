package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class ShareMessengerOpenGraphMusicTemplateContent extends ShareContent<ShareMessengerOpenGraphMusicTemplateContent, a> {
    public static final Creator<ShareMessengerOpenGraphMusicTemplateContent> CREATOR = new Creator<ShareMessengerOpenGraphMusicTemplateContent>() {
        /* renamed from: a */
        public ShareMessengerOpenGraphMusicTemplateContent createFromParcel(Parcel parcel) {
            return new ShareMessengerOpenGraphMusicTemplateContent(parcel);
        }

        /* renamed from: a */
        public ShareMessengerOpenGraphMusicTemplateContent[] newArray(int i) {
            return new ShareMessengerOpenGraphMusicTemplateContent[i];
        }
    };
    private final ShareMessengerActionButton button;
    private final Uri url;

    public static class a extends com.facebook.share.model.ShareContent.a<ShareMessengerOpenGraphMusicTemplateContent, a> {
        /* access modifiers changed from: private */
        public Uri a;
        /* access modifiers changed from: private */
        public ShareMessengerActionButton b;

        public a b(Uri uri) {
            this.a = uri;
            return this;
        }

        public a a(ShareMessengerActionButton shareMessengerActionButton) {
            this.b = shareMessengerActionButton;
            return this;
        }

        public a a(ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent) {
            if (shareMessengerOpenGraphMusicTemplateContent == null) {
                return this;
            }
            return ((a) super.a(shareMessengerOpenGraphMusicTemplateContent)).b(shareMessengerOpenGraphMusicTemplateContent.getUrl()).a(shareMessengerOpenGraphMusicTemplateContent.getButton());
        }
    }

    public int describeContents() {
        return 0;
    }

    private ShareMessengerOpenGraphMusicTemplateContent(a aVar) {
        super((com.facebook.share.model.ShareContent.a) aVar);
        this.url = aVar.a;
        this.button = aVar.b;
    }

    ShareMessengerOpenGraphMusicTemplateContent(Parcel parcel) {
        super(parcel);
        this.url = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.button = (ShareMessengerActionButton) parcel.readParcelable(ShareMessengerActionButton.class.getClassLoader());
    }

    public Uri getUrl() {
        return this.url;
    }

    public ShareMessengerActionButton getButton() {
        return this.button;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.url, i);
        parcel.writeParcelable(this.button, i);
    }
}
