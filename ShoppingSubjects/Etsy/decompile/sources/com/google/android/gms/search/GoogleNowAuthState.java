package com.google.android.gms.search;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "GoogleNowAuthStateCreator")
@Reserved({1000})
public class GoogleNowAuthState extends AbstractSafeParcelable {
    public static final Creator<GoogleNowAuthState> CREATOR = new zza();
    @Field(getter = "getAuthCode", id = 1)
    private String zzbk;
    @Field(getter = "getAccessToken", id = 2)
    private String zzbl;
    @Field(getter = "getNextAllowedTimeMillis", id = 3)
    private long zzbm;

    @Constructor
    GoogleNowAuthState(@Param(id = 1) String str, @Param(id = 2) String str2, @Param(id = 3) long j) {
        this.zzbk = str;
        this.zzbl = str2;
        this.zzbm = j;
    }

    public String getAccessToken() {
        return this.zzbl;
    }

    public String getAuthCode() {
        return this.zzbk;
    }

    public long getNextAllowedTimeMillis() {
        return this.zzbm;
    }

    public String toString() {
        String str = this.zzbk;
        String str2 = this.zzbl;
        long j = this.zzbm;
        StringBuilder sb = new StringBuilder(74 + String.valueOf(str).length() + String.valueOf(str2).length());
        sb.append("mAuthCode = ");
        sb.append(str);
        sb.append("\nmAccessToken = ");
        sb.append(str2);
        sb.append("\nmNextAllowedTimeMillis = ");
        sb.append(j);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getAuthCode(), false);
        SafeParcelWriter.writeString(parcel, 2, getAccessToken(), false);
        SafeParcelWriter.writeLong(parcel, 3, getNextAllowedTimeMillis());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
