package com.facebook.share.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import java.util.Set;

public class CameraEffectArguments implements ShareModel {
    public static final Creator<CameraEffectArguments> CREATOR = new Creator<CameraEffectArguments>() {
        /* renamed from: a */
        public CameraEffectArguments createFromParcel(Parcel parcel) {
            return new CameraEffectArguments(parcel);
        }

        /* renamed from: a */
        public CameraEffectArguments[] newArray(int i) {
            return new CameraEffectArguments[i];
        }
    };
    /* access modifiers changed from: private */
    public final Bundle params;

    public static class a {
        /* access modifiers changed from: private */
        public Bundle a = new Bundle();

        public a a(CameraEffectArguments cameraEffectArguments) {
            if (cameraEffectArguments != null) {
                this.a.putAll(cameraEffectArguments.params);
            }
            return this;
        }

        public a a(Parcel parcel) {
            return a((CameraEffectArguments) parcel.readParcelable(CameraEffectArguments.class.getClassLoader()));
        }

        public CameraEffectArguments a() {
            return new CameraEffectArguments(this);
        }
    }

    public int describeContents() {
        return 0;
    }

    private CameraEffectArguments(a aVar) {
        this.params = aVar.a;
    }

    CameraEffectArguments(Parcel parcel) {
        this.params = parcel.readBundle(getClass().getClassLoader());
    }

    @Nullable
    public String getString(String str) {
        return this.params.getString(str);
    }

    @Nullable
    public String[] getStringArray(String str) {
        return this.params.getStringArray(str);
    }

    @Nullable
    public Object get(String str) {
        return this.params.get(str);
    }

    public Set<String> keySet() {
        return this.params.keySet();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.params);
    }
}
