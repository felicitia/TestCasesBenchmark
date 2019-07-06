package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class GoogleSignInOptionsExtensionParcelable extends AbstractSafeParcelable {
    public static final Creator<GoogleSignInOptionsExtensionParcelable> CREATOR = new GoogleSignInOptionsExtensionCreator();
    private Bundle mBundle;
    private final int versionCode;
    private int zzac;

    GoogleSignInOptionsExtensionParcelable(int i, int i2, Bundle bundle) {
        this.versionCode = i;
        this.zzac = i2;
        this.mBundle = bundle;
    }

    public Bundle getBundle() {
        return this.mBundle;
    }

    public int getType() {
        return this.zzac;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeInt(parcel, 2, getType());
        SafeParcelWriter.writeBundle(parcel, 3, getBundle(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
