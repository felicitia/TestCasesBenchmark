package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class ShareCameraEffectContent extends ShareContent<ShareCameraEffectContent, a> {
    public static final Creator<ShareCameraEffectContent> CREATOR = new Creator<ShareCameraEffectContent>() {
        /* renamed from: a */
        public ShareCameraEffectContent createFromParcel(Parcel parcel) {
            return new ShareCameraEffectContent(parcel);
        }

        /* renamed from: a */
        public ShareCameraEffectContent[] newArray(int i) {
            return new ShareCameraEffectContent[i];
        }
    };
    private CameraEffectArguments arguments;
    private String effectId;
    private CameraEffectTextures textures;

    public static final class a extends com.facebook.share.model.ShareContent.a<ShareCameraEffectContent, a> {
        /* access modifiers changed from: private */
        public String a;
        /* access modifiers changed from: private */
        public CameraEffectArguments b;
        /* access modifiers changed from: private */
        public CameraEffectTextures c;

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a a(CameraEffectArguments cameraEffectArguments) {
            this.b = cameraEffectArguments;
            return this;
        }

        public a a(ShareCameraEffectContent shareCameraEffectContent) {
            if (shareCameraEffectContent == null) {
                return this;
            }
            return ((a) super.a(shareCameraEffectContent)).a(this.a).a(this.b);
        }
    }

    private ShareCameraEffectContent(a aVar) {
        super((com.facebook.share.model.ShareContent.a) aVar);
        this.effectId = aVar.a;
        this.arguments = aVar.b;
        this.textures = aVar.c;
    }

    ShareCameraEffectContent(Parcel parcel) {
        super(parcel);
        this.effectId = parcel.readString();
        this.arguments = new com.facebook.share.model.CameraEffectArguments.a().a(parcel).a();
        this.textures = new com.facebook.share.model.CameraEffectTextures.a().a(parcel).a();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.effectId);
        parcel.writeParcelable(this.arguments, 0);
        parcel.writeParcelable(this.textures, 0);
    }

    public String getEffectId() {
        return this.effectId;
    }

    public CameraEffectArguments getArguments() {
        return this.arguments;
    }

    public CameraEffectTextures getTextures() {
        return this.textures;
    }
}
