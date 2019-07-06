package com.facebook.share.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import java.util.Set;

public class CameraEffectTextures implements ShareModel {
    public static final Creator<CameraEffectTextures> CREATOR = new Creator<CameraEffectTextures>() {
        /* renamed from: a */
        public CameraEffectTextures createFromParcel(Parcel parcel) {
            return new CameraEffectTextures(parcel);
        }

        /* renamed from: a */
        public CameraEffectTextures[] newArray(int i) {
            return new CameraEffectTextures[i];
        }
    };
    /* access modifiers changed from: private */
    public final Bundle textures;

    public static class a {
        /* access modifiers changed from: private */
        public Bundle a = new Bundle();

        public a a(CameraEffectTextures cameraEffectTextures) {
            if (cameraEffectTextures != null) {
                this.a.putAll(cameraEffectTextures.textures);
            }
            return this;
        }

        public a a(Parcel parcel) {
            return a((CameraEffectTextures) parcel.readParcelable(CameraEffectTextures.class.getClassLoader()));
        }

        public CameraEffectTextures a() {
            return new CameraEffectTextures(this);
        }
    }

    public int describeContents() {
        return 0;
    }

    private CameraEffectTextures(a aVar) {
        this.textures = aVar.a;
    }

    CameraEffectTextures(Parcel parcel) {
        this.textures = parcel.readBundle(getClass().getClassLoader());
    }

    @Nullable
    public Bitmap getTextureBitmap(String str) {
        Object obj = this.textures.get(str);
        if (obj instanceof Bitmap) {
            return (Bitmap) obj;
        }
        return null;
    }

    @Nullable
    public Uri getTextureUri(String str) {
        Object obj = this.textures.get(str);
        if (obj instanceof Uri) {
            return (Uri) obj;
        }
        return null;
    }

    @Nullable
    public Object get(String str) {
        return this.textures.get(str);
    }

    public Set<String> keySet() {
        return this.textures.keySet();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.textures);
    }
}
