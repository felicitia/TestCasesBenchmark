package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "IdTokenCreator")
@Reserved({1000})
public final class IdToken extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<IdToken> CREATOR = new zzl();
    @Field(getter = "getAccountType", id = 1)
    @NonNull
    private final String zzbx;
    @Field(getter = "getIdToken", id = 2)
    @NonNull
    private final String zzdf;

    @Constructor
    public IdToken(@Param(id = 1) @NonNull String str, @Param(id = 2) @NonNull String str2) {
        Preconditions.checkArgument(!TextUtils.isEmpty(str), "account type string cannot be null or empty");
        Preconditions.checkArgument(!TextUtils.isEmpty(str2), "id token string cannot be null or empty");
        this.zzbx = str;
        this.zzdf = str2;
    }

    @NonNull
    public final String getAccountType() {
        return this.zzbx;
    }

    @NonNull
    public final String getIdToken() {
        return this.zzdf;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getAccountType(), false);
        SafeParcelWriter.writeString(parcel, 2, getIdToken(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
