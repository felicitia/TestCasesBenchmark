package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public final class ShareMessengerURLActionButton extends ShareMessengerActionButton {
    public static final Creator<ShareMessengerURLActionButton> CREATOR = new Creator<ShareMessengerURLActionButton>() {
        /* renamed from: a */
        public ShareMessengerURLActionButton createFromParcel(Parcel parcel) {
            return new ShareMessengerURLActionButton(parcel);
        }

        /* renamed from: a */
        public ShareMessengerURLActionButton[] newArray(int i) {
            return new ShareMessengerURLActionButton[i];
        }
    };
    private final Uri fallbackUrl;
    private final boolean isMessengerExtensionURL;
    private final boolean shouldHideWebviewShareButton;
    private final Uri url;
    private final WebviewHeightRatio webviewHeightRatio;

    public enum WebviewHeightRatio {
        WebviewHeightRatioFull,
        WebviewHeightRatioTall,
        WebviewHeightRatioCompact
    }

    public static final class a extends com.facebook.share.model.ShareMessengerActionButton.a<ShareMessengerURLActionButton, a> {
        /* access modifiers changed from: private */
        public Uri a;
        /* access modifiers changed from: private */
        public boolean b;
        /* access modifiers changed from: private */
        public Uri c;
        /* access modifiers changed from: private */
        public WebviewHeightRatio d;
        /* access modifiers changed from: private */
        public boolean e;
    }

    private ShareMessengerURLActionButton(a aVar) {
        super((com.facebook.share.model.ShareMessengerActionButton.a) aVar);
        this.url = aVar.a;
        this.isMessengerExtensionURL = aVar.b;
        this.fallbackUrl = aVar.c;
        this.webviewHeightRatio = aVar.d;
        this.shouldHideWebviewShareButton = aVar.e;
    }

    ShareMessengerURLActionButton(Parcel parcel) {
        super(parcel);
        this.url = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        boolean z = false;
        this.isMessengerExtensionURL = parcel.readByte() != 0;
        this.fallbackUrl = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.webviewHeightRatio = (WebviewHeightRatio) parcel.readSerializable();
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.shouldHideWebviewShareButton = z;
    }

    public Uri getUrl() {
        return this.url;
    }

    public boolean getIsMessengerExtensionURL() {
        return this.isMessengerExtensionURL;
    }

    @Nullable
    public Uri getFallbackUrl() {
        return this.fallbackUrl;
    }

    @Nullable
    public WebviewHeightRatio getWebviewHeightRatio() {
        return this.webviewHeightRatio;
    }

    public boolean getShouldHideWebviewShareButton() {
        return this.shouldHideWebviewShareButton;
    }
}
