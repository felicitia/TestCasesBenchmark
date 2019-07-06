package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "WalletObjectMessageCreator")
@Reserved({1})
public final class WalletObjectMessage extends AbstractSafeParcelable {
    public static final Creator<WalletObjectMessage> CREATOR = new zzn();
    @Field(id = 2)
    String zzgz;
    @Field(id = 3)
    String zzha;
    @Field(id = 4)
    TimeInterval zzhe;
    @Field(id = 5)
    UriData zzhf;
    @Field(id = 6)
    UriData zzhg;

    public final class a {
        private a() {
        }
    }

    WalletObjectMessage() {
    }

    @Constructor
    WalletObjectMessage(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) TimeInterval timeInterval, @Param(id = 5) UriData uriData, @Param(id = 6) UriData uriData2) {
        this.zzgz = str;
        this.zzha = str2;
        this.zzhe = timeInterval;
        this.zzhf = uriData;
        this.zzhg = uriData2;
    }

    public static a newBuilder() {
        return new a();
    }

    public final UriData getActionUri() {
        return this.zzhf;
    }

    public final String getBody() {
        return this.zzha;
    }

    public final TimeInterval getDisplayInterval() {
        return this.zzhe;
    }

    public final String getHeader() {
        return this.zzgz;
    }

    public final UriData getImageUri() {
        return this.zzhg;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzgz, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzha, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzhe, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzhf, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzhg, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
